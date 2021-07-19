package com.buit.his.shyb.upload.response.sysjybgResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author weijing
 * @Date 2020-11-02 19:06
 * @Description
 **/
@ApiModel("细菌结果信息")
public class XjjgXx {
    @ApiModelProperty("细菌结果流水号")
    /**细菌结果流水号**/
    private String XJJGLSH;

    @ApiModelProperty("医保收费代码")
    /**医保收费代码**/
    private String YBSFDM;

    @ApiModelProperty("细菌名称")
    /**细菌名称**/
    private String XJMC;

    @ApiModelProperty("菌落计数")
    /**菌落计数**/
    private String JLJS;

    @ApiModelProperty("检测结果")
    /**检测结果**/
    private String JCJG;

    @ApiModelProperty("检测结果文字描述")
    /**检测结果文字描述**/
    private String JCJGWZ;

    public String getXJJGLSH() {
        return XJJGLSH;
    }

    public void setXJJGLSH(String XJJGLSH) {
        this.XJJGLSH = XJJGLSH;
    }

    public String getYBSFDM() {
        return YBSFDM;
    }

    public void setYBSFDM(String YBSFDM) {
        this.YBSFDM = YBSFDM;
    }

    public String getXJMC() {
        return XJMC;
    }

    public void setXJMC(String XJMC) {
        this.XJMC = XJMC;
    }

    public String getJLJS() {
        return JLJS;
    }

    public void setJLJS(String JLJS) {
        this.JLJS = JLJS;
    }

    public String getJCJG() {
        return JCJG;
    }

    public void setJCJG(String JCJG) {
        this.JCJG = JCJG;
    }

    public String getJCJGWZ() {
        return JCJGWZ;
    }

    public void setJCJGWZ(String JCJGWZ) {
        this.JCJGWZ = JCJGWZ;
    }
}
