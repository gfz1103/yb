   
package com.buit.his.medinsuinterface.sh.dataitem.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：ShybYpjggz<br> 
 * 类描述：药品价格规则表<br>
 * @author 老花生
 */
@ApiModel(value="药品价格规则表-新增")
public class ShybYpjggzAddReq{
    @ApiModelProperty(value="统编代码")
    private String tbdm;
    @ApiModelProperty(value="发布日期")
    private String fbrq;
    @ApiModelProperty(value="发布类型")
    private String fblx;
    @ApiModelProperty(value="计价单位")
    private String jjdw;
    @ApiModelProperty(value="进货价格规则代码")
    private String jhjggzdm;
    @ApiModelProperty(value="进货价格规则说明")
    private String jhjggzsm;
    @ApiModelProperty(value="进货规则价格金额")
    private Double jhgzjgje;
    @ApiModelProperty(value="销售价格规则代码")
    private String xsjggzdm;
    @ApiModelProperty(value="销售价格规则说明")
    private String xsjggzsm;
    @ApiModelProperty(value="销售规则价格金额")
    private Double xsgzjgje;
    @ApiModelProperty(value="规则适用范围")
    private String gzsyfw;
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
     * 设置:计价单位
     */
    public void setJjdw(String value) {
        this.jjdw = value;
    }
    /**
     * 获取:计价单位
     */
    public String getJjdw() {
        return jjdw;
    }
    /**
     * 设置:进货价格规则代码
     */
    public void setJhjggzdm(String value) {
        this.jhjggzdm = value;
    }
    /**
     * 获取:进货价格规则代码
     */
    public String getJhjggzdm() {
        return jhjggzdm;
    }
    /**
     * 设置:进货价格规则说明
     */
    public void setJhjggzsm(String value) {
        this.jhjggzsm = value;
    }
    /**
     * 获取:进货价格规则说明
     */
    public String getJhjggzsm() {
        return jhjggzsm;
    }
    /**
     * 设置:进货规则价格金额
     */
    public void setJhgzjgje(Double value) {
        this.jhgzjgje = value;
    }
    /**
     * 获取:进货规则价格金额
     */
    public Double getJhgzjgje() {
        return jhgzjgje;
    }
    /**
     * 设置:销售价格规则代码
     */
    public void setXsjggzdm(String value) {
        this.xsjggzdm = value;
    }
    /**
     * 获取:销售价格规则代码
     */
    public String getXsjggzdm() {
        return xsjggzdm;
    }
    /**
     * 设置:销售价格规则说明
     */
    public void setXsjggzsm(String value) {
        this.xsjggzsm = value;
    }
    /**
     * 获取:销售价格规则说明
     */
    public String getXsjggzsm() {
        return xsjggzsm;
    }
    /**
     * 设置:销售规则价格金额
     */
    public void setXsgzjgje(Double value) {
        this.xsgzjgje = value;
    }
    /**
     * 获取:销售规则价格金额
     */
    public Double getXsgzjgje() {
        return xsgzjgje;
    }
    /**
     * 设置:规则适用范围
     */
    public void setGzsyfw(String value) {
        this.gzsyfw = value;
    }
    /**
     * 获取:规则适用范围
     */
    public String getGzsyfw() {
        return gzsyfw;
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