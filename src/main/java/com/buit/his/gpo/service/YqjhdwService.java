package com.buit.his.gpo.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.buit.commons.BaseException;
import com.buit.constans.ErrorCode;
import com.buit.constans.TableName;
import com.buit.his.gpo.controller.response.JhdwResponse;
import com.buit.his.gpo.controller.response.YqResponse;
import com.buit.his.gpo.dao.GpoYqJhdwDao;
import com.buit.his.gpo.model.GpoYqJhdw;
import com.buit.his.gpo.service.result.JhdwYqIdMcDto;
import com.buit.utill.RedisFactory;
import com.buit.utill.TimestampUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author sunjx
 * @Date 2020-11-06 15:25
 * @Description 药企进货单位关联
 **/
@Service
public class YqjhdwService {

    @Autowired
    private RedisFactory redisFactory;

    @Autowired
    private GpoYqJhdwDao yqJhdwDao;

    public List<JhdwResponse> jhdwList(String kw, Integer dzzt) {
        return yqJhdwDao.jhdwList((StrUtil.isNotBlank(kw) ? kw.toUpperCase() : null), dzzt);
    }

    public List<YqResponse> yqList(String kw) {
        return yqJhdwDao.yqList(StrUtil.isNotBlank(kw) ? kw.toUpperCase() : null);
    }

    public void dz(Integer jhdwid, Integer yqid, Integer jgid, Integer userId, String userName) {
        //该进货单位已经对照
        if(yqJhdwDao.existJhdw(jhdwid) > 0){
            throw BaseException.create(ErrorCode.ERROR_GPO_YQJHDW_0001);
        }

        //该药企已经对照
        if(yqJhdwDao.existYq(yqid) > 0){
            throw BaseException.create(ErrorCode.ERROR_GPO_YQJHDW_0002);
        }

        GpoYqJhdw yqJhdw = new GpoYqJhdw();
        yqJhdw.setXh(redisFactory.getTableKey(TableName.DB_NAME, TableName.TB_NAME_GPO_YQ_JHDW));
        yqJhdw.setJgid(jgid);
        yqJhdw.setYqid(yqid);
        yqJhdw.setJhdwid(jhdwid);
        yqJhdw.setCtime(TimestampUtil.now());
        yqJhdw.setCuser(String.valueOf(userId));
        yqJhdw.setCuname(userName);
        yqJhdwDao.insert(yqJhdw);
    }

    public Integer zddz(Integer jgid, Integer userId, String userName) {
        //查询未对照的进货单位
        List<JhdwYqIdMcDto> jhdwDtos = yqJhdwDao.queryWdzJhdw(jgid);
        //查询未对照的药企
        List<JhdwYqIdMcDto> yqDtos = yqJhdwDao.queryWdzYq(jgid);

        if(CollUtil.isEmpty(jhdwDtos) || CollUtil.isEmpty(yqDtos)){
            return 0;
        }

        //名称匹配
        Integer count = 0;
        for (JhdwYqIdMcDto jhdwDto : jhdwDtos) {
            for (JhdwYqIdMcDto yqDto : yqDtos) {
                if(jhdwDto.getMc().equals(yqDto.getMc())){

                    GpoYqJhdw yqJhdw = new GpoYqJhdw();
                    yqJhdw.setXh(redisFactory.getTableKey(TableName.DB_NAME, TableName.TB_NAME_GPO_YQ_JHDW));
                    yqJhdw.setJgid(jgid);
                    yqJhdw.setYqid(yqDto.getYqid());
                    yqJhdw.setJhdwid(jhdwDto.getJhdwid());
                    yqJhdw.setCtime(TimestampUtil.now());
                    yqJhdw.setCuser(String.valueOf(userId));
                    yqJhdw.setCuname(userName);
                    yqJhdwDao.insert(yqJhdw);

                    count ++;
                    break;
                }
            }
        }

        return count;
    }

    public void jcdz(Integer xh) {
        yqJhdwDao.deleteById(xh);
    }
}
