package com.buit.constans;

/**
 * @Author sunjx
 * @Date 2020-10-30 16:41
 * @Description 错误码常量
 **/
public class ErrorCode {

    /** GPO webservice */
    //gpo webservice 调用异常
    public static final String ERROR_GPO_WS_0001 = "ERROR_GPO_WS_0001";
    //gpo 参数签名出现异常
    public static final String ERROR_GPO_WS_0002 = "ERROR_GPO_WS_0002";
    //gpo 返回数据格式解析异常
    public static final String ERROR_GPO_WS_0003 = "ERROR_GPO_WS_0003";
    //gpo webservice 处理返回失败
    public static final String ERROR_GPO_WS_0004 = "ERROR_GPO_WS_0004";
    //gpo 返回数据格式转换类型错误
    public static final String ERROR_GPO_WS_0005 = "ERROR_GPO_WS_0005";
    //gpo webservice 未配置
    public static final String ERROR_GPO_WS_0006 = "ERROR_GPO_WS_0006";

    /** 配送点 */
    //配送点编号已存在
    public static final String ERROR_GPO_PSD_0001 = "ERROR_GPO_PSD_0001";
    //配送点名称已存在
    public static final String ERROR_GPO_PSD_0002 = "ERROR_GPO_PSD_0002";
    //该配送点数据不存在
    public static final String ERROR_GPO_PSD_0003 = "ERROR_GPO_PSD_0003";
    //该配送点数据已传报,不能修改
    public static final String ERROR_GPO_PSD_0004 = "ERROR_GPO_PSD_0004";
    //该配送点数据已传报,不能再次传报
    public static final String ERROR_GPO_PSD_0005 = "ERROR_GPO_PSD_0005";
    //医院已经存在一个有效传报的配送点
    public static final String ERROR_GPO_PSD_0006 = "ERROR_GPO_PSD_0006";
    //待传报的不能作废
    public static final String ERROR_GPO_PSD_0007 = "ERROR_GPO_PSD_0007";
    //已传报的不能删除
    public static final String ERROR_GPO_PSD_0008 = "ERROR_GPO_PSD_0008";

    /** 药企进货单位 */
    //该进货单位已经对照
    public static final String ERROR_GPO_YQJHDW_0001 = "ERROR_GPO_YQJHDW_0001";
    //该药企已经对照
    public static final String ERROR_GPO_YQJHDW_0002 = "ERROR_GPO_YQJHDW_0002";

    /** GPO询价单 */
    //商品统编代码不能为空
    public static final String ERROR_GPO_XJD_001 = "ERROR_GPO_XJD_001";
    //医院内部商品代码不能为空
    public static final String ERROR_GPO_XJD_002 = "ERROR_GPO_XJD_002";
    //商品类型不能为空
    public static final String ERROR_GPO_XJD_003 = "ERROR_GPO_XJD_003";
    //药品类型不能为空
    public static final String ERROR_GPO_XJD_004 = "ERROR_GPO_XJD_004";
    //询价时间-起始不能为空
    public static final String ERROR_GPO_XJD_005 = "ERROR_GPO_XJD_005";
    //询价时间-结束不能为空
    public static final String ERROR_GPO_XJD_006 = "ERROR_GPO_XJD_006";
    //该询价单数据不存在
    public static final String ERROR_GPO_XJD_007 = "ERROR_GPO_XJD_007";
    //已传报的询价单不能修改
    public static final String ERROR_GPO_XJD_008 = "ERROR_GPO_XJD_008";
    //已传报的、已确认的、已作废的、已删除的都不能传报
    public static final String ERROR_GPO_XJD_009 = "ERROR_GPO_XJD_009";
    //待传报的不能作废
    public static final String ERROR_GPO_XJD_010 = "ERROR_GPO_XJD_010";
    //已确认的、已作废的、已删除的都不能确认
    public static final String ERROR_GPO_XJD_011 = "ERROR_GPO_XJD_011";
    //已传报的不能删除
    public static final String ERROR_GPO_XJD_012 = "ERROR_GPO_XJD_012";

    /** GPO 订单 */
    //该订单不存在
    public static final String ERROR_GPO_DD_0001 = "ERROR_GPO_DD_0001";
    //已传报的订单不能删除
    public static final String ERROR_GPO_DD_0002 = "ERROR_GPO_DD_0002";
    //已传报的订单不能传报
    public static final String ERROR_GPO_DD_0003 = "ERROR_GPO_DD_0003";
    //已传报的订单不能修改
    public static final String ERROR_GPO_DD_0004 = "ERROR_GPO_DD_0004";
    //订单明细不能为空
    public static final String ERROR_GPO_DD_0005 = "ERROR_GPO_DD_0005";
    //订单已作废不能确认
    public static final String ERROR_GPO_DD_0006 = "ERROR_GPO_DD_0006";
    //订单已确认不能确认
    public static final String ERROR_GPO_DD_0007 = "ERROR_GPO_DD_0007";
    //订单待传报不能确认
    public static final String ERROR_GPO_DD_0008 = "ERROR_GPO_DD_0008";
    //已确认的订单不能作废
    public static final String ERROR_GPO_DD_0009 = "ERROR_GPO_DD_0009";
    //确认订单失败
    public static final String ERROR_GPO_DD_0010 = "ERROR_GPO_DD_0010";

    /** GPO 退货单 */
    //该退货单不存在
    public static final String ERROR_GPO_THD_0001 = "ERROR_GPO_THD_0001";
    //已传报的退货单不能修改
    public static final String ERROR_GPO_THD_0002 = "ERROR_GPO_THD_0002";
    //已传报的退货单不能再传报
    public static final String ERROR_GPO_THD_0003 = "ERROR_GPO_THD_0003";
    //已作废的退货单不能再确认
    public static final String ERROR_GPO_THD_0004 = "ERROR_GPO_THD_0004";
    //已确认的退货单不能再确认
    public static final String ERROR_GPO_THD_0005 = "ERROR_GPO_THD_0005";
    //待传报的退货单不能再确认
    public static final String ERROR_GPO_THD_0006 = "ERROR_GPO_THD_0006";
    //已确认的退货单不能作废
    public static final String ERROR_GPO_THD_0007 = "ERROR_GPO_THD_0007";
    //已传报的退货单不能删除
    public static final String ERROR_GPO_THD_0008 = "ERROR_GPO_THD_0008";

    /** GPO 发票 */
    //数据错误,未查询到相关商品信息
    public static final String ERROR_GPO_FP_0001 = "ERROR_GPO_FP_0001";
    //发票进货价格与系统商品价格不一致,不能入库
    public static final String ERROR_GPO_FP_0002 = "ERROR_GPO_FP_0002";
}
