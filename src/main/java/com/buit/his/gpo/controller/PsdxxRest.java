package com.buit.his.gpo.controller;

import com.buit.commons.BaseSpringController;
import com.buit.commons.SysUser;
import com.buit.his.gpo.controller.request.GpoBaseRequest;
import com.buit.his.gpo.model.GpoPsdxx;
import com.buit.his.gpo.service.PsdxxService;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author sunjx
 * @Date 2020-11-03 17:02
 * @Description 配送点信息
 **/
@Api(tags = "配送点信息")
@RequestMapping("/psdxx")
@RestController
public class PsdxxRest extends BaseSpringController {

    @Autowired
    private PsdxxService psdxxService;

    @ApiOperation(value = "新增", httpMethod = "POST")
    @PostMapping("/insert")
    public ReturnEntity insert(@RequestBody GpoPsdxx psdxx) {
        SysUser user = getUser();
        psdxx.setJgid(user.getHospitalId());
        psdxx.setCuser(String.valueOf(user.getUserId()));
        psdxx.setCuname(user.getUserName());
        psdxxService.save(psdxx);
        return ReturnEntityUtil.success(psdxx.getXh());
    }

    @ApiOperation(value = "修改", httpMethod = "POST")
    @PostMapping("/update")
    public ReturnEntity update(@RequestBody GpoPsdxx psdxx) {
        psdxxService.save(psdxx);
        return ReturnEntityUtil.success();
    }

    @ApiOperation(value = "作废", httpMethod = "POST")
    @PostMapping("/disable/{xh}")
    public ReturnEntity disable(
            @ApiParam(name = "xh", value = "xh", required = true) @PathVariable("xh") Integer xh,
            @ApiParam(name = "ip", value = "ip", required = true) @RequestParam String ip,
            @ApiParam(name = "mac", value = "mac", required = true) @RequestParam String mac) {
        SysUser user = getUser();
        psdxxService.disable(xh, new GpoBaseRequest(ip, mac, null, user));
        return ReturnEntityUtil.success();
    }

    @ApiOperation(value = "删除", httpMethod = "POST")
    @PostMapping("/delete/{xh}")
    public ReturnEntity delete(@ApiParam(name = "xh", value = "xh", required = true) @PathVariable("xh") Integer xh) {
        SysUser user = getUser();
        psdxxService.delete(xh, user.getUserId());
        return ReturnEntityUtil.success();
    }

    @ApiOperation(value = "传报", httpMethod = "POST")
    @PostMapping("/upload/{xh}")
    public ReturnEntity upload(
            @ApiParam(name = "xh", value = "xh", required = true) @PathVariable("xh") Integer xh,
            @ApiParam(name = "ip", value = "ip", required = true) @RequestParam String ip,
            @ApiParam(name = "mac", value = "mac", required = true) @RequestParam String mac) {
        SysUser user = getUser();
        psdxxService.upload(xh, new GpoBaseRequest(ip, mac, null, user));
        return ReturnEntityUtil.success();
    }

    @ApiOperation(value = "分页查询", httpMethod = "POST")
    @PostMapping("/page")
    public ReturnEntity<PageInfo<GpoPsdxx>> page(
            @ApiParam(name = "pageNo", value = "pageNo", required = true) @RequestParam Integer pageNo,
            @ApiParam(name = "pageSize", value = "pageSize", required = true) @RequestParam Integer pageSize) {
        PageInfo<GpoPsdxx> page = PageHelper.startPage(pageNo, pageSize)
                .doSelectPageInfo(() -> psdxxService.page());
        return ReturnEntityUtil.success(page);
    }

    @ApiOperation(value = "详情查询", httpMethod = "POST")
    @PostMapping("/detail/{xh}")
    public ReturnEntity<GpoPsdxx> detail(
            @ApiParam(name = "xh", value = "xh") @PathVariable("xh") Integer xh) {
        return ReturnEntityUtil.success(psdxxService.detail(xh));
    }

    @ApiOperation(value = "获取ip和mac", httpMethod = "POST")
    @PostMapping("/getIpMac")
    public ReturnEntity<List<String>> getMacAndIp(
    ) {
        List<String> objects = new ArrayList<>();
        objects.add("192.168.1.1");
        objects.add("F875A451F7CE");
        return ReturnEntityUtil.success(objects);
    }
}

