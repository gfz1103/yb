package com.buit.his.shyb.source.entity;

import java.io.Serializable;

/**
 * 医保回参实体类
 * @author beijunhua
 * @date 2020/8/18 8:52
 */
public class SettleResultDTO implements Serializable {
    /**
     * 交易状态
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 业务对象
     */
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SettleResultDTO{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
