   
package com.buit.his.medinsuinterface.sh.dataitem.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：ShybYbkbxgz<br> 
 * 类描述：药品医保可报销条件规则表<br>
 * @author 老花生
 */
@ApiModel(value="药品医保可报销条件规则表")
public class ShybYbkbxgzAddReq {
    @ApiModelProperty(value="统编代码")
    private String tbdm;
    @ApiModelProperty(value="发布日期")
    private String fbrq;
    @ApiModelProperty(value="发布类型")
    private String fblx;
    @ApiModelProperty(value="报销条件代码")
    private String bxtjdm;
    @ApiModelProperty(value="报销条件内容")
    private String bxtjnr;
    @ApiModelProperty(value="规则来源")
    private String gzly;
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
     * 设置:报销条件代码
     */
    public void setBxtjdm(String value) {
        this.bxtjdm = value;
    }
    /**
     * 获取:报销条件代码
     */
    public String getBxtjdm() {
        return bxtjdm;
    }
    /**
     * 设置:报销条件内容
     */
    public void setBxtjnr(String value) {
        this.bxtjnr = value;
    }
    /**
     * 获取:报销条件内容
     */
    public String getBxtjnr() {
        return bxtjnr;
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