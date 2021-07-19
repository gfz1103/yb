package com.buit.his.medinsuinterface.sh.dataitem.dao;

import com.buit.commons.EntityDao;
import com.buit.his.medinsuinterface.sh.dataitem.model.ShybYpcggz;
import org.apache.ibatis.annotations.Mapper;


/**
 * 药品采购规则<br>
 * @author 老花生
 */
@Mapper
public interface ShybYpcggzDao extends EntityDao<ShybYpcggz,Integer> {
	/**
	 * 根据统编代码更新对象
	 * @param obj	根据统编代码更新对象
	 * @return
	 */
	Long updateByTbdm(ShybYpcggz obj);

	/**
	 * 检查标识唯一
	 * @param ypjggz
	 * @return
	 */
    Long getBytBdm(ShybYpcggz ypjggz);
}
