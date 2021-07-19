package com.buit.his.gpo.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.buit.constans.TableName;
import com.buit.his.gpo.controller.request.GpoBaseRequest;
import com.buit.his.gpo.model.GpoBjd;
import com.buit.his.gpo.ws.GpoConsts;
import com.buit.his.gpo.ws.GpoWebService;
import com.buit.his.gpo.ws.GpoZidian;
import com.buit.his.gpo.ws.request.BjdYY015ReqMain;
import com.buit.his.gpo.ws.response.BaseRespMain;
import com.buit.his.gpo.ws.response.BjdYY015RespStruct;
import com.buit.his.gpo.ws.response.DdYY009RespStruct;
import com.buit.his.gpo.ws.xml.CommonMapUtils;
import com.buit.his.gpo.ws.xml.Head;
import com.buit.his.gpo.ws.xml.Struct;
import com.buit.his.gpo.ws.xml.XmlData;
import com.buit.utill.RedisFactory;
import com.buit.utill.TimestampUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author sunjx
 * @Date 2020-11-09 15:53
 * @Description 报价单
 **/
@Service
public class BjdService {

    @Autowired
    private GpoWebService webService;

    @Autowired
    private GpoService gpoService;

    @Autowired
    private RedisFactory redisFactory;

    public List<GpoBjd> list(GpoBaseRequest baseRequest, String rqks, String rqjs, String yqbm, String splx, String yplx, String xjdbh) {
        //ws
        BjdYY015ReqMain main = new BjdYY015ReqMain();
        main.setYQBM(yqbm==null?"":yqbm);
        main.setQSRQ(rqks);
        main.setJZRQ(rqjs);
        main.setSPLX(splx==null?"":splx);
        main.setYPLX(yplx==null?"":yplx);
        main.setXJDBH(xjdbh==null?"":xjdbh);
        main.setBJDBH("");

        List<GpoBjd> list = new ArrayList<>();
        XmlData<BaseRespMain, BjdYY015RespStruct> xmldata;
        do {
            xmldata = webService.bjdcxbhqYY015(new XmlData<>(new Head(baseRequest.getIp(), baseRequest.getMac(), null), main, null), baseRequest);

            if(CollUtil.isEmpty(xmldata.getDETAIL())){
                break;
            }

            for (Struct<BjdYY015RespStruct> struct : xmldata.getDETAIL()) {
                Map<String, Object> stringObjectMap = CommonMapUtils.objToMap(struct);
                BjdYY015RespStruct bjdyy015 = JSONUtil.toBean(stringObjectMap.get("struct").toString(), BjdYY015RespStruct.class);
                //药库类型为中草药, 药品类型不为中草药
                if(baseRequest.getYklx().equals(GpoConsts.YKLX.ZCY.getKey())
                        && !bjdyy015.getYPLX().equals(GpoZidian.YPLX.ZYYP.getVal())){
                    break;
                }

                //药库类型为西药, 药品类型为中草药
                if(baseRequest.getYklx().equals(GpoConsts.YKLX.XY.getKey())
                        && bjdyy015.getYPLX().equals(GpoZidian.YPLX.ZYYP.getVal())){
                    break;
                }

                GpoBjd bjd = new GpoBjd();
                bjd.setXh(redisFactory.getTableKey(TableName.DB_NAME, TableName.TB_NAME_GPO_BJD));
                bjd.setJgid(baseRequest.getJgid());
                bjd.setGpoJgid(gpoService.gpoJgbm(baseRequest.getJgid()).getGpoJgdm());
                bjd.setBjdbh(bjdyy015.getBJDBH());
                bjd.setXjdbh(bjdyy015.getXJDBH());
                bjd.setYqbm(bjdyy015.getYQBM());
                bjd.setYqmc(bjdyy015.getYQMC());
                bjd.setSplx(bjdyy015.getSPLX());
                bjd.setYplx(bjdyy015.getYPLX());
                bjd.setZxspbm(bjdyy015.getZXSPBM());
                bjd.setCpm(bjdyy015.getCPM());
                bjd.setYpjx(bjdyy015.getYPJX());
                bjd.setGg(bjdyy015.getGG());
                bjd.setBzdwxz(bjdyy015.getBZDWXZ());
                bjd.setBzdwmc(bjdyy015.getBZDWMC());
                bjd.setYydwmc(bjdyy015.getYYDWMC());
                bjd.setBznhsl(bjdyy015.getBZNHSL());
                bjd.setScqymc(bjdyy015.getSCQYMC());
                bjd.setYpbj(bjdyy015.getYPBJ());
                bjd.setZt(GpoConsts.ZT.YX.getCode());
                bjd.setCtime(TimestampUtil.now());
                bjd.setCuser(baseRequest.getUserid());
                bjd.setCuname(baseRequest.getUname());
                list.add(bjd);
            }
            Map<String, Object> stringObjectMap = CommonMapUtils.objToMap(xmldata.getDETAIL().get(xmldata.getDETAIL().size()-1));
            BjdYY015RespStruct bjdyy015 = JSONUtil.toBean(stringObjectMap.get("struct").toString(), BjdYY015RespStruct.class);
            main.setBJDBH(bjdyy015.getBJDBH());
        }while ("0".equals(xmldata.getMAIN().getSFWJ()));

        return list;
     }
}
