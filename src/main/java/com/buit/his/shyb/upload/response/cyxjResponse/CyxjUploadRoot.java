package com.buit.his.shyb.upload.response.cyxjResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author weijing
 * @Date 2020-11-02 16:33
 * @Description
 **/
@ApiModel("出院小结List")
public class CyxjUploadRoot {
    @ApiModelProperty("出院小结上传列表")
    private List<CyxjUploadResp> list;

    public List<CyxjUploadResp> getList() {
        return list;
    }

    public void setList(List<CyxjUploadResp> list) {
        this.list = list;
    }
}
