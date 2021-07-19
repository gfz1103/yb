
package com.buit.his.medinsuinterface.sh.dataitem.controller;

import com.alibaba.excel.EasyExcel;
import com.buit.commons.BaseException;
import com.buit.commons.BaseSpringController;
import com.buit.his.medinsuinterface.sh.dataitem.excelmodel.ShybYlqcxxExcelModel;
import com.buit.his.medinsuinterface.sh.dataitem.listener.ShybYlqcxxExcelListener;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYlqcxxAddReq;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYlqcxxEditReq;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYlqcxxQueryReq;
import com.buit.his.medinsuinterface.sh.dataitem.response.ShybYlqcxxResp;
import com.buit.his.medinsuinterface.sh.dataitem.service.ShybYlqcxxSer;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 上海医保医疗器材信息<br>
 * @author 老花生
 */
@Api(tags="医疗器材信息")
@Controller
@RequestMapping("/shybylqcxx")
public class ShybYlqcxxCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(ShybYlqcxxCtr.class);
    
    @Autowired
    private ShybYlqcxxSer shybYlqcxxSer;
    
    @RequestMapping("/queryPage")
    @ResponseBody
    @ApiOperationSupport(author = "老花生")
    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
    public ReturnEntity<PageInfo<ShybYlqcxxResp>> queryPage(ShybYlqcxxQueryReq req){
    	req.setHospitalId(getUser().getHospitalId());
        shybYlqcxxSer.setSortColumns(req);
        PageInfo<ShybYlqcxxResp> pageInfo = PageHelper.startPage(
        		req.getPageNum(), req.getPageSize()).doSelectPageInfo(
                    () -> shybYlqcxxSer.findByEntity(req)
            );
        return ReturnEntityUtil.success(pageInfo);
    }
    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<ShybYlqcxxResp>> getByEntity( ShybYlqcxxReq shybylqcxx){//@RequestBody 
//        return ReturnEntityUtil.success(shybYlqcxxSer.findByEntity(shybylqcxx));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<ShybYlqcxxResp> getOneByEntity(ShybYlqcxxReq shybylqcxx){
//        List<ShybYlqcxx> list=shybYlqcxxSer.findByEntity(shybylqcxx);
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
    public ReturnEntity add(ShybYlqcxxAddReq req) {
        shybYlqcxxSer.add(req, getUser());        
        return ReturnEntityUtil.success();            
    }
    
    /** 编辑 */
    @RequestMapping("/edit")
    @ResponseBody
    @ApiOperationSupport(author = "老花生")
    @ApiOperation(value="编辑" ,httpMethod="POST")
    public ReturnEntity edit(ShybYlqcxxEditReq req)  {
        shybYlqcxxSer.edit(req);        
        return ReturnEntityUtil.success();            
    }
    
    /** 导入文件 */
    @RequestMapping("/impExcel")
    @ResponseBody
    @ApiOperationSupport(author = "老花生")
    @ApiOperation(value="导入文件" ,httpMethod="POST")
    public ReturnEntity impExcel(@RequestParam("file") MultipartFile file) {
    	try {
			EasyExcel.read(file.getInputStream(), ShybYlqcxxExcelModel.class, new ShybYlqcxxExcelListener(shybYlqcxxSer, getUser())).sheet().doRead();
		} catch (IOException e) {
			throw BaseException.create("ERROR_SHYB_0001");
		}
        return ReturnEntityUtil.success();            
    }
    
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<ShybYlqcxxResp> delete(ShybYlqcxxReq shybYlqcxx) {
//        shybYlqcxxSer.removeByEntity(shybYlqcxx);        
//        return ReturnEntityUtil.success(shybYlqcxx);            
//    }
    
}

