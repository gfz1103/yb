   
package com.buit.his.medinsuinterface.sh.dataitem.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：ShybYlqcxx<br> 
 * 类描述：上海医保医疗器材信息<br>
 * @author 老花生
 */
@ApiModel(value="上海医保医疗器材信息-查询")
public class ShybYlqcxxQueryReq  extends  PageQuery{
    @ApiModelProperty(value="查询条件")
    private String materialName;
    @ApiModelProperty(value="医保编码")
    private String ybCode;
	@ApiModelProperty(value="医疗机构ID")
    private Integer hospitalId;

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getYbCode() {
		return ybCode;
	}

	public void setYbCode(String ybCode) {
		this.ybCode = ybCode;
	}

	public Integer getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}
    
}
