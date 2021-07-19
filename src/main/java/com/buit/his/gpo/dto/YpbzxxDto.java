package com.buit.his.gpo.dto;

import java.math.BigDecimal;

/**
 * @Description
 * @Author yueyu
 * @Date 2021/5/7 13:30
 */
public class YpbzxxDto {

    private String tbdm;
    private String jjdw;
    private String bfdw;
    private Integer jjbz;
    private Integer bfbz;
    private BigDecimal cgdj;
    private BigDecimal bfdj;
    private String cdmc;

    public String getCdmc() {
        return cdmc;
    }

    public void setCdmc(String cdmc) {
        this.cdmc = cdmc;
    }

    public BigDecimal getCgdj() {
        return cgdj;
    }

    public void setCgdj(BigDecimal cgdj) {
        this.cgdj = cgdj;
    }

    public BigDecimal getBfdj() {
        return bfdj;
    }

    public void setBfdj(BigDecimal bfdj) {
        this.bfdj = bfdj;
    }

    public String getTbdm() {
        return tbdm;
    }

    public void setTbdm(String tbdm) {
        this.tbdm = tbdm;
    }

    public String getJjdw() {
        return jjdw;
    }

    public void setJjdw(String jjdw) {
        this.jjdw = jjdw;
    }


    public Integer getJjbz() {
        return jjbz;
    }

    public void setJjbz(Integer jjbz) {
        this.jjbz = jjbz;
    }

    public String getBfdw() {
        return bfdw;
    }

    public void setBfdw(String bfdw) {
        this.bfdw = bfdw;
    }

    public Integer getBfbz() {
        return bfbz;
    }

    public void setBfbz(Integer bfbz) {
        this.bfbz = bfbz;
    }
}
