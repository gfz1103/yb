
package com.buit.his.medinsuinterface.sh.dataitem.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类描述：上海医保结算2号表互助帮困<br>
 * 
 * @author 宋宇
 */
@ApiModel(value = "上海医疗保险费用结算申报二号表工伤部分")
public class ShFeehzEhbhzbkResp extends PageQuery {
	@ApiModelProperty(value = "类型")
	private String LX;
	@ApiModelProperty(value = "医保机构代码")
	private String JGID;
	@ApiModelProperty(value = "个人性质")
	private String GRXZ;
	@ApiModelProperty(value = "人次")
	private String RC;
	@ApiModelProperty(value = "互助帮困补贴支付")
	private String btzf;
	@ApiModelProperty(value = "互助帮困补助支付")
	private String bzzf;
	@ApiModelProperty(value = "互助帮困支付小计")
	private String hzbkhj;


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

	public String getRC() {
		return RC;
	}

	public void setRC(String RC) {
		this.RC = RC;
	}

	public String getJGID() {
		return JGID;
	}

	public void setJGID(String JGID) {
		this.JGID = JGID;
	}

	public String getBtzf() {
		return btzf;
	}

	public void setBtzf(String btzf) {
		this.btzf = btzf;
	}

	public String getBzzf() {
		return bzzf;
	}

	public void setBzzf(String bzzf) {
		this.bzzf = bzzf;
	}

	public String getHzbkhj() {
		return hzbkhj;
	}

	public void setHzbkhj(String hzbkhj) {
		this.hzbkhj = hzbkhj;
	}

}