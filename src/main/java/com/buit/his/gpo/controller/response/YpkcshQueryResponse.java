package com.buit.his.gpo.controller.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author sunjx
 * @Date 2020-11-03 19:20
 * @Description
 **/
@ApiModel("药品库存数据查询响应")
public class YpkcshQueryResponse {

    @ApiModelProperty("配送点编码")
    private String psdbm;

    @ApiModelProperty("商品统编代码")
    private String zxspbm;

    @ApiModelProperty("商品名称")
    private String spmc;

    @ApiModelProperty("规格包装")
    private String gg;

    @ApiModelProperty("商品类型代码")
    private String splx;

    @ApiModelProperty("药品类型代码")
    private String yplx;

    @ApiModelProperty("医院商品编码")
    private String yyspbm;

    @ApiModelProperty("当前库存数量")
    private String ypkcl;

    @ApiModelProperty("库存单位")
    private String kcdw;

    public String getPsdmc() {
        return psdmc;
    }

    public void setPsdmc(String psdmc) {
        this.psdmc = psdmc;
    }

    @ApiModelProperty("配送點名稱")
    private String psdmc;

    public String getPsdbm() {
        return psdbm;
    }

    public void setPsdbm(String psdbm) {
        this.psdbm = psdbm;
    }

    public String getZxspbm() {
        return zxspbm;
    }

    public void setZxspbm(String zxspbm) {
        this.zxspbm = zxspbm;
    }

    public String getSpmc() {
        return spmc;
    }

    public void setSpmc(String spmc) {
        this.spmc = spmc;
    }

    public String getGg() {
        return gg;
    }

    public void setGg(String gg) {
        this.gg = gg;
    }

    public String getSplx() {
        return splx;
    }

    public void setSplx(String splx) {
        this.splx = splx;
    }

    public String getYplx() {
        return yplx;
    }

    public void setYplx(String yplx) {
        this.yplx = yplx;
    }

    public String getYyspbm() {
        return yyspbm;
    }

    public void setYyspbm(String yyspbm) {
        this.yyspbm = yyspbm;
    }

    public String getYpkcl() {
        return ypkcl;
    }

    public void setYpkcl(String ypkcl) {
        this.ypkcl = ypkcl;
    }

    public String getKcdw() {
        return kcdw;
    }

    public void setKcdw(String kcdw) {
        this.kcdw = kcdw;
    }
}
