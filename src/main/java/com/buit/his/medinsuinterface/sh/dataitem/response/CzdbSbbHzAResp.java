
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
@ApiModel(value = "城镇大病上报表A总")
public class CzdbSbbHzAResp extends PageQuery {
	@ApiModelProperty(value="城镇大病上报表明细")
	private List<CzdbAResp> czdbsbbresps;
    @ApiModelProperty(value = "总记录数")
    private String totalRecord;
    @ApiModelProperty(value = "就诊总人次")
    private String JZNUM_ZJ;
    @ApiModelProperty(value = "费用总计")
    private String TOTFY_ZJ;
    @ApiModelProperty(value = "分类自负在职")
    private String FLGRZF_ZZ_ZJ;
    @ApiModelProperty(value = "分类自负退休")
    private String FLGRZF_TX_ZJ;
    @ApiModelProperty(value = "自负在职")
    private String GRXJ_ZZ_ZJ;
    @ApiModelProperty(value = "自负退休")
    private String GRXJ_TX_ZJ;
    @ApiModelProperty(value = "当年在职")
    private String TCZF_ZZ;
    @ApiModelProperty(value = "当年退休")
    private String TCZF_TX_ZJ;
    @ApiModelProperty(value = "历年在职")
    private String GRZFL_ZZ_ZJ;
    @ApiModelProperty(value = "历年退休")
    private String GRZFL_TX_ZJ;
    @ApiModelProperty(value = "附加在职")
    private String FJBX_ZZ_ZJ;
    @ApiModelProperty(value = "附加退休")
    private String FJBX_TX_ZJ;

    @ApiModelProperty(value = "分类自负合计")
    private String FLGRZFHJ;
    @ApiModelProperty(value = "自负合计")
    private String GRXJHJ;
    @ApiModelProperty(value = "当年合计")
    private String TCZFHJ;
    @ApiModelProperty(value = "历年合计")
    private String GRZFLHJ;
    @ApiModelProperty(value = "附加合计")
    private String FJBXHJ;
    @ApiModelProperty(value = "自付合计")
    private String ZFHJ;
    @ApiModelProperty(value = "自付合计-在职")
    private String ZFHJ_ZZ;
    @ApiModelProperty(value = "自付合计-退休")
    private String ZFHJ_TX;

    @ApiModelProperty(value = "就诊人次-在职")
    private String JZRC_ZZ;
    @ApiModelProperty(value = "就诊人次-退休")
    private String JZRC_TX;
    @ApiModelProperty(value = "附加支付人次合计")
    private String FJZFRCHJ;
    @ApiModelProperty(value = "附加支付人次-在职")
    private String FJZFRC_ZZ;
    @ApiModelProperty(value = "附加支付人次-退休")
    private String FJZFRC_TX;

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

    public List<CzdbAResp> getCzdbsbbresps() {
        return czdbsbbresps;
    }

