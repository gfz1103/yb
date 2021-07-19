
package com.buit.his.medinsuinterface.sh.dataitem.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 类描述：工伤上报表汇总A<br>
 *
 * @author beijunhua
 */
@ApiModel(value = "工伤上报表汇总A")
public class GsbxSbbHzAResp extends PageQuery {
	@ApiModelProperty(value="工伤上报表明细")
	private List<GsbxAResp> gsbxsbbresps;
    @ApiModelProperty(value = "总记录数")
    private String totalRecord;

    @ApiModelProperty(value = "就诊次数总计")
    private String JZNUM_SUM;
    @ApiModelProperty(value = "工伤保险基本支付总计 ")
    private String GSJJZFS_SUM;

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

    public List<GsbxAResp> getGsbxsbbresps() {
        return gsbxsbbresps;
    }

    public void setGsbxsbbresps(List<GsbxAResp> gsbxsbbresps) {
        this.gsbxsbbresps = gsbxsbbresps;
    }

    public String getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(String totalRecord) {
        this.totalRecord = totalRecord;
    }

    public String getJZNUM_SUM() {
        return JZNUM_SUM;
    }

    public void setJZNUM_SUM(String JZNUM_SUM) {
        this.JZNUM_SUM = JZNUM_SUM;
    }

    public String getGSJJZFS_SUM() {
        return GSJJZFS_SUM;
    }

    public void setGSJJZFS_SUM(String GSJJZFS_SUM) {
        this.GSJJZFS_SUM = GSJJZFS_SUM;
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