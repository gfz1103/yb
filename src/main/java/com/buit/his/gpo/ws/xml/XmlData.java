package com.buit.his.gpo.ws.xml;

import java.util.List;

/**
 * @Author sunjx
 * @Date 2020-11-03 10:04
 * @Description T main实体类, E struct实体类; 没有的用Object 类替代; 构造方法中参数为空的用 null
 **/
public class XmlData<T,E> {

    private Head HEAD;

    private T MAIN;

    private List<Struct<E>> DETAIL;

    public XmlData(Head HEAD, T MAIN, List<Struct<E>> DETAIL) {
        this.HEAD = HEAD;
        this.MAIN = MAIN;
        this.DETAIL = DETAIL;
    }

    public Head getHEAD() {
        return HEAD;
    }

    public T getMAIN() {
        return MAIN;
    }

    public List<Struct<E>> getDETAIL() {
        return DETAIL;
    }

    public void setHEAD(Head HEAD) {
        this.HEAD = HEAD;
    }

    public void setMAIN(T MAIN) {
        this.MAIN = MAIN;
    }

    public void setDETAIL(List<Struct<E>> DETAIL) {
        this.DETAIL = DETAIL;
    }
}
