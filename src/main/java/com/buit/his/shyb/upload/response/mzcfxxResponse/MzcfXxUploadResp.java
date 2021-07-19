package com.buit.his.shyb.upload.response.mzcfxxResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author weijing
 * @Date 2020-11-02 18:45
 * @Description
 **/
@ApiModel("门诊处方信息")
public class MzcfXxUploadResp {
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

    @ApiModelProperty("医保医疗机构代码")
    /**医保医疗机构代码  医保的医院11位代码**/
    private String JGDM;

    @ApiModelProperty("处方号码")
    /**处方号码 **/
    private String CFH;

    @ApiModelProperty("处方项目明细序号")
    /**处方项目明细序号 **/
    private String CFMXH;

    @ApiModelProperty("就诊流水号")
    /**就诊流水号  医院HIS系统的唯一编号**/
    private String JYH;

    @ApiModelProperty("中心交易流水号")
    /**中心交易流水号 **/
    private String LSH;

    @ApiModelProperty("就诊科室代码")
    /**就诊科室代码 **/
    private String JZKSDM;

    @ApiModelProperty("开方医生工号")
    /**开方医生工号 **/
    private String KFYS;

    @ApiModelProperty("开方医生姓名")
    /**开方医生姓名 **/
    private String KFYSXM;

    @ApiModelProperty("开方时间")
    /**开方时间 **/
    private String KFRQ;

    @ApiModelProperty("项目明细医保编码")
    /**项目明细医保编码 **/
    private String XMBMYB;

    @ApiModelProperty("项目明细名称")
    /**项目明细名称 **/
    private String XMMC;

    @ApiModelProperty("是否药品")
    /**是否药品 1.药品 0.非药品**/
    private String CFLX;

    @ApiModelProperty("药品用法")
    /**药品用法 **/
    private String YPYF;

    @ApiModelProperty("发药数量")
    /**发药数量 **/
    private String YPSL;

    @ApiModelProperty("发药数量单位")
    /**发药数量单位 **/
    private String YPDW;

    @ApiModelProperty("处方贴数")
    /**处方贴数 **/
    private String CFTS;

    @ApiModelProperty("用药频次代码")
    /**用药频次代码 **/
    private String SYPCDM;

    @ApiModelProperty("用药频次")
    /**用药频次 **/
    private String SYPC;

    @ApiModelProperty("每次使用剂量")
    /**每次使用剂量 **/
    private String JL;

    @ApiModelProperty("每次使用剂量单位")
    /**每次使用剂量单位 **/
    private String DW;

    @ApiModelProperty("每次使用数量")
    /**每次使用数量 **/
    private String MCSL;

    @ApiModelProperty("每次使用数量单位")
    /**每次使用数量单位 **/
    private String MCDW;

    @ApiModelProperty("用药途径代码")
    /**用药途径代码 **/
    private String YF;

    @ApiModelProperty("用药天数")
    /**用药天数 **/
    private String YYTS;

    @ApiModelProperty("皮试判别")
    /**皮试判别  1.是 ；0.否**/
    private String SFPS;

    @ApiModelProperty("备注信息")
    /**备注信息 **/
    private String BZ;

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

    public String getJGDM() {
        return JGDM;
    }

    public void setJGDM(String JGDM) {
        this.JGDM = JGDM;
    }

    public String getCFH() {
        return CFH;
    }

    public void setCFH(String CFH) {
        this.CFH = CFH;
    }

    public String getCFMXH() {
        return CFMXH;
    }

    public void setCFMXH(String CFMXH) {
        this.CFMXH = CFMXH;
    }

    public String getJYH() {
        return JYH;
    }

    public void setJYH(String JYH) {
        this.JYH = JYH;
    }

    public String getLSH() {
        return LSH;
    }

    public void setLSH(String LSH) {
        this.LSH = LSH;
    }

    public String getJZKSDM() {
        return JZKSDM;
    }

    public void setJZKSDM(String JZKSDM) {
        this.JZKSDM = JZKSDM;
    }

    public String getKFYS() {
        return KFYS;
    }

    public void setKFYS(String KFYS) {
        this.KFYS = KFYS;
    }

    public String getKFYSXM() {
        return KFYSXM;
    }

    public void setKFYSXM(String KFYSXM) {
        this.KFYSXM = KFYSXM;
    }

    public String getKFRQ() {
        return KFRQ;
    }

    public void setKFRQ(String KFRQ) {
        this.KFRQ = KFRQ;
    }

    public String getXMBMYB() {
        return XMBMYB;
    }

    public void setXMBMYB(String XMBMYB) {
        this.XMBMYB = XMBMYB;
    }

    public String getXMMC() {
        return XMMC;
    }

    public void setXMMC(String XMMC) {
        this.XMMC = XMMC;
    }

    public String getCFLX() {
        return CFLX;
    }

    public void setCFLX(String CFLX) {
        this.CFLX = CFLX;
    }

    public String getYPYF() {
        return YPYF;
    }

    public void setYPYF(String YPYF) {
        this.YPYF = YPYF;
    }

    public String getYPSL() {
        return YPSL;
    }

    public void setYPSL(String YPSL) {
        this.YPSL = YPSL;
    }

    public String getYPDW() {
        return YPDW;
    }

    public void setYPDW(String YPDW) {
        this.YPDW = YPDW;
    }

    public String getCFTS() {
        return CFTS;
    }

    public void setCFTS(String CFTS) {
        this.CFTS = CFTS;
    }

    public String getSYPCDM() {
        return SYPCDM;
    }

    public void setSYPCDM(String SYPCDM) {
        this.SYPCDM = SYPCDM;
    }

    public String getSYPC() {
        return SYPC;
    }

    public void setSYPC(String SYPC) {
        this.SYPC = SYPC;
    }

    public String getJL() {
        return JL;
    }

    public void setJL(String JL) {
        this.JL = JL;
    }

    public String getDW() {
        return DW;
    }

    public void setDW(String DW) {
        this.DW = DW;
    }

    public String getMCSL() {
        return MCSL;
    }

    public void setMCSL(String MCSL) {
        this.MCSL = MCSL;
    }

    public String getMCDW() {
        return MCDW;
    }

    public void setMCDW(String MCDW) {
        this.MCDW = MCDW;
    }

    public String getYF() {
        return YF;
    }

    public void setYF(String YF) {
        this.YF = YF;
    }

    public String getYYTS() {
        return YYTS;
    }

    public void setYYTS(String YYTS) {
        this.YYTS = YYTS;
    }

    public String getSFPS() {
        return SFPS;
    }

    public void setSFPS(String SFPS) {
        this.SFPS = SFPS;
    }

    public String getBZ() {
        return BZ;
    }

    public void setBZ(String BZ) {
        this.BZ = BZ;
    }
}
