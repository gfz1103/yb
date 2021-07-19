package com.buit.his.gpo.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.buit.commons.BaseException;
import com.buit.constans.ErrorCode;
import com.buit.constans.TableName;
import com.buit.his.gpo.controller.request.GpoBaseRequest;
import com.buit.his.gpo.dao.GpoThdDao;
import com.buit.his.gpo.model.GpoThd;
import com.buit.his.gpo.ws.GpoConsts;
import com.buit.his.gpo.ws.GpoWebService;
import com.buit.his.gpo.ws.GpoZidian;
import com.buit.his.gpo.ws.request.ThdYY011ReqMain;
import com.buit.his.gpo.ws.request.ThdYY012ReqMain;
import com.buit.his.gpo.ws.response.ThdYY011RespMain;
import com.buit.his.gpo.ws.xml.Head;
import com.buit.his.gpo.ws.xml.XmlData;
import com.buit.utill.RedisFactory;
import com.buit.utill.TimestampUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Author sunjx
 * @Date 2020-11-17 14:48
 * @Description
 **/
@Service
public class ThdService {

    @Autowired
    private GpoThdDao thdDao;

    @Autowired
    private RedisFactory redisFactory;

    @Autowired
    private GpoWebService webService;

    public void save(GpoThd thd) {
        if(null == thd.getXh()){
            thd.setXh(redisFactory.getTableKey(TableName.DB_NAME, TableName.TB_NAME_GPO_THD));
            thd.setZt(GpoConsts.ZT.YX.getCode());
            //操作类型为新增
            thd.setCzlx("1");
            thd.setCbzt(GpoConsts.CBZT.DCB.getCode());
            thd.setZfzt(GpoConsts.ZFZT.YX.getCode());
            thd.setQrzt(GpoConsts.QRZT.DQR.getCode());
            thd.setCtime(TimestampUtil.now());
            thdDao.insert(thd);
        }else{
            GpoThd update = thdDao.getById(thd.getXh());

            if(null == update){
                throw BaseException.create(ErrorCode.ERROR_GPO_THD_0001);
            }

            //已经传报的不能再修改
            if(update.getCbzt().equals(GpoConsts.CBZT.YCB.getCode())){
                throw BaseException.create(ErrorCode.ERROR_GPO_THD_0002);
            }

            BeanUtil.copyProperties(thd, update, CopyOptions.create().ignoreNullValue());
            thdDao.update(update);
        }
    }

    public void upload(Integer xh, GpoBaseRequest baseRequest) {
        GpoThd thd = thdDao.getById(xh);
        if(null == thd){
            throw BaseException.create(ErrorCode.ERROR_GPO_THD_0001);
        }

        if(thd.getCbzt().equals(GpoConsts.CBZT.YCB.getCode())){
            throw BaseException.create(ErrorCode.ERROR_GPO_THD_0003);
        }

        ThdYY011ReqMain main = new ThdYY011ReqMain();
        main.setCZLX(GpoZidian.CZLX.XZ.getVal());
        main.setYYBM(thd.getYybm());
        main.setPSDBM(thd.getPsdbm());
        main.setYQBM(thd.getYqbm());
        main.setTHDBH(thd.getThdbh()==null?"":thd.getThdbh());
        main.setDLCGBZ(thd.getDlcgbz());
        main.setSPLX(thd.getSplx());
        main.setZXSPBM(thd.getZxspbm());
        main.setGGBZ(thd.getGgbz());
        main.setCGJLDW(thd.getCgjldw());
        main.setSCPH(thd.getScph());
        main.setTHSL(thd.getThsl());
        main.setTHDJ(thd.getThdj());
        main.setTHZJ(thd.getThzj());
        main.setTHYY(thd.getThyy());

        XmlData<ThdYY011RespMain, Object> xmldata = webService.thdtbYY011(new XmlData<>(new Head(baseRequest.getIp(), baseRequest.getMac(), null), main, null), baseRequest);

        thd.setThdbh(xmldata.getMAIN().getTHDBH());
        thd.setCbrdm(baseRequest.getUserid());
        thd.setCbsj(TimestampUtil.now());
        thd.setCbzt(GpoConsts.CBZT.YCB.getCode());
        thdDao.update(thd);
    }

