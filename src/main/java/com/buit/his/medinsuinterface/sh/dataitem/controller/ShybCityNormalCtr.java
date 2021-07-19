package com.buit.his.medinsuinterface.sh.dataitem.controller;


import cn.hutool.json.JSONUtil;
import com.buit.commons.BaseSpringController;
import com.buit.commons.PageQuery;
import com.buit.his.medinsuinterface.sh.dataitem.request.ShybbbReq;
import com.buit.his.medinsuinterface.sh.dataitem.response.*;
import com.buit.his.medinsuinterface.sh.dataitem.service.ShybbbSer;
import com.buit.his.shyb.source.entity.SL01s;
import com.buit.his.shyb.source.entity.business.SL01;
import com.buit.his.shyb.source.entity.business.SL01Res;
import com.buit.utill.ReturnEntity;
import com.buit.utill.ReturnEntityUtil;
import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 上海医保明细对账<br>
 * @author beijunhua
 */
@Api(tags="医保报表")
@Controller
@RequestMapping("/shybbb")
public class ShybCityNormalCtr extends BaseSpringController {
    static final Logger logger = LoggerFactory.getLogger(ShybCityNormalCtr.class);
    @Autowired
    private ShybbbSer shybMxdzSer;

    @RequestMapping("/mxdzcs")
    @ResponseBody
    @ApiOperationSupport(author = "beijunhua")
    @ApiOperation(value="产生详情" ,httpMethod="POST")
    public ReturnEntity<MxdzResp> detail(@ApiParam(required = true) @RequestParam Integer mzlb ,
                                         @ApiParam(required = true) @RequestParam String querydate){
        MxdzResp response = shybMxdzSer.getYbMxsj(mzlb,querydate);
        return ReturnEntityUtil.success(response);
    }

    @RequestMapping("/mxdz")
    @ResponseBody
    @ApiOperationSupport(author = "beijunhua")
    @ApiOperation(value="对账详情" ,httpMethod="POST")
    public ReturnEntity<SL01Res> detail(SL01s sl01){
        SL01Res response = shybMxdzSer.getYbMxdz(sl01, getUser(),getIpAddress());
        return ReturnEntityUtil.success(response);
    }

    @RequestMapping("/mxsc/jyjlk")
    @ResponseBody
    @ApiOperationSupport(author = "beijunhua")
    @ApiOperation(value="明细上传-交易记录库" ,httpMethod="POST")
    public ReturnEntity<List<MxscJyjlkResp>> queryMxscJyjlk(@ApiParam(required = true) @RequestParam Integer mzlb ,
                                                            @ApiParam(required = true) @RequestParam String date ){
        List<MxscJyjlkResp> response = shybMxdzSer.queryMxscJyjlk(date, mzlb);
        return ReturnEntityUtil.success(response);
    }

    @RequestMapping("/mxsc/dbghk")
    @ResponseBody
    @ApiOperationSupport(author = "beijunhua")
    @ApiOperation(value="明细上传-大病挂号库" ,httpMethod="POST")
    public ReturnEntity<List<MxscDbghkResp>> queryMxscDbghk(@ApiParam(required = true) @RequestParam String mzlb ,
                                                            @ApiParam(required = true) @RequestParam String date ){
        List<MxscDbghkResp> response = shybMxdzSer.queryMxscDbghk(date, mzlb);
        return ReturnEntityUtil.success(response);
    }

    @RequestMapping("/mxsc/zyjsjlk")
    @ResponseBody
    @ApiOperationSupport(author = "beijunhua")
    @ApiOperation(value="明细上传-住院结算记录库" ,httpMethod="POST")
    public ReturnEntity<List<MxscZyjsjlkResp>> queryMxscZyjsjlk(@ApiParam(required = true) @RequestParam String mzlb ,
                                                                @ApiParam(required = true) @RequestParam String date ){
        List<MxscZyjsjlkResp> response = shybMxdzSer.queryMxscZyjsjlk(date, mzlb);
        return ReturnEntityUtil.success(response);
    }

    @RequestMapping("/mxsc/zyfymxk")
    @ResponseBody
    @ApiOperationSupport(author = "beijunhua")
    @ApiOperation(value="明细上传-住院费用明细库" ,httpMethod="POST")
    public ReturnEntity<List<MxscZyfymxkResp>> queryMxscZyfymxk(@ApiParam(required = true) @RequestParam String mzlb ,
                                                                @ApiParam(required = true) @RequestParam String date ){
        List<MxscZyfymxkResp> response = shybMxdzSer.queryMxscZyfymxk(date, mzlb);
        return ReturnEntityUtil.success(response);
    }

