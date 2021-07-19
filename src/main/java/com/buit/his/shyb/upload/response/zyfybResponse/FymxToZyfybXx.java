package com.buit.his.shyb.upload.response.zyfybResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author weijing
 * @Date 2020-11-02 19:32
 * @Description
 **/
@ApiModel("费用明细信息")
public class FymxToZyfybXx {
    @ApiModelProperty("费用类别")
    /**费用类别 **/
    private String FYLB;

    @ApiModelProperty("明细项目编码")
    /**明细项目编码 **/
    private String MXXMBM;

    @ApiModelProperty("明细项目名称")
    /**明细项目名称 **/
    private String MXXMMC;

    @ApiModelProperty("明细项目单位")
    /**明细项目单位 **/
    private String JJDW;

    @ApiModelProperty("明细项目数量")
    /**明细项目数量 **/
    private String JJSL;

    @ApiModelProperty("单价")
    /**单价 **/
    private String DJ;

    @ApiModelProperty("明细项目计价金额")
    /**明细项目计价金额 **/
    private String MXXMJJJE;

    @ApiModelProperty("明细项目规格")
    /**明细项目规格 **/
    private String GG;

    @ApiModelProperty("医用材料品牌/药品通用名")
    /**医用材料品牌/药品通用名 **/
    private String YYCLPP;

    @ApiModelProperty("注册证号")
    /**注册证号 **/
    private String ZCZH;

    @ApiModelProperty("使用日期")
    /**使用日期 **/
    private String SYRQ;

    @ApiModelProperty("收费、退费标志")
    /**收费、退费标志 **/
    private String SFTFBZ;

    @ApiModelProperty("是否细分明细")
    /**是否细分明细 **/
    private String SFXFMX;

    @ApiModelProperty("细分明细循环体")
    /**细分明细循环体 **/
    private List<XfmxXx> XFMX;

    public String getFYLB() {
        return FYLB;
    }

    public void setFYLB(String FYLB) {
        this.FYLB = FYLB;
    }

    public String getMXXMBM() {
        return MXXMBM;
    }

    public void setMXXMBM(String MXXMBM) {
        this.MXXMBM = MXXMBM;
    }

    public String getMXXMMC() {
        return MXXMMC;
    }

    public void setMXXMMC(String MXXMMC) {
        this.MXXMMC = MXXMMC;
    }

    public String getJJDW() {
        return JJDW;
    }

    public void setJJDW(String JJDW) {
        this.JJDW = JJDW;
    }

    public String getJJSL() {
        return JJSL;
    }

    public void setJJSL(String JJSL) {
        this.JJSL = JJSL;
    }

    public String getDJ() {
        return DJ;
    }

    public void setDJ(String DJ) {
        this.DJ = DJ;
    }

    public String getMXXMJJJE() {
        return MXXMJJJE;
    }

    public void setMXXMJJJE(String MXXMJJJE) {
        this.MXXMJJJE = MXXMJJJE;
    }

    public String getGG() {
        return GG;
    }

    public void setGG(String GG) {
        this.GG = GG;
    }

    public String getYYCLPP() {
        return YYCLPP;
    }

    public void setYYCLPP(String YYCLPP) {
        this.YYCLPP = YYCLPP;
    }

    public String getZCZH() {
        return ZCZH;
    }

    public void setZCZH(String ZCZH) {
        this.ZCZH = ZCZH;
    }

    public String getSYRQ() {
        return SYRQ;
    }

    public void setSYRQ(String SYRQ) {
        this.SYRQ = SYRQ;
    }

    public String getSFTFBZ() {
        return SFTFBZ;
    }

    public void setSFTFBZ(String SFTFBZ) {
        this.SFTFBZ = SFTFBZ;
    }

    public String getSFXFMX() {
        return SFXFMX;
    }

    public void setSFXFMX(String SFXFMX) {
        this.SFXFMX = SFXFMX;
    }

    public List<XfmxXx> getXFMX() {
        return XFMX;
    }

    public void setXFMX(List<XfmxXx> XFMX) {
        this.XFMX = XFMX;
    }
}
