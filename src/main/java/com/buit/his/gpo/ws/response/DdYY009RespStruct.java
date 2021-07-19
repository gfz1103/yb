package com.buit.his.gpo.ws.response;

/**
 * @Author sunjx
 * @Date 2020-11-10 13:40
 * @Description
 **/
public class DdYY009RespStruct {

    // 订单明细编号  市药事系统返回的可唯一标识此订单明细的编号
    private String DDMXBH;
    // 顺序号  上报订单的顺序号，同一个订单内的顺序号必须唯一
    private String SXH;
    // 商品类型  见4.1.5商品类型
    private String SPLX;
    // 商品统编代码  统一发布的药品商品唯一标识编码
    private String ZXSPBM;
    // 医院商品编码  预留字段，暂不使用
    // 处理结果  详见4.1.12消息明细条目处理结果
    private String CLJG;
    // 处理情况描述 消息明细条目处理结果的详细描述
    private String CLQKMS;

    public String getYYSPBM() {
        return YYSPBM;
    }

    public void setYYSPBM(String YYSPBM) {
        this.YYSPBM = YYSPBM;
    }

    private String YYSPBM;

    public String getDDMXBH() {
        return DDMXBH;
    }

    public void setDDMXBH(String DDMXBH) {
        this.DDMXBH = DDMXBH;
    }

    public String getSXH() {
        return SXH;
    }

    public void setSXH(String SXH) {
        this.SXH = SXH;
    }

    public String getSPLX() {
        return SPLX;
    }

    public void setSPLX(String SPLX) {
        this.SPLX = SPLX;
    }

    public String getZXSPBM() {
        return ZXSPBM;
    }

    public void setZXSPBM(String ZXSPBM) {
        this.ZXSPBM = ZXSPBM;
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
