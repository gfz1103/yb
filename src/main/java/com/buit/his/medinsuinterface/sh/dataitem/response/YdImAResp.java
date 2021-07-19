
package com.buit.his.medinsuinterface.sh.dataitem.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类描述：异地住院上报表A明细<br>
 *
 * @author beijunhua
 */
@ApiModel(value = "异地住院上报表A明细-明细")
public class YdImAResp extends PageQuery {
	@ApiModelProperty(value = "序号")
	private String XH;
	@ApiModelProperty(value = "住院号")
	private String ZYID;
	@ApiModelProperty(value = "病人姓名")
	private String NAME;
	@ApiModelProperty(value = "凭证编号")
	private String YBID;
	@ApiModelProperty(value = "出院日期")
	private String CYDATE;
	@ApiModelProperty(value = "住院天数")
	private String ZYDNUM;
	@ApiModelProperty(value = "项目")
	private String ZYJGBZ;
	@ApiModelProperty(value = "科室名称")
	private String KSNA;
	@ApiModelProperty(value = "费用总计")
	private String TOTFY;
	@ApiModelProperty(value = "个人现金支付(个人现金当)")
	private String GRXJD;
	@ApiModelProperty(value = "个人现金支付(个人现金去)")
	private String GRXJQ;
	@ApiModelProperty(value = "个人现金支付")
	private String GRXJZF;
	@ApiModelProperty(value = "账户资金支付(当)")
	private String GRZFLD;
	@ApiModelProperty(value = "账户资金支付(去)")
	private String GRZFLQ;
	@ApiModelProperty(value = "账户资金支付")
	private String GRZF;
	@ApiModelProperty(value = "统筹支付")
	private String TCZF;
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

	public String getZYJGBZ() {
		return ZYJGBZ;
	}

	public void setZYJGBZ(String ZYJGBZ) {
		this.ZYJGBZ = ZYJGBZ;
	}

	public String getKSNA() {
		return KSNA;
	}

	public void setKSNA(String KSNA) {
		this.KSNA = KSNA;
	}

	public String getTOTFY() {
		return TOTFY;
	}

	public void setTOTFY(String TOTFY) {
		this.TOTFY = TOTFY;
	}

	public String getGRXJD() {
		return GRXJD;
	}

	public void setGRXJD(String GRXJD) {
		this.GRXJD = GRXJD;
	}

	public String getGRXJQ() {
		return GRXJQ;
	}

	public void setGRXJQ(String GRXJQ) {
		this.GRXJQ = GRXJQ;
	}

	public String getGRZFLD() {
		return GRZFLD;
	}

	public void setGRZFLD(String GRZFLD) {
		this.GRZFLD = GRZFLD;
	}

	public String getGRZFLQ() {
		return GRZFLQ;
	}

	public void setGRZFLQ(String GRZFLQ) {
		this.GRZFLQ = GRZFLQ;
	}

	public String getTCZF() {
		return TCZF;
	}

	public void setTCZF(String TCZF) {
		this.TCZF = TCZF;
	}

	public String getZDNAME() {
		return ZDNAME;
	}

	public void setZDNAME(String ZDNAME) {
		this.ZDNAME = ZDNAME;
	}

	public String getGRXJZF() {
		return GRXJZF;
	}

	public void setGRXJZF(String GRXJZF) {
		this.GRXJZF = GRXJZF;
	}

	public String getGRZF() {
		return GRZF;
	}

	public void setGRZF(String GRZF) {
		this.GRZF = GRZF;
	}
}