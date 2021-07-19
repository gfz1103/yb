package com.buit.his.shyb.upload.response.mzfybResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author weijing
 * @Date 2020-11-02 17:12
 * @Description
 **/
@ApiModel("门诊非医保实时病人交易明细数据List")
public class MzfybUploadRoot {
    @ApiModelProperty("门诊非医保实时病人交易明细数据列表")
    private List<MzfybUploadResp> list;

    public List<MzfybUploadResp> getList() {
        return list;
    }

    public void setList(List<MzfybUploadResp> list) {
        this.list = list;
    }
}
