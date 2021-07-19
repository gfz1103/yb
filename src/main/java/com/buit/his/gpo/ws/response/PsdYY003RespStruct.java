package com.buit.his.gpo.ws.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author sunjx
 * @Date 2020-11-11 19:35
 * @Description
 **/
@ApiModel("配送单明细响应 YY003")
public class PsdYY003RespStruct {

    @ApiModelProperty("配送单号,药企内部的配送单唯一标识编号")
    private String PSDH;

    @ApiModelProperty("药企编码,药企的编码")
    private String YQBM;

    @ApiModelProperty("配送点编码,医院预先提交的医院内配送点编码")
    private String PSDBM;

    @ApiModelProperty("配送单创建日期,配送单创建日期，日期格式A")
    private String CJRQ;

    @ApiModelProperty("配送明细编号,市药事系统统编的配送明细唯一编号，进行验收确认时需要上传该编号")
    private String PSMXBH;

    @ApiModelProperty("配送单条码,配送单的唯一标识条码")
    private String PSDTM;

    @ApiModelProperty("装箱类型,详见4.1.4装箱类型")
    private String ZXLX;

    @ApiModelProperty("采购类型,详见4.1.2采购类型")
    private String CGLX;

    @ApiModelProperty("商品类型,详见4.1.5商品类型")
    private String SPLX;

    @ApiModelProperty("药品类型,详见4.1.1药品类型")
    private String YPLX;

    @ApiModelProperty("商品统编代码,统一发布的药品商品唯一标识编码")
    private String ZXSPBM;

    @ApiModelProperty("产品名,参照国家药监发布的药品/医材的“产品名”")
    private String CPM;

    @ApiModelProperty("药品剂型(西药)/炮制方法(中药),参照国家药监发布的药品的“剂型”/中药饮片的炮制方法")
    private String YPJX;

    @ApiModelProperty("规格,参照国家药监发布的药品规格或医用材料的规格型号")
    private String GG;

    @ApiModelProperty("包装材质,详见4.1.7包装单位性质")
    private String BZDWXZ;

    @ApiModelProperty("包装单位名称,该货品销售计价的基本包装单位名称")
    private String BZDWMC;

    @ApiModelProperty("用药单位名称,该货品使用时的基本单位名称，例如，片、支等")
    private String YYDWMC;

    @ApiModelProperty("包装内含数量  包装单位内所含基本使用单位的数量")
    private String BZNHSL;

    @ApiModelProperty("生产企业名称,参照国家药监发布的“生产单位”")
    private String SCQYMC;

    @ApiModelProperty("生产批号,同一箱中同一个品规的商品可包含多生产批号，批号不同将分成多条记录")
    private String SCPH;

    @ApiModelProperty("生产日期,如 20201111")
    private String SCRQ;

    @ApiModelProperty("有效日期,如 20201111")
    private String YXRQ;

    @ApiModelProperty("计划单号,医院传报到药事系统的订单所属计划单号")
    private String JHDH;

    @ApiModelProperty("销售订单号,配送企业内部的销售订单唯一编码，用于与发票的匹配关联")
    private String XSDDH;

    @ApiModelProperty("关联订单明细编号,该配送所对应的原始订单的订单明细编号")
    private String DDMXBH;

    @ApiModelProperty("顺序号,上报订单的顺序号，同一个订单内的不同明细的顺序号必须唯一")
    private String SXH;

    @ApiModelProperty("配送数量,该次配送的同一装箱且同一货品且同一生产批号按药品基础信息中包装单位名称所界定的货品配送数量")
    private String PSL;

    public String getPSDH() {
        return PSDH;
    }

    public void setPSDH(String PSDH) {
        this.PSDH = PSDH;
    }

    public String getYQBM() {
        return YQBM;
    }

    public void setYQBM(String YQBM) {
        this.YQBM = YQBM;
    }

    public String getPSDBM() {
        return PSDBM;
    }

    public void setPSDBM(String PSDBM) {
        this.PSDBM = PSDBM;
    }

    public String getCJRQ() {
        return CJRQ;
    }

    public void setCJRQ(String CJRQ) {
        this.CJRQ = CJRQ;
    }

    public String getPSMXBH() {
        return PSMXBH;
    }

    public void setPSMXBH(String PSMXBH) {
        this.PSMXBH = PSMXBH;
    }

    public String getPSDTM() {
        return PSDTM;
    }

    public void setPSDTM(String PSDTM) {
        this.PSDTM = PSDTM;
    }

    public String getZXLX() {
        return ZXLX;
    }

    public void setZXLX(String ZXLX) {
        this.ZXLX = ZXLX;
    }

    public String getCGLX() {
        return CGLX;
    }

    public void setCGLX(String CGLX) {
        this.CGLX = CGLX;
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

    public String getZXSPBM() {
        return ZXSPBM;
    }

    public void setZXSPBM(String ZXSPBM) {
        this.ZXSPBM = ZXSPBM;
    }

    public String getCPM() {
        return CPM;
    }

    public void setCPM(String CPM) {
        this.CPM = CPM;
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

    public String getYXRQ() {
        return YXRQ;
    }

    public void setYXRQ(String YXRQ) {
        this.YXRQ = YXRQ;
    }

    public String getJHDH() {
        return JHDH;
    }

    public void setJHDH(String JHDH) {
        this.JHDH = JHDH;
    }

    public String getXSDDH() {
        return XSDDH;
    }

    public void setXSDDH(String XSDDH) {
        this.XSDDH = XSDDH;
    }

    public String getDDMXBH() {
        return DDMXBH;
    }

    public void setDDMXBH(String DDMXBH) {
        this.DDMXBH = DDMXBH;
    }

    public String getSXH() {
        return SXH;
    }

    public void setSXH(String SXH) {
        this.SXH = SXH;
    }

    public String getPSL() {
        return PSL;
    }

    public void setPSL(String PSL) {
        this.PSL = PSL;
    }
}
