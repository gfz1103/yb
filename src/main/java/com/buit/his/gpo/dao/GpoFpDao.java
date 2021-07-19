package com.buit.his.gpo.dao;

import com.buit.commons.EntityDao;

import com.buit.his.gpo.service.result.Fpsj;
import org.apache.ibatis.annotations.Mapper;
import com.buit.his.gpo.model.GpoFp;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * GPO发票<br>
 * @author sunjx
 */
@Mapper
public interface GpoFpDao extends EntityDao<GpoFp,Integer>{

    List<Fpsj> list(@Param("jgid") Integer jgid,
                    @Param("yksb") Integer yksb,
                    @Param("yqbm") String yqbm,
                    @Param("ksrq") String ksrq,
                    @Param("jsrq") String jsrq,
                    @Param("dlcgbz") String dlcgbz,
                    @Param("yszt") String yszt,
                    @Param("dzzt") String dzzt,
                    @Param("zfuzt") String zfuzt,
                    @Param("rkzt") String rkzt);

    GpoFp selectByFph(String fph);

    int dz(@Param("xh") String xh, @Param("dzsj") Timestamp dzsj, @Param("dzrdm") Integer dzrdm);

    int zf(@Param("fph")String fph, @Param("zfsj")Timestamp zfsj, @Param("zfrdm")String zfrdm);

    List<GpoFp> selectListInXh(@Param("fpxhs")List<Integer> fpxhs);

    Integer selectYksb(@Param("glmxbh")String glmxbh);
}
