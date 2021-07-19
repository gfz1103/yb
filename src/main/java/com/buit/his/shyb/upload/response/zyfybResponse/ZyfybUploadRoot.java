package com.buit.his.shyb.upload.response.zyfybResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author weijing
 * @Date 2020-11-02 19:25
 * @Description
 **/
@ApiModel("住院非医保实时患者交易明细数据List")
public class ZyfybUploadRoot {
    @ApiModelProperty("住院非医保患者交易明细列表")
    private List<ZyfybUploadResp> list;

    public List<ZyfybUploadResp> getList() {
        return list;
    }

    public void setList(List<ZyfybUploadResp> list) {
        this.list = list;
    }
}
