package com.buit.his.medinsuinterface.sh.dataitem.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.buit.commons.BaseException;
import com.buit.commons.BaseManagerImp;
import com.buit.constans.TableName;
import com.buit.his.medinsuinterface.sh.dataitem.dao.ShybYlqcxxDao;
import com.buit.his.medinsuinterface.sh.dataitem.excelmodel.ShybYlqcxxExcelModel;
import com.buit.his.medinsuinterface.sh.dataitem.model.ShybYlqcxx;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYlqcxxAddReq;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYlqcxxEditReq;
import com.buit.commons.SysUser;
import com.buit.utill.ChineseCharacterUtil;
import com.buit.utill.DateUtil;
import com.buit.utill.RedisFactory;

/**
 * 上海医保医疗器材信息<br>
 * @author 老花生
 */
@Service
public class ShybYlqcxxSer extends BaseManagerImp<ShybYlqcxx,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(ShybYlqcxxSer.class);
    
    @Autowired
    private ShybYlqcxxDao shybYlqcxxDao;
    @Autowired
    private RedisFactory redisFactory;
    
    @Override
    public ShybYlqcxxDao getEntityMapper(){        
        return shybYlqcxxDao;
    }
    
    public void add(ShybYlqcxxAddReq req, SysUser user) {
    	ShybYlqcxx data = new ShybYlqcxx();
    	BeanUtils.copyProperties(req, data);
    	
    	data.setYbmaterialdataId(redisFactory.getTableKey(TableName.DB_NAME, TableName.SHYB_DATA01_VALUE));
    	data.setGmtCreate(DateUtil.getCurrentTimestamp());
    	data.setHospitalId(user.getHospitalId());
		ChineseCharacterUtil.setPyAndWb(data,data.getMaterialName());
    	shybYlqcxxDao.insert(data);
    }
    
    public void edit(ShybYlqcxxEditReq req) {
    	
    	ShybYlqcxx data = shybYlqcxxDao.getById(req.getYbmaterialdataId());
    	if(data == null) {
    		throw BaseException.create("ERROR_DIC_0041");
    	}
    	
    	BeanUtils.copyProperties(req, data);
    	
    	data.setGmtModify(DateUtil.getCurrentTimestamp());
		ChineseCharacterUtil.setPyAndWb(data,data.getMaterialName());
    	shybYlqcxxDao.update(data);
    }

    public void impExcel(List<ShybYlqcxxExcelModel> list, SysUser user) {
    	
    	ShybYlqcxx data = null;
    	
    	for(ShybYlqcxxExcelModel model : list) {
    		data = new ShybYlqcxx();
    		
    		//开始插入
    		BeanUtils.copyProperties(model, data);
    		
    		data.setYbmaterialdataId(redisFactory.getTableKey(TableName.DB_NAME, TableName.SHYB_YLQCXX));
    		data.setGmtCreate(DateUtil.getCurrentTimestamp());
    		data.setGmtModify(DateUtil.getCurrentTimestamp());
    		data.setHospitalId(user.getHospitalId());
    		
    		Integer ret = shybYlqcxxDao.updateObj(data);
    		if(ret == 0) {
    			shybYlqcxxDao.insert(data);
    		}
    	}
    	
    }
}
