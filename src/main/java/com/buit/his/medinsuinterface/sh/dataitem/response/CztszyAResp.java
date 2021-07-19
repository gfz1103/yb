
package com.buit.his.medinsuinterface.sh.dataitem.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类描述：城镇特殊住院A上报表明细<br>
 *
 * @author beijunhua
 */
@ApiModel(value = "城镇特殊住院A上报表明细")
public class CztszyAResp extends PageQuery {
	@ApiModelProperty(value = "序号")
	private String XH;
	@ApiModelProperty(value = "住院号")
	private String ZYID;
	@ApiModelProperty(value = "姓名")
	private String NAME;
	@ApiModelProperty(value = "凭证编号")
	private String YBID;
	@ApiModelProperty(value = "科别")
	private String KSNA;
	@ApiModelProperty(value = "出院日期")
	private String CYDATE;
	@ApiModelProperty(value = "住院本次结算住院天数次数")
	private String ZYDNUM;
	@ApiModelProperty(value = "费用总计")
	private String TOTFY;

	@ApiModelProperty(value = "分类小计")
	private String FLGRZF_XJ;
	@ApiModelProperty(value = "分类离休")
	private String FLGRZF_LX;
	@ApiModelProperty(value = "分类伤残")
	private String FLGRZF_SC;
	@ApiModelProperty(value = "分类其他")
	private String FLGRZF_QT;
	@ApiModelProperty(value = "自负小计")
	private String ZF_XJ;
	@ApiModelProperty(value = "自负医嘱")
	private String ZF_YZ;
	@ApiModelProperty(value = "自负高龄")
	private String ZF_GL;
	@ApiModelProperty(value = "自负重残")
	private String ZF_ZC;
	@ApiModelProperty(value = "统筹基金支付小计")
	private String TCZF_XJ;
	@ApiModelProperty(value = "统筹基金支付离休")
	private String TCZF_LX;
	@ApiModelProperty(value = "统筹基金支付伤残")
	private String TCZF_SC;
	@ApiModelProperty(value = "统筹基金支付其他")
	private String TCZF_QT;
	@ApiModelProperty(value = "统筹基金支付医嘱")
	private String TCZF_YZ;
	@ApiModelProperty(value = "统筹基金支付高龄")
	private String TCZF_GL;
	@ApiModelProperty(value = "统筹基金支付重残")
	private String TCZF_ZC;
	@ApiModelProperty(value = "诊断")
	private String ZDNAME;

	public String getXH() {
		return XH;
	}

	public void setXH(String XH) {
		this.XH = XH;
	}

	public String getZYID() {
		return ZYID;
	}

	public void setZYID(String ZYID) {
		this.ZYID = ZYID;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String NAME) {
		this.NAME = NAME;
	}

	public String getYBID() {
		return YBID;
	}

	public void setYBID(String YBID) {
		this.YBID = YBID;
	}

	public String getKSNA() {
		return KSNA;
	}

	public void setKSNA(String KSNA) {
		this.KSNA = KSNA;
	}

	public String getCYDATE() {
		return CYDATE;
	}

	public void setCYDATE(String CYDATE) {
		this.CYDATE = CYDATE;
	}

	public String getZYDNUM() {
		return ZYDNUM;
	}

	public void setZYDNUM(String ZYDNUM) {
		this.ZYDNUM = ZYDNUM;
	}

	public String getTOTFY() {
		return TOTFY;
	}

	public void setTOTFY(String TOTFY) {
		this.TOTFY = TOTFY;
	}

	public String getFLGRZF_XJ() {
		return FLGRZF_XJ;
	}

	public void setFLGRZF_XJ(String FLGRZF_XJ) {
		this.FLGRZF_XJ = FLGRZF_XJ;
	}

	public String getFLGRZF_LX() {
		return FLGRZF_LX;
	}

	public void setFLGRZF_LX(String FLGRZF_LX) {
		this.FLGRZF_LX = FLGRZF_LX;
	}

	public String getFLGRZF_SC() {
		return FLGRZF_SC;
	}

	public void setFLGRZF_SC(String FLGRZF_SC) {
		this.FLGRZF_SC = FLGRZF_SC;
	}

	public String getFLGRZF_QT() {
		return FLGRZF_QT;
	}

	public void setFLGRZF_QT(String FLGRZF_QT) {
		this.FLGRZF_QT = FLGRZF_QT;
	}

	public String getZF_XJ() {
		return ZF_XJ;
	}

	public void setZF_XJ(String ZF_XJ) {
		this.ZF_XJ = ZF_XJ;
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

	public String getTCZF_XJ() {
		return TCZF_XJ;
	}

	public void setTCZF_XJ(String TCZF_XJ) {
		this.TCZF_XJ = TCZF_XJ;
	}

	public String getTCZF_LX() {
		return TCZF_LX;
	}

	public void setTCZF_LX(String TCZF_LX) {
		this.TCZF_LX = TCZF_LX;
	}

	public String getTCZF_SC() {
		return TCZF_SC;
	}

	public void setTCZF_SC(String TCZF_SC) {
		this.TCZF_SC = TCZF_SC;
	}

	public String getTCZF_QT() {
		return TCZF_QT;
	}

	public void setTCZF_QT(String TCZF_QT) {
		this.TCZF_QT = TCZF_QT;
	}

	public String getTCZF_YZ() {
		return TCZF_YZ;
	}

	public void setTCZF_YZ(String TCZF_YZ) {
		this.TCZF_YZ = TCZF_YZ;
	}

	public String getTCZF_GL() {
		return TCZF_GL;
	}

	public void setTCZF_GL(String TCZF_GL) {
		this.TCZF_GL = TCZF_GL;
	}

	public String getTCZF_ZC() {
		return TCZF_ZC;
	}

	public void setTCZF_ZC(String TCZF_ZC) {
		this.TCZF_ZC = TCZF_ZC;
	}

	public String getZDNAME() {
		return ZDNAME;
	}

	public void setZDNAME(String ZDNAME) {
		this.ZDNAME = ZDNAME;
	}
}