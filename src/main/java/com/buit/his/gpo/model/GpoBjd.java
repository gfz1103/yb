package com.buit.his.gpo.model;

import java.sql.Timestamp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：GpoBjd<br> 
 * 类描述：GPO报价单
 * @author sunjx
 */
@ApiModel(value="GPO报价单")
public class GpoBjd {

	@ApiModelProperty(value="XH")
    private Integer xh;

	@ApiModelProperty(value="机构编码")
    private Integer jgid;

	@ApiModelProperty(value="GPO机构编码")
    private String gpoJgid;

	@ApiModelProperty(value="报价单编号, 市药事系统返回的可唯一标识的报价单编号")
    private String bjdbh;

	@ApiModelProperty(value="询价单编号, 市药事系统返回的可唯一标识的询价单编号")
    private String xjdbh;

	@ApiModelProperty(value="药企编码, 市药事系统发布的对药企的统编代码")
    private String yqbm;

	@ApiModelProperty(value="药企名称, 报价药企的企业名称")
    private String yqmc;

	@ApiModelProperty(value="商品类型, 详见4.1.5商品类型")
    private String splx;

	@ApiModelProperty(value="药品类型, 详见4.1.1药品类型")
    private String yplx;

	@ApiModelProperty(value="商品统编代码, 统一发布的药品商品唯一标识编码")
    private String zxspbm;

	@ApiModelProperty(value="产品名, 参照国家药监发布的药品/医用材料的“产品名”")
    private String cpm;

	@ApiModelProperty(value="药品剂型, 参照国家药监发布的药品的“剂型”")
    private String ypjx;

	@ApiModelProperty(value="规格, 参照国家药监发布的药品的规格和医用材料的规格型号")
    private String gg;

	@ApiModelProperty(value="包装单位性质, 详见4.1.7包装单位性质")
    private String bzdwxz;

	@ApiModelProperty(value="包装单位名称, 该货品销售计价的基本包装单位名称")
    private String bzdwmc;

	@ApiModelProperty(value="用药单位名称, 该货品基本使用单位的名称，例如，片、支等")
    private String yydwmc;

	@ApiModelProperty(value="包装内含数量, 包装单位内所含基本使用单位的数量")
    private String bznhsl;

	@ApiModelProperty(value="生产企业名称, 参照国家药监发布的“生产单位”")
    private String scqymc;

	@ApiModelProperty(value="药品报价, 对应该药品基础信息销售单位的报价")
    private String ypbj;

	@ApiModelProperty(value="数据状态 -1逻辑删除 1有效")
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

    public void setBjdbh(String value) {
        this.bjdbh = value;
    }
    public String getBjdbh() {
        return bjdbh;
    }

    public void setXjdbh(String value) {
        this.xjdbh = value;
    }
    public String getXjdbh() {
        return xjdbh;
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

    public void setZxspbm(String value) {
        this.zxspbm = value;
    }
    public String getZxspbm() {
        return zxspbm;
    }

    public void setCpm(String value) {
        this.cpm = value;
    }
    public String getCpm() {
        return cpm;
    }

    public void setYpjx(String value) {
        this.ypjx = value;
    }
    public String getYpjx() {
        return ypjx;
    }

    public void setGg(String value) {
        this.gg = value;
    }
    public String getGg() {
        return gg;
    }

    public void setBzdwxz(String value) {
        this.bzdwxz = value;
    }
    public String getBzdwxz() {
        return bzdwxz;
    }

    public void setBzdwmc(String value) {
        this.bzdwmc = value;
    }
    public String getBzdwmc() {
        return bzdwmc;
    }

    public void setYydwmc(String value) {
        this.yydwmc = value;
    }
    public String getYydwmc() {
        return yydwmc;
    }

    public void setBznhsl(String value) {
        this.bznhsl = value;
    }
    public String getBznhsl() {
        return bznhsl;
    }

    public void setScqymc(String value) {
        this.scqymc = value;
    }
    public String getScqymc() {
        return scqymc;
    }

    public void setYpbj(String value) {
        this.ypbj = value;
    }
    public String getYpbj() {
        return ypbj;
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


}