
package com.buit.his.medinsuinterface.sh.dataitem.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 类描述：城镇住院上报表B<br>
 *
 * @author beijunhua
 */
@ApiModel(value = "城镇住院上报表B")
public class CzzySbbHzBResp extends PageQuery {
	@ApiModelProperty(value="城镇住院上报表B明细")
	private List<CzzyBResp> czzysbbresps;
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

    @ApiModelProperty(value = "住院费_合计")
    private String FY01;
    @ApiModelProperty(value = "诊疗费_合计")
    private String FY02;
    @ApiModelProperty(value = "治疗费_合计")
    private String FY03;
    @ApiModelProperty(value = "护理费_合计")
    private String FY04;
    @ApiModelProperty(value = "手材费_合计")
    private String FY05;
    @ApiModelProperty(value = "检查费_合计")
    private String FY06;
    @ApiModelProperty(value = "化验费_合计")
    private String FY07;
    @ApiModelProperty(value = "摄片费_合计")
    private String FY08;
    @ApiModelProperty(value = "透视费_合计")
    private String FY09;
    @ApiModelProperty(value = "输血费_合计")
    private String FY10;
    @ApiModelProperty(value = "输氧费_合计")
    private String FY11;
    @ApiModelProperty(value = "西药费_合计")
    private String FY12;
    @ApiModelProperty(value = "中成药费_合计")
    private String FY13;
    @ApiModelProperty(value = "中草药费_合计")
    private String FY14;
    @ApiModelProperty(value = "其它_合计")
    private String FY15;
    @ApiModelProperty(value = "住院费用合计")
    private String JYTOTFY;

    public List<CzzyBResp> getCzzysbbresps() {
        return czzysbbresps;
    }

    public void setCzzysbbresps(List<CzzyBResp> czzysbbresps) {
        this.czzysbbresps = czzysbbresps;
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

    public String getFY01() {
        return FY01;
    }

    public void setFY01(String FY01) {
        this.FY01 = FY01;
    }

    public String getFY02() {
        return FY02;
    }

    public void setFY02(String FY02) {
        this.FY02 = FY02;
    }

    public String getFY03() {
        return FY03;
    }

    public void setFY03(String FY03) {
        this.FY03 = FY03;
    }

    public String getFY04() {
        return FY04;
    }

    public void setFY04(String FY04) {
        this.FY04 = FY04;
    }

    public String getFY05() {
        return FY05;
    }

    public void setFY05(String FY05) {
        this.FY05 = FY05;
    }

    public String getFY06() {
        return FY06;
    }

    public void setFY06(String FY06) {
        this.FY06 = FY06;
    }

    public String getFY07() {
        return FY07;
    }

    public void setFY07(String FY07) {
        this.FY07 = FY07;
    }

    public String getFY08() {
        return FY08;
    }

    public void setFY08(String FY08) {
        this.FY08 = FY08;
    }

    public String getFY09() {
        return FY09;
    }

    public void setFY09(String FY09) {
        this.FY09 = FY09;
    }

    public String getFY10() {
        return FY10;
    }

    public void setFY10(String FY10) {
        this.FY10 = FY10;
    }

    public String getFY11() {
        return FY11;
    }

    public void setFY11(String FY11) {
        this.FY11 = FY11;
    }

    public String getFY12() {
        return FY12;
    }

    public void setFY12(String FY12) {
        this.FY12 = FY12;
    }

    public String getFY13() {
        return FY13;
    }

    public void setFY13(String FY13) {
        this.FY13 = FY13;
    }

    public String getFY14() {
        return FY14;
    }

    public void setFY14(String FY14) {
        this.FY14 = FY14;
    }

    public String getFY15() {
        return FY15;
    }

    public void setFY15(String FY15) {
        this.FY15 = FY15;
    }

    public String getJYTOTFY() {
        return JYTOTFY;
    }

    public void setJYTOTFY(String JYTOTFY) {
        this.JYTOTFY = JYTOTFY;
    }
}