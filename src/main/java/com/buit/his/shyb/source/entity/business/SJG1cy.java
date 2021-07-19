package com.buit.his.shyb.source.entity.business;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 居保门诊转院查询
 * @author beijunhua
 */
@ApiModel(value="工伤认定号查询")
public class SJG1cy {
    /**
     * 凭证类别
     */
    private String cardtype;

    /**
     * 凭证码
     */
    private String carddata;

    private List<SJG1Rescy> GSXXS;

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

    public List<SJG1Rescy> getGSXXS() {
        return GSXXS;
    }

    public void setGSXXS(List<SJG1Rescy> GSXXS) {
        this.GSXXS = GSXXS;
    }
}
