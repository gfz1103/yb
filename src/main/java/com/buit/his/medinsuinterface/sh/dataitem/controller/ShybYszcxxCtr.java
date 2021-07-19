
package com.buit.his.medinsuinterface.sh.dataitem.controller;

import com.alibaba.excel.EasyExcel;
import com.buit.commons.BaseException;
import com.buit.commons.BaseSpringController;
import com.buit.his.medinsuinterface.sh.dataitem.excelmodel.ShybYszcxxExcelModel;
import com.buit.his.medinsuinterface.sh.dataitem.listener.ShybYszcxxExcelListener;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYszcxxAddReq;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYszcxxEditReq;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYszcxxQueryReq;
import com.buit.his.medinsuinterface.sh.dataitem.response.ShybYszcxxResp;
import com.buit.his.medinsuinterface.sh.dataitem.service.ShybYszcxxSer;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

/**
 * 上海医保医师注册信息<br>
 * @author 老花生
 */
@Api(tags="上海医保医师注册信息")
@Controller
@RequestMapping("/shybyszcxx")
public class ShybYszcxxCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(ShybYszcxxCtr.class);
    
    @Autowired
    private ShybYszcxxSer shybYszcxxSer;
    
    @RequestMapping("/queryPage")
    @ResponseBody
    @ApiOperationSupport(author = "老花生")
    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
    public ReturnEntity<PageInfo<ShybYszcxxResp>> queryPage(ShybYszcxxQueryReq req){
    	req.setHospitalId(getUser().getHospitalId());
        shybYszcxxSer.setSortColumns(req);
        PageInfo<ShybYszcxxResp> pageInfo = PageHelper.startPage(
        		req.getPageNum(), req.getPageSize()).doSelectPageInfo(
                    () -> shybYszcxxSer.findByEntity(req)
            );
        return ReturnEntityUtil.success(pageInfo);
    }
    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<ShybYszcxxResp>> getByEntity( ShybYszcxxReq shybyszcxx){//@RequestBody 
//        return ReturnEntityUtil.success(shybYszcxxSer.findByEntity(shybyszcxx));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<ShybYszcxxResp> getOneByEntity(ShybYszcxxReq shybyszcxx){
//        List<ShybYszcxx> list=shybYszcxxSer.findByEntity(shybyszcxx);
//        if(list.size()>0){
//           return ReturnEntityUtil.success(list.get(0));    
//        }
//        return ReturnEntityUtil.success();
//    }
    
    /** 新增 */
    @RequestMapping("/add")
    @ResponseBody
    @ApiOperationSupport(author = "老花生")
    @ApiOperation(value="新增" ,httpMethod="POST")
    public ReturnEntity add(ShybYszcxxAddReq req) {
        shybYszcxxSer.add(req, getUser());        
        return ReturnEntityUtil.success();            
    }
    
    /** 编辑 */
    @RequestMapping("/edit")
    @ResponseBody
    @ApiOperationSupport(author = "老花生")
    @ApiOperation(value="编辑" ,httpMethod="POST")
    public ReturnEntity edit(@Valid ShybYszcxxEditReq req)  {
        shybYszcxxSer.edit(req); 
        return ReturnEntityUtil.success();            
    }
    
    /** 导入文件 */
    @RequestMapping("/impExcel")
    @ResponseBody
    @ApiOperationSupport(author = "老花生")
    @ApiOperation(value="导入文件" ,httpMethod="POST")
    public ReturnEntity impExcel(@RequestParam("file") MultipartFile file) {
    	try {
			EasyExcel.read(file.getInputStream(), ShybYszcxxExcelModel.class, new ShybYszcxxExcelListener(shybYszcxxSer, getUser())).sheet().doRead();
		} catch (IOException e) {
			throw BaseException.create("ERROR_SHYB_0001");
		}
        return ReturnEntityUtil.success();            
    }
    
    
    /** 用户锁定 */
    @RequestMapping("/lock")
    @ResponseBody
    @ApiOperationSupport(author = "老花生")
    @ApiOperation(value="用户锁定" ,httpMethod="POST")
    public ReturnEntity lock(@ApiParam(name = "ybdoctordataId",value = "医保医生数据ID") @RequestParam Integer ybdoctordataId) {
        shybYszcxxSer.lock(ybdoctordataId);        
        return ReturnEntityUtil.success();            
    }
    
    /** 用户锁定 */
    @RequestMapping("/unlock")
    @ResponseBody
    @ApiOperationSupport(author = "老花生")
    @ApiOperation(value="用户解锁" ,httpMethod="POST")
    public ReturnEntity unlock(@ApiParam(name = "ybdoctordataId",value = "医保医生数据ID") @RequestParam Integer ybdoctordataId) {
        shybYszcxxSer.unlock(ybdoctordataId);        
        return ReturnEntityUtil.success();            
    }
    
}

