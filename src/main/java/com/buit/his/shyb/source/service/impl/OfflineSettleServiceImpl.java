package com.buit.his.shyb.source.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.buit.aop.lock.Locked;
import com.buit.his.shyb.source.dao.*;
import com.buit.his.shyb.source.entity.*;
import com.buit.op.service.OpBrzdService;
import com.buit.op.service.OpCf01Service;
import com.buit.op.service.OpYjcf01Service;
import com.buit.system.service.DicJbbmService;
import com.buit.system.service.SysXtcsCacheSer;
import com.buit.system.utill.MedicineUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.buit.constans.TableName;
import com.buit.his.shyb.source.entity.business.DetailsBillcy;
import com.buit.his.shyb.source.entity.business.DiagnosisInfocy;
import com.buit.his.shyb.source.entity.business.MessageBody;
import com.buit.his.shyb.source.entity.business.SI11;
import com.buit.his.shyb.source.entity.business.SI12;
import com.buit.his.shyb.source.entity.business.SI51;
import com.buit.his.shyb.source.entity.business.SI52;
import com.buit.his.shyb.source.entity.business.SJ11;
import com.buit.his.shyb.source.entity.business.SJ21cy;
import com.buit.his.shyb.source.entity.business.SK01;
import com.buit.his.shyb.source.entity.business.SL01;
import com.buit.his.shyb.source.entity.business.SN01_MZ;
import com.buit.his.shyb.source.entity.business.Ylxfmx;
import com.buit.his.shyb.source.entity.business.Ylxmmx;
import com.buit.his.shyb.source.enums.BusinessTypeEnum;
import com.buit.his.shyb.source.enums.MedicalCategoryEnum;
import com.buit.his.shyb.source.enums.TradingChannelEnum;
import com.buit.his.shyb.source.model.MedicalInsuranceModel;
import com.buit.his.shyb.source.model.UnifiedBusinessManager;
import com.buit.his.shyb.source.util.HisUtil;
import com.buit.his.shyb.source.util.JackJsonUtil;
import com.buit.his.shyb.source.util.ServiceCode;
import com.buit.commons.SysUser;
import com.buit.utill.RedisFactory;
import com.buit.utill.StrUtil;
import org.springframework.transaction.annotation.Transactional;

/**
 * 医保
 * @author beijunhua
 */
@DubboService(timeout = 60000)
public class OfflineSettleServiceImpl implements OfflineSettleService {
	@DubboReference
	private SysXtcsCacheSer sysXtcsCacheSer;
    @Autowired
    private ShybSh01Dao ybSh01Dao;
    @Autowired
    private ShybSn01Dao shybSn01Dao;
    @DubboReference
    private OpCf01Service opCf01Service;
    @DubboReference
    private OpYjcf01Service opYjcf01Service;
    @DubboReference
    private OpBrzdService opBrzdService;
    @DubboReference
    private DicJbbmService dicJbbmService;
    @Autowired
    private ShybSi11Dao shybSi11Dao;
    @Autowired
    private ShybSj11Dao shybSj11Dao;
    @Autowired
    private ShybSi51Dao shybSi51Dao;
    @Autowired
    public RedisFactory redisFactory;
    @Autowired
    private UnifiedBusinessManager unifiedBusinessManager;

    static final Logger log = LoggerFactory.getLogger(OfflineSettleServiceImpl.class);

    //private UnifiedBusinessManager unifiedBusinessManager = new UnifiedBusinessManager();

    @Override
    public SettleResultDTO send(Map<String, Object> res, Map<String, Object> options)  {
        SettleResultDTO requestResult = null;
        options.put("jyqd", TradingChannelEnum.OFFLINE);
        if ("SM01".equals(options.get("xxlxm"))) {
            //账户查询
            requestResult = unifiedBusinessManager.sm01(options);
            // 交易查询
        } else if ("SJ31".equals(options.get("xxlxm"))) {
            requestResult = unifiedBusinessManager.sj31(options);
        } else if ("S000".equals(options.get("xxlxm"))) {
            requestResult = unifiedBusinessManager.s000(options);
        } else if("SJG1".equals(options.get("xxlxm"))){
            requestResult = unifiedBusinessManager.sjg1(options);
        }
        res.put("code", requestResult.getCode());
        if (requestResult.getCode() > 200) {
            res.put("isError", -1);
        } else {
            res.put("isError", 0);
        }
        res.put("msg", requestResult.getMsg());
        res.put("errorText", requestResult.getMsg());
        //取返回信息
        if(requestResult.getCode()== 200){
            String json = requestResult.getData().toString();
            Map<String,Object> tempMap = JackJsonUtil.parse(json,Map.class);
            Map<String,Object> xxnrMap = (Map<String, Object>) tempMap.get("xxnr");
            res.put("xxt",xxnrMap);
        }
        System.out.println("bbbbbbbbb");
        return requestResult;
    }

    /**
     * 挂号预结算
     * @param res
     * @param options
     * @return
     */
    @Override
    public SettleResultDTO prepareRegisterOp(Map<String, Object> res, Map<String, Object> options) {
        SettleResultDTO requestResult = new SettleResultDTO();
        MedicalInsuranceModel model = new MedicalInsuranceModel();
        try {
            options.put("jyqd", TradingChannelEnum.OFFLINE);
            options.put("businessType", BusinessTypeEnum.REGISTER);
            //进行预挂号
            requestResult = unifiedBusinessManager.sh01(options);
            res.put("code", requestResult.getCode());
            res.put("msg", requestResult.getMsg());
            //ObjectMapper mapper = new ObjectMapper();
            //datTableRow是一个数据行对象，从datTableRow获取JSON格式字符串
            if (requestResult.getCode() == 200) {
                String jsonData = requestResult.getData().toString();
                Map<String, Object> tmpMap = JackJsonUtil.parse(jsonData, Map.class);
                Map<String, Object> xxtMap = (Map<String, Object>)tmpMap.get("xxnr");
                xxtMap.put("jysj", tmpMap.get("jysj"));
                xxtMap.put("persontype", options.get("persontype"));
                xxtMap.put("jgdm", tmpMap.get("jgdm"));
                xxtMap.put("ksdm", options.get("deptid"));
                res.put("data",xxtMap);
            }
        } catch (Exception e) {
            requestResult.setCode(ServiceCode.CODE_ERROR);
            requestResult.setMsg(ExceptionTips.BUSINESS_EXCEPTION_TIP);
        }
        return requestResult;
    }

    /**
     * 挂号实结算
     * @param options
     * @param options
     * @return
     */
    @Override
    public SettleResultDTO registerOp(Map<String, Object> options) {
        SettleResultDTO requestResult = new SettleResultDTO();
        MedicalInsuranceModel model = new MedicalInsuranceModel();
        try {
            options.put("jyqd", TradingChannelEnum.OFFLINE);
            options.put("businessType", BusinessTypeEnum.REGISTER);
            //进行挂号
            requestResult = unifiedBusinessManager.sh02(options);
            //res.put("code", requestResult.getCode());
            //res.put("msg", requestResult.getMsg());
            //ObjectMapper mapper = new ObjectMapper();
            //datTableRow是一个数据行对象，从datTableRow获取JSON格式字符串
            if (requestResult.getCode() == 200 && requestResult.getData() != null) {
                String jsonData = requestResult.getData().toString();
                Map<String, Object> tmpMap = JackJsonUtil.parse(jsonData, Map.class);
                Map<String, Object> xxtMap = (Map<String, Object>)tmpMap.get("xxnr");
                SimpleDateFormat fmFrom = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat fmTo = new SimpleDateFormat("yyyyMMdd/HHmmss/");
                xxtMap.put("jysj", fmTo.format(fmFrom.parse(tmpMap.get("jysj").toString())));
                xxtMap.put("jgdm", tmpMap.get("jgdm"));
                xxtMap.put("ksdm", options.get("deptid"));
                //res.put("data",xxtMap);
            }else {
                 /*
                //交易查询 SI91
                //计算申请序号
                String jssqxh = options.get("jssqxh").toString();
                //交易费用总额
                BigDecimal totalexpense = new BigDecimal(options.get("totalexpense").toString());
                //如果超时，交易查询
                options.put("jssqxh", jssqxh);
                options.put("totalexpense", totalexpense.setScale(2).toString());
                options.put("xxlxm", "SI91");
                requestResult = unifiedBusinessManager.si91(options);
                if (requestResult.getCode() == 200 && requestResult.getData() != null) {
                    String jsonData = requestResult.getData().toString();
                    Map<String, Object> tmpMap = JackJsonUtil.parse(jsonData, Map.class);
                    Map<String, Object> xxtMap = (Map<String, Object>)tmpMap.get("xxnr");
                    SimpleDateFormat fmFrom = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    SimpleDateFormat fmTo = new SimpleDateFormat("yyyyMMdd/HHmmss/");
                    xxtMap.put("jysj", fmTo.format(fmFrom.parse(tmpMap.get("jysj").toString())));
                    xxtMap.put("jgdm", tmpMap.get("jgdm"));
                    xxtMap.put("ksdm", options.get("deptid"));
                    //res.put("data",xxtMap);
                }*/
            }
        } catch (Exception e) {
            requestResult.setCode(ServiceCode.CODE_ERROR);
            requestResult.setMsg(ExceptionTips.BUSINESS_EXCEPTION_TIP);
        }
        return requestResult;
    }

    /**
     * 收费明细账单
     * @param res
     * @param body
     * @return
     */
    @Override
    public SettleResultDTO batchUploadFeeDetails_MZ(Map<String, Object> body, Map<String, Object> res) {
        SettleResultDTO resultDTO = new SettleResultDTO();
        if("".equals(StrUtil.null2Str(body.get("jzdyh")))){
            resultDTO.setCode(ServiceCode.CODE_ERROR);
            resultDTO.setMsg("就诊单元号为空，请先挂号");
            return resultDTO;
        }
        if("".equals(StrUtil.null2Str(body.get("jzlsh")))){
            resultDTO.setCode(ServiceCode.CODE_ERROR);
            resultDTO.setMsg("就诊流水号为空");
            return resultDTO;
        }
        //查询大病项目代码
        String dbtype = "";
        Map<String, Object> dbtypeList = ybSh01Dao.getDbtype(StrUtil.null2Str(body.get("jzlsh")),StrUtil.null2Str(body.get("carddata")));
        if(dbtypeList!=null && !"".equals(StrUtil.null2Str(dbtypeList.get("dbbz"))) ){
            dbtype = StrUtil.null2Str(dbtypeList.get("dbbz"));
            body.put("dbtype",dbtype);
            body.put("jslxbz","32"+dbtype);
        }
        SettleResultDTO feeDetailsListDTO = new SettleResultDTO();
        Integer jgid = Integer.parseInt(body.get("jgid")+"");
        String YBMXXMJEJDMZ = sysXtcsCacheSer.getCsz(jgid, "YBMXXMJEJDMZ");
        if("1".equals(YBMXXMJEJDMZ)){
            //feeDetailsListDTO = getFeeDetailsUploadList_MZ(body);
        }else{
            feeDetailsListDTO = getFeeDetailsUploadList2_MZ(body);//明细项目金额 取处方HJJE
        }
        if(feeDetailsListDTO.getCode() == ServiceCode.CODE_ERROR){
            res.put("code", ServiceCode.CODE_ERROR);
            return feeDetailsListDTO;
        }
        List<Map<String, Object>> feeDetailsList = (List<Map<String, Object>>) feeDetailsListDTO.getData();
        //3. 向医保上传
        List<Map<String, Object>> feeDetailsListOneTimeList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        //明细账单号 初始为空, 后续上传需要将返回的明细账单号填入
        String mxzdh = "";
        String YBMXZDSCTSMZ = sysXtcsCacheSer.getCsz(jgid, "YBMXSCTSMZ");
        if("".equals(StrUtil.null2Str(YBMXZDSCTSMZ))){
            YBMXZDSCTSMZ = "50";
        }
        Integer sccs = Integer.valueOf(StrUtil.null2Str(YBMXZDSCTSMZ));
        if(feeDetailsList.size() > sccs){
            for(int i = 0; i < feeDetailsList.size(); i++){
                feeDetailsListOneTimeList.add(feeDetailsList.get(i));
                if((i+1) % sccs== 0 || i == feeDetailsList.size()-1){//
                    body.put("mxzdh", mxzdh);
                    SettleResultDTO uploadDTO = uploadFeeDetailsOneTime_MZ(body, feeDetailsListOneTimeList);
                    if(uploadDTO.getCode() != ServiceCode.CODE_OK){
                        res.put("code", ServiceCode.CODE_ERROR);
                        return uploadDTO;
                    }
                    Map<String, Object> uploadResMap = unifiedBusinessManager.convertYBDTOData2Map(uploadDTO.getData().toString());
                    resultList.add(uploadResMap);
                    mxzdh = uploadResMap.get("MXZDH")+"";
                    feeDetailsListOneTimeList.clear();
                }
            }
            resultDTO.setCode(ServiceCode.CODE_OK);
            resultDTO.setMsg("");
            resultDTO.setData(resultList);
        } else{
            SettleResultDTO oneDTO = uploadFeeDetailsOneTime_MZ(body, feeDetailsList);
            if(oneDTO.getCode() != ServiceCode.CODE_OK){
                res.put("code", ServiceCode.CODE_ERROR);
                return oneDTO;
            }
            Map<String, Object> oneResMap = unifiedBusinessManager.convertYBDTOData2Map(oneDTO.getData().toString());
            resultList.add(oneResMap);
            resultDTO.setCode(ServiceCode.CODE_OK);
            resultDTO.setMsg("");
            resultDTO.setData(resultList);
        }
        return resultDTO;
    }

