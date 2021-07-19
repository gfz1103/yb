package com.buit.his.shyb.source.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.buit.his.shyb.source.entity.*;
import com.buit.his.shyb.source.service.impl.UnifiedBusinessService;
import com.buit.aop.lock.Locked;
import com.buit.system.service.SysXtcsCacheSer;
import com.buit.system.utill.MedicineUtils;
import org.apache.dubbo.config.annotation.DubboReference;

import com.buit.his.shyb.source.entity.business.MessageBody;
import com.buit.his.shyb.source.entity.business.SH01;
import com.buit.his.shyb.source.entity.business.SH02;
import com.buit.his.shyb.source.entity.business.SI11;
import com.buit.his.shyb.source.entity.business.SI12;
import com.buit.his.shyb.source.entity.business.SI51;
import com.buit.his.shyb.source.entity.business.SI52;
import com.buit.his.shyb.source.entity.business.SI91;
import com.buit.his.shyb.source.entity.business.SJ11;
import com.buit.his.shyb.source.entity.business.SJ21cy;
import com.buit.his.shyb.source.entity.business.SJC1;
import com.buit.his.shyb.source.entity.business.SJD1;
import com.buit.his.shyb.source.entity.business.SJF1;
import com.buit.his.shyb.source.entity.business.SK01;
import com.buit.his.shyb.source.entity.business.SM01;
import com.buit.his.shyb.source.entity.business.SN01_MZ;
import com.buit.his.shyb.source.enums.MessageTypeEnum;
import com.buit.his.shyb.source.enums.TradingChannelEnum;
import com.buit.his.shyb.source.service.impl.SendMessageServiceImpl;
import com.buit.his.shyb.source.util.HisUtil;
import com.buit.his.shyb.source.util.JackJsonUtil;
import com.buit.utill.StrUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService(timeout = 60000)
public class UnifiedBusinessManager implements UnifiedBusinessService {
	@DubboReference
	private SysXtcsCacheSer sysXtcsCacheSer;



	@Autowired
    public MedicalInsuranceModel MedicalInsuranceModel;
    @Autowired
    public SendMessageServiceImpl sendMessageServiceImpl;
    /**
     * 调用中间服务与医保交易【线下】
     *
     * @param messageBody
     * @return
     */
    public SettleResultDTO sendMessageOffLine(MessageBody messageBody, String ip,String jgid) {
     //   Context ctx = ContextUtils.getContext();
     //   String ipAddress = (String) ctx.get(Context.CLIENT_IP_ADDRESS);
        //String jgid = getUser().getHospitalId()+"" ;
        String url = sysXtcsCacheSer.getCsz(MedicineUtils.parseInt(jgid),"YBOFFMIDSERVICE");
        url = "http://" + ip + url;

        //url = "http://" + ip +"8090/WRL";  :9500/yb/sendMes
       // ISendMessageService service = AppContextHolder.getBean(OnlineSettleConstant.SEND_SERVICE_BEAN_NAME, SendMessageServiceImpl.class);
       // SendMessageServiceImpl service = new SendMessageServiceImpl();
        SettleResultDTO settleResultDTO = sendMessageServiceImpl.sendMessage(url, messageBody);
        return settleResultDTO;
    }

    /**
     * 账户查询
     * @param accountOptions
     * @return
     */
    public SettleResultDTO sm01(Map<String,Object> accountOptions){
        String jgid = StrUtil.null2Str(accountOptions.get("jgid"));
        String orgId = accountOptions.get("orgId").toString();
        int ygdm = Integer.parseInt(StrUtil.null2Str(accountOptions.get("ygdm")));
        String ygxm = StrUtil.null2Str(accountOptions.get("ygxm"));
        TradingChannelEnum jyqd = (TradingChannelEnum) accountOptions.get("jyqd");
        String cardType = accountOptions.get("cardtype").toString();
        String cardData = StrUtil.null2Str(accountOptions.get("carddata"));
        String ip = StrUtil.null2Str(accountOptions.get("ip"));
        MessageBody messageBody = buildCommonMessageBody(orgId, ygdm,ygxm,MessageTypeEnum.SM01, jyqd);

        SM01 sm01 = new SM01();
        sm01.setCardtype(cardType);
        if (cardData != null && !"".equals(cardData)) {
            sm01.setCarddata(cardData);
        }else {
            sm01.setCarddata("");
        }
        messageBody.setXxnr(sm01);

        SettleResultDTO result = null;
        if(jyqd.equals(TradingChannelEnum.OFFLINE)){
            result = sendMessageOffLine( messageBody,ip,jgid);
        }else{

        }
        return result;
    }

