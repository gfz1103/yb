
package com.buit.his.medinsuinterface.sh.dataitem.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 类描述：异地门诊医保<br>
 * 
 * @author beijunhua
 */
@ApiModel(value = "异地门诊医保")
public class YbbbYdybopResp extends PageQuery {
	@ApiModelProperty(value = "姓名")
	private String NAME;
	@ApiModelProperty(value = "保险编码(身份证)")
	private String BXID;
	@ApiModelProperty(value = "凭证号码")
	private String YBID;
	@ApiModelProperty(value = "科别编码")
	private String KSID;
	@ApiModelProperty(value = "科别名称")
	private String KSNA;
	@ApiModelProperty(value = "就诊日期")
	private String JZDATE;
	@ApiModelProperty(value = "就诊次数")
	private BigDecimal JZNUM;
	@ApiModelProperty(value = "在退标志")
	private String GRXZ;
	@ApiModelProperty(value = "总费额")
	private BigDecimal JYTOTFY;
	@ApiModelProperty(value = "自负")
	private BigDecimal GRXJ;
	@ApiModelProperty(value = "当年支付")
	private BigDecimal GRZFD;
	@ApiModelProperty(value = "历年支付")
	private BigDecimal GRZFL;
	@ApiModelProperty(value = "统筹基金支付")
	private BigDecimal TCZF;
	@ApiModelProperty(value = "医保结算范围费用总额")
	private BigDecimal TOTFY;
	@ApiModelProperty(value = "诊疗费")
	private BigDecimal FY00;
	@ApiModelProperty(value = "治疗费")
	private BigDecimal FY01;
	@ApiModelProperty(value = "手材费")
	private BigDecimal FY02;
	@ApiModelProperty(value = "检查费")
	private BigDecimal FY03;
	@ApiModelProperty(value = "化验费")
	private BigDecimal FY04;
	@ApiModelProperty(value = "摄片费")
	private BigDecimal FY05;
	@ApiModelProperty(value = "透视费")
	private BigDecimal FY06;
	@ApiModelProperty(value = "西药费")
	private BigDecimal FY07;
	@ApiModelProperty(value = "中成药费")
	private BigDecimal FY08;
	@ApiModelProperty(value = "中草药费")
	private BigDecimal FY09;
	@ApiModelProperty(value = "其它")
	private BigDecimal FY10;
	@ApiModelProperty(value = "自负合计")
	private BigDecimal FLGRZF;
	@ApiModelProperty(value = "自负诊疗费")
	private BigDecimal FY20;
	@ApiModelProperty(value = "自负治疗费")
	private BigDecimal FY21;
	@ApiModelProperty(value = "自负手材费")
	private BigDecimal FY22;
	@ApiModelProperty(value = "自负检查费")
	private BigDecimal FY23;
	@ApiModelProperty(value = "自负化验费")
	private BigDecimal FY24;
	@ApiModelProperty(value = "自负摄片费")
	private BigDecimal FY25;
	@ApiModelProperty(value = "自负透视费")
	private BigDecimal FY26;
	@ApiModelProperty(value = "自负西药费")
	private BigDecimal FY27;
	@ApiModelProperty(value = "自负中成药费")
	private BigDecimal FY28;
	@ApiModelProperty(value = "自负中草药费")
	private BigDecimal FY29;
	@ApiModelProperty(value = "自负其它")
	private BigDecimal FY30;
	@ApiModelProperty(value = "非医保个人自费")
	private BigDecimal ZF;
	@ApiModelProperty(value = "诊断编码")
	private String ZDICD;
	@ApiModelProperty(value = "诊断说明")
	private String ZDNAME;
	@ApiModelProperty(value = "保健情况")
	private String BJQK;
	@ApiModelProperty(value = "上报年月")
	private String DAA;
	@ApiModelProperty(value = "医院法人码")
	private String YYID;
	@ApiModelProperty(value = "流水号")
	private String LSH;


	public String getNAME() {
		return NAME;
	}

	public void setNAME(String NAME) {
		this.NAME = NAME;
	}

	public String getBXID() {
		return BXID;
	}

	public void setBXID(String BXID) {
		this.BXID = BXID;
	}

	public String getYBID() {
		return YBID;
	}

	public void setYBID(String YBID) {
		this.YBID = YBID;
	}

	public String getKSID() {
		return KSID;
	}

