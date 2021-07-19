package com.buit.his.gpo.ws.xml;

/**
 * @Author sunjx
 * @Date 2020-11-03 09:06
 * @Description
 **/
public class Head {

    /** 发送 XML 报文 */

    //IP地址
    private String IP;
    //MAC地址
    private String MAC;


    /** 接收 XML 报文 */

    //接收时间
    private String JSSJ;
    //消息主体处理结果
    private String ZTCLJG;
    //错误提示内容
    private String CWXX;


    /** 公用 */

    //备注信息
    private String BZXX;

    public Head(String IP, String MAC, String BZXX) {
        setIP(IP);
        setMAC(MAC);
        setBZXX(BZXX);
    }

    public Head(String JSSJ, String ZTCLJG, String CWXX, String BZXX) {
        this.BZXX = BZXX;
        this.JSSJ = JSSJ;
        this.ZTCLJG = ZTCLJG;
        this.CWXX = CWXX;
    }

    public String getIP() {
        return IP;
    }

    public String getMAC() {
        return MAC;
    }

    public String getJSSJ() {
        return JSSJ;
    }

    public String getZTCLJG() {
        return ZTCLJG;
    }

    public String getCWXX() {
        return CWXX;
    }

    public String getBZXX() {
        return BZXX;
    }

    public void setIP(String IP) {
        this.IP = IP != null ? IP : "";
    }

    public void setMAC(String MAC) {
        this.MAC = MAC != null ? MAC : "";
    }

    public void setJSSJ(String JSSJ) {
        this.JSSJ = JSSJ;
    }

    public void setZTCLJG(String ZTCLJG) {
        this.ZTCLJG = ZTCLJG;
    }

    public void setCWXX(String CWXX) {
        this.CWXX = CWXX;
    }

    public void setBZXX(String BZXX) {
        this.BZXX = BZXX != null ? BZXX : "";
    }
}