    public void confirm(Integer xh, GpoBaseRequest baseRequest) {
        GpoThd thd = thdDao.getById(xh);
        if(null == thd){
            throw BaseException.create(ErrorCode.ERROR_GPO_THD_0001);
        }

        //已作废
        if(thd.getQrzt().equals(GpoConsts.ZFZT.YZF.getCode())){
            throw BaseException.create(ErrorCode.ERROR_GPO_THD_0004);
        }

        //已确认
        if(thd.getQrzt().equals(GpoConsts.QRZT.YQR.getCode())){
            throw BaseException.create(ErrorCode.ERROR_GPO_THD_0005);
        }

        //待传报
        if(thd.getCbzt().equals(GpoConsts.CBZT.DCB.getCode())){
            throw BaseException.create(ErrorCode.ERROR_GPO_THD_0006);
        }

        ThdYY012ReqMain main = new ThdYY012ReqMain();
        main.setYYBM(thd.getYybm());
        main.setYQBM(thd.getYqbm());
        main.setPSDBM(thd.getPsdbm());
        main.setDLCGBZ(thd.getDlcgbz());
        main.setTHSL(thd.getThsl());
        main.setTHDBH(thd.getThdbh());
        webService.thdtbqrYY012(new XmlData<>(new Head(baseRequest.getIp(), baseRequest.getMac(), null), main, null), baseRequest);
        thd.setQrzt(GpoConsts.QRZT.YQR.getCode());
        thd.setQrrdm(baseRequest.getUserid());
        thdDao.update(thd);
    }

    public void disable(Integer xh, GpoBaseRequest baseRequest) {
        GpoThd thd = thdDao.getById(xh);
        if(null == thd){
            throw BaseException.create(ErrorCode.ERROR_GPO_THD_0001);
        }

        if(thd.getCbzt().equals(GpoConsts.QRZT.YQR.getCode())){
            throw BaseException.create(ErrorCode.ERROR_GPO_THD_0007);
        }

        ThdYY011ReqMain main = new ThdYY011ReqMain();
        main.setCZLX(GpoZidian.CZLX.ZF.getVal());
        main.setYYBM(thd.getYybm());
        main.setPSDBM(thd.getPsdbm());
        main.setYQBM(thd.getYqbm());
        main.setTHDBH(thd.getThdbh());
        main.setDLCGBZ(thd.getDlcgbz());
        main.setSPLX(thd.getSplx());
        main.setGGBZ(thd.getGgbz());
        main.setZXSPBM(thd.getZxspbm());
        main.setCGJLDW(thd.getCgjldw());
        main.setSCPH(thd.getScph());
        main.setTHSL(thd.getThsl());
        main.setTHDJ(thd.getThdj());
        main.setTHZJ(thd.getThzj());
        main.setTHYY(thd.getThyy());

        XmlData<ThdYY011RespMain, Object> xmldata = webService.thdtbYY011(new XmlData<>(new Head(baseRequest.getIp(), baseRequest.getMac(), null), main, null), baseRequest);
        thd.setZfzt(GpoConsts.ZFZT.YZF.getCode());
        thd.setZfrdm(String.valueOf(baseRequest.getUserid()));
        thd.setZfsj(TimestampUtil.now());
        thdDao.update(thd);
    }

    public void delete(Integer xh, Integer userid) {
        GpoThd thd = thdDao.getById(xh);
        if(null == thd){
            throw BaseException.create(ErrorCode.ERROR_GPO_THD_0001);
        }

        if(thd.getCbzt().equals(GpoConsts.CBZT.YCB.getCode())){
            throw BaseException.create(ErrorCode.ERROR_GPO_THD_0008);
        }

        thd.setZt(GpoConsts.ZT.SC.getCode());
        thd.setScrdm(String.valueOf(userid));
        thd.setScsj(TimestampUtil.now());
        thdDao.update(thd);

    }

    public List<GpoThd> page(
            Integer jgid,
            Timestamp rqks,
            Timestamp rqjz,
            String yqbm,
            List<String> cbzt,
            String qrzt,
            String zfzt) {
        List<GpoThd> list = thdDao.list(jgid, rqks, rqjz, yqbm, cbzt, qrzt, zfzt);
        for (GpoThd gpoThd : list) {
            gpoThd.setPsdmc(thdDao.selectPsdmc(gpoThd.getPsdbm(),jgid));
        }
        return list;
    }

    public GpoThd detail(Integer xh) {
        GpoThd thd = thdDao.getById(xh);
        thd.setPsdmc(thdDao.selectPsdmc(thd.getPsdbm(),thd.getJgid()));
        return thdDao.getById(xh);
    }
}
