package com.buit.his.medinsuinterface.sh.dataitem.dao;

import com.buit.commons.EntityDao;
import com.buit.commons.SysUser;
import com.buit.his.medinsuinterface.sh.dataitem.model.YbMzdbDbks;
import com.buit.his.medinsuinterface.sh.dataitem.request.SaveDbRegisterReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <br>
 * 
 * @author WY
 */
@Mapper
public interface ShybMzdbDbksDao extends EntityDao<YbMzdbDbks, String> {

	/**
	 * 获取科室代码
	 * 
	 * @return
	 */
	public List<Integer> getKsdm();

	List<SaveDbRegisterReq> queryDbdjInfo(SaveDbRegisterReq dbregister);

	void saveDbdjInfo(SaveDbRegisterReq req);
	void updateDbdjzt(String cardid,String dbxm);

}
