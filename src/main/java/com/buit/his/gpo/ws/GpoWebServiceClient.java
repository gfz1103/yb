package com.buit.his.gpo.ws;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.buit.commons.BaseException;
import com.buit.constans.ErrorCode;
import com.buit.constans.TableName;
import com.buit.his.gpo.dao.GpoWsjlDao;
import com.buit.his.gpo.model.GpoWsjl;
//import com.buit.his.gpo.ws.wsdl.DispatcherService;
//import com.buit.his.gpo.ws.wsdl.DispatcherServiceProxy;
import com.buit.his.gpo.ws.response.DdYY009RespMain;
import com.buit.his.gpo.ws.response.DdYY009RespStruct;
import com.buit.his.gpo.ws.wsdl.DispatcherService;
import com.buit.his.gpo.ws.wsdl.DispatcherServiceProxy;
import com.buit.his.gpo.ws.xml.XmlData;
import com.buit.his.gpo.ws.xml.GpoXmlUtil;
import com.buit.utill.RedisFactory;
import com.buit.utill.TimestampUtil;
//import org.apache.cxf.endpoint.Client;
import org.apache.axis.client.Call;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author sunjx
 * @Date 2020-10-30 16:01
 * @Description GPO webservice 客户端
 **/
public class GpoWebServiceClient {

    private static final Logger log = LoggerFactory.getLogger(GpoWebServiceClient.class);

//    private Client client;
    private RedisFactory redisFactory;
    private GpoWsjlDao gpoWsjlDao;
    private String jgbm;
    private String user;
    private String pwd;
    private String version;
    private String wsdl;

    public GpoWebServiceClient(RedisFactory redisFactory, GpoWsjlDao gpoWsjlDao, String jgbm, String user, String pwd, String version,String wsdl) {
        this.redisFactory = redisFactory;
        this.gpoWsjlDao = gpoWsjlDao;
        this.jgbm = jgbm;
        this.user = user;
        this.pwd = pwd;
        this.version = version;
        this.wsdl = wsdl;
    }

//    public GpoWebServiceClient(Client client, RedisFactory redisFactory, GpoWsjlDao gpoWsjlDao, String jgbm, String user, String pwd, String version) {
//        this.client = client;
//        this.redisFactory = redisFactory;
//        this.gpoWsjlDao = gpoWsjlDao;
//        this.jgbm = jgbm;
//        this.user = user;
//        this.pwd = pwd;
//        this.version = version;
//    }

