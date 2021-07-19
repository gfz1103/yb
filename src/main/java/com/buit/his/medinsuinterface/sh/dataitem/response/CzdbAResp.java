
package com.buit.his.medinsuinterface.sh.dataitem.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类描述：城镇大病上报表<br>
 *
 * @author beijunhua
 */
@ApiModel(value = "城镇大病上报表A")
public class CzdbAResp extends PageQuery {
	@ApiModelProperty(value = "序号")
	private String XH;
	@ApiModelProperty(value = "姓名")
	private String NAME;
	@ApiModelProperty(value = "凭证编号")
	private String YBID;
	@ApiModelProperty(value = "就诊日期")
	private String JZDATE;
	@ApiModelProperty(value = "就诊次数")
	private String JZNUM;
	@ApiModelProperty(value = "大病项目")
	private String DBXM;
	@ApiModelProperty(value = "科别")
	private String KSNA;
	@ApiModelProperty(value = "交易费用")
	private String JYTOTFY;

	@ApiModelProperty(value = "分类在职")
	private String FLGRZF_ZZ;
	@ApiModelProperty(value = "分类退休")
	private String FLGRZF_TX;
	@ApiModelProperty(value = "自负在职")
	private String GRXJ_ZZ;
	@ApiModelProperty(value = "自负退休")
	private String GRXJ_TX;
	@ApiModelProperty(value = "当年账户退休")
	private String TCZF_ZZ;
	@ApiModelProperty(value = "当年账户在职")
	private String TCZF_TX;
	@ApiModelProperty(value = "历年账户在职")
	private String GRZFL_ZZ;
	@ApiModelProperty(value = "历年账户退休")
	private String GRZFL_TX;
	@ApiModelProperty(value = "附加在职")
	private String FJBX_ZZ;
	@ApiModelProperty(value = "附加退休")
	private String FJBX_TX;

	public String getXH() {
		return XH;
	}

	public void setXH(String XH) {
		this.XH = XH;
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

	public String getJZDATE() {
		return JZDATE;
	}

	public void setJZDATE(String JZDATE) {
		this.JZDATE = JZDATE;
	}

	public String getJZNUM() {
		return JZNUM;
	}

	public void setJZNUM(String JZNUM) {
		this.JZNUM = JZNUM;
	}

	public String getDBXM() {
		return DBXM;
	}

	public void setDBXM(String DBXM) {
		this.DBXM = DBXM;
	}

	public String getKSNA() {
		return KSNA;
	}

	public void setKSNA(String KSNA) {
		this.KSNA = KSNA;
	}

	public String getJYTOTFY() {
		return JYTOTFY;
	}

	public void setJYTOTFY(String JYTOTFY) {
		this.JYTOTFY = JYTOTFY;
	}

	public String getFLGRZF_ZZ() {
		return FLGRZF_ZZ;
	}

	public void setFLGRZF_ZZ(String FLGRZF_ZZ) {
		this.FLGRZF_ZZ = FLGRZF_ZZ;
	}

	public String getFLGRZF_TX() {
		return FLGRZF_TX;
	}

	public void setFLGRZF_TX(String FLGRZF_TX) {
		this.FLGRZF_TX = FLGRZF_TX;
	}

	public String getGRXJ_ZZ() {
		return GRXJ_ZZ;
	}

	public void setGRXJ_ZZ(String GRXJ_ZZ) {
		this.GRXJ_ZZ = GRXJ_ZZ;
	}

	public String getGRXJ_TX() {
		return GRXJ_TX;
	}

	public void setGRXJ_TX(String GRXJ_TX) {
		this.GRXJ_TX = GRXJ_TX;
	}

	public String getTCZF_ZZ() {
		return TCZF_ZZ;
	}

	public void setTCZF_ZZ(String TCZF_ZZ) {
		this.TCZF_ZZ = TCZF_ZZ;
	}

	public String getTCZF_TX() {
		return TCZF_TX;
	}

	public void setTCZF_TX(String TCZF_TX) {
		this.TCZF_TX = TCZF_TX;
	}

	public String getGRZFL_ZZ() {
		return GRZFL_ZZ;
	}

	public void setGRZFL_ZZ(String GRZFL_ZZ) {
		this.GRZFL_ZZ = GRZFL_ZZ;
	}

	public String getGRZFL_TX() {
		return GRZFL_TX;
	}

	public void setGRZFL_TX(String GRZFL_TX) {
		this.GRZFL_TX = GRZFL_TX;
	}

	public String getFJBX_ZZ() {
		return FJBX_ZZ;
	}

	public void setFJBX_ZZ(String FJBX_ZZ) {
		this.FJBX_ZZ = FJBX_ZZ;
	}

	public String getFJBX_TX() {
		return FJBX_TX;
	}

	public void setFJBX_TX(String FJBX_TX) {
		this.FJBX_TX = FJBX_TX;
	}
}