package com.buit.his.gpo.dao;

import com.buit.commons.EntityDao;

import org.apache.ibatis.annotations.Mapper;
import com.buit.his.gpo.model.GpoDd;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * GPO订单<br>
 * @author sunjx
 */
@Mapper
public interface GpoDdDao extends EntityDao<GpoDd,Integer>{

    List<GpoDd> page(
            @Param("yklx") Integer yklx,
            @Param("jgid") Integer jgid,
            @Param("rqks") Timestamp rqks,
            @Param("rqjz") Timestamp rqjz,
            @Param("yqmb") String yqmb,
            @Param("cbzt") List<String> cbzt,
            @Param("qrzt") String qrzt,
            @Param("zfzt") String zfzt);

    String selectYqmc( @Param("yqbm")String yqbm);

    void batchInsert(@Param("list") List<GpoDd> gpoDdList);
}