    /**
     * 获取需要上传的费用明细,取处方hjje
     * @param body
     * @return
     */
    private SettleResultDTO getFeeDetailsUploadList2_MZ(Map<String, Object> body){
        SettleResultDTO resultDTO = new SettleResultDTO();
        Integer jgid = Integer.parseInt(body.get("jgid")+"");
        String cfsbxhs = body.get("cfsbxhs")+"";
        String yjsbxhs = body.get("yjsbxhs")+"";
        String dbtype = body.get("dbtype")+"";
        String jfbz = "0";
        if(!"".equals(dbtype)){
            if("3".equals(dbtype) || "4".equals(dbtype)){
                jfbz = "1";
            }else if("6".equals(dbtype)){
                jfbz = "2";
            }
        }
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("CFSBXHS", cfsbxhs);
        paramMap.put("YJSBXHS", yjsbxhs);
        paramMap.put("JZLSH", StrUtil.null2Str(body.get("jzlsh")));

        paramMap.put("CFSBXH",body.get("cflist"));
        paramMap.put("YJSBXH",body.get("yjlist"));
        int bz = StrUtil.null2Str(body.get("bz")) == "" ?1:2;
        paramMap.put("BZ", bz);
        //伤残
        String personspectag = StrUtil.null2Str(body.get("personspectag"));
        List<Map<String, Object>> feeDetailsList = shybSn01Dao.getMxzd(paramMap);
        if(feeDetailsList.isEmpty()){
            resultDTO.setCode(ServiceCode.CODE_ERROR);
            resultDTO.setMsg("没有需要结算的明细");
            return resultDTO;
        }
        String brxz = StrUtil.null2Str(body.get("brxz"));
        String MZBKBRXZ = sysXtcsCacheSer.getCsz(jgid, "MZBKBRXZ");
        for(int i=0;i<feeDetailsList.size();i++){
            Map<String, Object> fee = feeDetailsList.get(i);
            String mxxmgg = MedicineUtils.parseString(fee.get("MXXMGG"));
            if(mxxmgg!=null && mxxmgg.length() >= 120){
                mxxmgg = mxxmgg.substring(0,120);
            }
            fee.put("MXXMGG", mxxmgg.trim());
            String mxxmdw = MedicineUtils.parseString(fee.get("MXXMDW"));
            if(mxxmdw!=null && mxxmdw.length() >= 20){
                mxxmdw = mxxmdw.substring(0,20);
            }
            fee.put("MXXMDW", mxxmdw.trim());
            String yyclpp = MedicineUtils.parseString(fee.get("YYCLPP"));
            if(yyclpp!= null && yyclpp.length() >=100){
                yyclpp = yyclpp.substring(0,100);
            }
            fee.put("YYCLPP", yyclpp.trim());
            fee.put("FYLB",String.valueOf(fee.get("FYLB")!=null?fee.get("FYLB"):"0"));
           if(String.valueOf(fee.get("FYLB")).length() < 2){
                fee.put("FYLB", "0"+String.valueOf(fee.get("FYLB")));
            }
            fee.put("SFXFMX", 0);
            fee.put( "XFMX",  Collections.EMPTY_LIST);

            Long fylx = MedicineUtils.parseLong(fee.get("YPLX"));
            Integer fyxh = MedicineUtils.parseInt(fee.get("FYXH"));
            BigDecimal zfbl = new BigDecimal(MedicineUtils.parseString(fee.get("ZFBL")));

            BigDecimal mxxmdj = new BigDecimal(MedicineUtils.parseString(fee.get("MXXMDJ")));
            BigDecimal mxxmsl = new BigDecimal(MedicineUtils.parseString(fee.get("MXXMSL")));
            mxxmdj = mxxmdj.setScale(3, BigDecimal.ROUND_HALF_UP);
            mxxmsl = mxxmsl.setScale(5, BigDecimal.ROUND_HALF_UP);
            //异地修改 小数点位数
            mxxmdj = mxxmdj.setScale(6, BigDecimal.ROUND_HALF_UP);
            mxxmsl = mxxmsl.setScale(4, BigDecimal.ROUND_HALF_UP);
            fee.put("MXXMDJ", mxxmdj);
            fee.put("MXXMSL", mxxmsl);
            //BigDecimal mxxmje = mxxmdj.multiply(mxxmsl).setScale(3, BigDecimal.ROUND_HALF_UP);

            BigDecimal hjje = new BigDecimal(MedicineUtils.parseString(fee.get("HJJE")));
            BigDecimal mxxmje = hjje.setScale(3, BigDecimal.ROUND_HALF_UP);
            //异地修改 小数点位数
            mxxmje = hjje.setScale(2, BigDecimal.ROUND_HALF_UP);
            fee.put("MXXMJE", mxxmje);

            boolean isSpecial = Boolean.FALSE;
            if(fylx == 0){
                String cflx = StrUtil.null2Str(body.get("cflx"));
                isSpecial = isSpecialCzf(brxz, cflx,fyxh,jgid);
            }
            String tsfy = StrUtil.null2Str(fee.get("TSFY"));//特殊药品
            if (brxz.equals(MZBKBRXZ)) {//帮困
                fee.put("MXXMYBJSFWFY",mxxmje);//
                fee.put("MXXMJYFY",mxxmje);
                fee.put("BXBZ", "0");
            }else if(isSpecial){
                //特殊项目费用，自负比例的金额作为纯自费金额
                fee.put("MXXMYBJSFWFY", ( mxxmje.subtract(  mxxmje.multiply(zfbl).setScale(2, BigDecimal.ROUND_HALF_UP) ) ).setScale(3, BigDecimal.ROUND_HALF_UP) );
                fee.put("MXXMJYFY",     ( mxxmje.subtract(  mxxmje.multiply(zfbl).setScale(2, BigDecimal.ROUND_HALF_UP) ) ).setScale(3, BigDecimal.ROUND_HALF_UP) );
                fee.put("BXBZ", "0");
            } else if("15".equals(tsfy)){
                BigDecimal grzfde = BigDecimal.ZERO;
                if(!StringUtils.isEmpty(MedicineUtils.parseString(fee.get("GRZFDE")))){
                    grzfde = new BigDecimal(MedicineUtils.parseString(fee.get("GRZFDE")));
                }
                //高价药定额支付作为个人自负部分
                BigDecimal grzfdeze = grzfde.multiply(mxxmsl);
                fee.put("MXXMYBJSFWFY",mxxmje);
                fee.put("MXXMJYFY",( mxxmje.subtract(grzfdeze) ).setScale(3, BigDecimal.ROUND_HALF_UP));
                fee.put("BXBZ", "2");
            }else if (zfbl.intValue() == 1) {
                fee.put("MXXMYBJSFWFY",( BigDecimal.ZERO ).setScale(3, BigDecimal.ROUND_HALF_UP));
                fee.put("MXXMJYFY",( BigDecimal.ZERO ).setScale(3, BigDecimal.ROUND_HALF_UP));
                fee.put("BXBZ", "1");
            } else if("2".equals(personspectag)) {
                fee.put("MXXMYBJSFWFY",mxxmje);//
                fee.put("MXXMJYFY",mxxmje);
                fee.put("BXBZ", "0");
            }else if("3".equals(dbtype) || "4".equals(dbtype) ||"6".equals(dbtype)){
                fee.put("MXXMYBJSFWFY",mxxmje);//
                fee.put("MXXMJYFY",mxxmje);
                fee.put("BXBZ", "0");
                fee.put("JFBZ",jfbz);
            } else{
                fee.put("MXXMYBJSFWFY",mxxmje);
                //fee.put("MXXMJYFY",( mxxmje.subtract(mxxmje.multiply(zfbl)) ).setScale(3, BigDecimal.ROUND_HALF_UP));
                fee.put("MXXMJYFY",( mxxmje.subtract( mxxmje.multiply(zfbl).setScale(2, BigDecimal.ROUND_HALF_UP)  )  ).setScale(3, BigDecimal.ROUND_HALF_UP));
                //异地修改 小数点位数
                fee.put("MXXMJYFY",( mxxmje.subtract( mxxmje.multiply(zfbl).setScale(2, BigDecimal.ROUND_HALF_UP)  )  ).setScale(2, BigDecimal.ROUND_HALF_UP));
                fee.put("BXBZ", "0");
            }
        }
        resultDTO.setCode(ServiceCode.CODE_OK);
        resultDTO.setData(feeDetailsList);
        return resultDTO;
    }

    /**
     * 特殊费用
     * @param brxz
     * @param cflx
     * @param yPXH
     * @param jgid
     * @return
     */
    private boolean isSpecialCzf(String brxz, String cflx,Integer yPXH, Integer jgid) {
        String SPBRXZANDFYXH = sysXtcsCacheSer.getCsz(jgid,"SPBRXZANDFYXH");//'32'-''1'-1234','
        if (SPBRXZANDFYXH==null||SPBRXZANDFYXH.trim().length()==0||SPBRXZANDFYXH.equals("0")||SPBRXZANDFYXH.indexOf("-")==-1) {
            return false;
        }
        String [] array = SPBRXZANDFYXH.split("\\|\\|");
        Map<String,String> map=findArray(array);
        String key=(brxz+"-"+cflx+"-"+yPXH).toString();
        boolean contatins=map.containsValue(key);
        if (contatins) {
            return true;
        }else{
            return false;
        }
    }

    public static Map<String, String> findArray(String[] str) {
        Map<String, String> map = new HashMap<String, String>();
        if(str==null){
            return null;
        }
        for(int i=0;i<str.length;i++){
            map.put("key"+i, str[i]);
        }
        return map;
    }

