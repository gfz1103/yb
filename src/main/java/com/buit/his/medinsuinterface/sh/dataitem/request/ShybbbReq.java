
package com.buit.his.medinsuinterface.sh.dataitem.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：CzptSbbReq<br>
 * 类描述：城镇普通上报表<br>
 * 
 * @author beijunhua
 */
@ApiModel(value = "城镇普通上报表req")
public class ShybbbReq extends PageQuery {
	@ApiModelProperty(value = "上报月份")
	private String daa ;
	@ApiModelProperty(value = "门诊类别")
	private String mzlb;
	@ApiModelProperty(value = "开始时间")
	private String datefrom;
	@ApiModelProperty(value = "结束时间")
	private String dateto;

	public String getMzlb() {
		return mzlb;
	}

	public void setMzlb(String mzlb) {
		this.mzlb = mzlb;
	}

	public String getDatefrom() {
		return datefrom;
	}

	public void setDatefrom(String datefrom) {
		this.datefrom = datefrom;
	}

	public String getDateto() {
		return dateto;
	}

	public void setDateto(String dateto) {
		this.dateto = dateto;
	}

	public String getDaa() {
		return daa;
	}

	public void setDaa(String daa) {
		this.daa = daa;
	}
}