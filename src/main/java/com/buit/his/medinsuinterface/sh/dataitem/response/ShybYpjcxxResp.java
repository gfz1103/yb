   
package com.buit.his.medinsuinterface.sh.dataitem.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：ShybYpjcxx<br> 
 * 类描述：药品品规基础信息表<br>
 * @author 老花生
 */
@ApiModel(value="药品品规基础信息表")
public class ShybYpjcxxResp  extends  PageQuery{
    @ApiModelProperty(value="药品品规数据ID")
    private Integer ypjcxxdataId;
    @ApiModelProperty(value="统编代码")
    private String tbdm;
    @ApiModelProperty(value="发布日期")
    private String fbrq;
    @ApiModelProperty(value="发布类型")
    private String fblx;
    @ApiModelProperty(value="批准文号")
    private String pzwh;
    @ApiModelProperty(value="本位码")
    private String bwm;
    @ApiModelProperty(value="药品通用名")
    private String yptym;
    @ApiModelProperty(value="英文名")
    private String ywm;
    @ApiModelProperty(value="剂型")
    private String jx;
    @ApiModelProperty(value="规格")
    private String gg;
    @ApiModelProperty(value="生产企业")
    private String scqy;
    @ApiModelProperty(value="商品名")
    private String spm;
    @ApiModelProperty(value="包装单位")
    private String bzdw;
    @ApiModelProperty(value="包装数量(转换系数)")
    private Double bzsl;
    @ApiModelProperty(value="包装材质")
    private String bzcz;
    @ApiModelProperty(value="包装方式")
    private String bzfs;
    @ApiModelProperty(value="规格包装文字表述")
    private String ggbzwzbs;
    @ApiModelProperty(value="药品属性")
    private String ypsx;
    @ApiModelProperty(value="参考分类")
    private String ckfl;
    @ApiModelProperty(value="备注信息")
    private String bzxx;
    @ApiModelProperty(value="启用日期")
    private String qyrq;
    @ApiModelProperty(value="有效期限")
    private String yxrq;
    @ApiModelProperty(value="变更字段")
    private String bgzd;
	public Integer getYpjcxxdataId() {
		return ypjcxxdataId;
	}
	public void setYpjcxxdataId(Integer ypjcxxdataId) {
		this.ypjcxxdataId = ypjcxxdataId;
	}
	public String getTbdm() {
		return tbdm;
	}
	public void setTbdm(String tbdm) {
		this.tbdm = tbdm;
	}
	public String getFbrq() {
		return fbrq;
	}
	public void setFbrq(String fbrq) {
		this.fbrq = fbrq;
	}
	public String getFblx() {
		return fblx;
	}
	public void setFblx(String fblx) {
		this.fblx = fblx;
	}
	public String getPzwh() {
		return pzwh;
	}
	public void setPzwh(String pzwh) {
		this.pzwh = pzwh;
	}
	public String getBwm() {
		return bwm;
	}
	public void setBwm(String bwm) {
		this.bwm = bwm;
	}
	public String getYptym() {
		return yptym;
	}
	public void setYptym(String yptym) {
		this.yptym = yptym;
	}
	public String getYwm() {
		return ywm;
	}
	public void setYwm(String ywm) {
		this.ywm = ywm;
	}
	public String getJx() {
		return jx;
	}
	public void setJx(String jx) {
		this.jx = jx;
	}
	public String getGg() {
		return gg;
	}
	public void setGg(String gg) {
		this.gg = gg;
	}
	public String getScqy() {
		return scqy;
	}
	public void setScqy(String scqy) {
		this.scqy = scqy;
	}
	public String getSpm() {
		return spm;
	}
	public void setSpm(String spm) {
		this.spm = spm;
	}
	public String getBzdw() {
		return bzdw;
	}
	public void setBzdw(String bzdw) {
		this.bzdw = bzdw;
	}
	public Double getBzsl() {
		return bzsl;
	}
	public void setBzsl(Double bzsl) {
		this.bzsl = bzsl;
	}
	public String getBzcz() {
		return bzcz;
	}
	public void setBzcz(String bzcz) {
		this.bzcz = bzcz;
	}
	public String getBzfs() {
		return bzfs;
	}
	public void setBzfs(String bzfs) {
		this.bzfs = bzfs;
	}
	public String getGgbzwzbs() {
		return ggbzwzbs;
	}
	public void setGgbzwzbs(String ggbzwzbs) {
		this.ggbzwzbs = ggbzwzbs;
	}
	public String getYpsx() {
		return ypsx;
	}
	public void setYpsx(String ypsx) {
		this.ypsx = ypsx;
	}
	public String getCkfl() {
		return ckfl;
	}
	public void setCkfl(String ckfl) {
		this.ckfl = ckfl;
	}
	public String getBzxx() {
		return bzxx;
	}
	public void setBzxx(String bzxx) {
		this.bzxx = bzxx;
	}
	public String getQyrq() {
		return qyrq;
	}
	public void setQyrq(String qyrq) {
		this.qyrq = qyrq;
	}
	public String getYxrq() {
		return yxrq;
	}
	public void setYxrq(String yxrq) {
		this.yxrq = yxrq;
	}
	public String getBgzd() {
		return bgzd;
	}
	public void setBgzd(String bgzd) {
		this.bgzd = bgzd;
	}
    
}