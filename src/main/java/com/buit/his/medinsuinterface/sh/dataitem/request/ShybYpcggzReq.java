   
package com.buit.his.medinsuinterface.sh.dataitem.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;


/**
 * 类名称：ShybYpcggz<br> 
 * 类描述：药品采购规则<br>
 * @author 老花生
 */
@ApiModel(value="药品采购规则")
public class ShybYpcggzReq  extends  PageQuery{
    @ApiModelProperty(value="药品采购规则ID")
    private Integer ypcggzdataId;
    @ApiModelProperty(value="版本号")
    private Integer dataVersion;
    @ApiModelProperty(value="创建时间")
    private Timestamp gmtCreate;
    @ApiModelProperty(value="最后一次更新时间")
    private Timestamp gmtModify;
    @ApiModelProperty(value="医疗机构ID")
    private Integer hospitalId;
    @ApiModelProperty(value="统编代码")
    private String tbdm;
    @ApiModelProperty(value="发布日期")
    private String fbrq;
    @ApiModelProperty(value="发布类型")
    private String fblx;
    @ApiModelProperty(value="采购方式")
    private String cgfs;
    @ApiModelProperty(value="采购状态")
    private String cgzt;
    @ApiModelProperty(value="采购允许范围")
    private String cgyxfw;
    @ApiModelProperty(value="采购配送时限")
    private String cgpssx;
    @ApiModelProperty(value="结账周期")
    private String jzzq;
    @ApiModelProperty(value="支付要求")
    private String zfyq;
    @ApiModelProperty(value="规则依据")
    private String gzyj;
    @ApiModelProperty(value="启用日期")
    private String qyrq;
    @ApiModelProperty(value="有效期限")
    private String yxrq;
    @ApiModelProperty(value="变更字段")
    private String bgzd;
    /**
     * 设置:药品采购规则ID
     */
    public void setYpcggzdataId(Integer value) {
        this.ypcggzdataId = value;
    }
    /**
     * 获取:药品采购规则ID
     */
    public Integer getYpcggzdataId() {
        return ypcggzdataId;
    }
    /**
     * 设置:版本号
     */
    public void setDataVersion(Integer value) {
        this.dataVersion = value;
    }
    /**
     * 获取:版本号
     */
    public Integer getDataVersion() {
        return dataVersion;
    }
    /**
     * 设置:创建时间
     */
    public void setGmtCreate(Timestamp value) {
        this.gmtCreate = value;
    }
    /**
     * 获取:创建时间
     */
    public Timestamp getGmtCreate() {
        return gmtCreate;
    }
    /**
     * 设置:最后一次更新时间
     */
    public void setGmtModify(Timestamp value) {
        this.gmtModify = value;
    }
    /**
     * 获取:最后一次更新时间
     */
    public Timestamp getGmtModify() {
        return gmtModify;
    }
    /**
     * 设置:医疗机构ID
     */
    public void setHospitalId(Integer value) {
        this.hospitalId = value;
    }
    /**
     * 获取:医疗机构ID
     */
    public Integer getHospitalId() {
        return hospitalId;
    }
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
     * 设置:采购方式
     */
    public void setCgfs(String value) {
        this.cgfs = value;
    }
    /**
     * 获取:采购方式
     */
    public String getCgfs() {
        return cgfs;
    }
    /**
     * 设置:采购状态
     */
    public void setCgzt(String value) {
        this.cgzt = value;
    }
    /**
     * 获取:采购状态
     */
    public String getCgzt() {
        return cgzt;
    }
    /**
     * 设置:采购允许范围
     */
    public void setCgyxfw(String value) {
        this.cgyxfw = value;
    }
    /**
     * 获取:采购允许范围
     */
    public String getCgyxfw() {
        return cgyxfw;
    }
    /**
     * 设置:采购配送时限
     */
    public void setCgpssx(String value) {
        this.cgpssx = value;
    }
    /**
     * 获取:采购配送时限
     */
    public String getCgpssx() {
        return cgpssx;
    }
    /**
     * 设置:结账周期
     */
    public void setJzzq(String value) {
        this.jzzq = value;
    }
    /**
     * 获取:结账周期
     */
    public String getJzzq() {
        return jzzq;
    }
    /**
     * 设置:支付要求
     */
    public void setZfyq(String value) {
        this.zfyq = value;
    }
    /**
     * 获取:支付要求
     */
    public String getZfyq() {
        return zfyq;
    }
    /**
     * 设置:规则依据
     */
    public void setGzyj(String value) {
        this.gzyj = value;
    }
    /**
     * 获取:规则依据
     */
    public String getGzyj() {
        return gzyj;
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