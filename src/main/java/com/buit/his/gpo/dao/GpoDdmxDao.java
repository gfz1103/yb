package com.buit.his.gpo.dao;

import com.buit.commons.EntityDao;

import com.buit.his.gpo.model.MoneySum;
import org.apache.ibatis.annotations.Mapper;
import com.buit.his.gpo.model.GpoDdmx;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * GPO订单明细<br>
 * @author sunjx
 */
@Mapper
public interface GpoDdmxDao extends EntityDao<GpoDdmx,Integer>{

    void deleteByDdxh(@Param(value="ddxh")Integer ddxh);

    List<GpoDdmx> details(@Param(value="ddxh") Integer ddxh);

    List<MoneySum> JeSum(@Param(value="xh")Integer xh);

    void deleteByDdxhList(@Param("list") List<Integer> deleteDdxhList);

    void batchInsert(@Param("list") List<GpoDdmx> gpoDdmxList);

    List<GpoDdmx> queryByddmxbh(@Param("list") List<String> ddmxbhList);

}
