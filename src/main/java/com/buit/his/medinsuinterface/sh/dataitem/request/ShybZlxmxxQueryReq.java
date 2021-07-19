   
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
public class ShybZlxmxxQueryReq  extends  PageQuery{
    @ApiModelProperty(value="项目名称")
    private String itemName;
	@ApiModelProperty(value="医疗机构ID")
    private Integer hospitalId;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}
    
}
