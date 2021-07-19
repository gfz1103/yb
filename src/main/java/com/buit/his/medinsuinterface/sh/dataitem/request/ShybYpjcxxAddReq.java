   
package com.buit.his.medinsuinterface.sh.dataitem.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;


/**
 * 类名称：ShybYpjcxx<br> 
 * 类描述：药品品规基础信息表<br>
 * @author 老花生
 */
@ApiModel(value="药品品规基础信息表-新增")
public class ShybYpjcxxAddReq{
    @NotBlank(message = "统编代码 不能为空")
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
    /**
     * 设置:统编代码
     */
    public void setTbdm(String value) {
        this.tbdm = value;
    }
    /**
     * 获取:统编代码
     */
    public String getTbdm() {
        return tbdm;
    }
    /**
     * 设置:发布日期
     */
    public void setFbrq(String value) {
        this.fbrq = value;
    }
    /**
     * 获取:发布日期
     */
    public String getFbrq() {
        return fbrq;
    }
    /**
     * 设置:发布类型
     */
    public void setFblx(String value) {
        this.fblx = value;
    }
    /**
     * 获取:发布类型
     */
    public String getFblx() {
        return fblx;
    }
    /**
     * 设置:批准文号
     */
    public void setPzwh(String value) {
        this.pzwh = value;
    }
    /**
     * 获取:批准文号
     */
    public String getPzwh() {
        return pzwh;
    }
    /**
     * 设置:本位码
     */
    public void setBwm(String value) {
        this.bwm = value;
    }
    /**
     * 获取:本位码
     */
    public String getBwm() {
        return bwm;
    }
    /**
     * 设置:药品通用名
     */
    public void setYptym(String value) {
        this.yptym = value;
    }
    /**
     * 获取:药品通用名
     */
    public String getYptym() {
        return yptym;
    }
    /**
     * 设置:英文名
     */
    public void setYwm(String value) {
        this.ywm = value;
    }
    /**
     * 获取:英文名
     */
    public String getYwm() {
        return ywm;
    }
    /**
     * 设置:剂型
     */
    public void setJx(String value) {
        this.jx = value;
    }
    /**
     * 获取:剂型
     */
    public String getJx() {
        return jx;
    }
    /**
     * 设置:规格
     */
    public void setGg(String value) {
        this.gg = value;
    }
    /**
     * 获取:规格
     */
    public String getGg() {
        return gg;
    }
    /**
     * 设置:生产企业
     */
    public void setScqy(String value) {
        this.scqy = value;
    }
    /**
     * 获取:生产企业
     */
    public String getScqy() {
        return scqy;
    }
    /**
     * 设置:商品名
     */
    public void setSpm(String value) {
        this.spm = value;
    }
    /**
     * 获取:商品名
     */
    public String getSpm() {
        return spm;
    }
    /**
     * 设置:包装单位
     */
    public void setBzdw(String value) {
        this.bzdw = value;
    }
    /**
     * 获取:包装单位
     */
    public String getBzdw() {
        return bzdw;
    }
    /**
     * 设置:包装数量(转换系数)
     */
    public void setBzsl(Double value) {
        this.bzsl = value;
    }
    /**
     * 获取:包装数量(转换系数)
     */
    public Double getBzsl() {
        return bzsl;
    }
    /**
     * 设置:包装材质
     */
    public void setBzcz(String value) {
        this.bzcz = value;
    }
    /**
     * 获取:包装材质
     */
    public String getBzcz() {
        return bzcz;
    }
    /**
     * 设置:包装方式
     */
    public void setBzfs(String value) {
        this.bzfs = value;
    }
    /**
     * 获取:包装方式
     */
    public String getBzfs() {
        return bzfs;
    }
    /**
     * 设置:规格包装文字表述
     */
    public void setGgbzwzbs(String value) {
        this.ggbzwzbs = value;
    }
    /**
     * 获取:规格包装文字表述
     */
    public String getGgbzwzbs() {
        return ggbzwzbs;
    }
    /**
     * 设置:药品属性
     */
    public void setYpsx(String value) {
        this.ypsx = value;
    }
    /**
     * 获取:药品属性
     */
    public String getYpsx() {
        return ypsx;
    }
    /**
     * 设置:参考分类
     */
    public void setCkfl(String value) {
        this.ckfl = value;
    }
    /**
     * 获取:参考分类
     */
    public String getCkfl() {
        return ckfl;
    }
    /**
     * 设置:备注信息
     */
    public void setBzxx(String value) {
        this.bzxx = value;
    }
    /**
     * 获取:备注信息
     */
    public String getBzxx() {
        return bzxx;
    }
    /**
     * 设置:启用日期
     */
    public void setQyrq(String value) {
        this.qyrq = value;
    }
    /**
     * 获取:启用日期
     */
    public String getQyrq() {
        return qyrq;
    }
    /**
     * 设置:有效期限
     */
    public void setYxrq(String value) {
        this.yxrq = value;
    }
    /**
     * 获取:有效期限
     */
    public String getYxrq() {
        return yxrq;
    }
    /**
     * 设置:变更字段
     */
    public void setBgzd(String value) {
        this.bgzd = value;
    }
    /**
     * 获取:变更字段
     */
    public String getBgzd() {
        return bgzd;
    }
}