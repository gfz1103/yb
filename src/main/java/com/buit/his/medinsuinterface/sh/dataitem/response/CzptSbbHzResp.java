
package com.buit.his.medinsuinterface.sh.dataitem.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 类描述：城镇普通上报表<br>
 *
 * @author beijunhua
 */
@ApiModel(value = "城镇普通上报表")
public class CzptSbbHzResp extends PageQuery {
	@ApiModelProperty(value="城镇普通上报表明细")
	private List<CzptAResp> czptsbbresps;
    @ApiModelProperty(value = "日期")
    private String querydate;
    @ApiModelProperty(value = "当年账户合计")
    private String DNZHHJ;
    @ApiModelProperty(value = "附加合计")
    private String FJZZ;
    @ApiModelProperty(value = "附加合计人次")
    private String FJZZRC;
    @ApiModelProperty(value = "自负合计")
    private String ZFZZ;
    @ApiModelProperty(value = "自付")
    private String ZZZF;
    @ApiModelProperty(value = "分类合计")
    private String FLZZ;
    @ApiModelProperty(value = "财会负责人")
    private String financialOfficer;
    @ApiModelProperty(value = "制表人")
    private String maker;
    @ApiModelProperty(value = "审核人")
    private String reviewer;
    @ApiModelProperty(value = "总记录数")
    private String totalRecord;
    @ApiModelProperty(value = "自付")
    private String ZF;
    @ApiModelProperty(value = "就诊总人次")
    private String RC;
    @ApiModelProperty(value = "退休人次")
    private String TXRC;
    @ApiModelProperty(value = "附加退休人次")
    private String FJTXRC;
    @ApiModelProperty(value = "填报日期")
    private String reportDate;
    @ApiModelProperty(value = "编号")
    private String headno;
    @ApiModelProperty(value = "退休zf")
    private String TXZF;
    @ApiModelProperty(value = "历年账户合计")
    private String LNZHHJ;
    @ApiModelProperty(value = "附加报销")
    private String FJBX;
    @ApiModelProperty(value = "zz人次")
    private String ZZRC;
    @ApiModelProperty(value = "医院名称")
    private String hosName;
    @ApiModelProperty(value = "医疗机构负责人")
    private String president;

    public List<CzptAResp> getCzptsbbresps() {
        return czptsbbresps;
    }

    public void setCzptsbbresps(List<CzptAResp> czptsbbresps) {
        this.czptsbbresps = czptsbbresps;
    }

    public String getQuerydate() {
        return querydate;
    }

    public void setQuerydate(String querydate) {
        this.querydate = querydate;
    }

    public String getDNZHHJ() {
        return DNZHHJ;
    }

    public void setDNZHHJ(String DNZHHJ) {
        this.DNZHHJ = DNZHHJ;
    }

    public String getFJZZ() {
        return FJZZ;
    }

    public void setFJZZ(String FJZZ) {
        this.FJZZ = FJZZ;
    }

    public String getFJZZRC() {
        return FJZZRC;
    }

    public void setFJZZRC(String FJZZRC) {
        this.FJZZRC = FJZZRC;
    }

    public String getZFZZ() {
        return ZFZZ;
    }

    public void setZFZZ(String ZFZZ) {
        this.ZFZZ = ZFZZ;
    }

    public String getZZZF() {
        return ZZZF;
    }

    public void setZZZF(String ZZZF) {
        this.ZZZF = ZZZF;
    }

    public String getFLZZ() {
        return FLZZ;
    }

    public void setFLZZ(String FLZZ) {
        this.FLZZ = FLZZ;
    }

    public String getFinancialOfficer() {
        return financialOfficer;
    }

    public void setFinancialOfficer(String financialOfficer) {
        this.financialOfficer = financialOfficer;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(String totalRecord) {
        this.totalRecord = totalRecord;
    }

    public String getZF() {
        return ZF;
    }

    public void setZF(String ZF) {
        this.ZF = ZF;
    }

    public String getRC() {
        return RC;
    }

    public void setRC(String RC) {
        this.RC = RC;
    }

    public String getTXRC() {
        return TXRC;
    }

    public void setTXRC(String TXRC) {
        this.TXRC = TXRC;
    }

    public String getFJTXRC() {
        return FJTXRC;
    }

    public void setFJTXRC(String FJTXRC) {
        this.FJTXRC = FJTXRC;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getHeadno() {
        return headno;
    }

    public void setHeadno(String headno) {
        this.headno = headno;
    }

    public String getTXZF() {
        return TXZF;
    }

    public void setTXZF(String TXZF) {
        this.TXZF = TXZF;
    }

    public String getLNZHHJ() {
        return LNZHHJ;
    }

    public void setLNZHHJ(String LNZHHJ) {
        this.LNZHHJ = LNZHHJ;
    }

    public String getFJBX() {
        return FJBX;
    }

    public void setFJBX(String FJBX) {
        this.FJBX = FJBX;
    }

    public String getZZRC() {
        return ZZRC;
    }

    public void setZZRC(String ZZRC) {
        this.ZZRC = ZZRC;
    }

    public String getHosName() {
        return hosName;
    }

    public void setHosName(String hosName) {
        this.hosName = hosName;
    }

    public String getPresident() {
        return president;
    }

    public void setPresident(String president) {
        this.president = president;
    }
}