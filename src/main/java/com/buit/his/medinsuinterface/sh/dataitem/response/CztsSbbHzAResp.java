
package com.buit.his.medinsuinterface.sh.dataitem.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 类描述：城镇特殊上报表<br>
 *
 * @author beijunhua
 */
@ApiModel(value = "城镇特殊上报表A总")
public class CztsSbbHzAResp extends PageQuery {
	@ApiModelProperty(value="城镇特殊上报表明细")
	private List<CztsAResp> cztssbbresps;
    @ApiModelProperty(value = "总记录数")
    private String totalRecord;
    @ApiModelProperty(value = "就诊总人次")
    private String JZNUM_SUM;
    @ApiModelProperty(value = "费用总计")
    private String TOTFY_SUM;
    @ApiModelProperty(value = "分类自负离休")
    private String FLGRZF_LX_SUM;
    @ApiModelProperty(value = "分类自负伤残")
    private String FLGRZF_SC_SUM;
    @ApiModelProperty(value = "分类自负其他")
    private String FLGRZF_QT_SUM;
    @ApiModelProperty(value = "分类自负小计")
    private String FLGRZF_XJ_SUM;
    @ApiModelProperty(value = "自负小计")
    private String ZF_XJ_SUM;
    @ApiModelProperty(value = "自负医嘱")
    private String ZF_YZ_SUM;
    @ApiModelProperty(value = "自负高龄")
    private String ZF_GL_SUM;
    @ApiModelProperty(value = "自负重残")
    private String ZF_ZC_SUM;
    @ApiModelProperty(value = "自负离休")
    private String ZF_LX_SUM;
    @ApiModelProperty(value = "自负伤残")
    private String ZF_SC_SUM;
    @ApiModelProperty(value = "自负其他")
    private String ZF_QT_SUM;

    @ApiModelProperty(value = "统筹离休")
    private String TCZF_LX_SUM;
    @ApiModelProperty(value = "统筹伤残")
    private String TCZF_SC_SUM;
    @ApiModelProperty(value = "统筹其他")
    private String TCZF_QT_SUM;
    @ApiModelProperty(value = "统筹小计")
    private String TCZF_XJ_SUM;
    @ApiModelProperty(value = "统筹医嘱")
    private String TCZF_YZ_SUM;
    @ApiModelProperty(value = "统筹高龄")
    private String TCZF_GL_SUM;
    @ApiModelProperty(value = "统筹重残")
    private String TCZF_ZC_SUM;

    @ApiModelProperty(value = "个人自费费用")
    private String GRZF_SUM;
    @ApiModelProperty(value = "个人自费费用_离休")
    private String GRZF_LX_SUM;
    @ApiModelProperty(value = "个人自费费用_伤残")
    private String GRZF_SC_SUM;
    @ApiModelProperty(value = "个人自费费用_其他")
    private String GRZF_QT_SUM;
    @ApiModelProperty(value = "个人自费费用_医嘱")
    private String GRZF_YZ_SUM;
    @ApiModelProperty(value = "个人自费费用_高龄")
    private String GRZF_GL_SUM;
    @ApiModelProperty(value = "个人自费费用_重残")
    private String GRZF_ZC_SUM;

    @ApiModelProperty(value = "就诊人次")
    private String JZ_SUM;
    @ApiModelProperty(value = "就诊人次_离休")
    private String JZ_LX_SUM;
    @ApiModelProperty(value = "就诊人次_伤残")
    private String JZ_SC_SUM;
    @ApiModelProperty(value = "就诊人次_其他")
    private String JZ_QT_SUM;
    @ApiModelProperty(value = "就诊人次_医嘱")
    private String JZ_YZ_SUM;
    @ApiModelProperty(value = "就诊人次_高龄")
    private String JZ_GL_SUM;
    @ApiModelProperty(value = "就诊人次_重残")
    private String JZ_ZC_SUM;

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

    public List<CztsAResp> getCztssbbresps() {
        return cztssbbresps;
    }

