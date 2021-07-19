package com.buit.his.gpo.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author sunjx
 * @Date 2020-11-17 14:33
 * @Description
 **/
@ApiModel("生成入库单参数")
public class ScrkdRequest {

    @ApiModelProperty(hidden = true)
    private Integer jgid;

    @ApiModelProperty(hidden = true)
    private Integer userid;

    @ApiModelProperty(hidden = true)
    private String uname;

    @ApiModelProperty("入库发票xh")
    private List<Integer> fplist;

    public Integer getJgid() {
        return jgid;
    }

    public void setJgid(Integer jgid) {
        this.jgid = jgid;
    }

    public List<Integer> getFplist() {
        return fplist;
    }

    public void setFplist(List<Integer> fplist) {
        this.fplist = fplist;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
