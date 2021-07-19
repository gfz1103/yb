package com.buit.his.shyb.source.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {

    private static final int TIMEOUT_DEFAULT = 300000;//默认超时时间5分钟
    public static Map postJSON(String url, String params){
        return postJSON(url, params, null, "UTF-8");
    }


    /**
     * 用post方式提交JOSN数据
     * @param url
     * @param params
     * @param header
     * @param charset
     * @return
     * @author Zhang Xiangxin
     */
    public static Map postJSON(String url,String params,Map<String,String> header,String charset){

        return postJSON(url, params, header, charset, TIMEOUT_DEFAULT);
    }

    /**
     * 用post方式提交JOSN数据
     * @param url
     * @param params
     * @param header
     * @param charset
     * @param timeout
     * @return
     */
    public static Map postJSON(String url,String params,Map<String,String> header,String charset, int timeout){
        HttpClient httpclient = new DefaultHttpClient();

        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeout).setConnectionRequestTimeout(timeout).setSocketTimeout(timeout).build();
        HttpPost method = new HttpPost(url);
        method.setConfig(requestConfig);

        Map res = new HashMap();
        res.put("status", 0);
        res.put("msg", "");
        res.put("result", null);
        res.put("time", 0);
        String result ="";
        try {
            StringEntity entity = new StringEntity(params, Charset.forName(charset));
            entity.setContentEncoding(charset);
            entity.setContentType("application/json; charset=" + charset + ";");//发送json数据需要设置contentType
            method.setEntity(entity);
            if(header !=null){
                for(Map.Entry<String,String> entry : header.entrySet() ){
                    method.setHeader(entry.getKey(),entry.getValue());
                }
            }
            long startTime = System.currentTimeMillis();
            HttpResponse rep = httpclient.execute(method);
            long endTime = System.currentTimeMillis();
            res.put("time", endTime-startTime);
            int status = rep.getStatusLine().getStatusCode();
            String str = EntityUtils.toString(rep.getEntity(),"UTF-8");// 返回json格式：
            if( status== HttpStatus.SC_OK){
                res.put("result", str);
            }else{
                res.put("msg", str);
            }
            res.put("status", status);
        }catch(Exception e) {
            res.put("status", -1);
            res.put("msg", e.getMessage());
        }

//        Map<String,Object> map= new HashMap<>() ;
//        map.put("req",params);
//        String a = HttpsClientq.post(url,map);
//        res.put("result", a);


    	/*List<NameValuePair> formparams = new ArrayList<NameValuePair>();
    	formparams.add(new BasicNameValuePair("req", params));
    	UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
    	HttpPost httppost = new HttpPost(url);
        entity.setContentType("application/json; charset=" + charset + ";");//发送json数据需要设置contentType
        httppost.setEntity(entity);

    	CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse req = null;
        try {
            req = httpclient.execute(httppost);
            HttpEntity entity1 = req.getEntity();
            int status = req.getStatusLine().getStatusCode();
            String str = EntityUtils.toString(req.getEntity(),"UTF-8");// 返回json格式：
            if( status== HttpStatus.SC_OK){
                res.put("result", str);
            }else{
                res.put("msg", str);
            }
            res.put("status", status);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(req!=null){
                try {
                    req.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }*/

//        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeout).setConnectionRequestTimeout(timeout).setSocketTimeout(timeout).build();
//        HttpPost method = new HttpPost(url);
//        method.setConfig(requestConfig);


  //      try {
//            //StringEntity entity = new StringEntity(params, Charset.forName(charset));
//            entity.setContentEncoding(charset);
//            entity.setContentType("application/json; charset=" + charset + ";");//发送json数据需要设置contentType
//            method.setEntity(entity);
//            if(header !=null){
//                for(Map.Entry<String,String> entry : header.entrySet() ){
//                    method.setHeader(entry.getKey(),entry.getValue());
//                }
//            }
//            long startTime = System.currentTimeMillis();
//            HttpResponse rep = httpclient.execute(method);
//            long endTime = System.currentTimeMillis();
//            res.put("time", endTime-startTime);
//            int status = rep.getStatusLine().getStatusCode();
//            String str = EntityUtils.toString(rep.getEntity(),"UTF-8");// 返回json格式：
//            if( status== HttpStatus.SC_OK){
//                res.put("result", str);
//            }else{
//                res.put("msg", str);
//            }
//            res.put("status", status);
//        }catch(Exception e){
//            res.put("status", -1);
//            res.put("msg", e.getMessage());
//        }
        return res;
    }
}
