
package com.buit.his.medinsuinterface.sh.dataitem.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类描述：城镇普通上报表B<br>
 *
 * @author beijunhua
 */
@ApiModel(value = "城镇普通上报表B")
public class CzptBResp extends PageQuery {
	@ApiModelProperty(value = "序号")
	private String XH;
	@ApiModelProperty(value = "凭证编号")
	private String YBID;
	@ApiModelProperty(value = "诊断")
	private String ZDICD;
	@ApiModelProperty(value = "诊疗费")
	private String FY00;
	@ApiModelProperty(value = "治疗费")
	private String FY01;
	@ApiModelProperty(value = "手材费")
	private String FY02;
	@ApiModelProperty(value = "检查费")
	private String FY03;
	@ApiModelProperty(value = "化验费")
	private String FY04;
	@ApiModelProperty(value = "摄片费")
	private String FY05;
	@ApiModelProperty(value = "透视费")
	private String FY06;
	@ApiModelProperty(value = "西药费")
	private String FY07;
	@ApiModelProperty(value = "中成药费")
	private String FY08;
	@ApiModelProperty(value = "中草药费")
	private String FY09;
	@ApiModelProperty(value = "其它")
	private String FY10;
	@ApiModelProperty(value = "医保内总费用")
	private String TOTFY;

	@ApiModelProperty(value = "诊疗费_all")
	private String FY00_ALL;
	@ApiModelProperty(value = "治疗费_all")
	private String FY01_ALL;
	@ApiModelProperty(value = "")
	private String FY02_ALL;
	@ApiModelProperty(value = "")
	private String FY03_ALL;
	@ApiModelProperty(value = "")
	private String FY04_ALL;
	@ApiModelProperty(value = "")
	private String FY05_ALL;
	@ApiModelProperty(value = "")
	private String FY06_ALL;
	@ApiModelProperty(value = "")
	private String FY07_ALL;
	@ApiModelProperty(value = "")
	private String FY08_ALL;
	@ApiModelProperty(value = "")
	private String FY09_ALL;
	@ApiModelProperty(value = "")
	private String FY10_ALL;
	@ApiModelProperty(value = "")
	private String TOTFY_ALL;

	public String getXH() {
		return XH;
	}

	public void setXH(String XH) {
		this.XH = XH;
	}

	public String getYBID() {
		return YBID;
	}

	public void setYBID(String YBID) {
		this.YBID = YBID;
	}

	public String getZDICD() {
		return ZDICD;
	}

	public void setZDICD(String ZDICD) {
		this.ZDICD = ZDICD;
	}

	public String getFY00() {
		return FY00;
	}

	public void setFY00(String FY00) {
		this.FY00 = FY00;
	}

	public String getFY01() {
		return FY01;
	}

	public void setFY01(String FY01) {
		this.FY01 = FY01;
	}

	public String getFY02() {
		return FY02;
	}

	public void setFY02(String FY02) {
		this.FY02 = FY02;
	}

	public String getFY03() {
		return FY03;
	}

	public void setFY03(String FY03) {
		this.FY03 = FY03;
	}

	public String getFY04() {
		return FY04;
	}

	public void setFY04(String FY04) {
		this.FY04 = FY04;
	}

	public String getFY05() {
		return FY05;
	}

	public void setFY05(String FY05) {
		this.FY05 = FY05;
	}

	public String getFY06() {
		return FY06;
	}

	public void setFY06(String FY06) {
		this.FY06 = FY06;
	}

	public String getFY07() {
		return FY07;
	}

	public void setFY07(String FY07) {
		this.FY07 = FY07;
	}

	public String getFY08() {
		return FY08;
	}

	public void setFY08(String FY08) {
		this.FY08 = FY08;
	}

	public String getFY09() {
		return FY09;
	}

	public void setFY09(String FY09) {
		this.FY09 = FY09;
	}

	public String getFY10() {
		return FY10;
	}

	public void setFY10(String FY10) {
		this.FY10 = FY10;
	}

	public String getTOTFY() {
		return TOTFY;
	}

	public void setTOTFY(String TOTFY) {
		this.TOTFY = TOTFY;
	}

	public String getFY00_ALL() {
		return FY00_ALL;
	}

	public void setFY00_ALL(String FY00_ALL) {
		this.FY00_ALL = FY00_ALL;
	}

	public String getFY01_ALL() {
		return FY01_ALL;
	}

	public void setFY01_ALL(String FY01_ALL) {
		this.FY01_ALL = FY01_ALL;
	}

	public String getFY02_ALL() {
		return FY02_ALL;
	}

	public void setFY02_ALL(String FY02_ALL) {
		this.FY02_ALL = FY02_ALL;
	}

	public String getFY03_ALL() {
		return FY03_ALL;
	}

	public void setFY03_ALL(String FY03_ALL) {
		this.FY03_ALL = FY03_ALL;
	}

	public String getFY04_ALL() {
		return FY04_ALL;
	}

	public void setFY04_ALL(String FY04_ALL) {
		this.FY04_ALL = FY04_ALL;
	}

	public String getFY05_ALL() {
		return FY05_ALL;
	}

	public void setFY05_ALL(String FY05_ALL) {
		this.FY05_ALL = FY05_ALL;
	}

	public String getFY06_ALL() {
		return FY06_ALL;
	}

	public void setFY06_ALL(String FY06_ALL) {
		this.FY06_ALL = FY06_ALL;
	}

	public String getFY07_ALL() {
		return FY07_ALL;
	}

	public void setFY07_ALL(String FY07_ALL) {
		this.FY07_ALL = FY07_ALL;
	}

	public String getFY08_ALL() {
		return FY08_ALL;
	}

	public void setFY08_ALL(String FY08_ALL) {
		this.FY08_ALL = FY08_ALL;
	}

	public String getFY09_ALL() {
		return FY09_ALL;
	}

	public void setFY09_ALL(String FY09_ALL) {
		this.FY09_ALL = FY09_ALL;
	}

	public String getFY10_ALL() {
		return FY10_ALL;
	}

	public void setFY10_ALL(String FY10_ALL) {
		this.FY10_ALL = FY10_ALL;
	}

	public String getTOTFY_ALL() {
		return TOTFY_ALL;
	}

	public void setTOTFY_ALL(String TOTFY_ALL) {
		this.TOTFY_ALL = TOTFY_ALL;
	}
}