package com.buit.his.gpo.service.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author sunjx
 * @Date 2020-11-16 17:15
 * @Description
 **/
@ApiModel("获取发票明细 ")
public class Fpmx {

    @ApiModelProperty("是否无配送发票 0：否；1：是")
    private String SFWPSFP;

    @ApiModelProperty("无配送发票说明")
    private String WPSFPSM;

    @ApiModelProperty("发票明细编号 市药事系统内部可唯一标识的发票明细编号")
    private String FPMXBH;

    @ApiModelProperty("商品类型 详见4.1.5商品类型")
    private String SPLX;

    @ApiModelProperty("是否冲红 标识是否是由于退货而产生的冲红发票明细；0：否，1：是")
    private String SFCH;

    @ApiModelProperty("商品统编代码 统一发布的药品商品唯一标识编码")
    private String ZXSPBM;

    @ApiModelProperty("商品名称")
    private String SPMC;

    @ApiModelProperty("规格")
    private String GG;

    @ApiModelProperty("生产批号")
    private String SCPH;

    @ApiModelProperty("生产日期")
    private String SCRQ;

    @ApiModelProperty("商品数量 对应该“商品统编代码+生产批号”的配送数量")
    private String SPSL;

    @ApiModelProperty("关联明细编号")
    private String GLMXBH;

    @ApiModelProperty("销售订单号")
    private String XSDDH;

    @ApiModelProperty("顺序号")
    private String SXH;

    @ApiModelProperty("有效日期")
    private String YXRQ;

    @ApiModelProperty("无税单价")
    private String WSDJ;

    @ApiModelProperty("含税单价")
    private String HSDJ;

    @ApiModelProperty("税率")
    private String SL;

    @ApiModelProperty("税额")
    private String SE;

    @ApiModelProperty("含税金额")
    private String HSJE;

    @ApiModelProperty("批发价")
    private String PFJ;

    @ApiModelProperty("零售价")
    private String LSJ;

    @ApiModelProperty("药品批准文号")
    private String PZWH;

    public String getSFWPSFP() {
        return SFWPSFP;
    }

    public void setSFWPSFP(String SFWPSFP) {
        this.SFWPSFP = SFWPSFP;
    }

    public String getWPSFPSM() {
        return WPSFPSM;
    }

    public void setWPSFPSM(String WPSFPSM) {
        this.WPSFPSM = WPSFPSM;
    }

    public String getFPMXBH() {
        return FPMXBH;
    }

    public void setFPMXBH(String FPMXBH) {
        this.FPMXBH = FPMXBH;
    }

    public String getSPLX() {
        return SPLX;
    }

    public void setSPLX(String SPLX) {
        this.SPLX = SPLX;
    }

    public String getSFCH() {
        return SFCH;
    }

    public void setSFCH(String SFCH) {
        this.SFCH = SFCH;
    }

    public String getZXSPBM() {
        return ZXSPBM;
    }

    public void setZXSPBM(String ZXSPBM) {
        this.ZXSPBM = ZXSPBM;
    }

    public String getSPMC() {
        return SPMC;
    }

    public void setSPMC(String SPMC) {
        this.SPMC = SPMC;
    }

    public String getGG() {
        return GG;
    }

    public void setGG(String GG) {
        this.GG = GG;
    }

    public String getSCPH() {
        return SCPH;
    }

    public void setSCPH(String SCPH) {
        this.SCPH = SCPH;
    }

    public String getSCRQ() {
        return SCRQ;
    }

    public void setSCRQ(String SCRQ) {
        this.SCRQ = SCRQ;
    }

    public String getSPSL() {
        return SPSL;
    }

    public void setSPSL(String SPSL) {
        this.SPSL = SPSL;
    }

    public String getGLMXBH() {
        return GLMXBH;
    }

    public void setGLMXBH(String GLMXBH) {
        this.GLMXBH = GLMXBH;
    }

    public String getXSDDH() {
        return XSDDH;
    }

    public void setXSDDH(String XSDDH) {
        this.XSDDH = XSDDH;
    }

    public String getSXH() {
        return SXH;
    }

    public void setSXH(String SXH) {
        this.SXH = SXH;
    }

    public String getYXRQ() {
        return YXRQ;
    }

    public void setYXRQ(String YXRQ) {
        this.YXRQ = YXRQ;
    }

    public String getWSDJ() {
        return WSDJ;
    }

    public void setWSDJ(String WSDJ) {
        this.WSDJ = WSDJ;
    }

    public String getHSDJ() {
        return HSDJ;
    }

    public void setHSDJ(String HSDJ) {
        this.HSDJ = HSDJ;
    }

    public String getSL() {
        return SL;
    }

    public void setSL(String SL) {
        this.SL = SL;
    }

    public String getSE() {
        return SE;
    }

    public void setSE(String SE) {
        this.SE = SE;
    }

    public String getHSJE() {
        return HSJE;
    }

    public void setHSJE(String HSJE) {
        this.HSJE = HSJE;
    }

    public String getPFJ() {
        return PFJ;
    }

    public void setPFJ(String PFJ) {
        this.PFJ = PFJ;
    }

    public String getLSJ() {
        return LSJ;
    }

    public void setLSJ(String LSJ) {
        this.LSJ = LSJ;
    }

    public String getPZWH() {
        return PZWH;
    }

    public void setPZWH(String PZWH) {
        this.PZWH = PZWH;
    }
}
