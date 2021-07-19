
package com.buit.his.medinsuinterface.sh.dataitem.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类描述：城镇大病上报表<br>
 *
 * @author beijunhua
 */
@ApiModel(value = "工伤保险上报表A")
public class GsbxAResp extends PageQuery {
	@ApiModelProperty(value = "序号")
	private String XH;
	@ApiModelProperty(value = "姓名")
	private String NAME;
	@ApiModelProperty(value = "工伤认定书编号")
	private String GSID;
	@ApiModelProperty(value = "科别")
	private String KSNA;
	@ApiModelProperty(value = "就诊日期")
	private String JZDATE;
	@ApiModelProperty(value = "就诊次数")
	private String JZNUM;
	@ApiModelProperty(value = "工伤保险基本支付")
	private String TOTFY;

	public String getXH() {
		return XH;
	}

	public void setXH(String XH) {
		this.XH = XH;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String NAME) {
		this.NAME = NAME;
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

	public String getGSID() {
		return GSID;
	}

	public void setGSID(String GSID) {
		this.GSID = GSID;
	}

	public String getTOTFY() {
		return TOTFY;
	}

	public void setTOTFY(String TOTFY) {
		this.TOTFY = TOTFY;
	}
}