    /**
     * s000
     *保障卡基本信息读取
     * @param options
     * @return
     */
    public SettleResultDTO s000(Map<String, Object> options) {
        String jgid = StrUtil.null2Str(options.get("jgid"));
        String orgId = StrUtil.null2Str(options.get("orgld"));
        int ygdm = Integer.parseInt(StrUtil.null2Str(options.get("ygdm")));
        String ygxm = StrUtil.null2Str(options.get("ygxm"));
        String ip = StrUtil.null2Str(options.get("ip"));
        TradingChannelEnum jyqd = (TradingChannelEnum) options.get("jyqd");
        MessageBody messageBody = new MessageBody();
        messageBody = buildCommonMessageBody(orgId,ygdm,ygxm, MessageTypeEnum.S000, jyqd);
        messageBody.setXxnr(new HashMap<String, Object>());
        SettleResultDTO result = sendMessageOffLine( messageBody ,ip,jgid);
        return result;
    }

    /**
     * 登记查询
     * @param options
     * @return
     */
    public SettleResultDTO sj31(Map<String,Object> options){
        String orgId = options.get("orgId").toString();
        String jgid = StrUtil.null2Str(options.get("jgid"));
        int ygdm = Integer.parseInt(StrUtil.null2Str(options.get("ygdm")));
        String ygxm = StrUtil.null2Str(options.get("ygxm"));
        TradingChannelEnum jyqd = (TradingChannelEnum) options.get("jyqd");
        String cardType = options.get("cardtype").toString();
        String cardData = StrUtil.null2Str(options.get("carddata"));
        String djlb = StrUtil.null2Str(options.get("djlb"));
        String ip = StrUtil.null2Str(options.get("ip"));
        MessageBody messageBody = buildCommonMessageBody(orgId, ygdm,ygxm,MessageTypeEnum.SJ31, jyqd);
        SJ31 sj31 = new SJ31();
        sj31.setCardtype(cardType);
        if (cardData != null && !"".equals(cardData)) {
            sj31.setCarddata(cardData);
        }else {
            sj31.setCarddata("");
        }
        sj31.setDjlb(djlb);
        messageBody.setXxnr(sj31);
        SettleResultDTO result = sendMessageOffLine(messageBody,ip,jgid);
        return result;
    }

    /**
     * 登记查询
     * @param options
     * @return
     */
    public SettleResultDTO sjg1(Map<String,Object> options){
        String orgId = options.get("orgId").toString();
        String jgid = StrUtil.null2Str(options.get("jgid"));
        int ygdm = Integer.parseInt(StrUtil.null2Str(options.get("ygdm")));
        String ygxm = StrUtil.null2Str(options.get("ygxm"));
        TradingChannelEnum jyqd = (TradingChannelEnum) options.get("jyqd");
        String cardType = options.get("cardtype").toString();
        String cardData = StrUtil.null2Str(options.get("carddata"));
        String ip = StrUtil.null2Str(options.get("ip"));
        MessageBody messageBody = buildCommonMessageBody(orgId, ygdm,ygxm,MessageTypeEnum.SJG1, jyqd);
        SM01 sm01 = new SM01();
        sm01.setCardtype(cardType);
        if (cardData != null && !"".equals(cardData)) {
            sm01.setCarddata(cardData);
        }else {
            sm01.setCarddata("");
        }
        messageBody.setXxnr(sm01);
        SettleResultDTO result = sendMessageOffLine(messageBody,ip,jgid);
        return result;
    }

