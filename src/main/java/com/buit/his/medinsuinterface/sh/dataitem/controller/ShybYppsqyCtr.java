
package com.buit.his.medinsuinterface.sh.dataitem.controller;

import com.buit.commons.BaseSpringController;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYppsqyAddReq;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYppsqyEditReq;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYppsqyQueryReq;
import com.buit.his.medinsuinterface.sh.dataitem.response.ShybYppsqyResp;
import com.buit.his.medinsuinterface.sh.dataitem.service.ShybYppsqySer;
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
 * 药品配送企业表<br>
 * @author 老花生
 */
@Api(tags="药品配送企业表")
@Controller
@RequestMapping("/shybyppsqy")
public class ShybYppsqyCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(ShybYppsqyCtr.class);
    
    @Autowired
    private ShybYppsqySer shybYppsqySer;
    
    @RequestMapping("/queryPage")
    @ResponseBody
    @ApiOperationSupport(author = "老花生")
    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
    public ReturnEntity<PageInfo<ShybYppsqyResp>> queryPage(ShybYppsqyQueryReq req){
    	req.setHospitalId(getUser().getHospitalId());
        shybYppsqySer.setSortColumns(req);
        PageInfo<ShybYppsqyResp> pageInfo = PageHelper.startPage(
        		req.getPageNum(), req.getPageSize()).doSelectPageInfo(
                    () -> shybYppsqySer.findByEntity(req)
            );
        return ReturnEntityUtil.success(pageInfo);
    }
    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<ShybYppsqyResp>> getByEntity( ShybYppsqyReq shybyppsqy){//@RequestBody 
//        return ReturnEntityUtil.success(shybYppsqySer.findByEntity(shybyppsqy));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<ShybYppsqyResp> getOneByEntity(ShybYppsqyReq shybyppsqy){
//        List<ShybYppsqy> list=shybYppsqySer.findByEntity(shybyppsqy);
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
    public ReturnEntity add(ShybYppsqyAddReq shybYppsqy) {
        shybYppsqySer.add(shybYppsqy, getUser());        
        return ReturnEntityUtil.success();            
    }
    
    /** 编辑 */
    @RequestMapping("/edit")
    @ResponseBody
    @ApiOperationSupport(author = "老花生")
    @ApiOperation(value="编辑" ,httpMethod="POST")
    public ReturnEntity edit(@Valid ShybYppsqyEditReq shybYppsqy)  {
        shybYppsqySer.edit(shybYppsqy);        
        return ReturnEntityUtil.success();            
    }
    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<ShybYppsqyResp> delete(ShybYppsqyReq shybYppsqy) {
//        shybYppsqySer.removeByEntity(shybYppsqy);        
//        return ReturnEntityUtil.success(shybYppsqy);            
//    }
    
    /** 导入文件 */
    @RequestMapping("/impExcel")
    @ResponseBody
    @ApiOperationSupport(author = "老花生")
    @ApiOperation(value="导入文件" ,httpMethod="POST")
    public ReturnEntity impExcel(@RequestParam("file") MultipartFile file) {
    	shybYppsqySer.impExcel(file, getUser());        
        return ReturnEntityUtil.success();            
    }
    
}

