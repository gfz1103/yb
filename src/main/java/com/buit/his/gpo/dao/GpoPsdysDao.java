package com.buit.his.gpo.dao;

import com.buit.commons.EntityDao;

import com.buit.his.gpo.model.GpoPsdys;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <br>
 * @author DESKTOP-P9STS99
 */
@Mapper
public interface GpoPsdysDao extends EntityDao<GpoPsdys,Integer>{

    void batchInsert(@Param("list") List<GpoPsdys> gpoPsdysList);
}
