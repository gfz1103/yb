package com.buit.his.gpo.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.TypeReference;
import com.buit.commons.BaseException;
import com.buit.constans.ErrorCode;
import com.buit.constans.TableName;
import com.buit.his.gpo.controller.request.GpoBaseRequest;
import com.buit.his.gpo.controller.response.SpResp;
import com.buit.his.gpo.dao.*;
import com.buit.his.gpo.dto.YpbzxxDto;
import com.buit.his.gpo.dto.YpbzzhDto;
import com.buit.his.gpo.dto.YqJhdwDto;
import com.buit.his.gpo.model.*;
import com.buit.his.gpo.ws.GpoConsts;
import com.buit.his.gpo.ws.GpoWebService;
import com.buit.his.gpo.ws.GpoZidian;
import com.buit.his.gpo.ws.request.DdYY009ReqMain;
import com.buit.his.gpo.ws.request.DdYY009ReqStruct;
import com.buit.his.gpo.ws.request.DdYY010ReqMain;
import com.buit.his.gpo.ws.response.DdYY009RespMain;
import com.buit.his.gpo.ws.response.DdYY009RespStruct;
import com.buit.his.gpo.ws.xml.*;
import com.buit.his.medinsuinterface.sh.dataitem.dao.ShybYpjcxxDao;
import com.buit.utill.RedisFactory;
import com.buit.utill.TimestampUtil;
import com.github.pagehelper.PageHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author sunjx
 * @Date 2020-11-10 10:31
 * @Description 订单
 **/
@Service
public class DdService {
    static final Logger log = LoggerFactory.getLogger(DdService.class);
    @Autowired
    private RedisFactory redisFactory;

    @Autowired
    private GpoWebService webService;

    @Autowired
    private GpoDdDao ddDao;

    @Autowired
    ShybYpjcxxDao shybYpjcxxDao;

    @Autowired
    private GpoYqJhdwDao yqJhdwDao;

    @Autowired
    private GpoDdmxDao ddmxDao;

    @Autowired
    private GpoDao gpoDao;

    @Autowired
    private GpoService gpoService;

    @Autowired
    private GpoMryqDao gpoMryqDao;

