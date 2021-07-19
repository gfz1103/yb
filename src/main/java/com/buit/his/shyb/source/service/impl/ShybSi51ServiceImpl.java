package com.buit.his.shyb.source.service.impl;

import com.buit.his.shyb.source.dao.ShybSi51Dao;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 上海医保住院试算<br>
 * @author Beijunhua
 */
@DubboService
public class ShybSi51ServiceImpl implements ShybSi51Service{
    @Autowired
    private ShybSi51Dao shybSi51Dao;

    @Override
    public long countJs(Integer integer, int i) {
        return shybSi51Dao.countJs(integer,i);
    }

    @Override
    public List<Map<String, Object>> getMxzdh(String s, String s1, String s2,String s3) {
        return shybSi51Dao.getMxzdh(s,s1,s2,s3);
    }

    @Override
    public List<Map<String, Object>> getScmxList(String s, String s1 ,String s2,String s3) {
        return shybSi51Dao.getScmxList(s,s1,s2,s3);
    }

    @Override
    public List<Map<String, Object>> getFymxList(String s, String s1, String s2,String s3) {
        return shybSi51Dao.getFymxList(s,s1,s2,s3);
    }

    @Override
    public List<Map<String, Object>> getJbmc(String s) {
        return shybSi51Dao.getJbmc(s);
    }

    @Override
    public long countisJs(String s, Integer integer) {
        return shybSi51Dao.countisJs(s,integer);
    }

    @Override
    public void insertYbsi51(Map<String, Object> map) {

    }

    @Override
    public void insertYb52(Map<String, Object> map) {

    }

    @Override
    public void updateImfymxbz(String s, String s1, String s2) {

    }

    @Override
    public void updateJscs52(String s, String s1) {

    }

    @Override
    public void updateYbsi51Zf(String s, String s1) {

    }
}
