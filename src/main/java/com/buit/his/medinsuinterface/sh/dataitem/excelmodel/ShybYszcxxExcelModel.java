package com.buit.his.medinsuinterface.sh.dataitem.excelmodel;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @author 老花生
 */
public class ShybYszcxxExcelModel {
	@ExcelProperty(value="执业医师医保标识", index = 0)
    private String doctorYbCode;
    @ExcelProperty(value="姓名", index = 1)
    private String doctorName;
    @ExcelProperty(value="性别", index = 2)
    private String doctorSex;
    @ExcelProperty(value="证件类型", index = 3)
    private String documentType;
    @ExcelProperty(value="证件编码", index = 4)
    private String documentNo;
    @ExcelProperty(value="资格证书编码", index = 5)
    private String quaNo;
    @ExcelProperty(value="执业证书编码", index = 6)
    private String workNo;
    @ExcelProperty(value="工号", index = 7)
    private String empNo;
    @ExcelProperty(value="医院代码", index = 8)
    private String hspCode;
    @ExcelProperty(value="医院名称", index = 9)
    private String hspName;
    @ExcelProperty(value="是否本院", index = 10)
    private String isHsp;
    @ExcelProperty(value="科室名称1", index = 11)
    private String deptName1;
    @ExcelProperty(value="科室名称2", index = 12)
    private String deptName2;
    @ExcelProperty(value="科室名称3", index = 13)
    private String deptName3;
    @ExcelProperty(value="是否透析医师", index = 14)
    private String ifTxDoctor;
    @ExcelProperty(value="是否肾移植术后抗排异诊治医师", index = 15)
    private String ifSyzDoctor;
    @ExcelProperty(value="是否肝移植医师", index = 16)
    private String ifGyzDoctor;
    @ExcelProperty(value="是否协议药品处方医师", index = 17)
    private String ifDrugDoctor;
    @ExcelProperty(value="医师级别", index = 18)
    private String doctorLevel;
    @ExcelProperty(value="执业地点", index = 19)
    private String workPlace;
    @ExcelProperty(value="出生年月", index = 20)
    private String birthday;
    @ExcelProperty(value="医师类别", index = 21)
    private String doctorType;
    @ExcelProperty(value="执业范围1", index = 22)
    private String workRange1;
    @ExcelProperty(value="执业范围2", index = 23)
    private String workRange2;
    @ExcelProperty(value="执业范围3", index = 24)
    private String workRange3;
    @ExcelProperty(value="最高学历", index = 25)
    private String highEdu;
    @ExcelProperty(value="专业技术职务", index = 26)
    private String caption;
    @ExcelProperty(value="执业证书发证时间", index = 27)
    private String cerDate;
    @ExcelProperty(value="进修开始日期", index = 28)
    private String beginDate;
    @ExcelProperty(value="进修结束日期", index = 29)
    private String endDate;
    @ExcelProperty(value="科室待定标志", index = 30)
    private String undeter;
	public String getDoctorYbCode() {
		return doctorYbCode;
	}
	public void setDoctorYbCode(String doctorYbCode) {
		this.doctorYbCode = doctorYbCode;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getDoctorSex() {
		return doctorSex;
	}
	public void setDoctorSex(String doctorSex) {
		this.doctorSex = doctorSex;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getDocumentNo() {
		return documentNo;
	}
	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}
	public String getQuaNo() {
		return quaNo;
	}
	public void setQuaNo(String quaNo) {
		this.quaNo = quaNo;
	}
	public String getWorkNo() {
		return workNo;
	}
	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getHspCode() {
		return hspCode;
	}
	public void setHspCode(String hspCode) {
		this.hspCode = hspCode;
	}
	public String getHspName() {
		return hspName;
	}
	public void setHspName(String hspName) {
		this.hspName = hspName;
	}
	public String getIsHsp() {
		return isHsp;
	}
	public void setIsHsp(String isHsp) {
		this.isHsp = isHsp;
	}
	public String getDeptName1() {
		return deptName1;
	}
	public void setDeptName1(String deptName1) {
		this.deptName1 = deptName1;
	}
	public String getDeptName2() {
		return deptName2;
	}
	public void setDeptName2(String deptName2) {
		this.deptName2 = deptName2;
	}
	public String getDeptName3() {
		return deptName3;
	}
	public void setDeptName3(String deptName3) {
		this.deptName3 = deptName3;
	}
	public String getIfTxDoctor() {
		return ifTxDoctor;
	}
	public void setIfTxDoctor(String ifTxDoctor) {
		this.ifTxDoctor = ifTxDoctor;
	}
	public String getIfSyzDoctor() {
		return ifSyzDoctor;
	}
	public void setIfSyzDoctor(String ifSyzDoctor) {
		this.ifSyzDoctor = ifSyzDoctor;
	}
	public String getIfGyzDoctor() {
		return ifGyzDoctor;
	}
	public void setIfGyzDoctor(String ifGyzDoctor) {
		this.ifGyzDoctor = ifGyzDoctor;
	}
	public String getIfDrugDoctor() {
		return ifDrugDoctor;
	}
	public void setIfDrugDoctor(String ifDrugDoctor) {
		this.ifDrugDoctor = ifDrugDoctor;
	}
	public String getDoctorLevel() {
		return doctorLevel;
	}
	public void setDoctorLevel(String doctorLevel) {
		this.doctorLevel = doctorLevel;
	}
	public String getWorkPlace() {
		return workPlace;
	}
	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getDoctorType() {
		return doctorType;
	}
	public void setDoctorType(String doctorType) {
		this.doctorType = doctorType;
	}
	public String getWorkRange1() {
		return workRange1;
	}
	public void setWorkRange1(String workRange1) {
		this.workRange1 = workRange1;
	}
	public String getWorkRange2() {
		return workRange2;
	}
	public void setWorkRange2(String workRange2) {
		this.workRange2 = workRange2;
	}
	public String getWorkRange3() {
		return workRange3;
	}
	public void setWorkRange3(String workRange3) {
		this.workRange3 = workRange3;
	}
	public String getHighEdu() {
		return highEdu;
	}
	public void setHighEdu(String highEdu) {
		this.highEdu = highEdu;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getCerDate() {
		return cerDate;
	}
	public void setCerDate(String cerDate) {
		this.cerDate = cerDate;
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
	public String getUndeter() {
		return undeter;
	}
	public void setUndeter(String undeter) {
		this.undeter = undeter;
	}
    
    
}
