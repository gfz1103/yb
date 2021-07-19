package com.buit.his.gpo.dao;

import com.buit.commons.EntityDao;
import com.buit.his.gpo.model.GpoBjd;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * GPO报价单<br>
 * @author sunjx
 */
@Mapper
public interface GpoBjdDao extends EntityDao<GpoBjd,Integer>{
}
