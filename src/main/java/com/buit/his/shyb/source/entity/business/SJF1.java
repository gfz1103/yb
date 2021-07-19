package com.buit.his.shyb.source.entity.business;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * 居保门诊转院查询
 * @author beijunhua
 */
@ApiModel(value="居保门诊转院查询")
public class SJF1 {
    /**
     * 凭证类别
     */
    private String cardtype;

    /**
     * 凭证码
     */
    private String carddata;

    /**
     * 登记类别
     */
    private String djtype;

    /**
     * 登记号
     */
    @NotNull(message = "证明单号 不能为空")
    @ApiModelProperty(value="证明单号")
    private String djno;

    /**
     * 门诊转院日期开始日期
     */
    private String startdate;


    /**
     * 转入医院编码
     */
    private String zrjgbh;

    private String personname;
    private String zcyybm;

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    public String getCarddata() {
        return carddata;
    }

    public void setCarddata(String carddata) {
        this.carddata = carddata;
    }

    public String getDjtype() {
        return djtype;
    }

    public void setDjtype(String djtype) {
        this.djtype = djtype;
    }

    public String getDjno() {
        return djno;
    }

    public void setDjno(String djno) {
        this.djno = djno;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getZrjgbh() {
        return zrjgbh;
    }

    public void setZrjgbh(String zrjgbh) {
        this.zrjgbh = zrjgbh;
    }

    public String getPersonname() {
        return personname;
    }

    public void setPersonname(String personname) {
        this.personname = personname;
    }

    public String getZcyybm() {
        return zcyybm;
    }

    public void setZcyybm(String zcyybm) {
        this.zcyybm = zcyybm;
    }
}
