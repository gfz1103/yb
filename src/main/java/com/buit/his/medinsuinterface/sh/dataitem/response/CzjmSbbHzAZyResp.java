
package com.buit.his.medinsuinterface.sh.dataitem.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 类描述：城镇大病上报表<br>
 *
 * @author beijunhua
 */
@ApiModel(value = "城镇居民上报表A住院总")
public class CzjmSbbHzAZyResp extends PageQuery {
	@ApiModelProperty(value="城镇居民上报表住院明细")
	private List<CzjmAZyResp> czjmzysbbresps;
    @ApiModelProperty(value = "总记录数")
    private String totalRecord;
    @ApiModelProperty(value = "住院天数总计")
    private String ZYD_ZJ;
    @ApiModelProperty(value = "费用总计")
    private String TOTFY_ZJ;
    @ApiModelProperty(value = "分类自负总计")
    private String FLGRZF_ZJ;
    @ApiModelProperty(value = "个人支付总计")
    private String GRZF_ZJ;
    @ApiModelProperty(value = "医保支付总计")
    private String YBZF;

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

    public List<CzjmAZyResp> getCzjmzysbbresps() {
        return czjmzysbbresps;
    }

    public void setCzjmzysbbresps(List<CzjmAZyResp> czjmzysbbresps) {
        this.czjmzysbbresps = czjmzysbbresps;
    }

    public String getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(String totalRecord) {
        this.totalRecord = totalRecord;
    }

    public String getZYD_ZJ() {
        return ZYD_ZJ;
    }

    public void setZYD_ZJ(String ZYD_ZJ) {
        this.ZYD_ZJ = ZYD_ZJ;
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

    public String getGRZF_ZJ() {
        return GRZF_ZJ;
    }

    public void setGRZF_ZJ(String GRZF_ZJ) {
        this.GRZF_ZJ = GRZF_ZJ;
    }

    public String getYBZF() {
        return YBZF;
    }

    public void setYBZF(String YBZF) {
        this.YBZF = YBZF;
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