    /**
     * 挂号预结算
     * @param preRegisterOptions
     * @return
     */
    public SettleResultDTO sh01(Map<String, Object> preRegisterOptions) {
        String jgid = StrUtil.null2Str(preRegisterOptions.get("jgid"));
        String orgId = StrUtil.null2Str(preRegisterOptions.get("orgid"));
        int ygdm = Integer.parseInt(StrUtil.null2Str(preRegisterOptions.get("ygdm")));
        String ygxm = StrUtil.null2Str(preRegisterOptions.get("ygxm"));
        TradingChannelEnum jyqd = (TradingChannelEnum) preRegisterOptions.get("jyqd");
        MessageBody messageBody = buildCommonMessageBody(orgId,ygdm,ygxm, MessageTypeEnum.SH01, jyqd);

        String cardType = StrUtil.null2Str(preRegisterOptions.get("cardtype"));
        String cardData = StrUtil.null2Str(preRegisterOptions.get("carddata"));
        String deptid = StrUtil.null2Str(preRegisterOptions.get("deptid"));
        String personSpecTag = StrUtil.null2Str(preRegisterOptions.get("personspectag"));
        String gsrdh = StrUtil.null2Str(preRegisterOptions.get("gsrdh"));
        String yllb = StrUtil.null2Str(preRegisterOptions.get("yllb"));
        String jmbz = StrUtil.null2Str(preRegisterOptions.get("jmbz"));
        String xsywlx = StrUtil.null2Str(preRegisterOptions.get("xsywlx"));
        String ghfstr = StrUtil.null2Str(preRegisterOptions.get("ghf"));
        String fybjsfwfyzestr = StrUtil.null2Str(preRegisterOptions.get("fybjsfwfyze"));
        String totalexpensestr = StrUtil.null2Str(preRegisterOptions.get("totalexpense"));
        String ybjsfwfyzestr = StrUtil.null2Str(preRegisterOptions.get("ybjsfwfyze"));
        String zhenlfstr = StrUtil.null2Str(preRegisterOptions.get("zhenlf"));
        String ip = StrUtil.null2Str(preRegisterOptions.get("ip"));
        String persontype = StrUtil.null2Str(preRegisterOptions.get("persontype"));
        String dbtype = StrUtil.null2Str(preRegisterOptions.get("dbtype"));
        BigDecimal ghf = null;
        BigDecimal fybjsfwfyze = null;
        BigDecimal totalexpense = null;
        BigDecimal ybjsfwfyze = null;
        BigDecimal zhenlf = null;
        if ("".equals(ghfstr)) {
            ghf = new BigDecimal(0);
        }else {
            ghf = new BigDecimal(ghfstr);
        }
        if ("".equals(fybjsfwfyzestr)) {
            fybjsfwfyze = new BigDecimal(0);
        }else {
            fybjsfwfyze = new BigDecimal(fybjsfwfyzestr);
        }
        if ("".equals(totalexpensestr)) {
            totalexpense = new BigDecimal(0);
        }else {
            totalexpense = new BigDecimal(totalexpensestr);
        }
        if ("".equals(ybjsfwfyzestr)) {
            ybjsfwfyze = new BigDecimal(0);
        }else {
            ybjsfwfyze = new BigDecimal(ybjsfwfyzestr);
        }
        if ("".equals(zhenlfstr)) {
            zhenlf = new BigDecimal(0);
        }else {
            zhenlf = new BigDecimal(zhenlfstr);
        }
        SH01 sh01 = new SH01();
        sh01.setCardtype(cardType);//凭证类别
        sh01.setCarddata(cardData);//凭证码
        sh01.setDeptid(deptid);//科室编码
        sh01.setZlxmdm(SettleConstant.ZLXMDM);//诊疗项目代码
        sh01.setPersonspectag(personSpecTag);//特殊人员标识
        sh01.setYllb(yllb);//医疗类别
        sh01.setTotalexpense(totalexpense.setScale(2).toString());//交易费用总额
        sh01.setYbjsfwfyze(ybjsfwfyze.setScale(2).toString());//医保结算范围费用总额
        sh01.setZhenlf(zhenlf.setScale(2).toString());//诊疗费
        sh01.setGhf(ghf.setScale(2).toString());//门（急）诊诊疗费自费
        sh01.setFybjsfwfyze(fybjsfwfyze.setScale(2).toString());//非医保结算范围费用总额
        sh01.setJmbz(jmbz);
        sh01.setXsywlx(xsywlx);
        sh01.setPersontype(persontype);
        sh01.setGsrdh(gsrdh);
        sh01.setDbtype(dbtype);
        messageBody.setXxnr(sh01);
        SettleResultDTO result = sendMessageOffLine(messageBody,ip,jgid);
        return result;
    }

