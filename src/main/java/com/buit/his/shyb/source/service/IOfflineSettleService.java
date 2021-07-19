package com.buit.his.shyb.source.service;

import com.buit.his.shyb.source.entity.SettleResultDTO;

import java.util.Map;

public interface IOfflineSettleService {

    public SettleResultDTO batchUploadFeeDetails_MZ(Map<String, Object> body, Map<String, Object> res);
}
