package com.buit.his.gpo.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.buit.commons.BaseException;
import com.buit.constans.ErrorCode;
import com.buit.constans.TableName;
import com.buit.drug.dto.IDrugsRk02;
import com.buit.drug.request.IDrugsRkReq;
import com.buit.drug.response.IRkPrimaryKeyResp;
import com.buit.drug.service.DrugService;
import com.buit.his.gpo.controller.request.*;
import com.buit.his.gpo.controller.response.FpdzResponse;
import com.buit.his.gpo.controller.response.SpResp;
import com.buit.his.gpo.dao.*;
import com.buit.his.gpo.model.GpoFp;
import com.buit.his.gpo.model.GpoFpmx;
import com.buit.his.gpo.model.GpoJgxx;
import com.buit.his.gpo.service.result.*;
import com.buit.his.gpo.ws.GpoConsts;
import com.buit.his.gpo.ws.GpoWebService;
import com.buit.his.gpo.ws.request.FpYY016ReqMain;
import com.buit.his.gpo.ws.request.FpYY016ReqStruct;
import com.buit.his.gpo.ws.request.FpYY017ReqMain;
import com.buit.his.gpo.ws.request.FpYY019ReqMain;
import com.buit.his.gpo.ws.response.*;
import com.buit.his.gpo.ws.xml.CommonMapUtils;
import com.buit.his.gpo.ws.xml.Head;
import com.buit.his.gpo.ws.xml.Struct;
import com.buit.his.gpo.ws.xml.XmlData;
import com.buit.utill.RedisFactory;
import com.buit.utill.TimestampUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Author sunjx
 * @Date 2020-11-12 14:34
 * @Description
 **/
@Service
public class FpService {

    private static final Logger log = LoggerFactory.getLogger(FpService.class);

    @Autowired
    private GpoWebService webService;

    @DubboReference
    private DrugService drugService;

    @Autowired
    private RedisFactory redisFactory;

    @Autowired
    private GpoYqJhdwDao yqJhdwDao;

    @Autowired
    private GpoFpDao fpDao;

    @Autowired
    private GpoFpmxDao mxDao;

    @Autowired
    private GpoDao gpoDao;
    @Autowired
    private GpoService gpoService;
    @Autowired
    private GpoDdDao ddDao;