    @Transactional(rollbackFor = Exception.class)
    public Integer save(GpoDd dd) {
        log.info("保存填报单[{}]",JSONUtil.toJsonStr(dd));
        //订单明细不能为空
        if (CollUtil.isEmpty(dd.getDdmxes())) {
            throw BaseException.create(ErrorCode.ERROR_GPO_DD_0005);
        }
        List<String> sptbdm = dd.getDdmxes().stream().map(m->m.getZxspbm()).collect(Collectors.toList());
        String yxrq = DateUtil.format(TimestampUtil.now(), "yyyyMMdd");
        List<SpResp> spRespList = gpoDao.queryByTbbmAndYxrq(sptbdm,yxrq);
        Map<String,SpResp> spRespMap = spRespList.stream().collect(Collectors.toMap(SpResp::getTBDM,s->s,(s1,s2)->s1));

        List<GpoDdmx> gpoDdmxList;
        List<Integer> deleteDdxhList;
        GpoJgxx gpoJgxx = gpoService.gpoJgbm(dd.getJgid());
        if (dd.getXh() == null) {
            deleteDdxhList = new ArrayList<>();
            Map<String,List<GpoDdmx>> mxList = dd.getDdmxes().stream().collect(Collectors.groupingBy(m->m.getYqbm()));
            List<String> yqbmList = mxList.keySet().stream().collect(Collectors.toList());
            List<YqJhdwDto> yqJhdwDtoList = yqJhdwDao.queryJhdwxx(yqbmList);
            Map<String,String> jhdwMap = yqJhdwDtoList.stream().collect(Collectors.toMap(YqJhdwDto::getYqbm,YqJhdwDto::getJhdwId));
            List<GpoDd> gpoDdList = mxList.keySet().stream().map(k->{
                GpoDd gpoDd = BeanUtil.toBean(dd,GpoDd.class);
                gpoDd.setXh(redisFactory.getTableKey(TableName.DB_NAME, TableName.TB_NAME_GPO_DD));
                gpoDd.setGpoJgid(gpoJgxx.getGpoJgdm());
                gpoDd.setYybm(gpoJgxx.getGpoJgdm());
                gpoDd.setJhdwbm(jhdwMap.get(k));
                gpoDd.setZt(GpoConsts.ZT.YX.getCode());
                gpoDd.setCbzt(GpoConsts.CBZT.DCB.getCode());
                gpoDd.setZfzt(GpoConsts.ZFZT.YX.getCode());
                gpoDd.setQrzt(GpoConsts.QRZT.DQR.getCode());
                gpoDd.setCtime(TimestampUtil.now());
                gpoDd.setCuser(dd.getCuser());
                gpoDd.setCuname(dd.getCuname());
                deleteDdxhList.add(gpoDd.getXh());
                mxList.get(k).forEach(m->m.setDdxh(gpoDd.getXh()));
                gpoDd.setYqbm(mxList.get(k).get(0).getYqbm());
                return gpoDd;
            }).collect(Collectors.toList());
            ddDao.batchInsert(gpoDdList);
            gpoDdmxList = mxList.values().stream().flatMap(l->l.stream()).collect(Collectors.toList());
        } else {
            GpoDd update = ddDao.getById(dd.getXh());
            if (null == update) {
                throw BaseException.create(ErrorCode.ERROR_GPO_DD_0001);
            }

            //已经传报的订单表不能再修改
            if (update.getCbzt().equals(GpoConsts.CBZT.YCB.getCode())) {
                throw BaseException.create(ErrorCode.ERROR_GPO_DD_0004);
            }

            BeanUtil.copyProperties(dd, update, CopyOptions.create().ignoreNullValue());
            String yqbm = dd.getDdmxes().get(0).getYqbm();
            update.setJhdwbm(yqJhdwDao.jhdwbm(yqbm));
            ddDao.update(update);
            gpoDdmxList = dd.getDdmxes();
            deleteDdxhList = Arrays.asList(dd.getXh());
        }

        //删除全部订单明细
        ddmxDao.deleteByDdxhList(deleteDdxhList);

        gpoDdmxList.forEach(d->{
            SpResp sp = spRespMap.get(d.getZxspbm());
            d.setXh(redisFactory.getTableKey(TableName.DB_NAME, TableName.TB_NAME_GPO_DDMX));
            d.setJgid(dd.getJgid());
            d.setGpoJgid(gpoJgxx.getGpoJgdm());
            if(d.getDdxh()==null){
                d.setDdxh(dd.getXh());
            }
            //采购类型
            d.setCglx(d.getCglx());
            //商品类型
            d.setSplx(GpoZidian.SPLX.YPL.getCode());
            //规格包装
            d.setGgbz(sp.getGG());
            //采购单价
//            d.setCgdj(sp.getCGDJ());
            //医院商品编码
            d.setYyspbm(sp.getSPID());

            d.setZt(GpoConsts.ZT.YX.getCode());
            d.setCtime(TimestampUtil.now());
            d.setCuser(dd.getCuser());
            d.setCuname(dd.getCuname());
        });
        ddmxDao.batchInsert(gpoDdmxList);
        List<GpoMryq> gpoMryqList = gpoDdmxList.stream().map(d->{
            GpoMryq mryq = new GpoMryq();
            mryq.setTbdm(d.getZxspbm());
            mryq.setYqbm(d.getYqbm());
            mryq.setYqmc(d.getYqmc());
            return mryq;
        }).collect(Collectors.toList());
        gpoMryqDao.save(gpoMryqList);
        return dd.getXh();
    }

