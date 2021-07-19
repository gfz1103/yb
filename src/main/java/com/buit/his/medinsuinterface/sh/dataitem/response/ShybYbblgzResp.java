   
package com.buit.his.medinsuinterface.sh.dataitem.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;


/**
 * 类名称：ShybYbblgz<br> 
 * 类描述：医保报销比例规则表<br>
 * @author 老花生
 */
@ApiModel(value="医保报销比例规则表")
public class ShybYbblgzResp  extends  PageQuery{
    @ApiModelProperty(value="医保报销比例规则ID")
    private Integer ybblgzdataId;
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
    @ApiModelProperty(value="医保人群")
    private String ybrq;
    @ApiModelProperty(value="报销比例")
    private Integer kbxbl;
    @ApiModelProperty(value="自付比例")
    private Integer grcdbl;
    @ApiModelProperty(value="支付限额")
    private Double zfxr;
    @ApiModelProperty(value="限额计量单位")
    private String xejldw;
    @ApiModelProperty(value="限额累计方式")
    private String xeljfs;
    @ApiModelProperty(value="适用病种代码")
    private String sybzdm;
    @ApiModelProperty(value="适用病种名称")
    private String sybzmc;
    @ApiModelProperty(value="规则文字说明")
    private String gzwzsm;
    @ApiModelProperty(value="规则来源")
    private String gzly;
    @ApiModelProperty(value="启用日期")
    private String qyrq;
    @ApiModelProperty(value="有效期限")
    private String yxrq;
    @ApiModelProperty(value="变更字段")
    private String bgzd;
    /**
     * 设置:医保报销比例规则ID
     */
    public void setYbblgzdataId(Integer value) {
        this.ybblgzdataId = value;
    }
    /**
     * 获取:医保报销比例规则ID
     */
    public Integer getYbblgzdataId() {
        return ybblgzdataId;
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
     * 设置:医保人群
     */
    public void setYbrq(String value) {
        this.ybrq = value;
    }
    /**
     * 获取:医保人群
     */
    public String getYbrq() {
        return ybrq;
    }
    /**
     * 设置:报销比例
     */
    public void setKbxbl(Integer value) {
        this.kbxbl = value;
    }
    /**
     * 获取:报销比例
     */
    public Integer getKbxbl() {
        return kbxbl;
    }
    /**
     * 设置:自付比例
     */
    public void setGrcdbl(Integer value) {
        this.grcdbl = value;
    }
    /**
     * 获取:自付比例
     */
    public Integer getGrcdbl() {
        return grcdbl;
    }
    /**
     * 设置:支付限额
     */
    public void setZfxr(Double value) {
        this.zfxr = value;
    }
    /**
     * 获取:支付限额
     */
    public Double getZfxr() {
        return zfxr;
    }
    /**
     * 设置:限额计量单位
     */
    public void setXejldw(String value) {
        this.xejldw = value;
    }
    /**
     * 获取:限额计量单位
     */
    public String getXejldw() {
        return xejldw;
    }
    /**
     * 设置:限额累计方式
     */
    public void setXeljfs(String value) {
        this.xeljfs = value;
    }
    /**
     * 获取:限额累计方式
     */
    public String getXeljfs() {
        return xeljfs;
    }
    /**
     * 设置:适用病种代码
     */
    public void setSybzdm(String value) {
        this.sybzdm = value;
    }
    /**
     * 获取:适用病种代码
     */
    public String getSybzdm() {
        return sybzdm;
    }
    /**
     * 设置:适用病种名称
     */
    public void setSybzmc(String value) {
        this.sybzmc = value;
    }
    /**
     * 获取:适用病种名称
     */
    public String getSybzmc() {
        return sybzmc;
    }
    /**
     * 设置:规则文字说明
     */
    public void setGzwzsm(String value) {
        this.gzwzsm = value;
    }
    /**
     * 获取:规则文字说明
     */
    public String getGzwzsm() {
        return gzwzsm;
    }
    /**
     * 设置:规则来源
     */
    public void setGzly(String value) {
        this.gzly = value;
    }
    /**
     * 获取:规则来源
     */
    public String getGzly() {
        return gzly;
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