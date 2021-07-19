package com.buit.his.medinsuinterface.sh.dataitem.dao;

import com.buit.commons.EntityDao;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybbbReq;
import com.buit.his.medinsuinterface.sh.dataitem.response.*;
import com.buit.his.medinsuinterface.sh.dataitem.service.ShybbbSer;
import org.apache.ibatis.annotations.Mapper;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 上海医保对账
 * 
 * @author beijunhua
 */
@Mapper
public interface ShybbbDao extends EntityDao<ShybbbSer, Integer> {

	/**
	 * 挂号金额
	 * @param mzlb
	 * @param querydate
	 * @return
	 */
	List<Map<String,Object>> getGh(Integer mzlb, String querydate);
	 List<Map<String,Object>> getSf(Integer mzlb, String querydate);
	 List<Map<String,Object>> getZy(Integer mzlb, String querydate);
	 List<Map<String,Object>> getTfmz(Integer mzlb, String querydate);
	 List<Map<String,Object>> getZytfmz(Integer mzlb, String querydate);

	LinkedList<CzptSbbResp> queryYbCityNormalList(ShybbbReq req);
	 List<MxscJyjlkResp> queryMxscJyjlkList(String date, int mzlb);
	 List<MxscDbghkResp> queryMxscDbghkList(String date, String mzlb);
	 List<MxscZyjsjlkResp> queryMxscZyjsjlkList(String date, String mzlb);
	 List<Map<String,Object>> queryMxscZyfymxkList(String date, String mzlb);
	 void updateXflsh(Map<String, Object> parameters);
	 List<MxscZyfymxkResp> queryYp(String date, String mzlb);
	 List<Map<String,Object>> queryYj(String date, String mzlb);
	 List<Map<String,Object>> queryYptf(String date, String mzlb);
	 List<Map<String,Object>> queryYjtf(String date, String mzlb);
	List<MxscCyzdkResp>  queryMxscCyzdkList(String date, String mzlb);

	//明细项目细分库
	List<MxscMxxmxfkResp> queryOpyjsql(String date, String mzlb);

	LinkedList<CzdbSbbResp> queryYbDbCityNormalList(ShybbbReq req);
	LinkedList<CzzySbbResp> queryYbZyCityNormalList(ShybbbReq req);
	LinkedList<CztsSbbResp> queryYbTsCityNormalList(ShybbbReq req);
	LinkedList<CztszySbbResp> queryYbTszyCityNormalList(ShybbbReq req);
	LinkedList<CzjmjsSbbResp> queryYbJmCityNormalList(ShybbbReq req);
	LinkedList<CzjmzySbbResp> queryYbJmzyCityNormalList(ShybbbReq req);
	//工伤
	LinkedList<YbOpGsbxbResp> queryYbGsbxopList(ShybbbReq req);
	//医疗互助帮困
	LinkedList<YbbbYlhzbkResp> queryYbYlhzbkList(ShybbbReq req);
	//贫困精神病
	LinkedList<ybbbpkjsbResp> queryYbPkjsbList(ShybbbReq req);
	//民政帮困
	LinkedList<YbbbMzbkResp> queryYbMzbkList(ShybbbReq req);
	//异地医保住院
	LinkedList<YbbbYdybResp> queryYbYdybList(ShybbbReq req);
	//非实时上传门诊
	LinkedList<YbbbFssscopResp> queryYbFssscopList(ShybbbReq req);
	//非实时上传住院
	LinkedList<YbbbFssscimResp> queryYbFssscimList(ShybbbReq req);
	//非实时上传住院记录
	LinkedList<YbbbFssscimResp> queryYbFssscimjlList(ShybbbReq req);
	//异地医保门诊
	LinkedList<YbbbYdybopResp> queryYbYdybopList(ShybbbReq req);
	//流动人口
	LinkedList<LdrkSbbResp> queryLdrkList(ShybbbReq req);

