package com.buit.his.gpo.controller.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author sunjx
 * @Date 2020-11-06 15:28
 * @Description
 **/
@ApiModel("进货单位响应")
public class JhdwResponse {

    @ApiModelProperty("对照ID")
    private Integer dzid;

    @ApiModelProperty("进货单位ID")
    private Integer jhdwid;

    @ApiModelProperty("进货单位名称")
    private String jhdwmc;

    @ApiModelProperty("药企ID")
    private Integer yqid;

    @ApiModelProperty("药企名称")
    private String yqmc;

    @ApiModelProperty("对照状态 0未对照 1已对照")
    private Integer dzzt;

    public Integer getDzid() {
        return dzid;
    }

    public void setDzid(Integer dzid) {
        this.dzid = dzid;
    }

    public Integer getJhdwid() {
        return jhdwid;
    }

    public void setJhdwid(Integer jhdwid) {
        this.jhdwid = jhdwid;
    }

    public Integer getYqid() {
        return yqid;
    }

    public void setYqid(Integer yqid) {
        this.yqid = yqid;
    }

    public String getJhdwmc() {
        return jhdwmc;
    }

    public void setJhdwmc(String jhdwmc) {
        this.jhdwmc = jhdwmc;
    }

    public String getYqmc() {
        return yqmc;
    }

    public void setYqmc(String yqmc) {
        this.yqmc = yqmc;
    }

    public Integer getDzzt() {
        return dzzt;
    }

    public void setDzzt(Integer dzzt) {
        this.dzzt = dzzt;
    }
}
