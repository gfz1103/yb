
package com.buit.his.medinsuinterface.sh.dataitem.controller;

import com.buit.commons.BaseSpringController;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYpcggzAddReq;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYpcggzEditReq;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYpcggzQueryReq;
import com.buit.his.medinsuinterface.sh.dataitem.response.ShybYpcggzResp;
import com.buit.his.medinsuinterface.sh.dataitem.service.ShybYpcggzSer;
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

/**
 * 药品采购规则<br>
 * @author 老花生
 */
@Api(tags="药品采购规则")
@Controller
@RequestMapping("/shybypcggz")
public class ShybYpcggzCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(ShybYpcggzCtr.class);
    
    @Autowired
    private ShybYpcggzSer shybYpcggzSer;
    
    @RequestMapping("/queryPage")
    @ResponseBody
    @ApiOperationSupport(author = "老花生")
    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
    public ReturnEntity<PageInfo<ShybYpcggzResp>> queryPage(ShybYpcggzQueryReq req){
    	req.setHospitalId(getUser().getHospitalId());
        shybYpcggzSer.setSortColumns(req);
        PageInfo<ShybYpcggzResp> pageInfo = PageHelper.startPage(
        		req.getPageNum(), req.getPageSize()).doSelectPageInfo(
                    () -> shybYpcggzSer.findByEntity(req)
            );
        return ReturnEntityUtil.success(pageInfo);
    }
    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<ShybYpcggzResp>> getByEntity( ShybYpcggzReq shybypcggz){//@RequestBody 
//        return ReturnEntityUtil.success(shybYpcggzSer.findByEntity(shybypcggz));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<ShybYpcggzResp> getOneByEntity(ShybYpcggzReq shybypcggz){
//        List<ShybYpcggz> list=shybYpcggzSer.findByEntity(shybypcggz);
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
    public ReturnEntity add(ShybYpcggzAddReq shybYpcggz) {
        shybYpcggzSer.add(shybYpcggz, getUser());        
        return ReturnEntityUtil.success();            
    }
    
    /** 编辑 */
    @RequestMapping("/edit")
    @ResponseBody
    @ApiOperationSupport(author = "老花生")
    @ApiOperation(value="编辑" ,httpMethod="POST")
    public ReturnEntity edit(@Valid ShybYpcggzEditReq shybYpcggz)  {
        shybYpcggzSer.edit(shybYpcggz);        
        return ReturnEntityUtil.success();            
    }
    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<ShybYpcggzResp> delete(ShybYpcggzReq shybYpcggz) {
//        shybYpcggzSer.removeByEntity(shybYpcggz);        
//        return ReturnEntityUtil.success(shybYpcggz);            
//    }
    
    /** 导入文件 */
    @RequestMapping("/impExcel")
    @ResponseBody
    @ApiOperationSupport(author = "老花生")
    @ApiOperation(value="导入文件" ,httpMethod="POST")
    public ReturnEntity impExcel(@RequestParam("file") MultipartFile file
                                 ) {
    	shybYpcggzSer.impExcel(file, getUser());        
        return ReturnEntityUtil.success();            
    }
    
}

