package com.buit.his.shyb.upload.response.sysjybgResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author weijing
 * @Date 2020-11-02 18:54
 * @Description
 **/
@ApiModel("实验室检验报告")
public class SysjybgUploadResp {
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

    @ApiModelProperty("检验报告单号")
    /**检验报告单号 **/
    private String BGDH;

    @ApiModelProperty("报告日期")
    /**报告日期  YYYYMMDD**/
    private String BGRQ;

    @ApiModelProperty("就诊流水号")
    /**就诊流水号  医院HIS系统的唯一编号**/
    private String JYH;

    @ApiModelProperty("中心交易流水号")
    /**中心交易流水号  医保实时患者填写“中心流水号”，自费及医保非实时患者填写16个0**/
    private String LSH;

    @ApiModelProperty("门诊/住院标志")
    /**门诊/住院标志 1门诊，2住院**/
    private String MZZYBZ;

    @ApiModelProperty("申请人医保医师姓名")
    /**申请人医保医师姓名 **/
    private String SQRGH;

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

    @ApiModelProperty("申请科室编码")
    /**申请科室编码 **/
    private String SQKS;

    @ApiModelProperty("病区")
    /**病区 **/
    private String BQ;

    @ApiModelProperty("床号")
    /**床号 **/
    private String CH;

    @ApiModelProperty("打印日期")
    /**打印日期 **/
    private String DYRQ;

    @ApiModelProperty("申请日期")
    /**申请日期 **/
    private String SQRQ;

    @ApiModelProperty("采集日期")
    /**采集日期 **/
    private String CJRQ;

    @ApiModelProperty("检验日期")
    /**检验日期 **/
    private String JYRQ;

    @ApiModelProperty("报告备注")
    /**报告备注 **/
    private String BGBZ;

    @ApiModelProperty("标本代码")
    /**标本代码 **/
    private String BBDM;

    @ApiModelProperty("标本名称")
    /**标本名称 **/
    private String BBMC;

    @ApiModelProperty("报告单类别编码")
    /**报告单类别编码 **/
    private String BGDLBBM;

    @ApiModelProperty("报告单类别名称")
    /**报告单类别名称 **/
    private String BGDLB;

    @ApiModelProperty("检验结果")
    /**检验结果 **/
    private String JYJG;

    @ApiModelProperty("细菌结果")
    /**细菌结果 **/
    private String XJXS;

    @ApiModelProperty("药敏结果")
    /**药敏结果 **/
    private String YMXS;

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

    public String getBGDH() {
        return BGDH;
    }

    public void setBGDH(String BGDH) {
        this.BGDH = BGDH;
    }

    public String getBGRQ() {
        return BGRQ;
    }

    public void setBGRQ(String BGRQ) {
        this.BGRQ = BGRQ;
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

    public String getSQRGH() {
        return SQRGH;
    }

    public void setSQRGH(String SQRGH) {
        this.SQRGH = SQRGH;
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

    public String getSQKS() {
        return SQKS;
    }

    public void setSQKS(String SQKS) {
        this.SQKS = SQKS;
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

    public String getDYRQ() {
        return DYRQ;
    }

    public void setDYRQ(String DYRQ) {
        this.DYRQ = DYRQ;
    }

    public String getSQRQ() {
        return SQRQ;
    }

    public void setSQRQ(String SQRQ) {
        this.SQRQ = SQRQ;
    }

    public String getCJRQ() {
        return CJRQ;
    }

    public void setCJRQ(String CJRQ) {
        this.CJRQ = CJRQ;
    }

    public String getJYRQ() {
        return JYRQ;
    }

    public void setJYRQ(String JYRQ) {
        this.JYRQ = JYRQ;
    }

    public String getBGBZ() {
        return BGBZ;
    }

    public void setBGBZ(String BGBZ) {
        this.BGBZ = BGBZ;
    }

    public String getBBDM() {
        return BBDM;
    }

    public void setBBDM(String BBDM) {
        this.BBDM = BBDM;
    }

    public String getBBMC() {
        return BBMC;
    }

    public void setBBMC(String BBMC) {
        this.BBMC = BBMC;
    }

    public String getBGDLBBM() {
        return BGDLBBM;
    }

    public void setBGDLBBM(String BGDLBBM) {
        this.BGDLBBM = BGDLBBM;
    }

    public String getBGDLB() {
        return BGDLB;
    }

    public void setBGDLB(String BGDLB) {
        this.BGDLB = BGDLB;
    }

    public String getJYJG() {
        return JYJG;
    }

    public void setJYJG(String JYJG) {
        this.JYJG = JYJG;
    }

    public String getXJXS() {
        return XJXS;
    }

    public void setXJXS(String XJXS) {
        this.XJXS = XJXS;
    }

    public String getYMXS() {
        return YMXS;
    }

    public void setYMXS(String YMXS) {
        this.YMXS = YMXS;
    }
}