    public void delete(Integer xh, Integer userId) {
        GpoDd dd = ddDao.getById(xh);
        if (null == dd) {
            throw BaseException.create(ErrorCode.ERROR_GPO_DD_0001);
        }

        if (dd.getCbzt().equals(GpoConsts.CBZT.YCB.getCode())) {
            throw BaseException.create(ErrorCode.ERROR_GPO_DD_0002);
        }

        dd.setZt(GpoConsts.ZT.SC.getCode());
        dd.setScrdm(String.valueOf(userId));
        dd.setScsj(TimestampUtil.now());
        ddDao.update(dd);
    }

//    /**
//     * 传报操作
//     *
//     * @param czlx        操作类型，新增和修改
//     * @param xh
//     * @param baseRequest
//     */
//    void upload(String czlx, Integer xh, GpoBaseRequest baseRequest) {
//        GpoDd dd = ddDao.getById(xh);
//        if (null == dd) {
//            throw BaseException.create(ErrorCode.ERROR_GPO_DD_0001);
//        }
//
//        if (dd.getQrzt().equals(GpoConsts.CBZT.YCB.getCode())) {
//            throw BaseException.create(ErrorCode.ERROR_GPO_DD_0003);
//        }
//
//        List<GpoDdmx> ddmxes = ddmxDao.details(xh);
//
//        List<Struct<DdYY009ReqStruct>> structs = new ArrayList<>();
//        for (GpoDdmx mx : ddmxes) {
//            DdYY009ReqStruct ddmxStruct = new DdYY009ReqStruct();
//            ddmxStruct.setSXH(mx.getSxh());
//            ddmxStruct.setCGLX(mx.getCglx());
//            ddmxStruct.setSPLX(mx.getSplx());
//            ddmxStruct.setZXSPBM(mx.getZxspbm());
//            ddmxStruct.setGGBZ(mx.getGgbz());
//            ddmxStruct.setCGJLDW(mx.getCgjldw());
//            ddmxStruct.setCGSL(mx.getCgsl());
//            ddmxStruct.setCGDJ(mx.getCgdj());
//            ddmxStruct.setYQBM(mx.getYqbm());
//            ddmxStruct.setDCPSBS(mx.getDcpsbs());
//            ddmxStruct.setBZSM(Optional.ofNullable(mx.getBzsm()).orElse("123"));
//
//            structs.add(new Struct<>(ddmxStruct));
//        }
//
//        DdYY009ReqMain main = new DdYY009ReqMain();
//        main.setCZLX(GpoZidian.CZLX.XZ.getVal());
//        main.setYYBM(gpoService.gpoJgbm(baseRequest.getJgid()).getGpoJgdm());
//        main.setPSDBM(dd.getPsdbm());
//        main.setDDLX(dd.getDdlx());
//        main.setDDBH(Optional.ofNullable(dd.getDdbh()).orElse(""));
//        main.setYYJHDH(dd.getYyjhdh());
//        main.setZWDHRQ(dd.getZwdhrq());
//        main.setJLS(structs.size());
//        main.setYYJHDH(Optional.ofNullable(dd.getYyjhdh()).orElse(""));
//        main.setZWDHRQ(Optional.ofNullable(dd.getZwdhrq()).orElse(""));
//
//        XmlData<DdYY009RespMain, DdYY009RespStruct> xmldata = webService.ddtbYY009(new XmlData<>(new Head(baseRequest.getIp(), baseRequest.getMac(), null), main, structs), baseRequest);
//
//        //  订单编号, 明细顺序号
//        dd.setDdbh(xmldata.getMAIN().getDDBH());
//        dd.setCbrdm(baseRequest.getUserid());
//        dd.setCbsj(TimestampUtil.now());
//        dd.setCbzt(GpoConsts.CBZT.YCB.getCode());
//        ddDao.update(dd);
//
//        if (CollUtil.isNotEmpty(xmldata.getDETAIL())) {
//            for (Struct<DdYY009RespStruct> struct : xmldata.getDETAIL()) {
//                Map<String, Object> stringObjectMap = CommonMapUtils.objToMap(struct);
//                DdYY009RespStruct ddYY009RespStruct = JSONUtil.toBean(stringObjectMap.get("struct").toString(), DdYY009RespStruct.class);
//                for (GpoDdmx mx : ddmxes) {
//                    if (ddYY009RespStruct.getSXH().equals(mx.getSxh())) {
//                        mx.setDdbh(xmldata.getMAIN().getDDBH());
//                        mx.setDdmxbh(ddYY009RespStruct.getDDMXBH());
//                        mx.setCljg(ddYY009RespStruct.getCLJG());
//                        mx.setClqkms(ddYY009RespStruct.getCLQKMS());
//                        ddmxDao.update(mx);
//                        break;
//                    }
//                }
//            }
//        }
//    }

