package com.buit.his.gpo.ws.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author sunjx
 * @Date 2020-11-11 19:57
 * @Description
 **/
@ApiModel("获取配送单数据响应 YY005")
public class PsdYY005RespStruct {

    @ApiModelProperty("配送单ID 市药事系统生成的配送单唯一标识编号")
    private String PSDID;

    @ApiModelProperty("配送单号 药企内部的配送单唯一标识编号")
    private String PSDH;

    @ApiModelProperty("配送单创建日期 配送单创建日期，日期格式A")
    private String CJRQ;

    @ApiModelProperty("配送箱数 对应配送单配送所含的物理装箱数量")
    private String PSXS;

    @ApiModelProperty("配送单状态 详见4.1.16配送单状态")
    private String PSDZT;

    public String getPSDID() {
        return PSDID;
    }

    public void setPSDID(String PSDID) {
        this.PSDID = PSDID;
    }

    public String getPSDH() {
        return PSDH;
    }

    public void setPSDH(String PSDH) {
        this.PSDH = PSDH;
    }

    public String getCJRQ() {
        return CJRQ;
    }

    public void setCJRQ(String CJRQ) {
        this.CJRQ = CJRQ;
    }

    public String getPSXS() {
        return PSXS;
    }

    public void setPSXS(String PSXS) {
        this.PSXS = PSXS;
    }

    public String getPSDZT() {
        return PSDZT;
    }

    public void setPSDZT(String PSDZT) {
        this.PSDZT = PSDZT;
    }
}
