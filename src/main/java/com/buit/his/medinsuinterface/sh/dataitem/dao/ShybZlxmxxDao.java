package com.buit.his.medinsuinterface.sh.dataitem.dao;

import com.buit.commons.EntityDao;
import com.buit.his.medinsuinterface.sh.dataitem.model.ShybZlxmxx;
import org.apache.ibatis.annotations.Mapper;

/**
 * 上海医保诊疗项目信息<br>
 * @author 老花生
 */
@Mapper
public interface ShybZlxmxxDao extends EntityDao<ShybZlxmxx,Integer> {
	/**
	 * 通过医保编码更新对象
	 * @param data	更新对象
	 * @return
	 */
	Integer updateByYbCode(ShybZlxmxx data);
}
