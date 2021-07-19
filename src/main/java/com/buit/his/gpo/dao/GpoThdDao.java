package com.buit.his.gpo.dao;

import com.buit.commons.EntityDao;

import org.apache.ibatis.annotations.Mapper;
import com.buit.his.gpo.model.GpoThd;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * GPO退货单<br>
 * @author sunjx
 */
@Mapper
public interface GpoThdDao extends EntityDao<GpoThd,Integer>{

    List<GpoThd> list(@Param("jgid") Integer jgid,
                      @Param("rqks") Timestamp rqks,
                      @Param("rqjz") Timestamp rqjz,
                      @Param("yqbm") String yqbm,
                      @Param("cbzt") List<String> cbzt,
                      @Param("qrzt") String qrzt,
                      @Param("zfzt") String zfzt);

    String selectPsdmc(@Param("psdbm") String psdbm, @Param("jgid")Integer jgid);
}
