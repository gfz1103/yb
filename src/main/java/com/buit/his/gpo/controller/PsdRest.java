package com.buit.his.gpo.controller;

import com.buit.commons.BaseSpringController;
import com.buit.commons.SysUser;
import com.buit.his.gpo.controller.request.GpoBaseRequest;
import com.buit.his.gpo.controller.request.PsdmxysRequest;
import com.buit.his.gpo.controller.request.PsdsjRequest;
import com.buit.his.gpo.controller.request.QueryPsdReq;
import com.buit.his.gpo.controller.response.QueryPsdResp;
import com.buit.his.gpo.service.PsdService;
import com.buit.his.gpo.ws.GpoConsts;
import com.buit.his.gpo.ws.response.PsdYY003RespStruct;
import com.buit.his.gpo.ws.response.PsdYY005RespStruct;
import com.buit.his.gpo.ws.response.PsdYY006RespStruct;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author sunjx
 * @Date 2020-11-11 19:00
 * @Description
 **/
@Api(tags = "配送单")
@RequestMapping("/psd")
@RestController
public class PsdRest extends BaseSpringController {

    @Autowired
    private PsdService psdService;

    @ApiOperation(value = "获取配送单", notes = "YY003, 待验收, 不分页", httpMethod = "POST")
    @PostMapping("/{yklx}/hqpsdmx")
    public ReturnEntity<QueryPsdResp> hqpsdmx(@PathVariable Integer yklx, QueryPsdReq req){
        req.setYklb(yklx);
        return ReturnEntityUtil.success(psdService.hqpsdmx(req, getUser()));
    }

    @ApiOperation(value = "配送明细验收", notes = "YY018", httpMethod = "POST")
    @PostMapping("/{yklx}/psmxys")
    public ReturnEntity ys(
            @ApiParam(name = "yklx", value = "药库类型 1西药 2中草药", required = true) @PathVariable Integer yklx,
            @RequestBody PsdmxysRequest req){
        SysUser user = getUser();
        req.setJgid(user.getHospitalId());
        req.setUserid(String.valueOf(user.getUserId()));
        req.setUname(user.getUserName());
        req.setYklx(yklx);
        psdService.ys(req,getUser());
        return ReturnEntityUtil.success();
    }

    @ApiOperation(value = "查询配送单数据", notes = "YY005, 不分页", httpMethod = "POST")
    @PostMapping("/{yklx}/hqpsd")
    public ReturnEntity<List<PsdYY005RespStruct>> hqpsd(
            @ApiParam(name = "yklx", value = "药库类型 1西药 2中草药", required = true) @PathVariable Integer yklx,
            @RequestBody PsdsjRequest req){
        SysUser user = getUser();
        req.setJgid(user.getHospitalId());
        req.setUserid(String.valueOf(user.getUserId()));
        req.setUname(user.getUserName());
        req.setYklx(yklx);
        return ReturnEntityUtil.success(psdService.hqpsd(req));
    }

    @ApiOperation(value = "查看明细/", notes = "YY006, 不分页", httpMethod = "POST")
    @PostMapping("/{yklx}/hqpsdmx_bh")
    public ReturnEntity<List<PsdYY006RespStruct>> hqpsdmxByPsdbh(
            @ApiParam(name = "yklx", value = "药库类型 1西药 2中草药", required = true) @PathVariable Integer yklx,
            @ApiParam(name = "psdid", value = "配送单ID", required = true) @RequestParam String psdid,
            @ApiParam(name = "ip", value = "ip", required = true) @RequestParam String ip,
            @ApiParam(name = "mac", value = "mac", required = true) @RequestParam String mac){
        return ReturnEntityUtil.success(psdService.hqpsdmxByPsdbh(new GpoBaseRequest(ip, mac, GpoConsts.YKLX.getByKey(yklx), getUser()), psdid));
    }
}
