package com.buit.his.gpo.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.StrUtil;
import com.buit.commons.BaseException;
import com.buit.constans.ErrorCode;
import com.buit.constans.TableName;
import com.buit.his.gpo.controller.request.GpoBaseRequest;
import com.buit.his.gpo.controller.response.SpResp;
import com.buit.his.gpo.dao.GpoXjdDao;
import com.buit.his.gpo.model.GpoXjd;
import com.buit.his.gpo.service.result.GpoXjdDto;
import com.buit.his.gpo.ws.GpoConsts;
import com.buit.his.gpo.ws.GpoWebService;
import com.buit.his.gpo.ws.GpoZidian;
import com.buit.his.gpo.ws.request.XjdYY013ReqMain;
import com.buit.his.gpo.ws.request.XjdYY014ReqMain;
import com.buit.his.gpo.ws.response.XjdYY013RespMain;
import com.buit.his.gpo.ws.xml.Head;
import com.buit.his.gpo.ws.xml.XmlData;
import com.buit.utill.RedisFactory;
import com.buit.utill.TimestampUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Author sunjx
 * @Date 2020-11-09 09:37
 * @Description 询价单
 **/
@Service
public class XjdService {

    @Autowired
    private RedisFactory redisFactory;

    @Autowired
    private GpoWebService webService;

    @Autowired
    private GpoService gpoService;

    @Autowired
    private GpoXjdDao xjdDao;

