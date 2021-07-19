package com.buit.his.medinsuinterface.sh.dataitem.service;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.buit.commons.BaseException;
import com.buit.commons.SysUser;
import com.buit.constans.TableName;
import com.buit.his.shyb.source.dao.ShybSjd1Dao;
import com.buit.his.shyb.source.entity.SettleResultDTO;
import com.buit.his.shyb.source.entity.business.MessageBody;
import com.buit.his.shyb.source.entity.business.SJC1;
import com.buit.his.shyb.source.entity.business.SJD1Res;
import com.buit.his.shyb.source.entity.business.SJF1;
import com.buit.his.shyb.source.service.impl.OfflineSettleServiceImpl;
import com.buit.his.shyb.source.util.CharacterTurnPy;
import com.buit.his.shyb.source.util.JackJsonUtil;
import com.buit.his.shyb.source.util.ServiceCode;
import com.buit.op.service.OpMzlbService;
import com.buit.utill.*;
import org.apache.commons.collections.map.HashedMap;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.poi.ss.formula.functions.Now;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Struct;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <br>
 * @author bjh
 */
@Service
public class SJD1Ser {
    
    static final Logger logger = LoggerFactory.getLogger(SJD1Ser.class);

    @DubboReference
    private OpMzlbService opMzlbDao;
    @Autowired
    private ShybSjd1Dao shybSjd1Dao;
    @Autowired
    private OfflineSettleServiceImpl offlineSettleServiceImpl;
    @Autowired
    RedisFactory redisFactory;


    public ReturnEntity<SJD1Res> queryMzzyInfo(String carddata, String ip, SysUser user) {
        SJD1Res sjd1res = new SJD1Res();
        Map<String,Object> map = new HashMap();
        String cardtype = "1";
        if(!"".equals(carddata) && carddata.length()==28){
            cardtype = "0";
        }
        String orgid = "";
        Map map_orgid = opMzlbDao.getYbjgdm(user.getHospitalId(),ip);
        if(map_orgid!=null && !map_orgid.isEmpty()){
            orgid = map_orgid.get("ybjgid").toString();
        }
        map.put("orgId",orgid);
        map.put("cardtype",cardtype);
        map.put("carddata",carddata);
        map.put("djtype","1");
        map.put("ip",ip);
        map.put("ygdm",user.getUserId());
        map.put("ygxm",user.getUserName());
        map.put("jgid",user.getHospitalId());
        SettleResultDTO resultDTO = offlineSettleServiceImpl.sjD1(map);
        if (resultDTO.getCode().intValue() == ServiceCode.CODE_OK && resultDTO.getData() != null) {
            MessageBody messageBody = JackJsonUtil.parse(resultDTO.getData().toString(), MessageBody.class);
            Map<String, Object> xxnr = (Map<String, Object>)messageBody.getXxnr();
            sjd1res = BeanUtil.fillBeanWithMapIgnoreCase(xxnr,sjd1res, true);
        }
        return ReturnEntityUtil.success(sjd1res);
    }

