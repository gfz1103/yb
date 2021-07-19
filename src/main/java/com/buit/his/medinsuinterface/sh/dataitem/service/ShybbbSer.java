package com.buit.his.medinsuinterface.sh.dataitem.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.http.HttpResponse;
import com.buit.commons.BaseException;
import com.buit.commons.SysUser;
import com.buit.his.medinsuinterface.sh.dataitem.dao.ShybbbDao;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybbbReq;
import com.buit.his.medinsuinterface.sh.dataitem.response.*;
import com.buit.his.shyb.source.dao.ShybSh01Dao;
import com.buit.his.shyb.source.entity.SL01s;
import com.buit.his.shyb.source.entity.SettleResultDTO;
import com.buit.his.shyb.source.entity.business.SL01;
import com.buit.his.shyb.source.entity.business.SL01Res;
import com.buit.his.shyb.source.enums.BusinessTypeEnum;
import com.buit.his.shyb.source.enums.TradingChannelEnum;
import com.buit.his.shyb.source.model.UnifiedBusinessManager;
import com.buit.his.shyb.source.service.impl.OfflineSettleServiceImpl;
import com.buit.his.shyb.source.util.HisUtil;
import com.buit.his.shyb.source.util.JackJsonUtil;
import com.buit.op.service.OpMzlbService;
import com.buit.system.service.SysXtcsCacheSer;
import com.buit.utill.BSPHISUtil;
import com.buit.utill.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linuxense.javadbf.DBFDataType;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFWriter;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 上海医保明细对账<br>
 * @author beijunhua
 */
@Service
public class ShybbbSer  {

    static final Logger logger = LoggerFactory.getLogger(ShybbbSer.class);

    @Autowired
    private ShybbbDao shybbbDao;
    @Autowired
    private ShybSh01Dao ybSh01Dao;
    @DubboReference
    private OpMzlbService opMzlbDao;
    @DubboReference
    private SysXtcsCacheSer sysXtcsCacheSer;
    @Autowired
    private UnifiedBusinessManager unifiedBusinessManager;

    public MxdzResp getYbMxsj(Integer mzlb, String querydate) {
        MxdzResp res = new MxdzResp();
        Map<String, Object> ghMap = new HashMap<String, Object>();
        Map<String, Object> sfMap = new HashMap<String, Object>();
        Map<String, Object> tfMap = new HashMap<String, Object>();
        Map<String, Object> zyMap = new HashMap<String, Object>();
        Map<String, Object> zytfMap = new HashMap<String, Object>();

        //Map<String,Object> remsgid = ybSh01Dao.getSeqMsgid("seq_yb_msgid");
        //挂号
        List<Map<String,Object>> ghmxje = shybbbDao.getGh(mzlb,querydate);
        if(ghmxje.size()>0){
            ghMap = ghmxje.get(0);
        }
        //收费
        List<Map<String,Object>> sfmxje = shybbbDao.getSf(mzlb,querydate);
        if(sfmxje.size()>0){
            sfMap = sfmxje.get(0);
        }
        //住院
        List<Map<String,Object>> zymxje = shybbbDao.getZy(mzlb,querydate);
        if(zymxje.size()>0){
            zyMap = zymxje.get(0);
        }
        //门诊退费
        List<Map<String,Object>> tfmxje = shybbbDao.getTfmz(mzlb,querydate);
        if(tfmxje.size()>0){
            tfMap = tfmxje.get(0);
        }
        //住院退费
        List<Map<String,Object>> zytfmxje = shybbbDao.getZytfmz(mzlb,querydate);
        if(zytfmxje.size()>0){
            zytfMap = zytfmxje.get(0);
        }
        //门诊
        res.setOp_dnzf(formatBig(ghMap.get("dnzf")).add(formatBig(sfMap.get("dnzf"))).subtract(formatBig(tfMap.get("dnzf"))));
        res.setOp_lnzf( formatBig(ghMap.get("lnzf")).add(formatBig(sfMap.get("lnzf"))).subtract(formatBig(tfMap.get("lnzf"))));
        res.setOp_xjzf(formatBig(ghMap.get("xjzf")).add(formatBig(sfMap.get("xjzf"))).subtract(formatBig(tfMap.get("xjzf"))));
        res.setOp_tczf(formatBig(ghMap.get("tczf")).add(formatBig(sfMap.get("tczf"))).subtract(formatBig(tfMap.get("tczf"))));
        res.setOp_fjzf(formatBig(ghMap.get("fjzf")).add(formatBig(sfMap.get("fjzf"))).subtract(formatBig(tfMap.get("fjzf"))));
        res.setOp_flzf(formatBig(ghMap.get("flzf")).add(formatBig(sfMap.get("flzf"))).subtract(formatBig(tfMap.get("flzf"))));
        res.setOp_czf(formatBig(ghMap.get("czf")).add(formatBig(sfMap.get("czf"))).subtract(formatBig(tfMap.get("czf"))));
        res.setOp_lsh(formatBig(ghMap.get("lsh")).add(formatBig(sfMap.get("lsh"))).add(formatBig(tfMap.get("lsh"))));
        //住院
        res.setIm_dnzf( new BigDecimal(0.00));
        res.setIm_lnzf(formatBig(zyMap.get("lnzf")).subtract(formatBig(zytfMap.get("lnzf"))));
        res.setIm_xjzf(formatBig(zyMap.get("xjzf")).subtract(formatBig(zytfMap.get("xjzf"))));
        res.setIm_tczf(formatBig(zyMap.get("tczf")).subtract(formatBig(zytfMap.get("tczf"))));
        res.setIm_fjzf(formatBig(zyMap.get("fjzf")).subtract(formatBig(zytfMap.get("fjzf"))));
        res.setIm_flzf(formatBig(zyMap.get("flzf")).subtract(formatBig(zytfMap.get("flzf"))));
        res.setIm_czf(formatBig(zyMap.get("czf")).subtract(formatBig(zytfMap.get("czf"))));
        res.setIm_lsh(formatBig(zyMap.get("lsh")).add(formatBig(zytfMap.get("lsh"))));
        //本地
        res.setBd_dnzf(formatBig(res.getOp_dnzf()).add(formatBig(res.getIm_dnzf())));
        res.setBd_lnzf(formatBig(res.getOp_lnzf()).add(formatBig(res.getIm_lnzf())));
        res.setBd_xjzf(formatBig(res.getOp_xjzf()).add(formatBig(res.getIm_xjzf())));
        res.setBd_tczf(formatBig(res.getOp_tczf()).add(formatBig(res.getIm_tczf())));
        res.setBd_fjzf(formatBig(res.getOp_fjzf()).add(formatBig(res.getIm_fjzf())));
        res.setBd_flzf(formatBig(res.getOp_flzf()).add(formatBig(res.getIm_flzf())));
        res.setBd_czf(formatBig(res.getOp_czf()).add(formatBig(res.getIm_czf())));
        res.setBd_lsh(formatBig(res.getOp_lsh()).add(formatBig(res.getIm_lsh())));
        return res;
    }

