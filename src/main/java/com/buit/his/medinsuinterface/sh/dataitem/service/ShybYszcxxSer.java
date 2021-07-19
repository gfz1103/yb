package com.buit.his.medinsuinterface.sh.dataitem.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buit.commons.BaseException;
import com.buit.commons.BaseManagerImp;
import com.buit.constans.TableName;
import com.buit.his.medinsuinterface.sh.dataitem.dao.ShybYszcxxDao;
import com.buit.his.medinsuinterface.sh.dataitem.excelmodel.ShybYszcxxExcelModel;
import com.buit.his.medinsuinterface.sh.dataitem.model.ShybYszcxx;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYszcxxAddReq;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYszcxxEditReq;
import com.buit.commons.SysUser;
import com.buit.utill.ChineseCharacterUtil;
import com.buit.utill.DateUtil;
import com.buit.utill.RedisFactory;
/**
 * 上海医保医师注册信息<br>
 * @author 老花生
 */
@Service
public class ShybYszcxxSer extends BaseManagerImp<ShybYszcxx,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(ShybYszcxxSer.class);
    
    @Autowired
    private ShybYszcxxDao shybYszcxxDao;
    
    @Autowired
    private RedisFactory redisFactory;

    @Override
    public ShybYszcxxDao getEntityMapper(){        
        return shybYszcxxDao;
    }
    
    public void add(ShybYszcxxAddReq req, SysUser user) {
		checkCode(null,req.getDoctorYbCode());

    	ShybYszcxx data = new ShybYszcxx();
    	BeanUtils.copyProperties(req, data);
    	
    	data.setYbdoctordataId(redisFactory.getTableKey(TableName.DB_NAME, TableName.SHYB_YSZCXX));
    	data.setGmtCreate(DateUtil.getCurrentTimestamp());
    	data.setHospitalId(user.getHospitalId());
		ChineseCharacterUtil.setPyAndWb(data,data.getDoctorName());
    	shybYszcxxDao.insert(data);
    }
    
    public void edit(ShybYszcxxEditReq req) {
		logger.info("==================YbdoctordataId:"+req.getYbdoctordataId());
		checkCode(req.getYbdoctordataId(),req.getDoctorYbCode());
		ShybYszcxx data = shybYszcxxDao.getById(req.getYbdoctordataId());
    	if(data == null) {
    		throw BaseException.create("ERROR_SHYB_0003");
    	}
    	
    	BeanUtils.copyProperties(req, data);
    	
    	data.setGmtModify(DateUtil.getCurrentTimestamp());
		ChineseCharacterUtil.setPyAndWb(data,data.getDoctorName());
    	shybYszcxxDao.update(data);
    }


    private void checkCode(Integer id,String code){
		ShybYszcxx data = new ShybYszcxx();
		data.setYbdoctordataId(id);
		data.setDoctorYbCode(code);
		Integer count = shybYszcxxDao.checkCode(data);
		if(count > 0){
			throw BaseException.create("ERROR_SHYB_0014");
		}
	}
    
    public void impExcel(List<ShybYszcxxExcelModel> list, SysUser user) {
    	
    	ShybYszcxx data;
    	
    	for(ShybYszcxxExcelModel model : list) {
    		data = new ShybYszcxx();
    		
    		//开始插入
    		BeanUtils.copyProperties(model, data);
    		
    		data.setYbdoctordataId(redisFactory.getTableKey(TableName.DB_NAME, TableName.SHYB_YSZCXX));
    		data.setGmtCreate(DateUtil.getCurrentTimestamp());
    		data.setGmtModify(DateUtil.getCurrentTimestamp());
    		data.setHospitalId(user.getHospitalId());
    		
    		Integer ret = shybYszcxxDao.updateByYbCode(data);
    		if(ret == 0) {
    			shybYszcxxDao.insert(data);
    		}
    	}
    	
    }
    
    @Transactional(rollbackFor=Exception.class)
    public void lock(Integer ybdoctordataId) {
    	ShybYszcxx data = validDoctorId(ybdoctordataId);
    	data.setLockFlag("1");
    	shybYszcxxDao.updateLockFlag(data);
		//根据新代码更换
    	//ehrServicePersonalSer.lockByDoctorCode(data.getDoctorYbCode());
    }
    
    @Transactional(rollbackFor=Exception.class)
    public void unlock(Integer ybdoctordataId) {
    	ShybYszcxx data = validDoctorId(ybdoctordataId);
    	data.setLockFlag("0");
    	shybYszcxxDao.updateLockFlag(data);
    	//根据新代码更换
    	//ehrServicePersonalSer.unlockByDoctorCode(data.getDoctorYbCode());
    }

	/**
	 * 获取医生信息
	 * @param doctorId 医师ID
	 * @return 医师对象
	 */
	private ShybYszcxx validDoctorId(Integer doctorId) {
    	ShybYszcxx data = shybYszcxxDao.getById(doctorId);
    	if(data == null) {
    		throw BaseException.create("ERROR_SHYB_0003");
    	}
    	return data;
    }
}
