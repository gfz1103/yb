package com.buit.his.medinsuinterface.sh.dataitem.excelmodel;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @author 老花生
 */
public class ShybYlqcxxExcelModel {
	@ExcelProperty(value = "医保编码", index = 0)
    private String ybCode;
	@ExcelProperty(value = "信息状态", index = 1)
    private String status;
    @ExcelProperty(value = "信息起效日期", index = 2)
    private String beginDate;
    @ExcelProperty(value = "信息失效日期", index = 3)
    private String endDate;
	@ExcelProperty(value = "支付办法", index = 4)
    private String payType;
    @ExcelProperty(value = "注册证号/备案号", index = 5)
    private String registerNo;
    @ExcelProperty(value = "注册证号/备案号开始日期", index = 6)
    private String registerBeginDate;
    @ExcelProperty(value = "注册证号/备案号结束日期", index = 7)
    private String registerEndDate;
    @ExcelProperty(value = "品名", index = 8)
    private String materialName;
    @ExcelProperty(value = "规格", index = 9)
    private String materialSpec;
    @ExcelProperty(value = "计价单位", index = 10)
    private String autName;
    @ExcelProperty(value = "品牌", index = 11)
    private String brand;
    @ExcelProperty(value = "生产企业", index = 12)
    private String factory;
    @ExcelProperty(value = "材质", index = 13)
    private String texture;
    @ExcelProperty(value = "备注", index = 14)
    private String remark;
    @ExcelProperty(value = "一级目录", index = 15)
    private String firstLevel;
    @ExcelProperty(value = "二级目录", index = 16)
    private String secondLevel;
    @ExcelProperty(value = "三级目录", index = 17)
    private String thirdLevel;
    @ExcelProperty(value = "四级目录", index = 18)
    private String fourLevel;
    
	public String getYbCode() {
		return ybCode;
	}
	public void setYbCode(String ybCode) {
		this.ybCode = ybCode;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRegisterNo() {
		return registerNo;
	}
	public void setRegisterNo(String registerNo) {
		this.registerNo = registerNo;
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
	public String getRegisterBeginDate() {
		return registerBeginDate;
	}
	public void setRegisterBeginDate(String registerBeginDate) {
		this.registerBeginDate = registerBeginDate;
	}
	public String getRegisterEndDate() {
		return registerEndDate;
	}
	public void setRegisterEndDate(String registerEndDate) {
		this.registerEndDate = registerEndDate;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getMaterialSpec() {
		return materialSpec;
	}
	public void setMaterialSpec(String materialSpec) {
		this.materialSpec = materialSpec;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getAutName() {
		return autName;
	}
	public void setAutName(String autName) {
		this.autName = autName;
	}
	public String getFactory() {
		return factory;
	}
	public void setFactory(String factory) {
		this.factory = factory;
	}
	public String getTexture() {
		return texture;
	}
	public void setTexture(String texture) {
		this.texture = texture;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getFirstLevel() {
		return firstLevel;
	}
	public void setFirstLevel(String firstLevel) {
		this.firstLevel = firstLevel;
	}
	public String getSecondLevel() {
		return secondLevel;
	}
	public void setSecondLevel(String secondLevel) {
		this.secondLevel = secondLevel;
	}
	public String getThirdLevel() {
		return thirdLevel;
	}
	public void setThirdLevel(String thirdLevel) {
		this.thirdLevel = thirdLevel;
	}
	public String getFourLevel() {
		return fourLevel;
	}
	public void setFourLevel(String fourLevel) {
		this.fourLevel = fourLevel;
	}
}
