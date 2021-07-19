package com.buit.utill;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @Author sunjx
 * @Date 2020-09-27 18:49
 * @Description
 **/
public abstract class TimestampUtil {

    private static final String YYYY_MM_DD = "yyyy-MM-dd";

    private static final String DATE_TIME_PARTTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 当前时间
     */
    public static Timestamp now(){
        return Timestamp.valueOf(LocalDateTime.now());
    }

    /**
     * 获取今年开始时间
     */
    public static Timestamp yearBegin(String year){
        if(StrUtil.isBlank(year)){
            return null;
        }
        return Timestamp.valueOf(LocalDateTime.of(Integer.parseInt(year), 1, 1, 0, 0, 0, 0));
    }

    /**
     * 获取今年结束时间
     */
    public static Timestamp yearEnd(String year){
        if(StrUtil.isBlank(year)){
            return null;
        }
        return Timestamp.valueOf(LocalDateTime.of(Integer.parseInt(year), 12, 31, 23, 59, 59, 999_999_999));
    }

    /**
     * 根据格式的字符串转换timestamp
     */
    public static Timestamp parseStr(String str, String pattern){
        if(StrUtil.hasBlank(str, pattern)){
            return null;
        }
        return Timestamp.valueOf(DateUtil.parseLocalDateTime(str, pattern));
    }

    /**
     * 根据日期格式yyyy-MM-dd的字符串转换timestamp
     */
    public static Timestamp parseDateStr(String dateStr){
        if(StrUtil.isBlank(dateStr)){
            return null;
        }
        return Timestamp.valueOf(DateUtil.parseLocalDateTime(dateStr, YYYY_MM_DD));
    }

    /**
     * 根据日期格式yyyy-MM-dd HH:mm:ss的字符串转换timestamp
     */
    public static Timestamp parseDateTimeStr(String datetimeStr){
        if(StrUtil.isBlank(datetimeStr)){
            return null;
        }
        return Timestamp.valueOf(DateUtil.parseLocalDateTime(datetimeStr, DATE_TIME_PARTTERN));
    }

    /**
     * sql.Date 转换为Timestamp
     */
    public static Timestamp parseSqlDate(Date date) {
        if(ObjectUtil.isEmpty(date)){
            return null;
        }
        return new Timestamp(date.getTime());
    }

    /**
     * Timestamp 转为 sql.Date
     */
    public static Date sqlDate(Timestamp timestamp){
        if(ObjectUtil.isEmpty(timestamp)){
            return null;
        }
        return new Date(timestamp.getTime());
    }

    /**
     * 计算当前年龄
     */
    public static Integer dateAge(String dateStr){
        if(StrUtil.isBlank(dateStr)){
            return null;
        }
        return DateUtil.age(TimestampUtil.parseDateStr(dateStr), Timestamp.valueOf(LocalDateTime.now()));
    }

    /**
     * 计算当前年龄
     */
    public static Integer dateAge(Date date){
        if(ObjectUtil.isEmpty(date)){
            return null;
        }
        return DateUtil.age(date, Timestamp.valueOf(LocalDateTime.now()));
    }

    /**
     * 计算当前年龄
     */
    public static Integer dateAge(Timestamp date){
        if(ObjectUtil.isEmpty(date)){
            return null;
        }
        return DateUtil.age(date, Timestamp.valueOf(LocalDateTime.now()));
    }
}
