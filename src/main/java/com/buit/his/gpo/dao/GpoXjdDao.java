package com.buit.his.gpo.dao;

import com.buit.commons.EntityDao;
import com.buit.his.gpo.model.GpoXjd;
import com.buit.his.gpo.service.result.GpoXjdDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * GPO询价单<br>
 * @author sunjx
 */
@Mapper
public interface GpoXjdDao extends EntityDao<GpoXjd,Integer>{

    List<GpoXjdDto> page(@Param("jgid") Integer jgid,
                         @Param("xjrqks") Timestamp xjrqks,
                         @Param("xjrqjz") Timestamp xjrqjz,
                         @Param("xjsptbbm") String xjsptbbm,
                         @Param("cbzt") List<String> cbzt,
                         @Param("qrzt") String qrzt,
                         @Param("zfzt") String zfzt);

    GpoXjdDto detail(Integer xh);

}
