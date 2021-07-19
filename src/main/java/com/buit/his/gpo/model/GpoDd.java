package com.buit.his.gpo.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.poi.hpsf.Decimal;

import javax.validation.constraints.NotBlank;

/**
 * 类名称：GpoDd<br>
 * 类描述：GPO订单
 * @author sunjx
 */
@ApiModel(value="GPO订单")
public class GpoDd {

	@ApiModelProperty(value="XH")
    private Integer xh;

	@ApiModelProperty(value="机构编码")
    private Integer jgid;

	@ApiModelProperty(value="GPO机构编码")
    private String gpoJgid;

	@ApiModelProperty(value="医院编码 必填 市药事系统发布的对医院的统编代码")
    private String yybm;

	@ApiModelProperty(value="配送点编码 指明配送到哪里。医院预先提交的医院内配送点编码", required = true)
    private String psdbm;

	@ApiModelProperty(value="配送点名称", required = true)
    private String psdmc;

    @NotBlank(message = "订单类型不能为空")
	@ApiModelProperty(value="订单类型 必填 详见4.1.14订单类型，对于医院药库订单和药企托管药库订单加以区分", required = true)
    private String ddlx;

	@ApiModelProperty(value="订单编号 选填 ")
    private String ddbh;

	@ApiModelProperty(value="医院计划单号 选填 该订单对应于医院HIS的计划单号", required = true)
    private String yyjhdh;

	@ApiModelProperty(value="最晚到货日期 选填 格式yyyyMMdd，若填写，则超过最晚到货日期的订单将自动置为完结状态，不能再行配送", required = true)
    private String zwdhrq;

    @ApiModelProperty(value="药企名稱")
    private String yqmc;

	private String yqbm;

	private String jhdwbm;



    public GpoDd() {
    }

    @ApiModelProperty(value="数据状态 -1逻辑删除 1有效")
    private String zt;

	@ApiModelProperty(value="删除时间")
    private Timestamp scsj;

	@ApiModelProperty(value="删除人代码")
    private String scrdm;

	@ApiModelProperty(value="传报状态 0待传报 1已传报")
    private String cbzt;

	@ApiModelProperty(value="作废时间")
    private Timestamp cbsj;

	@ApiModelProperty(value="作废人代码")
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

	@ApiModelProperty(value="药库类型 1西药库 2中草药库", required = true)
    private String yklx;

	@ApiModelProperty(value="药库识别")
    private Integer yksb;
	@ApiModelProperty(value="零售总金额")
    private BigDecimal lszje;
	@ApiModelProperty(value="进价总金额")
    private BigDecimal jjzje;

    @ApiModelProperty(value="订单明细", required = true)
    private List<GpoDdmx> ddmxes;

    public List<GpoDdmx> getDdmxes() {
        return ddmxes;
    }

    public String getYqmc() {
        return yqmc;
    }

    public void setYqmc(String yqmc) {
        this.yqmc = yqmc;
    }

    public void setDdmxes(List<GpoDdmx> ddmxes) {
        this.ddmxes = ddmxes;
    }

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

    public void setYybm(String value) {
        this.yybm = value;
    }
    public String getYybm() {
        return yybm;
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

    public void setDdlx(String value) {
        this.ddlx = value;
    }
    public String getDdlx() {
        return ddlx;
    }

    public void setDdbh(String value) {
        this.ddbh = value;
    }
    public String getDdbh() {
        return ddbh;
    }

    public void setYyjhdh(String value) {
        this.yyjhdh = value;
    }
    public String getYyjhdh() {
        return yyjhdh;
    }

    public void setZwdhrq(String value) {
        this.zwdhrq = value;
    }
    public String getZwdhrq() {
        return zwdhrq;
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

    public void setYklx(String value) {
        this.yklx = value;
    }
    public String getYklx() {
        return yklx;
    }

    public void setYksb(Integer value) {
        this.yksb = value;
    }
    public Integer getYksb() {
        return yksb;
    }

    public BigDecimal getLszje() {
        return lszje;
    }

    public void setLszje(BigDecimal lszje) {
        this.lszje = lszje;
    }

    public BigDecimal getJjzje() {
        return jjzje;
    }

    public void setJjzje(BigDecimal jjzje) {
        this.jjzje = jjzje;
    }

    public String getYqbm() {
        return yqbm;
    }

    public void setYqbm(String yqbm) {
        this.yqbm = yqbm;
    }

    public String getJhdwbm() {
        return jhdwbm;
    }

    public void setJhdwbm(String jhdwbm) {
        this.jhdwbm = jhdwbm;
    }
}