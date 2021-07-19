package com.buit.his.shyb.source.entity.business;

import com.buit.his.shyb.source.enums.MedicalCategoryEnum;

import java.util.List;
/**
 * 门诊类收费试算实体类
 * @author beijunhua
 * @date 2020/8/18 8:52
 */
public class SI11 {
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
     * 特殊人员标识
     */
    private String personspectag;
    /**
     * 医疗类别
     */
    private MedicalCategoryEnum yllb;
    /**
     * 病人类型
     */
    private String persontype;

    /**
     * 工伤认定号
     */
    private String gsrdh;

    /**
     * 诊断编码集合
     */
    private List<DiagnosisInfocy> zdnos;

    /**
     * 大病项目代码
     */
    private String dbtype;

    /**
     * 家床结算开始日期
     */
    private String jsksrq;
    /**
     * 家床结算结束日期
     */
    private String jsjsrq;

    /**
     * 家床就诊次数
     */
    private String jzcs;

    /**
     * 就诊单元号
     */
    private String jzdyh;

    /**
     * 线上业务类型
     */
    private String xsywlx;

    /**
     * 诊断编码集合
     */
    private List<DetailsBillcy> mxzdhs;

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

    public String getPersonspectag() {
        return personspectag;
    }

    public void setPersonspectag(String personspectag) {
        this.personspectag = personspectag;
    }

    public MedicalCategoryEnum getYllb() {
        return yllb;
    }

    public void setYllb(MedicalCategoryEnum yllb) {
        this.yllb = yllb;
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

    public List<DiagnosisInfocy> getZdnos() {
        return zdnos;
    }

    public void setZdnos(List<DiagnosisInfocy> zdnos) {
        this.zdnos = zdnos;
    }

    public String getDbtype() {
        return dbtype;
    }

    public void setDbtype(String dbtype) {
        this.dbtype = dbtype;
    }

    public String getJsksrq() {
        return jsksrq;
    }

    public void setJsksrq(String jsksrq) {
        this.jsksrq = jsksrq;
    }

    public String getJsjsrq() {
        return jsjsrq;
    }

    public void setJsjsrq(String jsjsrq) {
        this.jsjsrq = jsjsrq;
    }

    public String getJzcs() {
        return jzcs;
    }

    public void setJzcs(String jzcs) {
        this.jzcs = jzcs;
    }

    public String getJzdyh() {
        return jzdyh;
    }

    public void setJzdyh(String jzdyh) {
        this.jzdyh = jzdyh;
    }

    public String getXsywlx() {
        return xsywlx;
    }

    public void setXsywlx(String xsywlx) {
        this.xsywlx = xsywlx;
    }

    public List<DetailsBillcy> getMxzdhs() {
        return mxzdhs;
    }

    public void setMxzdhs(List<DetailsBillcy> mxzdhs) {
        this.mxzdhs = mxzdhs;
    }
}
