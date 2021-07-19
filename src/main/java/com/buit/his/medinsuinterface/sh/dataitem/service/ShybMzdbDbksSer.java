package com.buit.his.medinsuinterface.sh.dataitem.service;


import com.buit.commons.BaseManagerImp;
import com.buit.constans.TableName;
import com.buit.his.medinsuinterface.sh.dataitem.dao.ShybMzdbDbksDao;
import com.buit.his.medinsuinterface.sh.dataitem.model.YbMzdbDbks;
import com.buit.his.medinsuinterface.sh.dataitem.request.SaveDbRegisterReq;
import com.buit.utill.RedisFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <br>
 * @author WY
 */
@Service
public class ShybMzdbDbksSer extends BaseManagerImp<YbMzdbDbks,String> {
    
    static final Logger logger = LoggerFactory.getLogger(ShybMzdbDbksSer.class);
    
    @Autowired
    private ShybMzdbDbksDao shybMzdbDbksDao;
    @Autowired
    RedisFactory redisFactory;
    
    @Override
    public ShybMzdbDbksDao getEntityMapper(){
        return shybMzdbDbksDao;
    }



    public void saveDbdjInfo( SaveDbRegisterReq req) {
        if("".equals(req.getSqdh())){
            req.setSqdh(redisFactory.getTableKey(TableName.DB_NAME, TableName.SHYB_MZDB_DJ));

        }
        shybMzdbDbksDao.saveDbdjInfo(req);
    }

    public void updateDbdjzt( String cardid,String dbxm) {
        shybMzdbDbksDao.updateDbdjzt(cardid,dbxm);
    }
}
