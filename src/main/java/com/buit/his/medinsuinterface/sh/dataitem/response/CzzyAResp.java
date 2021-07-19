
package com.buit.his.medinsuinterface.sh.dataitem.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类描述：城镇住院上报表明细<br>
 *
 * @author beijunhua
 */
@ApiModel(value = "城镇住院上报表A")
public class CzzyAResp extends PageQuery {
	@ApiModelProperty(value = "序号")
	private String XH;
	@ApiModelProperty(value = "住院号")
	private String ZYID;
	@ApiModelProperty(value = "姓名")
	private String NAME;
	@ApiModelProperty(value = "凭证编号")
	private String YBID;
	@ApiModelProperty(value = "出院日期")
	private String CYDATE;
	@ApiModelProperty(value = "住院天数")
	private String ZYDNUM;
	@ApiModelProperty(value = "项目")
	private String ZYJGBZ;
	@ApiModelProperty(value = "科别")
	private String KSNA;
	@ApiModelProperty(value = "交易费用")
	private String JYTOTFY;

	@ApiModelProperty(value = "分类在职")
	private String ZZFLGRZF;
	@ApiModelProperty(value = "分类退休")
	private String TXFLGRZF;
	@ApiModelProperty(value = "自负在职")
	private String ZZZF;
	@ApiModelProperty(value = "自负退休")
	private String TXZF;
	@ApiModelProperty(value = "统筹基金支付在职")
	private String ZZTCZF;
	@ApiModelProperty(value = "统筹基金支付退休")
	private String TXTCZF;
	@ApiModelProperty(value = "历年账户在职")
	private String ZZNN;
	@ApiModelProperty(value = "历年账户退休")
	private String TXNN;
	@ApiModelProperty(value = "历年定额")
	private String GRZFLD;
	@ApiModelProperty(value = "历年其余")
	private String GRZFLQ;
	@ApiModelProperty(value = "附加在职")
	private String ZZFJBX;
	@ApiModelProperty(value = "附加退休")
	private String TXFJBX;
	@ApiModelProperty(value = "诊断")
	private String ZDNAME;

	public String getXH() {
		return XH;
	}

	public void setXH(String XH) {
		this.XH = XH;
	}

	public String getZYID() {
		return ZYID;
	}

	public void setZYID(String ZYID) {
		this.ZYID = ZYID;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String NAME) {
		this.NAME = NAME;
	}

	public String getYBID() {
		return YBID;
	}

	public void setYBID(String YBID) {
		this.YBID = YBID;
	}

	public String getCYDATE() {
		return CYDATE;
	}

	public void setCYDATE(String CYDATE) {
		this.CYDATE = CYDATE;
	}

	public String getZYDNUM() {
		return ZYDNUM;
	}

	public void setZYDNUM(String ZYDNUM) {
		this.ZYDNUM = ZYDNUM;
	}

	public String getZYJGBZ() {
		return ZYJGBZ;
	}

	public void setZYJGBZ(String ZYJGBZ) {
		this.ZYJGBZ = ZYJGBZ;
	}

	public String getKSNA() {
		return KSNA;
	}

	public void setKSNA(String KSNA) {
		this.KSNA = KSNA;
	}

	public String getJYTOTFY() {
		return JYTOTFY;
	}

	public void setJYTOTFY(String JYTOTFY) {
		this.JYTOTFY = JYTOTFY;
	}

	public String getZZFLGRZF() {
		return ZZFLGRZF;
	}

	public void setZZFLGRZF(String ZZFLGRZF) {
		this.ZZFLGRZF = ZZFLGRZF;
	}

	public String getTXFLGRZF() {
		return TXFLGRZF;
	}

	public void setTXFLGRZF(String TXFLGRZF) {
		this.TXFLGRZF = TXFLGRZF;
	}

	public String getZZZF() {
		return ZZZF;
	}

	public void setZZZF(String ZZZF) {
		this.ZZZF = ZZZF;
	}

	public String getTXZF() {
		return TXZF;
	}

	public void setTXZF(String TXZF) {
		this.TXZF = TXZF;
	}

	public String getZZTCZF() {
		return ZZTCZF;
	}

	public void setZZTCZF(String ZZTCZF) {
		this.ZZTCZF = ZZTCZF;
	}

	public String getTXTCZF() {
		return TXTCZF;
	}

	public void setTXTCZF(String TXTCZF) {
		this.TXTCZF = TXTCZF;
	}

	public String getZZNN() {
		return ZZNN;
	}

	public void setZZNN(String ZZNN) {
		this.ZZNN = ZZNN;
	}

	public String getTXNN() {
		return TXNN;
	}

	public void setTXNN(String TXNN) {
		this.TXNN = TXNN;
	}

	public String getGRZFLD() {
		return GRZFLD;
	}

	public void setGRZFLD(String GRZFLD) {
		this.GRZFLD = GRZFLD;
	}

	public String getGRZFLQ() {
		return GRZFLQ;
	}

	public void setGRZFLQ(String GRZFLQ) {
		this.GRZFLQ = GRZFLQ;
	}

	public String getZZFJBX() {
		return ZZFJBX;
	}

	public void setZZFJBX(String ZZFJBX) {
		this.ZZFJBX = ZZFJBX;
	}

	public String getTXFJBX() {
		return TXFJBX;
	}

	public void setTXFJBX(String TXFJBX) {
		this.TXFJBX = TXFJBX;
	}

	public String getZDNAME() {
		return ZDNAME;
	}

	public void setZDNAME(String ZDNAME) {
		this.ZDNAME = ZDNAME;
	}
}