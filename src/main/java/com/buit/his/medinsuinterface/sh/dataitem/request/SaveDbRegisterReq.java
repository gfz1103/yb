package com.buit.his.medinsuinterface.sh.dataitem.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 本地大病登记
 * beijunhua
 */
@ApiModel(value = "本地大病登记")
public class SaveDbRegisterReq {
    @ApiModelProperty(value="申请单号")
    private int sqdh;
    @ApiModelProperty(value="病人姓名")
    private String brxm;
    @ApiModelProperty(value="身份证")
    private String sfzh;
    @ApiModelProperty(value="联系地址")
    private String lxdz;
    @ApiModelProperty(value="电话号码")
    private String dhhm;
    @ApiModelProperty(value="诊疗项目")
    private String zlxm;
    @ApiModelProperty(value="诊疗项目2")
    private String zlxm2;
    @ApiModelProperty(value="医院名称")
    private String yymc;
    @ApiModelProperty(value="医生工号")
    private String ysgh;
    @ApiModelProperty(value="被委托人")
    private String bwtr;
    @ApiModelProperty(value="身份证2")
    private String sfzh2;
    @ApiModelProperty(value="电话号码2")
    private String dhhm2;
    @ApiModelProperty(value="登记日期")
    private String djrq;
    @ApiModelProperty(value="操作工号")
    private String czgh;
    @ApiModelProperty(value="状态状态")
    private String ztzt;
    @ApiModelProperty(value="医保卡号")
    private String ybkh;
    @ApiModelProperty(value="单位名称")
    private String dwmc;
    @ApiModelProperty(value="zlxm3")
    private String zlxm3;
    @ApiModelProperty(value="zlxm4")
    private String zlxm4;
    @ApiModelProperty(value="起始日期")
    private String qsrq;
    @ApiModelProperty(value="结束日期")
    private String jsrq;
    @ApiModelProperty(value="疾病诊断")
    private String jbzd;
    @ApiModelProperty(value="身份性质")
    private String sfxz;
    @ApiModelProperty(value="")
    private String gzqk;
    @ApiModelProperty(value="医保类别")
    private  String yblb;
    @ApiModelProperty(value="dd")
    private String dd;
    @ApiModelProperty(value="")
    private String modified;
    @ApiModelProperty(value="医院地址代码")
    private String hospital_code;
    @ApiModelProperty(value="")
    private String hzzt;
    @ApiModelProperty(value="经办人")
    private String jbr;
    @ApiModelProperty(value="卡内数据")
    private String knsj;

    public int getSqdh() {
        return sqdh;
    }

    public void setSqdh(int sqdh) {
        this.sqdh = sqdh;
    }

    public String getBrxm() {
        return brxm;
    }

    public void setBrxm(String brxm) {
        this.brxm = brxm;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getLxdz() {
        return lxdz;
    }

    public void setLxdz(String lxdz) {
        this.lxdz = lxdz;
    }

    public String getDhhm() {
        return dhhm;
    }

    public void setDhhm(String dhhm) {
        this.dhhm = dhhm;
    }

    public String getZlxm() {
        return zlxm;
    }

    public void setZlxm(String zlxm) {
        this.zlxm = zlxm;
    }

    public String getZlxm2() {
        return zlxm2;
    }

    public void setZlxm2(String zlxm2) {
        this.zlxm2 = zlxm2;
    }

    public String getYymc() {
        return yymc;
    }

    public void setYymc(String yymc) {
        this.yymc = yymc;
    }

    public String getYsgh() {
        return ysgh;
    }

    public void setYsgh(String ysgh) {
        this.ysgh = ysgh;
    }

    public String getBwtr() {
        return bwtr;
    }

    public void setBwtr(String bwtr) {
        this.bwtr = bwtr;
    }

    public String getSfzh2() {
        return sfzh2;
    }

    public void setSfzh2(String sfzh2) {
        this.sfzh2 = sfzh2;
    }

    public String getDhhm2() {
        return dhhm2;
    }

    public void setDhhm2(String dhhm2) {
        this.dhhm2 = dhhm2;
    }

    public String getDjrq() {
        return djrq;
    }

    public void setDjrq(String djrq) {
        this.djrq = djrq;
    }

    public String getCzgh() {
        return czgh;
    }

    public void setCzgh(String czgh) {
        this.czgh = czgh;
    }

    public String getZtzt() {
        return ztzt;
    }

    public void setZtzt(String ztzt) {
        this.ztzt = ztzt;
    }

    public String getYbkh() {
        return ybkh;
    }

    public void setYbkh(String ybkh) {
        this.ybkh = ybkh;
    }

    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    public String getZlxm3() {
        return zlxm3;
    }

    public void setZlxm3(String zlxm3) {
        this.zlxm3 = zlxm3;
    }

    public String getZlxm4() {
        return zlxm4;
    }

    public void setZlxm4(String zlxm4) {
        this.zlxm4 = zlxm4;
    }

    public String getQsrq() {
        return qsrq;
    }

    public void setQsrq(String qsrq) {
        this.qsrq = qsrq;
    }

    public String getJsrq() {
        return jsrq;
    }

    public void setJsrq(String jsrq) {
        this.jsrq = jsrq;
    }

    public String getJbzd() {
        return jbzd;
    }

    public void setJbzd(String jbzd) {
        this.jbzd = jbzd;
    }

    public String getSfxz() {
        return sfxz;
    }

    public void setSfxz(String sfxz) {
        this.sfxz = sfxz;
    }

    public String getGzqk() {
        return gzqk;
    }

    public void setGzqk(String gzqk) {
        this.gzqk = gzqk;
    }

    public String getYblb() {
        return yblb;
    }

    public void setYblb(String yblb) {
        this.yblb = yblb;
    }

    public String getDd() {
        return dd;
    }

    public void setDd(String dd) {
        this.dd = dd;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getHospital_code() {
        return hospital_code;
    }

    public void setHospital_code(String hospital_code) {
        this.hospital_code = hospital_code;
    }

    public String getHzzt() {
        return hzzt;
    }

    public void setHzzt(String hzzt) {
        this.hzzt = hzzt;
    }

    public String getJbr() {
        return jbr;
    }

    public void setJbr(String jbr) {
        this.jbr = jbr;
    }

    public String getKnsj() {
        return knsj;
    }

    public void setKnsj(String knsj) {
        this.knsj = knsj;
    }
}
