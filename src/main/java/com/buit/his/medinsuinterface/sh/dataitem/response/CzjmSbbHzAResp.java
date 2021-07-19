
package com.buit.his.medinsuinterface.sh.dataitem.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 类描述：城镇居民上报表a汇总<br>
 *
 * @author beijunhua
 */
@ApiModel(value = "城镇居民上报表A总")
public class CzjmSbbHzAResp extends PageQuery {
	@ApiModelProperty(value="城镇居民上报表明细")
	private List<CzjmAResp> czjmsbbresps;
    @ApiModelProperty(value = "总记录数")
    private String totalRecord;
    @ApiModelProperty(value = "费用总计")
    private String TOTFY_ZJ;
    @ApiModelProperty(value = "分类自负总计")
    private String FLGRZF_ZJ;
    @ApiModelProperty(value = "自负合计")
    private String GRXJ_ZJ;
    @ApiModelProperty(value = "就诊次数")
    private String JZNUM;
    @ApiModelProperty(value = "医嘱人次")
    private String JZNUM_YZ;
    @ApiModelProperty(value = "高龄人次")
    private String JZNUM_GL;
    @ApiModelProperty(value = "重残人次")
    private String JZNUM_ZC;
    @ApiModelProperty(value = "中小学人次")
    private String JZNUM_ZX;
    @ApiModelProperty(value = "其他人次")
    private String JZNUM_QT;
    @ApiModelProperty(value = "基金支付合计")
    private String TCBX;
    @ApiModelProperty(value = "医嘱")
    private String TCBX_YZ;
    @ApiModelProperty(value = "高龄")
    private String TCBX_GL;
    @ApiModelProperty(value = "重残")
    private String TCBX_ZC;
    @ApiModelProperty(value = "中小学生")
    private String TCBX_ZX;
    @ApiModelProperty(value = "其他")
    private String TCBX_QT;
    @ApiModelProperty(value = "本期非医保自负合计")
    private String ZF;
    @ApiModelProperty(value = "医嘱")
    private String ZF_YZ;
    @ApiModelProperty(value = "高龄")
    private String ZF_GL;
    @ApiModelProperty(value = "重残")
    private String ZF_ZC;
    @ApiModelProperty(value = "中小学生")
    private String ZF_ZX;
    @ApiModelProperty(value = "其他")
    private String ZF_QT;



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

    public List<CzjmAResp> getCzjmsbbresps() {
        return czjmsbbresps;
    }

    public void setCzjmsbbresps(List<CzjmAResp> czjmsbbresps) {
        this.czjmsbbresps = czjmsbbresps;
    }

    public String getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(String totalRecord) {
        this.totalRecord = totalRecord;
    }

    public String getTOTFY_ZJ() {
        return TOTFY_ZJ;
    }

    public void setTOTFY_ZJ(String TOTFY_ZJ) {
        this.TOTFY_ZJ = TOTFY_ZJ;
    }

    public String getFLGRZF_ZJ() {
        return FLGRZF_ZJ;
    }

    public void setFLGRZF_ZJ(String FLGRZF_ZJ) {
        this.FLGRZF_ZJ = FLGRZF_ZJ;
    }

    public String getGRXJ_ZJ() {
        return GRXJ_ZJ;
    }

    public void setGRXJ_ZJ(String GRXJ_ZJ) {
        this.GRXJ_ZJ = GRXJ_ZJ;
    }

    public String getJZNUM() {
        return JZNUM;
    }

    public void setJZNUM(String JZNUM) {
        this.JZNUM = JZNUM;
    }

    public String getJZNUM_YZ() {
        return JZNUM_YZ;
    }

    public void setJZNUM_YZ(String JZNUM_YZ) {
        this.JZNUM_YZ = JZNUM_YZ;
    }

    public String getJZNUM_GL() {
        return JZNUM_GL;
    }

    public void setJZNUM_GL(String JZNUM_GL) {
        this.JZNUM_GL = JZNUM_GL;
    }

    public String getJZNUM_ZC() {
        return JZNUM_ZC;
    }

    public void setJZNUM_ZC(String JZNUM_ZC) {
        this.JZNUM_ZC = JZNUM_ZC;
    }

    public String getJZNUM_ZX() {
        return JZNUM_ZX;
    }

    public void setJZNUM_ZX(String JZNUM_ZX) {
        this.JZNUM_ZX = JZNUM_ZX;
    }

    public String getJZNUM_QT() {
        return JZNUM_QT;
    }

    public void setJZNUM_QT(String JZNUM_QT) {
        this.JZNUM_QT = JZNUM_QT;
    }

    public String getTCBX() {
        return TCBX;
    }

    public void setTCBX(String TCBX) {
        this.TCBX = TCBX;
    }

    public String getTCBX_YZ() {
        return TCBX_YZ;
    }

    public void setTCBX_YZ(String TCBX_YZ) {
        this.TCBX_YZ = TCBX_YZ;
    }

    public String getTCBX_GL() {
        return TCBX_GL;
    }

    public void setTCBX_GL(String TCBX_GL) {
        this.TCBX_GL = TCBX_GL;
    }

    public String getTCBX_ZC() {
        return TCBX_ZC;
    }

    public void setTCBX_ZC(String TCBX_ZC) {
        this.TCBX_ZC = TCBX_ZC;
    }

    public String getTCBX_ZX() {
        return TCBX_ZX;
    }

    public void setTCBX_ZX(String TCBX_ZX) {
        this.TCBX_ZX = TCBX_ZX;
    }

    public String getTCBX_QT() {
        return TCBX_QT;
    }

    public void setTCBX_QT(String TCBX_QT) {
        this.TCBX_QT = TCBX_QT;
    }

    public String getZF() {
        return ZF;
    }

    public void setZF(String ZF) {
        this.ZF = ZF;
    }

    public String getZF_YZ() {
        return ZF_YZ;
    }

    public void setZF_YZ(String ZF_YZ) {
        this.ZF_YZ = ZF_YZ;
    }

    public String getZF_GL() {
        return ZF_GL;
    }

    public void setZF_GL(String ZF_GL) {
        this.ZF_GL = ZF_GL;
    }

    public String getZF_ZC() {
        return ZF_ZC;
    }

    public void setZF_ZC(String ZF_ZC) {
        this.ZF_ZC = ZF_ZC;
    }

    public String getZF_ZX() {
        return ZF_ZX;
    }

    public void setZF_ZX(String ZF_ZX) {
        this.ZF_ZX = ZF_ZX;
    }

    public String getZF_QT() {
        return ZF_QT;
    }

    public void setZF_QT(String ZF_QT) {
        this.ZF_QT = ZF_QT;
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