package com.buit.his.shyb.upload.response.yxyxbgResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author weijing
 * @Date 2020-11-02 19:19
 * @Description
 **/
@ApiModel("检查报告明细")
public class Jcbgmx {
    @ApiModelProperty("检查类型")
    /**检查类型 **/
    private String JCLX;

    @ApiModelProperty("检查部位")
    /**检查部位 **/
    private String JCBW;

    @ApiModelProperty("检查方法")
    /**检查方法 **/
    private String JCFF;

    @ApiModelProperty("检查名称")
    /**检查名称 **/
    private String JCMC;

    @ApiModelProperty("阴阳性")
    /**阴阳性 **/
    private String YYS;

    @ApiModelProperty("报告临床诊断")
    /**报告临床诊断 **/
    private String BGLCZD;

    @ApiModelProperty("影像表现或检查所见")
    /**影像表现或检查所见 **/
    private String YXBX;

    @ApiModelProperty("检查诊断或提示")
    /**检查诊断或提示 **/
    private String YXZD;

    @ApiModelProperty("备注或建议")
    /**备注或建议 **/
    private String BZHJY;

    @ApiModelProperty("其它检查明细")
    /**其它检查明细 **/
    private List<Qtmx> QTMX;

    public String getJCLX() {
        return JCLX;
    }

    public void setJCLX(String JCLX) {
        this.JCLX = JCLX;
    }

    public String getJCBW() {
        return JCBW;
    }

    public void setJCBW(String JCBW) {
        this.JCBW = JCBW;
    }

    public String getJCFF() {
        return JCFF;
    }

    public void setJCFF(String JCFF) {
        this.JCFF = JCFF;
    }

    public String getJCMC() {
        return JCMC;
    }

    public void setJCMC(String JCMC) {
        this.JCMC = JCMC;
    }

    public String getYYS() {
        return YYS;
    }

    public void setYYS(String YYS) {
        this.YYS = YYS;
    }

    public String getBGLCZD() {
        return BGLCZD;
    }

    public void setBGLCZD(String BGLCZD) {
        this.BGLCZD = BGLCZD;
    }

    public String getYXBX() {
        return YXBX;
    }

    public void setYXBX(String YXBX) {
        this.YXBX = YXBX;
    }

    public String getYXZD() {
        return YXZD;
    }

    public void setYXZD(String YXZD) {
        this.YXZD = YXZD;
    }

    public String getBZHJY() {
        return BZHJY;
    }

    public void setBZHJY(String BZHJY) {
        this.BZHJY = BZHJY;
    }

    public List<Qtmx> getQTMX() {
        return QTMX;
    }

    public void setQTMX(List<Qtmx> QTMX) {
        this.QTMX = QTMX;
    }
}
