package com.buit.his.gpo.dao;

import com.buit.commons.EntityDao;

import com.buit.his.gpo.controller.response.YpkcshQueryResponse;
import org.apache.ibatis.annotations.Mapper;
import com.buit.his.gpo.model.GpoYpkccb;

import java.util.List;

/**
 * GPO药品当前库存量数据传报<br>
 * @author sunjx
 */
@Mapper
public interface GpoYpkccbDao extends EntityDao<GpoYpkccb,Integer>{
    List<YpkcshQueryResponse> kccx(Integer jgid);
}
