package com.buit.his.shyb.upload.response.cyxjResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author weijing
 * @Date 2020-11-02 16:31
 * @Description
 **/
@ApiModel("出院小结返回")
public class CyxjUploadResp {
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

    @ApiModelProperty("病区")
    /**病区 **/
    private String BQ;

    @ApiModelProperty("床号")
    /**床号 **/
    private String CH;

    @ApiModelProperty("入院时主要症状及体征")
    /**入院时主要症状及体征  该数据项在某些医院的出院小结中还包括入院时主要重要检查结果**/
    private String RYZZTZ;

    @ApiModelProperty("实验室检查及主要会诊")
    /**实验室检查及主要会诊  该数据项在某些医院的出院小结中称为“住院期间主要检查结果”**/
    private String JCHZ;

    @ApiModelProperty("住院期间特殊检查")
    /**住院期间特殊检查 **/
    private String TSJC;

    @ApiModelProperty("诊疗过程")
    /**诊疗过程  该数据项在某些医院的出院小结中称为“住院期间病程与诊疗结果”**/
    private String ZLGC;

    @ApiModelProperty("合并症")
    /**合并症 **/
    private String HBZ;

    @ApiModelProperty("出院时情况")
    /**出院时情况 该数据项在某些医院的出院小结中称为“出院时情况（症状、体征）”**/
    private String CYQK;

    @ApiModelProperty("出院医嘱")
    /**出院医嘱  该数据项在某些医院的出院小结中称为“出院后用药及建议”**/
    private String CYYZ;

    @ApiModelProperty("治疗结果")
    /**治疗结果 **/
    private String ZLJG;

    @ApiModelProperty("主治医师医保医师代码")
    /**主治医师医保医师代码 **/
    private String ZZYS_DM;

    @ApiModelProperty("主治医师姓名")
    /**主治医师姓名 **/
    private String ZZYSXM;

    @ApiModelProperty("住院医师医保医师代码")
    /**住院医师医保医师代码 **/
    private String ZYYS_DM;

    @ApiModelProperty("住院医师姓名")
    /**住院医师姓名 **/
    private String ZYYSXM;

    @ApiModelProperty("医院自填报内容1标题")
    /**医院自填报内容1标题  由于出院小结在各医院具有灵活性，可根据各自样式自行填写认为重要的内容，供展示(不用于计算分析处理)**/
    private String YYZTBBT1;

    @ApiModelProperty("医院自填报内容1")
    /**医院自填报内容1 **/
    private String YYZTB1;

    @ApiModelProperty("医院自填报内容2标题")
    /**医院自填报内容2标题 **/
    private String YYZTBBT2;

    @ApiModelProperty("医院自填报内容2")
    /**医院自填报内容2 **/
    private String YYZTB2;

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

    public String getBQ() {
        return BQ;
    }

    public void setBQ(String BQ) {
        this.BQ = BQ;
    }

    public String getCH() {
        return CH;
    }

    public void setCH(String CH) {
        this.CH = CH;
    }

    public String getRYZZTZ() {
        return RYZZTZ;
    }

    public void setRYZZTZ(String RYZZTZ) {
        this.RYZZTZ = RYZZTZ;
    }

    public String getJCHZ() {
        return JCHZ;
    }

    public void setJCHZ(String JCHZ) {
        this.JCHZ = JCHZ;
    }

    public String getTSJC() {
        return TSJC;
    }

    public void setTSJC(String TSJC) {
        this.TSJC = TSJC;
    }

    public String getZLGC() {
        return ZLGC;
    }

    public void setZLGC(String ZLGC) {
        this.ZLGC = ZLGC;
    }

    public String getHBZ() {
        return HBZ;
    }

    public void setHBZ(String HBZ) {
        this.HBZ = HBZ;
    }

    public String getCYQK() {
        return CYQK;
    }

    public void setCYQK(String CYQK) {
        this.CYQK = CYQK;
    }

    public String getCYYZ() {
        return CYYZ;
    }

    public void setCYYZ(String CYYZ) {
        this.CYYZ = CYYZ;
    }

    public String getZLJG() {
        return ZLJG;
    }

    public void setZLJG(String ZLJG) {
        this.ZLJG = ZLJG;
    }

    public String getZZYS_DM() {
        return ZZYS_DM;
    }

    public void setZZYS_DM(String ZZYS_DM) {
        this.ZZYS_DM = ZZYS_DM;
    }

    public String getZZYSXM() {
        return ZZYSXM;
    }

    public void setZZYSXM(String ZZYSXM) {
        this.ZZYSXM = ZZYSXM;
    }

    public String getZYYS_DM() {
        return ZYYS_DM;
    }

    public void setZYYS_DM(String ZYYS_DM) {
        this.ZYYS_DM = ZYYS_DM;
    }

    public String getZYYSXM() {
        return ZYYSXM;
    }

    public void setZYYSXM(String ZYYSXM) {
        this.ZYYSXM = ZYYSXM;
    }

    public String getYYZTBBT1() {
        return YYZTBBT1;
    }

    public void setYYZTBBT1(String YYZTBBT1) {
        this.YYZTBBT1 = YYZTBBT1;
    }

    public String getYYZTB1() {
        return YYZTB1;
    }

    public void setYYZTB1(String YYZTB1) {
        this.YYZTB1 = YYZTB1;
    }

    public String getYYZTBBT2() {
        return YYZTBBT2;
    }

    public void setYYZTBBT2(String YYZTBBT2) {
        this.YYZTBBT2 = YYZTBBT2;
    }

    public String getYYZTB2() {
        return YYZTB2;
    }

    public void setYYZTB2(String YYZTB2) {
        this.YYZTB2 = YYZTB2;
    }
}