    private List<FpYY004RespStruct> hqfpsj(HqfpmxsjRequest req) {
        List<FpYY004RespStruct> list = new ArrayList<>();
        XmlData<BaseRespMain, FpYY004RespStruct> xmlData = null;
        do {
            xmlData = webService.hqfpmxsjYY004(new XmlData<>(new Head(req.getIp(), req.getMac(), null), req.getMain(), null), req);

            if (CollUtil.isEmpty(xmlData.getDETAIL())) {
                break;
            }
            Map<String, Object> stringObjectMap = CommonMapUtils.objToMap(xmlData.getDETAIL().get(0));
            Object struct1 = xmlData.getDETAIL().get(0).getSTRUCT();
            if (struct1 instanceof JSONArray) {
                JSONArray objects = JSONUtil.parseArray(stringObjectMap.get("struct").toString());
                List<FpYY004RespStruct> struct = JSONUtil.toList(objects, FpYY004RespStruct.class);
                list.addAll(struct);
            } else {
                FpYY004RespStruct struct = JSONUtil.toBean(stringObjectMap.get("struct").toString(), FpYY004RespStruct.class);
                list.add(struct);
            }
            req.getMain().setFPMXBH(list.get(list.size() - 1).getFPMXBH());
        } while ("0".equals(xmlData.getMAIN().getSFWJ()));

        return list;
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public List<Fpsj> hq(HqfpmxsjRequest req) {
        List<FpYY004RespStruct> structs = hqfpsj(req);
        if (CollUtil.isEmpty(structs)) {
            return null;
        }

        List<Fpsj> list = new ArrayList<>();
        for (FpYY004RespStruct struct : structs) {

            Fpsj response = createHqfpmxsjResponse(struct);
            //response.getMxs().add(createHqfpmx(struct));
            list.add(response);
        }
        List<Fpsj> collect = list.stream().filter(distinctByKey(Fpsj::getFPH)).collect(Collectors.toList());
        for (Fpsj fpsj : collect) {
            for (FpYY004RespStruct struct : structs) {
                if (fpsj.getFPH().equals(struct.getFPH())) {
                    fpsj.getMxs().add(createHqfpmx(struct));
                }
            }
        }

//        structs:
//        for (FpYY004RespStruct struct : structs) {
//            if (CollUtil.isEmpty(list)) {
//                Fpsj response = createHqfpmxsjResponse(struct);
//                response.getMxs().add(createHqfpmx(struct));
//                list.add(response);
//                continue;
//            } else {
//                Boolean flag = true;
//                for (Fpsj fpsj : list) {
//                    if (fpsj.getFPBZ().equals(struct.getFPBZ())) {
//                        flag = false;
//                    }
//                    if (flag) {
//                        Fpsj response = createHqfpmxsjResponse(struct);
//                        response.getMxs().add(createHqfpmx(struct));
//                        list.add(response);
//                    }
//                }
//            }
//
//            for (Fpsj response : list) {
//                if (response.getFPH().equals(struct.getFPH())) {
//                    response.getMxs().add(createHqfpmx(struct));
//                    continue structs;
//                }
//            }
//        }

        collect.sort(Comparator.comparing(Fpsj::getFPH));
        return collect;
    }

    @Transactional(rollbackFor = Exception.class)
    public void ys(FpysRequest request) {
        if (CollUtil.isEmpty(request.getItems())) {
            return;
        }

        for (Fpsj item : request.getItems()) {
            //验收
            FpYY019ReqMain main = new FpYY019ReqMain();
            main.setYQBM(item.getYQBM());
            main.setFPH(item.getFPH());
            main.setFPHSZJE(item.getFPHSZJE());
            XmlData xmlData = webService.fpysYY019(new XmlData<>(new Head(request.getIp(), request.getMac(), null), main, null), request);
            GpoJgxx gpoJgxx = gpoService.gpoJgbm(request.getJgid());
            //保存发票数据
            GpoFp fp = new GpoFp();
            fp.setXh(redisFactory.getTableKey(TableName.DB_NAME, TableName.TB_NAME_GPO_FP));
            fp.setJgid(request.getJgid());
            if (gpoJgxx != null) {
                fp.setGpoJgid(gpoJgxx.getGpoJgdm());
            }
//            fp.setFpid();
            fp.setFph(item.getFPH());
            GpoFp gpoFp = fpDao.selectByFph(item.getFPH());
            if (gpoFp != null) {
                continue;
            }
            fp.setYqbm(item.getYQBM());
            fp.setYqmc(item.getYQMC());
            fp.setFprq(item.getFPRQ());
            fp.setFphszje(item.getFPHSZJE());
            fp.setYybm(item.getYYBM());
            fp.setPsdbm(item.getPSDBM());
            fp.setDlcgbz(item.getDLCGBZ());
            fp.setFpbz(item.getFPBZ());
            fp.setFpzt("1");
            fp.setYszt(GpoConsts.YSZT.YYS.getCode());
            fp.setYssj(TimestampUtil.now());
            fp.setYsrdm(request.getUserid());
            fp.setDzzt(GpoConsts.DZZT.DDZ.getCode());
            //fp.setDzsj();
//            fp.setDzrdm();
            fp.setRkzt(GpoConsts.RKZT.DRK.getCode());
//            fp.setRksj();
//            fp.setRkdh();
//            fp.setRkrdm();
            fp.setZfzt(GpoConsts.ZFUZT.DZF.getCode());
//            fp.setZfsj();
//            fp.setZfrdm();
            fp.setZt(GpoConsts.ZT.YX.getCode());
//            fp.setScsj();
//            fp.setScrdm();
            fp.setCtime(TimestampUtil.now());
            fp.setCuser(request.getUserid());
            fp.setCuname(request.getUname());
            fp.setYklx(String.valueOf(request.getYklx()));
            if (CollUtil.isNotEmpty(item.getMxs())) {
                Fpmx fpmx = item.getMxs().get(0);
                Integer yksb = fpDao.selectYksb(fpmx.getGLMXBH());
                fp.setYksb(yksb);
            }
            fpDao.insert(fp);

            //保存发票明细
            for (Fpmx mx : item.getMxs()) {
                GpoFpmx fpmx = new GpoFpmx();
                fpmx.setXh(redisFactory.getTableKey(TableName.DB_NAME, TableName.TB_NAME_GPO_FPMX));
                fpmx.setJgid(request.getJgid());
                fpmx.setGpoJgid(item.getYYBM());
                fpmx.setFpxh(fp.getXh());
                fpmx.setSfwpsfp(mx.getSFWPSFP());
                fpmx.setWpsfpsm(mx.getWPSFPSM());
                fpmx.setFpmxbh(mx.getFPMXBH());
                fpmx.setSplx(mx.getSPLX());
                fpmx.setSfch(mx.getSFCH());
                fpmx.setZxspbm(mx.getZXSPBM());
                fpmx.setScph(mx.getSCPH());
                fpmx.setScrq(mx.getSCRQ());
                fpmx.setSpsl(mx.getSPSL());
                fpmx.setGlmxbh(mx.getGLMXBH());
                fpmx.setXsddh(mx.getXSDDH());
                fpmx.setSxh(mx.getSXH());
                fpmx.setYxrq(mx.getYXRQ());
                fpmx.setWsdj(mx.getWSDJ());
                fpmx.setHsdj(mx.getHSDJ());
                fpmx.setSl(mx.getSL());
                fpmx.setSe(mx.getSE());
                fpmx.setHsje(mx.getHSJE());
                fpmx.setPfj(mx.getPFJ());
                fpmx.setLsj(mx.getLSJ());
                fpmx.setPzwh(mx.getPZWH());
                fpmx.setZt(GpoConsts.ZT.YX.getCode());
                fpmx.setCtime(TimestampUtil.now());
                fpmx.setCuser(request.getUserid());
                fpmx.setCuname(request.getUname());
                mxDao.insert(fpmx);
            }
        }
    }

    private Fpmx createHqfpmx(FpYY004RespStruct struct) {
        Fpmx mx = new Fpmx();
        mx.setSFWPSFP(struct.getSFWPSFP());
        mx.setWPSFPSM(struct.getWPSFPSM());
        mx.setFPMXBH(struct.getFPMXBH());
        mx.setSPLX(struct.getSPLX());
        mx.setSFCH(struct.getSFCH());
        mx.setZXSPBM(struct.getZXSPBM());
        //TODO 查询商品名称,规格
        //根据商品统编代码，查询药品 ggbz, cgjldw, cgdj, yqbm,
        SpResp sp = gpoDao.yp(struct.getZXSPBM(), DateUtil.format(TimestampUtil.now(), "yyyyMMdd"));
        if (null != sp) {
            mx.setSPMC(sp.getYPTYM());
            mx.setGG(sp.getGG());
        }
        mx.setSCPH(struct.getSCPH());
        mx.setSCRQ(struct.getSCRQ());
        mx.setSPSL(struct.getSPSL());
        mx.setGLMXBH(struct.getGLMXBH());
        mx.setXSDDH(struct.getXSDDH());
        mx.setSXH(struct.getSXH());
        mx.setYXRQ(struct.getYXRQ());
        mx.setWSDJ(struct.getWSDJ());
        mx.setHSDJ(struct.getHSDJ());
        mx.setSL(struct.getSL());
        mx.setSE(struct.getSE());
        mx.setHSJE(struct.getHSJE());
        mx.setPFJ(struct.getPFJ());
        mx.setLSJ(struct.getLSJ());
        mx.setPZWH(struct.getPZWH());
        return mx;
    }

    private Fpsj createHqfpmxsjResponse(FpYY004RespStruct struct) {
        Fpsj resp = new Fpsj();
        resp.setFPH(struct.getFPH());
        resp.setFPRQ(struct.getFPRQ());
        resp.setFPHSZJE(struct.getFPHSZJE());
        resp.setYQBM(struct.getYQBM());
        resp.setYYBM(struct.getYYBM());
        resp.setPSDBM(struct.getPSDBM());
        resp.setDLCGBZ(struct.getDLSGBZ());
        resp.setFPBZ(struct.getFPBZ());
        resp.setMxs(new ArrayList<>());
        return resp;
    }

    public List<FpYY007RespStruct> cx(HqfpsjRequest req) {
        List<FpYY007RespStruct> list = new ArrayList<>();
        XmlData<BaseRespMain, FpYY007RespStruct> xmlData = null;
        do {
            xmlData = webService.cxfpsjYY007(new XmlData<>(new Head(req.getIp(), req.getMac(), null), req.getMain(), null), req);

            if (CollUtil.isEmpty(xmlData.getDETAIL())) {
                break;
            }
            Map<String, Object> stringObjectMap = CommonMapUtils.objToMap(xmlData.getDETAIL().get(0));
            Object struct1 = xmlData.getDETAIL().get(0).getSTRUCT();
            if (struct1 instanceof JSONArray) {
                JSONArray objects = JSONUtil.parseArray(stringObjectMap.get("struct").toString());
                List<FpYY007RespStruct> struct = JSONUtil.toList(objects, FpYY007RespStruct.class);
                list.addAll(struct);
            } else {
                FpYY007RespStruct struct = JSONUtil.toBean(stringObjectMap.get("struct").toString(), FpYY007RespStruct.class);
                list.add(struct);
            }


            req.getMain().setFPID(list.get(list.size() - 1).getFPID());
        } while ("0".equals(xmlData.getMAIN().getSFWJ()));

        return list;
    }

    public List<FpYY008RespStruct> fpxq(FpxqRequest req) {
        List<FpYY008RespStruct> list = new ArrayList<>();
        XmlData<BaseRespMain, FpYY008RespStruct> xmlData = null;
        do {
            xmlData = webService.afpbhcxYY008(new XmlData<>(new Head(req.getIp(), req.getMac(), null), req.getMain(), null), req);

            if (CollUtil.isEmpty(xmlData.getDETAIL())) {
                break;
            }
            Object struct1 = xmlData.getDETAIL().get(0).getSTRUCT();
            Map<String, Object> stringObjectMap = CommonMapUtils.objToMap(xmlData.getDETAIL().get(0));

            if (struct1 instanceof JSONArray) {
                JSONArray objects = JSONUtil.parseArray(stringObjectMap.get("struct").toString());
                List<FpYY008RespStruct> struct = JSONUtil.toList(objects, FpYY008RespStruct.class);
                list.addAll(struct);
            } else {
                FpYY008RespStruct struct = JSONUtil.toBean(stringObjectMap.get("struct").toString(), FpYY008RespStruct.class);
                list.add(struct);
            }

            req.getMain().setFPMXBH(list.get(list.size() - 1).getFPMXBH());

        } while ("0".equals(xmlData.getMAIN().getSFWJ()));
        return list;
    }

    public List<Fpsj> dzcx(
            Integer jgid,
            Integer yksb,
            String ksrq,
            String jsrq,
            String dlcgbz) {

        List<Fpsj> list = fpDao.list(jgid, yksb, null, ksrq, jsrq, dlcgbz, GpoConsts.YSZT.YYS.getCode(), GpoConsts.DZZT.DDZ.getCode(), null, null);

        if (CollUtil.isEmpty(list)) {
            return null;
        }

        return list.stream()
                .filter((s -> s.getDLCGBZ().equals(dlcgbz)))
                .collect(Collectors.toList());

    }

    public FpdzxqData fpdzxq(List<Fpsj> list) {
        if (CollUtil.isEmpty(list)) {
            return null;
        }

        FpdzxqData data = new FpdzxqData();
        data.setDetails(new ArrayList<>());
        data.setFPSL(list.size());
        data.setFPJE("0");
        BigDecimal fpje = new BigDecimal(0);

        structs:
        for (Fpsj fpsj : list) {
            //发票总金额
            fpje = fpje.add(new BigDecimal(fpsj.getFPHSZJE()));

            if (CollUtil.isNotEmpty(data.getDetails())) {
                for (int i = 0; i < data.getDetails().size(); i++) {
                    FpdzxqDataDetail detail = data.getDetails().get(i);
                    if (detail.getYQBM().equals(fpsj.getYQBM())) {
                        detail.setFPSL(new BigDecimal(detail.getFPSL()).add(BigDecimal.ONE).toString());
                        detail.setFPJE(new BigDecimal(detail.getFPJE()).add(new BigDecimal(fpsj.getFPHSZJE())).toString());
                        detail.setYQMC(ddDao.selectYqmc(fpsj.getYQBM()));
                        continue structs;
                    }
                }
            }

            FpdzxqDataDetail detail = new FpdzxqDataDetail();
            detail.setYQMC(fpsj.getYQMC());
            detail.setYQBM(fpsj.getYQBM());
            detail.setYQMC(ddDao.selectYqmc(fpsj.getYQBM()));
            detail.setFPSL("1");
            detail.setFPJE(fpsj.getFPHSZJE());
            data.getDetails().add(detail);
        }

        data.setFPJE(fpje.toString());
        data.getDetails().sort(Comparator.comparing(FpdzxqDataDetail::getYQMC));
        return data;
    }

    public FpdzResponse dz(String ip, String mac, Integer hospitalId, Integer yksb, Integer userId, String userName, String dzq, String ksrq, String jsrq, String dlcgbz) {
        List<Fpsj> dzlist = dzcx(hospitalId, yksb, ksrq, jsrq, dlcgbz);

        FpdzxqData fpdzxq = fpdzxq(dzlist);

        if (null == fpdzxq || CollUtil.isEmpty(fpdzxq.getDetails())) {
            return null;
        }

        FpYY016ReqMain main = new FpYY016ReqMain();
        main.setDZQ(dzq);
        main.setKSRQ(ksrq);
        main.setJSRQ(jsrq);
        main.setDLCGBZ(dlcgbz);
        main.setFPSL(String.valueOf(fpdzxq.getFPSL()));
        main.setFPJE(fpdzxq.getFPJE());
        main.setJLS(String.valueOf(fpdzxq.getDetails().size()));

        GpoBaseRequest baseRequst = new GpoBaseRequest();
        baseRequst.setYklx(null);
        baseRequst.setIp(ip);
        baseRequst.setMac(mac);
        baseRequst.setBzxx("");
        baseRequst.setJgid(hospitalId);
        baseRequst.setUserid(String.valueOf(userId));
        baseRequst.setUname(userName);

        List<Struct<FpYY016ReqStruct>> structs = new ArrayList<>();
        for (FpdzxqDataDetail detail : fpdzxq.getDetails()) {
            FpYY016ReqStruct struct = new FpYY016ReqStruct();
            struct.setYQBM(detail.getYQBM());
            struct.setFPJE(detail.getFPJE());
            struct.setFPSL(detail.getFPSL());
            structs.add(new Struct<>(struct));
        }

        XmlData<BaseRespMain, FpYY016RespStruct> xmlData = webService.dzYY016(new XmlData<>(new Head(ip, mac, null), main, structs), baseRequst);

        if (null == xmlData || CollUtil.isEmpty(xmlData.getDETAIL())) {
            return null;
        }

        for (Fpsj fpsj : dzlist) {
            //发票状态
            if (fpDao.dz(fpsj.getXH(), TimestampUtil.now(), userId) == 0) {
                //发票对账失败
                log.error("GPO 发票对账失败 => fpxh:{}", fpsj.getXH());
            }
        }

        FpdzResponse response = new FpdzResponse();
        response.setFPJE("0");
        response.setFPSL(0);
        response.setDetails(new ArrayList<>());
        BigDecimal fpje = new BigDecimal(0);

        for (Struct<FpYY016RespStruct> struct : xmlData.getDETAIL()) {
            fpje = fpje.add(new BigDecimal(struct.getSTRUCT().getFPJE()));
            response.setFPJE(new BigDecimal(response.getFPJE()).add(new BigDecimal(struct.getSTRUCT().getFPJE())).toString());
            response.setFPSL(response.getFPSL() + Integer.parseInt(struct.getSTRUCT().getFPSL()));

            FpdzxqDataDetail detail = new FpdzxqDataDetail();
            detail.setYQBM(struct.getSTRUCT().getYQBM());
            detail.setFPSL(struct.getSTRUCT().getFPSL());
            detail.setFPJE(struct.getSTRUCT().getFPJE());
            response.getDetails().add(detail);
        }
        response.setFPJE(fpje.toString());

        return response;
    }

    public List<Fpsj> zfcx(Integer jgid, Integer yksb, String ksrq, String jsrq, String yqbm) {
        return fpDao.list(jgid, yksb, yqbm, ksrq, jsrq, null, GpoConsts.YSZT.YYS.getCode(), null, GpoConsts.ZFUZT.DZF.getCode(), null);
    }

    public void zf(ZfRequest request) {
        if (CollUtil.isEmpty(request.getItems())) {
            return;
        }

        for (ZfRequestItem item : request.getItems()) {
            FpYY017ReqMain main = new FpYY017ReqMain();
            main.setYQBM(item.getYQBM());
            main.setFPH(item.getFPH());
            main.setFPHSZJE(item.getFPHSZJE());
            webService.zfqrYY017(new XmlData<>(new Head(request.getIp(), request.getMac(), null), main, null), request);

            //发票支付状态
            if (fpDao.zf(item.getFPH(), TimestampUtil.now(), request.getUserid()) == 0) {
                //发票支付失败
                log.error("GPO 发票支付失败 => fph:{}", item.getFPH());
            }
        }
    }

    public List<Fpsj> rkcx(
            Integer jgid,
            Integer yksb,
            String ksrq,
            String jsrq,
            String yqbm) {
        return fpDao.list(jgid, yksb, yqbm, ksrq, jsrq, null, GpoConsts.YSZT.YYS.getCode(), null, null, GpoConsts.RKZT.DRK.getCode());
    }

    @Transactional(rollbackFor = Exception.class)
    public void rk(ScrkdRequest request) {
        List<GpoFp> fpList = fpDao.selectListInXh(request.getFplist());
        if (CollUtil.isEmpty(fpList)) {
            return;
        }

        for (GpoFp fp : fpList) {
            //生成入库单
            IDrugsRkReq rkReq = new IDrugsRkReq();
            rkReq.setJgid(request.getJgid());
            //药库识别
            rkReq.setYksb(fp.getYksb());
            rkReq.setPwd(0);

            //根据药企编码查询
            rkReq.setDwxh(Integer.valueOf(yqJhdwDao.jhdwbm(fp.getYqbm())));
//        rkReq.setFdjs();
//        rkReq.setRkbz();
            rkReq.setCgrq(TimestampUtil.now());
            rkReq.setCggh(request.getUserid());
            rkReq.setCzgh(request.getUserid());
            rkReq.setRk02List(new ArrayList<>());

            //查询发票信息含明细
            List<Fpmx> mxs = mxDao.list(request.getJgid(), fp.getXh());
            if (CollUtil.isEmpty(mxs)) {
                continue;
            }

            for (Fpmx mx : mxs) {
                YpcdjgDto ypcdjgDto = gpoDao.selectYpCdJgByYbbm(String.valueOf(request.getJgid()), mx.getZXSPBM());

                if (null == ypcdjgDto) {
                    throw BaseException.create(ErrorCode.ERROR_GPO_FP_0001);
                }

                //校验如果发票价格与his药品价格不一致,不能入库
//                if (!ypcdjgDto.getJhjg().equals(mx.getHSDJ())) {
//                    throw BaseException.create(ErrorCode.ERROR_GPO_FP_0002);
//                }

                IDrugsRk02 rkmx = new IDrugsRk02();

                //根据统编码查询
                rkmx.setYpxh(Integer.valueOf(ypcdjgDto.getYpxh()));
                rkmx.setYpcd(Integer.valueOf(ypcdjgDto.getYpcd()));
//            rkmx.setYpph();
//            rkmx.setYpxq();
                //rkmx.set
                rkmx.setRksl(new BigDecimal(mx.getSL()));
                rkReq.getRk02List().add(rkmx);
            }

            IRkPrimaryKeyResp resp = drugService.addDrugsRk(rkReq);

            //发票入库状态
            fp.setRkzt(GpoConsts.RKZT.YRK.getCode());
            fp.setRkdh(String.valueOf(resp.getRkdh()));
            fp.setRkrdm(String.valueOf(request.getUserid()));
            fp.setRksj(TimestampUtil.now());
            fpDao.update(fp);
        }

    }

    public List<Fpmx> mx(Integer jgid, String fph) {
        GpoFp fp = fpDao.selectByFph(fph);
        List<Fpmx> list = mxDao.list(jgid, fp.getXh());

        for (Fpmx fpmx : list) {
            SpResp sp = gpoDao.yp(fpmx.getZXSPBM(), DateUtil.format(TimestampUtil.now(), "yyyyMMdd"));
            if (null != sp) {
                fpmx.setGG(sp.getGG());
                fpmx.setSPMC(sp.getYPTYM());
            }

        }
        return list;
    }
}
