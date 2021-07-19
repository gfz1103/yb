package com.buit.his.shyb.upload.response.basyResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author weijing
 * @Date 2020-10-27 13:55
 * @Description
 **/
@ApiModel("手术信息（SSXX）循环体")
public class BasySsXx {
    @ApiModelProperty("手术序号")
    /**01至07**/
    private String SSXH;

    @ApiModelProperty("手术及操作编码")
    /**手术及操作编码**/
    private String SSBM1;

    @ApiModelProperty("手术及操作名称")
    /**手术及操作名称**/
    private String SSJCZMC1;

    @ApiModelProperty("手术及操作日期")
    /**手术及操作日期 格式：YYYYMMDD**/
    private String SSJCZRQ;

    @ApiModelProperty("手术级别")
    /**手术级别**/
    private String SSJB;

    @ApiModelProperty("手术者医保医师代码")
    /**手术者医保医师代码**/
    private String SZ_DM;

    @ApiModelProperty("手术者姓名")
    /**手术者姓名**/
    private String SZ;

    @ApiModelProperty("Ⅰ助签名")
    /**Ⅰ助签名**/
    private String YZ;

    @ApiModelProperty("Ⅱ助签名")
    /**Ⅱ助签名**/
    private String EZ;

    @ApiModelProperty("切口类别")
    /**切口类别**/
    private String QKYLB;

    @ApiModelProperty("麻醉方式")
    /**麻醉方式**/
    private String MZFS;

    @ApiModelProperty("麻醉医保医师代码")
    /**麻醉医保医师代码**/
    private String MZYSDM;

    @ApiModelProperty("麻醉医师姓名")
    /**麻醉医师姓名**/
    private String MZYS;

    public String getSSXH() {
        return SSXH;
    }

    public void setSSXH(String SSXH) {
        this.SSXH = SSXH;
    }

    public String getSSBM1() {
        return SSBM1;
    }

    public void setSSBM1(String SSBM1) {
        this.SSBM1 = SSBM1;
    }

    public String getSSJCZMC1() {
        return SSJCZMC1;
    }

    public void setSSJCZMC1(String SSJCZMC1) {
        this.SSJCZMC1 = SSJCZMC1;
    }

    public String getSSJCZRQ() {
        return SSJCZRQ;
    }

    public void setSSJCZRQ(String SSJCZRQ) {
        this.SSJCZRQ = SSJCZRQ;
    }

    public String getSSJB() {
        return SSJB;
    }

    public void setSSJB(String SSJB) {
        this.SSJB = SSJB;
    }

    public String getSZ_DM() {
        return SZ_DM;
    }

    public void setSZ_DM(String SZ_DM) {
        this.SZ_DM = SZ_DM;
    }

    public String getSZ() {
        return SZ;
    }

    public void setSZ(String SZ) {
        this.SZ = SZ;
    }

    public String getYZ() {
        return YZ;
    }

    public void setYZ(String YZ) {
        this.YZ = YZ;
    }

    public String getEZ() {
        return EZ;
    }

    public void setEZ(String EZ) {
        this.EZ = EZ;
    }

    public String getQKYLB() {
        return QKYLB;
    }

    public void setQKYLB(String QKYLB) {
        this.QKYLB = QKYLB;
    }

    public String getMZFS() {
        return MZFS;
    }

    public void setMZFS(String MZFS) {
        this.MZFS = MZFS;
    }

    public String getMZYSDM() {
        return MZYSDM;
    }

    public void setMZYSDM(String MZYSDM) {
        this.MZYSDM = MZYSDM;
    }

    public String getMZYS() {
        return MZYS;
    }

    public void setMZYS(String MZYS) {
        this.MZYS = MZYS;
    }
}
