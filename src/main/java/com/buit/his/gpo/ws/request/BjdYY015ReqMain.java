package com.buit.his.gpo.ws.request;

/**
 * @Author sunjx
 * @Date 2020-11-09 16:15
 * @Description YY015 报价单
 * 参考文档:
 *  上海市医药采购服务与监管信息系统医院接口规范.pdf
 **/
public class BjdYY015ReqMain {

    //药企编码  选填 作为选择的查询条件，市药事系统发布的对药企的统编代码
    private String YQBM;
    //起始日期 必填
    private String QSRQ;
    //截止日期 必填
    private String JZRQ;
    //商品类型 选填 作为可选择的查询条件的商品类型和药品类型，当不填报时则表示要
    private String SPLX;
    //药品类型 选填 求查询相关的全部询价单
    private String YPLX;
    //询价单编号  选填 市药事系统可唯一标识的询价单编号
    private String XJDBH;
    //报价单编号  选填 初次调用不填写，接续调用时须填写上次返回的最后一个报价单编号
    private String BJDBH;

    public String getYQBM() {
        return YQBM;
    }

    public void setYQBM(String YQBM) {
        this.YQBM = YQBM;
    }

    public String getQSRQ() {
        return QSRQ;
    }

    public void setQSRQ(String QSRQ) {
        this.QSRQ = QSRQ;
    }

    public String getJZRQ() {
        return JZRQ;
    }

    public void setJZRQ(String JZRQ) {
        this.JZRQ = JZRQ;
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

    public String getXJDBH() {
        return XJDBH;
    }

    public void setXJDBH(String XJDBH) {
        this.XJDBH = XJDBH;
    }

    public String getBJDBH() {
        return BJDBH;
    }

    public void setBJDBH(String BJDBH) {
        this.BJDBH = BJDBH;
    }
}
