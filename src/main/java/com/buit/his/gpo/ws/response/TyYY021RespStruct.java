package com.buit.his.gpo.ws.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class TyYY021RespStruct {
    @ApiModelProperty(value="订单明细编号")
    private String DDMXBH;
    @ApiModelProperty(value="订单状态")
    private String DDMXZT;
    private String BZSM;

    public String getDDMXBH() {
        return DDMXBH;
    }

    public void setDDMXBH(String DDMXBH) {
        this.DDMXBH = DDMXBH;
    }

    public String getDDMXZT() {
        return DDMXZT;
    }

    public void setDDMXZT(String DDMXZT) {
        this.DDMXZT = DDMXZT;
    }

    public String getBZSM() {
        return BZSM;
    }

    public void setBZSM(String BZSM) {
        this.BZSM = BZSM;
    }
}
