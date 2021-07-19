package com.buit.his.gpo.ws.request;

/**
 * @Author sunjx
 * @Date 2020-11-09 10:19
 * @Description YY013 询价单填报请求main
 * 参考文档:
 *  上海市医药采购服务与监管信息系统医院接口规范.pdf
 **/
public class XjdYY013ReqMain {

    //操作类型 必填 详见4.1.6操作类型
    private String CZLX;
    //询价单编号  选填 初始询价不必填。作废时需要填。本药事系统返回的唯一标识编号。
    private String XJDBH;
    //商品类型 必填 详见4.1.5商品类型
    private String SPLX;
    //药品类型 必填 详见4.1.1药品类型
    private String YPLX;
    //商品统编代码  必填 统一发布的药品商品唯一标识编码
    private String ZXSPBM;
    //医院商品编码  必填 医院内部的商品唯一标识编码
    private String YYSPBM;
    //询价起始日期 必填药品询价的时间范围，日期格式B
    private String XJQSRQ;
    //询价截止日期 必填
    private String XJJZRQ;
    //备注说明  选填
    private String BZSM;

    public String getCZLX() {
        return CZLX;
    }

    public void setCZLX(String CZLX) {
        this.CZLX = CZLX;
    }

    public String getXJDBH() {
        return XJDBH;
    }

    public void setXJDBH(String XJDBH) {
        this.XJDBH = XJDBH;
    }

    public String getSPLX() {
        return SPLX;
    }

    public void setSPLX(String SPLX) {
        this.SPLX = SPLX;
    }

    public String getYPLX() {
        return YPLX;
    }

    public void setYPLX(String YPLX) {
        this.YPLX = YPLX;
    }

    public String getZXSPBM() {
        return ZXSPBM;
    }

    public void setZXSPBM(String ZXSPBM) {
        this.ZXSPBM = ZXSPBM;
    }

    public String getYYSPBM() {
        return YYSPBM;
    }

    public void setYYSPBM(String YYSPBM) {
        this.YYSPBM = YYSPBM;
    }

    public String getXJQSRQ() {
        return XJQSRQ;
    }

    public void setXJQSRQ(String XJQSRQ) {
        this.XJQSRQ = XJQSRQ;
    }

    public String getXJJZRQ() {
        return XJJZRQ;
    }

    public void setXJJZRQ(String XJJZRQ) {
        this.XJJZRQ = XJJZRQ;
    }

    public String getBZSM() {
        return BZSM;
    }

    public void setBZSM(String BZSM) {
        this.BZSM = BZSM;
    }
}
