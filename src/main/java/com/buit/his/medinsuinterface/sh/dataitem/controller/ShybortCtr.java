package com.buit.his.medinsuinterface.sh.dataitem.controller;

import com.buit.commons.BaseSpringController;
import com.buit.commons.SysUser;
import com.buit.his.medinsuinterface.sh.dataitem.service.ShybbbReportSer;
import com.buit.his.shyb.source.entity.SettleResultDTO;
import com.buit.his.shyb.source.entity.business.MessageBody;
import com.buit.his.shyb.source.entity.business.SM01;
import com.buit.his.shyb.source.enums.MessageTypeEnum;
import com.buit.his.shyb.source.enums.TradingChannelEnum;
import com.buit.his.shyb.source.model.MedicalInsuranceModel;
import com.buit.his.shyb.source.model.UnifiedBusinessManager;
import com.buit.his.shyb.source.service.impl.SendMessageServiceImpl;
import com.buit.his.shyb.source.util.HttpsClientq;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 挂号收费报表<br>
 *
 * @author WY
 */
//@Api(tags = "医保报表拆分啊啊啊啊啊啊")
//@Controller
//@RequestMapping("/shybbbReportaaa")
//public class ShybortCtr extends BaseSpringController {
//    @Autowired
//    public SendMessageServiceImpl sendMessageServiceImpl;
//    @Autowired
//    public UnifiedBusinessManager unifiedBusinessManager;
//    @Autowired
//    private ShybbbReportSer shybbbReportSer;
//    @RequestMapping("/mzbkbbd")
//    @ResponseBody
//    @ApiOperationSupport(author = "卑军华")
//    @ApiOperation(value = "民政帮困D#", httpMethod = "POST", notes = "民政帮困D#")
//    public MessageBody buildCommonMessage(String orgId, MessageTypeEnum messageType, TradingChannelEnum tradingChannelEnum) {
//        SysUser user = new SysUser();
//        //SysUser user = getUser();
//        user.setHospitalId(310112041);
//        user.setUserName("测试人");
//        Integer operatorId = user.getUserId();
//        String operatorName = user.getUserName();
//
//
//
//        MedicalInsuranceModel model = new MedicalInsuranceModel();
//        String msgId = model.getMsgId(orgId,9);
//
//        MessageBody messageBody = new MessageBody();
//        messageBody.setJysj(new Date());
//        messageBody.setMsgid(msgId);
//        messageBody.setJgdm(orgId);
//        messageBody.setCzybm(operatorId);
//        messageBody.setCzyxm(operatorName);
//
//        messageBody.setXxlxm(messageType);
//        messageBody.setJyqd(tradingChannelEnum);
//        return messageBody;
//    }
//
//    @RequestMapping("/mzbkbbdss")
//    @ResponseBody
//    @ApiOperationSupport(author = "卑军华")
//    @ApiOperation(value = "mzbkbbdss#", httpMethod = "POST", notes = "民政帮困D#")
//    public void mzbkbbdss() {
//            Map<String,Object> map= new HashMap<>() ;
////            String req="{\"jysj\":\"2020-10-28 16:34:25\",\"xxlxm\":\"SM01\",\"xxfhm\":\"\",\"fhxx\":\"\",\"bbh\":\"\",\"msgid\":\"3323398600020201028000000135\",\"xzqhdm\":\"\",\"jgdm\":\"33233986000\",\"czybm\":8888,\"czyxm\":\"cs\",\"xxnr\":{\"cardtype\":\"1\",\"carddata\":\"\"},\"jyqd\":\"10\",\"jyyzm\":\"\",\"zdjbhs\":\"\"}";
////
////
////            map.put("req",req);
////            Map a = HttpsClientq.post("http://172.22.145.198:9500/yb/sendMes",req);
//
//        map.put("cardtype","1");
//        map.put("carddata","");
//        map.put("ip","172.22.145.198");
//        map.put("xxlxm","SM01");
//        map.put("orgId","33233986000");
//        map.put("jyqd","33233986000");
//   //     SettleResultDTO requestResult = unifiedBusinessManager.sm01(map);
//        MessageBody messageBody = new MessageBody();
//        messageBody.setJysj(new Date());
//        messageBody.setMsgid("3323398600020201028000000135");
//        messageBody.setJgdm("33233986000");
//        messageBody.setCzybm(8888);
//        messageBody.setCzyxm("cc");
//        messageBody.setXxlxm(MessageTypeEnum.SM01);
//        messageBody.setJyqd(TradingChannelEnum.OFFLINE);
//        SM01 sm01 = new SM01();
//        sm01.setCardtype("1");
//
//        sm01.setCarddata("");
//        messageBody.setXxnr(sm01);
//        SettleResultDTO settleResultDTO = sendMessageServiceImpl.sendMessage("http://172.22.145.198:9500/yb/sendMes", messageBody);
//            System.out.println(settleResultDTO);
//    }


    //}




