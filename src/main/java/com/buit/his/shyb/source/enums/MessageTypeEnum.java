package com.buit.his.shyb.source.enums;

/**
 * 消息类型枚举
 * @author beijh
 */
public enum MessageTypeEnum {


    /**
     * 保障卡基本信息读取
     */
    S000,

    /**
     * 门诊类挂号
     */
    SH01,

    /**
     * 门诊类挂号确认
     */
    SH02,

    /**
     * 门诊类收费
     */
    SI11,

    /**
     * 门诊类收费确认
     */
    SI12,

    /**
     * 住院类收费
     */
    SI51,

    /**
     * 住院类收费确认
     */
    SI52,

    /**
     * 退款
     */
    SK01,

    /**
     * 登记业务
     */
    SJ11,

    /**
     * 登记撤消
     */
    SJ21,

    /**
     * 登记查询
     */
    SJ31,

    /**
     * 干保登记查询
     */
    SJ41,

    /**
     * 民政帮困定点查询
     */
    SJ51,

    /**
     * 居保门诊转院撤销
     */
    SJC1,

    /**
     * 居保门诊转院查询
     */
    SJD1,

    /**
     * 居保门诊转院
     */
    SJF1,

    /**
     * 居保门诊转院转入医院查询
     */
    SJH1,

    /**
     * 对账
     */
    SL01,

    /**
     * 帐户查询
     */
    SM01,

    /**
     * 交易查询
     */
    SI91,

    /**
     * 工伤认定号查询
     */
    SJG1,

    /**
     * 明细账单提交
     */
    SN01,

    /**
     * 明细账单撤销
     */
    SN02,

    /**
     * 解码
     */
    SE01,

    /**
     * 互联网线上结算
     */
    SE02,

    /**
     * 支付结果推送
     */
    SE03,

    /**
     * 支付结果查询
     */
    SE04,

    /**
     * 现金退款结果推送
     */
    SE05,

    /**
     * 现金退款结果查询
     */
    SE06,

    /**
     * 现金退款提交
     */
    SE07;
}