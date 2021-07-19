package com.buit.his.medinsuinterface.sh.dataitem.dao;

import com.buit.commons.EntityDao;
import com.buit.his.medinsuinterface.sh.dataitem.model.ShybYlqcxx;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 上海医保医疗器材信息<br>
 * @author 老花生
 */
@Mapper
public interface ShybYlqcxxDao extends EntityDao<ShybYlqcxx,Integer> {
	/**
	 * 批量插入
	 * @param list
	 */
	void insertBatch(List<ShybYlqcxx> list);

	/**
	 * 更新对象
	 * @param obj
	 * @return
	 */
	Integer updateObj(ShybYlqcxx obj);
}
