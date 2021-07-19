package com.buit.his.shyb.source.entity.business;
/**
 * 账户查询实体类
 * @author beijunhua
 * @date 2020/8/18 8:52
 */
public class SM01 {
    //凭证类别
    private String cardtype;
    //凭证码
    private String carddata ;

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
}
