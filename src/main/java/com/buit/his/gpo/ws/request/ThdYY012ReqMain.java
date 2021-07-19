package com.buit.his.gpo.ws.request;

/**
 * @Author sunjx
 * @Date 2020-11-17 16:12
 * @Description
 **/
public class ThdYY012ReqMain {

    //医院编码 必填 市药事系统发布的对医院的统编代码
    private String YYBM;
    //药企编码 必填 市药事系统发布的对药企的统编代码
    private String YQBM;
    //配送点编码 必填 医院预先提交的医院内配送点编码
    private String PSDBM;
    //带量采购标志 必填 标识是否带量采购的退货单。0：否，1：是
    private String DLCGBZ;
    //退货数量 必填 该品规药品按原发货包装单位的退货数量
    private String THSL;
    //退货单编号 必填 YY011接口返回的退货单编号
    private String THDBH;

    public String getYYBM() {
        return YYBM;
    }

    public void setYYBM(String YYBM) {
        this.YYBM = YYBM;
    }

    public String getYQBM() {
        return YQBM;
    }

    public void setYQBM(String YQBM) {
        this.YQBM = YQBM;
    }

    public String getPSDBM() {
        return PSDBM;
    }

    public void setPSDBM(String PSDBM) {
        this.PSDBM = PSDBM;
    }

    public String getDLCGBZ() {
        return DLCGBZ;
    }

    public void setDLCGBZ(String DLCGBZ) {
        this.DLCGBZ = DLCGBZ;
    }

    public String getTHSL() {
        return THSL;
    }

    public void setTHSL(String THSL) {
        this.THSL = THSL;
    }

    public String getTHDBH() {
        return THDBH;
    }

    public void setTHDBH(String THDBH) {
        this.THDBH = THDBH;
    }
}
