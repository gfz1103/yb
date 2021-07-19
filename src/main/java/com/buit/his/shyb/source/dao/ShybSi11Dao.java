package com.buit.his.shyb.source.dao;

import com.buit.commons.EntityDao;
import com.buit.his.shyb.source.entity.business.SI11;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
/**
 * 上海医保收费试算<br>
 * @author Beijunhua
 */
@Mapper
public interface ShybSi11Dao extends EntityDao<SI11, Integer> {
    /**
     * 收费预结
     * @param preRegisMap
     */
    void insertYbsi11(Map<String, Object> preRegisMap);

    /**
     * 收费实结
     * @param si12Map
     */
    void insertYbsi12(Map<String, Object> si12Map);

    /**
     * 获取诊断
     * @param jzlsh
     * @return
     */
    List<Map<String, Object>> getZdBysf(String jzlsh);

}
