package com.buit.his.shyb.source.service.impl;

import com.buit.constans.TableName;
import com.buit.his.shyb.source.entity.SettleResultDTO;
import com.buit.his.shyb.source.entity.business.MessageBody;
import com.buit.his.shyb.source.enums.MessageTypeEnum;
import com.buit.his.shyb.source.util.*;
import com.buit.system.utill.MedicineUtils;
import com.buit.utill.RedisFactory;
import com.buit.utill.StrUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 医保
 * @author beijunhua
 */
@Service
public class SendMessageServiceImpl  {
  //  @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RedisFactory redisFactory;
    @Autowired
    private RestTemplateBuilder builder;

    private static String charset="UTF-8";

    public SettleResultDTO sendMessage(String url, MessageBody messageBodyI) {
//        restTemplate=    builder.build();
//        String req="{\"jysj\":\"2020-10-28 16:34:25\",\"xxlxm\":\"SM01\",\"xxfhm\":\"\",\"fhxx\":\"\",\"bbh\":\"\",\"msgid\":\"3323398600020201028000000135\",\"xzqhdm\":\"\",\"jgdm\":\"33233986000\",\"czybm\":8888,\"czyxm\":\"测试\",\"xxnr\":{\"cardtype\":\"1\",\"carddata\":\"\"},\"jyqd\":\"10\",\"jyyzm\":\"\",\"zdjbhs\":\"\"}";
//        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
//        map.add("req",req);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
//        ResponseEntity<String> a = restTemplate.postForEntity(url, request,String.class);
//        String b = a.getBody();
//        System.out.println(a);
//        System.out.println(b);
//        return null;
//.
        String message = JackJsonUtil.writeValueAsString(messageBodyI);
//        message = "{\n" +
//                "\t\"jysj\": \"2020-06-09 11:22:22\",\n" +
//                "\t\"xxlxm\": \"SN01\",\n" +
//                "\t\"xxfhm\": \"\",\n" +
//                "\t\"fhxx\": \"\",\n" +
//                "\t\"bbh\": \"\",\n" +
//                "\t\"msgid\": \"3323398600020201104000001293\",\n" +
//                "\t\"xzqhdm\": \"\",\n" +
//                "\t\"jgdm\": \"33233986000\",\n" +
//                "\t\"czybm\": \"8888\",\n" +
//                "\t\"czyxm\": \"金戈\",\n" +
//                "\t\"xxnr\": {\n" +
//                "\t\t\"cardtype\": \"1\",\n" +
//                "\t\t\"carddata\": \"Z598972C6\",\n" +
//                "\t\t\"jzdyh\": \"20110100000062479621\",\n" +
//                "\t\t\"djh\": \"\",\n" +
//                "\t\t\"mxzdh\": \"\",\n" +
//                "\t\t\"bcmxylfyze\": \"1.00\",\n" +
//                "\t\t\"jslxbz\": \"120\",\n" +
//                "\t\t\"mxxms\": [{\n" +
//                "\t\t\t\"xh\": 1,\n" +
//                "\t\t\t\"cfh\": \"7296552\",\n" +
//                "\t\t\t\"deptid\": \"07\",\n" +
//                "\t\t\t\"ksmc\": \"儿科\",\n" +
//                "\t\t\t\"cfysh\": \"207666\",\n" +
//                "\t\t\t\"cfysxm\": \"陆林\",\n" +
//                "\t\t\t\"fylb\": \"08\",\n" +
//                "\t\t\t\"mxxmbm\": \"XN0000030072418\",\n" +
//                "\t\t\t\"mxxmmc\": \"\",\n" +
//                "\t\t\t\"mxxmdw\": \"盒\",\n" +
//                "\t\t\t\"mxxmdj\": \"29.931\",\n" +
//                "\t\t\t\"mxxmsl\": \"0.03333\",\n" +
//                "\t\t\t\"mxxmje\": \"1.000\",\n" +
//                "\t\t\t\"mxxmjyfy\": \"1.000\",\n" +
//                "\t\t\t\"mxxmybjsfwfy\": \"1.000\",\n" +
//                "\t\t\t\"yyclpp\": \"地高辛片\",\n" +
//                "\t\t\t\"zczh\": \"\",\n" +
//                "\t\t\t\"mxxmgg\": \"0.25mg*10'*3板/盒,铝塑包装\",\n" +
//                "\t\t\t\"mxxmsyrq\": \"20200609\",\n" +
//                "\t\t\t\"bxbz\": \"0\",\n" +
//                "\t\t\t\"sftfbz\": \"1\",\n" +
//                "\t\t\t\"jfbz\": \"0\",\n" +
//                "\t\t\t\"sfxfmx\": \"0\",\n" +
//                "\t\t\t\"xfmx\": []\n" +
//                "\t\t}]\n" +
//                "\t},\n" +
//                "\t\"jyqd\": \"10\",\n" +
//                "\t\"jyyzm\": \"\",\n" +
//                "\t\"zdjbhs\": \"\"\n" +
//                "}";
        //调用医保中间服务
        System.out.println("医保调用Input:   " + message);


/*        Map<String,Object> map= new HashMap<>() ;
        String req="{\"jysj\":\"2020-10-28 16:34:25\",\"xxlxm\":\"SM01\",\"xxfhm\":\"\",\"fhxx\":\"\",\"bbh\":\"\",\"msgid\":\"3323398600020201028000000135\",\"xzqhdm\":\"\",\"jgdm\":\"33233986000\",\"czybm\":8888,\"czyxm\":\"cs\",\"xxnr\":{\"cardtype\":\"1\",\"carddata\":\"\"},\"jyqd\":\"10\",\"jyyzm\":\"\",\"zdjbhs\":\"\"}";
        map.put("req",req);
        Map a = HttpsClientq.post("http://172.22.145.198:9500/yb/sendMes",req);
        System.out.println(a);
        return null;*/



 //       测试用
//        Map httpResultMap = new HashMap<String,Object>();
//        httpResultMap.put("status",200);
//        if(messageBodyI.getXxlxm()==MessageTypeEnum.SM01){
//            httpResultMap.put("result","{\n" +
//                    "\t\"bbh\": \"0001\",\n" +
//                    "\t\"czybm\": \"8888\",\n" +
//                    "\t\"czyxm\": \"cs\",\n" +
//                    "\t\"fhxx\": \"\",\n" +
//                    "\t\"jgdm\": \"33233986000\",\n" +
//                    "\t\"jyqd\": \"10\",\n" +
//                    "\t\"jysj\": \"2020-11-02 10:59:00\",\n" +
//                    "\t\"jyyzm\": \"\",\n" +
//                    "\t\"msgid\": \"3323398600020201028000000135\",\n" +
//                    "\t\"recvTime\": \"0004f875a451fa5c\",\n" +
//                    "\t\"sysResv\": \"1234567887654321pHE1HE1ppEH1E1pH1HpEppH1Ep11HHE1EpH1ac1691c6\",\n" +
//                    "\t\"xxfhm\": \"P001\",\n" +
//                    "\t\"xxlxm\": \"SM01\",\n" +
//                    "\t\"xxnr\": {\n" +
//                    "\t\t\"accountattr\": \"1000000000000000\",\n" +
//                    "\t\t\"beinqf\": \"1500.00\",\n" +
//                    "\t\t\"cardid\": \"6015522532\",\n" +
//                    "\t\t\"cardtype\": \"0\",\n" +
//                    "\t\t\"curaccountamt\": \"1393.20\",\n" +
//                    "\t\t\"hisaccountamt\": \"3218.84\",\n" +
//                    "\t\t\"jlch\": \"301558481001\",\n" +
//                    "\t\t\"personname\": \"卑军华\",\n" +
//                    "\t\t\"qfxsfdxxfylj\": \"0.00\",\n" +
//                    "\t\t\"qfxxpay\": \"0.00\",\n" +
//                    "\t\t\"rationpay\": \"1500.00\",\n" +
//                    "\t\t\"tcfdx\": \"550000.00\",\n" +
//                    "\t\t\"totalmzzfdpay\": \"0.00\",\n" +
//                    "\t\t\"xb\": \"1\",\n" +
//                    "\t\t\"ybsqjmbz\": \"1\"\n" +
//                    "\t},\n" +
//                    "\t\"xzqhdm\": \"\",\n" +
//                    "\t\"zdsbsbm\": \"\"\n" +
//                    "}");
//        }else if(messageBodyI.getXxlxm()==MessageTypeEnum.SH01){
//            httpResultMap.put("result","{\n" +
//                    "\t\"bbh\": \"0001\",\n" +
//                    "\t\"czybm\": \"8888\",\n" +
//                    "\t\"czyxm\": \"医保测试\",\n" +
//                    "\t\"fhxx\": \"\",\n" +
//                    "\t\"jgdm\": \"33233986000\",\n" +
//                    "\t\"jyqd\": \"10\",\n" +
//                    "\t\"jysj\": \"2020-11-01 13:44:51\",\n" +
//                    "\t\"jyyzm\": \"\",\n" +
//                    "\t\"msgid\": \"3323398600020201030000000665\",\n" +
//                    "\t\"recvTime\": \"0004f875a451fa5c\",\n" +
//                    "\t\"sysResv\": \"12345678876543217e9ue9u779eu9u7eue7977eu97uuee9u97euac1691c6\",\n" +
//                    "\t\"xxfhm\": \"P001\",\n" +
//                    "\t\"xxlxm\": \"SH01\",\n" +
//                    "\t\"xxnr\": {\n" +
//                    "\t\t\"accountattr\": \"1000000000000000\",\n" +
//                    "\t\t\"cardid\": \"Z598972C6\",\n" +
//                    "\t\t\"cardtype\": \"1\",\n" +
//                    "\t\t\"curaccountamt\": \"0.00\",\n" +
//                    "\t\t\"curaccountpay\": \"0.00\",\n" +
//                    "\t\t\"fjdxjzfs\": \"0.00\",\n" +
//                    "\t\t\"fjdzhzfs\": \"0.00\",\n" +
//                    "\t\t\"fjzfs\": \"0.00\",\n" +
//                    "\t\t\"fybjsfwfyze\": \"5.00\",\n" +
//                    "\t\t\"hisaccountamt\": \"0.00\",\n" +
//                    "\t\t\"hisaccountpay\": \"0.00\",\n" +
//                    "\t\t\"jfje\": \"0\",\n" +
//                    "\t\t\"jlc\": \"\",\n" +
//                    "\t\t\"jmjsbz\": \"0\",\n" +
//                    "\t\t\"jssqxh\": \"H023323398600000027681101712597532\",\n" +
//                    "\t\t\"personname\": \"测试卡\",\n" +
//                    "\t\t\"personspectag\": \"0\",\n" +
//                    "\t\t\"tcdxjzfs\": \"0.00\",\n" +
//                    "\t\t\"tcdzhzfs\": \"0.00\",\n" +
//                    "\t\t\"tczfs\": \"0.00\",\n" +
//                    "\t\t\"totalexpense\": \"15.00\",\n" +
//                    "\t\t\"ybjsfwfyze\": \"15.00\",\n" +
//                    "\t\t\"zfdlnzhzfs\": \"0.00\",\n" +
//                    "\t\t\"zfdxjzfs\": \"15.00\"\n" +
//                    "\t},\n" +
//                    "\t\"xzqhdm\": \"310000\",\n" +
//                    "\t\"zdsbsbm\": \"\"\n" +
//                    "}");
//        }else if(messageBodyI.getXxlxm()==MessageTypeEnum.SH02){
//            httpResultMap.put("result","{\n" +
//                    "\t\"bbh\": \"0001\",\n" +
//                    "\t\"czybm\": \"8888\",\n" +
//                    "\t\"czyxm\": \"医保测试\",\n" +
//                    "\t\"fhxx\": \"\",\n" +
//                    "\t\"jgdm\": \"33233986000\",\n" +
//                    "\t\"jyqd\": \"10\",\n" +
//                    "\t\"jysj\": \"2020-11-01 13:47:04\",\n" +
//                    "\t\"jyyzm\": \"\",\n" +
//                    "\t\"msgid\": \"3323398600020201030000000824\",\n" +
//                    "\t\"recvTime\": \"0004f875a451fa5c\",\n" +
//                    "\t\"sysResv\": \"12345678876543218WW1WW188WW1W18W1W8W88W1W811WWW1W8W1ac1691c6\",\n" +
//                    "\t\"xxfhm\": \"P001\",\n" +
//                    "\t\"xxlxm\": \"SH02\",\n" +
//                    "\t\"xxnr\": {\n" +
//                    "\t\t\"cardid\": \"Z598972C6\",\n" +
//                    "\t\t\"cardtype\": \"1\",\n" +
//                    "\t\t\"curaccountamt\": \"0.00\",\n" +
//                    "\t\t\"curaccountpay\": \"0.00\",\n" +
//                    "\t\t\"fjdxjzfs\": \"0.00\",\n" +
//                    "\t\t\"fjdzhzfs\": \"0.00\",\n" +
//                    "\t\t\"fjzfs\": \"0.00\",\n" +
//                    "\t\t\"fybjsfwfyze\": \"5.00\",\n" +
//                    "\t\t\"hisaccountamt\": \"0.00\",\n" +
//                    "\t\t\"hisaccountpay\": \"0.00\",\n" +
//                    "\t\t\"jfje\": \"0.00\",\n" +
//                    "\t\t\"jlc\": \"\",\n" +
//                    "\t\t\"jmjsbz\": \"0\",\n" +
//                    "\t\t\"jssqxh\": \"H023323398600000027681101712597532\",\n" +
//                    "\t\t\"jzdyh\": \"20110100000062479621\",\n" +
//                    "\t\t\"lsh\": \"2001010125089865\",\n" +
//                    "\t\t\"tcdxjzfs\": \"0.00\",\n" +
//                    "\t\t\"tcdzhzfs\": \"0.00\",\n" +
//                    "\t\t\"tczfs\": \"0.00\",\n" +
//                    "\t\t\"totalexpense\": \"15.00\",\n" +
//                    "\t\t\"ybjsfwfyze\": \"15.00\",\n" +
//                    "\t\t\"zfdlnzhzfs\": \"0.00\",\n" +
//                    "\t\t\"zfdxjzfs\": \"15.00\"\n" +
//                    "\t},\n" +
//                    "\t\"xzqhdm\": \"310000\",\n" +
//                    "\t\"zdsbsbm\": \"\"\n" +
//                    "}");
//        }
//        else if(messageBodyI.getXxlxm()==MessageTypeEnum.SK01){
//            httpResultMap.put("result","{\n" +
//                    "\t\"bbh\": \"0001\",\n" +
//                    "\t\"czybm\": \"8888\",\n" +
//                    "\t\"czyxm\": \"医保测试\",\n" +
//                    "\t\"fhxx\": \"\",\n" +
//                    "\t\"jgdm\": \"33233986000\",\n" +
//                    "\t\"jyqd\": \"10\",\n" +
//                    "\t\"jysj\": \"2020-11-01 16:13:11\",\n" +
//                    "\t\"jyyzm\": \"\",\n" +
//                    "\t\"msgid\": \"3323398600020201101000000934\",\n" +
//                    "\t\"recvTime\": \"0004f875a451fa5c\",\n" +
//                    "\t\"sysResv\": \"1234567887654321NTuMTuMNNuTMuMNTMTNuNNTMuNMMTTuMuNTMac1691c6\",\n" +
//                    "\t\"xxfhm\": \"P001\",\n" +
//                    "\t\"xxlxm\": \"SK01\",\n" +
//                    "\t\"xxnr\": {\n" +
//                    "\t\t\"accountattr\": \"1000000000000000\",\n" +
//                    "\t\t\"cardid\": \"Z598972C6\",\n" +
//                    "\t\t\"cardtype\": \"1\",\n" +
//                    "\t\t\"curaccount\": \"0.00\",\n" +
//                    "\t\t\"curaccountamt\": \"0.00\",\n" +
//                    "\t\t\"dffj\": \"0.00\",\n" +
//                    "\t\t\"dffjcash\": \"0.00\",\n" +
//                    "\t\t\"dffjhisaccount\": \"0.00\",\n" +
//                    "\t\t\"hisaccount\": \"0.00\",\n" +
//                    "\t\t\"hisaccountamt\": \"0.00\",\n" +
//                    "\t\t\"personname\": \"测试卡\",\n" +
//                    "\t\t\"tc\": \"0.00\",\n" +
//                    "\t\t\"tccash\": \"0.00\",\n" +
//                    "\t\t\"tchisaccount\": \"0.00\",\n" +
//                    "\t\t\"translsh\": \"2001310002514635\",\n" +
//                    "\t\t\"zfcash\": \"15.00\"\n" +
//                    "\t},\n" +
//                    "\t\"xzqhdm\": \"310000\",\n" +
//                    "\t\"zdsbsbm\": \"\"\n" +
//                    "}");
//        }


        SettleResultDTO settleResultDTO = new SettleResultDTO();
        Map<String,Object> httpResultMap = post(url, message);
        System.out.println("医保调用Output:   " + httpResultMap);
        int status = MedicineUtils.parseInt(httpResultMap.get("status"));
        if(status == ServiceCode.CODE_OK){
            String result = StrUtil.null2Str(httpResultMap.get("result"));
            Map<String,Object> tempMap = JackJsonUtil.parse(result,Map.class);
            String data =  StrUtil.null2Str(tempMap.get("data"));
            MessageBody messageBody = null;
            try {
                messageBody = JackJsonUtil.parse(data, MessageBody.class);
            } catch (Exception e){
                settleResultDTO.setCode(ServiceCode.CODE_ERROR);
                settleResultDTO.setMsg(String.format("JSON解析错误，医保返回值:%s", data));
                return settleResultDTO;
            }
            String xxfhm = messageBody.getXxfhm();
            String fhxx =  xxfhm + ": " + messageBody.getFhxx();
            //返回正常
            if(!ServiceCode.P001.equals(xxfhm)){
                settleResultDTO.setCode(ServiceCode.CODE_ERROR);
                settleResultDTO.setMsg(fhxx);
                settleResultDTO.setData(data);
            }else{
                settleResultDTO.setCode(ServiceCode.CODE_OK);
                settleResultDTO.setData(data);
            }
            if(MessageTypeEnum.S000==messageBody.getXxlxm()&&"".equals(xxfhm)){//S000读卡成功 返回消息码 是空
                settleResultDTO.setCode(ServiceCode.CODE_OK);
                settleResultDTO.setData(data);
            }

        }else{
            String msg = StrUtil.null2Str(httpResultMap.get("msg"));
            settleResultDTO.setCode(ServiceCode.CODE_ERROR);
            settleResultDTO.setMsg("请开启客户端医保服务!");
        }
        return settleResultDTO;
    }


