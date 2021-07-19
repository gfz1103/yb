package com.buit.his.shyb.upload.response.sysjybgResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author weijing
 * @Date 2020-11-02 19:08
 * @Description
 **/
@ApiModel("药敏结果")
public class YmjgXx {
    @ApiModelProperty("药敏结果流水号")
    /**药敏结果流水号**/
    private String YMJGLSH;

    @ApiModelProperty("医保收费代码")
    /**医保收费代码**/
    private String YBSFDM;

    @ApiModelProperty("药敏代码")
    /**药敏代码**/
    private String YMDM;

    @ApiModelProperty("药敏名称")
    /**药敏名称**/
    private String YMMC;

    @ApiModelProperty("检测结果描述")
    /**检测结果描述**/
    private String JCJG;

    @ApiModelProperty("细菌结果流水号")
    /**细菌结果流水号**/
    private String XJJGLSH;

    @ApiModelProperty("纸片含药量")
    /**纸片含药量**/
    private String ZPHYL;

    @ApiModelProperty("抑菌浓度")
    /**抑菌浓度**/
    private String YJND;

    @ApiModelProperty("抑菌环直径")
    /**抑菌环直径**/
    private String YJHZJ;

    public String getYMJGLSH() {
        return YMJGLSH;
    }

    public void setYMJGLSH(String YMJGLSH) {
        this.YMJGLSH = YMJGLSH;
    }

    public String getYBSFDM() {
        return YBSFDM;
    }

    public void setYBSFDM(String YBSFDM) {
        this.YBSFDM = YBSFDM;
    }

    public String getYMDM() {
        return YMDM;
    }

    public void setYMDM(String YMDM) {
        this.YMDM = YMDM;
    }

    public String getYMMC() {
        return YMMC;
    }

    public void setYMMC(String YMMC) {
        this.YMMC = YMMC;
    }

    public String getJCJG() {
        return JCJG;
    }

    public void setJCJG(String JCJG) {
        this.JCJG = JCJG;
    }

    public String getXJJGLSH() {
        return XJJGLSH;
    }

    public void setXJJGLSH(String XJJGLSH) {
        this.XJJGLSH = XJJGLSH;
    }

    public String getZPHYL() {
        return ZPHYL;
    }

    public void setZPHYL(String ZPHYL) {
        this.ZPHYL = ZPHYL;
    }

    public String getYJND() {
        return YJND;
    }

    public void setYJND(String YJND) {
        this.YJND = YJND;
    }

    public String getYJHZJ() {
        return YJHZJ;
    }

    public void setYJHZJ(String YJHZJ) {
        this.YJHZJ = YJHZJ;
    }
}
