package com.buit.his.shyb.source.entity.business;
/**
 * 账户查询实体类
 * @author beijunhua
 * @date 2020/9/15 13:39
 */
public class SL01 {
    /**
     * 对账日
     */
    private String daycollate;
    /**
     * 对帐日中心流水号数量
     */
    private String daycount;
    /**
     * 当年帐户支付总额
     */
    private String totalcuraccpay;
    /**
     * 历年帐户支付总额
     */
    private String totalhisaccpay;
    /**
     * 现金自负总额
     */
    private String totalcashpay;
    /**
     * 统筹支付总额
     */
    private String totaltcpay;
    /**
     * 附加支付总额
     */
    private String totaldffjpay;
    /**
     * 分类自负总额
     */
    private String totalflzf;
    /**
     * 非医保结算范围费用总额总额
     */
    private String totalfybjsfw;



    @Override
    public String toString() {
        return "SL01{" +
                "daycollate='" + daycollate + '\'' +
                ", daycount='" + daycount + '\'' +
                ", totalcuraccpay='" + totalcuraccpay + '\'' +
                ", totalhisaccpay='" + totalhisaccpay + '\'' +
                ", totalcashpay='" + totalcashpay + '\'' +
                ", totaltcpay='" + totaltcpay + '\'' +
                ", totaldffjpay='" + totaldffjpay + '\'' +
                ", totalflzf='" + totalflzf + '\'' +
                ", totalfybjsfw='" + totalfybjsfw + '\'' +
                '}';
    }



    public String getDaycollate() {
        return daycollate;
    }



    public void setDaycollate(String daycollate) {
        this.daycollate = daycollate;
    }



    public String getDaycount() {
        return daycount;
    }



    public void setDaycount(String daycount) {
        this.daycount = daycount;
    }



    public String getTotalcuraccpay() {
        return totalcuraccpay;
    }



    public void setTotalcuraccpay(String totalcuraccpay) {
        this.totalcuraccpay = totalcuraccpay;
    }



    public String getTotalhisaccpay() {
        return totalhisaccpay;
    }



    public void setTotalhisaccpay(String totalhisaccpay) {
        this.totalhisaccpay = totalhisaccpay;
    }



    public String getTotalcashpay() {
        return totalcashpay;
    }



    public void setTotalcashpay(String totalcashpay) {
        this.totalcashpay = totalcashpay;
    }



    public String getTotaltcpay() {
        return totaltcpay;
    }



    public void setTotaltcpay(String totaltcpay) {
        this.totaltcpay = totaltcpay;
    }



    public String getTotaldffjpay() {
        return totaldffjpay;
    }



    public void setTotaldffjpay(String totaldffjpay) {
        this.totaldffjpay = totaldffjpay;
    }



    public String getTotalflzf() {
        return totalflzf;
    }



    public void setTotalflzf(String totalflzf) {
        this.totalflzf = totalflzf;
    }



    public String getTotalfybjsfw() {
        return totalfybjsfw;
    }



    public void setTotalfybjsfw(String totalfybjsfw) {
        this.totalfybjsfw = totalfybjsfw;
    }
}
