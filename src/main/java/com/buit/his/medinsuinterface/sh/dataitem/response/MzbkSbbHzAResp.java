
package com.buit.his.medinsuinterface.sh.dataitem.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 类描述：民政帮困汇总A上报表<br>
 *
 * @author beijunhua
 */
@ApiModel(value = "民政帮困汇总A上报表")
public class MzbkSbbHzAResp extends PageQuery {
	@ApiModelProperty(value="民政帮困A上报表明细")
	private List<MzbkAResp> mzbkasbbresps;
    @ApiModelProperty(value = "总记录数")
    private String totalRecord;
    @ApiModelProperty(value = "就诊总人次")
    private String JZNUM_ZJ;
    @ApiModelProperty(value = "费用总计")
    private String TOTFY_ZJ;
    @ApiModelProperty(value = "分类自负在职")
    private String FLZZ_ZJ;
    @ApiModelProperty(value = "分类自负退休")
    private String FLTX_ZJ;
    @ApiModelProperty(value = "自负在职")
    private String ZFZZ_ZJ;
    @ApiModelProperty(value = "自负退休")
    private String ZFTX_ZJ;
    @ApiModelProperty(value = "当年在职")
    private String DNZHZZ_ZJ;
    @ApiModelProperty(value = "当年退休")
    private String DNZHTX_ZJ;
    @ApiModelProperty(value = "历年在职")
    private String LNZHZZ_ZJ;
    @ApiModelProperty(value = "历年退休")
    private String LNZHTX_ZJ;
    @ApiModelProperty(value = "附加在职")
    private String FJZZ_ZJ;
    @ApiModelProperty(value = "附加退休")
    private String FJTX_ZJ;

    @ApiModelProperty(value = "分类自负合计")
    private String FLZZ;
    @ApiModelProperty(value = "自负合计")
    private String ZFZZ;
    @ApiModelProperty(value = "当年合计")
    private String DNZHHJ;
    @ApiModelProperty(value = "历年合计")
    private String LNZHHJ;
    @ApiModelProperty(value = "附加合计")
    private String FJZZ;
    @ApiModelProperty(value = "自付合计")
    private String ZF;
    @ApiModelProperty(value = "自付合计-在职")
    private String ZZZF;
    @ApiModelProperty(value = "自付合计-退休")
    private String TXZF;

    @ApiModelProperty(value = "就诊人次")
    private String RC;
    @ApiModelProperty(value = "就诊人次-在职")
    private String ZZRC;
    @ApiModelProperty(value = "就诊人次-退休")
    private String TXRC;
    @ApiModelProperty(value = "附加支付人次合计")
    private String FJBX;
    @ApiModelProperty(value = "附加支付人次-在职")
    private String FJZZRC;
    @ApiModelProperty(value = "附加支付人次-退休")
    private String FJTXRC;

    @ApiModelProperty(value = "医院名称")
    private String hosName;
    @ApiModelProperty(value = "日期")
    private String querydate;
    @ApiModelProperty(value = "编号")
    private String headno;
    @ApiModelProperty(value = "填报日期")
    private String reportDate;
    @ApiModelProperty(value = "医疗机构负责人")
    private String president;
    @ApiModelProperty(value = "财会负责人")
    private String financialOfficer;
    @ApiModelProperty(value = "制表人")
    private String maker;
    @ApiModelProperty(value = "审核人")
    private String reviewer;


    public List<MzbkAResp> getMzbkasbbresps() {
        return mzbkasbbresps;
    }

    public void setMzbkasbbresps(List<MzbkAResp> mzbkasbbresps) {
        this.mzbkasbbresps = mzbkasbbresps;
    }

    public String getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(String totalRecord) {
        this.totalRecord = totalRecord;
    }

    public String getJZNUM_ZJ() {
        return JZNUM_ZJ;
    }

    public void setJZNUM_ZJ(String JZNUM_ZJ) {
        this.JZNUM_ZJ = JZNUM_ZJ;
    }

    public String getTOTFY_ZJ() {
        return TOTFY_ZJ;
    }

    public void setTOTFY_ZJ(String TOTFY_ZJ) {
        this.TOTFY_ZJ = TOTFY_ZJ;
    }

    public String getFLZZ_ZJ() {
        return FLZZ_ZJ;
    }

    public void setFLZZ_ZJ(String FLZZ_ZJ) {
        this.FLZZ_ZJ = FLZZ_ZJ;
    }

    public String getFLTX_ZJ() {
        return FLTX_ZJ;
    }

    public void setFLTX_ZJ(String FLTX_ZJ) {
        this.FLTX_ZJ = FLTX_ZJ;
    }

