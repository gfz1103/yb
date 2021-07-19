package com.buit.his.gpo.model;

import java.sql.Timestamp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：GpoYpkccbxqmx<br> 
 * 类描述：GPO药品当前库存量数据传报明细
 * @author sunjx
 */
@ApiModel(value="GPO药品当前库存量数据传报明细")
public class GpoYpkccbxqmx {

	@ApiModelProperty(value="XH")
    private Integer xh;

	@ApiModelProperty(value="机构编码")
    private Integer jgid;

	@ApiModelProperty(value="商品类型, 必填 详见4.1.5商品类型")
    private String splx;

	@ApiModelProperty(value="药品类型, 必填 详见4.1.1药品类型，非药品无需填写")
    private String yplx;

	@ApiModelProperty(value="商品统编代码, 必填 统一发布的药品商品唯一标识编码")
    private String zxspbm;

	@ApiModelProperty(value="医院商品编码, 必填 医院内部的药品商品唯一标识编码")
    private String yyspbm;

	@ApiModelProperty(value="配送点编码, 必填 医院内部配送点编码，如西药库编码")
    private String psdbm;

	@ApiModelProperty(value="当前库存量, 必填 按药品包装单位进行盘点的汇总剩余库存量，当前库存量不能是负数")
    private String ypkcl;

	@ApiModelProperty(value="库存单位, 必填 库存计量单位")
    private String kcdw;

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

    public void setYyspbm(String value) {
        this.yyspbm = value;
    }
    public String getYyspbm() {
        return yyspbm;
    }

    public void setPsdbm(String value) {
        this.psdbm = value;
    }
    public String getPsdbm() {
        return psdbm;
    }

    public void setYpkcl(String value) {
        this.ypkcl = value;
    }
    public String getYpkcl() {
        return ypkcl;
    }

    public void setKcdw(String value) {
        this.kcdw = value;
    }
    public String getKcdw() {
        return kcdw;
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