    /**
     * 门诊挂号
     *
     * @param RegisterOptions
     * @return
     */
    public SettleResultDTO sh02(Map<String, Object> RegisterOptions) {
        String jgid = StrUtil.null2Str(RegisterOptions.get("jgid"));
        String orgid = StrUtil.null2Str(RegisterOptions.get("orgid"));
        TradingChannelEnum jyqd = (TradingChannelEnum) RegisterOptions.get("jyqd");
        int ygdm = Integer.parseInt(StrUtil.null2Str(RegisterOptions.get("ygdm")));
        String ygxm = StrUtil.null2Str(RegisterOptions.get("ygxm"));
        MessageBody messageBody = buildCommonMessageBody(orgid, ygdm,ygxm,MessageTypeEnum.SH02, jyqd);

        String cardType = StrUtil.null2Str(RegisterOptions.get("cardtype"));
        String cardData = StrUtil.null2Str(RegisterOptions.get("carddata"));
        String deptid = StrUtil.null2Str(RegisterOptions.get("deptid"));
        String personSpecTag = StrUtil.null2Str(RegisterOptions.get("personspectag"));
        String gsrdh = StrUtil.null2Str(RegisterOptions.get("gsrdh"));
        String dbtype = StrUtil.null2Str(RegisterOptions.get("dbtype"));
        String yllb = StrUtil.null2Str(RegisterOptions.get("yllb"));
        String jmbz = StrUtil.null2Str(RegisterOptions.get("jmbz"));
        String jssqxh = StrUtil.null2Str(RegisterOptions.get("jssqxh"));
        String xsywlx = StrUtil.null2Str(RegisterOptions.get("xsywlx"));

        String ghfstr = StrUtil.null2Str(RegisterOptions.get("ghf"));
        String fybjsfwfyzestr = StrUtil.null2Str(RegisterOptions.get("fybjsfwfyze"));
        String totalexpensestr = StrUtil.null2Str(RegisterOptions.get("totalexpense"));
        String ybjsfwfyzestr = StrUtil.null2Str(RegisterOptions.get("ybjsfwfyze"));
        String zhenlfstr = StrUtil.null2Str(RegisterOptions.get("zhenlf"));
        String ip = StrUtil.null2Str(RegisterOptions.get("ip"));
        String persontype = StrUtil.null2Str(RegisterOptions.get("persontype"));

        BigDecimal ghf = null;
        BigDecimal fybjsfwfyze = null;
        BigDecimal totalexpense = null;
        BigDecimal ybjsfwfyze = null;
        BigDecimal zhenlf = null;

        if ("".equals(ghfstr)) {
            ghf = new BigDecimal(0);
        }else {
            ghf = new BigDecimal(ghfstr);
        }
        if ("".equals(fybjsfwfyzestr)) {
            fybjsfwfyze = new BigDecimal(0);
        }else {
            fybjsfwfyze = new BigDecimal(fybjsfwfyzestr);
        }
        if ("".equals(totalexpensestr)) {
            totalexpense = new BigDecimal(0);
        }else {
            totalexpense = new BigDecimal(totalexpensestr);
        }
        if ("".equals(ybjsfwfyzestr)) {
            ybjsfwfyze = new BigDecimal(0);
        }else {
            ybjsfwfyze = new BigDecimal(ybjsfwfyzestr);
        }
        if ("".equals(zhenlfstr)) {
            zhenlf = new BigDecimal(0);
        }else {
            zhenlf = new BigDecimal(zhenlfstr);
        }
        SH02 sh02 = new SH02();
        sh02.setCardtype(cardType);//凭证类别
        sh02.setCarddata(cardData);//凭证码
        sh02.setDeptid(deptid);//科室编码
        sh02.setZlxmdm(SettleConstant.ZLXMDM);//诊疗项目代码
        sh02.setPersonspectag(personSpecTag);//特殊人员标识
        sh02.setYllb(yllb);//医疗类别
        sh02.setJmbz(jmbz);//享受社区减免标志
        sh02.setJssqxh(jssqxh);//计算申请序号
        sh02.setXsywlx(xsywlx);
        sh02.setPersontype(persontype);
        sh02.setGsrdh(gsrdh);
        sh02.setDbtype(dbtype);

        sh02.setTotalexpense(totalexpense.setScale(2).toString());//交易费用总额
        sh02.setYbjsfwfyze(ybjsfwfyze.setScale(2).toString());//医保结算范围费用总额
        sh02.setZhenlf(zhenlf.setScale(2).toString());//诊疗费
        sh02.setGhf(ghf.setScale(2).toString());//门（急）诊诊疗费自费
        sh02.setFybjsfwfyze(fybjsfwfyze.setScale(2).toString());//非医保结算范围费用总额

        messageBody.setXxnr(sh02);
        SettleResultDTO result = sendMessageOffLine(messageBody,ip,jgid);
        return result;
    }

