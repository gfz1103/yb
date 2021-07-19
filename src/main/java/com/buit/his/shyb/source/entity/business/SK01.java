package com.buit.his.shyb.source.entity.business;

/**
 * 医保反交易实体类
 * @author beijunhua
 */
public class SK01 {
    /**
     * 凭证类别
     */
    private String cardtype;

    /**
     * 凭证码
     */
    private String carddata;

    /**
     * 中心流水号
     */

    private String translsh;

    /**
     * 交易费用总额
     */
    private String totalexpense;

    /**
     * 线上业务类型
     */
    private String xsywlx;

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    public String getCarddata() {
        return carddata;
    }

    public void setCarddata(String carddata) {
        this.carddata = carddata;
    }

    public String getTranslsh() {
        return translsh;
    }

    public void setTranslsh(String translsh) {
        this.translsh = translsh;
    }

    public String getTotalexpense() {
        return totalexpense;
    }

    public void setTotalexpense(String totalexpense) {
        this.totalexpense = totalexpense;
    }

    public String getXsywlx() {
        return xsywlx;
    }

    public void setXsywlx(String xsywlx) {
        this.xsywlx = xsywlx;
    }
}
