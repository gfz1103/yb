package com.buit.his.gpo.ws.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author sunjx
 * @Date 2020-11-12 10:37
 * @Description
 **/
@ApiModel("配送单明细请求 YY005")
public class PsdYY005ReqMain {

    @ApiModelProperty("起始日期 字符串 8 必填 作为选择的查询条件的配送单提交的起")
    private String QSRQ;

    @ApiModelProperty("截止日期 字符串 8 必填 始日期以及截至日期。日期格式B")
    private String JZRQ;

    @ApiModelProperty("药企编码 字符串 20 选填 市药事系统对药企的统编代码，若不输入，则查询该医院所有的尚未验收的配送单信息")
    private String YQBM;

    @ApiModelProperty("配送单号 字符串 20 选填 药企内部的配送单唯一标识编号，若不填写则查询所有符合条件的配送单")
    private String PSDH;

    @ApiModelProperty("配送点编码 字符串 20 选填 医院预先提交的医院内配送点编码")
    private String PSDBM;

    @ApiModelProperty("配送单ID 字符串 20 选填 市药事系统生成的配送单唯一标识编号，初次调用不填写，接续调用时须填写上次返回的最后一个配送单ID")
    private String PSDID;


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

    public String getPSDH() {
        return PSDH;
    }

    public void setPSDH(String PSDH) {
        this.PSDH = PSDH;
    }

    public String getPSDBM() {
        return PSDBM;
    }

    public void setPSDBM(String PSDBM) {
        this.PSDBM = PSDBM;
    }

    public String getPSDID() {
        return PSDID;
    }

    public void setPSDID(String PSDID) {
        this.PSDID = PSDID;
    }
}
