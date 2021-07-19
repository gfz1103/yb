package com.buit.his.gpo.controller.request;

import com.buit.his.gpo.ws.request.FpYY004ReqMain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author sunjx
 * @Date 2020-11-13 17:29
 * @Description
 **/
@ApiModel
public class HqfpmxsjRequest extends GpoBaseRequest {

    @ApiModelProperty
    private FpYY004ReqMain main;

    public FpYY004ReqMain getMain() {
        return main;
    }

    public void setMain(FpYY004ReqMain main) {
        this.main = main;
    }
}
