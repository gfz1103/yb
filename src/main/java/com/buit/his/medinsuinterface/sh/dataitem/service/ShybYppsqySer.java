package com.buit.his.medinsuinterface.sh.dataitem.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.buit.commons.BaseException;
import com.buit.commons.BaseManagerImp;
import com.buit.constans.TableName;
import com.buit.his.enums.ShybFileNameEnum;
import com.buit.his.medinsuinterface.sh.dataitem.dao.ShybYppsqyDao;
import com.buit.his.medinsuinterface.sh.dataitem.model.ShybYppsqy;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYppsqyAddReq;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYppsqyEditReq;
import com.buit.commons.SysUser;
import com.buit.utill.ChineseCharacterUtil;
import com.buit.utill.DateUtil;
import com.buit.utill.ReadTxtFileUtil;
import com.buit.utill.RedisFactory;
/**
 * 药品配送企业表<br>
 * @author 老花生
 */
@Service
public class ShybYppsqySer extends BaseManagerImp<ShybYppsqy,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(ShybYppsqySer.class);
    
    @Autowired
    private ShybYppsqyDao shybYppsqyDao;
    
    @Autowired
    private RedisFactory redisFactory;
    
    @Override
    public ShybYppsqyDao getEntityMapper(){        
        return shybYppsqyDao;
    }
    
    public void add(ShybYppsqyAddReq req, SysUser user) {
    	ShybYppsqy data = new ShybYppsqy();
    	data.setTbdm(req.getTbdm());
		checktbdm(null,req.getTbdm());
    	if(shybYppsqyDao.findByEntityCount(data) > 0 ) {
    		throw BaseException.create("ERROR_SHYB_0004");
    	}
    	
    	BeanUtils.copyProperties(req, data);
    	
    	data.setYppsqydataId(redisFactory.getTableKey(TableName.DB_NAME, TableName.SHYB_YPPSQY));
    	data.setGmtCreate(DateUtil.getCurrentTimestamp());
    	data.setHospitalId(user.getHospitalId());
		ChineseCharacterUtil.setPyAndWb(data,data.getPsqymc());
    	shybYppsqyDao.insert(data);
    }
    
    public void edit(ShybYppsqyEditReq req) {
		checktbdm(req.getYppsqydataId(),req.getTbdm());
    	ShybYppsqy data = shybYppsqyDao.getById(req.getYppsqydataId());
    	if(data == null) {
    		throw BaseException.create("ERROR_SHYB_0007");
    	}
    	
    	BeanUtils.copyProperties(req, data);
    	
    	data.setGmtModify(DateUtil.getCurrentTimestamp());
		ChineseCharacterUtil.setPyAndWb(data,data.getPsqymc());
    	shybYppsqyDao.update(data);
    }

	/**
	 * 判断统编代码是否重复
	 * @param tbdm
	 */
	private void checktbdm(Integer id,String tbdm){
		ShybYppsqy ypjggz = new ShybYppsqy();
		ypjggz.setYppsqydataId(id);
		ypjggz.setTbdm(tbdm);
		Long ret = shybYppsqyDao.getBytBdm(ypjggz);
		if(ret != null && ret.intValue() > 0) {
			throw BaseException.create("ERROR_SHYB_0004");
		}
	}


    public void impExcel(MultipartFile file, SysUser user) {
    	if(file.getOriginalFilename().indexOf(ShybFileNameEnum.YPPSQY.getCode()) < 0) {
    		throw BaseException.create("ERROR_SHYB_0012");
    	}
    	
    	Integer[] widths = new Integer[] {15, 8, 1, 15, 80, 100, 1, 100, 8, 8, 100};
		
    	List<List<String>> ret = ReadTxtFileUtil.readTxt(file, widths);
    	
    	for(List<String> obj : ret) {
    		ShybYppsqy data = new ShybYppsqy();
    		data.setTbdm(obj.get(0).trim());
    		data.setFbrq(obj.get(1).trim());
    		data.setFblx(obj.get(2).trim());
    		data.setPsqydm(obj.get(3).trim());
    		data.setZbqs(obj.get(4).trim());
    		data.setPsqymc(obj.get(5).trim());
    		data.setPszyyq(obj.get(6).trim());
    		data.setPsfw(obj.get(7).trim());
    		data.setQyrq(obj.get(8).trim());
    		data.setYxrq(obj.get(9).trim());
    		data.setBgzd(obj.get(10).trim());
    		
    		
    		//如果更新成功则不新增
    		if(shybYppsqyDao.updateByTbdm(data) == 0) {
    			ShybYppsqyAddReq req = new ShybYppsqyAddReq();
    			BeanUtils.copyProperties(data, req);
    			add(req, user);
    		}
    	}
    }
}
