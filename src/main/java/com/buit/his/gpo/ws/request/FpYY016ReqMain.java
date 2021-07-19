package com.buit.his.gpo.ws.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author sunjx
 * @Date 2020-11-16 16:15
 * @Description
 **/
@ApiModel("发票对账请求 main")
public class FpYY016ReqMain {

    @ApiModelProperty("对账期 必填 对账的业务期")
    private String DZQ;

    @ApiModelProperty("开始日期 必填 数字格式B，对账期的开始日期")
    private String KSRQ;

    @ApiModelProperty("结束日期 必填 数字格式B，对账期的结束日期")
    private String JSRQ;

    @ApiModelProperty("带量采购标志 必填 标识是否带量采购的对账信息。0：否，1：是")
    private String DLCGBZ;

    @ApiModelProperty("发票数量 必填 对账期期间医院接收的发票总数量")
    private String FPSL;

    @ApiModelProperty("发票金额 必填 对账期期间医院接收的发票总金额，若是退货的冲红发票则以负数进行汇总")
    private String FPJE;

    @ApiModelProperty("记录数 必填")
    private String JLS;

    public String getDZQ() {
        return DZQ;
    }

    public void setDZQ(String DZQ) {
        this.DZQ = DZQ;
    }

    public String getKSRQ() {
        return KSRQ;
    }

    public void setKSRQ(String KSRQ) {
        this.KSRQ = KSRQ;
    }

    public String getJSRQ() {
        return JSRQ;
    }

    public void setJSRQ(String JSRQ) {
        this.JSRQ = JSRQ;
    }

    public String getDLCGBZ() {
        return DLCGBZ;
    }

    public void setDLCGBZ(String DLCGBZ) {
        this.DLCGBZ = DLCGBZ;
    }

    public String getFPSL() {
        return FPSL;
    }

    public void setFPSL(String FPSL) {
        this.FPSL = FPSL;
    }

    public String getFPJE() {
        return FPJE;
    }

    public void setFPJE(String FPJE) {
        this.FPJE = FPJE;
    }

    public String getJLS() {
        return JLS;
    }

    public void setJLS(String JLS) {
        this.JLS = JLS;
    }
}