    /**
     * 处理了返回码, 如果非 00000 返回码, 抛异常提醒
     * @param request
     * @param xxlx 消息类型码
     * @see com.buit.his.gpo.ws.GpoConsts.Method
     * @param typeReference 返回结果对象类型
     * @return
     */
    public <T> T sendRecv(XmlData request, Integer jgid, String userid, String uname, GpoConsts.YKLX yklx, GpoConsts.Method xxlx, TypeReference<T> typeReference) {
        //gpo webservice 未配置
//        if(ObjectUtil.hasEmpty(client, redisFactory, gpoWsjlDao, jgbm, user, pwd, version)){
//            log.error("gpo webservice 缺少配置 => {},{}", xxlx.getCode(), xxlx.getDesc());
//            throw BaseException.create(ErrorCode.ERROR_GPO_WS_0006);
//        }

        if(ObjectUtil.hasEmpty( redisFactory, gpoWsjlDao, jgbm, user, pwd, version)){
            log.error("gpo webservice 缺少配置 => {},{}", xxlx.getCode(), xxlx.getDesc());
            throw BaseException.create(ErrorCode.ERROR_GPO_WS_0006);
        }

        GpoWsjl wsjl = new GpoWsjl();
        wsjl.setXh(redisFactory.getTableKey(TableName.DB_NAME, TableName.TB_NAME_GPO_WSJL));
        wsjl.setJgid(jgid);
        wsjl.setLx(null != yklx ? yklx.getKey() : 1);
        wsjl.setDm(xxlx.getCode());
        wsjl.setMc(xxlx.getDesc());
        wsjl.setCuser(userid);
        wsjl.setCuname(uname);

        long begin = System.currentTimeMillis();
        wsjl.setCtime(TimestampUtil.now());
        log.debug("调用GPO webservice [{},{}]参数 => {}", xxlx.getCode(), xxlx.getDesc(), JSONUtil.toJsonStr(request));
        //将请求转换为xml 字符串
        String xmldata = GpoXmlUtil.toXml(request, "XMLDATA");
        //格式化 xml
        String formatXml = GpoXmlUtil.format(xmldata).replaceFirst(" standalone=\"no\"", "");

        String recv="";

        //签名
        String sign = sign(xmldata, xxlx);
        System.out.println(sign);

        try {
            wsjl.setCs(xmldata);

            System.out.println("req: "+xmldata);

            log.debug("调用GPO webservice [{},{}]开始, sign => {}, formatXml => \n{}", xxlx.getCode(), xxlx.getDesc(), sign, formatXml);
             recv  = sendMessage(user, pwd, jgbm, version, xxlx.getCode(), sign, xmldata,wsdl);

            log.debug("调用GPO webservice [{},{}]返回, 耗时{}毫秒 => {}", xxlx.getCode(), xxlx.getDesc(), (System.currentTimeMillis() - begin), JSONUtil.toJsonStr(recv));
            wsjl.setFh(recv);
            wsjl.setEtime(TimestampUtil.now());
           wsjl.setHs((int) (wsjl.getEtime().getTime() - wsjl.getCtime().getTime()));
            gpoWsjlDao.insert(wsjl);
        } catch (Exception e) {
            log.error("调用GPO webservice [{},{}]异常, 耗时{}毫秒, formatXml => {}", xxlx.getCode(), xxlx.getDesc(), (System.currentTimeMillis() - begin), formatXml, e);
            throw BaseException.create(ErrorCode.ERROR_GPO_WS_0001);
        }

        if(!StrUtil.isBlank(recv)){
            T t = null;
            try {
                //将结果xml转换为javabean
                t = GpoXmlUtil.toObj(recv, typeReference);
            } catch (Exception e) {
                log.error("调用GPO webservice [{},{}]返回数据转换javabean 异常,请求全文 => {}, 响应全文 => {}",
                        xxlx.getCode(),
                        xxlx.getDesc(),
                        formatXml,
                        GpoXmlUtil.format(recv));
                throw BaseException.create(ErrorCode.ERROR_GPO_WS_0003);
            }

            //处理结果判断
            if(t instanceof XmlData){
                XmlData xmlData = (XmlData) t;
                if(!xmlData.getHEAD().getZTCLJG().equals(GpoZidian.ZTCLJG.C_00000.getCode())){
                    log.error("调用GPO webservice [{},{}]返回错误码 => {}, 错误信息 => {}, 请求全文 => {}, 响应全文 => {}",
                            xxlx.getCode(),
                            xxlx.getDesc(),
                            xmlData.getHEAD().getZTCLJG(),
                            xmlData.getHEAD().getCWXX(),
                            formatXml,
                            GpoXmlUtil.format(recv));
                    String msg = xmlData.getHEAD().getCWXX();
                    if(xxlx==GpoConsts.Method.YY009){
                        XmlData<Object, Object> resp = (XmlData<Object, Object>)xmlData;
                        String appendMsg = resp.getDETAIL().stream().flatMap(s->{
                            if(s.getSTRUCT() instanceof JSONArray){
                                List<DdYY009RespStruct> list = JSONUtil.toList((JSONArray) s.getSTRUCT(),DdYY009RespStruct.class);
                                return list.stream();
                            }else{
                                DdYY009RespStruct ddYY009RespStruct = JSONUtil.toBean((JSONObject) s.getSTRUCT(), DdYY009RespStruct.class);
                                return Arrays.asList(ddYY009RespStruct).stream();
                            }
                        }).filter(s->!s.getCLJG().equals(GpoZidian.ZTCLJG.C_00000.getCode())).map(s->s.getCLQKMS()).reduce((s,v)->s.concat("<br/>").concat(v)).get();
                        throw BaseException.create(ErrorCode.ERROR_GPO_WS_0004, msg+"<br/>"+appendMsg);
                    }
                    throw BaseException.create(ErrorCode.ERROR_GPO_WS_0004, msg);
                }

                return t;
            }else{
                log.error("调用GPO webservice [{},{}]返回数据转换javabean 类型错误, 请求全文 => {}, 响应全文 => {}",
                        xxlx.getCode(),
                        xxlx.getDesc(),
                        formatXml,
                        GpoXmlUtil.format(recv));
                throw BaseException.create(ErrorCode.ERROR_GPO_WS_0005);
            }
        }else{
            return null;
        }
    }

