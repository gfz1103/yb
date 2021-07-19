
package com.buit.his.medinsuinterface.sh.dataitem.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类描述：城镇普通上报表<br>
 *
 * @author beijunhua
 */
@ApiModel(value = "城镇普通上报表A")
public class CzptAResp extends PageQuery {
	@ApiModelProperty(value = "序号")
	private String XH;
	@ApiModelProperty(value = "姓名")
	private String BRXM;
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

	@ApiModelProperty(value = "就诊次数_ALL")
	private String JZNUM_ALL;
	@ApiModelProperty(value = "交易费用_ALL")
	private String TOTFY_ALL;
	@ApiModelProperty(value = "分类在职_ALL")
	private String FLZZ_ALL;
	@ApiModelProperty(value = "分类退休_ALL")
	private String FLTX_ALL;
	@ApiModelProperty(value = "自负在职_ALL")
	private String ZFZZ_ALL;
	@ApiModelProperty(value = "自负退休_ALL")
	private String ZFTX_ALL;
	@ApiModelProperty(value = "当年账户在职_ALL")
	private String DNZHZZ_ALL;
	@ApiModelProperty(value = "当年账户退休_aLL")
	private String DNZHTX_ALL;
	@ApiModelProperty(value = "历年在职_ALL")
	private String LNZHZZ_ALL;
	@ApiModelProperty(value = "历年账户退休_ALL")
	private String LNZHTX_ALL;
	@ApiModelProperty(value = "附加在职_ALL")
	private String FJZZ_ALL;
	@ApiModelProperty(value = "附加退休_aLL")
	private String FJTX_ALL;

	public String getXH() {
		return XH;
	}

	public void setXH(String XH) {
		this.XH = XH;
	}

	public String getBRXM() {
		return BRXM;
	}

	public void setBRXM(String BRXM) {
		this.BRXM = BRXM;
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

	public String getJZNUM_ALL() {
		return JZNUM_ALL;
	}

	public void setJZNUM_ALL(String JZNUM_ALL) {
		this.JZNUM_ALL = JZNUM_ALL;
	}

	public String getTOTFY_ALL() {
		return TOTFY_ALL;
	}

	public void setTOTFY_ALL(String TOTFY_ALL) {
		this.TOTFY_ALL = TOTFY_ALL;
	}

	public String getFLZZ_ALL() {
		return FLZZ_ALL;
	}

	public void setFLZZ_ALL(String FLZZ_ALL) {
		this.FLZZ_ALL = FLZZ_ALL;
	}

	public String getFLTX_ALL() {
		return FLTX_ALL;
	}

	public void setFLTX_ALL(String FLTX_ALL) {
		this.FLTX_ALL = FLTX_ALL;
	}

	public String getZFZZ_ALL() {
		return ZFZZ_ALL;
	}

	public void setZFZZ_ALL(String ZFZZ_ALL) {
		this.ZFZZ_ALL = ZFZZ_ALL;
	}

	public String getZFTX_ALL() {
		return ZFTX_ALL;
	}

	public void setZFTX_ALL(String ZFTX_ALL) {
		this.ZFTX_ALL = ZFTX_ALL;
	}

	public String getDNZHZZ_ALL() {
		return DNZHZZ_ALL;
	}

	public void setDNZHZZ_ALL(String DNZHZZ_ALL) {
		this.DNZHZZ_ALL = DNZHZZ_ALL;
	}

	public String getDNZHTX_ALL() {
		return DNZHTX_ALL;
	}

	public void setDNZHTX_ALL(String DNZHTX_ALL) {
		this.DNZHTX_ALL = DNZHTX_ALL;
	}

	public String getLNZHZZ_ALL() {
		return LNZHZZ_ALL;
	}

	public void setLNZHZZ_ALL(String LNZHZZ_ALL) {
		this.LNZHZZ_ALL = LNZHZZ_ALL;
	}

	public String getLNZHTX_ALL() {
		return LNZHTX_ALL;
	}

	public void setLNZHTX_ALL(String LNZHTX_ALL) {
		this.LNZHTX_ALL = LNZHTX_ALL;
	}

	public String getFJZZ_ALL() {
		return FJZZ_ALL;
	}

	public void setFJZZ_ALL(String FJZZ_ALL) {
		this.FJZZ_ALL = FJZZ_ALL;
	}

	public String getFJTX_ALL() {
		return FJTX_ALL;
	}

	public void setFJTX_ALL(String FJTX_ALL) {
		this.FJTX_ALL = FJTX_ALL;
	}
}