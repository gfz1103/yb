package com.buit.his.shyb.source.entity.business;
/**
 * 账户查询实体类
 * @author beijunhua
 * @date  2020/9/15 13:39
 */
public class SL01Res {
    /**
     * 对账日
     */
    private String daycollate;

    /**
     * 对账结果
     */
    private String resultcollate;

    public String getDaycollate() {
        return daycollate;
    }

    public void setDaycollate(String daycollate) {
        this.daycollate = daycollate;
    }

    public String getResultcollate() {
        return resultcollate;
    }

    public void setResultcollate(String resultcollate) {
        this.resultcollate = resultcollate;
    }
}
