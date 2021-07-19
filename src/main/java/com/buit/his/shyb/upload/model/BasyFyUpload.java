package com.buit.his.shyb.upload.model;

import java.math.BigDecimal;

/**
 * @Author weijing
 * @Date 2020-11-02 09:43
 * @Description
 **/
public class BasyFyUpload {
    /**医疗机构代码**/
    private String YLJGD;

    /**病案号**/
    private String BAH;

    /**病案费用类别**/
    private String BAFYLB;

    /**金额**/
    private BigDecimal JE_0;

    public String getYLJGD() {
        return YLJGD;
    }

    public void setYLJGD(String YLJGD) {
        this.YLJGD = YLJGD;
    }

    public String getBAH() {
        return BAH;
    }

    public void setBAH(String BAH) {
        this.BAH = BAH;
    }

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
