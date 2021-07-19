package com.buit.his.gpo.dao;

import com.buit.commons.EntityDao;

import com.buit.his.gpo.controller.response.JhdwResponse;
import com.buit.his.gpo.controller.response.YqResponse;
import com.buit.his.gpo.dto.YqJhdwDto;
import com.buit.his.gpo.service.result.JhdwYqIdMcDto;
import org.apache.ibatis.annotations.Mapper;
import com.buit.his.gpo.model.GpoYqJhdw;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * GPO药企与医院进货单位对照表<br>
 * @author sunjx
 */
@Mapper
public interface GpoYqJhdwDao extends EntityDao<GpoYqJhdw,Integer>{

    List<JhdwResponse> jhdwList(@Param("kw") String kw, @Param("dzzt") Integer dzzt);

    List<YqResponse> yqList(String kw);

    Integer existJhdw(Integer jhdwid);

    Integer existYq(Integer yqid);

    List<JhdwYqIdMcDto> queryWdzJhdw(Integer jgid);

    List<JhdwYqIdMcDto> queryWdzYq(Integer jgid);

    String jhdwbm(String yqbm);

    List<YqJhdwDto> queryJhdwxx(@Param("list") List<String> yqbmList);
}
