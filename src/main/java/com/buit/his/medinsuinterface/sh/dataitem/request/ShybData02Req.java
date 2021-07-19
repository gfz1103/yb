   
package com.buit.his.medinsuinterface.sh.dataitem.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;


/**
 * 类名称：ShybData01Value<br> 
 * 类描述：医保数据内容表<br>
 * @author 老花生
 */
@ApiModel(value="医保数据内容表")
public class ShybData02Req  extends  PageQuery{
    @ApiModelProperty(value="医保数据ID")
    private Integer ybdatavalueId;
    @ApiModelProperty(value="版本号")
    private Integer dataVersion;
    @ApiModelProperty(value="创建时间")
    private Timestamp gmtCreate;
    @ApiModelProperty(value="最后修改时间")
    private Timestamp gmtModify;
    @ApiModelProperty(value="医疗机构ID")
    private Integer hospitalId;
    @ApiModelProperty(value="医保字典ID")
    private Integer ybdataId;
    @ApiModelProperty(value="排序号")
    private Integer serialNumber;
    @ApiModelProperty(value="数据代码")
    private String dataCode;
    @ApiModelProperty(value="数据名称")
    private String dataName;
    @ApiModelProperty(value="数据类型")
    private String dataType;
    @ApiModelProperty(value="数据编号")
    private String dataNum;
    @ApiModelProperty(value="父类数据ID")
    private Integer parentDatavalueId;
    @ApiModelProperty(value="拼音码")
    private String pyCode;
    @ApiModelProperty(value="五笔码")
    private String wbCode;
    @ApiModelProperty(value="是否停用 1-停用")
    private String stopFlag;
    /**
     * 设置:医保数据ID
     */
    public void setYbdatavalueId(Integer value) {
        this.ybdatavalueId = value;
    }
    /**
     * 获取:医保数据ID
     */
    public Integer getYbdatavalueId() {
        return ybdatavalueId;
    }
    /**
     * 设置:版本号
     */
    public void setDataVersion(Integer value) {
        this.dataVersion = value;
    }
    /**
     * 获取:版本号
     */
    public Integer getDataVersion() {
        return dataVersion;
    }
    /**
     * 设置:创建时间
     */
    public void setGmtCreate(Timestamp value) {
        this.gmtCreate = value;
    }
    /**
     * 获取:创建时间
     */
    public Timestamp getGmtCreate() {
        return gmtCreate;
    }
    /**
     * 设置:最后修改时间
     */
    public void setGmtModify(Timestamp value) {
        this.gmtModify = value;
    }
    /**
     * 获取:最后修改时间
     */
    public Timestamp getGmtModify() {
        return gmtModify;
    }
    /**
     * 设置:医疗机构ID
     */
    public void setHospitalId(Integer value) {
        this.hospitalId = value;
    }
    /**
     * 获取:医疗机构ID
     */
    public Integer getHospitalId() {
        return hospitalId;
    }
    /**
     * 设置:医保字典ID
     */
    public void setYbdataId(Integer value) {
        this.ybdataId = value;
    }
    /**
     * 获取:医保字典ID
     */
    public Integer getYbdataId() {
        return ybdataId;
    }
    /**
     * 设置:排序号
     */
    public void setSerialNumber(Integer value) {
        this.serialNumber = value;
    }
    /**
     * 获取:排序号
     */
    public Integer getSerialNumber() {
        return serialNumber;
    }
    /**
     * 设置:数据代码
     */
    public void setDataCode(String value) {
        this.dataCode = value;
    }
    /**
     * 获取:数据代码
     */
    public String getDataCode() {
        return dataCode;
    }
    /**
     * 设置:数据名称
     */
    public void setDataName(String value) {
        this.dataName = value;
    }
    /**
     * 获取:数据名称
     */
    public String getDataName() {
        return dataName;
    }
    /**
     * 设置:数据类型
     */
    public void setDataType(String value) {
        this.dataType = value;
    }
    /**
     * 获取:数据类型
     */
    public String getDataType() {
        return dataType;
    }
    /**
     * 设置:数据编号
     */
    public void setDataNum(String value) {
        this.dataNum = value;
    }
    /**
     * 获取:数据编号
     */
    public String getDataNum() {
        return dataNum;
    }
    /**
     * 设置:父类数据ID
     */
    public void setParentDatavalueId(Integer value) {
        this.parentDatavalueId = value;
    }
    /**
     * 获取:父类数据ID
     */
    public Integer getParentDatavalueId() {
        return parentDatavalueId;
    }
    /**
     * 设置:拼音码
     */
    public void setPyCode(String value) {
        this.pyCode = value;
    }
    /**
     * 获取:拼音码
     */
    public String getPyCode() {
        return pyCode;
    }
    /**
     * 设置:五笔码
     */
    public void setWbCode(String value) {
        this.wbCode = value;
    }
    /**
     * 获取:五笔码
     */
    public String getWbCode() {
        return wbCode;
    }
    /**
     * 设置:是否停用 1-停用
     */
    public void setStopFlag(String value) {
        this.stopFlag = value;
    }
    /**
     * 获取:是否停用 1-停用
     */
    public String getStopFlag() {
        return stopFlag;
    }
}
