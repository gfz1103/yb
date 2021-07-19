package com.buit.his.shyb.source.dao;

import com.buit.commons.EntityDao;
import com.buit.his.shyb.source.entity.business.SJD1;
import com.buit.his.shyb.source.entity.business.SJD1Res;
import com.buit.his.shyb.source.model.YbZhbz;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
/**
 * 上海居保门诊转院<br>
 * @author Beijunhua
 */
@Mapper
public interface ShybSjd1Dao {
    /**
     * 新增医保转院数据保存
     * @param map
     */
    void insertShybSldj(Map<String,Object> map);

    /**
     * 转诊登记
     * @param zmdh
     */
    void deleteShybSldj(String zmdh);

    /**
     * 转诊详情
     * @param sjd1
     * @return
     */
    public List<SJD1Res> queryMzzyInfo(SJD1 sjd1);

    /**
     * 删除转诊医院
     */
    void deleteZcyydm();

    /**
     * 新增转出医院
     */
    void insertZcyydm(Map<String,Object> m);

    void updateShybslrydj(String brkh,String zcyybm,String zryybm);
}
