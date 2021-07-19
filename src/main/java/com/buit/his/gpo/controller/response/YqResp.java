package com.buit.his.gpo.controller.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author sunjx
 * @Date 2020-11-11 09:48
 * @Description 药企信息
 **/
@ApiModel("药企信息")
public class YqResp {

    @ApiModelProperty("统编代码")
    private String TBDM;

    @ApiModelProperty("名称")
    private String MC;

    public String getTBDM() {
        return TBDM;
    }

    public void setTBDM(String TBDM) {
        this.TBDM = TBDM;
    }

    public String getMC() {
        return MC;
    }

    public void setMC(String MC) {
        this.MC = MC;
    }
}
