package com.buit.his.gpo.dao;

import com.buit.his.gpo.controller.response.SpResp;
import com.buit.his.gpo.controller.response.YqResp;
import com.buit.his.gpo.model.GpoDd;
import com.buit.his.gpo.service.result.YpcdjgDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author sunjx
 * @Date 2020-11-10 17:21
 * @Description
 **/
@Mapper
public interface GpoDao {

    List<SpResp> ypList(@Param("jgid") String jgid, @Param("kw") String kw, @Param("rq") String rq, @Param("yksb") Integer yksb);

    SpResp yp(@Param("tbdm") String tbdm, @Param("rq") String rq);

    List<YqResp> yqList(@Param("jgid") String jgid, @Param("kw") String kw);

    YpcdjgDto selectYpCdJgByYbbm(@Param("jgid") String jgid, @Param("ybbm") String ybbm);

    Integer selectYksb(@Param("yklb") Integer yklb );

    List<YpcdjgDto> queryByTbdm(@Param("jgid") String jgid,@Param("list") List<String> tbdmList);

    List<SpResp> queryByTbbmAndYxrq(@Param("list") List<String> sptbdm, @Param("yxrq") String yxrq);
}
