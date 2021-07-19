package com.buit.his.gpo.ws.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author sunjx
 * @Date 2020-11-16 16:18
 * @Description
 **/
@ApiModel("发票对账请求 struct")
public class FpYY016ReqStruct {

    @ApiModelProperty("药企编码 必填 市药事系统对药企的统编代码")
    private String YQBM;

    @ApiModelProperty("发票数量 必填 对账期期间按药企汇总的医院接收的发票总数量")
    private String FPSL;

    @ApiModelProperty("发票金额 必填 对账期期间按药企汇总的医院接收的发票总金额，若是退货的冲红发票则以负数进行汇总")
    private String FPJE;

    public String getYQBM() {
        return YQBM;
    }

    public void setYQBM(String YQBM) {
        this.YQBM = YQBM;
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
}
