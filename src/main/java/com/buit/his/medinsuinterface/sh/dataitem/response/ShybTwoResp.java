
package com.buit.his.medinsuinterface.sh.dataitem.response;

import com.buit.commons.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 类描述：上海医保2号表<br>
 *
 * @author songyu
 */
@ApiModel(value = "上海医保2号")
public class ShybTwoResp extends PageQuery {

    @ApiModelProperty(value = "填报单位")
    private String jgmc;
    @ApiModelProperty(value = "代码")
    private String jgid;
    @ApiModelProperty(value = "居保工伤费用")
    private List<ShFeehzEhbjbResp> shFeehzEhbjbResps;
    @ApiModelProperty(value = "医疗互助费用")
    private List<ShFeehzEhbhzbkResp> shFeehzEhbhzbkResps;
    @ApiModelProperty(value = "申报年月")
    private String querydate;
    @ApiModelProperty(value = "居保门诊小写金额")
    private String jbmzxxje;
    @ApiModelProperty(value = "居保门诊人次")
    private String jbmzrc;
    @ApiModelProperty(value = "居保住院小写金额")
    private String jbzyxxje;
    @ApiModelProperty(value = "居保住院人次")
    private String jbzyrc;
    @ApiModelProperty(value = "居保小写金额")
    private String jbxxje;
    @ApiModelProperty(value = "居保大写金额")
    private String jbdxje;
    @ApiModelProperty(value = "工伤小写金额")
    private String gsxxje;
    @ApiModelProperty(value = "工伤大写金额")
    private String gsdxje;
    @ApiModelProperty(value = "医疗互助小写金额")
    private String ylhzxxje;
    @ApiModelProperty(value = "医疗互助大写金额")
    private String ylhzdxje;


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

    public List<ShFeehzEhbjbResp> getShFeehzEhbjbResps() {
        return shFeehzEhbjbResps;
    }

    public void setShFeehzEhbjbResps(List<ShFeehzEhbjbResp> shFeehzEhbjbResps) {
        this.shFeehzEhbjbResps = shFeehzEhbjbResps;
    }

    public List<ShFeehzEhbhzbkResp> getShFeehzEhbhzbkResps() {
        return shFeehzEhbhzbkResps;
    }

    public void setShFeehzEhbhzbkResps(List<ShFeehzEhbhzbkResp> shFeehzEhbhzbkResps) {
        this.shFeehzEhbhzbkResps = shFeehzEhbhzbkResps;
    }

    public String getQuerydate() {
        return querydate;
    }

    public void setQuerydate(String querydate) {
        this.querydate = querydate;
    }

    public String getJbxxje() {
        return jbxxje;
    }

    public void setJbxxje(String jbxxje) {
        this.jbxxje = jbxxje;
    }

    public String getJbdxje() {
        return jbdxje;
    }

    public void setJbdxje(String jbdxje) {
        this.jbdxje = jbdxje;
    }

    public String getGsxxje() {
        return gsxxje;
    }

    public void setGsxxje(String gsxxje) {
        this.gsxxje = gsxxje;
    }

    public String getGsdxje() {
        return gsdxje;
    }

    public void setGsdxje(String gsdxje) {
        this.gsdxje = gsdxje;
    }

    public String getYlhzxxje() {
        return ylhzxxje;
    }

    public void setYlhzxxje(String ylhzxxje) {
        this.ylhzxxje = ylhzxxje;
    }

    public String getYlhzdxje() {
        return ylhzdxje;
    }

    public void setYlhzdxje(String ylhzdxje) {
        this.ylhzdxje = ylhzdxje;
    }

    public String getJbmzxxje() {
        return jbmzxxje;
    }

    public void setJbmzxxje(String jbmzxxje) {
        this.jbmzxxje = jbmzxxje;
    }

    public String getJbmzrc() {
        return jbmzrc;
    }

    public void setJbmzrc(String jbmzrc) {
        this.jbmzrc = jbmzrc;
    }

    public String getJbzyxxje() {
        return jbzyxxje;
    }

    public void setJbzyxxje(String jbzyxxje) {
        this.jbzyxxje = jbzyxxje;
    }

    public String getJbzyrc() {
        return jbzyrc;
    }

    public void setJbzyrc(String jbzyrc) {
        this.jbzyrc = jbzyrc;
    }
}