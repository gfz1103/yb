package com.buit.his.gpo.ws;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.ObjectUtil;
import com.buit.commons.BaseException;
import com.buit.constans.ErrorCode;
import com.buit.his.gpo.controller.request.GpoBaseRequest;
import com.buit.his.gpo.ws.request.*;
import com.buit.his.gpo.ws.response.*;
import com.buit.his.gpo.ws.xml.XmlData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @Author sunjx
 * @Date 2020-10-30 16:59
 * @Description GPO webservice 接口
 * 参考文档:
 *  上海市医药采购服务与监管信息系统医院接口规范.pdf
 *  中药饮片-上海市医药采购服务与监管信息系统中药饮片医疗机构接口规范.pdf
 **/
@Component
public class GpoWebService {

    private static final Logger log = LoggerFactory.getLogger(GpoWebService.class);

//    @Autowired
//    @Qualifier("zhongyao")
//    private GpoWebServiceClient zhongyaoClient;

    @Autowired
    @Qualifier("xiyao")
    private GpoWebServiceClient client;

    public GpoWebServiceClient getClient(GpoConsts.YKLX yklx) {
//        log.debug("gpo webservice client => {}", yklx.getCode());
//        if(yklx == GpoConsts.YKLX.ZCY){
//            return zhongyaoClient;
//        }else{
//            return xiyaoClient;
//        }
        return client;
    }

    /**
     * YY001 医院配送点基础信息传报
     */
    public XmlData yypsdjcxxcbYY001(XmlData<YypsdjcxxcbYY001ReqMain, Object> request, GpoBaseRequest baseRequest){
        return getClient(GpoConsts.YKLX.getByKey(baseRequest.getYklx()))
                .sendRecv(request, baseRequest.getJgid(), baseRequest.getUserid(), baseRequest.getUname(), GpoConsts.YKLX.getByKey(baseRequest.getYklx()), GpoConsts.Method.YY001, new TypeReference<XmlData>() {});
    }

    /**
     * YY002 药品当前库存量数据传报
     * 中药饮片无此接口
     */
    public XmlData<Object, YpdqkclsjcbYY002RespStruct> ypdqkclsjcbYY002(XmlData<YpdqkclsjcbYY002ReqMain, YpdqkclsjcbYY002ReqStruct> request, GpoBaseRequest baseRequest){
        return getClient(GpoConsts.YKLX.getByKey(baseRequest.getYklx()))
                .sendRecv(request, baseRequest.getJgid(), baseRequest.getUserid(), baseRequest.getUname(), GpoConsts.YKLX.XY, GpoConsts.Method.YY002, new TypeReference<XmlData<Object, YpdqkclsjcbYY002RespStruct>>() {});
    }

    //YY003 获取配送单明细数据
    public XmlData<BaseRespMain, PsdYY003RespStruct> hqpsdmxsjYY003(XmlData<PsdYY003ReqMain, Object> request,GpoBaseRequest baseRequest){
        return getClient(GpoConsts.YKLX.getByKey(baseRequest.getYklx()))
                .sendRecv(request, baseRequest.getJgid(), baseRequest.getUserid(), baseRequest.getUname(), GpoConsts.YKLX.getByKey(baseRequest.getYklx()), GpoConsts.Method.YY003, new TypeReference<XmlData<BaseRespMain, PsdYY003RespStruct>>() {});
    }

    //YY004 获取发票明细数据
    public XmlData<BaseRespMain, FpYY004RespStruct> hqfpmxsjYY004(XmlData<FpYY004ReqMain, Object> request,GpoBaseRequest baseRequest){
        return getClient(GpoConsts.YKLX.getByKey(baseRequest.getYklx()))
                .sendRecv(request, baseRequest.getJgid(), baseRequest.getUserid(), baseRequest.getUname(), GpoConsts.YKLX.getByKey(baseRequest.getYklx()), GpoConsts.Method.YY004, new TypeReference<XmlData<BaseRespMain, FpYY004RespStruct>>() {});
    }

    //YY005 查询配送单数据
    public XmlData<BaseRespMain, PsdYY005RespStruct> cxpsdsjYY005(XmlData<Object, Object> request,GpoBaseRequest baseRequest){
        return getClient(GpoConsts.YKLX.getByKey(baseRequest.getYklx()))
                .sendRecv(request, baseRequest.getJgid(), baseRequest.getUserid(), baseRequest.getUname(), GpoConsts.YKLX.getByKey(baseRequest.getYklx()), GpoConsts.Method.YY005, new TypeReference<XmlData<BaseRespMain, PsdYY005RespStruct>>() {});
    }

