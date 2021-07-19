package com.buit.his.gpo.ws.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author sunjx
 * @Date 2020-11-12 15:02
 * @Description
 **/
@ApiModel("查询发票信息main")
public class FpYY007ReqMain {
    
    @ApiModelProperty(value = "起始日期,必填,如 20201112", required = true)
    private String QSRQ;
    
    @ApiModelProperty(value = "截止日期,必填,如 20201112", required = true)
    private String JZRQ;
    
    @ApiModelProperty("药企编码,选填")
    private String YQBM;
    
    @ApiModelProperty("发票ID,选填,市药事系统生成的发票唯一标识编号，初次调用不填写，接续调用时须填写上次返回的最后一个发票明ID")
    private String FPID;

    public String getQSRQ() {
        return QSRQ;
    }

    public void setQSRQ(String QSRQ) {
        this.QSRQ = QSRQ;
    }

    public String getJZRQ() {
        return JZRQ;
    }

    public void setJZRQ(String JZRQ) {
        this.JZRQ = JZRQ;
    }

    public String getYQBM() {
        return YQBM;
    }

    public void setYQBM(String YQBM) {
        this.YQBM = YQBM;
    }

    public String getFPID() {
        return FPID;
    }

    public void setFPID(String FPID) {
        this.FPID = FPID;
    }
}
