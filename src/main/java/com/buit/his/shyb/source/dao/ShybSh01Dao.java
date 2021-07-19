package com.buit.his.shyb.source.dao;

import com.buit.commons.EntityDao;
import com.buit.his.shyb.source.entity.business.SH01;
import com.buit.his.shyb.source.model.MedicalInsuranceModel;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;
/**
 * 上海医保挂号试算<br>
 * @author Beijunhua
 */
@Mapper
public interface ShybSh01Dao extends EntityDao<MedicalInsuranceModel, Integer> {
    /**
     * 挂号预结
     * @param preRegisMap
     */
    void insertShybsh01(Map<String,Object> preRegisMap);

    /**
     * 挂号实结
     * @param regisMap
     */
    void insertShybsh02(Map<String,Object> regisMap);

    /**
     * 挂号医保费用
     * @param parameters
     * @return
     */
    public Map<String,Object> getGhybFee(Map<String,Object> parameters);

    /**
     * 收费医保费用
     * @param parameters
     * @return
     */
    public Map<String,Object> getSfybFee(Map<String,Object> parameters);

    /**
     * 住院医保费用
     * @param parameters
     * @return
     */
    public Map<String,Object> getZyybFee(Map<String,Object> parameters);

    /**
     * 退费费用
     * @param parameters
     */
    void insertGhSk01(Map<String,Object> parameters);

    /**
     * 获取就诊单元号
     * @param currDate
     * @param brid
     * @param jgid
     * @param ghks
     * @return
     */
    public List<Map<String,Object>> getJzdyh(String currDate,Integer brid,Integer jgid,String ghks);

    public Map<String,Object> getSeqMsgid(String seq_name);
    //public Map<String,Object> getSeqMsgids();

    /**
     * 获取大病项目代码
     * @param jzlsh
     * @param jzkh
     * @return
     */
    public Map<String,Object> getDbtype(String jzlsh,String jzkh);

    /**
     * 更新sh02
     * @param SBXH
     */
    void updateSh02(String SBXH);

    /**
     * 更新si12
     * @param SBXH
     */
    void updateSi12(String SBXH);

    /**
     * 获取工伤认定号
     * @param jzlsh
     * @param jzkh
     * @return
     */
    public Map<String,Object> getGsrdh(String jzlsh,String jzkh);

    /**
     * 更新si12
     * @param jssqxh mzxh
     */
    void updateSi12bftf(String jssqxh,String mzxh);
}
