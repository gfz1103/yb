package com.buit.his.shyb.upload.response.zyyzResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author weijing
 * @Date 2020-11-02 16:50
 * @Description
 **/
@ApiModel("住院医嘱上传返回")
public class ZyyzUploadResp {
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
    /**医保医疗机构代码 医保的医院11位代码**/
    private String JGDM;

    @ApiModelProperty("医嘱ID（序号）")
    /**医嘱ID（序号） **/
    private String YZID;

    @ApiModelProperty("就诊流水号")
    /**就诊流水号 医院HIS系统的唯一编号**/
    private String JYH;

    @ApiModelProperty("中心交易流水号")
    /**中心交易流水号 医保实时患者填写“中心流水号”，自费及医保非实时患者填写16个0**/
    private String LSH;

    @ApiModelProperty("撤销标志")
    /**撤销标志 1：正常；2：撤销该医嘱**/
    private String CXBZ;

    @ApiModelProperty("病区")
    /**病区 **/
    private String BQ;

    @ApiModelProperty("下达科室编码")
    /**下达科室编码 **/
    private String XDKSBM;

    @ApiModelProperty("医嘱下达人医保医师代码")
    /**医嘱下达人医保医师代码 **/
    private String XDRGH;

    @ApiModelProperty("医嘱下达人医保医师姓名")
    /**医嘱下达人医保医师姓名 **/
    private String XDRXM;

    @ApiModelProperty("医嘱下达时间")
    /**医嘱下达时间  格式：yyyy-MM-dd HH:mm:ss**/
    private String YZXDSJ;

    @ApiModelProperty("执行科室编码")
    /**执行科室编码 **/
    private String ZXKSBM;

    @ApiModelProperty("医嘱执行人工号")
    /**医嘱执行人工号  按医院内部确认医嘱的护士的工号填写**/
    private String ZXRGH;

    @ApiModelProperty("医嘱执行人姓名")
    /**医嘱执行人姓名 **/
    private String ZXRXM;

    @ApiModelProperty("医嘱执行时间")
    /**医嘱执行时间  格式：yyyy-MM-dd HH:mm:ss**/
    private String YZZXSJ;

    @ApiModelProperty("医嘱终止时间")
    /**医嘱终止时间  格式：yyyy-MM-dd HH:mm:ss**/
    private String YZZZSJ;

    @ApiModelProperty("医嘱说明")
    /**医嘱说明  对该医嘱的文字性说明**/
    private String YZSM;

    @ApiModelProperty("医嘱组号")
    /**医嘱组号 **/
    private String YZZH;

    @ApiModelProperty("医嘱类别")
    /**医嘱类别 编码。1：长期（在院）；2：临时（在院）；3：出院带药；9：其他**/
    private String YZLB;

    @ApiModelProperty("医嘱明细医保编码")
    /**医嘱明细医保编码  如果是收费医嘱，填写医保统一要求的收费编码**/
    private String YZBMYB;

    @ApiModelProperty("医嘱明细名称")
    /**医嘱明细名称 **/
    private String YZMXMC;

    @ApiModelProperty("是否药品")
    /**是否药品  1.药品 0.非药品**/
    private String YZLX;

    @ApiModelProperty("药品包装规格")
    /**药品包装规格 **/
    private String YPGG;

    @ApiModelProperty("药品包装规格单位")
    /**药品包装规格单位 **/
    private String BZGGDW;

    @ApiModelProperty("药品包装规格系数")
    /**药品包装规格系数  例如：12指12片/每瓶；**/
    private String BZGGXS;

    @ApiModelProperty("药品用法")
    /**药品用法 **/
    private String YPYF;

    @ApiModelProperty("用药频次代码")
    /**用药频次代码 **/
    private String YYPDDM;

    @ApiModelProperty("用药频度")
    /**用药频度 一时段内用药次数。如：一日三次。**/
    private String YYPD;

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

    @ApiModelProperty("给药途径(用法)")
    /**给药途径(用法) **/
    private String YF;

    @ApiModelProperty("用药天数")
    /**用药天数 **/
    private String YYTS;

    @ApiModelProperty("皮试判别")
    /**皮试判别 **/
    private String SFPS;

