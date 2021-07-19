package com.buit.his.gpo.ws.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author sunjx
 * @Date 2020-11-12 15:06
 * @Description
 **/
@ApiModel("查询发票数据struct")
public class FpYY007RespStruct {
    
    @ApiModelProperty("发票ID 市药事系统生成的发票唯一标识编号")
    private String FPID;
    
    @ApiModelProperty("发票号 药企内可唯一标识该发票的编号")
    private String FPH;
    
    @ApiModelProperty("药企编码 市药事系统对药企的统编代码，若不填写则查询所有符合条件的发票信息")
    private String YQBM;
    
    @ApiModelProperty("药企名称 药企编码所对应的药企名称")
    private String YQMC;
    
    @ApiModelProperty("发票日期 发票开具日期，日期格式B")
    private String FPRQ;
    
    @ApiModelProperty("发票含税总金额")
    private String FPHSZJE;
    
    @ApiModelProperty("医院编码 市药事系统对医院的统编代码")
    private String YYBM;
    
    @ApiModelProperty("配送点编码 医院预先提交的医院内配送点编码")
    private String PSDBM;
    
    @ApiModelProperty("带量采购标志 标识是否带量采购的发票。0：否，1：是")
    private String DLCGBZ;
    
    @ApiModelProperty("发票备注 备注说明")
    private String FPBZ;
    
    @ApiModelProperty("是否无配送发票 0：否；1：是 选择“是”的必须与配送名字一一关联，选择“否”的则无需与配送明细关联")
    private String SFWPSFP;
    
    @ApiModelProperty("无配送发票说明 无配送的发票必须填写说明原因")
    private String WPSFPSM;
    
    @ApiModelProperty("发票状态 详见4.1.17发票状态")
    private String FPZT;

    public String getFPID() {
        return FPID;
    }

    public void setFPID(String FPID) {
        this.FPID = FPID;
    }

    public String getFPH() {
        return FPH;
    }

    public void setFPH(String FPH) {
        this.FPH = FPH;
    }

    public String getYQBM() {
        return YQBM;
    }

    public void setYQBM(String YQBM) {
        this.YQBM = YQBM;
    }

    public String getYQMC() {
        return YQMC;
    }

    public void setYQMC(String YQMC) {
        this.YQMC = YQMC;
    }

    public String getFPRQ() {
        return FPRQ;
    }

    public void setFPRQ(String FPRQ) {
        this.FPRQ = FPRQ;
    }

    public String getFPHSZJE() {
        return FPHSZJE;
    }

    public void setFPHSZJE(String FPHSZJE) {
        this.FPHSZJE = FPHSZJE;
    }

    public String getYYBM() {
        return YYBM;
    }

    public void setYYBM(String YYBM) {
        this.YYBM = YYBM;
    }

    public String getPSDBM() {
        return PSDBM;
    }

    public void setPSDBM(String PSDBM) {
        this.PSDBM = PSDBM;
    }

    public String getDLCGBZ() {
        return DLCGBZ;
    }

    public void setDLCGBZ(String DLCGBZ) {
        this.DLCGBZ = DLCGBZ;
    }

    public String getFPBZ() {
        return FPBZ;
    }

    public void setFPBZ(String FPBZ) {
        this.FPBZ = FPBZ;
    }

    public String getSFWPSFP() {
        return SFWPSFP;
    }

    public void setSFWPSFP(String SFWPSFP) {
        this.SFWPSFP = SFWPSFP;
    }

    public String getWPSFPSM() {
        return WPSFPSM;
    }

    public void setWPSFPSM(String WPSFPSM) {
        this.WPSFPSM = WPSFPSM;
    }

    public String getFPZT() {
        return FPZT;
    }

    public void setFPZT(String FPZT) {
        this.FPZT = FPZT;
    }
}
