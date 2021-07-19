package com.buit.his.shyb.source.dao;

import com.buit.commons.EntityDao;
import com.buit.his.shyb.source.model.YbDbdj;
import com.buit.his.shyb.source.model.YbZhbz;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
/**
 * 上海医保账户标志
 * @author beijunhua
 */
@Mapper
public interface ShybZhbzDao extends EntityDao<YbZhbz, Integer> {
    /**
     * 根据账户标志获取病人性质
     * @param zhbz
     * @return
     */
    public Map<String,Object> getZhbzBrxz(String zhbz);

    /**
     * 获取大病项目代码
     * @param brkh
     * @param date
     * @return
     */
    public List<YbDbdj> getShybDbxmdm(String brkh,String date);
}
