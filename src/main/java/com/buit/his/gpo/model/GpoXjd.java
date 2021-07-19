package com.buit.his.gpo.model;

import java.sql.Timestamp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：GpoXjd<br> 
 * 类描述：GPO询价单
 * @author sunjx
 */
@ApiModel(value="GPO询价单")
public class GpoXjd {

	@ApiModelProperty(value="XH")
    private Integer xh;

	@ApiModelProperty(value="机构编码")
    private Integer jgid;

	@ApiModelProperty(value="GPO机构编码")
    private String gpoJgid;

	@ApiModelProperty(value="询价单编号")
    private String xjdbh;

	@ApiModelProperty(value="商品类型")
    private String splx;

	@ApiModelProperty(value="药品类型")
    private String yplx;

	@ApiModelProperty(value="询价时间")
    private Timestamp xjrq;

	@ApiModelProperty(value="询价时间-起始")
    private String xjqsrq;

	@ApiModelProperty(value="询价时间-结束")
    private String xjjzrq;

	@ApiModelProperty(value="商品统编代码/医保编码")
    private String zxspbm;

	@ApiModelProperty(value="医院内部商品代码")
    private String yyspbm;

	@ApiModelProperty(value="备注说明")
    private String bzsm;

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

	@ApiModelProperty(value="确认状态 0待确认 1已确认")
    private String qrzt;

	@ApiModelProperty(value="确认时间")
    private Timestamp qrsj;

	@ApiModelProperty(value="确认人代码")
    private String qrrdm;

	@ApiModelProperty(value="ctime")
    private Timestamp ctime;

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

    public void setGpoJgid(String value) {
        this.gpoJgid = value;
    }
    public String getGpoJgid() {
        return gpoJgid;
    }

    public void setXjdbh(String value) {
        this.xjdbh = value;
    }
    public String getXjdbh() {
        return xjdbh;
    }

    public void setSplx(String value) {
        this.splx = value;
    }
    public String getSplx() {
        return splx;
    }

    public void setYplx(String value) {
        this.yplx = value;
    }
    public String getYplx() {
        return yplx;
    }

    public void setXjrq(Timestamp value) {
        this.xjrq = value;
    }
    public Timestamp getXjrq() {
        return xjrq;
    }

    public void setXjqsrq(String value) {
        this.xjqsrq = value;
    }
    public String getXjqsrq() {
        return xjqsrq;
    }

    public void setXjjzrq(String value) {
        this.xjjzrq = value;
    }
    public String getXjjzrq() {
        return xjjzrq;
    }

    public void setZxspbm(String value) {
        this.zxspbm = value;
    }
    public String getZxspbm() {
        return zxspbm;
    }

    public void setYyspbm(String value) {
        this.yyspbm = value;
    }
    public String getYyspbm() {
        return yyspbm;
    }

    public void setBzsm(String value) {
        this.bzsm = value;
    }
    public String getBzsm() {
        return bzsm;
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

    public void setQrzt(String value) {
        this.qrzt = value;
    }
    public String getQrzt() {
        return qrzt;
    }

    public void setQrsj(Timestamp value) {
        this.qrsj = value;
    }
    public Timestamp getQrsj() {
        return qrsj;
    }

    public void setQrrdm(String value) {
        this.qrrdm = value;
    }
    public String getQrrdm() {
        return qrrdm;
    }

    public void setCtime(Timestamp value) {
        this.ctime = value;
    }
    public Timestamp getCtime() {
        return ctime;
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