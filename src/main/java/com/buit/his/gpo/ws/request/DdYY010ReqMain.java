package com.buit.his.gpo.ws.request;

/**
 * @Author sunjx
 * @Date 2020-11-11 16:33
 * @Description
 **/
public class DdYY010ReqMain {

    //医院编码 字符串 20 必填 市药事系统对药企的统编代码
    private String YYBM;
    //配送点编码 字符串 20 必填 医院预先提交的医院内配送点编码
    private String PSDBM;
    //订单类型 字符串 1 必填 详见4.1.14订单类型，对于医院药库订单和药企托管药库订单加以区分
    private String DDLX;
    //订单编号 字符串 20 必填 YY009接口返回的订单编号
    private String DDBH;
    //商品数量 数字 10 必填 该订单所包含的订单明细的数量
    private Integer SPSL;

    public String getYYBM() {
        return YYBM;
    }

    public void setYYBM(String YYBM) {
        this.YYBM = YYBM;
    }

    public String getPSDBM() {
        return PSDBM;
    }

    public void setPSDBM(String PSDBM) {
        this.PSDBM = PSDBM;
    }

    public String getDDLX() {
        return DDLX;
    }

    public void setDDLX(String DDLX) {
        this.DDLX = DDLX;
    }

    public String getDDBH() {
        return DDBH;
    }

    public void setDDBH(String DDBH) {
        this.DDBH = DDBH;
    }

    public Integer getSPSL() {
        return SPSL;
    }

    public void setSPSL(Integer SPSL) {
        this.SPSL = SPSL;
    }
}
