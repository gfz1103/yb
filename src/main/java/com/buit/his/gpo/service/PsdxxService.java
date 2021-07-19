package com.buit.his.gpo.service;

import cn.hutool.core.util.StrUtil;
import com.buit.commons.BaseException;
import com.buit.constans.ErrorCode;
import com.buit.constans.TableName;
import com.buit.his.gpo.controller.request.GpoBaseRequest;
import com.buit.his.gpo.dao.GpoPsdxxDao;
import com.buit.his.gpo.model.GpoPsdxx;
import com.buit.his.gpo.ws.GpoConsts;
import com.buit.his.gpo.ws.GpoWebService;
import com.buit.his.gpo.ws.GpoZidian;
import com.buit.his.gpo.ws.request.YypsdjcxxcbYY001ReqMain;
import com.buit.his.gpo.ws.xml.Head;
import com.buit.his.gpo.ws.xml.XmlData;
import com.buit.utill.RedisFactory;
import com.buit.utill.TimestampUtil;
import io.micrometer.core.instrument.util.StringUtils;
import org.apache.axis.client.Call;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @Author sunjx
 * @Date 2020-11-03 18:38
 * @Description 配送点信息
 **/
@Service
public class PsdxxService {

    @Autowired
    private GpoWebService gpoWebService;

    @Autowired
    private RedisFactory redisFactory;

    @Autowired
    private GpoPsdxxDao psdxxDao;