	//上海医疗保险费用结算申报汇总表
	LinkedList<ShFeehzResp> queryFeehzList(String datefrom,String dateto,List<String>mzlb_list);
	LinkedList<Map<String,Object>> queryFeehzLists(String datefrom,String dateto,List<String>mzlb_list);
	LinkedList<Map<String,Object>> queryOpFeeList(String datefrom,String dateto,List<String>mzlb_list);
	LinkedList<Map<String,Object>> queryDbFeeList(String datefrom,String dateto,List<String>mzlb_list);
	LinkedList<Map<String,Object>> queryImFeeList(String datefrom,String dateto,List<String>mzlb_list);
	LinkedList<Map<String,Object>> queryOpSlDbFeeList(String datefrom,String dateto,List<String>mzlb_list);
	LinkedList<Map<String,Object>> queryImSlFeeList(String datefrom,String dateto,List<String>mzlb_list);
	LinkedList<Map<String,Object>> queryOpTsFeeList(String datefrom,String dateto,List<String>mzlb_list);
	LinkedList<Map<String,Object>> queryImTsFeeList(String datefrom,String dateto,List<String>mzlb_list);
	LinkedList<Map<String,Object>> queryOpZcbjsFeeList(String datefrom,String dateto,List<String>mzlb_list);
	LinkedList<Map<String,Object>> queryXjbtDbFeeList(String datefrom,String dateto,List<String>mzlb_list);
	LinkedList<Map<String,Object>> queryXjbtImFeeList(String datefrom,String dateto,List<String>mzlb_list);
	LinkedList<Map<String,Object>> queryWqOpFeeList(String datefrom,String dateto,List<String>mzlb_list);
	LinkedList<Map<String,Object>> queryWqImFeeList(String datefrom,String dateto,List<String>mzlb_list);
	LinkedList<Map<String,Object>> queryJdszOpFeeList(String datefrom,String dateto,List<String>mzlb_list);
	LinkedList<Map<String,Object>> queryJdszImFeeList(String datefrom,String dateto,List<String>mzlb_list);
	LinkedList<Map<String,Object>> querySgbjOpFeeList(String datefrom,String dateto,List<String>mzlb_list);
	LinkedList<Map<String,Object>> querySgbjImFeeList(String datefrom,String dateto,List<String>mzlb_list);
	LinkedList<Map<String,Object>> queryTsyxFeeList(String datefrom,String dateto,List<String>mzlb_list);
	LinkedList<Map<String,Object>> queryYdImFeeList(String datefrom,String dateto,List<String>mzlb_list);
	LinkedList<Map<String,Object>> queryYdOpFeeList(String datefrom,String dateto,List<String>mzlb_list);
	LinkedList<Map<String,Object>>  getBS();
	//普通1
	List<Map<String,Object>> queryYbCityNormalListire(String daa,String datefrom,String dateto,String mzlb);
    //普通大病
    List<Map<String,Object>> queryYbDbCityNormalListire(String daa,String datefrom,String dateto,String mzlb);
    //普通住院
    List<Map<String,Object>> queryYbZyCityNormalListire(String daa,String datefrom,String dateto,String mzlb);
    //城镇特殊
    List<Map<String,Object>> queryYbTsCityNormalListire(String daa,String datefrom,String dateto,String mzlb);
	List<Map<String,Object>> queryYbTszyCityNormalListire(String daa,String datefrom,String dateto,String mzlb);
    //城镇特殊
    List<Map<String,Object>> queryYbJmCityNormalListire(String daa,String datefrom,String dateto,String mzlb);
    //城镇特殊
    List<Map<String,Object>> queryYbJmzyCityNormalListire(String daa,String datefrom,String dateto,String mzlb);
    //城镇特殊
    List<Map<String,Object>> queryYbGsbxopListire(String daa,String datefrom,String dateto,String mzlb);
    //城镇特殊
    List<Map<String,Object>> queryYbYlhzbkListire(String daa,String datefrom,String dateto,String mzlb);
    //城镇特殊
    List<Map<String,Object>> queryYbPkjsbListire(String daa,String datefrom,String dateto,String mzlb,int pageno);
    //城镇特殊
    List<Map<String,Object>> queryYbMzbkListire(String daa,String datefrom,String dateto,String mzlb);
    //城镇特殊
    List<Map<String,Object>> queryYbYdybListire(String daa,String datefrom,String dateto,String mzlb);
    //城镇特殊
    List<Map<String,Object>> queryYbYdybopListire(String daa,String datefrom,String dateto,String mzlb);


	List<Map<String,Object>> getYbjgdm(String mzlb);

	List<ShFeehzEhbjbResp> getJbFeeEhb(String datefrom,String dateto,List<String>mzlb_list);

	List<ShFeehzEhbhzbkResp> getHzbkFeeEhb(String datefrom,String dateto,List<String>mzlb_list);
}