    /**
     * 交易查询
     * @param registerOptions
     * @return
     */
    public SettleResultDTO si91(Map<String, Object> registerOptions) {
        String orgId = StrUtil.null2Str(registerOptions.get("jgid"));
        TradingChannelEnum jyqd = (TradingChannelEnum) registerOptions.get("jyqd");
        int ygdm = Integer.parseInt(StrUtil.null2Str(registerOptions.get("ygdm")));
        String ygxm = StrUtil.null2Str(registerOptions.get("ygxm"));
        MessageBody messageBody = buildCommonMessageBody(orgId,ygdm,ygxm, MessageTypeEnum.SI91, jyqd);

        String cardType = StrUtil.null2Str(registerOptions.get("cardtype"));
        String cardData = StrUtil.null2Str(registerOptions.get("carddata"));
        String jssqxh = StrUtil.null2Str(registerOptions.get("jssqxh"));
        String totalexpenseStr = StrUtil.null2Str(registerOptions.get("totalexpense"));
        String ip = StrUtil.null2Str(registerOptions.get("ip"));
        BigDecimal totalexpense = null;
        if ("".equals(totalexpenseStr)) {
            totalexpense = new BigDecimal(0);
        }else {
            totalexpense = new BigDecimal(totalexpenseStr);
        }
        SI91 si91 = new SI91();
        si91.setCardtype(cardType);//凭证类别
        si91.setCarddata(cardData);//凭证码
        si91.setTotalexpense(totalexpense.setScale(2).toString());//交易费用总额
        si91.setJssqxh(jssqxh);//计算申请序号

        messageBody.setXxnr(si91);
        SettleResultDTO result = sendMessageOffLine(messageBody,ip,orgId);
        return result;
    }

    /**
     * 退费
     * @return
     */
    @Override
    @Locked("sk01_#[refundMap.sbxh]")
    public SettleResultDTO sk01(Map<String, Object> refundMap , Map<String, Object> tranMap){
        String orgid = StrUtil.null2Str(refundMap.get("orgid"));
        String jgid = StrUtil.null2Str(refundMap.get("jgid"));
        int ygdm = Integer.parseInt(StrUtil.null2Str(refundMap.get("ygdm")));
        String ygxm = StrUtil.null2Str(refundMap.get("ygxm"));
        if("10".equals(refundMap.get("jyqd"))){
            refundMap.put("jyqd", TradingChannelEnum.OFFLINE);
        }else {
            refundMap.put("jyqd", TradingChannelEnum.ONLINE);
        }
        TradingChannelEnum jyqd = (TradingChannelEnum)refundMap.get("jyqd");
        String cardType = StrUtil.null2Str(refundMap.get("cardtype"));
        String cardData = StrUtil.null2Str(refundMap.get("carddata"));
        String xsywlx = StrUtil.null2Str(refundMap.get("xsywlx"));
        String ip = StrUtil.null2Str(refundMap.get("ip"));

        String translsh = StrUtil.null2Str(tranMap.get("LSH"));
        String totalexpensestr = StrUtil.null2Str(tranMap.get("TOTALEXPENSE"));

        MessageBody messageBody = buildCommonMessageBody(orgid,ygdm,ygxm, MessageTypeEnum.SK01, jyqd);

        BigDecimal totalexpense = null;

        if ("".equals(totalexpensestr)) {
            totalexpense = new BigDecimal(0);
        }else {
            totalexpense = new BigDecimal(totalexpensestr);
        }

        SK01 sk01 = new SK01();
        sk01.setCardtype(cardType);
        sk01.setCarddata(cardData);
        sk01.setTranslsh(translsh);
        sk01.setTotalexpense(totalexpense.setScale(2).toString());
        if("1".equals(xsywlx)){
            sk01.setXsywlx(xsywlx);
        }
        messageBody.setXxnr(sk01);
        return sendMessageOffLine(messageBody,ip,jgid);
    }

    /**
     * 住院收费退费
     * @return
     */
    public SettleResultDTO sk01_mz(String orgId, int ygdm,String ygxm,String jgid,String ip,TradingChannelEnum jyqd, SK01 sk01){
        MessageBody messageBody = buildCommonMessageBody(orgId,ygdm,ygxm, MessageTypeEnum.SK01, jyqd);
        messageBody.setXxnr(sk01);
        return sendMessageOffLine(messageBody,ip,jgid);
    }

    /**
     * 明细账单提交
     * @return
     */
    public SettleResultDTO sn01_mz(String orgId, String jgid,int ygdm,String ygxm,String ip,TradingChannelEnum jyqd, SN01_MZ sn01_mz){
        MessageBody messageBody = buildCommonMessageBody(orgId, ygdm,ygxm,MessageTypeEnum.SN01, jyqd);
        messageBody.setXxnr(sn01_mz);
        return sendMessageOffLine(messageBody,ip,jgid);
    }

