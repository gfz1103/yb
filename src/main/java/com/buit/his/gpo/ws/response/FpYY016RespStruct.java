package com.buit.his.gpo.ws.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author sunjx
 * @Date 2020-11-16 16:21
 * @Description
 **/
@ApiModel("发票验收响应 struct")
public class FpYY016RespStruct {

    @ApiModelProperty("药企编码 市药事系统对药企的统编代码")
    private String YQBM;

    @ApiModelProperty("发票数量 对账期期间市药事系统按药企汇总的医院接收的发票总数量")
    private String FPSL;

    @ApiModelProperty("发票金额 对账期期间市药事系统按药企汇总的医院接收的发票总数量")
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
