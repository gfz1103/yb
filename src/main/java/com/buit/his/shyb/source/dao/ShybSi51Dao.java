package com.buit.his.shyb.source.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * 上海医保住院试算<br>
 * @author Beijunhua
 */
@Mapper
public interface ShybSi51Dao {

    /**
     * 获取结算次数
     * @param zyh
     * @param jscs
     * @return
     */
    public long countJs(Integer zyh,int jscs);

    /**
     * 获取明细账单号
     * @param zyh
     * @param jzdyh
     * @param jscs
     * @return
     */
    List<Map<String,Object>> getMxzdh(String zyh,String jzdyh,String jscs,String jsrq);

    /**
     * 获取上传明细
     * @param
     * @param jzdyh
     * @return
     */
    List<Map<String,Object>> getScmxList( String zyh,String jzdyh,String jscs,String jsrq);

    /**
     * 获取费用明细
     * @param zyh
     * @param jzdyh
     * @param
     * @return
     */
    List<Map<String,Object>> getFymxList(String zyh,String jzdyh,String jscs,String jsrq);

    /**
     * 获取疾病名称
     * @param zyh
     * @return
     */
    List<Map<String,Object>> getJbmc(String zyh);

    /**
     * 是否结算判别
     * @param totalexpense
     * @param zyh
     * @return
     */
    long countisJs(String totalexpense,Integer zyh);

    /**
     * 住院预结
     * @param resMap
     */
    void insertYbsi51(Map<String,Object> resMap);

    /**
     * 住院实结
     * @param si52Map
     */
    void insertYb52(Map<String,Object> si52Map);

    /**
     * 更新费用明细上传标志
     * @param zyh
     * @param jzdyh
     * @param mxzdh
     */
    void updateFymxbz(@Param("jscs")  Integer jscs,@Param("zyh")  String zyh, @Param("jzdyh") String jzdyh,@Param("mxzdh")  List<String> mxzdh);

    /**
     * 实结次数
     * @param jssqxh
     * @param jscs
     */
    void updateJscs52(String jssqxh,String jscs);

    /**
     * 住院作废
     * @param lsh
     */
    void updateYbsi51Zf(String lsh);

    /**
     * 更新费用明细上传标志
     * @param lsh
     */
    void updateImfymxjsbz(String lsh);

    /**
     * 获取明细账单号
     * @param jssqxh
     * @return
     */
    Map<String,Object> checkIsdeal(String jssqxh);

    /**
     * 获取费用明细记录序号
     * @param zyh
     * @param jzdyh
     * @param
     * @return
     */
    List<Map<String,Object>> queryJlxh(String zyh,String jzdyh,List<String> mxzdh);
}
