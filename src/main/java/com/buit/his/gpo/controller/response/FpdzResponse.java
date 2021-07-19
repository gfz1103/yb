package com.buit.his.gpo.controller.response;

import com.buit.his.gpo.service.result.FpdzxqDataDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author sunjx
 * @Date 2020-11-16 16:40
 * @Description
 **/
@ApiModel("发票对账响应")
public class FpdzResponse {

    @ApiModelProperty("发票数量")
    private Integer  FPSL;

    @ApiModelProperty("发票金额")
    private String FPJE;

    @ApiModelProperty("药企发票详情")
    private List<FpdzxqDataDetail> details;

    public List<FpdzxqDataDetail> getDetails() {
        return details;
    }

    public void setDetails(List<FpdzxqDataDetail> details) {
        this.details = details;
    }

    public Integer getFPSL() {
        return FPSL;
    }

    public void setFPSL(Integer FPSL) {
        this.FPSL = FPSL;
    }

    public String getFPJE() {
        return FPJE;
    }

    public void setFPJE(String FPJE) {
        this.FPJE = FPJE;
    }
}
