package com.buit.his.shyb.source.service;

import com.buit.his.shyb.source.entity.SettleResultDTO;
import com.buit.his.shyb.source.entity.business.MessageBody;

public interface ISendMessageService {

    /**
     * 发送入参至医保接口服务
     * @param url url
     * @param messageBody 入参
     * @return 返回值
     */
    public SettleResultDTO sendMessage(String url, MessageBody messageBody);
}
