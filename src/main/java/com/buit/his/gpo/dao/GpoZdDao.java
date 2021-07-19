package com.buit.his.gpo.dao;

import com.buit.commons.EntityDao;

import org.apache.ibatis.annotations.Mapper;
import com.buit.his.gpo.model.GpoZd;

import java.util.List;

/**
 * gpo 字典<br>
 * @author sunjx
 */
@Mapper
public interface GpoZdDao extends EntityDao<GpoZd,Integer>{

    List<GpoZd> zd(String type);
}
