package com.buit.his.gpo.controller.request;

import com.buit.his.gpo.ws.request.FpYY008ReqMain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author sunjx
 * @Date 2020-11-12 15:29
 * @Description
 **/
@ApiModel("发票详情请求")
public class FpxqRequest extends GpoBaseRequest {

    @ApiModelProperty
    private FpYY008ReqMain main;

    public FpYY008ReqMain getMain() {
        return main;
    }

    public void setMain(FpYY008ReqMain main) {
        this.main = main;
    }
}
