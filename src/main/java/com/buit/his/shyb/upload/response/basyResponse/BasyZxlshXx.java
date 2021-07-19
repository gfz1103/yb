package com.buit.his.shyb.upload.response.basyResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author weijing
 * @Date 2020-10-27 13:41
 * @Description
 **/
@ApiModel("中心流水号信息（JYLSHXX）循环体")
public class BasyZxlshXx {
    @ApiModelProperty("中心交易流水号")
    /**医保实时患者填写“中心流水号”，非医保实时患者填写16个0**/
    private String LSH;

    @ApiModelProperty("结算类型")
    /**1:普通2:高价药9:其它**/
    private String LSH_LX;

    public String getLSH() {
        return LSH;
    }

    public void setLSH(String LSH) {
        this.LSH = LSH;
    }

    public String getLSH_LX() {
        return LSH_LX;
    }

    public void setLSH_LX(String LSH_LX) {
        this.LSH_LX = LSH_LX;
    }
}
