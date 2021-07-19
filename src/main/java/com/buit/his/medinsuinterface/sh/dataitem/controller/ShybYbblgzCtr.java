
package com.buit.his.medinsuinterface.sh.dataitem.controller;

import com.buit.commons.BaseSpringController;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYbblgzAddReq;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYbblgzEditReq;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYbblgzQueryReq;
import com.buit.his.medinsuinterface.sh.dataitem.response.ShybYbblgzResp;
import com.buit.his.medinsuinterface.sh.dataitem.service.ShybYbblgzSer;
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
 * 医保报销比例规则表<br>
 * @author 老花生
 */
@Api(tags="医保报销比例规则表")
@Controller
@RequestMapping("/shybybblgz")
public class ShybYbblgzCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(ShybYbblgzCtr.class);
    
    @Autowired
    private ShybYbblgzSer shybYbblgzSer;
    
    @RequestMapping("/queryPage")
    @ResponseBody
    @ApiOperationSupport(author = "老花生")
    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
    public ReturnEntity<PageInfo<ShybYbblgzResp>> queryPage(ShybYbblgzQueryReq req){
    	req.setHospitalId(getUser().getHospitalId());
        shybYbblgzSer.setSortColumns(req);
        PageInfo<ShybYbblgzResp> pageInfo = PageHelper.startPage(
        		req.getPageNum(), req.getPageSize()).doSelectPageInfo(
                    () -> shybYbblgzSer.findByEntity(req)
            );
        return ReturnEntityUtil.success(pageInfo);
    }
    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<ShybYbblgzResp>> getByEntity( ShybYbblgzReq shybybblgz){//@RequestBody 
//        return ReturnEntityUtil.success(shybYbblgzSer.findByEntity(shybybblgz));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<ShybYbblgzResp> getOneByEntity(ShybYbblgzReq shybybblgz){
//        List<ShybYbblgz> list=shybYbblgzSer.findByEntity(shybybblgz);
//        if(list.size()>0){
//           return ReturnEntityUtil.success(list.get(0));    
//        }
//        return ReturnEntityUtil.success();
//    }
    
    /** 新增 */
    @RequestMapping("/add")
    @ResponseBody
    @ApiOperation(value="新增" ,httpMethod="POST")
    public ReturnEntity add(ShybYbblgzAddReq shybYbblgz) {
        shybYbblgzSer.add(shybYbblgz, getUser());        
        return ReturnEntityUtil.success();            
    }
    
    /** 编辑 */
    @RequestMapping("/edit")
    @ResponseBody
    @ApiOperation(value="编辑" ,httpMethod="POST")
    public ReturnEntity edit(@Valid ShybYbblgzEditReq shybYbblgz)  {
        shybYbblgzSer.edit(shybYbblgz);        
        return ReturnEntityUtil.success();            
    }
    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<ShybYbblgzResp> delete(ShybYbblgzReq shybYbblgz) {
//        shybYbblgzSer.removeByEntity(shybYbblgz);        
//        return ReturnEntityUtil.success(shybYbblgz);            
//    }
    
    /** 导入文件 */
    @RequestMapping("/impExcel")
    @ResponseBody
    @ApiOperationSupport(author = "老花生")
    @ApiOperation(value="导入文件" ,httpMethod="POST")
    public ReturnEntity impExcel(@RequestParam("file") MultipartFile file) {
    	shybYbblgzSer.impExcel(file, getUser());        
        return ReturnEntityUtil.success();            
    }
    
}

