package com.buit.his.medinsuinterface.sh.dataitem.excelmodel;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @author 老花生
 */
public class ShybZlxmxxExcelModel {
	
	@ExcelProperty(value = "医保编码", index = 0)
    private String ybCode;
    @ExcelProperty(value = "状态", index = 1)
    private String status;
    @ExcelProperty(value="信息起效日期", index = 2)
    private String beginDate;
    @ExcelProperty(value="信息失效日期", index = 3)
    private String endDate;
    @ExcelProperty(value="物价编码", index = 4)
    private String priceNo;
    @ExcelProperty(value="支付办法", index = 5)
    private String payType;
    @ExcelProperty(value="项目名称", index = 6)
    private String itemName;
    @ExcelProperty(value="项目内涵", index = 7)
    private String itemConnotation;
    @ExcelProperty(value="除外内容", index = 8)
    private String excludedContent;
    @ExcelProperty(value="计价单位", index = 9)
    private String autName;
    @ExcelProperty(value="收费标准", index = 10)
    private String price;
    @ExcelProperty(value="备注", index = 11)
    private String remark;
    @ExcelProperty(value="限定内容", index = 12)
    private String limitedContent;
    @ExcelProperty(value="费用类别", index = 13)
    private String costType;
    
	public String getYbCode() {
		return ybCode;
	}
	public void setYbCode(String ybCode) {
		this.ybCode = ybCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getPriceNo() {
		return priceNo;
	}
	public void setPriceNo(String priceNo) {
		this.priceNo = priceNo;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemConnotation() {
		return itemConnotation;
	}
	public void setItemConnotation(String itemConnotation) {
		this.itemConnotation = itemConnotation;
	}
	public String getExcludedContent() {
		return excludedContent;
	}
	public void setExcludedContent(String excludedContent) {
		this.excludedContent = excludedContent;
	}
	public String getAutName() {
		return autName;
	}
	public void setAutName(String autName) {
		this.autName = autName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getLimitedContent() {
		return limitedContent;
	}
	public void setLimitedContent(String limitedContent) {
		this.limitedContent = limitedContent;
	}
	public String getCostType() {
		return costType;
	}
	public void setCostType(String costType) {
		this.costType = costType;
	}
    
    
}
