
package com.buit.his.medinsuinterface.sh.dataitem.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类描述：城镇居民住院上报表A明细<br>
 *
 * @author beijunhua
 */
@ApiModel(value = "城镇居民住院上报表A-明细")
public class CzjmAZyResp extends PageQuery {
	@ApiModelProperty(value = "序号")
	private String XH;
	@ApiModelProperty(value = "住院号")
	private String ZYID;
	@ApiModelProperty(value = "姓名")
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
	@ApiModelProperty(value = "医保支付")
	private String YBZF;
	@ApiModelProperty(value = "分类自负")
	private String FLGRZF;
	@ApiModelProperty(value = "自负")
	private String GRZF;
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

	public String getYBZF() {
		return YBZF;
	}

	public void setYBZF(String YBZF) {
		this.YBZF = YBZF;
	}

	public String getFLGRZF() {
		return FLGRZF;
	}

	public void setFLGRZF(String FLGRZF) {
		this.FLGRZF = FLGRZF;
	}

	public String getGRZF() {
		return GRZF;
	}

	public void setGRZF(String GRZF) {
		this.GRZF = GRZF;
	}

	public String getZDNAME() {
		return ZDNAME;
	}

	public void setZDNAME(String ZDNAME) {
		this.ZDNAME = ZDNAME;
	}
}