package com.buit.his.medinsuinterface.sh.dataitem.service;

import com.buit.commons.BaseException;
import com.buit.commons.SysUser;
import com.buit.his.medinsuinterface.sh.dataitem.dao.ShybbbDao;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybbbReq;
import com.buit.his.medinsuinterface.sh.dataitem.response.*;
import com.buit.his.shyb.source.dao.ShybSh01Dao;
import com.buit.his.shyb.source.entity.SettleResultDTO;
import com.buit.his.shyb.source.entity.business.SL01;
import com.buit.his.shyb.source.entity.business.SL01Res;
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

import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 上海医保明细对账<br>
 * @author beijunhua
 */
@Service
public class ShybbbReportSer {

    static final Logger logger = LoggerFactory.getLogger(ShybbbReportSer.class);

    @Autowired
    private ShybbbDao shybbbDao;
    @Autowired
    private ShybSh01Dao ybSh01Dao;
    @DubboReference
    private OpMzlbService opMzlbDao;
    @DubboReference
    private SysXtcsCacheSer sysXtcsCacheSer;


    //拆分A
    public List<Map<String, Object>> QueryYbCityNormalListire(String daa, String datefrom, String dateto, String mzlb) {
        logger.info("城镇普通拆分A");
        //pageno = (pageno - 1) * 20;
        return shybbbDao.queryYbCityNormalListire(daa, datefrom, dateto, mzlb);
    }
    //医保拆分导出