    @RequestMapping("/mxsc/cyzdk")
    @ResponseBody
    @ApiOperationSupport(author = "beijunhua")
    @ApiOperation(value="明细上传-出院诊断库" ,httpMethod="POST")
    public ReturnEntity<List<MxscCyzdkResp>> queryMxscCyzdk(@ApiParam(required = true) @RequestParam String mzlb ,
                                                            @ApiParam(required = true) @RequestParam String date ){
        List<MxscCyzdkResp> response = shybMxdzSer.queryMxscCyzdk(date, mzlb);
        return ReturnEntityUtil.success(response);
    }

    @RequestMapping("/mxsc/mxxmxfk")
    @ResponseBody
    @ApiOperationSupport(author = "beijunhua")
    @ApiOperation(value="明细上传-明细项目细分库" ,httpMethod="POST")
    public ReturnEntity<List<MxscMxxmxfkResp>> queryMxscMxxmxfk(@ApiParam(required = true) @RequestParam String mzlb ,
                                                                @ApiParam(required = true) @RequestParam String date ){
        List<MxscMxxmxfkResp> response = shybMxdzSer.queryMxscMxxmxfk(date, mzlb);
        return ReturnEntityUtil.success(response);
    }




    @RequestMapping("/czptsbb")
    @ResponseBody
    @ApiOperationSupport(author = "Beijunhua")
    @ApiOperation(value="城镇普通医保上报表" ,httpMethod="POST")
    public ReturnEntity<PageInfo<CzptSbbResp>> queryPage(ShybbbReq req){
        //2020-09-13 2020-09-15  1
//        PageInfo<CzptSbbResp> pageInfo = PageHelper.startPage(page.getPageNum(), page.getPageSize())
//                .doSelectPageInfo(() -> shybMxdzSer.QueryYbCityNormalList(req));
//        return ReturnEntityUtil.success(pageInfo);
        return ReturnEntityUtil.success(shybMxdzSer.QueryYbCityNormalList(req));
    }
    @RequestMapping("/czptsbbdc")
    @ResponseBody
    @ApiOperation(value="城镇普通医保上报表导出" ,httpMethod="POST")
    public ReturnEntity queryPagedc(ShybbbReq req){
        //2020-09-13 2020-09-15  1
        req.setDaa(req.getDaa().replace("-","."));
        String path = shybMxdzSer.QueryYbCityNormalListdc(req);
        return  ReturnEntityUtil.success(path);
    }
    /**
     * 1#
     *
     * @return
     */
    @GetMapping("/czptdbfdc")
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "城镇普通医保上报表导出(1)")
    public void doCzptdbfdc(
            @RequestParam("reqStr") String reqStr, HttpServletResponse response) {
        ShybbbReq req = JSONUtil.toBean(reqStr, ShybbbReq.class);
        req.setDaa(req.getDaa().replace("-","."));
        shybMxdzSer.QueryYbCityNormalListdcs(req,response);
    }


    @RequestMapping("/czdbsbb")
    @ResponseBody
    @ApiOperation(value="城镇大病医保上报表" ,httpMethod="POST")
    public ReturnEntity<PageInfo<CzdbSbbResp>> queryDbPage(ShybbbReq req ){
        //2020-09-13 2020-09-15  1
        return ReturnEntityUtil.success(shybMxdzSer.QueryYbDbCityNormalList(req));
    }
    @RequestMapping("/czdbsbbdc")
    @ResponseBody
    @ApiOperation(value="城镇大病医保上报表导出" ,httpMethod="POST")
    public ReturnEntity  queryDbPagedc(ShybbbReq req){
        //2020-09-13 2020-09-15  1
        req.setDaa(req.getDaa().replace("-","."));
        String path =shybMxdzSer.QueryYbDbCityNormalListdc(req);
        return ReturnEntityUtil.success(path);
    }
    /**
     * 2#
     * @return
     */
    @GetMapping("/czdbdbfdc")
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "城镇大病医保上报表导出(1)")
    public void doCzdbdbfdc(
            @RequestParam("reqStr") String reqStr, HttpServletResponse response) {
        ShybbbReq req = JSONUtil.toBean(reqStr, ShybbbReq.class);
        req.setDaa(req.getDaa().replace("-","."));
        shybMxdzSer.QueryYbDbCityNormalListdcs(req,response);
    }

    @RequestMapping("/czzysbb")
    @ResponseBody
    @ApiOperation(value="城镇住院医保上报表" ,httpMethod="POST")
    public ReturnEntity<PageInfo<CzzySbbResp>> queryZyPage(ShybbbReq req ){
        //2020-09-13 16 29 23/    20200915/162923/  1
        return ReturnEntityUtil.success(shybMxdzSer.QueryYbZyCityNormalList(req));
    }
    @RequestMapping("/czzysbbdc")
    @ResponseBody
    @ApiOperation(value="城镇住院医保上报表导出" ,httpMethod="POST")
    public ReturnEntity  queryZyPagedc(ShybbbReq req){
        //2020-09-13 2020-09-15  1
        req.setDaa(req.getDaa().replace("-","."));
        String path = shybMxdzSer.QueryYbZyCityNormalListdc(req);
        return ReturnEntityUtil.success(path);
    }
    /**
     * 3#
     * @return
     */
    @GetMapping("/czzydbfdc")
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "城镇住院医保上报表导出(1)")
    public void doZyPagedc(
            @RequestParam("reqStr") String reqStr, HttpServletResponse response) {
        ShybbbReq req = JSONUtil.toBean(reqStr, ShybbbReq.class);
        req.setDaa(req.getDaa().replace("-","."));
        shybMxdzSer.QueryYbZyCityNormalListdcs(req,response);
    }

    @RequestMapping("/cztssbb")
    @ResponseBody
    @ApiOperation(value="城镇特殊医保上报表" ,httpMethod="POST")
    public ReturnEntity<PageInfo<CztsSbbResp>> queryTsPage(ShybbbReq req ){
        //20200913/162923/    20200915/162923/  1
        return ReturnEntityUtil.success(shybMxdzSer.QueryYbTsCityNormalList(req));
    }
    @RequestMapping("/cztssbbdc")
    @ResponseBody
    @ApiOperation(value="城镇特殊医保上报表导出" ,httpMethod="POST")
    public ReturnEntity  queryTsPagedc(ShybbbReq req){
        //2020-09-13 2020-09-15  1
        String path = shybMxdzSer.QueryYbTsCityNormalListdc(req);
        return ReturnEntityUtil.success(path);
    }
    /**
     * 4#
     * @return
     */
    @GetMapping("/cztsdbfdc")
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "城镇特殊门诊医保上报表导出(1)")
    public void doTsPagedc(
            @RequestParam("reqStr") String reqStr, HttpServletResponse response) {
        ShybbbReq req = JSONUtil.toBean(reqStr, ShybbbReq.class);
        req.setDaa(req.getDaa().replace("-","."));
        shybMxdzSer.QueryYbTsCityNormalListdcs(req,response);
    }

    @RequestMapping("/cztszysbb")
    @ResponseBody
    @ApiOperation(value="城镇特殊住院医保上报表" ,httpMethod="POST")
    public ReturnEntity<PageInfo<CztszySbbResp>> queryTsZyPage(ShybbbReq req ){
        //20200913/162923/    20200915/162923/  1
        return ReturnEntityUtil.success(shybMxdzSer.queryYbTsZyCityNormalList(req));
    }
    @RequestMapping("/cztszysbbdc")
    @ResponseBody
    @ApiOperation(value="城镇特殊住院上报表导出" ,httpMethod="POST")
    public ReturnEntity  queryTsZyPagedc(ShybbbReq req){
        //2020-09-13 2020-09-15  1
        req.setDaa(req.getDaa().replace("-","."));
        String path = shybMxdzSer.QueryYbTsZyCityNormalListdc(req);
        return ReturnEntityUtil.success(path);
    }
    /**
     * 5#
     * @return
     */
    @GetMapping("/cztszydbfdc")
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "城镇特殊住院上报表导出(1)")
    public void doTsZyPagedc(
            @RequestParam("reqStr") String reqStr, HttpServletResponse response) {
        ShybbbReq req = JSONUtil.toBean(reqStr, ShybbbReq.class);
        req.setDaa(req.getDaa().replace("-","."));
        shybMxdzSer.QueryYbTsZyCityNormalListdcs(req,response);
    }

    @RequestMapping("/czjmsbb")
    @ResponseBody
    @ApiOperation(value="城镇居民结算医保上报表" ,httpMethod="POST")
    public ReturnEntity<PageInfo<CzjmjsSbbResp>> queryJmPage(ShybbbReq req ){
        //20200913/162923/    20200915/162923/  1
        return ReturnEntityUtil.success(shybMxdzSer.QueryYbJmCityNormalList(req));
    }
    @RequestMapping("/czjmsbbdc")
    @ResponseBody
    @ApiOperation(value="城镇居民结算医保上报表导出" ,httpMethod="POST")
    public ReturnEntity  queryJmPagedc(ShybbbReq req){
        //2020-09-13 2020-09-15  1
        req.setDaa(req.getDaa().replace("-","."));
        String path = shybMxdzSer.QueryYbJmCityNormalListdc(req);
        return ReturnEntityUtil.success(path);
    }
    /**
     * Q#
     * @return
     */
    @GetMapping("/czjmdbfdc")
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "城镇居民结算医保上报表导出(1)")
    public void doJmPagedc(
            @RequestParam("reqStr") String reqStr, HttpServletResponse response) {
        ShybbbReq req = JSONUtil.toBean(reqStr, ShybbbReq.class);
        req.setDaa(req.getDaa().replace("-","."));
        shybMxdzSer.QueryYbJmCityNormalListdcs(req,response);
    }

    @RequestMapping("/czjmzysbb")
    @ResponseBody
    @ApiOperation(value="城镇居民住院结算医保上报表" ,httpMethod="POST")
    public ReturnEntity<PageInfo<CzjmzySbbResp>> queryJmzyPage(ShybbbReq req ){
        //20200913/162923/    20200915/162923/  1
        return ReturnEntityUtil.success(shybMxdzSer.QueryYbJmzyCityNormalList(req));
    }
    @RequestMapping("/czjmzysbbdc")
    @ResponseBody
    @ApiOperation(value="城镇居民住院结算医保上报表导出" ,httpMethod="POST")
    public ReturnEntity  queryJmzyPagedc(ShybbbReq req){

        //2020-09-13 2020-09-15  1
        req.setDaa(req.getDaa().replace("-","."));
        String path = shybMxdzSer.QueryYbJmzyCityNormalListdc(req);
        return ReturnEntityUtil.success(path);
    }
    /**
     * R#
     * @return
     */
    @GetMapping("/czjmzydbfdc")
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "城镇居民住院结算医保上报表导出(1)")
    public void doJmzyPagedc(
            @RequestParam("reqStr") String reqStr, HttpServletResponse response) {
        ShybbbReq req = JSONUtil.toBean(reqStr, ShybbbReq.class);
        req.setDaa(req.getDaa().replace("-","."));
        shybMxdzSer.QueryYbJmzyCityNormalListdcs(req,response);
    }

    @RequestMapping("/ylhzbkbb")
    @ResponseBody
    @ApiOperation(value="医疗互助帮困" ,httpMethod="POST")
    public ReturnEntity<PageInfo<YbbbYlhzbkResp>> queryYbYlhzbkPage(ShybbbReq req){
        //20200913/162923/    20200915/162923/  1
        return ReturnEntityUtil.success(shybMxdzSer.QueryYbYlhzbkList(req));
    }
    @RequestMapping("/ylhzbkbbdc")
    @ResponseBody
    @ApiOperation(value="医疗互助帮困导出" ,httpMethod="POST")
    public ReturnEntity  queryYbYlhzbkPagedc(ShybbbReq req){
        //2020-09-13 2020-09-15  1
        req.setDaa(req.getDaa().replace("-","."));
        String path = shybMxdzSer.QueryYbYlhzbkListdc(req);
        return ReturnEntityUtil.success(path);
    }
    /**
     * S#
     * @return
     */
    @GetMapping("/ylhzbkdbfdc")
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "医疗互助帮困导出(1)")
    public void doYbYlhzbkPagedc(
            @RequestParam("reqStr") String reqStr, HttpServletResponse response) {
        ShybbbReq req = JSONUtil.toBean(reqStr, ShybbbReq.class);
        req.setDaa(req.getDaa().replace("-","."));
        shybMxdzSer.QueryYbYlhzbkListdcs(req,response);
    }

    @RequestMapping("/ydybopbb")
    @ResponseBody
    @ApiOperation(value="门诊异地医保" ,httpMethod="POST")
    public ReturnEntity<PageInfo<YbbbYdybopResp>> queryYbYdybopPage(ShybbbReq req ){
        //20200913/162923/    20200915/162923/  1
        return ReturnEntityUtil.success(shybMxdzSer.QueryYbYdybopList(req));
    }
    @RequestMapping("/ydybopbbdc")
    @ResponseBody
    @ApiOperation(value="门诊异地医保导出" ,httpMethod="POST")
    public ReturnEntity  queryYbYdopybPagedc(ShybbbReq req){
        //2020-09-13 2020-09-15  1
        req.setDaa(req.getDaa().replace("-","."));
        String path = shybMxdzSer.QueryYbYdybopListdc(req);
        return ReturnEntityUtil.success(path);
    }
    /**
     * W#
     * @return
     */
    @GetMapping("/ydopdbfdc")
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "门诊异地医保导出(1)")
    public void doYbYdopybPagedc(
            @RequestParam("reqStr") String reqStr, HttpServletResponse response) {
        ShybbbReq req = JSONUtil.toBean(reqStr, ShybbbReq.class);
        req.setDaa(req.getDaa().replace("-","."));
        shybMxdzSer.QueryYbYdybopListdcs(req,response);
    }

    @RequestMapping("/ydybbb")
    @ResponseBody
    @ApiOperation(value="异地住院医保" ,httpMethod="POST")
    public ReturnEntity<PageInfo<YbbbYdybResp>> queryYbYdybPage(ShybbbReq req){
        //20200913/162923/    20200915/162923/  1
        return ReturnEntityUtil.success(shybMxdzSer.QueryYbYdybList(req));
    }
    @RequestMapping("/ydybbbdc")
    @ResponseBody
    @ApiOperation(value="异地住院医保导出" ,httpMethod="POST")
    public ReturnEntity  queryYbYdybPagedc(ShybbbReq req){
        //2020-09-13 2020-09-15  1
        req.setDaa(req.getDaa().replace("-","."));
        String path = shybMxdzSer.QueryYbYdybListdc(req);
        return ReturnEntityUtil.success(path);
    }
    /**
     * X#
     * @return
     */
    @GetMapping("/ydimdbfdc")
    @ApiOperationSupport(author = "卑军华")
    @ApiOperation(value = "异地住院医保导出(1)")
    public void doYbYdybPagedc(
            @RequestParam("reqStr") String reqStr, HttpServletResponse response) {
        ShybbbReq req = JSONUtil.toBean(reqStr, ShybbbReq.class);
        req.setDaa(req.getDaa().replace("-","."));
        shybMxdzSer.QueryYbYdybListdcs(req,response);
    }


    @RequestMapping("/fyjssbhzb")
    @ResponseBody
    @ApiOperation(value="费用结算申报汇总表" ,httpMethod="POST")
    public ReturnEntity<ShybOneResp> queryFeehz(ShybbbReq req){
        return ReturnEntityUtil.success(shybMxdzSer.QueryFeehzList(req,getUser()));
    }

    @RequestMapping("/fyjssbhzehb")
    @ResponseBody
    @ApiOperation(value="费用结算申报汇总2号表" ,httpMethod="POST")
    public ReturnEntity<ShybTwoResp> queryFeehzTwo(ShybbbReq req){
        return ReturnEntityUtil.success(shybMxdzSer.QueryFeehzTwoList(req,getUser()));
    }







    @RequestMapping("/gsbxopbb")
    @ResponseBody
    @ApiOperation(value="工伤保险门诊医保上报表" ,httpMethod="POST")
    public ReturnEntity<PageInfo<YbOpGsbxbResp>> queryGsbxopPage(ShybbbReq req ){
        //20200913/162923/    20200915/162923/  1
        return ReturnEntityUtil.success(shybMxdzSer.QueryYbGsbxopList(req));
    }
    @RequestMapping("/gsbxopbbdc")
    @ResponseBody
    @ApiOperation(value="工伤保险门诊医保上报表导出" ,httpMethod="POST")
    public ReturnEntity  queryGsbxopPagedc(ShybbbReq req){
        //2020-09-13 2020-09-15  1
        String path = shybMxdzSer.QueryYbGsbxopListdc(req);
        return ReturnEntityUtil.success(path);
    }


    @RequestMapping("/pkjsbbb")
    @ResponseBody
    @ApiOperation(value="贫困精神病" ,httpMethod="POST")
    public ReturnEntity<PageInfo<ybbbpkjsbResp>> queryYbPkjsbPage(ShybbbReq req){
        //20200913/162923/    20200915/162923/  1
        return ReturnEntityUtil.success(shybMxdzSer.QueryYbPkjsbList(req));
    }
    @RequestMapping("/pkjsbbbdc")
    @ResponseBody
    @ApiOperation(value="贫困精神病导出" ,httpMethod="POST")
    public ReturnEntity  qqueryYbPkjsbPagedc(ShybbbReq req){
        //2020-09-13 2020-09-15  1
        String path = shybMxdzSer.QueryYbPkjsbListdc(req);
        return ReturnEntityUtil.success(path);
    }

    @RequestMapping("/mzbkbb")
    @ResponseBody
    @ApiOperation(value="民政帮困" ,httpMethod="POST")
    public ReturnEntity<PageInfo<YbbbMzbkResp>> queryYbMzbkPage(ShybbbReq req){
        //20200913/162923/    20200915/162923/  1
        return ReturnEntityUtil.success(shybMxdzSer.QueryYbMzbkList(req));
    }
    @RequestMapping("/mzbkbbdc")
    @ResponseBody
    @ApiOperation(value="民政帮困导出" ,httpMethod="POST")
    public ReturnEntity queryYbMzbkPagedc(ShybbbReq req){
        //2020-09-13 2020-09-15  1
        String path = shybMxdzSer.QueryYbMzbkListdc(req);
        return ReturnEntityUtil.success(path);
    }


    @RequestMapping("/fssscmzbb")
    @ResponseBody
    @ApiOperation(value="非实时上传门诊" ,httpMethod="POST")
    public ReturnEntity<PageInfo<YbbbFssscopResp>> queryYbFssscopPage(ShybbbReq req){
        //20200913/162923/    20200915/162923/  1
        return ReturnEntityUtil.success(shybMxdzSer.QueryYbFssscopList(req));
    }
    @RequestMapping("/fssscmzbbdc")
    @ResponseBody
    @ApiOperation(value="非实时上传门诊导出" ,httpMethod="POST")
    public ReturnEntity  queryYbFssscopPagedc(ShybbbReq req){
        //2020-09-13 2020-09-15  1
        String path = shybMxdzSer.QueryYbFssscopListdc(req);
        return ReturnEntityUtil.success(path);
    }

    @RequestMapping("/fsssczybb")
    @ResponseBody
    @ApiOperation(value="非实时上传住院费用明细" ,httpMethod="POST")
    public ReturnEntity<PageInfo<YbbbFssscimResp>> queryYbFssscimPage(ShybbbReq req){
        //20200913/162923/    20200915/162923/  1
        return ReturnEntityUtil.success(shybMxdzSer.QueryYbFssscimList(req));
    }
    @RequestMapping("/fssscimbbdc")
    @ResponseBody
    @ApiOperation(value="非实时上传住院费用明细导出" ,httpMethod="POST")
    public ReturnEntity  queryYbFssscimPagedc(ShybbbReq req){
        //2020-09-13 2020-09-15  1
        String path = shybMxdzSer.QueryYbFssscimListdc(req);
        return ReturnEntityUtil.success(path);
    }

    @RequestMapping("/fsssczyjlbb")
    @ResponseBody
    @ApiOperation(value="非实时上传住院结算记录" ,httpMethod="POST")
    public ReturnEntity<PageInfo<YbbbFssscimjlResp>> queryYbFssscimjlPage(ShybbbReq req ){
        //20200913/162923/    20200915/162923/  1
        return ReturnEntityUtil.success(shybMxdzSer.QueryYbFssscimjlList(req));
    }
    @RequestMapping("/fsssczybbdc")
    @ResponseBody
    @ApiOperation(value="非实时上传住院结算记录导出" ,httpMethod="POST")
    public ReturnEntity  queryYbFssscimjlPagedc(ShybbbReq req){
        //2020-09-13 2020-09-15  1
        String path = shybMxdzSer.QueryYbFssscimjlListdc(req);
        return ReturnEntityUtil.success(path);
    }


    @RequestMapping("/ldrksbb")
    @ResponseBody
    @ApiOperation(value="流动人口医保上报表" ,httpMethod="POST")
    public ReturnEntity<PageInfo<LdrkSbbResp>> queryLdrkPage(ShybbbReq req ){
        //20200913/162923/    20200915/162923/  1
        return ReturnEntityUtil.success(shybMxdzSer.QueryLdrkCityNormalList(req));
    }




    @RequestMapping("/bbtodbf")
    @ResponseBody
    @ApiOperation(value="测试导出dbf" ,httpMethod="POST")
    public void queryYbYdybPage() throws Exception {
        //20200913/162923/    20200915/162923/  1
        //     shybMxdzSer.dbfWriter();
    }

}
