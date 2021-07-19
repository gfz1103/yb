
package com.buit.his.medinsuinterface.sh.dataitem.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 类描述：异地门诊上报表汇总A<br>
 *
 * @author beijunhua
 */
@ApiModel(value = "异地门诊上报表汇总A")
public class YdopSbbHzAResp extends PageQuery {
	@ApiModelProperty(value="异地门诊上报表A明细")
	private List<YdopAResp> ydopsbbresps;
    @ApiModelProperty(value = "总记录数")
    private String totalRecord;

    @ApiModelProperty(value = "就诊次数总计")
    private String JZNUM_ALL;
    @ApiModelProperty(value = "费用总计 ")
    private String JYTOTFY_ALL;
    @ApiModelProperty(value = "个人现金支付 ")
    private String GRXJ_ALL;
    @ApiModelProperty(value = "账户资金支付 ")
    private String ZHZJZF_ALL;
    @ApiModelProperty(value = "统筹基金支付 ")
    private String TCJJZF_ALL;

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

    public List<YdopAResp> getYdopsbbresps() {
        return ydopsbbresps;
    }

    public void setYdopsbbresps(List<YdopAResp> ydopsbbresps) {
        this.ydopsbbresps = ydopsbbresps;
    }

    public String getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(String totalRecord) {
        this.totalRecord = totalRecord;
    }

    public String getJZNUM_ALL() {
        return JZNUM_ALL;
    }

    public void setJZNUM_ALL(String JZNUM_ALL) {
        this.JZNUM_ALL = JZNUM_ALL;
    }

    public String getJYTOTFY_ALL() {
        return JYTOTFY_ALL;
    }

    public void setJYTOTFY_ALL(String JYTOTFY_ALL) {
        this.JYTOTFY_ALL = JYTOTFY_ALL;
    }

    public String getGRXJ_ALL() {
        return GRXJ_ALL;
    }

    public void setGRXJ_ALL(String GRXJ_ALL) {
        this.GRXJ_ALL = GRXJ_ALL;
    }

    public String getZHZJZF_ALL() {
        return ZHZJZF_ALL;
    }

    public void setZHZJZF_ALL(String ZHZJZF_ALL) {
        this.ZHZJZF_ALL = ZHZJZF_ALL;
    }

    public String getTCJJZF_ALL() {
        return TCJJZF_ALL;
    }

    public void setTCJJZF_ALL(String TCJJZF_ALL) {
        this.TCJJZF_ALL = TCJJZF_ALL;
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