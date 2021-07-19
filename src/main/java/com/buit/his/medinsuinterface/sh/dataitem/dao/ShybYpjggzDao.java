package com.buit.his.medinsuinterface.sh.dataitem.dao;

import com.buit.commons.EntityDao;
import com.buit.his.medinsuinterface.sh.dataitem.model.ShybYpjggz;
import org.apache.ibatis.annotations.Mapper;

/**
 * 药品价格规则表<br>
 * @author 老花生
 */
@Mapper
public interface ShybYpjggzDao extends EntityDao<ShybYpjggz,Integer> {
	/**
	 * 根据统编代码查询对象
	 * @param ypjggz	统编代码
	 * @return
	 */
	Long getBytBdm(ShybYpjggz ypjggz) ;

	/**
	 * 根据统编代码更新对象
	 * @param obj	根据统编代码更新对象
	 * @return
	 */
	Long updateByTbdm(ShybYpjggz obj);
}