    //YY006 按配送单编号查询配送单明细数据
    public XmlData<BaseRespMain, PsdYY006RespStruct> apsdbhcxYY006(XmlData<PsdYY006ReqMain, Object> request,GpoBaseRequest baseRequest){
        return getClient(GpoConsts.YKLX.getByKey(baseRequest.getYklx()))
                .sendRecv(request, baseRequest.getJgid(), baseRequest.getUserid(), baseRequest.getUname(), GpoConsts.YKLX.getByKey(baseRequest.getYklx()), GpoConsts.Method.YY006, new TypeReference<XmlData<BaseRespMain, PsdYY006RespStruct>>() {});
    }

    //YY007 查询发票数据
    public XmlData<BaseRespMain, FpYY007RespStruct> cxfpsjYY007(XmlData<FpYY007ReqMain, Object> request,GpoBaseRequest baseRequest){
        return getClient(GpoConsts.YKLX.getByKey(baseRequest.getYklx()))
                .sendRecv(request, baseRequest.getJgid(), baseRequest.getUserid(), baseRequest.getUname(), GpoConsts.YKLX.getByKey(baseRequest.getYklx()), GpoConsts.Method.YY007, new TypeReference<XmlData<BaseRespMain, FpYY007RespStruct>>() {});
    }

    //YY008 按发票编号查询发票明细数据
    public XmlData<BaseRespMain, FpYY008RespStruct> afpbhcxYY008(XmlData<FpYY008ReqMain, Object> request,GpoBaseRequest baseRequest){
        return getClient(GpoConsts.YKLX.getByKey(baseRequest.getYklx()))
                .sendRecv(request, baseRequest.getJgid(), baseRequest.getUserid(), baseRequest.getUname(), GpoConsts.YKLX.getByKey(baseRequest.getYklx()), GpoConsts.Method.YY008, new TypeReference<XmlData<BaseRespMain, FpYY008RespStruct>>() {});
    }

    //YY009 订单填报
    public XmlData<DdYY009RespMain, Object> ddtbYY009(XmlData<DdYY009ReqMain, DdYY009ReqStruct> request, GpoBaseRequest baseRequest){
        return getClient(GpoConsts.YKLX.getByKey(baseRequest.getYklx()))
                .sendRecv(request, baseRequest.getJgid(), baseRequest.getUserid(), baseRequest.getUname(), GpoConsts.YKLX.getByKey(baseRequest.getYklx()), GpoConsts.Method.YY009, new TypeReference<XmlData<DdYY009RespMain, Object>>() {});
    }

    //YY010 订单填报确认
    public XmlData ddtbqrYY010(XmlData<DdYY010ReqMain, Object> request, GpoBaseRequest baseRequest){
        return getClient(GpoConsts.YKLX.getByKey(baseRequest.getYklx()))
                .sendRecv(request, baseRequest.getJgid(), baseRequest.getUserid(), baseRequest.getUname(), GpoConsts.YKLX.getByKey(baseRequest.getYklx()), GpoConsts.Method.YY010, new TypeReference<XmlData>() {});
    }

    //YY011 退货单填报
    public XmlData<ThdYY011RespMain, Object> thdtbYY011(XmlData<ThdYY011ReqMain, Object> request,GpoBaseRequest baseRequest){
        return getClient(GpoConsts.YKLX.getByKey(baseRequest.getYklx()))
                .sendRecv(request, baseRequest.getJgid(), baseRequest.getUserid(), baseRequest.getUname(), GpoConsts.YKLX.XY, GpoConsts.Method.YY011, new TypeReference<XmlData<ThdYY011RespMain, Object>>() {});
    }

    //YY012 退货单填报确认
    public XmlData thdtbqrYY012(XmlData<ThdYY012ReqMain, Object> request,GpoBaseRequest baseRequest){
        return getClient(GpoConsts.YKLX.getByKey(baseRequest.getYklx()))
                .sendRecv(request, baseRequest.getJgid(), baseRequest.getUserid(), baseRequest.getUname(), GpoConsts.YKLX.XY, GpoConsts.Method.YY012, new TypeReference<XmlData>() {});
    }

    //YY013 询价单填报, 中药饮片无此接口
    public XmlData<XjdYY013RespMain, Object> xjdtbYY013(XmlData<XjdYY013ReqMain, Object> request, GpoBaseRequest baseRequest){
        return getClient(GpoConsts.YKLX.getByKey(baseRequest.getYklx()))
                .sendRecv(request, baseRequest.getJgid(), baseRequest.getUserid(), baseRequest.getUname(), GpoConsts.YKLX.XY, GpoConsts.Method.YY013, new TypeReference<XmlData<XjdYY013RespMain, Object>>() {});
    }

