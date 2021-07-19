package com.buit.his.gpo.model;

import java.sql.Timestamp;
import java.util.List;

import com.buit.his.gpo.dto.YpbzzhDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * 类名称：GpoDdmx<br> 
 * 类描述：GPO订单明细
 * @author sunjx
 */
@ApiModel(value="GPO订单明细")
public class GpoDdmx {

	@ApiModelProperty(value="XH")
    private Integer xh;

	@ApiModelProperty(value="机构编码")
    private Integer jgid;

	@ApiModelProperty(value="GPO机构编码")
    private String gpoJgid;

	@ApiModelProperty(value="订单XH")
    private Integer ddxh;

	@ApiModelProperty(value="订单编号 市药事系统返回的可唯一标识此订单的编号")
    private String ddbh;

	@ApiModelProperty(value="订单明细编号 市药事系统返回的可唯一标识此订单明细的编号")
    private String ddmxbh;

	@ApiModelProperty(value="顺序号 必填 上报订单的顺序号，同一个订单内的不同明细的顺序号必须唯一")
    private String sxh;
    @NotBlank(message = "采购类型不能为空")
	@ApiModelProperty(value="采购类型 必填 详见4.1.2采购类型")
    private String cglx;
    @NotBlank(message = "商品类型不能为空")
	@ApiModelProperty(value="商品类型 必填 详见4.1.5商品类型")
    private String splx;

	@ApiModelProperty(value="商品名称")
    private String spmc;

	@ApiModelProperty(value="商品统编代码 必填 统一发布的药品商品唯一标识编码")
    private String zxspbm;

	@ApiModelProperty(value="规格包装 选填 医院采购药品的规格包装要求，具体用于区分中药饮片的中、大包装说明，必须需要使用已上传到中心平台的规格包装字典。")
    private String ggbz;
    @NotBlank(message = "采购计量单位不能为空")
	@ApiModelProperty(value="采购计量单位")
    private String cgjldw;
    @NotBlank(message = "采购数量不能为空")
	@ApiModelProperty(value="采购数量 必填 该品规药品的采购数量")
    private String cgsl;

	@ApiModelProperty(value="采购单价 选填 带量采购无需填写，其他情况采购需要填写")
    private String cgdj;

	@ApiModelProperty("转换包装系数，入库数量=采购数量/转换包装系数")
	private Integer bzzh;

	@ApiModelProperty("包装名称")
	private String dwmc;

	@ApiModelProperty(value="药企编码 必填 指明由哪家配送供货。市药事系统对药企的统编代码")
    private String yqbm;

	@ApiModelProperty("药企名称")
	private String yqmc;

	@ApiModelProperty(value="多次配送标识 必填 是否允许药企分多天进行配送，若不允许则该订单只允许在一天内完成所有的配送，否则可以分多天配送。")
    private String dcpsbs;

	@ApiModelProperty(value="备注说明 选填")
    private String bzsm;

	@ApiModelProperty(value="医院商品编码 预留字段，暂不使用")
    private String yyspbm;

	@ApiModelProperty(value="处理结果 详见4.1.12消息明细条目处理结果")
    private String cljg;

	@ApiModelProperty(value="处理情况描述 消息明细条目处理结果的详细描述")
    private String clqkms;

	@ApiModelProperty(value="数据状态 -1物理删除 1有效")
    private String zt;

	@ApiModelProperty(value="ctime")
    private Timestamp ctime;

	@ApiModelProperty(value="cuser")
    private String cuser;

	@ApiModelProperty(value="cuname")
    private String cuname;

    @ApiModelProperty("产地名称")
	private String cdmc;

    private List<YpbzzhDto> zhbzList;

    public String getCdmc() {
        return cdmc;
    }

    public void setCdmc(String cdmc) {
        this.cdmc = cdmc;
    }

    public List<YpbzzhDto> getZhbzList() {
        return zhbzList;
    }

    public void setZhbzList(List<YpbzzhDto> zhbzList) {
        this.zhbzList = zhbzList;
    }

    public String getYqmc() {
        return yqmc;
    }

    public void setYqmc(String yqmc) {
        this.yqmc = yqmc;
    }

    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    public Integer getBzzh() {
        return bzzh;
    }

    public void setBzzh(Integer bzzh) {
        this.bzzh = bzzh;
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

    public void setDdxh(Integer value) {
        this.ddxh = value;
    }
    public Integer getDdxh() {
        return ddxh;
    }

    public void setDdbh(String value) {
        this.ddbh = value;
    }
    public String getDdbh() {
        return ddbh;
    }

    public void setDdmxbh(String value) {
        this.ddmxbh = value;
    }
    public String getDdmxbh() {
        return ddmxbh;
    }

    public void setSxh(String value) {
        this.sxh = value;
    }
    public String getSxh() {
        return sxh;
    }

    public void setCglx(String value) {
        this.cglx = value;
    }
    public String getCglx() {
        return cglx;
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

    public void setCgsl(String value) {
        this.cgsl = value;
    }
    public String getCgsl() {
        return cgsl;
    }

    public void setCgdj(String value) {
        this.cgdj = value;
    }
    public String getCgdj() {
        return cgdj;
    }

    public void setYqbm(String value) {
        this.yqbm = value;
    }
    public String getYqbm() {
        return yqbm;
    }

    public void setDcpsbs(String value) {
        this.dcpsbs = value;
    }
    public String getDcpsbs() {
        return dcpsbs;
    }

    public void setBzsm(String value) {
        this.bzsm = value;
    }
    public String getBzsm() {
        return bzsm;
    }

    public void setYyspbm(String value) {
        this.yyspbm = value;
    }
    public String getYyspbm() {
        return yyspbm;
    }

    public void setCljg(String value) {
        this.cljg = value;
    }
    public String getCljg() {
        return cljg;
    }

    public void setClqkms(String value) {
        this.clqkms = value;
    }
    public String getClqkms() {
        return clqkms;
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