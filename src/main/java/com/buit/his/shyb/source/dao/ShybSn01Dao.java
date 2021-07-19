package com.buit.his.shyb.source.dao;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;
/**
 * 上海医保费用明细上传
 * @author beijunhua
 */
@Mapper
public interface ShybSn01Dao {
    /**
     * 获取明细账单
     * @param paramMap
     * @return
     */
    List<Map<String,Object>> getMxzd(Map<String,Object> paramMap);

    /**
     * 获取最大账单序号
     * @param jzdyh
     * @return
     */
    List<Map<String,Object>> getMaxxh(String jzdyh);

    /**
     * 保存明细上传
     * @param sn01
     */
    void saveSn01Mz(Map<String,Object> sn01);

    /**
     * 获取住院费用明细
     * @param jscs
     * @param zyh
     * @return
     */
    List<Map<String,Object>> getZyfymx(String jscs,String zyh);

    /**
     * 获取费用日期
     * @param jscs
     * @param zyh
     * @return
     */
    List<Map<String,Object>> getFyrq(String jscs,String zyh,String jsrq);

    /**
     * 更新明细账单号
     * @param parameters
     */
    void updateMxzdh(Map<String,Object> parameters);

    /**
     * 新增住院上传结果
     * @param mxxm
     */
    void insertYbSn01Zy(Map<String,Object> mxxm);

    /**
     * 新增住院上传失败结果
     * @param mxxm
     */
    void insertYbSn01ZyEr(Map<String,Object> mxxm);

    /**
     * 获取明细账单号列表
     * @param zyh
     * @param jzdyh
     * @param
     * @return
     */
    List<Map<String,Object>> getMxzdList(String zyh,String jzdyh , String jscs,String jsrq);

    /**
     * 更新计算申请序号
     * @param jssqxh
     * @param
     */
    void updateJssqxh(String jssqxh,String zyh,String jzdyh,String jscs,String jsrq);

    /**
     * 获取记录序号
     * @param mxzdh
     * @param jzdyh
     * @return
     */
    List<Map<String,Object>> getJlxh( List<String>  mxzdh,String jzdyh);

    /**
     * 获取费用明细记录序号
     * @param zyh
     * @param jzdyh
     * @param mxzdh
     * @return
     */
    List<Map<String,Object>> getFymxjlxh(String zyh,String jzdyh,List<String> mxzdh);

    /**
     * 获取明细账单列表
     * @param zyh
     * @param jzdyh
     * @param mxzdh
     * @return
     */
    List<Map<String,Object>> getMxzdsList(String zyh,String jzdyh,List<String> mxzdh);

    /**
     * 获取明细账单
     * @param jzdyh
     * @return
     */
    List<Map<String,Object>> getMxzdhs(String jzdyh);

    void updateFymxbz(Map<String,Object> map);
}
