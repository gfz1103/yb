package com.buit.his.gpo.ws.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author sunjx
 * @Date 2020-11-12 10:28
 * @Description
 **/
@ApiModel("配送单验收请求 YY018")
public class PsdYY018ReqMain {

    @ApiModelProperty("药企编码 必填 市药事系统对药企的统编代码")
    private String YQBM;

    @ApiModelProperty("配送明细编号 必填 市药事系统可唯一标识的配送明细编号")
    private String PSMXBH;

    @ApiModelProperty("配送单条码 必填 配送单条码")
    private String PSDTM;

    @ApiModelProperty("商品统编代码 必填 统一发布的药品商品唯一标识编码")
    private String ZXSPBM;

    @ApiModelProperty("配送数量 必填 该次配送的同一装箱且同一货品且同一生产批号按药品基础信息中包装单位名称所界定的货品配送数量")
    private String PSL;

    @ApiModelProperty("生产批号 选填 若不填写，则说明按原配送明细的生产批号进行验收，若填写则将按填写的生产批号更新配送明细中的原生产批号，验收不同过时无需填写")
    private String SCPH;

    @ApiModelProperty("验收结果 必填 详见4.1.20验收结果")
    private String YSJG;

    @ApiModelProperty("验收结果备注 选填")
    private String YSJGBZ;

    public String getYQBM() {
        return YQBM;
    }

    public void setYQBM(String YQBM) {
        this.YQBM = YQBM;
    }

    public String getPSMXBH() {
        return PSMXBH;
    }

    public void setPSMXBH(String PSMXBH) {
        this.PSMXBH = PSMXBH;
    }

    public String getPSDTM() {
        return PSDTM;
    }

    public void setPSDTM(String PSDTM) {
        this.PSDTM = PSDTM;
    }

    public String getZXSPBM() {
        return ZXSPBM;
    }

    public void setZXSPBM(String ZXSPBM) {
        this.ZXSPBM = ZXSPBM;
    }

    public String getPSL() {
        return PSL;
    }

    public void setPSL(String PSL) {
        this.PSL = PSL;
    }

    public String getSCPH() {
        return SCPH;
    }

    public void setSCPH(String SCPH) {
        this.SCPH = SCPH;
    }

    public String getYSJG() {
        return YSJG;
    }

    public void setYSJG(String YSJG) {
        this.YSJG = YSJG;
    }

    public String getYSJGBZ() {
        return YSJGBZ;
    }

    public void setYSJGBZ(String YSJGBZ) {
        this.YSJGBZ = YSJGBZ;
    }
}
