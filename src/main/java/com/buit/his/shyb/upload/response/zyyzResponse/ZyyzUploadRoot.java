package com.buit.his.shyb.upload.response.zyyzResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author weijing
 * @Date 2020-11-02 16:51
 * @Description
 **/
@ApiModel("住院医嘱List")
public class ZyyzUploadRoot {
    @ApiModelProperty("住院医嘱列表")
    private List<ZyyzUploadResp> list;

    public List<ZyyzUploadResp> getList() {
        return list;
    }

    public void setList(List<ZyyzUploadResp> list) {
        this.list = list;
    }
}
