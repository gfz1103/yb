package com.buit.his.gpo.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.buit.aop.lock.Locked;
import com.buit.commons.BaseException;
import com.buit.commons.SysUser;
import com.buit.constans.TableName;
import com.buit.drug.dto.IDrugsRk02;
import com.buit.drug.request.IDrugsRkReq;
import com.buit.drug.service.DrugService;
import com.buit.his.gpo.controller.request.GpoBaseRequest;
import com.buit.his.gpo.controller.request.PsdmxysRequest;
import com.buit.his.gpo.controller.request.PsdsjRequest;
import com.buit.his.gpo.controller.request.QueryPsdReq;
import com.buit.his.gpo.controller.response.QueryPsdResp;
import com.buit.his.gpo.dao.GpoDao;
import com.buit.his.gpo.dao.GpoDdmxDao;
import com.buit.his.gpo.dao.GpoPsdysDao;
import com.buit.his.gpo.dao.GpoYqJhdwDao;
import com.buit.his.gpo.model.GpoDdmx;
import com.buit.his.gpo.model.GpoPsdys;
import com.buit.his.gpo.service.result.YpcdjgDto;
import com.buit.his.gpo.ws.GpoConsts;
import com.buit.his.gpo.ws.GpoWebService;
import com.buit.his.gpo.ws.GpoZidian;
import com.buit.his.gpo.ws.request.PsdYY003ReqMain;
import com.buit.his.gpo.ws.request.PsdYY006ReqMain;
import com.buit.his.gpo.ws.request.PsdYY018ReqMain;
import com.buit.his.gpo.ws.response.*;
import com.buit.his.gpo.ws.xml.*;
import com.buit.his.shyb.source.service.impl.OfflineSettleServiceImpl;
import com.buit.utill.BeanUtil;
import com.buit.utill.RedisFactory;
import com.buit.utill.TimestampUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author sunjx
 * @Date 2020-11-12 10:10
 * @Description
 **/
@Service
public class PsdService {
    static final Logger log = LoggerFactory.getLogger(PsdService.class);
    @Autowired
    private GpoWebService webService;

    @Autowired
    GpoYqJhdwDao gpoYqJhdwDao;

    @Autowired
    GpoDao gpoDao;
    @Autowired
    GpoDdmxDao gpoDdmxDao;
    @Autowired
    GpoPsdysDao gpoPsdysDao;

    @DubboReference
    DrugService drugService;

    @Autowired
    private RedisFactory redisFactory;

