package com.buit.his.gpo.ws.request;

/**
 * @Author sunjx
 * @Date 2020-11-03 14:07
 * @Description YY002 药品当前库存量数据传报 请求参数
 * 参考文档:
 *  上海市医药采购服务与监管信息系统医院接口规范.pdf
 **/
public class YpdqkclsjcbYY002ReqStruct {

    //商品类型 SPLX 字符串 1 必填 详见4.1.5商品类型
    private String SPLX;
    //药品类型 YPLX 字符串 1 必填 详见4.1.1药品类型，非药品无需填写
    private String YPLX;
    //商品统编代码 ZXSPBM 字符串 20 必填 统一发布的药品商品唯一标识编码
    private String ZXSPBM;
    //医院商品编码 YYSPBM 字符串 20 必填 医院内部的药品商品唯一标识编码
    private String YYSPBM;
    //配送点编码 PSDBM 字符串 20 必填 医院内部配送点编码，如西药库编码
    private String PSDBM;
    //当前库存量 YPKCL 数字 16,4 必填 按药品包装单位进行盘点的汇总剩余库存量，当前库存量不能是负数
    private String YPKCL;
    //库存单位 KCDW 字符串 10 必填 库存计量单位
    private String KCDW;

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

    public String getPSDBM() {
        return PSDBM;
    }

    public void setPSDBM(String PSDBM) {
        this.PSDBM = PSDBM;
    }

    public String getYPKCL() {
        return YPKCL;
    }

    public void setYPKCL(String YPKCL) {
        this.YPKCL = YPKCL;
    }

    public String getKCDW() {
        return KCDW;
    }

    public void setKCDW(String KCDW) {
        this.KCDW = KCDW;
    }
}
