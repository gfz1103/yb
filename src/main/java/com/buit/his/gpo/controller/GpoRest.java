package com.buit.his.gpo.controller;

import com.buit.commons.BaseSpringController;
import com.buit.commons.SysUser;
import com.buit.his.gpo.controller.response.SpResp;
import com.buit.his.gpo.model.GpoJgxx;
import com.buit.his.gpo.model.GpoZd;
import com.buit.his.gpo.service.GpoService;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author sunjx
 * @Date 2020-11-10 17:09
 * @Description
 **/
@Api(tags = "公用接口")
@RequestMapping("/gpo")
@RestController
public class GpoRest extends BaseSpringController {

    @Autowired
    private GpoService gpoService;

    @ApiOperation(value = "药品列表", httpMethod = "POST")
    @PostMapping(value = "/yplist")
    public ReturnEntity<PageInfo<SpResp>> yplist(
            @ApiParam("药库识别") @RequestParam Integer yksb,
            @ApiParam(name = "kw", value = "关键字, 名称, 拼音码") @RequestParam String kw,
            @ApiParam(name = "pageNum", value = "pageNum") @RequestParam(required = false) Integer pageNum,
            @ApiParam(name = "pageSize", value = "pageSize", required = true) @RequestParam(required = false) Integer pageSize){
        SysUser user = getUser();
        return ReturnEntityUtil.success(gpoService.ypList(pageNum,pageSize,String.valueOf(user.getHospitalId()), kw,yksb));
    }

    @ApiOperation(value = "药企列表", httpMethod = "POST")
    @PostMapping(value = "/yqlist")
    public ReturnEntity<PageInfo<SpResp>> yqlist(
            @ApiParam(name = "kw", value = "关键字, 名称, 拼音码") @RequestParam String kw,
            @ApiParam(name = "pageNum", value = "pageNum") @RequestParam(required = false) Integer pageNum,
            @ApiParam(name = "pageSize", value = "pageSize", required = true) @RequestParam(required = false) Integer pageSize){
        SysUser user = getUser();
        return ReturnEntityUtil.success(PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> gpoService.yqlist(String.valueOf(user.getHospitalId()), kw)));
    }

    @ApiOperation(value = "GPO机构编码", httpMethod = "POST")
    @PostMapping(value = "/jgbm")
    public ReturnEntity<GpoJgxx> gpoJgbm(){
        SysUser user = getUser();
        return ReturnEntityUtil.success(gpoService.gpoJgbm(user.getHospitalId()));
    }

    @ApiOperation(value = "字典数据", httpMethod = "POST",
            notes = "1, 药品类型(4.1.1),<br />" +
                    "2, 采购类型(4.1.2),<br />" +
                    "3, 验收情况(4.1.3),<br />" +
                    "4, 装箱类型(4.1.4),<br />" +
                    "5, 商品类型(4.1.5),<br />" +
                    "6, 操作类型(4.1.6),<br />" +
                    "7, 包装单位性质(4.1.7),<br />" +
                    "8, 订单处理状态(4.1.8),<br />" +
                    "9, 退货单处理状态(4.1.9),<br />" +
                    "10, 询价单处理状态(4.1.10),<br />" +
                    "11, 消息主体处理结果(4.1.11),<br />" +
                    "12, 消息明细条目处理结果(4.1.12),<br />" +
                    "13, 提交方式(4.1.13),<br />" +
                    "14, 订单类型(4.1.14),<br />" +
                    "15, 查询类型(4.1.15),<br />" +
                    "16, 配送单状态(4.1.16),<br />" +
                    "17, 发票状态(4.1.17),<br />" +
                    "18, 报价单状态(4.1.18),<br />" +
                    "19, 药品字典匹配状态(4.1.19),<br />" +
                    "20, 验收结果(4.1.20),<br />" +
                    "21, 对账结果(4.1.21),<br />" +
                    "22, 配送单明细状态(4.1.22),<br />" +
                    "23, 采购计量单位(4.1.23),<br />" +
                    "24, 急抢救用药数量(4.1.24),<br />" +
                    "25, 申请用途(4.1.25),<br />" +
                    "26, 申请原因(4.1.26),<br />" +
                    "27, 特需药品审核方式(4.1.27),<br />" +
                    "28, 特需药品申报结果(4.1.28),")
    @PostMapping(value = "/zd")
    public ReturnEntity<List<GpoZd>> zd(@ApiParam(name = "type", value = "type", required = true) @RequestParam String type){
        SysUser user = getUser();
        return ReturnEntityUtil.success(gpoService.zd(type));
    }
}
