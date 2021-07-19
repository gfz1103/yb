package com.buit.his.shyb.upload.response.basyResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author weijing
 * @Date 2020-10-30 15:52
 * @Description
 **/
@ApiModel("病案上传List")
public class BasyUploadRoot {
    @ApiModelProperty("病案上传列表")
    private List<BasyUpLoadResp> list;

    public List<BasyUpLoadResp> getList() {
        return list;
    }

    public void setList(List<BasyUpLoadResp> list) {
        this.list = list;
    }
}
