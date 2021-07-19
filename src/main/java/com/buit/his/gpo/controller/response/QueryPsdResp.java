package com.buit.his.gpo.controller.response;

import com.buit.his.gpo.controller.request.QueryPsdReq;
import com.buit.his.gpo.ws.response.PsdYY003RespStruct;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Description
 * @Author yueyu
 * @Date 2021/5/4 10:39
 */
public class QueryPsdResp extends QueryPsdReq {

    @ApiModelProperty("是否未最后一页，是：true，否：false")
    private boolean lastPage;

    @ApiModelProperty("列表")
    private List<PsdYY003RespStruct> list;

    public List<PsdYY003RespStruct> getList() {
        return list;
    }

    public void setList(List<PsdYY003RespStruct> list) {
        this.list = list;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }
}
