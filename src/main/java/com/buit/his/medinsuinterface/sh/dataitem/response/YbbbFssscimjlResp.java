
package com.buit.his.medinsuinterface.sh.dataitem.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类描述：非实时结算住院结算记录库<br>
 * 
 * @author beijunhua
 */
@ApiModel(value = "非实时结算住院结算记录库")
public class YbbbFssscimjlResp extends PageQuery {
	@ApiModelProperty(value = "医疗机构代码")
	private String YYID;
	@ApiModelProperty(value = "人员类型")
	private String RYLX;
	@ApiModelProperty(value = "凭证号码")
	private String YBID;
	@ApiModelProperty(value = "住院号")
	private String ZYID;
	@ApiModelProperty(value = "住院天数")
	private String ZYTS;
	@ApiModelProperty(value = "住院结算开始日期")
	private String JSKSRQ;
	@ApiModelProperty(value = "入院诊断编码")
	private String RYZDBM;
	@ApiModelProperty(value = "入院诊断说明")
	private String RYZDMC;
	@ApiModelProperty(value = "住院结算结束日期")
	private String JSJSRQ;
	@ApiModelProperty(value = "急观住院标志")
	private String ZYJGBZ;
	@ApiModelProperty(value = "出院标志")
	private String CYBZ;
	@ApiModelProperty(value = "出院诊断编码")
	private String CYZDBM;
	@ApiModelProperty(value = "出院诊断说明")
	private String CYZDMC;
	@ApiModelProperty(value = "治疗结果代码")
	private String ZLJG;

	public String getYYID() {
		return YYID;
	}

	public void setYYID(String YYID) {
		this.YYID = YYID;
	}

	public String getRYLX() {
		return RYLX;
	}

	public void setRYLX(String RYLX) {
		this.RYLX = RYLX;
	}

	public String getYBID() {
		return YBID;
	}

	public void setYBID(String YBID) {
		this.YBID = YBID;
	}

	public String getZYID() {
		return ZYID;
	}

	public void setZYID(String ZYID) {
		this.ZYID = ZYID;
	}

	public String getZYTS() {
		return ZYTS;
	}

	public void setZYTS(String ZYTS) {
		this.ZYTS = ZYTS;
	}

	public String getJSKSRQ() {
		return JSKSRQ;
	}

	public void setJSKSRQ(String JSKSRQ) {
		this.JSKSRQ = JSKSRQ;
	}

	public String getRYZDBM() {
		return RYZDBM;
	}

	public void setRYZDBM(String RYZDBM) {
		this.RYZDBM = RYZDBM;
	}

	public String getRYZDMC() {
		return RYZDMC;
	}

	public void setRYZDMC(String RYZDMC) {
		this.RYZDMC = RYZDMC;
	}

	public String getJSJSRQ() {
		return JSJSRQ;
	}

	public void setJSJSRQ(String JSJSRQ) {
		this.JSJSRQ = JSJSRQ;
	}

	public String getZYJGBZ() {
		return ZYJGBZ;
	}

	public void setZYJGBZ(String ZYJGBZ) {
		this.ZYJGBZ = ZYJGBZ;
	}

	public String getCYBZ() {
		return CYBZ;
	}

	public void setCYBZ(String CYBZ) {
		this.CYBZ = CYBZ;
	}

	public String getCYZDBM() {
		return CYZDBM;
	}

	public void setCYZDBM(String CYZDBM) {
		this.CYZDBM = CYZDBM;
	}

	public String getCYZDMC() {
		return CYZDMC;
	}

	public void setCYZDMC(String CYZDMC) {
		this.CYZDMC = CYZDMC;
	}

	public String getZLJG() {
		return ZLJG;
	}

	public void setZLJG(String ZLJG) {
		this.ZLJG = ZLJG;
	}
}