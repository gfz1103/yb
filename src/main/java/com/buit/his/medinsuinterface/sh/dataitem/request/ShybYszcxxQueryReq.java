   
package com.buit.his.medinsuinterface.sh.dataitem.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：ShybYszcxx<br> 
 * 类描述：上海医保医师注册信息<br>
 * @author 老花生
 */
@ApiModel(value="上海医保医师注册信息-查询")
public class ShybYszcxxQueryReq  extends  PageQuery{
    @ApiModelProperty(value="姓名")
    private String doctorName;
	@ApiModelProperty(value="医疗机构ID")
    private Integer hospitalId;

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public Integer getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}
    
    
}
