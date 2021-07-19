package com.buit.his.medinsuinterface.sh.dataitem.model;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;

import java.sql.Timestamp;

/**
 * 类名称：ShybZlxmxx<br> 
 * 类描述：上海医保诊疗项目信息<br>
 * @author 老花生
 */
@ApiModel(value="上海医保诊疗项目信息")
public class ShybZlxmxx  extends  PageQuery{
	//@ApiModelProperty(value="医保项目ID")
    /** 医保项目ID */
    private Integer ybitemdataId;
	//@ApiModelProperty(value="版本号")
    /** 版本号 */
    private Integer dataVersion;
	//@ApiModelProperty(value="创建时间")
    /** 创建时间 */
    private Timestamp gmtCreate;
	//@ApiModelProperty(value="最后更新时间")
    /** 最后更新时间 */
    private Timestamp gmtModify;
	//@ApiModelProperty(value="医疗机构ID")
    /** 医疗机构ID */
    private Integer hospitalId;
	//@ApiModelProperty(value="医保编码")
    /** 医保编码 */
    private String ybCode;
	//@ApiModelProperty(value="状态")
    /** 状态 */
    private String status;
	//@ApiModelProperty(value="信息起效日期")
    /** 信息起效日期 */
    private String beginDate;
	//@ApiModelProperty(value="信息失效日期")
    /** 信息失效日期 */
    private String endDate;
	//@ApiModelProperty(value="物价编码")
    /** 物价编码 */
    private String priceNo;
	//@ApiModelProperty(value="支付办法")
    /** 支付办法 */
    private String payType;
	//@ApiModelProperty(value="项目名称")
    /** 项目名称 */
    private String itemName;
	//@ApiModelProperty(value="项目内涵")
    /** 项目内涵 */
    private String itemConnotation;
	//@ApiModelProperty(value="除外内容")
    /** 除外内容 */
    private String excludedContent;
	//@ApiModelProperty(value="计价单位")
    /** 计价单位 */
    private String autName;
	//@ApiModelProperty(value="收费标准")
    /** 收费标准 */
    private Double price;
	//@ApiModelProperty(value="备注")
    /** 备注 */
    private String remark;
	//@ApiModelProperty(value="限定内容")
    /** 限定内容 */
    private String limitedContent;
	//@ApiModelProperty(value="费用类别")
    /** 费用类别 */
    private String costType;

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

    /** 设置:医保项目ID  */
    public void setYbitemdataId(Integer value) {
        this.ybitemdataId = value;
    }
    /** 获取:医保项目ID */
    public Integer getYbitemdataId() {
        return ybitemdataId;
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

    /** 设置:最后更新时间  */
    public void setGmtModify(Timestamp value) {
        this.gmtModify = value;
    }
    /** 获取:最后更新时间 */
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

    /** 设置:医保编码  */
    public void setYbCode(String value) {
        this.ybCode = value;
    }
    /** 获取:医保编码 */
    public String getYbCode() {
        return ybCode;
    }

    /** 设置:状态  */
    public void setStatus(String value) {
        this.status = value;
    }
    /** 获取:状态 */
    public String getStatus() {
        return status;
    }

    /** 设置:信息起效日期  */
    public void setBeginDate(String value) {
        this.beginDate = value;
    }
    /** 获取:信息起效日期 */
    public String getBeginDate() {
        return beginDate;
    }

    /** 设置:信息失效日期  */
    public void setEndDate(String value) {
        this.endDate = value;
    }
    /** 获取:信息失效日期 */
    public String getEndDate() {
        return endDate;
    }

    /** 设置:物价编码  */
    public void setPriceNo(String value) {
        this.priceNo = value;
    }
    /** 获取:物价编码 */
    public String getPriceNo() {
        return priceNo;
    }

    /** 设置:支付办法  */
    public void setPayType(String value) {
        this.payType = value;
    }
    /** 获取:支付办法 */
    public String getPayType() {
        return payType;
    }

    /** 设置:项目名称  */
    public void setItemName(String value) {
        this.itemName = value;
    }
    /** 获取:项目名称 */
    public String getItemName() {
        return itemName;
    }

    /** 设置:项目内涵  */
    public void setItemConnotation(String value) {
        this.itemConnotation = value;
    }
    /** 获取:项目内涵 */
    public String getItemConnotation() {
        return itemConnotation;
    }

    /** 设置:除外内容  */
    public void setExcludedContent(String value) {
        this.excludedContent = value;
    }
    /** 获取:除外内容 */
    public String getExcludedContent() {
        return excludedContent;
    }

    /** 设置:计价单位  */
    public void setAutName(String value) {
        this.autName = value;
    }
    /** 获取:计价单位 */
    public String getAutName() {
        return autName;
    }

    /** 设置:收费标准  */
    public void setPrice(Double value) {
        this.price = value;
    }
    /** 获取:收费标准 */
    public Double getPrice() {
        return price;
    }

    /** 设置:备注  */
    public void setRemark(String value) {
        this.remark = value;
    }
    /** 获取:备注 */
    public String getRemark() {
        return remark;
    }

    /** 设置:限定内容  */
    public void setLimitedContent(String value) {
        this.limitedContent = value;
    }
    /** 获取:限定内容 */
    public String getLimitedContent() {
        return limitedContent;
    }

    /** 设置:费用类别  */
    public void setCostType(String value) {
        this.costType = value;
    }
    /** 获取:费用类别 */
    public String getCostType() {
        return costType;
    }


}
