package com.buit.his.shyb.source.entity.business;

import java.util.List;

/**
 * 居保门诊转院查询res
 * @author beijunhua
 */
public class SJD1Res {
    /**
     * 凭证类别
     */
    private String cardtype;

    /**
     * 凭证码
     */
    private String cardid;

    /**
     * 登记类别
     */
    private String djtype;

    /**
     * 姓名
     */
    private String personname;

    /**
     * 转诊消息循环
     */
    private List<zzxxs> ZZXXS;

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public String getDjtype() {
        return djtype;
    }

    public void setDjtype(String djtype) {
        this.djtype = djtype;
    }

    public String getPersonname() {
        return personname;
    }

    public void setPersonname(String personname) {
        this.personname = personname;
    }

    public List<zzxxs> getZZXXS() {
        return ZZXXS;
    }

    public void setZZXXS(List<zzxxs> ZZXXS) {
        this.ZZXXS = ZZXXS;
    }

    public static class zzxxs {
        /**
         * 转出医院编码
         */
        private String zcjgdm;
        /**
         * 转出医院名称
         */
        private String zcjgmc;

        /**
         * 转入医院名称
         */
        private String zrjgdm;
        /**
         * 转入医院名称
         */
        private String zrjgmc;

        /**
         * 门诊转院日期
         */
        private String startdate;

        public String getZcjgmc() {
            return zcjgmc;
        }

        public void setZcjgmc(String zcjgmc) {
            this.zcjgmc = zcjgmc;
        }

        public String getZrjgmc() {
            return zrjgmc;
        }

        public void setZrjgmc(String zrjgmc) {
            this.zrjgmc = zrjgmc;
        }

        public String getStartdate() {
            return startdate;
        }

        public void setStartdate(String startdate) {
            this.startdate = startdate;
        }
    }
}
