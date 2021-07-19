package com.buit.his.gpo.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author sunjx
 * @Date 2020-11-03 19:04
 * @Description 药品库存数据上报请求
 **/
@ApiModel("药品库存数据上报请求")
public class YpkcsUploadRequest extends GpoBaseRequest {

    @ApiModelProperty(value = "传报日期", required = true)
    private String cbrq;

    public String getCbrq() {
        return cbrq;
    }

    public void setCbrq(String cbrq) {
        this.cbrq = cbrq;
    }
}
