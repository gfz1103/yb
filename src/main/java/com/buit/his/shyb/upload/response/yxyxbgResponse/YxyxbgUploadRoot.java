package com.buit.his.shyb.upload.response.yxyxbgResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author weijing
 * @Date 2020-11-02 19:12
 * @Description
 **/
@ApiModel("医学影像报告List")
public class YxyxbgUploadRoot {
    @ApiModelProperty("医学影像报告列表")
    private List<YxyxbgUploadResp> list;

    public List<YxyxbgUploadResp> getList() {
        return list;
    }

    public void setList(List<YxyxbgUploadResp> list) {
        this.list = list;
    }
}
