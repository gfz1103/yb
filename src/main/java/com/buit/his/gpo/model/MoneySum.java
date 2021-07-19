package com.buit.his.gpo.model;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class MoneySum {
    @ApiModelProperty(value="零售总金额")
    private BigDecimal lszje;
    @ApiModelProperty(value="进价总金额")
    private BigDecimal jjzje;

    public BigDecimal getLszje() {
        return lszje;
    }

    public void setLszje(BigDecimal lszje) {
        this.lszje = lszje;
    }

    public BigDecimal getJjzje() {
        return jjzje;
    }

    public void setJjzje(BigDecimal jjzje) {
        this.jjzje = jjzje;
    }
}
