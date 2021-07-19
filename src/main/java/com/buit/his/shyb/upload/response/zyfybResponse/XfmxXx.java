package com.buit.his.shyb.upload.response.zyfybResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author weijing
 * @Date 2020-11-02 19:38
 * @Description
 **/
@ApiModel("细分明细信息")
public class XfmxXx {
    @ApiModelProperty("细分明细项目编码")
    /**细分明细项目编码 **/
    private String MXXMBM;

    @ApiModelProperty("明细项目子类编码")
    /**明细项目子类编码 **/
    private String MXXMZLBM;

    @ApiModelProperty("明细项目名称（子类）")
    /**明细项目名称（子类）**/
    private String MXXMMC;

    @ApiModelProperty("明细项目单位（子类）")
    /**明细项目单位（子类） **/
    private String MXXMDW;

    @ApiModelProperty("明细项目单价（子类）")
    /**明细项目单价（子类） **/
    private String MXXMDJ;

    @ApiModelProperty("明细项目数量（子类）")
    /**明细项目数量（子类） **/
    private String MXXMSL;

    @ApiModelProperty("明细项目金额（子类）")
    /**明细项目金额（子类） **/
    private String MXXMJE;

    @ApiModelProperty("医用材料品牌/药品通用名（子类）")
    /**医用材料品牌/药品通用名（子类） **/
    private String YYCLPP;

    @ApiModelProperty("注册证号（子类）")
    /**注册证号（子类） **/
    private String ZCZH;

    @ApiModelProperty("明细项目规格（子类）")
    /**明细项目规格（子类） **/
    private String MXXMGG;

    @ApiModelProperty("收费、退费标志")
    /**收费、退费标志 **/
    private String SFTFBZ;

    @ApiModelProperty("明细项目使用日期")
    /**明细项目使用日期  格式：YYYYMMDD，是指本条明细项目在本次住院结算期间的使用日期，应介于“出入院（观）结算记录库”中住院结算开始日期和住院结算结束日期之间。**/
    private String MXXMSYRQ;

    public String getMXXMBM() {
        return MXXMBM;
    }

    public void setMXXMBM(String MXXMBM) {
        this.MXXMBM = MXXMBM;
    }

    public String getMXXMZLBM() {
        return MXXMZLBM;
    }

    public void setMXXMZLBM(String MXXMZLBM) {
        this.MXXMZLBM = MXXMZLBM;
    }

    public String getMXXMMC() {
        return MXXMMC;
    }

    public void setMXXMMC(String MXXMMC) {
        this.MXXMMC = MXXMMC;
    }

    public String getMXXMDW() {
        return MXXMDW;
    }

    public void setMXXMDW(String MXXMDW) {
        this.MXXMDW = MXXMDW;
    }

    public String getMXXMDJ() {
        return MXXMDJ;
    }

    public void setMXXMDJ(String MXXMDJ) {
        this.MXXMDJ = MXXMDJ;
    }

    public String getMXXMSL() {
        return MXXMSL;
    }

    public void setMXXMSL(String MXXMSL) {
        this.MXXMSL = MXXMSL;
    }

    public String getMXXMJE() {
        return MXXMJE;
    }

    public void setMXXMJE(String MXXMJE) {
        this.MXXMJE = MXXMJE;
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

    public String getMXXMGG() {
        return MXXMGG;
    }

    public void setMXXMGG(String MXXMGG) {
        this.MXXMGG = MXXMGG;
    }

    public String getSFTFBZ() {
        return SFTFBZ;
    }

    public void setSFTFBZ(String SFTFBZ) {
        this.SFTFBZ = SFTFBZ;
    }

    public String getMXXMSYRQ() {
        return MXXMSYRQ;
    }

    public void setMXXMSYRQ(String MXXMSYRQ) {
        this.MXXMSYRQ = MXXMSYRQ;
    }
}
