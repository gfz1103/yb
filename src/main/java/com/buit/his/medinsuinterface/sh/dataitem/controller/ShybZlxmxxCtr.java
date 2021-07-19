
package com.buit.his.medinsuinterface.sh.dataitem.controller;

import com.alibaba.excel.EasyExcel;
import com.buit.commons.BaseException;
import com.buit.commons.BaseSpringController;
import com.buit.his.medinsuinterface.sh.dataitem.excelmodel.ShybZlxmxxExcelModel;
import com.buit.his.medinsuinterface.sh.dataitem.listener.ShybZlxmxxExcelListener;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybZlxmxxAddReq;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybZlxmxxEditReq;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybZlxmxxQueryReq;
import com.buit.his.medinsuinterface.sh.dataitem.response.ShybZlxmxxResp;
import com.buit.his.medinsuinterface.sh.dataitem.service.ShybZlxmxxSer;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboService;
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
 * 上海医保诊疗项目信息<br>
 * @author 老花生
 */
@Api(tags="诊疗项目信息")
@Controller
@RequestMapping("/shybzlxmxx")
public class ShybZlxmxxCtr extends BaseSpringController{
    
    static final Logger logger = LoggerFactory.getLogger(ShybZlxmxxCtr.class);
    
    @Autowired
    private ShybZlxmxxSer shybZlxmxxSer;
    
    @RequestMapping("/queryPage")
    @ResponseBody
    @ApiOperationSupport(author = "老花生")
    @ApiOperation(value="按条件分页查询" ,httpMethod="POST")
    public ReturnEntity<PageInfo<ShybZlxmxxResp>> queryPage(ShybZlxmxxQueryReq req){
    	req.setHospitalId(getUser().getHospitalId());
        shybZlxmxxSer.setSortColumns(req);
        PageInfo<ShybZlxmxxResp> pageInfo = PageHelper.startPage(
        		req.getPageNum(), req.getPageSize()).doSelectPageInfo(
                    () -> shybZlxmxxSer.findByEntity(req)
            );
        return ReturnEntityUtil.success(pageInfo);
    }
    
    /** 新增 */
    @RequestMapping("/add")
    @ResponseBody
    @ApiOperationSupport(author = "老花生")
    @ApiOperation(value="新增" ,httpMethod="POST")
    public ReturnEntity add(ShybZlxmxxAddReq req) {
        shybZlxmxxSer.add(req, getUser());        
        return ReturnEntityUtil.success();            
    }
    
    /** 编辑 */
    @RequestMapping("/edit")
    @ResponseBody
    @ApiOperationSupport(author = "老花生")
    @ApiOperation(value="编辑" ,httpMethod="POST")
    public ReturnEntity edit(ShybZlxmxxEditReq req)  {
        shybZlxmxxSer.edit(req);        
        return ReturnEntityUtil.success();            
    }
    
    /** 导入文件 */
    @RequestMapping("/impExcel")
    @ResponseBody
    @ApiOperationSupport(author = "老花生")
    @ApiOperation(value="导入文件" ,httpMethod="POST")
    public ReturnEntity impExcel(@RequestParam("file") MultipartFile file) {
    	try {
			EasyExcel.read(file.getInputStream(), ShybZlxmxxExcelModel.class, new ShybZlxmxxExcelListener(shybZlxmxxSer, getUser())).sheet().doRead();
		} catch (IOException e) {
			throw BaseException.create("ERROR_SHYB_0001");
		}

    	return ReturnEntityUtil.success();            
    }
    
}

