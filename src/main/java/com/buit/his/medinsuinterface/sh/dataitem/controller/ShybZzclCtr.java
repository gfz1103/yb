
package com.buit.his.medinsuinterface.sh.dataitem.controller;

import com.buit.commons.BaseSpringController;
import com.buit.his.medinsuinterface.sh.dataitem.service.SJD1Ser;
import com.buit.his.shyb.source.dao.ShybSjd1Dao;
import com.buit.his.shyb.source.entity.business.SJC1;
import com.buit.his.shyb.source.entity.business.SJD1Res;
import com.buit.his.shyb.source.entity.business.SJF1;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 门诊_居保转院<br>
 * 
 * @author Beijunhua
 */
@Api(tags = "居保门诊转院")
@Controller
@RequestMapping("/shybMzzy")
public class ShybZzclCtr extends BaseSpringController {

	static final Logger logger = LoggerFactory.getLogger(ShybZzclCtr.class);

	@Autowired
	private ShybSjd1Dao shybSjd1Dao;
	@Autowired
	private SJD1Ser sjd1Ser;

	/**
	 * 门诊转院分页条件查询
	 * @param carddata
	 * @return
	 */
	@RequestMapping("/queryPage")
	@ResponseBody
	@ApiOperationSupport(author = "beijunhua")
	@ApiOperation(value = "刷卡查询", httpMethod = "POST", notes = "居保门诊转院查询")
	public ReturnEntity<SJD1Res> queryPage(String carddata) {
		return  sjd1Ser.queryMzzyInfo(carddata,getIpAddress(),getUser());
	}
	@RequestMapping("/queryPagebyRead")
	@ResponseBody
	@ApiOperationSupport(author = "beijunhua")
	@ApiOperation(value = "读卡查询", httpMethod = "POST", notes = "居保门诊转院查询")
	public ReturnEntity<SJD1Res> queryPagebyRead() {
		return  sjd1Ser.queryMzzyInfo("",getIpAddress(),getUser());
	}


	/**
	 *  门诊转院
	 * @param
	 * @return
	 */
	@RequestMapping("/sjf1")
	@ResponseBody
	@ApiOperationSupport(author = "beijunhua")
	@ApiOperation(value="门诊转院" ,httpMethod="POST")
	public ReturnEntity sjf1( SJF1 sjf1)  {
		sjd1Ser.doMzzy(sjf1 ,getIpAddress(), getUser());
		return ReturnEntityUtil.success();
	}

	/**
	 *  门诊转入医院更新
	 * @param
	 * @return
	 */
	@RequestMapping("/sjh1")
	@ResponseBody
	@ApiOperationSupport(author = "beijunhua")
	@ApiOperation(value="门诊转入医院更新" ,httpMethod="POST")
	public ReturnEntity sjh1()  {
		sjd1Ser.doUp(getIpAddress(), getUser());
		return ReturnEntityUtil.success();
	}


	/**
	 *  门诊撤销转院
	 * @param
	 * @return
	 */
	@RequestMapping("/sjc1")
	@ResponseBody
	@ApiOperationSupport(author = "beijunhua")
	@ApiOperation(value="门诊转院撤销" ,httpMethod="POST")
	public ReturnEntity sjc1(SJC1 sjc1req)  {
		sjd1Ser.doCancel(sjc1req,getIpAddress(), getUser());
		return ReturnEntityUtil.success();
	}

}