    @Transactional(rollbackFor = Exception.class)
    public void upload(Integer xh, GpoBaseRequest baseRequest) {
        GpoDd dd = ddDao.getById(xh);
        if (null == dd) {
            throw BaseException.create(ErrorCode.ERROR_GPO_DD_0001);
        }

        if (dd.getCbzt().equals(GpoConsts.CBZT.YCB.getCode())) {
            throw BaseException.create(ErrorCode.ERROR_GPO_DD_0003);
        }

        List<GpoDdmx> ddmxes = ddmxDao.details(xh);

        List<Struct<DdYY009ReqStruct>> structs = new ArrayList<>();
        for (GpoDdmx mx : ddmxes) {
            DdYY009ReqStruct ddmxStruct = new DdYY009ReqStruct();
            ddmxStruct.setSXH(mx.getSxh());
            ddmxStruct.setCGLX(mx.getCglx());
            ddmxStruct.setSPLX(mx.getSplx());
            ddmxStruct.setZXSPBM(mx.getZxspbm());
            ddmxStruct.setGGBZ(mx.getGgbz());
            ddmxStruct.setCGJLDW("1");
            ddmxStruct.setCGSL(mx.getCgsl());
            ddmxStruct.setCGDJ(mx.getCgdj());
            ddmxStruct.setYQBM(mx.getYqbm());
            ddmxStruct.setDCPSBS(mx.getDcpsbs());
            ddmxStruct.setBZSM(mx.getBzsm() == null ? "" : mx.getBzsm());

            structs.add(new Struct<>(ddmxStruct));
        }

        DdYY009ReqMain main = new DdYY009ReqMain();
        main.setCZLX(GpoZidian.CZLX.XZ.getVal());
        main.setYYBM(gpoService.gpoJgbm(baseRequest.getJgid()).getGpoJgdm());
        main.setPSDBM(dd.getPsdbm());
        main.setDDLX(dd.getDdlx());
        main.setDDBH(Optional.ofNullable(dd.getDdbh()).orElse(""));
        main.setYYJHDH(dd.getYyjhdh());
        main.setZWDHRQ(dd.getZwdhrq());
        main.setJLS(structs.size());
        main.setYYJHDH(Optional.ofNullable(dd.getYyjhdh()).orElse(""));
        main.setZWDHRQ(Optional.ofNullable(dd.getZwdhrq()).orElse(""));

        XmlData<DdYY009RespMain, Object> xmldata = webService.ddtbYY009(new XmlData<>(new Head(baseRequest.getIp(), baseRequest.getMac(), null), main, structs), baseRequest);

        //  订单编号, 明细顺序号
        dd.setDdbh(xmldata.getMAIN().getDDBH());
        dd.setCbrdm(baseRequest.getUserid());
        dd.setCbsj(TimestampUtil.now());
        dd.setCbzt(GpoConsts.CBZT.YCB.getCode());
        ddDao.update(dd);

        if (CollUtil.isNotEmpty(xmldata.getDETAIL())) {
            Map<String,DdYY009RespStruct> structMap = xmldata.getDETAIL().stream().flatMap(s->{
                if(s.getSTRUCT() instanceof JSONArray){
                    List<DdYY009RespStruct> list = JSONUtil.toList((JSONArray) s.getSTRUCT(),DdYY009RespStruct.class);
                    return list.stream();
                }else{
                    DdYY009RespStruct ddYY009RespStruct = JSONUtil.toBean((JSONObject) s.getSTRUCT(), DdYY009RespStruct.class);
                    return Arrays.asList(ddYY009RespStruct).stream();
                }
            }).collect(Collectors.toMap(s->s.getSXH(),s->s));
            ddmxes.forEach(d->{
                DdYY009RespStruct struct = structMap.get(d.getSxh());
                d.setDdbh(xmldata.getMAIN().getDDBH());
                d.setDdmxbh(struct.getDDMXBH());
                d.setCljg(struct.getCLJG());
                d.setClqkms(struct.getCLQKMS());
                ddmxDao.update(d);
            });
        }
    }

