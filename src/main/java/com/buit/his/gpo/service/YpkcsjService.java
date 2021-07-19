package com.buit.his.gpo.service;

import cn.hutool.core.collection.CollUtil;
import com.buit.constans.TableName;
import com.buit.his.gpo.controller.request.YpkcsUploadRequest;
import com.buit.his.gpo.controller.response.YpkcshQueryResponse;
import com.buit.his.gpo.dao.GpoPsdxxDao;
import com.buit.his.gpo.dao.GpoYpkccbDao;
import com.buit.his.gpo.dao.GpoYpkccbxqmxDao;
import com.buit.his.gpo.model.GpoPsdxx;
import com.buit.his.gpo.model.GpoYpkccb;
import com.buit.his.gpo.model.GpoYpkccbxqmx;
import com.buit.his.gpo.ws.GpoWebService;
import com.buit.his.gpo.ws.GpoZidian;
import com.buit.his.gpo.ws.request.YpdqkclsjcbYY002ReqMain;
import com.buit.his.gpo.ws.request.YpdqkclsjcbYY002ReqStruct;
import com.buit.his.gpo.ws.response.YpdqkclsjcbYY002RespStruct;
import com.buit.his.gpo.ws.xml.Head;
import com.buit.his.gpo.ws.xml.Struct;
import com.buit.his.gpo.ws.xml.XmlData;
import com.buit.utill.RedisFactory;
import com.buit.utill.TimestampUtil;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author sunjx
 * @Date 2020-11-03 18:58
 * @Description 药品库存数据
 **/
@Service
public class YpkcsjService {

    @Autowired
    private RedisFactory redisFactory;

    @Autowired
    private GpoWebService gpoWebService;

    @Autowired
    private GpoYpkccbDao kccbDao;

    @Autowired
    private GpoYpkccbxqmxDao mxDao;

    @Autowired
    private GpoPsdxxDao psdDao;

    /**
     * 每次最多上传500条
     * @param request
     */
    public void upload(YpkcsUploadRequest request) {
        List<YpkcshQueryResponse> list = list(request.getJgid());
        if (CollUtil.isEmpty(list)) {
            return;
        }
        int pointsDataLimit = 50;
        int listSize = list.size();
        int maxSize = listSize - 1;
        //新建一个载体list
        List<YpkcshQueryResponse> newList = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            //分批次处理
            newList.add(list.get(i));//循环将数据填入载体list
            //当条数到达五百条或者list最大条数小于500条时开始上传数据
            if (pointsDataLimit == newList.size() || i == maxSize) {
                YpdqkclsjcbYY002ReqMain wsMain = new YpdqkclsjcbYY002ReqMain();
                wsMain.setKCCBSJ(DateFormatUtils.format(TimestampUtil.now(), "yyyyMMdd/HHmmss"));
                wsMain.setJLS(String.valueOf(newList.size()));
                List<Struct<YpdqkclsjcbYY002ReqStruct>> wsStructs = new ArrayList<>();
                for (YpkcshQueryResponse resp : newList) {
                    YpdqkclsjcbYY002ReqStruct wsStruct = new YpdqkclsjcbYY002ReqStruct();
                    wsStruct.setSPLX(resp.getSplx());
                    wsStruct.setYPLX(resp.getYplx());
                    wsStruct.setZXSPBM(resp.getZxspbm());
                    wsStruct.setYYSPBM(resp.getYyspbm());
                    wsStruct.setPSDBM(resp.getPsdbm());
                    wsStruct.setYPKCL(resp.getYpkcl());
                    wsStruct.setKCDW(resp.getKcdw());
                    wsStructs.add(new Struct<>(wsStruct));
                }

                XmlData<YpdqkclsjcbYY002ReqMain, YpdqkclsjcbYY002ReqStruct> wsRequest = new XmlData<>(new Head(request.getIp(), request.getMac(), request.getBzxx()), wsMain, wsStructs);
                XmlData<Object, YpdqkclsjcbYY002RespStruct> wsResponse = gpoWebService.ypdqkclsjcbYY002(wsRequest, request);
                GpoYpkccb kccb = new GpoYpkccb();
                kccb.setXh(redisFactory.getTableKey(TableName.DB_NAME, TableName.TB_NAME_GPO_YPKCCB));
                kccb.setJgid(request.getJgid());
                kccb.setKccbsj(wsMain.getKCCBSJ());
                kccb.setJls(wsMain.getJLS());
                kccb.setCljg("00000");
                kccb.setCtime(TimestampUtil.now());
                kccb.setCuser(request.getUserid());
                kccb.setCuname(request.getUname());

                kccbDao.insert(kccb);
                for (YpkcshQueryResponse resp : newList) {
                    GpoYpkccbxqmx mx = new GpoYpkccbxqmx();
                    mx.setXh(redisFactory.getTableKey(TableName.DB_NAME, TableName.TB_NAME_GPO_YPKCCBXQMX));
                    mx.setJgid(request.getJgid());
                    mx.setSplx(resp.getSplx());
                    mx.setYplx(resp.getYplx());
                    mx.setZxspbm(resp.getZxspbm());
                    mx.setYyspbm(resp.getYyspbm());
                    mx.setPsdbm(resp.getPsdbm());
                    mx.setYpkcl(resp.getYpkcl());
                    mx.setKcdw(resp.getKcdw());
                    mx.setCtime(TimestampUtil.now());
                    mx.setCuser(request.getUserid());
                    mx.setCuname(request.getUname());
                    mxDao.insert(mx);
                }
                newList.clear();//每次批量操作后,清空载体list,等待下次的数据填入
            }
        }


    }

    public List<YpkcshQueryResponse> list(Integer jgid) {
        List<YpkcshQueryResponse> list = kccbDao.kccx(jgid);
        //查询唯一可用的配送点
        GpoPsdxx psd = psdDao.select(jgid);
        //查询药品库存西药,中成药
        if (psd == null) {
            psd=new GpoPsdxx();
        }

        for (YpkcshQueryResponse response : list) {
            response.setPsdbm(psd.getPsdbm());
            response.setSplx(GpoZidian.SPLX.YPL.getCode());
            response.setPsdmc(psd.getPsdmc());
        }
        return list;
    }
}
