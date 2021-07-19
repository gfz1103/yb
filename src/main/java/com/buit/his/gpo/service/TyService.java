package com.buit.his.gpo.service;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.buit.his.gpo.controller.request.GpoBaseRequest;
import com.buit.his.gpo.ws.GpoWebService;
import com.buit.his.gpo.ws.request.TyYY021Main;
import com.buit.his.gpo.ws.request.TychYY020Main;
import com.buit.his.gpo.ws.response.BaseRespMain;
import com.buit.his.gpo.ws.response.FpYY004RespStruct;
import com.buit.his.gpo.ws.response.TyYY021RespStruct;
import com.buit.his.gpo.ws.response.TychRespMain;
import com.buit.his.gpo.ws.xml.CommonMapUtils;
import com.buit.his.gpo.ws.xml.Head;
import com.buit.his.gpo.ws.xml.Struct;
import com.buit.his.gpo.ws.xml.XmlData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TyService {

    @Autowired
    private GpoWebService webService;

    public Object djcx(String cxlx, String cxbh, String djid, GpoBaseRequest baseRequest) {
        TychYY020Main main = new TychYY020Main();
        main.setCXLX(cxlx);
        main.setCXBH(cxbh);
        main.setDJID(djid == null ? "" : djid);
        XmlData<TychRespMain, Object> xmlData = webService.dtdjtycxYY020(new XmlData<>(new Head(baseRequest.getIp(), baseRequest.getMac(), null), main, null), baseRequest);
        Object main1 = xmlData.getMAIN();
        JSONObject jsonObject = JSONUtil.parseObj(main1);
        TychRespMain tychRespMain = JSONUtil.toBean(jsonObject, TychRespMain.class);
        return tychRespMain;
    }

    public Object ddzt(String ddbh, GpoBaseRequest baseRequest) {
        TyYY021Main main = new TyYY021Main();
        main.setDDBH(ddbh);
        main.setDDMXBH("");
        String sfwj="";
        XmlData<Object, TyYY021RespStruct> xmlData = null;
        List<TyYY021RespStruct> objects = new ArrayList<>();
        do {
            xmlData = webService.hqddmxztYY021(new XmlData<>(new Head(baseRequest.getIp(), baseRequest.getMac(), null), main, null), baseRequest);
            Object main1 = xmlData.getMAIN();
            JSONObject jsonObject = JSONUtil.parseObj(main1);
            sfwj=jsonObject.getStr("SFWJ>");
            List<Struct<TyYY021RespStruct>> detail = xmlData.getDETAIL();
            for (Struct<TyYY021RespStruct> struct : detail) {
                JSONObject json = JSONUtil.parseObj((Object)struct.getSTRUCT());
                TyYY021RespStruct tychRespMain = JSONUtil.toBean(json, TyYY021RespStruct.class);
                objects.add(tychRespMain);
            }
        } while ("0".equals(sfwj));

        return objects;
    }
}
