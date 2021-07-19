package com.buit.his.shyb.source.service.impl;

import com.buit.commons.SysUser;
import com.buit.constans.TableName;
import com.buit.his.shyb.source.dao.*;
import com.buit.his.shyb.source.entity.ExceptionTips;
import com.buit.his.shyb.source.entity.SettleConstant;
import com.buit.his.shyb.source.entity.SettleResultDTO;
import com.buit.his.shyb.source.entity.business.*;
import com.buit.his.shyb.source.enums.BusinessTypeEnum;
import com.buit.his.shyb.source.enums.MedicalCategoryEnum;
import com.buit.his.shyb.source.enums.TradingChannelEnum;
import com.buit.his.shyb.source.model.MedicalInsuranceModel;
import com.buit.his.shyb.source.model.UnifiedBusinessManager;
import com.buit.his.shyb.source.model.YbDbdj;
import com.buit.his.shyb.source.util.HisUtil;
import com.buit.his.shyb.source.util.JackJsonUtil;
import com.buit.his.shyb.source.util.ServiceCode;
import com.buit.op.service.OpBrzdService;
import com.buit.op.service.OpCf01Service;
import com.buit.op.service.OpYjcf01Service;
import com.buit.system.service.DicJbbmService;
import com.buit.system.service.SysXtcsCacheSer;
import com.buit.system.utill.MedicineUtils;
import com.buit.utill.RedisFactory;
import com.buit.utill.StrUtil;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 医保
 * @author beijunhua
 */
@DubboService
public class ShybZhbzServiceImpl implements ShybZhbzService {
    @Autowired
    private ShybZhbzDao shybZhbzDao;

    @Override
    public Map<String, Object> getZhbzBrxz(String s) {
        Map<String,Object> map = shybZhbzDao.getZhbzBrxz(s);
        return map;
    }

    @Override
    public List<YbDbdj> getShybDbxmdm(String s, String s1) {
        return null;
    }
}