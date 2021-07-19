package com.buit.his.gpo.dto;

import java.math.BigDecimal;

/**
 * @Description
 * @Author yueyu
 * @Date 2021/5/7 14:08
 */
public class YpbzzhDto {

    private Integer bzsl;

    private String dwmc;

    private BigDecimal bzjg;

    public BigDecimal getBzjg() {
        return bzjg;
    }

    public void setBzjg(BigDecimal bzjg) {
        this.bzjg = bzjg;
    }

    public Integer getBzsl() {
        return bzsl;
    }

    public void setBzsl(Integer bzsl) {
        this.bzsl = bzsl;
    }

    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }
}
