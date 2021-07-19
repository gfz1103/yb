package com.buit.his.shyb.source.model;

import com.buit.commons.BaseManagerImp;
import com.buit.constans.TableName;
import com.buit.his.shyb.source.dao.ShybSh01Dao;
import com.buit.his.shyb.source.entity.SettleConstant;
import com.buit.his.shyb.source.entity.business.SH01;
import com.buit.his.shyb.source.service.impl.MedicalInsuranceService;
import com.buit.utill.RedisFactory;
import com.buit.utill.StrUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@DubboService
public class MedicalInsuranceModel implements MedicalInsuranceService {

    @Autowired
    private ShybSh01Dao ybSh01Dao;
    @Autowired
    private RedisFactory redisFactory;

//    @Override
//    public ShybSh01Dao getEntityMapper(){ return ybSh01Dao; }
    /**
     * 报文序号
     * @param orgId
     * @param size
     * @return
     */
    @Override
    public String getMsgId(String orgId, int size) {
        String seqNumber  = redisFactory.getTableKey(TableName.DB_NAME, TableName.SHYB_SEQUENCE)+"";
        //Map<String,Object> remsgids = ybSh01Dao.getSeqMsgids();
        //Map<String,Object> remsgid = ybSh01Dao.getSeqMsgid("seq_yb_msgid");

        //Map<String,Object> remsgid = null;
        //String seqNumber = remsgid.get("seq")+"";
        StringBuilder msgId = new StringBuilder(30);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String currentDateString = simpleDateFormat.format(new Date());
        if(size==6){
            simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            currentDateString = simpleDateFormat.format(new Date());
        }
        if(seqNumber.length() >= size){
            seqNumber = seqNumber.substring(0,size);
        }else{
            seqNumber = StringUtils.leftPad(seqNumber, size, "0");
        }
        msgId.append(orgId).append(currentDateString).append(seqNumber);
        return msgId.toString();
    }


    /**
     * 查询退号,门诊交易费用金额等信息
     * @param
     */
    @Override
    public Map<String, Object> queryMedicalInsuranceTranInfo(Map<String,Object> body){
        String ywlx = StrUtil.null2Str(body.get("ywlx"));
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("sbxh", body.get("sbxh"));
        Map<String,Object> refund = new HashMap<String,Object>();
        if(SettleConstant.GHTFLX.equals(ywlx)){
            refund = ybSh01Dao.getGhybFee(parameters);
        }else if(SettleConstant.MZTFLX.equals(ywlx)){
            refund = ybSh01Dao.getSfybFee(parameters);
        }else if(SettleConstant.ZYTFLX.equals(ywlx)){
            refund = ybSh01Dao.getZyybFee(parameters);
        }
        return refund ;
    }

    /**
     * 挂号退号,门诊等退款保存sk01
     * @param
     * @param options
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSk01RequireNew(Map<String,Object> resMap, Map<String, Object> options,
                                   Map<String, Object> tranMap)  {
        Map<String, Object> parameters = new HashMap<String, Object>();
        if(SettleConstant.GHTFLX.equals(tranMap.get("MZBS")+"")){
            parameters.put("TFLX", SettleConstant.GHTFLX_SAVE);
        }else if(SettleConstant.MZTFLX.equals(tranMap.get("MZBS")+"")){
            parameters.put("TFLX", SettleConstant.MZTFLX_SAVE);
        }else if(SettleConstant.ZYTFLX.equals(tranMap.get("MZBS")+"")){
            parameters.put("TFLX", SettleConstant.ZYTFLX_SAVE);
        }
        parameters.put("JGID", options.get("orgid"));
        parameters.put("GLXH", options.get("sbxh"));
        parameters.put("FPHM", tranMap.get("FPHM"));
        parameters.put("YBJSFWFYZE", tranMap.get("YBJSFWFYZE"));//医保结算范围费用总额
        parameters.put("TOTALEXPENSE", tranMap.get("TOTALEXPENSE"));//交易费用总额
        parameters.put("FYBJSFWFYZE", tranMap.get("FYBJSFWFYZE"));//非医保结算范围个人自费
        String jysj = resMap.get("jysj").toString();
        String jysjStr = jysj.replace(" ", "/").replace("-", "").replace(":", "")+"/";
        parameters.put("JSSJ", jysjStr);
        parameters.put("ZT", 1);
        Map<String, Object> xxnrMap = (Map<String, Object>) resMap.get("xxnr");
        Set<String> keys = xxnrMap.keySet();
        for(String key :keys){
            if(StringUtils.isNotBlank(StrUtil.null2Str(xxnrMap.get(key)))){
                parameters.put(key.toUpperCase(),xxnrMap.get(key));
            }
        }
        ybSh01Dao.insertGhSk01(parameters);
        if("110".equals(parameters.get("TFLX"))){
            ybSh01Dao.updateSh02(StrUtil.null2Str(parameters.get("GLXH")));
        }else if("120".equals(parameters.get("TFLX"))){
            ybSh01Dao.updateSi12(StrUtil.null2Str(parameters.get("GLXH")));
        }
    }
}
