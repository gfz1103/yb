package com.buit.his.gpo.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Description
 * @Author yueyu
 * @Date 2021/5/4 10:24
 */
public class GpoBaseDto {

    private Integer yksb;

    private Integer yklb;

    @ApiModelProperty(value = "ip", required = true)
    private String ip;

    @ApiModelProperty(value = "mac", required = true)
    private String mac;

    public GpoBaseDto(){}

    public GpoBaseDto(String ip,String mac){
        this.ip=ip;
        this.mac=mac;
    }

    public Integer getYklb() {
        return yklb;
    }

    public void setYklb(Integer yklb) {
        this.yklb = yklb;
    }

    public Integer getYksb() {
        return yksb;
    }

    public void setYksb(Integer yksb) {
        this.yksb = yksb;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