    @Transactional(rollbackFor = Exception.class)
    @Locked("gpo_psdys_#[req.*ysmxs.PSMXBH]")
    public void ys(PsdmxysRequest req, SysUser user) {
        log.info("验收配送单[{}]",JSONUtil.toJsonStr(req));
        IDrugsRkReq rkReq = new IDrugsRkReq();
        rkReq.setJgid(user.getHospitalId());
        rkReq.setYksb(req.getYksb());
        rkReq.setPwd(req.getPwd());
        rkReq.setDwxh(Integer.valueOf(gpoYqJhdwDao.jhdwbm(req.getYqbm())));
        rkReq.setCgrq(TimestampUtil.now());
        rkReq.setCggh(user.getUserId());
        rkReq.setCzgh(user.getUserId());

        List<GpoPsdys> gpoPsdysList = req.getYsmxs().stream().map(y->{
            GpoPsdys gpoPsdys = new GpoPsdys();
            gpoPsdys.setXh(redisFactory.getTableKey(TableName.DB_NAME ,TableName.TB_NAME_GPO_PSDYS));
            gpoPsdys.setPsdh(y.getPSDH());
            gpoPsdys.setPsdbm(y.getPSDBM());
            gpoPsdys.setDdmxbm(y.getDDMXBH());
            gpoPsdys.setJhdh(y.getJHDH());
            gpoPsdys.setXsddh(y.getXSDDH());
            gpoPsdys.setYplx(req.getYklx());
            gpoPsdys.setYxrq(y.getYXRQ());
            gpoPsdys.setYqbm(req.getYqbm());
            gpoPsdys.setPsmxbh(y.getPSMXBH());
            gpoPsdys.setPsdtm(y.getPSDTM());
            gpoPsdys.setZxspbm(y.getZXSPBM());
            gpoPsdys.setPsl(y.getPSL());
            gpoPsdys.setScph(y.getSCPH());
            gpoPsdys.setYsjg(req.getYsjg());
            gpoPsdys.setYsjgbz(req.getYsjgbz());
            gpoPsdys.setCtime(new Timestamp(System.currentTimeMillis()));
            gpoPsdys.setCuser(user.getUserIdStr());
            gpoPsdys.setCuname(user.getUserName());
            return gpoPsdys;
        }).collect(Collectors.toList());
        gpoPsdysDao.batchInsert(gpoPsdysList);

        List<String> tbdmList = req.getYsmxs().stream().map(s->s.getZXSPBM()).collect(Collectors.toList());
        List<YpcdjgDto> ypcdjgList = gpoDao.queryByTbdm(user.getHospitalIdStr(),tbdmList);
        Map<String,YpcdjgDto> ypcdjgDtoMap = ypcdjgList.stream().collect(Collectors.toMap(y->y.getYbbm(),y->y,(y1,y2)->y1));
        List<String> ddmxbhList = req.getYsmxs().stream().map(r->r.getDDMXBH()).collect(Collectors.toList());
        List<GpoDdmx> gpoDdmxList = gpoDdmxDao.queryByddmxbh(ddmxbhList);
        Map<String,GpoDdmx> gpoDdmxMap = gpoDdmxList.stream().collect(Collectors.toMap(GpoDdmx::getDdmxbh,v->v));
        List<String> notExistsYp = req.getYsmxs().stream().filter(r->!ypcdjgDtoMap.containsKey(r.getZXSPBM())).map(r->r.getCPM()).collect(Collectors.toList());
        if(!notExistsYp.isEmpty()){
            String mc = String.join(",",notExistsYp);
            throw BaseException.create("ERROR_GPO_0002",new String[]{mc});
        }
        List<IDrugsRk02> rk02List = req.getYsmxs().stream()
                .collect(Collectors.groupingBy(d->String.format("%s,%s,%s",d.getZXSPBM(),d.getSCPH(),d.getYXRQ())))
                .values().stream().map(l->{
                    PsdYY003RespStruct struct = l.get(0);
                    GpoDdmx gpoDdmx = gpoDdmxMap.get(struct.getDDMXBH());
                    IDrugsRk02 rk02 = new IDrugsRk02();
                    BigDecimal rksl = l.stream().map(d->{
                        if(gpoDdmx!=null) {
                            return new BigDecimal(d.getPSL()).divide(BigDecimal.valueOf(gpoDdmx.getBzzh()));
                        }else{
                            return new BigDecimal(d.getPSL());
                        }
                    }).reduce((s,v)->s.add(v)).get();
                    rk02.setRksl(rksl);
                    YpcdjgDto ypcdjgDto = ypcdjgDtoMap.get(struct.getZXSPBM());
                    rk02.setYpcd(Integer.parseInt(ypcdjgDto.getYpcd()));
                    rk02.setYpxh(Integer.parseInt(ypcdjgDto.getYpxh()));
                    rk02.setYpph(struct.getSCPH());
                    rk02.setYpxq(Timestamp.valueOf(LocalDate.parse(struct.getYXRQ(), DateTimeFormatter.ofPattern("yyyyMMdd")).atTime(LocalTime.MIN)));
                    return rk02;
                }).collect(Collectors.toList());
        rkReq.setRk02List(rk02List);
        req.getYsmxs().stream().forEach(r->{
            PsdYY018ReqMain main = new PsdYY018ReqMain();
            main.setPSDTM(r.getPSDTM());
            main.setPSL(r.getPSL());
            main.setYQBM(req.getYqbm());
            main.setPSMXBH(r.getPSMXBH());
            main.setPSDTM(r.getPSDTM());
            main.setZXSPBM(r.getZXSPBM());
            main.setSCPH(r.getSCPH());
            main.setYSJG(req.getYsjg().toString());
            main.setYSJGBZ(req.getYsjgbz()==null?"":req.getYsjgbz());
            webService.psmxysYY018(new XmlData<>(new Head(req.getIp(), req.getMac(), null), main, null), req);
        });
        drugService.addDrugsRk(rkReq);
    }

