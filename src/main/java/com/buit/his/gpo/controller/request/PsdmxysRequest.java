package com.buit.his.gpo.controller.request;

import com.buit.his.gpo.ws.response.PsdYY003RespStruct;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author sunjx
 * @Date 2020-11-12 11:06
 * @Description
 **/
@ApiModel("配送点明细验收请求")
public class PsdmxysRequest extends GpoBaseRequest{

    @ApiModelProperty(value = "药企编码",required = true)
    @NotBlank
    private String yqbm;

    @NotNull
    @ApiModelProperty(value = "验收结果,0:不通过，1：验收通过",required = true)
    private Integer ysjg;

    @ApiModelProperty(value = "验收结果验收结果备注")
    private String ysjgbz;

    @ApiModelProperty(value = "0：货到票已到，1：货到票未到，2：票到货未到",required = true)
    private Integer pwd;
    @ApiModelProperty(value="明细验收参数列表",required = true)
    List<PsdYY003RespStruct> ysmxs;

    public String getYsjgbz() {
        return ysjgbz;
    }

    public void setYsjgbz(String ysjgbz) {
        this.ysjgbz = ysjgbz;
    }

    public Integer getYsjg() {
        return ysjg;
    }

    public void setYsjg(Integer ysjg) {
        this.ysjg = ysjg;
    }

    public String getYqbm() {
        return yqbm;
    }

    public void setYqbm(String yqbm) {
        this.yqbm = yqbm;
    }

    public Integer getPwd() {
        return pwd;
    }

    public void setPwd(Integer pwd) {
        this.pwd = pwd;
    }

    public List<PsdYY003RespStruct> getYsmxs() {
        return ysmxs;
    }

    public void setYsmxs(List<PsdYY003RespStruct> ysmxs) {
        this.ysmxs = ysmxs;
    }
}