	public void setKSID(String KSID) {
		this.KSID = KSID;
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

	public BigDecimal getJZNUM() {
		return JZNUM;
	}

	public void setJZNUM(BigDecimal JZNUM) {
		this.JZNUM = JZNUM;
	}

	public String getGRXZ() {
		return GRXZ;
	}

	public void setGRXZ(String GRXZ) {
		this.GRXZ = GRXZ;
	}

	public BigDecimal getJYTOTFY() {
		return JYTOTFY;
	}

	public void setJYTOTFY(BigDecimal JYTOTFY) {
		this.JYTOTFY = JYTOTFY;
	}

	public BigDecimal getGRXJ() {
		return GRXJ;
	}

	public void setGRXJ(BigDecimal GRXJ) {
		this.GRXJ = GRXJ;
	}

	public BigDecimal getGRZFD() {
		return GRZFD;
	}

	public void setGRZFD(BigDecimal GRZFD) {
		this.GRZFD = GRZFD;
	}

	public BigDecimal getGRZFL() {
		return GRZFL;
	}

	public void setGRZFL(BigDecimal GRZFL) {
		this.GRZFL = GRZFL;
	}

	public BigDecimal getTCZF() {
		return TCZF;
	}

	public void setTCZF(BigDecimal TCZF) {
		this.TCZF = TCZF;
	}

	public BigDecimal getTOTFY() {
		return TOTFY;
	}

	public void setTOTFY(BigDecimal TOTFY) {
		this.TOTFY = TOTFY;
	}

	public BigDecimal getFY00() {
		return FY00;
	}

	public void setFY00(BigDecimal FY00) {
		this.FY00 = FY00;
	}

	public BigDecimal getFY01() {
		return FY01;
	}

	public void setFY01(BigDecimal FY01) {
		this.FY01 = FY01;
	}

	public BigDecimal getFY02() {
		return FY02;
	}

	public void setFY02(BigDecimal FY02) {
		this.FY02 = FY02;
	}

	public BigDecimal getFY03() {
		return FY03;
	}

	public void setFY03(BigDecimal FY03) {
		this.FY03 = FY03;
	}

	public BigDecimal getFY04() {
		return FY04;
	}

	public void setFY04(BigDecimal FY04) {
		this.FY04 = FY04;
	}

	public BigDecimal getFY05() {
		return FY05;
	}

	public void setFY05(BigDecimal FY05) {
		this.FY05 = FY05;
	}

	public BigDecimal getFY06() {
		return FY06;
	}

	public void setFY06(BigDecimal FY06) {
		this.FY06 = FY06;
	}

	public BigDecimal getFY07() {
		return FY07;
	}

	public void setFY07(BigDecimal FY07) {
		this.FY07 = FY07;
	}

	public BigDecimal getFY08() {
		return FY08;
	}

	public void setFY08(BigDecimal FY08) {
		this.FY08 = FY08;
	}

	public BigDecimal getFY09() {
		return FY09;
	}

	public void setFY09(BigDecimal FY09) {
		this.FY09 = FY09;
	}

	public BigDecimal getFY10() {
		return FY10;
	}

	public void setFY10(BigDecimal FY10) {
		this.FY10 = FY10;
	}

	public BigDecimal getFLGRZF() {
		return FLGRZF;
	}

	public void setFLGRZF(BigDecimal FLGRZF) {
		this.FLGRZF = FLGRZF;
	}

	public BigDecimal getFY20() {
		return FY20;
	}

	public void setFY20(BigDecimal FY20) {
		this.FY20 = FY20;
	}

	public BigDecimal getFY21() {
		return FY21;
	}

	public void setFY21(BigDecimal FY21) {
		this.FY21 = FY21;
	}

	public BigDecimal getFY22() {
		return FY22;
	}

	public void setFY22(BigDecimal FY22) {
		this.FY22 = FY22;
	}

	public BigDecimal getFY23() {
		return FY23;
	}

	public void setFY23(BigDecimal FY23) {
		this.FY23 = FY23;
	}

	public BigDecimal getFY24() {
		return FY24;
	}

	public void setFY24(BigDecimal FY24) {
		this.FY24 = FY24;
	}

	public BigDecimal getFY25() {
		return FY25;
	}

	public void setFY25(BigDecimal FY25) {
		this.FY25 = FY25;
	}

	public BigDecimal getFY26() {
		return FY26;
	}

	public void setFY26(BigDecimal FY26) {
		this.FY26 = FY26;
	}

	public BigDecimal getFY27() {
		return FY27;
	}

	public void setFY27(BigDecimal FY27) {
		this.FY27 = FY27;
	}

	public BigDecimal getFY28() {
		return FY28;
	}

	public void setFY28(BigDecimal FY28) {
		this.FY28 = FY28;
	}

	public BigDecimal getFY29() {
		return FY29;
	}

	public void setFY29(BigDecimal FY29) {
		this.FY29 = FY29;
	}

	public BigDecimal getFY30() {
		return FY30;
	}

	public void setFY30(BigDecimal FY30) {
		this.FY30 = FY30;
	}

	public BigDecimal getZF() {
		return ZF;
	}

	public void setZF(BigDecimal ZF) {
		this.ZF = ZF;
	}

	public String getZDICD() {
		return ZDICD;
	}

	public void setZDICD(String ZDICD) {
		this.ZDICD = ZDICD;
	}

	public String getZDNAME() {
		return ZDNAME;
	}

	public void setZDNAME(String ZDNAME) {
		this.ZDNAME = ZDNAME;
	}

	public String getBJQK() {
		return BJQK;
	}

	public void setBJQK(String BJQK) {
		this.BJQK = BJQK;
	}

	public String getDAA() {
		return DAA;
	}

	public void setDAA(String DAA) {
		this.DAA = DAA;
	}

	public String getYYID() {
		return YYID;
	}

	public void setYYID(String YYID) {
		this.YYID = YYID;
	}

	public String getLSH() {
		return LSH;
	}

	public void setLSH(String LSH) {
		this.LSH = LSH;
	}
}