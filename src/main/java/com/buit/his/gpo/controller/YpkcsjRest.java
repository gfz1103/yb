package com.buit.his.gpo.controller;

import com.buit.commons.BaseSpringController;
import com.buit.commons.SysUser;
import com.buit.his.gpo.controller.request.YpkcsUploadRequest;
import com.buit.his.gpo.controller.response.YpkcshQueryResponse;
import com.buit.his.gpo.service.YpkcsjService;
import com.buit.his.gpo.ws.GpoConsts;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author sunjx
 * @Date 2020-11-03 18:53
 * @Description
 **/
@Api(tags = "库存", hidden = true)
@RequestMapping("/ypkcsj")
@RestController
public class YpkcsjRest extends BaseSpringController {

    @Autowired
    private YpkcsjService ypkcsjService;

    @ApiOperation(value = "库存分页查询", httpMethod = "POST")
    @PostMapping("/page")
    public ReturnEntity<PageInfo<YpkcshQueryResponse>> page(@ApiParam(name = "pageNum", value = "pageNum") @RequestParam Integer pageNum,
                                                            @ApiParam(name = "pageSize", value = "pageSize") @RequestParam Integer pageSize) {
        SysUser user = getUser();
        PageInfo<YpkcshQueryResponse> page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() ->
                ypkcsjService.list(user.getHospitalId()));
        return ReturnEntityUtil.success(page);
    }

    @ApiOperation(value = "传报", httpMethod = "POST")
    @PostMapping("/upload")
    public ReturnEntity upload(
            @RequestBody YpkcsUploadRequest request) {
        SysUser user = getUser();
        request.setJgid(user.getHospitalId());
        request.setUserid(String.valueOf(user.getUserId()));
        request.setUname(user.getUserName());
        request.setYklx(GpoConsts.YKLX.XY.getKey());
        ypkcsjService.upload(request);
        return ReturnEntityUtil.success();
    }

}
