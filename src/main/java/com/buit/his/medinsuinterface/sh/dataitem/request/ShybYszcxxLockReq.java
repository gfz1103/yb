   
package com.buit.his.medinsuinterface.sh.dataitem.request;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * 类名称：ShybYszcxx<br> 
 * 类描述：上海医保医师注册信息<br>
 * @author 老花生
 */
@ApiModel(value="上海医保医师注册信息")
public class ShybYszcxxLockReq  extends  PageQuery{
    @NotNull(message = "医保医生数据ID 不能为空")
	@ApiModelProperty(value="医保医生数据ID")
    private Integer ybdoctordataId;
    @NotBlank(message = "锁定标识 不能为空")
    @ApiModelProperty(value="锁定标识")
    private String lockFlag;
    
	public Integer getYbdoctordataId() {
		return ybdoctordataId;
	}
	public void setYbdoctordataId(Integer ybdoctordataId) {
		this.ybdoctordataId = ybdoctordataId;
	}
	public String getLockFlag() {
		return lockFlag;
	}
	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
	}
    
}
