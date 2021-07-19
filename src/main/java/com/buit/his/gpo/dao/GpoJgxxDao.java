package com.buit.his.gpo.dao;

import com.buit.commons.EntityDao;

import com.buit.his.gpo.model.GpoZd;
import org.apache.ibatis.annotations.Mapper;
import com.buit.his.gpo.model.GpoJgxx;

import java.util.List;

/**
 * gpo 机构信息<br>
 * @author sunjx
 */
@Mapper
public interface GpoJgxxDao extends EntityDao<GpoJgxx,Integer>{

    GpoJgxx selectByJgid(Integer jgid);

}
