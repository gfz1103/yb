package com.buit.his.shyb.source.entity.business;

import java.util.List;

public class SN01_MZ {

    /**
     * 凭证类别
     */

    private String cardtype;
    /**
     * 凭证码
     */

    private String carddata;
    /**
     * 就诊单元号
     */
    private String jzdyh;
    /**
     * 登记号
     */
    private String djh;
    /**
     * 明细账单号
     */
    private String mxzdh;
    /**
     * 本次费用明
     * 细包的医疗
     * 费用总额
     */
    private String bcmxylfyze;
    /**
     * 结算类型标
     */
    private String jslxbz;

    /**
     *  明细项目循环体
     */
    private List<Ylxmmx> mxxms;

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

    public String getJzdyh() {
        return jzdyh;
    }

    public void setJzdyh(String jzdyh) {
        this.jzdyh = jzdyh;
    }

    public String getDjh() {
        return djh;
    }

    public void setDjh(String djh) {
        this.djh = djh;
    }

    public String getMxzdh() {
        return mxzdh;
    }

    public void setMxzdh(String mxzdh) {
        this.mxzdh = mxzdh;
    }

    public String getBcmxylfyze() {
        return bcmxylfyze;
    }

    public void setBcmxylfyze(String bcmxylfyze) {
        this.bcmxylfyze = bcmxylfyze;
    }

    public String getJslxbz() {
        return jslxbz;
    }

    public void setJslxbz(String jslxbz) {
        this.jslxbz = jslxbz;
    }

    public List<Ylxmmx> getMxxms() {
        return mxxms;
    }

    public void setMxxms(List<Ylxmmx> mxxms) {
        this.mxxms = mxxms;
    }

    @Override
    public String toString() {
        return "SN01_MZ [cardtype=" + cardtype + ", carddata=" + carddata
                + ", jzdyh=" + jzdyh + ", djh=" + djh + ", mxzdh=" + mxzdh
                + ", bcmxylfyze=" + bcmxylfyze + ", jslxbz=" + jslxbz
                + ", mxxms=" + mxxms + "]";
    }




}
