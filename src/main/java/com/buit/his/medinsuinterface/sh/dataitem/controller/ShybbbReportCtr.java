package com.buit.his.medinsuinterface.sh.dataitem.controller;

import com.buit.commons.BaseSpringController;
import com.buit.commons.SysUser;
import com.buit.his.medinsuinterface.sh.dataitem.response.*;
import com.buit.his.medinsuinterface.sh.dataitem.service.ShybbbReportSer;
import com.buit.his.shyb.source.dao.ShybSi51Dao;
import com.buit.his.shyb.source.entity.SettleResultDTO;
import com.buit.his.shyb.source.entity.business.MessageBody;
import com.buit.his.shyb.source.enums.MessageTypeEnum;
import com.buit.his.shyb.source.enums.TradingChannelEnum;
import com.buit.his.shyb.source.model.MedicalInsuranceModel;
import com.buit.his.shyb.source.service.impl.OfflineSettleService;
import com.buit.his.shyb.source.service.impl.OfflineSettleServiceImpl;
import com.buit.system.service.ExportFileSer;
import com.buit.system.utill.MedicineUtils;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.buit.utill.StrUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ObjectInputValidation;
import java.util.*;

/**
 * 挂号收费报表<br>
 *
 * @author WY
 */
@Api(tags = "医保报表拆分打印")
@Controller
@RequestMapping("/shybbbReport")
public class ShybbbReportCtr  extends BaseSpringController {

    @DubboReference
    private ExportFileSer exportFileSer;

    @Autowired
    private ShybbbReportSer shybbbReportSer;

    @Autowired
    private ShybSi51Dao shybSi51Dao;
    /**
     * 城镇普通1A
     *
     * @param
     * @return
     */
    @RequestMapping("/czptsbba")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "城镇普通1A#", httpMethod = "POST", notes = "城镇普通1#")
    public ReturnEntity<CzptSbbHzResp> cityNormalTableView(
                @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
                @ApiParam(name = "datefrom", value = "日期开始") @RequestParam(value = "datefrom") String datefrom,
                @ApiParam(name = "dateto", value = "日期结束") @RequestParam(value = "dateto") String dateto,
                @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb
          //  , @ApiParam(name = "pageno", value = "页数") @RequestParam(value = "pageno") int pageno
    ) {
        List<Map<String,Object>> list  = shybbbReportSer.QueryYbCityNormalListire(daa,datefrom,dateto,mzlb);
        CzptSbbHzResp czhzresp = shybbbReportSer.getFields(list,datefrom,getUser());
        //List<Map<String,Object>> record = shybbbReportSer.getFields(list);
        int page = 0 ;
       /* if(list.size()>0 && pageno==1){
            if(list.size()%20==0){
                page = list.size()/20;
            }else{
                page = list.size()/20+1;
            }
        }
        SysUser user = getUser();
        String url = exportFileSer.reportHtml(shybbbReportSer.getFields(list),
                shybbbReportSer.getParameters(datefrom,user,pageno,list),
                "Ybbb001A.jasper");
        YbbburlResp ybu= new YbbburlResp();
        ybu.setPage(page);
        ybu.setUrl(url);*/
        return ReturnEntityUtil.success(czhzresp);
    }

    /**
     * 城镇普通1B
     *
     * @param
     * @return
     */
    @RequestMapping("/czptsbbb")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "城镇普通1B#", httpMethod = "POST", notes = "城镇普通1#B")
    public ReturnEntity<CzptSbbHzBResp> cityNormalTableBView(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "就诊卡号") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "就诊卡号") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb) {
        List<Map<String,Object>> list  = shybbbReportSer.QueryYbCityNormalListire(daa,datefrom,dateto,mzlb);
        CzptSbbHzBResp czhzbresp = shybbbReportSer.getFieldsB(list,datefrom,getUser());
      /*  int page = 0 ;
        if(list.size()>0 && pageno==1){
            if(list.size()%20==0){
                page = list.size()/20;
            }else{
                page = list.size()/20+1;
            }
        }
        SysUser user = getUser();
        String url = exportFileSer.reportHtml(shybbbReportSer.getFieldsB(list),
                shybbbReportSer.getParametersB(datefrom,dateto,user,pageno,list),
                "Ybbb001B.jasper");
        YbbburlResp ybu= new YbbburlResp();
        ybu.setPage(page);
        ybu.setUrl(url);*/
        return ReturnEntityUtil.success(czhzbresp);
    }

    /**
     * 城镇普通1C
     *
     * @param
     * @return
     */
    @RequestMapping("/czptsbbc")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "城镇普通1C#", httpMethod = "POST", notes = "城镇普通1#C")
    public ReturnEntity<Map> cityNormalTableCView(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "开始日期") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "结束日期") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb) {
        SysUser user = getUser();
        List<Map<String,Object>> list  = shybbbReportSer.QueryYbCityNormalListire(daa,datefrom,dateto,mzlb);
        Map<String, Object > map = shybbbReportSer.getParametersC(datefrom,getUser(),list);
       /* String url = exportFileSer.reportHtml( list, //shybbbReportSer.getFieldsC(datefrom,user,list),
                shybbbReportSer.getParametersC(datefrom,user,list),
                "Ybbb001C.jasper");
        YbbburlResp ybu= new YbbburlResp();
        ybu.setUrl(url);*/
        return ReturnEntityUtil.success(map);
    }

    /**
     * 城镇普通1D基本支付凭证
     *
     * @param
     * @return
     */
    @RequestMapping("/czptsbbd")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "城镇普通1附加支付凭证d#", httpMethod = "POST", notes = "城镇普通1基本支付凭证d#")
    public ReturnEntity<Map> cityNormalTableBaseView(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "开始日期") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "结束日期") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb) {
        SysUser user = getUser();
        List<Map<String,Object>> list  = shybbbReportSer.QueryYbCityNormalListire(daa,datefrom,dateto,mzlb);
        Map<String, Object > map = shybbbReportSer.getParametersD(datefrom,getUser(),list);
        /*String url = exportFileSer.reportHtml( list, //shybbbReportSer.getFieldsC(datefrom,user,list),
                shybbbReportSer.getParametersD(datefrom,user,list),
                "Ybbb001D.jasper");
        YbbburlResp ybu= new YbbburlResp();
        ybu.setUrl(url);*/
        return ReturnEntityUtil.success(map);
    }


