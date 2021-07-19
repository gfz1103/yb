package com.buit.his.shyb.upload.response.yxyxbgResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author weijing
 * @Date 2020-11-02 19:23
 * @Description
 **/
@ApiModel("其他明细")
public class Qtmx {
    @ApiModelProperty("标题一编码")
    /**标题一编码 **/
    private String BT1BM;

    @ApiModelProperty("标题一名称")
    /**标题一名称 **/
    private String BT1MC;

    @ApiModelProperty("标题一内容")
    /**标题一内容 **/
    private String BT1NR;

    public String getBT1BM() {
        return BT1BM;
    }

    public void setBT1BM(String BT1BM) {
        this.BT1BM = BT1BM;
    }

    public String getBT1MC() {
        return BT1MC;
    }

    public void setBT1MC(String BT1MC) {
        this.BT1MC = BT1MC;
    }

    public String getBT1NR() {
        return BT1NR;
    }

    public void setBT1NR(String BT1NR) {
        this.BT1NR = BT1NR;
    }
}
