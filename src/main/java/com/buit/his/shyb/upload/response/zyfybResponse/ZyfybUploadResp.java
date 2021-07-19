package com.buit.his.shyb.upload.response.zyfybResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author weijing
 * @Date 2020-11-02 19:25
 * @Description
 **/
@ApiModel("住院非医保实时患者交易明细数据")
public class ZyfybUploadResp {
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
    /**就诊流水号 **/
    private String JYH;

    @ApiModelProperty("医保医疗机构代码")
    /**医保医疗机构代码 **/
    private String JGDM;

    @ApiModelProperty("人员类型")
    /**人员类型 **/
    private String RYLX;

    @ApiModelProperty("医疗费用金额")
    /**医疗费用金额 总费用 **/
    private String YLFYJE;

    @ApiModelProperty("医疗费用发票明细")
    /**医疗费用发票明细 **/
    private List<FpmxXx> FPMXS;

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

    public String getRYLX() {
        return RYLX;
    }

    public void setRYLX(String RYLX) {
        this.RYLX = RYLX;
    }

    public String getYLFYJE() {
        return YLFYJE;
    }

    public void setYLFYJE(String YLFYJE) {
        this.YLFYJE = YLFYJE;
    }

    public List<FpmxXx> getFPMXS() {
        return FPMXS;
    }

    public void setFPMXS(List<FpmxXx> FPMXS) {
        this.FPMXS = FPMXS;
    }
}
