package com.buit.his.shyb.source.entity.business;

import java.util.List;

/**
 * 登记查询实体类
 * @author beijunhua
 * @date 2020/8/18 8:52
 */
public class SJ31cy {
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
    private String djlb;

    private String personname;
    private String accountattr;

    private String curaccountamt;
    private String hisaccountamt;
    private String zkdbnye;

    private List<SJ31Rescy> DJXXS;

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

    public String getDjlb() {
        return djlb;
    }

    public void setDjlb(String djlb) {
        this.djlb = djlb;
    }

    public String getPersonname() {
        return personname;
    }

    public void setPersonname(String personname) {
        this.personname = personname;
    }

    public String getAccountattr() {
        return accountattr;
    }

    public void setAccountattr(String accountattr) {
        this.accountattr = accountattr;
    }

    public List<SJ31Rescy> getDJXXS() {
        return DJXXS;
    }

    public void setDJXXS(List<SJ31Rescy> DJXXS) {
        this.DJXXS = DJXXS;
    }

    public String getCuraccountamt() {
        return curaccountamt;
    }

    public void setCuraccountamt(String curaccountamt) {
        this.curaccountamt = curaccountamt;
    }

    public String getHisaccountamt() {
        return hisaccountamt;
    }

    public void setHisaccountamt(String hisaccountamt) {
        this.hisaccountamt = hisaccountamt;
    }

    public String getZkdbnye() {
        return zkdbnye;
    }

    public void setZkdbnye(String zkdbnye) {
        this.zkdbnye = zkdbnye;
    }
}
