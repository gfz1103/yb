package com.buit.his.shyb.upload.controller;

import com.buit.his.shyb.upload.service.BasyUpLoadSer;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
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

/**
 * @Author weijing
 * @Date 2020-10-30 15:27
 * @Description
 **/
@Api(tags="病案首页上传")
@Controller
@RequestMapping("/upload")
public class BasyUploadCtr {
    static final Logger logger = LoggerFactory.getLogger(BasyUploadCtr.class);

    @Autowired
    private BasyUpLoadSer basyUpLoadSer;

    @RequestMapping("/basy")
    @ResponseBody
    @ApiOperation(value="病案首页上传" ,httpMethod="POST")
    public ReturnEntity printBasy(@ApiParam(name = "yljgd", value = "医疗机构代码", required = true) @RequestParam(value = "yljgd") Integer yljgd){
        String upload = basyUpLoadSer.basyUpLoad(yljgd);
        return ReturnEntityUtil.success(upload);
    }
}
