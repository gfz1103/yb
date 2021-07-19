package com.buit.his.gpo.controller.request;

import com.buit.his.gpo.ws.request.PsdYY005ReqMain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author sunjx
 * @Date 2020-11-12 11:16
 * @Description
 **/
@ApiModel("配送点数据获取请求")
public class PsdsjRequest extends GpoBaseRequest{

    @ApiModelProperty("配送单请求参数")
    private PsdYY005ReqMain psdqq;

    public PsdYY005ReqMain getPsdqq() {
        return psdqq;
    }

    public void setPsdqq(PsdYY005ReqMain psdqq) {
        this.psdqq = psdqq;
    }
}