    public static void main(String[] args) {
        String s = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><XMLDATA><HEAD><JSSJ>20210320/180750/</JSSJ><ZTCLJG>00000</ZTCLJG><CWXX/><BZXX>0CAB36593AF03707BC51F7476F9F3D8363ACD3B9</BZXX></HEAD><MAIN><DDBH>20210320000000139816</DDBH></MAIN><DETAIL><STRUCT><DDMXBH>20210320000000196101</DDMXBH><SXH>1</SXH><SPLX>1</SPLX><ZXSPBM>X00028400030010</ZXSPBM><YYSPBM/><CLJG>00000</CLJG><CLQKMS>处理成功,药企库存情况:没有上传库存</CLQKMS></STRUCT></DETAIL></XMLDATA>";
        XmlData<DdYY009RespMain, DdYY009RespStruct> data = GpoXmlUtil.toObj(s, new TypeReference<XmlData<DdYY009RespMain, DdYY009RespStruct>>() {


        });
        List<Struct<DdYY009RespStruct>> detail1 = data.getDETAIL();
        for (Struct<DdYY009RespStruct> ddYY009RespStructStruct : detail1) {
            Map<String, Object> stringObjectMap = CommonMapUtils.objToMap(ddYY009RespStructStruct);
            System.out.println(stringObjectMap);
            Object struct = stringObjectMap.get("struct");
            System.out.println(struct);
            DdYY009RespStruct ddYY009RespStruct = JSONUtil.toBean(struct.toString(), DdYY009RespStruct.class);
            System.out.println(ddYY009RespStruct.getSXH());

        }

        // DdYY009RespStruct ddYY009RespStruct = JSONUtil.toBean(s1, DdYY009RespStruct.class);
        // System.out.println(ddYY009RespStruct);

        String ss = "{\"CLQKMS\":\"处理成功,药企库存情况:没有上传库存\",\"SXH\":\"1\",\"YYSPBM\":\"\",\"ZXSPBM\":\"X00028400030010\",\"CLJG\":\"00000\",\"DDMXBH\":\"20210320000000196101\",\"SPLX\":\"1\"}";
        DdYY009RespStruct ddYY009RespStruct = JSONUtil.toBean(ss, DdYY009RespStruct.class);
        System.out.println(ddYY009RespStruct);
    }