    @Transactional(rollbackFor = Exception.class)
    public void save(GpoPsdxx psdxx) {
        if (psdxx.getXh() == null) {
            if (psdxxDao.existBm(psdxx.getJgid(), null, psdxx.getPsdbm()) > 0) {
                throw BaseException.create(ErrorCode.ERROR_GPO_PSD_0001);
            }

            if (psdxxDao.existMc(psdxx.getJgid(), null, psdxx.getPsdmc()) > 0) {
                throw BaseException.create(ErrorCode.ERROR_GPO_PSD_0002);
            }

            psdxx.setXh(redisFactory.getTableKey(TableName.DB_NAME, TableName.TB_NAME_GPO_PSDXX));
            psdxx.setCtime(TimestampUtil.now());
            psdxx.setZt(GpoConsts.ZT.YX.getCode());
            psdxx.setCbzt(GpoConsts.CBZT.DCB.getCode());
            psdxx.setZfzt(GpoConsts.ZFZT.YX.getCode());
            psdxxDao.insert(psdxx);
        } else {
            GpoPsdxx updatePsdxx = psdxxDao.getById(psdxx.getXh());
            if (null == updatePsdxx) {
                throw BaseException.create(ErrorCode.ERROR_GPO_PSD_0003);
            }

            if (psdxxDao.existBm(updatePsdxx.getJgid(), updatePsdxx.getXh(), psdxx.getPsdbm()) > 0) {
                throw BaseException.create(ErrorCode.ERROR_GPO_PSD_0001);
            }

            if (psdxxDao.existMc(updatePsdxx.getJgid(), updatePsdxx.getXh(), psdxx.getPsdmc()) > 0) {
                throw BaseException.create(ErrorCode.ERROR_GPO_PSD_0002);
            }

            //已传报的不能修改
            if (updatePsdxx.getCbzt().equals(GpoConsts.CBZT.YCB.getCode())) {
                throw BaseException.create(ErrorCode.ERROR_GPO_PSD_0004);
            }

            updatePsdxx.setBzxx(psdxx.getBzxx());
            updatePsdxx.setLxdh(psdxx.getLxdh());
            updatePsdxx.setLxrxm(psdxx.getLxrxm());
            updatePsdxx.setPsdbm(psdxx.getPsdbm());
            updatePsdxx.setPsdmc(psdxx.getPsdmc());
            updatePsdxx.setPsdz(psdxx.getPsdz());
            updatePsdxx.setYzbm(psdxx.getYzbm());
            psdxxDao.update(updatePsdxx);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void disable(Integer xh, GpoBaseRequest baseRequest) {
        GpoPsdxx psdxx = psdxxDao.getById(xh);
        if (null == psdxx) {
            throw BaseException.create(ErrorCode.ERROR_GPO_PSD_0003);
        }

        //待传报的不能作废
        if (psdxx.getCbzt().equals(GpoConsts.CBZT.DCB.getCode())) {
            throw BaseException.create("ERROR_GPO_PSD_0009","待传报的不能作废");
        }

        YypsdjcxxcbYY001ReqMain main = new YypsdjcxxcbYY001ReqMain();
        main.setCZLX(GpoZidian.CZLX.ZF.getVal());
        main.setPSDBM(psdxx.getPsdbm());
        main.setPSDMC(psdxx.getPsdmc());
        main.setPSDZ(psdxx.getPsdz());
        main.setLXRXM(psdxx.getLxrxm());
        main.setLXDH(psdxx.getLxdh());
        main.setYZBM(psdxx.getYzbm());
        main.setBZXX(StrUtil.blankToDefault(psdxx.getBzxx(), ""));
        XmlData<YypsdjcxxcbYY001ReqMain, Object> request = new XmlData<>(new Head(baseRequest.getIp(), baseRequest.getMac(), null), main, null);
        //传报ws接口
        XmlData xmlData = gpoWebService.yypsdjcxxcbYY001(request, baseRequest);

        psdxx.setZfzt(GpoConsts.ZT.SC.getCode());
        psdxx.setZfrdm(baseRequest.getUserid());
        psdxx.setZfsj(TimestampUtil.now());
        psdxxDao.update(psdxx);
    }

    public static void main(String[] args) {
        try {
            String str = "<?xml version=\"1.0\" encoding=\"utf-8\"?><XMLDATA>\n" +
                    "  <HEAD>\n" +
                    "    <IP>172.22.145.153</IP>\n" +
                    "    <MAC>F875A451F7CE</MAC>\n" +
                    "    <BZXX></BZXX>\n" +
                    "  </HEAD>\n" +
                    "  <MAIN>\n" +
                    "    <CZLX>1</CZLX>\n" +
                    "    <YYBM>33233986000</YYBM>\n" +
                    "    <PSDBM>0002</PSDBM>\n" +
                    "    <DDLX>1</DDLX>\n" +
                    "<DDBH></DDBH>\n" +
                    "<YYJHDH></YYJHDH>\n" +
                    "<ZWDHRQ>20130815</ZWDHRQ>\n" +
                    "    <JLS>1</JLS>\n" +
                    "  </MAIN>\n" +
                    "  <DETAIL>\n" +
                    "    <STRUCT>\n" +
                    "      <SXH>1</SXH>\n" +
                    "      <CGLX>4</CGLX>\n" +
                    "      <SPLX>1</SPLX>\n" +
                    "      <ZXSPBM>X00028400030010</ZXSPBM>\n" +
                    "      <GGBZ>0.125g</GGBZ>\n" +
                    "      <CGJLDW>1</CGJLDW>\n" +
                    "      <CGSL>2</CGSL>\n" +
                    "      <CGDJ>12.0000</CGDJ>\n" +
                    "      <YQBM>913301053281726821</YQBM>\n" +
                    "      <DCPSBS>0</DCPSBS>\n" +
                    "      <BZSM>123</BZSM>\n" +
                    "    </STRUCT>\n" +
                    "  </DETAIL>\n" +
                    "</XMLDATA>";
            String sign = getMessageDigest(str, null);
            String endpoint = "http://172.22.140.254/ysxt-wscs/service/mainservice?wsdl";
            org.apache.axis.client.Service service = new org.apache.axis.client.Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(endpoint);
            String parametersName1 = "arg0";        // 参数名//对应的是 public String printWord(@WebParam(name = "settle_num") String settle_num);
            String parametersName2 = "arg1";        // 参数名//对应的是 public String printWord(@WebParam(name = "settle_num") String settle_num);
            String parametersName3 = "arg2";        // 参数名//对应的是 public String printWord(@WebParam(name = "settle_num") String settle_num);
            String parametersName4 = "arg3";        // 参数名//对应的是 public String printWord(@WebParam(name = "settle_num") String settle_num);
            String parametersName5 = "arg4";        // 参数名//对应的是 public String printWord(@WebParam(name = "settle_num") String settle_num);
            String parametersName6 = "arg5";        // 参数名//对应的是 public String printWord(@WebParam(name = "settle_num") String settle_num);
            String parametersName7 = "arg6";        // 参数名//对应的是 public String printWord(@WebParam(name = "settle_num") String settle_num);
//	            call.setOperationName("printWord");  		// 调用的方法名//当这种调用不到的时候,可以使用下面的,加入命名空间名
            call.setOperationName(new QName("http://main.service.local.wondersgroup.com/", "sendRecv"));// 调用的方法名
            call.addParameter(parametersName1, XMLType.XSD_STRING, ParameterMode.IN);//参数名//XSD_STRING:String类型//.IN入参
            call.addParameter(parametersName2, XMLType.XSD_STRING, ParameterMode.IN);//参数名//XSD_STRING:String类型//.IN入参
            call.addParameter(parametersName3, XMLType.XSD_STRING, ParameterMode.IN);//参数名//XSD_STRING:String类型//.IN入参
            call.addParameter(parametersName4, XMLType.XSD_STRING, ParameterMode.IN);//参数名//XSD_STRING:String类型//.IN入参
            call.addParameter(parametersName5, XMLType.XSD_STRING, ParameterMode.IN);//参数名//XSD_STRING:String类型//.IN入参
            call.addParameter(parametersName6, XMLType.XSD_STRING, ParameterMode.IN);//参数名//XSD_STRING:String类型//.IN入参
            call.addParameter(parametersName7, XMLType.XSD_STRING, ParameterMode.IN);//参数名//XSD_STRING:String类型//.IN入参
            call.setReturnType(XMLType.XSD_STRING);    // 返回值类型：String
            String message = "shtyyy001";
            String result = (String) call.invoke(new Object[]{message, "xrt6m2ez2e", "33233986000", "1.0.0.0", "YY009", sign, str});// 远程调用
            System.out.println("result is " + result);
        } catch (Exception e) {
            System.err.println(e.toString());
        }

    }

    private static String byteArrayToHex(byte[] byteArray) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        char[] resultCharArray = new char[byteArray.length * 2];
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];

        }
        return new String(resultCharArray);
    }

    public static String getMessageDigest(String str, String encName) {
        byte[] digest = null;
        if (StringUtils.isBlank(encName)) {
            encName = "SHA-1";
        }
        try {
            MessageDigest md = MessageDigest.getInstance(encName);
            md.update(str.getBytes());
            digest = md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return byteArrayToHex(digest);
    }

    @Transactional(rollbackFor = Exception.class)
    public void upload(Integer xh, GpoBaseRequest baseRequest) {
        GpoPsdxx psdxx = psdxxDao.getById(xh);
        if (null == psdxx) {
            throw BaseException.create(ErrorCode.ERROR_GPO_PSD_0003);
        }

        //一个机构只能有一个传报有效的配送点
        if (psdxxDao.existUpload(psdxx.getJgid()) > 0) {
            throw BaseException.create(ErrorCode.ERROR_GPO_PSD_0006);
        }

        //未传报的才能传报
        if (psdxx.getCbzt().equals(GpoConsts.CBZT.YCB.getCode())) {
            throw BaseException.create(ErrorCode.ERROR_GPO_PSD_0005);
        }

        YypsdjcxxcbYY001ReqMain main = new YypsdjcxxcbYY001ReqMain();
        main.setCZLX(GpoZidian.CZLX.XZ.getVal());
        main.setPSDBM(psdxx.getPsdbm());
        main.setPSDMC(psdxx.getPsdmc());
        main.setPSDZ(psdxx.getPsdz());
        main.setLXRXM(psdxx.getLxrxm());
        main.setLXDH(psdxx.getLxdh());
        main.setYZBM(psdxx.getYzbm());
        main.setBZXX(StrUtil.blankToDefault(psdxx.getBzxx(), ""));
        XmlData<YypsdjcxxcbYY001ReqMain, Object> request = new XmlData<>(new Head(baseRequest.getIp(), baseRequest.getMac(), "123456"), main, null);

        //传报ws接口
        XmlData xmlData = gpoWebService.yypsdjcxxcbYY001(request, baseRequest);
        //传报成功修改传报状态
        if ("00000".equals(xmlData.getHEAD().getZTCLJG())) {
            psdxx.setCbrdm(baseRequest.getUserid());
            psdxx.setCbzt(GpoConsts.CBZT.YCB.getCode());
            psdxx.setCbsj(TimestampUtil.now());
            psdxxDao.update(psdxx);
        }

    }

    public List<GpoPsdxx> page() {
        return psdxxDao.page();
    }

    public GpoPsdxx detail(Integer xh) {
        return psdxxDao.getById(xh);
    }

    public void delete(Integer xh, Integer userid) {
        GpoPsdxx psdxx = psdxxDao.getById(xh);
        if (null == psdxx) {
            throw BaseException.create(ErrorCode.ERROR_GPO_PSD_0003);
        }

        if (psdxx.getCbzt().equals(GpoConsts.CBZT.YCB.getCode())) {
            throw BaseException.create(ErrorCode.ERROR_GPO_PSD_0008);
        }

        psdxx.setScrdm(String.valueOf(userid));
        psdxx.setScsj(TimestampUtil.now());
        psdxx.setZt(GpoConsts.ZFZT.YZF.getCode());
        psdxxDao.update(psdxx);
    }
}
