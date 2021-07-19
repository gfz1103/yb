package com.buit.config.webservice;

/**
 * @Author sunjx
 * @Date 2020-11-13 14:14
 * @Description
 **/
public class GpoWebServiceConfigPropertiesBean {

    private String name;

    //接口WSDL
    private String wsdl;

    //操作员用户名（市药事系统注册用户的用户名）
    private String user;

    //操作员密码（市药事系统注册用户的登录密码）
    private String pwd;

    //操作员所属机构编码（市医保统一发布的医院编码）
    private String jgbm;

    //接口版本号
    private String version = "1.0.0.0";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWsdl(String wsdl) {
        this.wsdl = wsdl;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setJgbm(String jgbm) {
        this.jgbm = jgbm;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getWsdl() {
        return wsdl;
    }

    public String getUser() {
        return user;
    }

    public String getPwd() {
        return pwd;
    }

    public String getJgbm() {
        return jgbm;
    }

    public String getVersion() {
        return version;
    }
}
