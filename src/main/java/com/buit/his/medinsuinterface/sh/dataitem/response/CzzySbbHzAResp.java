
package com.buit.his.medinsuinterface.sh.dataitem.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 类描述：城镇住院上报表汇总<br>
 *
 * @author beijunhua
 */
@ApiModel(value = "城镇住院上报表A总")
public class CzzySbbHzAResp extends PageQuery {
	@ApiModelProperty(value="城镇大病上报表明细")
	private List<CzzyAResp> czzysbbresps;
    @ApiModelProperty(value = "总记录数")
    private String totalRecord;

    @ApiModelProperty(value = "住院天数总计_合计")
    private String ZYTS_ALL;
    @ApiModelProperty(value = "费用总计_合计 ")
    private String FYZJ_ALL;
    @ApiModelProperty(value = "分类自负在职")
    private String ZZFLGRZF_ALL;
    @ApiModelProperty(value = "分类自负退休")
    private String TXFLGRZF_ALL;
    @ApiModelProperty(value = "自负在职")
    private String ZZZF_ALL;
    @ApiModelProperty(value = "自负退休")
    private String TXZF_ALL;
    @ApiModelProperty(value = "统筹基金在职")
    private String ZZTCZF_ALL;
    @ApiModelProperty(value = "统筹基金退休")
    private String TXTCZF_ALL;
    @ApiModelProperty(value = "历年基金在职")
    private String ZZNN_ALL;
    @ApiModelProperty(value = "历年基金退休")
    private String TXNN_ALL;
    @ApiModelProperty(value = "历年基金定额")
    private String GRZFLD_ALL;
    @ApiModelProperty(value = "历年基金其余")
    private String GRZFLQ_ALL;
    @ApiModelProperty(value = "附加在职")
    private String ZZFJBX_ALL;
    @ApiModelProperty(value = "附加退休")
    private String TXFJBX_ALL;

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

    public List<CzzyAResp> getCzzysbbresps() {
        return czzysbbresps;
    }

    public void setCzzysbbresps(List<CzzyAResp> czzysbbresps) {
        this.czzysbbresps = czzysbbresps;
    }

    public String getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(String totalRecord) {
        this.totalRecord = totalRecord;
    }

    public String getZYTS_ALL() {
        return ZYTS_ALL;
    }

    public void setZYTS_ALL(String ZYTS_ALL) {
        this.ZYTS_ALL = ZYTS_ALL;
    }

    public String getFYZJ_ALL() {
        return FYZJ_ALL;
    }

    public void setFYZJ_ALL(String FYZJ_ALL) {
        this.FYZJ_ALL = FYZJ_ALL;
    }

    public String getZZFLGRZF_ALL() {
        return ZZFLGRZF_ALL;
    }

    public void setZZFLGRZF_ALL(String ZZFLGRZF_ALL) {
        this.ZZFLGRZF_ALL = ZZFLGRZF_ALL;
    }

    public String getTXFLGRZF_ALL() {
        return TXFLGRZF_ALL;
    }

    public void setTXFLGRZF_ALL(String TXFLGRZF_ALL) {
        this.TXFLGRZF_ALL = TXFLGRZF_ALL;
    }

    public String getZZZF_ALL() {
        return ZZZF_ALL;
    }

    public void setZZZF_ALL(String ZZZF_ALL) {
        this.ZZZF_ALL = ZZZF_ALL;
    }

    public String getTXZF_ALL() {
        return TXZF_ALL;
    }

    public void setTXZF_ALL(String TXZF_ALL) {
        this.TXZF_ALL = TXZF_ALL;
    }

    public String getZZTCZF_ALL() {
        return ZZTCZF_ALL;
    }

    public void setZZTCZF_ALL(String ZZTCZF_ALL) {
        this.ZZTCZF_ALL = ZZTCZF_ALL;
    }

    public String getTXTCZF_ALL() {
        return TXTCZF_ALL;
    }

    public void setTXTCZF_ALL(String TXTCZF_ALL) {
        this.TXTCZF_ALL = TXTCZF_ALL;
    }

    public String getZZNN_ALL() {
        return ZZNN_ALL;
    }

    public void setZZNN_ALL(String ZZNN_ALL) {
        this.ZZNN_ALL = ZZNN_ALL;
    }

    public String getTXNN_ALL() {
        return TXNN_ALL;
    }

    public void setTXNN_ALL(String TXNN_ALL) {
        this.TXNN_ALL = TXNN_ALL;
    }

    public String getGRZFLD_ALL() {
        return GRZFLD_ALL;
    }

    public void setGRZFLD_ALL(String GRZFLD_ALL) {
        this.GRZFLD_ALL = GRZFLD_ALL;
    }

    public String getGRZFLQ_ALL() {
        return GRZFLQ_ALL;
    }

    public void setGRZFLQ_ALL(String GRZFLQ_ALL) {
        this.GRZFLQ_ALL = GRZFLQ_ALL;
    }

    public String getZZFJBX_ALL() {
        return ZZFJBX_ALL;
    }

    public void setZZFJBX_ALL(String ZZFJBX_ALL) {
        this.ZZFJBX_ALL = ZZFJBX_ALL;
    }

    public String getTXFJBX_ALL() {
        return TXFJBX_ALL;
    }

    public void setTXFJBX_ALL(String TXFJBX_ALL) {
        this.TXFJBX_ALL = TXFJBX_ALL;
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