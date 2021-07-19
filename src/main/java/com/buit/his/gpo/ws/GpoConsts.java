package com.buit.his.gpo.ws;

/**
 * @Author sunjx
 * @Date 2020-10-30 17:03
 * @Description GPO webservice 接口, 消息类型码
 **/
public abstract class GpoConsts {

    /**
     * 数据状态
     */
    public enum ZT {
        YX("1", "有效"),
        SC("-1", "逻辑删除")
        ;
        private String code;
        private String desc;

        ZT(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 传报状态
     */
    public enum CBZT {
        DCB("0", "待传报"),
        YCB("1", "已传报")
        ;
        private String code;
        private String desc;

        CBZT(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 作废状态
     */
    public enum ZFZT {
        YX("1", "有效"),
        YZF("-1", "已作废")
        ;
        private String code;
        private String desc;

        ZFZT(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 确认状态
     */
    public enum QRZT {
        DQR("0", "待确认"),
        YQR("1", "已确认")
        ;
        private String code;
        private String desc;

        QRZT(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 验收状态
     */
    public enum YSZT {
        DYS("0", "待验收"),
        YYS("1", "已验收")
        ;
        private String code;
        private String desc;

        YSZT(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 对账状态
     */
    public enum DZZT {
        DDZ("0", "待对账"),
        YDZ("1", "已对账")
        ;
        private String code;
        private String desc;

        DZZT(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 支付状态
     */
    public enum ZFUZT {
        DZF("0", "待支付"),
        YZF("1", "已支付")
        ;
        private String code;
        private String desc;

        ZFUZT(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 入库状态
     */
    public enum RKZT {
        DRK("0", "待入库"),
        YRK("1", "已入库")
        ;
        private String code;
        private String desc;

        RKZT(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 药库类型
     */
    public enum YKLX {
        XY(1, "xiyao", "西药库"),
        ZCY(2, "zhongyao","中草药库")
        ;
        private Integer key;
        private String code;
        private String desc;

        public static YKLX getByKey(Integer key){
            if(null == key){
                return null;
            }

            for (YKLX value : values()) {
                if(key.equals(value.key)){
                    return value;
                }
            }
            return null;
        }

        YKLX(Integer key, String code, String desc) {
            this.key = key;
            this.code = code;
            this.desc = desc;
        }

        public Integer getKey() {
            return key;
        }

        public String getDesc() {
            return desc;
        }

        public String getCode() {
            return code;
        }
    }

    /**
     * 接口代码,上海市医药采购服务与监管信息系统医院接口规范.pdf
     * 中药饮片-上海市医药采购服务与监管信息系统中药饮片医疗机构接口规范.pdf
     */
    public enum Method {
        YY001("YY001", "医院配送点基础信息传报"),
        YY002("YY002", "药品当前库存量数据传报"),
        YY003("YY003", "获取配送单明细数据"),
        YY004("YY004", "获取发票明细数据"),
        YY005("YY005", "查询配送单数据"),
        YY006("YY006", "按配送单编号查询配送单明细数据"),
        YY007("YY007", "查询发票数据"),
        YY008("YY008", "按发票编号查询发票明细数据"),
        YY009("YY009", "订单填报"),
        YY010("YY010", "订单填报确认"),
        YY011("YY011", "退货单填报"),
        YY012("YY012", "退货单填报确认"),
        YY013("YY013", "询价单填报"),
        YY014("YY014", "询价单填报确认"),
        YY015("YY015", "报价单查询并获取"),
        YY016("YY016", "对账"),
        YY017("YY017", "支付确认"),
        YY018("YY018", "配送明细验收"),
        YY019("YY019", "发票验收"),
        YY020("YY020", "单条单据通用查询"),
        YY021("YY021", "获取订单明细状态"),
        YY022("YY022", "带仓位信息的订单填报"),
        YY023("YY023", "单据明细状态查询（发票或配送单）"),
        YY024("YY024", "特需药品申报"),
        YY025("YY025", "特需药品申报情况查询"),
        YY026("YY026", "代煎处方上传"),
        YY027("YY027", "代煎处方上传确认"),
        YY028("YY028", "代煎处方退费"),
        YY029("YY029", "代煎处方退费确认"),
        ;
        private String code;
        private String desc;

        Method(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

}
