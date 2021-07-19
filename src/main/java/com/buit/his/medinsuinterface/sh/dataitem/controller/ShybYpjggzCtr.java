
package com.buit.his.medinsuinterface.sh.dataitem.controller;

import cn.hutool.core.util.StrUtil;
import com.buit.commons.BaseException;
import com.buit.commons.BaseSpringController;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYpjggzAddReq;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYpjggzEditReq;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYpjggzReq;
import com.buit.his.medinsuinterface.sh.dataitem.response.ShybYpjggzResp;
import com.buit.his.medinsuinterface.sh.dataitem.service.ShybYpjggzSer;
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

import javax.servlet.http.HttpServletRequest;

/**
 * 药品价格规则表<br>
 * @author 老花生
 */
@Api(tags="药品价格规则表")
@Controller
@RequestMapping("/shybypjggz")
public class ShybYpjggzCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(ShybYpjggzCtr.class);
    
    @Autowired
    private ShybYpjggzSer shybYpjggzSer;
    @Autowired
    private HttpServletRequest req;
    
    @RequestMapping("/queryPage")
    @ResponseBody
    @ApiOperationSupport(author = "老花生")
    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
    public ReturnEntity<PageInfo<ShybYpjggzResp>> queryPage(ShybYpjggzReq req){
    	req.setHospitalId(getUser().getHospitalId());
        shybYpjggzSer.setSortColumns(req);
        PageInfo<ShybYpjggzResp> pageInfo = PageHelper.startPage(
        		req.getPageNum(), req.getPageSize()).doSelectPageInfo(
                    () -> shybYpjggzSer.findByEntity(req)
            );
        return ReturnEntityUtil.success(pageInfo);
    }
    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<ShybYpjggzResp>> getByEntity( ShybYpjggzReq shybypjggz){//@RequestBody 
//        return ReturnEntityUtil.success(shybYpjggzSer.findByEntity(shybypjggz));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<ShybYpjggzResp> getOneByEntity(ShybYpjggzReq shybypjggz){
//        List<ShybYpjggz> list=shybYpjggzSer.findByEntity(shybypjggz);
//        if(list.size()>0){
//           return ReturnEntityUtil.success(list.get(0));    
//        }
//        return ReturnEntityUtil.success();
//    }
//    
    /** 新增 */
    @RequestMapping("/add")
    @ResponseBody
    @ApiOperationSupport(author = "老花生")
    @ApiOperation(value="新增" ,httpMethod="POST")
    public ReturnEntity add(ShybYpjggzAddReq req) {
        shybYpjggzSer.add(req, getUser());        
        return ReturnEntityUtil.success();            
    }
    /** 编辑 */
    @RequestMapping("/edit")
    @ResponseBody
    @ApiOperationSupport(author = "老花生")
    @ApiOperation(value="编辑" ,httpMethod="POST")
    public ReturnEntity edit(ShybYpjggzEditReq req)  {
        shybYpjggzSer.edit(req);        
        return ReturnEntityUtil.success(); 
    }
    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<ShybYpjggzResp> delete(ShybYpjggzReq shybYpjggz) {
//        shybYpjggzSer.removeByEntity(shybYpjggz);        
//        return ReturnEntityUtil.success(shybYpjggz);            
//    }
    
    
    /** 导入文件 */
    @RequestMapping("/impExcel")
    @ResponseBody
    @ApiOperationSupport(author = "老花生")
    @ApiOperation(value="导入文件" ,httpMethod="POST")
    public ReturnEntity impExcel(@RequestParam("file") MultipartFile file) {
        String lx = req.getParameter("lx");
        if(StrUtil.isEmpty(lx)){
            throw BaseException.create("ERROR_SHYB_0049");
        }
        shybYpjggzSer.impExcel(file, getUser(),Integer.valueOf(lx));
        return ReturnEntityUtil.success();            
    }
    
}