    public void setCzdbsbbresps(List<CzdbAResp> czdbsbbresps) {
        this.czdbsbbresps = czdbsbbresps;
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

    public String getFLGRZF_ZZ_ZJ() {
        return FLGRZF_ZZ_ZJ;
    }

    public void setFLGRZF_ZZ_ZJ(String FLGRZF_ZZ_ZJ) {
        this.FLGRZF_ZZ_ZJ = FLGRZF_ZZ_ZJ;
    }

    public String getFLGRZF_TX_ZJ() {
        return FLGRZF_TX_ZJ;
    }

    public void setFLGRZF_TX_ZJ(String FLGRZF_TX_ZJ) {
        this.FLGRZF_TX_ZJ = FLGRZF_TX_ZJ;
    }

    public String getGRXJ_ZZ_ZJ() {
        return GRXJ_ZZ_ZJ;
    }

    public void setGRXJ_ZZ_ZJ(String GRXJ_ZZ_ZJ) {
        this.GRXJ_ZZ_ZJ = GRXJ_ZZ_ZJ;
    }

    public String getGRXJ_TX_ZJ() {
        return GRXJ_TX_ZJ;
    }

    public void setGRXJ_TX_ZJ(String GRXJ_TX_ZJ) {
        this.GRXJ_TX_ZJ = GRXJ_TX_ZJ;
    }

    public String getTCZF_ZZ() {
        return TCZF_ZZ;
    }

    public void setTCZF_ZZ(String TCZF_ZZ) {
        this.TCZF_ZZ = TCZF_ZZ;
    }

    public String getTCZF_TX_ZJ() {
        return TCZF_TX_ZJ;
    }

    public void setTCZF_TX_ZJ(String TCZF_TX_ZJ) {
        this.TCZF_TX_ZJ = TCZF_TX_ZJ;
    }

    public String getGRZFL_ZZ_ZJ() {
        return GRZFL_ZZ_ZJ;
    }

    public void setGRZFL_ZZ_ZJ(String GRZFL_ZZ_ZJ) {
        this.GRZFL_ZZ_ZJ = GRZFL_ZZ_ZJ;
    }

    public String getGRZFL_TX_ZJ() {
        return GRZFL_TX_ZJ;
    }

    public void setGRZFL_TX_ZJ(String GRZFL_TX_ZJ) {
        this.GRZFL_TX_ZJ = GRZFL_TX_ZJ;
    }

    public String getFJBX_ZZ_ZJ() {
        return FJBX_ZZ_ZJ;
    }

    public void setFJBX_ZZ_ZJ(String FJBX_ZZ_ZJ) {
        this.FJBX_ZZ_ZJ = FJBX_ZZ_ZJ;
    }

    public String getFJBX_TX_ZJ() {
        return FJBX_TX_ZJ;
    }

    public void setFJBX_TX_ZJ(String FJBX_TX_ZJ) {
        this.FJBX_TX_ZJ = FJBX_TX_ZJ;
    }

    public String getFLGRZFHJ() {
        return FLGRZFHJ;
    }

    public void setFLGRZFHJ(String FLGRZFHJ) {
        this.FLGRZFHJ = FLGRZFHJ;
    }

    public String getGRXJHJ() {
        return GRXJHJ;
    }

    public void setGRXJHJ(String GRXJHJ) {
        this.GRXJHJ = GRXJHJ;
    }

    public String getTCZFHJ() {
        return TCZFHJ;
    }

    public void setTCZFHJ(String TCZFHJ) {
        this.TCZFHJ = TCZFHJ;
    }

    public String getGRZFLHJ() {
        return GRZFLHJ;
    }

    public void setGRZFLHJ(String GRZFLHJ) {
        this.GRZFLHJ = GRZFLHJ;
    }

    public String getFJBXHJ() {
        return FJBXHJ;
    }

    public void setFJBXHJ(String FJBXHJ) {
        this.FJBXHJ = FJBXHJ;
    }

    public String getZFHJ() {
        return ZFHJ;
    }

    public void setZFHJ(String ZFHJ) {
        this.ZFHJ = ZFHJ;
    }

    public String getZFHJ_ZZ() {
        return ZFHJ_ZZ;
    }

    public void setZFHJ_ZZ(String ZFHJ_ZZ) {
        this.ZFHJ_ZZ = ZFHJ_ZZ;
    }

    public String getZFHJ_TX() {
        return ZFHJ_TX;
    }

    public void setZFHJ_TX(String ZFHJ_TX) {
        this.ZFHJ_TX = ZFHJ_TX;
    }

    public String getJZRC_ZZ() {
        return JZRC_ZZ;
    }

    public void setJZRC_ZZ(String JZRC_ZZ) {
        this.JZRC_ZZ = JZRC_ZZ;
    }

    public String getJZRC_TX() {
        return JZRC_TX;
    }

    public void setJZRC_TX(String JZRC_TX) {
        this.JZRC_TX = JZRC_TX;
    }

    public String getFJZFRCHJ() {
        return FJZFRCHJ;
    }

    public void setFJZFRCHJ(String FJZFRCHJ) {
        this.FJZFRCHJ = FJZFRCHJ;
    }

    public String getFJZFRC_ZZ() {
        return FJZFRC_ZZ;
    }

    public void setFJZFRC_ZZ(String FJZFRC_ZZ) {
        this.FJZFRC_ZZ = FJZFRC_ZZ;
    }

    public String getFJZFRC_TX() {
        return FJZFRC_TX;
    }

    public void setFJZFRC_TX(String FJZFRC_TX) {
        this.FJZFRC_TX = FJZFRC_TX;
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