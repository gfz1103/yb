package com.buit.his.gpo.ws.request;

/**
 * @Author sunjx
 * @Date 2020-11-12 13:25
 * @Description
 **/
public class PsdYY003ReqMain {

    //药企编码  选填 供货药企在市药事系统的统编代码，不填写时则查询当前处于配送中的该医院符合条码的数据
    private String YQBM;

    //配送明细编号  选填 初次调用不填写，接续调用时须填写上次返回的最后一个配送明细编号
    private String PSMXBH;

    public String getYQBM() {
        return YQBM;
    }

    public void setYQBM(String YQBM) {
        this.YQBM = YQBM;
    }

    public String getPSMXBH() {
        return PSMXBH;
    }

    public void setPSMXBH(String PSMXBH) {
        this.PSMXBH = PSMXBH;
    }
}
