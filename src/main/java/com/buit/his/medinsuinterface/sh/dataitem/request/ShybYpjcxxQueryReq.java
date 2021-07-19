   
package com.buit.his.medinsuinterface.sh.dataitem.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：ShybYpjcxx<br> 
 * 类描述：药品品规基础信息表<br>
 * @author 老花生
 */
@ApiModel(value="药品品规基础信息表-查询")
public class ShybYpjcxxQueryReq  extends  PageQuery{
    @ApiModelProperty(value="药品通用名")
    private String yptym;
	@ApiModelProperty(value="医疗机构ID")
    private Integer hospitalId;

	public String getYptym() {
		return yptym;
	}

	public void setYptym(String yptym) {
		this.yptym = yptym;
	}

	public Integer getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}
    
    
}