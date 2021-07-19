package com.buit.his.shyb.upload.response.yxyxbgResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author weijing
 * @Date 2020-11-02 19:11
 * @Description
 **/
@ApiModel("医学影像报告")
public class YxyxbgUploadResp {
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
    /**医保医疗机构代码 **/
    private String JGDM;

    @ApiModelProperty("报告流水号")
    /**报告流水号 **/
    private String INSTANCEUID;

    @ApiModelProperty("检查号")
    /**检查号 **/
    private String STUDYUID;

    @ApiModelProperty("就诊流水号")
    /**就诊流水号 **/
    private String JYH;

    @ApiModelProperty("中心交易流水号")
    /**中心交易流水号 **/
    private String LSH;

    @ApiModelProperty("门诊/住院标志")
    /**门诊/住院标志  **/
    private String MZZYBZ;

    @ApiModelProperty("检查项目代码（医保）")
    /**检查项目代码（医保） **/
    private String JCDMYB;

    @ApiModelProperty("申请单号")
    /**申请单号 **/
    private String SQDH;

    @ApiModelProperty("开单时间")
    /**开单时间 **/
    private String KDSJ;

    @ApiModelProperty("检查时间")
    /**检查时间 **/
    private String JCSJ;

    @ApiModelProperty("检查类型")
    /**检查类型 **/
    private String EXAMTYPE;

    @ApiModelProperty("申请科室编码")
    /**申请科室编码 **/
    private String SQKS;

    @ApiModelProperty("申请人医保医师代码")
    /**申请人医保医师代码 **/
    private String SQRGH;

    @ApiModelProperty("申请人医保医师姓名")
    /**申请人医保医师姓名 **/
    private String SQRXM;

    @ApiModelProperty("检查科室编码")
    /**检查科室编码 **/
    private String JCKS;

    @ApiModelProperty("检查医生姓名")
    /**检查医生姓名 **/
    private String JCYS;

    @ApiModelProperty("检查医生工号")
    /**检查医生工号 **/
    private String JCYSGH;

    @ApiModelProperty("报告日期")
    /**报告日期  YYYYMMDD**/
    private String BGRQ;

    @ApiModelProperty("报告时间")
    /**报告时间 **/
    private String BGSJ;

    @ApiModelProperty("报告人工号")
    /**报告人工号 **/
    private String BGRGH;

    @ApiModelProperty("报告人姓名")
    /**报告人姓名 **/
    private String BGRXM;

    @ApiModelProperty("审核人工号")
    /**审核人工号 **/
    private String SHRGH;

    @ApiModelProperty("审核人姓名")
    /**审核人姓名 **/
    private String SHRXM;

    @ApiModelProperty("检查报告")
    /**检查报告 **/
    private List<Jcbgmx> JCBGMX;

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

    public String getINSTANCEUID() {
        return INSTANCEUID;
    }

    public void setINSTANCEUID(String INSTANCEUID) {
        this.INSTANCEUID = INSTANCEUID;
    }

    public String getSTUDYUID() {
        return STUDYUID;
    }

    public void setSTUDYUID(String STUDYUID) {
        this.STUDYUID = STUDYUID;
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

    public String getMZZYBZ() {
        return MZZYBZ;
    }

    public void setMZZYBZ(String MZZYBZ) {
        this.MZZYBZ = MZZYBZ;
    }

    public String getJCDMYB() {
        return JCDMYB;
    }

    public void setJCDMYB(String JCDMYB) {
        this.JCDMYB = JCDMYB;
    }

    public String getSQDH() {
        return SQDH;
    }

    public void setSQDH(String SQDH) {
        this.SQDH = SQDH;
    }

    public String getKDSJ() {
        return KDSJ;
    }

    public void setKDSJ(String KDSJ) {
        this.KDSJ = KDSJ;
    }

    public String getJCSJ() {
        return JCSJ;
    }

    public void setJCSJ(String JCSJ) {
        this.JCSJ = JCSJ;
    }

    public String getEXAMTYPE() {
        return EXAMTYPE;
    }

    public void setEXAMTYPE(String EXAMTYPE) {
        this.EXAMTYPE = EXAMTYPE;
    }

    public String getSQKS() {
        return SQKS;
    }

    public void setSQKS(String SQKS) {
        this.SQKS = SQKS;
    }

    public String getSQRGH() {
        return SQRGH;
    }

    public void setSQRGH(String SQRGH) {
        this.SQRGH = SQRGH;
    }

    public String getSQRXM() {
        return SQRXM;
    }

    public void setSQRXM(String SQRXM) {
        this.SQRXM = SQRXM;
    }

    public String getJCKS() {
        return JCKS;
    }

    public void setJCKS(String JCKS) {
        this.JCKS = JCKS;
    }

    public String getJCYS() {
        return JCYS;
    }

    public void setJCYS(String JCYS) {
        this.JCYS = JCYS;
    }

    public String getJCYSGH() {
        return JCYSGH;
    }

    public void setJCYSGH(String JCYSGH) {
        this.JCYSGH = JCYSGH;
    }

    public String getBGRQ() {
        return BGRQ;
    }

    public void setBGRQ(String BGRQ) {
        this.BGRQ = BGRQ;
    }

    public String getBGSJ() {
        return BGSJ;
    }

    public void setBGSJ(String BGSJ) {
        this.BGSJ = BGSJ;
    }

    public String getBGRGH() {
        return BGRGH;
    }

    public void setBGRGH(String BGRGH) {
        this.BGRGH = BGRGH;
    }

    public String getBGRXM() {
        return BGRXM;
    }

    public void setBGRXM(String BGRXM) {
        this.BGRXM = BGRXM;
    }

    public String getSHRGH() {
        return SHRGH;
    }

    public void setSHRGH(String SHRGH) {
        this.SHRGH = SHRGH;
    }

    public String getSHRXM() {
        return SHRXM;
    }

    public void setSHRXM(String SHRXM) {
        this.SHRXM = SHRXM;
    }

    public List<Jcbgmx> getJCBGMX() {
        return JCBGMX;
    }

    public void setJCBGMX(List<Jcbgmx> JCBGMX) {
        this.JCBGMX = JCBGMX;
    }
}
