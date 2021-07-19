package com.buit.his.gpo.controller;

import com.buit.commons.BaseSpringController;
import com.buit.commons.SysUser;
import com.buit.his.gpo.controller.request.GpoBaseRequest;
import com.buit.his.gpo.model.GpoBjd;
import com.buit.his.gpo.service.BjdService;
import com.buit.his.gpo.ws.GpoConsts;
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
 * @Date 2020-11-09 15:44
 * @Description 报价单
 **/
@Api(tags = "报价单")
@RequestMapping("/bjd")
@RestController
public class BjdRest extends BaseSpringController {

    @Autowired
    private BjdService bjdService;

    @ApiOperation(value = "列表查询(不分页)", httpMethod = "POST")
    @PostMapping("/{yklx}/query")
    public ReturnEntity<List<GpoBjd>> list(
            @ApiParam(name = "yklx", value = "药库类型 1西药 2中草药", required = true) @PathVariable Integer yklx,
            @ApiParam(name = "ip", value = "ip", required = true) @RequestParam String ip,
            @ApiParam(name = "mac", value = "mac", required = true) @RequestParam String mac,
            @ApiParam(name = "bjrqks", value = "报价日期-开始, 如 2020-11-09", required = true) @RequestParam String bjrqks,
            @ApiParam(name = "bjrqjs", value = "报价日期-结束, 如 2020-11-09", required = true) @RequestParam String bjrqjs,
            @ApiParam(name = "yqbm", value = "药企编码") @RequestParam(required = false) String yqbm,
            @ApiParam(name = "splx", value = "商品类型") @RequestParam(required = false) String splx,
            @ApiParam(name = "yplx", value = "药品类型") @RequestParam(required = false) String yplx,
            @ApiParam(name = "xjdbh", value = "询价单编号") @RequestParam(required = false) String xjdbh) {
        SysUser user = getUser();

        return ReturnEntityUtil.success(bjdService.list(
                new GpoBaseRequest(ip, mac, GpoConsts.YKLX.getByKey(yklx), user),
                bjrqks,
                bjrqjs,
                yqbm,
                splx,
                yplx,
                xjdbh));
    }

}
