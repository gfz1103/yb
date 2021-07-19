package com.buit.his.shyb.upload.response.mzfybResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author weijing
 * @Date 2020-11-02 17:11
 * @Description
 **/
@ApiModel("门诊非医保实时病人交易明细数据返回")
public class MzfybUploadResp {
    @ApiModelProperty("上报年份")
    /**上报年份 年份中最后二位 **/
    private String SBNF;

    @ApiModelProperty("上报月份")
    /**上报月份**/
    private String SBYF;

    @ApiModelProperty("上报日")
    /**上报日 **/
    private String SBR;

    @ApiModelProperty("文件分类编码")
    /**文件分类编码 A：病案首页
     B：住院自费及医保非实时病人交易明细数据
     C：出院小结
     D：出院医嘱信息
     E：门诊自费及医保非实时病人交易明细数据
     F：门诊处方信息
     G：实验室检验报告
     H：医学影像检查报告**/
    private String FLBM;

    @ApiModelProperty("就诊流水号")
    /**就诊流水号 医院HIS系统的唯一编号 **/
    private String JYH;

    @ApiModelProperty("医保医疗机构代码")
    /**医保医疗机构代码 **/
    private String JGDM;

    @ApiModelProperty("门急诊标识")
    /**门急诊标识  1：门诊；2：急诊；**/
    private String JSLX;

    @ApiModelProperty("就诊卡号")
    /**就诊卡号 **/
    private String JKKH;

    @ApiModelProperty("科室编码")
    /**科室编码 **/
    private String KSBM;

    @ApiModelProperty("人员类型")
    /**人员类型 **/
    private String RYLX;

    @ApiModelProperty("医师编码")
    /**医师编码 **/
    private String YSBM;

    @ApiModelProperty("处方号")
    /**处方号 **/
    private String CFH;

    @ApiModelProperty("姓名")
    /**姓名 **/
    private String XM;

    @ApiModelProperty("性别")
    /**性别 **/
    private String XB;

    @ApiModelProperty("出生日期")
    /**出生日期 **/
    private String CSRQ;

    @ApiModelProperty("年龄")
    /**年龄 **/
    private String NL;

    @ApiModelProperty("国籍")
    /**国籍 **/
    private String GJ;

    @ApiModelProperty("证件号码")
    /**证件号码 **/
    private String SFZH;

    @ApiModelProperty("证件类型")
    /**证件类型 **/
    private String ZJLX;

    @ApiModelProperty("现住址")
    /**现住址 **/
    private String XZZ;

    @ApiModelProperty("电话号码")
    /**电话号码 **/
    private String DH;

    @ApiModelProperty("联系人姓名")
    /**联系人姓名 **/
    private String LXRXM;

    @ApiModelProperty("关系(联系人与患者关系)")
    /**关系(联系人与患者关系) **/
    private String GX;

    @ApiModelProperty("联系人地址")
    /**联系人地址**/
    private String DZ;

    @ApiModelProperty("联系人电话号码")
    /**联系人电话号码 **/
    private String DH1;

    @ApiModelProperty("医疗费用金额")
    /**医疗费用金额 **/
    private String YLFYJE;

    @ApiModelProperty("医疗费用发票明细")
    /**医疗费用发票明细 **/
    private List<Fpmxs> FPMXS;

    public String getSBNF() {
        return SBNF;
    }

    public void setSBNF(String SBNF) {
        this.SBNF = SBNF;
    }

    public String getSBYF() {
        return SBYF;
    }

    public void setSBYF(String SBYF) {
        this.SBYF = SBYF;
    }

    public String getSBR() {
        return SBR;
    }

    public void setSBR(String SBR) {
        this.SBR = SBR;
    }

    public String getFLBM() {
        return FLBM;
    }

    public void setFLBM(String FLBM) {
        this.FLBM = FLBM;
    }

    public String getJYH() {
        return JYH;
    }

    public void setJYH(String JYH) {
        this.JYH = JYH;
    }

    public String getJGDM() {
        return JGDM;
    }

    public void setJGDM(String JGDM) {
        this.JGDM = JGDM;
    }

    public String getJSLX() {
        return JSLX;
    }

    public void setJSLX(String JSLX) {
        this.JSLX = JSLX;
    }

    public String getJKKH() {
        return JKKH;
    }

    public void setJKKH(String JKKH) {
        this.JKKH = JKKH;
    }

    public String getKSBM() {
        return KSBM;
    }

    public void setKSBM(String KSBM) {
        this.KSBM = KSBM;
    }

    public String getRYLX() {
        return RYLX;
    }

    public void setRYLX(String RYLX) {
        this.RYLX = RYLX;
    }

    public String getYSBM() {
        return YSBM;
    }

    public void setYSBM(String YSBM) {
        this.YSBM = YSBM;
    }

    public String getCFH() {
        return CFH;
    }

    public void setCFH(String CFH) {
        this.CFH = CFH;
    }

    public String getXM() {
        return XM;
    }

    public void setXM(String XM) {
        this.XM = XM;
    }

    public String getXB() {
        return XB;
    }

    public void setXB(String XB) {
        this.XB = XB;
    }

    public String getCSRQ() {
        return CSRQ;
    }

    public void setCSRQ(String CSRQ) {
        this.CSRQ = CSRQ;
    }

    public String getNL() {
        return NL;
    }

    public void setNL(String NL) {
        this.NL = NL;
    }

    public String getGJ() {
        return GJ;
    }

    public void setGJ(String GJ) {
        this.GJ = GJ;
    }

    public String getSFZH() {
        return SFZH;
    }

    public void setSFZH(String SFZH) {
        this.SFZH = SFZH;
    }

    public String getZJLX() {
        return ZJLX;
    }

    public void setZJLX(String ZJLX) {
        this.ZJLX = ZJLX;
    }

    public String getXZZ() {
        return XZZ;
    }

    public void setXZZ(String XZZ) {
        this.XZZ = XZZ;
    }

    public String getDH() {
        return DH;
    }

    public void setDH(String DH) {
        this.DH = DH;
    }

    public String getLXRXM() {
        return LXRXM;
    }

    public void setLXRXM(String LXRXM) {
        this.LXRXM = LXRXM;
    }

    public String getGX() {
        return GX;
    }

    public void setGX(String GX) {
        this.GX = GX;
    }

    public String getDZ() {
        return DZ;
    }

    public void setDZ(String DZ) {
        this.DZ = DZ;
    }

    public String getDH1() {
        return DH1;
    }

    public void setDH1(String DH1) {
        this.DH1 = DH1;
    }

    public String getYLFYJE() {
        return YLFYJE;
    }

    public void setYLFYJE(String YLFYJE) {
        this.YLFYJE = YLFYJE;
    }

    public List<Fpmxs> getFPMXS() {
        return FPMXS;
    }

    public void setFPMXS(List<Fpmxs> FPMXS) {
        this.FPMXS = FPMXS;
    }
}
