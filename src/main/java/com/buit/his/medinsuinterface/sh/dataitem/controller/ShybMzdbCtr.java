
package com.buit.his.medinsuinterface.sh.dataitem.controller;

import com.buit.commons.BaseSpringController;
import com.buit.commons.PageQuery;
import com.buit.commons.SysUser;
import com.buit.his.medinsuinterface.sh.dataitem.dao.ShybMzdbDbksDao;
import com.buit.his.medinsuinterface.sh.dataitem.request.SaveDbRegisterReq;
import com.buit.his.medinsuinterface.sh.dataitem.service.ShybMzdbDbksSer;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.buit.utill.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

import javax.validation.Valid;

/**
 * 门诊_大病<br>
 * 
 * @author Beijunhua
 */
@Api(tags = "门诊大病")
@Controller
@RequestMapping("/shybMzdb")
public class ShybMzdbCtr extends BaseSpringController {

	static final Logger logger = LoggerFactory.getLogger(ShybMzdbCtr.class);

	@Autowired
	private ShybMzdbDbksDao shybMzdbDbksDao;
	@Autowired
	private ShybMzdbDbksSer shybMzdbDbksSer;

	/**
	 * 门诊大病分页条件查询
	 * 
	 * @param dbregister
	 * @param page
	 * @return
	 */
	@RequestMapping("/queryPage")
	@ResponseBody
	@ApiOperationSupport(author = "beijunhua")
	@ApiOperation(value = "按条件分页查询", httpMethod = "POST", notes = "本地大病登记分页条件查询")
	public ReturnEntity<PageInfo<SaveDbRegisterReq>> queryPage(SaveDbRegisterReq dbregister, PageQuery page) {
        dbregister.setCzgh(StrUtil.null2Str(getUser().getUserId()));
		PageInfo<SaveDbRegisterReq> pageInfo = PageHelper.startPage(page.getPageNum(), page.getPageSize())
				.doSelectPageInfo(() -> shybMzdbDbksDao.queryDbdjInfo(dbregister));
		return ReturnEntityUtil.success(pageInfo);
	}

	/**
	 * 本地大病登记新增
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	@ApiOperationSupport(author = "beijunhua")
	@ApiOperation(value = "大病登记新增", httpMethod = "POST", notes = "大病登记新增")
	public ReturnEntity<?> add(@RequestBody @Valid SaveDbRegisterReq req) {
		shybMzdbDbksSer.saveDbdjInfo(req);
		return ReturnEntityUtil.success();
	}



}
