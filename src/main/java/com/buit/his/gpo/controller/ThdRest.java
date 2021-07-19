package com.buit.his.gpo.controller;

import cn.hutool.core.util.StrUtil;
import com.buit.commons.BaseSpringController;
import com.buit.commons.SysUser;
import com.buit.his.gpo.controller.request.GpoBaseRequest;
import com.buit.his.gpo.model.GpoThd;
import com.buit.his.gpo.service.ThdService;
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
 * @Date 2020-11-17 14:35
 * @Description
 **/
@Api(tags = "退货单")
@RequestMapping("/thd")
@RestController
public class ThdRest extends BaseSpringController {

    @Autowired
    private ThdService thdService;

    @ApiOperation(value = "新增", httpMethod = "POST")
    @PostMapping("/insert")
    public ReturnEntity insert(@RequestBody GpoThd thd){
        SysUser user = getUser();
        thd.setJgid(user.getHospitalId());
        thd.setCuser(user.getUserId());
        thd.setCuname(user.getUserName());
        thdService.save(thd);
        return ReturnEntityUtil.success();
    }

    @ApiOperation(value = "修改", httpMethod = "POST")
    @PostMapping("/update")
    public ReturnEntity update(@RequestBody GpoThd thd){
        thdService.save(thd);
        return ReturnEntityUtil.success();
    }

    @ApiOperation(value = "传报", notes = "YY011", httpMethod = "POST")
    @PostMapping("/upload/{xh}")
    public ReturnEntity upload(
        @ApiParam(name = "xh", value = "xh", required = true) @PathVariable Integer xh,
        @ApiParam(name = "ip", value = "ip", required = true) @RequestParam String ip,
        @ApiParam(name = "mac", value = "mac", required = true) @RequestParam String mac){
        thdService.upload(xh, new GpoBaseRequest(ip, mac, null, getUser()));
        return ReturnEntityUtil.success();
    }

    @ApiOperation(value = "确认", notes = "YY012", httpMethod = "POST")
    @PostMapping("/confirm/{xh}")
    public ReturnEntity confirm(
        @ApiParam(name = "xh", value = "xh", required = true) @PathVariable Integer xh,
        @ApiParam(name = "ip", value = "ip", required = true) @RequestParam String ip,
        @ApiParam(name = "mac", value = "mac", required = true) @RequestParam String mac){
        thdService.confirm(xh, new GpoBaseRequest(ip, mac, null, getUser()));
        return ReturnEntityUtil.success();
    }

    @ApiOperation(value = "作废", notes = "YY011", httpMethod = "POST")
    @PostMapping("/disable/{xh}")
    public ReturnEntity disable(
        @ApiParam(name = "xh", value = "xh", required = true) @PathVariable Integer xh,
        @ApiParam(name = "ip", value = "ip", required = true) @RequestParam String ip,
        @ApiParam(name = "mac", value = "mac", required = true) @RequestParam String mac){
        thdService.disable(xh, new GpoBaseRequest(ip, mac, null, getUser()));
        return ReturnEntityUtil.success();
    }

    @ApiOperation(value = "删除", httpMethod = "POST")
    @PostMapping("/delete/{xh}")
    public ReturnEntity delete(@ApiParam(name = "xh", value = "xh", required = true) @PathVariable Integer xh){
        SysUser user = getUser();
        thdService.delete(xh, user.getUserId());
        return ReturnEntityUtil.success();
    }

    @ApiOperation(value = "分页查询", httpMethod = "POST")
    @PostMapping("/page")
    public ReturnEntity page(
            @ApiParam(name = "rqks", value = "日期-开始,如 2020-11-09", required = true) @RequestParam String rqks,
            @ApiParam(name = "rqjz", value = "日期-截止,如 2020-11-10", required = true) @RequestParam String rqjz,
            @ApiParam(name = "yqbm", value = "药企编码") @RequestParam(required = false) String yqbm,
            @ApiParam(name = "zt", value = "状态或者(多个逗号分隔, 如 3,4 表示已确认或者已作废); 1未传报 2已传报 3已确认 4已作废") @RequestParam(required = false) String zt,
            @ApiParam(name = "pageNo", value = "pageNo", required = true) @RequestParam Integer pageNo,
            @ApiParam(name = "pageSize", value = "pageSize", required = true) @RequestParam Integer pageSize){
        SysUser user = getUser();

        final List<String> cbzt = new ArrayList<>();
        String qrzt = null;
        String zfzt = null;

        if(StrUtil.isNotBlank(zt)){

            if(zt.contains("1")){
                cbzt.add(GpoConsts.CBZT.DCB.getCode());
            }

            if(zt.contains("2")){
                cbzt.add(GpoConsts.CBZT.YCB.getCode());
            }

            if(zt.contains("3")){
                qrzt = GpoConsts.QRZT.YQR.getCode();
            }

            if(zt.contains("4")){
                zfzt = GpoConsts.ZFZT.YZF.getCode();
            }
        }

        String finalQrzt = qrzt;
        String finalZfzt = zfzt;
        PageInfo<GpoThd> page = PageHelper.startPage(pageNo, pageSize)
                .doSelectPageInfo(() -> thdService.page(
                        user.getHospitalId(),
                        TimestampUtil.parseDateTimeStr(rqks + " 00:00:00"),
                        TimestampUtil.parseDateTimeStr(rqjz + " 23:59:59"),
                        yqbm,
                        cbzt,
                        finalQrzt,
                        finalZfzt));
        return ReturnEntityUtil.success(page);
    }

    @ApiOperation(value = "详情查询", httpMethod = "POST")
    @PostMapping("/detail/{xh}")
    public ReturnEntity detail(@ApiParam(name = "xh", value = "xh", required = true) @PathVariable Integer xh){
        return ReturnEntityUtil.success(thdService.detail(xh));
    }


}