    /**
     * 门诊上传费用明细SN01
     * @param feeDetailsList
     * @return
     */
    private SettleResultDTO uploadFeeDetailsOneTime_MZ(Map<String, Object> body, List<Map<String, Object>> feeDetailsList) {
        String orgId = StrUtil.null2Str(body.get("jgdm"));
        String jgid = StrUtil.null2Str(body.get("jgid"));
        int ygdm = Integer.parseInt(StrUtil.null2Str(body.get("ygdm")));
        String ygxm = StrUtil.null2Str(body.get("ygxm"));
        String xsywlx = StrUtil.null2Str(body.get("xsywlx"));
        SN01_MZ sn01_mz = new SN01_MZ();
        sn01_mz.setCardtype(StrUtil.null2Str(body.get("cardtype")));
        String cardData = StrUtil.null2Str(body.get("carddata"));
        if (cardData != null && !"".equals(cardData)) {
            sn01_mz.setCarddata(cardData);
        } else {
            sn01_mz.setCarddata("");
        }
        sn01_mz.setJzdyh(StrUtil.null2Str(body.get("jzdyh")));
        sn01_mz.setDjh(StrUtil.null2Str(body.get("djh")));
        sn01_mz.setMxzdh(StrUtil.null2Str(body.get("mxzdh")));
        sn01_mz.setJslxbz(StrUtil.null2Str(body.get("jslxbz")));

        Calendar sysCal = Calendar.getInstance();
        int year = sysCal.get(Calendar.YEAR);//获取年份
        int month = sysCal.get(Calendar.MONTH);//获取月份
        int day = sysCal.get(Calendar.DATE);//获取日
        int hour = sysCal.get(Calendar.HOUR);//小时
        int minute = sysCal.get(Calendar.MINUTE);//分
        int second = sysCal.get(Calendar.SECOND);//秒

        //2. 获取需要上传的费用明细集合
        SettleResultDTO maxXhDTO = getMaxXH_MZ(body);
        if (maxXhDTO.getCode() == ServiceCode.CODE_ERROR) {
            maxXhDTO.setCode(ServiceCode.CODE_ERROR);
            maxXhDTO.setMsg("查询本次就诊中最大序号失败!");
            return maxXhDTO;
        }
        int maxxh = Integer.valueOf(maxXhDTO.getMsg());
        if (maxxh != 0) {
            maxxh += 1000;
        }
        Map<String, Map<String, Object>> details = new HashMap<String, Map<String, Object>>();//获取对应明细账单序号的费用名称等信息
        List<Ylxmmx> list = new ArrayList<Ylxmmx>();
        BigDecimal bcmxylfyze = BigDecimal.ZERO;
        for (Map<String, Object> feeDetailsMap : feeDetailsList) {
            bcmxylfyze = bcmxylfyze.add(new BigDecimal(StrUtil.nullAndEmpty2Zero(feeDetailsMap.get("MXXMJE"))));//明细项目金 额
            Ylxmmx xmmx = new Ylxmmx();
            xmmx.setXh(maxxh + Integer.valueOf(feeDetailsMap.get("XH").toString()));//费用明细单体序号
            xmmx.setCfh(StrUtil.null2Str(feeDetailsMap.get("CFH")));//处方号
            xmmx.setDeptid(StrUtil.null2Str(feeDetailsMap.get("DEPTID")));//科室编码
            xmmx.setKsmc(StrUtil.null2Str(feeDetailsMap.get("KSMC")));//科室名称
            xmmx.setCfysh(StrUtil.null2Str(feeDetailsMap.get("CFYSH")));//处方医师号
            xmmx.setCfysxm(StrUtil.null2Str(feeDetailsMap.get("CFYSXM")));//处方医师姓名
            xmmx.setFylb(StrUtil.null2Str(feeDetailsMap.get("FYLB")));//费用类别
            xmmx.setMxxmbm(StrUtil.null2Str(feeDetailsMap.get("MXXMBM")));//明细项目编码
            xmmx.setMxxmmc(StrUtil.null2Str(feeDetailsMap.get("MXXMMC")));//明细项目名称
            xmmx.setMxxmdw(StrUtil.null2Str(feeDetailsMap.get("MXXMDW")));//明细项目单位
            xmmx.setMxxmdj(StrUtil.nullAndEmpty2Zero(feeDetailsMap.get("MXXMDJ")));//明细项目单
            xmmx.setMxxmsl(StrUtil.nullAndEmpty2Zero(feeDetailsMap.get("MXXMSL")));//明细项目数量
            xmmx.setMxxmje(StrUtil.nullAndEmpty2Zero(feeDetailsMap.get("MXXMJE")));//明细项目金额
            xmmx.setMxxmjyfy(StrUtil.nullAndEmpty2Zero(feeDetailsMap.get("MXXMJYFY")));//医疗费用交易费用
            xmmx.setMxxmybjsfwfy(StrUtil.nullAndEmpty2Zero(feeDetailsMap.get("MXXMYBJSFWFY")));//明细项目医保结算范围费用
            xmmx.setYyclpp(StrUtil.null2Str(feeDetailsMap.get("YYCLPP")));//医用材料品牌/药品通用名
            xmmx.setZczh(StrUtil.null2Str(feeDetailsMap.get("ZCZH")));//注册证号
            xmmx.setMxxmgg(StrUtil.null2Str(feeDetailsMap.get("MXXMGG")));//明细项目规格
            xmmx.setMxxmsyrq(StrUtil.null2Str(feeDetailsMap.get("MXXMSYRQ")));//明细项目使用日期
            xmmx.setBxbz(StrUtil.null2Str(feeDetailsMap.get("BXBZ")));//报销标志
            xmmx.setSftfbz(StrUtil.null2Str(feeDetailsMap.get("SFTFBZ")));//收费、退费标志
            xmmx.setJfbz(StrUtil.null2Str(feeDetailsMap.get("JFBZ")));//减负标志
            xmmx.setSfxfmx("0");//是否细分明细
            xmmx.setXfmx(new ArrayList<Ylxfmx>());
            list.add(xmmx);
            Map<String, Object> detailMap = new HashMap<String, Object>();
            detailMap.put("JLXH", StrUtil.null2Str(feeDetailsMap.get("JLXH")));
            detailMap.put("YPLX", StrUtil.null2Str(feeDetailsMap.get("YPLX")));
            detailMap.put("FYMC", StrUtil.null2Str(feeDetailsMap.get("FYMC")));
            detailMap.put("ZFBL", StrUtil.null2Str(feeDetailsMap.get("ZFBL")));
            detailMap.put("MXXMDJ", StrUtil.null2Str(feeDetailsMap.get("MXXMDJ")));
            detailMap.put("MXXMSL", StrUtil.null2Str(feeDetailsMap.get("MXXMSL")));
            details.put((maxxh + Integer.valueOf(feeDetailsMap.get("XH").toString())) + "", detailMap);
        }
        sn01_mz.setBcmxylfyze(StrUtil.nullAndEmpty2Zero(bcmxylfyze.setScale(2, BigDecimal.ROUND_HALF_UP)));
        sn01_mz.setMxxms(list);
        SettleResultDTO sn01Dto = new SettleResultDTO();
        if (cardData != null && !"".equals(cardData) && "1".equals(xsywlx)) {
            //sn01Dto = unifiedBusinessManager.sn01_mzonline(orgId, TradingChannelEnum.ONLINE, sn01_mz);
        } else {
            sn01Dto = unifiedBusinessManager.sn01_mz(orgId, jgid,ygdm,ygxm,
                    StrUtil.null2Str(body.get("ip")),TradingChannelEnum.OFFLINE, sn01_mz);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        MessageBody messageBody = JackJsonUtil.parse(sn01Dto.getData().toString(), MessageBody.class);
        String jysj = sdf.format((Date) messageBody.getJysj());
        HashMap<String, Object> resMap = (HashMap<String, Object>) HisUtil.upcaseKeys((LinkedHashMap) messageBody.getXxnr());
        String mxzdh = StrUtil.null2Str(resMap.get("MXZDH"));
        List<Map<String, Object>> MXXMS = (List<Map<String, Object>>) resMap.get("MXXMS");
        String msg = "";
        if(null!=MXXMS){
            for(int i = 0;i<MXXMS.size();i++){
                HashMap<String, Object> sn01 = new HashMap<String, Object>();
                String xh = StrUtil.nullAndEmpty2Zero(MXXMS.get(i).get("xh"));
                if(!"".equals(xh.trim())){//如果序号在医保返回为空的情况下，不保存数据。测试时回为空字符串（在医保返回错误联系系统管理员时返回为空），且其他值也没有任何内容
                    sn01.put("MXZDH", mxzdh);// 明细账单号
                    sn01.put("XH", StrUtil.null2Str(MXXMS.get(i).get("xh")));//费用明细单体序号
                    sn01.put("CLBZ", StrUtil.null2Str(MXXMS.get(i).get("clbz")));// 处理标志
                    sn01.put("FHXX", StrUtil.null2Str(MXXMS.get(i).get("fhxx")));// 处理返回信息
                    sn01.put("BXBZ", StrUtil.null2Str(MXXMS.get(i).get("bxbz")));// 报销标志
                    sn01.put("MXXMJE", StrUtil.null2Str(MXXMS.get(i).get("mxxmje")) );// 明细项目金额
                    sn01.put("MXXMJYFY", StrUtil.null2Str(MXXMS.get(i).get("mxxmjyfy")) );// 明细项目交易费用
                    sn01.put("MXXMYBJSFWFY", StrUtil.null2Str(MXXMS.get(i).get("mxxmybjsfwfy")) );// 明细项目医保结算范围费用
                    sn01.put("JZDYH", StrUtil.null2Str(body.get("jzdyh")));
                    sn01.put("CXBZ",0);// 撤销标志
                    sn01.put("JSLX", 120);
                    sn01.put("BRXZ",  StrUtil.null2Str(body.get("brxz")));
                    sn01.put("ZFBL", StrUtil.null2Str(details.get(xh).get("ZFBL")));
                    sn01.put("JLXH", StrUtil.null2Str(details.get(xh).get("JLXH")));
                    sn01.put("FYMC", StrUtil.null2Str(details.get(xh).get("FYMC")));
                    sn01.put("YPLX", StrUtil.null2Str(details.get(xh).get("YPLX")));
                    sn01.put("MXXMDJ", StrUtil.null2Str(details.get(xh).get("MXXMDJ")));
                    sn01.put("MXXMSL", StrUtil.null2Str(details.get(xh).get("MXXMSL")));
                    sn01.put("JYSJ", jysj);// 明细账单号
                    //dao.doSave("create","phis.application.yb.schemas.YB_SN01_MZ", sn01,false);
                    shybSn01Dao.saveSn01Mz(sn01);
                    if("1".equals(StrUtil.null2Str(MXXMS.get(i).get("clbz")))){
                        msg+=StrUtil.null2Str(details.get(xh).get("FYMC"))+" "+ StrUtil.null2Str(MXXMS.get(i).get("fhxx")+" . ");
                    }
                }
            }
        }
        sn01Dto.setMsg(sn01Dto.getMsg()+msg);
        return sn01Dto;
    }

    /**
     * 根据 获取SN01中的最大MAX序号
     * @return
     */
    private SettleResultDTO getMaxXH_MZ(Map<String, Object> body){
        SettleResultDTO resultDTO = new SettleResultDTO();
            String jzdyh = body.get("jzdyh")+"";
            List<Map<String, Object>> JZDYHList = shybSn01Dao.getMaxxh(jzdyh);
            resultDTO.setCode(ServiceCode.CODE_OK);
            resultDTO.setMsg(StrUtil.nullAndEmpty2Zero(JZDYHList.get(0).get("XH")));
            return resultDTO;
    }

    /**
     * 收费预结算
     * @param body
     * @param res
     * @return
     */
    @Override
    public SettleResultDTO ChargePreSettlement_MZ(Map<String, Object> body,Map<String, Object> res) {
        SettleResultDTO resultDTO = new SettleResultDTO();
        SettleResultDTO zdnoResDTO = queryYBzdno_MZ(body);
        if(zdnoResDTO.getCode() == ServiceCode.CODE_ERROR){
            res.put("code", ServiceCode.CODE_ERROR);
            return zdnoResDTO;
        }
        List<Map<String, Object>> zdnoList = (List<Map<String, Object>>) zdnoResDTO.getData();
        List<DiagnosisInfocy> zdnos = new ArrayList<DiagnosisInfocy>();
        for(int i = 0 ;i<zdnoList.size();i++){
            DiagnosisInfocy zdno = new DiagnosisInfocy();
            zdno.setZdno( zdnoList.get(i).get("zdno")+"");
            zdno.setZdmc(zdnoList.get(i).get("zdmc")+"");
            zdnos.add(zdno);
        }
        //获取大病信息
        String dbtype = "";
        Map<String, Object> dbtypeList = ybSh01Dao.getDbtype(StrUtil.null2Str(body.get("jzlsh")),StrUtil.null2Str(body.get("carddata")));
        if(dbtypeList!=null && !"".equals(dbtypeList.get("dbbz"))){
            dbtype = StrUtil.null2Str(dbtypeList.get("dbbz"));
            body.put("dbtype",dbtype);
            body.put("jslxbz","32"+dbtype);
            body.put("yllb","S13");
        }
        //获取工伤认定号信息
        String gsrdh = "";
        Map<String, Object> gsrdhList = ybSh01Dao.getGsrdh(StrUtil.null2Str(body.get("jzlsh")),StrUtil.null2Str(body.get("carddata")));
        if(gsrdhList!=null && !"".equals(gsrdhList.get("gsrdh"))){
            gsrdh = StrUtil.null2Str(gsrdhList.get("gsrdh"));
            body.put("gsrdh",gsrdh);
            body.put("persontype","1");
            body.put("yllb","S11");
        }
        List<HashMap<String, Object>> resultSn01 = (List<HashMap<String, Object>>)body.get("resultSn01");
        List<DetailsBillcy> mxzdhs = new ArrayList<DetailsBillcy>();
        //   BigDecimal bcmxylfyze = BigDecimal.ZERO;
        BigDecimal totalexpense = BigDecimal.ZERO;//交易费用总额
        BigDecimal ybjsfwfyze = BigDecimal.ZERO;//医保结算范围费用总额
        BigDecimal fybjsfwfyze = BigDecimal.ZERO;//非医保结算范围费用总额
        String sn01mxzdh ="";
        for(int i = 0 ;i<resultSn01.size();i++){//只对应同一明细账单号的逻辑
            HashMap<String, Object> sn01xxnr = (HashMap<String, Object>)resultSn01.get(i);
            sn01mxzdh =sn01xxnr.get("MXZDH")+"";
            List<Map<String, Object>> sn01MXXMS = (List<Map<String, Object>>)sn01xxnr.get("MXXMS");
            for(int j = 0;j<sn01MXXMS.size();j++){
                if("2".equals(StrUtil.null2Str(sn01MXXMS.get(i).get("bxbz")))){ //高价药
                    totalexpense = totalexpense.add(new BigDecimal(StrUtil.nullAndEmpty2Zero(sn01MXXMS.get(j).get("mxxmybjsfwfy"))));//交易费用总额
                }else{
                    totalexpense = totalexpense.add(new BigDecimal(StrUtil.nullAndEmpty2Zero(sn01MXXMS.get(j).get("mxxmjyfy"))));//交易费用总额
                }

                ybjsfwfyze =  ybjsfwfyze.add(new BigDecimal(StrUtil.nullAndEmpty2Zero(sn01MXXMS.get(j).get("mxxmybjsfwfy"))));//医保结算范围费用总额
                fybjsfwfyze = fybjsfwfyze.add(
                        new BigDecimal(StrUtil.nullAndEmpty2Zero(sn01MXXMS.get(j).get("mxxmje")))
                                .subtract(new BigDecimal(StrUtil.nullAndEmpty2Zero(sn01MXXMS.get(j).get("mxxmybjsfwfy"))))
                );//非医保结算范围费用总额
            }
        }
        DetailsBillcy mxzdh = new DetailsBillcy();
        mxzdh.setMxzdh(sn01mxzdh);
        mxzdh.setTotalexpense(StrUtil.nullAndEmpty2Zero( totalexpense.setScale(2, BigDecimal.ROUND_HALF_UP)));
        mxzdh.setYbjsfwfyze( StrUtil.nullAndEmpty2Zero( ybjsfwfyze.setScale(2, BigDecimal.ROUND_HALF_UP)));
        mxzdh.setFybjsfwfyze(StrUtil.nullAndEmpty2Zero( fybjsfwfyze.setScale(2, BigDecimal.ROUND_HALF_UP)));
        mxzdhs.add(mxzdh);

        res.put("mxzdhs", mxzdhs);
        res.put("zdnos", zdnos);
        res.put("jzdyh", StrUtil.null2Str(body.get("jzdyh")));
        String  orgId = StrUtil.null2Str(body.get("jgdm"));
        String  jgid = StrUtil.null2Str(body.get("jgid"));
        int  ygdm = Integer.parseInt(StrUtil.null2Str(body.get("ygdm")));
        String  ygxm = StrUtil.null2Str(body.get("ygxm"));
        SI11 si11 = new SI11();
        si11.setCardtype(StrUtil.null2Str(body.get("cardtype")));
        String cardData = StrUtil.null2Str(body.get("carddata"));
        si11.setCarddata(cardData);
        si11.setDeptid(StrUtil.null2Str(body.get("deptid")));
        si11.setPersonspectag(StrUtil.null2Str(body.get("personspectag")));
        if(!"".equals(dbtype)){
            si11.setYllb(MedicalCategoryEnum.S13);
        }else{
            si11.setYllb(MedicalCategoryEnum.S11);//门急诊11门诊 12急诊
        }
        si11.setPersontype(StrUtil.null2Str(body.get("persontype")));//
        si11.setGsrdh(StrUtil.null2Str(body.get("gsrdh")));//
        si11.setZdnos(zdnos);//
        si11.setDbtype(StrUtil.null2Str(body.get("dbtype")));//
        si11.setJsksrq("" );//
        si11.setJsjsrq("");
        si11.setJzdyh(StrUtil.null2Str(body.get("jzdyh")));
        si11.setXsywlx(StrUtil.null2Str(body.get("xsywlx")));
        si11.setJzcs("");
        si11.setMxzdhs(mxzdhs);
        String fssj = HisUtil.getDate("yyyyMMdd/HHmmss/", new Date());

        if (cardData != null && !"".equals(cardData) && !"".equals(StrUtil.null2Str(body.get("xsywlx")))) {
        }else{
            resultDTO = unifiedBusinessManager.si11(orgId, jgid,ygdm,ygxm,TradingChannelEnum.OFFLINE,body.get("ip")+"", si11);
        }
        HashMap<String, Object> si11Map = new HashMap<String, Object>();
        String xzqhdm = "";
        if(resultDTO.getCode() == ServiceCode.CODE_OK){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd/HHmmss/");
            MessageBody messageBody = JackJsonUtil.parse(resultDTO.getData().toString(), MessageBody.class);
            String jysj =  sdf.format((Date)messageBody.getJysj());
            xzqhdm = messageBody.getXzqhdm();
            si11Map = (HashMap<String, Object>)messageBody.getXxnr();
            si11Map.put("fssj",fssj);
            si11Map.put("jssj",jysj);
            si11Map.put("knsj","");
            si11Map.put("jgid",body.get("jgdm"));
            si11Map.put("ybks",body.get("deptid"));
            si11Map.put("jfje", 0);
            si11Map.put("jzdyh", body.get("jzdyh"));
            si11Map.put("jslx", 120);
            shybSi11Dao.insertYbsi11(si11Map);
        }
        return resultDTO;
    }

    private SettleResultDTO queryYBzdno_MZ(Map<String, Object> body){
        SettleResultDTO resultDTO = new SettleResultDTO();
        /*
        String cfsbs = body.get("cfsbs")+"";
        String yjxhs = body.get("yjxhs")+"";
        String JZXHS = "";
        if(!"".equals(cfsbs)){
            List<Map<String,Object>> cfList = opCf01Service.getJzxh(cfsbs);
            for(int i =0;i<cfList.size();i++){
                if(!"".equals(StrUtil.null2Str(cfList.get(i).get("JZXH")))&&!"0".equals(StrUtil.null2Str(cfList.get(i).get("JZXH")))){
                    JZXHS = StrUtil.null2Str(cfList.get(i).get("JZXH"));
                }
            }
        }
        if(!"".equals(yjxhs)){
            List<Map<String,Object>> yjList = opYjcf01Service.getJzxh(yjxhs);
            for(int i = 0 ; i <yjList.size();i++){
                if(!"".equals(StrUtil.null2Str(yjList.get(i).get("JZXH")))&&!"0".equals(StrUtil.null2Str(yjList.get(i).get("JZXH")))
                    &&!"".equals(JZXHS)){
                    JZXHS =JZXHS+","+StrUtil.null2Str(yjList.get(i).get("JZXH"));
                }else if(!"".equals(StrUtil.null2Str(yjList.get(i).get("JZXH")))&&!"0".equals(StrUtil.null2Str(yjList.get(i).get("JZXH")))
                        &&"".equals(JZXHS)){
                    JZXHS = StrUtil.null2Str(yjList.get(i).get("JZXH"));
                }
            }
        }
        if(!"".equals(JZXHS)){//根据处方医技单获取诊断编码
            Map<String, Object> parmjzxh = new HashMap<String, Object>();
            parmjzxh.put("JZXHS", JZXHS);
            List<Map<String, Object>> sqlzdlist = opBrzdService.getZd(parmjzxh);
            if(sqlzdlist.size()>0){
                resultDTO.setCode(ServiceCode.CODE_OK);
                resultDTO.setData(sqlzdlist);
                return resultDTO;
            }
        }*/
        //List<Map<String, Object>> JZDYHList = dicJbbmService.getZd(body.get("zdxh")+"");
        List<Map<String, Object>> JZDYHList = shybSi11Dao.getZdBysf(body.get("jzlsh")+"");
        resultDTO.setCode(ServiceCode.CODE_OK);
        resultDTO.setData(JZDYHList);
        return resultDTO;
    }

    /**
     * 门诊收费实结算S12
     * @param body
     * @param res
     * @return
     */
    @Override
    public SettleResultDTO confirmCharge_MZ(Map<String, Object> body,Map<String, Object> res) {
        SettleResultDTO resultDTO = new SettleResultDTO();
        String  orgId = StrUtil.null2Str(body.get("jgdm"));
        String jgid = StrUtil.null2Str(body.get("jgid"));
        int ygdm = Integer.parseInt(StrUtil.null2Str(body.get("ygdm")));
        String ygxm = StrUtil.null2Str(body.get("ygxm"));
        String  ip = StrUtil.null2Str(body.get("ip"));
        //查询大病项目代码
        String dbtype = "";
        Map<String, Object> dbtypeList = ybSh01Dao.getDbtype(StrUtil.null2Str(body.get("jzlsh")),StrUtil.null2Str(body.get("carddata")));
        if(dbtypeList!=null && !"".equals(dbtypeList.get("dbbz"))){
            dbtype = StrUtil.null2Str(dbtypeList.get("dbbz"));
            body.put("dbtype",dbtype);
        }
        //获取工伤认定号信息
        String gsrdh = "";
        Map<String, Object> gsrdhList = ybSh01Dao.getGsrdh(StrUtil.null2Str(body.get("jzlsh")),StrUtil.null2Str(body.get("carddata")));
        if(gsrdhList!=null && !"".equals(gsrdhList.get("gsrdh"))){
            gsrdh = StrUtil.null2Str(gsrdhList.get("gsrdh"));
            body.put("gsrdh",gsrdh);
            body.put("persontype","1");
            body.put("yllb","S11");
        }

        List<Map<String, Object>> detailsBill = shybSn01Dao.getMxzdhs(StrUtil.null2Str(body.get("jzdyh")));
        List<DetailsBillcy> detailsBills = new ArrayList<DetailsBillcy>();
        DetailsBillcy db = new DetailsBillcy();
        for(int i = 0 ;i<detailsBill.size();i++){
            db.setMxzdh( detailsBill.get(i).get("mxzdh")+"");
            db.setTotalexpense(detailsBill.get(i).get("totalexpense")+"");
            db.setYbjsfwfyze(detailsBill.get(i).get("ybjsfwfyze")+"");
            db.setFybjsfwfyze(detailsBill.get(i).get("fybjsfwfyze")+"");
            detailsBills.add(db);
        }
        SI12 si12 = new SI12();
        si12.setCardtype(StrUtil.null2Str(body.get("cardtype")));
        String cardData = StrUtil.null2Str(body.get("carddata"));
        si12.setCarddata(cardData);
        si12.setDeptid(StrUtil.null2Str(body.get("deptid")));
        si12.setPersonspectag(StrUtil.null2Str(body.get("personspectag")));
        if(!"".equals(dbtype)){
            si12.setYllb(MedicalCategoryEnum.S13);
        }else {
            si12.setYllb(MedicalCategoryEnum.S11);
        }
        si12.setPersontype(StrUtil.null2Str(body.get("persontype")));
        si12.setGsrdh(StrUtil.null2Str(body.get("gsrdh")));
        si12.setZdnos((List<DiagnosisInfocy>) body.get("zdnos"));
        si12.setDbtype(StrUtil.null2Str(body.get("dbtype")));
        si12.setJsksrq("" );
        si12.setJsjsrq("");
        si12.setJzdyh(StrUtil.null2Str(body.get("jzdyh")));
        si12.setXsywlx("");
        si12.setJzcs("");
        si12.setJssqxh(StrUtil.null2Str(body.get("jssqxh")));
        si12.setMxzdhs(detailsBills);
        String fssj = HisUtil.getDate("yyyyMMdd/HHmmss/", new Date());
        resultDTO = unifiedBusinessManager.si12(orgId,jgid,ygdm,ygxm, TradingChannelEnum.OFFLINE, ip,si12);
        if(resultDTO.getCode() == ServiceCode.CODE_OK){
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd/HHmmss/");
                MessageBody messageBody = JackJsonUtil.parse(resultDTO.getData().toString(), MessageBody.class);
                String jysj = sdf.format((Date) messageBody.getJysj());
                HashMap<String, Object> si12Map = (HashMap<String, Object>) (LinkedHashMap) messageBody.getXxnr();
                si12Map.put("mzxh", StrUtil.null2Str(body.get("mzxh")));
                si12Map.put("fssj", fssj);//门急诊收费交易时间
                si12Map.put("jssj", jysj);//门急诊收费交易时间
                si12Map.put("jgid", StrUtil.null2Str(body.get("jgdm")));
                si12Map.put("zfpb", "0");
                if("".equals(si12Map.get("jfje"))){
                    si12Map.put("jfje", 0);
                }
                //si12Map.put("jfje", 0);
                si12Map.put("gsbz", StrUtil.null2Str(body.get("persontype")));//工伤认定号
                si12Map.put("gsrdh", StrUtil.null2Str(body.get("gsrdh")));//工伤认定号
                si12Map.put("zt", "1");//
                si12Map.put("jslx", 120);//
                si12Map.put("knsj", "");
                si12Map.put("orderno", "");
                si12Map.put("dbbz", dbtype);
                shybSi11Dao.insertYbsi12(si12Map);
            }catch(Exception e){
                resultDTO.setCode(ServiceCode.CODE_ERROR);
                resultDTO.setMsg("保存医保登记信息失败,请注销后重新登记!");
            }
        }
        return resultDTO;
    }

