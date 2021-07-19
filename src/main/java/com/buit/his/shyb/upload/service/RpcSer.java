package com.buit.his.shyb.upload.service;

import com.buit.emr.service.EmrFileIndexService;
import com.buit.system.model.DicXzqh;
import com.buit.system.request.DicGbsj02Model;
import com.buit.system.response.DicKszdModel;
import com.buit.system.response.DicYljgOut;
import com.buit.system.response.DictDto;
import com.buit.system.response.HrPersonnelModel;
import com.buit.system.service.*;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author weijing
 * @Date 2020-10-19 14:14
 * @Description
 **/
@Service
public class RpcSer {
    @DubboReference
    private DicGbsj01Service dicGbsj01Service;

    @DubboReference
    private DicGbsj02Service dicGbsj02Service;

    @DubboReference
    private HrPersonnelService hrPersonnelService;//查询医生信息

    @DubboReference
    private DicXzqhService dicXzqhService;

    @DubboReference
    private DicYljgOutSer dicYljgOutSer;

    @DubboReference
    private DicKszdOutSer dicKszdOutSer;

    @DubboReference
    private SysFlagDataValueOutSer sysFlagDataValueOutSer;

    @DubboReference
    private EmrFileIndexService emrFileIndexService;

    //    @DubboReference
//    private ExportFileSer exportFileSer;//导出打印公共类


    /**
     * 通过住院号判断 当前患者的病例数据是否完全提交
     * @param zyh
     * @return
     */
    public boolean queryCommitStatus(Integer zyh){
        return emrFileIndexService.queryCommitStatus(zyh);
    }

    /**
     * rpc[system-interface] 获取常量数据
     */
    public List<DicGbsj02Model> getSysData(String condition){
        DicGbsj02Model dicGbsj02Model = new DicGbsj02Model();
        dicGbsj02Model.setPrimarydataId(Integer.valueOf(condition));
        return dicGbsj02Service.findByEntity(dicGbsj02Model);
    }

    /**
     * rpc[system-interface]查询医生信息
     * @return
     */
    public List<HrPersonnelModel> findDoctorList(Integer yljgdm){
        HrPersonnelModel personnelModel = new HrPersonnelModel();
        personnelModel.setOrganizcode(yljgdm);
        List<HrPersonnelModel> doctorList = hrPersonnelService.findByEntity(personnelModel);
        return doctorList;
    }

    /**
     * rpc[system-interface]查询省市县
     * @return
     */
    public List<DicXzqh> findSSXList(){
        DicXzqh xzqh = new DicXzqh();
        List<DicXzqh> list = dicXzqhService.findByEntity(xzqh);
        return list;
    }

    /**
     * rpc[system-interface] 机构id查询机构名称
     * @param hospitalId
     * @return
     */
    public DicYljgOut findJGMC(Integer hospitalId){
        return dicYljgOutSer.getById(hospitalId);
    }

    /**
     * rpc[system-interface] 科室编码查询科室信息
     * @param ksdm
     * @return
     */
    public String findKSMC(Integer ksdm){
        return dicKszdOutSer.getNameById(ksdm);
    }

    /**
     * 查询医院的科室列表
     * @param hospitalId
     * @return
     */
    public List<DicKszdModel> findKsList(Integer hospitalId){
        return dicKszdOutSer.queryZyBrksDic(hospitalId);
    }

    /**
     * rpc[system-interface] 查询职业,婚姻等系统标识字典
     * @param dataId
     * @param code
     * @return
     */
    public DictDto findSysFlag(String dataId, String code){
        return sysFlagDataValueOutSer.findFlagValueByDataIdAndValueCode(dataId, code);
    }

}
