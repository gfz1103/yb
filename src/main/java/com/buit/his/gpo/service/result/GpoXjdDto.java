package com.buit.his.gpo.service.result;

import com.buit.his.gpo.model.GpoXjd;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author sunjx
 * @Date 2020-11-09 13:46
 * @Description
 **/
@ApiModel("询价单dto")
public class GpoXjdDto extends GpoXjd {

    @ApiModelProperty("商品名称")
    private String spmc;

    public String getSpmc() {
        return spmc;
    }

    public void setSpmc(String spmc) {
        this.spmc = spmc;
    }
}
