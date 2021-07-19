package com.buit.his.shyb.source.entity.business;

/**
 * 交易查询实体类
 * @author beijunhua
 * @date 2020/8/18 17:54
 */
public class SI91 {
    /**
     * 凭证类别
     */

    private String cardtype;

    /**
     * 凭证码
     */

    private String carddata;

    /**
     * 计算申请序号
     */
    private String jssqxh;

    /**
     * 交易费用总额
     */

    private String totalexpense;

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

    public String getJssqxh() {
        return jssqxh;
    }

    public void setJssqxh(String jssqxh) {
        this.jssqxh = jssqxh;
    }

    public String getTotalexpense() {
        return totalexpense;
    }

    public void setTotalexpense(String totalexpense) {
        this.totalexpense = totalexpense;
    }

}