    /**
     * 入院登记
     * @param body
     * @return
     * @throws ParseException
     */
    @Override
    public SettleResultDTO hospitalRegister(Map<String, Object> body) {
        SJ11 sj11 = new SJ11();
        sj11.setCardtype(StrUtil.null2Str(body.get("cardtype")));
        sj11.setCarddata(StrUtil.null2Str(body.get("carddata")));
        sj11.setDeptid(StrUtil.null2Str(body.get("deptid")));
        sj11.setDjtype(StrUtil.null2Str(body.get("djtype")));
        sj11.setDjno(StrUtil.null2Str(body.get("djno")));
        String ryrq = body.get("ryrq")+"";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(ryrq);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sj11.setStartdate(date);
        sj11.setEnddate(StrUtil.null2Str(body.get("enddate")));
        sj11.setZdnos((List<DiagnosisInfocy>) body.get("zdnos"));
        sj11.setDbxm(StrUtil.null2Str(body.get("dbxm")));
        sj11.setZd(StrUtil.null2Str(body.get("zd")));
        sj11.setWtrxm(StrUtil.null2Str(body.get("wtrxm")));
        sj11.setWtrsfzh(StrUtil.null2Str(body.get("wtrsfzh")));
        sj11.setYy(StrUtil.null2Str(body.get("yy")));
        sj11.setDes(StrUtil.null2Str(body.get("des")));
        sj11.setDbzl(StrUtil.null2Str(body.get("dbzl")));
        sj11.setYsxm(StrUtil.null2Str(body.get("ysxm")));
        sj11.setYsgh(StrUtil.null2Str(body.get("ysgh")));
        SettleResultDTO resultDTO = unifiedBusinessManager.sj11(StrUtil.null2Str(body.get("orgid")),
                Integer.parseInt(StrUtil.null2Str(body.get("ygdm"))),StrUtil.null2Str(body.get("ygxm")),StrUtil.null2Str(body.get("jgid")),
                TradingChannelEnum.OFFLINE, StrUtil.null2Str(body.get("ip")),sj11);
        if(resultDTO.getCode() == ServiceCode.CODE_OK){
            try {
                HashMap<String, Object> resMap = unifiedBusinessManager.convertYBDTOData2Map(resultDTO.getData().toString());
                resMap.put("ZYH", StrUtil.null2Str(body.get("zyh")));
                resMap.put("CURACCOUNTAMT", new BigDecimal(StrUtil.nullAndEmpty2Zero(resMap.get("CURACCOUNTAMT"))).doubleValue());
                resMap.put("HISACCOUNTAMT", new BigDecimal(StrUtil.nullAndEmpty2Zero(resMap.get("HISACCOUNTAMT"))).doubleValue());
                Integer jlxh=redisFactory.getTableKey(TableName.DB_NAME,TableName.SHYB_RJ11);
                resMap.put("JLXH",jlxh);
                shybSj11Dao.insertYbrj11(resMap);
            } catch (Exception e) {
                resultDTO.setCode(ServiceCode.CODE_ERROR);
                resultDTO.setMsg("保存医保登记信息失败,请注销后重新登记!");
            }
        }
        return resultDTO;
    }

