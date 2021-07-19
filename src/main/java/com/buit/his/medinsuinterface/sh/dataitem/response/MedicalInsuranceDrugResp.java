package com.buit.his.medinsuinterface.sh.dataitem.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.sql.Timestamp;

/**
 * @Description
 * @Author yueyu
 * @Date 2020/9/4 10:42
 */
public class MedicalInsuranceDrugResp {

    @JsonPropertyDescription("医保编码")
    private String ybbm;
    @JsonPropertyDescription("药品名称")
    private String ypmc;
    @JsonPropertyDescription("批准文号")
    private String pzwh;
    @JsonPropertyDescription("药品规格")
    private String ypgg;
    @JsonPropertyDescription("药品单位")
    private String ypdw;
    @JsonPropertyDescription("商品名称")
    private String spmc;
    @JsonPropertyDescription("启用标志:FD000038")
    private Integer qybz;
    @JsonPropertyDescription("启用日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp qyrq;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonPropertyDescription("结束日期")
    private Timestamp jsrq;

    public String getPzwh() {
        return pzwh;
    }

    public void setPzwh(String pzwh) {
        this.pzwh = pzwh;
    }

    public String getYbbm() {
        return ybbm;
    }

    public void setYbbm(String ybbm) {
        this.ybbm = ybbm;
    }

    public String getYpmc() {
        return ypmc;
    }

    public void setYpmc(String ypmc) {
        this.ypmc = ypmc;
    }

    public String getYpgg() {
        return ypgg;
    }

    public void setYpgg(String ypgg) {
        this.ypgg = ypgg;
    }

    public String getYpdw() {
        return ypdw;
    }

    public void setYpdw(String ypdw) {
        this.ypdw = ypdw;
    }

    public String getSpmc() {
        return spmc;
    }

    public void setSpmc(String spmc) {
        this.spmc = spmc;
    }

    public Integer getQybz() {
        return qybz;
    }

    public void setQybz(Integer qybz) {
        this.qybz = qybz;
    }

    public Timestamp getQyrq() {
        return qyrq;
    }

    public void setQyrq(Timestamp qyrq) {
        this.qyrq = qyrq;
    }

    public Timestamp getJsrq() {
        return jsrq;
    }

    public void setJsrq(Timestamp jsrq) {
        this.jsrq = jsrq;
    }
}