    public QueryPsdResp hqpsdmx(QueryPsdReq req, SysUser user) {
        log.info("查询未验收配送单[{},{}]",JSONUtil.toJsonStr(req),JSONUtil.toJsonStr(user));
        QueryPsdResp resp = BeanUtil.toBean(req,QueryPsdResp.class);
        resp.addSeq(req.getNextNo());
        resp.setBackNo(req.getNextNo());
        PsdYY003ReqMain main = new PsdYY003ReqMain();
        main.setYQBM(req.getYqbm());
        main.setPSMXBH(req.getNextNo()==null?"":req.getNextNo());
        GpoBaseRequest baseRequest = new GpoBaseRequest(req.getIp(),req.getMac(),GpoConsts.YKLX.getByKey(req.getYklb()),user);
        List<PsdYY003RespStruct> respList = new ArrayList<>();
//        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><XMLDATA><HEAD><JSSJ>20210429/154636/</JSSJ><ZTCLJG>00000</ZTCLJG><CWXX></CWXX><BZXX></BZXX></HEAD><MAIN><JLS>5</JLS><SFWJ>1</SFWJ></MAIN><DETAIL><STRUCT><PSDH>ps210000000000347</PSDH><YQBM>913416005663861331</YQBM><PSDBM>4</PSDBM><CJRQ>20210420/000000/</CJRQ><PSMXBH>20210420000833474616</PSMXBH><PSDTM>ahjsxb0000368001</PSDTM><ZXLX>2</ZXLX><CGLX>1</CGLX><SPLX>1</SPLX><YPLX>3</YPLX><ZXSPBM>YP1003202001035</ZXSPBM><CPM>醋龟甲</CPM><YPJX/><GG>g*1000g</GG><BZDWXZ>1</BZDWXZ><BZDWMC>克</BZDWMC><YYDWMC>克</YYDWMC><BZNHSL>1</BZNHSL><SCQYMC/><SCPH>210301</SCPH><SCRQ>29991231</SCRQ><YXRQ>29991231</YXRQ><JHDH>210420134015-944</JHDH><XSDDH>xb210000003470001</XSDDH><DDMXBH>20210420000066086194</DDMXBH><SXH>16596</SXH><PSL>3000</PSL></STRUCT><STRUCT><PSDH>ps210000000000347</PSDH><YQBM>913416005663861331</YQBM><PSDBM>4</PSDBM><CJRQ>20210420/000000/</CJRQ><PSMXBH>20210420000833474617</PSMXBH><PSDTM>ahjsxb0000368002</PSDTM><ZXLX>2</ZXLX><CGLX>1</CGLX><SPLX>1</SPLX><YPLX>3</YPLX><ZXSPBM>YP0203302000373</ZXSPBM><CPM>炒蔓荆子</CPM><YPJX/><GG/><BZDWXZ>1</BZDWXZ><BZDWMC>克</BZDWMC><YYDWMC>克</YYDWMC><BZNHSL>1</BZNHSL><SCQYMC/><SCPH>201201</SCPH><SCRQ>29991231</SCRQ><YXRQ>29991231</YXRQ><JHDH>210420134015-944</JHDH><XSDDH>xb210000003470002</XSDDH><DDMXBH>20210420000066086195</DDMXBH><SXH>16597</SXH><PSL>10000</PSL></STRUCT><STRUCT><PSDH>ps210000000000347</PSDH><YQBM>913416005663861331</YQBM><PSDBM>4</PSDBM><CJRQ>20210420/000000/</CJRQ><PSMXBH>20210420000833474618</PSMXBH><PSDTM>ahjsxb0000368003</PSDTM><ZXLX>2</ZXLX><CGLX>1</CGLX><SPLX>1</SPLX><YPLX>3</YPLX><ZXSPBM>YP0102201000053</ZXSPBM><CPM>麦冬</CPM><YPJX/><GG>1g*1000g</GG><BZDWXZ>1</BZDWXZ><BZDWMC>克</BZDWMC><YYDWMC>克</YYDWMC><BZNHSL>1</BZNHSL><SCQYMC/><SCPH>200201</SCPH><SCRQ>29991231</SCRQ><YXRQ>29991231</YXRQ><JHDH>210420134015-944</JHDH><XSDDH>xb210000003470003</XSDDH><DDMXBH>20210420000066086196</DDMXBH><SXH>16598</SXH><PSL>10000</PSL></STRUCT><STRUCT><PSDH>ps210000000000347</PSDH><YQBM>913416005663861331</YQBM><PSDBM>4</PSDBM><CJRQ>20210420/000000/</CJRQ><PSMXBH>20210420000833474619</PSMXBH><PSDTM>ahjsxb0000368004</PSDTM><ZXLX>2</ZXLX><CGLX>1</CGLX><SPLX>1</SPLX><YPLX>3</YPLX><ZXSPBM>YP0100701000016</ZXSPBM><CPM>太子参</CPM><YPJX/><GG>1g*1000g</GG><BZDWXZ>1</BZDWXZ><BZDWMC>克</BZDWMC><YYDWMC>克</YYDWMC><BZNHSL>1</BZNHSL><SCQYMC/><SCPH>201201</SCPH><SCRQ>29991231</SCRQ><YXRQ>29991231</YXRQ><JHDH>210420134015-944</JHDH><XSDDH>xb210000003470004</XSDDH><DDMXBH>20210420000066086197</DDMXBH><SXH>16599</SXH><PSL>10000</PSL></STRUCT><STRUCT><PSDH>CK210429007005</PSDH><YQBM>91330205MA2H6L8D35</YQBM><PSDBM>3</PSDBM><CJRQ>20210429/000000/</CJRQ><PSMXBH>20210429000845016529</PSMXBH><PSDTM>CK210429007005</PSDTM><ZXLX>2</ZXLX><CGLX>1</CGLX><SPLX>1</SPLX><YPLX>2</YPLX><ZXSPBM>ZN0000030072075</ZXSPBM><CPM>冠心宁片</CPM><YPJX>薄膜衣片</YPJX><GG>0.38g</GG><BZDWXZ>1</BZDWXZ><BZDWMC>盒</BZDWMC><YYDWMC>片(粒,支,瓶,袋,听)</YYDWMC><BZNHSL>18</BZNHSL><SCQYMC>正大青春宝药业有限公司</SCQYMC><SCPH>2012002</SCPH><SCRQ>20201214</SCRQ><YXRQ>20221213</YXRQ><JHDH>210429113422-548</JHDH><XSDDH>7978</XSDDH><DDMXBH>20210429000066451866</DDMXBH><SXH>16766</SXH><PSL>600</PSL></STRUCT></DETAIL></XMLDATA>";
        while(respList.size()<req.getPageNum()) {
            XmlData<BaseRespMain, PsdYY003RespStruct> xmlData = webService.hqpsdmxsjYY003(new XmlData<>(new Head(req.getIp(), req.getMac(), null), main, null), baseRequest);
//            XmlData<BaseRespMain, PsdYY003RespStruct> xmlData = GpoXmlUtil.toObj(xml,new TypeReference<XmlData<BaseRespMain, PsdYY003RespStruct>>() {});
            if (CollUtil.isEmpty(xmlData.getDETAIL())) {
                resp.setLastPage(true);
                break;
            }
            List<PsdYY003RespStruct> list = xmlData.getDETAIL().stream().map(t->(Object) t.getSTRUCT())
                    .flatMap(t->{
                        if ( t instanceof JSONObject) {
                            PsdYY003RespStruct psdobj = JSONUtil.toBean((JSONObject)t,PsdYY003RespStruct.class);
                            return Arrays.asList(psdobj).stream();
                        }
                        if ( t instanceof JSONArray) {
                            List<PsdYY003RespStruct> psdList = JSONUtil.toList((JSONArray) t, PsdYY003RespStruct.class);
                            return psdList.stream();
                        }
                        return null;
                    }).filter(t ->
                            (t.getYPLX().equals(GpoZidian.YPLX.ZYYP.getVal()) && req.getYklb() == 2)
                                    || (!t.getYPLX().equals(GpoZidian.YPLX.ZYYP.getVal()) && req.getYklb() == 1)
                    ).collect(Collectors.toList());

            if (list.size() != 0) {
                main.setPSMXBH(list.get(list.size() - 1).getPSMXBH());
                respList.addAll(list);
            }else{
                break;
            }
        }
        if(!respList.isEmpty()){
            resp.setNextNo(respList.get(respList.size()-1).getPSMXBH());
        }
        resp.setList(respList);
        return resp;
    }

