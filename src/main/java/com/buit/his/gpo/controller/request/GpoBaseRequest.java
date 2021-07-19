package com.buit.his.gpo.controller.request;

import com.buit.commons.SysUser;
import com.buit.his.gpo.dto.GpoBaseDto;
import com.buit.his.gpo.ws.GpoConsts;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author sunjx
 * @Date 2020-11-03 19:02
 * @Description
 **/
public class GpoBaseRequest extends GpoBaseDto {

    @ApiModelProperty("bzxx")
    private String bzxx;

    @ApiModelProperty(value = "药库类型 1西药库 2中草药库", hidden = true)
    private Integer yklx;

    private Integer jgid;

    private String userid;

    private String uname;

    public GpoBaseRequest() {
    }

    public GpoBaseRequest(String ip,String mac,GpoConsts.YKLX yklx, SysUser user) {
        super(ip,mac);
        this.yklx = yklx != null ? yklx.getKey() : null;
        this.jgid = user.getHospitalId();
        this.userid = String.valueOf(user.getUserId());
        this.uname = user.getUserName();
    }

    public GpoBaseRequest(GpoConsts.YKLX yklx, SysUser user) {
        this.yklx = yklx != null ? yklx.getKey() : null;
        this.jgid = user.getHospitalId();
        this.userid = String.valueOf(user.getUserId());
        this.uname = user.getUserName();
    }

    public Integer getYklx() {
        return yklx;
    }

    public void setYklx(Integer yklx) {
        this.yklx = yklx;
    }

    public String getBzxx() {
        return bzxx;
    }

    public void setBzxx(String bzxx) {
        this.bzxx = bzxx;
    }

    public Integer getJgid() {
        return jgid;
    }

    public void setJgid(Integer jgid) {
        this.jgid = jgid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