    //YY014 询价单填报确认, 中药饮片无此接口
    public XmlData xjdtbqrYY014(XmlData<XjdYY014ReqMain, Object> request, GpoBaseRequest baseRequest){
        return getClient(GpoConsts.YKLX.getByKey(baseRequest.getYklx()))
                .sendRecv(request, baseRequest.getJgid(), baseRequest.getUserid(), baseRequest.getUname(), GpoConsts.YKLX.XY, GpoConsts.Method.YY014, new TypeReference<XmlData>() {});
    }

    //YY015 报价单查询并获取, 中药饮片无此接口
    public XmlData<BaseRespMain, BjdYY015RespStruct> bjdcxbhqYY015(XmlData<BjdYY015ReqMain, Object> request, GpoBaseRequest baseRequest){
        return getClient(GpoConsts.YKLX.getByKey(baseRequest.getYklx()))
                .sendRecv(request, baseRequest.getJgid(), baseRequest.getUserid(), baseRequest.getUname(),  GpoConsts.YKLX.XY, GpoConsts.Method.YY015, new TypeReference<XmlData<BaseRespMain, BjdYY015RespStruct>>() {});
    }

    //YY016 对账, 中药饮片无此接口
    public XmlData<BaseRespMain, FpYY016RespStruct> dzYY016(XmlData<FpYY016ReqMain, FpYY016ReqStruct> request,GpoBaseRequest baseRequest){
        return getClient(GpoConsts.YKLX.getByKey(baseRequest.getYklx()))
                .sendRecv(request, baseRequest.getJgid(), baseRequest.getUserid(), baseRequest.getUname(), GpoConsts.YKLX.XY, GpoConsts.Method.YY016, new TypeReference<XmlData<BaseRespMain, FpYY016RespStruct>>() {});
    }

    //YY017 支付确认
    public XmlData zfqrYY017(XmlData<FpYY017ReqMain, Object> request,GpoBaseRequest baseRequest){
        return getClient(GpoConsts.YKLX.getByKey(baseRequest.getYklx()))
                .sendRecv(request, baseRequest.getJgid(), baseRequest.getUserid(), baseRequest.getUname(), GpoConsts.YKLX.getByKey(baseRequest.getYklx()), GpoConsts.Method.YY017, new TypeReference<XmlData>() {});
    }

    //YY018 配送明细验收
    public XmlData psmxysYY018(XmlData<PsdYY018ReqMain, Object> request,GpoBaseRequest baseRequest){
        return getClient(GpoConsts.YKLX.getByKey(baseRequest.getYklx()))
                .sendRecv(request, baseRequest.getJgid(), baseRequest.getUserid(), baseRequest.getUname(), GpoConsts.YKLX.getByKey(baseRequest.getYklx()), GpoConsts.Method.YY018, new TypeReference<XmlData>() {});
    }

    //YY019 发票验收
    public XmlData fpysYY019(XmlData<FpYY019ReqMain, Object> request,GpoBaseRequest baseRequest){
        return getClient(GpoConsts.YKLX.getByKey(baseRequest.getYklx()))
                .sendRecv(request, baseRequest.getJgid(), baseRequest.getUserid(), baseRequest.getUname(), GpoConsts.YKLX.getByKey(baseRequest.getYklx()), GpoConsts.Method.YY019, new TypeReference<XmlData>() {});
    }

    //YY020 单条单据通用查询
    public XmlData dtdjtycxYY020(XmlData<Object, Object> request,GpoBaseRequest baseRequest){
        return getClient(GpoConsts.YKLX.getByKey(baseRequest.getYklx()))
                .sendRecv(request, baseRequest.getJgid(), baseRequest.getUserid(), baseRequest.getUname(), GpoConsts.YKLX.getByKey(baseRequest.getYklx()), GpoConsts.Method.YY020, new TypeReference<XmlData>() {});
    }

    //YY021 获取订单明细状态
    public XmlData hqddmxztYY021(XmlData<Object, Object> request,GpoBaseRequest baseRequest){
        return getClient(GpoConsts.YKLX.getByKey(baseRequest.getYklx()))
                .sendRecv(request, baseRequest.getJgid(), baseRequest.getUserid(), baseRequest.getUname(), GpoConsts.YKLX.getByKey(baseRequest.getYklx()), GpoConsts.Method.YY021, new TypeReference<XmlData>() {});
    }

    //YY022 带仓位信息的订单填报
    public XmlData dcwxxdddYY022(XmlData<Object, Object> request,GpoBaseRequest baseRequest){
        return getClient(GpoConsts.YKLX.getByKey(baseRequest.getYklx()))
                .sendRecv(request, baseRequest.getJgid(), baseRequest.getUserid(), baseRequest.getUname(), GpoConsts.YKLX.getByKey(baseRequest.getYklx()), GpoConsts.Method.YY022, new TypeReference<XmlData>() {});
    }

