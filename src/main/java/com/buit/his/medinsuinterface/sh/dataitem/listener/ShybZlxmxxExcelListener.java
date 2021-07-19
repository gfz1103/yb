package com.buit.his.medinsuinterface.sh.dataitem.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.buit.his.medinsuinterface.sh.dataitem.excelmodel.ShybZlxmxxExcelModel;
import com.buit.his.medinsuinterface.sh.dataitem.service.ShybZlxmxxSer;
import com.buit.commons.SysUser;

import java.util.ArrayList;
import java.util.List;

/**
 * 上海医保医疗器材excel读取类
 *
 * @author 
 */
public class ShybZlxmxxExcelListener extends AnalysisEventListener<ShybZlxmxxExcelModel> {
    
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 1000;
    List<ShybZlxmxxExcelModel> list = new ArrayList<ShybZlxmxxExcelModel>();
    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    private ShybZlxmxxSer shybZlxmxxSer;
    private SysUser user;

    public ShybZlxmxxExcelListener() {
    	
    }

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param uploadDAO
     */
    public ShybZlxmxxExcelListener(ShybZlxmxxSer shybZlxmxxSer, SysUser user) {
        this.shybZlxmxxSer = shybZlxmxxSer;
        this.user = user;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(ShybZlxmxxExcelModel data, AnalysisContext context) {
        list.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
    	shybZlxmxxSer.impExcel(list, user);
    }
}
