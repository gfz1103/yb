
package com.buit.his.medinsuinterface.sh.dataitem.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类描述：工伤报销上报表B<br>
 *
 * @author beijunhua
 */
@ApiModel(value = "民政帮困上报表B明细")
public class MzbkBResp extends PageQuery {
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
	@ApiModelProperty(value = "费用总计")
	private String TOTFY;

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
}