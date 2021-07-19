package com.buit.his.medinsuinterface.sh.dataitem.dao;

import com.buit.commons.EntityDao;
import com.buit.his.medinsuinterface.sh.dataitem.model.ShybYszcxx;
import org.apache.ibatis.annotations.Mapper;

/**
 * 上海医保医师注册信息<br>
 * @author 老花生
 */
@Mapper
public interface ShybYszcxxDao extends EntityDao<ShybYszcxx,Integer> {
    /**
     * 通过医保编码更新医师信息
     * @param data  更新对象
     * @return
     */
    Integer updateByYbCode(ShybYszcxx data);

    /**
     * 更新启用状态
     * @param req   更新对象
     */
    void updateLockFlag(ShybYszcxx req);

    /**
     * 检查标识唯一
     * @param data
     * @return
     */
    Integer checkCode(ShybYszcxx data);
}
