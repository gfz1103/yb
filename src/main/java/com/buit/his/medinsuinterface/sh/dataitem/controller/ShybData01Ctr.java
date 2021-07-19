
package com.buit.his.medinsuinterface.sh.dataitem.controller;

import com.buit.commons.BaseSpringController;
import com.buit.his.medinsuinterface.sh.dataitem.service.ShybData01Ser;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 医保数据主表<br>
 * @author 老花生
 */
@Api(tags="医保数据主表")
@Controller
@RequestMapping("/shybdata01")
public class ShybData01Ctr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(ShybData01Ctr.class);
    
    @Autowired
    private ShybData01Ser shybData01Ser;
    
//    @RequestMapping("/queryPage")
//    @ResponseBody
//    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
//    public PageInfo<ShybData01Resp> queryPage(ShybData01Req shybdata01,PageQuery page){
//        PageInfo<ShybData01> pageInfo = PageHelper.startPage(
//            page.getPageNum(), page.getPageSize()).doSelectPageInfo(
//                    () -> shybData01Ser.findByEntity(shybdata01)
//            );
//        return pageInfo;
//    }
//    
//    @RequestMapping("/findList")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回列表" ,httpMethod="POST")
//    public ReturnEntity<List<ShybData01Resp>> getByEntity( ShybData01Req shybdata01){//@RequestBody 
//        return ReturnEntityUtil.success(shybData01Ser.findByEntity(shybdata01));    
//    }
//    
//    @RequestMapping("/getOneByEntity")
//    @ResponseBody
//    @ApiOperation(value="按条件查询-返回唯一值" ,httpMethod="POST")
//    public ReturnEntity<ShybData01Resp> getOneByEntity(ShybData01Req shybdata01){
//        List<ShybData01> list=shybData01Ser.findByEntity(shybdata01);
//        if(list.size()>0){
//           return ReturnEntityUtil.success(list.get(0));    
//        }
//        return ReturnEntityUtil.success();
//    }
//    
//    /** 新增 */
//    @RequestMapping("/add")
//    @ResponseBody
//    @ApiOperation(value="新增" ,httpMethod="POST")
//    public ReturnEntity<ShybData01Resp> add(ShybData01Req shybData01) {
//        shybData01Ser.insert(shybData01);        
//        return ReturnEntityUtil.success(shybData01);            
//    }
//    /** 编辑 */
//    @RequestMapping("/edit")
//    @ResponseBody
//    @ApiOperation(value="编辑" ,httpMethod="POST")
//    public ReturnEntity<ShybData01Resp> edit(ShybData01Req shybData01)  {
//        shybData01Ser.update(shybData01);        
//        return ReturnEntityUtil.success(shybData01);            
//    }
//    
//    /** 删除 */
//    @RequestMapping("/delete")
//    @ResponseBody
//    @ApiOperation(value="按条件查询" ,httpMethod="POST")
//    public ReturnEntity<ShybData01Resp> delete(ShybData01Req shybData01) {
//        shybData01Ser.removeByEntity(shybData01);        
//        return ReturnEntityUtil.success(shybData01);            
//    }
    
}

