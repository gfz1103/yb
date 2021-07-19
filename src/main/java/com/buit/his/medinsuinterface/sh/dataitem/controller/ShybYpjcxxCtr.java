
package com.buit.his.medinsuinterface.sh.dataitem.controller;

import cn.hutool.core.util.StrUtil;
import com.buit.commons.BaseException;
import com.buit.commons.BaseSpringController;
import com.buit.commons.PageQuery;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYpjcxxAddReq;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYpjcxxEditReq;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYpjcxxQueryReq;
import com.buit.his.medinsuinterface.sh.dataitem.response.ShybYpjcxxResp;
import com.buit.his.medinsuinterface.sh.dataitem.service.ShybYpjcxxSer;

import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.google.common.base.Splitter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 药品品规基础信息表<br>
 * @author 老花生
 */
@Api(tags="药品品规基础信息表")
@Controller
@RequestMapping("/shybypjcxx")
public class ShybYpjcxxCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(ShybYpjcxxCtr.class);
    
    @Autowired
    private ShybYpjcxxSer shybYpjcxxSer;



    @RequestMapping("/queryPage")
    @ResponseBody
    @ApiOperationSupport(author = "老花生")
    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
    public ReturnEntity<PageInfo<ShybYpjcxxResp>> queryPage(ShybYpjcxxQueryReq req){
    	req.setHospitalId(getUser().getHospitalId());
        shybYpjcxxSer.setSortColumns(req);
        PageInfo<ShybYpjcxxResp> pageInfo = PageHelper.startPage(
        		req.getPageNum(), req.getPageSize()).doSelectPageInfo(
                    () -> shybYpjcxxSer.findByEntity(req)
            );
        return ReturnEntityUtil.success(pageInfo);
    }
    
    @RequestMapping("/ybsrf")
    @ResponseBody
    @ApiOperationSupport(author = "神算子")
    @ApiOperation(value="药品医输入法" ,httpMethod="POST")
    public ReturnEntity<PageInfo<ShybYpjcxxResp>> queryPage(String ypbm,PageQuery page){
    	PageInfo<ShybYpjcxxResp> pageInfo = PageHelper.startPage(
    			page.getPageNum(), page.getPageSize()).doSelectPageInfo(
    					() -> shybYpjcxxSer.getEntityMapper().shurufa(ypbm)
    					);
    	return ReturnEntityUtil.success(pageInfo);
    }
    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<ShybYpjcxxResp>> getByEntity( ShybYpjcxxReq shybypjcxx){//@RequestBody 
//        return ReturnEntityUtil.success(shybYpjcxxSer.findByEntity(shybypjcxx));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<ShybYpjcxxResp> getOneByEntity(ShybYpjcxxReq shybypjcxx){
//        List<ShybYpjcxx> list=shybYpjcxxSer.findByEntity(shybypjcxx);
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
    public ReturnEntity add(ShybYpjcxxAddReq shybYpjcxx) {
        shybYpjcxxSer.add(shybYpjcxx, getUser());        
        return ReturnEntityUtil.success();            
    }
    
    /** 编辑 */
    @RequestMapping("/edit")
    @ResponseBody
    @ApiOperationSupport(author = "老花生")
    @ApiOperation(value="编辑" ,httpMethod="POST")
    public ReturnEntity edit(ShybYpjcxxEditReq shybYpjcxx)  {
        shybYpjcxxSer.edit(shybYpjcxx);        
        return ReturnEntityUtil.success();            
    }
    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<ShybYpjcxxResp> delete(ShybYpjcxxReq shybYpjcxx) {
//        shybYpjcxxSer.removeByEntity(shybYpjcxx);        
//        return ReturnEntityUtil.success(shybYpjcxx);            
//    }


        /** 导入文件 */
    @RequestMapping("/impExcel")
    @ResponseBody
    @ApiOperationSupport(author = "老花生")
    @ApiOperation(value="导入文件" ,httpMethod="POST")
    public ReturnEntity impExcel(@RequestParam("file") MultipartFile file,
                                HttpServletRequest req)throws Exception {
        String lx = req.getParameter("lx");
        if(StrUtil.isEmpty(lx)){
            throw BaseException.create("ERROR_SHYB_0049");
        }
        shybYpjcxxSer.impExcel(file, getUser(),Integer.valueOf(lx));
        return ReturnEntityUtil.success();            
    }
    
}

