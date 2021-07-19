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
import com.buit.his.medinsuinterface.sh.dataitem.dao.ShybYpjggzDao;
import com.buit.his.medinsuinterface.sh.dataitem.model.ShybYpjggz;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYpjggzAddReq;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYpjggzEditReq;
import com.buit.commons.SysUser;
import com.buit.utill.ChineseCharacterUtil;
import com.buit.utill.DateUtil;
import com.buit.utill.ReadTxtFileUtil;
import com.buit.utill.RedisFactory;

import javax.annotation.Resource;

/**
 * 药品价格规则表<br>
 * @author 老花生
 */
@Service
public class ShybYpjggzSer extends BaseManagerImp<ShybYpjggz,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(ShybYpjggzSer.class);
    
    @Autowired
    private ShybYpjggzDao shybYpjggzDao;
    
    @Autowired
    private RedisFactory redisFactory;
	public static AtomicInteger success = new AtomicInteger(0);
	public static AtomicInteger error = new AtomicInteger(0);
	@Resource(name = "executorTask")
	private ThreadPoolTaskExecutor executor;
    
    @Override
    public ShybYpjggzDao getEntityMapper(){        
        return shybYpjggzDao;
    }
    
    public void add(ShybYpjggzAddReq req, SysUser user) {
    	checktbdm(null,req.getTbdm());
    	
    	ShybYpjggz data = new ShybYpjggz();
    	BeanUtils.copyProperties(req, data);
    	
    	data.setYpjggzdataId(redisFactory.getTableKey(TableName.DB_NAME, TableName.SHYB_YPJGGZ));
    	data.setGmtCreate(DateUtil.getCurrentTimestamp());
    	data.setHospitalId(user.getHospitalId());
		ChineseCharacterUtil.setPyAndWb(data,data.getTbdm());
    	shybYpjggzDao.insert(data);
    }
    
    public void edit(ShybYpjggzEditReq req) {
		checktbdm(req.getYpjggzdataId(),req.getTbdm());
    	ShybYpjggz data = shybYpjggzDao.getById(req.getYpjggzdataId());
    	if(data == null) {
    		throw BaseException.create("ERROR_SHYB_0004");
    	}
    	
    	BeanUtils.copyProperties(req, data);
    	
    	data.setGmtModify(DateUtil.getCurrentTimestamp());
		ChineseCharacterUtil.setPyAndWb(data,data.getTbdm());
    	shybYpjggzDao.update(data);
    }

	public static void main(String[] args) {
		String str="克                  ";
		String s1="5";
		System.out.println(s1.length());
		System.out.println(str.length());
	}
    
    public void impExcel(MultipartFile file, SysUser user,Integer lx) {
    	if(file.getOriginalFilename().indexOf(ShybFileNameEnum.YPJGGZ.getCode()) < 0) {
    		throw BaseException.create("ERROR_SHYB_0012");
    	}
		Integer[] widths;
    	if(lx==1){
    		//类型等于1是西药
			widths= new Integer[] {15, 8, 1, 80, 1, 80, 16, 1, 80, 16, 500, 8, 8, 100};
		}else{
    		//中药
			widths= new Integer[] {15, 8, 1, 19, 2, 80, 16, 1, 80, 16, 500, 8, 8, 100};
		}
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
				//每段数据集合并行入库
				Future<?> future = executor.submit(() -> {
					insertJggz(user, lists);
				});
				//每段数据集合执行插入操作
				futureList.add(future);
			}
		}
    }

	@Async(value = "executorTask")
	public Future<String> insertJggz(SysUser user, List<List<String>> ret) {
		Future<String> result = new AsyncResult<String>("");
		for(List<String> obj : ret) {
			ShybYpjggz data = new ShybYpjggz();
			data.setTbdm(obj.get(0).trim());
			data.setFbrq(obj.get(1).trim());
			data.setFblx(obj.get(2).trim());
			data.setJjdw(obj.get(3).trim());
			data.setJhjggzdm(obj.get(4).trim());
			data.setJhjggzsm(obj.get(5).trim());
			if(StringUtils.isNotBlank(obj.get(6))) {
				data.setJhgzjgje(Double.valueOf(obj.get(6).trim()));
			}
			data.setXsjggzdm(obj.get(7).trim());
			data.setXsjggzsm(obj.get(8).trim());
			if(StringUtils.isNotBlank(obj.get(9))) {
				data.setXsgzjgje(Double.valueOf(obj.get(9).trim()));
			}
			data.setGzsyfw(obj.get(10).trim());
			data.setQyrq(obj.get(11).trim());
			data.setYxrq(obj.get(12).trim());
			data.setBgzd(obj.get(13).trim());
			data.setGmtModify(DateUtil.getCurrentTimestamp());

			//如果更新成功则不新增
			if(shybYpjggzDao.updateByTbdm(data) == 0) {
				ShybYpjggzAddReq req = new ShybYpjggzAddReq();
				BeanUtils.copyProperties(data, req);
				add(req, user);
			}
		}
		return result;
	}
    
    /**
     * 判断统编代码是否重复
     * @param tbdm
     */
    private void checktbdm(Integer id,String tbdm){
		ShybYpjggz ypjggz = new ShybYpjggz();
    	ypjggz.setYpjggzdataId(id);
    	ypjggz.setTbdm(tbdm);
    	Long ret = shybYpjggzDao.getBytBdm(ypjggz);
    	if(ret != null && ret.intValue() > 0) {
    		throw BaseException.create("ERROR_SHYB_0004");
    	}
    }
    
}
