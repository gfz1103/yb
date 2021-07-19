   
package com.buit.his.medinsuinterface.sh.dataitem.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;


/**
 * 类名称：ShybData01Value<br> 
 * 类描述：医保数据内容表<br>
 * @author 老花生
 */
@ApiModel(value="医保数据内容表查询-分页查询")
public class ShybData02QueryReq  extends  PageQuery{
	@NotNull(message = "医保字典ID不能为空")
    @ApiModelProperty(value="医保字典ID 1：区县代码表、2：科室编码表、3：大病项目代码表、4：门诊大病登记疾病诊断分类、5：医保门诊项目类别、6：医保住院项目类别")
    private Integer ybdataId;
    @ApiModelProperty(value="数据名称")
    private String dataName;
	@ApiModelProperty(value="父类数据ID")
    private Integer parentDatavalueId;
    
	public Integer getYbdataId() {
		return ybdataId;
	}
	public void setYbdataId(Integer ybdataId) {
		this.ybdataId = ybdataId;
	}
	public String getDataName() {
		return dataName;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	public Integer getParentDatavalueId() {
		return parentDatavalueId;
	}
	public void setParentDatavalueId(Integer parentDatavalueId) {
		this.parentDatavalueId = parentDatavalueId;
	}
    
    
}
