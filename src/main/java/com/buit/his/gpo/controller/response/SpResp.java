package com.buit.his.gpo.controller.response;

import com.buit.his.gpo.dto.YpbzzhDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author sunjx
 * @Date 2020-11-10 14:25
 * @Description 医保药品基础信息dto
 **/
@ApiModel("医保药品信息")
public class SpResp {

    @ApiModelProperty("药企编码")
    private String yqbm;
    @ApiModelProperty("药企名称")
    private String yqmc;

    @ApiModelProperty("商品ID, ")
    private String SPID;
    @ApiModelProperty("统编代码")
    private String TBDM;
    @ApiModelProperty("药品通用名")
    private String YPTYM;
    @ApiModelProperty("规格")
    private String GG;
    @ApiModelProperty("生产企业")
    private String SCQY;
    @ApiModelProperty("采购计量单位")
    private String CGJLDW;
    @ApiModelProperty("采购单价")
    private String CGDJ;
    private List<YpbzzhDto> zhbzList;

    public List<YpbzzhDto> getZhbzList() {
        return zhbzList;
    }

    public void setZhbzList(List<YpbzzhDto> zhbzList) {
        this.zhbzList = zhbzList;
    }

    public String getSPID() {
        return SPID;
    }

    public void setSPID(String SPID) {
        this.SPID = SPID;
    }

    public String getTBDM() {
        return TBDM;
    }

    public void setTBDM(String TBDM) {
        this.TBDM = TBDM;
    }

    public String getYPTYM() {
        return YPTYM;
    }

    public void setYPTYM(String YPTYM) {
        this.YPTYM = YPTYM;
    }

    public String getGG() {
        return GG;
    }

    public void setGG(String GG) {
        this.GG = GG;
    }

    public String getSCQY() {
        return SCQY;
    }

    public void setSCQY(String SCQY) {
        this.SCQY = SCQY;
    }

    public String getCGJLDW() {
        return CGJLDW;
    }

    public void setCGJLDW(String CGJLDW) {
        this.CGJLDW = CGJLDW;
    }

    public String getCGDJ() {
        return CGDJ;
    }

    public void setCGDJ(String CGDJ) {
        this.CGDJ = CGDJ;
    }

    public String getYqbm() {
        return yqbm;
    }

    public void setYqbm(String yqbm) {
        this.yqbm = yqbm;
    }

    public String getYqmc() {
        return yqmc;
    }

    public void setYqmc(String yqmc) {
        this.yqmc = yqmc;
    }
}
