package com.buit.his.shyb.source.util;

import com.buit.ShybRun;
import com.buit.utill.SpringContextUtil;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class aaa {
    public static void main(String[] args) {
        Map<String,Object> map= new HashMap<>() ;
        String req="{\"jysj\":\"2020-10-28 16:34:25\",\"xxlxm\":\"SM01\",\"xxfhm\":\"\",\"fhxx\":\"\",\"bbh\":\"\",\"msgid\":\"3323398600020201028000000135\",\"xzqhdm\":\"\",\"jgdm\":\"33233986000\",\"czybm\":8888,\"czyxm\":\"cs\",\"xxnr\":{\"cardtype\":\"1\",\"carddata\":\"\"},\"jyqd\":\"10\",\"jyyzm\":\"\",\"zdjbhs\":\"\"}";


        map.put("req",req);
       Map a = HttpsClientq.post("http://172.22.145.198:9500/yb/sendMes",req);
       System.out.println(a);
    }
}
