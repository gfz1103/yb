package com.buit.his.gpo.controller;

import com.buit.commons.BaseSpringController;
import com.buit.commons.SysUser;
import com.buit.his.gpo.controller.request.*;
import com.buit.his.gpo.controller.response.FpdzResponse;
import com.buit.his.gpo.service.FpService;
import com.buit.his.gpo.service.result.FpdzxqData;
import com.buit.his.gpo.service.result.Fpmx;
import com.buit.his.gpo.service.result.Fpsj;
import com.buit.his.gpo.ws.response.FpYY007RespStruct;
import com.buit.his.gpo.ws.response.FpYY008RespStruct;
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
 * @Date 2020-11-12 14:12
 * @Description
 **/
@Api(tags = "发票")
@RequestMapping("/fp")
@RestController
public class FpRest extends BaseSpringController {

    @Autowired
    private FpService fpService;

    @ApiOperation(value = "验收(查询)", notes = "获取未验收发票，YY004", httpMethod = "POST")
    @PostMapping("/{yklx}/hqfpsj")
    public ReturnEntity<List<Fpsj>> hq(
            @ApiParam(name = "yklx", value = "药库类型 1西药 2中草药", required = true) @PathVariable Integer yklx,
            @RequestBody HqfpmxsjRequest req){
        SysUser user = getUser();
        req.setJgid(user.getHospitalId());
        req.setUserid(String.valueOf(user.getUserId()));
        req.setUname(user.getUserName());
        req.setYklx(yklx);
        return ReturnEntityUtil.success(fpService.hq(req));
    }

    @ApiOperation(value = "验收", notes = "YY019；验收时候需要的参数, 发票数据和发票明细数据, 保存后便于后续支付，入库",  httpMethod = "POST")
    @PostMapping("/{yklx}/fpys")
    public ReturnEntity ys(
            @ApiParam(name = "yklx", value = "药库类型 1西药 2中草药", required = true) @PathVariable Integer yklx,
            @RequestBody FpysRequest req){
        SysUser user = getUser();
        req.setJgid(user.getHospitalId());
        req.setUserid(String.valueOf(user.getUserId()));
        req.setUname(user.getUserName());
        req.setYklx(yklx);
        fpService.ys(req);
        return ReturnEntityUtil.success();
    }

	@ApiOperation(value = "查询发票数据", notes = "YY007", httpMethod = "POST")
    @PostMapping("/{yklx}/cxfpsj")
    public ReturnEntity<List<FpYY007RespStruct>> cx(
            @ApiParam(name = "yklx", value = "药库类型 1西药 2中草药", required = true) @PathVariable Integer yklx,
            @RequestBody HqfpsjRequest req){
        SysUser user = getUser();
        req.setJgid(user.getHospitalId());
        req.setUserid(String.valueOf(user.getUserId()));
        req.setUname(user.getUserName());
        req.setYklx(yklx);
        return ReturnEntityUtil.success(fpService.cx(req));
    }

	@ApiOperation(value = "发票详情", notes = "YY008，通过药事系统发票ID查询",  httpMethod = "POST")
    @PostMapping("/{yklx}/fpxq")
    public ReturnEntity<List<FpYY008RespStruct>> fpxq(
            @ApiParam(name = "yklx", value = "药库类型 1西药 2中草药", required = true) @PathVariable Integer yklx,
            @RequestBody FpxqRequest req){
        SysUser user = getUser();
        req.setJgid(user.getHospitalId());
        req.setUserid(String.valueOf(user.getUserId()));
        req.setUname(user.getUserName());
        req.setYklx(yklx);
	    return ReturnEntityUtil.success(fpService.fpxq(req));
    }

    @ApiOperation(value = "发票号查明细", notes = "根据发票号查询发票明细；用于支付、入库查询发票明细",  httpMethod = "POST")
    @PostMapping("/mx")
    public ReturnEntity<List<Fpmx>> mx(
            @ApiParam(name = "fph", value = "发票号", required = true) @RequestParam String fph){
        SysUser user = getUser();
        return ReturnEntityUtil.success(fpService.mx(user.getHospitalId(), fph));
    }

