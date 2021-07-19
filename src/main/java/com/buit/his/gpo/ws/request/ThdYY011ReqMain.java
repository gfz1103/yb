package com.buit.his.gpo.ws.request;

/**
 * @Author sunjx
 * @Date 2020-11-17 16:00
 * @Description
 **/
public class ThdYY011ReqMain {
    
    //操作类型 必填 详见4.1.6操作类型
    private String CZLX;
    
    //医院编码 必填 市药事系统发布的对医院的统编代码
    private String YYBM;
    
    //配送点编码 必填 医院预先提交的医院内配送点编码
    private String PSDBM;
    
    //药企编码 必填 市药事系统对药企的统编代码
    private String YQBM;
    
    //退货单编号 选填 对于新填报的退货单无须填写，对于修改或撤销的退货单则必须填写市药事系统返回的退货单编号。
    private String THDBH;
    
    //带量采购标志 必填 标识是否带量采购的退货单。0：否，1：是
    private String DLCGBZ;
    
    //商品类型 必填 详见4.1.5商品类型
    private String SPLX;
    
    //商品统编代码 必填 统一发布的药品商品唯一标识编码
    private String ZXSPBM;

    //医院采购药品的规格包装要求，具体用于区分中药饮片的中、大包装说明
    private String GGBZ;
    
    //采购计量单位 必填 详见4.1.21采购计量单位
    private String CGJLDW;
    
    //生产批号 必填 药品生产的生产批号
    private String SCPH;
    
    //退货数量 必填 该品规药品按原发货包装单位的退货数量
    private String THSL;
    
    //退货单价 必填 该品规药品按原包装的进货单价
    private String THDJ;
    
    //退货总价 必填 该品规药品本次退货的总金额
    private String THZJ;
    
    //退货原因 必填 本次退货的原因
    private String THYY;

    public String getGGBZ() {
        return GGBZ;
    }

    public void setGGBZ(String GGBZ) {
        this.GGBZ = GGBZ;
    }

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

    public String getYQBM() {
        return YQBM;
    }

    public void setYQBM(String YQBM) {
        this.YQBM = YQBM;
    }

    public String getTHDBH() {
        return THDBH;
    }

    public void setTHDBH(String THDBH) {
        this.THDBH = THDBH;
    }

    public String getDLCGBZ() {
        return DLCGBZ;
    }

    public void setDLCGBZ(String DLCGBZ) {
        this.DLCGBZ = DLCGBZ;
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

    public String getCGJLDW() {
        return CGJLDW;
    }

    public void setCGJLDW(String CGJLDW) {
        this.CGJLDW = CGJLDW;
    }

    public String getSCPH() {
        return SCPH;
    }

    public void setSCPH(String SCPH) {
        this.SCPH = SCPH;
    }

    public String getTHSL() {
        return THSL;
    }

    public void setTHSL(String THSL) {
        this.THSL = THSL;
    }

    public String getTHDJ() {
        return THDJ;
    }

    public void setTHDJ(String THDJ) {
        this.THDJ = THDJ;
    }

    public String getTHZJ() {
        return THZJ;
    }

    public void setTHZJ(String THZJ) {
        this.THZJ = THZJ;
    }

    public String getTHYY() {
        return THYY;
    }

    public void setTHYY(String THYY) {
        this.THYY = THYY;
    }
}
