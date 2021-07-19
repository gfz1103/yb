package com.buit.his.medinsuinterface.sh.dataitem.dao;

import com.buit.commons.EntityDao;
import com.buit.his.medinsuinterface.sh.dataitem.model.ShybYbblgz;
import org.apache.ibatis.annotations.Mapper;


/**
 * 医保报销比例规则表<br>
 * @author 老花生
 */
@Mapper
public interface ShybYbblgzDao extends EntityDao<ShybYbblgz,Integer> {
	/**
	 * 根据统编代码更新对象
	 * @param obj	更新对象
	 * @return
	 */
	Long updateByTbdm(ShybYbblgz obj);

	/**
	 * 检查编码唯一
	 * @param ypjggz
	 * @return
	 */
    Long getBytBdm(ShybYbblgz ypjggz);
}
