package com.buit.his.medinsuinterface.sh.dataitem.service;


import com.alibaba.excel.util.CollectionUtils;
import com.buit.commons.BaseException;
import com.buit.commons.BaseManagerImp;
import com.buit.constans.TableName;
import com.buit.his.enums.ShybFileNameEnum;
import com.buit.his.medinsuinterface.sh.dataitem.dao.ShybYpjcxxDao;
import com.buit.his.medinsuinterface.sh.dataitem.model.ShybYpjcxx;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYpjcxxAddReq;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybYpjcxxEditReq;
import com.buit.commons.SysUser;
import com.buit.utill.*;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 药品品规基础信息表<br>
 *
 * @author 老花生
 */
@Service
public class ShybYpjcxxSer extends BaseManagerImp<ShybYpjcxx, Integer> {

    static final Logger logger = LoggerFactory.getLogger(ShybYpjcxxSer.class);
    public static AtomicInteger success = new AtomicInteger(0);
    public static AtomicInteger error = new AtomicInteger(0);
    @Resource(name = "executorTask")
    private ThreadPoolTaskExecutor executor;

    @Autowired
    private ShybYpjcxxDao shybYpjcxxDao;

    @Autowired
    private RedisFactory redisFactory;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public ShybYpjcxxDao getEntityMapper() {
        return shybYpjcxxDao;
    }

    public void add(ShybYpjcxxAddReq req, SysUser user) {
        checktbdm(null, req.getTbdm());

        ShybYpjcxx data = new ShybYpjcxx();
        BeanUtils.copyProperties(req, data);

        data.setYpjcxxdataId(redisFactory.getTableKey(TableName.DB_NAME, TableName.SHYB_YPJCXX));
        data.setGmtCreate(DateUtil.getCurrentTimestamp());
        data.setHospitalId(user.getHospitalId());
        data.setDataVersion(1);
        data.setPyCode(PinyinUtils.getSimplePinYin(data.getYptym()));
        data.setWbCode(WubiUtils.getSimpleWuBi(data.getYptym()));
        //ChineseCharacterUtil.setPyAndWb(data,data.getYptym());
        shybYpjcxxDao.insert(data);
    }
    public void addList(ShybYpjcxxAddReq req, SysUser user) {

        ShybYpjcxx data = new ShybYpjcxx();
        BeanUtils.copyProperties(req, data);

        data.setYpjcxxdataId(redisFactory.getTableKey(TableName.DB_NAME, TableName.SHYB_YPJCXX));
        data.setGmtCreate(DateUtil.getCurrentTimestamp());
        data.setHospitalId(user.getHospitalId());
        data.setDataVersion(1);
        data.setPyCode(PinyinUtils.getSimplePinYin(data.getYptym()));
        data.setWbCode(WubiUtils.getSimpleWuBi(data.getYptym()));
        //ChineseCharacterUtil.setPyAndWb(data,data.getYptym());
        shybYpjcxxDao.insert(data);
    }


    public void edit(ShybYpjcxxEditReq req) {
        checktbdm(req.getYpjcxxdataId(), req.getTbdm());
        ShybYpjcxx data = shybYpjcxxDao.getById(req.getYpjcxxdataId());
        if (data == null) {
            throw BaseException.create("ERROR_SHYB_0003");
        }

        BeanUtils.copyProperties(req, data);

        data.setGmtModify(DateUtil.getCurrentTimestamp());
        ChineseCharacterUtil.setPyAndWb(data, data.getYptym());
        shybYpjcxxDao.update(data);
    }

    @Async(value = "executorTask")
    public Future<String> insertXy(SysUser user, List<List<String>> ret) {
        Future<String> result = new AsyncResult<String>("");
        for (List<String> obj : ret) {
            ShybYpjcxx data = new ShybYpjcxx();
            data.setTbdm(obj.get(0).trim()); //15
            data.setFbrq(obj.get(1).trim());//8
            data.setFblx(obj.get(2).trim());//1
            data.setPzwh(obj.get(3).trim());//20
            data.setBwm(obj.get(4).trim());//20
            data.setYptym(obj.get(5).trim());//80
            data.setYwm(obj.get(6).trim());//80
            data.setJx(obj.get(7).trim());//20
            data.setGg(obj.get(8).trim());//80
            data.setScqy(obj.get(9).trim());//100
            data.setSpm(obj.get(10).trim());//80
            data.setBzdw(obj.get(11).trim());//20
            if (StringUtils.isNotBlank(obj.get(12))) {//16
                data.setBzsl(Double.valueOf(obj.get(12).trim()));
            }
            data.setBzcz(obj.get(13).trim());//40
            data.setBzfs(obj.get(14).trim());//40
            data.setGgbzwzbs(obj.get(15).trim());//300
            if (obj.get(16).length() > 4) {
                data.setYpsx(String.valueOf(obj.get(16).trim().charAt(3)));//32
            }
            //data.setYpsx(String.valueOf(obj.get(16).trim().charAt(3)));//32
            data.setCkfl(obj.get(17).trim());//100
            data.setBzxx(obj.get(18).trim());//200
            data.setQyrq(obj.get(19).trim());//8
            data.setYxrq(obj.get(20).trim());//8
            data.setBgzd(obj.get(21).trim());//100
            data.setGmtCreate(DateUtil.getCurrentTimestamp());
            data.setHospitalId(user.getHospitalId());
            data.setDataVersion(1);
            //如果更新成功则不新增
            if (shybYpjcxxDao.updateByTbdm(data) == 0) {
                ShybYpjcxxAddReq req = new ShybYpjcxxAddReq();
                BeanUtils.copyProperties(data, req);
                addList(req, user);
                success.incrementAndGet();
            }
        }
        return result;
    }

