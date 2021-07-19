package com.buit.his.gpo.ws.response;

/**
 * @Author sunjx
 * @Date 2020-11-10 13:40
 * @Description
 **/
public class DdYY009RespMain {

    //订单编号, 市药事系统返回的可唯一标识此订单的编号
    private String DDBH;

    public String getDDBH() {
        return DDBH;
    }

    public void setDDBH(String DDBH) {
        this.DDBH = DDBH;
    }
}
