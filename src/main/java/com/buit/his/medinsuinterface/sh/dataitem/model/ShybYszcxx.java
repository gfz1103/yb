package com.buit.his.medinsuinterface.sh.dataitem.model;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

/**
 * 类名称：ShybYszcxx<br> 
 * 类描述：上海医保医师注册信息<br>
 * @author 老花生
 */
@ApiModel(value="上海医保医师注册信息")
public class ShybYszcxx  extends  PageQuery{
	//@ApiModelProperty(value="医保医生数据ID")
    /** 医保医生数据ID */
    private Integer ybdoctordataId;
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
	//@ApiModelProperty(value="执业医师医保标识")
    /** 执业医师医保标识 */
    private String doctorYbCode;
	//@ApiModelProperty(value="姓名")
    /** 姓名 */
    private String doctorName;
	//@ApiModelProperty(value="性别")
    /** 性别 */
    private String doctorSex;
	//@ApiModelProperty(value="证件类型")
    /** 证件类型 */
    private String documentType;
	//@ApiModelProperty(value="证件编码")
    /** 证件编码 */
    private String documentNo;
	//@ApiModelProperty(value="资格证书编码")
    /** 资格证书编码 */
    private String quaNo;
	//@ApiModelProperty(value="执业证书编码")
    /** 执业证书编码 */
    private String workNo;
	//@ApiModelProperty(value="工号")
    /** 工号 */
    private String empNo;
	//@ApiModelProperty(value="医院代码")
    /** 医院代码 */
    private String hspCode;
	//@ApiModelProperty(value="医院名称")
    /** 医院名称 */
    private String hspName;
	//@ApiModelProperty(value="是否本院")
    /** 是否本院 */
    private String isHsp;
	//@ApiModelProperty(value="科室名称1")
    /** 科室名称1 */
    private String deptName1;
	//@ApiModelProperty(value="科室名称2")
    /** 科室名称2 */
    private String deptName2;
	//@ApiModelProperty(value="科室名称3")
    /** 科室名称3 */
    private String deptName3;
	//@ApiModelProperty(value="是否透析医师")
    /** 是否透析医师 */
    private String ifTxDoctor;
	//@ApiModelProperty(value="是否肾移植术后抗排异诊治医师")
    /** 是否肾移植术后抗排异诊治医师 */
    private String ifSyzDoctor;
	//@ApiModelProperty(value="是否肝移植医师")
    /** 是否肝移植医师 */
    private String ifGyzDoctor;
	//@ApiModelProperty(value="是否协议药品处方医师")
    /** 是否协议药品处方医师 */
    private String ifDrugDoctor;
	//@ApiModelProperty(value="医师级别")
    /** 医师级别 */
    private String doctorLevel;
	//@ApiModelProperty(value="执业地点")
    /** 执业地点 */
    private String workPlace;
	//@ApiModelProperty(value="出生年月")
    /** 出生年月 */
    private String birthday;
	//@ApiModelProperty(value="医师类别")
    /** 医师类别 */
    private String doctorType;
	//@ApiModelProperty(value="执业范围1")
    /** 执业范围1 */
    private String workRange1;
	//@ApiModelProperty(value="执业范围2")
    /** 执业范围2 */
    private String workRange2;
	//@ApiModelProperty(value="执业范围3")
    /** 执业范围3 */
    private String workRange3;
	//@ApiModelProperty(value="最高学历")
    /** 最高学历 */
    private String highEdu;
	//@ApiModelProperty(value="专业技术职务")
    /** 专业技术职务 */
    private String caption;
	//@ApiModelProperty(value="执业证书发证时间")
    /** 执业证书发证时间 */
    private String cerDate;
	//@ApiModelProperty(value="进修开始日期")
    /** 进修开始日期 */
    private String beginDate;
	//@ApiModelProperty(value="进修结束日期")
    /** 进修结束日期 */
    private String endDate;
	//@ApiModelProperty(value="科室待定标志")
    /** 科室待定标志 */
    private String undeter;
    @ApiModelProperty(value="锁定标识")
    private String lockFlag;

    @ApiModelProperty(value="拼音码")
    private String pyCode;
    @ApiModelProperty(value="五笔码")
    private String wbCode;

