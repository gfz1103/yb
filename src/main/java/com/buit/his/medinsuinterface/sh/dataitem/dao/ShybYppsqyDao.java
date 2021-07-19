package com.buit.his.medinsuinterface.sh.dataitem.dao;

import com.buit.commons.EntityDao;
import com.buit.his.medinsuinterface.sh.dataitem.model.ShybYppsqy;
import org.apache.ibatis.annotations.Mapper;

/**
 * 药品配送企业表<br>
 * @author 老花生
 */
@Mapper
public interface ShybYppsqyDao extends EntityDao<ShybYppsqy,Integer> {
	/**
	 * 根据统编代码更新对象
	 * @param obj	更新对象
	 * @return
	 */
	Long updateByTbdm(ShybYppsqy obj);

	/**
	 * 检查编码唯一
	 * @param ypjggz
	 * @return
	 */
    Long getBytBdm(ShybYppsqy ypjggz);
}
