package com.buit.his.gpo.ws.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel
public class TychRespMain {
    @ApiModelProperty(value="根据要求查询的单据类型，返回相应的内容，\n" +
            "具体说明如下：\n" +
            "订单状态，详见4.1.8订单处理状态\n" +
            "配送单状态，详见4.1.16配送单状态\n" +
            "发票状态，详见4.1.17发票状态\n" +
            "退货单状态，详见4.1.9退货单状态\n" +
            "报价单状态，详见4.1.18报价单状态\n" +
            "询价单状态，详见4.1.10询价单状态\n" +
            "配送明细验收结果，详见4.1.3验收情况")
    private String CLZT;
    @ApiModelProperty(value="询价单最新报价")
    private String ZXBJ;
    @ApiModelProperty(value="配送单条码")
    private String PSDTM;
    @ApiModelProperty(value="商品类型 详见4.1.5商品类型")
    private String SPLX;
    @ApiModelProperty(value="详见4.1.1药品类型")
    private String YPLX;
    @ApiModelProperty(value="通用名")
    private String TYM;
    @ApiModelProperty(value="产品名")
    private String CPM;
    @ApiModelProperty(value="商品名")
    private String SPM;
    @ApiModelProperty(value="药品剂型")
    private String YPJX;
    @ApiModelProperty(value="规格")
    private String GG;
    @ApiModelProperty(value="包装单位性质")
    private String BZDWXZ;
    @ApiModelProperty(value="包装单位名称")
    private String BZDWMC;
    @ApiModelProperty(value="用药单位名称")
    private String YYDWMC;
    @ApiModelProperty(value="包装内含数量")
    private String BZNHSL;
    @ApiModelProperty(value="生产企业名称")
    private String SCQYMC;
    @ApiModelProperty(value="药品本位码")
    private String YPBWM;
    @ApiModelProperty(value="批准文号")
    private String YPPZWH;

    public String getCLZT() {
        return CLZT;
    }

    public void setCLZT(String CLZT) {
        this.CLZT = CLZT;
    }

    public String getZXBJ() {
        return ZXBJ;
    }

    public void setZXBJ(String ZXBJ) {
        this.ZXBJ = ZXBJ;
    }

    public String getPSDTM() {
        return PSDTM;
    }

    public void setPSDTM(String PSDTM) {
        this.PSDTM = PSDTM;
    }

    public String getSPLX() {
        return SPLX;
    }

    public void setSPLX(String SPLX) {
        this.SPLX = SPLX;
    }

    public String getYPLX() {
        return YPLX;
    }

    public void setYPLX(String YPLX) {
        this.YPLX = YPLX;
    }

    public String getTYM() {
        return TYM;
    }

    public void setTYM(String TYM) {
        this.TYM = TYM;
    }

    public String getCPM() {
        return CPM;
    }

    public void setCPM(String CPM) {
        this.CPM = CPM;
    }

    public String getSPM() {
        return SPM;
    }

    public void setSPM(String SPM) {
        this.SPM = SPM;
    }

    public String getYPJX() {
        return YPJX;
    }

    public void setYPJX(String YPJX) {
        this.YPJX = YPJX;
    }

    public String getGG() {
        return GG;
    }

    public void setGG(String GG) {
        this.GG = GG;
    }

    public String getBZDWXZ() {
        return BZDWXZ;
    }

    public void setBZDWXZ(String BZDWXZ) {
        this.BZDWXZ = BZDWXZ;
    }

    public String getBZDWMC() {
        return BZDWMC;
    }

    public void setBZDWMC(String BZDWMC) {
        this.BZDWMC = BZDWMC;
    }

    public String getYYDWMC() {
        return YYDWMC;
    }

    public void setYYDWMC(String YYDWMC) {
        this.YYDWMC = YYDWMC;
    }

    public String getBZNHSL() {
        return BZNHSL;
    }

    public void setBZNHSL(String BZNHSL) {
        this.BZNHSL = BZNHSL;
    }

    public String getSCQYMC() {
        return SCQYMC;
    }

    public void setSCQYMC(String SCQYMC) {
        this.SCQYMC = SCQYMC;
    }

    public String getYPBWM() {
        return YPBWM;
    }

    public void setYPBWM(String YPBWM) {
        this.YPBWM = YPBWM;
    }

    public String getYPPZWH() {
        return YPPZWH;
    }

    public void setYPPZWH(String YPPZWH) {
        this.YPPZWH = YPPZWH;
    }
}
