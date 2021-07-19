package com.buit.his.shyb.upload.response.sysjybgResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author weijing
 * @Date 2020-11-02 19:04
 * @Description
 **/
@ApiModel("检验结果信息")
public class JyjgXx {
    @ApiModelProperty("检验指标流水号")
    /**检验指标流水号**/
    private String JYZBLSH;

    @ApiModelProperty("医保收费代码")
    /**医保收费代码**/
    private String YBSFDM;

    @ApiModelProperty("检测指标名称")
    /**检测指标名称**/
    private String JCZBMC;

    @ApiModelProperty("检测指标结果")
    /**检测指标结果**/
    private String JCZBJG;

    @ApiModelProperty("参考值范围")
    /**参考值范围**/
    private String CKZ;

    @ApiModelProperty("计量单位")
    /**计量单位**/
    private String JLDW;

    @ApiModelProperty("异常提示")
    /**异常提示 1：正常；2：无法识别的异常；3：异常偏高；4：异常偏低**/
    private String YCTS;

    public String getJYZBLSH() {
        return JYZBLSH;
    }

    public void setJYZBLSH(String JYZBLSH) {
        this.JYZBLSH = JYZBLSH;
    }

    public String getYBSFDM() {
        return YBSFDM;
    }

    public void setYBSFDM(String YBSFDM) {
        this.YBSFDM = YBSFDM;
    }

    public String getJCZBMC() {
        return JCZBMC;
    }

    public void setJCZBMC(String JCZBMC) {
        this.JCZBMC = JCZBMC;
    }

    public String getJCZBJG() {
        return JCZBJG;
    }

    public void setJCZBJG(String JCZBJG) {
        this.JCZBJG = JCZBJG;
    }

    public String getCKZ() {
        return CKZ;
    }

    public void setCKZ(String CKZ) {
        this.CKZ = CKZ;
    }

    public String getJLDW() {
        return JLDW;
    }

    public void setJLDW(String JLDW) {
        this.JLDW = JLDW;
    }

    public String getYCTS() {
        return YCTS;
    }

    public void setYCTS(String YCTS) {
        this.YCTS = YCTS;
    }
}
