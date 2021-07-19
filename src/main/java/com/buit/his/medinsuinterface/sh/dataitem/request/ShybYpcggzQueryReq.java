   
package com.buit.his.medinsuinterface.sh.dataitem.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：ShybYpcggz<br> 
 * 类描述：药品采购规则<br>
 * @author 老花生 
 */
@ApiModel(value="药品采购规则-查询")
public class ShybYpcggzQueryReq  extends  PageQuery{
    @ApiModelProperty(value="统编代码")
    private String tbdm;
	@ApiModelProperty(value="医疗机构ID")
    private Integer hospitalId;
    
    /**
     * 设置:统编代码
     */
    public void setTbdm(String value) {
        this.tbdm = value;
    }
    /**
     * 获取:统编代码
     */
    public String getTbdm() {
        return tbdm;
    }
	public Integer getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}
    
}