    @Transactional(rollbackFor = Exception.class)
    public void confirm(Integer xh, GpoBaseRequest baseRequest) {
        GpoDd dd = ddDao.getById(xh);
        if (null == dd) {
            throw BaseException.create(ErrorCode.ERROR_GPO_DD_0001);
        }

        //已作废
        if (dd.getQrzt().equals(GpoConsts.ZFZT.YZF.getCode())) {
            throw BaseException.create(ErrorCode.ERROR_GPO_DD_0006);
        }

        //已确认
        if (dd.getQrzt().equals(GpoConsts.QRZT.YQR.getCode())) {
            throw BaseException.create(ErrorCode.ERROR_GPO_DD_0007);
        }

        //待传报
        if (dd.getCbzt().equals(GpoConsts.CBZT.DCB.getCode())) {
            throw BaseException.create(ErrorCode.ERROR_GPO_DD_0008);
        }

        long mxCount = PageHelper.count(() -> ddmxDao.details(xh));

        DdYY010ReqMain main = new DdYY010ReqMain();
        main.setYYBM(dd.getYybm());
        main.setPSDBM(dd.getPsdbm());
        main.setDDLX(dd.getDdlx());
        main.setDDBH(dd.getDdbh());
        main.setSPSL((int) mxCount);

        XmlData xmlData = webService.ddtbqrYY010(new XmlData<>(new Head(baseRequest.getIp(), baseRequest.getMac(), null), main, null), baseRequest);

        if ("00000".equals(xmlData.getHEAD().getZTCLJG())) {
            dd.setQrzt("1");
            dd.setQrrdm(baseRequest.getUserid());
            ddDao.update(dd);
        } else {
            throw BaseException.create(ErrorCode.ERROR_GPO_DD_0010);
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public void disable(Integer xh, GpoBaseRequest baseRequest) {
        GpoDd dd = ddDao.getById(xh);
        if (null == dd) {
            throw BaseException.create(ErrorCode.ERROR_GPO_DD_0001);
        }

        if (dd.getQrzt().equals(GpoConsts.QRZT.YQR.getCode())) {
            throw BaseException.create(ErrorCode.ERROR_GPO_DD_0009);
        }

        List<GpoDdmx> ddmxes = ddmxDao.details(xh);

        List<Struct<DdYY009ReqStruct>> structs = new ArrayList<>();
        for (GpoDdmx mx : ddmxes) {
            DdYY009ReqStruct ddmxStruct = new DdYY009ReqStruct();
            ddmxStruct.setSXH(mx.getSxh());
            ddmxStruct.setCGLX(mx.getCglx());
            ddmxStruct.setSPLX(mx.getSplx());
            ddmxStruct.setZXSPBM(mx.getZxspbm());
            ddmxStruct.setGGBZ(mx.getGgbz());
            ddmxStruct.setCGJLDW(mx.getCgjldw());
            ddmxStruct.setCGSL(mx.getCgsl());
            ddmxStruct.setCGDJ(mx.getCgdj());
            ddmxStruct.setYQBM(mx.getYqbm());
            ddmxStruct.setDCPSBS(mx.getDcpsbs());
            ddmxStruct.setBZSM(mx.getBzsm() == null ? "" : mx.getBzsm());
            structs.add(new Struct<>(ddmxStruct));
        }

        DdYY009ReqMain main = new DdYY009ReqMain();
        main.setCZLX(GpoZidian.CZLX.ZF.getVal());
        main.setYYBM(gpoService.gpoJgbm(baseRequest.getJgid()).getJgid());
        main.setPSDBM(dd.getPsdbm());
        main.setDDLX(dd.getDdlx());
        main.setDDBH(dd.getDdbh());
        main.setYYJHDH(dd.getYyjhdh() == null ? "" : dd.getYyjhdh());
        main.setZWDHRQ(dd.getZwdhrq() == null ? "" : dd.getZwdhrq());
        main.setJLS(structs.size());
//        XmlData<DdYY009RespMain, DdYY009RespStruct> xmldata = webService.ddtbYY009(new XmlData<>(new Head(baseRequest.getIp(), baseRequest.getMac(), null), main, structs), baseRequest);
        dd.setZfzt(GpoConsts.ZFZT.YZF.getCode());
        dd.setZfrdm(String.valueOf(baseRequest.getUserid()));
        dd.setZfsj(TimestampUtil.now());
        ddDao.update(dd);
    }

    public List<GpoDd> page(
            Integer yklx,
            Integer jgid,
            Timestamp rqks,
            Timestamp rqjz,
            String yqmb,
            List<String> cbzt,
            String qrzt,
            String zfzt) {
        List<GpoDd> page = ddDao.page(yklx, jgid, rqks, rqjz, yqmb, cbzt, qrzt, zfzt);
        BigDecimal lszje = new BigDecimal(0);
        BigDecimal jjzje = new BigDecimal(0);
        for (GpoDd gpoDd : page) {
            gpoDd.setYqmc(ddDao.selectYqmc(gpoDd.getYqbm()));
            List<MoneySum> details = ddmxDao.JeSum(gpoDd.getXh());
            for (MoneySum detail : details) {
                lszje=  lszje.add(detail.getLszje()==null? new BigDecimal(0):detail.getLszje()).setScale(2, RoundingMode.HALF_UP);
                jjzje=  jjzje.add(detail.getJjzje()==null? new BigDecimal(0):detail.getLszje()).setScale(2, RoundingMode.HALF_UP);
            }
            gpoDd.setLszje(lszje);
            gpoDd.setJjzje(jjzje);
        }
        return page;
    }

    public GpoDd detail(Integer xh, String hospitalIdStr) {
        GpoDd byId = ddDao.getById(xh);
        byId.setDdmxes(ddmxDao.details(xh));
        byId.setYqmc(ddDao.selectYqmc(byId.getYqbm()));
        List<String> tbdmList = byId.getDdmxes().stream().map(d->d.getZxspbm()).collect(Collectors.toList());
        List<YpbzxxDto> ypbzxxDtoList = shybYpjcxxDao.queryYpbzxx(hospitalIdStr,tbdmList);
        Map<String,YpbzxxDto> ypbzxxMap = ypbzxxDtoList.stream().collect(Collectors.toMap(YpbzxxDto::getTbdm,v->v,(v1,v2)->v1));
        byId.getDdmxes().forEach(d->{
            YpbzxxDto ypbzxxDto = ypbzxxMap.get(d.getZxspbm());
            d.setCdmc(ypbzxxDto.getCdmc());
            YpbzzhDto jjbz = new YpbzzhDto();
            jjbz.setBzsl(1);
            jjbz.setDwmc(ypbzxxDto.getJjdw());
            jjbz.setBzjg(ypbzxxDto.getCgdj());
            YpbzzhDto bfbz = new YpbzzhDto();
            bfbz.setBzsl(ypbzxxDto.getJjbz());
            bfbz.setDwmc(ypbzxxDto.getBfdw());
            bfbz.setBzjg(ypbzxxDto.getBfdj());
            List<YpbzzhDto> bzzhList = new ArrayList<>();
            bzzhList.add(jjbz);
            if(bfbz.getBzsl()!=1){
                bzzhList.add(bfbz);
            }
            d.setZhbzList(bzzhList);
        });
        return byId;
    }

    @Transactional(rollbackFor = Exception.class)
    public Object update(GpoDd dd) {
        GpoDd update = ddDao.getById(dd.getXh());
        if (null == update) {
            throw BaseException.create(ErrorCode.ERROR_GPO_DD_0001);
        }
        //已经传报的订单表不能再修改
        if (update.getCbzt().equals(GpoConsts.CBZT.YCB.getCode())) {
            throw BaseException.create(ErrorCode.ERROR_GPO_DD_0004);
        }
        BeanUtil.copyProperties(dd, update, CopyOptions.create().ignoreNullValue());
        update.setJhdwbm(yqJhdwDao.jhdwbm(dd.getYqbm()));
        ddDao.update(update);
        //删除全部订单明细
        ddmxDao.deleteByDdxh(dd.getXh());
        //订单明细不能为空
        if (CollUtil.isEmpty(dd.getDdmxes())) {
            throw BaseException.create(ErrorCode.ERROR_GPO_DD_0005);
        }

        for (GpoDdmx ddmx : dd.getDdmxes()) {
            //根据商品统编代码，查询药品 ggbz, cgjldw, cgdj, yqbm,
            ddmx.setXh(redisFactory.getTableKey(TableName.DB_NAME, TableName.TB_NAME_GPO_DDMX));
            ddmx.setCtime(TimestampUtil.now());
            ddmx.setCuser(update.getCuser());
            ddmx.setCuname(update.getCuname());
            ddmxDao.insert(ddmx);
        }

        return dd.getXh();
    }
}
