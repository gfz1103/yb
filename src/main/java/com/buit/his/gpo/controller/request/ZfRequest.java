package com.buit.his.gpo.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author sunjx
 * @Date 2020-11-17 13:59
 * @Description
 **/
@ApiModel
public class ZfRequest extends GpoBaseRequest {

    @ApiModelProperty
    private List<ZfRequestItem> items;

    public List<ZfRequestItem> getItems() {
        return items;
    }

    public void setItems(List<ZfRequestItem> items) {
        this.items = items;
    }
}
