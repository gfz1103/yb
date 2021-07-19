package com.buit.his.gpo.model;

import java.sql.Timestamp;
import com.buit.commons.PageQuery;

/**
 * 类名称：GpoPsdys<br> 
 * 类描述：
 * @author DESKTOP-P9STS99 
 * @ApiModel(value="")
 */
public class GpoPsdys  extends  PageQuery{
	//@ApiModelProperty(value="配送单明细验收")
    /** 配送单明细验收 */
    private Integer xh;
	//@ApiModelProperty(value="药企编码 必填 市药事系统对药企的统编代码")
    /** 药企编码 必填 市药事系统对药企的统编代码 */
    private String yqbm;
	//@ApiModelProperty(value="配送明细编号 必填 市药事系统可唯一标识的配送明细编号")
    /** 配送明细编号 必填 市药事系统可唯一标识的配送明细编号 */
    private String psmxbh;
	//@ApiModelProperty(value="配送单条码 必填 配送单条码")
    /** 配送单条码 必填 配送单条码 */
    private String psdtm;
	//@ApiModelProperty(value="商品统编代码 必填 统一发布的药品商品唯一标识编码")
    /** 商品统编代码 必填 统一发布的药品商品唯一标识编码 */
    private String zxspbm;
	//@ApiModelProperty(value="配送数量 必填 该次配送的同一装箱且同一货品且同一生产批号按药品基础信息中包装单位名称所界定的货品配送数量")
    /** 配送数量 必填 该次配送的同一装箱且同一货品且同一生产批号按药品基础信息中包装单位名称所界定的货品配送数量 */
    private String psl;
	//@ApiModelProperty(value="生产批号")
    /** 生产批号 */
    private String scph;
	//@ApiModelProperty(value="验收结果 0验收不通过  1验收通过")
    /** 验收结果 0验收不通过  1验收通过 */
    private Integer ysjg;
	//@ApiModelProperty(value="验收结果备注")
    /** 验收结果备注 */
    private String ysjgbz;
	//@ApiModelProperty(value="ctime")
    /** ctime */
    private Timestamp ctime;
	//@ApiModelProperty(value="cuser")
    /** cuser */
    private String cuser;
	//@ApiModelProperty(value="cuname")
    /** cuname */
    private String cuname;
    private String psdbm;
    private String psdh;
    private Integer yplx;
    private String yxrq;
    private String jhdh;
    private String xsddh;
    private String ddmxbm;

    public String getPsdbm() {
        return psdbm;
    }

    public void setPsdbm(String psdbm) {
        this.psdbm = psdbm;
    }

    public String getPsdh() {
        return psdh;
    }

    public void setPsdh(String psdh) {
        this.psdh = psdh;
    }

    public Integer getYplx() {
        return yplx;
    }

    public void setYplx(Integer yplx) {
        this.yplx = yplx;
    }

    public String getYxrq() {
        return yxrq;
    }

    public void setYxrq(String yxrq) {
        this.yxrq = yxrq;
    }

    public String getJhdh() {
        return jhdh;
    }

    public void setJhdh(String jhdh) {
        this.jhdh = jhdh;
    }

    public String getXsddh() {
        return xsddh;
    }

    public void setXsddh(String xsddh) {
        this.xsddh = xsddh;
    }

    public String getDdmxbm() {
        return ddmxbm;
    }

    public void setDdmxbm(String ddmxbm) {
        this.ddmxbm = ddmxbm;
    }

    /** 设置:配送单明细验收  */
    public void setXh(Integer value) {
        this.xh = value;
    }
    /** 获取:配送单明细验收 */
    public Integer getXh() {
        return xh;
    }

    /** 设置:药企编码 必填 市药事系统对药企的统编代码  */
    public void setYqbm(String value) {
        this.yqbm = value;
    }
    /** 获取:药企编码 必填 市药事系统对药企的统编代码 */
    public String getYqbm() {
        return yqbm;
    }

    /** 设置:配送明细编号 必填 市药事系统可唯一标识的配送明细编号  */
    public void setPsmxbh(String value) {
        this.psmxbh = value;
    }
    /** 获取:配送明细编号 必填 市药事系统可唯一标识的配送明细编号 */
    public String getPsmxbh() {
        return psmxbh;
    }

    /** 设置:配送单条码 必填 配送单条码  */
    public void setPsdtm(String value) {
        this.psdtm = value;
    }
    /** 获取:配送单条码 必填 配送单条码 */
    public String getPsdtm() {
        return psdtm;
    }

    /** 设置:商品统编代码 必填 统一发布的药品商品唯一标识编码  */
    public void setZxspbm(String value) {
        this.zxspbm = value;
    }
    /** 获取:商品统编代码 必填 统一发布的药品商品唯一标识编码 */
    public String getZxspbm() {
        return zxspbm;
    }

    /** 设置:配送数量 必填 该次配送的同一装箱且同一货品且同一生产批号按药品基础信息中包装单位名称所界定的货品配送数量  */
    public void setPsl(String value) {
        this.psl = value;
    }
    /** 获取:配送数量 必填 该次配送的同一装箱且同一货品且同一生产批号按药品基础信息中包装单位名称所界定的货品配送数量 */
    public String getPsl() {
        return psl;
    }

    /** 设置:生产批号  */
    public void setScph(String value) {
        this.scph = value;
    }
    /** 获取:生产批号 */
    public String getScph() {
        return scph;
    }

    public Integer getYsjg() {
        return ysjg;
    }

    public void setYsjg(Integer ysjg) {
        this.ysjg = ysjg;
    }

    /** 设置:验收结果备注  */
    public void setYsjgbz(String value) {
        this.ysjgbz = value;
    }
    /** 获取:验收结果备注 */
    public String getYsjgbz() {
        return ysjgbz;
    }

    /** 设置:ctime  */
    public void setCtime(Timestamp value) {
        this.ctime = value;
    }
    /** 获取:ctime */
    public Timestamp getCtime() {
        return ctime;
    }

    /** 设置:cuser  */
    public void setCuser(String value) {
        this.cuser = value;
    }
    /** 获取:cuser */
    public String getCuser() {
        return cuser;
    }

    /** 设置:cuname  */
    public void setCuname(String value) {
        this.cuname = value;
    }
    /** 获取:cuname */
    public String getCuname() {
        return cuname;
    }


}