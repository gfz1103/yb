package com.buit.his.medinsuinterface.sh.dataitem.dao;

import com.buit.commons.EntityDao;
import com.buit.his.medinsuinterface.sh.dataitem.model.ShybData02;
import com.buit.his.shyb.source.model.ShybData02Resp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 医保数据内容表<br>
 * @author 老花生
 */
@Mapper

public interface ShybData02Dao extends EntityDao<ShybData02,Integer>{
    /**
     * 更新启用状态
     * @param val   更新对象
     */
    void editStopFlag(ShybData02 val);

    /**
     * 检查code唯一
     * @param value
     * @return
     */
    Long checkCode(ShybData02 value);

    /**
     * 查询医保科室
     * @return
     */
    List<ShybData02Resp> queryList();
    /**
     * 查询线上医保科室
     * @return
     */
    List<ShybData02Resp> queryXsList();
}