    /**
     * 住院/家床撤销 实现
     * @return
     */
    @Override
    public SettleResultDTO hospitalRevocation(Map<String, Object> body) {
        SettleResultDTO resultDTO = new SettleResultDTO();
        String ip = body.get("ip")+"";
        SettleResultDTO result = new SettleResultDTO();
        SJ21cy sj21 = new SJ21cy();
        sj21.setCardtype(StrUtil.null2Str(body.get("cardtype")));
        sj21.setCarddata(StrUtil.null2Str(body.get("carddata")));
        sj21.setCxtype(StrUtil.null2Str(body.get("cxtype")));
        sj21.setDbxm(StrUtil.null2Str(body.get("dbxm")));
        return resultDTO = unifiedBusinessManager.sj21(StrUtil.null2Str(body.get("orgId")),
                Integer.parseInt(StrUtil.null2Str(body.get("ygdm"))), StrUtil.null2Str(body.get("ygxm")), StrUtil.null2Str(body.get("jgid")),
                TradingChannelEnum.OFFLINE, ip,sj21);
    }

    @Override
    public SettleResultDTO sl01(SL01s sl01s, String s, String s1, int i) {
        return null;
    }


    /**
     * 住院上传费用明细
     * @param body
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SettleResultDTO batchUploadFeeDetails_ZY(Map<String,Object> body,SysUser user){
        //获取就诊单元号
        SettleResultDTO resultDTO = new SettleResultDTO();
        resultDTO.setCode(100);
        SettleResultDTO jzdyhResDTO = getJzdyhByZyh(StrUtil.null2Str(body.get("ZYH")));
        if(jzdyhResDTO.getCode() == ServiceCode.CODE_ERROR){
            return  jzdyhResDTO;
        }
        body.put("jzdyh",jzdyhResDTO.getMsg());
        String mxzdh = "";
        String sclx = StrUtil.null2Str(body.get("sclx"));
        SettleResultDTO feeDetailsListDTO =  new SettleResultDTO();
        List<Map<String, Object>> dateList =  new ArrayList<Map<String, Object>>();
        //	费用明细	 每次上传""后获取新的mxzdh
        feeDetailsListDTO = getFeeDetailsUploadListByZyh(body,sclx);
        if(feeDetailsListDTO.getCode() == ServiceCode.CODE_ERROR){
            return feeDetailsListDTO;
        }
        //获取费用日期集合
        dateList = getDateUploadListByZyh(body,sclx);
        List<Map<String, Object>> feeDetailsList = (List<Map<String, Object>>) feeDetailsListDTO.getData();
        List<Map<String, Object>> feeDetailsListtmp = new ArrayList<Map<String, Object>>();
        if (feeDetailsListDTO.getCode() == ServiceCode.CODE_OK   && feeDetailsList.isEmpty()){
            feeDetailsListDTO.setData(ServiceCode.CODE_OK);
            return feeDetailsListDTO;
        }
        Integer jgid = user.getHospitalId();
        int YBMXSCTSZY = Integer.parseInt(sysXtcsCacheSer.getCsz(jgid, "YBMXSCTSZY"));
        //医保上传
        SN01_MZ sn01 = new SN01_MZ();
        sn01.setCardtype(StrUtil.null2Str(body.get("cardtype")));
        sn01.setCarddata(StrUtil.null2Str(body.get("carddata")));
        sn01.setJzdyh(StrUtil.null2Str(body.get("jzdyh")));
        sn01.setDjh(StrUtil.null2Str(body.get("ZYH")));
        sn01.setMxzdh(mxzdh);//明细账单号
        //sn01.setBcmxylfyze(StrUtil.nullAndEmpty2Zero(body.get("bcmxylfyze")));//本次费用明细包的医疗费用总额
        if(!"".equals(body.get("dbxm"))){
            sn01.setJslxbz("610");
        }else {
            sn01.setJslxbz("610");
        }
        List<Map<String, Object>> feeDetailsListOneTimeList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        for (Map<String, Object>mapdate : dateList){
            feeDetailsListtmp.clear();
            for(Map<String, Object>mapfee : feeDetailsList){
                if (StrUtil.null2Str(mapdate.get("FYRQ")).equals(StrUtil.null2Str(mapfee.get("FYRQ")))){
                    feeDetailsListtmp.add(mapfee);
                }
            }
            if(feeDetailsListtmp.size() ==0){
                continue;
            }
            if (!feeDetailsListtmp.isEmpty() && feeDetailsListtmp.size() > 0){
                if(feeDetailsListtmp.size() > YBMXSCTSZY){
                    for(int i = 0; i < feeDetailsListtmp.size(); i++){
                        feeDetailsListOneTimeList.add(feeDetailsListtmp.get(i));
                        if((i + 1) % YBMXSCTSZY == 0 || i == feeDetailsListtmp.size()-1){
                            sn01.setMxzdh(mxzdh);
                            SettleResultDTO uploadDTO = uploadFeeDetailsOneTime_ZY(body, sn01, feeDetailsListOneTimeList);
                            if(uploadDTO.getCode() != ServiceCode.CODE_OK){
                                return uploadDTO;
                            }
                            Map<String, Object> uploadResMap = unifiedBusinessManager.convertYBDTOData2Map
                                    (StrUtil.null2Str(uploadDTO.getData()));
                            resultList.add(uploadResMap);
                            mxzdh = "";
                            feeDetailsListOneTimeList.clear();
                        }
                    }
                    resultDTO.setData(resultList);
                }else{
                    resultDTO = uploadFeeDetailsOneTime_ZY(body, sn01, feeDetailsListtmp);
                    if(resultDTO.getCode() != ServiceCode.CODE_OK){
                        return resultDTO;
                    }
                }
            }
        }
        return resultDTO;
    }

    /**
     * 根据 ZYH 获取住院登记后保存的 jzdyh
     * @param ZYH
     * @return
     */
    private SettleResultDTO getJzdyhByZyh(String ZYH){
        SettleResultDTO resultDTO = new SettleResultDTO();
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("ZYH", ZYH);
        try {
            List<Map<String,Object>> JzdyhList = shybSj11Dao.getJzdyhByZyh(ZYH);
            if(JzdyhList.isEmpty() || "".equals(StrUtil.null2Str(JzdyhList.get(0).get("JZDYH")))){
                resultDTO.setCode(ServiceCode.CODE_ERROR);
                resultDTO.setMsg("该用户医保未登记，请先补登记!");
                return resultDTO;
            }
            resultDTO.setCode(ServiceCode.CODE_OK);
            resultDTO.setMsg(StrUtil.null2Str(JzdyhList.get(0).get("JZDYH")));
            return resultDTO;
        } catch (Exception e) {
            e.printStackTrace();
            resultDTO.setCode(ServiceCode.CODE_ERROR);
            resultDTO.setMsg("查询就诊单元号错误!");
            return resultDTO;
        }
    }

    /**
     * 根据 ZYH 获取需要上传的费用明细
     * @return
     */
    private SettleResultDTO getFeeDetailsUploadListByZyh(Map<String, Object> body,String sclx){
        SettleResultDTO resultDTO = new SettleResultDTO();
        String zyh = StrUtil.null2Str(body.get("ZYH"));
        String jscs = "0";
        if("10".equals(body.get("JSLX")+"")){
            jscs = "  FYMX.JSCS = " + StrUtil.null2Str(body.get("JSCS")) ;
        }else if("5".equals(body.get("JSLX")+"") || "1".equals(body.get("JSLX")+"")){
            String jsrq = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String endDay = "CURRENT_DATE()";
            if("1".equals(body.get("JSLX")+"") &&
                    StrUtil.null2Str(body.get("JSRQ")+"")!=null &&
                        !jsrq.equals( StrUtil.null2Str(body.get("JSRQ")+""))){
                jscs = "1";
              //  jscs += " and STR_TO_DATE(fymx.FYRQ,'%Y-%m-%d')<= "+endDay;
            }
            log.info("shyb 住院费用明细上传日期 "+ jsrq);
        }
        List<Map<String, Object>> feeDetailsList = shybSn01Dao.getZyfymx(jscs,zyh);
        if(feeDetailsList.isEmpty()){
            resultDTO.setCode(ServiceCode.CODE_OK);
            resultDTO.setData(feeDetailsList);
            return resultDTO;
        }
        for(int i=0;i<feeDetailsList.size();i++) {
            Map<String, Object> fee = feeDetailsList.get(i);
            String mxxmgg = StrUtil.null2Str(fee.get("MXXMGG"));
            if (mxxmgg != null && mxxmgg.length() >= 120) {
                mxxmgg = mxxmgg.substring(0, 120);
                fee.put("MXXMGG", mxxmgg);
            }
            String mxxmdw = StrUtil.null2Str(fee.get("MXXMDW"));
            if (mxxmdw != null && mxxmdw.length() >= 20) {
                mxxmdw = mxxmdw.substring(0, 20);
                fee.put("MXXMDW", mxxmdw);
            }
            String yyclpp = StrUtil.null2Str(fee.get("YYCLPP"));
            if (yyclpp != null && yyclpp.length() >= 100) {
                yyclpp = yyclpp.substring(0, 100);
                fee.put("YYCLPP", yyclpp);
            }
        }
        resultDTO.setCode(ServiceCode.CODE_OK);
        resultDTO.setData(feeDetailsList);
        return resultDTO;
    }

