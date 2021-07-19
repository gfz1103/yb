package com.buit.his.shyb.source.service;

import com.buit.his.shyb.source.entity.SettleResultDTO;
import com.buit.his.shyb.source.util.ServiceCode;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * 医保执行文件
 */
public class YBOperateService {


    /**
     * 门诊收费预结算接口
     * SN01和SI11
     * @throws
     */
//    public Map doPrepareSettle_MZ(Map<String, Object> req) {
//        Map<String,Object> res = new HashMap<String,Object>();
//        Map<String, Object> body = (Map<String, Object>) req.get("body");
//        SettleResultDTO sn01ResultDTO = iOfflineSettleService.batchUploadFeeDetails_MZ(body, res);
//        if(sn01ResultDTO.getCode() != ServiceCode.CODE_OK){
//            res.put("code", ServiceCode.CODE_ERROR);
//            res.put("body", sn01ResultDTO);
//            return res;
//        }
//        body.put("resultSn01", sn01ResultDTO.getData());
//       /* SettleResultDTO si11ResultDTO = iOfflineSettleService.requestCharge_MZ(body, res);
//        if(si11ResultDTO.getCode() != ServiceCode.CODE_OK){
//            res.put("code", ServiceCode.CODE_ERROR);
//            res.put("body", si11ResultDTO);
//            return res;
//        }*/
//        setResData(res, si11ResultDTO);
//    }
}
