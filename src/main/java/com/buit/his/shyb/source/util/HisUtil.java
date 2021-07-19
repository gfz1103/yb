package com.buit.his.shyb.source.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;

public class HisUtil {
    static final Logger logger = LoggerFactory.getLogger(HisUtil.class);

    public static String getDate() {
        Date date = new Date();
        SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd");
        return matter.format(date);
    }

    public static final Map<String,Object> upcaseKeys(Map<String,Object> src){
        Map<String,Object> target=new HashMap<String, Object>(src.size());
        for(Iterator<String> it = src.keySet().iterator(); it.hasNext();){
            String key=it.next();
            target.put(key.toUpperCase(), src.get(key));
        }
        return target;
    }
    /**
     * 获取指定格式的日期
     *
     * @param format
     * @return
     */
    public static String getDate(String format,Date date) {
        SimpleDateFormat matter = new SimpleDateFormat(format);
        return matter.format(date);
    }

    /**
     * 结果集List<Map,String> 转List<T>
     *
     * @param <T>
     * @param mapList
     * @param bean
     * @return
     * @author wy
     */
    @SuppressWarnings("hiding")
    public static <T> List<T> ListToResultSet(List<Map<String, Object>> mapList, T bean) {
        List<T> resultList = new ArrayList<T>();
        try {
            for (Map<String, Object> map : mapList) {
                bean = (T) bean.getClass().newInstance();
                T t = cn.hutool.core.bean.BeanUtil.fillBeanWithMapIgnoreCase(map, bean, true);
                resultList.add(t);
            }
        } catch (InstantiationException e) {
            logger.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(), e);
        }
        return resultList;
    }
}
