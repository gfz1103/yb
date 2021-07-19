package com.buit.his.medinsuinterface.sh.dataitem.dao;

import com.buit.commons.EntityDao;
import com.buit.his.medinsuinterface.sh.dataitem.model.ShybYbkbxgz;
import org.apache.ibatis.annotations.Mapper;

/**
 * 药品医保可报销条件规则表<br>
 * @author 老花生
 */
@Mapper
public interface ShybYbkbxgzDao extends EntityDao<ShybYbkbxgz,Integer> {

	/**
	 * 更新统编代码
	 * @param obj	更新对象
	 * @return
	 */
	Long updateByTbdm(ShybYbkbxgz obj);

	/**
	 * 检查编码唯一
	 * @param ypjggz
	 * @return
	 */
    Long getBytBdm(ShybYbkbxgz ypjggz);
}
