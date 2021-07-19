
package com.buit.his.medinsuinterface.sh.dataitem.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类描述：民政帮困上报表A明细<br>
 *
 * @author beijunhua
 */
@ApiModel(value = "民政帮困上报表A明细")
public class MzbkAResp extends PageQuery {
	@ApiModelProperty(value = "序号")
	private String XH;
	@ApiModelProperty(value = "姓名")
	private String NAME;
	@ApiModelProperty(value = "凭证编号")
	private String YBID;
	@ApiModelProperty(value = "科别")
	private String KSNA;
	@ApiModelProperty(value = "就诊日期")
	private String JZDATE;
	@ApiModelProperty(value = "就诊次数")
	private String JZNUM;
	@ApiModelProperty(value = "交易费用")
	private String TOTFY;
	@ApiModelProperty(value = "分类在职")
	private String FLZZ;
	@ApiModelProperty(value = "分类退休")
	private String FLTX;
	@ApiModelProperty(value = "自负在职")
	private String ZFZZ;
	@ApiModelProperty(value = "自负退休")
	private String ZFTX;
	@ApiModelProperty(value = "当年账户退休")
	private String DNZHTX;
	@ApiModelProperty(value = "当年账户在职")
	private String DNZHZZ;
	@ApiModelProperty(value = "历年账户在职")
	private String LNZHZZ;
	@ApiModelProperty(value = "历年账户退休")
	private String LNZHTX;
	@ApiModelProperty(value = "附加在职")
	private String FJZZ;
	@ApiModelProperty(value = "附加退休")
	private String FJTX;

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

	public String getKSNA() {
		return KSNA;
	}

	public void setKSNA(String KSNA) {
		this.KSNA = KSNA;
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

	public String getTOTFY() {
		return TOTFY;
	}

	public void setTOTFY(String TOTFY) {
		this.TOTFY = TOTFY;
	}

	public String getFLZZ() {
		return FLZZ;
	}

	public void setFLZZ(String FLZZ) {
		this.FLZZ = FLZZ;
	}

	public String getFLTX() {
		return FLTX;
	}

	public void setFLTX(String FLTX) {
		this.FLTX = FLTX;
	}

	public String getZFZZ() {
		return ZFZZ;
	}

	public void setZFZZ(String ZFZZ) {
		this.ZFZZ = ZFZZ;
	}

	public String getZFTX() {
		return ZFTX;
	}

	public void setZFTX(String ZFTX) {
		this.ZFTX = ZFTX;
	}

	public String getDNZHTX() {
		return DNZHTX;
	}

	public void setDNZHTX(String DNZHTX) {
		this.DNZHTX = DNZHTX;
	}

	public String getDNZHZZ() {
		return DNZHZZ;
	}

	public void setDNZHZZ(String DNZHZZ) {
		this.DNZHZZ = DNZHZZ;
	}

	public String getLNZHZZ() {
		return LNZHZZ;
	}

	public void setLNZHZZ(String LNZHZZ) {
		this.LNZHZZ = LNZHZZ;
	}

	public String getLNZHTX() {
		return LNZHTX;
	}

	public void setLNZHTX(String LNZHTX) {
		this.LNZHTX = LNZHTX;
	}

	public String getFJZZ() {
		return FJZZ;
	}

	public void setFJZZ(String FJZZ) {
		this.FJZZ = FJZZ;
	}

	public String getFJTX() {
		return FJTX;
	}

	public void setFJTX(String FJTX) {
		this.FJTX = FJTX;
	}
}