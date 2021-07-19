package com.buit.his.gpo.ws.xml;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * @Author sunjx
 * @Date 2020-11-03 15:11
 * @Description
 **/
public abstract class GpoXmlUtil {

    /**
     * xml to javabean
     */
    public static <T> T toObj(String xml, TypeReference<T> typeReference){
        Map<String, Object> map = cn.hutool.core.util.XmlUtil.xmlToMap(xml);
        JSONObject jsonObject = JSONUtil.parseFromMap(map);
        return JSONUtil.toBean(jsonObject, typeReference, false);
    }

    /**
     * javabean to xml
     */
    public static String toXml(Object obj, String rootName){
        String xmldata = cn.hutool.core.util.XmlUtil.mapToXmlStr(JSONUtil.parseObj(obj, true, true), rootName);
        xmldata = xmldata.replaceFirst(" standalone=\"no\"", "").replaceFirst("UTF-8","utf-8");
        return xmldata;
    }

    /**
     * format
     */
    public static String format(String xml){
        return cn.hutool.core.util.XmlUtil.format(xml).replaceFirst(" standalone=\"no\"", "");
    }
}
