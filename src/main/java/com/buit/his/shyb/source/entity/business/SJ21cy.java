package com.buit.his.shyb.source.entity.business;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;
/**
 * 登记撤销实体类
 * @author beijunhua
 * @date 2020/8/18 8:52
 */
public class SJ21cy {
    /**
     * 凭证类别
     */
    private String cardtype;
    /**
     * 凭证码
     */
    private String carddata;
    /**
     * 撤销类别
     */
    private String cxtype;
    /**
     * 撤销大病项目
     */
    private String dbxm;

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

    public String getCxtype() {
        return cxtype;
    }

    public void setCxtype(String cxtype) {
        this.cxtype = cxtype;
    }

    public String getDbxm() {
        return dbxm;
    }

    public void setDbxm(String dbxm) {
        this.dbxm = dbxm;
    }

}
