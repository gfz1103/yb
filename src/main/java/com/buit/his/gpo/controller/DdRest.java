package com.buit.his.gpo.controller;

import cn.hutool.core.util.StrUtil;
import com.buit.commons.BaseSpringController;
import com.buit.commons.SysUser;
import com.buit.his.gpo.controller.request.GpoBaseRequest;
import com.buit.his.gpo.model.GpoDd;
import com.buit.his.gpo.model.GpoDdmx;
import com.buit.his.gpo.model.GpoXjd;
import com.buit.his.gpo.service.DdService;
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

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author sunjx
 * @Date 2020-11-10 10:18
 * @Description 订单
 **/
@Api(tags = "订单")
@RequestMapping("/dd")
@RestController
public class DdRest extends BaseSpringController {

    @Autowired
    private DdService ddService;

    @ApiOperation(value = "新增", httpMethod = "POST")
    @PostMapping("/{yklx}/insert")
    public ReturnEntity insert(
            @ApiParam(name = "yklx", value = "药库类型 1西药 2中草药", required = true) @PathVariable Integer yklx,
            @Valid @RequestBody GpoDd dd){
        SysUser user = getUser();
        dd.setCuser(String.valueOf(user.getUserId()));
        dd.setCuname(user.getUserName());
        dd.setJgid(user.getHospitalId());
        dd.setYklx(String.valueOf(yklx));
        return ReturnEntityUtil.success(ddService.save(dd));
    }

    @ApiOperation(value = "删除", httpMethod = "POST")
    @PostMapping("/delete/{xh}")
    public ReturnEntity delete(@ApiParam(name = "xh", value = "xh", required = true) @PathVariable Integer xh){
        SysUser user = getUser();
        ddService.delete(xh, user.getUserId());
        return ReturnEntityUtil.success();
    }

    @ApiOperation(value = "修改", httpMethod = "POST")
    @PostMapping("/update")
    public ReturnEntity update(@RequestBody GpoDd dd){
        return ReturnEntityUtil.success(ddService.update(dd));
    }

    @ApiOperation(value = "传报", httpMethod = "POST")
    @PostMapping("/{yklx}/upload/{xh}")
    public ReturnEntity upload(
            @ApiParam(name = "yklx", value = "药库类型 1西药 2中草药", required = true) @PathVariable String yklx,
            @ApiParam(name = "xh", value = "xh", required = true) @PathVariable Integer xh,
            @ApiParam(name = "ip", value = "ip", required = true) @RequestParam String ip,
            @ApiParam(name = "mac", value = "mac", required = true) @RequestParam String mac){
        ddService.upload(xh, new GpoBaseRequest(ip,mac, GpoConsts.YKLX.getByKey(Integer.valueOf(yklx)), getUser()));
        return ReturnEntityUtil.success();
    }

    @ApiOperation(value = "确认", httpMethod = "POST")
    @PostMapping("/{yklx}/confirm/{xh}")
    public ReturnEntity confirm(
            @ApiParam(name = "yklx", value = "药库类型 1西药 2中草药", required = true) @PathVariable Integer yklx,
            @ApiParam(name = "xh", value = "xh", required = true) @PathVariable Integer xh,
            @ApiParam(name = "ip", value = "ip", required = true) @RequestParam String ip,
            @ApiParam(name = "mac", value = "mac", required = true) @RequestParam String mac){
        ddService.confirm(xh, new GpoBaseRequest(ip,mac, GpoConsts.YKLX.getByKey(yklx), getUser()));
        return ReturnEntityUtil.success();
    }

    @ApiOperation(value = "作废", httpMethod = "POST")
    @PostMapping("/{yklx}/disable/{xh}")
    public ReturnEntity disable(
            @ApiParam(name = "yklx", value = "药库类型 1西药 2中草药", required = true) @PathVariable Integer yklx,
            @ApiParam(name = "xh", value = "xh", required = true) @PathVariable Integer xh,
            @ApiParam(name = "ip", value = "ip", required = false) @RequestParam String ip,
            @ApiParam(name = "mac", value = "mac", required = false) @RequestParam String mac){
        ddService.disable(xh, new GpoBaseRequest(ip, mac, GpoConsts.YKLX.getByKey(yklx), getUser()));
        return ReturnEntityUtil.success();
    }

    @ApiOperation(value = "分页查询", httpMethod = "POST")
    @PostMapping("/{yklx}/page")
    public ReturnEntity<PageInfo<GpoDd>> page(
            @ApiParam(name = "yklx", value = "药库类型 1西药 2中草药", required = true) @PathVariable Integer yklx,
            @ApiParam(name = "rqks", value = "日期-开始,如 2020-11-09", required = true) @RequestParam String rqks,
            @ApiParam(name = "rqjz", value = "日期-截止,如 2020-11-10", required = true) @RequestParam String rqjz,
            @ApiParam(name = "yqmb", value = "药企编码") @RequestParam(required = false) String yqmb,
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
        PageInfo<GpoDd> page = PageHelper.startPage(pageNo, pageSize)
                .doSelectPageInfo(() -> ddService.page(
                        yklx,
                        user.getHospitalId(),
                        TimestampUtil.parseDateTimeStr(rqks + " 00:00:00"),
                        TimestampUtil.parseDateTimeStr(rqjz + " 23:59:59"),
                        yqmb,
                        cbzt,
                        finalQrzt,
                        finalZfzt));
        return ReturnEntityUtil.success(page);
    }

    @ApiOperation(value = "详情查询", httpMethod = "POST")
    @PostMapping("/detail/{xh}")
    public ReturnEntity<GpoDd > detail(@ApiParam(name = "xh", value = "xh", required = true) @PathVariable Integer xh){
        return ReturnEntityUtil.success(ddService.detail(xh,getUser().getHospitalIdStr()));
    }
}
