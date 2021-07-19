package com.buit.his.shyb.upload.uploadutils;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * @Author weijing
 * @Date 2020-11-02 11:18
 * @Description
 **/
public class DataObjectUtils {

    /**
     * 设置空值为空字符串
     * @param object
     */
    public static void  coverEmptyNullToString(Object object){
        try {
            Field[] fs = object.getClass().getDeclaredFields();
            for (int i = 0;i < fs.length;i++){
                Field f = fs[i];
                f.setAccessible(true);//设置属性是可以访问的
                Object val = f.get(object);//字段值
                String type = f.getType().toString(); //字段类型
                if (type.endsWith("String")){
                    if (val == null){
                        f.set(object,"");
                    }
                }
                if (type.endsWith("BigDecimal")){
                    if (val == null){
                        f.set(object,new BigDecimal("0.00"));
                    }else {
                        String format = String.format("%.2f", Float.valueOf(val.toString()));
                        f.set(object,new BigDecimal(format));//保留两位小数，个位数及小数点后两位无数字填充0，四舍五入
                    }
                }
            }
        }catch (Exception e){
            e.getStackTrace();
        }
    }
}
