package com.buit.his.gpo.model;

import java.sql.Timestamp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：GpoWsjl<br> 
 * 类描述：GPO webservice 记录
 * @author sunjx
 */
@ApiModel(value="GPO webservice 记录")
public class GpoWsjl {

	@ApiModelProperty(value="XH")
    private Integer xh;

	@ApiModelProperty(value="机构编码")
    private Integer jgid;

	@ApiModelProperty(value="药库类型 1西药库 2中草药库 com.buit.his.gpo.ws.GpoConsts.YKLX")
    private Integer lx;

	@ApiModelProperty(value="接口代码")
    private String dm;

	@ApiModelProperty(value="接口名称")
    private String mc;

	@ApiModelProperty(value="入参xml")
    private String cs;

	@ApiModelProperty(value="返回xml")
    private String fh;

	@ApiModelProperty(value="调用耗时(毫秒)")
    private Integer hs;

	@ApiModelProperty(value="调用开始时间")
    private Timestamp ctime;

	@ApiModelProperty(value="调用结束时间")
    private Timestamp etime;

	@ApiModelProperty(value="cuser")
    private String cuser;

	@ApiModelProperty(value="cuname")
    private String cuname;


    public void setXh(Integer value) {
        this.xh = value;
    }
    public Integer getXh() {
        return xh;
    }

    public void setJgid(Integer value) {
        this.jgid = value;
    }
    public Integer getJgid() {
        return jgid;
    }

    public void setLx(Integer value) {
        this.lx = value;
    }
    public Integer getLx() {
        return lx;
    }

    public void setDm(String value) {
        this.dm = value;
    }
    public String getDm() {
        return dm;
    }

    public void setMc(String value) {
        this.mc = value;
    }
    public String getMc() {
        return mc;
    }

    public void setCs(String value) {
        this.cs = value;
    }
    public String getCs() {
        return cs;
    }

    public void setFh(String value) {
        this.fh = value;
    }
    public String getFh() {
        return fh;
    }

    public void setHs(Integer value) {
        this.hs = value;
    }
    public Integer getHs() {
        return hs;
    }

    public void setCtime(Timestamp value) {
        this.ctime = value;
    }
    public Timestamp getCtime() {
        return ctime;
    }

    public void setEtime(Timestamp value) {
        this.etime = value;
    }
    public Timestamp getEtime() {
        return etime;
    }

    public void setCuser(String value) {
        this.cuser = value;
    }
    public String getCuser() {
        return cuser;
    }

    public void setCuname(String value) {
        this.cuname = value;
    }
    public String getCuname() {
        return cuname;
    }


}