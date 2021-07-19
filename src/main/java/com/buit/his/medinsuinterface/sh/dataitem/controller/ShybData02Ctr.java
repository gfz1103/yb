
package com.buit.his.medinsuinterface.sh.dataitem.controller;

import com.buit.commons.BaseSpringController;
import com.buit.his.medinsuinterface.sh.dataitem.model.ShybData02;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybData02AddReq;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybData02QueryReq;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybData02Req;
import com.buit.his.medinsuinterface.sh.dataitem.service.ShybData02Ser;
import com.buit.his.shyb.source.model.ShybData02Resp;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * 医保数据内容表<br>
 * @author 老花生
 */
@Api(tags="医保数据代码")
@Controller
@RequestMapping("/shybdata02")
public class ShybData02Ctr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(ShybData02Ctr.class);
    
    @Autowired
    private ShybData02Ser shybData0102Ser;
    
    @RequestMapping("/unifiedQueryPage")
    @ResponseBody
    @ApiOperationSupport(author = "老花生")
    @ApiOperation(value="统一按条件分页查询" ,httpMethod="POST")
    public ReturnEntity<PageInfo<ShybData02>> unifiedQueryPage(@Valid ShybData02QueryReq req){
        return ReturnEntityUtil.success(shybData0102Ser.unifiedQueryPage(req, getUser().getHospitalId()));
    }
    

    
    /** 新增 */
    @RequestMapping("/unifiedAdd")
    @ResponseBody
    @ApiOperationSupport(author = "老花生")
    @ApiOperation(value="统一新增" ,httpMethod="POST")
    public ReturnEntity unifiedAdd(@Valid ShybData02AddReq req) {
        shybData0102Ser.unifiedAdd(req, getUser());        
        return ReturnEntityUtil.success();            
    }
    
    /** 编辑 */
    @RequestMapping("/unifiedEdit")
    @ResponseBody
    @ApiOperationSupport(author = "老花生")
    @ApiOperation(value="统一编辑(整个对象json提交)" ,httpMethod="POST")
    public ReturnEntity<ShybData02Resp> unifiedEdit(@Valid @RequestBody ShybData02Req shybData0102)  {
        shybData0102Ser.unifiedEdit(shybData0102);        
        return ReturnEntityUtil.success();            
    }
    
    /** 停用 */
    @RequestMapping("/editStopFlag")
    @ResponseBody
    @ApiOperationSupport(author = "老花生")
    @ApiOperation(value="编辑停用状态" ,httpMethod="POST")
    public ReturnEntity<String> editStopFlag(@ApiParam(name = "ybdatavalueId",value = "医保数据ID") @RequestParam Integer ybdatavalueId)  {
        return ReturnEntityUtil.success(shybData0102Ser.editStopFlag(ybdatavalueId));            
    }
    
    
}

