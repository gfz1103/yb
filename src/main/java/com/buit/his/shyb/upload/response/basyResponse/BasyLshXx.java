package com.buit.his.shyb.upload.response.basyResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author weijing
 * @Date 2020-10-27 14:13
 * @Description
 **/
@ApiModel("中心流水号信息（LSHXX）循环体")
public class BasyLshXx {
    @ApiModelProperty("中心交易流水号")
    /**中心交易流水号 医保实时结算患者填写“中心流水号”**/
    private String LSHXXXHT;

    public String getLSHXXXHT() {
        return LSHXXXHT;
    }

    public void setLSHXXXHT(String LSHXXXHT) {
        this.LSHXXXHT = LSHXXXHT;
    }
}
