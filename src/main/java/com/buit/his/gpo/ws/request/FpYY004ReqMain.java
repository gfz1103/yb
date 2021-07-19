package com.buit.his.gpo.ws.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author sunjx
 * @Date 2020-11-13 17:19
 * @Description
 **/
@ApiModel
public class FpYY004ReqMain {

    @ApiModelProperty("药企编码 选填 市药事系统对药企的统编代码，若不填写，则根据发票号和当前医院的医院编码查找符合条件的发票信息")
    private String YQBM;

    //发票明细编号 选填 初次调用不填写，接续调用时须填写上次返回的最后一个发票明细编号
    private String FPMXBH;

    public String getYQBM() {
        return YQBM==null?"":YQBM;
    }

    public void setYQBM(String YQBM) {
        this.YQBM = YQBM;
    }

    public String getFPMXBH() {
        return FPMXBH==null?"":FPMXBH;
    }

    public void setFPMXBH(String FPMXBH) {
        this.FPMXBH = FPMXBH;
    }
}
