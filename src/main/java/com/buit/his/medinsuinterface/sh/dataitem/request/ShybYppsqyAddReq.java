   
package com.buit.his.medinsuinterface.sh.dataitem.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：ShybYppsqy<br> 
 * 类描述：药品配送企业表<br>
 * @author 老花生
 */
@ApiModel(value="药品配送企业表")
public class ShybYppsqyAddReq{
    @ApiModelProperty(value="统编代码")
    private String tbdm;
    @ApiModelProperty(value="发布日期")
    private String fbrq;
    @ApiModelProperty(value="发布类型")
    private String fblx;
    @ApiModelProperty(value="配送企业代码")
    private String psqydm;
    @ApiModelProperty(value="中标期数")
    private String zbqs;
    @ApiModelProperty(value="配送企业名称")
    private String psqymc;
    @ApiModelProperty(value="配送制约要求")
    private String pszyyq;
    @ApiModelProperty(value="配送范围")
    private String psfw;
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
     * 设置:配送企业代码
     */
    public void setPsqydm(String value) {
        this.psqydm = value;
    }
    /**
     * 获取:配送企业代码
     */
    public String getPsqydm() {
        return psqydm;
    }
    /**
     * 设置:中标期数
     */
    public void setZbqs(String value) {
        this.zbqs = value;
    }
    /**
     * 获取:中标期数
     */
    public String getZbqs() {
        return zbqs;
    }
    /**
     * 设置:配送企业名称
     */
    public void setPsqymc(String value) {
        this.psqymc = value;
    }
    /**
     * 获取:配送企业名称
     */
    public String getPsqymc() {
        return psqymc;
    }
    /**
     * 设置:配送制约要求
     */
    public void setPszyyq(String value) {
        this.pszyyq = value;
    }
    /**
     * 获取:配送制约要求
     */
    public String getPszyyq() {
        return pszyyq;
    }
    /**
     * 设置:配送范围
     */
    public void setPsfw(String value) {
        this.psfw = value;
    }
    /**
     * 获取:配送范围
     */
    public String getPsfw() {
        return psfw;
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