    /**
     * 门诊收费预结算
     * @return
     */
    public SettleResultDTO si11(String orgId,String jgid,int ygdm,String ygxm, TradingChannelEnum jyqd,String ip, SI11 si11){
        MessageBody messageBody = buildCommonMessageBody(orgId, ygdm,ygxm,MessageTypeEnum.SI11, jyqd);
        messageBody.setXxnr(si11);
        return sendMessageOffLine(messageBody,ip,jgid);
    }

    /**
     * 门诊收费结算
     * @return
     */
    public SettleResultDTO si12(String orgId, String jgid,int ygdm,String ygxm,TradingChannelEnum jyqd, String ip, SI12 si12){
        MessageBody messageBody = buildCommonMessageBody(orgId,ygdm,ygxm, MessageTypeEnum.SI12, jyqd);
        messageBody.setXxnr(si12);
        return sendMessageOffLine(messageBody,ip,jgid);
    }

    /**
     * 住院登记消息内容并请求医保
     * @return
     */
    public SettleResultDTO sj11(String orgId, int ygdm,String ygxm,String jgid,TradingChannelEnum jyqd, String ip,SJ11 sj11){
        MessageBody messageBody = buildCommonMessageBody(orgId,ygdm,ygxm, MessageTypeEnum.SJ11, jyqd);
        messageBody.setXxnr(sj11);
        return sendMessageOffLine(messageBody,ip,jgid);
    }

    /**
     * 构建住院/家床撤销消息内容并请求医保
     * @return
     */
    public SettleResultDTO sj21(String orgId,int ygdm,String ygxm,String jgid,TradingChannelEnum jyqd,String ip , SJ21cy sj21){
        MessageBody messageBody = buildCommonMessageBody(orgId, ygdm,ygxm,MessageTypeEnum.SJ21, jyqd);
        messageBody.setXxnr(sj21);
        return sendMessageOffLine(messageBody,ip,jgid);
    }

    /**
     * si51
     * @return
     */
    public SettleResultDTO si51(String orgId, String jgid,int ygdm,String ygxm,TradingChannelEnum jyqd,String ip , SI51 si51){
        MessageBody messageBody = buildCommonMessageBody(orgId, ygdm,ygxm,MessageTypeEnum.SI51, jyqd);
        messageBody.setXxnr(si51);
        return sendMessageOffLine(messageBody,ip,jgid);
    }

    /**
     * si52
     * @return
     */
    public SettleResultDTO si52(String orgId, String jgid,int ygdm,String ygxm,TradingChannelEnum jyqd, String ip , SI52 si52){
        MessageBody messageBody = buildCommonMessageBody(orgId, ygdm,ygxm,MessageTypeEnum.SI52, jyqd);
        messageBody.setXxnr(si52);
        return sendMessageOffLine(messageBody,ip,jgid);
    }

    /**
     * 构建居保门诊转院查询消息内容并请求医保
     * @return
     */
    public SettleResultDTO sjD1(Map<String, Object> body){
        String orgId = body.get("orgId").toString();
        String jgid = body.get("jgid").toString();
        int ygdm = Integer.parseInt(StrUtil.null2Str(body.get("ygdm")));
        String ygxm = StrUtil.null2Str(body.get("ygxm"));
        String ip = body.get("ip").toString();
        TradingChannelEnum jyqd = (TradingChannelEnum) body.get("jyqd");
        String cardType = StrUtil.null2Str(body.get("cardtype"));
        String carddata = StrUtil.null2Str(body.get("carddata"));
        String djtype = StrUtil.null2Str(body.get("djtype"));
        MessageBody messageBody = buildCommonMessageBody(orgId, ygdm,ygxm,MessageTypeEnum.SJD1, jyqd);
        SJD1 sjD1 = new SJD1();
        sjD1.setCardtype(cardType);
        sjD1.setCarddata(carddata);
        sjD1.setDjtype(djtype);
        messageBody.setXxnr(sjD1);
        return sendMessageOffLine( messageBody,ip,jgid);
    }