    public void setCztssbbresps(List<CztsAResp> cztssbbresps) {
        this.cztssbbresps = cztssbbresps;
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

    public String getTOTFY_SUM() {
        return TOTFY_SUM;
    }

    public void setTOTFY_SUM(String TOTFY_SUM) {
        this.TOTFY_SUM = TOTFY_SUM;
    }

    public String getFLGRZF_LX_SUM() {
        return FLGRZF_LX_SUM;
    }

    public void setFLGRZF_LX_SUM(String FLGRZF_LX_SUM) {
        this.FLGRZF_LX_SUM = FLGRZF_LX_SUM;
    }

    public String getFLGRZF_SC_SUM() {
        return FLGRZF_SC_SUM;
    }

    public void setFLGRZF_SC_SUM(String FLGRZF_SC_SUM) {
        this.FLGRZF_SC_SUM = FLGRZF_SC_SUM;
    }

    public String getFLGRZF_QT_SUM() {
        return FLGRZF_QT_SUM;
    }

    public void setFLGRZF_QT_SUM(String FLGRZF_QT_SUM) {
        this.FLGRZF_QT_SUM = FLGRZF_QT_SUM;
    }

    public String getFLGRZF_XJ_SUM() {
        return FLGRZF_XJ_SUM;
    }

    public void setFLGRZF_XJ_SUM(String FLGRZF_XJ_SUM) {
        this.FLGRZF_XJ_SUM = FLGRZF_XJ_SUM;
    }

    public String getZF_XJ_SUM() {
        return ZF_XJ_SUM;
    }

    public void setZF_XJ_SUM(String ZF_XJ_SUM) {
        this.ZF_XJ_SUM = ZF_XJ_SUM;
    }

    public String getZF_YZ_SUM() {
        return ZF_YZ_SUM;
    }

    public void setZF_YZ_SUM(String ZF_YZ_SUM) {
        this.ZF_YZ_SUM = ZF_YZ_SUM;
    }

    public String getZF_GL_SUM() {
        return ZF_GL_SUM;
    }

    public void setZF_GL_SUM(String ZF_GL_SUM) {
        this.ZF_GL_SUM = ZF_GL_SUM;
    }

    public String getZF_ZC_SUM() {
        return ZF_ZC_SUM;
    }

    public void setZF_ZC_SUM(String ZF_ZC_SUM) {
        this.ZF_ZC_SUM = ZF_ZC_SUM;
    }

    public String getZF_LX_SUM() {
        return ZF_LX_SUM;
    }

    public void setZF_LX_SUM(String ZF_LX_SUM) {
        this.ZF_LX_SUM = ZF_LX_SUM;
    }

    public String getZF_SC_SUM() {
        return ZF_SC_SUM;
    }

    public void setZF_SC_SUM(String ZF_SC_SUM) {
        this.ZF_SC_SUM = ZF_SC_SUM;
    }

    public String getZF_QT_SUM() {
        return ZF_QT_SUM;
    }

    public void setZF_QT_SUM(String ZF_QT_SUM) {
        this.ZF_QT_SUM = ZF_QT_SUM;
    }

    public String getTCZF_LX_SUM() {
        return TCZF_LX_SUM;
    }

    public void setTCZF_LX_SUM(String TCZF_LX_SUM) {
        this.TCZF_LX_SUM = TCZF_LX_SUM;
    }

    public String getTCZF_SC_SUM() {
        return TCZF_SC_SUM;
    }

    public void setTCZF_SC_SUM(String TCZF_SC_SUM) {
        this.TCZF_SC_SUM = TCZF_SC_SUM;
    }

    public String getTCZF_QT_SUM() {
        return TCZF_QT_SUM;
    }

    public void setTCZF_QT_SUM(String TCZF_QT_SUM) {
        this.TCZF_QT_SUM = TCZF_QT_SUM;
    }

    public String getTCZF_XJ_SUM() {
        return TCZF_XJ_SUM;
    }

    public void setTCZF_XJ_SUM(String TCZF_XJ_SUM) {
        this.TCZF_XJ_SUM = TCZF_XJ_SUM;
    }

    public String getTCZF_YZ_SUM() {
        return TCZF_YZ_SUM;
    }

    public void setTCZF_YZ_SUM(String TCZF_YZ_SUM) {
        this.TCZF_YZ_SUM = TCZF_YZ_SUM;
    }

    public String getTCZF_GL_SUM() {
        return TCZF_GL_SUM;
    }

    public void setTCZF_GL_SUM(String TCZF_GL_SUM) {
        this.TCZF_GL_SUM = TCZF_GL_SUM;
    }

    public String getTCZF_ZC_SUM() {
        return TCZF_ZC_SUM;
    }

    public void setTCZF_ZC_SUM(String TCZF_ZC_SUM) {
        this.TCZF_ZC_SUM = TCZF_ZC_SUM;
    }

    public String getGRZF_SUM() {
        return GRZF_SUM;
    }

    public void setGRZF_SUM(String GRZF_SUM) {
        this.GRZF_SUM = GRZF_SUM;
    }

    public String getGRZF_LX_SUM() {
        return GRZF_LX_SUM;
    }

    public void setGRZF_LX_SUM(String GRZF_LX_SUM) {
        this.GRZF_LX_SUM = GRZF_LX_SUM;
    }

    public String getGRZF_SC_SUM() {
        return GRZF_SC_SUM;
    }

    public void setGRZF_SC_SUM(String GRZF_SC_SUM) {
        this.GRZF_SC_SUM = GRZF_SC_SUM;
    }

    public String getGRZF_QT_SUM() {
        return GRZF_QT_SUM;
    }

    public void setGRZF_QT_SUM(String GRZF_QT_SUM) {
        this.GRZF_QT_SUM = GRZF_QT_SUM;
    }

    public String getGRZF_YZ_SUM() {
        return GRZF_YZ_SUM;
    }

    public void setGRZF_YZ_SUM(String GRZF_YZ_SUM) {
        this.GRZF_YZ_SUM = GRZF_YZ_SUM;
    }

    public String getGRZF_GL_SUM() {
        return GRZF_GL_SUM;
    }

    public void setGRZF_GL_SUM(String GRZF_GL_SUM) {
        this.GRZF_GL_SUM = GRZF_GL_SUM;
    }

    public String getGRZF_ZC_SUM() {
        return GRZF_ZC_SUM;
    }

    public void setGRZF_ZC_SUM(String GRZF_ZC_SUM) {
        this.GRZF_ZC_SUM = GRZF_ZC_SUM;
    }

    public String getJZ_SUM() {
        return JZ_SUM;
    }

    public void setJZ_SUM(String JZ_SUM) {
        this.JZ_SUM = JZ_SUM;
    }

    public String getJZ_LX_SUM() {
        return JZ_LX_SUM;
    }

    public void setJZ_LX_SUM(String JZ_LX_SUM) {
        this.JZ_LX_SUM = JZ_LX_SUM;
    }

    public String getJZ_SC_SUM() {
        return JZ_SC_SUM;
    }

    public void setJZ_SC_SUM(String JZ_SC_SUM) {
        this.JZ_SC_SUM = JZ_SC_SUM;
    }

    public String getJZ_QT_SUM() {
        return JZ_QT_SUM;
    }

    public void setJZ_QT_SUM(String JZ_QT_SUM) {
        this.JZ_QT_SUM = JZ_QT_SUM;
    }

    public String getJZ_YZ_SUM() {
        return JZ_YZ_SUM;
    }

    public void setJZ_YZ_SUM(String JZ_YZ_SUM) {
        this.JZ_YZ_SUM = JZ_YZ_SUM;
    }

    public String getJZ_GL_SUM() {
        return JZ_GL_SUM;
    }

    public void setJZ_GL_SUM(String JZ_GL_SUM) {
        this.JZ_GL_SUM = JZ_GL_SUM;
    }

    public String getJZ_ZC_SUM() {
        return JZ_ZC_SUM;
    }

    public void setJZ_ZC_SUM(String JZ_ZC_SUM) {
        this.JZ_ZC_SUM = JZ_ZC_SUM;
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