///////////////////////////////////////////////////////////
    /**
     * 城镇普通大病2A
     *
     * @param
     * @return
     */
    @RequestMapping("/czdbsbba")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "城镇大病1A#", httpMethod = "POST", notes = "城镇大病1A#")
    public ReturnEntity<CzdbSbbHzAResp> cityDbTableView(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "日期开始") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "日期结束") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb) {
        List<Map<String,Object>> list  = shybbbReportSer.QueryYbCityDbListire(daa,datefrom,dateto,mzlb);
        CzdbSbbHzAResp czdbhzresp = shybbbReportSer.getDbFields(list,datefrom,getUser());
      /*  int page = 0 ;
        if(list.size()>0 && pageno==1){
            if(list.size()%20==0){
                page = list.size()/20;
            }else{
                page = list.size()/20+1;
            }
        }
        SysUser user = getUser();
        String url = exportFileSer.reportHtml(shybbbReportSer.getDbFields(list),
                shybbbReportSer.getDbParameters(datefrom,user,pageno,list),
                "Ybbb02A.jasper");
        YbbburlResp ybu= new YbbburlResp();
        ybu.setPage(page);
        ybu.setUrl(url);*/
        return ReturnEntityUtil.success(czdbhzresp);
    }

    /**
     * 城镇大病1B
     *
     * @param
     * @return
     */
    @RequestMapping("/czdbsbbb")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "城镇大病B#", httpMethod = "POST", notes = "城镇大病#B")
    public ReturnEntity<CzptSbbHzBResp> cityDbTableBView(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "开始时间") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "结束时间") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb) {
        List<Map<String,Object>> list  = shybbbReportSer.QueryYbCityDbListire(daa,datefrom,dateto,mzlb);
        CzptSbbHzBResp czhzdbresp = shybbbReportSer.getDbFieldsB(datefrom,getUser(),list);
        int page = 0 ;

       /* SysUser user = getUser();
        String url = exportFileSer.reportHtml(shybbbReportSer.getDbFieldsB(list),
                shybbbReportSer.getDbParametersB(datefrom,user,list),
                "Ybbb02B.jasper");
        YbbburlResp ybu= new YbbburlResp();
        ybu.setPage(page);
        ybu.setUrl(url);*/
        return ReturnEntityUtil.success(czhzdbresp);
    }

    /**
     * 城镇大病C
     *
     * @param
     * @return
     */
    @RequestMapping("/czdbsbbc")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "城镇大病C#", httpMethod = "POST", notes = "城镇大病C#")
    public ReturnEntity<Map> cityDbTableCView(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "开始日期") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "结束日期") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb) {
        SysUser user = getUser();
        List<Map<String,Object>> list  = shybbbReportSer.QueryYbCityDbListire(daa,datefrom,dateto,mzlb);
        Map<String, Object > map = shybbbReportSer.getDbParametersC(datefrom,getUser(),list);
        /*String url = exportFileSer.reportHtml( list, //shybbbReportSer.getFieldsC(datefrom,user,list),
                shybbbReportSer.getDbParametersC(datefrom,user,list),
                "Ybbb02C.jasper");
        YbbburlResp ybu= new YbbburlResp();
        ybu.setUrl(url);*/
        return ReturnEntityUtil.success(map);
    }

    /**
     * 城镇大病附加支付凭证d
     *
     * @param
     * @return
     */
    @RequestMapping("/czdbsbbd")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "城镇大病附加支付凭证d#", httpMethod = "POST", notes = "城镇大病附加支付凭证d#")
    public ReturnEntity<Map> cityDbTableBaseView(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "开始日期") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "结束日期") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb,
            @ApiParam(name = "pageno", value = "页数") @RequestParam(value = "pageno") int pageno) {
        SysUser user = getUser();
        List<Map<String,Object>> list  = shybbbReportSer.QueryYbCityDbListire(daa,datefrom,dateto,mzlb);
        Map<String, Object > map = shybbbReportSer.getDbParametersD(datefrom,getUser(),list);
       /* String url = exportFileSer.reportHtml( list, //shybbbReportSer.getFieldsC(datefrom,user,list),
                shybbbReportSer.getDbParametersD(datefrom,user,list),
                "Ybbb02D.jasper");
        YbbburlResp ybu= new YbbburlResp();
        ybu.setUrl(url);*/
        return ReturnEntityUtil.success(map);
    }


