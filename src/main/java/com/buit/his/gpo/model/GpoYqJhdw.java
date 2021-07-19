package com.buit.his.gpo.model;

import java.sql.Timestamp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名称：GpoYqJhdw<br> 
 * 类描述：GPO药企与医院进货单位对照表
 * @author sunjx
 */
@ApiModel(value="GPO药企与医院进货单位对照表")
public class GpoYqJhdw {

	@ApiModelProperty(value="XH")
    private Integer xh;

	@ApiModelProperty(value="机构编码")
    private Integer jgid;

	@ApiModelProperty(value="药企ID shyb_yppsqy")
    private Integer yqid;

	@ApiModelProperty(value="进货单位ID drugs_jhdw")
    private Integer jhdwid;

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

    public void setYqid(Integer value) {
        this.yqid = value;
    }
    public Integer getYqid() {
        return yqid;
    }

    public void setJhdwid(Integer value) {
        this.jhdwid = value;
    }
    public Integer getJhdwid() {
        return jhdwid;
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