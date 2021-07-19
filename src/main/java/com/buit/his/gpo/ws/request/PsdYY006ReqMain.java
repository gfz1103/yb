package com.buit.his.gpo.ws.request;

import com.buit.his.gpo.controller.request.GpoBaseRequest;

/**
 * @Author sunjx
 * @Date 2020-11-12 13:50
 * @Description
 **/
public class PsdYY006ReqMain extends GpoBaseRequest {

    //配送单ID 字符串 20 必填 市药事系统生成的配送单唯一标识编号
    private String PSDID;

    //配送明细编号 字符串 20 选填 初次调用不填写，接续调用时须填写上次返回的最后一个配送明细编号
    private String PSMXBH;

    public String getPSDID() {
        return PSDID;
    }

    public void setPSDID(String PSDID) {
        this.PSDID = PSDID;
    }

    public String getPSMXBH() {
        return PSMXBH;
    }

    public void setPSMXBH(String PSMXBH) {
        this.PSMXBH = PSMXBH;
    }
}
