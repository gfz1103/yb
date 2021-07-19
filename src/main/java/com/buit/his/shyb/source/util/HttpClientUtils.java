package com.buit.his.shyb.source.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

public class HttpClientUtils {
	public static final String SUCCESS = "SUCCESS";
	public static final String FAIL = "FAIL";
	private static final int TIMEOUT_DEFAULT = 300000;//默认超时时间5分钟

	protected static Logger getLog(){
		Logger log = LoggerFactory.getLogger(HttpClientUtil.class);
		return log;
	}

	 public static String doPost(String url, Map<String,String> map){
	 	return doPost(url, map,"UTF-8");
	 }

	 public static String doPost(String url, Map<String,String> map, String charset){
		 String result = null;
		 HttpClient client = new HttpClient();
		 PostMethod method = new PostMethod(url);

		 if(map!=null){
			 for(Map.Entry<String, String> entry : map.entrySet()){
				 method.addParameter(entry.getKey(),entry.getValue());
			 }
		 }
		 method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, charset);
		 client.getHttpConnectionManager().getParams().setConnectionTimeout(60000);
		 client.getHttpConnectionManager().getParams().setSoTimeout(60000);
		 try {
	  		client.executeMethod(method);
	  		String body = method.getResponseBodyAsString();

//	  		BufferedReader reader=new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(),"ISO-8859-1" ));
//			String tmp=null;
//			String str ="";
//			while((tmp=reader.readLine())!=null){
//				str+=tmp+"\r\n";
//			}
			result =   new String(body.getBytes("UTF-8"), charset);
	  	} catch (HttpException e) {
	  		e.printStackTrace();
	  		getLog().error(e.getMessage());
	  	} catch (IOException e) {
	  		e.printStackTrace();
	  		getLog().error(e.getMessage());
	  	}finally{
		  	method.releaseConnection();
		}
		 return result;
	 }

	 public static String doGet(String url, Map<String,String> map){
		 return doGet(url, map,"UTF-8");
	 }

	 public static String doGet(String url, Map<String,String> map, String charset){
		 String result = null;
		 HttpClient client = new HttpClient();

		 if(map!=null){
			 String s = (url.indexOf("?") > -1 ? "&"  : "?") +"1=1";
			 for(Map.Entry<String, String> entry : map.entrySet()){
				s += "&"+ entry.getKey()+"="+entry.getValue();
			 }
			 url  += s;
		 }

		 GetMethod method = new GetMethod(url);
		 try {
	  		client.executeMethod(method);
	  		String body = method.getResponseBodyAsString();
			result =   new String(body.getBytes("ISO-8859-1"), charset);
	  	} catch (HttpException e) {
	  		e.printStackTrace();
	  		getLog().error(e.getMessage());
	  	} catch (IOException e) {
	  		e.printStackTrace();
	  		getLog().error(e.getMessage());
	  	}finally{
	  		method.releaseConnection();
	  	}
		return result;
	 }

	 /**
	  * 判断一个请求是否成功
	  * @author muzx
	  * @date 2018-01-17
	  * @param url: HTTP-url
	  * @return
	  */
	 public static String doPostIsSucess(String url){
//		 HttpClient client = new HttpClient();
//		 PostMethod method = new PostMethod(url);
//		 client.getHttpConnectionManager().getParams().setConnectionTimeout(60000);
//		 client.getHttpConnectionManager().getParams().setSoTimeout(60000);
//
//		 try {
//	  		client.executeMethod(method);
//	  		StatusLine sl = method.getStatusLine();
//	  		if(sl.getStatusCode() == 200){
//	  			return SUCCESS;
//	  		}
//	  	} catch (HttpException e) {
//	  		e.printStackTrace();
//	  		getLog().error(e.getMessage());
//	  	} catch (IOException e) {
//	  		e.printStackTrace();
//	  		getLog().error(e.getMessage());
//	  	}finally{
//		  	method.releaseConnection();
//		}
//		 return FAIL;
		 if (url==null) {
	         return FAIL;
	     }
		 try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			if(HttpURLConnection.HTTP_OK == connection.getResponseCode()){
				return SUCCESS;
			}
		 } catch (MalformedURLException e) {
			e.printStackTrace();
			getLog().error(e.getMessage());
		 } catch (IOException e) {
			e.printStackTrace();
			getLog().error(e.getMessage());
		 }
		 return FAIL;
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
		public static String postJSON(String url, String params, Map<String,String> header, String charset, int timeout){
			Map res = new HashMap();
			String str = "";
			res.put("status", 0);
			res.put("msg", "");
			res.put("result", null);
			res.put("time", 0);
			String result ="";
			try {
				SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {
				      @Override
				      public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
				         return true;
				      }
				   }).build();


				 CloseableHttpClient client = HttpClients.custom().setSSLContext(sslContext).
				         setSSLHostnameVerifier(new NoopHostnameVerifier()).build();


				RequestConfig requestConfig = RequestConfig.custom()
						.setConnectTimeout(TIMEOUT_DEFAULT)
						.setConnectionRequestTimeout(TIMEOUT_DEFAULT).setSocketTimeout(TIMEOUT_DEFAULT)
						.build();


				HttpPost method = new HttpPost(url);
				method.setConfig(requestConfig);


				StringEntity entity = new StringEntity(params, Charset.forName(charset));
				entity.setContentEncoding(charset);
				entity.setContentType("application/json; charset=" + charset + ";");//发送json数据需要设置contentType
				method.setEntity(entity);
				if(header !=null){
					 for(Map.Entry<String, String> entry : header.entrySet() ){
						 method.setHeader(entry.getKey(),entry.getValue());
					 }
				}
				long startTime = System.currentTimeMillis();
				HttpResponse rep = client.execute(method);
				long endTime = System.currentTimeMillis();
				res.put("time", endTime-startTime);
				int status = rep.getStatusLine().getStatusCode();
				str = EntityUtils.toString(rep.getEntity());// 返回json格式：
				if( status== HttpStatus.SC_OK){
					res.put("result", str);
	            }else{
	            	res.put("msg", str);
	            }
				res.put("status", status);
			}catch(Exception e){
				res.put("status", -1);
				res.put("msg", e.getMessage());
			}
			return str;
		}

		 private static void trustAllHttpsCertificates() throws Exception {
		        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
		        javax.net.ssl.TrustManager tm = new miTM();
		        trustAllCerts[0] = tm;
		        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext
		                .getInstance("SSL");
		        sc.init(null, trustAllCerts, null);
		        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc
		                .getSocketFactory());
		    }

		 static class miTM implements javax.net.ssl.TrustManager,
         javax.net.ssl.X509TrustManager {
     public java.security.cert.X509Certificate[] getAcceptedIssuers() {
         return null;
     }

     public boolean isServerTrusted(
             java.security.cert.X509Certificate[] certs) {
         return true;
     }

     public boolean isClientTrusted(
             java.security.cert.X509Certificate[] certs) {
         return true;
     }

     public void checkServerTrusted(
             java.security.cert.X509Certificate[] certs, String authType)
             throws java.security.cert.CertificateException {
         return;
     }

     public void checkClientTrusted(
             java.security.cert.X509Certificate[] certs, String authType)
             throws java.security.cert.CertificateException {
         return;  
     }  
 }  
	/////////////////////////////
		 
		
		 public static String doPostForm(String httpUrl, Map param) {
		        HttpURLConnection connection = null;
		        InputStream is = null;
		        OutputStream os = null;
		        BufferedReader br = null;
		        String result = null;
		        try {
		            URL url = new URL(httpUrl);
		            // 通过远程url连接对象打开连接
		            connection = (HttpURLConnection) url.openConnection();
		            // 设置连接请求方式
		            connection.setRequestMethod("POST");
		            // 设置连接主机服务器超时时间：15000毫秒
		            connection.setConnectTimeout(15000);
		            // 设置读取主机服务器返回数据超时时间：60000毫秒
		            connection.setReadTimeout(60000);
		            // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
		            connection.setDoOutput(true);
		            // 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
		            connection.setDoInput(true);
		            // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
		            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		            // 设置鉴权信息：Authorization: Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0
		            //connection.setRequestProperty("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
		            // 通过连接对象获取一个输出流
		            os = connection.getOutputStream();
		            // 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的(form表单形式的参数实质也是key,value值的拼接，类似于get请求参数的拼接)
		            os.write(createLinkString(param).getBytes());
		            // 通过连接对象获取一个输入流，向远程读取
		            if (connection.getResponseCode() == 200) {
		                is = connection.getInputStream();
		                // 对输入流对象进行包装:charset根据工作项目组的要求来设置
		                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		                StringBuffer sbf = new StringBuffer();
		                String temp = null;
		                // 循环遍历一行一行读取数据
		                while ((temp = br.readLine()) != null) {
		                    sbf.append(temp);
		                    sbf.append("\r\n");
		                }
		                result = sbf.toString();
		            }
		        } catch (MalformedURLException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        } finally {
		            // 关闭资源
		            if (null != br) {
		                try {
		                    br.close();
		                } catch (IOException e) {
		                    e.printStackTrace();
		                }
		            }
		            if (null != os) {
		                try {
		                    os.close();
		                } catch (IOException e) {
		                    e.printStackTrace();
		                }
		            }
		            if (null != is) {
		                try {
		                    is.close();
		                } catch (IOException e) {
		                    e.printStackTrace();
		                }
		            }
		            // 断开与远程地址url的连接
		            connection.disconnect();
		        }
		        return result;
		    }
		 
		 public static String createLinkString(Map<String, String> params) {
		        List<String> keys = new ArrayList<String>(params.keySet());
		        Collections.sort(keys);
		        StringBuilder prestr = new StringBuilder();
		        for (int i = 0; i < keys.size(); i++) {
		            String key = keys.get(i);
		            String value = params.get(key);
		            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
		                prestr.append(key).append("=").append(value);
		            } else {
		                prestr.append(key).append("=").append(value).append("&");
		            }
		        }
		        return prestr.toString();
		    }

}