    public String getZFZZ_ZJ() {
        return ZFZZ_ZJ;
    }

    public void setZFZZ_ZJ(String ZFZZ_ZJ) {
        this.ZFZZ_ZJ = ZFZZ_ZJ;
    }

    public String getZFTX_ZJ() {
        return ZFTX_ZJ;
    }

    public void setZFTX_ZJ(String ZFTX_ZJ) {
        this.ZFTX_ZJ = ZFTX_ZJ;
    }

    public String getDNZHZZ_ZJ() {
        return DNZHZZ_ZJ;
    }

    public void setDNZHZZ_ZJ(String DNZHZZ_ZJ) {
        this.DNZHZZ_ZJ = DNZHZZ_ZJ;
    }

    public String getDNZHTX_ZJ() {
        return DNZHTX_ZJ;
    }

    public void setDNZHTX_ZJ(String DNZHTX_ZJ) {
        this.DNZHTX_ZJ = DNZHTX_ZJ;
    }

    public String getLNZHZZ_ZJ() {
        return LNZHZZ_ZJ;
    }

    public void setLNZHZZ_ZJ(String LNZHZZ_ZJ) {
        this.LNZHZZ_ZJ = LNZHZZ_ZJ;
    }

    public String getLNZHTX_ZJ() {
        return LNZHTX_ZJ;
    }

    public void setLNZHTX_ZJ(String LNZHTX_ZJ) {
        this.LNZHTX_ZJ = LNZHTX_ZJ;
    }

    public String getFJZZ_ZJ() {
        return FJZZ_ZJ;
    }

    public void setFJZZ_ZJ(String FJZZ_ZJ) {
        this.FJZZ_ZJ = FJZZ_ZJ;
    }

    public String getFJTX_ZJ() {
        return FJTX_ZJ;
    }

    public void setFJTX_ZJ(String FJTX_ZJ) {
        this.FJTX_ZJ = FJTX_ZJ;
    }

    public String getFLZZ() {
        return FLZZ;
    }

    public void setFLZZ(String FLZZ) {
        this.FLZZ = FLZZ;
    }

    public String getZFZZ() {
        return ZFZZ;
    }

    public void setZFZZ(String ZFZZ) {
        this.ZFZZ = ZFZZ;
    }

    public String getDNZHHJ() {
        return DNZHHJ;
    }

    public void setDNZHHJ(String DNZHHJ) {
        this.DNZHHJ = DNZHHJ;
    }

    public String getLNZHHJ() {
        return LNZHHJ;
    }

    public void setLNZHHJ(String LNZHHJ) {
        this.LNZHHJ = LNZHHJ;
    }

    public String getFJZZ() {
        return FJZZ;
    }

    public void setFJZZ(String FJZZ) {
        this.FJZZ = FJZZ;
    }

    public String getZF() {
        return ZF;
    }

    public void setZF(String ZF) {
        this.ZF = ZF;
    }

    public String getZZZF() {
        return ZZZF;
    }

    public void setZZZF(String ZZZF) {
        this.ZZZF = ZZZF;
    }

    public String getTXZF() {
        return TXZF;
    }

    public void setTXZF(String TXZF) {
        this.TXZF = TXZF;
    }

    public String getRC() {
        return RC;
    }

    public void setRC(String RC) {
        this.RC = RC;
    }

    public String getZZRC() {
        return ZZRC;
    }

    public void setZZRC(String ZZRC) {
        this.ZZRC = ZZRC;
    }

    public String getTXRC() {
        return TXRC;
    }

    public void setTXRC(String TXRC) {
        this.TXRC = TXRC;
    }

    public String getFJBX() {
        return FJBX;
    }

    public void setFJBX(String FJBX) {
        this.FJBX = FJBX;
    }

    public String getFJZZRC() {
        return FJZZRC;
    }

    public void setFJZZRC(String FJZZRC) {
        this.FJZZRC = FJZZRC;
    }

    public String getFJTXRC() {
        return FJTXRC;
    }

    public void setFJTXRC(String FJTXRC) {
        this.FJTXRC = FJTXRC;
    }

    public String getHosName() {
        return hosName;
    }

    public void setHosName(String hosName) {
        this.hosName = hosName;
    }

    public String getQuerydate() {
        return querydate;
    }

    public void setQuerydate(String querydate) {
        this.querydate = querydate;
    }

    public String getHeadno() {
        return headno;
    }

    public void setHeadno(String headno) {
        this.headno = headno;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getPresident() {
        return president;
    }

    public void setPresident(String president) {
        this.president = president;
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
}