    public void save(GpoXjd xjd) {

        //商品统编代码/医保编码 zxspbm;
        if(StrUtil.isBlank(xjd.getZxspbm())){
            throw BaseException.create(ErrorCode.ERROR_GPO_XJD_001);
        }

        //医院内部商品代码 yyspbm;
        if(StrUtil.isBlank(xjd.getYyspbm())){
            throw BaseException.create(ErrorCode.ERROR_GPO_XJD_002);
        }

        //商品类型 splx;
        if(StrUtil.isBlank(xjd.getSplx())){
            throw BaseException.create(ErrorCode.ERROR_GPO_XJD_003);
        }

        //药品类型 yplx;
        if(StrUtil.isBlank(xjd.getYplx())){
            throw BaseException.create(ErrorCode.ERROR_GPO_XJD_004);
        }

        //询价时间-起始 xjqsrq;
        if(StrUtil.isBlank(xjd.getXjqsrq())){
            throw BaseException.create(ErrorCode.ERROR_GPO_XJD_005);
        }

        //询价时间-结束 xjjzrq;
        if(StrUtil.isBlank(xjd.getXjjzrq())){
            throw BaseException.create(ErrorCode.ERROR_GPO_XJD_006);
        }

        if(null == xjd.getXh()){
            xjd.setGpoJgid(gpoService.gpoJgbm(xjd.getJgid()).getGpoJgdm());
            xjd.setXh(redisFactory.getTableKey(TableName.DB_NAME, TableName.TB_NAME_GPO_XJD));
            xjd.setXjrq(TimestampUtil.now());
            xjd.setZt(GpoConsts.ZT.YX.getCode());
            xjd.setCbzt(GpoConsts.CBZT.DCB.getCode());
            xjd.setQrzt(GpoConsts.QRZT.DQR.getCode());
            xjd.setZfzt(GpoConsts.ZFZT.YX.getCode());
            xjdDao.insert(xjd);
        }else{
            GpoXjd update = xjdDao.getById(xjd.getXh());
            if(null == update){
                throw BaseException.create(ErrorCode.ERROR_GPO_XJD_007);
            }

            //已传报的不能修改
            if(update.getCbzt().equals(GpoConsts.CBZT.YCB.getCode())){
                throw BaseException.create(ErrorCode.ERROR_GPO_XJD_008);
            }

            BeanUtil.copyProperties(xjd , update, CopyOptions.create().ignoreNullValue());
            xjdDao.update(update);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void disable(Integer xh, GpoBaseRequest baseRequest) {
        GpoXjd xjd = xjdDao.getById(xh);
        if(null == xjd){
            throw BaseException.create(ErrorCode.ERROR_GPO_XJD_007);
        }

        //已传报的不能作废
        if(xjd.getCbzt().equals(GpoConsts.CBZT.DCB.getCode())){
            throw BaseException.create(ErrorCode.ERROR_GPO_XJD_010);
        }

        XjdYY013ReqMain main = new XjdYY013ReqMain();
        main.setCZLX(GpoZidian.CZLX.XZ.getVal());
        main.setXJDBH(xjd.getXjdbh()==null ? "":xjd.getXjdbh());
        main.setSPLX(xjd.getSplx());
        main.setYPLX(xjd.getYplx());
        main.setZXSPBM(xjd.getZxspbm());
        main.setYYSPBM(xjd.getYyspbm());
        main.setXJQSRQ(xjd.getXjqsrq());
        main.setXJJZRQ(xjd.getXjjzrq());
        main.setBZSM(xjd.getBzsm()==null ? "":xjd.getBzsm());

        webService.xjdtbYY013(new XmlData<>(new Head(baseRequest.getIp(), baseRequest.getMac(), null), main, null), baseRequest);

        //删除
        xjd.setZfzt(GpoConsts.ZFZT.YZF.getCode());
        xjd.setZfrdm(baseRequest.getUserid());
        xjd.setZfsj(TimestampUtil.now());
        xjdDao.update(xjd);
    }

    @Transactional(rollbackFor = Exception.class)
    public void upload(Integer xh, GpoBaseRequest baseRequest) {
        GpoXjd update = xjdDao.getById(xh);
        if(null == update){
            throw BaseException.create(ErrorCode.ERROR_GPO_XJD_007);
        }

        //已传报的、已确认的、已作废的、已删除的都不能传报
        if(update.getCbzt().equals(GpoConsts.CBZT.YCB.getCode())
            || update.getQrzt().equals(GpoConsts.QRZT.YQR.getCode())
            || update.getZfzt().equals(GpoConsts.ZFZT.YZF.getCode())
            || update.getZt().equals(GpoConsts.ZT.SC.getCode())){
            throw BaseException.create(ErrorCode.ERROR_GPO_XJD_009);
        }

        XjdYY013ReqMain main = new XjdYY013ReqMain();
        main.setCZLX(GpoZidian.CZLX.XZ.getVal());
        main.setSPLX(update.getSplx());
        main.setYPLX(update.getYplx());
        main.setZXSPBM(update.getZxspbm());
        main.setYYSPBM(update.getYyspbm());
        main.setXJQSRQ(update.getXjqsrq());
        main.setXJJZRQ(update.getXjjzrq());
        main.setBZSM(update.getBzsm());

        XmlData<XjdYY013RespMain, Object> response = webService.xjdtbYY013(new XmlData<>(new Head(baseRequest.getIp(), baseRequest.getMac(), null), main, null), baseRequest);

        //传报
        update.setCbzt(GpoConsts.CBZT.YCB.getCode());
        update.setCbsj(TimestampUtil.now());
        update.setCbrdm(baseRequest.getUserid());
        update.setXjdbh(response.getMAIN().getXJDBH());
        xjdDao.update(update);
    }

    @Transactional(rollbackFor = Exception.class)
    public void confirm(Integer xh, GpoBaseRequest baseRequest) {
        GpoXjd update = xjdDao.getById(xh);
        if(null == update){
            throw BaseException.create(ErrorCode.ERROR_GPO_XJD_007);
        }

        //已确认的、已作废的、已删除的都不能确认
        if(update.getQrzt().equals(GpoConsts.QRZT.YQR.getCode())
                || update.getZfzt().equals(GpoConsts.ZFZT.YZF.getCode())
                || update.getZt().equals(GpoConsts.ZT.SC.getCode())){
            throw BaseException.create(ErrorCode.ERROR_GPO_XJD_011);
        }

        XjdYY014ReqMain main = new XjdYY014ReqMain();
        main.setXJDBH(update.getXjdbh());
        main.setSPLX(update.getSplx());
        main.setYPLX(update.getYplx());
        main.setZXSPBM(update.getZxspbm());
        main.setYYSPBM(update.getYyspbm());

        webService.xjdtbqrYY014(new XmlData<>(new Head(baseRequest.getIp(), baseRequest.getMac(), null), main, null), baseRequest);

        //确认
        update.setQrzt(GpoConsts.QRZT.YQR.getCode());
        update.setQrrdm(baseRequest.getUserid());
        update.setQrsj(TimestampUtil.now());
        xjdDao.update(update);
    }

    public List<GpoXjdDto> page(Integer jgid,
                             Timestamp xjrqks,
                             Timestamp xjrqjz,
                             String xjsptbbm,
                             List<String> cbzt,
                             String qrzt,
                             String zfzt) {
        return xjdDao.page(jgid, xjrqks, xjrqjz, xjsptbbm, cbzt, qrzt, zfzt);
    }

    public GpoXjdDto detail(Integer xh) {
        return xjdDao.detail(xh);
    }

    public void delete(Integer xh, Integer userid) {
        GpoXjd xjd = xjdDao.getById(xh);
        if(null == xjd){
            throw BaseException.create(ErrorCode.ERROR_GPO_XJD_007);
        }

        //已传报的不能删除
        if(xjd.getCbzt().equals(GpoConsts.CBZT.YCB.getCode())){
            throw BaseException.create(ErrorCode.ERROR_GPO_XJD_012);
        }

        //删除
        xjd.setZt(GpoConsts.ZT.SC.getCode());
        xjd.setScrdm(String.valueOf(userid));
        xjd.setScsj(TimestampUtil.now());
        xjdDao.update(xjd);
    }

}
