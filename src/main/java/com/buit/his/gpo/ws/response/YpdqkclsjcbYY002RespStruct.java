package com.buit.his.gpo.ws.response;

/**
 * @Author sunjx
 * @Date 2020-11-03 14:07
 * @Description YY002 药品当前库存量数据传报 请求参数
 * 参考文档:
 *  上海市医药采购服务与监管信息系统医院接口规范.pdf
 **/
public class YpdqkclsjcbYY002RespStruct {

    //商品类型 SPLX 字符串 1 详见4.1.5商品类型
    private String SPLX;
    //药品类型 YPLX 字符串 1 详见4.1.1药品类型，非药品无需填写
    private String YPLX;
    //商品统编代码 ZXSPBM 字符串 20 统一发布的药品商品唯一标识编码
    private String ZXSPBM;
    //医院商品编码 YYSPBM 字符串 20 医院内部的药品商品唯一标识编码
    private String YYSPBM;
    //处理结果 CLJG 字符串 5 详见4.1.12消息明细条目处理结果
    private String CLJG;
    //处理情况描述 CLQKMS 字符串 200 消息明细条目处理结果的详细描述
    private String CLQKMS;

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

    public String getCLJG() {
        return CLJG;
    }

    public void setCLJG(String CLJG) {
        this.CLJG = CLJG;
    }

    public String getCLQKMS() {
        return CLQKMS;
    }

    public void setCLQKMS(String CLQKMS) {
        this.CLQKMS = CLQKMS;
    }
}
