
package com.buit.his.medinsuinterface.sh.dataitem.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类描述：城镇居民上报表明细<br>
 *
 * @author beijunhua
 */
@ApiModel(value = "城镇居民上报表A-明细")
public class CzjmAResp extends PageQuery {
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
	@ApiModelProperty(value = "费用总计")
	private String TOTFY;
	@ApiModelProperty(value = "分类自负")
	private String FLGRZF;
	@ApiModelProperty(value = "自负")
	private String GRXJ;
	@ApiModelProperty(value = "附加基金支付")
	private String TCBX;

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

	public String getTOTFY() {
		return TOTFY;
	}

	public void setTOTFY(String TOTFY) {
		this.TOTFY = TOTFY;
	}

	public String getFLGRZF() {
		return FLGRZF;
	}

	public void setFLGRZF(String FLGRZF) {
		this.FLGRZF = FLGRZF;
	}

	public String getGRXJ() {
		return GRXJ;
	}

	public void setGRXJ(String GRXJ) {
		this.GRXJ = GRXJ;
	}

	public String getTCBX() {
		return TCBX;
	}

	public void setTCBX(String TCBX) {
		this.TCBX = TCBX;
	}
}