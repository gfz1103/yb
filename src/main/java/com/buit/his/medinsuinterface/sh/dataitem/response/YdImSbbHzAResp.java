
package com.buit.his.medinsuinterface.sh.dataitem.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 类描述：异地住院上报表a汇总<br>
 *
 * @author beijunhua
 */
@ApiModel(value = "异地住院上报表a汇总")
public class YdImSbbHzAResp extends PageQuery {
	@ApiModelProperty(value="异地住院a上报表明细")
	private List<YdImAResp> ydimasbbresps;
    @ApiModelProperty(value = "总记录数")
    private String totalRecord;
    @ApiModelProperty(value = "住院天数_总计")
    private String ZYDNUM_SUM;
    @ApiModelProperty(value = "费用总计")
    private String JYTOTFY_SUM;
    @ApiModelProperty(value = "个人现金支付")
    private String GRXJZF_SUM;
    @ApiModelProperty(value = "账户资金支付")
    private String ZHZJZF_SUM;
    @ApiModelProperty(value = "统筹基金支付")
    private String TCJJZF_SUM;



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

    public List<YdImAResp> getYdimasbbresps() {
        return ydimasbbresps;
    }

    public void setYdimasbbresps(List<YdImAResp> ydimasbbresps) {
        this.ydimasbbresps = ydimasbbresps;
    }

    public String getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(String totalRecord) {
        this.totalRecord = totalRecord;
    }

    public String getZYDNUM_SUM() {
        return ZYDNUM_SUM;
    }

    public void setZYDNUM_SUM(String ZYDNUM_SUM) {
        this.ZYDNUM_SUM = ZYDNUM_SUM;
    }

    public String getJYTOTFY_SUM() {
        return JYTOTFY_SUM;
    }

    public void setJYTOTFY_SUM(String JYTOTFY_SUM) {
        this.JYTOTFY_SUM = JYTOTFY_SUM;
    }

    public String getGRXJZF_SUM() {
        return GRXJZF_SUM;
    }

    public void setGRXJZF_SUM(String GRXJZF_SUM) {
        this.GRXJZF_SUM = GRXJZF_SUM;
    }

    public String getZHZJZF_SUM() {
        return ZHZJZF_SUM;
    }

    public void setZHZJZF_SUM(String ZHZJZF_SUM) {
        this.ZHZJZF_SUM = ZHZJZF_SUM;
    }

    public String getTCJJZF_SUM() {
        return TCJJZF_SUM;
    }

    public void setTCJJZF_SUM(String TCJJZF_SUM) {
        this.TCJJZF_SUM = TCJJZF_SUM;
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