
package com.buit.his.medinsuinterface.sh.dataitem.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 类描述：上海医保1号表<br>
 *
 * @author beijunhua
 */
@ApiModel(value = "上海医保1号")
public class ShybOneResp extends PageQuery {
	@ApiModelProperty(value="上海医保1号")
	private List<ShFeehzResp> shybfeeres;
    @ApiModelProperty(value = "填报单位")
    private String jgmc;
    @ApiModelProperty(value = "代码")
    private String jgid;
    @ApiModelProperty(value = "大写合计")
    private String jeh;
    @ApiModelProperty(value = "小写金额")
    private String jes;
    @ApiModelProperty(value = "申报年月")
    private String querydate;

    public List<ShFeehzResp> getShybfeeres() {
        return shybfeeres;
    }

    public void setShybfeeres(List<ShFeehzResp> shybfeeres) {
        this.shybfeeres = shybfeeres;
    }

    public String getJgmc() {
        return jgmc;
    }

    public void setJgmc(String jgmc) {
        this.jgmc = jgmc;
    }

    public String getJgid() {
        return jgid;
    }

    public void setJgid(String jgid) {
        this.jgid = jgid;
    }

    public String getJeh() {
        return jeh;
    }

    public void setJeh(String jeh) {
        this.jeh = jeh;
    }

    public String getJes() {
        return jes;
    }

    public void setJes(String jes) {
        this.jes = jes;
    }

    public String getQuerydate() {
        return querydate;
    }

    public void setQuerydate(String querydate) {
        this.querydate = querydate;
    }
}