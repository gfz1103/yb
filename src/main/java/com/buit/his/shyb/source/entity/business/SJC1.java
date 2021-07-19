package com.buit.his.shyb.source.entity.business;

/**
 * 居保门诊转院撤销
 * @author beijunhua
 */
public class SJC1 {
    /**
     * 凭证类别
     */
    private String cardtype;

    /**
     * 凭证码
     */
    private String carddata;

    /**
     * 撤消类别
     */
    private String djtype;


    /**
     * 转出医院编码
     */
    private String zcyybm;


    /**
     * 转入医院编码
     */
    private String zrjgbh;


    /**
     * 门诊转院日期开始日期
     */
    private String startdate;

    /**
     * 转出医院编码
     */
     private String zcjgdm;


    /**
     * 转入医院编码
     */
    private String zrjgdm;


    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    public String getCarddata() {
        return carddata;
    }

    public void setCarddata(String carddata) {
        this.carddata = carddata;
    }

    public String getDjtype() {
        return djtype;
    }

    public void setDjtype(String djtype) {
        this.djtype = djtype;
    }

    public String getZcyybm() {
        return zcyybm;
    }

    public void setZcyybm(String zcyybm) {
        this.zcyybm = zcyybm;
    }

    public String getZrjgbh() {
        return zrjgbh;
    }

    public void setZrjgbh(String zrjgbh) {
        this.zrjgbh = zrjgbh;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getZcjgdm() {
        return zcjgdm;
    }

    public void setZcjgdm(String zcjgdm) {
        this.zcjgdm = zcjgdm;
    }

    public String getZrjgdm() {
        return zrjgdm;
    }

    public void setZrjgdm(String zrjgdm) {
        this.zrjgdm = zrjgdm;
    }
}
