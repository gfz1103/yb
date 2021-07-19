package com.buit.his.gpo.service.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author sunjx
 * @Date 2020-11-16 11:09
 * @Description
 **/
@ApiModel
public class FpdzxqDataDetail {

    @ApiModelProperty("药企编码 市药事系统对药企的统编代码")
    private String YQBM;

    @ApiModelProperty("药企名称")
    private String YQMC;

    @ApiModelProperty("发票数量 对账期期间市药事系统按药企汇总的医院接收的发票总数量")
    private String FPSL;

    @ApiModelProperty("发票金额 对账期期间市药事系统按药企汇总的医院接收的发票总数量")
    private String FPJE;

    public String getYQMC() {
        return YQMC;
    }

    public void setYQMC(String YQMC) {
        this.YQMC = YQMC;
    }

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