    /**
     * 构建居保门诊转院撤销消息内容并请求医保
     * @return
     */
    public SettleResultDTO sjF1(Map<String, Object> body){
        String orgId = body.get("orgId").toString();
        String jgid = body.get("jgid").toString();
        int ygdm = Integer.parseInt(StrUtil.null2Str(body.get("ygdm")));
        String ygxm = StrUtil.null2Str(body.get("ygxm"));
        TradingChannelEnum jyqd = (TradingChannelEnum) body.get("jyqd");
        String cardType = StrUtil.null2Str(body.get("cardtype"));
        String carddata = StrUtil.null2Str(body.get("carddata"));
        String djtype = StrUtil.null2Str(body.get("djtype"));
        String djno = StrUtil.null2Str(body.get("djno"));
        String startdate = StrUtil.null2Str(body.get("startdate"));
        String zrjgbh = StrUtil.null2Str(body.get("zrjgbh"));
        String ip = StrUtil.null2Str(body.get("ip"));
        MessageBody messageBody = buildCommonMessageBody(orgId,ygdm,ygxm, MessageTypeEnum.SJF1, jyqd);
        SJF1 sjF1 = new SJF1();
        sjF1.setCardtype(cardType);
        sjF1.setCarddata(carddata);
        sjF1.setDjtype(djtype);
        sjF1.setDjno(djno);
        sjF1.setStartdate(startdate);
        sjF1.setZrjgbh(zrjgbh);
        messageBody.setXxnr(sjF1);
        return sendMessageOffLine( messageBody,ip,jgid);
    }

    /**
     * 转入医院查询
     * @param body
     * @return
     */
    public SettleResultDTO sjh1(Map<String,Object> body){
        String ip = body.get("ip")+"";
        String orgId = body.get("orgId")+"";
        String jgid = body.get("jgid")+"";
        int ygdm = Integer.parseInt(StrUtil.null2Str(body.get("ygdm")));
        String ygxm = StrUtil.null2Str(body.get("ygxm"));
        TradingChannelEnum jyqd = (TradingChannelEnum) body.get("jyqd");
        MessageBody messageBody = this.buildCommonMessageBody(orgId, ygdm,ygxm,MessageTypeEnum.SJH1, jyqd);
        messageBody.setXxnr(new HashMap<String, Object>());
        return sendMessageOffLine( messageBody,ip,jgid);
    }

    /**
     * 门诊收费电子凭证解码
     * SE01
     * @return
     */
    public SettleResultDTO se01_mz(String orgId,String ip, TradingChannelEnum jyqd, SE01 se01,int ygdm,String ygxm,String jgid){
        MessageBody messageBody = buildCommonMessageBody(orgId, ygdm,ygxm,MessageTypeEnum.SE01, jyqd);
        messageBody.setXxnr(se01);
        return sendMessageOffLine(messageBody,ip,jgid);
    }

    /**
     * 构建居保门诊转院撤销消息内容并请求医保
     * @return
     */
    public SettleResultDTO sjC1(Map<String, Object> body){
        String orgId = body.get("orgId").toString();
        String jgid = body.get("jgid").toString();
        int ygdm = Integer.parseInt(StrUtil.null2Str(body.get("ygdm")));
        String ygxm = StrUtil.null2Str(body.get("ygxm"));
        TradingChannelEnum jyqd = (TradingChannelEnum) body.get("jyqd");
        String cardType = StrUtil.null2Str(body.get("cardtype"));
        String carddata = StrUtil.null2Str(body.get("carddata"));
        String djtype = StrUtil.null2Str(body.get("djtype"));
        String zcjgdm = StrUtil.null2Str(body.get("zcjgdm"));
        String zrjgdm = StrUtil.null2Str(body.get("zrjgdm"));
        String startdate = StrUtil.null2Str(body.get("startdate"));
        String ip = StrUtil.null2Str(body.get("ip"));
        MessageBody messageBody = buildCommonMessageBody(orgId, ygdm,ygxm,MessageTypeEnum.SJC1, jyqd);
        SJC1 sjC1 = new SJC1();
        sjC1.setCardtype(cardType);
        sjC1.setCarddata(carddata);
        sjC1.setDjtype(djtype);
        sjC1.setZcjgdm(zcjgdm);
        sjC1.setZrjgdm(zrjgdm);
        sjC1.setStartdate(startdate);
        messageBody.setXxnr(sjC1);
        return sendMessageOffLine( messageBody,ip,jgid);
    }

