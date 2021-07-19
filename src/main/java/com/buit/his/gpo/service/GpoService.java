package com.buit.his.gpo.service;

import cn.hutool.core.date.DateUtil;
import com.buit.his.gpo.controller.response.SpResp;
import com.buit.his.gpo.controller.response.YqResp;
import com.buit.his.gpo.dao.GpoDao;
import com.buit.his.gpo.dao.GpoJgxxDao;
import com.buit.his.gpo.dao.GpoZdDao;
import com.buit.his.gpo.dto.YpbzzhDto;
import com.buit.his.gpo.dto.YpbzxxDto;
import com.buit.his.gpo.model.GpoJgxx;
import com.buit.his.gpo.model.GpoZd;
import com.buit.his.medinsuinterface.sh.dataitem.dao.ShybYpjcxxDao;
import com.buit.utill.TimestampUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author sunjx
 * @Date 2020-11-10 17:12
 * @Description gpo 公用接口
 **/
@Service
public class GpoService {

    @Autowired
    private GpoDao gpoDao;

    @Autowired
    private GpoJgxxDao jgxxDao;

    @Autowired
    private GpoZdDao zdDao;

    @Autowired
    ShybYpjcxxDao shybYpjcxxDao;

    @Value("${gpo.ws.xiyao.jgbm}")
    String jgbm;

    public PageInfo<SpResp> ypList(Integer pageNum, Integer pageSize, String jgid, String kw, Integer yksb) {
        PageInfo<SpResp> resp = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() ->gpoDao.ypList(jgid, kw, DateUtil.format(TimestampUtil.now(), "yyyyMMdd"),yksb));
        List<String> ypybbmList = resp.getList().stream().map(r->r.getTBDM()).collect(Collectors.toList());
        if(ypybbmList!=null&&!ypybbmList.isEmpty()) {
            List<YpbzxxDto> ypbzxxDtoList = shybYpjcxxDao.queryYpbzxx(jgid, ypybbmList);
            Map<String, YpbzxxDto> ypbzxxMap = ypbzxxDtoList.stream().collect(Collectors.toMap(YpbzxxDto::getTbdm, v -> v, (v1, v2) -> v1));
            resp.getList().forEach(r -> {
                YpbzxxDto ypbzxxDto = ypbzxxMap.get(r.getTBDM());
                YpbzzhDto jjbz = new YpbzzhDto();
                jjbz.setBzsl(1);
                jjbz.setDwmc(ypbzxxDto.getJjdw());
                jjbz.setBzjg(ypbzxxDto.getCgdj());
                YpbzzhDto bfbz = new YpbzzhDto();
                bfbz.setBzsl(ypbzxxDto.getJjbz());
                bfbz.setDwmc(ypbzxxDto.getBfdw());
                bfbz.setBzjg(ypbzxxDto.getBfdj());
                List<YpbzzhDto> bzzhList = new ArrayList<>();
                bzzhList.add(jjbz);
                if (bfbz.getBzsl() != 1) {
                    bzzhList.add(bfbz);
                }
                r.setZhbzList(bzzhList);
            });
        }
        return resp;
    }

    public List<YqResp> yqlist(String jgid, String kw) {
        return gpoDao.yqList(jgid, kw);
    }

    public GpoJgxx gpoJgbm(Integer hospitalId) {
        GpoJgxx jgxx = new GpoJgxx();
        jgxx.setGpoJgdm(jgbm);
        jgxx.setJgid(hospitalId.toString());
        return jgxx;
    }

    public List<GpoZd> zd(String type) {
        return zdDao.zd(type);
    }
}
