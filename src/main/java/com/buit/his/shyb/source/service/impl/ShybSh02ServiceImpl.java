package com.buit.his.shyb.source.service.impl;

import com.buit.his.medinsuinterface.sh.dataitem.dao.ShybData02Dao;
import com.buit.his.shyb.source.model.ShybData02Resp;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 类名：ShybSh02ServiceImpl
 * 描述：医保02
 *
 * @author : liushijie
 * 2020/11/6
 **/
@DubboService(timeout = 10000)
public class ShybSh02ServiceImpl implements ShybSh02Service{

    @Autowired
    private ShybData02Dao shybData02Dao;

    @Override
    public List<ShybData02Resp> queryList() {
        return shybData02Dao.queryList();
    }
    @Override
    public List<ShybData02Resp> queryXsList() {
        return shybData02Dao.queryXsList();
    }
}
