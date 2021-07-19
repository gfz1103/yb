package com.buit.his.shyb.source.entity.business;

import java.util.List;

public class Ylxmmx {

    /**
     * 费用明细单
     * 体序号
     */
    private Integer xh;
    /**
     * 处方号
     */
    private String cfh;
    /**
     * 科室编码
     */
    private String deptid;
    /**
     * 科室名称
     */
    private String ksmc;
    /**
     * 处方医师号
     */
    private String cfysh;
    /**
     * 处方医师姓名
     */
    private String cfysxm;
    /**
     * 费用类别
     */
    private String fylb;
    /**
     * 明细项目编码
     */
    private String mxxmbm;
    /**
     * 明细项目名称
     */
    private String mxxmmc;
    /**
     * 明细项目单位
     */
    private String mxxmdw;
    /**
     * 明细项目单价
     */
    private String mxxmdj;
    /**
     * 明细项目数量
     */
    private String mxxmsl;
    /**
     * 明细项目金额
     */
    private String mxxmje;
    /**
     * 医疗费用交易费用
     */
    private String mxxmjyfy;
    /**
     * 明细项目医保结算范围费用
     */
    private String mxxmybjsfwfy;
    /**
     * 医用材料品牌/药品通用名
     */
    private String yyclpp;
    /**
     * 注册证号
     */
    private String zczh;
    /**
     * 明细项目规格
     */
    private String mxxmgg;
    /**
     * 明细项目使用日期
     */
    private String mxxmsyrq;
    /**
     * 明细项目单价
     */
    private String bxbz;
    /**
     * 收费、退费标志
     */
    private String sftfbz;

    /**
     * 减负标志
     */
    private String jfbz;
    /**
     * 是否细分明细
     */
    private String sfxfmx;
    /**
     * 细分明细
     */
    private List<Ylxfmx> xfmx;

    public Integer getXh() {
        return xh;
    }

    public void setXh(Integer xh) {
        this.xh = xh;
    }

    public String getCfh() {
        return cfh;
    }
    public void setCfh(String cfh) {
        this.cfh = cfh;
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public String getKsmc() {
        return ksmc;
    }
    public void setKsmc(String ksmc) {
        this.ksmc = ksmc;
    }
    public String getCfysh() {
        return cfysh;
    }
    public void setCfysh(String cfysh) {
        this.cfysh = cfysh;
    }
    public String getCfysxm() {
        return cfysxm;
    }
    public void setCfysxm(String cfysxm) {
        this.cfysxm = cfysxm;
    }
    public String getFylb() {
        return fylb;
    }
    public void setFylb(String fylb) {
        this.fylb = fylb;
    }
    public String getMxxmbm() {
        return mxxmbm;
    }
    public void setMxxmbm(String mxxmbm) {
        this.mxxmbm = mxxmbm;
    }
    public String getMxxmmc() {
        return mxxmmc;
    }
    public void setMxxmmc(String mxxmmc) {
        this.mxxmmc = mxxmmc;
    }
    public String getMxxmdw() {
        return mxxmdw;
    }
    public void setMxxmdw(String mxxmdw) {
        this.mxxmdw = mxxmdw;
    }

    public String getMxxmdj() {
        return mxxmdj;
    }

    public void setMxxmdj(String mxxmdj) {
        this.mxxmdj = mxxmdj;
    }

    public String getMxxmsl() {
        return mxxmsl;
    }

    public void setMxxmsl(String mxxmsl) {
        this.mxxmsl = mxxmsl;
    }

    public String getMxxmje() {
        return mxxmje;
    }

    public void setMxxmje(String mxxmje) {
        this.mxxmje = mxxmje;
    }

    public String getMxxmjyfy() {
        return mxxmjyfy;
    }

    public void setMxxmjyfy(String mxxmjyfy) {
        this.mxxmjyfy = mxxmjyfy;
    }

    public String getMxxmybjsfwfy() {
        return mxxmybjsfwfy;
    }

    public void setMxxmybjsfwfy(String mxxmybjsfwfy) {
        this.mxxmybjsfwfy = mxxmybjsfwfy;
    }

    public String getYyclpp() {
        return yyclpp;
    }
    public void setYyclpp(String yyclpp) {
        this.yyclpp = yyclpp;
    }
    public String getZczh() {
        return zczh;
    }
    public void setZczh(String zczh) {
        this.zczh = zczh;
    }
    public String getMxxmgg() {
        return mxxmgg;
    }
    public void setMxxmgg(String mxxmgg) {
        this.mxxmgg = mxxmgg;
    }
    public String getMxxmsyrq() {
        return mxxmsyrq;
    }
    public void setMxxmsyrq(String mxxmsyrq) {
        this.mxxmsyrq = mxxmsyrq;
    }
    public String getBxbz() {
        return bxbz;
    }
    public void setBxbz(String bxbz) {
        this.bxbz = bxbz;
    }
    public String getSftfbz() {
        return sftfbz;
    }
    public void setSftfbz(String sftfbz) {
        this.sftfbz = sftfbz;
    }
    public String getJfbz() {
        return jfbz;
    }
    public void setJfbz(String jfbz) {
        this.jfbz = jfbz;
    }
    public String getSfxfmx() {
        return sfxfmx;
    }
    public void setSfxfmx(String sfxfmx) {
        this.sfxfmx = sfxfmx;
    }
    public List<Ylxfmx> getXfmx() {
        return xfmx;
    }
    public void setXfmx(List<Ylxfmx> xfmx) {
        this.xfmx = xfmx;
    }
    @Override
    public String toString() {
        return "Ylxmmx [xh=" + xh + ", cfh=" + cfh + ", deptid=" + deptid
                + ", ksmc=" + ksmc + ", cfysh=" + cfysh + ", cfysxm=" + cfysxm
                + ", fylb=" + fylb + ", mxxmbm=" + mxxmbm + ", mxxmmc="
                + mxxmmc + ", mxxmdw=" + mxxmdw + ", mxxmdj=" + mxxmdj
                + ", mxxmsl=" + mxxmsl + ", mxxmje=" + mxxmje + ", mxxmjyfy="
                + mxxmjyfy + ", mxxmybjsfwfy=" + mxxmybjsfwfy + ", yyclpp="
                + yyclpp + ", zczh=" + zczh + ", mxxmgg=" + mxxmgg
                + ", mxxmsyrq=" + mxxmsyrq + ", bxbz=" + bxbz + ", sftfbz="
                + sftfbz + ", jfbz=" + jfbz + ", sfxfmx=" + sfxfmx + ", xfmx="
                + xfmx + "]";
    }
}