    /**
     * 根据 ZYH 获取需要上传的费用明细
     * @return
     */
    private List<Map<String, Object>> getDateUploadListByZyh(Map<String, Object> body,String SCLX){
        SettleResultDTO resultDTO = new SettleResultDTO();
        String ZYH = StrUtil.null2Str(body.get("ZYH"));
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<Map<String, Object>> dateList =  new ArrayList<Map<String, Object>>();
        String jscs = "0";
        if ("10".equals(body.get("JSLX") + "")) {
            jscs = " AND fymx.JSCS = " + (Integer) body.get("JSCS");
        } else if (("5".equals(body.get("JSLX") + ""))
                || ("1".equals(body.get("JSLX") + ""))) {
        //    jscs = " AND fymx.JSCS = 0";//fymx jscs=0是当前产生的最新费用，最后再改变成最新的结算次数
            String JSRQ = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            System.out.print("结算日期初始化："+JSRQ);
          //  String endDay = "sysdate";
            if ("1".equals(body.get("JSLX") + "")
                    && body.get("JSRQ") != null
                    && !JSRQ.equals(body.get("JSRQ").toString())) {
             //   endDay = "str_to_date('"+body.get("JSRQ")+" 23:59:59','%y-%m-%d %H:%i:%S')";
                jscs = "1";
            //    jscs += " and fymx.FYRQ<"+endDay;
                System.out.print("结束日期："+body.get("JSRQ"));
            }
        }
        dateList = shybSn01Dao.getFyrq(jscs,ZYH,body.get("JSRQ")+"");
        return dateList;
    }

    /**
     * 上传费用明细,不细分明细
     * @param body
     * @param sn01
     * @param feeDetailsList
     * @return
     */
    private SettleResultDTO uploadFeeDetailsOneTime_ZY(Map<String, Object> body, SN01_MZ sn01, List<Map<String, Object>> feeDetailsList) {
        String sclx = StrUtil.null2Str(body.get("sclx"));
        List<Ylxmmx> list = new ArrayList<Ylxmmx>();
        BigDecimal bcmxylfyze = new BigDecimal("0.00");
        for(Map<String, Object> feeDetailsMap : feeDetailsList){
            Ylxmmx xmmx = new Ylxmmx();
            xmmx.setXh(Integer.parseInt(StrUtil.null2Str(feeDetailsMap.get("XH"))));
            xmmx.setCfh(StrUtil.null2Str(feeDetailsMap.get("CFH")));
            xmmx.setDeptid(StrUtil.null2Str(feeDetailsMap.get("DEPTID")));
            xmmx.setKsmc(StrUtil.null2Str(feeDetailsMap.get("KSMC")));
            xmmx.setCfysh(StrUtil.null2Str(feeDetailsMap.get("CFYSH")));
            xmmx.setCfysxm(StrUtil.null2Str(feeDetailsMap.get("CFYSXM")));
            String FYLB = StrUtil.null2Str(feeDetailsMap.get("FYLB"));
            String FYLBStr = FYLB.length() < 2 ? "0" + String.valueOf(FYLB) : String.valueOf(FYLB);
            xmmx.setFylb(FYLBStr);
            xmmx.setMxxmbm(StrUtil.null2Str(feeDetailsMap.get("MXXMBM")));
            xmmx.setMxxmmc(StrUtil.null2Str(feeDetailsMap.get("MXXMMC")));
            xmmx.setMxxmdw(StrUtil.null2Str(feeDetailsMap.get("MXXMDW")));
            xmmx.setMxxmdj(StrUtil.null2Str(feeDetailsMap.get("MXXMDJ")));
            xmmx.setMxxmsl(StrUtil.null2Str(feeDetailsMap.get("MXXMSL")));
            xmmx.setJfbz(StrUtil.null2Str(feeDetailsMap.get("JFBZ")));
            BigDecimal zfbl = new BigDecimal(MedicineUtils.parseString(feeDetailsMap.get("ZFBL")));
            BigDecimal zjje = new BigDecimal(MedicineUtils.parseString(feeDetailsMap.get("ZJJE")));
            BigDecimal mxxmje = zjje.setScale(3, BigDecimal.ROUND_HALF_UP);
            //异地修改 小数点位数
            mxxmje = zjje.setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal zfje = new BigDecimal(MedicineUtils.parseString(feeDetailsMap.get("ZFJE")));
            bcmxylfyze = bcmxylfyze.add(new BigDecimal(StrUtil.nullAndEmpty2Zero(mxxmje)));
            mxxmje = mxxmje.abs();
            zfje = zfje.abs();
            xmmx.setMxxmje(StrUtil.null2Str(mxxmje));
            if (zfbl.intValue() == 1) {
                xmmx.setMxxmybjsfwfy(( BigDecimal.ZERO ).setScale(3, BigDecimal.ROUND_HALF_UP)+"");
                xmmx.setMxxmjyfy(( BigDecimal.ZERO ).setScale(3, BigDecimal.ROUND_HALF_UP)+"");
                //异地修改 小数点位数
                xmmx.setMxxmybjsfwfy(( BigDecimal.ZERO ).setScale(2, BigDecimal.ROUND_HALF_UP)+"");
                xmmx.setMxxmjyfy(( BigDecimal.ZERO ).setScale(2, BigDecimal.ROUND_HALF_UP)+"");
            } else if("2".equals(body.get("personspectag"))) {
                xmmx.setMxxmybjsfwfy(StrUtil.null2Str(mxxmje));
                xmmx.setMxxmjyfy(StrUtil.null2Str(mxxmje));
            } else if("".equals(body.get("dbxm"))){
                if ("3".equals(body.get("dbxm")) || "4".equals(body.get("dbxm")) ){
                    xmmx.setMxxmybjsfwfy(StrUtil.null2Str(mxxmje));
                    xmmx.setMxxmjyfy(StrUtil.null2Str(mxxmje));
                    xmmx.setJfbz("1");
                }else if(  "6".equals(body.get("dbxm"))){
                    xmmx.setMxxmybjsfwfy(StrUtil.null2Str(mxxmje));
                    xmmx.setMxxmjyfy(StrUtil.null2Str(mxxmje));
                    xmmx.setJfbz("2");
                }else if( "A".equals(body.get("dbxm"))){
                    xmmx.setMxxmybjsfwfy(StrUtil.null2Str(mxxmje));
                    xmmx.setMxxmjyfy(StrUtil.null2Str(mxxmje));
                    xmmx.setJfbz("3");
                }else{
                    xmmx.setMxxmybjsfwfy(StrUtil.null2Str(mxxmje));
                    xmmx.setMxxmjyfy(StrUtil.null2Str(mxxmje.subtract(zfje).setScale(2, BigDecimal.ROUND_HALF_UP)));
                }
            }else {
                xmmx.setMxxmybjsfwfy(StrUtil.null2Str(mxxmje));
                xmmx.setMxxmjyfy(StrUtil.null2Str(mxxmje.subtract(zfje).setScale(3, BigDecimal.ROUND_HALF_UP)));
                //异地修改 小数点位数
                xmmx.setMxxmybjsfwfy(StrUtil.null2Str(mxxmje));
                xmmx.setMxxmjyfy(StrUtil.null2Str(mxxmje.subtract(zfje).setScale(2, BigDecimal.ROUND_HALF_UP)));
            }
            xmmx.setYyclpp(StrUtil.null2Str(feeDetailsMap.get("YYCLPP")));
            xmmx.setZczh(StrUtil.null2Str(feeDetailsMap.get("ZCZH")));
            xmmx.setMxxmgg(StrUtil.null2Str(feeDetailsMap.get("MXXMGG")));
            xmmx.setMxxmsyrq(StrUtil.null2Str(feeDetailsMap.get("MXXMSYRQ")));
            xmmx.setBxbz(StrUtil.null2Str(feeDetailsMap.get("BXBZ")));
            xmmx.setSftfbz(StrUtil.null2Str(feeDetailsMap.get("SFTFBZ")));
            xmmx.setSfxfmx("0");
            xmmx.setXfmx(new ArrayList<Ylxfmx>());
            list.add(xmmx);
        }
        bcmxylfyze =bcmxylfyze.setScale(2, BigDecimal.ROUND_HALF_UP);
        sn01.setBcmxylfyze(bcmxylfyze.toString());
        sn01.setMxxms(list);
        SettleResultDTO sn01Dto = unifiedBusinessManager.sn01_mz(StrUtil.null2Str(body.get("orgld")), StrUtil.null2Str(body.get("jgid")),
                Integer.parseInt(StrUtil.null2Str(body.get("ygdm"))),StrUtil.null2Str(body.get("ygxm")),StrUtil.null2Str(body.get("ip")),                                                                 TradingChannelEnum.OFFLINE, sn01);
        if(sn01Dto.getCode() != ServiceCode.CODE_OK){
            saveSHYB_SN01FromYBResult_ZY("SHYB_SN01_ZY_ERR",StrUtil.null2Str(body.get("jzdyh")), sn01Dto, list,sclx);
            return sn01Dto;
        }else{
            saveSHYB_SN01FromYBResult_ZY("SHYB_SN01_ZY", StrUtil.null2Str(body.get("jzdyh")), sn01Dto, list,sclx);
            return sn01Dto;
        }
    }

