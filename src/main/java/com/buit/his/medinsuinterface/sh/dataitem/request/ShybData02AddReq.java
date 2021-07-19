   
package com.buit.his.medinsuinterface.sh.dataitem.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：ShybData02<br> 
 * 类描述：医保数据内容表<br>
 * @author 老花生
 */
@ApiModel(value="医保数据内容表新增")
public class ShybData02AddReq{
    @ApiModelProperty(value="医保字典ID 1：区县代码表、2：科室编码表、3：大病项目代码表、4：门诊大病登记疾病诊断分类、5：医保门诊项目类别、6：医保住院项目类别")
    private Integer ybdataId;
    @ApiModelProperty(value="数据代码")
    private String dataCode;
    @ApiModelProperty(value="数据名称")
    private String dataName;
    @ApiModelProperty(value="数据类型--门诊大病登记疾病诊断分类的疾病分类")
    private String dataType;
    @ApiModelProperty(value="数据编号--区县代码的编码")
    private String dataNum;
    @ApiModelProperty(value="父类数据ID--新增下级时使用")
    private Integer parentDatavalueId;
    
	public Integer getYbdataId() {
		return ybdataId;
	}
	public void setYbdataId(Integer ybdataId) {
		this.ybdataId = ybdataId;
	}
	public String getDataCode() {
		return dataCode;
	}
	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
	}
	public String getDataName() {
		return dataName;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getDataNum() {
		return dataNum;
	}
	public void setDataNum(String dataNum) {
		this.dataNum = dataNum;
	}
	public Integer getParentDatavalueId() {
		return parentDatavalueId;
	}
	public void setParentDatavalueId(Integer parentDatavalueId) {
		this.parentDatavalueId = parentDatavalueId;
	}
}
