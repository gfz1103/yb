package com.buit.his.gpo.ws.request;

/**
 * @Author sunjx
 * @Date 2020-11-03 14:07
 * @Description YY002 药品当前库存量数据传报 请求参数
 * 参考文档:
 *  上海市医药采购服务与监管信息系统医院接口规范.pdf
 **/
public class YpdqkclsjcbYY002ReqMain {

    //传报时间 KCCBSJ 字符串 16 必填 日期格式A
    private String KCCBSJ;
    //记录数 JLS 数值 10 必填 本消息内的<DETAIL>记录条数
    private String JLS;

    public String getKCCBSJ() {
        return KCCBSJ;
    }

    public void setKCCBSJ(String KCCBSJ) {
        this.KCCBSJ = KCCBSJ;
    }

    public String getJLS() {
        return JLS;
    }

    public void setJLS(String JLS) {
        this.JLS = JLS;
    }
}
