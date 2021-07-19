package com.buit.his.medinsuinterface.sh.dataitem.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buit.commons.BaseManagerImp;
import com.buit.his.medinsuinterface.sh.dataitem.dao.ShybData01Dao;
import com.buit.his.medinsuinterface.sh.dataitem.model.ShybData01;
/**
 * 医保数据主表<br>
 * @author 老花生
 */
@Service
public class ShybData01Ser extends BaseManagerImp<ShybData01,Integer> {
    
    static final Logger logger = LoggerFactory.getLogger(ShybData01Ser.class);
    
    @Autowired
    private ShybData01Dao shybData01Dao;
    
    @Override
    public ShybData01Dao getEntityMapper(){        
        return shybData01Dao;
    }
    
}
