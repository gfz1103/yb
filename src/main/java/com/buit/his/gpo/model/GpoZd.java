package com.buit.his.gpo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：GpoZd<br> 
 * 类描述：gpo 字典
 * @author sunjx
 */
@ApiModel(value="gpo 字典")
public class GpoZd {

	@ApiModelProperty(value="xh")
    private Integer xh;

	@ApiModelProperty(value="com.buit.his.gpo.ws.GpoZd.TYPE")
    private String type;

	@ApiModelProperty(value="代码")
    private String code;

	@ApiModelProperty(value="显示文本")
    private String txt;

	@ApiModelProperty(value="说明")
    private String desc;

    public void setXh(Integer value) {
        this.xh = value;
    }
    public Integer getXh() {
        return xh;
    }

    public void setType(String value) {
        this.type = value;
    }
    public String getType() {
        return type;
    }

    public void setCode(String value) {
        this.code = value;
    }
    public String getCode() {
        return code;
    }

    public void setTxt(String value) {
        this.txt = value;
    }
    public String getTxt() {
        return txt;
    }

    public void setDesc(String value) {
        this.desc = value;
    }
    public String getDesc() {
        return desc;
    }


}