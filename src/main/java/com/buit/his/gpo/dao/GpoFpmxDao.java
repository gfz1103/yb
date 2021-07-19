package com.buit.his.gpo.dao;

import com.buit.commons.EntityDao;

import com.buit.his.gpo.service.result.Fpmx;
import org.apache.ibatis.annotations.Mapper;
import com.buit.his.gpo.model.GpoFpmx;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * GPO发票明细<br>
 * @author sunjx
 */
@Mapper
public interface GpoFpmxDao extends EntityDao<GpoFpmx,Integer>{

    List<Fpmx> list(@Param("jgid") Integer jgid, @Param("fpxh") Integer fpxh);
}
