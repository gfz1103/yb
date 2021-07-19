package com.buit.his.medinsuinterface.sh.dataitem.service;

import java.util.List;

import com.buit.system.service.SysDictConfigSer;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buit.commons.BaseException;
import com.buit.commons.BaseManagerImp;
import com.buit.constans.TableName;
import com.buit.his.enums.ShybData01TypeEnum;
import com.buit.his.enums.StopFlagEnum;
import com.buit.his.medinsuinterface.sh.dataitem.dao.ShybData02Dao;
import com.buit.his.medinsuinterface.sh.dataitem.model.ShybData02;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybData02AddReq;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybData02QueryReq;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybData02Req;
import com.buit.commons.SysUser;
import com.buit.utill.ChineseCharacterUtil;
import com.buit.utill.DateUtil;
import com.buit.utill.RedisFactory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 医保数据内容表<br>
 * @author 老花生
 */
@Service
public class ShybData02Ser extends BaseManagerImp<ShybData02, Integer> {

	static final Logger logger = LoggerFactory.getLogger(ShybData02Ser.class);

	@Autowired
	private ShybData02Dao shybData02Dao;
	@Autowired
	private RedisFactory redisFactory;
	@DubboReference
    private SysDictConfigSer sysDictConfigSer;

	@Override
	public ShybData02Dao getEntityMapper() {
		return shybData02Dao;
	}

	public void unifiedAdd(ShybData02AddReq req, SysUser user) {
		checkCode(null,req.getYbdataId(),req.getDataCode());
		ShybData02 shybData0102 = new ShybData02();
		BeanUtils.copyProperties(req, shybData0102);

		shybData0102.setYbdatavalueId(redisFactory.getTableKey(TableName.DB_NAME, TableName.SHYB_DATA02));
		shybData0102.setGmtCreate(DateUtil.getCurrentTimestamp());
		shybData0102.setHospitalId(user.getHospitalId());
		shybData0102.setDataVersion(1);
		shybData0102.setStopFlag("0");
		shybData0102.setSerialNumber(shybData0102.getYbdatavalueId());
		ChineseCharacterUtil.setPyAndWb(shybData0102,shybData0102.getDataName());
		shybData02Dao.insert(shybData0102);
		sysDictConfigSer.upDateVersion("shyb_data02");
	}

	public void unifiedEdit(ShybData02Req req) {
		checkCode(req.getYbdatavalueId(),req.getYbdataId(),req.getDataCode());
		ShybData02 ret = shybData02Dao.getById(req.getYbdatavalueId());
		if (ret == null) {
			throw BaseException.create("ERROR_DIC_0040");
		}

		req.setGmtModify(DateUtil.getCurrentTimestamp());
		ChineseCharacterUtil.setPyAndWb(req,req.getDataName());
		shybData02Dao.update(req);
		sysDictConfigSer.upDateVersion("shyb_data02");
	}

	public String editStopFlag(Integer ybdatavalueId) {
		ShybData02 ret = shybData02Dao.getById(ybdatavalueId);
		if (ret == null) {
			throw BaseException.create("ERROR_DIC_0040");
		}
		if (ret.getStopFlag() != null && StopFlagEnum.code_1.getCode().equals(ret.getStopFlag())) {
			ret.setStopFlag(StopFlagEnum.code_0.getCode());
		} else {
			ret.setStopFlag(StopFlagEnum.code_1.getCode());
		}

		shybData02Dao.editStopFlag(ret);
		sysDictConfigSer.upDateVersion("shyb_data02");
		return ret.getStopFlag();
	}


	private void checkCode(Integer ybdatavalueId, Integer ybdataId,String dataCode){
		ShybData02 value = new ShybData02();
		value.setYbdatavalueId(ybdatavalueId);
		value.setYbdataId(ybdataId);
		value.setDataCode(dataCode);
		Long count = shybData02Dao.checkCode(value);
		if(count > 0){
			throw BaseException.create("ERROR_SHYB_0014");
		}
	}

	public PageInfo<ShybData02> unifiedQueryPage(ShybData02QueryReq req, Integer hospitalId) {
		
		ShybData02 shybData0102 = new ShybData02();
		BeanUtils.copyProperties(req, shybData0102);
		shybData0102.setHospitalId(hospitalId);
		setSortColumns(shybData0102);
		PageInfo<ShybData02> pageInfo = PageHelper.startPage(shybData0102.getPageNum(), shybData0102.getPageSize())
				.doSelectPageInfo(() -> shybData02Dao.findByEntity(shybData0102));
		
		if(req.getYbdataId().intValue() == ShybData01TypeEnum.code_2.getCode().intValue()) {
			List<ShybData02> list = pageInfo.getList();
			
			for(ShybData02 value : list) {
				ShybData02 query = new ShybData02();
				query.setParentDatavalueId(value.getYbdatavalueId());
				
				value.setChildNum(shybData02Dao.findByEntityCount(query));
			}
		}
		
		return pageInfo;
	}
}
