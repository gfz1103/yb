package com.buit.his.medinsuinterface.sh.dataitem.service;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import com.alibaba.excel.util.CollectionUtils;
import com.buit.his.medinsuinterface.sh.dataitem.model.ShybYbblgz;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYbblgzAddReq;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.buit.commons.BaseException;
import com.buit.commons.BaseManagerImp;
import com.buit.constans.TableName;
import com.buit.his.enums.ShybFileNameEnum;
import com.buit.his.medinsuinterface.sh.dataitem.dao.ShybYbkbxgzDao;
import com.buit.his.medinsuinterface.sh.dataitem.model.ShybYbkbxgz;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYbkbxgzAddReq;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYbkbxgzEditReq;
import com.buit.commons.SysUser;
import com.buit.utill.ChineseCharacterUtil;
import com.buit.utill.DateUtil;
import com.buit.utill.ReadTxtFileUtil;
import com.buit.utill.RedisFactory;

import javax.annotation.Resource;

/**
 * 药品医保可报销条件规则表<br>
 * @author 老花生
 */
@Service
public class ShybYbkbxgzSer extends BaseManagerImp<ShybYbkbxgz,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(ShybYbkbxgzSer.class);
    
    @Autowired
    private ShybYbkbxgzDao shybYbkbxgzDao;
    
    @Autowired
    private RedisFactory redisFactory;
	public static AtomicInteger success = new AtomicInteger(0);
	public static AtomicInteger error = new AtomicInteger(0);
	@Resource(name = "executorTask")
	private ThreadPoolTaskExecutor executor;
    
    @Override
    public ShybYbkbxgzDao getEntityMapper(){        
        return shybYbkbxgzDao;
    }
    
    public void add(ShybYbkbxgzAddReq req, SysUser user) {
    	ShybYbkbxgz data = new ShybYbkbxgz();
    	data.setTbdm(req.getTbdm());
		checktbdm(null,req.getTbdm());
    	if(shybYbkbxgzDao.findByEntityCount(data) > 0 ) {
    		throw BaseException.create("ERROR_SHYB_0004");
    	}
    	
    	BeanUtils.copyProperties(req, data);
    	
    	data.setYbkbxgzdataId(redisFactory.getTableKey(TableName.DB_NAME, TableName.SHYB_YBKBXGZ));
    	data.setGmtCreate(DateUtil.getCurrentTimestamp());
    	data.setHospitalId(user.getHospitalId());
		ChineseCharacterUtil.setPyAndWb(data,data.getTbdm());
    	shybYbkbxgzDao.insert(data);
    }
    public void addList(ShybYbkbxgzAddReq req, SysUser user) {
    	ShybYbkbxgz data = new ShybYbkbxgz();
    	data.setTbdm(req.getTbdm());
    	if(shybYbkbxgzDao.findByEntityCount(data) > 0 ) {
    		throw BaseException.create("ERROR_SHYB_0004");
    	}

    	BeanUtils.copyProperties(req, data);

    	data.setYbkbxgzdataId(redisFactory.getTableKey(TableName.DB_NAME, TableName.SHYB_YBKBXGZ));
    	data.setGmtCreate(DateUtil.getCurrentTimestamp());
    	data.setHospitalId(user.getHospitalId());
		ChineseCharacterUtil.setPyAndWb(data,data.getTbdm());
    	shybYbkbxgzDao.insert(data);
    }

    public void edit(ShybYbkbxgzEditReq req) {
		checktbdm(req.getYbkbxgzdataId(),req.getTbdm());
    	ShybYbkbxgz data = shybYbkbxgzDao.getById(req.getYbkbxgzdataId());
    	if(data == null) {
    		throw BaseException.create("ERROR_SHYB_0008");
    	}
    	
    	BeanUtils.copyProperties(req, data);
    	
    	data.setGmtModify(DateUtil.getCurrentTimestamp());
		ChineseCharacterUtil.setPyAndWb(data,data.getTbdm());
    	shybYbkbxgzDao.update(data);
    }

	/**
	 * 判断统编代码是否重复
	 * @param tbdm
	 */
	private void checktbdm(Integer id,String tbdm){
		ShybYbkbxgz ypjggz = new ShybYbkbxgz();
		ypjggz.setYbkbxgzdataId(id);
		ypjggz.setTbdm(tbdm);
		Long ret = shybYbkbxgzDao.getBytBdm(ypjggz);
		if(ret != null && ret.intValue() > 0) {
			throw BaseException.create("ERROR_SHYB_0004");
		}
	}



    public void impExcel(MultipartFile file, SysUser user) {
    	if(file.getOriginalFilename().indexOf(ShybFileNameEnum.YBKBXGZ.getCode()) < 0) {
    		throw BaseException.create("ERROR_SHYB_0012");
    	}
    	
    	Integer[] widths = new Integer[] {15, 8, 1, 3, 500, 100, 8, 8, 100};
		List<Future<?>> futureList = new ArrayList<Future<?>>();

		List<List<String>> list = ReadTxtFileUtil.readTxt(file, widths);
		success = new AtomicInteger(0);
		error = new AtomicInteger(0);
		if (!CollectionUtils.isEmpty(list)) {
			int listSize = list.size();
			//将集合切分的段数(2*CPU的核心数)
			int threadSum = 2 * Runtime.getRuntime().availableProcessors();
			int listStart, listEnd;
			//当总条数不足threadSum条时 用总条数 当做线程切分值
			if (threadSum > listSize) {
				threadSum = listSize;
			}
			//将list 切分多份 多线程执行
			for (int i = 0; i < threadSum; i++) {
				//计算切割  开始和结束
				listStart = listSize / threadSum * i;
				listEnd = listSize / threadSum * (i + 1);
				//最后一段线程会 出现与其他线程不等的情况
				if (i == threadSum - 1) {
					listEnd = listSize;
				}
				//数据切断
				List<List<String>> lists = list.subList(listStart, listEnd);
				int j = i;
				//每段数据集合执行插入操作
				Future<?> future = executor.submit(() -> {
					insertKbxgz(user, lists);
				});
				//每段数据集合并行入库
				futureList.add(future);
			}
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


	}
	@Async(value = "executorTask")
	public Future<String> insertKbxgz(SysUser user, List<List<String>> ret) {
		Future<String> result = new AsyncResult<String>("");
		for(List<String> obj : ret) {
			ShybYbkbxgz data = new ShybYbkbxgz();
			data.setTbdm(obj.get(0).trim());
			data.setFbrq(obj.get(1).trim());
			data.setFblx(obj.get(2).trim());
			data.setBxtjdm(obj.get(3).trim());
			data.setBxtjnr(obj.get(4).trim());
			data.setGzly(obj.get(5).trim());
			data.setQyrq(obj.get(6).trim());
			data.setYxrq(obj.get(7).trim());
			data.setBgzd(obj.get(8).trim());
			data.setGmtCreate(DateUtil.getCurrentTimestamp());
			data.setHospitalId(user.getHospitalId());
			data.setDataVersion(1);



			//如果更新成功则不新增
			if(shybYbkbxgzDao.updateByTbdm(data) == 0) {
				ShybYbkbxgzAddReq req = new ShybYbkbxgzAddReq();
				BeanUtils.copyProperties(data, req);
				addList(req, user);
			}
		}
		return result;
	}
    
}
