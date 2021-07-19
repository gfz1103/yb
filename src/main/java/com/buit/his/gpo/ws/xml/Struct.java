package com.buit.his.gpo.ws.xml;

/**
 * @Author sunjx
 * @Date 2020-11-03 10:19
 * @Description
 **/
public class Struct<T> {

    private T STRUCT;

    public Struct(T STRUCT) {
        this.STRUCT = STRUCT;
    }

    public T getSTRUCT() {
        return STRUCT;
    }

    public void setSTRUCT(T STRUCT) {
        this.STRUCT = STRUCT;
    }
}