    public List<PsdYY005RespStruct> hqpsd(PsdsjRequest req) {
        XmlData<BaseRespMain, PsdYY005RespStruct> xmlData = null;
        List<PsdYY005RespStruct> list = new ArrayList<>();
        do {
            xmlData = webService.cxpsdsjYY005(new XmlData<>(new Head(req.getIp(), req.getMac(), null), req.getPsdqq(), null), req);

            if (CollUtil.isEmpty(xmlData.getDETAIL())) {
                break;
            }
            Object struct1 = xmlData.getDETAIL().get(0).getSTRUCT();
            Map<String, Object> stringObjectMap = CommonMapUtils.objToMap(xmlData.getDETAIL().get(0));
            if (struct1 instanceof JSONArray) {
                JSONArray objects = JSONUtil.parseArray(stringObjectMap.get("struct").toString());
                List<PsdYY005RespStruct> struct = JSONUtil.toList(objects, PsdYY005RespStruct.class);
                list.addAll(struct);
            } else {
                PsdYY005RespStruct struct = JSONUtil.toBean(stringObjectMap.get("struct").toString(), PsdYY005RespStruct.class);
                list.add(struct);
            }
//            for (Struct<PsdYY005RespStruct> struct : xmlData.getDETAIL()) {
//                Map<String, Object> stringObjectMap = CommonMapUtils.objToMap(struct);
//                PsdYY005RespStruct psdyy005 = JSONUtil.toBean(stringObjectMap.get("struct").toString(), PsdYY005RespStruct.class);
//                list.add(psdyy005);
//            }

            req.getPsdqq().setPSDID(list.get(list.size() - 1).getPSDID());
        } while ("0".equals(xmlData.getMAIN().getSFWJ()));

        return list;
    }

