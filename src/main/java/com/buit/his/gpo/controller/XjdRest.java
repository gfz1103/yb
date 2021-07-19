package com.buit.his.gpo.controller;

import cn.hutool.core.util.StrUtil;
import com.buit.commons.BaseSpringController;
import com.buit.commons.SysUser;
import com.buit.his.gpo.controller.request.GpoBaseRequest;
import com.buit.his.gpo.model.GpoXjd;
import com.buit.his.gpo.service.XjdService;
import com.buit.his.gpo.controller.response.SpResp;
import com.buit.his.gpo.ws.GpoConsts;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.buit.utill.TimestampUtil;
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
 * @Date 2020-11-09 09:30
 * @Description 西药中成药有此功能；中草药无此功能
 **/
@Api(tags = "询价单")
@RequestMapping("/xjd")
@RestController
public class XjdRest extends BaseSpringController {

    @Autowired
    private XjdService xjdService;

    @ApiOperation(value = "新增", httpMethod = "POST")
    @PostMapping("/insert")
    public ReturnEntity insert(@RequestBody GpoXjd xjd) {
        SysUser user = getUser();
        xjd.setJgid(user.getHospitalId());
        xjd.setCuser(String.valueOf(user.getUserId()));
        xjd.setCuname(user.getUserName());
        xjd.setCtime(TimestampUtil.now());
        xjdService.save(xjd);
        return ReturnEntityUtil.success(xjd.getXh());
    }

    @ApiOperation(value = "修改", httpMethod = "POST")
    @PostMapping("/update")
    public ReturnEntity update(@RequestBody GpoXjd xjd) {
        xjdService.save(xjd);
        return ReturnEntityUtil.success();
    }

    @ApiOperation(value = "作废", httpMethod = "POST")
    @PostMapping("/disable/{xh}")
    public ReturnEntity disable(
            @ApiParam(name = "xh", value = "xh", required = true) @PathVariable("xh") Integer xh,
            @ApiParam(name = "ip", value = "ip", required = true) @RequestParam String ip,
            @ApiParam(name = "mac", value = "mac", required = true) @RequestParam String mac) {
        SysUser user = getUser();
        xjdService.disable(xh, new GpoBaseRequest(ip, mac, null, user));
        return ReturnEntityUtil.success();
    }

    @ApiOperation(value = "删除", httpMethod = "POST")
    @PostMapping("/delete/{xh}")
    public ReturnEntity delete(@ApiParam(name = "xh", value = "xh", required = true) @PathVariable("xh") Integer xh) {
        SysUser user = getUser();
        xjdService.delete(xh, user.getUserId());
        return ReturnEntityUtil.success();
    }

    @ApiOperation(value = "传报", httpMethod = "POST")
    @PostMapping("/upload/{xh}")
    public ReturnEntity upload(
            @ApiParam(name = "xh", value = "xh", required = true) @PathVariable("xh") Integer xh,
            @ApiParam(name = "ip", value = "ip", required = true) @RequestParam String ip,
            @ApiParam(name = "mac", value = "mac", required = true) @RequestParam String mac) {
        SysUser user = getUser();
        xjdService.upload(xh, new GpoBaseRequest(ip, mac, null, user));
        return ReturnEntityUtil.success();
    }

    @ApiOperation(value = "确认", httpMethod = "POST")
    @PostMapping("/confirm/{xh}")
    public ReturnEntity confirm(
            @ApiParam(name = "xh", value = "xh", required = true) @PathVariable("xh") Integer xh,
            @ApiParam(name = "ip", value = "ip", required = true) @RequestParam String ip,
            @ApiParam(name = "mac", value = "mac", required = true) @RequestParam String mac) {
        SysUser user = getUser();
        xjdService.confirm(xh, new GpoBaseRequest(ip, mac, null, user));
        return ReturnEntityUtil.success();
    }

    @ApiOperation(value = "分页查询", httpMethod = "POST")
    @PostMapping("/page")
    public ReturnEntity<PageInfo<GpoXjd>> page(
            @ApiParam(name = "xjrqks", value = "询价日期-开始,如 2020-11-09", required = true) @RequestParam String xjrqks,
            @ApiParam(name = "xjrqjz", value = "询价日期-截止,如 2020-11-10", required = true) @RequestParam String xjrqjz,
            @ApiParam(name = "xjsptbbm", value = "询价商品统编编码") @RequestParam(required = false) String xjsptbbm,
            @ApiParam(name = "xjzt", value = "状态或者(多个逗号分隔, 如 3,4 表示已确认或者已作废); 1未传报 2已传报 3已确认 4已作废") @RequestParam(required = false) String xjzt,
            @ApiParam(name = "pageNo", value = "pageNo", required = true) @RequestParam Integer pageNo,
            @ApiParam(name = "pageSize", value = "pageSize", required = true) @RequestParam Integer pageSize) {
        SysUser user = getUser();

        final List<String> cbzt = new ArrayList<>();
        String qrzt = null;
        String zfzt = null;

        if(StrUtil.isNotBlank(xjzt)){

            if(xjzt.contains("1")){
                cbzt.add(GpoConsts.CBZT.DCB.getCode());
            }

            if(xjzt.contains("2")){
                cbzt.add(GpoConsts.CBZT.YCB.getCode());
            }

            if(xjzt.contains("3")){
                qrzt = GpoConsts.QRZT.YQR.getCode();
            }

            if(xjzt.contains("4")){
                zfzt = GpoConsts.ZFZT.YZF.getCode();
            }
        }

        String finalQrzt = qrzt;
        String finalZfzt = zfzt;
        PageInfo<GpoXjd> page = PageHelper.startPage(pageNo, pageSize)
                .doSelectPageInfo(() -> xjdService.page(
                        user.getHospitalId(),
                        TimestampUtil.parseDateTimeStr(xjrqks + " 00:00:00"),
                        TimestampUtil.parseDateTimeStr(xjrqjz + " 23:59:59"),
                        xjsptbbm,
                        cbzt,
                        finalQrzt,
                        finalZfzt));
        return ReturnEntityUtil.success(page);
    }

    @ApiOperation(value = "详情查询", httpMethod = "POST")
    @PostMapping("/detail/{xh}")
    public ReturnEntity<GpoXjd> detail(
            @ApiParam(name = "xh", value = "xh") @PathVariable("xh") Integer xh) {
        return ReturnEntityUtil.success(xjdService.detail(xh));
    }
}
