package com.buit.his.gpo.controller.request;

import com.buit.his.gpo.service.result.Fpsj;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author sunjx
 * @Date 2020-11-12 15:23
 * @Description
 **/
@ApiModel("发票验收请求")
public class FpysRequest extends GpoBaseRequest{

    @ApiModelProperty(value = "发票明细数据", required = true)
    private List<Fpsj> items;

    public List<Fpsj> getItems() {
        return items;
    }

    public void setItems(List<Fpsj> items) {
        this.items = items;
    }
}
