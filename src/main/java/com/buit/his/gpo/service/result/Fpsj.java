package com.buit.his.gpo.service.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author sunjx
 * @Date 2020-11-16 15:32
 * @Description
 **/
@ApiModel("发票数据")
public class Fpsj {

    @ApiModelProperty("xh")
    private String XH;

    @ApiModelProperty("发票号")
    private String FPH;

    @ApiModelProperty("发票日期")
    private String FPRQ;

    @ApiModelProperty("发票含税总金额")
    private String FPHSZJE;

    @ApiModelProperty("药企编码")
    private String YQBM;

    @ApiModelProperty("药企名称")
    private String YQMC;

    @ApiModelProperty("医院编码")
    private String YYBM;

    @ApiModelProperty("配送点编码")
    private String PSDBM;

    @ApiModelProperty("带量采购标识")
    private String DLCGBZ;

    @ApiModelProperty("发票备注")
    private String FPBZ;

    @ApiModelProperty("药库识别")
    private Integer YKSB;

    @ApiModelProperty("发票明细")
    private List<Fpmx> mxs;

    public Integer getYKSB() {
        return YKSB;
    }

    public void setYKSB(Integer YKSB) {
        this.YKSB = YKSB;
    }

    public String getXH() {
        return XH;
    }

    public void setXH(String XH) {
        this.XH = XH;
    }

    public List<Fpmx> getMxs() {
        return mxs;
    }

    public void setMxs(List<Fpmx> mxs) {
        this.mxs = mxs;
    }

    public String getFPH() {
        return FPH;
    }

    public void setFPH(String FPH) {
        this.FPH = FPH;
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
}
