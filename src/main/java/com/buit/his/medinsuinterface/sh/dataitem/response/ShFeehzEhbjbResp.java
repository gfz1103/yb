
package com.buit.his.medinsuinterface.sh.dataitem.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类描述：上海医保结算2号表居保部分<br>
 * 
 * @author 宋宇
 */
@ApiModel(value = "上海医疗保险费用结算申报二号表居保部分")
public class ShFeehzEhbjbResp extends PageQuery {
	@ApiModelProperty(value = "类型")
	private Integer LX;
	@ApiModelProperty(value = "医保机构代码")
	private String JGID;
	@ApiModelProperty(value = "个人性质")
	private String GRXZ;
	@ApiModelProperty(value = "人次")
	private Integer RC;
	@ApiModelProperty(value = "统筹基金支付")
	private Double TCJJZF;

	public Integer getLX() {
		return LX;
	}

	public void setLX(Integer LX) {
		this.LX = LX;
	}

	public String getGRXZ() {
		return GRXZ;
	}

	public void setGRXZ(String GRXZ) {
		this.GRXZ = GRXZ;
	}

	public Integer getRC() {
		return RC;
	}

	public void setRC(Integer RC) {
		this.RC = RC;
	}

	public Double getTCJJZF() {
		return TCJJZF;
	}

	public void setTCJJZF(Double TCJJZF) {
		this.TCJJZF = TCJJZF;
	}

	public String getJGID() {
		return JGID;
	}

	public void setJGID(String JGID) {
		this.JGID = JGID;
	}
}