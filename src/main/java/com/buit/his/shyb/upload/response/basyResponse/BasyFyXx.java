package com.buit.his.shyb.upload.response.basyResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @Author weijing
 * @Date 2020-10-27 14:16
 * @Description
 **/
@ApiModel("费用信息（FYXX）循环体")
public class BasyFyXx {
    @ApiModelProperty("病案费用类别")
    /**病案费用类别 病案费用类别填写说明；
     西医病案首页：总费费用（即：合计）、24个病案费用类别；
     中医病案首页：总费费用（即：合计）、26个病案费用类别；**/
    private String BAFYLB;

    @ApiModelProperty("金额")
    /**金额**/
    private BigDecimal JE_0;

    public String getBAFYLB() {
        return BAFYLB;
    }

    public void setBAFYLB(String BAFYLB) {
        this.BAFYLB = BAFYLB;
    }

    public BigDecimal getJE_0() {
        return JE_0;
    }

    public void setJE_0(BigDecimal JE_0) {
        this.JE_0 = JE_0;
    }
}
