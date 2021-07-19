   
package com.buit.his.medinsuinterface.sh.dataitem.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：ShybZlxmxx<br> 
 * 类描述：上海医保诊疗项目信息<br>
 * @author 老花生
 */
@ApiModel(value="上海医保诊疗项目信息")
public class ShybZlxmxxEditReq  extends  PageQuery{
	@ApiModelProperty(value="医保项目ID")
    private Integer ybitemdataId;
	@ApiModelProperty(value="医保编码")
    private String ybCode;
    @ApiModelProperty(value="支付办法")
    private String payType;
    @ApiModelProperty(value="状态")
    private String status;
    @ApiModelProperty(value="物价编码")
    private String priceNo;
    @ApiModelProperty(value="信息起效日期")
    private String beginDate;
    @ApiModelProperty(value="信息失效日期")
    private String endDate;
    @ApiModelProperty(value="项目名称")
    private String itemName;
    @ApiModelProperty(value="项目内涵")
    private String itemConnotation;
    @ApiModelProperty(value="除外内容")
    private String excludedContent;
    @ApiModelProperty(value="计价单位")
    private String autName;
    @ApiModelProperty(value="收费标准")
    private Double price;
    @ApiModelProperty(value="备注")
    private String remark;
    @ApiModelProperty(value="限定内容")
    private String limitedContent;
    @ApiModelProperty(value="费用类别")
    private String costType;
    
    /**
     * 设置:医保编码
     */
    public void setYbCode(String value) {
        this.ybCode = value;
    }
    /**
     * 获取:医保编码
     */
    public String getYbCode() {
        return ybCode;
    }
    /**
     * 设置:状态
     */
    public void setStatus(String value) {
        this.status = value;
    }
    /**
     * 获取:状态
     */
    public String getStatus() {
        return status;
    }
    /**
     * 设置:信息起效日期
     */
    public void setBeginDate(String value) {
        this.beginDate = value;
    }
    /**
     * 获取:信息起效日期
     */
    public String getBeginDate() {
        return beginDate;
    }
    /**
     * 设置:信息失效日期
     */
    public void setEndDate(String value) {
        this.endDate = value;
    }
    /**
     * 获取:信息失效日期
     */
    public String getEndDate() {
        return endDate;
    }
    /**
     * 设置:物价编码
     */
    public void setPriceNo(String value) {
        this.priceNo = value;
    }
    /**
     * 获取:物价编码
     */
    public String getPriceNo() {
        return priceNo;
    }
    /**
     * 设置:支付办法
     */
    public void setPayType(String value) {
        this.payType = value;
    }
    /**
     * 获取:支付办法
     */
    public String getPayType() {
        return payType;
    }
    /**
     * 设置:项目名称
     */
    public void setItemName(String value) {
        this.itemName = value;
    }
    /**
     * 获取:项目名称
     */
    public String getItemName() {
        return itemName;
    }
    /**
     * 设置:项目内涵
     */
    public void setItemConnotation(String value) {
        this.itemConnotation = value;
    }
    /**
     * 获取:项目内涵
     */
    public String getItemConnotation() {
        return itemConnotation;
    }
    /**
     * 设置:除外内容
     */
    public void setExcludedContent(String value) {
        this.excludedContent = value;
    }
    /**
     * 获取:除外内容
     */
    public String getExcludedContent() {
        return excludedContent;
    }
    /**
     * 设置:计价单位
     */
    public void setAutName(String value) {
        this.autName = value;
    }
    /**
     * 获取:计价单位
     */
    public String getAutName() {
        return autName;
    }
    /**
     * 设置:收费标准
     */
    public void setPrice(Double value) {
        this.price = value;
    }
    /**
     * 获取:收费标准
     */
    public Double getPrice() {
        return price;
    }
    /**
     * 设置:备注
     */
    public void setRemark(String value) {
        this.remark = value;
    }
    /**
     * 获取:备注
     */
    public String getRemark() {
        return remark;
    }
    /**
     * 设置:限定内容
     */
    public void setLimitedContent(String value) {
        this.limitedContent = value;
    }
    /**
     * 获取:限定内容
     */
    public String getLimitedContent() {
        return limitedContent;
    }
    /**
     * 设置:费用类别
     */
    public void setCostType(String value) {
        this.costType = value;
    }
    /**
     * 获取:费用类别
     */
    public String getCostType() {
        return costType;
    }
	public Integer getYbitemdataId() {
		return ybitemdataId;
	}
	public void setYbitemdataId(Integer ybitemdataId) {
		this.ybitemdataId = ybitemdataId;
	}
}
