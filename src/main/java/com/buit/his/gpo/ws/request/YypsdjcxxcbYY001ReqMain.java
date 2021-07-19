package com.buit.his.gpo.ws.request;

/**
 * @Author sunjx
 * @Date 2020-11-03 10:39
 * @Description YY001 医院配送点基础信息传报 请求参数
 * 参考文档:
 *  上海市医药采购服务与监管信息系统医院接口规范.pdf
 *  中药饮片-上海市医药采购服务与监管信息系统中药饮片医疗机构接口规范.pdf
 **/
public class YypsdjcxxcbYY001ReqMain {

    //操作类型 字符串 1 必填 详见4.1.6操作类型
    private String CZLX;
    //配送点编码 字符串 20 必填 医院内部的配送点唯一编码，如中药药库编码
    private String PSDBM;
    //配送点名称 字符串 80 必填 配送点中文名称，如中药药库等
    private String PSDMC;
    //配送地址 字符串 200 必填 配送地点的详细地址和地点
    private String PSDZ;
    //联系人姓名 字符串 40 必填
    private String LXRXM;
    //联系电话 字符串 40 必填
    private String LXDH;
    //邮政编码 字符串 10 必填 配送地址对应的邮政编码
    private String YZBM;
    //备注信息 字符串 200 选填
    private String BZXX;

    public String getCZLX() {
        return CZLX;
    }

    public void setCZLX(String CZLX) {
        this.CZLX = CZLX;
    }

    public String getPSDBM() {
        return PSDBM;
    }

    public void setPSDBM(String PSDBM) {
        this.PSDBM = PSDBM;
    }

    public String getPSDMC() {
        return PSDMC;
    }

    public void setPSDMC(String PSDMC) {
        this.PSDMC = PSDMC;
    }

    public String getPSDZ() {
        return PSDZ;
    }

    public void setPSDZ(String PSDZ) {
        this.PSDZ = PSDZ;
    }

    public String getLXRXM() {
        return LXRXM;
    }

    public void setLXRXM(String LXRXM) {
        this.LXRXM = LXRXM;
    }

    public String getLXDH() {
        return LXDH;
    }

    public void setLXDH(String LXDH) {
        this.LXDH = LXDH;
    }

    public String getYZBM() {
        return YZBM;
    }

    public void setYZBM(String YZBM) {
        this.YZBM = YZBM;
    }

    public String getBZXX() {
        return BZXX;
    }

    public void setBZXX(String BZXX) {
        this.BZXX = BZXX;
    }
}
