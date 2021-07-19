package com.buit.his.gpo.ws.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author sunjx
 * @Date 2020-11-09 16:22
 * @Description
 * 参考文档:
 *  上海市医药采购服务与监管信息系统医院接口规范.pdf
 **/
@ApiModel
public class BaseRespMain {

    @ApiModelProperty("是否完结 是否还存在未返回的查询结果；‘0’：否，‘1’：是")
    private String SFWJ;

    @ApiModelProperty("记录数 本消息内的<DETAIL>记录条数")
    private Integer JLS;

    public String getSFWJ() {
        return SFWJ;
    }

    public void setSFWJ(String SFWJ) {
        this.SFWJ = SFWJ;
    }

    public Integer getJLS() {
        return JLS;
    }

    public void setJLS(Integer JLS) {
        this.JLS = JLS;
    }
}
