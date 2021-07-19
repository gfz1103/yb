package com.buit.his.gpo.model;

import java.sql.Timestamp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：GpoThd<br> 
 * 类描述：GPO退货单
 * @author sunjx
 */
@ApiModel(value="GPO退货单")
public class GpoThd {

	@ApiModelProperty(value="xh")
    private Integer xh;

	@ApiModelProperty(value="机构编码")
    private Integer jgid;

	@ApiModelProperty(value="必填 详见4.1.6操作类型")
    private String czlx;

	@ApiModelProperty(value="必填 市药事系统发布的对医院的统编代码")
    private String yybm;

	@ApiModelProperty(value="必填 医院预先提交的医院内配送点编码")
    private String psdbm;

	@ApiModelProperty(value="药企名称")
    private String yqmc;

	@ApiModelProperty(value="必填 市药事系统对药企的统编代码")
    private String yqbm;

	@ApiModelProperty(value="选填 市药事系统返回的退货单编号。")
    private String thdbh;

	@ApiModelProperty(value="必填 标识是否带量采购的退货单。0：否，1：是")
    private String dlcgbz;

	@ApiModelProperty(value="必填 详见4.1.5商品类型")
    private String splx;

	@ApiModelProperty(value="商品名称")
    private String spmc;

	@ApiModelProperty(value="必填 统一发布的药品商品唯一标识编码")
    private String zxspbm;

	@ApiModelProperty(value="医院采购药品的规格包装要求，具体用于区分中药饮片的中、大包装说明")
    private String ggbz;

	@ApiModelProperty(value="必填 详见4.1.21采购计量单位")
    private String cgjldw;

	@ApiModelProperty(value="必填 药品生产的生产批号")
    private String scph;

	@ApiModelProperty(value="必填 该品规药品按原发货包装单位的退货数量")
    private String thsl;

	@ApiModelProperty(value="必填 该品规药品按原包装的进货单价")
    private String thdj;

	@ApiModelProperty(value="必填 该品规药品本次退货的总金额")
    private String thzj;

	@ApiModelProperty(value="必填 本次退货的原因")
    private String thyy;

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

    public String getPsdmc() {
        return psdmc;
    }

    public void setPsdmc(String psdmc) {
        this.psdmc = psdmc;
    }

    @ApiModelProperty(value="配送点名称")
    private String psdmc;

	@ApiModelProperty(value="cuser")
    private Integer cuser;

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

    public void setCzlx(String value) {
        this.czlx = value;
    }
    public String getCzlx() {
        return czlx;
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

    public void setYqmc(String value) {
        this.yqmc = value;
    }
    public String getYqmc() {
        return yqmc;
    }

    public void setYqbm(String value) {
        this.yqbm = value;
    }
    public String getYqbm() {
        return yqbm;
    }

    public void setThdbh(String value) {
        this.thdbh = value;
    }
    public String getThdbh() {
        return thdbh;
    }

    public void setDlcgbz(String value) {
        this.dlcgbz = value;
    }
    public String getDlcgbz() {
        return dlcgbz;
    }

    public void setSplx(String value) {
        this.splx = value;
    }
    public String getSplx() {
        return splx;
    }

    public void setSpmc(String value) {
        this.spmc = value;
    }
    public String getSpmc() {
        return spmc;
    }

    public void setZxspbm(String value) {
        this.zxspbm = value;
    }
    public String getZxspbm() {
        return zxspbm;
    }

    public void setGgbz(String value) {
        this.ggbz = value;
    }
    public String getGgbz() {
        return ggbz;
    }

    public void setCgjldw(String value) {
        this.cgjldw = value;
    }
    public String getCgjldw() {
        return cgjldw;
    }

    public void setScph(String value) {
        this.scph = value;
    }
    public String getScph() {
        return scph;
    }

    public void setThsl(String value) {
        this.thsl = value;
    }
    public String getThsl() {
        return thsl;
    }

    public void setThdj(String value) {
        this.thdj = value;
    }
    public String getThdj() {
        return thdj;
    }

    public void setThzj(String value) {
        this.thzj = value;
    }
    public String getThzj() {
        return thzj;
    }

    public void setThyy(String value) {
        this.thyy = value;
    }
    public String getThyy() {
        return thyy;
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

    public void setCuser(Integer value) {
        this.cuser = value;
    }
    public Integer getCuser() {
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