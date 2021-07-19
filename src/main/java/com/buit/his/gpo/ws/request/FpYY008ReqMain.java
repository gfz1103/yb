package com.buit.his.gpo.ws.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author sunjx
 * @Date 2020-11-12 15:32
 * @Description
 **/
@ApiModel("获取发票明细 main")
public class FpYY008ReqMain {

    @ApiModelProperty(value = "发票ID,必填", required = true)
    private String FPID;

    @ApiModelProperty("发票明细编号,选填 初次调用不填写，接续调用时须填写上次返回的最后一个发票明细编号")
    private String FPMXBH;

    public String getFPID() {
        return FPID;
    }

    public void setFPID(String FPID) {
        this.FPID = FPID;
    }

    public String getFPMXBH() {
        return FPMXBH;
    }

    public void setFPMXBH(String FPMXBH) {
        this.FPMXBH = FPMXBH;
    }
}
