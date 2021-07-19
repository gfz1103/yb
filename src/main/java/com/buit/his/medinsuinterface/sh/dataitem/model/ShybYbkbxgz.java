package com.buit.his.medinsuinterface.sh.dataitem.model;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;

import java.sql.Timestamp;

/**
 * 类名称：ShybYbkbxgz<br> 
 * 类描述：药品医保可报销条件规则表<br>
 * @author 老花生
 */

@ApiModel(value="药品医保可报销条件规则表")
public class ShybYbkbxgz  extends  PageQuery{
	//@ApiModelProperty(value="药品医保可报销条件规则ID")
    /** 药品医保可报销条件规则ID */
    private Integer ybkbxgzdataId;
	//@ApiModelProperty(value="版本号")
    /** 版本号 */
    private Integer dataVersion;
	//@ApiModelProperty(value="创建时间")
    /** 创建时间 */
    private Timestamp gmtCreate;
	//@ApiModelProperty(value="最后一次更新时间")
    /** 最后一次更新时间 */
    private Timestamp gmtModify;
	//@ApiModelProperty(value="医疗机构ID")
    /** 医疗机构ID */
    private Integer hospitalId;
	//@ApiModelProperty(value="统编代码")
    /** 统编代码 */
    private String tbdm;
	//@ApiModelProperty(value="发布日期")
    /** 发布日期 */
    private String fbrq;
	//@ApiModelProperty(value="发布类型")
    /** 发布类型 */
    private String fblx;
	//@ApiModelProperty(value="报销条件代码")
    /** 报销条件代码 */
    private String bxtjdm;
	//@ApiModelProperty(value="报销条件内容")
    /** 报销条件内容 */
    private String bxtjnr;
	//@ApiModelProperty(value="规则来源")
    /** 规则来源 */
    private String gzly;
	//@ApiModelProperty(value="启用日期")
    /** 启用日期 */
    private String qyrq;
	//@ApiModelProperty(value="有效期限")
    /** 有效期限 */
    private String yxrq;
	//@ApiModelProperty(value="变更字段")
    /** 变更字段 */
    private String bgzd;


    //@ApiModelProperty(value="拼音码")
    private String pyCode;
    //@ApiModelProperty(value="五笔码")
    private String wbCode;

    public String getPyCode() {
        return pyCode;
    }

    public void setPyCode(String pyCode) {
        this.pyCode = pyCode;
    }

    public String getWbCode() {
        return wbCode;
    }

    public void setWbCode(String wbCode) {
        this.wbCode = wbCode;
    }

    /** 设置:药品医保可报销条件规则ID  */
    public void setYbkbxgzdataId(Integer value) {
        this.ybkbxgzdataId = value;
    }
    /** 获取:药品医保可报销条件规则ID */
    public Integer getYbkbxgzdataId() {
        return ybkbxgzdataId;
    }

    /** 设置:版本号  */
    public void setDataVersion(Integer value) {
        this.dataVersion = value;
    }
    /** 获取:版本号 */
    public Integer getDataVersion() {
        return dataVersion;
    }

    /** 设置:创建时间  */
    public void setGmtCreate(Timestamp value) {
        this.gmtCreate = value;
    }
    /** 获取:创建时间 */
    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    /** 设置:最后一次更新时间  */
    public void setGmtModify(Timestamp value) {
        this.gmtModify = value;
    }
    /** 获取:最后一次更新时间 */
    public Timestamp getGmtModify() {
        return gmtModify;
    }

    /** 设置:医疗机构ID  */
    public void setHospitalId(Integer value) {
        this.hospitalId = value;
    }
    /** 获取:医疗机构ID */
    public Integer getHospitalId() {
        return hospitalId;
    }

    /** 设置:统编代码  */
    public void setTbdm(String value) {
        this.tbdm = value;
    }
    /** 获取:统编代码 */
    public String getTbdm() {
        return tbdm;
    }

    /** 设置:发布日期  */
    public void setFbrq(String value) {
        this.fbrq = value;
    }
    /** 获取:发布日期 */
    public String getFbrq() {
        return fbrq;
    }

    /** 设置:发布类型  */
    public void setFblx(String value) {
        this.fblx = value;
    }
    /** 获取:发布类型 */
    public String getFblx() {
        return fblx;
    }

    /** 设置:报销条件代码  */
    public void setBxtjdm(String value) {
        this.bxtjdm = value;
    }
    /** 获取:报销条件代码 */
    public String getBxtjdm() {
        return bxtjdm;
    }

    /** 设置:报销条件内容  */
    public void setBxtjnr(String value) {
        this.bxtjnr = value;
    }
    /** 获取:报销条件内容 */
    public String getBxtjnr() {
        return bxtjnr;
    }

    /** 设置:规则来源  */
    public void setGzly(String value) {
        this.gzly = value;
    }
    /** 获取:规则来源 */
    public String getGzly() {
        return gzly;
    }

    /** 设置:启用日期  */
    public void setQyrq(String value) {
        this.qyrq = value;
    }
    /** 获取:启用日期 */
    public String getQyrq() {
        return qyrq;
    }

    /** 设置:有效期限  */
    public void setYxrq(String value) {
        this.yxrq = value;
    }
    /** 获取:有效期限 */
    public String getYxrq() {
        return yxrq;
    }

    /** 设置:变更字段  */
    public void setBgzd(String value) {
        this.bgzd = value;
    }
    /** 获取:变更字段 */
    public String getBgzd() {
        return bgzd;
    }


}