    /**
     * 城镇普通1A
     *
     * @return
     */
    public CzptSbbHzResp getFields(List<Map<String, Object>> list,String datefrom,SysUser user) {
        CzptSbbHzResp Czpthz = new CzptSbbHzResp();
        List<Map<String, Object>> records = new ArrayList<>();
        double TOTFY_ALL = 0; //费用总计
        double FLZZ_ALL = 0; //分类自负在职
        double FLTX_ALL = 0; //分类自负退休
        double ZFZZ_ALL = 0; //自负在职
        double ZFTX_ALL = 0; //自负退休
        double DNZHZZ_ALL = 0; //账户当年资金支付在职
        double DNZHTX_ALL = 0; //账户当年资金支付退休
        double LNZHZZ_ALL = 0; //历年
        double LNZHTX_ALL = 0; //
        double FJZZ_ALL = 0; //附加基金支付
        double FJTX_ALL = 0; //其他
        int JZNUM_ALL = 0;

        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            Map<String, Object> map_res = new HashMap<>();

            map_res.put("XH", i + 1);
            map_res.put("BRXM", map.get("NAME"));    // 病人姓名
            map_res.put("YBID", map.get("YBID"));    // 医保号码
            map_res.put("KSNA", map.get("KSNA"));    // 科室名称
            map_res.put("JZDATE", map.get("JZDATE")); // 就诊日期
            map_res.put("JZNUM", map.get("JZNUM")); // 就诊人次
            map_res.put("TOTFY", map.get("TOTFY")); // 费用总计

            if (Integer.parseInt(map.get("GRXZ") + "") == 1) {
                map_res.put("FLZZ", map.get("FLGRZF")); // 分类自负-在职
                map_res.put("ZFZZ", map.get("GRXJ")); // 自负-在职
                map_res.put("DNZHZZ", map.get("GRZFD")); // 当年账户资金支付-在职
                map_res.put("LNZHZZ", map.get("GRZFL")); // 历年账户资金支付-在职
                map_res.put("FJZZ", map.get("FJBX")); // 附加支付人次-在职

                map_res.put("FLTX", 0); // 分类自负-退休
                map_res.put("ZFTX", 0); // 自负-退休
                map_res.put("DNZHTX", 0); // 当年账户资金支付-退休
                map_res.put("LNZHTX", 0); // 历年账户资金支付-退休
                map_res.put("FJTX", 0); // 附加支付人次-退休
            } else if (Integer.parseInt(map.get("GRXZ") + "") == 2) {
                map_res.put("FLZZ", 0); // 分类自负-在职
                map_res.put("ZFZZ", 0); // 自负-在职
                map_res.put("DNZHZZ", 0); // 当年账户资金支付-在职
                map_res.put("LNZHZZ", 0); // 历年账户资金支付-在职
                map_res.put("FJZZ", 0); // 附加支付人次-在职

                map_res.put("FLTX", map.get("FLGRZF")); // 分类自负-退休
                map_res.put("ZFTX", map.get("GRXJ")); // 自负-退休
                map_res.put("DNZHTX", map.get("GRZFD")); // 当年账户资金支付-退休
                map_res.put("LNZHTX", map.get("GRZFL")); // 历年账户资金支付-退休
                map_res.put("FJTX", map.get("FJBX")); // 附加支付人次-退休
            } else {
                map_res.put("FLZZ", 0); // 分类自负-在职
                map_res.put("ZFZZ", 0); // 自负-在职
                map_res.put("DNZHZZ", 0); // 当年账户资金支付-在职
                map_res.put("LNZHZZ", 0); // 历年账户资金支付-在职
                map_res.put("FJZZ", 0); // 附加支付人次-在职
                map_res.put("FLTX", 0); // 分类自负-退休
                map_res.put("ZFTX", 0); // 自负-退休
                map_res.put("DNZHTX", 0); // 当年账户资金支付-退休
                map_res.put("LNZHTX", 0); // 历年账户资金支付-退休
                map_res.put("FJTX", 0); // 附加支付人次-退休
            }
            TOTFY_ALL += NumberUtils.toDouble(String.valueOf(map_res.get("TOTFY")));
            FLZZ_ALL += NumberUtils.toDouble(String.valueOf(map_res.get("FLZZ")));
            FLTX_ALL += NumberUtils.toDouble(String.valueOf(map_res.get("FLTX")));
            ZFZZ_ALL += NumberUtils.toDouble(String.valueOf(map_res.get("ZFZZ")));

            ZFTX_ALL += NumberUtils.toDouble(String.valueOf(map_res.get("ZFTX")));
            DNZHZZ_ALL += NumberUtils.toDouble(String.valueOf(map_res.get("DNZHZZ")));
            DNZHTX_ALL += NumberUtils.toDouble(String.valueOf(map_res.get("DNZHTX")));
            LNZHZZ_ALL += NumberUtils.toDouble(String.valueOf(map_res.get("LNZHZZ")));

            LNZHTX_ALL += NumberUtils.toDouble(String.valueOf(map_res.get("LNZHTX")));
            FJZZ_ALL += NumberUtils.toDouble(String.valueOf(map_res.get("FJZZ")));
            FJTX_ALL += NumberUtils.toDouble(String.valueOf(map_res.get("FJTX")));
            JZNUM_ALL +=  Integer.parseInt(String.valueOf(map_res.get("JZNUM")));

            map_res.put("TOTFY_ALL", TOTFY_ALL);
            map_res.put("FLZZ_ALL", FLZZ_ALL);
            map_res.put("FLTX_ALL", FLTX_ALL);
            map_res.put("ZFZZ_ALL", ZFZZ_ALL);

            map_res.put("ZFTX_ALL", ZFTX_ALL);
            map_res.put("DNZHZZ_ALL", DNZHZZ_ALL);
            map_res.put("DNZHTX_ALL", DNZHTX_ALL);
            map_res.put("LNZHZZ_ALL", LNZHZZ_ALL);

            map_res.put("LNZHTX_ALL", LNZHTX_ALL);
            map_res.put("FJZZ_ALL", FJZZ_ALL);
            map_res.put("FJTX_ALL", FJTX_ALL);
            map_res.put("JZNUM_ALL", JZNUM_ALL);

            records.add(map_res);
        }
        List<CzptAResp> mzfpBaseResp = HisUtil.ListToResultSet(records, new CzptAResp());
        Czpthz.setCzptsbbresps(mzfpBaseResp);
        Map<String,Object> map = getParameters(datefrom,user,1,list);
        Czpthz.setDNZHHJ(map.get("DNZHHJ")+"");
        Czpthz.setFinancialOfficer(map.get("financialOfficer")+"");
        Czpthz.setQuerydate(map.get("querydate")+"");
        Czpthz.setMaker(map.get("maker")+"");
        Czpthz.setReviewer(map.get("reviewer")+"");
        Czpthz.setReportDate(map.get("reportDate")+"");
        Czpthz.setHeadno(map.get("headno")+"");
        Czpthz.setPresident(map.get("president")+"");
        Czpthz.setFLZZ(map.get("FLZZ")+"");
        Czpthz.setZFZZ(map.get("ZFZZ")+"");
        Czpthz.setLNZHHJ(map.get("LNZHHJ")+"");
        Czpthz.setFJZZ(map.get("FJZZ")+"");
        Czpthz.setZF(map.get("ZF")+"");
        Czpthz.setZZZF(map.get("ZZZF")+"");
        Czpthz.setTXZF(map.get("TXZF")+"");
        return Czpthz;
    }

    public Map<String, Object> getParameters(String datefrom, SysUser user, int pageno, List<Map<String, Object>> list) {
        Map<String, Object> response = new HashMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Integer jgid = user.getHospitalId();
        String username = user.getUserName();
        String hosname = user.getHospitalName();
        String fzr = sysXtcsCacheSer.getCsz(jgid, "YBBBFZR");
        String ybbh = sysXtcsCacheSer.getCsz(jgid, "YBBBBH");
        if (fzr == null || fzr.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        if (ybbh == null || ybbh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0044");
        }
        String[] fzrArr = fzr.split(",");
        if (fzrArr.length < 3) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        getParametersForView(pageno, response, list);
        response.put("president", "医疗机构负责人:" + fzrArr[0]);
        response.put("financialOfficer", "财会负责人:" + fzrArr[1]);
        response.put("reviewer", "审核人:" + fzrArr[2]);
        response.put("maker", "制表人:" + username);
        response.put("hosName", hosname);
        response.put("querydate", datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");
        response.put("headno",
                "编号:" + ybbh + "-" + datefrom.substring(0, 4) + datefrom.substring(5, 7) + datefrom.substring(9));// 待确认规则
        response.put("reportDate", sdf.format(new Date()));
        return response;
    }

    private void getParametersForView(int pageno, Map<String, Object> response,
                                      List<Map<String, Object>> list) {
        int pageNo = pageno;
        int endIndex = pageNo * 5000;
        int startIndex = (pageNo - 1) * 20;
        if (endIndex > list.size()) {
            //防止下标越界
            endIndex = list.size();
        }
        response.put("totalRecord", endIndex);

        double FLZZ = 0.00;
        double ZFZZ = 0.00;
        double DNZHHJ = 0.00;
        double LNZHHJ = 0.00;
        double FJZZ = 0.00;
        double ZF = 0.00;
        double ZZZF = 0.00;
        double TXZF = 0.00;
        int FJBX = 0;
        int RC = 0;
        int ZZRC = 0;
        int TXRC = 0;
        int FJZZRC = 0;
        int FJTXRC = 0;

        for (int i = startIndex; i < endIndex; i++) {
            Map<String, Object> map = list.get(i);
            FLZZ += NumberUtils.toDouble(String.valueOf(map.get("FLGRZF")));
            ZFZZ += NumberUtils.toDouble(String.valueOf(map.get("GRXJ")));
            DNZHHJ += NumberUtils.toDouble(String.valueOf(map.get("GRZFD")));
            LNZHHJ += NumberUtils.toDouble(String.valueOf(map.get("GRZFL")));
            FJZZ += NumberUtils.toDouble(String.valueOf(map.get("FJBX")));
            RC += NumberUtils.toDouble(String.valueOf(map.get("JZNUM")));
            ZF += NumberUtils.toDouble(String.valueOf(map.get("ZF")));
            int GRXZ = NumberUtils.toInt(String.valueOf(map.get("GRXZ")));
            if (GRXZ == 1) {
                ZZZF += NumberUtils.toDouble(String.valueOf(map.get("ZF")));
                ZZRC += NumberUtils.toDouble(String.valueOf(map.get("JZNUM")));
            } else if (GRXZ == 2) {
                TXZF += NumberUtils.toDouble(String.valueOf(map.get("ZF")));
                TXRC += NumberUtils.toDouble(String.valueOf(map.get("JZNUM")));
            }

            double FJZF = NumberUtils.toDouble(String.valueOf(map.get("FJBX")));
            if (FJZF != 0) {
                FJBX += NumberUtils.toDouble(String.valueOf(map.get("JZNUM")));
                if (GRXZ == 1) {
                    FJZZRC += NumberUtils.toDouble(String.valueOf(map.get("JZNUM")));
                } else if (GRXZ == 2) {
                    FJTXRC += NumberUtils.toDouble(String.valueOf(map.get("JZNUM")));
                }
            }
        }

        response.put("FJZZRC", FJZZRC); //
        response.put("FJTXRC", FJTXRC); //
        response.put("ZZZF", String.format("%.2f", ZZZF)); //
        response.put("TXZF", String.format("%.2f", TXZF)); //
        response.put("FLZZ", String.format("%.2f", FLZZ)); //
        response.put("ZFZZ", String.format("%.2f", ZFZZ));
        response.put("DNZHHJ", String.format("%.2f", DNZHHJ)); //
        response.put("LNZHHJ", String.format("%.2f", LNZHHJ)); //
        response.put("FJZZ", String.format("%.2f", FJZZ)); //
        response.put("RC", RC); //人次
        response.put("ZZRC", ZZRC); //
        response.put("TXRC", TXRC); //
        response.put("ZF", String.format("%.2f", ZF)); //
        response.put("FJBX", FJBX); //
    }

    public CzptSbbHzBResp getFieldsB(List<Map<String, Object>> list,String datefrom,SysUser user) {
        List<Map<String, Object>> records = new ArrayList<>();
        CzptSbbHzBResp czpthzb = new CzptSbbHzBResp();
        double FY00_ALL = 0; //诊疗费
        double FY01_ALL = 0; //治疗费
        double FY02_ALL = 0; //手术材料费
        double FY03_ALL = 0; //检查费
        double FY04_ALL = 0; //化验费
        double FY05_ALL = 0; //摄片费
        double FY06_ALL = 0; //透视费
        double FY07_ALL = 0; //西药费
        double FY08_ALL = 0; //中成药费
        double FY09_ALL = 0; //中草药费
        double FY10_ALL = 0; //其他
        double TOTFY_ALL = 0; //费用总计
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> m = list.get(i);
            FY00_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY00")));
            FY01_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY01")));
            FY02_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY02")));
            FY03_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY03")));
            FY04_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY04")));
            FY05_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY05")));
            FY06_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY06")));
            FY07_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY07")));
            FY08_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY08")));
            FY09_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY09")));
            FY10_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY10")));
            TOTFY_ALL += NumberUtils.toDouble(String.valueOf(m.get("TOTFY")));

            list.get(i).put("FY00", NumberUtils.toDouble(String.valueOf(m.get("FY00"))));
            list.get(i).put("FY01", NumberUtils.toDouble(String.valueOf(m.get("FY01"))));
            list.get(i).put("FY02", NumberUtils.toDouble(String.valueOf(m.get("FY02"))));
            list.get(i).put("FY03", NumberUtils.toDouble(String.valueOf(m.get("FY03"))));
            list.get(i).put("FY04", NumberUtils.toDouble(String.valueOf(m.get("FY04"))));
            list.get(i).put("FY05", NumberUtils.toDouble(String.valueOf(m.get("FY05"))));
            list.get(i).put("FY06", NumberUtils.toDouble(String.valueOf(m.get("FY06"))));
            list.get(i).put("FY07", NumberUtils.toDouble(String.valueOf(m.get("FY07"))));
            list.get(i).put("FY08", NumberUtils.toDouble(String.valueOf(m.get("FY08"))));
            list.get(i).put("FY09", NumberUtils.toDouble(String.valueOf(m.get("FY09"))));
            list.get(i).put("FY10", NumberUtils.toDouble(String.valueOf(m.get("FY10"))));
            list.get(i).put("TOTFY", NumberUtils.toDouble(String.valueOf(m.get("TOTFY"))));
        }

        DecimalFormat df = new DecimalFormat("#0.00");
        String FY00_ALL_DE = df.format(FY00_ALL);
        String FY01_ALL_DE = df.format(FY01_ALL);
        String FY02_ALL_DE = df.format(FY02_ALL);
        String FY03_ALL_DE = df.format(FY03_ALL);
        String FY04_ALL_DE = df.format(FY04_ALL);
        String FY05_ALL_DE = df.format(FY05_ALL);
        String FY06_ALL_DE = df.format(FY06_ALL);
        String FY07_ALL_DE = df.format(FY07_ALL);
        String FY08_ALL_DE = df.format(FY08_ALL);
        String FY09_ALL_DE = df.format(FY09_ALL);
        String FY10_ALL_DE = df.format(FY10_ALL);
        String TOTFY_ALL_DE = df.format(TOTFY_ALL);

        for (int i = 0; i < list.size(); i++) {
            list.get(i).put("XH", i + 1);
            list.get(i).put("FY00_ALL", FY00_ALL_DE);
            list.get(i).put("FY01_ALL", FY01_ALL_DE);
            list.get(i).put("FY02_ALL", FY02_ALL_DE);
            list.get(i).put("FY03_ALL", FY03_ALL_DE);
            list.get(i).put("FY04_ALL", FY04_ALL_DE);
            list.get(i).put("FY05_ALL", FY05_ALL_DE);
            list.get(i).put("FY06_ALL", FY06_ALL_DE);
            list.get(i).put("FY07_ALL", FY07_ALL_DE);
            list.get(i).put("FY08_ALL", FY08_ALL_DE);
            list.get(i).put("FY09_ALL", FY09_ALL_DE);
            list.get(i).put("FY10_ALL", FY10_ALL_DE);
            list.get(i).put("TOTFY_ALL", TOTFY_ALL_DE);
            records.add(list.get(i));
        }
        List<CzptBResp> BResp = HisUtil.ListToResultSet(records, new CzptBResp());
        czpthzb.setCzptsbbresps(BResp);
        Map<String,Object> map = getParametersB(datefrom,user,1,list);
        czpthzb.setBankName(map.get("bankName")+"");
        czpthzb.setBankNo(map.get("bankNo")+"");
        czpthzb.setFinancialOfficer(map.get("financialOfficer")+"");
        czpthzb.setQuerydate(map.get("querydate")+"");
        czpthzb.setMaker(map.get("maker")+"");
        czpthzb.setReviewer(map.get("reviewer")+"");
        czpthzb.setReportDate(map.get("reportDate")+"");
        czpthzb.setHeadno(map.get("headno")+"");
        czpthzb.setPresident(map.get("president")+"");

        return czpthzb;
    }


    public Map<String, Object> getParametersB(String datefrom, SysUser user, int pageno, List<Map<String, Object>> list) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Map<String, Object> response = new HashMap();
        Integer jgid = user.getHospitalId();
        String username = user.getUserName();
        String hosname = user.getHospitalName();
        String fzr = sysXtcsCacheSer.getCsz(jgid, "YBBBFZR");
        String ybbh = sysXtcsCacheSer.getCsz(jgid, "YBBBBH");
        if (fzr == null || fzr.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        if (ybbh == null || ybbh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0044");
        }
        String[] fzrArr = fzr.split(",");
        if (fzrArr.length < 3) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        getParametersForViewB(response, sdf, datefrom, list, hosname);

        response.put("headno",
                "编号:" + ybbh + "-" + datefrom.substring(0, 4) + datefrom.substring(5, 7) + datefrom.substring(9));// 待确认规则
        response.put("president", "医疗机构负责人:" + fzrArr[0]);
        response.put("financialOfficer", "财会负责人:" + fzrArr[1]);
        response.put("reviewer", "审核人:" + fzrArr[2]);
        response.put("maker", "制表人:" + user.getUserName());
        return response;
    }

    private void getParametersForViewB(Map<String, Object> response,
                                       SimpleDateFormat sdf, String begindate,
                                       List<Map<String, Object>> list, String hosname) {
        response.put("totalRecord", list.size());
        response.put("hosName", hosname);
        response.put("querydate", begindate.substring(0, 4) + "年" + begindate.substring(5, 7) + "月");
        response.put("headno", "编号:74727214300-" + (begindate.substring(2, 4) + begindate.substring(5, 6)));//待确认规则
        response.put("bankNo", "03416900040019262");
        response.put("bankName", "农行闵行区水清南路支行");
        response.put("reportDate", sdf.format(new Date()));
    }

    public Map<String, Object> getParametersC(String datefrom, SysUser user, List<Map<String, Object>> list) {
        List<Map<String, Object>> list_res = new ArrayList<>();
        Map<String, Object> response = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Integer jgid = user.getHospitalId();
        response.put("hosName", user.getHospitalName());
        response.put("querydate", datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");

        String yhzh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHZH");
        String yhmc = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHMC");
        String lxdh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBLXDH");
        String ybbh = sysXtcsCacheSer.getCsz(jgid, "YBBBBH");
        if (yhzh == null || yhzh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0045");
        }
        if (yhmc == null || yhmc.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0046");
        }
        if (lxdh == null || lxdh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0047");
        }
        response.put("headno",
                "编号:" + ybbh + "-" + datefrom.substring(0, 4) + datefrom.substring(5, 7) + datefrom.substring(9));// 待确认规则
        response.put("YHZH", yhzh);
        response.put("KHYH", yhmc);
        response.put("reportDate", sdf.format(new Date()));

        int ZRC = 0;      //总人次    小计
        int ZZRC = 0;     //总人次    在职
        int TXRC = 0;     //总人次    退休

        double NN = 0;     //帐户当年资金支付  小计
        double ZZNN = 0;   //帐户当年资金支付  在职
        double TXNN = 0;   //帐户当年资金支付  退休

        double TCZF = 0;    //帐户历年资金支付  小计
        double ZZTCZF = 0;  //帐户历年资金支付  在职
        double TXTCZF = 0;  //帐户历年资金支付  退休

        double YBZF = 0;     //医保支付总计小计
        double ZZYBZF = 0;   //医保支付总计 在职
        double TXYBZF = 0;   //医保支付总计退休
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            int GRXZ = NumberUtils.toInt(String.valueOf(map.get("GRXZ")));
            ZRC += NumberUtils.toDouble(String.valueOf((map.get("JZNUM"))));
            NN += NumberUtils.toDouble(String.valueOf(map.get("GRZFD")));
            TCZF += NumberUtils.toDouble(String.valueOf(map.get("GRZFL")));
            if (GRXZ == 1) {
                ZZRC += NumberUtils.toDouble(String.valueOf(map.get("JZNUM")));
                ZZNN += NumberUtils.toDouble(String.valueOf(map.get("GRZFD")));
                ZZTCZF += NumberUtils.toDouble(String.valueOf(map.get("GRZFL")));
            } else if (GRXZ == 2) {
                TXRC += NumberUtils.toDouble(String.valueOf(map.get("JZNUM")));
                TXNN += NumberUtils.toDouble(String.valueOf(map.get("GRZFD")));
                TXTCZF += NumberUtils.toDouble(String.valueOf(map.get("GRZFL")));
            }
        }
        YBZF = NN + TCZF;
        ZZYBZF = ZZNN + ZZTCZF;
        TXYBZF = TXNN + TXTCZF;

        DecimalFormat df = new DecimalFormat("#0.00");

        response.put("ZRC", ZRC);  //总人次    小计
        response.put("ZZRC", ZZRC); //总人次    在职
        response.put("TXRC", TXRC);//总人次    退休
        response.put("NN", df.format(NN)); //帐户当年资金支付  小计
        response.put("ZZNN", df.format(ZZNN));//帐户当年资金支付  在职
        response.put("TXNN", df.format(TXNN));//帐户当年资金支付  退休
        response.put("TCZF", df.format(TCZF));//帐户历年资金支付  小计
        response.put("ZZTCZF", df.format(ZZTCZF)); //帐户历年资金支付  在职
        response.put("TXTCZF", df.format(TXTCZF));//帐户历年资金支付  退休
        response.put("YBZF", df.format(YBZF));//医保支付总计小计
        response.put("ZZYBZF", df.format(ZZYBZF));//医保支付总计 在职
        response.put("TXYBZF", df.format(TXYBZF));//医保支付总计退休
        response.put("SBJE", BSPHISUtil.changeMoneyUpper(YBZF));
        response.put("SHJE", BSPHISUtil.changeMoneyUpper(YBZF));
        list_res.add(response);
        return response;
    }

    /**
     * D
     *
     * @param datefrom
     * @param user
     * @param list
     * @return
     */
    public Map<String, Object> getParametersD(String datefrom, SysUser user, List<Map<String, Object>> list) {
        List<Map<String, Object>> list_res = new ArrayList<>();
        Map<String, Object> response = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Integer jgid = user.getHospitalId();
        response.put("hosName", user.getHospitalName());
        response.put("querydate", datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");

        String yhzh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHZH");
        String yhmc = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHMC");
        String lxdh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBLXDH");
        String ybbh = sysXtcsCacheSer.getCsz(jgid, "YBBBBH");
        if (yhzh == null || yhzh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0045");
        }
        if (yhmc == null || yhmc.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0046");
        }
        if (lxdh == null || lxdh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0047");
        }
        response.put("headno",
                "编号:" + ybbh + "-" + datefrom.substring(0, 4) + datefrom.substring(5, 7) + datefrom.substring(9));// 待确认规则
        response.put("bankNo", yhzh);
        response.put("bankName", yhmc);
        response.put("reportDate", sdf.format(new Date()));

        int RC = 0;
        int ZZRC = 0;
        int TXRC = 0;
        double FJZF = 0;
        double ZZFJZF = 0;
        double TXFJZF = 0;
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            int GRXZ = NumberUtils.toInt(String.valueOf(map.get("GRXZ")));
            double FJBX = NumberUtils.toDouble(String.valueOf((map.get("FJBX"))));
            FJZF += NumberUtils.toDouble(String.valueOf((map.get("FJBX"))));
            if (FJBX != 0) {
                RC += NumberUtils.toDouble(String.valueOf((map.get("JZNUM"))));
                if (GRXZ == 1) {
                    ZZFJZF += NumberUtils.toDouble(String.valueOf((map.get("FJBX"))));
                    ZZRC += NumberUtils.toDouble(String.valueOf((map.get("JZNUM"))));
                }
                if (GRXZ == 2) {
                    TXFJZF += NumberUtils.toDouble(String.valueOf((map.get("FJBX"))));
                    TXRC += NumberUtils.toDouble(String.valueOf((map.get("JZNUM"))));
                }
            }
        }

        DecimalFormat df = new DecimalFormat("#0.00");

        response.put("RC", RC);  //小计
        response.put("ZZRC", ZZRC);  //在职
        response.put("TXRC", TXRC); //退休
        response.put("FJZF", df.format(FJZF));  //附加小计
        response.put("ZZFJZF", df.format(ZZFJZF));  //附加在职
        response.put("TXFJZF", df.format(TXFJZF));  //附加退休

        response.put("SBJE", BSPHISUtil.changeMoneyUpper(FJZF)); //申报
        response.put("SHJE", BSPHISUtil.changeMoneyUpper(FJZF)); //审核

        return response;
    }

    //拆分城镇大病A
    public List<Map<String, Object>> QueryYbCityDbListire(String daa, String datefrom, String dateto, String mzlb) {
        logger.info("城镇大病拆分A");
        return shybbbDao.queryYbDbCityNormalListire(daa, datefrom, dateto, mzlb);
    }

    public CzdbSbbHzAResp getDbFields(List<Map<String, Object>> list,String datefrom,SysUser user) {
        List<Map<String, Object>> records = new ArrayList<>();
        CzdbSbbHzAResp czdbres = new CzdbSbbHzAResp();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            map.put("XH", i + 1);
            map.put("NAME", map.get("NAME"));    // 病人姓名
            map.put("YBID", map.get("YBID"));    // 医保号码
            map.put("KSNA", map.get("KSNA"));    // 科室名称
            map.put("JZDATE", map.get("JZDATE")); // 就诊日期
            map.put("JZNUM", map.get("JZNUM")); // 就诊人次
            map.put("JYTOTFY", map.get("TOTFY")); // 费用总计
            map.put("DBXM", map.get("DBXM")); // 大病项目
            if (Integer.parseInt(map.get("GRXZ") + "") == 1) {
                map.put("FLGRZF_ZZ", map.get("FLGRZF")); // 分类自负-在职
                map.put("GRXJ_ZZ", map.get("GRXJ")); // 自负-在职
                map.put("TCZF_ZZ", map.get("TCZF")); // 统筹支付-在职
                map.put("GRZFL_ZZ", map.get("GRZFL")); // 历年账户支付-在职
                map.put("FJBX_ZZ", map.get("FJBX")); // 附加支付-在职
            } else {
                map.put("FLGRZF_TX", map.get("FLGRZF")); // 分类自负-退休
                map.put("GRXJ_TX", map.get("GRXJ")); // 自负-退休
                map.put("TCZF_TX", map.get("TCZF")); // 统筹支付-退休
                map.put("GRZFL_TX", map.get("GRZFL")); // 历年账户支付-退休
                map.put("FJBX_TX", map.get("FJBX")); // 附加支付-退休
            }
            records.add(map);
            List<CzdbAResp> czdbaResp = HisUtil.ListToResultSet(records, new CzdbAResp());
            czdbres.setCzdbsbbresps(czdbaResp);
            Map<String,Object> map_hj = getDbParameters(datefrom,user,1,list);
            czdbres.setHosName(map_hj.get("hosName")+"");
            czdbres.setFinancialOfficer(map_hj.get("financialOfficer")+"");
            czdbres.setQuerydate(map_hj.get("querydate")+"");
            czdbres.setMaker(map_hj.get("maker")+"");
            czdbres.setReviewer(map_hj.get("reviewer")+"");
            czdbres.setReportDate(map_hj.get("reportDate")+"");
            czdbres.setHeadno(map_hj.get("headno")+"");
            czdbres.setPresident(map_hj.get("president")+"");
            czdbres.setJZNUM_ZJ(map_hj.get("JZNUM_ZJ")+"");
            czdbres.setTOTFY_ZJ(map_hj.get("TOTFY_ZJ")+"");
            czdbres.setFLGRZF_ZZ_ZJ(map_hj.get("FLGRZF_ZZ_ZJ")+"");
            czdbres.setGRXJ_ZZ_ZJ(map_hj.get("GRXJ_ZZ_ZJ")+"");
            czdbres.setTCZF_ZZ(map_hj.get("TCZF_ZZ")+"");
            czdbres.setGRZFL_ZZ_ZJ(map_hj.get("GRZFL_ZZ_ZJ")+"");
            czdbres.setFJBX_ZZ_ZJ(map_hj.get("FJBX_ZZ_ZJ")+"");
            czdbres.setFLGRZF_TX_ZJ(map_hj.get("FLGRZF_TX_ZJ")+"");
            czdbres.setGRXJ_TX_ZJ(map_hj.get("GRXJ_TX_ZJ")+"");
            czdbres.setTCZF_TX_ZJ(map_hj.get("TCZF_TX_ZJ")+"");
            czdbres.setGRZFL_TX_ZJ(map_hj.get("GRZFL_TX_ZJ")+"");
            czdbres.setFJBX_TX_ZJ(map_hj.get("FJBX_TX_ZJ")+"");
            czdbres.setFLGRZFHJ(map_hj.get("FLGRZFHJ")+"");
            czdbres.setGRXJHJ(map_hj.get("GRXJHJ")+"");
            czdbres.setTCZFHJ(map_hj.get("TCZFHJ")+"");
            czdbres.setGRZFLHJ(map_hj.get("GRZFLHJ")+"");
            czdbres.setFJBXHJ(map_hj.get("FJBXHJ")+"");
            czdbres.setZFHJ(map_hj.get("ZFHJ")+"");
            czdbres.setZFHJ_ZZ(map_hj.get("ZFHJ_ZZ")+"");
            czdbres.setZFHJ_TX(map_hj.get("ZFHJ_TX")+"");
            czdbres.setJZRC_ZZ(map_hj.get("JZRC_ZZ")+"");
            czdbres.setJZRC_TX(map_hj.get("JZRC_TX")+"");
            czdbres.setFJZFRCHJ(map_hj.get("FJZFRCHJ")+"");
            czdbres.setFJZFRC_ZZ(map_hj.get("FJZFRC_ZZ")+"");
            czdbres.setFJZFRC_TX(map_hj.get("FJZFRC_TX")+"");
        }
        return czdbres;
    }

    public Map<String, Object> getDbParameters(String datefrom, SysUser user, int pageno, List<Map<String, Object>> list) {
        Map<String, Object> response = new HashMap<>();
        Integer jgid = user.getHospitalId();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        response.put("hosName", user.getHospitalName());
        response.put("querydate", datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");
        response.put("headno",
                "编号:747272143-" + datefrom.substring(0, 4) + datefrom.substring(5, 7) + datefrom.substring(9));// 待确认规则
  /*      response.put("bankNo", "03416900040019262");
        response.put("bankName", "农行闵行区水清南路支行");*/
        response.put("reportDate", sdf.format(new Date()));
        String fzr = sysXtcsCacheSer.getCsz(jgid, "YBBBFZR");
        if (fzr == null || fzr.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        String[] fzrArr = fzr.split(",");
        if (fzrArr.length < 3) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        response.put("president", "医疗机构负责人" + fzrArr[0]);
        response.put("financialOfficer", "财会负责人" + fzrArr[1]);
        response.put("reviewer", "审核人"+fzrArr[2]);
        response.put("maker", "制表人" +user.getUserName());
        Map<String, Object> map = list.get(0);
        double FLGRZFHJ = 0;
        double GRXJHJ = 0;
        double TCZFHJ = 0;
        double GRZFLHJ = 0;
        double FJBXHJ = 0;
        double ZFHJ = 0;
        double ZFHJ_ZZ = 0;
        double ZFHJ_TX = 0;
        int JZRC_ZZ = 0;
        int JZRC_TX = 0;
        int FJZFRCHJ = 0;
        int FJZFRC_ZZ = 0;
        int FJZFRC_TX = 0;
        int JZNUM_ZJ = 0;
        double TOTFY_ZJ = 0;
        double FLGRZF_ZZ_ZJ = 0;
        double GRXJ_ZZ_ZJ = 0;
        double TCZF_ZZ = 0;
        double GRZFL_ZZ_ZJ = 0;
        double FJBX_ZZ_ZJ = 0;
        double FLGRZF_TX_ZJ = 0;
        double GRXJ_TX_ZJ = 0;
        double TCZF_TX_ZJ = 0;
        double GRZFL_TX_ZJ = 0;
        double FJBX_TX_ZJ = 0;
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> mapl = list.get(i);
            FLGRZFHJ += NumberUtils.toDouble(String.valueOf(mapl.get("FLGRZF")));
            GRXJHJ += NumberUtils.toDouble(String.valueOf(mapl.get("GRXJ")));
            TCZFHJ += NumberUtils.toDouble(String.valueOf(mapl.get("TCZF")));
            GRZFLHJ += NumberUtils.toDouble(String.valueOf(mapl.get("GRZFL")));
            FJBXHJ += NumberUtils.toDouble(String.valueOf(mapl.get("FJBX")));
            ZFHJ += NumberUtils.toDouble(String.valueOf(mapl.get("ZF")));
            JZNUM_ZJ += NumberUtils.toDouble(String.valueOf(mapl.get("JZNUM")));
            TOTFY_ZJ += NumberUtils.toDouble(String.valueOf(mapl.get("TOTFY")));
            String GRXZ = mapl.get("GRXZ").toString();
            if ("1".equals(GRXZ)) {
                ZFHJ_ZZ += NumberUtils.toDouble(String.valueOf(mapl.get("ZF")));
                JZRC_ZZ += NumberUtils.toDouble(String.valueOf(mapl.get("JZNUM")));
                FLGRZF_ZZ_ZJ += NumberUtils.toDouble(String.valueOf(mapl.get("FLGRZF")));
                GRXJ_ZZ_ZJ += NumberUtils.toDouble(String.valueOf(mapl.get("GRXJ")));
                TCZF_ZZ += NumberUtils.toDouble(String.valueOf(mapl.get("TCZF")));
                GRZFL_ZZ_ZJ += NumberUtils.toDouble(String.valueOf(mapl.get("GRZFL")));
                FJBX_ZZ_ZJ += NumberUtils.toDouble(String.valueOf(mapl.get("FJBX")));
            } else {
                ZFHJ_TX += NumberUtils.toDouble(String.valueOf(mapl.get("ZF")));
                JZRC_TX += NumberUtils.toDouble(String.valueOf(mapl.get("JZNUM")));
                FLGRZF_TX_ZJ += NumberUtils.toDouble(String.valueOf(mapl.get("FLGRZF")));
                GRXJ_TX_ZJ += NumberUtils.toDouble(String.valueOf(mapl.get("GRXJ")));
                TCZF_TX_ZJ += NumberUtils.toDouble(String.valueOf(mapl.get("TCZF")));
                GRZFL_TX_ZJ += NumberUtils.toDouble(String.valueOf(mapl.get("GRZFL")));
                FJBX_TX_ZJ += NumberUtils.toDouble(String.valueOf(mapl.get("FJBX")));
            }
            double FJZF = NumberUtils.toDouble(String.valueOf(mapl.get("FJBX")));
            if (FJZF != 0) {
                FJZFRCHJ += NumberUtils.toDouble(String.valueOf(mapl.get("JZNUM")));
                if ("1".equals(GRXZ)) {
                    FJZFRC_ZZ += NumberUtils.toDouble(String.valueOf(mapl.get("JZNUM")));
                } else {
                    FJZFRC_TX += NumberUtils.toDouble(String.valueOf(mapl.get("JZNUM")));
                }
            }
        }
        response.put("totalRecord", list.size());
        response.put("JZNUM_ZJ", JZNUM_ZJ);
        response.put("TOTFY_ZJ", String.format("%.2f", TOTFY_ZJ));
        response.put("FLGRZF_ZZ_ZJ", String.format("%.2f", FLGRZF_ZZ_ZJ));
        response.put("GRXJ_ZZ_ZJ", String.format("%.2f", GRXJ_ZZ_ZJ));
        response.put("TCZF_ZZ", String.format("%.2f", TCZF_ZZ));
        response.put("GRZFL_ZZ_ZJ", String.format("%.2f", GRZFL_ZZ_ZJ));
        response.put("FJBX_ZZ_ZJ", String.format("%.2f", FJBX_ZZ_ZJ));
        response.put("FLGRZF_TX_ZJ", String.format("%.2f", FLGRZF_TX_ZJ));
        response.put("GRXJ_TX_ZJ", String.format("%.2f", GRXJ_TX_ZJ));
        response.put("TCZF_TX_ZJ", String.format("%.2f", TCZF_TX_ZJ));
        response.put("GRZFL_TX_ZJ", String.format("%.2f", GRZFL_TX_ZJ));
        response.put("FJBX_TX_ZJ", String.format("%.2f", FJBX_TX_ZJ));

        response.put("FLGRZFHJ", FLGRZFHJ);
        response.put("GRXJHJ", GRXJHJ);
        response.put("TCZFHJ", TCZFHJ);
        response.put("GRZFLHJ", GRZFLHJ);
        response.put("FJBXHJ", FJBXHJ);
        response.put("ZFHJ", ZFHJ);
        response.put("ZFHJ_ZZ", ZFHJ_ZZ);
        response.put("ZFHJ_TX", ZFHJ_TX);

        response.put("JZRC_ZZ", JZRC_ZZ);
        response.put("JZRC_TX", JZRC_TX);
        response.put("FJZFRCHJ", FJZFRCHJ);
        response.put("FJZFRC_ZZ", FJZFRC_ZZ);
        response.put("FJZFRC_TX", FJZFRC_TX);
        return response;
    }

    public CzptSbbHzBResp getDbFieldsB(String datefrom,SysUser user,List<Map<String, Object>> list) {
        CzptSbbHzBResp czdbresp = new CzptSbbHzBResp();
        List<Map<String, Object>> records = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            map.put("XH", i + 1);
            map.put("ZDICD", map.get("ZDNAME"));    // 诊断
            map.put("YBID", map.get("YBID"));    // 医保号码
            map.put("FY00", Double.valueOf(map.get("FY00") + ""));    //
            map.put("FY01", Double.valueOf(map.get("FY01") + ""));    //
            map.put("FY02", Double.valueOf(map.get("FY02") + ""));    //
            map.put("FY03", Double.valueOf(map.get("FY03") + ""));    //
            map.put("FY04", Double.valueOf(map.get("FY04") + ""));    //
            map.put("FY05", Double.valueOf(map.get("FY05") + ""));    //
            map.put("FY06", Double.valueOf(map.get("FY06") + ""));    //
            map.put("FY07", Double.valueOf(map.get("FY07") + ""));    //
            map.put("FY08", Double.valueOf(map.get("FY08") + ""));    //
            map.put("FY09", Double.valueOf(map.get("FY09") + ""));    //
            map.put("FY10", Double.valueOf(map.get("FY10") + ""));    //
            map.put("TOTFY", Double.valueOf(map.get("TOTFY") + ""));    // 费用总计
            records.add(map);
        }
        List<CzptBResp> BResp = HisUtil.ListToResultSet(records, new CzptBResp());
        czdbresp.setCzptsbbresps(BResp);
        Map<String,Object> map_qt = getDbParametersB(datefrom,user,list);
        czdbresp.setBankName(map_qt.get("bankName")+"");
        czdbresp.setBankNo(map_qt.get("bankNo")+"");
        czdbresp.setFinancialOfficer(map_qt.get("financialOfficer")+"");
        czdbresp.setQuerydate(map_qt.get("querydate")+"");
        czdbresp.setMaker(map_qt.get("maker")+"");
        czdbresp.setReviewer(map_qt.get("reviewer")+"");
        czdbresp.setReportDate(map_qt.get("reportDate")+"");
        czdbresp.setHeadno(map_qt.get("headno")+"");
        czdbresp.setPresident(map_qt.get("president")+"");
        czdbresp.setFY00(map_qt.get("FY00_ZJ")+"");
        czdbresp.setFY01(map_qt.get("FY01_ZJ")+"");
        czdbresp.setFY02(map_qt.get("FY02_ZJ")+"");
        czdbresp.setFY03(map_qt.get("FY03_ZJ")+"");
        czdbresp.setFY04(map_qt.get("FY04_ZJ")+"");
        czdbresp.setFY05(map_qt.get("FY05_ZJ")+"");
        czdbresp.setFY06(map_qt.get("FY06_ZJ")+"");
        czdbresp.setFY07(map_qt.get("FY07_ZJ")+"");
        czdbresp.setFY08(map_qt.get("FY08_ZJ")+"");
        czdbresp.setFY09(map_qt.get("FY09_ZJ")+"");
        czdbresp.setFY10(map_qt.get("FY10_ZJ")+"");
        czdbresp.setTOTFY(map_qt.get("TOTFY_ZJ")+"");
        czdbresp.setTotalRecord(map_qt.get("totalRecord")+"");
        return czdbresp;
    }

    public Map<String, Object> getDbParametersB(String datefrom, SysUser user, List<Map<String, Object>> list) {
        Map<String, Object> response = new HashMap<>();
        double FY00_ZJ = 0;
        double FY01_ZJ = 0;
        double FY02_ZJ = 0;
        double FY03_ZJ = 0;
        double FY04_ZJ = 0;
        double FY05_ZJ = 0;
        double FY06_ZJ = 0;
        double FY07_ZJ = 0;
        double FY08_ZJ = 0;
        double FY09_ZJ = 0;
        double FY10_ZJ = 0;
        double TOTFY_ZJ = 0;
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            FY00_ZJ += NumberUtils.toDouble(String.valueOf(map.get("FY00")));
            FY01_ZJ += NumberUtils.toDouble(String.valueOf(map.get("FY01")));
            FY02_ZJ += NumberUtils.toDouble(String.valueOf(map.get("FY02")));
            FY03_ZJ += NumberUtils.toDouble(String.valueOf(map.get("FY03")));
            FY04_ZJ += NumberUtils.toDouble(String.valueOf(map.get("FY04")));
            FY05_ZJ += NumberUtils.toDouble(String.valueOf(map.get("FY05")));
            FY06_ZJ += NumberUtils.toDouble(String.valueOf(map.get("FY06")));
            FY07_ZJ += NumberUtils.toDouble(String.valueOf(map.get("FY07")));
            FY08_ZJ += NumberUtils.toDouble(String.valueOf(map.get("FY08")));
            FY09_ZJ += NumberUtils.toDouble(String.valueOf(map.get("FY09")));
            FY10_ZJ += NumberUtils.toDouble(String.valueOf(map.get("FY10")));
            TOTFY_ZJ += NumberUtils.toDouble(String.valueOf(map.get("TOTFY")));
        }
        response.put("totalRecord", list.size());
        response.put("TOTFY_ZJ", String.format("%.2f", TOTFY_ZJ));
        response.put("FY00_ZJ", String.format("%.2f", FY00_ZJ));
        response.put("FY01_ZJ", String.format("%.2f", FY01_ZJ));
        response.put("FY02_ZJ", String.format("%.2f", FY02_ZJ));
        response.put("FY03_ZJ", String.format("%.2f", FY03_ZJ));
        response.put("FY04_ZJ", String.format("%.2f", FY04_ZJ));
        response.put("FY05_ZJ", String.format("%.2f", FY05_ZJ));
        response.put("FY06_ZJ", String.format("%.2f", FY06_ZJ));
        response.put("FY07_ZJ", String.format("%.2f", FY07_ZJ));
        response.put("FY08_ZJ", String.format("%.2f", FY08_ZJ));
        response.put("FY09_ZJ", String.format("%.2f", FY09_ZJ));
        response.put("FY10_ZJ", String.format("%.2f", FY10_ZJ));

        Integer jgid = user.getHospitalId();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        response.put("hosName", user.getHospitalName());
        response.put("querydate", datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");
        response.put("headno", "编号:74727214300-150");//待确认规则
/*        response.put("bankNo", "03416900040019262");
        response.put("bankName", "农行闵行区水清南路支行");*/
        response.put("reportDate", sdf.format(new Date()));
        String fzr = sysXtcsCacheSer.getCsz(jgid, "YBBBFZR");
        if (fzr == null || fzr.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        String[] fzrArr = fzr.split(",");
        if (fzrArr.length < 3) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        response.put("president","医疗机构负责人" + fzrArr[0]);
        response.put("financialOfficer", "财会负责人" + fzrArr[1]);
        response.put("reviewer", "审核人"+ fzrArr[2]);
        response.put("maker", "制表人" + user.getUserName());
        return response;
    }

    public Map<String, Object> getDbParametersC(String datefrom, SysUser user, List<Map<String, Object>> list) {
        Map<String, Object> response = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String jgmc = user.getHospitalName();
        Integer jgid = user.getHospitalId();
        response.put("hosName", jgmc);
        String yhzh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHZH");
        String yhmc = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHMC");
        String lxdh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBLXDH");
        String ybbh = sysXtcsCacheSer.getCsz(jgid, "YBBBBH");
        if (yhzh == null || yhzh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0045");
        }
        if (yhmc == null || yhmc.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0046");
        }
        if (lxdh == null || lxdh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0047");
        }
        response.put("querydate", datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");
        response.put("headno",
                ybbh + "-" + datefrom.substring(0, 4) + datefrom.substring(5, 7) + datefrom.substring(9));// 待确认规则
        response.put("YHZH", yhzh);
        response.put("KHYH", yhmc);
        response.put("reportDate", sdf.format(new Date()));
        int ZRC = 0;
        int ZZRC = 0;
        int TXRC = 0;
        double NN = 0;
        double ZZNN = 0;
        double TXNN = 0;
        double TCZF = 0;
        double ZZTCZF = 0;
        double TXTCZF = 0;
        double YBZF = 0;
        double ZZYBZF = 0;
        double TXYBZF = 0;
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            int GRXZ = NumberUtils.toInt(String.valueOf(map.get("GRXZ")));
            ZRC += NumberUtils.toDouble(String.valueOf((map.get("JZNUM"))));
            NN += NumberUtils.toDouble(String.valueOf(map.get("TCZF")));
            TCZF += NumberUtils.toDouble(String.valueOf(map.get("GRZFL")));
            if (GRXZ == 1) {
                ZZRC += NumberUtils.toDouble(String.valueOf(map.get("JZNUM")));
                ZZNN += NumberUtils.toDouble(String.valueOf(map.get("TCZF")));
                ZZTCZF += NumberUtils.toDouble(String.valueOf(map.get("GRZFL")));
            } else {
                TXRC += NumberUtils.toDouble(String.valueOf(map.get("JZNUM")));
                TXNN += NumberUtils.toDouble(String.valueOf(map.get("TCZF")));
                TXTCZF += NumberUtils.toDouble(String.valueOf(map.get("GRZFL")));
            }
        }
        YBZF += NN + TCZF;
        ZZYBZF += ZZNN + ZZTCZF;
        TXYBZF += TXNN + TXTCZF;

        response.put("ZRC", ZRC);  //总人次
        response.put("ZZRC", ZZRC);  //在职
        response.put("TXRC", TXRC);  //退休
        response.put("NN", NN);  //历年
        response.put("ZZNN", ZZNN);  //在职历年
        response.put("TXNN", TXNN);  //退休历年
        response.put("TCZF", TCZF);  //统筹
        response.put("ZZTCZF", ZZTCZF);  //在职统筹
        response.put("TXTCZF", TXTCZF);  //退休统筹
        response.put("YBZF", YBZF);  //医保支付
        response.put("ZZYBZF", ZZYBZF);  //在职
        response.put("TXYBZF", TXYBZF);  //退休
        response.put("SBJE", BSPHISUtil.changeMoneyUpper(YBZF));
        response.put("SHJE", BSPHISUtil.changeMoneyUpper(YBZF));
        return response;
    }

    public Map<String, Object> getDbParametersD(String datefrom, SysUser user, List<Map<String, Object>> list) {
        Map<String, Object> response = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        response.put("hosName", user.getHospitalName());
        response.put("querydate", datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");
        response.put("headno", "编号:74727214300-150");//待确认规则
        response.put("bankNo", "03416900040019262");
        response.put("bankName", "农行闵行区水清南路支行");
        response.put("reportDate", sdf.format(new Date()));
        int RC = 0;
        int ZZRC = 0;
        int TXRC = 0;
        double FJZF = 0;
        double ZZFJZF = 0;
        double TXFJZF = 0;
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            int GRXZ = NumberUtils.toInt(String.valueOf(map.get("GRXZ")));
            double FJBX = NumberUtils.toDouble(String.valueOf((map.get("FJBX"))));
            FJZF += NumberUtils.toDouble(String.valueOf((map.get("FJBX"))));
            if (FJBX != 0) {
                RC += NumberUtils.toDouble(String.valueOf((map.get("JZNUM"))));
                if (GRXZ == 1) {
                    ZZFJZF += NumberUtils.toDouble(String.valueOf((map.get("FJBX"))));
                    ZZRC += NumberUtils.toDouble(String.valueOf((map.get("JZNUM"))));
                } else {
                    TXFJZF += NumberUtils.toDouble(String.valueOf((map.get("FJBX"))));
                    TXRC += NumberUtils.toDouble(String.valueOf((map.get("JZNUM"))));
                }
            }
        }
        response.put("RC", RC);  //人次小计
        response.put("ZZRC", ZZRC); //在职人次
        response.put("TXRC", TXRC); //退休人次
        response.put("FJZF", FJZF); //附加小计
        response.put("ZZFJZF", ZZFJZF); //在职附加
        response.put("TXFJZF", TXFJZF); //退休附加

        response.put("SBJE", BSPHISUtil.changeMoneyUpper(FJZF));
        response.put("SHJE", BSPHISUtil.changeMoneyUpper(FJZF));
        return response;
    }

    ///////////////////////////////////住院//////
    public List<Map<String, Object>> QueryYbZyCityNormalListire(String daa, String datefrom, String dateto, String mzlb) {
        logger.info("城镇住院A");

        return shybbbDao.queryYbZyCityNormalListire(daa, datefrom, dateto, mzlb);
    }

    public CzzySbbHzAResp getZyFields(List<Map<String, Object>> list,String datefrom,SysUser user) {
        List<Map<String, Object>> records = new ArrayList<>();
        CzzySbbHzAResp czzyhz = new CzzySbbHzAResp();
        double CZF = 0;// 个人自费费用
        double ZZCZF = 0;// 个人自费费用(在职)
        double TXCZF = 0;// 个人自费费用(退休)
        int CYRC = 0;// 出院人次
        int ZZCYRC = 0;// 出院人次（在职）
        int TXCYRC = 0;// 出院人次（退休）
        int FJ = 0;// 附加支付人次
        int ZZFJ = 0;// 附加支付人次（在职）
        int TXFJ = 0;// 附加支付人次（退休）

        double ZYTS_ALL = 0;
        double FYZJ_ALL = 0;
        double ZZFLGRZF_ALL = 0;
        double TXFLGRZF_ALL = 0;
        double ZZZF_ALL = 0;
        double TXZF_ALL = 0;
        double ZZTCZF_ALL = 0;
        double TXTCZF_ALL = 0;
        double ZZNN_ALL = 0;
        double TXNN_ALL = 0;
        double GRZFLD_ALL = 0;
        double GRZFLQ_ALL = 0;
        double ZZFJBX_ALL = 0;
        double TXFJBX_ALL = 0;
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            String GRXZ = "";
            double ZYDNUM = 0+NumberUtils.toDouble(String.valueOf(map.get("ZYDNUM")));    //住院天数
            String CYDATE = String.valueOf(map.get("CYDATE"));//出院日期
            int k = ZYDNUM < 0 ? -1 : 1;
            k = ("".equals(CYDATE) || "0".equals(CYDATE)) ? 0 : k;
            // 在职
            if (Integer.parseInt(map.get("GRXZ") + "") == 1) {
                GRXZ = "ZZ";
                if (map.get("CYDATE") != null) {
                    ZZCYRC = ZZCYRC + 1;
                    CYRC = CYRC + 1;
                }
                if (map.get("ZF") != null) {
                    ZZCZF = ZZCZF + Double.valueOf(map.get("ZF").toString());
                    CZF = CZF + Double.valueOf(map.get("ZF").toString());
                }
                if (map.get("FJBX") != null && Double.valueOf(map.get("FJBX").toString()) != 0.0) {
                    FJ = FJ + 1;
                    ZZFJ = ZZFJ + 1;
                }
                map.put("JZNUMZZ_ALL", k);
                map.put("JZNUMTX_ALL", 0);
                // 退休
            } else if (Integer.parseInt(map.get("GRXZ") + "") == 2) {
                GRXZ = "TX";
                if (map.get("CYDATE") != null) {
                    TXCYRC = TXCYRC + 1;
                    CYRC = CYRC + 1;
                }
                if (map.get("ZF") != null) {
                    TXCZF = TXCZF + Double.valueOf(map.get("ZF").toString());
                    CZF = CZF + Double.valueOf(map.get("ZF").toString());
                }
                if (map.get("FJBX") != null && Double.valueOf(map.get("FJBX").toString()) != 0.0) {
                    FJ = FJ + 1;
                    TXFJ = TXFJ + 1;
                }
                map.put("JZNUMZZ_ALL", 0);
                map.put("JZNUMTX_ALL", k);
            } else {
                map.put("JZNUMZZ_ALL", 0);
                map.put("JZNUMTX_ALL", 0);
            }
            map.put("XH", i + 1);
            ZYTS_ALL += NumberUtils.toDouble(String.valueOf(map.get("ZYDNUM")));
            FYZJ_ALL += NumberUtils.toDouble(String.valueOf(map.get("JYTOTFY")));
            if("ZZ".equals(GRXZ)){
                map.put(GRXZ + "FLGRZF", map.get("FLGRZF"));// 分类自负
                map.put(GRXZ + "ZF",new BigDecimal(map.get("GRXJD")+"").add(new BigDecimal(map.get("GRXJQ")+"")));// 自负
                map.put(GRXZ + "TCZF", map.get("TCZF"));// 统筹基金支付
                map.put(GRXZ + "NN", new BigDecimal(map.get("GRZFLD")+"").add(new BigDecimal(map.get("GRZFLQ")+"")));// 统筹历年基金支付
                map.put("GRZFLD", map.get("GRZFLD"));// 统筹历年基金支付 (定额)
                map.put("GRZFLQ", map.get("GRZFLQ"));// 统筹历年基金支付 (其余)
                map.put(GRXZ + "FJBX", map.get("FJBX"));// 附加基金支付

                map.put("TXFLGRZF","0.00");// 分类自负
                map.put("TXZF", "0.00");// 自负
                map.put("TXTCZF", "0.00");// 统筹基金支付
                map.put("TXNN","0.00");// 统筹历年基金支付
                map.put("TXFJBX", "0.00");// 附加基金支付


            }else if("TX".equals(GRXZ)){
                map.put(GRXZ + "FLGRZF", map.get("FLGRZF"));// 分类自负
                map.put(GRXZ + "ZF", new BigDecimal( map.get("GRXJD")+"").add(new BigDecimal( map.get("GRXJQ")+"")));// 自负
                map.put(GRXZ + "TCZF", map.get("TCZF"));// 统筹基金支付
                map.put(GRXZ + "NN",  new BigDecimal(map.get("GRZFLD")+"").add(new BigDecimal( map.get("GRZFLQ")+"")));// 统筹历年基金支付
                map.put("GRZFLD", map.get("GRZFLD"));// 统筹历年基金支付 (定额)
                map.put("GRZFLQ", map.get("GRZFLQ"));// 统筹历年基金支付 (其余)
                map.put(GRXZ + "FJBX", map.get("FJBX"));// 附加基金支付
                map.put("ZZFLGRZF","0.00");// 分类自负
                map.put("ZZZF", "0.00");// 自负
                map.put("ZZTCZF", "0.00");// 统筹基金支付
                map.put("ZZNN","0.00");// 统筹历年基金支付
                map.put("ZZFJBX", "0.00");// 附加基金支付
            }
            map.put("JZNUM_ALL", k);
            map.put("CZF_ALL", CZF);
            map.put("CZFZZ_ALL", ZZCZF);
            map.put("CZFTX_ALL", TXCZF);
            map.put("FJNUM_ALL", FJ);
            map.put("FJNUMZZ_ALL", ZZFJ);
            map.put("FJNUMTX_ALL", TXFJ);

           ZZFLGRZF_ALL += NumberUtils.toDouble(String.valueOf(map.get("ZZFLGRZF")));
            TXFLGRZF_ALL += NumberUtils.toDouble(String.valueOf(map.get("TXFLGRZF")));
            ZZZF_ALL += NumberUtils.toDouble(String.valueOf(map.get("ZZZF")));
            TXZF_ALL += NumberUtils.toDouble(String.valueOf(map.get("TXZF")));
            ZZTCZF_ALL += NumberUtils.toDouble(String.valueOf(map.get("ZZTCZF")));
            TXTCZF_ALL += NumberUtils.toDouble(String.valueOf(map.get("TXTCZF")));
            ZZNN_ALL += NumberUtils.toDouble(String.valueOf(map.get("ZZNN")));
            TXNN_ALL += NumberUtils.toDouble(String.valueOf(map.get("TXNN")));
            GRZFLD_ALL += NumberUtils.toDouble(String.valueOf(map.get("GRZFLD")));
            GRZFLQ_ALL += NumberUtils.toDouble(String.valueOf(map.get("GRZFLQ")));
            ZZFJBX_ALL += NumberUtils.toDouble(String.valueOf(map.get("ZZFJBX")));
            TXFJBX_ALL += NumberUtils.toDouble(String.valueOf(map.get("TXFJBX")));
            records.add(map);
        }
        List<CzzyAResp> czzyaResp = HisUtil.ListToResultSet(records, new CzzyAResp());
        czzyhz.setCzzysbbresps(czzyaResp);
        Map<String,Object> map = getZyParameters(datefrom,user,1,list);
        czzyhz.setZYTS_ALL(String.valueOf(ZYTS_ALL));
        czzyhz.setFYZJ_ALL(String.valueOf(FYZJ_ALL));
        czzyhz.setZZFLGRZF_ALL(String.valueOf(ZZFLGRZF_ALL));
        czzyhz.setTXFLGRZF_ALL(String.valueOf(TXFLGRZF_ALL));
        czzyhz.setZZZF_ALL(String.valueOf(ZZZF_ALL));
        czzyhz.setTXZF_ALL(String.valueOf(TXZF_ALL));
        czzyhz.setZZTCZF_ALL(String.valueOf(ZZTCZF_ALL));
        czzyhz.setTXTCZF_ALL(String.valueOf(TXTCZF_ALL));
        czzyhz.setZZNN_ALL(String.valueOf(ZZNN_ALL));
        czzyhz.setTXNN_ALL(String.valueOf(TXNN_ALL));
        czzyhz.setGRZFLD_ALL(String.valueOf(GRZFLD_ALL));
        czzyhz.setGRZFLQ_ALL(String.valueOf(GRZFLQ_ALL));
        czzyhz.setZZFJBX_ALL(String.valueOf(ZZFJBX_ALL));
        czzyhz.setTXFJBX_ALL(String.valueOf(TXFJBX_ALL));
        czzyhz.setHosName(map.get("hosName")+"");
        czzyhz.setFinancialOfficer(map.get("financialOfficer")+"");
        czzyhz.setQuerydate(map.get("querydate")+"");
        czzyhz.setMaker(map.get("maker")+"");
        czzyhz.setReviewer(map.get("reviewer")+"");
        czzyhz.setReportDate(map.get("reportDate")+"");
        czzyhz.setHeadno(map.get("headno")+"");
        czzyhz.setPresident(map.get("president")+"");
        return czzyhz;
    }

    public Map<String, Object> getZyParameters(String datefrom, SysUser user, int pageno, List<Map<String, Object>> list) {
        Map<String, Object> response = new HashMap<>();
        Integer jgid = user.getHospitalId();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String fzr = sysXtcsCacheSer.getCsz(jgid, "YBBBFZR");
        if (fzr == null || fzr.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        String ybbh = sysXtcsCacheSer.getCsz(jgid, "YBBBBH");
        if (ybbh == null || ybbh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0045");
        }
        String[] fzrArr = fzr.split(",");
        if (fzrArr.length < 3) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        response.put("totalRecord", list.size());

        response.put("hosName", user.getHospitalName());
        response.put("querydate", datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");

        response.put("headno",
                "编号:" + ybbh + "-" + datefrom.substring(0, 4) + datefrom.substring(5, 7) + datefrom.substring(9));// 待确认规则
        response.put("reportDate", sdf.format(new Date()));
        response.put("president", "医疗机构负责人:" + fzrArr[0]);
        response.put("financialOfficer", "财会负责人:" + fzrArr[1]);
        response.put("reviewer", "审核人:" + fzrArr[2]);
        response.put("maker", user.getUserName());
        return response;
    }

    public CzzySbbHzBResp getZyFieldsB(String datefrom,SysUser user,List<Map<String, Object>> list) {
        List<Map<String, Object>> records = new ArrayList<>();
        CzzySbbHzBResp czzyhzb = new CzzySbbHzBResp();
        List<CzzyBResp> BResp = HisUtil.ListToResultSet(list, new CzzyBResp());
        czzyhzb.setCzzysbbresps(BResp);
        double FY01_ALL = 0;double FY02_ALL = 0;double FY03_ALL = 0;double FY04_ALL = 0;
        double FY08_ALL = 0;double FY07_ALL = 0;double FY06_ALL = 0;double FY05_ALL = 0;
        double FY09_ALL = 0;double FY10_ALL = 0;double FY11_ALL = 0;double FY12_ALL = 0;
        double JYTOTFY_ALL = 0;double FY15_ALL = 0;double FY14_ALL = 0;double FY13_ALL = 0;
        for (int i = 0; i < list.size(); i++) {
            FY01_ALL += NumberUtils.toDouble(list.get(i).get("FY01")+"");
            FY02_ALL += NumberUtils.toDouble(list.get(i).get("FY02")+"");
            FY03_ALL += NumberUtils.toDouble(list.get(i).get("FY03")+"");
            FY04_ALL += NumberUtils.toDouble(list.get(i).get("FY04")+"");
            FY05_ALL += NumberUtils.toDouble(list.get(i).get("FY05")+"");
            FY06_ALL += NumberUtils.toDouble(list.get(i).get("FY06")+"");
            FY07_ALL += NumberUtils.toDouble(list.get(i).get("FY07")+"");
            FY08_ALL += NumberUtils.toDouble(list.get(i).get("FY08")+"");
            FY09_ALL += NumberUtils.toDouble(list.get(i).get("FY09")+"");
            FY10_ALL += NumberUtils.toDouble(list.get(i).get("FY10")+"");
            FY11_ALL += NumberUtils.toDouble(list.get(i).get("FY11")+"");
            FY12_ALL += NumberUtils.toDouble(list.get(i).get("FY12")+"");
            FY13_ALL += NumberUtils.toDouble(list.get(i).get("FY13")+"");
            FY14_ALL += NumberUtils.toDouble(list.get(i).get("FY14")+"");
            FY15_ALL += NumberUtils.toDouble(list.get(i).get("FY15")+"");
            JYTOTFY_ALL += NumberUtils.toDouble(list.get(i).get("JYTOTFY")+"");
        }
        Map<String,Object> map = getZyParametersB(datefrom,user,list);
        czzyhzb.setFY01(FY01_ALL+"");
        czzyhzb.setFY02(FY02_ALL+"");
        czzyhzb.setFY03(FY03_ALL+"");
        czzyhzb.setFY04(FY04_ALL+"");
        czzyhzb.setFY05(FY05_ALL+"");
        czzyhzb.setFY06(FY06_ALL+"");
        czzyhzb.setFY07(FY07_ALL+"");
        czzyhzb.setFY08(FY08_ALL+"");
        czzyhzb.setFY09(FY09_ALL+"");
        czzyhzb.setFY10(FY10_ALL+"");
        czzyhzb.setFY11(FY11_ALL+"");
        czzyhzb.setFY12(FY12_ALL+"");
        czzyhzb.setFY13(FY13_ALL+"");
        czzyhzb.setFY14(FY14_ALL+"");
        czzyhzb.setFY15(FY15_ALL+"");
        czzyhzb.setJYTOTFY(JYTOTFY_ALL+"");
        czzyhzb.setHosName(map.get("hosName")+"");
        czzyhzb.setFinancialOfficer(map.get("financialOfficer")+"");
        czzyhzb.setQuerydate(map.get("querydate")+"");
        czzyhzb.setMaker(map.get("maker")+"");
        czzyhzb.setReviewer(map.get("reviewer")+"");
        czzyhzb.setReportDate(map.get("reportDate")+"");
        czzyhzb.setHeadno(map.get("headno")+"");
        czzyhzb.setPresident(map.get("president")+"");
        return czzyhzb;
    }

    public Map<String, Object> getZyParametersB(String datefrom, SysUser user, List<Map<String, Object>> list) {
        Map<String, Object> response = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Integer jgid = user.getHospitalId();
        String fzr = sysXtcsCacheSer.getCsz(jgid, "YBBBFZR");
        if (fzr == null || fzr.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        String ybbh = sysXtcsCacheSer.getCsz(jgid, "YBBBBH");
        if (ybbh == null || ybbh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0045");
        }
        String[] fzrArr = fzr.split(",");
        if (fzrArr.length < 3) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        response.put("totalRecord", list.size());
        response.put("hosName", user.getHospitalName());
        response.put("querydate", datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");
        response.put("headno",
                "编号:" + ybbh + "-" + datefrom.substring(0, 4) + datefrom.substring(5, 7) + datefrom.substring(9));// 待确认规则
        response.put("reportDate", sdf.format(new Date()));
        response.put("president", "医疗机构负责人:" + fzrArr[0]);
        response.put("financialOfficer", "财会负责人:" + fzrArr[1]);
        response.put("reviewer", "审核人:" + fzrArr[2]);
        response.put("maker", user.getUserName());
        return response;
    }

    public Map<String, Object> getZyParametersC(String datefrom, SysUser user, List<Map<String, Object>> list) {
        Map<String, Object> response = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        response.put("hosName", user.getUserName());
        response.put("querydate", datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");
        response.put("headno",
                "编号:747272143-" + datefrom.substring(0, 4) + datefrom.substring(5, 7) + datefrom.substring(9));// 待确认规则
        response.put("YHZH", "03416900040019262");
        response.put("KHYH", "农行闵行区水清南路支行");
        response.put("reportDate", sdf.format(new Date()));
        int rc = 0;
        int zzrc = 0;
        int txrc = 0;
        BigDecimal nn = new BigDecimal(0.00);
        BigDecimal zznn = new BigDecimal(0.00);
        BigDecimal txnn = new BigDecimal(0.00);
        BigDecimal tc = new BigDecimal(0.00);
        BigDecimal zztc = new BigDecimal(0.00);
        BigDecimal txtc = new BigDecimal(0.00);
        BigDecimal yb = new BigDecimal(0.00);
        BigDecimal zzyb = new BigDecimal(0.00);
        BigDecimal txyb = new BigDecimal(0.00);
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            double ZYDNUM = NumberUtils.toDouble(String.valueOf(map.get("ZYDNUM")));    //住院天数
            String CYDATE = String.valueOf(map.get("CYDATE"));//出院日期
            int k = ZYDNUM < 0 ? -1 : 1;
            k = ("".equals(CYDATE) || "0".equals(CYDATE)) ? 0 : k;
            // 历年账户
            BigDecimal nnzf = new BigDecimal(map.get("GRZFLD") + "").add(new BigDecimal(map.get("GRZFLQ") + ""));
            System.out.println(nnzf);
            // 统筹支付
            BigDecimal tczf = new BigDecimal(map.get("TCZF") + "");
            // 医保支付
            BigDecimal ybzf = new BigDecimal(map.get("JYTOTFY") + "");
            // 在职
            if (Integer.parseInt(map.get("GRXZ") + "") == 1) {
                zzrc += k;
                nn = nn.add(nnzf);
                zznn = zznn.add(nnzf);
                tc = tc.add(tczf);
                zztc = zztc.add(tczf);
                // 退休
            } else if (Integer.parseInt(map.get("GRXZ") + "") == 2) {
                txrc += k;
                nn = nn.add(nnzf);
                txnn = txnn.add(nnzf);
                tc = tc.add(tczf);
                txtc = txtc.add(tczf);
            }
            rc += k;
        }
        yb = yb.add(nn).add(tc);
        zzyb = zzyb.add(zznn).add(zztc);
        txyb = txyb.add(txnn).add(txtc);
        response.put("RC", rc);  //小计人次
        response.put("ZZRC", zzrc);  //在职
        response.put("TXRC", txrc);  //退休
        response.put("NN", nn.doubleValue()); //历年
        response.put("ZZNN", zznn.doubleValue());//
        response.put("TXNN", txnn.doubleValue());//
        response.put("TCZF", tc.doubleValue());//统筹
        response.put("ZZTCZF", zztc.doubleValue());
        response.put("TXTCZF", txtc.doubleValue());
        response.put("YBZF", yb.doubleValue()); //医保支付
        response.put("ZZYBZF", zzyb.doubleValue());
        response.put("TXYBZF", txyb.doubleValue());
        response.put("SBJE", BSPHISUtil.changeMoneyUpper(yb.doubleValue()));
        response.put("SHJE", BSPHISUtil.changeMoneyUpper(yb.doubleValue()));
        return response;
    }

    public Map<String, Object> getZyParametersD(String datefrom, SysUser user, List<Map<String, Object>> list) {
        Map<String, Object> response = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        response.put("hosName", user.getHospitalName());
        response.put("querydate", datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");
        response.put("headno",
                "编号:747272143-" + datefrom.substring(0, 4) + datefrom.substring(5, 7) + datefrom.substring(9));// 待确认规则
        response.put("YHZH", "03416900040019262");
        response.put("KHYH", "农行闵行区水清南路支行");
        response.put("reportDate", sdf.format(new Date()));
        int rc = 0;
        int zzrc = 0;
        int txrc = 0;
        BigDecimal fj = new BigDecimal(0.00);
        BigDecimal zzfj = new BigDecimal(0.00);
        BigDecimal txfj = new BigDecimal(0.00);
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            // 附加支付
            BigDecimal fjzf = new BigDecimal(map.get("FJBX") + "");
            if (fjzf.doubleValue() != 0.0) {
                // 在职
                if (Integer.parseInt(map.get("GRXZ") + "") == 1) {
                    zzrc++;
                    zzfj = zzfj.add(fjzf);
                    // 退休
                } else if (Integer.parseInt(map.get("GRXZ") + "") == 2) {
                    txrc++;
                    txfj = txfj.add(fjzf);
                }
                rc++;
                fj = fj.add(fjzf);
            }
        }
        response.put("RC", rc);//人次小计
        response.put("ZZRC", zzrc); //在职
        response.put("TXRC", txrc);//退休
        response.put("FJZF", fj.doubleValue()); //附加
        response.put("ZZFJZF", zzfj.doubleValue());//附加在职
        response.put("TXFJZF", txfj.doubleValue());//附加退休
        response.put("SBJE", BSPHISUtil.changeMoneyUpper(fj.doubleValue()));
        response.put("SHJE", BSPHISUtil.changeMoneyUpper(fj.doubleValue()));
        return response;
    }
//////////////////城镇特殊////////////////////////////////////

    public List<Map<String, Object>> QueryYbTsCityNormalListire(String daa, String datefrom, String dateto, String mzlb) {
        logger.info("城镇特殊A");

        return shybbbDao.queryYbTsCityNormalListire(daa, datefrom, dateto, mzlb);
    }

    /**
     * 城镇特殊A
     *
     * @param
     * @return
     */
    public CztsSbbHzAResp getTsFields(List<Map<String, Object>> list,String datefrom,SysUser user) {
        List<Map<String, Object>> records = new ArrayList<>();
        List<Map<String, Object>> list_a = new ArrayList<>();
        CztsSbbHzAResp cztsahz = new CztsSbbHzAResp();
        int JZNUM_SUM = 0;    //就诊次数
        double TOTFY_SUM = 0; //费用总计
        double FLGRZF_LX_SUM = 0;    //分类自负-离休
        double FLGRZF_SC_SUM = 0;    //分类自负-伤残
        double FLGRZF_QT_SUM = 0;    //分类自负-离休
        double FLGRZF_XJ_SUM = 0;    //分类自负-伤残
        double ZF_XJ_SUM = 0;    //自负-小计
        double ZF_YZ_SUM = 0;    //自负-遗属
        double ZF_GL_SUM = 0;    //自负-高龄
        double ZF_ZC_SUM = 0;    //自负-重残
        double TCZF_LX_SUM = 0;    //统筹-离休
        double TCZF_SC_SUM = 0;    //统筹-伤残
        double TCZF_QT_SUM = 0;    //统筹-其它
        double TCZF_XJ_SUM = 0;    //统筹-小计
        double TCZF_YZ_SUM = 0;    //统筹-遗属
        double TCZF_GL_SUM = 0;    //统筹-高龄
        double TCZF_ZC_SUM = 0;    //统筹-重残
        double GRZF_LX_SUM = 0;    //个人自费-离休
        double GRZF_SC_SUM = 0;    //个人自费-伤残
        double GRZF_QT_SUM = 0;    //个人自费-其它
        double GRZF_XJ_SUM = 0;    //个人自费-小计
        double GRZF_YZ_SUM = 0;    //个人自费-遗属
        double GRZF_GL_SUM = 0;    //个人自费-高龄
        double GRZF_ZC_SUM = 0;    //个人自费-重残
        double GRZF_SUM = 0;        //个人自费-总计
        int JZ_LX_SUM = 0;            //就诊人次-离休
        int JZ_SC_SUM = 0;            //就诊人次-伤残
        int JZ_QT_SUM = 0;            //就诊人次-其它
        int JZ_XJ_SUM = 0;            //就诊人次-小计
        int JZ_YZ_SUM = 0;            //就诊人次-遗属
        int JZ_GL_SUM = 0;            //就诊人次-高龄
        int JZ_ZC_SUM = 0;            //就诊人次-重残
        int JZ_SUM = 0;            //就诊人次-总计
        int XH = 0;
        for (Map<String, Object> m : list) {
            String GRXZ = String.valueOf(m.get("GRXZ"));//在退标识(1离休,2伤残,3计划生育,4甲类传染病,9其它)
            double FLGRZF = NumberUtils.toDouble(String.valueOf(m.get("FLGRZF")));
            double ZF = NumberUtils.toDouble(String.valueOf(m.get("GRXJ")));            //自负
            double TCZF = NumberUtils.toDouble(String.valueOf(m.get("TCZF")));        //(统筹基金支付)
            double GRZF = NumberUtils.toDouble(String.valueOf(m.get("ZF")));            //个人自费
            double JZNUM = NumberUtils.toDouble(String.valueOf(m.get("JZNUM")));    //就诊次数
            double TOTFY = NumberUtils.toDouble(String.valueOf(m.get("TOTFY")));    //总费用
            if ("1".equals(GRXZ)) {                //离休
                m.put("FLGRZF_LX", FLGRZF);        //分类自负-离休
                m.put("FLGRZF_SC", 0);            //分类自负-伤残
                m.put("FLGRZF_QT", 0);            //分类自负-其它
                m.put("FLGRZF_XJ", FLGRZF);        //分类自负-小计
                m.put("ZF_XJ", ZF);                //自负-小计
                m.put("ZF_LX", 0);                //自负-离休
                m.put("ZF_SC", 0);                //自负-伤残
                m.put("ZF_QT", 0);                //自负-其他
                m.put("ZF_YZ", 0);                //自负-遗属
                m.put("ZF_GL", 0);                //自负-高龄
                m.put("ZF_ZC", ZF);                //自负-重残
                m.put("TCZF_LX", TOTFY - FLGRZF);    //统筹基金支付-离休
                m.put("TCZF_SC", 0);            //统筹基金支付-伤残
                m.put("TCZF_QT", 0);            //统筹基金支付-其它
                m.put("TCZF_XJ", TOTFY - FLGRZF - ZF);            //统筹基金支付-小计
                m.put("TCZF_YZ", 0);            //统筹基金支付-遗属
                m.put("TCZF_GL", 0);            //统筹基金支付-高龄
                m.put("TCZF_ZC", 0);            //统筹基金支付-重残
                GRZF_LX_SUM += GRZF;            //个人自费-离休
                JZ_LX_SUM += JZNUM;            //就诊人次-离休
            } else if ("2".equals(GRXZ)) {                //伤残
                m.put("FLGRZF_LX", 0);                //分类自负-离休
                m.put("FLGRZF_SC", FLGRZF);            //分类自负-伤残
                m.put("FLGRZF_QT", 0);                //分类自负-其它
                m.put("FLGRZF_XJ", FLGRZF);            //分类自负-小计
                m.put("ZF_XJ", 0);                //自负-小计
                m.put("ZF_LX", 0);                //自负-离休
                m.put("ZF_SC", 0);                //自负-伤残
                m.put("ZF_QT", 0);                //自负-其他
                m.put("ZF_YZ", 0);                //自负-遗属
                m.put("ZF_GL", 0);                //自负-高龄
                m.put("ZF_ZC", 0);                //自负-重残
                m.put("TCZF_LX", 0);            //统筹基金支付-离休
                m.put("TCZF_SC", TOTFY - FLGRZF);            //统筹基金支付-伤残
                m.put("TCZF_QT", 0);            //统筹基金支付-其它
                m.put("TCZF_XJ", TOTFY - FLGRZF - ZF);            //统筹基金支付-小计
                m.put("TCZF_YZ", 0);            //统筹基金支付-遗属
                m.put("TCZF_GL", 0);            //统筹基金支付-高龄
                m.put("TCZF_ZC", 0);            //统筹基金支付-重残
                GRZF_SC_SUM += GRZF;            //个人自费-伤残
                JZ_SC_SUM += JZNUM;            //就诊人次-伤残
            } else if ("9".equals(GRXZ)) {                //其它
                m.put("FLGRZF_LX", 0);                //分类自负-离休
                m.put("FLGRZF_SC", 0);                //分类自负-伤残
                m.put("FLGRZF_QT", FLGRZF);            //分类自负-其它
                m.put("FLGRZF_XJ", FLGRZF);            //分类自负-小计
                m.put("ZF_XJ", 0);                //自负-小计
                m.put("ZF_LX", 0);                //自负-离休
                m.put("ZF_SC", 0);                //自负-伤残
                m.put("ZF_QT", 0);                //自负-其他
                m.put("ZF_YZ", 0);                //自负-遗属
                m.put("ZF_GL", 0);                //自负-高龄
                m.put("ZF_ZC", 0);                //自负-重残
                m.put("TCZF_LX", 0);            //统筹基金支付-离休
                m.put("TCZF_SC", 0);            //统筹基金支付-伤残
                m.put("TCZF_QT", TOTFY - FLGRZF);    //统筹基金支付-其它
                m.put("TCZF_XJ", TOTFY - FLGRZF - ZF);    //统筹基金支付-小计
                m.put("TCZF_YZ", 0);            //统筹基金支付-遗属
                m.put("TCZF_GL", 0);            //统筹基金支付-高龄
                m.put("TCZF_ZC", 0);            //统筹基金支付-重残
                GRZF_QT_SUM += GRZF;            //个人自费-其它
                JZ_QT_SUM += JZNUM;            //就诊人次-其它
            } else if ("xxx".equals(GRXZ)) {        //遗属
                m.put("FLGRZF_LX", 0);            //分类自负-离休
                m.put("FLGRZF_SC", 0);            //分类自负-伤残
                m.put("FLGRZF_QT", 0);            //分类自负-其它
                m.put("FLGRZF_XJ", 0);            //分类自负-小计
                m.put("ZF_XJ", ZF);                //自负-小计
                m.put("ZF_YZ", ZF);                //自负-遗属
                m.put("ZF_GL", 0);                //自负-高龄
                m.put("ZF_ZC", 0);                //自负-重残
                m.put("TCZF_LX", 0);            //统筹基金支付-离休
                m.put("TCZF_SC", 0);            //统筹基金支付-伤残
                m.put("TCZF_QT", 0);            //统筹基金支付-其它
                m.put("TCZF_XJ", TOTFY - FLGRZF - ZF);            //统筹基金支付-小计
                m.put("TCZF_YZ", TCZF);            //统筹基金支付-遗属
                m.put("TCZF_GL", 0);            //统筹基金支付-高龄
                m.put("TCZF_ZC", 0);            //统筹基金支付-重残
                GRZF_YZ_SUM += GRZF;            //个人自费-遗属
                JZ_YZ_SUM += JZNUM;            //就诊人次-遗属
            } else if ("xxx".equals(GRXZ)) {            //高龄
                m.put("FLGRZF_LX", 0);                //分类自负-离休
                m.put("FLGRZF_SC", 0);            //分类自负-伤残
                m.put("FLGRZF_QT", 0);                //分类自负-其它
                m.put("FLGRZF_XJ", 0);            //分类自负-小计
                m.put("ZF_XJ", ZF);                //自负-小计
                m.put("ZF_YZ", 0);                //自负-遗属
                m.put("ZF_GL", ZF);                //自负-高龄
                m.put("ZF_ZC", 0);                //自负-重残
                m.put("TCZF_LX", 0);            //统筹基金支付-离休
                m.put("TCZF_SC", 0);            //统筹基金支付-伤残
                m.put("TCZF_QT", 0);            //统筹基金支付-其它
                m.put("TCZF_XJ", TOTFY - FLGRZF - ZF);            //统筹基金支付-小计
                m.put("TCZF_YZ", 0);            //统筹基金支付-遗属
                m.put("TCZF_GL", TCZF);            //统筹基金支付-高龄
                m.put("TCZF_ZC", 0);            //统筹基金支付-重残
                GRZF_GL_SUM += GRZF;            //个人自费-高龄
                JZ_GL_SUM += JZNUM;            //就诊人次-高龄
            } else if ("xxx".equals(GRXZ)) {        //重残
                m.put("FLGRZF_LX", 0);            //分类自负-离休
                m.put("FLGRZF_SC", 0);            //分类自负-伤残
                m.put("FLGRZF_QT", 0);            //分类自负-其它
                m.put("FLGRZF_XJ", 0);            //分类自负-小计
                m.put("ZF_XJ", ZF);                //自负-小计
                m.put("ZF_YZ", 0);                //自负-遗属
                m.put("ZF_GL", 0);                //自负-高龄
                m.put("ZF_ZC", ZF);                //自负-重残
                m.put("TCZF_LX", 0);            //统筹基金支付-离休
                m.put("TCZF_SC", 0);            //统筹基金支付-伤残
                m.put("TCZF_QT", 0);            //统筹基金支付-其它
                m.put("TCZF_XJ", TOTFY - FLGRZF - ZF);            //统筹基金支付-小计
                m.put("TCZF_YZ", 0);            //统筹基金支付-遗属
                m.put("TCZF_GL", 0);            //统筹基金支付-高龄
                m.put("TCZF_ZC", TCZF);            //统筹基金支付-重残
                GRZF_ZC_SUM += GRZF;            //个人自费-重残
                JZ_ZC_SUM += JZNUM;            //就诊人次-重残
            } else {
                m.put("FLGRZF_LX", 0);            //分类自负-离休
                m.put("FLGRZF_SC", 0);            //分类自负-伤残
                m.put("FLGRZF_QT", 0);            //分类自负-其它
                m.put("FLGRZF_XJ", 0);            //分类自负-小计
                m.put("ZF_XJ", 0);                //自负-小计
                m.put("ZF_YZ", 0);                //自负-遗属
                m.put("ZF_GL", 0);                //自负-高龄
                m.put("ZF_ZC", 0);                //自负-重残
                m.put("TCZF_LX", 0);            //统筹基金支付-离休
                m.put("TCZF_SC", 0);            //统筹基金支付-伤残
                m.put("TCZF_QT", 0);            //统筹基金支付-其它
                m.put("TCZF_XJ", 0);            //统筹基金支付-小计
                m.put("TCZF_YZ", 0);            //统筹基金支付-遗属
                m.put("TCZF_GL", 0);            //统筹基金支付-高龄
                m.put("TCZF_ZC", 0);            //统筹基金支付-重残
            }
            list_a.add(m);
            m.put("XH", ++XH);
            GRZF_SUM += GRZF;
            JZ_SUM += JZNUM;
            JZNUM_SUM += NumberUtils.toDouble(String.valueOf(m.get("JZNUM")));
            TOTFY_SUM += TOTFY;
            FLGRZF_LX_SUM += NumberUtils.toDouble(String.valueOf(m.get("FLGRZF_LX")));
            FLGRZF_SC_SUM += NumberUtils.toDouble(String.valueOf(m.get("FLGRZF_SC")));
            FLGRZF_QT_SUM += NumberUtils.toDouble(String.valueOf(m.get("FLGRZF_QT")));
            FLGRZF_XJ_SUM += NumberUtils.toDouble(String.valueOf(m.get("FLGRZF_XJ")));
            ZF_XJ_SUM += NumberUtils.toDouble(String.valueOf(m.get("ZF_XJ")));
            ZF_YZ_SUM += NumberUtils.toDouble(String.valueOf(m.get("ZF_YZ")));
            ZF_GL_SUM += NumberUtils.toDouble(String.valueOf(m.get("ZF_GL")));
            ZF_ZC_SUM += NumberUtils.toDouble(String.valueOf(m.get("ZF_ZC")));
            TCZF_LX_SUM += NumberUtils.toDouble(String.valueOf(m.get("TCZF_LX")));
            TCZF_SC_SUM += NumberUtils.toDouble(String.valueOf(m.get("TCZF_SC")));
            TCZF_QT_SUM += NumberUtils.toDouble(String.valueOf(m.get("TCZF_QT")));
            TCZF_XJ_SUM += NumberUtils.toDouble(String.valueOf(m.get("TCZF_XJ")));
            TCZF_YZ_SUM += NumberUtils.toDouble(String.valueOf(m.get("TCZF_YZ")));
            TCZF_GL_SUM += NumberUtils.toDouble(String.valueOf(m.get("TCZF_GL")));
            TCZF_ZC_SUM += NumberUtils.toDouble(String.valueOf(m.get("TCZF_ZC")));
        }
        List<CztsAResp> AResp = HisUtil.ListToResultSet(list_a, new CztsAResp());
        cztsahz.setCztssbbresps(AResp);
        Map<String,Object> map = getTsParameters(datefrom,user,list);

        DecimalFormat df = new DecimalFormat("#0.00");
        String TOTFY_SUM_1 = df.format(TOTFY_SUM);
        String FLGRZF_LX_SUM_1 = df.format(FLGRZF_LX_SUM);
        String FLGRZF_SC_SUM_1 = df.format(FLGRZF_SC_SUM);
        String FLGRZF_QT_SUM_1 = df.format(FLGRZF_QT_SUM);
        String FLGRZF_XJ_SUM_1 = df.format(FLGRZF_XJ_SUM);
        String TCZF_LX_SUM_1 = df.format(TCZF_LX_SUM);
        String TCZF_SC_SUM_1 = df.format(TCZF_SC_SUM);
        String TCZF_QT_SUM_1 = df.format(TCZF_QT_SUM);
        String TCZF_XJ_SUM_1 = df.format(TCZF_XJ_SUM);
        String TCZF_YZ_SUM_1 = df.format(TCZF_YZ_SUM);
        String TCZF_GL_SUM_1 = df.format(TCZF_GL_SUM);
        String TCZF_ZC_SUM_1 = df.format(TCZF_ZC_SUM);
        String ZF_XJ_SUM_1 = df.format(ZF_XJ_SUM);
        String ZF_YZ_SUM_1 = df.format(ZF_YZ_SUM);
        String ZF_GL_SUM_1 = df.format(ZF_GL_SUM);
        String ZF_ZC_SUM_1 = df.format(ZF_ZC_SUM);
        String GRZF_LX_SUM_1 = df.format(GRZF_LX_SUM);
        String GRZF_SC_SUM_1 = df.format(GRZF_SC_SUM);
        String GRZF_QT_SUM_1 = df.format(GRZF_QT_SUM);
        String GRZF_XJ_SUM_1 = df.format(GRZF_XJ_SUM);
        String GRZF_YZ_SUM_1 = df.format(GRZF_YZ_SUM);
        String GRZF_GL_SUM_1 = df.format(GRZF_GL_SUM);
        String GRZF_ZC_SUM_1 = df.format(GRZF_ZC_SUM);
        String GRZF_SUM_1 = df.format(GRZF_SUM);

        cztsahz.setJZNUM_SUM(df.format(JZNUM_SUM));
        cztsahz.setTOTFY_SUM(TOTFY_SUM_1);
        cztsahz.setFLGRZF_XJ_SUM(FLGRZF_XJ_SUM_1);
        cztsahz.setFLGRZF_LX_SUM(FLGRZF_LX_SUM_1);
        cztsahz.setFLGRZF_SC_SUM(FLGRZF_SC_SUM_1);
        cztsahz.setFLGRZF_QT_SUM(FLGRZF_QT_SUM_1);
        cztsahz.setZF_XJ_SUM(ZF_XJ_SUM_1);
        cztsahz.setZF_YZ_SUM(ZF_YZ_SUM_1);
        cztsahz.setZF_GL_SUM(ZF_GL_SUM_1);
        cztsahz.setZF_ZC_SUM(ZF_ZC_SUM_1);
        cztsahz.setZF_LX_SUM("0.00");
        cztsahz.setZF_SC_SUM("0.00");
        cztsahz.setZF_QT_SUM("0.00");
        cztsahz.setTCZF_LX_SUM(TCZF_LX_SUM_1);
        cztsahz.setTCZF_SC_SUM(TCZF_SC_SUM_1);
        cztsahz.setTCZF_QT_SUM(TCZF_QT_SUM_1);
        cztsahz.setTCZF_XJ_SUM(TCZF_XJ_SUM_1);
        cztsahz.setTCZF_YZ_SUM(TCZF_YZ_SUM_1);
        cztsahz.setTCZF_GL_SUM(TCZF_GL_SUM_1);
        cztsahz.setTCZF_ZC_SUM(TCZF_ZC_SUM_1);

        cztsahz.setGRZF_SUM(GRZF_SUM_1);
        cztsahz.setGRZF_LX_SUM(GRZF_SUM_1);
        cztsahz.setGRZF_SC_SUM(GRZF_SUM_1);
        cztsahz.setGRZF_QT_SUM(GRZF_SUM_1);
        cztsahz.setGRZF_YZ_SUM(GRZF_SUM_1);
        cztsahz.setGRZF_GL_SUM(GRZF_SUM_1);
        cztsahz.setGRZF_ZC_SUM(GRZF_SUM_1);
        cztsahz.setJZ_LX_SUM(String.valueOf(JZ_LX_SUM));
        cztsahz.setJZ_SC_SUM(String.valueOf(JZ_SC_SUM));
        cztsahz.setJZ_QT_SUM(String.valueOf(JZ_QT_SUM));
        cztsahz.setJZ_SUM(String.valueOf(JZ_SUM));
        cztsahz.setJZ_YZ_SUM(String.valueOf(JZ_YZ_SUM));
        cztsahz.setJZ_GL_SUM(String.valueOf(JZ_GL_SUM));
        cztsahz.setJZ_ZC_SUM(String.valueOf(JZ_ZC_SUM));
        cztsahz.setHosName(map.get("hosName")+"");
        cztsahz.setQuerydate(map.get("querydate")+"");
        cztsahz.setReportDate(map.get("reportDate")+"");
        cztsahz.setHeadno(map.get("headno")+"");
        cztsahz.setTotalRecord(map.get("totalRecord")+"");
        Integer jgid = user.getHospitalId();
        String fzr = sysXtcsCacheSer.getCsz(jgid, "YBBBFZR");
        if (fzr == null || fzr.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        String ybbh = sysXtcsCacheSer.getCsz(jgid, "YBBBBH");
        if (ybbh == null || ybbh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0045");
        }
        String[] fzrArr = fzr.split(",");
        if (fzrArr.length < 3) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        cztsahz.setPresident(fzrArr[0]);
        cztsahz.setFinancialOfficer( fzrArr[0]);
        cztsahz.setReviewer( fzrArr[2]);
        cztsahz.setMaker(user.getUserName());

      /*  int pageSize = 20;
        int totalRecord = list.size();
        int totalPage = (totalRecord + pageSize - 1) / pageSize;
        int start = pageSize + 1;
        int end = (totalPage - 1) * pageSize;
        int i = 0;
        for (Map<String, Object> m : list) {
            ++i;
            m.put("JZNUM_SUM", JZNUM_SUM);
            m.put("TOTFY_SUM", TOTFY_SUM_1);
            m.put("FLGRZF_LX_SUM", FLGRZF_LX_SUM_1);
            m.put("FLGRZF_SC_SUM", FLGRZF_SC_SUM_1);
            m.put("FLGRZF_QT_SUM", FLGRZF_QT_SUM_1);
            m.put("FLGRZF_XJ_SUM", FLGRZF_XJ_SUM_1);
            m.put("ZF_XJ_SUM", ZF_XJ_SUM_1);
            m.put("ZF_YZ_SUM", ZF_YZ_SUM_1);
            m.put("ZF_GL_SUM", ZF_GL_SUM_1);
            m.put("ZF_ZC_SUM", ZF_ZC_SUM_1);
            m.put("ZF_LX_SUM", 0);
            m.put("ZF_SC_SUM", 0);
            m.put("ZF_QT_SUM", 0);
            m.put("TCZF_LX_SUM", TCZF_LX_SUM_1);
            m.put("TCZF_SC_SUM", TCZF_SC_SUM_1);
            m.put("TCZF_QT_SUM", TCZF_QT_SUM_1);
            m.put("TCZF_XJ_SUM", TCZF_XJ_SUM_1);
            m.put("TCZF_YZ_SUM", TCZF_YZ_SUM_1);
            m.put("TCZF_GL_SUM", TCZF_GL_SUM_1);
            m.put("TCZF_ZC_SUM", TCZF_ZC_SUM_1);
            if (i == list.size()) {
                m.put("GRZF_LX_SUM", GRZF_LX_SUM_1);
                m.put("GRZF_SC_SUM", GRZF_SC_SUM_1);
                m.put("GRZF_QT_SUM", GRZF_QT_SUM_1);
                m.put("GRZF_XJ_SUM", GRZF_XJ_SUM_1);

                m.put("GRZF_YZ_SUM", GRZF_YZ_SUM_1);
                m.put("GRZF_GL_SUM", GRZF_GL_SUM_1);
                m.put("GRZF_ZC_SUM", GRZF_ZC_SUM_1);
                m.put("GRZF_SUM", GRZF_SUM_1);

                m.put("JZ_LX_SUM", JZ_LX_SUM);
                m.put("JZ_SC_SUM", JZ_SC_SUM);
                m.put("JZ_QT_SUM", JZ_QT_SUM);
                m.put("JZ_XJ_SUM", JZ_XJ_SUM);
                m.put("JZ_YZ_SUM", JZ_YZ_SUM);
                m.put("JZ_GL_SUM", JZ_GL_SUM);
                m.put("JZ_ZC_SUM", JZ_ZC_SUM);
                m.put("JZ_SUM", JZ_SUM);
            }
            records.add(m);
        }*/
        return cztsahz;
    }

    public Map<String, Object> getTsParameters(String datefrom, SysUser user, List<Map<String, Object>> list) {
        Map<String, Object> response = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        response.put("totalRecord", list.size());
        response.put("hosName", user.getHospitalName());
        response.put("querydate", datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");
        response.put("headno", "编号:74727214300-" + (datefrom.substring(0, 4) + datefrom.substring(5, 7)));//待确认规则
        response.put("reportDate", sdf.format(new Date()));
        return response;
    }

    public CztsSbbHzBResp  getTsFieldsB(String datefrom,SysUser user,List<Map<String, Object>> list) {
        List<Map<String, Object>> records = new ArrayList<>();
        CztsSbbHzBResp cztshzb = new CztsSbbHzBResp();
        List<CztsBResp> cztsb = HisUtil.ListToResultSet(list,new CztsBResp());
        cztshzb.setCztssbbresps(cztsb);
        double FY00_ALL = 0; //
        double FY01_ALL = 0; //
        double FY02_ALL = 0; //
        double FY03_ALL = 0; //
        double FY04_ALL = 0; //
        double FY05_ALL = 0; //
        double FY06_ALL = 0; //
        double FY07_ALL = 0; //
        double FY08_ALL = 0; //
        double FY09_ALL = 0; //
        double FY10_ALL = 0; //
        double FY11_ALL = 0; //诊疗费(自费)
        double TOTFY_ALL = 0; //费用总计
        int XH = 0;
        for (Map<String, Object> m : list) {
            m.put("XH", ++XH);
            TOTFY_ALL += NumberUtils.toDouble(String.valueOf(m.get("TOTFY")));
            FY00_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY00")));
            FY01_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY01")));
            FY02_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY02")));
            FY03_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY03")));
            FY04_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY04")));
            FY05_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY05")));
            FY06_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY06")));
            FY07_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY07")));
            FY08_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY08")));
            FY09_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY09")));
            FY10_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY10")));
            FY11_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY11")));
        }
        DecimalFormat df = new DecimalFormat("#0.00");
        String FY00_ALL_1 = df.format(FY00_ALL);
        String FY01_ALL_1 = df.format(FY01_ALL);
        String FY02_ALL_1 = df.format(FY02_ALL);
        String FY03_ALL_1 = df.format(FY03_ALL);
        String FY04_ALL_1 = df.format(FY04_ALL);
        String FY05_ALL_1 = df.format(FY05_ALL);
        String FY06_ALL_1 = df.format(FY06_ALL);
        String FY07_ALL_1 = df.format(FY07_ALL);
        String FY08_ALL_1 = df.format(FY08_ALL);
        String FY09_ALL_1 = df.format(FY09_ALL);
        String FY10_ALL_1 = df.format(FY10_ALL);
        String FY11_ALL_1 = df.format(FY11_ALL);
        String TOTFY_ALL_1 = df.format(TOTFY_ALL);


        Map<String,Object> map = getTsParametersB(datefrom,user,list);
        cztshzb.setHosName(map.get("hosName")+"");
        cztshzb.setQuerydate(map.get("querydate")+"");
        cztshzb.setReportDate(map.get("reportDate")+"");
        cztshzb.setHeadno(map.get("headno")+"");
        cztshzb.setTotalRecord(map.get("totalRecord")+"");
        Integer jgid = user.getHospitalId();
        String fzr = sysXtcsCacheSer.getCsz(jgid, "YBBBFZR");
        if (fzr == null || fzr.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        String ybbh = sysXtcsCacheSer.getCsz(jgid, "YBBBBH");
        if (ybbh == null || ybbh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0045");
        }
        String[] fzrArr = fzr.split(",");
        if (fzrArr.length < 3) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        cztshzb.setPresident(fzrArr[0]);
        cztshzb.setFinancialOfficer( fzrArr[0]);
        cztshzb.setReviewer( fzrArr[2]);
        cztshzb.setMaker(user.getUserName());

        cztshzb.setFY00_ALL(FY00_ALL_1);
        cztshzb.setFY01_ALL(FY01_ALL_1);
        cztshzb.setFY02_ALL(FY02_ALL_1);
        cztshzb.setFY03_ALL(FY03_ALL_1);
        cztshzb.setFY04_ALL(FY04_ALL_1);
        cztshzb.setFY05_ALL(FY05_ALL_1);
        cztshzb.setFY06_ALL(FY06_ALL_1);
        cztshzb.setFY07_ALL(FY07_ALL_1);
        cztshzb.setFY08_ALL(FY08_ALL_1);
        cztshzb.setFY09_ALL(FY09_ALL_1);
        cztshzb.setFY10_ALL(FY10_ALL_1);
        cztshzb.setFY11_ALL(FY11_ALL_1);
        cztshzb.setTOTFY_ALL(TOTFY_ALL_1);
        return cztshzb;
    }

    public Map<String, Object> getTsParametersB(String datefrom, SysUser user, List<Map<String, Object>> list) {
        Map<String, Object> response = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        response.put("totalRecord", list.size());

        response.put("hosName", user.getHospitalName());
        response.put("querydate", datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");
        response.put("headno",
                "编号:74727214300-" + datefrom.substring(0, 4) + datefrom.substring(5, 7));// 待确认规则
        response.put("reportDate", sdf.format(new Date()));
        return response;
    }

    public Map<String, Object> getTsParametersC(String datefrom, SysUser user, List<Map<String, Object>> list) {
        Map<String, Object> response = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        response.put("hosName", user.getHospitalName());
        response.put("querydate", datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");
        response.put("headno", "编号:747272143");// 待确认规则
        response.put("bankNo", "03416900040019262");
        response.put("bankName", "农行闵行区水清南路支行");
        response.put("reportDate", sdf.format(new Date()));
        //门诊
        double MZJZ_LX_SUM = 0;    //门诊急诊-离休
        double MZJZ_SC_SUM = 0;    //门诊急诊-伤残
        double MZJZ_QT_SUM = 0;    //门诊急诊-其它
        double MZJZ_XJ_SUM = 0;    //门诊急诊-小计
        double MZJZ_YZ_SUM = 0;    //门诊急诊-遗属
        double MZJZ_GL_SUM = 0;    //门诊急诊-高龄
        double MZJZ_ZC_SUM = 0;    //门诊急诊-重残

        double MZJZ_SUM = 0;        //门诊急诊-总计

        int MZRC_LX_SUM = 0;    //门诊人次-离休
        int MZRC_SC_SUM = 0;    //门诊人次-伤残
        int MZRC_QT_SUM = 0;    //门诊人次-其它
        int MZRC_XJ_SUM = 0;    //门诊人次-小计
        int MZRC_YZ_SUM = 0;    //门诊人次-遗属
        int MZRC_GL_SUM = 0;    //门诊人次-高龄
        int MZRC_ZC_SUM = 0;    //门诊人次-重残

        int MZRC_SUM = 0;            //门诊人次-总计

        double YB_LX_SUM = 0;    //医保支付-离休
        double YB_SC_SUM = 0;    //医保支付-伤残
        double YB_QT_SUM = 0;    //医保支付-其它
        double YB_XJ_SUM = 0;    //医保支付-小计
        double YB_YZ_SUM = 0;    //医保支付-遗属
        double YB_GL_SUM = 0;    //医保支付-高龄
        double YB_ZC_SUM = 0;    //医保支付-重残

        double YB_SUM = 0;        //医保支付-总计
        for (Map<String, Object> m : list) {
            String GRXZ = String.valueOf(m.get("GRXZ"));//在退标识(1离休,2伤残,3计划生育,4甲类传染病,9其它)
            double TOTFY = NumberUtils.toDouble(String.valueOf(m.get("TOTFY")));            //(医保结算范围费用总额)
            double JZNUM = NumberUtils.toDouble(String.valueOf(m.get("JZNUM")));    //就诊次数
            if ("1".equals(GRXZ)) {                    //离休
                MZJZ_LX_SUM += TOTFY;
                MZRC_LX_SUM += JZNUM;
            } else if ("2".equals(GRXZ)) {                //伤残
                MZJZ_SC_SUM += TOTFY;
                MZRC_SC_SUM += JZNUM;
            } else if ("9".equals(GRXZ)) {                //其它
                MZJZ_QT_SUM += TOTFY;
                MZRC_QT_SUM += JZNUM;
            } else if ("xxx".equals(GRXZ)) {            //遗属
                MZJZ_YZ_SUM += TOTFY;
                MZRC_YZ_SUM += JZNUM;
            } else if ("xxx".equals(GRXZ)) {            //高龄
                MZJZ_GL_SUM += TOTFY;
                MZRC_GL_SUM += JZNUM;
            } else if ("xxx".equals(GRXZ)) {            //重残
                MZJZ_ZC_SUM += TOTFY;
                MZRC_ZC_SUM += JZNUM;
            }
            MZRC_SUM += JZNUM;
            MZJZ_SUM += TOTFY;
        }
        //住院
        double ZYYB_LX_SUM = 0;    //住院医保-离休
        double ZYYB_SC_SUM = 0;    //住院医保-伤残
        double ZYYB_QT_SUM = 0;    //住院医保-其它
        double ZYYB_XJ_SUM = 0;    //住院医保-小计
        double ZYYB_YZ_SUM = 0;    //住院医保-遗属
        double ZYYB_GL_SUM = 0;    //住院医保-高龄
        double ZYYB_ZC_SUM = 0;    //住院医保-重残
        double ZYYB_SUM = 0;        //住院医保-总计
        int CYRC_LX_SUM = 0;    //出院人次-离休
        int CYRC_SC_SUM = 0;    //出院人次-伤残
        int CYRC_QT_SUM = 0;    //出院人次-其它
        int CYRC_XJ_SUM = 0;    //出院人次-小计
        int CYRC_YZ_SUM = 0;    //出院人次-遗属
        int CYRC_GL_SUM = 0;    //出院人次-高龄
        int CYRC_ZC_SUM = 0;    //出院人次-重残
        int CYRC_SUM = 0;            //出院人次-总计
        //医保总计
        YB_LX_SUM = MZJZ_LX_SUM + ZYYB_LX_SUM;    //医保支付-离休
        YB_SC_SUM = MZJZ_SC_SUM + ZYYB_SC_SUM;    //医保支付-伤残
        YB_QT_SUM = MZJZ_QT_SUM + ZYYB_QT_SUM;    //医保支付-其它
        YB_XJ_SUM = MZJZ_XJ_SUM + ZYYB_XJ_SUM;    //医保支付-小计
        YB_YZ_SUM = MZJZ_YZ_SUM + ZYYB_YZ_SUM;    //医保支付-遗属
        YB_GL_SUM = MZJZ_GL_SUM + ZYYB_GL_SUM;    //医保支付-高龄
        YB_ZC_SUM = MZJZ_ZC_SUM + ZYYB_ZC_SUM;    //医保支付-重残
        YB_SUM = MZJZ_SUM + ZYYB_SUM;            //医保支付-总计
        DecimalFormat df = new DecimalFormat("#0.00");
        String MZJZ_LX_SUM_1 = df.format(MZJZ_LX_SUM);    //门诊急诊-离休
        String MZJZ_SC_SUM_1 = df.format(MZJZ_SC_SUM);    //门诊急诊-伤残
        String MZJZ_QT_SUM_1 = df.format(MZJZ_QT_SUM);    //门诊急诊-其它
        String MZJZ_XJ_SUM_1 = df.format(MZJZ_XJ_SUM);    //门诊急诊-小计
        String MZJZ_YZ_SUM_1 = df.format(MZJZ_YZ_SUM);    //门诊急诊-遗属
        String MZJZ_GL_SUM_1 = df.format(MZJZ_GL_SUM);    //门诊急诊-高龄
        String MZJZ_ZC_SUM_1 = df.format(MZJZ_ZC_SUM);    //门诊急诊-重残
        String MZJZ_SUM_1 = df.format(MZJZ_SUM);        //门诊急诊-总计
        String ZYYB_LX_SUM_1 = df.format(ZYYB_LX_SUM);    //住院医保-离休
        String ZYYB_SC_SUM_1 = df.format(ZYYB_SC_SUM);    //住院医保-伤残
        String ZYYB_QT_SUM_1 = df.format(ZYYB_QT_SUM);    //住院医保-其它
        String ZYYB_XJ_SUM_1 = df.format(ZYYB_XJ_SUM);    //住院医保-小计
        String ZYYB_YZ_SUM_1 = df.format(ZYYB_YZ_SUM);    //住院医保-遗属
        String ZYYB_GL_SUM_1 = df.format(ZYYB_GL_SUM);    //住院医保-高龄
        String ZYYB_ZC_SUM_1 = df.format(ZYYB_ZC_SUM);    //住院医保-重残
        String ZYYB_SUM_1 = df.format(ZYYB_SUM);        //住院医保-总计
        String YB_LX_SUM_1 = df.format(YB_LX_SUM);    //医保-离休
        String YB_SC_SUM_1 = df.format(YB_SC_SUM);    //医保-伤残
        String YB_QT_SUM_1 = df.format(YB_QT_SUM);    //医保-其它
        String YB_XJ_SUM_1 = df.format(YB_XJ_SUM);    //医保-小计
        String YB_YZ_SUM_1 = df.format(YB_YZ_SUM);    //医保-遗属
        String YB_GL_SUM_1 = df.format(YB_GL_SUM);    //医保-高龄
        String YB_ZC_SUM_1 = df.format(YB_ZC_SUM);    //医保-重残
        String YB_SUM_1 = df.format(YB_SUM);        //医保-总计
        response.put("MZJZ_LX_SUM", MZJZ_LX_SUM_1);    //门诊急诊-离休
        response.put("MZJZ_SC_SUM", MZJZ_SC_SUM_1);    //门诊急诊-伤残
        response.put("MZJZ_QT_SUM", MZJZ_QT_SUM_1);    //门诊急诊-其它
        response.put("MZJZ_XJ_SUM", MZJZ_XJ_SUM_1);    //门诊急诊-小计
        response.put("MZJZ_YZ_SUM", MZJZ_YZ_SUM_1);    //门诊急诊-遗属
        response.put("MZJZ_GL_SUM", MZJZ_GL_SUM_1);    //门诊急诊-高龄
        response.put("MZJZ_ZC_SUM", MZJZ_ZC_SUM_1);    //门诊急诊-重残
        response.put("MZJZ_SUM", MZJZ_SUM_1);            //门诊急诊-总计
        response.put("MZRC_LX_SUM", MZRC_LX_SUM);    //门诊人次-离休
        response.put("MZRC_SC_SUM", MZRC_SC_SUM);    //门诊人次-伤残
        response.put("MZRC_QT_SUM", MZRC_QT_SUM);    //门诊人次-其它
        response.put("MZRC_XJ_SUM", MZRC_XJ_SUM);    //门诊人次-小计
        response.put("MZRC_YZ_SUM", MZRC_YZ_SUM);    //门诊人次-遗属
        response.put("MZRC_GL_SUM", MZRC_GL_SUM);    //门诊人次-高龄
        response.put("MZRC_ZC_SUM", MZRC_ZC_SUM);    //门诊人次-重残
        response.put("MZRC_SUM", MZRC_SUM);            //门诊人次-总计
        response.put("ZYYB_LX_SUM", ZYYB_LX_SUM_1);    //住院医保-离休
        response.put("ZYYB_SC_SUM", ZYYB_SC_SUM_1);    //住院医保-伤残
        response.put("ZYYB_QT_SUM", ZYYB_QT_SUM_1);    //住院医保-其它
        response.put("ZYYB_XJ_SUM", ZYYB_XJ_SUM_1);    //住院医保-小计
        response.put("ZYYB_YZ_SUM", ZYYB_YZ_SUM_1);    //住院医保-遗属
        response.put("ZYYB_GL_SUM", ZYYB_GL_SUM_1);    //住院医保-高龄
        response.put("ZYYB_ZC_SUM", ZYYB_ZC_SUM_1);    //住院医保-重残
        response.put("ZYYB_SUM", ZYYB_SUM_1);            //住院医保-总计
        response.put("CYRC_LX_SUM", CYRC_LX_SUM);    //出院人次-离休
        response.put("CYRC_SC_SUM", CYRC_SC_SUM);    //出院人次-伤残
        response.put("CYRC_QT_SUM", CYRC_QT_SUM);    //出院人次-其它
        response.put("CYRC_XJ_SUM", CYRC_XJ_SUM);    //出院人次-小计
        response.put("CYRC_YZ_SUM", CYRC_YZ_SUM);    //出院人次-遗属
        response.put("CYRC_GL_SUM", CYRC_GL_SUM);    //出院人次-高龄
        response.put("CYRC_ZC_SUM", CYRC_ZC_SUM);    //出院人次-重残
        response.put("CYRC_SUM", CYRC_SUM);            //出院人次-总计
        response.put("YB_LX_SUM", YB_LX_SUM_1);    //医保-离休
        response.put("YB_SC_SUM", YB_SC_SUM_1);    //医保-伤残
        response.put("YB_QT_SUM", YB_QT_SUM_1);    //医保-其它
        response.put("YB_XJ_SUM", YB_XJ_SUM_1);    //医保-小计
        response.put("YB_YZ_SUM", YB_YZ_SUM_1);    //医保-遗属
        response.put("YB_GL_SUM", YB_GL_SUM_1);    //医保-高龄
        response.put("YB_ZC_SUM", YB_ZC_SUM_1);    //医保-重残
        response.put("YB_SUM", YB_SUM_1);            //医保-总计
        response.put("YB_SUM_DX", BSPHISUtil.changeMoneyUpper(NumberUtils.toDouble(YB_SUM_1)));
        return response;
    }


    ///城镇特殊住院A
    public CztszySbbHzAResp getTsZyFieldsA(List<Map<String, Object>> list,String datefrom,SysUser user) {
        CztszySbbHzAResp cztszya = new CztszySbbHzAResp();
        int ZYDNUM_SUM = 0; 	//住院天数
        double TOTFY_SUM = 0; //费用总计

        double FLGRZF_LX_SUM = 0; 	//分类自负-离休
        double FLGRZF_SC_SUM = 0; 	//分类自负-伤残
        double FLGRZF_QT_SUM = 0; 	//分类自负-离休
        double FLGRZF_XJ_SUM = 0; 	//分类自负-伤残

        double ZF_XJ_SUM = 0; 	//自负-小计
        double ZF_YZ_SUM = 0; 	//自负-遗属
        double ZF_GL_SUM = 0; 	//自负-高龄
        double ZF_ZC_SUM = 0; 	//自负-重残


        double TCZF_LX_SUM = 0; 	//统筹-离休
        double TCZF_SC_SUM = 0; 	//统筹-伤残
        double TCZF_QT_SUM = 0; 	//统筹-其它
        double TCZF_XJ_SUM = 0; 	//统筹-小计
        double TCZF_YZ_SUM = 0; 	//统筹-遗属
        double TCZF_GL_SUM = 0; 	//统筹-高龄
        double TCZF_ZC_SUM = 0; 	//统筹-重残

        double GRZF_LX_SUM = 0; 	//个人自费-离休
        double GRZF_SC_SUM = 0; 	//个人自费-伤残
        double GRZF_QT_SUM = 0; 	//个人自费-其它
        double GRZF_XJ_SUM = 0; 	//个人自费-小计
        double GRZF_YZ_SUM = 0; 	//个人自费-遗属
        double GRZF_GL_SUM = 0; 	//个人自费-高龄
        double GRZF_ZC_SUM = 0; 	//个人自费-重残
        double GRZF_SUM = 0; 		//个人自费-总计

        int JZ_LX_SUM = 0; 			//就诊人次-离休
        int JZ_SC_SUM = 0; 			//就诊人次-伤残
        int JZ_QT_SUM = 0; 			//就诊人次-其它
        int JZ_XJ_SUM = 0; 			//就诊人次-小计
        int JZ_YZ_SUM = 0; 			//就诊人次-遗属
        int JZ_GL_SUM = 0; 			//就诊人次-高龄
        int JZ_ZC_SUM = 0; 			//就诊人次-重残
        int JZ_SUM = 0; 			//就诊人次-总计

        int XH =0;
        for(Map<String, Object> m : list){
            String GRXZ = String.valueOf(m.get("GRXZ"));//在退标识(1离休,2伤残,3计划生育,4甲类传染病,9其它)
            double FLGRZF =  NumberUtils.toDouble(String.valueOf(m.get("FLGRZF"))); 	//分类自负
            double ZF =  NumberUtils.toDouble(String.valueOf(m.get("GRXJ"))); 			//自负
            double TCZF =  NumberUtils.toDouble(String.valueOf(m.get("TCZF"))); 		//(统筹基金支付)
            double GRZF =  NumberUtils.toDouble(String.valueOf(m.get("ZF"))); 			//个人自费
            double TOTFY = NumberUtils.toDouble(String.valueOf(m.get("TOTFY")));	//总费用

            double ZYDNUM =  NumberUtils.toDouble(String.valueOf(m.get("ZYDNUM"))); 	//住院天数
            String CYDATE = String.valueOf(m.get("CYDATE"));//出院日期
            int k = ZYDNUM <  0 ? -1 : 1;
            k = ("".equals(CYDATE)  || "0".equals(CYDATE)) ? 0 :  k;

            if("1".equals(GRXZ)){				//离休
                m.put("FLGRZF_LX", FLGRZF);		//分类自负-离休
                m.put("FLGRZF_SC", 0);			//分类自负-伤残
                m.put("FLGRZF_QT", 0);			//分类自负-其它
                m.put("FLGRZF_XJ", FLGRZF);		//分类自负-小计

                m.put("ZF_XJ", ZF);				//自负-小计
                m.put("ZF_YZ", 0);				//自负-遗属
                m.put("ZF_GL", 0);				//自负-高龄
                m.put("ZF_ZC", ZF);				//自负-重残

                m.put("TCZF_LX", TOTFY-FLGRZF);			//统筹基金支付-离休
                m.put("TCZF_SC", 0);			//统筹基金支付-伤残
                m.put("TCZF_QT", 0);			//统筹基金支付-其它
                m.put("TCZF_XJ", TOTFY-FLGRZF-ZF);			//统筹基金支付-小计
                m.put("TCZF_YZ", 0);			//统筹基金支付-遗属
                m.put("TCZF_GL", 0);			//统筹基金支付-高龄
                m.put("TCZF_ZC", 0);			//统筹基金支付-重残

                GRZF_LX_SUM += GRZF;			//个人自费-离休
                JZ_LX_SUM  += k;				//就诊人次-离休

            }else if("2".equals(GRXZ)){				//伤残
                m.put("FLGRZF_LX", 0);				//分类自负-离休
                m.put("FLGRZF_SC", FLGRZF);			//分类自负-伤残
                m.put("FLGRZF_QT", 0);				//分类自负-其它
                m.put("FLGRZF_XJ", FLGRZF);			//分类自负-小计

                m.put("ZF_XJ", 0);				//自负-小计
                m.put("ZF_YZ", 0);				//自负-遗属
                m.put("ZF_GL", 0);				//自负-高龄
                m.put("ZF_ZC", 0);				//自负-重残

                m.put("TCZF_LX", 0);			//统筹基金支付-离休
                m.put("TCZF_SC", TOTFY-FLGRZF);	//统筹基金支付-伤残
                m.put("TCZF_QT", 0);			//统筹基金支付-其它
                m.put("TCZF_XJ", TOTFY-FLGRZF-ZF);			//统筹基金支付-小计
                m.put("TCZF_YZ", 0);			//统筹基金支付-遗属
                m.put("TCZF_GL", 0);			//统筹基金支付-高龄
                m.put("TCZF_ZC", 0);			//统筹基金支付-重残

                GRZF_SC_SUM += GRZF;			//个人自费-伤残
                JZ_SC_SUM  += k;				//就诊人次-伤残

            }else if("9".equals(GRXZ)){				//其它
                m.put("FLGRZF_LX", 0);				//分类自负-离休
                m.put("FLGRZF_SC", 0);				//分类自负-伤残
                m.put("FLGRZF_QT", FLGRZF);			//分类自负-其它
                m.put("FLGRZF_XJ", FLGRZF);			//分类自负-小计

                m.put("ZF_XJ", 0);				//自负-小计
                m.put("ZF_YZ", 0);				//自负-遗属
                m.put("ZF_GL", 0);				//自负-高龄
                m.put("ZF_ZC", 0);				//自负-重残

                m.put("TCZF_LX", 0);			//统筹基金支付-离休
                m.put("TCZF_SC", 0);			//统筹基金支付-伤残
                m.put("TCZF_QT", TOTFY-FLGRZF);			//统筹基金支付-其它
                m.put("TCZF_XJ", TOTFY-FLGRZF-ZF);		//统筹基金支付-小计
                m.put("TCZF_YZ", 0);			//统筹基金支付-遗属
                m.put("TCZF_GL", 0);			//统筹基金支付-高龄
                m.put("TCZF_ZC", 0);			//统筹基金支付-重残

                GRZF_QT_SUM += GRZF;			//个人自费-其它
                JZ_QT_SUM  += k;				//就诊人次-其它

            }else if("xxx".equals(GRXZ)){		//遗属
                m.put("FLGRZF_LX", 0);			//分类自负-离休
                m.put("FLGRZF_SC", 0);			//分类自负-伤残
                m.put("FLGRZF_QT", 0);			//分类自负-其它
                m.put("FLGRZF_XJ", 0);			//分类自负-小计

                m.put("ZF_XJ", ZF);				//自负-小计
                m.put("ZF_YZ", ZF);				//自负-遗属
                m.put("ZF_GL", 0);				//自负-高龄
                m.put("ZF_ZC", 0);				//自负-重残

                m.put("TCZF_LX", 0);			//统筹基金支付-离休
                m.put("TCZF_SC", 0);			//统筹基金支付-伤残
                m.put("TCZF_QT", 0);			//统筹基金支付-其它
                m.put("TCZF_XJ", TCZF);			//统筹基金支付-小计
                m.put("TCZF_YZ", TCZF);			//统筹基金支付-遗属
                m.put("TCZF_GL", 0);			//统筹基金支付-高龄
                m.put("TCZF_ZC", 0);			//统筹基金支付-重残

                GRZF_YZ_SUM += GRZF;			//个人自费-遗属
                JZ_YZ_SUM  += k;				//就诊人次-遗属

            }else if("xxx".equals(GRXZ)){			//高龄
                m.put("FLGRZF_LX", 0);				//分类自负-离休
                m.put("FLGRZF_SC", 0);			//分类自负-伤残
                m.put("FLGRZF_QT", 0);				//分类自负-其它
                m.put("FLGRZF_XJ", 0);			//分类自负-小计

                m.put("ZF_XJ", ZF);				//自负-小计
                m.put("ZF_YZ", 0);				//自负-遗属
                m.put("ZF_GL", ZF);				//自负-高龄
                m.put("ZF_ZC", 0);				//自负-重残

                m.put("TCZF_LX", 0);			//统筹基金支付-离休
                m.put("TCZF_SC", 0);			//统筹基金支付-伤残
                m.put("TCZF_QT", 0);			//统筹基金支付-其它
                m.put("TCZF_XJ", TCZF);			//统筹基金支付-小计
                m.put("TCZF_YZ", 0);			//统筹基金支付-遗属
                m.put("TCZF_GL", TCZF);			//统筹基金支付-高龄
                m.put("TCZF_ZC", 0);			//统筹基金支付-重残

                GRZF_GL_SUM += GRZF;			//个人自费-高龄
                JZ_GL_SUM  += k;			//就诊人次-高龄

            }else if("xxx".equals(GRXZ)){			//重残
                m.put("FLGRZF_LX", 0);				//分类自负-离休
                m.put("FLGRZF_SC", 0);			//分类自负-伤残
                m.put("FLGRZF_QT", 0);				//分类自负-其它
                m.put("FLGRZF_XJ", 0);			//分类自负-小计

                m.put("ZF_XJ", ZF);				//自负-小计
                m.put("ZF_YZ", 0);				//自负-遗属
                m.put("ZF_GL", 0);				//自负-高龄
                m.put("ZF_ZC", ZF);				//自负-重残

                m.put("TCZF_LX", 0);			//统筹基金支付-离休
                m.put("TCZF_SC", 0);			//统筹基金支付-伤残
                m.put("TCZF_QT", 0);			//统筹基金支付-其它
                m.put("TCZF_XJ", TCZF);			//统筹基金支付-小计
                m.put("TCZF_YZ", 0);			//统筹基金支付-遗属
                m.put("TCZF_GL", 0);			//统筹基金支付-高龄
                m.put("TCZF_ZC", TCZF);			//统筹基金支付-重残

                GRZF_ZC_SUM += GRZF;			//个人自费-重残
                JZ_ZC_SUM  += k;				//就诊人次-重残

            }else {
                m.put("FLGRZF_LX", 0);			//分类自负-离休
                m.put("FLGRZF_SC", 0);			//分类自负-伤残
                m.put("FLGRZF_QT", 0);			//分类自负-其它
                m.put("FLGRZF_XJ", 0);			//分类自负-小计

                m.put("ZF_XJ", 0);				//自负-小计
                m.put("ZF_YZ", 0);				//自负-遗属
                m.put("ZF_GL", 0);				//自负-高龄
                m.put("ZF_ZC", 0);				//自负-重残

                m.put("TCZF_LX", 0);			//统筹基金支付-离休
                m.put("TCZF_SC", 0);			//统筹基金支付-伤残
                m.put("TCZF_QT", 0);			//统筹基金支付-其它
                m.put("TCZF_XJ", 0);			//统筹基金支付-小计
                m.put("TCZF_YZ", 0);			//统筹基金支付-遗属
                m.put("TCZF_GL", 0);			//统筹基金支付-高龄
                m.put("TCZF_ZC", 0);			//统筹基金支付-重残
            }
            m.put("XH", ++XH);

            GRZF_SUM+= GRZF;
            JZ_SUM+= k;

            ZYDNUM_SUM += NumberUtils.toDouble(String.valueOf(m.get("ZYDNUM")));
            TOTFY_SUM += NumberUtils.toDouble(String.valueOf(m.get("TOTFY")));

            FLGRZF_LX_SUM += NumberUtils.toDouble(String.valueOf(m.get("FLGRZF_LX")));
            FLGRZF_SC_SUM += NumberUtils.toDouble(String.valueOf(m.get("FLGRZF_SC")));
            FLGRZF_QT_SUM += NumberUtils.toDouble(String.valueOf(m.get("FLGRZF_QT")));
            FLGRZF_XJ_SUM += NumberUtils.toDouble(String.valueOf(m.get("FLGRZF_XJ")));

            ZF_XJ_SUM += NumberUtils.toDouble(String.valueOf(m.get("ZF_XJ")));
            ZF_YZ_SUM += NumberUtils.toDouble(String.valueOf(m.get("ZF_YZ")));
            ZF_GL_SUM += NumberUtils.toDouble(String.valueOf(m.get("ZF_GL")));
            ZF_ZC_SUM += NumberUtils.toDouble(String.valueOf(m.get("ZF_ZC")));

            TCZF_LX_SUM += NumberUtils.toDouble(String.valueOf(m.get("TCZF_LX")));
            TCZF_SC_SUM += NumberUtils.toDouble(String.valueOf(m.get("TCZF_SC")));
            TCZF_QT_SUM += NumberUtils.toDouble(String.valueOf(m.get("TCZF_QT")));
            TCZF_XJ_SUM += NumberUtils.toDouble(String.valueOf(m.get("TCZF_XJ")));
            TCZF_YZ_SUM += NumberUtils.toDouble(String.valueOf(m.get("TCZF_YZ")));
            TCZF_GL_SUM += NumberUtils.toDouble(String.valueOf(m.get("TCZF_GL")));
            TCZF_ZC_SUM += NumberUtils.toDouble(String.valueOf(m.get("TCZF_ZC")));
        }
        List<CztszyAResp> AResp = HisUtil.ListToResultSet(list, new CztszyAResp());
        cztszya.setCztszysbbresps(AResp);

        DecimalFormat df = new DecimalFormat("#0.00");
        String TOTFY_SUM_1 =  df.format(TOTFY_SUM);

        String FLGRZF_LX_SUM_1 = df.format(FLGRZF_LX_SUM);
        String FLGRZF_SC_SUM_1 = df.format(FLGRZF_SC_SUM);
        String FLGRZF_QT_SUM_1 = df.format(FLGRZF_QT_SUM);
        String FLGRZF_XJ_SUM_1 = df.format(FLGRZF_XJ_SUM);

        String TCZF_LX_SUM_1 = df.format(TCZF_LX_SUM);
        String TCZF_SC_SUM_1 = df.format(TCZF_SC_SUM);
        String TCZF_QT_SUM_1 = df.format(TCZF_QT_SUM);
        String TCZF_XJ_SUM_1 = df.format(TCZF_XJ_SUM);
        String TCZF_YZ_SUM_1 = df.format(TCZF_YZ_SUM);
        String TCZF_GL_SUM_1 = df.format(TCZF_GL_SUM);
        String TCZF_ZC_SUM_1 = df.format(TCZF_ZC_SUM);

        String ZF_XJ_SUM_1 = df.format(ZF_XJ_SUM);
        String ZF_YZ_SUM_1 = df.format(ZF_YZ_SUM);
        String ZF_GL_SUM_1 = df.format(ZF_GL_SUM);
        String ZF_ZC_SUM_1 = df.format(ZF_ZC_SUM);

        String GRZF_LX_SUM_1 = df.format(GRZF_LX_SUM);
        String GRZF_SC_SUM_1 = df.format(GRZF_SC_SUM);
        String GRZF_QT_SUM_1 = df.format(GRZF_QT_SUM);
        String GRZF_XJ_SUM_1 = df.format(GRZF_XJ_SUM);
        String GRZF_YZ_SUM_1 = df.format(GRZF_YZ_SUM);
        String GRZF_GL_SUM_1 = df.format(GRZF_GL_SUM);
        String GRZF_ZC_SUM_1 = df.format(GRZF_ZC_SUM);
        String GRZF_SUM_1 = df.format(GRZF_SUM);

        cztszya.setZYDNUM_SUM(ZYDNUM_SUM+"");
        cztszya.setTOTFY_SUM(TOTFY_SUM_1+"");
        cztszya.setFLGRZF_LX_SUM(FLGRZF_LX_SUM_1+"");
        cztszya.setFLGRZF_SC_SUM(FLGRZF_SC_SUM_1+"");
        cztszya.setFLGRZF_QT_SUM(FLGRZF_QT_SUM_1+"");
        cztszya.setFLGRZF_XJ_SUM(FLGRZF_XJ_SUM_1+"");
        cztszya.setZF_XJ_SUM(ZF_XJ_SUM_1+"");
        cztszya.setZF_YZ_SUM(ZF_YZ_SUM_1+"");
        cztszya.setZF_GL_SUM(ZF_GL_SUM_1+"");
        cztszya.setZF_ZC_SUM(ZF_ZC_SUM_1+"");
        cztszya.setTCZF_LX_SUM(TCZF_LX_SUM_1+"");
        cztszya.setTCZF_SC_SUM(TCZF_SC_SUM_1+"");
        cztszya.setTCZF_QT_SUM(TCZF_QT_SUM_1+"");
        cztszya.setTCZF_XJ_SUM(TCZF_XJ_SUM_1+"");
        cztszya.setTCZF_YZ_SUM(TCZF_YZ_SUM_1+"");
        cztszya.setTCZF_GL_SUM(TCZF_GL_SUM_1+"");
        cztszya.setTCZF_ZC_SUM(TCZF_ZC_SUM_1+"");

        cztszya.setGRZF_LX_SUM(GRZF_LX_SUM_1+"");
        cztszya.setGRZF_SC_SUM(GRZF_SC_SUM_1+"");
        cztszya.setGRZF_QT_SUM(GRZF_QT_SUM_1+"");
        cztszya.setGRZF_YZ_SUM(GRZF_YZ_SUM_1+"");
        cztszya.setGRZF_GL_SUM(GRZF_GL_SUM_1+"");
        cztszya.setGRZF_ZC_SUM(GRZF_ZC_SUM_1+"");
        cztszya.setGRZF_SUM(GRZF_SUM_1+"");

        cztszya.setJZ_LX_SUM(JZ_LX_SUM+"");
        cztszya.setJZ_SC_SUM(JZ_SC_SUM+"");
        cztszya.setJZ_QT_SUM(JZ_QT_SUM+"");
        cztszya.setJZ_YZ_SUM(JZ_YZ_SUM+"");
        cztszya.setJZ_GL_SUM(JZ_GL_SUM+"");
        cztszya.setJZ_ZC_SUM(JZ_ZC_SUM+"");
        cztszya.setJZ_SUM(JZ_SUM+"");
        Integer jgid = user.getHospitalId();
        String fzr = sysXtcsCacheSer.getCsz(jgid, "YBBBFZR");
        if (fzr == null || fzr.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        String ybbh = sysXtcsCacheSer.getCsz(jgid, "YBBBBH");
        if (ybbh == null || ybbh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0045");
        }
        String[] fzrArr = fzr.split(",");
        if (fzrArr.length < 3) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        cztszya.setPresident(fzrArr[0]);
        cztszya.setFinancialOfficer( fzrArr[0]);
        cztszya.setReviewer( fzrArr[2]);
        cztszya.setMaker(user.getUserName());
        cztszya.setTotalRecord(list.size()+"");
        cztszya.setHosName(user.getHospitalName());
        cztszya.setQuerydate(datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");
        cztszya.setHeadno("编号:74727214300-" + datefrom.substring(0, 4) + datefrom.substring(5, 7));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        cztszya.setReportDate(sdf.format(new Date()));
        return cztszya;
    }


    ///城镇特殊住院B
    public CztszySbbHzBResp getTsZyFieldsB(List<Map<String, Object>> list,String datefrom,SysUser user) {
        CztszySbbHzBResp cztszybhz = new CztszySbbHzBResp();
        List<CztszyBResp> cztszyb = HisUtil.ListToResultSet(list, new CztszyBResp());
        cztszybhz.setCztszysbbresps(cztszyb);
        double FY00_ALL = 0; //
        double FY01_ALL = 0; //
        double FY02_ALL = 0; //
        double FY03_ALL = 0; //
        double FY04_ALL = 0; //
        double FY05_ALL = 0; //
        double FY06_ALL = 0; //
        double FY07_ALL = 0; //
        double FY08_ALL = 0; //
        double FY09_ALL = 0; //
        double FY10_ALL = 0; //
        double FY11_ALL = 0; //
        double FY12_ALL = 0; //
        double FY13_ALL = 0; //
        double FY14_ALL = 0; //
        double FY15_ALL = 0; //
        double TOTFY_ALL = 0; //费用总计

        int XH =0;

        for(Map<String, Object> m : list){
            m.put("XH", ++XH);
            TOTFY_ALL += NumberUtils.toDouble(String.valueOf(m.get("TOTFY")));
            //FY00_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY00")));
            FY01_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY01")));
            FY02_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY02")));
            FY03_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY03")));
            FY04_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY04")));
            FY05_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY05")));
            FY06_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY06")));
            FY07_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY07")));
            FY08_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY08")));
            FY09_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY09")));
            FY10_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY10")));
            FY11_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY11")));
            FY12_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY12")));
            FY13_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY13")));
            FY14_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY14")));
            FY15_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY15")));
        }
        cztszybhz.setFY01_ALL(FY01_ALL+"");
        cztszybhz.setFY02_ALL(FY02_ALL+"");
        cztszybhz.setFY03_ALL(FY03_ALL+"");
        cztszybhz.setFY04_ALL(FY04_ALL+"");
        cztszybhz.setFY05_ALL(FY05_ALL+"");
        cztszybhz.setFY06_ALL(FY06_ALL+"");
        cztszybhz.setFY07_ALL(FY07_ALL+"");
        cztszybhz.setFY08_ALL(FY08_ALL+"");
        cztszybhz.setFY09_ALL(FY09_ALL+"");
        cztszybhz.setFY10_ALL(FY10_ALL+"");
        cztszybhz.setFY11_ALL(FY11_ALL+"");
        cztszybhz.setFY12_ALL(FY12_ALL+"");
        cztszybhz.setFY13_ALL(FY13_ALL+"");
        cztszybhz.setFY14_ALL(FY14_ALL+"");
        cztszybhz.setFY15_ALL(FY15_ALL+"");
        cztszybhz.setTOTFY_ALL(TOTFY_ALL+"");
        Integer jgid = user.getHospitalId();
        String fzr = sysXtcsCacheSer.getCsz(jgid, "YBBBFZR");
        if (fzr == null || fzr.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        String ybbh = sysXtcsCacheSer.getCsz(jgid, "YBBBBH");
        if (ybbh == null || ybbh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0045");
        }
        String[] fzrArr = fzr.split(",");
        if (fzrArr.length < 3) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        cztszybhz.setPresident(fzrArr[0]);
        cztszybhz.setFinancialOfficer( fzrArr[0]);
        cztszybhz.setReviewer( fzrArr[2]);
        cztszybhz.setMaker(user.getUserName());
        cztszybhz.setTotalRecord(list.size()+"");
        cztszybhz.setHosName(user.getHospitalName());
        cztszybhz.setQuerydate(datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");
        cztszybhz.setHeadno("编号:74727214300-" + datefrom.substring(0, 4) + datefrom.substring(5, 7));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        cztszybhz.setReportDate(sdf.format(new Date()));
        return cztszybhz;
    }

    //////城镇居民///////
    public List<Map<String, Object>> QueryYbJmCityNormalListire(String daa, String datefrom, String dateto, String mzlb) {
        logger.info("城镇居民上报");

        return shybbbDao.queryYbJmCityNormalListire(daa, datefrom, dateto, mzlb);
    }

    public CzjmSbbHzAResp getJmFields(List<Map<String, Object>> list,String datefrom,SysUser user) {
        //   if (isPrint==null) {
        CzjmSbbHzAResp res = getJmFieldsForView(list,datefrom,user);
//        }else{
//            getFieldsForPrint(req, records, list);
//        }
        return res;
    }

    private CzjmSbbHzAResp getJmFieldsForView(List<Map<String, Object>> list,String datefrom,SysUser user) {
        List<Map<String, Object>> records = new ArrayList<>();
        CzjmSbbHzAResp czjmres = new CzjmSbbHzAResp();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            map.put("XH", i + 1);
            map.put("BRXM", map.get("NAME"));    // 病人姓名
            map.put("YBID", map.get("YBID"));    // 凭证编号
            map.put("KSNA", map.get("KSNA"));    // 科室名称
            map.put("JZDATE", map.get("JZDATE")); // 就诊日期
            map.put("JZNUM", map.get("JZNUM")); // 就诊人次
            map.put("TOTFY", map.get("TOTFY")); // 费用总计
            map.put("FLGRZF", map.get("FLGRZF")); // 分类自负
            map.put("GRXJ", map.get("GRXJ")); // 自负
            map.put("TCBX", map.get("TCBX")); // 基金支付
            records.add(map);
        }
        List<CzjmAResp> czjmaResp = HisUtil.ListToResultSet(records, new CzjmAResp());
        czjmres.setCzjmsbbresps(czjmaResp);
        Map<String,Object> map = getJmParameters(datefrom,user,list);
        DecimalFormat df = new DecimalFormat("#0.00");
        czjmres.setTOTFY_ZJ(map.get("TOTFY_ZJ")+"");
        czjmres.setFLGRZF_ZJ(map.get("FLGRZF_ZJ")+"");
        czjmres.setGRXJ_ZJ(map.get("GRXJ_ZJ")+"");
        czjmres.setJZNUM(map.get("JZNUM")+"");
        czjmres.setJZNUM_YZ(map.get("JZNUM_YZ")+"");
        czjmres.setJZNUM_GL(map.get("JZNUM_GL")+"");
        czjmres.setJZNUM_ZC(map.get("JZNUM_ZC")+"");
        czjmres.setJZNUM_ZX(map.get("JZNUM_ZX")+"");
        czjmres.setJZNUM_QT(map.get("JZNUM_QT")+"");
        czjmres.setTCBX(map.get("TCBX")+"");
        czjmres.setTCBX_YZ(map.get("TCBX_YZ")+"");
        czjmres.setTCBX_GL(map.get("TCBX_GL")+"");
        czjmres.setTCBX_ZC(map.get("TCBX_ZC")+"");
        czjmres.setTCBX_ZX(map.get("TCBX_ZX")+"");
        czjmres.setTCBX_QT(map.get("TCBX_QT")+"");
        czjmres.setZF(map.get("ZF")+"");
        czjmres.setZF_YZ(map.get("ZF_YZ")+"");
        czjmres.setZF_GL(map.get("ZF_GL")+"");
        czjmres.setZF_ZC(map.get("ZF_ZC")+"");
        czjmres.setZF_ZX(map.get("ZF_ZX")+"");
        czjmres.setZF_QT(map.get("ZF_QT")+"");

        czjmres.setHosName(map.get("hosName")+"");
        czjmres.setFinancialOfficer(map.get("financialOfficer")+"");
        czjmres.setQuerydate(map.get("querydate")+"");
        czjmres.setMaker(map.get("maker")+"");
        czjmres.setReviewer(map.get("reviewer")+"");
        czjmres.setReportDate(map.get("reportDate")+"");
        czjmres.setHeadno(map.get("headno")+"");
        czjmres.setPresident(map.get("president")+"");
        return czjmres;
    }

    public Map<String, Object> getJmParameters(String datefrom, SysUser user, List<Map<String, Object>> list) {
        Map<String, Object> response = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Integer jgid = user.getHospitalId();
        String fzr = sysXtcsCacheSer.getCsz(jgid, "YBBBFZR");
        if (fzr == null || fzr.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        String yhzh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHZH");
        if (yhzh == null || yhzh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0045");
        }
        String yhmc = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHMC");
        if (yhmc == null || yhmc.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0046");
        }
        String lxdh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBLXDH");
        if (lxdh == null || lxdh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0047");
        }
        String ybbh = sysXtcsCacheSer.getCsz(jgid, "YBBBBH");
        if (ybbh == null || ybbh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0044");
        }
//        if (isPrint==null) {
        response = getParametersForView(list);
//        }else{
//            getParametersForPrint(response, list);
//        }
        String[] fzrArr = fzr.split(",");
        response.put("president", "医疗机构负责人:" + fzrArr[0]);
        response.put("financialOfficer", "财会负责人:" + fzrArr[1]);
        response.put("reviewer", "审核人:" + fzrArr[2]);
        response.put("maker", "制表人:" + user.getUserName());
        response.put("hosName", user.getHospitalName());
        response.put("querydate", datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");
        response.put("headno",
                "编号:" + ybbh + "-" + datefrom.substring(0, 4) + datefrom.substring(5, 7) + datefrom.substring(9));// 待确认规则
        response.put("bankNo", yhzh);
        response.put("bankName", yhmc);
        response.put("reportDate", sdf.format(new Date()));
        return response;
    }

    private Map<String, Object> getParametersForView(List<Map<String, Object>> list) {
        Map<String, Object> response = new HashMap<>();
        response.put("totalRecord", list.size());
        int JZNUM = 0;
        int JZNUM_YZ = 0;
        int JZNUM_GL = 0;
        int JZNUM_ZC = 0;
        int JZNUM_ZX = 0;
        int JZNUM_QT = 0;
        double TCBX = 0;
        double TCBX_YZ = 0;
        double TCBX_GL = 0;
        double TCBX_ZC = 0;
        double TCBX_ZX = 0;
        double TCBX_QT = 0;
        double ZF = 0;
        double ZF_YZ = 0;
        double ZF_GL = 0;
        double ZF_ZC = 0;
        double ZF_ZX = 0;
        double ZF_QT = 0;
        int JZNUM_ZJ = 0;
        double TOTFY_ZJ = 0;
        double FLGRZF_ZJ = 0;
        double GRXJ_ZJ = 0;
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            TOTFY_ZJ += NumberUtils.toDouble(String.valueOf(map.get("TOTFY")));
            GRXJ_ZJ += NumberUtils.toDouble(String.valueOf(map.get("GRXJ")));
            FLGRZF_ZJ += NumberUtils.toDouble(String.valueOf(map.get("FLGRZF")));
            String GRXZ = String.valueOf(map.get("GRXZ"));
            JZNUM += NumberUtils.toDouble(String.valueOf(map.get("JZNUM")));
            TCBX += NumberUtils.toDouble(String.valueOf(map.get("TCBX")));
            ZF += NumberUtils.toDouble(String.valueOf(map.get("ZF")));
            if ("B".equals(GRXZ)) {
                JZNUM_YZ += NumberUtils.toDouble(String.valueOf(map.get("JZNUM")));
                TCBX_YZ += NumberUtils.toDouble(String.valueOf(map.get("TCBX")));
                ZF_YZ += NumberUtils.toDouble(String.valueOf(map.get("ZF")));
            } else if ("C".equals(GRXZ)) {
                JZNUM_GL += NumberUtils.toDouble(String.valueOf(map.get("JZNUM")));
                TCBX_GL += NumberUtils.toDouble(String.valueOf(map.get("TCBX")));
                ZF_GL += NumberUtils.toDouble(String.valueOf(map.get("ZF")));
            } else if ("D".equals(GRXZ)) {
                JZNUM_ZC += NumberUtils.toDouble(String.valueOf(map.get("JZNUM")));
                TCBX_ZC += NumberUtils.toDouble(String.valueOf(map.get("TCBX")));
                ZF_ZC += NumberUtils.toDouble(String.valueOf(map.get("ZF")));
            } else if ("E".equals(GRXZ)) {
                JZNUM_ZX += NumberUtils.toDouble(String.valueOf(map.get("JZNUM")));
                TCBX_ZX += NumberUtils.toDouble(String.valueOf(map.get("TCBX")));
                ZF_ZX += NumberUtils.toDouble(String.valueOf(map.get("ZF")));
            } else if ("F".equals(GRXZ)) {
                JZNUM_QT += NumberUtils.toDouble(String.valueOf(map.get("JZNUM")));
                TCBX_QT += NumberUtils.toDouble(String.valueOf(map.get("TCBX")));
                ZF_QT += NumberUtils.toDouble(String.valueOf(map.get("ZF")));
            }
            response.put("TOTFY_ZJ", String.format("%.2f", TOTFY_ZJ));
            response.put("FLGRZF_ZJ", String.format("%.2f", FLGRZF_ZJ));
            response.put("GRXJ_ZJ", String.format("%.2f", GRXJ_ZJ));
            response.put("JZNUM", JZNUM);
            response.put("JZNUM_YZ", JZNUM_YZ);
            response.put("JZNUM_GL", JZNUM_GL);
            response.put("JZNUM_ZC", JZNUM_ZC);
            response.put("JZNUM_ZX", JZNUM_ZX);
            response.put("JZNUM_QT", JZNUM_QT);
            response.put("TCBX", String.format("%.2f", TCBX));
            response.put("TCBX_YZ", String.format("%.2f", TCBX_YZ));
            response.put("TCBX_GL", String.format("%.2f", TCBX_GL));
            response.put("TCBX_ZC", String.format("%.2f", TCBX_ZC));
            response.put("TCBX_ZX", String.format("%.2f", TCBX_ZX));
            response.put("TCBX_QT", String.format("%.2f", TCBX_QT));
            response.put("ZF", String.format("%.2f", ZF));
            response.put("ZF_YZ", String.format("%.2f", ZF_YZ));
            response.put("ZF_GL", String.format("%.2f", ZF_GL));
            response.put("ZF_ZC", String.format("%.2f", ZF_ZC));
            response.put("ZF_ZX", String.format("%.2f", ZF_ZX));
            response.put("ZF_QT", String.format("%.2f", ZF_QT));
            response.put("JUNUM_ZJ", map.get("JZNUM"));
        }
        return response;
    }

    public CzptSbbHzBResp getJmFieldsB(List<Map<String, Object>> list,String datefrom,SysUser user) {

//        if (isPrint==null) {
        CzptSbbHzBResp res = getJmFieldsForViewB(list,datefrom,user);
//        }else{
//            getFieldsForPrint(req, records, list);
//        }
        return res;
    }

    private CzptSbbHzBResp getJmFieldsForViewB(List<Map<String, Object>> list,String datefrom,SysUser user) {
        List<Map<String, Object>> records = new ArrayList<>();
        CzptSbbHzBResp czjmbres = new CzptSbbHzBResp();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            map.put("XH", i + 1);
            map.put("ZDICD", map.get("ZDNAME"));    // 诊断
            map.put("YBID", map.get("YBID"));    // 医保号码
            map.put("FY00", map.get("FY00"));    //
            map.put("FY01", map.get("FY01"));    //
            map.put("FY02", map.get("FY02"));    //
            map.put("FY03", map.get("FY03"));    //
            map.put("FY04", map.get("FY04"));    //
            map.put("FY05", map.get("FY05"));    //
            map.put("FY06", map.get("FY06"));    //
            map.put("FY07", map.get("FY07"));    //
            map.put("FY08", map.get("FY08"));    //
            map.put("FY09", map.get("FY09"));    //
            map.put("FY10", map.get("FY10"));    //
            map.put("TOTFY", map.get("TOTFY"));    // 费用总计
            records.add(map);
        }
        List<CzptBResp> czjmbResp = HisUtil.ListToResultSet(records, new CzptBResp());
        czjmbres.setCzptsbbresps(czjmbResp);
        Map<String,Object> map = getJmParametersB(datefrom,user,list);
        czjmbres.setHosName(map.get("hosName")+"");
        czjmbres.setFinancialOfficer(map.get("financialOfficer")+"");
        czjmbres.setQuerydate(map.get("querydate")+"");
        czjmbres.setMaker(map.get("maker")+"");
        czjmbres.setReviewer(map.get("reviewer")+"");
        czjmbres.setReportDate(map.get("reportDate")+"");
        czjmbres.setHeadno(map.get("headno")+"");
        czjmbres.setPresident(map.get("president")+"");

        czjmbres.setFY00(map.get("FY00_ZJ")+"");
        czjmbres.setFY01(map.get("FY01_ZJ")+"");
        czjmbres.setFY02(map.get("FY02_ZJ")+"");
        czjmbres.setFY03(map.get("FY03_ZJ")+"");
        czjmbres.setFY04(map.get("FY04_ZJ")+"");
        czjmbres.setFY05(map.get("FY05_ZJ")+"");
        czjmbres.setFY06(map.get("FY06_ZJ")+"");
        czjmbres.setFY07(map.get("FY07_ZJ")+"");
        czjmbres.setFY08(map.get("FY08_ZJ")+"");
        czjmbres.setFY09(map.get("FY09_ZJ")+"");
        czjmbres.setFY10(map.get("FY10_ZJ")+"");
        czjmbres.setTOTFY(map.get("TOTFY_ZJ")+"");
        return czjmbres;
    }

    public Map<String, Object> getJmParametersB(String datefrom, SysUser user, List<Map<String, Object>> list) {
        Map<String, Object> response = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Integer jgid = user.getHospitalId();
        String fzr = sysXtcsCacheSer.getCsz(jgid, "YBBBFZR");
        if (fzr == null || fzr.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        String yhzh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHZH");
        if (yhzh == null || yhzh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0045");
        }
        String yhmc = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHMC");
        if (yhmc == null || yhmc.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0046");
        }
        String lxdh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBLXDH");
        if (lxdh == null || lxdh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0047");
        }
        String ybbh = sysXtcsCacheSer.getCsz(jgid, "YBBBBH");
        if (ybbh == null || ybbh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0044");
        }
        //       if (isPrint==null) {
        response = getJmParametersForViewB(list);
//        }else{
//            getParametersForPrint(request, response,list);
//        }
        String[] fzrArr = fzr.split(",");
        response.put("president", "医疗机构负责人:" + fzrArr[0]);
        response.put("financialOfficer", "财会负责人:" + fzrArr[1]);
        response.put("reviewer", "审核人:" + fzrArr[2]);
        response.put("maker", "制表人:" + user.getUserName());
        response.put("hosName", user.getHospitalName());
        response.put("querydate", datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");
        response.put("headno",
                "编号:" + ybbh + "-" + datefrom.substring(0, 4) + datefrom.substring(5, 7) + datefrom.substring(9));// 待确认规则
        response.put("bankNo", yhzh);
        response.put("bankName", yhmc);
        response.put("reportDate", sdf.format(new Date()));
        return response;
    }

    private Map<String, Object> getJmParametersForViewB(List<Map<String, Object>> list) {
        Map<String, Object> response = new HashMap<>();
        double FY00_ZJ = 0;
        double FY01_ZJ = 0;
        double FY02_ZJ = 0;
        double FY03_ZJ = 0;
        double FY04_ZJ = 0;
        double FY05_ZJ = 0;
        double FY06_ZJ = 0;
        double FY07_ZJ = 0;
        double FY08_ZJ = 0;
        double FY09_ZJ = 0;
        double FY10_ZJ = 0;
        double TOTFY_ZJ = 0;
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            FY00_ZJ += NumberUtils.toDouble(String.valueOf(map.get("FY00")));
            FY01_ZJ += NumberUtils.toDouble(String.valueOf(map.get("FY01")));
            FY02_ZJ += NumberUtils.toDouble(String.valueOf(map.get("FY02")));
            FY03_ZJ += NumberUtils.toDouble(String.valueOf(map.get("FY03")));
            FY04_ZJ += NumberUtils.toDouble(String.valueOf(map.get("FY04")));
            FY05_ZJ += NumberUtils.toDouble(String.valueOf(map.get("FY05")));
            FY06_ZJ += NumberUtils.toDouble(String.valueOf(map.get("FY06")));
            FY07_ZJ += NumberUtils.toDouble(String.valueOf(map.get("FY07")));
            FY08_ZJ += NumberUtils.toDouble(String.valueOf(map.get("FY08")));
            FY09_ZJ += NumberUtils.toDouble(String.valueOf(map.get("FY09")));
            FY10_ZJ += NumberUtils.toDouble(String.valueOf(map.get("FY10")));
            TOTFY_ZJ += NumberUtils.toDouble(String.valueOf(map.get("TOTFY")));
        }
        response.put("FY00_ZJ", String.format("%.2f", FY00_ZJ));
        response.put("FY01_ZJ", String.format("%.2f", FY01_ZJ));
        response.put("FY02_ZJ", String.format("%.2f", FY02_ZJ));
        response.put("FY03_ZJ", String.format("%.2f", FY03_ZJ));
        response.put("FY04_ZJ", String.format("%.2f", FY04_ZJ));
        response.put("FY05_ZJ", String.format("%.2f", FY05_ZJ));
        response.put("FY06_ZJ", String.format("%.2f", FY06_ZJ));
        response.put("FY07_ZJ", String.format("%.2f", FY07_ZJ));
        response.put("FY08_ZJ", String.format("%.2f", FY08_ZJ));
        response.put("FY09_ZJ", String.format("%.2f", FY09_ZJ));
        response.put("FY10_ZJ", String.format("%.2f", FY10_ZJ));
        response.put("TOTFY_ZJ", String.format("%.2f", TOTFY_ZJ));
        return response;
    }

    public Map<String, Object> getJmParametersC(String datefrom, SysUser user, List<Map<String, Object>> list) {
        Map<String, Object> response = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Integer jgid = user.getHospitalId();
        String fzr = sysXtcsCacheSer.getCsz(jgid, "YBBBFZR");
        if (fzr == null || fzr.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        String yhzh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHZH");
        if (yhzh == null || yhzh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0045");
        }
        String yhmc = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHMC");
        if (yhmc == null || yhmc.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0046");
        }
        String lxdh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBLXDH");
        if (lxdh == null || lxdh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0047");
        }
        String ybbh = sysXtcsCacheSer.getCsz(jgid, "YBBBBH");
        if (ybbh == null || ybbh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0044");
        }
        String[] fzrArr = fzr.split(",");
        response.put("hosName", user.getHospitalName());
        response.put("querydate", datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");
        response.put("headno",
                "编号:" + ybbh + "-" + datefrom.substring(0, 4) + datefrom.substring(5, 7) + datefrom.substring(9));// 待确认规则
        response.put("YHZH", yhzh);
        response.put("KHYH", yhmc);
        response.put("reportDate", sdf.format(new Date()));
        //门诊——人次
        int MRC = 0;
        int MRC_YZ = 0;
        int MRC_GL = 0;
        int MRC_ZC = 0;
        int MRC_ZX = 0;
        int MRC_QT = 0;
        //门诊——基金支付
        double MJJ = 0;
        double MJJ_YZ = 0;
        double MJJ_GL = 0;
        double MJJ_ZC = 0;
        double MJJ_ZX = 0;
        double MJJ_QT = 0;
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            String grxz = String.valueOf(map.get("GRXZ"));
            MRC += NumberUtils.toDouble(String.valueOf((map.get("JZNUM"))));
            MJJ += NumberUtils.toDouble(String.valueOf((map.get("YBZF"))));
            if ("B".equals(grxz)) {
                MRC_YZ += NumberUtils.toDouble(String.valueOf((map.get("JZNUM"))));
                MJJ_YZ += NumberUtils.toDouble(String.valueOf((map.get("YBZF"))));
            } else if ("C".equals(grxz)) {
                MRC_GL += NumberUtils.toDouble(String.valueOf((map.get("JZNUM"))));
                MJJ_GL += NumberUtils.toDouble(String.valueOf((map.get("YBZF"))));

            } else if ("D".equals(grxz)) {
                MRC_ZC += NumberUtils.toDouble(String.valueOf((map.get("JZNUM"))));
                MJJ_ZC += NumberUtils.toDouble(String.valueOf((map.get("YBZF"))));

            } else if ("E".equals(grxz)) {
                MRC_ZX += NumberUtils.toDouble(String.valueOf((map.get("JZNUM"))));
                MJJ_ZX += NumberUtils.toDouble(String.valueOf((map.get("YBZF"))));

            } else if ("F".equals(grxz)) {
                MRC_QT += NumberUtils.toDouble(String.valueOf((map.get("JZNUM"))));
                MJJ_QT += NumberUtils.toDouble(String.valueOf((map.get("YBZF"))));

            }
        }
        //住院——人次
        int ZRC = 0;
        int ZRC_YZ = 0;
        int ZRC_GL = 0;
        int ZRC_ZC = 0;
        int ZRC_ZX = 0;
        int ZRC_QT = 0;
        //住院——基金支付
        double ZJJ = 0;
        double ZJJ_YZ = 0;
        double ZJJ_GL = 0;
        double ZJJ_ZC = 0;
        double ZJJ_ZX = 0;
        double ZJJ_QT = 0;
//        String filename1 = (String) request.get("filename1");
//        if(filename1!=null){
//            List<Map<String, Object>> list1 = ExportDbf.readerDBF(request.get("filename1").toString());
//            for (int i = 0; i < list1.size(); i++) {
//                Map<String, Object> map = list1.get(i);
//                String grxz = String.valueOf(map.get("GRXZ"));
//                ZJJ +=NumberUtils.toDouble(String.valueOf((map.get("YBZF"))));
//                if (map.get("CYDATE")!=null&&!map.get("CYDATE").equals("")) {
//                    ZRC++;
//                    //MJJ += NumberUtils.toDouble(String.valueOf((map.get("YBZF"))));
//                    if("B".equals(grxz)){
//                        ZRC_YZ++;
//                        //ZJJ_YZ +=NumberUtils.toDouble(String.valueOf((map.get("YBZF"))));
//                    }else if("C".equals(grxz)){
//                        ZRC_GL ++;
//                        //ZJJ_GL+=NumberUtils.toDouble(String.valueOf((map.get("YBZF"))));
//                    }else if("D".equals(grxz)){
//                        ZRC_ZC ++;
//                        //ZJJ_ZC +=NumberUtils.toDouble(String.valueOf((map.get("YBZF"))));
//                    }else if("E".equals(grxz)){
//                        ZRC_ZX ++;
//                        //ZJJ_ZX +=NumberUtils.toDouble(String.valueOf((map.get("YBZF"))));
//                    }else if("F".equals(grxz)){
//                        ZRC_QT ++;
//                        //ZJJ_QT +=NumberUtils.toDouble(String.valueOf((map.get("YBZF"))));
//                    }
//                }
//                if("B".equals(grxz)){
//
//                    ZJJ_YZ +=NumberUtils.toDouble(String.valueOf((map.get("YBZF"))));
//                }else if("C".equals(grxz)){
//
//                    ZJJ_GL+=NumberUtils.toDouble(String.valueOf((map.get("YBZF"))));
//                }else if("D".equals(grxz)){
//
//                    ZJJ_ZC +=NumberUtils.toDouble(String.valueOf((map.get("YBZF"))));
//                }else if("E".equals(grxz)){
//
//                    ZJJ_ZX +=NumberUtils.toDouble(String.valueOf((map.get("YBZF"))));
//                }else if("F".equals(grxz)){
//
//                    ZJJ_QT +=NumberUtils.toDouble(String.valueOf((map.get("YBZF"))));
//                }
//            }
//        }

        //医保支付
        response.put("YBZF", String.format("%.2f", ZJJ + MJJ)); //医保支付总集-小计
        response.put("YBZF_YZ", String.format("%.2f", ZJJ_YZ + MJJ_YZ)); //医保支付总集-医嘱
        response.put("YBZF_GL", String.format("%.2f", ZJJ_GL + MJJ_GL)); //医保支付总集-高龄
        response.put("YBZF_ZC", String.format("%.2f", ZJJ_ZC + MJJ_ZC)); //医保支付总集-重残
        response.put("YBZF_ZX", String.format("%.2f", ZJJ_ZX + MJJ_ZX)); //医保支付总集-中小
        response.put("YBZF_QT", String.format("%.2f", ZJJ_QT + MJJ_QT)); //医保支付总集-其他
        response.put("ZRC", ZRC); //住院费用-总人次-小计
        response.put("ZRC_YZ", ZRC_YZ); //住院费用-总人次-医嘱
        response.put("ZRC_GL", ZRC_GL); //住院费用-总人次-高龄
        response.put("ZRC_ZC", ZRC_ZC); //住院费用-总人次-重残
        response.put("ZRC_ZX", ZRC_ZX); //住院费用-总人次-中小
        response.put("ZRC_QT", ZRC_QT); //住院费用-总人次-其他
        response.put("ZJJ", String.format("%.2f", ZJJ)); //住院费用-基金支付-小计
        response.put("ZJJ_YZ", String.format("%.2f", ZJJ_YZ)); //住院费用-基金支付-医嘱
        response.put("ZJJ_GL", String.format("%.2f", ZJJ_GL)); //住院费用-基金支付-高龄
        response.put("ZJJ_ZC", String.format("%.2f", ZJJ_ZC)); //住院费用-基金支付-重残
        response.put("ZJJ_ZX", String.format("%.2f", ZJJ_ZX)); //住院费用-基金支付-中小
        response.put("ZJJ_QT", String.format("%.2f", ZJJ_QT)); //住院费用-基金支付-其他
        response.put("MRC", MRC);  //门急诊费用-小计
        response.put("MRC_YZ", MRC_YZ); //门急诊费用-总人次-医嘱
        response.put("MRC_GL", MRC_GL); //门急诊费用-高龄
        response.put("MRC_ZC", MRC_ZC); //门急诊费用-重残
        response.put("MRC_ZX", MRC_ZX); //门急诊费用-中小
        response.put("MRC_QT", MRC_QT); //门急诊费用-其他
        response.put("MJJ", String.format("%.2f", MJJ)); //门急诊费用-基金支付-小计
        response.put("MJJ_YZ", String.format("%.2f", MJJ_YZ)); //门急诊费用-基金支付-医嘱
        response.put("MJJ_GL", String.format("%.2f", MJJ_GL)); //门急诊费用-基金支付-高龄
        response.put("MJJ_ZC", String.format("%.2f", MJJ_ZC)); //门急诊费用-基金支付-重残
        response.put("MJJ_ZX", String.format("%.2f", MJJ_ZX)); //门急诊费用-基金支付-中小
        response.put("MJJ_QT", String.format("%.2f", MJJ_QT)); //门急诊费用-基金支付-其他
        response.put("SBJE", BSPHISUtil.changeMoneyUpper(ZJJ + MJJ));
        return response;
    }


    public List<Map<String, Object>> queryYbTszyCityNormalListire(String daa, String datefrom, String dateto, String mzlb) {
        logger.info("城镇特殊住院上报");

        return shybbbDao.queryYbTszyCityNormalListire(daa, datefrom, dateto, mzlb);
    }

    //城镇居民住院A
    public List<Map<String, Object>> QueryYbJmzyCityNormalListire(String daa, String datefrom, String dateto, String mzlb) {
        logger.info("城镇居民住院上报");

        return shybbbDao.queryYbJmzyCityNormalListire(daa, datefrom, dateto, mzlb);
    }

    public CzjmSbbHzAZyResp getJmzyFields(String datefrom,SysUser user,List<Map<String, Object>> list) {
        List<Map<String, Object>> records = new ArrayList<>();
        CzjmSbbHzAZyResp czjmhza = new CzjmSbbHzAZyResp();
        for(  int i = 0; i<list.size();i++) {
        Map<String, Object> map = list.get(i);
        map.put("XH", i + 1);
        map.put("ZYID", map.get("ZYID"));    // 住院号
        map.put("NAME", map.get("NAME"));    // 病人姓名
            map.put("YBID", map.get("YBID"));    // 凭证编号
        map.put("CYDATE", map.get("CYDATE"));    // 出院日期
        map.put("ZYDNUM", map.get("ZYDNUM"));    //住院天数
        map.put("ZYJGBZ", map.get("ZYJGBZ"));    //项目
        map.put("KSNA", map.get("KSNA"));    //科别
        map.put("TOTFY", map.get("TOTFY"));    //费用总计
        map.put("YBZF", map.get("YBZF"));    //医保支付
        map.put("FLGRZF", map.get("FLGRZF"));    //分类自负
        map.put("GRZF", NumberUtils.toDouble(String.valueOf(map.get("GRXJD"))) + NumberUtils.toDouble(String.valueOf(map.get("GRXJQ"))));    //个人现金支付
            map.put("ZDNAME", map.get("ZDNAME"));    // 诊断
        records.add(map);
    }
        List<CzjmAZyResp> czjmzya = HisUtil.ListToResultSet(records,new CzjmAZyResp());
        czjmhza.setCzjmzysbbresps(czjmzya);
        Map<String,Object> map = getJmzyParameters(datefrom,user,list);
        czjmhza.setZYD_ZJ(map.get("ZYD_ZJ")+"");
        czjmhza.setTOTFY_ZJ(map.get("TOTFY_ZJ")+"");
        czjmhza.setFLGRZF_ZJ(map.get("FLGRZF_ZJ")+"");
        czjmhza.setGRZF_ZJ(map.get("GRZF_ZJ")+"");
        czjmhza.setYBZF(map.get("YBZF")+"");

        Integer jgid = user.getHospitalId();
        String fzr = sysXtcsCacheSer.getCsz(jgid, "YBBBFZR");
        if (fzr == null || fzr.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        String ybbh = sysXtcsCacheSer.getCsz(jgid, "YBBBBH");
        if (ybbh == null || ybbh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0045");
        }
        String[] fzrArr = fzr.split(",");
        if (fzrArr.length < 3) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        czjmhza.setPresident(fzrArr[0]);
        czjmhza.setFinancialOfficer( fzrArr[0]);
        czjmhza.setReviewer( fzrArr[2]);
        czjmhza.setMaker(user.getUserName());
        czjmhza.setHosName(map.get("hosName")+"");
        czjmhza.setQuerydate(map.get("querydate")+"");
        czjmhza.setHeadno(map.get("headno")+"");
        czjmhza.setReportDate(map.get("reportDate")+"");
    return czjmhza;
}

    public Map<String,Object> getJmzyParameters(String datefrom,SysUser user,List<Map<String, Object>> list)  {
        Map<String,Object> response = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");

        response.put("hosName", user.getHospitalName());
        response.put("querydate", datefrom.substring(0, 4) + "年" + datefrom.substring(5,7) + "月");
        response.put("headno", "编号:74727214300-150");//待确认规则
        response.put("bankNo", "03416900040019262");
        response.put("bankName", "农行闵行区水清南路支行");
        response.put("reportDate", sdf.format(new Date()));
        int CYD = 0;
        int CYD_YZ = 0 ;
        int CYD_GL = 0 ;
        int CYD_ZC = 0 ;
        int CYD_ZX = 0 ;
        int CYD_QT = 0 ;
        double YBZF = 0;
        double YBZF_YZ = 0;
        double YBZF_GL = 0;
        double YBZF_ZC = 0;
        double YBZF_ZX = 0;
        double YBZF_QT = 0;
        double ZF = 0;
        double ZF_YZ = 0;
        double ZF_GL = 0;
        double ZF_ZC = 0;
        double ZF_ZX = 0;
        double ZF_QT = 0;
        double ZYD_ZJ = 0;
        double TOTFY_ZJ = 0;
        double FLGRZF_ZJ = 0;
        double GRZF_ZJ = 0;
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            ZYD_ZJ += NumberUtils.toDouble(String.valueOf(map.get("ZYDNUM")));
            TOTFY_ZJ += NumberUtils.toDouble(String.valueOf(map.get("TOTFY")));
            FLGRZF_ZJ += NumberUtils.toDouble(String.valueOf(map.get("FLGRZF")));
            GRZF_ZJ	+=  NumberUtils.toDouble(String.valueOf(map.get("GRXJD")))+ NumberUtils.toDouble(String.valueOf(map.get("GRXJQ")));
            String CYDATE = String.valueOf(map.get("CYDATE"));
            String GRXZ = String.valueOf(map.get("GRXZ"));
            YBZF += NumberUtils.toDouble(String.valueOf(map.get("YBZF")));
            ZF += NumberUtils.toDouble(String.valueOf(map.get("ZF")));
            if(!"".equals(CYDATE)||CYDATE!=null){
                CYD ++ ;
            }
            if("B".equals(GRXZ)){
                CYD_YZ ++ ;
                YBZF_YZ += NumberUtils.toDouble(String.valueOf(map.get("YBZF")));
                ZF_YZ += NumberUtils.toDouble(String.valueOf(map.get("ZF")));
            }else if("C".equals(GRXZ)){
                CYD_GL ++ ;
                YBZF_GL += NumberUtils.toDouble(String.valueOf(map.get("YBZF")));
                ZF_GL += NumberUtils.toDouble(String.valueOf(map.get("ZF")));
            }else if("D".equals(GRXZ)){
                CYD_ZC ++ ;
                YBZF_ZC += NumberUtils.toDouble(String.valueOf(map.get("YBZF")));
                ZF_ZC += NumberUtils.toDouble(String.valueOf(map.get("ZF")));
            }else if("E".equals(GRXZ)){
                CYD_ZX ++ ;
                YBZF_ZX += NumberUtils.toDouble(String.valueOf(map.get("YBZF")));
                ZF_ZX += NumberUtils.toDouble(String.valueOf(map.get("ZF")));
            }else if("F".equals(GRXZ)){
                CYD_QT ++ ;
                YBZF_QT += NumberUtils.toDouble(String.valueOf(map.get("YBZF")));
                ZF_QT += NumberUtils.toDouble(String.valueOf(map.get("ZF")));
            }
        }
        response.put("totalRecord", list.size());
        response.put("ZYD_ZJ", String.format("%.2f",ZYD_ZJ));  //住院天数总计
        response.put("TOTFY_ZJ", String.format("%.2f",TOTFY_ZJ)); //费用总计
        response.put("FLGRZF_ZJ", String.format("%.2f",FLGRZF_ZJ)); //分类个人总计
        response.put("GRZF_ZJ", String.format("%.2f",GRZF_ZJ));//个人支付总计
        response.put("CYD", CYD);
        response.put("CYD_YZ", CYD_YZ);
        response.put("CYD_GL", CYD_GL);
        response.put("CYD_ZC", CYD_ZC);
        response.put("CYD_ZX", CYD_ZX);
        response.put("CYD_QT", CYD_QT);
        response.put("YBZF", String.format("%.2f",YBZF));  //医保支付总计
        response.put("YBZF_YZ", String.format("%.2f",YBZF_YZ));
        response.put("YBZF_GL", String.format("%.2f",YBZF_GL));
        response.put("YBZF_ZC", String.format("%.2f",YBZF_ZC));
        response.put("YBZF_ZX", String.format("%.2f",YBZF_ZX));
        response.put("YBZF_QT", String.format("%.2f",YBZF_QT));
        response.put("ZF", String.format("%.2f",ZF));
        response.put("ZF_YZ", String.format("%.2f",ZF_YZ));
        response.put("ZF_GL", String.format("%.2f",ZF_GL));
        response.put("ZF_ZC", String.format("%.2f",ZF_ZC));
        response.put("ZF_ZX", String.format("%.2f",ZF_ZX));
        response.put("ZF_QT", String.format("%.2f",ZF_QT));
        return response;
    }

    public CzzySbbHzBResp getJmzyFieldsB(String datefrom,SysUser user,List<Map<String, Object>> list) {
        List<Map<String,Object>> records = new ArrayList<>();
        CzzySbbHzBResp czzyhzb = new CzzySbbHzBResp();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            map.put("XH", i + 1);
            map.put("YBID", map.get("YBID"));
            map.put("FY01", map.get("FY01"));
            map.put("FY02", map.get("FY02"));
            map.put("FY03", map.get("FY03"));
            map.put("FY04", map.get("FY04"));
            map.put("FY05", map.get("FY05"));
            map.put("FY06", map.get("FY06"));
            map.put("FY07", map.get("FY07"));
            map.put("FY08", map.get("FY08"));
            map.put("FY09", map.get("FY09"));
            map.put("FY010", map.get("FY010"));
            map.put("FY011", map.get("FY011"));
            map.put("FY012", map.get("FY012"));
            map.put("FY013", map.get("FY013"));
            map.put("FY014", map.get("FY014"));
            map.put("FY015", map.get("FY015"));
            map.put("TOTFY", map.get("TOTFY"));
            records.add(map);
        }
        List<CzzyBResp> czjmzyb = HisUtil.ListToResultSet(records, new CzzyBResp());
        czzyhzb.setCzzysbbresps(czjmzyb);
        Map<String,Object> map = getJmzyParametersB(datefrom,user,list);
        czzyhzb.setFY01(map.get("FY01")+"");
        czzyhzb.setFY02(map.get("FY02")+"");
        czzyhzb.setFY03(map.get("FY03")+"");
        czzyhzb.setFY04(map.get("FY04")+"");
        czzyhzb.setFY05(map.get("FY05")+"");
        czzyhzb.setFY06(map.get("FY06")+"");
        czzyhzb.setFY07(map.get("FY07")+"");
        czzyhzb.setFY08(map.get("FY08")+"");
        czzyhzb.setFY09(map.get("FY09")+"");
        czzyhzb.setFY10(map.get("FY10")+"");
        czzyhzb.setFY11(map.get("FY11")+"");
        czzyhzb.setFY12(map.get("FY12")+"");
        czzyhzb.setFY13(map.get("FY13")+"");
        czzyhzb.setFY14(map.get("FY14")+"");
        czzyhzb.setFY15(map.get("FY15")+"");
        czzyhzb.setJYTOTFY(map.get("TOTFY")+"");

        Integer jgid = user.getHospitalId();
        String fzr = sysXtcsCacheSer.getCsz(jgid, "YBBBFZR");
        if (fzr == null || fzr.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        String yhzh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHZH");
        if (yhzh == null || yhzh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0045");
        }
        String yhmc = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHMC");
        if (yhmc == null || yhmc.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0046");
        }
        String lxdh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBLXDH");
        if (lxdh == null || lxdh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0047");
        }
        String ybbh = sysXtcsCacheSer.getCsz(jgid, "YBBBBH");
        if (ybbh == null || ybbh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0044");
        }
        String[] fzrArr = fzr.split(",");
        czzyhzb.setPresident(fzrArr[0]);
        czzyhzb.setFinancialOfficer( fzrArr[1]);
        czzyhzb.setReviewer( fzrArr[2]);
        czzyhzb.setMaker( user.getUserName());
        czzyhzb.setHosName(user.getHospitalName());
        czzyhzb.setQuerydate(datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");
        czzyhzb.setHeadno( "编号:" + ybbh + "-" + datefrom.substring(0, 4) + datefrom.substring(5, 7) + datefrom.substring(9));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        czzyhzb.setReportDate( sdf.format(new Date()));
        return czzyhzb;
    }

    public Map<String,Object> getJmzyParametersB(String datefrom,SysUser user,List<Map<String, Object>> list)  {
        Map<String,Object> response = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        response.put("hosName", user.getUserName());
        response.put("querydate", datefrom.substring(0, 4) + "年" + datefrom.substring(5,7) + "月");
        response.put("headno", "编号:74727214300-150");//待确认规则
        response.put("bankNo", "03416900040019262");
        response.put("bankName", "农行闵行区水清南路支行");
        response.put("reportDate", sdf.format(new Date()));
        int RC =0;
        int ZZRC =0;
        int TXRC =0;
        double FJZF =0;
        double ZZFJZF =0;
        double TXFJZF =0;
        double FY01_ZJ =0;
        double FY02_ZJ =0;
        double FY03_ZJ =0;
        double FY04_ZJ =0;
        double FY05_ZJ =0;
        double FY06_ZJ =0;
        double FY07_ZJ =0;
        double FY08_ZJ =0;
        double FY09_ZJ =0;
        double FY10_ZJ =0;
        double FY11_ZJ =0;
        double FY12_ZJ =0;
        double FY13_ZJ =0;
        double FY14_ZJ =0;
        double FY15_ZJ =0;
        double TOTFY_ZJ =0;
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            FY01_ZJ += NumberUtils.toDouble(String.valueOf((map.get("FY01"))));
            FY02_ZJ += NumberUtils.toDouble(String.valueOf((map.get("FY02"))));
            FY03_ZJ += NumberUtils.toDouble(String.valueOf((map.get("FY03"))));
            FY04_ZJ += NumberUtils.toDouble(String.valueOf((map.get("FY04"))));
            FY05_ZJ += NumberUtils.toDouble(String.valueOf((map.get("FY05"))));
            FY06_ZJ += NumberUtils.toDouble(String.valueOf((map.get("FY06"))));
            FY07_ZJ += NumberUtils.toDouble(String.valueOf((map.get("FY07"))));
            FY08_ZJ += NumberUtils.toDouble(String.valueOf((map.get("FY08"))));
            FY09_ZJ += NumberUtils.toDouble(String.valueOf((map.get("FY09"))));
            FY10_ZJ += NumberUtils.toDouble(String.valueOf((map.get("FY10"))));
            FY11_ZJ += NumberUtils.toDouble(String.valueOf((map.get("FY11"))));
            FY12_ZJ += NumberUtils.toDouble(String.valueOf((map.get("FY12"))));
            FY13_ZJ += NumberUtils.toDouble(String.valueOf((map.get("FY13"))));
            FY14_ZJ += NumberUtils.toDouble(String.valueOf((map.get("FY14"))));
            FY15_ZJ += NumberUtils.toDouble(String.valueOf((map.get("FY15"))));
            TOTFY_ZJ += NumberUtils.toDouble(String.valueOf((map.get("TOTFY"))));
            int GRXZ = NumberUtils.toInt(String.valueOf(map.get("GRXZ")));
            double FJBX = NumberUtils.toDouble(String.valueOf((map.get("FJBX"))));
            FJZF +=NumberUtils.toDouble(String.valueOf((map.get("FJBX"))));
            if(FJBX!=0){
                RC +=NumberUtils.toDouble(String.valueOf((map.get("JZNUM"))));
                if(GRXZ==1){
                    ZZFJZF +=NumberUtils.toDouble(String.valueOf((map.get("FJBX"))));
                    ZZRC +=NumberUtils.toDouble(String.valueOf((map.get("JZNUM"))));
                }else{
                    TXFJZF +=NumberUtils.toDouble(String.valueOf((map.get("FJBX"))));
                    TXRC +=NumberUtils.toDouble(String.valueOf((map.get("JZNUM"))));
                }
            }
        }
        response.put("FY01", String.format("%.2f",FY01_ZJ));
        response.put("FY02", String.format("%.2f",FY02_ZJ));
        response.put("FY03", String.format("%.2f",FY03_ZJ));
        response.put("FY04", String.format("%.2f",FY04_ZJ));
        response.put("FY05", String.format("%.2f",FY05_ZJ));
        response.put("FY06", String.format("%.2f",FY06_ZJ));
        response.put("FY07", String.format("%.2f",FY07_ZJ));
        response.put("FY08", String.format("%.2f",FY08_ZJ));
        response.put("FY09", String.format("%.2f",FY09_ZJ));
        response.put("FY10", String.format("%.2f",FY10_ZJ));
        response.put("FY11", String.format("%.2f",FY11_ZJ));
        response.put("FY12", String.format("%.2f",FY12_ZJ));
        response.put("FY13", String.format("%.2f",FY13_ZJ));
        response.put("FY14", String.format("%.2f",FY14_ZJ));
        response.put("FY15", String.format("%.2f",FY15_ZJ));
        response.put("TOTFY", String.format("%.2f",TOTFY_ZJ));
        response.put("RC", RC);
        response.put("ZZRC", ZZRC);
        response.put("TXRC", TXRC);
        response.put("FJZF", FJZF);
        response.put("ZZFJZF", ZZFJZF);
        response.put("TXFJZF", TXFJZF);

        response.put("SBJE", BSPHISUtil.changeMoneyUpper(FJZF));
        response.put("SHJE", BSPHISUtil.changeMoneyUpper(FJZF));
        return response;
    }

    ///////////////工伤保险门诊/////////////////////
    //城镇居民住院A
    public List<Map<String, Object>> queryYbGsbxopListire(String daa, String datefrom, String dateto, String mzlb) {
        logger.info("工伤A上报");

        return shybbbDao.queryYbGsbxopListire(daa, datefrom, dateto, mzlb);
    }

    public GsbxSbbHzAResp getgsbxopFields(String datefrom,SysUser user,List<Map<String, Object>>list)  {
        List<Map<String, Object>> records = new ArrayList<>();
        GsbxSbbHzAResp gsbxhza = new GsbxSbbHzAResp();
        List<GsbxAResp> gsbxa = HisUtil.ListToResultSet(list,new GsbxAResp());
        gsbxhza.setGsbxsbbresps(gsbxa);
        int JZNUM_SUM = 0; //就诊次数
        double GSJJZFS_SUM = 0; //工伤保险基本支付
        for(Map<String, Object> m : list){
            JZNUM_SUM += NumberUtils.toDouble(String.valueOf(m.get("JZNUM")));
            GSJJZFS_SUM += NumberUtils.toDouble(String.valueOf(m.get("GSJJZFS")));
        }
/*        DecimalFormat df = new DecimalFormat("#0.00");
        String GSJJZFS_SUM_DE =  df.format(GSJJZFS_SUM);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).put("XH", i + 1);
            list.get(i).put("JZNUM_SUM", JZNUM_SUM);
            list.get(i).put("GSJJZFS_SUM", GSJJZFS_SUM_DE);
        }
        records.addAll(list);*/
        gsbxhza.setJZNUM_SUM(JZNUM_SUM+"");
        gsbxhza.setGSJJZFS_SUM(GSJJZFS_SUM+"");
       // Map<String,Object> map = getgsbxopParameters(datefrom,user,list);
        Integer jgid = user.getHospitalId();
        String fzr = sysXtcsCacheSer.getCsz(jgid, "YBBBFZR");
        if (fzr == null || fzr.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        String yhzh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHZH");
        if (yhzh == null || yhzh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0045");
        }
        String yhmc = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHMC");
        if (yhmc == null || yhmc.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0046");
        }
        String lxdh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBLXDH");
        if (lxdh == null || lxdh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0047");
        }
        String ybbh = sysXtcsCacheSer.getCsz(jgid, "YBBBBH");
        if (ybbh == null || ybbh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0044");
        }
        String[] fzrArr = fzr.split(",");
        gsbxhza.setPresident(fzrArr[0]);
        gsbxhza.setFinancialOfficer( fzrArr[1]);
        gsbxhza.setReviewer( fzrArr[2]);
        gsbxhza.setMaker( user.getUserName());
        gsbxhza.setHosName(user.getHospitalName());
        gsbxhza.setQuerydate(datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");
        gsbxhza.setHeadno( "编号:" + ybbh + "-" + datefrom.substring(0, 4) + datefrom.substring(5, 7) + datefrom.substring(9));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        gsbxhza.setReportDate( sdf.format(new Date()));
        return gsbxhza;
    }
    public Map<String,Object> getgsbxopParameters(String datefrom,SysUser user, List<Map<String, Object>> list)  {
        Map<String,Object> response = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        response.put("totalRecord",list.size());

        response.put("hosName", user.getHospitalName());
        response.put("querydate", datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");
        response.put("headno",
                "编号:747272143-" + datefrom.substring(0, 4) + datefrom.substring(5, 7) + datefrom.substring(9));// 待确认规则
        response.put("reportDate", sdf.format(new Date()));
        return response;
    }

    public GsbxSbbHzBResp getgsbxopFieldsB(String datefrom,SysUser user,List<Map<String, Object>>list)  {
        List<Map<String,Object>> records = new ArrayList<>();
        GsbxSbbHzBResp gsbxhzb = new GsbxSbbHzBResp();
        List<GsbxBResp> gsbxb = HisUtil.ListToResultSet(list,new GsbxBResp());
        gsbxhzb.setGsbxsbbbresps(gsbxb);
        double FY00_ALL = 0; //诊疗费
        double FY01_ALL = 0; //治疗费
        double FY02_ALL = 0; //手术材料费
        double FY03_ALL = 0; //检查费
        double FY04_ALL = 0; //化验费
        double FY05_ALL = 0; //摄片费
        double FY06_ALL = 0; //透视费
        double FY07_ALL = 0; //西药费
        double FY08_ALL = 0; //中成药费
        double FY09_ALL = 0; //中草药费
        double FY10_ALL = 0; //其他
        double TOTFY_ALL = 0; //费用总计
        for(Map<String, Object> m : list){
            FY00_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY00")));
            FY01_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY01")));
            FY02_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY02")));
            FY03_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY03")));
            FY04_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY04")));
            FY05_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY05")));
            FY06_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY06")));
            FY07_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY07")));
            FY08_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY08")));
            FY09_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY09")));
            FY10_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY10")));
            TOTFY_ALL += NumberUtils.toDouble(String.valueOf(m.get("TOTFY")));
        }
        DecimalFormat df = new DecimalFormat("#0.00");
        String FY00_ALL_DE = df.format(FY00_ALL);
        String FY01_ALL_DE = df.format(FY01_ALL);
        String FY02_ALL_DE = df.format(FY02_ALL);
        String FY03_ALL_DE = df.format(FY03_ALL);
        String FY04_ALL_DE = df.format(FY04_ALL);
        String FY05_ALL_DE = df.format(FY05_ALL);
        String FY06_ALL_DE = df.format(FY06_ALL);
        String FY07_ALL_DE = df.format(FY07_ALL);
        String FY08_ALL_DE = df.format(FY08_ALL);
        String FY09_ALL_DE = df.format(FY09_ALL);
        String FY10_ALL_DE = df.format(FY10_ALL);
        String TOTFY_ALL_DE = df.format(TOTFY_ALL);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).put("XH", i + 1);
            list.get(i).put("FY00", FY00_ALL_DE);
            list.get(i).put("FY01", FY01_ALL_DE);
            list.get(i).put("FY02", FY02_ALL_DE);
            list.get(i).put("FY03", FY03_ALL_DE);
            list.get(i).put("FY04", FY04_ALL_DE);
            list.get(i).put("FY05", FY05_ALL_DE);
            list.get(i).put("FY06", FY06_ALL_DE);
            list.get(i).put("FY07", FY07_ALL_DE);
            list.get(i).put("FY08", FY08_ALL_DE);
            list.get(i).put("FY09", FY09_ALL_DE);
            list.get(i).put("FY10", FY10_ALL_DE);
            list.get(i).put("TOTFY", TOTFY_ALL_DE);
        }
        records.addAll(list);
        gsbxhzb.setFY00(FY00_ALL_DE);
        gsbxhzb.setFY01(FY01_ALL_DE);
        gsbxhzb.setFY02(FY02_ALL_DE);
        gsbxhzb.setFY03(FY03_ALL_DE);
        gsbxhzb.setFY04(FY04_ALL_DE);
        gsbxhzb.setFY05(FY05_ALL_DE);
        gsbxhzb.setFY06(FY06_ALL_DE);
        gsbxhzb.setFY07(FY07_ALL_DE);
        gsbxhzb.setFY08(FY08_ALL_DE);
        gsbxhzb.setFY09(FY09_ALL_DE);
        gsbxhzb.setFY10(FY10_ALL_DE);
        gsbxhzb.setTOTFY(TOTFY_ALL_DE);
        Integer jgid = user.getHospitalId();
        String fzr = sysXtcsCacheSer.getCsz(jgid, "YBBBFZR");
        if (fzr == null || fzr.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        String yhzh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHZH");
        if (yhzh == null || yhzh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0045");
        }
        String yhmc = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHMC");
        if (yhmc == null || yhmc.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0046");
        }
        String lxdh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBLXDH");
        if (lxdh == null || lxdh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0047");
        }
        String ybbh = sysXtcsCacheSer.getCsz(jgid, "YBBBBH");
        if (ybbh == null || ybbh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0044");
        }
        String[] fzrArr = fzr.split(",");
        gsbxhzb.setPresident(fzrArr[0]);
        gsbxhzb.setFinancialOfficer( fzrArr[1]);
        gsbxhzb.setReviewer( fzrArr[2]);
        gsbxhzb.setMaker( user.getUserName());
        gsbxhzb.setHosName(user.getHospitalName());
        gsbxhzb.setQuerydate(datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");
        gsbxhzb.setHeadno( "编号:" + ybbh + "-" + datefrom.substring(0, 4) + datefrom.substring(5, 7) + datefrom.substring(9));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        gsbxhzb.setReportDate( sdf.format(new Date()));
        return gsbxhzb;
    }

    public Map<String,Object> getgsbxopParametersB(String datefrom,SysUser user,List<Map<String, Object>> list) {
        Map<String,Object> response = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        response.put("totalRecord",list.size());

        response.put("hosName", user.getHospitalName());
        response.put("querydate", datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");
        response.put("headno",
                "编号:747272143-" + datefrom.substring(0, 4) + datefrom.substring(5, 7) + datefrom.substring(9));// 待确认规则
        response.put("reportDate", sdf.format(new Date()));
        return response;
    }

    public Map<String,Object> getgsbxopParametersC(String datefrom,SysUser user,List<Map<String, Object>> list)  {
        Map<String,Object> response = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        response.put("hosName",user.getHospitalName());
        response.put("querydate", datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");
        response.put("headno",
                "编号:747272143-" + datefrom.substring(0, 4) + datefrom.substring(5, 7) + datefrom.substring(9));// 待确认规则
        response.put("bankNo", "03416900040019262");
        response.put("bankName", "农行闵行区水清南路支行");
        response.put("reportDate", sdf.format(new Date()));
        int JZNUM_SUM = 0; //就诊次数
        double GSJJZFS = 0; //互助帮困补贴支付
        for(Map<String, Object> m : list){
            JZNUM_SUM += NumberUtils.toDouble(String.valueOf(m.get("JZNUM")));
            GSJJZFS += NumberUtils.toDouble(String.valueOf(m.get("GSJJZFS")));
        }
        DecimalFormat df = new DecimalFormat("#0.00");
        String GSJJZFS_SUM =  df.format(GSJJZFS);
        response.put("JZNUM_SUM",JZNUM_SUM);
        response.put("GSJJZFS",GSJJZFS_SUM);
        response.put("GSJJZFS_DX",BSPHISUtil.changeMoneyUpper(NumberUtils.toDouble(GSJJZFS_SUM)));
        return response;
    }

    //////////医疗互助帮困/////////////
    public List<Map<String, Object>> QueryYbYlhzbListire(String daa, String datefrom, String dateto, String mzlb) {
        logger.info("互助帮困上报");
       // pageno = (pageno - 1) * 20;
        return shybbbDao.queryYbYlhzbkListire(daa, datefrom, dateto, mzlb);
    }

    public List<Map<String,Object>> getylhzbkFieldsA(List<Map<String, Object>> list)  {
        List<Map<String,Object>> res = new ArrayList<>();
//        if (isPrint) {
        //           getFieldsForPrint(records, isPrint, list);
//        }else{
        res =   getFieldsForView(list);
//        }
        return res;
    }

    private List<Map<String,Object>> getFieldsForView(List<Map<String, Object>> list) {
        List<Map<String,Object>> records = new ArrayList<>();
        int JZNUM_SUM = 0; //就诊次数
        double TOTFY_SUM = 0; //费用总计
        double FLGRZF_SUM = 0; //分类自负
        double GRXJ_SUM = 0; //自负
        double GRZFD_SUM = 0; //互助帮困补贴支付
        double TCBX_SUM = 0; //互助帮困补助支付
        double ZF_SUM = 0; //本期非医保结算范围（个人自费费用）合计
        int XH =0;
        for(Map<String, Object> m : list){
            m.put("XH", ++XH);
            JZNUM_SUM += NumberUtils.toDouble(String.valueOf(m.get("JZNUM")));
            TOTFY_SUM += NumberUtils.toDouble(String.valueOf(m.get("TOTFY")));
            FLGRZF_SUM += NumberUtils.toDouble(String.valueOf(m.get("FLGRZF")));
            GRXJ_SUM += NumberUtils.toDouble(String.valueOf(m.get("GRXJ")));
            GRZFD_SUM += NumberUtils.toDouble(String.valueOf(m.get("GRZFD")));
            TCBX_SUM += NumberUtils.toDouble(String.valueOf(m.get("TCBX")));

            ZF_SUM += NumberUtils.toDouble(String.valueOf(m.get("ZF")));
        }
        DecimalFormat df = new DecimalFormat("#0.00");
        String TOTFY_SUM_1 =  df.format(TOTFY_SUM);
        String FLGRZF_SUM_1 =  df.format(FLGRZF_SUM);
        String GRXJ_SUM_1 =  df.format(GRXJ_SUM);
        String GRZFD_SUM_1 =  df.format(GRZFD_SUM);
        String TCBX_SUM_1 =  df.format(TCBX_SUM);
        String ZF_SUM_1 =  df.format(ZF_SUM);
        int pageSize =20;
        int totalRecord  = list.size();
        int totalPage = (totalRecord+pageSize-1) /pageSize;
        int start = pageSize+1;
        int end =  (totalPage-1)*pageSize;
        int i=0;
        for(Map<String, Object> m : list){
            ++i;
            m.put("JZNUM_SUM", JZNUM_SUM);
            m.put("TOTFY_SUM", TOTFY_SUM_1);
            m.put("FLGRZF_SUM", FLGRZF_SUM_1);
            m.put("GRXJ_SUM", GRXJ_SUM_1);
            m.put("GRZFD_SUM", GRZFD_SUM_1);
            m.put("TCBX_SUM", TCBX_SUM_1);

            if(i==list.size()){
                m.put("ZF_SUM", ZF_SUM_1);
            }
            records.add(m);
        }
        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
        for (int j = 0; j < list.size(); j++) {
            if (0>records.size()||list.size()>records.size()) {
                break;
            }
            result.add(records.get(j));
        }
        records.clear();
        records.addAll(result);
        return records;
    }

    public Map<String,Object> getylhzbkParametersA(String datefrom,SysUser user,List<Map<String, Object>> list)  {
        Map<String,Object> response = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Integer jgid = user.getHospitalId();
        String fzr = sysXtcsCacheSer.getCsz(jgid, "YBBBFZR");
        if (fzr == null || fzr.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        String yhzh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHZH");
        if (yhzh == null || yhzh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0045");
        }
        String yhmc = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHMC");
        if (yhmc == null || yhmc.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0046");
        }
        String lxdh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBLXDH");
        if (lxdh == null || lxdh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0047");
        }
        String ybbh = sysXtcsCacheSer.getCsz(jgid, "YBBBBH");
        if (ybbh == null || ybbh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0044");
        }
  //      if (isPrint==null) {
        response =   getYlhzbkParametersForViewA(list);
//        }else{
//            getParametersForPrint(response, list);
//        }
        String [] fzrArr = fzr.split(",");
        response.put("president", "医疗机构负责人:"+fzrArr[0]);
        response.put("financialOfficer", "财会负责人:"+fzrArr[1]);
        response.put("reviewer", "审核人:"+fzrArr[2]);
        response.put("maker","制表人:"+user.getUserName());
        response.put("time", "闵行区贫困精神病人门诊免费治疗费报表");
        response.put("jgmc", user.getHospitalName());
        response.put("account","账户："+yhzh);
        response.put("bank", "开户银行："+yhmc);
        response.put("zdr", "制单人："+user.getUserName());
        response.put("lxdh", "联系电话："+lxdh);
        return response;
    }

    private Map<String,Object> getYlhzbkParametersForViewA( List<Map<String, Object>> list) {
        Map<String,Object> response = new HashMap<>();
        double sumGhf=0;
        double sumZlf=0;
        double sumZyf=0;
        double sumZfyf=0;
        double sumZlf_z=0;
        double sumJyf=0;
        double sumJcf=0;
        double sumQtf=0;
        double sumTotal=0;
        for (int i = 0;i < list.size(); i++) {
            Map<String,Object> map = list.get(i);
            sumGhf +=map.get("GHF")==null?0:(Double)map.get("GHF");
            sumZlf+=map.get("ZLF")==null?0:(Double)map.get("ZLF");
            sumZyf+=map.get("ZYF")==null?0:(Double)map.get("ZYF");
            sumZfyf+=map.get("ZFYF")==null?0:(Double)map.get("ZFYF");
            sumZlf_z+=map.get("ZLF_Z")==null?0:(Double)map.get("ZLF_Z");
            sumJyf+=map.get("JYF")==null?0:(Double)map.get("JYF");
            sumJcf+=map.get("JCF")==null?0:(Double)map.get("JCF");
            sumQtf+=map.get("QTF")==null?0:(Double)map.get("QTF");
            sumTotal+=map.get("TOTFY")==null?0:(Double)map.get("TOTFY");
        }

        response.put("HJ", "各项费用合计：");
        response.put("sumGhf", String.format("%1$.2f", sumGhf));
        response.put("sumZlf", String.format("%1$.2f", sumZlf));
        response.put("sumZyf", String.format("%1$.2f", sumZyf));
        response.put("sumZfyf", String.format("%1$.2f", sumZfyf));
        response.put("sumZlf_z", String.format("%1$.2f", sumZlf_z));
        response.put("sumJyf", String.format("%1$.2f", sumJyf));
        response.put("sumJcf", String.format("%1$.2f", sumJcf));
        response.put("sumQtf", String.format("%1$.2f", sumQtf));
        response.put("sumTotal", String.format("%1$.2f", sumTotal));
        return response ;
    }

    public GsbxSbbHzBResp getylhzbkFieldsB(String datefrom,SysUser user,List<Map<String, Object>> list)  {
        GsbxSbbHzBResp ylhzbkhzb = new GsbxSbbHzBResp();
        List<GsbxBResp> ylhzbkb = HisUtil.ListToResultSet(list,new GsbxBResp());
        ylhzbkhzb.setGsbxsbbbresps(ylhzbkb);

        List<Map<String,Object>> records = new ArrayList<>();
        double FY00_ALL = 0; //诊疗费
        double FY01_ALL = 0; //治疗费
        double FY02_ALL = 0; //手术材料费
        double FY03_ALL = 0; //检查费
        double FY04_ALL = 0; //化验费
        double FY05_ALL = 0; //摄片费
        double FY06_ALL = 0; //透视费
        double FY07_ALL = 0; //西药费
        double FY08_ALL = 0; //中成药费
        double FY09_ALL = 0; //中草药费
        double FY10_ALL = 0; //其他
        double TOTFY_ALL = 0; //费用总计
        int XH =0;
        for(Map<String, Object> m : list){
            m.put("XH", ++XH);
            FY00_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY00")));
            FY01_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY01")));
            FY02_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY02")));
            FY03_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY03")));
            FY04_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY04")));
            FY05_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY05")));
            FY06_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY06")));
            FY07_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY07")));
            FY08_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY08")));
            FY09_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY09")));
            FY10_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY10")));
            TOTFY_ALL += NumberUtils.toDouble(String.valueOf(m.get("TOTFY")));
        }
        ylhzbkhzb.setFY00(FY00_ALL+"");
        ylhzbkhzb.setFY01(FY01_ALL+"");
        ylhzbkhzb.setFY02(FY02_ALL+"");
        ylhzbkhzb.setFY03(FY03_ALL+"");
        ylhzbkhzb.setFY04(FY04_ALL+"");
        ylhzbkhzb.setFY05(FY05_ALL+"");
        ylhzbkhzb.setFY06(FY06_ALL+"");
        ylhzbkhzb.setFY07(FY07_ALL+"");
        ylhzbkhzb.setFY08(FY08_ALL+"");
        ylhzbkhzb.setFY09(FY09_ALL+"");
        ylhzbkhzb.setFY10(FY10_ALL+"");
        ylhzbkhzb.setTOTFY(TOTFY_ALL+"");

        Integer jgid = user.getHospitalId();
        String fzr = sysXtcsCacheSer.getCsz(jgid, "YBBBFZR");
        if (fzr == null || fzr.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        String yhzh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHZH");
        if (yhzh == null || yhzh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0045");
        }
        String yhmc = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHMC");
        if (yhmc == null || yhmc.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0046");
        }
        String lxdh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBLXDH");
        if (lxdh == null || lxdh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0047");
        }
        String ybbh = sysXtcsCacheSer.getCsz(jgid, "YBBBBH");
        if (ybbh == null || ybbh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0044");
        }
        String[] fzrArr = fzr.split(",");
        ylhzbkhzb.setPresident(fzrArr[0]);
        ylhzbkhzb.setFinancialOfficer( fzrArr[1]);
        ylhzbkhzb.setReviewer( fzrArr[2]);
        ylhzbkhzb.setMaker( user.getUserName());
        ylhzbkhzb.setHosName(user.getHospitalName());
        ylhzbkhzb.setQuerydate(datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");
        ylhzbkhzb.setHeadno( "编号:" + ybbh + "-" + datefrom.substring(0, 4) + datefrom.substring(5, 7) + datefrom.substring(9));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        ylhzbkhzb.setReportDate( sdf.format(new Date()));
        return ylhzbkhzb;
    }

    public Map<String,Object> getylhzbkParametersB(String datefrom,SysUser user,List<Map<String, Object>> list) {
        Map<String,Object> response = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Integer jgid = user.getHospitalId();
        String fzr = sysXtcsCacheSer.getCsz(jgid, "YBBBFZR");
        if (fzr == null || fzr.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        String ybbh = sysXtcsCacheSer.getCsz(jgid, "YBBBBH");
        if (ybbh == null || ybbh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0044");
        }
        String [] fzrArr = fzr.split(",");
   //     if (isPrint==null) {
        response =   getylhzbkParametersForViewB(list,user,datefrom);
//        }else{
//            getParametersForPrint(response, sdf, begindate, list);
//        }
        response.put("president", "医疗机构负责人:"+fzrArr[0]);
        response.put("financialOfficer", "财会负责人:"+fzrArr[1]);
        response.put("reviewer", "审核人:"+fzrArr[2]);
        response.put("maker","制表人:"+user.getUserName());
        response.put("headno",
                "编号:"+ybbh+"-"+ datefrom.substring(0, 4) + datefrom.substring(5, 7) + datefrom.substring(9));// 待确认规则
        response.put("reportDate", sdf.format(new Date()));
        return response;
    }
    private Map<String,Object> getylhzbkParametersForViewB(   List<Map<String, Object>> list,SysUser user,String datefrom) {
       Map<String,Object> response = new HashMap<>();
        response.put("totalRecord",list.size());
        response.put("hosName", user.getHospitalName());
        response.put("querydate", datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");
        response.put("headno",
                "编号:74727214300-" + datefrom.substring(0, 4) + datefrom.substring(5, 7));// 待确认规则
        response.put("bankNo", "03416900040019262");
        response.put("bankName", "农行闵行区水清南路支行");
       return response;
    }

    public Map<String,Object> getylhzbkParametersC(String datefrom,SysUser user,List<Map<String, Object>> list) {
        Map<String,Object> response = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Integer jgid = user.getHospitalId();
        String fzr = sysXtcsCacheSer.getCsz(jgid, "YBBBFZR");
        if (fzr == null || fzr.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        String yhzh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHZH");
        if (yhzh == null || yhzh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0045");
        }
        String yhmc = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHMC");
        if (yhmc == null || yhmc.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0046");
        }
        String lxdh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBLXDH");
        if (lxdh == null || lxdh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0047");
        }
        String ybbh = sysXtcsCacheSer.getCsz(jgid, "YBBBBH");
        if (ybbh == null || ybbh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0044");
        }
        response.put("hosName", user.getHospitalName());
        response.put("querydate", datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");
        response.put("bankNo", yhzh);
        response.put("bankName", yhmc);
        response.put("reportDate", sdf.format(new Date()));
        response.put("headno",
                "编号:"+ybbh+"-"+ datefrom.substring(0, 4) + datefrom.substring(5, 7) + datefrom.substring(9));// 待确认规则
        int JZNUM_SUM = 0; //就诊次数
        double GRZFD_SUM = 0; //互助帮困补贴支付
        double TCBX_SUM = 0; //互助帮困补助支付
        double HZBK_SUM = 0; //互助帮困支付总计
        for(Map<String, Object> m : list){
            JZNUM_SUM += NumberUtils.toDouble(String.valueOf(m.get("JZNUM")));
            GRZFD_SUM += NumberUtils.toDouble(String.valueOf(m.get("GRZFD")));
            TCBX_SUM += NumberUtils.toDouble(String.valueOf(m.get("YBZF")));
        }
        HZBK_SUM = GRZFD_SUM + TCBX_SUM;
        DecimalFormat df = new DecimalFormat("#0.00");
        String GRZFD_SUM_1 =  df.format(GRZFD_SUM);
        String TCBX_SUM_1 =  df.format(TCBX_SUM);
        String HZBK_SUM_1 =  df.format(HZBK_SUM);

        response.put("JZNUM_SUM",JZNUM_SUM);
        response.put("GRZFD_SUM",GRZFD_SUM_1);
        response.put("TCBX_SUM",TCBX_SUM_1);
        response.put("HZBK_SUM",HZBK_SUM_1);
        response.put("HZBK_SUM_DX",BSPHISUtil.changeMoneyUpper(NumberUtils.toDouble(HZBK_SUM_1)));
        return response;
    }

    ////////////////贫困精神病/////
    public List<Map<String, Object>> QueryYbPkjsbListire(String daa, String datefrom, String dateto, String mzlb, int pageno) {
        logger.info("贫困精神病");
        pageno = (pageno - 1) * 20;
        return shybbbDao.queryYbPkjsbListire(daa, datefrom, dateto, mzlb, pageno);
    }
    public List<Map<String,Object>> getpkjsbFieldsA(List<Map<String, Object>> list) {
        List<Map<String,Object>> res = new ArrayList<>();
//        if (isPrint) {
//            getFieldsForPrint(records, isPrint, list);
//        }else{
         res = getpkjsbFieldsForView( list);
 //       }
        return res;
    }

    private List<Map<String,Object>> getpkjsbFieldsForView(List<Map<String, Object>> list) {
        List<Map<String,Object>> records = new ArrayList<>();
        int JZNUM_SUM = 0; //就诊次数
        double TOTFY_SUM = 0; //费用总计
        double FLGRZF_SUM = 0; //分类自负
        double GRXJ_SUM = 0; //自负
        double GRZFD_SUM = 0; //互助帮困补贴支付
        double TCBX_SUM = 0; //互助帮困补助支付
        double ZF_SUM = 0; //本期非医保结算范围（个人自费费用）合计
        int XH =0;
        for(Map<String, Object> m : list){
            m.put("XH", ++XH);
            JZNUM_SUM += NumberUtils.toDouble(String.valueOf(m.get("JZNUM")));
            TOTFY_SUM += NumberUtils.toDouble(String.valueOf(m.get("TOTFY")));
            FLGRZF_SUM += NumberUtils.toDouble(String.valueOf(m.get("FLGRZF")));
            GRXJ_SUM += NumberUtils.toDouble(String.valueOf(m.get("GRXJ")));
            GRZFD_SUM += NumberUtils.toDouble(String.valueOf(m.get("GRZFD")));
            TCBX_SUM += NumberUtils.toDouble(String.valueOf(m.get("TCBX")));
            ZF_SUM += NumberUtils.toDouble(String.valueOf(m.get("ZF")));
        }
        DecimalFormat df = new DecimalFormat("#0.00");
        String TOTFY_SUM_1 =  df.format(TOTFY_SUM);
        String FLGRZF_SUM_1 =  df.format(FLGRZF_SUM);
        String GRXJ_SUM_1 =  df.format(GRXJ_SUM);
        String GRZFD_SUM_1 =  df.format(GRZFD_SUM);
        String TCBX_SUM_1 =  df.format(TCBX_SUM);
        String ZF_SUM_1 =  df.format(ZF_SUM);
        int pageSize =20;
        int totalRecord  = list.size();
        int totalPage = (totalRecord+pageSize-1) /pageSize;
        int start = pageSize+1;
        int end =  (totalPage-1)*pageSize;
        int i=0;
        for(Map<String, Object> m : list){
            ++i;
            m.put("JZNUM_SUM", JZNUM_SUM);
            m.put("TOTFY_SUM", TOTFY_SUM_1);
            m.put("FLGRZF_SUM", FLGRZF_SUM_1);
            m.put("GRXJ_SUM", GRXJ_SUM_1);
            m.put("GRZFD_SUM", GRZFD_SUM_1);
            m.put("TCBX_SUM", TCBX_SUM_1);
            if(i==list.size()){
                m.put("ZF_SUM", ZF_SUM_1);
            }
            records.add(m);
        }
        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
        for (int j = 0; j < list.size(); j++) {
            result.add(records.get(j));
        }
        records.clear();
        records.addAll(result);
        return records;
    }

    public Map<String,Object> getpkjsbParametersA(String datefrom,SysUser user,List<Map<String, Object>> list)  {
        Map<String,Object> response = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String date1=datefrom.substring(0, 4).toString()+"年"+datefrom.substring(5, 7).toString()+"月";
        Integer jgid = user.getHospitalId();
        String fzr = sysXtcsCacheSer.getCsz(jgid, "YBBBFZR");
        if (fzr == null || fzr.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        String yhzh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHZH");
        if (yhzh == null || yhzh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0045");
        }
        String yhmc = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHMC");
        if (yhmc == null || yhmc.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0046");
        }
        String lxdh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBLXDH");
        if (lxdh == null || lxdh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0047");
        }
        String ybbh = sysXtcsCacheSer.getCsz(jgid, "YBBBBH");
        if (ybbh == null || ybbh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0044");
        }
//        if (isPrint==null) {
        response = getpkjsbParametersForView(list);
//        }else{
//            getParametersForPrint(response, list);
//        }
        String [] fzrArr = fzr.split(",");
        response.put("president", "医疗机构负责人:"+fzrArr[0]);
        response.put("financialOfficer", "财会负责人:"+fzrArr[1]);
        response.put("reviewer", "审核人:"+fzrArr[2]);
        response.put("maker","制表人:"+user.getUserName());
        response.put("time", "闵行区贫困精神病人门诊免费治疗费报表"+date1);
        response.put("jgmc", "定点医院：上海市闵行区"+user.getHospitalName());
        response.put("account","账    户："+yhzh);
        response.put("bank", "开户银行："+yhmc);
        response.put("zdr", "制单人："+user.getUserName());
        response.put("lxdh", "联系电话："+lxdh);
        return response;
    }
    private Map<String,Object> getpkjsbParametersForView( List<Map<String, Object>> list) {
        Map<String,Object> response = new HashMap<>();
        double sumGhf=0;
        double sumZlf=0;
        double sumZyf=0;
        double sumZfyf=0;
        double sumZlf_z=0;
        double sumJyf=0;
        double sumJcf=0;
        double sumQtf=0;
        double sumTotal=0;
        for (int i = 0;i < list.size(); i++) {
            Map<String,Object> map = list.get(i);
            sumGhf +=map.get("GHF")==null?0:(Double)map.get("GHF");
            sumZlf+=map.get("ZLF")==null?0:(Double)map.get("ZLF");
            sumZyf+=map.get("ZYF")==null?0:(Double)map.get("ZYF");
            sumZfyf+=map.get("ZFYF")==null?0:(Double)map.get("ZFYF");
            sumZlf_z+=map.get("ZLF_Z")==null?0:(Double)map.get("ZLF_Z");
            sumJyf+=map.get("JYF")==null?0:(Double)map.get("JYF");
            sumJcf+=map.get("JCF")==null?0:(Double)map.get("JCF");
            sumQtf+=map.get("QTF")==null?0:(Double)map.get("QTF");
            sumTotal+=map.get("TOTFY")==null?0:(Double)map.get("TOTFY");
        }
        response.put("HJ", "各项费用合计：");
        response.put("sumGhf", String.format("%1$.2f", sumGhf));
        response.put("sumZlf", String.format("%1$.2f", sumZlf));
        response.put("sumZyf", String.format("%1$.2f", sumZyf));
        response.put("sumZfyf", String.format("%1$.2f", sumZfyf));
        response.put("sumZlf_z", String.format("%1$.2f", sumZlf_z));
        response.put("sumJyf", String.format("%1$.2f", sumJyf));
        response.put("sumJcf", String.format("%1$.2f", sumJcf));
        response.put("sumQtf", String.format("%1$.2f", sumQtf));
        response.put("sumTotal", String.format("%1$.2f", sumTotal));
        return response;
    }


    public List<Map<String,Object>> getpkjsbFieldsB(List<Map<String, Object>> list)  {
        List<Map<String, Object>> res = new ArrayList<>();
//        if (isPrint) {
//            getFieldsForPrint(records, isPrint, list);
//        }else{
        res = getpkjsbFieldsForViewB( list);
//        }
        return res;
    }
    private List<Map<String,Object>> getpkjsbFieldsForViewB( List<Map<String, Object>> list) {
        List<Map<String,Object>> records = new ArrayList<>();
        int JZNUM_SUM = 0; //就诊次数
        double TOTFY_SUM = 0; //费用总计
        double FLGRZF_SUM = 0; //分类自负
        double GRXJ_SUM = 0; //自负
        double GRZFD_SUM = 0; //互助帮困补贴支付
        double TCBX_SUM = 0; //互助帮困补助支付
        double ZF_SUM = 0; //本期非医保结算范围（个人自费费用）合计
        int XH =0;
        for(Map<String, Object> m : list){
            m.put("XH", ++XH);
            JZNUM_SUM += NumberUtils.toDouble(String.valueOf(m.get("JZNUM")));
            TOTFY_SUM += NumberUtils.toDouble(String.valueOf(m.get("TOTFY")));
            FLGRZF_SUM += NumberUtils.toDouble(String.valueOf(m.get("FLGRZF")));
            GRXJ_SUM += NumberUtils.toDouble(String.valueOf(m.get("GRXJ")));
            GRZFD_SUM += NumberUtils.toDouble(String.valueOf(m.get("GRZFD")));
            TCBX_SUM += NumberUtils.toDouble(String.valueOf(m.get("TCBX")));
            ZF_SUM += NumberUtils.toDouble(String.valueOf(m.get("ZF")));
        }
        DecimalFormat df = new DecimalFormat("#0.00");
        String TOTFY_SUM_1 =  df.format(TOTFY_SUM);
        String FLGRZF_SUM_1 =  df.format(FLGRZF_SUM);
        String GRXJ_SUM_1 =  df.format(GRXJ_SUM);
        String GRZFD_SUM_1 =  df.format(GRZFD_SUM);
        String TCBX_SUM_1 =  df.format(TCBX_SUM);
        String ZF_SUM_1 =  df.format(ZF_SUM);
        int pageSize =20;
        int totalRecord  = list.size();
        int totalPage = (totalRecord+pageSize-1) /pageSize;
        int start = pageSize+1;
        int end =  (totalPage-1)*pageSize;
        int i=0;
        for(Map<String, Object> m : list){
            ++i;
            m.put("JZNUM_SUM", JZNUM_SUM);
            m.put("TOTFY_SUM", TOTFY_SUM_1);
            m.put("FLGRZF_SUM", FLGRZF_SUM_1);
            m.put("GRXJ_SUM", GRXJ_SUM_1);
            m.put("GRZFD_SUM", GRZFD_SUM_1);
            m.put("TCBX_SUM", TCBX_SUM_1);
            if(i==list.size()){
                m.put("ZF_SUM", ZF_SUM_1);
            }
            records.add(m);
        }
        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
        for (int j = 0; j < list.size(); j++) {
            result.add(records.get(j));
        }
        records.clear();
        records.addAll(result);
        return  records;
    }

    public Map<String,Object> getpkjsbParametersB(String datefrom,SysUser user,List<Map<String, Object>> list) {
        Map<String,Object> response = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String date1=datefrom.substring(0, 4).toString()+"年"+datefrom.substring(5, 7).toString()+"月";
        Integer jgid = user.getHospitalId();
        String fzr = sysXtcsCacheSer.getCsz(jgid, "YBBBFZR");
        if (fzr == null || fzr.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        String yhzh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHZH");
        if (yhzh == null || yhzh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0045");
        }
        String yhmc = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHMC");
        if (yhmc == null || yhmc.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0046");
        }
        String lxdh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBLXDH");
        if (lxdh == null || lxdh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0047");
        }
        String ybbh = sysXtcsCacheSer.getCsz(jgid, "YBBBBH");
        if (ybbh == null || ybbh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0044");
        }
//        Object isPrint = request.get("print");
//        if (isPrint==null) {
        response = getpkjsbParametersForViewB(list);
//        }else{
//            getParametersForPrint(response, list);
//        }
        String [] fzrArr = fzr.split(",");
        response.put("president", "医疗机构负责人:"+fzrArr[0]);
        response.put("financialOfficer", "财会负责人:"+fzrArr[1]);
        response.put("reviewer", "审核人:"+fzrArr[2]);
        response.put("maker","制表人:"+user.getUserName());
        response.put("time", "闵行区贫困精神病人门诊免费治疗费报表"+date1);
        response.put("jgmc", "定点医院：上海市闵行区"+user.getHospitalName());
        response.put("account","账    户："+yhzh);
        response.put("bank", "开户银行："+yhmc);
        response.put("zdr", "制单人："+user.getUserName());
        response.put("lxdh", "联系电话："+lxdh);
        return response;
    }
    private Map<String,Object> getpkjsbParametersForViewB(List<Map<String, Object>> list) {
        Map<String,Object> response = new HashMap<>();
        double sumGhf=0;
        double sumZlf=0;
        double sumZyf=0;
        double sumZfyf=0;
        double sumZlf_z=0;
        double sumJyf=0;
        double sumJcf=0;
        double sumQtf=0;
        double sumTotal=0;
        for (int i = 0;i < list.size(); i++) {
            Map<String,Object> map = list.get(i);
            sumGhf +=map.get("GHF")==null?0:(Double)map.get("GHF");
            sumZlf+=map.get("ZLF")==null?0:(Double)map.get("ZLF");
            sumZyf+=map.get("ZYF")==null?0:(Double)map.get("ZYF");
            sumZfyf+=map.get("ZFYF")==null?0:(Double)map.get("ZFYF");
            sumZlf_z+=map.get("ZLF_Z")==null?0:(Double)map.get("ZLF_Z");
            sumJyf+=map.get("JYF")==null?0:(Double)map.get("JYF");
            sumJcf+=map.get("JCF")==null?0:(Double)map.get("JCF");
            sumQtf+=map.get("QTF")==null?0:(Double)map.get("QTF");
            sumTotal+=map.get("TOTFY")==null?0:(Double)map.get("TOTFY");
        }
        response.put("HJ", "各项费用合计：");
        response.put("sumGhf", String.format("%1$.2f", sumGhf));
        response.put("sumZlf", String.format("%1$.2f", sumZlf));
        response.put("sumZyf", String.format("%1$.2f", sumZyf));
        response.put("sumZfyf", String.format("%1$.2f", sumZfyf));
        response.put("sumZlf_z", String.format("%1$.2f", sumZlf_z));
        response.put("sumJyf", String.format("%1$.2f", sumJyf));
        response.put("sumJcf", String.format("%1$.2f", sumJcf));
        response.put("sumQtf", String.format("%1$.2f", sumQtf));
        response.put("sumTotal", String.format("%1$.2f", sumTotal));
        return response;
    }

    //////////////民政帮困/////////////////////
    public List<Map<String, Object>> QueryYbMzbkListire(String daa, String datefrom, String dateto, String mzlb) {
        logger.info("民政帮困");
        //pageno = (pageno - 1) * 20;
        return shybbbDao.queryYbMzbkListire(daa, datefrom, dateto, mzlb);
    }
    public MzbkSbbHzAResp getmzbkFieldsA(String datefrom,SysUser user,List<Map<String, Object>> list) {
        List<Map<String, Object>> records = new ArrayList<>();
        MzbkSbbHzAResp mzbkhza = new MzbkSbbHzAResp();
        List<Map<String,Object>> res = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            map.put("XH", i + 1);
            map.put("BRXM", map.get("NAME"));    // 病人姓名
            map.put("YBID", map.get("YBID"));    // 医保号码
            map.put("KSNA", map.get("KSNA"));    // 科室名称
            map.put("JZDATE", map.get("JZDATE")); // 就诊日期
            map.put("JZNUM", map.get("JZNUM")); // 就诊人次
            map.put("TOTFY", map.get("TOTFY")); // 费用总计
            if (Integer.parseInt(map.get("GRXZ") + "") == 1) {
                map.put("FLZZ", map.get("FLGRZF")); // 分类自负-在职
                map.put("ZFZZ", map.get("GRXJ")); // 自负-在职
                map.put("DNZHZZ", map.get("GRZFD")); // 当年账户资金支付-在职
                map.put("LNZHZZ", map.get("GRZFL")); // 历年账户资金支付-在职
                map.put("FJZZ", map.get("FJBX")); // 附加支付人次-在职

                map.put("FLTX", 0); // 分类自负-退休
                map.put("ZFTX", 0); // 自负-退休
                map.put("DNZHTX", 0); // 当年账户资金支付-退休
                map.put("LNZHTX", 0); // 历年账户资金支付-退休
                map.put("FJTX", 0); // 附加支付人次-退休
            } else {
                map.put("FLTX", map.get("FLGRZF")); // 分类自负-退休
                map.put("ZFTX", map.get("GRXJ")); // 自负-退休
                map.put("DNZHTX", map.get("GRZFD")); // 当年账户资金支付-退休
                map.put("LNZHTX", map.get("GRZFL")); // 历年账户资金支付-退休
                map.put("FJTX", map.get("FJBX")); // 附加支付人次-退休

                map.put("FLZZ", 0); // 分类自负-在职
                map.put("ZFZZ", 0); // 自负-在职
                map.put("DNZHZZ", 0); // 当年账户资金支付-在职
                map.put("LNZHZZ", 0); // 历年账户资金支付-在职
                map.put("FJZZ", 0); // 附加支付人次-在职
            }
            res.add(map);
        }
        List<MzbkAResp> mzbka = HisUtil.ListToResultSet(res,new MzbkAResp());
        mzbkhza.setMzbkasbbresps(mzbka);
        int JZNUM_ZJ = 0;
        double TOTFY_ZJ = 0.00;

        double FLZZ_ZJ = 0.00;
        double FLTX_ZJ = 0.00;
        double ZFZZ_ZJ = 0.00;
        double ZFTX_ZJ = 0.00;
        double DNZHZZ_ZJ = 0.00;
        double DNZHTX_ZJ = 0.00;
        double LNZHZZ_ZJ = 0.00;
        double LNZHTX_ZJ = 0.00;
        double FJZZ_ZJ = 0.00;
        double FJTX_ZJ = 0.00;

        double FLZZ = 0.00;
        double ZFZZ = 0.00;
        double DNZHHJ = 0.00;
        double LNZHHJ = 0.00;
        double FJZZ = 0.00;
        double ZF = 0.00;
        double ZZZF = 0.00;
        double TXZF = 0.00;
        int FJBX = 0;
        int RC = 0;
        int ZZRC = 0;
        int TXRC = 0;
        int FJZZRC = 0;
        int FJTXRC = 0;


        int XH =0;
        for(Map<String, Object> m : list){
            FLZZ += NumberUtils.toDouble(String.valueOf(m.get("FLGRZF")));
            ZFZZ += NumberUtils.toDouble(String.valueOf(m.get("GRXJ")));
            DNZHHJ += NumberUtils.toDouble(String.valueOf(m.get("GRZFD")));
            LNZHHJ += NumberUtils.toDouble(String.valueOf(m.get("GRZFL")));
            FJZZ += NumberUtils.toDouble(String.valueOf(m.get("FJBX")));
            RC += NumberUtils.toDouble(String.valueOf(m.get("JZNUM")));
            ZF += NumberUtils.toDouble(String.valueOf(m.get("ZF")));
            String GRXZ = m.get("GRXZ").toString();
            if("1".equals(GRXZ)) {
                RC += NumberUtils.toDouble(String.valueOf(m.get("JZNUM")));
                ZZZF += NumberUtils.toDouble(String.valueOf(m.get("ZF")));
                ZZRC += NumberUtils.toDouble(String.valueOf(m.get("JZNUM")));

                FLZZ_ZJ += NumberUtils.toDouble(String.valueOf(m.get("FLGRZF")));
                ZFZZ_ZJ += NumberUtils.toDouble(String.valueOf(m.get("GRXJ")));
                DNZHZZ_ZJ += NumberUtils.toDouble(String.valueOf(m.get("GRZFD")));
                LNZHZZ_ZJ += NumberUtils.toDouble(String.valueOf(m.get("GRZFL")));
                FJZZ_ZJ += NumberUtils.toDouble(String.valueOf(m.get("FJBX")));
            }else{
                RC += NumberUtils.toDouble(String.valueOf(m.get("JZNUM")));
                TXZF += NumberUtils.toDouble(String.valueOf(m.get("ZF")));
                TXRC += NumberUtils.toDouble(String.valueOf(m.get("JZNUM")));

                FLTX_ZJ += NumberUtils.toDouble(String.valueOf(m.get("FLGRZF")));
                ZFTX_ZJ += NumberUtils.toDouble(String.valueOf(m.get("GRXJ")));
                DNZHTX_ZJ += NumberUtils.toDouble(String.valueOf(m.get("GRZFD")));
                LNZHTX_ZJ += NumberUtils.toDouble(String.valueOf(m.get("GRZFL")));
                FJTX_ZJ += NumberUtils.toDouble(String.valueOf(m.get("FJBX")));
            }
            double FJZF = NumberUtils.toDouble(String.valueOf(m.get("FJBX")));
            if(FJZF!=0){
                FJBX += NumberUtils.toDouble(String.valueOf(m.get("JZNUM")));
                if("1".equals(GRXZ)){
                    FJZZRC += NumberUtils.toDouble(String.valueOf(m.get("JZNUM")));
                }else{
                    FJTXRC += NumberUtils.toDouble(String.valueOf(m.get("JZNUM")));
                }
            }
            JZNUM_ZJ += NumberUtils.toDouble(String.valueOf(m.get("JZNUM")));
            TOTFY_ZJ += NumberUtils.toDouble(String.valueOf(m.get("TOTFY")));
        }
        mzbkhza.setJZNUM_ZJ(JZNUM_ZJ+"");mzbkhza.setTOTFY_ZJ(TOTFY_ZJ+"");
        mzbkhza.setFLZZ_ZJ(FLZZ_ZJ+"");mzbkhza.setFLTX_ZJ(FLTX_ZJ+"");
        mzbkhza.setZFZZ_ZJ(ZFZZ_ZJ+"");mzbkhza.setZFTX_ZJ(ZFTX_ZJ+"");
        mzbkhza.setDNZHZZ_ZJ(DNZHZZ_ZJ+"");mzbkhza.setDNZHTX_ZJ(DNZHTX_ZJ+"");
        mzbkhza.setLNZHZZ_ZJ(LNZHZZ_ZJ+"");mzbkhza.setLNZHTX_ZJ(LNZHTX_ZJ+"");
        mzbkhza.setFJZZ_ZJ(FJZZ_ZJ+"");mzbkhza.setFJTX_ZJ(FJTX_ZJ+"");

        mzbkhza.setFLZZ(FLZZ+"");mzbkhza.setZFZZ(ZFZZ+"");
        mzbkhza.setDNZHHJ(DNZHHJ+"");mzbkhza.setLNZHHJ(LNZHHJ+"");
        mzbkhza.setFJZZ(FJZZ+"");mzbkhza.setZF(ZF+"");
        mzbkhza.setZZZF(ZZZF+"");mzbkhza.setTXZF(TXZF+"");
        mzbkhza.setFJBX(FJBX+"");mzbkhza.setRC(RC+"");
        mzbkhza.setZZRC(ZZRC+"");mzbkhza.setTXRC(TXRC+"");
        mzbkhza.setFJZZRC(FJZZRC+"");mzbkhza.setFJTXRC(FJTXRC+"");

        Integer jgid = user.getHospitalId();
        String fzr = sysXtcsCacheSer.getCsz(jgid, "YBBBFZR");
        if (fzr == null || fzr.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        String ybbh = sysXtcsCacheSer.getCsz(jgid, "YBBBBH");
        if (ybbh == null || ybbh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0045");
        }
        String[] fzrArr = fzr.split(",");
        if (fzrArr.length < 3) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        mzbkhza.setPresident("医疗机构负责人:"+fzrArr[0]);
        mzbkhza.setFinancialOfficer( "财会负责人:"+fzrArr[0]);
        mzbkhza.setReviewer( "审核人:"+fzrArr[2]);
        mzbkhza.setMaker("制表人:"+user.getUserName());
        mzbkhza.setTotalRecord(list.size()+"");
        mzbkhza.setHosName(user.getHospitalName());
        mzbkhza.setQuerydate(datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");
        mzbkhza.setHeadno("编号:74727214300-" + datefrom.substring(0, 4) + datefrom.substring(5, 7));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        mzbkhza.setReportDate(sdf.format(new Date()));
        return mzbkhza;
    }


    public MzbkSbbHzBResp getmzbkFieldsB(String datefrom,SysUser user,List<Map<String, Object>> list) {
        MzbkSbbHzBResp mzbkhzb = new MzbkSbbHzBResp();
        List<Map<String,Object>> records = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            map.put("XH", i + 1);
            map.put("ZDICD", map.get("ZDICD"));	// 诊断
            map.put("YBID", map.get("YBID"));	// 医保号码
            map.put("FY00", map.get("FY00")); 	//
            map.put("FY01", map.get("FY01"));	//
            map.put("FY02", map.get("FY02"));	//
            map.put("FY03", map.get("FY03"));	//
            map.put("FY04", map.get("FY04"));	//
            map.put("FY05", map.get("FY05"));	//
            map.put("FY06", map.get("FY06"));	//
            map.put("FY07", map.get("FY07"));	//
            map.put("FY08", map.get("FY08"));	//
            map.put("FY09", map.get("FY09"));	//
            map.put("FY10", map.get("FY10"));	//
            map.put("TOTFY", map.get("TOTFY"));	// 费用总计
            records.add(map);
        }
        List<MzbkBResp> mzbkb = HisUtil.ListToResultSet(records,new MzbkBResp());
        mzbkhzb.setMzbksbbbresps(mzbkb);
        Map<String,Object> response = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            sum("double", response, map, "FY00");
            sum("double", response, map, "FY01");
            sum("double", response, map, "FY02");
            sum("double", response, map, "FY03");
            sum("double", response, map, "FY04");
            sum("double", response, map, "FY05");
            sum("double", response, map, "FY06");
            sum("double", response, map, "FY07");
            sum("double", response, map, "FY08");
            sum("double", response, map, "FY09");
            sum("double", response, map, "FY10");
            sum("double", response, map, "TOTFY");
        }
        mzbkhzb.setFY00(response.get("FY00")+"");
        mzbkhzb.setFY01(response.get("FY01")+"");
        mzbkhzb.setFY02(response.get("FY02")+"");
        mzbkhzb.setFY03(response.get("FY03")+"");
        mzbkhzb.setFY04(response.get("FY04")+"");
        mzbkhzb.setFY05(response.get("FY05")+"");
        mzbkhzb.setFY06(response.get("FY06")+"");
        mzbkhzb.setFY07(response.get("FY07")+"");
        mzbkhzb.setFY08(response.get("FY08")+"");
        mzbkhzb.setFY09(response.get("FY09")+"");
        mzbkhzb.setFY10(response.get("FY10")+"");
        mzbkhzb.setTOTFY(response.get("TOTFY")+"");
        Integer jgid = user.getHospitalId();
        String fzr = sysXtcsCacheSer.getCsz(jgid, "YBBBFZR");
        if (fzr == null || fzr.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        String ybbh = sysXtcsCacheSer.getCsz(jgid, "YBBBBH");
        if (ybbh == null || ybbh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0045");
        }
        String[] fzrArr = fzr.split(",");
        if (fzrArr.length < 3) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        mzbkhzb.setPresident("医疗机构负责人:"+fzrArr[0]);
        mzbkhzb.setFinancialOfficer( "财会负责人:"+fzrArr[0]);
        mzbkhzb.setReviewer( "审核人:"+fzrArr[2]);
        mzbkhzb.setMaker("制表人:"+user.getUserName());
        mzbkhzb.setTotalRecord(list.size()+"");
        mzbkhzb.setHosName(user.getHospitalName());
        mzbkhzb.setQuerydate(datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");
        mzbkhzb.setHeadno("编号:74727214300-" + datefrom.substring(0, 4) + datefrom.substring(5, 7));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        mzbkhzb.setReportDate(sdf.format(new Date()));
        return mzbkhzb ;
    }
    public Map<String,Object> getmzbkParametersB(String datefrom,SysUser user,List<Map<String, Object>> list)  {
        Map<String,Object> response = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        response.put("hosName", user.getHospitalName());
        response.put("querydate", datefrom.substring(0, 4) + "年" + datefrom.substring(5,7) + "月");
        response.put("headno", "编号:74727214300-150");//待确认规则
        response.put("bankNo", "03416900040019262");
        response.put("bankName", "农行闵行区水清南路支行");
        response.put("reportDate", sdf.format(new Date()));
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            sum("double", response, map, "FY00");
            sum("double", response, map, "FY01");
            sum("double", response, map, "FY02");
            sum("double", response, map, "FY03");
            sum("double", response, map, "FY04");
            sum("double", response, map, "FY05");
            sum("double", response, map, "FY06");
            sum("double", response, map, "FY07");
            sum("double", response, map, "FY08");
            sum("double", response, map, "FY09");
            sum("double", response, map, "FY10");
            sum("double", response, map, "TOTFY");
        }
        return response;
    }
    public static void sum(String type,Map<String,Object> result,Map<String,Object> detail,String detailKey){
        String resultKey=detailKey+"_ZJ";
        sum(type, result, detail, resultKey, detailKey);
    }
    public static void sum(String type,Map<String,Object> result,Map<String,Object> detail,String resultKey,String detailKey){
        if(!detail.containsKey(detailKey)){
            return;
        }
        Double obj=NumberUtils.toDouble(String.valueOf(result.get(resultKey)));
        obj=obj+NumberUtils.toDouble(String.valueOf(detail.get(detailKey)));
        if("int".equals(type)){
            result.put(resultKey, obj.intValue());
        }else{
            result.put(resultKey, String.format("%.2f",obj));
        }
    }

    public Map<String,Object> getmzbkParametersC(String datefrom,SysUser user,List<Map<String, Object>> list) {
        Map<String,Object> response = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        response.put("hosName", user.getHospitalName());
        response.put("querydate", datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");
        response.put("headno",
                "编号:747272143-" + datefrom.substring(0, 4) + datefrom.substring(5, 7) + datefrom.substring(9));// 待确认规则
        response.put("YHZH", "03416900040019262");
        response.put("KHYH", "农行闵行区水清南路支行");
        response.put("reportDate", sdf.format(new Date()));
        int ZRC = 0 ;
        int ZZRC = 0 ;
        int TXRC = 0 ;
        double NN = 0;
        double ZZNN = 0;
        double TXNN = 0;
        double TCZF =0;
        double ZZTCZF =0;
        double TXTCZF =0;
        double YBZF =0;
        double ZZYBZF =0;
        double TXYBZF =0;
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            int GRXZ = NumberUtils.toInt(String.valueOf(map.get("GRXZ")));
            ZRC +=NumberUtils.toDouble(String.valueOf((map.get("JZNUM"))));
            NN +=NumberUtils.toDouble(String.valueOf(map.get("GRZFD")));
            TCZF +=NumberUtils.toDouble(String.valueOf(map.get("GRZFL")));
            YBZF +=NumberUtils.toDouble(String.valueOf(map.get("GRZFL")))+NumberUtils.toDouble(String.valueOf(map.get("GRZFD")));
            if(GRXZ==1){
                ZZRC +=NumberUtils.toDouble(String.valueOf(map.get("JZNUM")));
                ZZNN +=NumberUtils.toDouble(String.valueOf(map.get("GRZFD")));
                ZZTCZF +=NumberUtils.toDouble(String.valueOf(map.get("GRZFL")));
                ZZYBZF +=NumberUtils.toDouble(String.valueOf(map.get("GRZFL")))+NumberUtils.toDouble(String.valueOf(map.get("GRZFD")));
            }else{
                TXRC +=NumberUtils.toDouble(String.valueOf(map.get("JZNUM")));
                TXNN +=NumberUtils.toDouble(String.valueOf(map.get("GRZFD")));
                TXTCZF +=NumberUtils.toDouble(String.valueOf(map.get("GRZFL")));
                TXYBZF +=NumberUtils.toDouble(String.valueOf(map.get("GRZFL")))+NumberUtils.toDouble(String.valueOf(map.get("GRZFD")));
            }
        }
        response.put("ZRC", ZRC);  //总人次
        response.put("ZZRC", ZZRC); //总人次-在职
        response.put("TXRC", TXRC);//总人次-退休
        response.put("NN", NN);//当年账户-小机
        response.put("ZZNN", ZZNN);//当年账户-在职
        response.put("TXNN", TXNN);//当年账户-退休
        response.put("TCZF", TCZF);//历年账户-小计
        response.put("ZZTCZF", ZZTCZF);//历年账户-在职
        response.put("TXTCZF", TXTCZF);//历年账户-退休
        response.put("YBZF", YBZF);//医保支付-小机
        response.put("ZZYBZF", ZZYBZF);//医保支付-在职
        response.put("TXYBZF", TXYBZF);//医保支付-退休
        response.put("SBJE", BSPHISUtil.changeMoneyUpper(YBZF));
        response.put("SHJE", BSPHISUtil.changeMoneyUpper(YBZF));
        return response;
    }

    public Map<String,Object> getmzbkParametersD(String datefrom,SysUser user,List<Map<String, Object>> list) {
        Map<String,Object> response = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        response.put("hosName", user.getHospitalName());
        response.put("querydate", datefrom.substring(0, 4) + "年" + datefrom.substring(5,7) + "月");
        response.put("headno", "编号:74727214300-150");//待确认规则
        response.put("bankNo", "03416900040019262");
        response.put("bankName", "农行闵行区水清南路支行");
        response.put("reportDate", sdf.format(new Date()));
        int RC =0;
        int ZZRC =0;
        int TXRC =0;
        double FJZF =0;
        double ZZFJZF =0;
        double TXFJZF =0;
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            int GRXZ = NumberUtils.toInt(String.valueOf(map.get("GRXZ")));
            double FJBX = NumberUtils.toDouble(String.valueOf((map.get("FJBX"))));
            FJZF +=NumberUtils.toDouble(String.valueOf((map.get("FJBX"))));
            if(FJBX!=0){
                RC +=NumberUtils.toDouble(String.valueOf((map.get("JZNUM"))));
                if(GRXZ==1){
                    ZZFJZF +=NumberUtils.toDouble(String.valueOf((map.get("FJBX"))));
                    ZZRC +=NumberUtils.toDouble(String.valueOf((map.get("JZNUM"))));
                }else{
                    TXFJZF +=NumberUtils.toDouble(String.valueOf((map.get("FJBX"))));
                    TXRC +=NumberUtils.toDouble(String.valueOf((map.get("JZNUM"))));
                }
            }
        }
        response.put("RC", RC);
        response.put("ZZRC", ZZRC);
        response.put("TXRC", TXRC);
        response.put("FJZF", FJZF);
        response.put("ZZFJZF", ZZFJZF);
        response.put("TXFJZF", TXFJZF);
        response.put("SBJE", BSPHISUtil.changeMoneyUpper(FJZF));
        response.put("SHJE", BSPHISUtil.changeMoneyUpper(FJZF));
        return response;
    }

    //////////异地医保//////
    public List<Map<String, Object>> QueryYbYdybListire(String daa, String datefrom, String dateto, String mzlb) {
        logger.info("异地医保");

        return shybbbDao.queryYbYdybListire(daa, datefrom, dateto, mzlb);
    }
    public YdImSbbHzAResp getydybParametersA(String datefrom,SysUser user,List<Map<String, Object>> list)  {
        Map<String,Object> response = new HashMap<>();
        YdImSbbHzAResp ydimhza = new YdImSbbHzAResp();
        List<YdImAResp> ydima = HisUtil.ListToResultSet(list,new YdImAResp());
        ydimhza.setYdimasbbresps(ydima);
       int ZYDNUM_SUM = 0;
        double JYTOTFY_SUM = 0;
        double GRXJZF_SUM = 0;
        double ZHZJZF_SUM =0;
        double TCJJZF_SUM =0;


        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            ZYDNUM_SUM += NumberUtils.toInt(String.valueOf(map.get("ZYDNUM")));
            JYTOTFY_SUM +=NumberUtils.toDouble(String.valueOf((map.get("TOTFY"))));
            GRXJZF_SUM +=NumberUtils.toDouble(String.valueOf(map.get("GRXJZF")));
            ZHZJZF_SUM +=NumberUtils.toDouble(String.valueOf(map.get("GRZF")));
            TCJJZF_SUM +=NumberUtils.toDouble(String.valueOf(map.get("TCZF")));
        }
        ydimhza.setZYDNUM_SUM(ZYDNUM_SUM+"");
        ydimhza.setJYTOTFY_SUM(JYTOTFY_SUM+"");
        ydimhza.setGRXJZF_SUM(GRXJZF_SUM+"");
        ydimhza.setZHZJZF_SUM(ZHZJZF_SUM+"");
        ydimhza.setTCJJZF_SUM(TCJJZF_SUM+"");
        Integer jgid = user.getHospitalId();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String fzr = sysXtcsCacheSer.getCsz(jgid, "YBBBFZR");
        if (fzr == null || fzr.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        String ybbh = sysXtcsCacheSer.getCsz(jgid, "YBBBBH");
        if (ybbh == null || ybbh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0044");
        }
        String [] fzrArr = fzr.split(",");
        if (fzrArr.length<3) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        ydimhza.setTotalRecord(list.size()+"");
        ydimhza.setHosName(user.getHospitalName());
        ydimhza.setReportDate(sdf.format(new Date()));
        ydimhza.setQuerydate(datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");
       ydimhza.setHeadno( "编号:"+ybbh+"-" + datefrom.substring(0, 4) + datefrom.substring(5, 7) + datefrom.substring(9));
        ydimhza.setPresident("医疗机构负责人:"+fzrArr[0]);
        ydimhza.setFinancialOfficer("财会负责人:"+fzrArr[1]);
        ydimhza.setReviewer("审核人:"+fzrArr[2]);
        ydimhza.setMaker("制表人:"+user.getUserName());

        return ydimhza;
    }

    public YdImSbbHzBResp getydybFieldsB(String datefrom,SysUser user,List<Map<String, Object>> list)  {
        YdImSbbHzBResp ydimhzb = new YdImSbbHzBResp();
        List<Map<String,Object>> res = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).put("XH", i + 1);
        }
        res.addAll(list);
        List<YdImBResp> ydimb = HisUtil.ListToResultSet(res,new YdImBResp());
        ydimhzb.setYdimsbbresps(ydimb);
        Integer jgid = user.getHospitalId();
        String fzr = sysXtcsCacheSer.getCsz(jgid, "YBBBFZR");
        if (fzr == null || fzr.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        String yhzh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHZH");
        if (yhzh == null || yhzh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0045");
        }
        String yhmc = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHMC");
        if (yhmc == null || yhmc.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0046");
        }
        String lxdh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBLXDH");
        if (lxdh == null || lxdh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0047");
        }
        String ybbh = sysXtcsCacheSer.getCsz(jgid, "YBBBBH");
        if (ybbh == null || ybbh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0044");
        }
        String[] fzrArr = fzr.split(",");
        ydimhzb.setPresident(fzrArr[0]);
        ydimhzb.setFinancialOfficer( fzrArr[1]);
        ydimhzb.setReviewer( fzrArr[2]);
        ydimhzb.setMaker( user.getUserName());
        ydimhzb.setHosName(user.getHospitalName());
        ydimhzb.setQuerydate(datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");
        ydimhzb.setHeadno( "编号:" + ybbh + "-" + datefrom.substring(0, 4) + datefrom.substring(5, 7) + datefrom.substring(9));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        ydimhzb.setReportDate( sdf.format(new Date()));
        return ydimhzb;
    }
    public Map<String,Object> getydybParametersB(String datefrom,SysUser user,List<Map<String, Object>> list)  {
        Map<String,Object> response = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Integer jgid = user.getHospitalId();
        String fzr = sysXtcsCacheSer.getCsz(jgid, "YBBBFZR");
        if (fzr == null || fzr.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        String ybbh = sysXtcsCacheSer.getCsz(jgid, "YBBBBH");
        if (ybbh == null || ybbh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0044");
        }
        String [] fzrArr = fzr.split(",");
        if (fzrArr.length<3) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        response.put("totalRecord",list.size());
        response.put("hosName", user.getHospitalName());
        response.put("querydate", datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");
        response.put("headno",
                "编号:"+ybbh+"-" + datefrom.substring(0, 4) + datefrom.substring(5, 7) + datefrom.substring(9));// 待确认规则
        response.put("reportDate", sdf.format(new Date()));
        response.put("president", "医疗机构负责人:"+fzrArr[0]);
        response.put("financialOfficer", "财会负责人:"+fzrArr[1]);
        response.put("reviewer", "审核人:"+fzrArr[2]);
        response.put("maker", user.getUserName());
        return response;
    }

    public Map<String,Object> getydybParametersC(String datefrom,SysUser user,List<Map<String, Object>> list)  {
        Map<String,Object> response = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String jgmc = user.getHospitalName();
        Integer jgid = user.getHospitalId();
        response.put("hosName", jgmc);
        String yhzh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHZH");
        if (yhzh == null || yhzh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0045");
        }
        String yhmc = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHMC");
        if (yhmc == null || yhmc.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0046");
        }
        String ybbh = sysXtcsCacheSer.getCsz(jgid, "YBBBBH");
        if (ybbh == null || ybbh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0044");
        }
        response.put("querydate", datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");
        response.put("headno",
                "编号:" +ybbh+"-" + datefrom.substring(0, 4) + datefrom.substring(5, 7) + datefrom.substring(9));// 待确认规则
        response.put("YHZH", yhzh);
        response.put("KHYH", yhmc);
        response.put("reportDate", sdf.format(new Date()));
        int rc = 0;
        BigDecimal YBZF = new BigDecimal(0.00);
        BigDecimal ZHZF = new BigDecimal(0.00);
        BigDecimal TCZF = new BigDecimal(0.00);
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            double ZYDNUM =  NumberUtils.toDouble(String.valueOf(map.get("ZYDNUM"))); 	//住院天数
            String CYDATE = String.valueOf(map.get("CYDATE"));//出院日期
            int k = ZYDNUM <  0 ? -1 : 1;
            k = ("".equals(CYDATE)  || "0".equals(CYDATE)) ? 0 :  k;
            // 账户
            BigDecimal ZHZF1 = new BigDecimal(map.get("GRZFLD") + "").add(new BigDecimal(map.get("GRZFLQ") + ""));
            // 统筹支付
            BigDecimal TCZF1 = new BigDecimal(map.get("TCZF") + "");

            ZHZF = ZHZF.add(ZHZF1);
            TCZF = TCZF.add(TCZF1);
            rc+= k;
        }
        YBZF = YBZF.add(ZHZF).add(TCZF);
        response.put("RC", rc);
        response.put("ZHZF", ZHZF.doubleValue());
        response.put("TCZF", TCZF.doubleValue());
        response.put("YBZF", YBZF.doubleValue());

        response.put("SBJE", BSPHISUtil.changeMoneyUpper(YBZF.doubleValue()));
        response.put("SHJE", BSPHISUtil.changeMoneyUpper(YBZF.doubleValue()));
        return response;
    }
    ////////异地医保门诊//////////////////////
    public List<Map<String, Object>> QueryYbYdybopListire(String daa, String datefrom, String dateto, String mzlb) {
        logger.info("异地医保门诊");

        return shybbbDao.queryYbYdybopListire(daa, datefrom, dateto, mzlb);
    }
    public YdopSbbHzAResp getydybopFieldsA(String datefrom,SysUser user,List<Map<String, Object>> list) {
        List<Map<String, Object>> records = new ArrayList<>();
        YdopSbbHzAResp ydophza = new YdopSbbHzAResp();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            map.put("XH", i + 1);
            map.put("BRXM", map.get("NAME")); 	// 病人姓名
            map.put("YBID", map.get("YBID"));	// 医保号码
            map.put("KSNA", map.get("KSNA")); 	// 科室名称
            map.put("JZDATE", map.get("JZDATE")); // 就诊日期
            map.put("JZNUM", map.get("JZNUM")); // 就诊人次
            map.put("JYTOTFY", map.get("JYTOTFY")); // 费用总计
            map.put("GRXJ", map.get("GRXJ")); // 个人现金自付
            map.put("ZHZJZF", NumberUtils.toDouble(String.valueOf(map.get("GRZFD")))+NumberUtils.toDouble(String.valueOf(map.get("GRZFL")))); // 个人现金自付
            map.put("TCJJZF", map.get("TCZF")); // 个人现金自付
            records.add(map);
        }
        List<YdopAResp> ydopa = HisUtil.ListToResultSet(records,new YdopAResp());
        ydophza.setYdopsbbresps(ydopa);
        ydophza.setTotalRecord(list.size()+"");
        double JZNUM_ALL = 0; //其他
        double JYTOTFY_ALL = 0; //其他
        double GRXJ_ALL = 0; //其他
        double ZHZJZF_ALL = 0; //其他
        double TCJJZF_ALL = 0; //其他
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            JZNUM_ALL+=NumberUtils.toDouble(String.valueOf(map.get("JZNUM")));
            JYTOTFY_ALL+=NumberUtils.toDouble(String.valueOf(map.get("JYTOTFY")));
            GRXJ_ALL+=NumberUtils.toDouble(String.valueOf(map.get("GRXJ")));
            ZHZJZF_ALL+=NumberUtils.toDouble(String.valueOf(map.get("GRZFD")))+NumberUtils.toDouble(String.valueOf(map.get("GRZFL")));
            TCJJZF_ALL+=NumberUtils.toDouble(String.valueOf(map.get("TCJJZF")));
        }
        ydophza.setJZNUM_ALL(JZNUM_ALL+"");
        ydophza.setJYTOTFY_ALL(JYTOTFY_ALL+"");
        ydophza.setGRXJ_ALL(GRXJ_ALL+"");
        ydophza.setZHZJZF_ALL(ZHZJZF_ALL+"");
        ydophza.setTCJJZF_ALL(TCJJZF_ALL+"");

        Integer jgid = user.getHospitalId();
        String fzr = sysXtcsCacheSer.getCsz(jgid, "YBBBFZR");
        if (fzr == null || fzr.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        String yhzh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHZH");
        if (yhzh == null || yhzh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0045");
        }
        String yhmc = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHMC");
        if (yhmc == null || yhmc.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0046");
        }
        String lxdh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBLXDH");
        if (lxdh == null || lxdh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0047");
        }
        String ybbh = sysXtcsCacheSer.getCsz(jgid, "YBBBBH");
        if (ybbh == null || ybbh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0044");
        }
        String[] fzrArr = fzr.split(",");
        ydophza.setPresident("医疗机构负责人:"+fzrArr[0]);
        ydophza.setFinancialOfficer( "财会负责人:"+fzrArr[1]);
        ydophza.setReviewer( "审核人:"+fzrArr[2]);
        ydophza.setMaker( "制表人:"+user.getUserName());
        ydophza.setHosName(user.getHospitalName());
        ydophza.setQuerydate(datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");
        ydophza.setHeadno( "编号:" + ybbh + "-" + datefrom.substring(0, 4) + datefrom.substring(5, 7) + datefrom.substring(9));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        ydophza.setReportDate( sdf.format(new Date()));
        return ydophza;
    }

    public Map<String,Object> getydybopParametersA(String datefrom,SysUser user,List<Map<String, Object>> list) {
        Map<String,Object> response = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");


        Integer jgid = user.getHospitalId();
        String fzr = sysXtcsCacheSer.getCsz(jgid, "YBBBFZR");
        if (fzr == null || fzr.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        String bbbh = sysXtcsCacheSer.getCsz(jgid, "YBBBBH");
        if (bbbh == null || bbbh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0044");
        }
        String [] fzrArr = fzr.split(",");
        if (fzrArr.length<3) {
            throw BaseException.create("ERROR_SHYB_0043");
        }

//        Object isPrint = request.get("print");
//        if (isPrint==null) {
        response = getydybopParametersForView( list);
//        }else{
//            getParametersForPrint(response, list);
//        }
        response.put("president", "医疗机构负责人:" + fzrArr[0]);
        response.put("financialOfficer", "财会负责人:" + fzrArr[1]);
        response.put("reviewer", "审核人:"+ fzrArr[2]);
        response.put("maker","制表人:"+ user.getUserName());
        response.put("hosName", user.getHospitalName());
        response.put("querydate", datefrom.substring(0, 4) + "年" + datefrom.substring(5,7) + "月");
        response.put("headno",
                "编号:" + bbbh +"-"+ datefrom.substring(2, 4) + datefrom.substring(5, 7) );// 待确认规则
        response.put("reportDate", sdf.format(new Date()));
        return response;
    }
    private Map<String,Object> getydybopParametersForView(List<Map<String, Object>> list) {
        Map<String,Object> response = new HashMap();
        response.put("totalRecord",list.size());
        double JZNUM_ALL = 0; //其他
        double JYTOTFY_ALL = 0; //其他
        double GRXJ_ALL = 0; //其他
        double ZHZJZF_ALL = 0; //其他
        double TCJJZF_ALL = 0; //其他
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            JZNUM_ALL+=NumberUtils.toDouble(String.valueOf(map.get("JZNUM")));
            JYTOTFY_ALL+=NumberUtils.toDouble(String.valueOf(map.get("JYTOTFY")));
            GRXJ_ALL+=NumberUtils.toDouble(String.valueOf(map.get("GRXJ")));
            ZHZJZF_ALL+=NumberUtils.toDouble(String.valueOf(map.get("GRZFD")))+NumberUtils.toDouble(String.valueOf(map.get("GRZFL")));
            TCJJZF_ALL+=NumberUtils.toDouble(String.valueOf(map.get("TCJJZF")));
        }
        response.put("JZNUM_ALL",String.format("%.2f", JZNUM_ALL)); //
        response.put("JYTOTFY_ALL",String.format("%.2f", JYTOTFY_ALL)); //
        response.put("GRXJ_ALL",String.format("%.2f", GRXJ_ALL)); //
        response.put("ZHZJZF_ALL",String.format("%.2f", ZHZJZF_ALL)); //
        response.put("TCJJZF_ALL",String.format("%.2f", TCJJZF_ALL)); //
        return response;
    }

    public YdopSbbHzBResp getydybopFieldsB(String datefrom,SysUser user,List<Map<String, Object>> list)  {
        YdopSbbHzBResp ydophzb = new YdopSbbHzBResp();
        List<Map<String,Object>> res = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).put("XH", i + 1);
        }
        List<YdopBResp> ydopb = HisUtil.ListToResultSet(list,new YdopBResp());
        ydophzb.setYdopbsbbresps(ydopb);
        double FY00_ALL = 0; //诊疗费
        double FY01_ALL = 0; //治疗费
        double FY02_ALL = 0; //手术材料费
        double FY03_ALL = 0; //检查费
        double FY04_ALL = 0; //化验费
        double FY05_ALL = 0; //摄片费
        double FY06_ALL = 0; //透视费
        double FY07_ALL = 0; //西药费
        double FY08_ALL = 0; //中成药费
        double FY09_ALL = 0; //中草药费
        double FY10_ALL = 0; //其他
        double TOTFY_ALL = 0; //费用总计
        double ZF_ALL = 0;//自费
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> m = list.get(i);
            FY00_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY00")));
            FY01_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY01")));
            FY02_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY02")));
            FY03_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY03")));
            FY04_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY04")));
            FY05_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY05")));
            FY06_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY06")));
            FY07_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY07")));
            FY08_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY08")));
            FY09_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY09")));
            FY10_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY10")));

            ZF_ALL += NumberUtils.toDouble(String.valueOf(m.get("ZF")));
            TOTFY_ALL += NumberUtils.toDouble(String.valueOf(m.get("TOTFY")))+NumberUtils.toDouble(String.valueOf(m.get("ZF")));
        }
        ydophzb.setFY00_ALL(String.format("%.2f", FY00_ALL));
        ydophzb.setFY01_ALL(String.format("%.2f", FY01_ALL));
        ydophzb.setFY02_ALL(String.format("%.2f", FY02_ALL));
        ydophzb.setFY03_ALL(String.format("%.2f", FY03_ALL));
        ydophzb.setFY04_ALL(String.format("%.2f", FY04_ALL));
        ydophzb.setFY05_ALL(String.format("%.2f", FY05_ALL));
        ydophzb.setFY06_ALL(String.format("%.2f", FY06_ALL));
        ydophzb.setFY07_ALL(String.format("%.2f", FY07_ALL));
        ydophzb.setFY08_ALL(String.format("%.2f", FY08_ALL));
        ydophzb.setFY09_ALL(String.format("%.2f", FY09_ALL));
        ydophzb.setFY10_ALL(String.format("%.2f", FY10_ALL));
        ydophzb.setTOTFY_ALL(String.format("%.2f", FY00_ALL));
        ydophzb.setZF_ALL(String.format("%.2f", FY00_ALL));
        ydophzb.setTotalRecord(list.size()+"");
        Integer jgid = user.getHospitalId();
        String fzr = sysXtcsCacheSer.getCsz(jgid, "YBBBFZR");
        if (fzr == null || fzr.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        String yhzh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHZH");
        if (yhzh == null || yhzh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0045");
        }
        String yhmc = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHMC");
        if (yhmc == null || yhmc.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0046");
        }
        String lxdh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBLXDH");
        if (lxdh == null || lxdh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0047");
        }
        String ybbh = sysXtcsCacheSer.getCsz(jgid, "YBBBBH");
        if (ybbh == null || ybbh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0044");
        }
        String[] fzrArr = fzr.split(",");
        ydophzb.setPresident("医疗机构负责人:"+fzrArr[0]);
        ydophzb.setFinancialOfficer( "财会负责人:"+fzrArr[1]);
        ydophzb.setReviewer("审核人:"+ fzrArr[2]);
        ydophzb.setMaker("制表人:"+ user.getUserName());
        ydophzb.setHosName(user.getHospitalName());
        ydophzb.setQuerydate(datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");
        ydophzb.setHeadno( "编号:" + ybbh + "-" + datefrom.substring(0, 4) + datefrom.substring(5, 7) + datefrom.substring(9));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        ydophzb.setReportDate( sdf.format(new Date()));
        return ydophzb;
    }

    public Map<String,Object> getydybopParametersB(String datefrom,SysUser user,List<Map<String, Object>> list) {
        Map<String,Object> response = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");


        Integer jgid = user.getHospitalId();
        String fzr = sysXtcsCacheSer.getCsz(jgid, "YBBBFZR");
        if (fzr == null || fzr.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        String bbbh = sysXtcsCacheSer.getCsz(jgid, "YBBBBH");
        if (bbbh == null || bbbh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0044");
        }
        String [] fzrArr = fzr.split(",");
        if (fzrArr.length<3) {
            throw BaseException.create("ERROR_SHYB_0043");
        }

		/*if (isPrint==null) {
			getParametersForView(request,response, sdf, begindate, list);
		}else{
			getParametersForPrint(response, sdf, begindate, list);
		}*/
        response = getydybopParametersForPrintB(list);
        response.put("reportDate", sdf.format(new Date()));
        response.put("hosName", user.getHospitalName());
        response.put("headno",
                "编号:"+bbbh+"-"+ datefrom.substring(2, 4) + datefrom.substring(5, 7) );// 待确认规则
        response.put("president", "医疗机构负责人:"+ fzrArr[0]);
        response.put("financialOfficer", "财会负责人:"+ fzrArr[1]);
        response.put("reviewer", "审核人:"+ fzrArr[2]);
        response.put("maker","制表人:"+user.getUserName());
        return response;
    }

    private Map<String,Object> getydybopParametersForPrintB( List<Map<String, Object>> list) {
        Map<String,Object> response = new HashMap<>();
        double FY00_ALL = 0; //诊疗费
        double FY01_ALL = 0; //治疗费
        double FY02_ALL = 0; //手术材料费
        double FY03_ALL = 0; //检查费
        double FY04_ALL = 0; //化验费
        double FY05_ALL = 0; //摄片费
        double FY06_ALL = 0; //透视费
        double FY07_ALL = 0; //西药费
        double FY08_ALL = 0; //中成药费
        double FY09_ALL = 0; //中草药费
        double FY10_ALL = 0; //其他
        double TOTFY_ALL = 0; //费用总计
        double ZF_ALL = 0;//自费
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> m = list.get(i);
            FY00_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY00")));
            FY01_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY01")));
            FY02_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY02")));
            FY03_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY03")));
            FY04_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY04")));
            FY05_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY05")));
            FY06_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY06")));
            FY07_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY07")));
            FY08_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY08")));
            FY09_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY09")));
            FY10_ALL += NumberUtils.toDouble(String.valueOf(m.get("FY10")));

            ZF_ALL += NumberUtils.toDouble(String.valueOf(m.get("ZF")));
            TOTFY_ALL += NumberUtils.toDouble(String.valueOf(m.get("TOTFY")))+NumberUtils.toDouble(String.valueOf(m.get("ZF")));
        }
        response.put("FY00_ALL",String.format("%.2f", FY00_ALL)); //
        response.put("FY01_ALL",String.format("%.2f", FY01_ALL)); //
        response.put("FY02_ALL",String.format("%.2f", FY02_ALL)); //
        response.put("FY03_ALL",String.format("%.2f", FY03_ALL)); //
        response.put("FY04_ALL",String.format("%.2f", FY04_ALL)); //
        response.put("FY05_ALL",String.format("%.2f", FY05_ALL)); //
        response.put("FY06_ALL",String.format("%.2f", FY06_ALL)); //
        response.put("FY07_ALL",String.format("%.2f", FY07_ALL)); //
        response.put("FY08_ALL",String.format("%.2f", FY08_ALL)); //
        response.put("FY09_ALL",String.format("%.2f", FY09_ALL)); //
        response.put("FY10_ALL",String.format("%.2f", FY10_ALL)); //
        response.put("TOTFY_ALL",String.format("%.2f", TOTFY_ALL)); //
        response.put("ZF_ALL",String.format("%.2f", ZF_ALL)); //
        response.put("totalRecord",list.size());
        return response;
    }

    public Map<String,Object> getydybopParametersC(String datefrom,SysUser user,List<Map<String, Object>> list) {
        Map<String,Object> response = new HashMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Integer jgid = user.getHospitalId();
        response.put("querydate", datefrom.substring(0, 4) + "年" + datefrom.substring(5, 7) + "月");
        String yhzh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHZH");
        String yhmc = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBYHMC");
        String lxdh = sysXtcsCacheSer.getCsz(jgid, "YLHZBKBBLXDH");
        if (yhzh == null || yhzh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0045");
        }
        if (yhmc == null || yhmc.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0046");
        }
        if (lxdh == null || lxdh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0047");
        }

        String fzr = sysXtcsCacheSer.getCsz(jgid, "YBBBFZR");
        if (fzr == null || fzr.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        String [] fzrArr = fzr.split(",");
        if (fzrArr.length<3) {
            throw BaseException.create("ERROR_SHYB_0043");
        }
        String bbbh = sysXtcsCacheSer.getCsz(jgid, "YBBBBH");
        if (bbbh == null || bbbh.trim().length() == 0) {
            throw BaseException.create("ERROR_SHYB_0044");
        }
        response.put("hosName", user.getHospitalName());
        response.put("headno",
                "编号:"+ bbbh+ datefrom.substring(0, 4) + datefrom.substring(5, 7)+"1" );// 待确认规则
        response.put("YHZH", yhzh);
        response.put("KHYH", yhmc);
        response.put("reportDate", sdf.format(new Date()));
        int ZRC = 0 ;      //总人次    小计
        double GRZFD =0;   //医保支付总计退休
        double GRZFL =0;   //医保支付总计退休
        double TCZF =0;   //医保支付总计退休
        double JYTOTFY =0;   //医保支付总计退休
        double TCJJZF =0;   //医保支付总计退休
        double ZHZJZF =0;   //医保支付总计退休
        double YBZFZJ =0;   //医保支付总计退休

        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            ZRC +=NumberUtils.toDouble(String.valueOf((map.get("JZNUM"))));
            GRZFD +=NumberUtils.toDouble(String.valueOf(map.get("GRZFD")));
            GRZFL +=NumberUtils.toDouble(String.valueOf(map.get("GRZFL")));
            TCZF +=NumberUtils.toDouble(String.valueOf(map.get("TCZF")));
            JYTOTFY +=NumberUtils.toDouble(String.valueOf(map.get("JYTOTFY")));
        }
        DecimalFormat df = new DecimalFormat("#0.00");
        TCJJZF = TCZF;
        ZHZJZF = GRZFD+GRZFL;
        YBZFZJ = ZHZJZF+TCZF;
        response.put("TCJJZF", df.format(TCJJZF));
        response.put("ZHZJZF", df.format(ZHZJZF));
        response.put("YBZFZJ", df.format(YBZFZJ));
        response.put("ZRC", ZRC);
        response.put("SBJE", BSPHISUtil.changeMoneyUpper(YBZFZJ));
        response.put("SHJE", BSPHISUtil.changeMoneyUpper(YBZFZJ));
        response.put("SBJE", BSPHISUtil.changeMoneyUpper(YBZFZJ));
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy.MM.dd");
        response.put("TBRQ",sdf2.format(new Date()));
        response.put("JBR",user.getUserName());
        return response;


    }
}