///////////////////////////////////////////////////////////
    /**
     * 城镇住院A
     *
     * @param
     * @return
     */
    @RequestMapping("/czzysbba")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "城镇住院A#", httpMethod = "POST", notes = "城镇住院A#")
    public ReturnEntity<CzzySbbHzAResp> cityZyTableView(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "日期开始") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "日期结束") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb) {
        List<Map<String,Object>> list  = shybbbReportSer.QueryYbZyCityNormalListire(daa,datefrom,dateto,mzlb);
        CzzySbbHzAResp czzyhzresp = shybbbReportSer.getZyFields(list,datefrom,getUser());
     /*   int page = 0 ;
        if(list.size()>0 && pageno==1){
            if(list.size()%20==0){
                page = list.size()/20;
            }else{
                page = list.size()/20+1;
            }
        }
        SysUser user = getUser();
        String url = exportFileSer.reportHtml(shybbbReportSer.getZyFields(list),
                shybbbReportSer.getZyParameters(datefrom,user,pageno,list),
                "Ybbb03A.jasper");
        YbbburlResp ybu= new YbbburlResp();
        ybu.setPage(page);
        ybu.setUrl(url);*/
        return ReturnEntityUtil.success(czzyhzresp);
    }

    /**
     * 城镇住院B
     *
     * @param
     * @return
     */
    @RequestMapping("/czzysbbb")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "城镇住院B#", httpMethod = "POST", notes = "城镇住院B#B")
    public ReturnEntity<CzzySbbHzBResp> cityZyTableBView(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "开始时间") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "结束时间") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb) {
        List<Map<String,Object>> list  = shybbbReportSer.QueryYbZyCityNormalListire(daa,datefrom,dateto,mzlb);
        CzzySbbHzBResp czzyhzb = shybbbReportSer.getZyFieldsB(datefrom,getUser(),list);
       /* if(list.size()>0 && pageno==1){
            if(list.size()%20==0){
                page = list.size()/20;
            }else{
                page = list.size()/20+1;
            }
        }
        SysUser user = getUser();
        String url = exportFileSer.reportHtml(shybbbReportSer.getZyFieldsB(list),
                shybbbReportSer.getZyParametersB(datefrom,user,list),
                "Ybbb03B.jasper");
        YbbburlResp ybu= new YbbburlResp();
        ybu.setPage(page);
        ybu.setUrl(url);*/
        return ReturnEntityUtil.success(czzyhzb);
    }

    /**
     * 城镇住院C
     *
     * @param
     * @return
     */
    @RequestMapping("/czzysbbc")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "城镇住院C#", httpMethod = "POST", notes = "城镇住院C#")
    public ReturnEntity<Map> cityZyTableCView(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "开始日期") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "结束日期") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb) {
        SysUser user = getUser();
        List<Map<String,Object>> list  = shybbbReportSer.QueryYbZyCityNormalListire(daa,datefrom,dateto,mzlb);
        Map<String, Object > map = shybbbReportSer.getZyParametersC(datefrom,getUser(),list);
 /*       String url = exportFileSer.reportHtml( list, //shybbbReportSer.getFieldsC(datefrom,user,list),
                shybbbReportSer.getZyParametersC(datefrom,user,list),
                "Ybbb02C.jasper");
        YbbburlResp ybu= new YbbburlResp();
        ybu.setUrl(url);*/
        return ReturnEntityUtil.success(map);
    }

    /**
     * 城镇住院D
     *
     * @param
     * @return
     */
    @RequestMapping("/czzysbbd")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "城镇住院D#", httpMethod = "POST", notes = "城镇住院D#")
    public ReturnEntity<Map> cityZyTableBaseView(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "开始日期") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "结束日期") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb) {
        SysUser user = getUser();
        List<Map<String,Object>> list  = shybbbReportSer.QueryYbZyCityNormalListire(daa,datefrom,dateto,mzlb);
        Map<String, Object > map = shybbbReportSer.getZyParametersD(datefrom,getUser(),list);
      /*  String url = exportFileSer.reportHtml( list, //shybbbReportSer.getFieldsC(datefrom,user,list),
                shybbbReportSer.getZyParametersD(datefrom,user,list),
                "Ybbb02D.jasper");
        YbbburlResp ybu= new YbbburlResp();
        ybu.setUrl(url);*/
        return ReturnEntityUtil.success(map);
    }

   /////////////////////城镇特殊/////////////////////////////////////

    /**
     * 城镇特殊A
     *
     * @param
     * @return
     */
    @RequestMapping("/cztssbba")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "城镇特殊A#", httpMethod = "POST", notes = "城镇特殊A#")
    public ReturnEntity<CztsSbbHzAResp> cityTsTableView(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "日期开始") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "日期结束") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb) {
        List<Map<String,Object>> list  = shybbbReportSer.QueryYbTsCityNormalListire(daa,datefrom,dateto,mzlb);
        CztsSbbHzAResp cztshzresp = shybbbReportSer.getTsFields(list,datefrom,getUser());
       /* int page = 0 ;
        if(list.size()>0 && pageno==1){
            if(list.size()%20==0){
                page = list.size()/20;
            }else{
                page = list.size()/20+1;
            }
        }
        SysUser user = getUser();
        String url = exportFileSer.reportHtml(shybbbReportSer.getTsFields(list,datefrom,getUser()),
                shybbbReportSer.getTsParameters(datefrom,user,list),
                "YbbbXcztsmzA.jasper");
        YbbburlResp ybu= new YbbburlResp();
        ybu.setPage(page);
        ybu.setUrl(url);*/
        return ReturnEntityUtil.success(cztshzresp);
    }

    /**
     * 城镇特殊B
     *
     * @param
     * @return
     */
    @RequestMapping("/cztssbbb")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "城镇特殊B#", httpMethod = "POST", notes = "城镇特殊B#")
    public ReturnEntity<CztsSbbHzBResp> cityTsTableBView(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "开始时间") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "结束时间") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb) {
        List<Map<String,Object>> list  = shybbbReportSer.QueryYbTsCityNormalListire(daa,datefrom,dateto,mzlb);
        CztsSbbHzBResp cztshzb = shybbbReportSer.getTsFieldsB(datefrom,getUser(),list);
     /*   int page = 0 ;
        if(list.size()>0 && pageno==1){
            if(list.size()%20==0){
                page = list.size()/20;
            }else{
                page = list.size()/20+1;
            }
        }
        SysUser user = getUser();
        String url = exportFileSer.reportHtml(shybbbReportSer.getTsFieldsB(list),
                shybbbReportSer.getTsParametersB(datefrom,user,list),
                "YbbbXcztsmzB.jasper");
        YbbburlResp ybu= new YbbburlResp();
        ybu.setPage(page);
        ybu.setUrl(url);*/
        return ReturnEntityUtil.success(cztshzb);
    }

    /**
     * 城镇特殊C
     *
     * @param
     * @return
     */
    @RequestMapping("/cztssbbc")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "城镇特殊C#", httpMethod = "POST", notes = "城镇特殊C#")
    public ReturnEntity<Map> cityTsTableCView(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "开始日期") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "结束日期") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb) {
        SysUser user = getUser();
        List<Map<String,Object>> list  = shybbbReportSer.QueryYbTsCityNormalListire(daa,datefrom,dateto,mzlb);
        Map<String,Object> map =   shybbbReportSer.getTsParametersC(datefrom,user,list);
        /*String url = exportFileSer.reportHtml( list, //shybbbReportSer.getFieldsC(datefrom,user,list),
                shybbbReportSer.getTsParametersC(datefrom,user,list),
                "Ybbb02C.jasper");
        YbbburlResp ybu= new YbbburlResp();
        ybu.setUrl(url);*/
        return ReturnEntityUtil.success(map);
    }

    /**
     * 城镇特殊住院A
     *
     * @param
     * @return
     */
    @RequestMapping("/cztszysbba")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "城镇特殊住院A#", httpMethod = "POST", notes = "城镇特殊住院A#")
    public ReturnEntity<CztszySbbHzAResp> cityTszyTableView(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "日期开始") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "日期结束") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb) {
        List<Map<String,Object>> list  = shybbbReportSer.queryYbTszyCityNormalListire(daa,datefrom,dateto,mzlb);
        CztszySbbHzAResp czjmres = shybbbReportSer.getTsZyFieldsA(list,datefrom,getUser());

        return ReturnEntityUtil.success(czjmres);
    }

    /**
     * 城镇特殊住院B
     *
     * @param
     * @return
     */
    @RequestMapping("/cztszysbbb")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "城镇特殊住院B#", httpMethod = "POST", notes = "城镇特殊住院B#")
    public ReturnEntity<CztszySbbHzBResp> cityTszyBTableView(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "日期开始") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "日期结束") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb) {
        List<Map<String,Object>> list  = shybbbReportSer.queryYbTszyCityNormalListire(daa,datefrom,dateto,mzlb);
        CztszySbbHzBResp czjmres = shybbbReportSer.getTsZyFieldsB(list,datefrom,getUser());

        return ReturnEntityUtil.success(czjmres);
    }

    ////////////////城镇居民//////////////////////

    /**
     * 城镇居民A
     *
     * @param
     * @return
     */
    @RequestMapping("/czjmsbba")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "城镇居民A#", httpMethod = "POST", notes = "城镇居民A#")
    public ReturnEntity<CzjmSbbHzAResp> cityJmTableView(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "日期开始") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "日期结束") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb) {
        List<Map<String,Object>> list  = shybbbReportSer.QueryYbJmCityNormalListire(daa,datefrom,dateto,mzlb);
        CzjmSbbHzAResp czjmres = shybbbReportSer.getJmFields(list,datefrom,getUser());
     /*   int page = 0 ;
        if(list.size()>0 && pageno==1){
            if(list.size()%20==0){
                page = list.size()/20;
            }else{
                page = list.size()/20+1;
            }
        }
        SysUser user = getUser();
        String url = exportFileSer.reportHtml(shybbbReportSer.getJmFields(list),
                shybbbReportSer.getJmParameters(datefrom,user,list),
                "Ybbb08A.jasper");
        YbbburlResp ybu= new YbbburlResp();
        ybu.setPage(page);
        ybu.setUrl(url);*/
        return ReturnEntityUtil.success(czjmres);
    }

    /**
     * 城镇居民B
     *
     * @param
     * @return
     */
    @RequestMapping("/czjmsbbb")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "城镇居民B#", httpMethod = "POST", notes = "城镇居民B#")
    public ReturnEntity<CzptSbbHzBResp> cityJmTableBView(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "开始时间") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "结束时间") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb) {
        List<Map<String,Object>> list  = shybbbReportSer.QueryYbJmCityNormalListire(daa,datefrom,dateto,mzlb);
        CzptSbbHzBResp czjmbres = shybbbReportSer.getJmFieldsB(list,datefrom,getUser());
        return ReturnEntityUtil.success(czjmbres);
    }

    /**
     * 城镇居民C
     *
     * @param
     * @return
     */
    @RequestMapping("/czjmsbbc")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "城镇居民C#", httpMethod = "POST", notes = "城镇居民C#")
    public ReturnEntity<Map> cityJmTableCView(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "开始日期") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "结束日期") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb) {
        SysUser user = getUser();
        List<Map<String,Object>> list  = shybbbReportSer.QueryYbJmCityNormalListire(daa,datefrom,dateto,mzlb);
        Map<String,Object> map = shybbbReportSer.getJmParametersC(datefrom,getUser(),list);
        return ReturnEntityUtil.success(map);
    }

    /**
     * 城镇居民住院A
     *
     * @param
     * @return
     */
    @RequestMapping("/czjmzysbba")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "城镇居民住院A#", httpMethod = "POST", notes = "城镇居民住院A#")
    public ReturnEntity<CzjmSbbHzAZyResp> cityJmzyTableView(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "日期开始") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "日期结束") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb) {
        List<Map<String,Object>> list  = shybbbReportSer.QueryYbJmzyCityNormalListire(daa,datefrom,dateto,mzlb);
        CzjmSbbHzAZyResp czjmzyA = shybbbReportSer.getJmzyFields(datefrom,getUser(),list);
  /*      int page = 0 ;
        if(list.size()>0 && pageno==1){
            if(list.size()%20==0){
                page = list.size()/20;
            }else{
                page = list.size()/20+1;
            }
        }
        SysUser user = getUser();
        String url = exportFileSer.reportHtml(shybbbReportSer.getJmzyFields(list),
                shybbbReportSer.getJmzyParameters(datefrom,user,list),
                "Ybbb08D.jasper");
        YbbburlResp ybu= new YbbburlResp();
        ybu.setPage(page);
        ybu.setUrl(url);*/
        return ReturnEntityUtil.success(czjmzyA);
    }

    /**
     * 城镇居民住院B
     *
     * @param
     * @return
     */
    @RequestMapping("/czjmzysbbb")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "城镇居民住院B#", httpMethod = "POST", notes = "城镇居民住院B#")
    public ReturnEntity<CzzySbbHzBResp> cityJmzyTableViewB(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "日期开始") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "日期结束") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb) {
        List<Map<String,Object>> list  = shybbbReportSer.QueryYbJmzyCityNormalListire(daa,datefrom,dateto,mzlb);
        CzzySbbHzBResp czjmzyb = shybbbReportSer.getJmzyFieldsB(datefrom,getUser(),list);
       /* int page = 0 ;
        if(list.size()>0 && pageno==1){
            if(list.size()%20==0){
                page = list.size()/20;
            }else{
                page = list.size()/20+1;
            }
        }
        SysUser user = getUser();
        String url = exportFileSer.reportHtml(shybbbReportSer.getJmzyFieldsB(list),
                shybbbReportSer.getJmzyParametersB(datefrom,user,list),
                "Ybbb08E.jasper");
        YbbburlResp ybu= new YbbburlResp();
        ybu.setPage(page);
        ybu.setUrl(url);*/
        return ReturnEntityUtil.success(czjmzyb);
    }

    ///////////工伤保险门诊///////////////////////
    /**
     * 工伤保险门诊A
     *
     * @param
     * @return
     */
    @RequestMapping("/gsbxopbba")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "工伤保险门诊A#", httpMethod = "POST", notes = "工伤保险门诊A#")
    public ReturnEntity<GsbxSbbHzAResp> gsbxopTableView(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "日期开始") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "日期结束") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb) {
        List<Map<String,Object>> list  = shybbbReportSer.queryYbGsbxopListire(daa,datefrom,dateto,mzlb);
        GsbxSbbHzAResp gsbxhza = shybbbReportSer.getgsbxopFields(datefrom,getUser(),list);
      /*  int page = 0 ;
        if(list.size()>0 && pageno==1){
            if(list.size()%20==0){
                page = list.size()/20;
            }else{
                page = list.size()/20+1;
            }
        }
        SysUser user = getUser();
        String url = exportFileSer.reportHtml(shybbbReportSer.getgsbxopFields(list),
                shybbbReportSer.getgsbxopParameters(datefrom,user,list),
                "Ybbb12A.jasper");
        YbbburlResp ybu= new YbbburlResp();
        ybu.setPage(page);
        ybu.setUrl(url);*/
        return ReturnEntityUtil.success(gsbxhza);
    }

    /**
     * 工伤保险门诊B
     *
     * @param
     * @return
     */
    @RequestMapping("/gsbxopbbb")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "工伤保险门诊B#", httpMethod = "POST", notes = "工伤保险门诊B#")
    public ReturnEntity<GsbxSbbHzBResp> gsbxopTableViewB(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "日期开始") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "日期结束") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb) {
        List<Map<String,Object>> list  = shybbbReportSer.queryYbGsbxopListire(daa,datefrom,dateto,mzlb);
        GsbxSbbHzBResp gsbxhzb = shybbbReportSer.getgsbxopFieldsB(datefrom,getUser(),list);

      /*  int page = 0 ;
        if(list.size()>0 && pageno==1){
            if(list.size()%20==0){
                page = list.size()/20;
            }else{
                page = list.size()/20+1;
            }
        }
        SysUser user = getUser();
        String url = exportFileSer.reportHtml(shybbbReportSer.getgsbxopFieldsB(list),
                shybbbReportSer.getgsbxopParametersB(datefrom,user,list),
                "Ybbb12B.jasper");
        YbbburlResp ybu= new YbbburlResp();
        ybu.setPage(page);
        ybu.setUrl(url);*/
        return ReturnEntityUtil.success(gsbxhzb);
    }

    /**
     * 工伤保险门诊C
     *
     * @param
     * @return
     */
    @RequestMapping("/gsbxopbbc")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "工伤保险门诊C#", httpMethod = "POST", notes = "工伤保险门诊C#")
    public ReturnEntity<Map> gsbxopTableBaseViewC(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "开始日期") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "结束日期") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb) {
        SysUser user = getUser();
        List<Map<String,Object>> list  = shybbbReportSer.queryYbGsbxopListire(daa,datefrom,dateto,mzlb);
        Map<String,Object> map = shybbbReportSer.getgsbxopParametersC(datefrom,user,list);
       /* String url = exportFileSer.reportHtml( list, //shybbbReportSer.getFieldsC(datefrom,user,list),
                shybbbReportSer.getgsbxopParametersC(datefrom,user,list),
                "Ybbb12C.jasper");
        YbbburlResp ybu= new YbbburlResp();
        ybu.setUrl(url);*/
        return ReturnEntityUtil.success(map);
    }

    ////////////////////医疗互助帮困////////////////////////

    /**
     * 医疗互助帮困A
     *
     * @param
     * @return
     */
    @RequestMapping("/ylhzbkbba")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "医疗互助帮困A#", httpMethod = "POST", notes = "医疗互助帮困A#")
    public ReturnEntity<YbbburlResp> ylhzbkTableViewA(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "日期开始") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "日期结束") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb) {
        List<Map<String,Object>> list  = shybbbReportSer.QueryYbYlhzbListire(daa,datefrom,dateto,mzlb);
        /*int page = 0 ;
        if(list.size()>0 && pageno==1){
            if(list.size()%20==0){
                page = list.size()/20;
            }else{
                page = list.size()/20+1;
            }
        }*/
        SysUser user = getUser();
        String url = exportFileSer.reportHtml(shybbbReportSer.getylhzbkFieldsA(list),
                shybbbReportSer.getylhzbkParametersA(datefrom,user,list),
                "YbbbYlhzA.jasper");
        YbbburlResp ybu= new YbbburlResp();
       // ybu.setPage(page);
        ybu.setUrl(url);
        return ReturnEntityUtil.success(ybu);
    }

    /**
     * 医疗互助帮困B
     *
     * @param
     * @return
     */
    @RequestMapping("/ylhzbkbbb")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "医疗互助帮困B#", httpMethod = "POST", notes = "医疗互助帮困B#")
    public ReturnEntity<GsbxSbbHzBResp> ylhzbkTableViewB(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "日期开始") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "日期结束") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb) {
        List<Map<String,Object>> list  = shybbbReportSer.QueryYbYlhzbListire(daa,datefrom,dateto,mzlb);
        GsbxSbbHzBResp ylhzbkhzb = shybbbReportSer.getylhzbkFieldsB(datefrom,getUser(),list);
       /* int page = 0 ;
        if(list.size()>0 && pageno==1){
            if(list.size()%20==0){
                page = list.size()/20;
            }else{
                page = list.size()/20+1;
            }
        }
        SysUser user = getUser();
        String url = exportFileSer.reportHtml(shybbbReportSer.getylhzbkFieldsB(list),
                shybbbReportSer.getylhzbkParametersB(datefrom,user,list),
                "YbbbYlhzB.jasper");
        YbbburlResp ybu= new YbbburlResp();
        ybu.setPage(page);
        ybu.setUrl(url);*/
        return ReturnEntityUtil.success(ylhzbkhzb);
    }

    /**
     * 医疗互助帮困C
     *
     * @param
     * @return
     */
    @RequestMapping("/ylhzbkbbc")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "医疗互助帮困C#", httpMethod = "POST", notes = "医疗互助帮困C#")
    public ReturnEntity<Map> ylhzbkTableBaseViewC(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "开始日期") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "结束日期") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb) {
        SysUser user = getUser();
        List<Map<String,Object>> list  = shybbbReportSer.QueryYbYlhzbListire(daa,datefrom,dateto,mzlb);
        Map<String,Object> map =  shybbbReportSer.getylhzbkParametersC(datefrom,user,list);
        /*String url = exportFileSer.reportHtml( list, //shybbbReportSer.getFieldsC(datefrom,user,list),
                shybbbReportSer.getylhzbkParametersC(datefrom,user,list),
                "YbbbYlhzC.jasper");
        YbbburlResp ybu= new YbbburlResp();
        ybu.setUrl(url);*/
        return ReturnEntityUtil.success(map);
    }

    /////////精神病//////////
    /**
     * 精神病A
     *
     * @param
     * @return
     */
    @RequestMapping("/pkjsbbba")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "精神病A#", httpMethod = "POST", notes = "精神病A#")
    public ReturnEntity<YbbburlResp> pkjsbTableViewA(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "日期开始") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "日期结束") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb,
            @ApiParam(name = "pageno", value = "页数") @RequestParam(value = "pageno") int pageno) {
        List<Map<String,Object>> list  = shybbbReportSer.QueryYbPkjsbListire(daa,datefrom,dateto,mzlb,pageno);
        int page = 0 ;
        if(list.size()>0 && pageno==1){
            if(list.size()%20==0){
                page = list.size()/20;
            }else{
                page = list.size()/20+1;
            }
        }
        SysUser user = getUser();
        String url = exportFileSer.reportHtml(shybbbReportSer.getpkjsbFieldsA(list),
                shybbbReportSer.getpkjsbParametersA(datefrom,user,list),
                "YbbbPkjsbHz.jasper");
        YbbburlResp ybu= new YbbburlResp();
        ybu.setPage(page);
        ybu.setUrl(url);
        return ReturnEntityUtil.success(ybu);
    }

    /**
     * 精神病B
     *
     * @param
     * @return
     */
    @RequestMapping("/pkjsbbbb")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "精神病B#", httpMethod = "POST", notes = "精神病B#")
    public ReturnEntity<YbbburlResp> pkjsbTableViewB(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "日期开始") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "日期结束") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb,
            @ApiParam(name = "pageno", value = "页数") @RequestParam(value = "pageno") int pageno) {
        List<Map<String,Object>> list  = shybbbReportSer.QueryYbPkjsbListire(daa,datefrom,dateto,mzlb,pageno);
        int page = 0 ;
        if(list.size()>0 && pageno==1){
            if(list.size()%20==0){
                page = list.size()/20;
            }else{
                page = list.size()/20+1;
            }
        }
        SysUser user = getUser();
        String url = exportFileSer.reportHtml(shybbbReportSer.getpkjsbFieldsB(list),
                shybbbReportSer.getpkjsbParametersB(datefrom,user,list),
                "YbbbPkjsbMx.jasper");
        YbbburlResp ybu= new YbbburlResp();
        ybu.setPage(page);
        ybu.setUrl(url);
        return ReturnEntityUtil.success(ybu);
    }