    private static String sign(String xml, GpoConsts.Method xxlx){
        try {
            return getMessageDigest(xml, "SHA-1");
        } catch (Exception e) {
            log.error("调用GPO webservice [{},{}]异常, 签名计算错误,参数 => {}", xxlx.getCode(), xxlx.getDesc(), xml, e);
            throw BaseException.create(ErrorCode.ERROR_GPO_WS_0002);
        }
    }

    // 字节数组转换为 16 进制的字符串
    private static String byteArrayToHex(byte[] byteArray) {
        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F' };
        char[] resultCharArray = new char[byteArray.length * 2];
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }
        return new String(resultCharArray);
    }

    //计算消息摘要
    private static String getMessageDigest(String str, String encName) throws NoSuchAlgorithmException {
        byte[] digest = null;
        if (StrUtil.isBlank(encName)) {
            encName = "SHA-1";
        }

        MessageDigest md = MessageDigest.getInstance(encName);
        md.update(str.getBytes());
        digest = md.digest();
        return byteArrayToHex(digest);
    }

   String sendMessage(String sUser, String sPwd, String sJgbm, String sVersion, String sXxlx, String sSign, String xmlData,String wsdl){
       String result="";
        try {
           String endpoint = wsdl;
           org.apache.axis.client.Service service = new org.apache.axis.client.Service();
           Call call = (Call) service.createCall();
           call.setTargetEndpointAddress(endpoint);
           String parametersName1 = "arg0";
           String parametersName2 = "arg1";
           String parametersName3 = "arg2";
           String parametersName4 = "arg3";
           String parametersName5 = "arg4";
           String parametersName6 = "arg5";
           String parametersName7 = "arg6";
           call.setOperationName(new QName("http://main.service.local.wondersgroup.com/", "sendRecv"));// 调用的方法名
           call.addParameter(parametersName1, XMLType.XSD_STRING, ParameterMode.IN);//参数名//XSD_STRING:String类型//.IN入参
           call.addParameter(parametersName2, XMLType.XSD_STRING, ParameterMode.IN);//参数名//XSD_STRING:String类型//.IN入参
           call.addParameter(parametersName3, XMLType.XSD_STRING, ParameterMode.IN);//参数名//XSD_STRING:String类型//.IN入参
           call.addParameter(parametersName4, XMLType.XSD_STRING, ParameterMode.IN);//参数名//XSD_STRING:String类型//.IN入参
           call.addParameter(parametersName5, XMLType.XSD_STRING, ParameterMode.IN);//参数名//XSD_STRING:String类型//.IN入参
           call.addParameter(parametersName6, XMLType.XSD_STRING, ParameterMode.IN);//参数名//XSD_STRING:String类型//.IN入参
           call.addParameter(parametersName7, XMLType.XSD_STRING, ParameterMode.IN);//参数名//XSD_STRING:String类型//.IN入参
           call.setReturnType(XMLType.XSD_STRING); 	// 返回值类型：String
            result = (String) call.invoke(new Object[] { sUser,sPwd, sJgbm,sVersion,sXxlx,sSign,xmlData});// 远程调用
           System.out.println("result is " + result);
       } catch (Exception e) {
           System.err.println(e.toString());
       }
        return result;
    }


}
