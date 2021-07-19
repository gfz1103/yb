package com.buit.his.medinsuinterface.sh.dataitem.model;

import com.buit.commons.PageQuery;

import java.math.BigDecimal;

/**
 * 类描述：
 * @author WY 
 * @ApiModel(value="")
 */
public class YbMzdbDbks extends  PageQuery{
	//@ApiModelProperty(value="dbbm")
    /** dbbm */
    private String dbbm;
	//@ApiModelProperty(value="ksdm")
    /** ksdm */
    private BigDecimal ksdm;

    /** 设置:dbbm  */
    public void setDbbm(String value) {
        this.dbbm = value;
    }
    /** 获取:dbbm */
    public String getDbbm() {
        return dbbm;
    }

    /** 设置:ksdm  */
    public void setKsdm(BigDecimal value) {
        this.ksdm = value;
    }
    /** 获取:ksdm */
    public BigDecimal getKsdm() {
        return ksdm;
    }


}