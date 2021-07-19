package com.buit.his.gpo.model;

import java.sql.Timestamp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：GpoYpkccb<br> 
 * 类描述：GPO药品当前库存量数据传报
 * @author sunjx
 */
@ApiModel(value="GPO药品当前库存量数据传报")
public class GpoYpkccb {

	@ApiModelProperty(value="XH")
    private Integer xh;

	@ApiModelProperty(value="机构编码")
    private Integer jgid;

	@ApiModelProperty(value="传报时间, 必填 格式yyyyMMdd/HHmmss")
    private String kccbsj;

	@ApiModelProperty(value="记录数, 必填 本消息内的<DETAIL>记录条数")
    private String jls;

	@ApiModelProperty(value="处理结果, 详见4.1.12消息明细条目处理结果")
    private String cljg;

	@ApiModelProperty(value="处理情况描述, 消息明细条目处理结果的详细描述")
    private String clqkms;

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

    public void setKccbsj(String value) {
        this.kccbsj = value;
    }
    public String getKccbsj() {
        return kccbsj;
    }

    public void setJls(String value) {
        this.jls = value;
    }
    public String getJls() {
        return jls;
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