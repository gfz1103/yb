package com.buit.his.gpo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：GpoJgxx<br> 
 * 类描述：gpo 机构信息
 * @author sunjx
 */
@ApiModel(value="gpo 机构信息")
public class GpoJgxx {

	@ApiModelProperty(value="xh")
    private Integer xh;

	@ApiModelProperty(value="jgid")
    private String jgid;

	@ApiModelProperty(value="代码")
    private String gpoJgdm;

	@ApiModelProperty(value="机构名称")
    private String jgmc;

	@ApiModelProperty(value="说明")
    private String desc;


    public void setXh(Integer value) {
        this.xh = value;
    }
    public Integer getXh() {
        return xh;
    }

    public void setJgid(String value) {
        this.jgid = value;
    }
    public String getJgid() {
        return jgid;
    }

    public void setGpoJgdm(String value) {
        this.gpoJgdm = value;
    }
    public String getGpoJgdm() {
        return gpoJgdm;
    }

    public void setJgmc(String value) {
        this.jgmc = value;
    }
    public String getJgmc() {
        return jgmc;
    }

    public void setDesc(String value) {
        this.desc = value;
    }
    public String getDesc() {
        return desc;
    }


}