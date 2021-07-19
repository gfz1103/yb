package com.buit.his.shyb.upload.response.zyfybResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author weijing
 * @Date 2020-11-02 19:29
 * @Description
 **/
@ApiModel("医疗费用发票明细")
public class FpmxXx {
    @ApiModelProperty("发票号")
    /**发票号 **/
    private String FPH;

    @ApiModelProperty("发票金额")
    /**发票金额 **/
    private String FPJE;

    @ApiModelProperty("发票日期")
    /**发票日期 **/
    private String FPRQ;

    @ApiModelProperty("医疗费用明细信息")
    /**医疗费用明细信息 **/
    private List<FymxToZyfybXx> FYMXXX;


    public String getFPH() {
        return FPH;
    }

    public void setFPH(String FPH) {
        this.FPH = FPH;
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

    public List<FymxToZyfybXx> getFYMXXX() {
        return FYMXXX;
    }

    public void setFYMXXX(List<FymxToZyfybXx> FYMXXX) {
        this.FYMXXX = FYMXXX;
    }
}
