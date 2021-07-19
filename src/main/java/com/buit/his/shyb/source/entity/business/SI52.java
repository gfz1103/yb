package com.buit.his.shyb.source.entity.business;

import java.util.List;
/**
 * 门诊类住院确认实体类
 * @author beijunhua
 * @date 2020/8/18 8:52
 */
public class SI52 {
    /**
     * 凭证类别
     */
    private String cardtype;
    /**
     * 凭证码
     */
    private String carddata;
    /**
     * 特殊人员标识
     */
    private String personspectag;
    /**
     * 医疗类别
     */
    private String yllb;
    /**
     * 病人类型
     */
    private String persontype;
    /**
     * 工伤认定号
     */
    private String gsrdh;
    /**
     * 出院结算标志
     */
    private String cyjsbz;
    /**
     * 结算开始日期
     */
    private String jsksrq;
    /**
     * 结算结束日期
     */
//    @JsonFormat(pattern="yyyyMMdd")
    private String jsjsrq;
    /**
     * 住院/急观天数
     */
    private String zyts;
    /**
     * 住院号/急观号
     */
    private String zyh;
    /**
     * deptid
     */
    private String deptid;
    /**
     * 诊断编码循环体开
     */
    private List<DiagnosisInfocy> zdnos;
    /**
     * 就诊单元号
     */
    private String jzdyh;
    /**
     * 线上业务类型
     */
    private String xsywlx;
    /**
     * 计算申请序号
     */
    private String jssqxh;
    /**
     * 明细账单号循环列表
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
    public String getCyjsbz() {
        return cyjsbz;
    }
    public void setCyjsbz(String cyjsbz) {
        this.cyjsbz = cyjsbz;
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
    public String getZyts() {
        return zyts;
    }
    public void setZyts(String zyts) {
        this.zyts = zyts;
    }
    public String getZyh() {
        return zyh;
    }
    public void setZyh(String zyh) {
        this.zyh = zyh;
    }
    public String getDeptid() {
        return deptid;
    }
    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }
    public List<DiagnosisInfocy> getZdnos() {
        return zdnos;
    }
    public void setZdnos(List<DiagnosisInfocy> zdnos) {
        this.zdnos = zdnos;
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
    public String getJssqxh() {
        return jssqxh;
    }
    public void setJssqxh(String jssqxh) {
        this.jssqxh = jssqxh;
    }
    public List<DetailsBillcy> getMxzdhs() {
        return mxzdhs;
    }
    public void setMxzdhs(List<DetailsBillcy> mxzdhs) {
        this.mxzdhs = mxzdhs;
    }



}
