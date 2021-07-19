package com.buit.his.gpo.model;

import java.sql.Timestamp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：GpoFp<br> 
 * 类描述：GPO发票
 * @author sunjx
 */
@ApiModel(value="GPO发票")
public class GpoFp {

	@ApiModelProperty(value="XH")
    private Integer xh;

	@ApiModelProperty(value="机构编码")
    private Integer jgid;

	@ApiModelProperty(value="GPO机构编码")
    private String gpoJgid;

	@ApiModelProperty(value="发票ID 市药事系统生成的发票唯一标识编号")
    private String fpid;

	@ApiModelProperty(value="发票号  药企内可唯一标识该发票的编号")
    private String fph;

	@ApiModelProperty(value="药企编码  市药事系统对药企的统编代码，若不填写则查询所有符合条件的发票信息")
    private String yqbm;

	@ApiModelProperty(value="药企名称  药企编码所对应的药企名称")
    private String yqmc;

	@ApiModelProperty(value="发票日期  发票开具日期，格式yyyyMMdd")
    private String fprq;

	@ApiModelProperty(value="发票含税总金额")
    private String fphszje;

	@ApiModelProperty(value="医院编码  市药事系统对医院的统编代码")
    private String yybm;

	@ApiModelProperty(value="配送点编码  医院预先提交的医院内配送点编码")
    private String psdbm;

	@ApiModelProperty(value="带量采购标志  标识是否带量采购的发票。0：否，1：是")
    private String dlcgbz;

	@ApiModelProperty(value="发票备注  备注说明")
    private String fpbz;

	@ApiModelProperty(value="发票状态  详见4.1.17发票状态")
    private String fpzt;

	@ApiModelProperty(value="验收状态 0待验收 1已验收")
    private String yszt;

	@ApiModelProperty(value="验收时间")
    private Timestamp yssj;

	@ApiModelProperty(value="验收人代码")
    private String ysrdm;

	@ApiModelProperty(value="对账状态 0待对账 1已对账")
    private String dzzt;

	@ApiModelProperty(value="对账时间")
    private Timestamp dzsj;

	@ApiModelProperty(value="对账人代码")
    private String dzrdm;

	@ApiModelProperty(value="入库状态 0待入库 1已入库")
    private String rkzt;

	@ApiModelProperty(value="入库时间")
    private Timestamp rksj;

	@ApiModelProperty(value="入库单号")
    private String rkdh;

	@ApiModelProperty(value="入库人代码")
    private String rkrdm;

	@ApiModelProperty(value="支付状态 0待支付 1已支付")
    private String zfzt;

	@ApiModelProperty(value="支付时间")
    private Timestamp zfsj;

	@ApiModelProperty(value="支付人代码")
    private String zfrdm;

	@ApiModelProperty(value="数据状态 -1物理删除 1有效")
    private String zt;

	@ApiModelProperty(value="删除时间")
    private Timestamp scsj;

	@ApiModelProperty(value="删除人代码")
    private String scrdm;

	@ApiModelProperty(value="ctime")
    private Timestamp ctime;

	@ApiModelProperty(value="cuser")
    private String cuser;

	@ApiModelProperty(value="cuname")
    private String cuname;

	@ApiModelProperty(value="药库类型 1西药库 2中草药库")
    private String yklx;

	@ApiModelProperty(value="药库识别")
    private Integer yksb;


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

    public void setFpid(String value) {
        this.fpid = value;
    }
    public String getFpid() {
        return fpid;
    }

    public void setFph(String value) {
        this.fph = value;
    }
    public String getFph() {
        return fph;
    }

    public void setYqbm(String value) {
        this.yqbm = value;
    }
    public String getYqbm() {
        return yqbm;
    }

    public void setYqmc(String value) {
        this.yqmc = value;
    }
    public String getYqmc() {
        return yqmc;
    }

    public void setFprq(String value) {
        this.fprq = value;
    }
    public String getFprq() {
        return fprq;
    }

    public void setFphszje(String value) {
        this.fphszje = value;
    }
    public String getFphszje() {
        return fphszje;
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

    public void setDlcgbz(String value) {
        this.dlcgbz = value;
    }
    public String getDlcgbz() {
        return dlcgbz;
    }

    public void setFpbz(String value) {
        this.fpbz = value;
    }
    public String getFpbz() {
        return fpbz;
    }

    public void setFpzt(String value) {
        this.fpzt = value;
    }
    public String getFpzt() {
        return fpzt;
    }

    public void setYszt(String value) {
        this.yszt = value;
    }
    public String getYszt() {
        return yszt;
    }

    public void setYssj(Timestamp value) {
        this.yssj = value;
    }
    public Timestamp getYssj() {
        return yssj;
    }

    public void setYsrdm(String value) {
        this.ysrdm = value;
    }
    public String getYsrdm() {
        return ysrdm;
    }

    public void setDzzt(String value) {
        this.dzzt = value;
    }
    public String getDzzt() {
        return dzzt;
    }

    public void setDzsj(Timestamp value) {
        this.dzsj = value;
    }
    public Timestamp getDzsj() {
        return dzsj;
    }

    public void setDzrdm(String value) {
        this.dzrdm = value;
    }
    public String getDzrdm() {
        return dzrdm;
    }

    public void setRkzt(String value) {
        this.rkzt = value;
    }
    public String getRkzt() {
        return rkzt;
    }

    public void setRksj(Timestamp value) {
        this.rksj = value;
    }
    public Timestamp getRksj() {
        return rksj;
    }

    public void setRkdh(String value) {
        this.rkdh = value;
    }
    public String getRkdh() {
        return rkdh;
    }

    public void setRkrdm(String value) {
        this.rkrdm = value;
    }
    public String getRkrdm() {
        return rkrdm;
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


}