    @ApiModelProperty("发药数量")
    /**发药数量 **/
    private String YPSL;

    @ApiModelProperty("发药数量单位")
    /**发药数量单位 **/
    private String YPDW;

    @ApiModelProperty("备注")
    /**备注 **/
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

    public String getYZID() {
        return YZID;
    }

    public void setYZID(String YZID) {
        this.YZID = YZID;
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

    public String getCXBZ() {
        return CXBZ;
    }

    public void setCXBZ(String CXBZ) {
        this.CXBZ = CXBZ;
    }

    public String getBQ() {
        return BQ;
    }

    public void setBQ(String BQ) {
        this.BQ = BQ;
    }

    public String getXDKSBM() {
        return XDKSBM;
    }

    public void setXDKSBM(String XDKSBM) {
        this.XDKSBM = XDKSBM;
    }

    public String getXDRGH() {
        return XDRGH;
    }

    public void setXDRGH(String XDRGH) {
        this.XDRGH = XDRGH;
    }

    public String getXDRXM() {
        return XDRXM;
    }

    public void setXDRXM(String XDRXM) {
        this.XDRXM = XDRXM;
    }

    public String getYZXDSJ() {
        return YZXDSJ;
    }

    public void setYZXDSJ(String YZXDSJ) {
        this.YZXDSJ = YZXDSJ;
    }

    public String getZXKSBM() {
        return ZXKSBM;
    }

    public void setZXKSBM(String ZXKSBM) {
        this.ZXKSBM = ZXKSBM;
    }

    public String getZXRGH() {
        return ZXRGH;
    }

    public void setZXRGH(String ZXRGH) {
        this.ZXRGH = ZXRGH;
    }

    public String getZXRXM() {
        return ZXRXM;
    }

    public void setZXRXM(String ZXRXM) {
        this.ZXRXM = ZXRXM;
    }

    public String getYZZXSJ() {
        return YZZXSJ;
    }

    public void setYZZXSJ(String YZZXSJ) {
        this.YZZXSJ = YZZXSJ;
    }

    public String getYZZZSJ() {
        return YZZZSJ;
    }

    public void setYZZZSJ(String YZZZSJ) {
        this.YZZZSJ = YZZZSJ;
    }

    public String getYZSM() {
        return YZSM;
    }

    public void setYZSM(String YZSM) {
        this.YZSM = YZSM;
    }

    public String getYZZH() {
        return YZZH;
    }

    public void setYZZH(String YZZH) {
        this.YZZH = YZZH;
    }

    public String getYZLB() {
        return YZLB;
    }

    public void setYZLB(String YZLB) {
        this.YZLB = YZLB;
    }

    public String getYZBMYB() {
        return YZBMYB;
    }

    public void setYZBMYB(String YZBMYB) {
        this.YZBMYB = YZBMYB;
    }

    public String getYZMXMC() {
        return YZMXMC;
    }

    public void setYZMXMC(String YZMXMC) {
        this.YZMXMC = YZMXMC;
    }

    public String getYZLX() {
        return YZLX;
    }

    public void setYZLX(String YZLX) {
        this.YZLX = YZLX;
    }

    public String getYPGG() {
        return YPGG;
    }

    public void setYPGG(String YPGG) {
        this.YPGG = YPGG;
    }

    public String getBZGGDW() {
        return BZGGDW;
    }

    public void setBZGGDW(String BZGGDW) {
        this.BZGGDW = BZGGDW;
    }

    public String getBZGGXS() {
        return BZGGXS;
    }

    public void setBZGGXS(String BZGGXS) {
        this.BZGGXS = BZGGXS;
    }

    public String getYPYF() {
        return YPYF;
    }

    public void setYPYF(String YPYF) {
        this.YPYF = YPYF;
    }

    public String getYYPDDM() {
        return YYPDDM;
    }

    public void setYYPDDM(String YYPDDM) {
        this.YYPDDM = YYPDDM;
    }

    public String getYYPD() {
        return YYPD;
    }

    public void setYYPD(String YYPD) {
        this.YYPD = YYPD;
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

    public String getBZ() {
        return BZ;
    }

    public void setBZ(String BZ) {
        this.BZ = BZ;
    }
}
