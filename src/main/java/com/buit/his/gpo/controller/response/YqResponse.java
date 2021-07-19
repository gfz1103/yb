package com.buit.his.gpo.controller.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author sunjx
 * @Date 2020-11-06 15:53
 * @Description
 **/
@ApiModel("药企信息响应")
public class YqResponse {

    @ApiModelProperty("药企ID")
    private Integer yqid;

    @ApiModelProperty("药企名称")
    private String yqmc;

    @ApiModelProperty("药企统编代码")
    private String yqtbdm;

    public Integer getYqid() {
        return yqid;
    }

    public void setYqid(Integer yqid) {
        this.yqid = yqid;
    }

    public String getYqmc() {
        return yqmc;
    }

    public void setYqmc(String yqmc) {
        this.yqmc = yqmc;
    }

    public String getYqtbdm() {
        return yqtbdm;
    }

    public void setYqtbdm(String yqtbdm) {
        this.yqtbdm = yqtbdm;
    }
}
