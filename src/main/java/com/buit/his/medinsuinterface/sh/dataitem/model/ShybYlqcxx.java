package com.buit.his.medinsuinterface.sh.dataitem.model;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;

import java.sql.Timestamp;

/**
 * 类名称：ShybYlqcxx<br> 
 * 类描述：上海医保医疗器材信息<br>
 * @author 老花生
 */
@ApiModel(value="上海医保医疗器材信息")
public class ShybYlqcxx  extends  PageQuery{
	//@ApiModelProperty(value="医保医疗器材ID")
    /** 医保医疗器材ID */
    private Integer ybmaterialdataId;
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
	//@ApiModelProperty(value="信息状态")
    /** 信息状态 */
    private String status;
	//@ApiModelProperty(value="信息起效日期")
    /** 信息起效日期 */
    private String beginDate;
	//@ApiModelProperty(value="信息失效日期")
    /** 信息失效日期 */
    private String endDate;
	//@ApiModelProperty(value="支付办法")
    /** 支付办法 */
    private String payType;
	//@ApiModelProperty(value="注册证号/备案号")
    /** 注册证号/备案号 */
    private String registerNo;
	//@ApiModelProperty(value="注册证号/备案号开始日期")
    /** 注册证号/备案号开始日期 */
    private String registerBeginDate;
	//@ApiModelProperty(value="注册证号/备案号结束日期")
    /** 注册证号/备案号结束日期 */
    private String registerEndDate;
	//@ApiModelProperty(value="品名")
    /** 品名 */
    private String materialName;
	//@ApiModelProperty(value="规格")
    /** 规格 */
    private String materialSpec;
	//@ApiModelProperty(value="计价单位")
    /** 计价单位 */
    private String autName;
	//@ApiModelProperty(value="品牌")
    /** 品牌 */
    private String brand;
	//@ApiModelProperty(value="生产企业")
    /** 生产企业 */
    private String factory;
	//@ApiModelProperty(value="材质")
    /** 材质 */
    private String texture;
	//@ApiModelProperty(value="备注")
    /** 备注 */
    private String remark;
	//@ApiModelProperty(value="一级目录")
    /** 一级目录 */
    private String firstLevel;
	//@ApiModelProperty(value="二级目录")
    /** 二级目录 */
    private String secondLevel;
	//@ApiModelProperty(value="三级目录")
    /** 三级目录 */
    private String thirdLevel;
	//@ApiModelProperty(value="四级目录")
    /** 四级目录 */
    private String fourLevel;

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

    /** 设置:医保医疗器材ID  */
    public void setYbmaterialdataId(Integer value) {
        this.ybmaterialdataId = value;
    }
    /** 获取:医保医疗器材ID */
    public Integer getYbmaterialdataId() {
        return ybmaterialdataId;
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

    /** 设置:信息状态  */
    public void setStatus(String value) {
        this.status = value;
    }
    /** 获取:信息状态 */
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

    /** 设置:支付办法  */
    public void setPayType(String value) {
        this.payType = value;
    }
    /** 获取:支付办法 */
    public String getPayType() {
        return payType;
    }

    /** 设置:注册证号/备案号  */
    public void setRegisterNo(String value) {
        this.registerNo = value;
    }
    /** 获取:注册证号/备案号 */
    public String getRegisterNo() {
        return registerNo;
    }

    /** 设置:注册证号/备案号开始日期  */
    public void setRegisterBeginDate(String value) {
        this.registerBeginDate = value;
    }
    /** 获取:注册证号/备案号开始日期 */
    public String getRegisterBeginDate() {
        return registerBeginDate;
    }

    /** 设置:注册证号/备案号结束日期  */
    public void setRegisterEndDate(String value) {
        this.registerEndDate = value;
    }
    /** 获取:注册证号/备案号结束日期 */
    public String getRegisterEndDate() {
        return registerEndDate;
    }

    /** 设置:品名  */
    public void setMaterialName(String value) {
        this.materialName = value;
    }
    /** 获取:品名 */
    public String getMaterialName() {
        return materialName;
    }

    /** 设置:规格  */
    public void setMaterialSpec(String value) {
        this.materialSpec = value;
    }
    /** 获取:规格 */
    public String getMaterialSpec() {
        return materialSpec;
    }

    /** 设置:计价单位  */
    public void setAutName(String value) {
        this.autName = value;
    }
    /** 获取:计价单位 */
    public String getAutName() {
        return autName;
    }

    /** 设置:品牌  */
    public void setBrand(String value) {
        this.brand = value;
    }
    /** 获取:品牌 */
    public String getBrand() {
        return brand;
    }

    /** 设置:生产企业  */
    public void setFactory(String value) {
        this.factory = value;
    }
    /** 获取:生产企业 */
    public String getFactory() {
        return factory;
    }

    /** 设置:材质  */
    public void setTexture(String value) {
        this.texture = value;
    }
    /** 获取:材质 */
    public String getTexture() {
        return texture;
    }

    /** 设置:备注  */
    public void setRemark(String value) {
        this.remark = value;
    }
    /** 获取:备注 */
    public String getRemark() {
        return remark;
    }

    /** 设置:一级目录  */
    public void setFirstLevel(String value) {
        this.firstLevel = value;
    }
    /** 获取:一级目录 */
    public String getFirstLevel() {
        return firstLevel;
    }

    /** 设置:二级目录  */
    public void setSecondLevel(String value) {
        this.secondLevel = value;
    }
    /** 获取:二级目录 */
    public String getSecondLevel() {
        return secondLevel;
    }

    /** 设置:三级目录  */
    public void setThirdLevel(String value) {
        this.thirdLevel = value;
    }
    /** 获取:三级目录 */
    public String getThirdLevel() {
        return thirdLevel;
    }

    /** 设置:四级目录  */
    public void setFourLevel(String value) {
        this.fourLevel = value;
    }
    /** 获取:四级目录 */
    public String getFourLevel() {
        return fourLevel;
    }


}
