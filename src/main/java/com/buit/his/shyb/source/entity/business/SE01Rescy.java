package com.buit.his.shyb.source.entity.business;
/**
 * 账户查询实体类
 * @author beijunhua
 * @date 2020/9/14 11:38
 */
public class SE01Rescy {

    /**
     * 姓名
     */
    private String username;

    /**
     * 证件号
     */
    private String idno;

    /**
     * 证件类型
     */
    private String idtype;

    /**
     * 令牌
     */
    private String ecToken;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getIdtype() {
        return idtype;
    }

    public void setIdtype(String idtype) {
        this.idtype = idtype;
    }

    public String getEcToken() {
        return ecToken;
    }

    public void setEcToken(String ecToken) {
        this.ecToken = ecToken;
    }
}
