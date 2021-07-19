package com.buit.his.shyb.upload.response.basyResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author weijing
 * @Date 2020-10-27 13:45
 * @Description
 **/
@ApiModel("诊断信息（ZDXX）循环体")
public class BasyZdXx {
    @ApiModelProperty("诊断序号")
    /**00：主要诊断、01至14为其它诊断**/
    private String ZDXH;

    @ApiModelProperty("疾病编码")
    /**疾病编码**/
    private String ZDBM1;

    @ApiModelProperty("疾病名称")
    /**疾病名称**/
    private String ZDMC1;

    @ApiModelProperty("中医诊断编码")
    /**中医诊断编码**/
    private String ZYZDBM;

    @ApiModelProperty("中医诊断名称")
    /**中医诊断名称**/
    private String ZYZDMC;

    @ApiModelProperty("入院病情")
    /**入院病情**/
    private String RYBQ;

    @ApiModelProperty("出院情况")
    /**出院情况**/
    private String CYQK;

    public String getZDXH() {
        return ZDXH;
    }

    public void setZDXH(String ZDXH) {
        this.ZDXH = ZDXH;
    }

    public String getZDBM1() {
        return ZDBM1;
    }

    public void setZDBM1(String ZDBM1) {
        this.ZDBM1 = ZDBM1;
    }

    public String getZDMC1() {
        return ZDMC1;
    }

    public void setZDMC1(String ZDMC1) {
        this.ZDMC1 = ZDMC1;
    }

    public String getZYZDBM() {
        return ZYZDBM;
    }

    public void setZYZDBM(String ZYZDBM) {
        this.ZYZDBM = ZYZDBM;
    }

    public String getZYZDMC() {
        return ZYZDMC;
    }

    public void setZYZDMC(String ZYZDMC) {
        this.ZYZDMC = ZYZDMC;
    }

    public String getRYBQ() {
        return RYBQ;
    }

    public void setRYBQ(String RYBQ) {
        this.RYBQ = RYBQ;
    }

    public String getCYQK() {
        return CYQK;
    }

    public void setCYQK(String CYQK) {
        this.CYQK = CYQK;
    }
}
