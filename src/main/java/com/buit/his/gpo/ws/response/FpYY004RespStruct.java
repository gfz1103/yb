package com.buit.his.gpo.ws.response;

/**
 * @Author sunjx
 * @Date 2020-11-13 17:22
 * @Description
 **/
public class FpYY004RespStruct {

    //发票号 药企内可唯一标识该发票的编号
    private String FPH;

    //发票日期 发票开具日期，日期格式B
    private String FPRQ;

    //发票含税总金额
    private String FPHSZJE;

    //药企编码
    private String YQBM;

    //医院编码 市药事系统对医院的统编代码
    private String YYBM;

    //配送点编码 医院预先提交的医院内配送点编码
    private String PSDBM;

    //带量采购标志 字符串 1 标识是否带量采购的发票。0：否，1：是
    private String DLSGBZ;

    //发票备注 备注说明
    private String FPBZ;

    //是否无配送发票 0：否；1：是 选择“是”的必须与配送名字一一关联，选择“否”的则无需与配送明细关联
    private String SFWPSFP;

    //无配送发票说明 无配送的发票必须填写说明原因
    private String WPSFPSM;

    //发票明细编号 市药事系统内部可唯一标识的发票明细编号
    private String FPMXBH;

    //商品类型 字符串 1 详见4.1.5商品类型
    private String SPLX;

    //是否冲红 字符串 1 标识是否是由于退货而产生的冲红发票明细；0：否，1：是
    private String SFCH;

    //商品统编代码 统一发布的药品商品唯一标识编码
    private String ZXSPBM;

    //生产批号
    private String SCPH;

    //生产日期
    private String SCRQ;

    //商品数量 对应该“商品统编代码+生产批号”的配送数量
    private String SPSL;

    //关联明细编号 该配送所对应的原始订单的订单明细编号或退货单号，无法关联时可以不填写
    private String GLMXBH;

    //销售订单号  销售公司用于和发票匹配关联的销售订单号，按配送明细中的销售订单号进行数量和费用的汇总，无法关联时可以不填写，冲红发票无需填写
    private String XSDDH;

    //顺序号 上报订单的顺序号，同一个订单内的不同明细的顺序号必须唯一
    private String SXH;

    //有效日期 日期格式B
    private String YXRQ;

    //无税单价
    private String WSDJ;

    //含税单价
    private String HSDJ;

    //税率
    private String SL;

    //税额
    private String SE;

    //含税金额
    private String HSJE;

    //批发价
    private String PFJ;

    //零售价
    private String LSJ;

    //药品批准文号
    private String PZWH;

    public String getFPH() {
        return FPH;
    }

    public void setFPH(String FPH) {
        this.FPH = FPH;
    }

    public String getFPRQ() {
        return FPRQ;
    }

    public void setFPRQ(String FPRQ) {
        this.FPRQ = FPRQ;
    }

    public String getFPHSZJE() {
        return FPHSZJE;
    }

    public void setFPHSZJE(String FPHSZJE) {
        this.FPHSZJE = FPHSZJE;
    }

    public String getYQBM() {
        return YQBM;
    }

    public void setYQBM(String YQBM) {
        this.YQBM = YQBM;
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

    public String getDLSGBZ() {
        return DLSGBZ;
    }

    public void setDLSGBZ(String DLSGBZ) {
        this.DLSGBZ = DLSGBZ;
    }

    public String getFPBZ() {
        return FPBZ;
    }

    public void setFPBZ(String FPBZ) {
        this.FPBZ = FPBZ;
    }

    public String getSFWPSFP() {
        return SFWPSFP;
    }

    public void setSFWPSFP(String SFWPSFP) {
        this.SFWPSFP = SFWPSFP;
    }

    public String getWPSFPSM() {
        return WPSFPSM;
    }

    public void setWPSFPSM(String WPSFPSM) {
        this.WPSFPSM = WPSFPSM;
    }

    public String getFPMXBH() {
        return FPMXBH;
    }

    public void setFPMXBH(String FPMXBH) {
        this.FPMXBH = FPMXBH;
    }

    public String getSPLX() {
        return SPLX;
    }

    public void setSPLX(String SPLX) {
        this.SPLX = SPLX;
    }

    public String getSFCH() {
        return SFCH;
    }

    public void setSFCH(String SFCH) {
        this.SFCH = SFCH;
    }

    public String getZXSPBM() {
        return ZXSPBM;
    }

    public void setZXSPBM(String ZXSPBM) {
        this.ZXSPBM = ZXSPBM;
    }

    public String getSCPH() {
        return SCPH;
    }

    public void setSCPH(String SCPH) {
        this.SCPH = SCPH;
    }

    public String getSCRQ() {
        return SCRQ;
    }

    public void setSCRQ(String SCRQ) {
        this.SCRQ = SCRQ;
    }

    public String getSPSL() {
        return SPSL;
    }

    public void setSPSL(String SPSL) {
        this.SPSL = SPSL;
    }

    public String getGLMXBH() {
        return GLMXBH;
    }

    public void setGLMXBH(String GLMXBH) {
        this.GLMXBH = GLMXBH;
    }

    public String getXSDDH() {
        return XSDDH;
    }

    public void setXSDDH(String XSDDH) {
        this.XSDDH = XSDDH;
    }

    public String getSXH() {
        return SXH;
    }

    public void setSXH(String SXH) {
        this.SXH = SXH;
    }

    public String getYXRQ() {
        return YXRQ;
    }

    public void setYXRQ(String YXRQ) {
        this.YXRQ = YXRQ;
    }

    public String getWSDJ() {
        return WSDJ;
    }

    public void setWSDJ(String WSDJ) {
        this.WSDJ = WSDJ;
    }

    public String getHSDJ() {
        return HSDJ;
    }

    public void setHSDJ(String HSDJ) {
        this.HSDJ = HSDJ;
    }

    public String getSL() {
        return SL;
    }

    public void setSL(String SL) {
        this.SL = SL;
    }

    public String getSE() {
        return SE;
    }

    public void setSE(String SE) {
        this.SE = SE;
    }

    public String getHSJE() {
        return HSJE;
    }

    public void setHSJE(String HSJE) {
        this.HSJE = HSJE;
    }

    public String getPFJ() {
        return PFJ;
    }

    public void setPFJ(String PFJ) {
        this.PFJ = PFJ;
    }

    public String getLSJ() {
        return LSJ;
    }

    public void setLSJ(String LSJ) {
        this.LSJ = LSJ;
    }

    public String getPZWH() {
        return PZWH;
    }

    public void setPZWH(String PZWH) {
        this.PZWH = PZWH;
    }
}
