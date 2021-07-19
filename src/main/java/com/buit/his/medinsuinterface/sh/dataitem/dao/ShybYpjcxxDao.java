package com.buit.his.medinsuinterface.sh.dataitem.dao;

import java.util.List;

import com.buit.his.gpo.dto.YpbzxxDto;
import com.buit.his.medinsuinterface.sh.dataitem.response.MedicalInsuranceDrugResp;
import org.apache.dubbo.config.support.Parameter;
import org.apache.ibatis.annotations.Mapper;

import com.buit.commons.EntityDao;
import com.buit.his.medinsuinterface.sh.dataitem.model.ShybYpjcxx;
import org.apache.ibatis.annotations.Param;

/**
 * 药品品规基础信息表<br>
 * @author 老花生
 */
@Mapper
public interface ShybYpjcxxDao extends EntityDao<ShybYpjcxx,Integer> {
	/**
	 * 根据统编代码查询对象
	 * @param shybYpjcxx	统编代码
	 * @return
	 */
	Long getBytBdm(ShybYpjcxx shybYpjcxx) ;

	/**
	 * 根据统编代码更新对象
	 * @param obj	根据统编代码更新对象
	 * @return
	 */
	Long updateByTbdm(ShybYpjcxx obj);
	List<ShybYpjcxx> shurufa(String  que);

	/**
	 * 医保药品选择器
	 * */
    List<MedicalInsuranceDrugResp> ybypSelector(String TBDM,String PY_CODE);

    void  insertList( List<ShybYpjcxx> list);

    List<YpbzxxDto> queryYpbzxx(@Param("jgid") String jgid ,@Param("list") List<String> ypybbmList);
}