    //YY023 单据明细状态查询（发票或配送单）
    public XmlData djmxztcxYY023(XmlData<Object, Object> request,GpoBaseRequest baseRequest){
        return getClient(GpoConsts.YKLX.getByKey(baseRequest.getYklx()))
                .sendRecv(request, baseRequest.getJgid(), baseRequest.getUserid(), baseRequest.getUname(), GpoConsts.YKLX.getByKey(baseRequest.getYklx()), GpoConsts.Method.YY023, new TypeReference<XmlData>() {});
    }

    //YY024 特需药品申报
    public XmlData txypsbYY024(XmlData<Object, Object> request,GpoBaseRequest baseRequest){
        return getClient(GpoConsts.YKLX.getByKey(baseRequest.getYklx()))
                .sendRecv(request, baseRequest.getJgid(), baseRequest.getUserid(), baseRequest.getUname(), GpoConsts.YKLX.getByKey(baseRequest.getYklx()), GpoConsts.Method.YY024, new TypeReference<XmlData>() {});
    }

    //YY025 特需药品申报情况查询
    public XmlData txypsbqkYY025(XmlData<Object, Object> request,GpoBaseRequest baseRequest){
        return getClient(GpoConsts.YKLX.getByKey(baseRequest.getYklx()))
                .sendRecv(request, baseRequest.getJgid(), baseRequest.getUserid(), baseRequest.getUname(), GpoConsts.YKLX.getByKey(baseRequest.getYklx()), GpoConsts.Method.YY025, new TypeReference<XmlData>() {});
    }

    //YY026 代煎处方上传, 西药，中成药无此接口
    public XmlData djcfscYY026(XmlData<Object, Object> request,GpoBaseRequest baseRequest){
        return getClient(GpoConsts.YKLX.getByKey(baseRequest.getYklx()))
                .sendRecv(request, baseRequest.getJgid(), baseRequest.getUserid(), baseRequest.getUname(), GpoConsts.YKLX.ZCY, GpoConsts.Method.YY026, new TypeReference<XmlData>() {});
    }

    //YY027 代煎处方上传确认, 西药，中成药无此接口
    public XmlData djcfscqrYY027(XmlData<Object, Object> request,GpoBaseRequest baseRequest){
        return getClient(GpoConsts.YKLX.getByKey(baseRequest.getYklx()))
                .sendRecv(request, baseRequest.getJgid(), baseRequest.getUserid(), baseRequest.getUname(), GpoConsts.YKLX.ZCY, GpoConsts.Method.YY027, new TypeReference<XmlData>() {});
    }

    //YY028 代煎处方退费, 西药，中成药无此接口
    public XmlData djcftfYY028(XmlData<Object, Object> request,GpoBaseRequest baseRequest){
        return getClient(GpoConsts.YKLX.getByKey(baseRequest.getYklx()))
                .sendRecv(request, baseRequest.getJgid(), baseRequest.getUserid(), baseRequest.getUname(), GpoConsts.YKLX.ZCY, GpoConsts.Method.YY028, new TypeReference<XmlData>() {});
    }

    //YY029 代煎处方退费确认, 西药，中成药无此接口
    public XmlData djcftfqrYY029(XmlData<Object, Object> request,GpoBaseRequest baseRequest){
        return getClient(GpoConsts.YKLX.getByKey(baseRequest.getYklx()))
                .sendRecv(request, baseRequest.getJgid(), baseRequest.getUserid(), baseRequest.getUname(), GpoConsts.YKLX.ZCY, GpoConsts.Method.YY029, new TypeReference<XmlData>() {});
    }


//    public static void main(String[] args) {
//        //head
//        Head head = new Head("127.0.0.1", "abcdef", "这是备注");
//
//        //main
//        YpdqkclsjcbYY002ReqMain reqMain = new YpdqkclsjcbYY002ReqMain();
//        reqMain.setKCCBSJ("20130801");
//        reqMain.setJLS("2");
//
//        //构造
//        XmlData<YpdqkclsjcbYY002ReqMain, Object> xmldata = new XmlData<>(head, reqMain, null);
//
//        //xml
//        String xml = XmlUtil.toXml(xmldata, "XMLDATA");
//
//        //format xml
//        System.out.println(XmlUtil.format(xml).replaceFirst(" standalone=\"no\"", ""));
//
//        //从xml字符串转为bean对象
//        XmlData<YpdqkclsjcbYY002ReqMain, Object> xmlObj = XmlUtil.toObj(xml, new TypeReference<XmlData>() {});
//
//        System.out.println();
//
//    }
}