    public SL01Res getYbMxdz(SL01s sl01, SysUser user, String ip) {
        SL01Res sl01Res = new SL01Res();
        OfflineSettleServiceImpl ossi = new OfflineSettleServiceImpl();
        String orgid = "";
        Map map_orgid = opMzlbDao.getYbjgdm(user.getHospitalId(),ip);
        if(map_orgid!=null && !map_orgid.isEmpty()){
            orgid = map_orgid.get("ybjgid").toString();
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("jyqd", TradingChannelEnum.OFFLINE);
        map.put("businessType", BusinessTypeEnum.REGISTER);
        map.put("orgid", orgid);
        map.put("ip", ip);
        map.put("jgid", user.getHospitalId());
        map.put("ygdm", user.getUserId());
        map.put("ygxm", user.getUserName());
        //SettleResultDTO resultDTO = ossi.sl01(sl01,orgid,ip,user.getHospitalId());
        SettleResultDTO resultDTO = unifiedBusinessManager.SL01(sl01,map);
        if (resultDTO.getCode() == 200 && resultDTO.getData() != null) {
            String jsonData = resultDTO.getData().toString();
            Map<String, Object> tmpMap = JackJsonUtil.parse(jsonData, Map.class);
            Map<String, Object> xxtMap = (Map<String, Object>) tmpMap.get("xxnr");
            sl01Res.setDaycollate(xxtMap.get("daycollate")+"");
            sl01Res.setResultcollate(xxtMap.get("resultcollate")+"");
        }else{
            sl01Res.setResultcollate(resultDTO.getMsg());
        }
        return sl01Res;
    }

    public List<MxscJyjlkResp> queryMxscJyjlk(String date, Integer mzlb) {
        return shybbbDao.queryMxscJyjlkList(date,mzlb);
    }

    public List<MxscDbghkResp> queryMxscDbghk(String date, String mzlb){
        List<MxscDbghkResp> re = new ArrayList<MxscDbghkResp>();
        List<MxscDbghkResp> resp = shybbbDao.queryMxscDbghkList(date, mzlb);

        if(resp.size()>0){
            for( MxscDbghkResp res : resp){
                if("2".equals(res.getJMJSBZ())){
                    res.setTOTFY(number_format_H("0"));
                    res.setTOTFY(number_format_H("0"));
                    res.setZF(number_format_H(String.valueOf(Double.valueOf(res.getGHF())+Double.valueOf(res.getZLF()))));
                }else if("1".equals(res.getJMJSBZ())){
                    res.setJYTOTFY(number_format_H(String.valueOf(Double.valueOf(res.getGHF())+Double.valueOf(res.getZLF()))));
                    res.setTOTFY(number_format_H(String.valueOf(Double.valueOf(res.getGHF())+Double.valueOf(res.getZLF()))));
                    res.setZF(number_format_H("0"));
                }else{
                    if("2".equals(res.getZHBZ().substring(4,5))){
                        res.setJYTOTFY(number_format_H(String.valueOf(Double.valueOf(res.getGHF())+Double.valueOf(res.getZLF()))));
                        res.setTOTFY(number_format_H(String.valueOf(Double.valueOf(res.getGHF())+Double.valueOf(res.getZLF()))));
                        res.setZF(number_format_H("0"));
                    }else{
                        res.setJYTOTFY(number_format_H(res.getJYTOTFY()));
                        res.setTOTFY(number_format_H(res.getTOTFY()));
                        res.setZF(number_format_H(res.getZF()));
                    }
                }
                res.setYBLX(res.getZHBZ().substring(11,12));
                re.add(res);
            }
        }

        return re;
    }

    /**
     * 明细上传-住院结算记录库
     * @param date
     * @param mzlb
     * @return
     */
    public List<MxscZyjsjlkResp> queryMxscZyjsjlk(String date, String mzlb) {
        List<MxscZyjsjlkResp> re = new ArrayList<MxscZyjsjlkResp>();
        List<MxscZyjsjlkResp> resp = shybbbDao.queryMxscZyjsjlkList(date, mzlb);
        if (resp.size() > 0) {
            for (MxscZyjsjlkResp res : resp) {
                res.setBRKH(rightSpace(res.getBRKH(),10));
                res.setZYHM(rightSpace(res.getZYHM(),16));
                res.setZYDNUM(number_format_H(res.getZYDNUM()));
                res.setRYZDBM(rightSpace(res.getRYZDBM(),7));
                res.setRYZDMC(rightSpace(res.getRYZDMC(),30));
                if("1".equals(res.getJSLX())){
                    res.setCYBZ("2");
                }else{
                    res.setCYBZ("1");
                }
                re.add(res);
            }
        }
        return re;
    }

    /**
     * 主演费用明细库
     * @param date
     * @param mzlb
     * @return
     */
    public List<MxscZyfymxkResp> queryMxscZyfymxk(String date, String mzlb) {
        List<MxscZyfymxkResp> re = new ArrayList<MxscZyfymxkResp>();
        List<Map<String,Object>> zyxflshList = shybbbDao.queryMxscZyfymxkList(date, mzlb);
        if(zyxflshList.size() > 0){
            Map<String,List<Map<String,Object>>> groupedMap=new LinkedHashMap<String,List<Map<String,Object>>>();
            for(Map<String, Object> map : zyxflshList){
                String zxlsh=(String) map.get("ZXLSH");
                List<Map<String,Object>> groupedList = groupedMap.get(zxlsh);
                if(groupedList==null){
                    groupedList = new ArrayList<Map<String,Object>>();
                    groupedMap.put(zxlsh, groupedList);
                }
                groupedList.add(map);
            }
            zyxflshList.clear();
            for(Iterator<String> it=groupedMap.keySet().iterator();it.hasNext();){
                List<Map<String,Object>> groupedList=groupedMap.get(it.next());
                for(int i=0;i<groupedList.size();i++){
                    groupedList.get(i).put("XFLSH", (i+1));
                }
                zyxflshList.addAll(groupedList);
            }
            for(Map<String, Object> map : zyxflshList){
                Map<String, Object> parameters = new HashMap<String, Object>();
                parameters.put("XFLSH", this.number_format_L(String.valueOf(map.get("XFLSH"))));
                parameters.put("SBXH", String.valueOf(map.get("SBXH")));
                shybbbDao.updateXflsh(parameters);
            }
        }
        List<MxscZyfymxkResp> infoListres = shybbbDao.queryYp(date,mzlb);
        List<Map<String, Object>> infoList=  BSPHISUtil.ListObjToMap(infoListres);
        List<Map<String, Object>> yjList = shybbbDao.queryYj(date,mzlb);
        List<Map<String, Object>> yptfList = shybbbDao.queryYptf(date,mzlb);
        List<Map<String, Object>> yjtfList = shybbbDao.queryYjtf(date,mzlb);
        infoList.addAll(yjList);
        if(yptfList.size()>0){
            infoList.addAll(yptfList);
        }
        if(yjtfList.size()>0){
            infoList.addAll(yjtfList);
        }
        if(infoList.size() > 0){
            for(Map<String, Object> map : infoList){
                map.put("YBID", this.rightSpace(String.valueOf(map.get("YBID")), 10));
                map.put("KSBM", this.rightSpace(String.valueOf(map.get("KSBM")), 5));
                map.put("KSMC", this.rightSpace(String.valueOf(map.get("KSMC")), 10));
                map.put("YSGH", this.rightSpace(String.valueOf(map.get("YSGH")), 6));
                map.put("YSXM", this.rightSpace(String.valueOf(map.get("YSXM")), 12));
                if(String.valueOf(map.get("FYLB")).length() < 2){
                    map.put("FYLB", "0"+String.valueOf(map.get("FYLB")));
                }
                if(map.containsKey("XMBM") && map.get("XMBM") != null){
                    map.put("XMBM", this.rightSpace(String.valueOf(map.get("XMBM")), 15));
                }else{
                    map.put("XMBM", this.rightSpace("   ", 15));
                }
                if(map.containsKey("XMMC") && map.get("XMMC") != null){
                    map.put("XMMC", this.rightSpace(String.valueOf(map.get("XMMC")), 30));
                }else{
                    map.put("XMMC", this.rightSpace("   ", 30));
                }
                if("14".equals(String.valueOf(map.get("FYLB")))){
                    map.put("XMMC", this.rightSpace((map.get("TYMC") != null) ? map.get("TYMC").toString() : "", 30));
                }else{
                    map.put("XMMC", this.rightSpace((map.get("YBSPMC") != null) ? map.get("YBSPMC").toString() : "", 30));
                }
                if(map.containsKey("XMDW") && map.get("XMDW") != null){
                    map.put("XMDW", this.rightSpace(String.valueOf(map.get("XMDW")), 4));
                }else{
                    map.put("XMDW", this.rightSpace("   ", 4));
                }
                map.put("XMJE", this.number_format_I(String.valueOf(map.get("XMJE"))));
                double zjje = Double.valueOf(String.valueOf(map.get("ZJJE")));
                double zfje = Double.valueOf(String.valueOf(map.get("ZFJE")));
                double xmsl = Double.valueOf(String.valueOf(map.get("XMSL")));
                double czfje = Double.valueOf(String.valueOf(map.get("CZFJE")));
                map.put("XMSL", this.number_format_K(String.valueOf(map.get("XMSL"))).substring(0, 9));
                map.put("XMDJ", this.number_format_I(String.valueOf(map.get("XMDJ"))));
                map.put("XMJYJE", this.number_format_I(String.valueOf(zjje - zfje)));
                map.put("XMJSJE", this.number_format_I(String.valueOf(zjje - czfje)));
                map.put("YBLX", String.valueOf(map.get("ZHBZ")).substring(11,12));
                map.put("XFXMLSH", this.rightSpace((map.get("XFXMLSH") != null) ? String.valueOf(map.get("XFXMLSH")) : "", 4));
                if(map.containsKey("TYMC") && map.get("TYMC") != null){
                    map.put("TYMC", this.rightSpace(String.valueOf(map.get("TYMC")), 60));
                }else{
                    map.put("TYMC", this.rightSpace("   ", 60));
                }
                map.put("ZCZH", this.rightSpace(String.valueOf(map.get("ZCZH")), 100));
                if(map.containsKey("MXXMGG") && map.get("MXXMGG") != null){
                    map.put("MXXMGG", this.rightSpace(String.valueOf(map.get("MXXMGG")), 120));
                }else{
                    map.put("MXXMGG", this.rightSpace("   ", 120));
                }
                if(map.containsKey("MXXMSYRQ") && map.get("MXXMSYRQ") != null){
                    map.put("MXXMSYRQ", this.rightSpace(String.valueOf(map.get("MXXMSYRQ")), 8));
                }else{
                    map.put("MXXMSYRQ", this.rightSpace("   ", 8));
                }
            }
        }
        return infoListres;
    }

    /**
     * 出院诊断库
     * @param date
     * @param mzlb
     * @return
     */
    public List<MxscCyzdkResp> queryMxscCyzdk(String date, String mzlb){
        List<MxscCyzdkResp> re = new ArrayList<MxscCyzdkResp>();
        List<MxscCyzdkResp> resp = shybbbDao.queryMxscCyzdkList(date, mzlb);

        if(resp.size()>0){
            for( MxscCyzdkResp res : resp){
                res.setYBID(rightSpace(res.getYBID(),10));
                res.setZYHM(rightSpace(res.getZYHM(),16));
                res.setCYZDBM(rightSpace(res.getCYZDBM(),9));
                res.setCYZDMC(rightSpace(res.getCYZDMC(),28));
                re.add(res);
            }
        }
        return re;
    }

    /**
     * 明细项目细分库
     * @param date
     * @param mzlb
     * @return
     */
    public List<MxscMxxmxfkResp> queryMxscMxxmxfk(String date, String mzlb){
        List<MxscMxxmxfkResp> re = new ArrayList<MxscMxxmxfkResp>();
        List<MxscMxxmxfkResp> msyjList = shybbbDao.queryOpyjsql(date, mzlb);
        if(msyjList.size()>0) {
            for (MxscMxxmxfkResp res : msyjList) {
                res.setLSH(rightSpace(res.getLSH(),16));
                res.setJZLSH(rightSpace(res.getJZLSH()!=null?res.getJZLSH():"",32));
                res.setMXXMBM(rightSpace(res.getMXXMBM()!=null?res.getMXXMBM():"",15));
                res.setXFLSH(rightSpace(res.getXFLSH()!=null?res.getXFLSH():"",4));
                res.setMXXMZLBM(rightSpace(res.getMXXMZLBM()!=null?res.getMXXMZLBM():"",15));
                res.setMXXMMC(rightSpace(res.getMXXMMC()!=null?res.getMXXMMC():"",30));
                res.setMXXMDW(rightSpace(res.getMXXMDW()!=null?res.getMXXMDW():"",4));
                res.setMXXMDJ(number_format_I(res.getMXXMDJ()));
                res.setMXXMSL(number_format_K(res.getMXXMSL()));
                res.setMXXMJE(number_format_I(res.getMXXMJE()));
                res.setBXBZ(rightSpace(res.getBXBZ()!=null?res.getBXBZ():"",1));
                res.setTYMC(rightSpace(res.getTYMC()!=null?res.getTYMC():"",60));
                res.setZCZH(rightSpace(res.getZCZH()!=null?res.getZCZH():"",100));
                res.setMXXMGG(rightSpace(res.getMXXMGG()!=null?res.getMXXMGG():"",120));
                res.setTFBZ(rightSpace(res.getTFBZ()!=null?res.getTFBZ():"",1));
                res.setXMSYRQ(rightSpace(res.getXMSYRQ()!=null?res.getXMSYRQ():"",8));
                re.add(res);
            }
        }
        return re;
    }

    public PageInfo<CzptSbbResp> QueryYbCityNormalList(ShybbbReq req) {
        logger.info("城镇普通上报" );
        long l = opMzlbDao.findYbmcCount(req.getMzlb());
        if(l>1){
            throw BaseException.create("ERROR_SHYB_0041");
        }
        PageInfo<CzptSbbResp> resp = PageHelper.startPage(req.getPageNum(),req.getPageSize())
                .doSelectPageInfo(()->shybbbDao.queryYbCityNormalList(req));
        return resp;
    }
    public String QueryYbCityNormalListdc(ShybbbReq req) {
        logger.info("城镇普通上报导出" );
        String path = "";
        LinkedList<CzptSbbResp> resp = shybbbDao.queryYbCityNormalList(req);
        if(resp.size()<1){
            throw BaseException.create("ERROR_SHYB_0042");
        }
        long l = opMzlbDao.findYbmcCount(req.getMzlb());
        if(l>1){
            throw BaseException.create("ERROR_SHYB_0041");
        }
        Map<String,Object> opybmc = opMzlbDao.getYbmc(req.getMzlb());
        String ybmc = opybmc.get("ybmc")+"";
        try {
            LinkedList<Map<String,Object>> czptList = BSPHISUtil.LinkedListObjToMap(resp);
            if(czptList.size()>0){
                Map<String,Object> czptMap = czptList.get(0);
                czptMap.remove("sortType");
                czptMap.remove("pageNum");
                czptMap.remove("sortColumns");
                czptMap.remove("pageSize");
                int i = czptList.get(0).size();
                path = dbfWriter(i,czptList,req.getDaa(),ybmc,"YK","1");
            }
        } catch (Exception e) {
            throw BaseException.create("ERROR_SHYB_0040");
        }
        return path;
    }
    public String QueryYbCityNormalListdcs(ShybbbReq req,HttpServletResponse response) {
        logger.info("城镇普通上报导出" );
        String path = "";
        LinkedList<CzptSbbResp> resp = shybbbDao.queryYbCityNormalList(req);
        if(resp.size()<1){
            throw BaseException.create("ERROR_SHYB_0042");
        }
        long l = opMzlbDao.findYbmcCount(req.getMzlb());
        if(l>1){
            throw BaseException.create("ERROR_SHYB_0041");
        }
        Map<String,Object> opybmc = opMzlbDao.getYbmc(req.getMzlb());
        String ybmc = opybmc.get("ybmc")+"";
        try {
            LinkedList<Map<String,Object>> czptList = BSPHISUtil.LinkedListObjToMap(resp);
            if(czptList.size()>0){
                Map<String,Object> czptMap = czptList.get(0);
                czptMap.remove("sortType");
                czptMap.remove("pageNum");
                czptMap.remove("sortColumns");
                czptMap.remove("pageSize");
                int i = czptList.get(0).size();
                path = dbfWriters(i,czptList,req.getDaa(),ybmc,"YK","1",response);
            }
        } catch (Exception e) {
            throw BaseException.create("ERROR_SHYB_0040");
        }
        return path;
    }


    public PageInfo<CzdbSbbResp> QueryYbDbCityNormalList(ShybbbReq req) {
        logger.info("城镇大病上报" );
        PageInfo<CzdbSbbResp> resp = PageHelper.startPage(req.getPageNum(),req.getPageSize())
                .doSelectPageInfo(()->shybbbDao.queryYbDbCityNormalList(req));
        return resp;
    }
    public String QueryYbDbCityNormalListdc(ShybbbReq req) {
        logger.info("城镇大病上报导出" );
        String path = "";
        LinkedList<CzdbSbbResp> resp = shybbbDao.queryYbDbCityNormalList(req);
        if(resp.size()<1){
            throw BaseException.create("ERROR_SHYB_0042");
        }
        long l = opMzlbDao.findYbmcCount(req.getMzlb());
        if(l>1){
            throw BaseException.create("ERROR_SHYB_0041");
        }
        Map<String,Object> opybmc = opMzlbDao.getYbmc(req.getMzlb());
        String ybmc = opybmc.get("ybmc")+"";
        try {
            LinkedList<Map<String,Object>> czptList = BSPHISUtil.LinkedListObjToMap(resp);
            if(czptList.size()>0) {
                Map<String, Object> czptMap = czptList.get(0);
                czptMap.remove("sortType");
                czptMap.remove("pageNum");
                czptMap.remove("sortColumns");
                czptMap.remove("pageSize");
                int i = czptList.get(0).size();
                path = dbfWriter(i, czptList, req.getDaa(), ybmc,"YK","2");
            }
        } catch (Exception e) {
            throw BaseException.create("ERROR_SHYB_0040");
        }
        return path;
    }
    public String QueryYbDbCityNormalListdcs(ShybbbReq req,HttpServletResponse response) {
        logger.info("城镇大病上报导出" );
        String path = "";
        LinkedList<CzdbSbbResp> resp = shybbbDao.queryYbDbCityNormalList(req);
        if(resp.size()<1){
            throw BaseException.create("ERROR_SHYB_0042");
        }
        long l = opMzlbDao.findYbmcCount(req.getMzlb());
        if(l>1){
            throw BaseException.create("ERROR_SHYB_0041");
        }
        Map<String,Object> opybmc = opMzlbDao.getYbmc(req.getMzlb());
        String ybmc = opybmc.get("ybmc")+"";
        try {
            LinkedList<Map<String,Object>> czptList = BSPHISUtil.LinkedListObjToMap(resp);
            if(czptList.size()>0) {
                Map<String, Object> czptMap = czptList.get(0);
                czptMap.remove("sortType");
                czptMap.remove("pageNum");
                czptMap.remove("sortColumns");
                czptMap.remove("pageSize");
                int i = czptList.get(0).size();
                path = dbfWriters(i, czptList, req.getDaa(), ybmc,"YK","2",response);
            }
        } catch (Exception e) {
            throw BaseException.create("ERROR_SHYB_0040");
        }
        return path;
    }

    public PageInfo<CzzySbbResp> QueryYbZyCityNormalList(ShybbbReq req) {
        logger.info("城镇住院上报" );
        PageInfo<CzzySbbResp> resp = PageHelper.startPage(req.getPageNum(),req.getPageSize())
                .doSelectPageInfo(()->shybbbDao.queryYbZyCityNormalList(req));
        return resp;
    }
    public String QueryYbZyCityNormalListdc(ShybbbReq req) {
        logger.info("城镇住院上报导出" );
        String path = "";
        req.setDateto(req.getDateto()+" 23:59:59");
        LinkedList<CzzySbbResp> resp = shybbbDao.queryYbZyCityNormalList(req);
        if(resp.size()<1){
            throw BaseException.create("ERROR_SHYB_0042");
        }
        long l = opMzlbDao.findYbmcCount(req.getMzlb());
        if(l>1){
            throw BaseException.create("ERROR_SHYB_0041");
        }
        Map<String,Object> opybmc = opMzlbDao.getYbmc(req.getMzlb());
        String ybmc = opybmc.get("ybmc")+"";
        try {
            LinkedList<Map<String,Object>> czptList = BSPHISUtil.LinkedListObjToMap(resp);
            if(czptList.size()>0) {
                Map<String, Object> czptMap = czptList.get(0);
                czptMap.remove("sortType");
                czptMap.remove("pageNum");
                czptMap.remove("sortColumns");
                czptMap.remove("pageSize");
                int i = czptList.get(0).size();
                path = dbfWriter(i, czptList, req.getDaa(), ybmc,"YK","3");
            }
        } catch (Exception e) {
            throw BaseException.create("ERROR_SHYB_0040");
        }
        return path;
    }
    public String QueryYbZyCityNormalListdcs(ShybbbReq req,HttpServletResponse response) {
        logger.info("城镇住院上报导出" );
        String path = "";
        req.setDateto(req.getDateto()+" 23:59:59");
        LinkedList<CzzySbbResp> resp = shybbbDao.queryYbZyCityNormalList(req);
        if(resp.size()<1){
            throw BaseException.create("ERROR_SHYB_0042");
        }
        long l = opMzlbDao.findYbmcCount(req.getMzlb());
        if(l>1){
            throw BaseException.create("ERROR_SHYB_0041");
        }
        Map<String,Object> opybmc = opMzlbDao.getYbmc(req.getMzlb());
        String ybmc = opybmc.get("ybmc")+"";
        try {
            LinkedList<Map<String,Object>> czptList = BSPHISUtil.LinkedListObjToMap(resp);
            if(czptList.size()>0) {
                Map<String, Object> czptMap = czptList.get(0);
                czptMap.remove("sortType");
                czptMap.remove("pageNum");
                czptMap.remove("sortColumns");
                czptMap.remove("pageSize");
                int i = czptList.get(0).size();
                path = dbfWriters(i, czptList, req.getDaa(), ybmc,"YK","3", response);
            }
        } catch (Exception e) {
            throw BaseException.create("ERROR_SHYB_0040");
        }
        return path;
    }

    public PageInfo<CztsSbbResp> QueryYbTsCityNormalList(ShybbbReq req) {
        logger.info("城镇特殊上报" );
        PageInfo<CztsSbbResp> resp = PageHelper.startPage(req.getPageNum(),req.getPageSize())
                .doSelectPageInfo(()->shybbbDao.queryYbTsCityNormalList(req));
        return resp;
    }
    public String QueryYbTsCityNormalListdc(ShybbbReq req) {
        logger.info("城镇特殊上报导出" );
        String path = "";
        LinkedList<CztsSbbResp> resp = shybbbDao.queryYbTsCityNormalList(req);
        if(resp.size()<1){
            throw BaseException.create("ERROR_SHYB_0042");
        }
        long l = opMzlbDao.findYbmcCount(req.getMzlb());
        if(l>1){
            throw BaseException.create("ERROR_SHYB_0041");
        }
        Map<String,Object> opybmc = opMzlbDao.getYbmc(req.getMzlb());
        String ybmc = opybmc.get("ybmc")+"";
        try {
            LinkedList<Map<String,Object>> czptList = BSPHISUtil.LinkedListObjToMap(resp);
            if(czptList.size()>0) {
                Map<String, Object> czptMap = czptList.get(0);
                czptMap.remove("sortType");
                czptMap.remove("pageNum");
                czptMap.remove("sortColumns");
                czptMap.remove("pageSize");
                int i = czptList.get(0).size();
                path = dbfWriter(i, czptList, req.getDaa(), ybmc,"YK","4");
            }
        } catch (Exception e) {
            throw BaseException.create("ERROR_SHYB_0040");
        }
        return path;
    }
    public String QueryYbTsCityNormalListdcs(ShybbbReq req,HttpServletResponse response) {
        logger.info("城镇特殊上报导出" );
        String path = "";
        LinkedList<CztsSbbResp> resp = shybbbDao.queryYbTsCityNormalList(req);
        if(resp.size()<1){
            throw BaseException.create("ERROR_SHYB_0042");
        }
        long l = opMzlbDao.findYbmcCount(req.getMzlb());
        if(l>1){
            throw BaseException.create("ERROR_SHYB_0041");
        }
        Map<String,Object> opybmc = opMzlbDao.getYbmc(req.getMzlb());
        String ybmc = opybmc.get("ybmc")+"";
        try {
            LinkedList<Map<String,Object>> czptList = BSPHISUtil.LinkedListObjToMap(resp);
            if(czptList.size()>0) {
                Map<String, Object> czptMap = czptList.get(0);
                czptMap.remove("sortType");
                czptMap.remove("pageNum");
                czptMap.remove("sortColumns");
                czptMap.remove("pageSize");
                int i = czptList.get(0).size();
                path = dbfWriters(i, czptList, req.getDaa(), ybmc,"YK","4",response);
            }
        } catch (Exception e) {
            throw BaseException.create("ERROR_SHYB_0040");
        }
        return path;
    }

    /**
     * 城镇特殊住院上报
     * @param req
     * @return
     */
    public PageInfo<CztszySbbResp> queryYbTsZyCityNormalList(ShybbbReq req) {
        logger.info("城镇特殊住院上报" );
        PageInfo<CztszySbbResp> resp = PageHelper.startPage(req.getPageNum(),req.getPageSize())
                .doSelectPageInfo(()->shybbbDao.queryYbTszyCityNormalList(req));
        return resp;
    }

    public String QueryYbTsZyCityNormalListdc(ShybbbReq req) {
        logger.info("城镇特殊住院上报表导出" );
        String path = "";
        LinkedList<CztsSbbResp> resp = shybbbDao.queryYbTsCityNormalList(req);
        if(resp.size()<1){
            throw BaseException.create("ERROR_SHYB_0042");
        }
        long l = opMzlbDao.findYbmcCount(req.getMzlb());
        if(l>1){
            throw BaseException.create("ERROR_SHYB_0041");
        }
        Map<String,Object> opybmc = opMzlbDao.getYbmc(req.getMzlb());
        String ybmc = opybmc.get("ybmc")+"";
        try {
            LinkedList<Map<String,Object>> czptList = BSPHISUtil.LinkedListObjToMap(resp);
            if(czptList.size()>0) {
                Map<String, Object> czptMap = czptList.get(0);
                czptMap.remove("sortType");
                czptMap.remove("pageNum");
                czptMap.remove("sortColumns");
                czptMap.remove("pageSize");
                int i = czptList.get(0).size();
                path = dbfWriter(i, czptList, req.getDaa(), ybmc,"YK","5");
            }
        } catch (Exception e) {
            throw BaseException.create("ERROR_SHYB_0040");
        }
        return path;
    }
    public String QueryYbTsZyCityNormalListdcs(ShybbbReq req, HttpServletResponse response) {
        logger.info("城镇特殊住院上报表导出" );
        String path = "";
        LinkedList<CztsSbbResp> resp = shybbbDao.queryYbTsCityNormalList(req);
        if(resp.size()<1){
            throw BaseException.create("ERROR_SHYB_0042");
        }
        long l = opMzlbDao.findYbmcCount(req.getMzlb());
        if(l>1){
            throw BaseException.create("ERROR_SHYB_0041");
        }
        Map<String,Object> opybmc = opMzlbDao.getYbmc(req.getMzlb());
        String ybmc = opybmc.get("ybmc")+"";
        try {
            LinkedList<Map<String,Object>> czptList = BSPHISUtil.LinkedListObjToMap(resp);
            if(czptList.size()>0) {
                Map<String, Object> czptMap = czptList.get(0);
                czptMap.remove("sortType");
                czptMap.remove("pageNum");
                czptMap.remove("sortColumns");
                czptMap.remove("pageSize");
                int i = czptList.get(0).size();
                path = dbfWriters(i, czptList, req.getDaa(), ybmc,"YK","5",response);
            }
        } catch (Exception e) {
            throw BaseException.create("ERROR_SHYB_0040");
        }
        return path;
    }

    public PageInfo<CzjmjsSbbResp> QueryYbJmCityNormalList(ShybbbReq req) {
        logger.info("城镇居民上报" );
        PageInfo<CzjmjsSbbResp> resp = PageHelper.startPage(req.getPageNum(),req.getPageSize())
                .doSelectPageInfo(()->shybbbDao.queryYbJmCityNormalList(req));
        return resp;
    }
    public String QueryYbJmCityNormalListdc(ShybbbReq req) {
        logger.info("城镇居民上报导出" );
        String path = "";
        LinkedList<CzjmjsSbbResp> resp = shybbbDao.queryYbJmCityNormalList(req);
        if(resp.size()<1){
            throw BaseException.create("ERROR_SHYB_0042");
        }
        long l = opMzlbDao.findYbmcCount(req.getMzlb());
        if(l>1){
            throw BaseException.create("ERROR_SHYB_0041");
        }
        Map<String,Object> opybmc = opMzlbDao.getYbmc(req.getMzlb());
        String ybmc = opybmc.get("ybmc")+"";
        try {
            LinkedList<Map<String,Object>> czptList = BSPHISUtil.LinkedListObjToMap(resp);
            if(czptList.size()>0) {
                Map<String, Object> czptMap = czptList.get(0);
                czptMap.remove("sortType");
                czptMap.remove("pageNum");
                czptMap.remove("sortColumns");
                czptMap.remove("pageSize");
                int i = czptList.get(0).size();
                path = dbfWriter(i, czptList, req.getDaa(), ybmc,"YK","Q");
            }
        } catch (Exception e) {
            throw BaseException.create("ERROR_SHYB_0040");
        }
        return path;
    }
    public String QueryYbJmCityNormalListdcs(ShybbbReq req,HttpServletResponse response) {
        logger.info("城镇居民上报导出" );
        String path = "";
        LinkedList<CzjmjsSbbResp> resp = shybbbDao.queryYbJmCityNormalList(req);
        if(resp.size()<1){
            throw BaseException.create("ERROR_SHYB_0042");
        }
        long l = opMzlbDao.findYbmcCount(req.getMzlb());
        if(l>1){
            throw BaseException.create("ERROR_SHYB_0041");
        }
        Map<String,Object> opybmc = opMzlbDao.getYbmc(req.getMzlb());
        String ybmc = opybmc.get("ybmc")+"";
        try {
            LinkedList<Map<String,Object>> czptList = BSPHISUtil.LinkedListObjToMap(resp);
            if(czptList.size()>0) {
                Map<String, Object> czptMap = czptList.get(0);
                czptMap.remove("sortType");
                czptMap.remove("pageNum");
                czptMap.remove("sortColumns");
                czptMap.remove("pageSize");
                int i = czptList.get(0).size();
                path = dbfWriters(i, czptList, req.getDaa(), ybmc,"YK","Q",response);
            }
        } catch (Exception e) {
            throw BaseException.create("ERROR_SHYB_0040");
        }
        return path;
    }

    public PageInfo<CzjmzySbbResp> QueryYbJmzyCityNormalList(ShybbbReq req) {
        logger.info("城镇居民住院上报" );
        PageInfo<CzjmzySbbResp> resp = PageHelper.startPage(req.getPageNum(),req.getPageSize())
                .doSelectPageInfo(()->shybbbDao.queryYbJmzyCityNormalList(req));
        return resp;
    }
    public String QueryYbJmzyCityNormalListdc(ShybbbReq req) {
        logger.info("城镇居民住院上报导出" );
        String path = "";
        req.setDateto(req.getDateto()+" 23:59:59");
        LinkedList<CzjmzySbbResp> resp = shybbbDao.queryYbJmzyCityNormalList(req);
        if(resp.size()<1){
            throw BaseException.create("ERROR_SHYB_0042");
        }
        long l = opMzlbDao.findYbmcCount(req.getMzlb());
        if(l>1){
            throw BaseException.create("ERROR_SHYB_0041");
        }
        Map<String,Object> opybmc = opMzlbDao.getYbmc(req.getMzlb());
        String ybmc = opybmc.get("ybmc")+"";
        try {
            LinkedList<Map<String,Object>> czptList = BSPHISUtil.LinkedListObjToMap(resp);
            if(czptList.size()>0) {
                Map<String, Object> czptMap = czptList.get(0);
                czptMap.remove("sortType");
                czptMap.remove("pageNum");
                czptMap.remove("sortColumns");
                czptMap.remove("pageSize");
                int i = czptList.get(0).size();
                path = dbfWriter(i, czptList, req.getDaa(), ybmc,"YK","R");
            }
        } catch (Exception e) {
            throw BaseException.create("ERROR_SHYB_0040");
        }
        return path;
    }
    public String QueryYbJmzyCityNormalListdcs(ShybbbReq req,HttpServletResponse response) {
        logger.info("城镇居民住院上报导出" );
        String path = "";
        req.setDateto(req.getDateto()+" 23:59:59");
        LinkedList<CzjmzySbbResp> resp = shybbbDao.queryYbJmzyCityNormalList(req);
        if(resp.size()<1){
            throw BaseException.create("ERROR_SHYB_0042");
        }
        long l = opMzlbDao.findYbmcCount(req.getMzlb());
        if(l>1){
            throw BaseException.create("ERROR_SHYB_0041");
        }
        Map<String,Object> opybmc = opMzlbDao.getYbmc(req.getMzlb());
        String ybmc = opybmc.get("ybmc")+"";
        try {
            LinkedList<Map<String,Object>> czptList = BSPHISUtil.LinkedListObjToMap(resp);
            if(czptList.size()>0) {
                Map<String, Object> czptMap = czptList.get(0);
                czptMap.remove("sortType");
                czptMap.remove("pageNum");
                czptMap.remove("sortColumns");
                czptMap.remove("pageSize");
                int i = czptList.get(0).size();
                path = dbfWriters(i, czptList, req.getDaa(), ybmc,"YK","R",response);
            }
        } catch (Exception e) {
            throw BaseException.create("ERROR_SHYB_0040");
        }
        return path;
    }

    public PageInfo<YbOpGsbxbResp> QueryYbGsbxopList(ShybbbReq req) {
        logger.info("工伤保险门诊上报" );
        PageInfo<YbOpGsbxbResp> resp = PageHelper.startPage(req.getPageNum(),req.getPageSize())
                .doSelectPageInfo(()->shybbbDao.queryYbGsbxopList(req));
        return resp;
    }
    public String QueryYbGsbxopListdc(ShybbbReq req) {
        logger.info("工伤保险门诊上报导出" );
        String path = "";
        LinkedList<YbOpGsbxbResp> resp = shybbbDao.queryYbGsbxopList(req);
        if(resp.size()<1){
            throw BaseException.create("ERROR_SHYB_0042");
        }
        long l = opMzlbDao.findYbmcCount(req.getMzlb());
        if(l>1){
            throw BaseException.create("ERROR_SHYB_0041");
        }
        Map<String,Object> opybmc = opMzlbDao.getYbmc(req.getMzlb());
        String ybmc = opybmc.get("ybmc")+"";
        try {
            LinkedList<Map<String,Object>> czptList = BSPHISUtil.LinkedListObjToMap(resp);
            if(czptList.size()>0) {
                Map<String, Object> czptMap = czptList.get(0);
                czptMap.remove("sortType");
                czptMap.remove("pageNum");
                czptMap.remove("sortColumns");
                czptMap.remove("pageSize");
                int i = czptList.get(0).size();
                path = dbfWriter(i, czptList, req.getDaa(), ybmc,"UK","A");
            }
        } catch (Exception e) {
            throw BaseException.create("ERROR_SHYB_0040");
        }
        return path;
    }

    public PageInfo<YbbbYlhzbkResp> QueryYbYlhzbkList(ShybbbReq req) {
        logger.info("医疗互助帮困" );
        PageInfo<YbbbYlhzbkResp> resp = PageHelper.startPage(req.getPageNum(),req.getPageSize())
                .doSelectPageInfo(()->shybbbDao.queryYbYlhzbkList(req));
        return resp;
    }
    public String QueryYbYlhzbkListdc(ShybbbReq req) {
        logger.info("医疗互助帮困导出" );
        String path = "";
        LinkedList<YbbbYlhzbkResp> resp = shybbbDao.queryYbYlhzbkList(req);
        if(resp.size()<1){
            throw BaseException.create("ERROR_SHYB_0042");
        }
        long l = opMzlbDao.findYbmcCount(req.getMzlb());
        if(l>1){
            throw BaseException.create("ERROR_SHYB_0041");
        }
        Map<String,Object> opybmc = opMzlbDao.getYbmc(req.getMzlb());
        String ybmc = opybmc.get("ybmc")+"";
        try {
            LinkedList<Map<String,Object>> czptList = BSPHISUtil.LinkedListObjToMap(resp);
            if(czptList.size()>0) {
                Map<String, Object> czptMap = czptList.get(0);
                czptMap.remove("sortType");
                czptMap.remove("pageNum");
                czptMap.remove("sortColumns");
                czptMap.remove("pageSize");
                int i = czptList.get(0).size();
                path = dbfWriter(i, czptList, req.getDaa(), ybmc,"YK","S");
            }
        } catch (Exception e) {
            throw BaseException.create("ERROR_SHYB_0040");
        }
        return path;
    }
    public String QueryYbYlhzbkListdcs(ShybbbReq req,HttpServletResponse response) {
        logger.info("医疗互助帮困导出" );
        String path = "";
        LinkedList<YbbbYlhzbkResp> resp = shybbbDao.queryYbYlhzbkList(req);
        if(resp.size()<1){
            throw BaseException.create("ERROR_SHYB_0042");
        }
        long l = opMzlbDao.findYbmcCount(req.getMzlb());
        if(l>1){
            throw BaseException.create("ERROR_SHYB_0041");
        }
        Map<String,Object> opybmc = opMzlbDao.getYbmc(req.getMzlb());
        String ybmc = opybmc.get("ybmc")+"";
        try {
            LinkedList<Map<String,Object>> czptList = BSPHISUtil.LinkedListObjToMap(resp);
            if(czptList.size()>0) {
                Map<String, Object> czptMap = czptList.get(0);
                czptMap.remove("sortType");
                czptMap.remove("pageNum");
                czptMap.remove("sortColumns");
                czptMap.remove("pageSize");
                int i = czptList.get(0).size();
                path = dbfWriters(i, czptList, req.getDaa(), ybmc,"YK","S",response);
            }
        } catch (Exception e) {
            throw BaseException.create("ERROR_SHYB_0040");
        }
        return path;
    }

    public PageInfo<ybbbpkjsbResp> QueryYbPkjsbList(ShybbbReq req) {
        logger.info("贫困精神病" );
        PageInfo<ybbbpkjsbResp> resp = PageHelper.startPage(req.getPageNum(),req.getPageSize())
                .doSelectPageInfo(()->shybbbDao.queryYbPkjsbList(req));
        return resp;
    }
    public String QueryYbPkjsbListdc(ShybbbReq req) {
        logger.info("贫困精神病导出" );
        String path = "";
        LinkedList<ybbbpkjsbResp> resp = shybbbDao.queryYbPkjsbList(req);
        try {
            LinkedList<Map<String,Object>> czptList = BSPHISUtil.LinkedListObjToMap(resp);
            if(czptList.size()>0) {
                Map<String, Object> czptMap = czptList.get(0);
                czptMap.remove("sortType");
                czptMap.remove("pageNum");
                czptMap.remove("sortColumns");
                czptMap.remove("pageSize");
                int i = czptList.get(0).size();
                path = dbfWriter(i, czptList, req.getDaa(), "","081","PKJSB");
            }
        } catch (Exception e) {
            throw BaseException.create("ERROR_SHYB_0040");
        }
        return path;
    }

    public PageInfo<YbbbMzbkResp> QueryYbMzbkList(ShybbbReq req) {
        logger.info("民政帮困");
        PageInfo<YbbbMzbkResp> resp = PageHelper.startPage(req.getPageNum(),req.getPageSize())
                .doSelectPageInfo(()->shybbbDao.queryYbMzbkList(req));
        return resp;
    }
    public String QueryYbMzbkListdc(ShybbbReq req) {
        logger.info("民政帮困导出" );
        String path = "";
        LinkedList<YbbbMzbkResp> resp = shybbbDao.queryYbMzbkList(req);
        if(resp.size()<1){
            throw BaseException.create("ERROR_SHYB_0042");
        }
        long l = opMzlbDao.findYbmcCount(req.getMzlb());
        if(l>1){
            throw BaseException.create("ERROR_SHYB_0041");
        }
        Map<String,Object> opybmc = opMzlbDao.getYbmc(req.getMzlb());
        String ybmc = opybmc.get("ybmc")+"";
        try {
            LinkedList<Map<String,Object>> czptList = BSPHISUtil.LinkedListObjToMap(resp);
            if(czptList.size()>0) {
                Map<String, Object> czptMap = czptList.get(0);
                czptMap.remove("sortType");
                czptMap.remove("pageNum");
                czptMap.remove("sortColumns");
                czptMap.remove("pageSize");
                int i = czptList.get(0).size();
                path = dbfWriter(i, czptList, req.getDaa(), ybmc,"UK","MZBK");
            }
        } catch (Exception e) {
            throw BaseException.create("ERROR_SHYB_0040");
        }
        return path;
    }



    public PageInfo<YbbbFssscopResp> QueryYbFssscopList(ShybbbReq req) {
        logger.info("非实时上传门诊费用明细库");
        PageInfo<YbbbFssscopResp> resp = PageHelper.startPage(req.getPageNum(),req.getPageSize())
                .doSelectPageInfo(()->shybbbDao.queryYbFssscopList(req));
        return resp;
    }
    public String QueryYbFssscopListdc(ShybbbReq req) {
        logger.info("非实时上传门诊导出" );
        String path = "";
        SysUser user= new SysUser();
        LinkedList<YbbbFssscopResp> resp = shybbbDao.queryYbFssscopList(req);
        if(resp.size()<1){
            throw BaseException.create("ERROR_SHYB_0042");
        }
        long l = opMzlbDao.findYbmcCount(req.getMzlb());
        if(l>1){
            throw BaseException.create("ERROR_SHYB_0041");
        }
        try {
            LinkedList<Map<String,Object>> czptList = BSPHISUtil.LinkedListObjToMap(resp);
            if(czptList.size()>0) {
                Map<String,Object> opybmc = opMzlbDao.getYbmc(req.getMzlb());
                String ybjgid = opybmc.get("ybjgid")+"";
                String tempDirName = System.getProperty("java.io.tmpdir");
                String queryDate = req.getDaa();
                path =tempDirName + "M" + ybjgid + queryDate.substring(2,4) + queryDate.substring(5,7) + "I.TXT";
                String record = "";
                for(Map<String, Object> map : czptList){
                    record += this.rightSpace(ybjgid, 11) +//1
                            this.rightSpace(map.get("YBID").toString(),10) + //2
                            this.rightSpace(map.get("MZHM").toString(),16) + //3
                            this.rightSpace(map.get("BXID").toString(),18) + //4
                            this.rightSpace(map.get("KSID").toString(),5) + //5
                            this.rightSpace(map.get("KSNA").toString(),10) + //6
                            this.rightSpace(map.get("YSGH")!=null?String.valueOf(map.get("YSGH")):"",6) + //7
                            this.rightSpace(map.get("YSXM")!=null?String.valueOf(map.get("YSXM")):"",12) + //8
                            map.get("CFHM").toString() + //9
                            map.get("FYLB").toString() + //10
                            this.rightSpace(map.get("XMBM")!=null?String.valueOf(map.get("XMBM")):"",15) + //11
                            this.rightSpace(map.get("XMMC")!=null?String.valueOf(map.get("XMMC")):"",30) + //12
                            this.rightSpace(map.get("XMDW")!=null?String.valueOf(map.get("XMDW")):"",4) + //13
                            this.number_format_I(map.get("XMDJ").toString()) +//14
                            this.rightSpace(map.get("TYMC")!=null?String.valueOf(map.get("TYMC")):"",60) +//15
                            this.rightSpace(map.get("ZCZH")!=null?String.valueOf(map.get("ZCZH")):"",100) +//16
                            this.rightSpace(map.get("MXGG")!=null?String.valueOf(map.get("MXGG")):"",120) +//17
                            this.number_format_K(map.get("XMSL").toString()) +//18
                            this.number_format_I(map.get("XMJE").toString()) +//19
                            map.get("JYTOTFY").toString() +//20
                            map.get("TOTFY").toString() +//21
                            this.rightSpace(map.get("BXBZ").toString(),1) +//22
                            this.rightSpace(map.get("RYLX").toString(),1) +//23
                            this.rightSpace(map.get("TFBZ").toString(),1) +//24
                            this.rightSpace(map.get("JZDATE").toString(),8) +//25
                            "\r\n";
                }

                writeRecord(path,record);
            }
        } catch (Exception e) {
            throw BaseException.create("ERROR_SHYB_0040");
        }
        path = path.replace("\\","/");
        return path;
    }

    public PageInfo<YbbbFssscimResp> QueryYbFssscimList(ShybbbReq req) {
        logger.info("非实时上传住院费用明细库");
        PageInfo<YbbbFssscimResp> resp = PageHelper.startPage(req.getPageNum(),req.getPageSize())
                .doSelectPageInfo(()->shybbbDao.queryYbFssscimList(req));
        return resp;
    }
    public String QueryYbFssscimListdc(ShybbbReq req) {
        logger.info("非实时上传住院导出" );
        String path = "";
        SysUser user= new SysUser();
        LinkedList<YbbbFssscimResp> resp = shybbbDao.queryYbFssscimList(req);
        if(resp.size()<1){
            throw BaseException.create("ERROR_SHYB_0042");
        }
        long l = opMzlbDao.findYbmcCount(req.getMzlb());
        if(l>1){
            throw BaseException.create("ERROR_SHYB_0041");
        }
        try {
            LinkedList<Map<String,Object>> czptList = BSPHISUtil.LinkedListObjToMap(resp);
            if(czptList.size()>0) {
                Map<String,Object> opybmc = opMzlbDao.getYbmc(req.getMzlb());
                String ybjgid = opybmc.get("ybjgid")+"";
                String tempDirName = System.getProperty("java.io.tmpdir");
                String queryDate = req.getDaa();
                path =tempDirName + "M" + ybjgid + queryDate.substring(2,4) + queryDate.substring(5,7) + "J.TXT";
                String record = "";
                for(Map<String, Object> map : czptList){
                    record += this.rightSpace(ybjgid, 11) +//1
                            this.rightSpace(map.get("YBID").toString(),10) + //2
                            this.rightSpace(map.get("ZYID").toString(),16) + //3
                            this.rightSpace(map.get("BXID").toString(),18) + //4
                            this.rightSpace(map.get("KSID").toString(),5) + //5
                            this.rightSpace(map.get("KSNA").toString(),10) + //6
                            this.rightSpace(map.get("YSGH")!=null?String.valueOf(map.get("YSGH")):"",6) + //7
                            this.rightSpace(map.get("YSXM")!=null?String.valueOf(map.get("YSXM")):"",12) + //8
                            map.get("FYLB").toString() + //10
                            this.rightSpace(map.get("XMBM")!=null?String.valueOf(map.get("XMBM")):"",15) + //11
                            this.rightSpace(map.get("XMMC")!=null?String.valueOf(map.get("XMMC")):"",30) + //12
                            this.rightSpace(map.get("XMDW")!=null?String.valueOf(map.get("XMDW")):"",4) + //13
                            this.number_format_I(map.get("XMDJ").toString()) +//14
                            this.rightSpace(map.get("TYMC")!=null?String.valueOf(map.get("TYMC")):"",60) +//15
                            this.rightSpace(map.get("ZCZH")!=null?String.valueOf(map.get("ZCZH")):"",100) +//16
                            this.rightSpace(map.get("MXGG")!=null?String.valueOf(map.get("MXGG")):"",120) +//17
                            this.number_format_K(map.get("XMSL").toString()) +//18
                            this.number_format_I(map.get("XMJE").toString()) +//19
                            map.get("JYTOTFY").toString() +//20
                            map.get("TOTFY").toString() +//21
                            this.rightSpace(map.get("BXBZ").toString(),1) +//22
                            this.rightSpace(map.get("RYLX").toString(),1) +//23
                            this.rightSpace(map.get("XMSYRQ").toString(),8) +//24
                            this.rightSpace(map.get("TFBZ").toString(),1) +//25
                            "\r\n";
                }

                writeRecord(path,record);
            }
        } catch (Exception e) {
            throw BaseException.create("ERROR_SHYB_0040");
        }
        path = path.replace("\\","/");
        return path;
    }

    public PageInfo<YbbbFssscimjlResp> QueryYbFssscimjlList(ShybbbReq req) {
        logger.info("非实时上传住院结算记录");
        PageInfo<YbbbFssscimjlResp> resp = PageHelper.startPage(req.getPageNum(),req.getPageSize())
                .doSelectPageInfo(()->shybbbDao.queryYbFssscimjlList(req));
        return resp;
    }
    public String QueryYbFssscimjlListdc(ShybbbReq req) {
        logger.info("非实时上传住院结算记录导出" );
        String path = "";
        SysUser user= new SysUser();
        LinkedList<YbbbFssscimResp> resp = shybbbDao.queryYbFssscimjlList(req);
        if(resp.size()<1){
            throw BaseException.create("ERROR_SHYB_0042");
        }
        long l = opMzlbDao.findYbmcCount(req.getMzlb());
        if(l>1){
            throw BaseException.create("ERROR_SHYB_0041");
        }
        try {
            LinkedList<Map<String,Object>> czptList = BSPHISUtil.LinkedListObjToMap(resp);
            if(czptList.size()>0) {
                Map<String,Object> opybmc = opMzlbDao.getYbmc(req.getMzlb());
                String ybjgid = opybmc.get("ybjgid")+"";
                String tempDirName = System.getProperty("java.io.tmpdir");
                String queryDate = req.getDaa();
                path =tempDirName + "M" + ybjgid + queryDate.substring(2,4) + queryDate.substring(5,7) + "K.TXT";
                String record = "";
                for(Map<String, Object> map : czptList){
                    record += this.rightSpace(ybjgid, 11) +//1
                            this.rightSpace(map.get("RYLX").toString(),1) + //2
                            this.rightSpace(map.get("YBID").toString(),10) + //2
                            this.rightSpace(map.get("ZYID").toString(),16) + //3
                            this.number_format_J(map.get("ZYTS").toString()) + //4
                            this.rightSpace(map.get("JSKSRQ").toString(),8) + //5
                            this.rightSpace(map.get("RYZDBM").toString(),7) + //6
                            this.rightSpace(map.get("RYZDMC").toString(),30) + //6
                            this.rightSpace(map.get("JSJSRQ").toString(),8) + //6
                            this.rightSpace(map.get("ZYJGBZ").toString(),1) + //6
                            this.rightSpace(map.get("CYBZ").toString(),1) + //6
                            this.rightSpace(map.get("CYZDBM").toString(),7) + //6
                            this.rightSpace(map.get("CYZDMC").toString(),30) + //6
                            this.rightSpace(map.get("ZLJG").toString(),1) + //6
                            "\r\n";
                }
                writeRecord(path,record);
            }
        } catch (Exception e) {
            throw BaseException.create("ERROR_SHYB_0040");
        }
        path = path.replace("\\","/");
        return path;
    }

    public PageInfo<YbbbYdybResp> QueryYbYdybList(ShybbbReq req) {
        logger.info("异地医保(住院)");
        PageInfo<YbbbYdybResp> resp = PageHelper.startPage(req.getPageNum(),req.getPageSize())
                .doSelectPageInfo(()->shybbbDao.queryYbYdybList(req));
        return resp;
    }
    public String QueryYbYdybListdc(ShybbbReq req) {
        logger.info("异地医保(住院)导出" );
        String path = "";
        LinkedList<YbbbYdybResp> resp = shybbbDao.queryYbYdybList(req);
        if(resp.size()<1){
            throw BaseException.create("ERROR_SHYB_0042");
        }
        long l = opMzlbDao.findYbmcCount(req.getMzlb());
        if(l>1){
            throw BaseException.create("ERROR_SHYB_0041");
        }
        Map<String,Object> opybmc = opMzlbDao.getYbmc(req.getMzlb());
        String ybmc = opybmc.get("ybmc")+"";
        try {
            LinkedList<Map<String,Object>> czptList = BSPHISUtil.LinkedListObjToMap(resp);
            if(czptList.size()>0) {
                Map<String, Object> czptMap = czptList.get(0);
                czptMap.remove("sortType");
                czptMap.remove("pageNum");
                czptMap.remove("sortColumns");
                czptMap.remove("pageSize");
                int i = czptList.get(0).size();
                path = dbfWriter(i, czptList, req.getDaa(), ybmc,"YK","X");
            }
        } catch (Exception e) {
            throw BaseException.create("ERROR_SHYB_0040");
        }
        return path;
    }
    public String QueryYbYdybListdcs(ShybbbReq req,HttpServletResponse response) {
        logger.info("异地医保(住院)导出" );
        String path = "";
        LinkedList<YbbbYdybResp> resp = shybbbDao.queryYbYdybList(req);
        if(resp.size()<1){
            throw BaseException.create("ERROR_SHYB_0042");
        }
        long l = opMzlbDao.findYbmcCount(req.getMzlb());
        if(l>1){
            throw BaseException.create("ERROR_SHYB_0041");
        }
        Map<String,Object> opybmc = opMzlbDao.getYbmc(req.getMzlb());
        String ybmc = opybmc.get("ybmc")+"";
        try {
            LinkedList<Map<String,Object>> czptList = BSPHISUtil.LinkedListObjToMap(resp);
            if(czptList.size()>0) {
                Map<String, Object> czptMap = czptList.get(0);
                czptMap.remove("sortType");
                czptMap.remove("pageNum");
                czptMap.remove("sortColumns");
                czptMap.remove("pageSize");
                int i = czptList.get(0).size();
                path = dbfWriters(i, czptList, req.getDaa(), ybmc,"YK","X",response);
            }
        } catch (Exception e) {
            throw BaseException.create("ERROR_SHYB_0040");
        }
        return path;
    }

    public PageInfo<YbbbYdybopResp> QueryYbYdybopList(ShybbbReq req) {
        logger.info("门诊异地医保");
        PageInfo<YbbbYdybopResp> resp = PageHelper.startPage(req.getPageNum(),req.getPageSize())
                .doSelectPageInfo(()->shybbbDao.queryYbYdybopList(req));
        return resp;
    }
    public String QueryYbYdybopListdc(ShybbbReq req) {
        logger.info("异地医保(门诊)导出" );
        String path = "";
        LinkedList<YbbbYdybopResp> resp = shybbbDao.queryYbYdybopList(req);
        if(resp.size()<1){
            throw BaseException.create("ERROR_SHYB_0042");
        }
        long l = opMzlbDao.findYbmcCount(req.getMzlb());
        if(l>1){
            throw BaseException.create("ERROR_SHYB_0041");
        }
        Map<String,Object> opybmc = opMzlbDao.getYbmc(req.getMzlb());
        String ybmc = opybmc.get("ybmc")+"";
        try {
            LinkedList<Map<String,Object>> czptList = BSPHISUtil.LinkedListObjToMap(resp);
            if(czptList.size()>0) {
                Map<String, Object> czptMap = czptList.get(0);
                czptMap.remove("sortType");
                czptMap.remove("pageNum");
                czptMap.remove("sortColumns");
                czptMap.remove("pageSize");
                int i = czptList.get(0).size();
                path = dbfWriter(i, czptList, req.getDaa(), ybmc,"YK","W");
            }
        } catch (Exception e) {
            throw BaseException.create("ERROR_SHYB_0040");
        }
        return path;
    }
    public String QueryYbYdybopListdcs(ShybbbReq req,HttpServletResponse response) {
        logger.info("异地医保(门诊)导出" );
        String path = "";
        LinkedList<YbbbYdybopResp> resp = shybbbDao.queryYbYdybopList(req);
        if(resp.size()<1){
            throw BaseException.create("ERROR_SHYB_0042");
        }
        long l = opMzlbDao.findYbmcCount(req.getMzlb());
        if(l>1){
            throw BaseException.create("ERROR_SHYB_0041");
        }
        Map<String,Object> opybmc = opMzlbDao.getYbmc(req.getMzlb());
        String ybmc = opybmc.get("ybmc")+"";
        try {
            LinkedList<Map<String,Object>> czptList = BSPHISUtil.LinkedListObjToMap(resp);
            if(czptList.size()>0) {
                Map<String, Object> czptMap = czptList.get(0);
                czptMap.remove("sortType");
                czptMap.remove("pageNum");
                czptMap.remove("sortColumns");
                czptMap.remove("pageSize");
                int i = czptList.get(0).size();
                path = dbfWriters(i, czptList, req.getDaa(), ybmc,"YK","W",response);
            }
        } catch (Exception e) {
            throw BaseException.create("ERROR_SHYB_0040");
        }
        return path;
    }

    public PageInfo<LdrkSbbResp> QueryLdrkCityNormalList(ShybbbReq req) {
        logger.info("ldrk");
        PageInfo<LdrkSbbResp> resp = PageHelper.startPage(req.getPageNum(),req.getPageSize())
                .doSelectPageInfo(()->shybbbDao.queryLdrkList(req));
        return resp;
    }


    public ShybOneResp QueryFeehzList(ShybbbReq req,SysUser user) {
        logger.info("费用结算申报汇总表");
        String datefrom = req.getDatefrom();
        BigDecimal total = BigDecimal.ZERO;
        ShybOneResp sr = new ShybOneResp();
        List<String> mzlb_list = new ArrayList<>();
        if(req.getMzlb().contains(",")){
            String[] mzlb = req.getMzlb().split(",");
            for(int i=0;i<mzlb.length;i++){
                mzlb_list.add(mzlb[i]);
            }
        }else {
            mzlb_list.add(req.getMzlb());
        }
        List<Map<String,Object>> list_orgid = shybbbDao.getYbjgdm(mzlb_list.get(0));
        sr.setJgid(list_orgid.get(0).get("ybjgid").toString());

     /*   PageInfo<ShFeehzResp> resp = PageHelper.startPage(req.getPageNum(),req.getPageSize())
                .doSelectPageInfo(()->shybbbDao.queryFeehzList(req.getDatefrom(),req.getDateto(),mzlb_list));*/
        List<Map<String,Object>> list = shybbbDao.queryFeehzLists(req.getDatefrom(),req.getDateto(),mzlb_list);
        List<Map<String,Object>> BS = shybbbDao.getBS();
        if(list.size()>0 ){
            for(int i =0;i<list.size();i++){
                for(int j=0;j<BS.size();j++){
                    if(list.get(i).get("LX").equals(BS.get(j).get("LX")) && list.get(i).get("GRXZ").equals(BS.get(j).get("GRXZ"))){
                        BS.remove(j);
                        BS.add(list.get(i));
                    }
                }
            }
        }
        for(int k=0;k<BS.size();k++){
            total =  total.add(new BigDecimal(String.valueOf(BS.get(k).get("YBZFXJ"))));
        }
        sr.setJes(String.valueOf(total));
        sr.setJeh(BSPHISUtil.changeMoneyUpper(total));

        sr.setJgmc(user.getHospitalName());
        List<ShFeehzResp> BResp = HisUtil.ListToResultSet(BS, new ShFeehzResp());
        sr.setShybfeeres(BResp);
        sr.setQuerydate(datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");


/*        List<Map<String,Object>> list_1 = shybbbDao.queryOpFeeList(req.getDatefrom(),req.getDateto(),mzlb_list);
        List<Map<String,Object>> list_2 = shybbbDao.queryDbFeeList(req.getDatefrom(),req.getDateto(),mzlb_list);
        List<Map<String,Object>> list_3 = shybbbDao.queryImFeeList(req.getDatefrom(),req.getDateto(),mzlb_list);
        List<Map<String,Object>> list_4 = shybbbDao.queryOpSlDbFeeList(req.getDatefrom(),req.getDateto(),mzlb_list);
        List<Map<String,Object>> list_5 = shybbbDao.queryImSlFeeList(req.getDatefrom(),req.getDateto(),mzlb_list);
        List<Map<String,Object>> list_6 = shybbbDao.queryOpTsFeeList(req.getDatefrom(),req.getDateto(),mzlb_list);
        List<Map<String,Object>> list_7 = shybbbDao.queryImTsFeeList(req.getDatefrom(),req.getDateto(),mzlb_list);
        List<Map<String,Object>> list_8 = shybbbDao.queryOpZcbjsFeeList(req.getDatefrom(),req.getDateto(),mzlb_list);
        List<Map<String,Object>> list_9 = shybbbDao.queryXjbtDbFeeList(req.getDatefrom(),req.getDateto(),mzlb_list);
        List<Map<String,Object>> list_10 = shybbbDao.queryXjbtImFeeList(req.getDatefrom(),req.getDateto(),mzlb_list);
        List<Map<String,Object>> list_11 = shybbbDao.queryWqOpFeeList(req.getDatefrom(),req.getDateto(),mzlb_list);
        List<Map<String,Object>> list_12 = shybbbDao.queryWqImFeeList(req.getDatefrom(),req.getDateto(),mzlb_list);
        List<Map<String,Object>> list_13 = shybbbDao.queryJdszOpFeeList(req.getDatefrom(),req.getDateto(),mzlb_list);
        List<Map<String,Object>> list_14 = shybbbDao.queryJdszImFeeList(req.getDatefrom(),req.getDateto(),mzlb_list);
        List<Map<String,Object>> list_15 = shybbbDao.querySgbjOpFeeList(req.getDatefrom(),req.getDateto(),mzlb_list);
        List<Map<String,Object>> list_16 = shybbbDao.querySgbjImFeeList(req.getDatefrom(),req.getDateto(),mzlb_list);
        List<Map<String,Object>> list_17 = shybbbDao.queryTsyxFeeList(req.getDatefrom(),req.getDateto(),mzlb_list);
        List<Map<String,Object>> list_18 = shybbbDao.queryYdImFeeList(req.getDatefrom(),req.getDateto(),mzlb_list);
        List<Map<String,Object>> list_19 = shybbbDao.queryYdOpFeeList(req.getDatefrom(),req.getDateto(),mzlb_list);*/

        return sr;
    }

    public ShybTwoResp QueryFeehzTwoList(ShybbbReq req,SysUser user) {
        logger.info("费用结算申报汇总2号表");
        String datefrom = req.getDatefrom();
        ShybTwoResp sr = new ShybTwoResp();
        List<String> mzlb_list = new ArrayList<>();
        if(req.getMzlb().contains(",")){
            String[] mzlb = req.getMzlb().split(",");
            for(int i=0;i<mzlb.length;i++){
                mzlb_list.add(mzlb[i]);
            }
        }else {
            mzlb_list.add(req.getMzlb());
        }
        List<Map<String,Object>> list_orgid = shybbbDao.getYbjgdm(mzlb_list.get(0));
        sr.setJgid(list_orgid.get(0).get("ybjgid").toString());

        //费用
        List<ShFeehzEhbjbResp> list = shybbbDao.getJbFeeEhb(req.getDatefrom(),req.getDateto(),mzlb_list);
        sr.setShFeehzEhbjbResps(list);
        Map<Integer, List<ShFeehzEhbjbResp>> map = list.stream().collect(Collectors.groupingBy(ShFeehzEhbjbResp::getLX));
        BigDecimal jbtotal = new BigDecimal(0);

        if(map.get(1) != null){
            double jbmztotal = map.get(1).stream().mapToDouble(ShFeehzEhbjbResp::getTCJJZF).sum();
            BigDecimal mz = new BigDecimal(jbmztotal).setScale(2,BigDecimal.ROUND_HALF_UP);
            int jbmzrc = map.get(1).stream().mapToInt(ShFeehzEhbjbResp::getRC).sum();
            sr.setJbmzxxje(String.valueOf(mz));
            sr.setJbmzrc(String.valueOf(jbmzrc));
            jbtotal = jbtotal.add(mz);
        }
        if (map.get(2) != null){
            double jbzytotal = map.get(2).stream().mapToDouble(ShFeehzEhbjbResp::getTCJJZF).sum();
            BigDecimal zy = new BigDecimal(jbzytotal).setScale(2,BigDecimal.ROUND_HALF_UP);
            int jbzyrc = map.get(2).stream().mapToInt(ShFeehzEhbjbResp::getRC).sum();
            sr.setJbzyxxje(String.valueOf(zy));
            sr.setJbzyrc(String.valueOf(jbzyrc));
            jbtotal = jbtotal.add(zy);
        }

        sr.setJbxxje(String.valueOf(jbtotal));
        sr.setJbdxje(BSPHISUtil.changeMoneyUpper(jbtotal));

        double gstotal = 0;
        if (map.get(3) != null){
            gstotal = map.get(3).stream().mapToDouble(ShFeehzEhbjbResp::getTCJJZF).sum();
            sr.setGsxxje(String.valueOf(gstotal));
            sr.setGsxxje(BSPHISUtil.changeMoneyUpper(gstotal));
        }

        List<ShFeehzEhbhzbkResp> hzbklist = shybbbDao.getHzbkFeeEhb(req.getDatefrom(),req.getDateto(),mzlb_list);

        sr.setShFeehzEhbhzbkResps(hzbklist);
        if (!hzbklist.isEmpty()){
            sr.setYlhzxxje(hzbklist.get(0).getHzbkhj());
            sr.setYlhzdxje(BSPHISUtil.changeMoneyUpper(hzbklist.get(0).getHzbkhj()));
        }

//        List<Map<String,Object>> BS = shybbbDao.getBS();
//        if(list.size()>0 ){
//            for(int i =0;i<list.size();i++){
//                for(int j=0;j<BS.size();j++){
//                    if(list.get(i).get("LX").equals(BS.get(j).get("LX")) && list.get(i).get("GRXZ").equals(BS.get(j).get("GRXZ"))){
//                        BS.remove(j);
//                        BS.add(list.get(i));
//                    }
//                }
//            }
//        }
//        for(int k=0;k<BS.size();k++){
//            total +=  NumberUtils.toDouble(String.valueOf(BS.get(k).get("YBZFXJ")));
//        }
//        sr.setJes(String.valueOf(total));
//        sr.setJeh(BSPHISUtil.changeMoneyUpper(total));
//
          sr.setJgmc(user.getHospitalName());
          sr.setQuerydate(datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");

        return sr;
    }



    public String dbfWriter(int i,LinkedList<Map<String,Object>> list,String date,String ybmc,String otype,String type) throws Exception {
//		   https://blog.csdn.net/sy3345036/article/details/78012323
        logger.info("dbfWriter导出" );
        Map<String,Object> map_key = new HashMap();
        int s = 0;
        Map<String,Object> map = list.get(0);
        for(String key: map.keySet()) {
            map_key.put(s+"",key);
            s++;
        }
        DBFField fields[] = new DBFField[i];
        for(int count=0;count<i;count++){
            fields[count] = new DBFField();
            String a = StrUtil.null2Str(list.get(0).keySet());
            fields[count].setName((map_key.get(String.valueOf(count))+"").toUpperCase());
            if((map_key.get(String.valueOf(count))+"").toUpperCase().contains("FY") ||
                    (map_key.get(String.valueOf(count))+"").toUpperCase().contains("XJ") ||
                    (map_key.get(String.valueOf(count))+"").toUpperCase().contains("ZF") ||
                    "FJBX".equals((map_key.get(String.valueOf(count))+"").toUpperCase())
            ){
               // DBFDataType avv = DBFDataType.NUMERIC;
                //fields[count].setType(DBFDataType.NUMERIC );
                fields[count].setDataType(DBFField.FIELD_TYPE_F);
                fields[count].setFieldLength(20);
                fields[count].setDecimalCount(2);
            }else if( "JZNUM".equals((map_key.get(String.valueOf(count))+"").toUpperCase()) ){
                fields[count].setDataType(DBFField.FIELD_TYPE_F);
            }else if("ZYDNUM".equals((map_key.get(String.valueOf(count))+"").toUpperCase())){
                fields[count].setDataType(DBFField.FIELD_TYPE_F);
                fields[count].setFieldLength(20);
                fields[count].setDecimalCount(1);
            }else {
                fields[count].setType(DBFDataType.CHARACTER);
            }

            fields[count].setLength(20);
            //fields[count].setFieldLength(20);
        }
        logger.info("dbfWriter1" );
        //String tempDirName = System.getProperty("java.io.tmpdir");
        //String tempDirName = System.getProperty("user.dir");
        String tempDirName = "D:\\yb\\";
        File dir = new File(tempDirName);
        if(!dir.exists()){
            dir.mkdirs();
        }
        tempDirName = tempDirName.replace("\\","\\\\");
        String path = tempDirName+//"/"+
                otype+ybmc+date.substring(2, 4)+
                Integer.toHexString(Integer.valueOf(date.substring(5))).toUpperCase() + type+
                ".dbf";
        if("PKJSB".equals(type)){
            date = date.replace(".","");
            path = tempDirName+
                    otype+date+ type+
                    ".dbf";
        }
        //path = "f:\\test"+type+ ".dbf";
        File file = new File(path);
        if(file.exists()){
            file.delete();
        }

        //FileOutputStream fos = new FileOutputStream("d:\\test11.dbf");
        //DBFWriter writer = new DBFWriter(fos, Charset.defaultCharset());
        DBFWriter writer = new DBFWriter();
        writer.setCharactersetName("GBK");
        OutputStream out = new FileOutputStream(new File(path));
        writer.setFields(fields);


        for(int k=0;k<list.size();k++){
            Object[] rowData = new Object[i];
            for(int count=0;count<i;count++){
                String sss = map_key.get(String.valueOf(count))+"";
                String zzz = list.get(k).get(sss)+"";
                rowData[count] =  list.get(k).get(map_key.get(String.valueOf(count)));//   StrUtil.null2Str(list.get(i).keySet());
            }
            writer.addRecord(rowData);
        }
        writer.write(out);
        //writer.addRecord(rowData);
        out.close();
        writer.close();
        // fos.close();
        path = path.replace("\\\\","/");


        FileInputStream in = null;

       /* try {
            String savePath = path;
            //创建输出流
            out= response.getOutputStream();
            //获取文件名
            String filename = savePath.substring(savePath.lastIndexOf("/")+1);
            filename = new String(filename.getBytes("iso8859-1"),"UTF-8");

            String downloadpath = savePath;
            in= new FileInputStream(downloadpath);

            byte buffer[] = new byte[1024];
            int len = 0;
            while((len = in.read(buffer)) > 0){
                out.write(buffer, 0, len);
            }
        }finally {
            in.close();
            out.close();
        }
        file.delete();*/

        return path;
    }


    public String dbfWriters(int i,LinkedList<Map<String,Object>> list,String date,String ybmc,String otype,
                             String type,HttpServletResponse response) throws Exception {
//		   https://blog.csdn.net/sy3345036/article/details/78012323
        logger.info("dbfWriter导出" );
        Map<String,Object> map_key = new HashMap();
        int s = 0;
        Map<String,Object> map = list.get(0);
        for(String key: map.keySet()) {
            map_key.put(s+"",key);
            s++;
        }
        DBFField fields[] = new DBFField[i];
        for(int count=0;count<i;count++){
            fields[count] = new DBFField();
            String a = StrUtil.null2Str(list.get(0).keySet());
            fields[count].setName((map_key.get(String.valueOf(count))+"").toUpperCase());
            if((map_key.get(String.valueOf(count))+"").toUpperCase().contains("FY") ||
                    (map_key.get(String.valueOf(count))+"").toUpperCase().contains("XJ") ||
                    (map_key.get(String.valueOf(count))+"").toUpperCase().contains("ZF") ||
                    "FJBX".equals((map_key.get(String.valueOf(count))+"").toUpperCase())
            ){
                fields[count].setDataType(DBFField.FIELD_TYPE_N);
                fields[count].setFieldLength(20);
                fields[count].setDecimalCount(2);
            }else if( "JZNUM".equals((map_key.get(String.valueOf(count))+"").toUpperCase()) ){
                fields[count].setDataType(DBFField.FIELD_TYPE_N);
            }else if("ZYDNUM".equals((map_key.get(String.valueOf(count))+"").toUpperCase())){
                fields[count].setDataType(DBFField.FIELD_TYPE_N);
                fields[count].setFieldLength(20);
                fields[count].setDecimalCount(1);
            }else {
                fields[count].setType(DBFDataType.CHARACTER);
            }

            fields[count].setLength(20);
        }
        logger.info("dbfWriter1" );
        //String tempDirName = System.getProperty("java.io.tmpdir");
        //String tempDirName = System.getProperty("user.dir");
        String tempDirName = System.getProperty("java.io.tmpdir");
       /* String tempDirName = "D:\\yb\\";
        File dir = new File(tempDirName);
        if(!dir.exists()){
            dir.mkdirs();
        }*/
        tempDirName = tempDirName.replace("\\","\\\\");
        String path = tempDirName+//"/"+
                otype+ybmc+date.substring(2, 4)+
                Integer.toHexString(Integer.valueOf(date.substring(5))).toUpperCase() + type+
                ".dbf";
        if("PKJSB".equals(type)){
            date = date.replace(".","");
            path = tempDirName+
                    otype+date+ type+
                    ".dbf";
        }
        //path = "f:\\test"+type+ ".dbf";
        File file = new File(path);
        if(file.exists()){
            file.delete();
        }

        //FileOutputStream fos = new FileOutputStream("d:\\test11.dbf");
        //DBFWriter writer = new DBFWriter(fos, Charset.defaultCharset());
        DBFWriter writer = new DBFWriter();
        writer.setCharactersetName("GBK");
        OutputStream out = new FileOutputStream(new File(path));
        writer.setFields(fields);


        for(int k=0;k<list.size();k++){
            Object[] rowData = new Object[i];
            for(int count=0;count<i;count++){
                String sss = map_key.get(String.valueOf(count))+"";
                String zzz = list.get(k).get(sss)+"";
                rowData[count] =  list.get(k).get(map_key.get(String.valueOf(count)));//   StrUtil.null2Str(list.get(i).keySet());
            }
            writer.addRecord(rowData);
        }
        writer.write(out);
        //writer.addRecord(rowData);
        out.close();
        writer.close();
        // fos.close();
        path = path.replace("\\\\","/");

        FileInputStream in = null;
        String savePath = path;
            //创建输出流
            ServletOutputStream outputStream= response.getOutputStream();
            //获取文件名
            String filename = savePath.substring(savePath.lastIndexOf("/")+1);
            byte[] bytes = FileUtil.readBytes( path);
            response.setContentType("application/octet-stream");
            filename = new String(filename.getBytes("GBK"),"ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + filename);
            outputStream.write(bytes);
            IoUtil.close(outputStream);

        file.delete();

        return path;
    }





    private void writeRecord(String file,String record) throws IOException {
        BufferedWriter buff = null;
        try {
            buff = new BufferedWriter(new FileWriter(file));

            buff.write(record);
            buff.flush();
            buff.close();

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if (buff!=null) {
                buff.close();
            }
        }
    }

    private BigDecimal formatBig(Object o) {
        if (o == null || "".equals(o.toString())){
            return new BigDecimal("0");
        }  else {
            return new BigDecimal(o.toString());
        }
    }

    private String number_format_H(String num){

        if(num.contains("-")){
            DecimalFormat df = new DecimalFormat("0.00");
            num = df.format(Double.parseDouble(num));
            int length = 10 - num.length();
            for(int i=0; i<length;i++){
                num = "0" + num;
            }
        }else{
            DecimalFormat df = new DecimalFormat("0000000.00");
            num = df.format(Double.parseDouble(num));
        }
        return num;
    }

    private String rightSpace(String str,int n){
        str = subStringByByte(str,n);
        int len = str.getBytes().length;
        while (len < n) {
            str += " ";
            len++;
        }
        return str;
    }
    private static String subStringByByte(String str, int len) {
        String result = null;
        if (str != null) {
            byte[] a = str.getBytes();
            if (a.length <= len) {
                result = str;
            } else if (len > 0) {
                result = new String(a, 0, len);
                int length = result.length();
                if (str.charAt(length - 1) != result.charAt(length - 1)) {
                    if (length < 2) {
                        result = null;
                    } else {
                        result = result.substring(0, length - 1);
                    }
                }
            }
        }
        return result;
    }
    private String number_format_L(String num){
        if(num.contains("-")){
            DecimalFormat df = new DecimalFormat("0");
            num = df.format(Double.parseDouble(num));
            int length = 4 - num.length();
            for(int i=0; i<length;i++){
                num = "0" + num;
            }
        }else{
            DecimalFormat df = new DecimalFormat("0000");
            num = df.format(Double.parseDouble(num));
        }
        return num;
    }

    private String number_format_I(String num){
        if(num.contains("-")){
            DecimalFormat df = new DecimalFormat("0.000");
            num = df.format(Double.parseDouble(num));
            int length = 11 - num.length();
            for(int i=0; i<length;i++){
                num = "0" + num;
            }
        }else{
            DecimalFormat df = new DecimalFormat("0000000.000");
            num = df.format(Double.parseDouble(num));
        }
        return num;
    }

    private String number_format_K(String num){
        if(num.contains("-")){
            DecimalFormat df = new DecimalFormat("0.00000");
            num = df.format(Double.parseDouble(num));
            int length = 9 - num.length();
            for(int i=0; i<length;i++){
                num = "0" + num;
            }
        }else{
            DecimalFormat df = new DecimalFormat("000.00000");
            num = df.format(Double.parseDouble(num));
        }
        return num;
    }

    private String number_format_J(String num){

        if(num.contains("-")){
            DecimalFormat df = new DecimalFormat("0.0");
            num = df.format(Double.parseDouble(num));
            int length = 6 - num.length();
            for(int i=0; i<length;i++){
                num = "0" + num;
            }
        }else{
            DecimalFormat df = new DecimalFormat("0000.0");
            num = df.format(Double.parseDouble(num));
        }
        return num;
    }




}
