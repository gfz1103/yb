package com.buit.his.shyb.upload.response.sysjybgResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author weijing
 * @Date 2020-11-02 19:02
 * @Description
 **/
@ApiModel("实验室检验报告List")
public class SysjybgUploadRoot {
    @ApiModelProperty("实验室检验报告列表")
    private List<SysjybgUploadResp> list;

    public List<SysjybgUploadResp> getList() {
        return list;
    }

    public void setList(List<SysjybgUploadResp> list) {
        this.list = list;
    }
}