    /**
     * 转诊处理
     * @param sjf1
     * @param ip
     * @param user
     */
    public void doMzzy(SJF1 sjf1, String ip , SysUser user){
        String djno = StrUtil.null2Str(sjf1.getDjno());
        if("".equals(djno)){
            throw BaseException.create("ERROR_SHYB_0048");
        }
        Map<String,Object> map = new HashedMap();
        Map<String,Object> slry_map = new HashedMap();
        String orgid = "";
        Map map_orgid = opMzlbDao.getYbjgdm(user.getHospitalId(),ip);
        if(map_orgid!=null && !map_orgid.isEmpty()){
            orgid = map_orgid.get("ybjgid").toString();
        }
        String carddata = sjf1.getCarddata();
        String cardtype = "0" ;
        if("".equals(carddata) || carddata ==null){
            carddata = "";
            cardtype = "1";
        }
        slry_map.put("djxh",redisFactory.getTableKey(TableName.DB_NAME, TableName.SHYB_SLRY_DJ));
        slry_map.put("brkh", StrUtil.null2Str(sjf1.getCarddata()));
        slry_map.put("brxm",StrUtil.null2Str(sjf1.getPersonname()));
        slry_map.put("djbh",StrUtil.null2Str(sjf1.getDjno()));
        slry_map.put("bdsj", DateUtil.now());
        slry_map.put("czgh", user.getUserId());
        slry_map.put("djbz", "0");
        slry_map.put("iscal", 0);
        slry_map.put("calrq", "");
        slry_map.put("zcyybm", StrUtil.null2Str(sjf1.getZcyybm()));
        slry_map.put("zryybm", StrUtil.null2Str(sjf1.getZrjgbh()));
        slry_map.put("zyrq",StrUtil.null2Str(sjf1.getStartdate()));
        Integer mzlb = opMzlbDao.getMzlb(user.getHospitalId(),ip);
        slry_map.put("mzlb", mzlb);
        try{
            shybSjd1Dao.insertShybSldj(slry_map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw BaseException.create("ERROR_SHYB_0035");
        }
        map.put("orgId",orgid);
        map.put("jgid",user.getHospitalId());
        map.put("ygdm",user.getUserId());
        map.put("ygxm",user.getUserName());
        map.put("ip",ip);
        map.put("cardtype",cardtype);
        map.put("carddata",carddata);
        map.put("djtype","1");
        map.put("djno",StrUtil.null2Str(sjf1.getDjno()));
        map.put("zrjgbh",StrUtil.null2Str(sjf1.getZrjgbh()));
        map.put("startdate",StrUtil.null2Str(sjf1.getStartdate()));
        SettleResultDTO resultDTO = offlineSettleServiceImpl.sjF1(map);
        if (resultDTO.getCode() == 200 && resultDTO.getData() != null) {
            logger.info("转出成功"+resultDTO.getData().toString());
        }else{
            try {
                shybSjd1Dao.deleteShybSldj(sjf1.getDjno());
            }catch (Exception e) {
                logger.error(e.getMessage(), e);
                throw BaseException.create("ERROR_SHYB_0037");
            }
            throw BaseException.create("ERROR_SHYB_0036",new String[]{resultDTO.getCode()+"--"+resultDTO.getMsg()+""});
        }
    }

    /**
     * 转院更新
     * @param ip
     * @param user
     */
    public void doUp(String ip , SysUser user){
        Map<String,Object> map = new HashedMap();
        String orgid = "";
        Map map_orgid = opMzlbDao.getYbjgdm(user.getHospitalId(),ip);
        if(map_orgid!=null && !map_orgid.isEmpty()){
            orgid = map_orgid.get("ybjgid").toString();
        }
        map.put("ip",ip);
        map.put("orgId",orgid);
        map.put("jgid",user.getHospitalId());
        map.put("ygdm",user.getUserId());
        map.put("ygxm",user.getUserName()
        );
        SettleResultDTO resultDTO = offlineSettleServiceImpl.SJH1(map);
        MessageBody messageBody = JackJsonUtil.parse(resultDTO.getData().toString(), MessageBody.class);
        Map<String, Object> xxnr = (Map<String, Object>)messageBody.getXxnr();
        List<Map<String, Object>> zrjgs = (List<Map<String, Object>>)xxnr.get("zrjgs");
        try {
            if (zrjgs.size() > 0) {
                shybSjd1Dao.deleteZcyydm();
            }
            Map<String, Object> m = new HashMap<String, Object>();
            for (int i = 0;i < zrjgs.size(); i++) {
                m.clear();
                m.put("id", String.valueOf(i));
                m.put("code", zrjgs.get(i).get("zrjgbh"));
                m.put("name", zrjgs.get(i).get("zrjgmc"));
                String hosNamePinyin = PinyinUtils.getSimplePinYin(zrjgs.get(i).get("zrjgmc")+"");
                m.put("pydm", hosNamePinyin);
                shybSjd1Dao.insertZcyydm(m);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw BaseException.create("ERROR_SHYB_0038");
        }
    }

    /**
     * 转诊撤销
     * @param sjc1req
     * @param ip
     * @param user
     */
    public void doCancel(SJC1 sjc1req, String ip , SysUser user){
        Map<String,Object> map = new HashedMap();
        String orgid = "";
        Map map_orgid = opMzlbDao.getYbjgdm(user.getHospitalId(),ip);
        if(map_orgid!=null && !map_orgid.isEmpty()){
            orgid = map_orgid.get("ybjgid").toString();
        }
        String carddata = sjc1req.getCarddata();
        String cardtype = "0" ;
        if("".equals(carddata) || carddata ==null){
            carddata = "";
            cardtype = "1";
        }
        map.put("ip",ip);
        map.put("orgId",orgid);
        map.put("jgid",user.getHospitalId());
        map.put("ygxm",user.getUserName());
        map.put("ygdm",user.getUserId());
        map.put("cardtype",cardtype);
        map.put("cardtdata",carddata);
        map.put("djtype","1");
        map.put("zcjgdm",sjc1req.getZcyybm());
        map.put("zrjgdm",sjc1req.getZrjgbh());
        map.put("startdate",sjc1req.getStartdate());
        SettleResultDTO resultDTO = offlineSettleServiceImpl.sjC1(map);
        if (resultDTO.getCode().intValue() == ServiceCode.CODE_OK && resultDTO.getData() != null) {
            shybSjd1Dao.updateShybslrydj(sjc1req.getCarddata(),sjc1req.getZcyybm(),sjc1req.getZrjgbh());
        }else {
            throw BaseException.create("ERROR_SHYB_0039",new String[]{resultDTO.getCode()+"--"+resultDTO.getMsg()+""});
        }
    }
}
