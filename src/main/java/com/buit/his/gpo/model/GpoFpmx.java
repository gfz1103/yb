package com.buit.his.gpo.model;

import java.sql.Timestamp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：GpoFpmx<br> 
 * 类描述：GPO发票明细
 * @author sunjx
 */
@ApiModel(value="GPO发票明细")
public class GpoFpmx {

	@ApiModelProperty(value="XH")
    private Integer xh;

	@ApiModelProperty(value="机构编码")
    private Integer jgid;

	@ApiModelProperty(value="GPO机构编码")
    private String gpoJgid;

	@ApiModelProperty(value="发票XH")
    private Integer fpxh;

	@ApiModelProperty(value="是否无配送发票,0：否；1：是 选择“是”的必须与配送名字一一关联，选择“否”的则无需与配送明细关联 ")
    private String sfwpsfp;

	@ApiModelProperty(value="无配送发票说明,无配送的发票必须填写说明原因 ")
    private String wpsfpsm;

	@ApiModelProperty(value="发票明细编号,市药事系统内部可唯一标识的发票明细编号 ")
    private String fpmxbh;

	@ApiModelProperty(value="商品类型,详见4.1.5商品类型 ")
    private String splx;

	@ApiModelProperty(value="是否冲红,标识是否是由于退货而产生的冲红发票明细；0：否，1：是 ")
    private String sfch;

	@ApiModelProperty(value="商品统编代码,统一发布的药品商品唯一标识编码 ")
    private String zxspbm;

	@ApiModelProperty(value="生产批号")
    private String scph;

	@ApiModelProperty(value="生产日期")
    private String scrq;

	@ApiModelProperty(value="商品数量,对应该“商品统编代码+生产批号”的配送数量 ")
    private String spsl;

	@ApiModelProperty(value="关联明细编号,该配送所对应的原始订单的订单明细编号或退货单号，无法关联时可以不填写 ")
    private String glmxbh;

	@ApiModelProperty(value="销售订单号,销售公司用于和发票匹配关联的销售订单号，按配送明细中的销售订单号进行数量和费用的汇总，无法关联时可以不填写，冲红发票无需填写 ")
    private String xsddh;

	@ApiModelProperty(value="顺序号,上报订单的顺序号，同一个订单内的不同明细的顺序号必须唯一 ")
    private String sxh;

	@ApiModelProperty(value="有效日期,格式yyyyMMdd ")
    private String yxrq;

	@ApiModelProperty(value="无税单价,")
    private String wsdj;

	@ApiModelProperty(value="含税单价,")
    private String hsdj;

	@ApiModelProperty(value="税率")
    private String sl;

	@ApiModelProperty(value="税额")
    private String se;

	@ApiModelProperty(value="含税金额")
    private String hsje;

	@ApiModelProperty(value="批发价")
    private String pfj;

	@ApiModelProperty(value="零售价")
    private String lsj;

	@ApiModelProperty(value="药品批准文号")
    private String pzwh;

	@ApiModelProperty(value="数据状态 -1物理删除 1有效")
    private String zt;

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

    public void setFpxh(Integer value) {
        this.fpxh = value;
    }
    public Integer getFpxh() {
        return fpxh;
    }

    public void setSfwpsfp(String value) {
        this.sfwpsfp = value;
    }
    public String getSfwpsfp() {
        return sfwpsfp;
    }

    public void setWpsfpsm(String value) {
        this.wpsfpsm = value;
    }
    public String getWpsfpsm() {
        return wpsfpsm;
    }

    public void setFpmxbh(String value) {
        this.fpmxbh = value;
    }
    public String getFpmxbh() {
        return fpmxbh;
    }

    public void setSplx(String value) {
        this.splx = value;
    }
    public String getSplx() {
        return splx;
    }

    public void setSfch(String value) {
        this.sfch = value;
    }
    public String getSfch() {
        return sfch;
    }

    public void setZxspbm(String value) {
        this.zxspbm = value;
    }
    public String getZxspbm() {
        return zxspbm;
    }

    public void setScph(String value) {
        this.scph = value;
    }
    public String getScph() {
        return scph;
    }

    public void setScrq(String value) {
        this.scrq = value;
    }
    public String getScrq() {
        return scrq;
    }

    public void setSpsl(String value) {
        this.spsl = value;
    }
    public String getSpsl() {
        return spsl;
    }

    public void setGlmxbh(String value) {
        this.glmxbh = value;
    }
    public String getGlmxbh() {
        return glmxbh;
    }

    public void setXsddh(String value) {
        this.xsddh = value;
    }
    public String getXsddh() {
        return xsddh;
    }

    public void setSxh(String value) {
        this.sxh = value;
    }
    public String getSxh() {
        return sxh;
    }

    public void setYxrq(String value) {
        this.yxrq = value;
    }
    public String getYxrq() {
        return yxrq;
    }

    public void setWsdj(String value) {
        this.wsdj = value;
    }
    public String getWsdj() {
        return wsdj;
    }

    public void setHsdj(String value) {
        this.hsdj = value;
    }
    public String getHsdj() {
        return hsdj;
    }

    public void setSl(String value) {
        this.sl = value;
    }
    public String getSl() {
        return sl;
    }

    public void setSe(String value) {
        this.se = value;
    }
    public String getSe() {
        return se;
    }

    public void setHsje(String value) {
        this.hsje = value;
    }
    public String getHsje() {
        return hsje;
    }

    public void setPfj(String value) {
        this.pfj = value;
    }
    public String getPfj() {
        return pfj;
    }

    public void setLsj(String value) {
        this.lsj = value;
    }
    public String getLsj() {
        return lsj;
    }

    public void setPzwh(String value) {
        this.pzwh = value;
    }
    public String getPzwh() {
        return pzwh;
    }

    public void setZt(String value) {
        this.zt = value;
    }
    public String getZt() {
        return zt;
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