package com.buit.his.gpo.ws.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author sunjx
 * @Date 2020-11-12 15:33
 * @Description
 **/
@ApiModel("发票明细struct")
public class FpYY008RespStruct {

    @ApiModelProperty("发票明细编号  市药事系统内部可唯一标识的发票明细编号")
    private String FPMXBH;

    @ApiModelProperty("商品类型 详见4.1.5商品类型")
    private String SPLX;

    @ApiModelProperty("是否冲红 标识是否是由于退货而产生的冲红发票明细；0：否，1：是")
    private String SFCH;

    @ApiModelProperty("药品统编代码")
    private String ZXSPBM;

    @ApiModelProperty("生产批号")
    private String SCPH;

    @ApiModelProperty("关联明细编号  该配送所对应的原始订单的订单明细编号或退货单号，无法关联时可以不填写")
    private String GLMXBH;

    @ApiModelProperty("销售订单号  销售公司用于和发票匹配关联的销售订单号，按配送明细中的销售订单号进行数量和费用的汇总，无法关联时可以不填写，冲红发票无需填写")
    private String XSDDH;

    @ApiModelProperty("计划单号")
    private String JHDH;

    @ApiModelProperty("顺序号 上报订单的顺序号，同一个订单内的不同明细的顺序号必须唯一")
    private String SXH;

    @ApiModelProperty("商品数量  对应该“配送单号+装箱顺序号+商品统编代码+生产批号”的配送数量")
    private String SPSL;

    @ApiModelProperty("有效日期 日期格式B")
    private String YXRQ;

    @ApiModelProperty("扣率  销售折扣率")
    private String KL;

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

    public String getSCPH() {
        return SCPH;
    }

    public void setSCPH(String SCPH) {
        this.SCPH = SCPH;
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

    public String getJHDH() {
        return JHDH;
    }

    public void setJHDH(String JHDH) {
        this.JHDH = JHDH;
    }

    public String getSXH() {
        return SXH;
    }

    public void setSXH(String SXH) {
        this.SXH = SXH;
    }

    public String getSPSL() {
        return SPSL;
    }

    public void setSPSL(String SPSL) {
        this.SPSL = SPSL;
    }

    public String getYXRQ() {
        return YXRQ;
    }

    public void setYXRQ(String YXRQ) {
        this.YXRQ = YXRQ;
    }

    public String getKL() {
        return KL;
    }

    public void setKL(String KL) {
        this.KL = KL;
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
}
