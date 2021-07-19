package com.buit.his.shyb.upload.model;

import java.math.BigDecimal;

/**
 * @Author weijing
 * @Date 2020-11-02 10:29
 * @Description
 **/
public class BasyTotalFee {
    /**医疗机构代码**/
    private String YLJGD;

    /**住院号**/
    private String ZYH;

    /**总费用**/
    private BigDecimal ZFY;

    /**自付金额**/
    private BigDecimal ZFJE;

    /**自费金额**/
    private BigDecimal ZFIJE;

    /**其他费用**/
    private BigDecimal QTZF;

    public String getYLJGD() {
        return YLJGD;
    }

    public void setYLJGD(String YLJGD) {
        this.YLJGD = YLJGD;
    }

    public String getZYH() {
        return ZYH;
    }

    public void setZYH(String ZYH) {
        this.ZYH = ZYH;
    }

    public BigDecimal getZFY() {
        return ZFY;
    }

    public void setZFY(BigDecimal ZFY) {
        this.ZFY = ZFY;
    }

    public BigDecimal getZFJE() {
        return ZFJE;
    }

    public void setZFJE(BigDecimal ZFJE) {
        this.ZFJE = ZFJE;
    }

    public BigDecimal getZFIJE() {
        return ZFIJE;
    }

    public void setZFIJE(BigDecimal ZFIJE) {
        this.ZFIJE = ZFIJE;
    }

    public BigDecimal getQTZF() {
        return QTZF;
    }

    public void setQTZF(BigDecimal QTZF) {
        this.QTZF = QTZF;
    }
}
