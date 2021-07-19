package com.buit.his.gpo.ws.request;

/**
 * @Author sunjx
 * @Date 2020-11-10 13:39
 * @Description
 **/
public class DdYY009ReqMain {

    // 操作类型  必填 详见4.1.6操作类型
    private String CZLX;
    // 医院编码  必填 市药事系统发布的对医院的统编代码
    private String YYBM;
    // 配送点编码  必填 指明配送到哪里。医院预先提交的医院内配送点编码
    private String PSDBM;
    // 订单类型  必填 详见4.1.14订单类型，对于医院药库订单和药企托管药库订单加以区分
    private String DDLX;
    // 订单编号  选填 对于新填报的订单无须填写，对于修改或撤销的订单则必须填写，填写的内容为市药事系统返回的订单编号。注意业务规则二对订单的修改或撤销的说法。
    private String DDBH;
    // 医院计划单号  选填 该订单对应于医院HIS的计划单号
    private String YYJHDH;
    // 最晚到货日期  选填 日期格式B，若填写，则超过最晚到货日期的订单将自动置为完结状态，不能再行配送
    private String ZWDHRQ;
    // 记录数 必填 本消息内的<DETAIL>记录条数
    private Integer JLS;

    public String getCZLX() {
        return CZLX;
    }

    public void setCZLX(String CZLX) {
        this.CZLX = CZLX;
    }

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

    public String getYYJHDH() {
        return YYJHDH;
    }

    public void setYYJHDH(String YYJHDH) {
        this.YYJHDH = YYJHDH;
    }

    public String getZWDHRQ() {
        return ZWDHRQ;
    }

    public void setZWDHRQ(String ZWDHRQ) {
        this.ZWDHRQ = ZWDHRQ;
    }

    public Integer getJLS() {
        return JLS;
    }

    public void setJLS(Integer JLS) {
        this.JLS = JLS;
    }
}
