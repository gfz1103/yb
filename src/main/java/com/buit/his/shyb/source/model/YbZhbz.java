package com.buit.his.shyb.source.model;

import com.buit.commons.PageQuery;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 类名称：YbZhbz<br>
 * 类描述：医保_账户标志
 * 
 * @author Brijunhua
 */
public class YbZhbz extends PageQuery {
	//账户标志
	private String zhbz;
	//病人性质
	private Integer brxz;

	public String getZhbz() {
		return zhbz;
	}

	public void setZhbz(String zhbz) {
		this.zhbz = zhbz;
	}

	public Integer getBrxz() {
		return brxz;
	}

	public void setBrxz(Integer brxz) {
		this.brxz = brxz;
	}
}