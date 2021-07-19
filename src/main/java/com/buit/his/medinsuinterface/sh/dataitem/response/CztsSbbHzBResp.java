
package com.buit.his.medinsuinterface.sh.dataitem.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 类描述：城镇特殊上报表B汇总<br>
 *
 * @author beijunhua
 */
@ApiModel(value = "城镇特殊上报表B汇总")
public class CztsSbbHzBResp extends PageQuery {
	@ApiModelProperty(value="城镇特殊上报表B明细")
	private List<CztsBResp> cztssbbresps;
    @ApiModelProperty(value = "编号")
    private String headno;
    @ApiModelProperty(value = "医疗机构负责人")
    private String president;
    @ApiModelProperty(value = "财会负责人")
    private String financialOfficer;
    @ApiModelProperty(value = "日期")
    private String querydate;
    @ApiModelProperty(value = "制表人")
    private String maker;
    @ApiModelProperty(value = "审核人")
    private String reviewer;
    @ApiModelProperty(value = "医院名称")
    private String hosName;
    @ApiModelProperty(value = "总记录数")
    private String totalRecord;
    @ApiModelProperty(value = "填报日期")
    private String reportDate;
    @ApiModelProperty(value = "银行账号")
    private String bankNo;
    @ApiModelProperty(value = "银行")
    private String bankName;

    @ApiModelProperty(value = "诊疗费(自费)")
    private String FY11_ALL;
    @ApiModelProperty(value = "诊疗费")
    private String FY00_ALL;
    @ApiModelProperty(value = "治疗费")
    private String FY01_ALL;
    @ApiModelProperty(value = "手材费")
    private String FY02_ALL;
    @ApiModelProperty(value = "检查费")
    private String FY03_ALL;
    @ApiModelProperty(value = "化验费")
    private String FY04_ALL;
    @ApiModelProperty(value = "摄片费")
    private String FY05_ALL;
    @ApiModelProperty(value = "透视费")
    private String FY06_ALL;
    @ApiModelProperty(value = "西药费")
    private String FY07_ALL;
    @ApiModelProperty(value = "中成药费")
    private String FY08_ALL;
    @ApiModelProperty(value = "中草药费")
    private String FY09_ALL;
    @ApiModelProperty(value = "其它")
    private String FY10_ALL;
    @ApiModelProperty(value = "医保内总费用")
    private String TOTFY_ALL;

    public List<CztsBResp> getCztssbbresps() {
        return cztssbbresps;
    }

    public void setCztssbbresps(List<CztsBResp> cztssbbresps) {
        this.cztssbbresps = cztssbbresps;
    }

    public String getHeadno() {
        return headno;
    }

    public void setHeadno(String headno) {
        this.headno = headno;
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

    public String getQuerydate() {
        return querydate;
    }

    public void setQuerydate(String querydate) {
        this.querydate = querydate;
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

    public String getHosName() {
        return hosName;
    }

    public void setHosName(String hosName) {
        this.hosName = hosName;
    }

    public String getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(String totalRecord) {
        this.totalRecord = totalRecord;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getFY11_ALL() {
        return FY11_ALL;
    }

    public void setFY11_ALL(String FY11_ALL) {
        this.FY11_ALL = FY11_ALL;
    }



    public String getFY00_ALL() {
        return FY00_ALL;
    }

    public void setFY00_ALL(String FY00_ALL) {
        this.FY00_ALL = FY00_ALL;
    }

    public String getFY01_ALL() {
        return FY01_ALL;
    }

    public void setFY01_ALL(String FY01_ALL) {
        this.FY01_ALL = FY01_ALL;
    }

    public String getFY02_ALL() {
        return FY02_ALL;
    }

    public void setFY02_ALL(String FY02_ALL) {
        this.FY02_ALL = FY02_ALL;
    }

    public String getFY03_ALL() {
        return FY03_ALL;
    }

    public void setFY03_ALL(String FY03_ALL) {
        this.FY03_ALL = FY03_ALL;
    }

    public String getFY04_ALL() {
        return FY04_ALL;
    }

    public void setFY04_ALL(String FY04_ALL) {
        this.FY04_ALL = FY04_ALL;
    }

    public String getFY05_ALL() {
        return FY05_ALL;
    }

    public void setFY05_ALL(String FY05_ALL) {
        this.FY05_ALL = FY05_ALL;
    }

    public String getFY06_ALL() {
        return FY06_ALL;
    }

    public void setFY06_ALL(String FY06_ALL) {
        this.FY06_ALL = FY06_ALL;
    }

    public String getFY07_ALL() {
        return FY07_ALL;
    }

    public void setFY07_ALL(String FY07_ALL) {
        this.FY07_ALL = FY07_ALL;
    }

    public String getFY08_ALL() {
        return FY08_ALL;
    }

    public void setFY08_ALL(String FY08_ALL) {
        this.FY08_ALL = FY08_ALL;
    }

    public String getFY09_ALL() {
        return FY09_ALL;
    }

    public void setFY09_ALL(String FY09_ALL) {
        this.FY09_ALL = FY09_ALL;
    }

    public String getFY10_ALL() {
        return FY10_ALL;
    }

    public void setFY10_ALL(String FY10_ALL) {
        this.FY10_ALL = FY10_ALL;
    }

    public String getTOTFY_ALL() {
        return TOTFY_ALL;
    }

    public void setTOTFY_ALL(String TOTFY_ALL) {
        this.TOTFY_ALL = TOTFY_ALL;
    }
}