package com.buit.his.gpo.ws.response;

/**
 * @Author sunjx
 * @Date 2020-11-09 16:40
 * @Description YY015 报价单
 * 参考文档:
 *  上海市医药采购服务与监管信息系统医院接口规范.pdf
 **/
public class BjdYY015RespStruct {
    // 报价单编号 市药事系统返回的可唯一标识的报价单编号
    private String BJDBH;
    // 询价单编号 市药事系统返回的可唯一标识的询价单编号
    private String XJDBH;
    // 药企编码 市药事系统发布的对药企的统编代码
    private String YQBM;
    // 药企名称 报价药企的企业名称
    private String YQMC;
    // 商品类型 详见4.1.5商品类型
    private String SPLX;
    // 药品类型 详见4.1.1药品类型
    private String YPLX;
    // 商品统编代码 统一发布的药品商品唯一标识编码
    private String ZXSPBM;
    // 产品名 参照国家药监发布的药品/医用材料的“产品名”
    private String CPM;
    // 药品剂型 参照国家药监发布的药品的“剂型”
    private String YPJX;
    // 规格 参照国家药监发布的药品的规格和医用材料的规格型号
    private String GG;
    // 包装单位性质 详见4.1.7包装单位性质
    private String BZDWXZ;
    // 包装单位名称 该货品销售计价的基本包装单位名称
    private String BZDWMC;
    // 用药单位名称 该货品基本使用单位的名称，例如，片、支等
    private String YYDWMC;
    // 包装内含数量, 包装单位内所含基本使用单位的数量
    private String BZNHSL;
    // 生产企业名称 参照国家药监发布的“生产单位”
    private String SCQYMC;
    // 药品报价, 对应该药品基础信息销售单位的报价
    private String YPBJ;

    public String getBJDBH() {
        return BJDBH;
    }

    public void setBJDBH(String BJDBH) {
        this.BJDBH = BJDBH;
    }

    public String getXJDBH() {
        return XJDBH;
    }

    public void setXJDBH(String XJDBH) {
        this.XJDBH = XJDBH;
    }

    public String getYQBM() {
        return YQBM;
    }

    public void setYQBM(String YQBM) {
        this.YQBM = YQBM;
    }

    public String getYQMC() {
        return YQMC;
    }

    public void setYQMC(String YQMC) {
        this.YQMC = YQMC;
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

    public String getCPM() {
        return CPM;
    }

    public void setCPM(String CPM) {
        this.CPM = CPM;
    }

    public String getYPJX() {
        return YPJX;
    }

    public void setYPJX(String YPJX) {
        this.YPJX = YPJX;
    }

    public String getGG() {
        return GG;
    }

    public void setGG(String GG) {
        this.GG = GG;
    }

    public String getBZDWXZ() {
        return BZDWXZ;
    }

    public void setBZDWXZ(String BZDWXZ) {
        this.BZDWXZ = BZDWXZ;
    }

    public String getBZDWMC() {
        return BZDWMC;
    }

    public void setBZDWMC(String BZDWMC) {
        this.BZDWMC = BZDWMC;
    }

    public String getYYDWMC() {
        return YYDWMC;
    }

    public void setYYDWMC(String YYDWMC) {
        this.YYDWMC = YYDWMC;
    }

    public String getBZNHSL() {
        return BZNHSL;
    }

    public void setBZNHSL(String BZNHSL) {
        this.BZNHSL = BZNHSL;
    }

    public String getSCQYMC() {
        return SCQYMC;
    }

    public void setSCQYMC(String SCQYMC) {
        this.SCQYMC = SCQYMC;
    }

    public String getYPBJ() {
        return YPBJ;
    }

    public void setYPBJ(String YPBJ) {
        this.YPBJ = YPBJ;
    }
}
