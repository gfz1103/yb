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
import com.buit.his.medinsuinterface.sh.dataitem.dao.ShybZlxmxxDao;
import com.buit.his.medinsuinterface.sh.dataitem.excelmodel.ShybZlxmxxExcelModel;
import com.buit.his.medinsuinterface.sh.dataitem.model.ShybZlxmxx;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybZlxmxxAddReq;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybZlxmxxEditReq;
import com.buit.commons.SysUser;
import com.buit.utill.ChineseCharacterUtil;
import com.buit.utill.DateUtil;
import com.buit.utill.RedisFactory;

import cn.hutool.core.convert.Convert;
/**
 * 上海医保诊疗项目信息<br>
 * @author 老花生
 */
@Service
public class ShybZlxmxxSer extends BaseManagerImp<ShybZlxmxx,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(ShybZlxmxxSer.class);
    
    @Autowired
    private ShybZlxmxxDao shybZlxmxxDao;
    
    @Autowired
    private RedisFactory redisFactory;
    
    @Override
    public ShybZlxmxxDao getEntityMapper(){        
        return shybZlxmxxDao;
    }
    
    public void add(ShybZlxmxxAddReq req, SysUser user) {
    	ShybZlxmxx data = new ShybZlxmxx();
    	BeanUtils.copyProperties(req, data);
    	
    	data.setYbitemdataId(redisFactory.getTableKey(TableName.DB_NAME, TableName.SHYB_ZLXMXX));
    	data.setGmtCreate(DateUtil.getCurrentTimestamp());
    	data.setHospitalId(user.getHospitalId());
		ChineseCharacterUtil.setPyAndWb(data,data.getItemName());
    	shybZlxmxxDao.insert(data);
    }
    
    public void edit(ShybZlxmxxEditReq req) {
    	
    	ShybZlxmxx data = shybZlxmxxDao.getById(req.getYbitemdataId());
    	if(data == null) {
    		throw BaseException.create("ERROR_SHYB_0002");
    	}
    	
    	BeanUtils.copyProperties(req, data);
    	
    	data.setGmtModify(DateUtil.getCurrentTimestamp());
		ChineseCharacterUtil.setPyAndWb(data,data.getItemName());
    	shybZlxmxxDao.update(data);
    }

    public void impExcel(List<ShybZlxmxxExcelModel> list, SysUser user) {
    	
    	ShybZlxmxx data = null;
    	
    	for(ShybZlxmxxExcelModel model : list) {
    		data = new ShybZlxmxx();
    		
    		//开始插入
    		BeanUtils.copyProperties(model, data);
    		
    		data.setYbitemdataId(redisFactory.getTableKey(TableName.DB_NAME, TableName.SHYB_ZLXMXX));
    		data.setGmtCreate(DateUtil.getCurrentTimestamp());
    		data.setGmtModify(DateUtil.getCurrentTimestamp());
    		data.setHospitalId(user.getHospitalId());
    		
    		data.setPrice(Convert.toDouble(model.getPrice(), null));
    		
    		Integer ret = shybZlxmxxDao.updateByYbCode(data);
    		if(ret == 0) {
    			shybZlxmxxDao.insert(data);
    		}
    	}
    	
    }
    
}
