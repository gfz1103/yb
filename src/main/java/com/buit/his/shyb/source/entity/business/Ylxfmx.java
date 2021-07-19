package com.buit.his.shyb.source.entity.business;

import java.math.BigDecimal;

public class Ylxfmx {
    /**
     * 费用明细单体序号
     */
    private Long xh;
    /**
     * 明细项目细分流水号
     */
    private String mxxflsh;
    /**
     * 明细项目子类编码
     */
    private String mxxmbmzl;
    /**
     * 明细项目名称（子类）
     */
    private String mxxmmczl;
    /**
     * 明细项目单位（子类）
     */
    private String mxxmdwzl;
    /**
     * 明细项目单价（子类）
     */
    private BigDecimal mxxmdjzl;
    /**
     * 明细项目数量（子类）
     */
    private BigDecimal mxxmslzl;
    /**
     * 明细项目金额（子类）
     */
    private BigDecimal mxxmjezl;
    /**
     * 医用材料品牌/药品通用名（子类）
     */
    private Long yyclppzl;
    /**
     * 注册证号（子类）
     */
    private Long zczhzl;
    /**
     * 明细项目规格（子类）
     */
    private String mxxmggzl;
    /**
     * 收费、退费标志
     */
    private String sftfbzzl;
    /**
     * 明细项目使用日期
     */
    private String mxxmsyrqzl;
    /**
     * 报销标志（子类）
     */
    private String bxbzzl;


    public Long getXh() {
        return xh;
    }

    public void setXh(Long xh) {
        this.xh = xh;
    }

    public String getMxxflsh() {
        return mxxflsh;
    }

    public void setMxxflsh(String mxxflsh) {
        this.mxxflsh = mxxflsh;
    }

    public String getMxxmbmzl() {
        return mxxmbmzl;
    }

    public void setMxxmbmzl(String mxxmbmzl) {
        this.mxxmbmzl = mxxmbmzl;
    }

    public String getMxxmmczl() {
        return mxxmmczl;
    }

    public void setMxxmmczl(String mxxmmczl) {
        this.mxxmmczl = mxxmmczl;
    }

    public String getMxxmdwzl() {
        return mxxmdwzl;
    }

    public void setMxxmdwzl(String mxxmdwzl) {
        this.mxxmdwzl = mxxmdwzl;
    }

    public BigDecimal getMxxmdjzl() {
        return mxxmdjzl;
    }

    public void setMxxmdjzl(BigDecimal mxxmdjzl) {
        this.mxxmdjzl = mxxmdjzl;
    }

    public BigDecimal getMxxmslzl() {
        return mxxmslzl;
    }

    public void setMxxmslzl(BigDecimal mxxmslzl) {
        this.mxxmslzl = mxxmslzl;
    }

    public BigDecimal getMxxmjezl() {
        return mxxmjezl;
    }

    public void setMxxmjezl(BigDecimal mxxmjezl) {
        this.mxxmjezl = mxxmjezl;
    }

    public Long getYyclppzl() {
        return yyclppzl;
    }

    public void setYyclppzl(Long yyclppzl) {
        this.yyclppzl = yyclppzl;
    }

    public Long getZczhzl() {
        return zczhzl;
    }

    public void setZczhzl(Long zczhzl) {
        this.zczhzl = zczhzl;
    }

    public String getMxxmggzl() {
        return mxxmggzl;
    }

    public void setMxxmggzl(String mxxmggzl) {
        this.mxxmggzl = mxxmggzl;
    }

    public String getSftfbzzl() {
        return sftfbzzl;
    }

    public void setSftfbzzl(String sftfbzzl) {
        this.sftfbzzl = sftfbzzl;
    }

    public String getMxxmsyrqzl() {
        return mxxmsyrqzl;
    }

    public void setMxxmsyrqzl(String mxxmsyrqzl) {
        this.mxxmsyrqzl = mxxmsyrqzl;
    }

    public String getBxbzzl() {
        return bxbzzl;
    }

    public void setBxbzzl(String bxbzzl) {
        this.bxbzzl = bxbzzl;
    }

    @Override
    public String toString() {
        return "Ylxfmx{" +
                "xh=" + xh +
                ", mxxflsh='" + mxxflsh + '\'' +
                ", mxxmbmzl='" + mxxmbmzl + '\'' +
                ", mxxmmczl='" + mxxmmczl + '\'' +
                ", mxxmdwzl='" + mxxmdwzl + '\'' +
                ", mxxmdjzl=" + mxxmdjzl +
                ", mxxmslzl=" + mxxmslzl +
                ", mxxmjezl=" + mxxmjezl +
                ", yyclppzl=" + yyclppzl +
                ", zczhzl=" + zczhzl +
                ", mxxmggzl='" + mxxmggzl + '\'' +
                ", sftfbzzl='" + sftfbzzl + '\'' +
                ", mxxmsyrqzl='" + mxxmsyrqzl + '\'' +
                ", bxbzzl='" + bxbzzl + '\'' +
                '}';
    }
}
