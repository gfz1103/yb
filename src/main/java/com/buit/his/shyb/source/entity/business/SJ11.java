package com.buit.his.shyb.source.entity.business;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;
/**
 * 登记业务实体类
 * @author beijunhua
 * @date 2020/8/18 8:52
 */
public class SJ11 {
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
     * 登记类别
     */
    private String djtype;
    /**
     * 登记号
     */
    private String djno;

    /**
     * 开始日期
     */
    @JsonFormat(pattern="yyyyMMdd")
    private Date startdate;
    /**
     * 结束日期
     */
    private String enddate;
    /**
     * 诊断编码集合
     */
    private List<DiagnosisInfocy> zdnos;
    /**
     * 大病项目
     */
    private String dbxm;
    /**
     * 门诊大病登记
     * 疾病诊断分类
     */
    private String zd;
    /**
     * 大病登记委托人姓名
     */
    private String wtrxm;
    /**
     * 大病登记委托人身份证
     */
    private String wtrsfzh;
    /**
     * 大病登记原因
     */
    private String yy;
    /**
     * 大病登记描述
     */
    private String des;
    /**
     * 大病登记子类
     */
    private String dbzl;
    /**
     * 大病登记医师姓名
     */
    private String ysxm;
    /**
     * 大病登记医师工号
     */
    private String ysgh;

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

    public String getDjtype() {
        return djtype;
    }

    public void setDjtype(String djtype) {
        this.djtype = djtype;
    }

    public String getDjno() {
        return djno;
    }

    public void setDjno(String djno) {
        this.djno = djno;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public List<DiagnosisInfocy> getZdnos() {
        return zdnos;
    }

    public void setZdnos(List<DiagnosisInfocy> zdnos) {
        this.zdnos = zdnos;
    }

    public String getDbxm() {
        return dbxm;
    }

    public void setDbxm(String dbxm) {
        this.dbxm = dbxm;
    }

    public String getZd() {
        return zd;
    }

    public void setZd(String zd) {
        this.zd = zd;
    }

    public String getWtrxm() {
        return wtrxm;
    }

    public void setWtrxm(String wtrxm) {
        this.wtrxm = wtrxm;
    }

    public String getWtrsfzh() {
        return wtrsfzh;
    }

    public void setWtrsfzh(String wtrsfzh) {
        this.wtrsfzh = wtrsfzh;
    }

    public String getYy() {
        return yy;
    }

    public void setYy(String yy) {
        this.yy = yy;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDbzl() {
        return dbzl;
    }

    public void setDbzl(String dbzl) {
        this.dbzl = dbzl;
    }

    public String getYsxm() {
        return ysxm;
    }

    public void setYsxm(String ysxm) {
        this.ysxm = ysxm;
    }

    public String getYsgh() {
        return ysgh;
    }

    public void setYsgh(String ysgh) {
        this.ysgh = ysgh;
    }




}
