package com.buit.his.gpo.controller;

import com.buit.commons.BaseSpringController;
import com.buit.commons.SysUser;
import com.buit.his.gpo.controller.request.GpoBaseRequest;
import com.buit.his.gpo.service.TyService;
import com.buit.his.gpo.service.XjdService;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "通用查询")
@RequestMapping("/ty")
@RestController
public class TyRest extends BaseSpringController {

    @Autowired
    private TyService tyService;

    @ApiOperation(value = "单挑单据通用查询", httpMethod = "POST")
    @PostMapping("/djcx")
    public ReturnEntity detail(@ApiParam(name = "cxlx", value = "详见4.1.15查询类型", required = true)  String cxlx,
                               @ApiParam(name = "cxbh", value = "对应不同的查询类型，需要填写不同\n" +
                                       "的信息，具体填写内容如下：\n" +
                                       "01：填写订单编号\n" +
                                       "02：填写配送单编号\n" +
                                       "03：填写发票号\n" +
                                       "04：填写退货单编号\n" +
                                       "05：填写报价单编号\n" +
                                       "06：填写询价单编号\n" +
                                       "07：填写药品统编代码\n" +
                                       " 110 / 146\n" +
                                       "08：填写配送明细编码\n" +
                                       "09：填写配送点编码\n" +
                                       "10: 填写订单明细编号", required = true) String cxbh,
                               @ApiParam(name = "djid", value = "若查询类型为发票或配送单，则可以\n" +
                                       "通过对应的发票ID或配送单ID查询指\n" +
                                       "定单据的信息，可返回状态为已作废\n" +
                                       "的数据", required = false)  String djid,
                               @ApiParam(name = "bzxx", value = "备注", required = false)  String bzxx,
                               @ApiParam(name = "ip", value = "ip", required = true) @RequestParam String ip,
                               @ApiParam(name = "mac", value = "mac", required = true) @RequestParam String mac) {
        SysUser user = getUser();
        return ReturnEntityUtil.success(tyService.djcx(cxlx,cxbh,djid, new GpoBaseRequest(ip, mac, null, user)));
    }

    @ApiOperation(value = "获取订单明细状态", notes = "YY021", httpMethod = "POST")
    @PostMapping("/ddzt")
    public ReturnEntity confirm(
            @ApiParam(name = "ddbh", value = "订单编号", required = true) @RequestParam String ddbh,
            @ApiParam(name = "ip", value = "ip", required = true) @RequestParam String ip,
            @ApiParam(name = "mac", value = "mac", required = true) @RequestParam String mac){
        return ReturnEntityUtil.success( tyService.ddzt(ddbh, new GpoBaseRequest(ip, mac, null, getUser())));
    }
}
