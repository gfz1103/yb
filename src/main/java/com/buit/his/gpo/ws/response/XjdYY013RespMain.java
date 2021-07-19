package com.buit.his.gpo.ws.response;

/**
 * @Author sunjx
 * @Date 2020-11-09 10:31
 * @Description YY013 询价单填报响应main
 * 参考文档:
 *  上海市医药采购服务与监管信息系统医院接口规范.pdf
 **/
public class XjdYY013RespMain {

    //询价单编号 市药事系统返回的可唯一标识的询价单编号
    private String XJDBH;
    //商品类型 详见4.1.5商品类型
    private String SPLX;
    //药品类型 详见4.1.1药品类型
    private String YPLX;
    //药品统编代码 统一发布的药品商品唯一标识编码
    private String ZXSPBM;
    //医院商品编码 医院内部的该药品商品唯一标识编码
    private String YYSPBM;

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
}
