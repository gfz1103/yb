
package com.buit.his.medinsuinterface.sh.dataitem.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类描述：门诊异地A上报表明细<br>
 *
 * @author beijunhua
 */
@ApiModel(value = "门诊异地A上报表明细")
public class YdopAResp extends PageQuery {
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
	private String JYTOTFY;
	@ApiModelProperty(value = "个人现金")
	private String GRXJ;
	@ApiModelProperty(value = "账户资金支付")
	private String ZHZJZF;
	@ApiModelProperty(value = "统筹基金支付")
	private String TCJJZF;

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

	public String getJYTOTFY() {
		return JYTOTFY;
	}

	public void setJYTOTFY(String JYTOTFY) {
		this.JYTOTFY = JYTOTFY;
	}

	public String getGRXJ() {
		return GRXJ;
	}

	public void setGRXJ(String GRXJ) {
		this.GRXJ = GRXJ;
	}

	public String getZHZJZF() {
		return ZHZJZF;
	}

	public void setZHZJZF(String ZHZJZF) {
		this.ZHZJZF = ZHZJZF;
	}

	public String getTCJJZF() {
		return TCJJZF;
	}

	public void setTCJJZF(String TCJJZF) {
		this.TCJJZF = TCJJZF;
	}
}