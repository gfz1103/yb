package com.buit.his.shyb.source.dao;

import com.buit.commons.EntityDao;
import com.buit.his.shyb.source.entity.business.SJ11;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 上海医保登记业务<br>
 * @author Beijunhua
 */
@Mapper
public interface ShybSl01Dao extends EntityDao<SJ11, Integer> {
    /**
     *登记
     *
     * @param resMap
     */
    void insertYbrj11(Map<String, Object> resMap);

    /**
     * 获取就诊单元号
     * @param zyh
     * @return
     */
    public List<Map<String,Object>> getJzdyhByZyh(String zyh);

}
