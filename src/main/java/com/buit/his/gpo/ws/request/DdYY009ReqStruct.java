package com.buit.his.gpo.ws.request;

/**
 * @Author sunjx
 * @Date 2020-11-10 13:39
 * @Description
 **/
public class DdYY009ReqStruct {

    // 顺序号 必填 上报订单的顺序号，同一个订单内的不同明细的顺序号必须唯一
    private String SXH;
    // 采购类型 必填 详见4.1.2采购类型
    private String CGLX;
    // 商品类型 必填 详见4.1.5商品类型
    private String SPLX;
    // 商品统编代码 必填 统一发布的药品商品唯一标识编码
    private String ZXSPBM;
    // 规格包装 选填 医院采购药品的规格包装要求，具体用于区分中药饮片的中、大包装说明，如7g一袋，7袋一包等
    private String GGBZ;
    // 采购计量单位必填 详见4.1.23采购计量单位
    private String CGJLDW;
    // 采购数量 必填 该品规药品的采购数量
    private String CGSL;
    // 采购单价 选填 带量采购无需填写，其他情况采购需要填写
    private String CGDJ;
    // 药企编码 必填 指明由哪家配送供货。市药事系统对药企的统编代码
    private String YQBM;
    // 多次配送标识 必填 0：不允许；1：允许是否允许药企分多天进行配送，若不允许则该订单只允许在一天内完成所有的配送，否则可以分多天配送。
    private String DCPSBS;
    // 备注说明 选填
    private String BZSM;

    public String getSXH() {
        return SXH;
    }

    public void setSXH(String SXH) {
        this.SXH = SXH;
    }

    public String getCGLX() {
        return CGLX;
    }

    public void setCGLX(String CGLX) {
        this.CGLX = CGLX;
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

    public String getGGBZ() {
        return GGBZ;
    }

    public void setGGBZ(String GGBZ) {
        this.GGBZ = GGBZ;
    }

    public String getCGJLDW() {
        return CGJLDW;
    }

    public void setCGJLDW(String CGJLDW) {
        this.CGJLDW = CGJLDW;
    }

    public String getCGSL() {
        return CGSL;
    }

    public void setCGSL(String CGSL) {
        this.CGSL = CGSL;
    }

    public String getCGDJ() {
        return CGDJ;
    }

    public void setCGDJ(String CGDJ) {
        this.CGDJ = CGDJ;
    }

    public String getYQBM() {
        return YQBM;
    }

    public void setYQBM(String YQBM) {
        this.YQBM = YQBM;
    }

    public String getDCPSBS() {
        return DCPSBS;
    }

    public void setDCPSBS(String DCPSBS) {
        this.DCPSBS = DCPSBS;
    }

    public String getBZSM() {
        return BZSM;
    }

    public void setBZSM(String BZSM) {
        this.BZSM = BZSM;
    }
}