////////////////////////////////////////////
    /**
     * 民政帮困A
     *
     * @param
     * @return
     */
    @RequestMapping("/mzbkbba")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "民政帮困A#", httpMethod = "POST", notes = "民政帮困A#")
    public ReturnEntity<MzbkSbbHzAResp> mzbkTableViewA(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "日期开始") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "日期结束") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb) {
        List<Map<String,Object>> list  = shybbbReportSer.QueryYbMzbkListire(daa,datefrom,dateto,mzlb);
        MzbkSbbHzAResp mzbka = shybbbReportSer.getmzbkFieldsA(datefrom,getUser(),list);
       /* int page = 0 ;
        if(list.size()>0 && pageno==1){
            if(list.size()%20==0){
                page = list.size()/20;
            }else{
                page = list.size()/20+1;
            }
        }
        SysUser user = getUser();
        String url = exportFileSer.reportHtml(shybbbReportSer.getmzbkFieldsA(list),
                shybbbReportSer.getmzbkParametersA(datefrom,user,list),
                "Ybbb10A.jasper");
        YbbburlResp ybu= new YbbburlResp();
        ybu.setPage(page);
        ybu.setUrl(url);*/
        return ReturnEntityUtil.success(mzbka);
    }

    /**
     * 民政帮困B
     *
     * @param
     * @return
     */
    @RequestMapping("/mzbkbbb")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "民政帮困B#", httpMethod = "POST", notes = "民政帮困B#")
    public ReturnEntity<MzbkSbbHzBResp> mzbkTableViewB(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "日期开始") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "日期结束") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb) {
        List<Map<String,Object>> list  = shybbbReportSer.QueryYbMzbkListire(daa,datefrom,dateto,mzlb);
        MzbkSbbHzBResp mzbkhzb = shybbbReportSer.getmzbkFieldsB(datefrom,getUser(),list);

        return ReturnEntityUtil.success(mzbkhzb);
    }

    /**
     * 民政帮困C
     *
     * @param
     * @return
     */
    @RequestMapping("/mzbkbbc")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "民政帮困C#", httpMethod = "POST", notes = "民政帮困C#")
    public ReturnEntity<Map> mzbkTableViewC(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "开始日期") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "结束日期") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb) {
        SysUser user = getUser();
        List<Map<String,Object>> list  = shybbbReportSer.QueryYbMzbkListire(daa,datefrom,dateto,mzlb);
        Map<String,Object> map =    shybbbReportSer.getmzbkParametersC(datefrom,user,list);
        /*String url = exportFileSer.reportHtml( list, //shybbbReportSer.getFieldsC(datefrom,user,list),
                shybbbReportSer.getmzbkParametersC(datefrom,user,list),
                "Ybbb10C.jasper");
        YbbburlResp ybu= new YbbburlResp();
        ybu.setUrl(url);*/
        return ReturnEntityUtil.success(map);
    }

    /**
     * 民政帮困D
     *
     * @param
     * @return
     */
    @RequestMapping("/mzbkbbd")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "民政帮困D#", httpMethod = "POST", notes = "民政帮困D#")
    public ReturnEntity<Map> mzbkTableViewD(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "开始日期") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "结束日期") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb) {
        SysUser user = getUser();
        List<Map<String,Object>> list  = shybbbReportSer.QueryYbMzbkListire(daa,datefrom,dateto,mzlb);
        Map<String,Object> map =  shybbbReportSer.getmzbkParametersD(datefrom,user,list);
      /*  String url = exportFileSer.reportHtml( list, //shybbbReportSer.getFieldsC(datefrom,user,list),
                shybbbReportSer.getmzbkParametersD(datefrom,user,list),
                "Ybbb10D.jasper");
        YbbburlResp ybu= new YbbburlResp();
        ybu.setUrl(url);*/
        return ReturnEntityUtil.success(map);
    }

    ////////////异地医保///////////
    /**
     * 异地医保A
     *
     * @param
     * @return
     */
    @RequestMapping("/ydybbba")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "异地医保A#", httpMethod = "POST", notes = "异地医保A#")
    public ReturnEntity<YdImSbbHzAResp> ydybTableViewA(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "日期开始") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "日期结束") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb) {
        List<Map<String,Object>> list  = shybbbReportSer.QueryYbYdybListire(daa,datefrom,dateto,mzlb);
        YdImSbbHzAResp ydimhza =  shybbbReportSer.getydybParametersA(datefrom,getUser(),list);
       /* int page = 0 ;
        if(list.size()>0 && pageno==1){
            if(list.size()%20==0){
                page = list.size()/20;
            }else{
                page = list.size()/20+1;
            }
        }
        SysUser user = getUser();
        String url = exportFileSer.reportHtml(list,
                shybbbReportSer.getydybParametersA(datefrom,user,list),
                "Ybbb19A.jasper");
        YbbburlResp ybu= new YbbburlResp();
        ybu.setPage(page);
        ybu.setUrl(url);*/
        return ReturnEntityUtil.success(ydimhza);
    }

    /**
     * 异地医保B
     *
     * @param
     * @return
     */
    @RequestMapping("/ydybbbb")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "异地医保B#", httpMethod = "POST", notes = "异地医保B#")
    public ReturnEntity<YdImSbbHzBResp> ydybTableViewB(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "日期开始") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "日期结束") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb) {
        List<Map<String,Object>> list  = shybbbReportSer.QueryYbYdybListire(daa,datefrom,dateto,mzlb);
        YdImSbbHzBResp ydimhzb = shybbbReportSer.getydybFieldsB(datefrom,getUser(),list);
       /* int page = 0 ;
        if(list.size()>0 && pageno==1){
            if(list.size()%20==0){
                page = list.size()/20;
            }else{
                page = list.size()/20+1;
            }
        }
        SysUser user = getUser();
        String url = exportFileSer.reportHtml(shybbbReportSer.getydybFieldsB(list),
                shybbbReportSer.getydybParametersB(datefrom,user,list),
                "Ybbb19B.jasper");
        YbbburlResp ybu= new YbbburlResp();
        ybu.setPage(page);
        ybu.setUrl(url);*/
        return ReturnEntityUtil.success(ydimhzb);
    }

    /**
     * 异地医保C
     *
     * @param
     * @return
     */
    @RequestMapping("/ydybbbc")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "异地医保C#", httpMethod = "POST", notes = "异地医保C#")
    public ReturnEntity<Map> ydybTableViewC(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "开始日期") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "结束日期") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb) {
        SysUser user = getUser();
        List<Map<String,Object>> list  = shybbbReportSer.QueryYbYdybListire(daa,datefrom,dateto,mzlb);
        Map<String,Object> map =  shybbbReportSer.getydybParametersC(datefrom,user,list);
       /* String url = exportFileSer.reportHtml( list, //shybbbReportSer.getFieldsC(datefrom,user,list),
                shybbbReportSer.getydybParametersC(datefrom,user,list),
                "Ybbb19C.jasper");
        YbbburlResp ybu= new YbbburlResp();
        ybu.setUrl(url);*/
        return ReturnEntityUtil.success(map);
    }

    //////////门诊异地医保//////////////
    /**
     * 门诊异地医保A
     *
     * @param
     * @return
     */
    @RequestMapping("/ydybopbba")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "门诊异地医保A#", httpMethod = "POST", notes = "门诊异地医保A#")
    public ReturnEntity<YdopSbbHzAResp> ydybopTableViewA(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "日期开始") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "日期结束") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb) {
        List<Map<String,Object>> list  = shybbbReportSer.QueryYbYdybopListire(daa,datefrom,dateto,mzlb);
        YdopSbbHzAResp ydophza = shybbbReportSer.getydybopFieldsA(datefrom,getUser(),list);
       /* int page = 0 ;
        if(list.size()>0 && pageno==1){
            if(list.size()%20==0){
                page = list.size()/20;
            }else{
                page = list.size()/20+1;
            }
        }
        SysUser user = getUser();
        String url = exportFileSer.reportHtml(shybbbReportSer.getydybopFieldsA(list),
                shybbbReportSer.getydybopParametersA(datefrom,user,list),
                "Ybbbydmz20A.jasper");
        YbbburlResp ybu= new YbbburlResp();
        ybu.setPage(page);
        ybu.setUrl(url);*/
        return ReturnEntityUtil.success(ydophza);
    }
    /**
     * 门诊异地医保B
     *
     * @param
     * @return
     */
    @RequestMapping("/ydybopbbb")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "门诊异地医保B#", httpMethod = "POST", notes = "门诊异地医保B#")
    public ReturnEntity<YdopSbbHzBResp> ydybopTableViewB(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "日期开始") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "日期结束") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb) {
        List<Map<String,Object>> list  = shybbbReportSer.QueryYbYdybopListire(daa,datefrom,dateto,mzlb);
        YdopSbbHzBResp ydophzb = shybbbReportSer.getydybopFieldsB(datefrom,getUser(),list);
        /*int page = 0 ;
        if(list.size()>0 && pageno==1){
            if(list.size()%20==0){
                page = list.size()/20;
            }else{
                page = list.size()/20+1;
            }
        }
        SysUser user = getUser();
        String url = exportFileSer.reportHtml(shybbbReportSer.getydybopFieldsB(list),
                shybbbReportSer.getydybopParametersB(datefrom,user,list),
                "Ybbbydmz20B.jasper");
        YbbburlResp ybu= new YbbburlResp();
        ybu.setPage(page);
        ybu.setUrl(url);*/
        return ReturnEntityUtil.success(ydophzb);
    }

    /**
     * 门诊异地医保C
     *
     * @param
     * @return
     */
    @RequestMapping("/ydybopbbc")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "门诊异地医保C#", httpMethod = "POST", notes = "门诊异地医保C#")
    public ReturnEntity<Map> ydybopTableViewC(
            @ApiParam(name = "daa", value = "上报日期") @RequestParam(value = "daa") String daa,
            @ApiParam(name = "datefrom", value = "开始日期") @RequestParam(value = "datefrom") String datefrom,
            @ApiParam(name = "dateto", value = "结束日期") @RequestParam(value = "dateto") String dateto,
            @ApiParam(name = "mzlb", value = "门诊类别") @RequestParam(value = "mzlb") String mzlb) {
        SysUser user = getUser();
        List<Map<String,Object>> list  = shybbbReportSer.QueryYbYdybopListire(daa,datefrom,dateto,mzlb);
        Map<String,Object> map = shybbbReportSer.getydybopParametersC(datefrom,getUser(),list);
        /*String url = exportFileSer.reportHtml( list, //shybbbReportSer.getFieldsC(datefrom,user,list),
                shybbbReportSer.getydybopParametersC(datefrom,user,list),
                "Ybbbydmz20C.jasper");
        YbbburlResp ybu= new YbbburlResp();
        ybu.setUrl(url);*/
        return ReturnEntityUtil.success(map);
    }

    @RequestMapping("/mzbkbbdaaa")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "民政帮困aaaaaaaa#", httpMethod = "POST", notes = "民政帮困Daaaaaaa#")
    public MessageBody buildCommonMessage(String orgId, MessageTypeEnum messageType, TradingChannelEnum tradingChannelEnum) {
        SysUser user = new SysUser();
        //SysUser user = getUser();
        user.setHospitalId(310112041);
        user.setUserName("测试人");
        Integer operatorId = user.getUserId();
        String operatorName = user.getUserName();



        MedicalInsuranceModel model = new MedicalInsuranceModel();
        String msgId = model.getMsgId(orgId,9);

        MessageBody messageBody = new MessageBody();
        messageBody.setJysj(new Date());
        messageBody.setMsgid(msgId);
        messageBody.setJgdm(orgId);
        messageBody.setCzybm(operatorId);
        messageBody.setCzyxm(operatorName);

        messageBody.setXxlxm(messageType);
        messageBody.setJyqd(tradingChannelEnum);
        return messageBody;
    }

/*    @RequestMapping("/qqqq")
    @ResponseBody
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "1111111111111", httpMethod = "POST", notes = "1111111111111111111")
    public void CommonMessage(){
        Map body = new HashMap();
        body.put("JSCS",1);
        body.put("zyh","29");
        body.put("jzdyh","2021");

        List<String> mxzdh = new ArrayList<>();
        mxzdh.add("111");
        shybSi51Dao.updateFymxbz(MedicineUtils.parseInt(StrUtil.null2Str(body.get("JSCS"))),StrUtil.null2Str(body.get("zyh")),StrUtil.null2Str(body.get("jzdyh")),mxzdh);
    }*/

}
