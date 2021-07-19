package com.buit.his.gpo.controller;

import com.buit.commons.BaseSpringController;
import com.buit.commons.SysUser;
import com.buit.his.gpo.controller.response.JhdwResponse;
import com.buit.his.gpo.controller.response.YqResponse;
import com.buit.his.gpo.service.YqjhdwService;
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

/**
 * @Author sunjx
 * @Date 2020-11-06 14:46
 * @Description
 **/
@Api(tags = "药企信息对照")
@RequestMapping("/yqjhdw")
@RestController
public class YqjhdwRest extends BaseSpringController {

    @Autowired
    private YqjhdwService yqjhdwService;

    @ApiOperation(value = "进货单位分页", httpMethod = "POST")
    @PostMapping("/jhdw_page")
    public ReturnEntity<PageInfo<JhdwResponse>> jhdwPage(
            @ApiParam(name = "pageNo", value = "pageNo", required = true) @RequestParam Integer pageNo,
            @ApiParam(name = "pageSize", value = "pageSize", required = true) @RequestParam Integer pageSize,
            @ApiParam(name = "kw", value = "关键字, 名字/拼音模糊查询") @RequestParam(required = false) String kw,
            @ApiParam(name = "dzzt", value = "对照状态, 0未对照 1已对照") @RequestParam(required = false) Integer dzzt){
        return ReturnEntityUtil.success(PageHelper.startPage(pageNo, pageSize)
                .doSelectPageInfo(() -> yqjhdwService.jhdwList(kw, dzzt)));
    }

    @ApiOperation(value = "药企分页", httpMethod = "POST")
    @PostMapping("/yq_page")
    public ReturnEntity<PageInfo<YqResponse>> yqPage(
            @ApiParam(name = "pageNo", value = "pageNo", required = true) @RequestParam Integer pageNo,
            @ApiParam(name = "pageSize", value = "pageSize", required = true) @RequestParam Integer pageSize,
            @ApiParam(name = "kw", value = "关键字, 名字/拼音模糊查询") @RequestParam(required = false) String kw){
        return ReturnEntityUtil.success(PageHelper.startPage(pageNo, pageSize)
                .doSelectPageInfo(() -> yqjhdwService.yqList(kw)));
    }

    @ApiOperation(value = "对照", httpMethod = "POST")
    @PostMapping("/dz")
    public ReturnEntity dz(
            @ApiParam(name = "jhdwid", value = "进货单位ID", required = true) @RequestParam Integer jhdwid,
            @ApiParam(name = "yqid", value = "药企ID", required = true) @RequestParam Integer yqid){
        SysUser user = getUser();
        yqjhdwService.dz(jhdwid, yqid, user.getHospitalId(), user.getUserId(), user.getUserName());
        return ReturnEntityUtil.success();
    }

    @ApiOperation(value = "自动对照", httpMethod = "POST")
    @PostMapping("/zddz")
    public ReturnEntity zddz(){
        SysUser user = getUser();
        return ReturnEntityUtil.success(yqjhdwService.zddz(user.getHospitalId(), user.getUserId(), user.getUserName()));
    }

    @ApiOperation(value = "解除对照", httpMethod = "POST")
    @PostMapping("/jcdz")
    public ReturnEntity jcdz(
            @ApiParam(name = "dzid", value = "对照id", required = true) @RequestParam Integer dzid){
        yqjhdwService.jcdz(dzid);
        return ReturnEntityUtil.success();
    }
}
