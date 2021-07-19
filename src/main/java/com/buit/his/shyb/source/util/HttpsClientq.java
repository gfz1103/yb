package com.buit.his.shyb.source.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

/**
 * https client
 * @author
 *
 */
public class HttpsClientq {

	
	private static String charset="UTF-8";
	private static String charsets="gb2312";

	/**
	 * 传统的form表单参数式请求
	 * @author walker
	 * @param url
	 * @param params
	 * @return
	 */
	public static  Map<String,Object> post(String url,String params){

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
		String result ="";
		Map<String,Object> map= new HashMap<String,Object>() ;
		map.put("req",params);
		HttpClient client=HttpClients.createDefault();
        HttpResponse response=null;
        HttpEntity entity = null;
        String responseContent=null;
        try {
            HttpPost post=new HttpPost(url);
            List<NameValuePair> list=new ArrayList<NameValuePair>();
            for(Entry<String,Object> entry:map.entrySet()){
            	list.add(new BasicNameValuePair(entry.getKey(),entry.getValue().toString()));
            }
            post.setEntity(new UrlEncodedFormEntity(list,charset));
			//post.setHeader("Content-Type","text/html");
			response=client.execute(post);
			entity = response.getEntity(); //获取响应实体
			int status = response.getStatusLine().getStatusCode();
			if (status==HttpStatus.SC_OK
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
	
	/**
	 * post方法发送JSON字符串
	 * @author walker
	 * @param url
	 * @param json
	 * @return
	 */
//	public static String post(String url,String json){
//		HttpClient client=getHttpClient();
//
//        HttpResponse response=null;
//        HttpEntity entity = null;
//        String responseContent=null;
//        try {
//        	HttpPost post=new HttpPost(url);
//        	post.setHeader("Content-Type","application/json");
//            post.setEntity(new StringEntity(json,charset));
//
//			response=client.execute(post);
//			entity = response.getEntity(); //获取响应实体
//			if (response.getStatusLine().getStatusCode()==HttpStatus.SC_OK
//					&&null != entity) {
//				responseContent = EntityUtils.toString(entity, charset);
//				EntityUtils.consume(entity);
//			}
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally{
//			response = null;
//			entity = null;
//			client = null;
//			System.gc();
//		}
//        return responseContent;
//	}
	
	/**
	 * post方法发送JSON字符串
	 * @author walker
	 * @param url
	 * @param json
	 * @return
	 */
	public static String apachePostXml(String url,String xml){
		CloseableHttpClient client=getHttpClient();
		CloseableHttpResponse response=null;
        HttpEntity entity = null;
        String responseContent=null;
        try {
        	HttpPost post=new HttpPost(url);
        	post.setHeader("Content-Type","text/xml");
            post.setEntity(new StringEntity(xml,charset));
            
			response=client.execute(post);
			entity = response.getEntity(); //获取响应实体  
			if (response.getStatusLine().getStatusCode()==HttpStatus.SC_OK
					&&null != entity) {
				responseContent = EntityUtils.toString(entity, charset);
				EntityUtils.consume(entity); 
			}
			System.out.println("【http】" + url + " Status："
					+ response.getStatusLine().getStatusCode() + " \r发送：" + xml
					+ " 接收：" + responseContent);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("【http】" + url + "异常提示：" + e.getMessage()
					+ " \r发送：" + xml+e);
		} finally{
			response = null;
			entity = null;
			client = null;
			System.gc();
		}		
        return responseContent;
	}
	

    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

	
	public static CloseableHttpClient getHttpClient(){
		CloseableHttpClient httpClient=HttpClients.createDefault();
        return httpClient;
	}
	
	public static String apacheSSLXml(String url,String xml){
        SSLContext sslcontext = SSLContexts.createDefault();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,null,null,SSLConnectionSocketFactory.getDefaultHostnameVerifier());
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf).build();
        HttpPost httppost = new HttpPost(url);
        CloseableHttpResponse response=null;
        String responseContent=null;
        try {
        	httppost.setHeader("Content-Type","text/xml");
        	httppost.setEntity(new StringEntity(xml,Consts.UTF_8));
            response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            if (response.getStatusLine().getStatusCode()==HttpStatus.SC_OK
					&&null != entity) {
				responseContent = EntityUtils.toString(entity, charset);
				EntityUtils.consume(entity); 
			}
            System.out.println("【https】" + url + " Status："
					+ response.getStatusLine().getStatusCode() + " \r发送：" + xml
					+ " 接收：" + responseContent);
        } catch (Exception e) {
        	e.printStackTrace();
        	System.out.println("【https】" + url + "异常提示：" + e.getMessage()
					+ " \r发送：" + xml+e);
		} finally {
            try {
				response.close();
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
            System.gc();
        }
		return responseContent;
	}
	
	/**
	 * 使用apache实现的HTTP连接方法类
	 * @param  apacheHttp
	 * @author walker
	 * @return
	 */
	public static String apacheNormal(ApacheHttp apacheHttp){
		CloseableHttpClient client=HttpClients.createDefault();
		try {
			if(apacheHttp==null){
				throw new IllegalArgumentException("Apache http object can be null!");
			}
			CloseableHttpResponse response=null;
			if(apacheHttp.post!=null){
				response=client.execute(apacheHttp.post);
			}else if(apacheHttp.get!=null){
				response=client.execute(apacheHttp.get);
			}
			System.out.println("Apache http --> URL：" + apacheHttp.getUrl()
					+ "\n DATA-FORM："
					/*+ EntityUtils.toString(apacheHttp.getEntity(),Consts.UTF_8)*/);
			return EntityUtils.toString(response.getEntity(),Consts.UTF_8);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			//LogUtil.error("Apache http ClientProtocolException["+e.getMessage()+"]", e);
		} catch (IOException e) {
			e.printStackTrace();
			//LogUtil.error("Apache http IOException["+e.getMessage()+"]", e);
		} finally{
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static class ApacheHttp{
		
		private HttpPost post;
		
		private HttpGet get;
		
		private String url;
		
		private HttpEntity entity;
		
		private ApacheHttp(){}
		
		public static ApacheHttp instance(){
			return new ApacheHttp();
		}
		
		public ApacheHttp url(String url){
			return url(url,HttpMethod.POST);
		}

		public ApacheHttp url(String url,HttpMethod method){
			if(StringUtils.isBlank(url)){
				throw new IllegalArgumentException("Host address can not be null!");
			}
			if(method==null){
				throw new IllegalArgumentException("Request Method can not be null!");
			}
			this.url=url;
			if(method.compareTo(HttpMethod.POST)==BigInteger.ZERO.intValue()){
				this.post=createPost(url);
			}else if(method.compareTo(HttpMethod.GET)==BigInteger.ZERO.intValue()){
				this.get=createGet(url);
			}
			return this;
		}
		
		public void form(Map<String,Object> map){
			form(map,false);
		}
		
		public void form(Map<String,Object> map,boolean multipart){
			if(post!=null){
				this.doPost(map, multipart);
				return ;
			}else if(get!=null){
				this.doGet(map);
				return ;
			}
			throw new IllegalArgumentException("Unknow apache http request method!");
			
		}
		
		public String getUrl(){
			return this.url;
		}
		
		private void doGet(Map<String,Object> map){
			StringBuffer sb=new StringBuffer(getUrl().toString());
			for(Entry<String,Object> entry:map.entrySet()){
				sb.append(entry.getKey()+"="+entry.getValue()+"&");
			}
			get=createGet(sb.toString());
		}
		
		private void doPost(Map<String,Object> map,boolean multipart){
//			List<NameValuePair> formparams=new ArrayList<NameValuePair>();
//			if(multipart){//媒体上传
//				MultipartEntityBuilder builder=MultipartEntityBuilder.create();
//				for(Entry<String,Object> entry:map.entrySet()){
//					if(entry.getValue() instanceof File[]){    //多个文件
//						File[] files=(File[])entry.getValue();
//						for(File file:files){
//							FileBody bin = new FileBody(file);
//							builder.addPart(entry.getKey(),bin);
//						}
//					}else if(entry.getValue() instanceof File){//单个文件
//						FileBody bin = new FileBody((File)entry.getValue());
//						builder.addPart(entry.getKey(),bin);
//					}else{                                     //添加字符参数
//						StringBody stringEntity=new StringBody(entry.getValue().toString(),
//								ContentType.create("text/plain",Consts.UTF_8));
//						builder.addPart(entry.getKey(),stringEntity);
//					}
//				}
//				entity=builder.build();
//			}else{
//				for(Entry<String,Object> entry:map.entrySet()){
//					formparams.add(new BasicNameValuePair(entry.getKey(),entry.getValue().toString()));
//				}
//				entity =new UrlEncodedFormEntity(formparams, Consts.UTF_8); 
//			}
//			post.setEntity(entity);
		}
		
		private static HttpPost createPost(String url){
			return new HttpPost(url);
		}
		
		private static HttpGet createGet(String url){
			return new HttpGet(url);
		}

		public HttpEntity getEntity() {
			return entity;
		}
	}
	
	/**
	 * http请求方式枚举
	 * @author com.ky Coperations
	 * @version 1.0,2018-3-27 上午10:54:15 
	 * @since JDK 1.7
	 */
	public enum HttpMethod{
		POST,GET,PUT;
	}
}
