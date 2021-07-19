package com.buit.his.gpo.model;

import java.sql.Timestamp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：GpoPsdxx<br> 
 * 类描述：GPO配送点信息
 * @author sunjx
 */
@ApiModel(value="GPO配送点信息")
public class GpoPsdxx {

	@ApiModelProperty(value="XH")
    private Integer xh;

	@ApiModelProperty(value="机构ID")
    private Integer jgid;

	@ApiModelProperty(value="配送点编码", required = true)
    private String psdbm;

	@ApiModelProperty(value="配送点名称", required = true)
    private String psdmc;

	@ApiModelProperty(value="配送地址", required = true)
    private String psdz;

	@ApiModelProperty(value="联系人姓名", required = true)
    private String lxrxm;

	@ApiModelProperty(value="联系电话", required = true)
    private String lxdh;

	@ApiModelProperty(value="邮政编码", required = true)
    private String yzbm;

	@ApiModelProperty(value="备注信息")
    private String bzxx;

	@ApiModelProperty(value="数据状态 -1逻辑删除 1有效")
    private String zt;

	@ApiModelProperty(value="删除时间")
    private Timestamp scsj;

	@ApiModelProperty(value="删除人代码")
    private String scrdm;

	@ApiModelProperty(value="传报状态 0待传报 1已传报")
    private String cbzt;

	@ApiModelProperty(value="传报时间")
    private Timestamp cbsj;

	@ApiModelProperty(value="传报人代码")
    private String cbrdm;

	@ApiModelProperty(value="作废状态 -1作废 1有效")
    private String zfzt;

	@ApiModelProperty(value="作废时间")
    private Timestamp zfsj;

	@ApiModelProperty(value="作废人代码")
    private String zfrdm;

	@ApiModelProperty(value="cuser")
    private String cuser;

	@ApiModelProperty(value="cuname")
    private String cuname;

	@ApiModelProperty(value="ctime")
    private Timestamp ctime;


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

    public void setPsdbm(String value) {
        this.psdbm = value;
    }
    public String getPsdbm() {
        return psdbm;
    }

    public void setPsdmc(String value) {
        this.psdmc = value;
    }
    public String getPsdmc() {
        return psdmc;
    }

    public void setPsdz(String value) {
        this.psdz = value;
    }
    public String getPsdz() {
        return psdz;
    }

    public void setLxrxm(String value) {
        this.lxrxm = value;
    }
    public String getLxrxm() {
        return lxrxm;
    }

    public void setLxdh(String value) {
        this.lxdh = value;
    }
    public String getLxdh() {
        return lxdh;
    }

    public void setYzbm(String value) {
        this.yzbm = value;
    }
    public String getYzbm() {
        return yzbm;
    }

    public void setBzxx(String value) {
        this.bzxx = value;
    }
    public String getBzxx() {
        return bzxx;
    }

    public void setZt(String value) {
        this.zt = value;
    }
    public String getZt() {
        return zt;
    }

    public void setScsj(Timestamp value) {
        this.scsj = value;
    }
    public Timestamp getScsj() {
        return scsj;
    }

    public void setScrdm(String value) {
        this.scrdm = value;
    }
    public String getScrdm() {
        return scrdm;
    }

    public void setCbzt(String value) {
        this.cbzt = value;
    }
    public String getCbzt() {
        return cbzt;
    }

    public void setCbsj(Timestamp value) {
        this.cbsj = value;
    }
    public Timestamp getCbsj() {
        return cbsj;
    }

    public void setCbrdm(String value) {
        this.cbrdm = value;
    }
    public String getCbrdm() {
        return cbrdm;
    }

    public void setZfzt(String value) {
        this.zfzt = value;
    }
    public String getZfzt() {
        return zfzt;
    }

    public void setZfsj(Timestamp value) {
        this.zfsj = value;
    }
    public Timestamp getZfsj() {
        return zfsj;
    }

    public void setZfrdm(String value) {
        this.zfrdm = value;
    }
    public String getZfrdm() {
        return zfrdm;
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

    public void setCtime(Timestamp value) {
        this.ctime = value;
    }
    public Timestamp getCtime() {
        return ctime;
    }


}