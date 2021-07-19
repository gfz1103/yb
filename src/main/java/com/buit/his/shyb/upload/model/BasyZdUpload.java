package com.buit.his.shyb.upload.model;

/**
 * @Author weijing
 * @Date 2020-11-02 09:12
 * @Description
 **/
public class BasyZdUpload {
    /**医疗机构代码**/
    private String YLJGD;

    /**病案号**/
    private String BAH;

    /**序号**/
    private String ZDXH;

    /**诊断编码**/
    private String ZDBM1;

    /**诊断名称**/
    private String ZDMC1;

    /**中医诊断编码**/
    private String ZYZDBM;

    /**中医诊断名称**/
    private String ZYZDMC;

    /**入院病情**/
    private String RYBQ;

    /**出院情况**/
    private String CYQK;

    public String getYLJGD() {
        return YLJGD;
    }

    public void setYLJGD(String YLJGD) {
        this.YLJGD = YLJGD;
    }

    public String getBAH() {
        return BAH;
    }

    public void setBAH(String BAH) {
        this.BAH = BAH;
    }

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
