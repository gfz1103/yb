   
package com.buit.his.medinsuinterface.sh.dataitem.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：ShybYppsqy<br> 
 * 类描述：药品配送企业表<br>
 * @author 老花生
 */
@ApiModel(value="药品配送企业表-查询")
public class ShybYppsqyQueryReq  extends  PageQuery{
    @ApiModelProperty(value="统编代码")
    private String tbdm;
	@ApiModelProperty(value="医疗机构ID")
    private Integer hospitalId;

	public String getTbdm() {
		return tbdm;
	}

	public void setTbdm(String tbdm) {
		this.tbdm = tbdm;
	}

	public Integer getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}
    
}