    @ApiOperation(value = "对账(查询)", notes = "中草药无此接口",  httpMethod = "POST")
    @PostMapping("/fpdzxq")
    public ReturnEntity<FpdzxqData> fpdzxq(
            @ApiParam(name = "yksb", value = "药库sb", required = true) @RequestParam Integer yksb,
            @ApiParam(name = "ksrq", value = "对账日期开始, 如 20201116", required = true) @RequestParam String ksrq,
            @ApiParam(name = "jsrq", value = "对账日期结束, 如 20201116", required = true) @RequestParam String jsrq,
            @ApiParam(name = "dlcgbz", value = "带量采购标识, 。0：否，1：是", required = true) @RequestParam String dlcgbz){
        SysUser user = getUser();
        return ReturnEntityUtil.success(fpService.fpdzxq(fpService.dzcx(user.getHospitalId(), yksb, ksrq, jsrq, dlcgbz)));
    }

    @ApiOperation(value = "对账", notes = "中草药无此接口, YY016",  httpMethod = "POST")
    @PostMapping("/fpdz")
    public ReturnEntity<FpdzResponse> dz(
            @ApiParam(name = "ip", value = "ip", required = true) @RequestParam String ip,
            @ApiParam(name = "mac", value = "mac", required = true) @RequestParam String mac,
            @ApiParam(name = "yksb", value = "药库sb", required = true) @RequestParam Integer yksb,
            @ApiParam(name = "dzq", value = "对账期, 如 20201116", required = true) @RequestParam String dzq,
            @ApiParam(name = "ksrq", value = "对账日期开始, 如 20201116", required = true) @RequestParam String ksrq,
            @ApiParam(name = "jsrq", value = "对账日期结束, 如 20201116", required = true) @RequestParam String jsrq,
            @ApiParam(name = "dlcgbz", value = "带量采购标识, 。0：否，1：是", required = true) @RequestParam String dlcgbz){
        SysUser user = getUser();
        return ReturnEntityUtil.success(fpService.dz(ip, mac, user.getHospitalId(), yksb, user.getUserId(), user.getUserName(), dzq, ksrq, jsrq, dlcgbz));
    }

    @ApiOperation(value = "支付(查询)", notes = "YY017",  httpMethod = "POST")
    @PostMapping("/zfcx")
    public ReturnEntity<List<Fpsj>> zfcx(
            @ApiParam(name = "yksb", value = "药库sb", required = true) @RequestParam Integer yksb,
            @ApiParam(name = "ksrq", value = "对账日期开始, 如 20201116") @RequestParam(required = false) String ksrq,
            @ApiParam(name = "jsrq", value = "对账日期结束, 如 20201116") @RequestParam(required = false) String jsrq,
            @ApiParam(name = "yqbm", value = "药企编码") @RequestParam(required = false) String yqbm){
        SysUser user = getUser();
        return ReturnEntityUtil.success(fpService.zfcx(user.getHospitalId(), yksb, ksrq, jsrq, yqbm));
    }

    @ApiOperation(value = "支付", notes = "YY017",  httpMethod = "POST")
    @PostMapping("/zf")
    public ReturnEntity zf(@RequestBody ZfRequest request){
        SysUser user = getUser();
        request.setJgid(user.getHospitalId());
        request.setUserid(String.valueOf(user.getUserId()));
        request.setUname(user.getUserName());
        fpService.zf(request);
        return ReturnEntityUtil.success();
    }

    @ApiOperation(value = "入库(查询)",  httpMethod = "POST")
    @PostMapping("/rkcx")
    public ReturnEntity<List<Fpsj>> rkcx(
            @ApiParam(name = "yksb", value = "药库sb", required = true) @RequestParam Integer yksb,
            @ApiParam(name = "ksrq", value = "对账日期开始, 如 20201116") @RequestParam(required = false) String ksrq,
            @ApiParam(name = "jsrq", value = "对账日期结束, 如 20201116") @RequestParam(required = false) String jsrq,
            @ApiParam(name = "yqbm", value = "药企编码") @RequestParam(required = false) String yqbm){
        SysUser user = getUser();
        ksrq= ksrq.replaceAll("-", "");
        jsrq= jsrq.replaceAll("-", "");
        return ReturnEntityUtil.success(fpService.rkcx(user.getHospitalId(), yksb, ksrq, jsrq, yqbm));
    }

    @ApiOperation(value = "生成入库单", notes = "YY017",  httpMethod = "POST")
    @PostMapping("/scrkd")
    public ReturnEntity rk(@RequestBody ScrkdRequest request){
        SysUser user = getUser();
        request.setUserid(user.getUserId());
        request.setUname(user.getUserName());
        request.setJgid(user.getHospitalId());
        fpService.rk(request);
        return ReturnEntityUtil.success();
    }
}
