package com.buit.his.shyb.source.entity.business;
/**
 * 明细账单
 * @author beijunhua
 */
public class DetailsBillcy {
    /**
     * 明细账单号
     */
    private String mxzdh;
    /**
     * 交易费用总额
     */
    private String totalexpense;
    /**
     * 医保结算范围费
     * 用总额
     */
    private String ybjsfwfyze;
    /**
     * 非医保结算范围
     * 费用总额
     */
    private String fybjsfwfyze;

    public String getMxzdh() {
        return mxzdh;
    }
    public void setMxzdh(String mxzdh) {
        this.mxzdh = mxzdh;
    }
    public String getTotalexpense() {
        return totalexpense;
    }
    public void setTotalexpense(String totalexpense) {
        this.totalexpense = totalexpense;
    }
    public String getYbjsfwfyze() {
        return ybjsfwfyze;
    }
    public void setYbjsfwfyze(String ybjsfwfyze) {
        this.ybjsfwfyze = ybjsfwfyze;
    }
    public String getFybjsfwfyze() {
        return fybjsfwfyze;
    }
    public void setFybjsfwfyze(String fybjsfwfyze) {
        this.fybjsfwfyze = fybjsfwfyze;
    }
}
