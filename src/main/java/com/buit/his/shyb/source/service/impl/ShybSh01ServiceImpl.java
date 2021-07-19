package com.buit.his.shyb.source.service.impl;

import com.buit.his.shyb.source.dao.ShybSh01Dao;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 上海医保挂号试算<br>
 * @author Beijunhua
 */
@DubboService
public class ShybSh01ServiceImpl implements ShybSh01Service{
    @Autowired
    private ShybSh01Dao shybSh01Dao;
    /**
     * 挂号预结
     * @param preRegisMap
     */
    @Override
    public void insertShybsh01(Map<String, Object> preRegisMap){
        shybSh01Dao.insertShybsh01(preRegisMap);
    }

    /**
     * 挂号实结
     * @param regisMap
     */
    @Override
    public void insertShybsh02(Map<String, Object> regisMap){
        shybSh01Dao.insertShybsh02(regisMap);
    }

    /**
     * 挂号医保费用
     * @param parameters
     * @return
     */
    @Override
    public Map<String,Object> getGhybFee(Map<String, Object> parameters){
        Map<String,Object> map = shybSh01Dao.getGhybFee(parameters);
        return map;
    }

    /**
     * 收费医保费用
     * @param parameters
     * @return
     */
    @Override
    public Map<String,Object> getSfybFee(Map<String, Object> parameters){
        Map<String,Object> map = shybSh01Dao.getSfybFee(parameters);
        return map;
    }

    /**
     * 住院医保费用
     * @param parameters
     * @return
     */
    @Override
    public Map<String,Object> getZyybFee(Map<String, Object> parameters){
        Map<String,Object> map = shybSh01Dao.getZyybFee(parameters);
        return map;
    }

    /**
     * 退费费用
     * @param parameters
     */
    @Override
    public void insertGhSk01(Map<String, Object> parameters){
         shybSh01Dao.insertGhSk01(parameters);
    }

    /**
     * 获取就诊单元号
     * @param currDate
     * @param brid
     * @param jgid
     * @param ghks
     * @return
     */
    @Override
    public List<Map<String,Object>> getJzdyh(String currDate, Integer brid, Integer jgid, String ghks){
        List<Map<String,Object>> list = shybSh01Dao.getJzdyh(currDate,brid,jgid,ghks);
        return list;
    }

    @Override
    public Map<String,Object> getSeqMsgid(String seq_name){
        Map<String,Object> map = shybSh01Dao.getSeqMsgid(seq_name);
        return map;
    }

    /**
     * 更新收费mzxh
     * @param
     */
    @Override
    public void updateSi12bftf(String jssqxh,String mzxh){
        shybSh01Dao.updateSi12bftf(jssqxh,mzxh);
    }

}
