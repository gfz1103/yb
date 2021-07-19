
package com.buit.his.medinsuinterface.sh.dataitem.controller;

import com.buit.commons.BaseSpringController;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYbkbxgzAddReq;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYbkbxgzEditReq;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYbkbxgzQueryReq;
import com.buit.his.medinsuinterface.sh.dataitem.response.ShybYbkbxgzResp;
import com.buit.his.medinsuinterface.sh.dataitem.service.ShybYbkbxgzSer;
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

import javax.validation.Valid;

/**
 * 药品医保可报销条件规则表<br>
 * @author 老花生
 */
@Api(tags="药品医保可报销条件规则表")
@Controller
@RequestMapping("/shybybkbxgz")
public class ShybYbkbxgzCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(ShybYbkbxgzCtr.class);
    
    @Autowired
    private ShybYbkbxgzSer shybYbkbxgzSer;
    
    @RequestMapping("/queryPage")
    @ResponseBody
    @ApiOperationSupport(author = "老花生")
    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
    public ReturnEntity<PageInfo<ShybYbkbxgzResp>> queryPage(ShybYbkbxgzQueryReq req){
    	req.setHospitalId(getUser().getHospitalId());
        shybYbkbxgzSer.setSortColumns(req);
        PageInfo<ShybYbkbxgzResp> pageInfo = PageHelper.startPage(
        		req.getPageNum(), req.getPageSize()).doSelectPageInfo(
                    () -> shybYbkbxgzSer.findByEntity(req)
            );
        return ReturnEntityUtil.success(pageInfo);
    }
    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<ShybYbkbxgzResp>> getByEntity( ShybYbkbxgzReq shybybkbxgz){//@RequestBody 
//        return ReturnEntityUtil.success(shybYbkbxgzSer.findByEntity(shybybkbxgz));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<ShybYbkbxgzResp> getOneByEntity(ShybYbkbxgzReq shybybkbxgz){
//        List<ShybYbkbxgz> list=shybYbkbxgzSer.findByEntity(shybybkbxgz);
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
    public ReturnEntity add(ShybYbkbxgzAddReq shybYbkbxgz) {
        shybYbkbxgzSer.add(shybYbkbxgz, getUser());        
        return ReturnEntityUtil.success();            
    }
    
    /** 编辑 */
    @RequestMapping("/edit")
    @ResponseBody
    @ApiOperationSupport(author = "老花生")
    @ApiOperation(value="编辑" ,httpMethod="POST")
    public ReturnEntity edit(@Valid ShybYbkbxgzEditReq shybYbkbxgz)  {
        shybYbkbxgzSer.edit(shybYbkbxgz);        
        return ReturnEntityUtil.success();            
    }
    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<ShybYbkbxgzResp> delete(ShybYbkbxgzReq shybYbkbxgz) {
//        shybYbkbxgzSer.removeByEntity(shybYbkbxgz);        
//        return ReturnEntityUtil.success(shybYbkbxgz);            
//    }
    
    /** 导入文件 */
    @RequestMapping("/impExcel")
    @ResponseBody
    @ApiOperationSupport(author = "老花生")
    @ApiOperation(value="导入文件" ,httpMethod="POST")
    public ReturnEntity impExcel(@RequestParam("file") MultipartFile file) {
    	shybYbkbxgzSer.impExcel(file, getUser());        
        return ReturnEntityUtil.success();            
    }
    
}

