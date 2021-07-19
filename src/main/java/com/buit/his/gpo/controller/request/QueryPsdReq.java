package com.buit.his.gpo.controller.request;

import com.alibaba.excel.util.StringUtils;
import com.buit.his.gpo.dto.GpoBaseDto;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author yueyu
 * @Date 2021/5/4 10:10
 */
public class QueryPsdReq extends GpoBaseDto {

    @ApiModelProperty("上一页编号")
    private String backNo;
    @ApiModelProperty("下一页编号")
    private String nextNo;
    @ApiModelProperty("每页显示条数")
    private Integer pageNum=20;
    @ApiModelProperty("连续号码")
    private List<String> seqQueue;
    @ApiModelProperty("药企编码")
    @NotNull
    private String yqbm;


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public String getBackNo() {
        return backNo;
    }

    public void setBackNo(String backNo) {
        this.backNo = backNo;
    }

    public String getNextNo() {
        return nextNo;
    }

    public void setNextNo(String nextNo) {
        this.nextNo = nextNo;
    }

    public void addSeq(String seq){
        if(seqQueue==null){
            seqQueue=new ArrayList<>();
        }
        seqQueue.add(seq);
    }

    public String getLastSeq(){
        if(seqQueue==null){
            seqQueue=new ArrayList<>();
        }
        if(seqQueue.isEmpty()){
            return StringUtils.EMPTY;
        }else{
            String v = seqQueue.get(seqQueue.size()-1);
            seqQueue.remove(v);
            return v;
        }
    }

    public List<String> getSeqQueue() {
        return seqQueue;
    }

    public void setSeqQueue(List<String> seqQueue) {
        this.seqQueue = seqQueue;
    }

    public String getYqbm() {
        return yqbm;
    }

    public void setYqbm(String yqbm) {
        this.yqbm = yqbm;
    }
}
