
package com.buit.his.medinsuinterface.sh.dataitem.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类描述：费用申报汇总<br>
 * 
 * @author beijunhua
 */
@ApiModel(value = "上海医疗保险费用结算申报汇总表")
public class ShFeehzResp extends PageQuery {
	@ApiModelProperty(value = "类型")
	private String LX;
	@ApiModelProperty(value = "医保机构代码")
	private String JGID;
	@ApiModelProperty(value = "个人性质")
	private String GRXZ;
	@ApiModelProperty(value = "人次")
	private int RC;
	@ApiModelProperty(value = "当年账户资金支付")
	private String DNZHZJZF;
	@ApiModelProperty(value = "历年账户资金支付")
	private String LNZHZJZF;
	@ApiModelProperty(value = "统筹基金支付")
	private String TCJJZF;
	@ApiModelProperty(value = "附加基金支付")
	private String FJJJZF;
	@ApiModelProperty(value = "医保支付小计")
	private String YBZFXJ;

	public String getLX() {
		return LX;
	}

	public void setLX(String LX) {
		this.LX = LX;
	}

	public String getGRXZ() {
		return GRXZ;
	}

	public void setGRXZ(String GRXZ) {
		this.GRXZ = GRXZ;
	}

	public int getRC() {
		return RC;
	}

	public void setRC(int RC) {
		this.RC = RC;
	}

	public String getDNZHZJZF() {
		return DNZHZJZF;
	}

	public void setDNZHZJZF(String DNZHZJZF) {
		this.DNZHZJZF = DNZHZJZF;
	}

	public String getLNZHZJZF() {
		return LNZHZJZF;
	}

	public void setLNZHZJZF(String LNZHZJZF) {
		this.LNZHZJZF = LNZHZJZF;
	}

	public String getTCJJZF() {
		return TCJJZF;
	}

	public void setTCJJZF(String TCJJZF) {
		this.TCJJZF = TCJJZF;
	}

	public String getFJJJZF() {
		return FJJJZF;
	}

	public void setFJJJZF(String FJJJZF) {
		this.FJJJZF = FJJJZF;
	}

	public String getYBZFXJ() {
		return YBZFXJ;
	}

	public void setYBZFXJ(String YBZFXJ) {
		this.YBZFXJ = YBZFXJ;
	}

	public String getJGID() {
		return JGID;
	}

	public void setJGID(String JGID) {
		this.JGID = JGID;
	}
}