    /** 设置:医保医生数据ID  */
    public void setYbdoctordataId(Integer value) {
        this.ybdoctordataId = value;
    }
    /** 获取:医保医生数据ID */
    public Integer getYbdoctordataId() {
        return ybdoctordataId;
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

    /** 设置:执业医师医保标识  */
    public void setDoctorYbCode(String value) {
        this.doctorYbCode = value;
    }
    /** 获取:执业医师医保标识 */
    public String getDoctorYbCode() {
        return doctorYbCode;
    }

    /** 设置:姓名  */
    public void setDoctorName(String value) {
        this.doctorName = value;
    }
    /** 获取:姓名 */
    public String getDoctorName() {
        return doctorName;
    }

    /** 设置:性别  */
    public void setDoctorSex(String value) {
        this.doctorSex = value;
    }
    /** 获取:性别 */
    public String getDoctorSex() {
        return doctorSex;
    }

    /** 设置:证件类型  */
    public void setDocumentType(String value) {
        this.documentType = value;
    }
    /** 获取:证件类型 */
    public String getDocumentType() {
        return documentType;
    }

    /** 设置:证件编码  */
    public void setDocumentNo(String value) {
        this.documentNo = value;
    }
    /** 获取:证件编码 */
    public String getDocumentNo() {
        return documentNo;
    }

    /** 设置:资格证书编码  */
    public void setQuaNo(String value) {
        this.quaNo = value;
    }
    /** 获取:资格证书编码 */
    public String getQuaNo() {
        return quaNo;
    }

    /** 设置:执业证书编码  */
    public void setWorkNo(String value) {
        this.workNo = value;
    }
    /** 获取:执业证书编码 */
    public String getWorkNo() {
        return workNo;
    }

    /** 设置:工号  */
    public void setEmpNo(String value) {
        this.empNo = value;
    }
    /** 获取:工号 */
    public String getEmpNo() {
        return empNo;
    }

    /** 设置:医院代码  */
    public void setHspCode(String value) {
        this.hspCode = value;
    }
    /** 获取:医院代码 */
    public String getHspCode() {
        return hspCode;
    }

    /** 设置:医院名称  */
    public void setHspName(String value) {
        this.hspName = value;
    }
    /** 获取:医院名称 */
    public String getHspName() {
        return hspName;
    }

    /** 设置:是否本院  */
    public void setIsHsp(String value) {
        this.isHsp = value;
    }
    /** 获取:是否本院 */
    public String getIsHsp() {
        return isHsp;
    }

    /** 设置:科室名称1  */
    public void setDeptName1(String value) {
        this.deptName1 = value;
    }
    /** 获取:科室名称1 */
    public String getDeptName1() {
        return deptName1;
    }

    /** 设置:科室名称2  */
    public void setDeptName2(String value) {
        this.deptName2 = value;
    }
    /** 获取:科室名称2 */
    public String getDeptName2() {
        return deptName2;
    }

    /** 设置:科室名称3  */
    public void setDeptName3(String value) {
        this.deptName3 = value;
    }
    /** 获取:科室名称3 */
    public String getDeptName3() {
        return deptName3;
    }

    /** 设置:是否透析医师  */
    public void setIfTxDoctor(String value) {
        this.ifTxDoctor = value;
    }
    /** 获取:是否透析医师 */
    public String getIfTxDoctor() {
        return ifTxDoctor;
    }

    /** 设置:是否肾移植术后抗排异诊治医师  */
    public void setIfSyzDoctor(String value) {
        this.ifSyzDoctor = value;
    }
    /** 获取:是否肾移植术后抗排异诊治医师 */
    public String getIfSyzDoctor() {
        return ifSyzDoctor;
    }

    /** 设置:是否肝移植医师  */
    public void setIfGyzDoctor(String value) {
        this.ifGyzDoctor = value;
    }
    /** 获取:是否肝移植医师 */
    public String getIfGyzDoctor() {
        return ifGyzDoctor;
    }

    /** 设置:是否协议药品处方医师  */
    public void setIfDrugDoctor(String value) {
        this.ifDrugDoctor = value;
    }
    /** 获取:是否协议药品处方医师 */
    public String getIfDrugDoctor() {
        return ifDrugDoctor;
    }

    /** 设置:医师级别  */
    public void setDoctorLevel(String value) {
        this.doctorLevel = value;
    }
    /** 获取:医师级别 */
    public String getDoctorLevel() {
        return doctorLevel;
    }

    /** 设置:执业地点  */
    public void setWorkPlace(String value) {
        this.workPlace = value;
    }
    /** 获取:执业地点 */
    public String getWorkPlace() {
        return workPlace;
    }

    /** 设置:出生年月  */
    public void setBirthday(String value) {
        this.birthday = value;
    }
    /** 获取:出生年月 */
    public String getBirthday() {
        return birthday;
    }

    /** 设置:医师类别  */
    public void setDoctorType(String value) {
        this.doctorType = value;
    }
    /** 获取:医师类别 */
    public String getDoctorType() {
        return doctorType;
    }

    /** 设置:执业范围1  */
    public void setWorkRange1(String value) {
        this.workRange1 = value;
    }
    /** 获取:执业范围1 */
    public String getWorkRange1() {
        return workRange1;
    }

    /** 设置:执业范围2  */
    public void setWorkRange2(String value) {
        this.workRange2 = value;
    }
    /** 获取:执业范围2 */
    public String getWorkRange2() {
        return workRange2;
    }

    /** 设置:执业范围3  */
    public void setWorkRange3(String value) {
        this.workRange3 = value;
    }
    /** 获取:执业范围3 */
    public String getWorkRange3() {
        return workRange3;
    }

    /** 设置:最高学历  */
    public void setHighEdu(String value) {
        this.highEdu = value;
    }
    /** 获取:最高学历 */
    public String getHighEdu() {
        return highEdu;
    }

    /** 设置:专业技术职务  */
    public void setCaption(String value) {
        this.caption = value;
    }
    /** 获取:专业技术职务 */
    public String getCaption() {
        return caption;
    }

    /** 设置:执业证书发证时间  */
    public void setCerDate(String value) {
        this.cerDate = value;
    }
    /** 获取:执业证书发证时间 */
    public String getCerDate() {
        return cerDate;
    }

    /** 设置:进修开始日期  */
    public void setBeginDate(String value) {
        this.beginDate = value;
    }
    /** 获取:进修开始日期 */
    public String getBeginDate() {
        return beginDate;
    }

    /** 设置:进修结束日期  */
    public void setEndDate(String value) {
        this.endDate = value;
    }
    /** 获取:进修结束日期 */
    public String getEndDate() {
        return endDate;
    }

    /** 设置:科室待定标志  */
    public void setUndeter(String value) {
        this.undeter = value;
    }
    /** 获取:科室待定标志 */
    public String getUndeter() {
        return undeter;
    }
	public String getLockFlag() {
		return lockFlag;
	}
	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
	}


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
}
