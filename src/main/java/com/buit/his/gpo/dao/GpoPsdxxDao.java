package com.buit.his.gpo.dao;

import com.buit.commons.EntityDao;

import org.apache.ibatis.annotations.Mapper;
import com.buit.his.gpo.model.GpoPsdxx;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * GPO配送点信息<br>
 * @author sunjx
 */
@Mapper
public interface GpoPsdxxDao extends EntityDao<GpoPsdxx,Integer>{

    Integer existBm(@Param("jgid") Integer jgid, @Param("xh") Integer xh, @Param("psdbm") String psdbm);

    Integer existMc(@Param("jgid") Integer jgid, @Param("xh") Integer xh, @Param("psdmc") String psdmc);

    List<GpoPsdxx> page();

    Integer existUpload(Integer jgid);

    GpoPsdxx select(Integer jgid);
}