    public List<PsdYY006RespStruct> hqpsdmxByPsdbh(GpoBaseRequest baseRequest, String psdid) {
        PsdYY006ReqMain main = new PsdYY006ReqMain();
        //main.setIp(baseRequest.getIp());
        //main.setMac(baseRequest.getMac());
        // main.setJgid(baseRequest.getJgid());
        //main.setUserid(String.valueOf(baseRequest.getUserid()));
        //main.setUname(baseRequest.getUname());
        main.setPSDID(psdid);
        main.setPSMXBH("");

        List<PsdYY006RespStruct> list = new ArrayList<>();
        XmlData<BaseRespMain, PsdYY006RespStruct> xmlData = null;

        do {
            xmlData = webService.apsdbhcxYY006(new XmlData<>(new Head(baseRequest.getIp(), baseRequest.getMac(), null), main, null), baseRequest);

            if (CollUtil.isEmpty(xmlData.getDETAIL())) {
                break;
            }

            for (Struct<PsdYY006RespStruct> struct : xmlData.getDETAIL()) {
                Map<String, Object> stringObjectMap = CommonMapUtils.objToMap(struct);
                PsdYY006RespStruct psdyy006 = JSONUtil.toBean(stringObjectMap.get("struct").toString(), PsdYY006RespStruct.class);
                //药库类型为中草药, 药品类型不为中草药
                if (baseRequest.getYklx().equals(GpoConsts.YKLX.ZCY.getKey())
                        && !psdyy006.getYPLX().equals(GpoZidian.YPLX.ZYYP.getVal())) {
                    break;
                }
                //药库类型为西药, 药品类型为中草药
                if (baseRequest.getYklx().equals(GpoConsts.YKLX.XY.getKey())
                        && psdyy006.getYPLX().equals(GpoZidian.YPLX.ZYYP.getVal())) {
                    break;
                }
                list.add(psdyy006);
            }
            if (list.size()!=0) {
                main.setPSMXBH(list.get(list.size() - 1).getPSMXBH());
            }

        } while ("0".equals(xmlData.getMAIN().getSFWJ()));

        return list;

    }
}
