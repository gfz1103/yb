package com.buit.his.gpo.dao;

import com.buit.his.gpo.model.GpoMryq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Author yueyu
 * @Date 2021/6/23 13:19
 */
@Mapper
public interface GpoMryqDao {

    void save(@Param("list") List<GpoMryq> gpoMryqList);

}