    /**
     * 上传费用明细成功后保存结果到本地
     * @param sn01Dto
     * @param fymxList
     * @param JZDYH
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public SettleResultDTO saveSHYB_SN01FromYBResult_ZY(String entryName, String JZDYH, SettleResultDTO sn01Dto, List<Ylxmmx> fymxList,String sclx) {
        SettleResultDTO resultDTO = new SettleResultDTO();
        resultDTO.setCode(ServiceCode.CODE_OK);
        Map<String, Object> parameters = new HashMap<String, Object>();
        Date date = new Date();
        HashMap<String, Object> resMap = unifiedBusinessManager.convertYBDTOData2Map(sn01Dto.getData().toString());
        if (resMap.get("MXXMS") == null){
            resultDTO.setCode(ServiceCode.CODE_ERROR);
            resultDTO.setMsg(sn01Dto.getMsg());
            return resultDTO;
        }
        ArrayList<HashMap<String, Object>> MXXMSList = (ArrayList<HashMap<String, Object>>) resMap.get("MXXMS");
        try {
            for(HashMap<String, Object> mxxm : MXXMSList){
                for(Ylxmmx ylxmmx : fymxList){
                    if(ylxmmx.getXh() == MedicineUtils.parseInt(mxxm.get("xh"))){
                        mxxm.put("fymxjlxh", ylxmmx.getCfh());
                    }
                }
                mxxm.put("jzdyh", JZDYH);
                mxxm.put("mxzdh", StrUtil.null2Str(resMap.get("MXZDH")));
                mxxm.put("jysj", date);
                parameters.put("JZDYH", JZDYH);
                parameters.put("MXZDH", StrUtil.null2Str(resMap.get("MXZDH")));
                parameters.put("JLXH", StrUtil.null2Str(mxxm.get("fymxjlxh")));
                if (sn01Dto.getCode() == ServiceCode.CODE_OK && !"SN00".equals(sclx)){
                    shybSn01Dao.updateMxzdh(parameters);
                    mxxm.put("jlxh",redisFactory.getTableKey(TableName.DB_NAME, TableName.SHYB_SN01_ZY));
                    shybSn01Dao.insertYbSn01Zy(mxxm);
                }else{
                    if ("".equals(StrUtil.null2Str(mxxm.get("fhxx")))){
                        mxxm.put("fhxx", StrUtil.null2Str(sn01Dto.getMsg()));
                    }else{
                        sn01Dto.setMsg(StrUtil.null2Str(mxxm.get("fhxx")));
                    }
                    mxxm.put("jlxh",redisFactory.getTableKey(TableName.DB_NAME, TableName.SHYB_SN01_ZY_ERR));
                    shybSn01Dao.insertYbSn01ZyEr(mxxm);
                }
            }
        } catch (Exception e) {
            resultDTO.setCode(ServiceCode.CODE_ERROR);
            resultDTO.setMsg("本地保存明细数据结果失败");
        }
        resMap.put("JZDYH",JZDYH);
        resultDTO.setData(resMap);
        return resultDTO;
    }

    /**
     * si51住院
     *
     * @param body
     * @return
     */
    @Override
    public SettleResultDTO si51(Map<String, Object> body,Map<String, Object> res) {
        String ip = StrUtil.null2Str(body.get("ip"));
        body.put("jyqd", TradingChannelEnum.OFFLINE);
        SettleResultDTO resultDTO = new SettleResultDTO();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SI51 si51 = new SI51();
        String MXZDHS = "";
        Map<String, Object> parameters = new HashMap<String, Object>();
        String jscs = "0";//fymx jscs=0是当前产生的最新费用，最后再改变成最新的结算次数
        try {
            si51.setCarddata(StrUtil.null2Str(body.get("carddata")));
            si51.setCardtype(StrUtil.null2Str(body.get("cardtype")));
            si51.setPersonspectag(StrUtil.null2Str(body.get("personspectag")));
            si51.setYllb(StrUtil.null2Str(body.get("yllb")));
            si51.setPersontype(StrUtil.null2Str(body.get("persontype")));
            si51.setGsrdh(StrUtil.null2Str(body.get("gsrdh")));
            si51.setCyjsbz(StrUtil.null2Str(body.get("cyjsbz")));
            si51.setJsksrq(StrUtil.null2Str(body.get("jsksrq")));
            si51.setJsjsrq(StrUtil.null2Str(body.get("jsjsrq")));
            si51.setZyts(StrUtil.null2Str(MedicineUtils.parseDouble(StrUtil.null2Str(body.get("zyts")))));
            si51.setZyh(StrUtil.null2Str(body.get("zyh")));
            si51.setDeptid(StrUtil.null2Str(body.get("deptid")));
            si51.setJzdyh(StrUtil.null2Str(body.get("jzdyh")));
            si51.setXsywlx(StrUtil.null2Str(body.get("xsywlx")));

            if ("10".equals(body.get("JSLX") + "")) {
                jscs = " AND fymx.JSCS = " + (Integer) body.get("JSCS");
            } else if (("5".equals(body.get("JSLX") + ""))
                    || ("1".equals(body.get("JSLX") + ""))) {

//                String jsrq = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//                String endDay = "CURRENT_DATE()";
                if ("1".equals(body.get("JSLX") + "")) {
                    jscs = "1";
                }
            }
//                String JSRQ = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//                System.out.print("结算日期初始化：" + JSRQ);
//                String endDay = "sysdate";
//                if ("1".equals(body.get("JSLX") + "")
//                        && body.get("JSRQ") != null
//                        && !JSRQ.equals(body.get("JSRQ").toString())) {
//                    endDay = "str_to_date('" + body.get("JSRQ") + " 23:59:59','%y-%m-%d %H:%i:%S')";
//                    jscs += " and fymx.FYRQ<" + endDay;
//                    System.out.print("结束日期：" + endDay);
//                }select
//            }
            List<Map<String, Object>> MxzdhList = shybSi51Dao.getMxzdh(StrUtil.null2Str(body.get("zyh")),
                                                StrUtil.null2Str(body.get("jzdyh")), jscs,StrUtil.null2Str(body.get("jsrq")));
            if (!MxzdhList.isEmpty() && MxzdhList.size() > 0) {
                for (int i = 0; i < MxzdhList.size(); i++) {
                    if (i == MxzdhList.size() - 1) {
                        MXZDHS += "'" + StrUtil.null2Str(MxzdhList.get(i).get("mxzdh")) + "'";
                    } else {
                        MXZDHS += "'" + StrUtil.null2Str(MxzdhList.get(i).get("mxzdh")) + "',";
                    }
                }
                //上传的费用行数
                List<Map<String, Object>> sn01jlxhList = shybSi51Dao.getScmxList(StrUtil.null2Str(body.get("zyh")),StrUtil.null2Str(body.get("jzdyh")),
                        jscs,StrUtil.null2Str(body.get("jsrq")));
                List<Map<String, Object>> fymxjlxhList = shybSi51Dao.getFymxList(StrUtil.null2Str(body.get("zyh")), StrUtil.null2Str(body.get("jzdyh")),
                        jscs,StrUtil.null2Str(body.get("jsrq")));
                boolean flag = true;
                if (sn01jlxhList.size() == fymxjlxhList.size()) {
                    for (int i = 0; i < sn01jlxhList.size(); i++) {
                        if (!StrUtil.null2Str(sn01jlxhList.get(i).get("jlxh")).equals(StrUtil.null2Str(fymxjlxhList.get(i).get("jlxh")))) {
                            flag = false;
                            break;
                        }
                    }
                } else {

                    flag = false;
                }
                if (flag) {
                    List<Map<String, Object>> sn01mxzList = shybSn01Dao.getMxzdList(StrUtil.null2Str(body.get("zyh")),
                                                                                    StrUtil.null2Str(body.get("jzdyh")), jscs,StrUtil.null2Str(body.get("jsrq")));
                    List<DetailsBillcy> list = new ArrayList<DetailsBillcy>();
                    for (Map<String, Object> map : sn01mxzList) {
                        DetailsBillcy billDedails = new DetailsBillcy();
                        billDedails.setMxzdh(StrUtil.null2Str(map.get("mxzdh")));
                        billDedails.setTotalexpense(StrUtil.null2Str(map.get("totalexpense")));
                        billDedails.setYbjsfwfyze(StrUtil.null2Str(map.get("ybjsfwfyze")));
                        billDedails.setFybjsfwfyze(StrUtil.null2Str(map.get("fybjsfwfyze")));
                        list.add(billDedails);
                    }
                    si51.setMxzdhs(list);
                } else {
                    resultDTO.setCode(ServiceCode.CODE_ERROR);
                    resultDTO.setMsg("上传明细与费用明细不符");
                    return resultDTO;
                }
                List<Map<String, Object>> brzdList = shybSi51Dao.getJbmc(StrUtil.null2Str(body.get("zyh")));
                if (!brzdList.isEmpty() && brzdList.size() > 0) {
                    List<DiagnosisInfocy> list = new ArrayList<DiagnosisInfocy>();
                    Map<String, Object> tmpmap = brzdList.get(0);
                    DiagnosisInfocy diagnosisInfo = new DiagnosisInfocy();
                    diagnosisInfo.setZdmc(StrUtil.null2Str(tmpmap.get("JBMC")));
                    diagnosisInfo.setZdno(StrUtil.null2Str(tmpmap.get("ICD10")));
                    list.add(diagnosisInfo);
                    si51.setZdnos(list);
                } else {
                    resultDTO.setCode(ServiceCode.CODE_ERROR);
                    resultDTO.setMsg("无诊断");
                    return resultDTO;
                }
            } else {
                resultDTO.setCode(ServiceCode.CODE_ERROR);
                resultDTO.setMsg("无明细账单号请先上传明细" + MxzdhList);
                return resultDTO;
            }
        }catch (Exception e){
            resultDTO.setCode(ServiceCode.CODE_ERROR);
            resultDTO.setMsg(e.getMessage());
            return resultDTO;
        }
        String fssj = HisUtil.getDate("yyyyMMdd/HHmmss/", new Date());
        resultDTO = unifiedBusinessManager.si51(StrUtil.null2Str(body.get("jgdm")), StrUtil.null2Str(body.get("jgid")),
                Integer.parseInt(body.get("ygdm")+""), StrUtil.null2Str(body.get("ygxm")), TradingChannelEnum.OFFLINE,ip, si51);
        if(ServiceCode.CODE_OK == resultDTO.getCode()){
            Map<String, Object> resMap = unifiedBusinessManager.convertYBDTOData2Map(resultDTO.getData().toString());
            Map<String, Object> si51Map = new HashMap<String, Object>();
            MessageBody messageBody = JackJsonUtil.parse(resultDTO.getData().toString(), MessageBody.class);
            String jssj = HisUtil.getDate("yyyyMMdd/HHmmss/",messageBody.getJysj());
            resMap.put("JGID",StrUtil.null2Str(body.get("jgdm")));
            resMap.put("ZYSFFSSJ",fssj);
            resMap.put("ZYSFJSSJ",jssj);
            resMap.put("ZYJSKSRQ",StrUtil.null2Str(body.get("jsksrq")));
            resMap.put("ZYJSJSRQ",StrUtil.null2Str(body.get("jsjsrq")));
            resMap.put("JZDYH", StrUtil.null2Str(body.get("jzdyh")));
            resMap.put("BCJSZYTS", StrUtil.null2Str(body.get("zyts")));
            try {
                shybSi51Dao.insertYbsi51(resMap);
                shybSn01Dao.updateJssqxh(resMap.get("JSSQXH")+"",StrUtil.null2Str(body.get("zyh")), StrUtil.null2Str(body.get("jzdyh")),
                        jscs,StrUtil.null2Str(body.get("jsrq")));
                //res.put("MXZDHS",MXZDHS);
            }catch(Exception e){
                resultDTO.setCode(ServiceCode.CODE_ERROR);
                resultDTO.setMsg("si51本地保存结果失败!");
                return resultDTO;
            }
        }
        return resultDTO;
    }