    /**
     * 传统的form表单参数式请求
     * @author walker
     * @param url
     * @param params
     * @return
     */
    public static  Map<String,Object> post(String url,String params){
        HttpClient client= HttpClients.createDefault();
        HttpResponse response=null;
        HttpEntity entity = null;
        String responseContent=null;
        Map<String,Object> res = new HashMap();
/*		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
        res.put("status", 0);
        res.put("msg", "");
        res.put("result", null);
        res.put("time", 0);
        Map<String,Object> map= new HashMap<String,Object>() ;
        map.put("req",params);
        try {
            HttpPost post=new HttpPost(url);
            List<NameValuePair> list=new ArrayList<NameValuePair>();
            for(Map.Entry<String,Object> entry:map.entrySet()){
                list.add(new BasicNameValuePair(entry.getKey(),entry.getValue().toString()));
            }
            post.setEntity(new UrlEncodedFormEntity(list,charset));
            //post.setHeader("Content-Type","text/html");
            response=client.execute(post);
            entity = response.getEntity(); //获取响应实体
            int status = response.getStatusLine().getStatusCode();
            if (status== HttpStatus.SC_OK
                    &&null != entity) {
                responseContent = EntityUtils.toString(entity, charset);
                EntityUtils.consume(entity);
                res.put("result", responseContent);
            }else{
                res.put("msg", responseContent);
            }
            res.put("status", status);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            response = null;
            entity = null;
            client = null;
            System.gc();
        }
        return res;
    }
}
