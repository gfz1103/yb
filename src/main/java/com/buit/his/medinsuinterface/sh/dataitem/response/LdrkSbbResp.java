
package com.buit.his.medinsuinterface.sh.dataitem.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类描述：流动人口上报表<br>
 * 
 * @author beijunhua
 */
@ApiModel(value = "流动人口上报表")
public class LdrkSbbResp extends PageQuery {
	@ApiModelProperty(value = "姓名")
	private String NAME;
	@ApiModelProperty(value = "卡号")
	private String CARDNO;
	@ApiModelProperty(value = "暂住证号")
	private String ZZZH;
	@ApiModelProperty(value = "就诊日期")
	private String JZDATE;
	@ApiModelProperty(value = "就诊次数")
	private String JZNUM;
	@ApiModelProperty(value = "在退标志、1在2退")
	private String GRXZ;
	@ApiModelProperty(value = "交易总费额")
	private String ZFY;
	@ApiModelProperty(value = "总折扣费用")
	private String ZZKFY;
	@ApiModelProperty(value = "总个人支付")
	private String ZGRZF;
	@ApiModelProperty(value = "诊疗费折扣")
	private String FY00;
	@ApiModelProperty(value = "治疗费折扣")
	private String FY01;
	@ApiModelProperty(value = "手材费折扣")
	private String FY02;
	@ApiModelProperty(value = "检查费折扣")
	private String FY03;
	@ApiModelProperty(value = "化验费折扣")
	private String FY04;
	@ApiModelProperty(value = "摄片费折扣")
	private String FY05;
	@ApiModelProperty(value = "透视费折扣")
	private String FY06;
	@ApiModelProperty(value = "西药费折扣")
	private String FY07;
	@ApiModelProperty(value = "中成药费折扣")
	private String FY08;
	@ApiModelProperty(value = "中草药费折扣")
	private String FY09;
	@ApiModelProperty(value = "其他折扣")
	private String FY10;
	@ApiModelProperty(value = "自负合计")
	private String FLGRZF;
	@ApiModelProperty(value = "诊疗费个人")
	private String FY20;
	@ApiModelProperty(value = "治疗费个人")
	private String FY21;
	@ApiModelProperty(value = "手材费个人")
	private String FY22;
	@ApiModelProperty(value = "检查费个人")
	private String FY23;
	@ApiModelProperty(value = "化验费个人")
	private String FY24;
	@ApiModelProperty(value = "摄片费个人")
	private String FY25;
	@ApiModelProperty(value = "透视费个人")
	private String FY26;
	@ApiModelProperty(value = "西药费个人")
	private String FY27;
	@ApiModelProperty(value = "中成药费个人")
	private String FY28;
	@ApiModelProperty(value = "中草药费个人")
	private String FY29;
	@ApiModelProperty(value = "其他个人")
	private String FY30;
	@ApiModelProperty(value = "上报年月")
	private String DAA;

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String NAME) {
		this.NAME = NAME;
	}

	public String getCARDNO() {
		return CARDNO;
	}

	public void setCARDNO(String CARDNO) {
		this.CARDNO = CARDNO;
	}

	public String getZZZH() {
		return ZZZH;
	}

	public void setZZZH(String ZZZH) {
		this.ZZZH = ZZZH;
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

	public String getGRXZ() {
		return GRXZ;
	}

	public void setGRXZ(String GRXZ) {
		this.GRXZ = GRXZ;
	}

	public String getZFY() {
		return ZFY;
	}

	public void setZFY(String ZFY) {
		this.ZFY = ZFY;
	}

	public String getZZKFY() {
		return ZZKFY;
	}

	public void setZZKFY(String ZZKFY) {
		this.ZZKFY = ZZKFY;
	}

	public String getZGRZF() {
		return ZGRZF;
	}

	public void setZGRZF(String ZGRZF) {
		this.ZGRZF = ZGRZF;
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

	public String getFLGRZF() {
		return FLGRZF;
	}

	public void setFLGRZF(String FLGRZF) {
		this.FLGRZF = FLGRZF;
	}

	public String getFY20() {
		return FY20;
	}

	public void setFY20(String FY20) {
		this.FY20 = FY20;
	}

	public String getFY21() {
		return FY21;
	}

	public void setFY21(String FY21) {
		this.FY21 = FY21;
	}

	public String getFY22() {
		return FY22;
	}

	public void setFY22(String FY22) {
		this.FY22 = FY22;
	}

	public String getFY23() {
		return FY23;
	}

	public void setFY23(String FY23) {
		this.FY23 = FY23;
	}

	public String getFY24() {
		return FY24;
	}

	public void setFY24(String FY24) {
		this.FY24 = FY24;
	}

	public String getFY25() {
		return FY25;
	}

	public void setFY25(String FY25) {
		this.FY25 = FY25;
	}

	public String getFY26() {
		return FY26;
	}

	public void setFY26(String FY26) {
		this.FY26 = FY26;
	}

	public String getFY27() {
		return FY27;
	}

	public void setFY27(String FY27) {
		this.FY27 = FY27;
	}

	public String getFY28() {
		return FY28;
	}

	public void setFY28(String FY28) {
		this.FY28 = FY28;
	}

	public String getFY29() {
		return FY29;
	}

	public void setFY29(String FY29) {
		this.FY29 = FY29;
	}

	public String getFY30() {
		return FY30;
	}

	public void setFY30(String FY30) {
		this.FY30 = FY30;
	}

	public String getDAA() {
		return DAA;
	}

	public void setDAA(String DAA) {
		this.DAA = DAA;
	}
}