    /**
     * si52
     *
     * @param body
     * @return
     */
    @Override
    @Locked("si52_#[body.jzdyh]")
    public SettleResultDTO si52(Map<String, Object> body) {
        body.put("jyqd", TradingChannelEnum.OFFLINE);
        String jscs= "0";
        SettleResultDTO resultDTO = new SettleResultDTO();
        Map js_map = shybSi51Dao.checkIsdeal(StrUtil.null2Str(body.get("jssqxh")));
        if(!"0".equals(js_map.get("num")+"")){
            resultDTO.setCode(1119);
            return resultDTO;
        }
        SI52 si52 = new SI52();
        try {
            Map<String, Object> parameters = new HashMap<String, Object>();
            si52.setCarddata(StrUtil.null2Str(body.get("carddata")));
            si52.setCardtype(StrUtil.null2Str(body.get("cardtype")));
            si52.setPersonspectag(StrUtil.null2Str(body.get("personspectag")));
            si52.setYllb(StrUtil.null2Str(body.get("yllb")));
            si52.setPersontype(StrUtil.null2Str(body.get("persontype")));
            si52.setGsrdh(StrUtil.null2Str(body.get("gsrdh")));
            si52.setCyjsbz(StrUtil.null2Str(body.get("cyjsbz")));
            si52.setJsksrq(StrUtil.null2Str(body.get("jsksrq")));
            si52.setJsjsrq(StrUtil.null2Str(body.get("jsjsrq")));
            si52.setZyts(StrUtil.null2Str(MedicineUtils.parseDouble(StrUtil.null2Str(body.get("zyts")))));
            si52.setZyh(StrUtil.null2Str(body.get("zyh")));
            si52.setDeptid(StrUtil.null2Str(body.get("deptid")));
            si52.setJzdyh(StrUtil.null2Str(body.get("jzdyh")));
            si52.setXsywlx(StrUtil.null2Str(body.get("xsywlx")));
            si52.setJssqxh(StrUtil.null2Str(body.get("jssqxh")));
           String MXZDHS = "";
            if ("10".equals(body.get("JSLX") + "")) {
                jscs = " AND fymx.JSCS = " + (Integer) body.get("JSCS");
            } else if ("1".equals(body.get("JSLX") + "")) {
                    jscs = "1";
            }
            List<Map<String, Object>> MxzdhList = shybSi51Dao.getMxzdh(StrUtil.null2Str(body.get("zyh")),
                    StrUtil.null2Str(body.get("jzdyh")), jscs,StrUtil.null2Str(body.get("jsjsrq")));
            if (!MxzdhList.isEmpty() && MxzdhList.size() > 0) {
                for (int i = 0; i < MxzdhList.size(); i++) {
                    if (i == MxzdhList.size() - 1) {
                        MXZDHS += "'" + StrUtil.null2Str(MxzdhList.get(i).get("mxzdh")) + "'";
                    } else {
                        MXZDHS += "'" + StrUtil.null2Str(MxzdhList.get(i).get("mxzdh")) + "',";
                    }
                }
            }
            if(!MXZDHS.equals("")){
                List<String> mxzdh = new ArrayList<>();
                for (Map<String, Object> mxzdhmap : MxzdhList) {
                    mxzdh.add(mxzdhmap.get("mxzdh")+"");
                }
                List<Map<String, Object>> sn01jlxhList = shybSn01Dao.getJlxh(mxzdh,body.get("jzdyh")+"");
                List<Map<String, Object>> fymxjlxhList = shybSn01Dao.getFymxjlxh(body.get("zyh")+"",body.get("jzdyh")+"",mxzdh);
                boolean flag = true;
                if (sn01jlxhList.size() == fymxjlxhList.size()){
                    for (int i = 0;i < sn01jlxhList.size();i++){
                        if (!StrUtil.null2Str(sn01jlxhList.get(i).get("jlxh")).equals(StrUtil.null2Str(fymxjlxhList.get(i).get("jlxh")))){
                            flag = false;
                            break;
                        }
                    }
                }else{
                    flag = false;
                }
                if (flag){
                    List<Map<String, Object>> sn01mxzList = shybSn01Dao.getMxzdsList(body.get("zyh")+"",body.get("jzdyh")+"",mxzdh);
                    List<DetailsBillcy> list = new ArrayList<DetailsBillcy>();
                    for (Map<String, Object> map:sn01mxzList){
                        DetailsBillcy billDedails = new DetailsBillcy();
                        billDedails.setMxzdh(StrUtil.null2Str(map.get("mxzdh")));
                        billDedails.setTotalexpense(StrUtil.null2Str(map.get("totalexpense")));
                        billDedails.setYbjsfwfyze(StrUtil.null2Str(map.get("ybjsfwfyze")));
                        billDedails.setFybjsfwfyze(StrUtil.null2Str(map.get("fybjsfwfyze")));
                        list.add(billDedails);
                    }
                    si52.setMxzdhs(list);
                }else{
                    resultDTO.setCode(ServiceCode.CODE_ERROR);
                    resultDTO.setMsg("上传明细与费用明细不符");
                    return resultDTO;
                }
                List<Map<String, Object>> brzdList = dicJbbmService.getBrzd(body.get("zyh")+"");
                if(!brzdList.isEmpty() && brzdList.size()>0){
                    List<DiagnosisInfocy> list = new ArrayList<DiagnosisInfocy>();
                    Map<String, Object> tmpmap = brzdList.get(0);
                    DiagnosisInfocy diagnosisInfo = new DiagnosisInfocy();
                    diagnosisInfo.setZdmc(StrUtil.null2Str(tmpmap.get("JBMC")));
                    diagnosisInfo.setZdno(StrUtil.null2Str(tmpmap.get("ICD10")));
                    list.add(diagnosisInfo);
                    si52.setZdnos(list);
                }else{
                    resultDTO.setCode(ServiceCode.CODE_ERROR);
                    resultDTO.setMsg("无诊断");
                    return resultDTO;
                }
                String fsjs = HisUtil.getDate("yyyyMMdd/HHmmss/", new Date());
                resultDTO = unifiedBusinessManager.si52(StrUtil.null2Str(body.get("jgdm")),StrUtil.null2Str(body.get("jgid")),
                        Integer.parseInt(StrUtil.null2Str(body.get("ygdm"))),StrUtil.null2Str(body.get("ygxm")),
                        TradingChannelEnum.OFFLINE, StrUtil.null2Str(body.get("ip")),si52);
                if(ServiceCode.CODE_OK == resultDTO.getCode()){
                    log.info("住院实结算成功1: " + body.get("zyh")+" " + body.get("jzdyh")+" " +mxzdh);
                   /* List<String> jlxh = new ArrayList<>();
                    List<Map<String,Object>> list_jlxh =  shybSi51Dao.queryJlxh(body.get("zyh")+"",body.get("jzdyh")+"",mxzdh);
                    for (Map<String, Object> jlxhmap : list_jlxh) {
                        jlxh.add(jlxhmap.get("jlxh")+"");
                    }
                    log.info("住院实结算成功2: " + body.get("zyh")+" " + body.get("jzdyh")+" " +jlxh);
                    shybSi51Dao.updateFymxbz(body.get("zyh")+"",body.get("jzdyh")+"",jlxh);*/
                    /*parameters.put("zyh", StrUtil.null2Str(body.get("zyh")));
                    parameters.put("jzdyh", StrUtil.null2Str(body.get("jzdyh")));
                    parameters.put("mxzdh",mxzdh);
                    shybSn01Dao.updateFymxbz(parameters);*/

                    Map<String, Object> resMap = unifiedBusinessManager.convertYBDTOData2Map(resultDTO.getData().toString());
                    Map<String, Object> si52Map = new HashMap<String, Object>();
                    MessageBody messageBody = JackJsonUtil.parse(resultDTO.getData().toString(), MessageBody.class);
                    String jssj = HisUtil.getDate("yyyyMMdd/HHmmss/",messageBody.getJysj());

                    si52Map.putAll(resMap);
                    si52Map.put("ZYSFFSSJ", fsjs);
                    si52Map.put("ZYSFJSSJ", jssj);
                    si52Map.put("ZYHM", StrUtil.null2Str(body.get("zyh")));
                    si52Map.put("JFJE", StrUtil.null2Str(resMap.get("JFJE")).trim());
                    si52Map.put("JGID", StrUtil.null2Str(body.get("jgdm")));//
                    si52Map.put("ZFPB", "0");
                    si52Map.put("JZDYH", StrUtil.null2Str(body.get("jzdyh")));
                    si52Map.put("JSCS", MedicineUtils.parseInt(StrUtil.null2Str(body.get("JSCS"))));
                    log.info("住院实结算成功2: ");
                    shybSi51Dao.insertYb52(si52Map);
                    parameters.clear();
                    parameters.put("zyh", StrUtil.null2Str(body.get("zyh")));
                    parameters.put("jzdyh", StrUtil.null2Str(body.get("jzdyh")));
                    log.info("住院实结算成功3: " +MedicineUtils.parseInt(StrUtil.null2Str(body.get("JSCS"))) + " "+
                            StrUtil.null2Str(body.get("zyh")) +" "+ StrUtil.null2Str(body.get("jzdyh")) +"" +mxzdh);
                    //shybSi51Dao.updateFymxbz(MedicineUtils.parseInt(StrUtil.null2Str(body.get("JSCS"))),StrUtil.null2Str(body.get("zyh")),StrUtil.null2Str(body.get("jzdyh")),mxzdh);
                    log.info("住院实结算成功4: ");
                }
            }else{
                resultDTO.setCode(ServiceCode.CODE_ERROR);
                resultDTO.setMsg("无明细账单号请先上传明细");
                return resultDTO;
            }
            }catch (Exception e) {
            resultDTO.setCode(ServiceCode.CODE_ERROR);
            resultDTO.setMsg("si52本地保存失败!");
            return resultDTO;
        }
        return resultDTO;
    }

    @Override
    public SettleResultDTO hospitalRefund(Map<String, Object> body) {
        body.put("jyqd", TradingChannelEnum.OFFLINE);
        SettleResultDTO resultDTO = new SettleResultDTO();
        if ("".equals(StrUtil.null2Str(body.get("jgdm")))) {
            resultDTO.setCode(ServiceCode.CODE_ERROR);
            resultDTO.setMsg("机构代码为空!");
            return resultDTO;
        }
        String  orgId = StrUtil.null2Str(body.get("jgdm"));
        String  jgid = StrUtil.null2Str(body.get("jgid"));
        int  ygdm = Integer.parseInt(StrUtil.null2Str(body.get("ygdm")));
        String  ygxm = StrUtil.null2Str(body.get("ygxm"));
        SK01 sk01 = new SK01();
        sk01.setCardtype(StrUtil.null2Str(body.get("cardtype")));
        sk01.setCarddata(StrUtil.null2Str(body.get("carddata")));
        sk01.setTranslsh(StrUtil.null2Str(body.get("lsh")));
        sk01.setTotalexpense(StrUtil.null2Str(body.get("totalexpense")));
        sk01.setXsywlx(StrUtil.null2Str(body.get("ywlx")));
        Map<String, Object> parameters = new HashMap<String, Object>();
        resultDTO = unifiedBusinessManager.sk01_mz(orgId, ygdm,ygxm,jgid,body.get("ip")+"",TradingChannelEnum.OFFLINE, sk01);
        if (resultDTO.getCode() == 200 && resultDTO.getData() != null) {
            shybSi51Dao.updateImfymxjsbz(sk01.getTranslsh());
            shybSi51Dao.updateYbsi51Zf(sk01.getTranslsh());
        }
        return resultDTO;
    }

    /**
     * 居保门诊转院查询
     * @param body
     * @return
     */
    public SettleResultDTO sjD1(Map<String, Object> body) {
        body.put("jyqd", TradingChannelEnum.OFFLINE);
        return unifiedBusinessManager.sjD1(body);
    }

    /**
     * 居保门诊转院
     * @param body
     * @return
     */
    public SettleResultDTO sjF1(Map<String, Object> body) {
        body.put("jyqd", TradingChannelEnum.OFFLINE);
        return unifiedBusinessManager.sjF1(body);
    }

    /**
     * 转出医院查询
     * @param body
     * @return
     */
    public SettleResultDTO SJH1(Map<String, Object> body) {
        body.put("jyqd", TradingChannelEnum.OFFLINE);
        //body.put("businessType", BusinessTypeEnum.REGISTER_OP);
        SettleResultDTO requestResult = unifiedBusinessManager.sjh1(body);
        return requestResult;

    }

    /**
     * 居保门诊转院撤销
     */
    public SettleResultDTO sjC1(Map<String, Object> body) {
        body.put("jyqd", TradingChannelEnum.OFFLINE);
        return unifiedBusinessManager.sjC1(body);
    }

    /**
     * 解码
     * @param body
     * @return
     */
    @Override
    public SettleResultDTO se01(Map<String, Object> body) {
        SettleResultDTO resultDTO = new SettleResultDTO();
        String ywlx = StrUtil.null2Str(body.get("ywlx"));
        String  orgId = StrUtil.null2Str(body.get("orgId"));
        String ip = StrUtil.null2Str(body.get("ip"));
        String termId = StrUtil.null2Str(body.get("termId"));
        int ygdm = Integer.parseInt(StrUtil.null2Str(body.get("ygdm")));
        String ygxm = StrUtil.null2Str(body.get("ygxm"));
        String jgid = StrUtil.null2Str(body.get("jgid"));
        if ("".equals(termId)) {
            termId = SettleConstant.DEFAULT_TERM_ID;
        }
        SE01 se01 = new SE01();
        String operatorId = body.get("operatorId")+"";
        String operatorName = body.get("operatorName")+"";
        se01.setOrgId(orgId);
        se01.setEcQrCode(StrUtil.null2Str(body.get("ecQrCode")));
        se01.setEcQrChannel(StrUtil.null2Str(body.get("ecQrChannel")));
        if("1".equals(ywlx)){
            se01.setBusinessType(BusinessTypeEnum.REGISTER.getValue());
        }else if("2".equals(ywlx)){
            se01.setBusinessType(BusinessTypeEnum.SETTLEMENT.getValue());
        }else if("3".equals(ywlx)){
            se01.setBusinessType(BusinessTypeEnum.CREATE_ARCHIVES.getValue());
        }else{
            se01.setBusinessType(BusinessTypeEnum.REGISTER_IM.getValue());
        }
        se01.setTermId(StrUtil.null2Str(termId));
        se01.setTermIp(StrUtil.null2Str(ip));
        se01.setOperatorId(operatorId);
        se01.setOperatorName(operatorName);
        se01.setOfficeId(StrUtil.null2Str(body.get("officeId")));
        se01.setOfficeName(StrUtil.null2Str(body.get("officeName")));
        resultDTO = unifiedBusinessManager.se01_mz(orgId,ip, TradingChannelEnum.OFFLINE, se01,ygdm,ygxm,jgid);
        return resultDTO;
    }



/*    *//**
     * SL01
     * @return
     *//*
    @Override
    public SettleResultDTO sl01(SL01s sl01,String orgid,String ip,int jgid) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("jyqd", TradingChannelEnum.OFFLINE);
        map.put("businessType", BusinessTypeEnum.REGISTER);
        map.put("orgid", orgid);
        map.put("ip", ip);
        map.put("jgid", jgid);
        Map<String,Object> map1 = new HashMap<>();
        map1.put("orgid","111");
        SettleResultDTO a = unifiedBusinessManager.SL01(map1);
        SettleResultDTO resultDTO
        System.out.println("11111");
        return unifiedBusinessManager.SL01(map);
    }*/

}