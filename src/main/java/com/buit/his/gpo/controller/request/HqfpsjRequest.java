package com.buit.his.gpo.controller.request;

import com.buit.his.gpo.ws.request.FpYY007ReqMain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author sunjx
 * @Date 2020-11-12 14:51
 * @Description
 **/
@ApiModel("获取发票数据请求")
public class HqfpsjRequest extends GpoBaseRequest {

    @ApiModelProperty
    private FpYY007ReqMain main;

    public FpYY007ReqMain getMain() {
        return main;
    }

    public void setMain(FpYY007ReqMain main) {
        this.main = main;
    }
}