    @Async(value = "executorTask")
    public Future<String> insertZy(SysUser user, List<List<String>> ret) {
        Future<String> result = new AsyncResult<String>("");
        for (List<String> obj : ret) {
            ShybYpjcxx data = new ShybYpjcxx();
            data.setTbdm(obj.get(0).trim()); //15
            data.setFbrq(obj.get(1).trim());//8
            data.setFblx(obj.get(2).trim());//1
            data.setYptym(obj.get(3).trim());//20
            data.setYwm(obj.get(4).trim());//20
            data.setPzwh(obj.get(5).trim());//80
            data.setJx(obj.get(6).trim());//80
            data.setScqy(obj.get(7).trim());//20
            data.setBzdw(obj.get(8).trim());//包装单位
            data.setGg(obj.get(12).trim());//80
            data.setSpm(obj.get(3).trim());//80
//                 data.setBzdw(obj.get(11).trim());//20
//                if (StringUtils.isNotBlank(obj.get(12))) {//16
//                    data.setBzsl(Double.valueOf(obj.get(12).trim()));
//                }
//                    data.setBzcz(obj.get(13).trim());//40
//                data.setBzfs(obj.get(14).trim());//40
//                data.setGgbzwzbs(obj.get(15).trim());//300
//                if (obj.get(16).length() > 4) {
//                    data.setYpsx(String.valueOf(obj.get(16).trim().charAt(3)));//32
//                }
//                data.setYpsx(String.valueOf(obj.get(16).trim().charAt(3)));//32
//                data.setCkfl(obj.get(17).trim());//100
//                data.setBzxx(obj.get(18).trim());//200
            data.setQyrq(obj.get(13).trim());//8
            data.setYxrq(obj.get(14).trim());//8
//                data.setBgzd(obj.get(21).trim());//100
            data.setGmtCreate(DateUtil.getCurrentTimestamp());
            data.setHospitalId(user.getHospitalId());
            data.setDataVersion(1);
            //如果更新成功则不新增
            if (shybYpjcxxDao.updateByTbdm(data) == 0) {
                ShybYpjcxxAddReq req = new ShybYpjcxxAddReq();
                BeanUtils.copyProperties(data, req);
                add(req, user);
            }
        }
        return result;
    }


    public void impExcel(MultipartFile file, SysUser user, Integer lx) throws InterruptedException, ExecutionException {
        if (file.getOriginalFilename().indexOf(ShybFileNameEnum.YPJCXX.getCode()) < 0) {
            throw BaseException.create("ERROR_SHYB_0012");
        }
        Integer[] widths = null;
        List<List<String>> list = null;
        List<Future<?>> futureList = new ArrayList<Future<?>>();
        if (lx == 1) {
            long start = System.currentTimeMillis();
            widths = new Integer[]{15, 8, 1, 20, 20, 80, 80, 20, 80, 100, 80, 20, 16, 40, 40, 300, 32, 100, 200, 8, 8, 100};
            System.err.println("执行任务消耗了 ：" + (System.currentTimeMillis() - start) + "毫秒");
            list = ReadTxtFileUtil.readTxt(file, widths);
            System.out.println(list.size());
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
                        insertXy(user, lists);
                    });
                    //每段数据集合并行入库
                    futureList.add(future);
                }
            }

        } else {
            widths = new Integer[]{15, 8, 1, 160, 40, 40, 40, 100, 20, 56, 40, 32, 40, 8, 8};
            list = ReadTxtFileUtil.readTxt(file, widths);
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
                        insertZy(user, lists);
                    });
                    //每段数据集合并行入库
                    futureList.add(future);
                }
            }

        }


    }

    /**
     * 判断统编代码是否重复
     *
     * @param tbdm
     */
    private void checktbdm(Integer id, String tbdm) {
        ShybYpjcxx shybYpjcxx = new ShybYpjcxx();
        shybYpjcxx.setYpjcxxdataId(id);
        shybYpjcxx.setTbdm(tbdm);
        Long ret = shybYpjcxxDao.getBytBdm(shybYpjcxx);

        if (ret != null && ret.intValue() > 0) {
            throw BaseException.create("ERROR_SHYB_0004");
        }
    }



}
