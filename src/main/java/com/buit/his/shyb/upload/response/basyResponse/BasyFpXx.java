package com.buit.his.shyb.upload.response.basyResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author weijing
 * @Date 2020-10-27 14:09
 * @Description
 **/
@ApiModel("发票和结算信息（FPXX）循环体")
public class BasyFpXx {
    @ApiModelProperty("发票号")
    /**发票号**/
    private String FPH;

    @ApiModelProperty("中心交易流水号")
    /**中心交易流水号**/
    private List<BasyLshXx> LSHXX;

    @ApiModelProperty("发票金额")
    /**发票金额 单位：元**/
    private String FPJE;

    @ApiModelProperty("发票日期")
    /**发票日期 格式：YYYYMMDD**/
    private String FPRQ;

    public String getFPH() {
        return FPH;
    }

    public void setFPH(String FPH) {
        this.FPH = FPH;
    }

    public List<BasyLshXx> getLSHXX() {
        return LSHXX;
    }

    public void setLSHXX(List<BasyLshXx> LSHXX) {
        this.LSHXX = LSHXX;
    }

    public String getFPJE() {
        return FPJE;
    }

    public void setFPJE(String FPJE) {
        this.FPJE = FPJE;
    }

    public String getFPRQ() {
        return FPRQ;
    }

    public void setFPRQ(String FPRQ) {
        this.FPRQ = FPRQ;
    }
}
