package com.buit.his.gpo.ws.request;

/**
 * @Author sunjx
 * @Date 2020-11-17 14:22
 * @Description
 **/
public class FpYY017ReqMain {

    //"药企编码 必填 市药事系统对药企的统编代码
    private String YQBM;

    //"发票号 必填 药企内可唯一标识该发票的编号
    private String FPH;

    //"发票含税总金额 必填
    private String FPHSZJE;

    public String getYQBM() {
        return YQBM;
    }

    public void setYQBM(String YQBM) {
        this.YQBM = YQBM;
    }

    public String getFPH() {
        return FPH;
    }

    public void setFPH(String FPH) {
        this.FPH = FPH;
    }

    public String getFPHSZJE() {
        return FPHSZJE;
    }

    public void setFPHSZJE(String FPHSZJE) {
        this.FPHSZJE = FPHSZJE;
    }
}