    /**
     * 医保对账
     * @param sl01
     * @param body
     * @return
     */
    public SettleResultDTO SL01(SL01s sl01, Map<String,Object> body){
        String orgld = body.get("orgid")+"";
        String jgid = body.get("jgid")+"";
        String ip = body.get("ip")+"";
        int ygdm = Integer.parseInt(StrUtil.null2Str(body.get("ygdm")));
        String ygxm = StrUtil.null2Str(body.get("ygxm"));
        TradingChannelEnum jyqd = (TradingChannelEnum) body.get("jyqd");
        MessageBody messageBody = this.buildCommonMessageBody(orgld, ygdm,ygxm,MessageTypeEnum.SL01, jyqd);

        String totalcuraccpaystr = StrUtil.null2Str(sl01.getTotalcuraccpay());
        String totalhisaccpaystr = StrUtil.null2Str(sl01.getTotalhisaccpay());
        String totalcashpaystr = StrUtil.null2Str(sl01.getTotalcashpay());
        String totaltcpaystr = StrUtil.null2Str(sl01.getTotaltcpay());
        String totaldffjpaystr = StrUtil.null2Str(sl01.getTotaldffjpay());
        String totalflzfstr = StrUtil.null2Str(sl01.getTotalflzf());
        String totalfybjsfwstr = StrUtil.null2Str(sl01.getTotalfybjsfw());
        String daycount = StrUtil.null2Str(sl01.getDaycount());

        BigDecimal totalcuraccpay = null;
        BigDecimal totalhisaccpay = null;
        BigDecimal totalcashpay = null;
        BigDecimal totaltcpay = null;
        BigDecimal totaldffjpay = null;
        BigDecimal totalflzf = null;
        BigDecimal totalfybjsfw = null;

        if ("".equals(totalcuraccpaystr)) {
            totalcuraccpay = new BigDecimal(0);
        }else {
            totalcuraccpay = new BigDecimal(totalcuraccpaystr);
        }

        if ("".equals(totalhisaccpaystr)) {
            totalhisaccpay = new BigDecimal(0);
        }else {
            totalhisaccpay = new BigDecimal(totalhisaccpaystr);
        }

        if ("".equals(totalcashpaystr)) {
            totalcashpay = new BigDecimal(0);
        }else {
            totalcashpay = new BigDecimal(totalcashpaystr);
        }

        if ("".equals(totaltcpaystr)) {
            totaltcpay = new BigDecimal(0);
        }else {
            totaltcpay = new BigDecimal(totaltcpaystr);
        }

        if ("".equals(totaldffjpaystr)) {
            totaldffjpay = new BigDecimal(0);
        }else {
            totaldffjpay = new BigDecimal(totaldffjpaystr);
        }

        if ("".equals(totalflzfstr)) {
            totalflzf = new BigDecimal(0);
        }else {
            totalflzf = new BigDecimal(totalflzfstr);
        }

        if ("".equals(totalfybjsfwstr)) {
            totalfybjsfw = new BigDecimal(0);
        }else {
            totalfybjsfw = new BigDecimal(totalfybjsfwstr);
        }

        sl01.setDaycount(daycount);//对帐日中心流水号数量
        sl01.setTotalcuraccpay(totalcuraccpay.setScale(2).toString()); //当年帐户支付总额
        sl01.setTotalhisaccpay(totalhisaccpay.setScale(2).toString());//历年帐户支付总额
        sl01.setTotalcashpay(totalcashpay.setScale(2).toString());//现金自负总额
        sl01.setTotaltcpay(totaltcpay.setScale(2).toString());//统筹支付总额
        sl01.setTotaldffjpay(totaldffjpay.setScale(2).toString());//附加支付总额
        sl01.setTotalflzf(totalflzf.setScale(2).toString());//分类自负总额
        sl01.setTotalfybjsfw(totalfybjsfw.setScale(2).toString());//非医保结算范围费用总额总额
        messageBody.setXxnr(sl01);
        return sendMessageOffLine( messageBody,ip,jgid);
    }
    /**
     * 创建公共的消息体
     *
     * @param orgId 机构代码
     * @return 消息体
     */
    public MessageBody buildCommonMessageBody(String orgId, int operatorId,String operatorName,MessageTypeEnum messageType,
                                              TradingChannelEnum tradingChannelEnum) {
        //MedicalInsuranceModel model = new MedicalInsuranceModel();
        String msgId = MedicalInsuranceModel.getMsgId(orgId,9);

        MessageBody messageBody = new MessageBody();
        messageBody.setJysj(new Date());
        messageBody.setMsgid(msgId);
        messageBody.setJgdm(orgId);
        messageBody.setCzybm(operatorId);
        messageBody.setCzyxm(operatorName);

        messageBody.setXxlxm(messageType);
        messageBody.setJyqd(tradingChannelEnum);
        return messageBody;
    }

    /**
     * 从请求医保返回的 OnlineSettleResultDTO 获取 xxnr
     * @param YBDTOData
     * @return
     */
    public HashMap<String, Object> convertYBDTOData2Map(String YBDTOData){
        MessageBody messageBody = JackJsonUtil.parse(YBDTOData, MessageBody.class);
        LinkedHashMap map = (LinkedHashMap)messageBody.getXxnr();
        HashMap<String, Object> resMap = (HashMap<String, Object>) HisUtil.upcaseKeys(map);
        return resMap;
    }
}
