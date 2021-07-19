package com.buit.his.gpo.service.result;

/**
 * @Author sunjx
 * @Date 2020-11-20 13:38
 * @Description
 **/
public class YpcdjgDto {

    //医保编码
    private String ybbm;

    //药品xh
    private String ypxh;

    //药品产地
    private String ypcd;

    //进货价格
    private String jhjg;

    public String getYbbm() {
        return ybbm;
    }

    public void setYbbm(String ybbm) {
        this.ybbm = ybbm;
    }

    public String getYpxh() {
        return ypxh;
    }

    public void setYpxh(String ypxh) {
        this.ypxh = ypxh;
    }

    public String getYpcd() {
        return ypcd;
    }

    public void setYpcd(String ypcd) {
        this.ypcd = ypcd;
    }

    public String getJhjg() {
        return jhjg;
    }

    public void setJhjg(String jhjg) {
        this.jhjg = jhjg;
    }
}
