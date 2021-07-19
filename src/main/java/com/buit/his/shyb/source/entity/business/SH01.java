package com.buit.his.shyb.source.entity.business;

/**
 * 门诊挂号预结算
 * @author beijunhua
 */
public class SH01 {
    /**
     * 凭证类别
     */
    private String cardtype;

    /**
     * 凭证码
     */
    private String carddata;

    /**
     * 科室编码
     */
    private String deptid;

    /**
     * 诊疗项目代码
     */
    private String zlxmdm;

    /**
     * 特殊人员标识
     */
    private String personspectag;

    /**
     * 医疗类别
     */
    private String yllb;

    /**
     * 大病项目代码
     */
    private String dbtype;

    /**
     * 病人类型
     */
    private String persontype;

    /**
     * 工伤认定号
     */
    private String gsrdh;

    /**
     * 交易费用总额
     */
    private String totalexpense;

    /**
     * 医保结算范围费用总额
     */
    private String ybjsfwfyze;

    /**
     * 诊疗费
     */
    private String zhenlf;

    /**
     *门（急）诊诊疗费自费
     */
    private String ghf;

    /**
     * 非医保结算范围费用总额
     */
    private String fybjsfwfyze;

    /**
     * 享受社区减免标志
     */
    private String jmbz;

    /**
     * 线上业务类型
     */
    private String xsywlx;

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

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public String getZlxmdm() {
        return zlxmdm;
    }

    public void setZlxmdm(String zlxmdm) {
        this.zlxmdm = zlxmdm;
    }

    public String getPersonspectag() {
        return personspectag;
    }

    public void setPersonspectag(String personspectag) {
        this.personspectag = personspectag;
    }

    public String getYllb() {
        return yllb;
    }

    public void setYllb(String yllb) {
        this.yllb = yllb;
    }

    public String getDbtype() {
        return dbtype;
    }

    public void setDbtype(String dbtype) {
        this.dbtype = dbtype;
    }

    public String getPersontype() {
        return persontype;
    }

    public void setPersontype(String persontype) {
        this.persontype = persontype;
    }

    public String getGsrdh() {
        return gsrdh;
    }

    public void setGsrdh(String gsrdh) {
        this.gsrdh = gsrdh;
    }

    public String getTotalexpense() {
        return totalexpense;
    }

    public void setTotalexpense(String totalexpense) {
        this.totalexpense = totalexpense;
    }

    public String getYbjsfwfyze() {
        return ybjsfwfyze;
    }

    public void setYbjsfwfyze(String ybjsfwfyze) {
        this.ybjsfwfyze = ybjsfwfyze;
    }

    public String getZhenlf() {
        return zhenlf;
    }

    public void setZhenlf(String zhenlf) {
        this.zhenlf = zhenlf;
    }

    public String getGhf() {
        return ghf;
    }

    public void setGhf(String ghf) {
        this.ghf = ghf;
    }

    public String getFybjsfwfyze() {
        return fybjsfwfyze;
    }

    public void setFybjsfwfyze(String fybjsfwfyze) {
        this.fybjsfwfyze = fybjsfwfyze;
    }

    public String getJmbz() {
        return jmbz;
    }

    public void setJmbz(String jmbz) {
        this.jmbz = jmbz;
    }

    public String getXsywlx() {
        return xsywlx;
    }

    public void setXsywlx(String xsywlx) {
        this.xsywlx = xsywlx;
    }
}
