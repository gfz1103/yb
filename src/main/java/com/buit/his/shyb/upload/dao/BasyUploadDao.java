package com.buit.his.shyb.upload.dao;

import com.buit.his.shyb.upload.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 病案首页上传
 */
@Mapper
public interface BasyUploadDao {

    /**
     * 病案首页主表上传数据
     * @return
     */
    List<BasyUpload>  basyUpload();

    /**
     * 病案首页诊断上传数据
     * @return
     */
    List<BasyZdUpload> basyZdUpload(@Param("yljgd") Integer yljgd,@Param("bah") String bah);

    /**
     * 病案首页手术上传数据
     * @param yljgd
     * @param bah
     * @return
     */
    List<BasySsUpload> basySsUpload(@Param("yljgd") Integer yljgd,@Param("bah") String bah);

    /**
     * 病案首页费用上传数据
     * @param yljgd
     * @param bah
     * @return
     */
    List<BasyFyUpload> basyFyUpload(@Param("yljgd") Integer yljgd,@Param("bah") String bah);

    /**
     * 查询患者住院总费用信息
     * @param yljgd
     * @param zyh
     * @return
     */
    List<BasyTotalFee> basyTotalFee(@Param("yljgd") Integer yljgd,@Param("zyh") String zyh);
}
