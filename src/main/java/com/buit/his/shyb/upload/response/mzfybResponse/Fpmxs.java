package com.buit.his.shyb.upload.response.mzfybResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author weijing
 * @Date 2020-11-02 18:37
 * @Description
 **/
@ApiModel("发票明细")
public class Fpmxs {
    @ApiModelProperty("发票号")
    /**发票号**/
    private String FPH;

    @ApiModelProperty("发票金额")
    /**发票金额**/
    private BigDecimal FPJE;

    @ApiModelProperty("发票日期")
    /**发票日期  格式：YYYYMMDD**/
    private String FPRQ;

    @ApiModelProperty("医疗费用明细信息")
    /**医疗费用明细信息**/
    private List<FymxXx> FYMXXX;

    public String getFPH() {
        return FPH;
    }

    public void setFPH(String FPH) {
        this.FPH = FPH;
    }

    public BigDecimal getFPJE() {
        return FPJE;
    }

    public void setFPJE(BigDecimal FPJE) {
        this.FPJE = FPJE;
    }

    public String getFPRQ() {
        return FPRQ;
    }

    public void setFPRQ(String FPRQ) {
        this.FPRQ = FPRQ;
    }

    public List<FymxXx> getFYMXXX() {
        return FYMXXX;
    }

    public void setFYMXXX(List<FymxXx> FYMXXX) {
        this.FYMXXX = FYMXXX;
    }
}
