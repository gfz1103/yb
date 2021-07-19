package com.buit.his.shyb.upload.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.buit.his.shyb.upload.dao.BasyUploadDao;
import com.buit.his.shyb.upload.model.*;
import com.buit.his.shyb.upload.response.basyResponse.*;
import com.buit.his.shyb.upload.status.UpLoadStatus;
import com.buit.his.shyb.upload.uploadutils.DataObjectUtils;
import com.buit.system.model.DicXzqh;
import com.buit.system.response.DicKszdModel;
import com.buit.system.response.HrPersonnelModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author weijing
 * @Date 2020-10-27 13:39
 * @Description
 **/
@Service
public class BasyUpLoadSer {

    @Autowired
    private BasyUploadDao basyUploadDao;

    @Autowired
    private RpcSer rpcSer;


    /**
     * 病案首页上传
     * @param yljgd
     * @return
     */
    public String basyUpLoad(Integer yljgd){
        BasyUploadRoot root = new BasyUploadRoot();
        //查询省市县字典【转编码为中文】
        List<DicXzqh> ssxList = rpcSer.findSSXList();
        //查询医生字典【对应医保医生编码】
        List<HrPersonnelModel> doctorList = rpcSer.findDoctorList(yljgd);
        //查询科室字典【对应医保科室代码】
        List<DicKszdModel> ksList = rpcSer.findKsList(yljgd);

        List<BasyUpload> basyUploadList = basyUploadDao.basyUpload();// TODO: 2020/11/3 条件增加医疗机构代码 ？
        if (CollUtil.isNotEmpty(basyUploadList)){
            List<BasyUpLoadResp> resps = basyUploadList.stream().map(o -> {
                BasyUpLoadResp resp = new BasyUpLoadResp();
                //处理病案主数据
                dealBasy(o,resp,ssxList,doctorList,ksList);
                //处理病案诊断
                dealBasyZd(resp,o.getYLJGD(),o.getBAH());
                //处理病案手术
                dealBasySs(resp,doctorList,o.getYLJGD(),o.getBAH());
                //处理病案费用
                dealBasyFy(resp,o.getYLJGD(),o.getBAH());
                //病案首页中心交易流水号
                dealbasyZxjylsh(resp);
                //发票和结算信息
                dealbasyFphjsxx(resp);
                return resp;
            }).collect(Collectors.toList());
            root.setList(resps);
            JSONObject jsonObject = JSONUtil.parseObj(root, false,true);

            Object list = jsonObject.get("list");

            System.out.println(list.toString());
            //return StringEscapeUtils.unescapeJava(jsonObject.toString());
            return list.toString();
        }
        return "";
    }

    /**
     * 处理病案首页主数据
     * @param resp
     * @param ssxList
     * @param doctorList
     * @param ksList
     */
    private void dealBasy(BasyUpload basyUpload,BasyUpLoadResp resp,List<DicXzqh> ssxList,List<HrPersonnelModel> doctorList,List<DicKszdModel> ksList){
        BeanUtils.copyProperties(basyUpload,resp);
        //上传年月设置
        resp.setSBNF(String.valueOf(DateUtil.year(DateUtil.date())).substring(2,4));
        resp.setSBYF(String.valueOf(DateUtil.month(DateUtil.date())));
        resp.setSBR(String.valueOf(DateUtil.dayOfMonth(DateUtil.date())));
        resp.setFLBM(UpLoadStatus.Flbm.BASY.getCode());

        //姓名 身份证号SM4加密
        SymmetricCrypto sm4 = SmUtil.sm4();
        resp.setXM(sm4.encryptHex(basyUpload.getXM(), CharsetUtil.CHARSET_UTF_8));
        resp.setSFZH(sm4.encryptHex(basyUpload.getSFZH(), CharsetUtil.CHARSET_UTF_8));

        //年龄不足一周岁的年龄(月龄) 计算
        int bzyzsnl = 0;
        if (StrUtil.isNotBlank(basyUpload.getNLDW())){
            bzyzsnl = Integer.valueOf(basyUpload.getNLDW()) * 30;
            if (StrUtil.isNotBlank(basyUpload.getNLFZ())){
                bzyzsnl += Integer.valueOf(basyUpload.getNLFZ());
            }
        }
        resp.setBZYZSNL(String.valueOf(bzyzsnl));

        //省市县转换
        ssxList.stream().filter(o ->o.getId().equals(basyUpload.getCSDSHENG())).forEach(o ->basyUpload.setCSDSHENG(o.getName()));//出生地省
        ssxList.stream().filter(o ->o.getId().equals(basyUpload.getCSDSHI())).forEach(o ->basyUpload.setCSDSHI(o.getName()));//出生地市
        ssxList.stream().filter(o ->o.getId().equals(basyUpload.getCSDXIAN())).forEach(o ->basyUpload.setCSDXIAN(o.getName()));//出生地县
        resp.setCSD((basyUpload.getCSDSHENG() == null ? "" : basyUpload.getCSDSHENG())
                .concat(basyUpload.getCSDSHI() == null ? "" : basyUpload.getCSDSHI())
                .concat(basyUpload.getCSDXIAN() == null ? "" : basyUpload.getCSDXIAN()));
        ssxList.stream().filter(o ->o.getId().equals(basyUpload.getJGSHENG())).forEach(o ->basyUpload.setJGSHENG(o.getName()));//籍贯省
        ssxList.stream().filter(o ->o.getId().equals(basyUpload.getJGSHI())).forEach(o ->basyUpload.setJGSHI(o.getName()));//籍贯市
        resp.setGG((basyUpload.getJGSHENG() == null ? "" : basyUpload.getJGSHENG())
                .concat(basyUpload.getJGSHI() == null ? "" : basyUpload.getJGSHI()));
        ssxList.stream().filter(o ->o.getId().equals(basyUpload.getHKDZSHENG())).forEach(o ->basyUpload.setHKDZSHENG(o.getName()));//户口地址省
        ssxList.stream().filter(o ->o.getId().equals(basyUpload.getHKDZSHI())).forEach(o ->basyUpload.setHKDZSHI(o.getName()));//户口地址市
        ssxList.stream().filter(o ->o.getId().equals(basyUpload.getHKDZXIAN())).forEach(o ->basyUpload.setHKDZXIAN(o.getName()));//户口地址县
        resp.setHKDZ((basyUpload.getHKDZSHENG() == null ? "" : basyUpload.getHKDZSHENG())
                .concat(basyUpload.getHKDZSHI() == null ? "" : basyUpload.getHKDZSHI())
                .concat(basyUpload.getHKDZXIAN() == null ? "" : basyUpload.getHKDZXIAN())
                .concat(basyUpload.getHKDZXXDZ() == null ? "" : basyUpload.getHKDZXXDZ()));


        //医生转换（医生编码转成医保对应的编码）
        doctorList.stream().filter(o ->String.valueOf(o.getPersonid()).equals(basyUpload.getKZR_DM())).forEach(o -> {resp.setKZR_DM(o.getCertificatenum());resp.setKZR(o.getPersonname());});//科主任代码
        doctorList.stream().filter(o ->String.valueOf(o.getPersonid()).equals(basyUpload.getZRYS_DM())).forEach(o ->{resp.setZRYS_DM(o.getCertificatenum());resp.setZRYS(o.getPersonname());});//主任（副主任）医生代码
        doctorList.stream().filter(o ->String.valueOf(o.getPersonid()).equals(basyUpload.getZZYS_DM())).forEach(o ->{resp.setZZYS_DM(o.getCertificatenum());resp.setZZYS(o.getPersonname());});//主治医生代码
        doctorList.stream().filter(o ->String.valueOf(o.getPersonid()).equals(basyUpload.getZYYS_DM())).forEach(o ->{resp.setZYYS_DM(o.getCertificatenum());resp.setZYYS(o.getPersonname());});//住院医生代码
        doctorList.stream().filter(o ->String.valueOf(o.getPersonid()).equals(basyUpload.getZRHS())).forEach(o ->resp.setZRHS(o.getCertificatenum()));//责任护士代码
        doctorList.stream().filter(o ->String.valueOf(o.getPersonid()).equals(basyUpload.getJXYS_DM())).forEach(o ->{resp.setJXYS_DM(o.getCertificatenum());resp.setJXYS(o.getPersonname());});//进修医生代码
        doctorList.stream().filter(o ->String.valueOf(o.getPersonid()).equals(basyUpload.getSXYS())).forEach(o ->resp.setSXYS(o.getCertificatenum()));//实习医师代码
        doctorList.stream().filter(o ->String.valueOf(o.getPersonid()).equals(basyUpload.getBMY())).forEach(o ->resp.setBMY(o.getCertificatenum()));//编码员代码
        doctorList.stream().filter(o ->String.valueOf(o.getPersonid()).equals(basyUpload.getZKYS())).forEach(o ->resp.setZKYS(o.getCertificatenum()));//质控医生代码
        doctorList.stream().filter(o ->String.valueOf(o.getPersonid()).equals(basyUpload.getZKHS())).forEach(o ->resp.setZKHS(o.getCertificatenum()));//质控护士代码

        //科室转换（科室对应医保科室编码）
        ksList.stream().filter(o ->String.valueOf(o.getId()).equals(basyUpload.getRYKB())&o.getOrganizcode().equals(basyUpload.getYLJGD())).forEach(o ->resp.setRYKB(o.getYbks()));//入院科别
        ksList.stream().filter(o ->String.valueOf(o.getId()).equals(basyUpload.getZKKB())&o.getOrganizcode().equals(basyUpload.getYLJGD())).forEach(o ->resp.setZKKB(o.getYbks()));//转科科别
        ksList.stream().filter(o ->String.valueOf(o.getId()).equals(basyUpload.getCYKB())&o.getOrganizcode().equals(basyUpload.getYLJGD())).forEach(o ->resp.setCYKB(o.getYbks()));//出院科别

        //接受机构（2医嘱转院 3医嘱转社区卫生服务机构/乡镇卫生院）
        if (UpLoadStatus.lyfs.LYFS_YZZY.getCode().equals(basyUpload.getLYFS())){
            resp.setYZZY_YLJG(basyUpload.getNJSYLJGMC());
        }else if (UpLoadStatus.lyfs.LYFS_YZZWSY.getCode().equals(basyUpload.getLYFS())){
            resp.setWSY_YLJG(basyUpload.getNJSYLJGMC());
        }

        //费用信息  todo 费用数据获取方式是否正确？  其他费用代表啥？？
        List<BasyTotalFee> basyTotalFees = basyUploadDao.basyTotalFee(basyUpload.getYLJGD(), basyUpload.getJYH());
        if (CollUtil.isNotEmpty(basyTotalFees)){
            BasyTotalFee totalFee = basyTotalFees.get(0);
            resp.setZFY(totalFee.getZFY());
            resp.setZFJE(totalFee.getZFJE());
            resp.setZFIJE(totalFee.getZFIJE());
            resp.setQTZF(totalFee.getQTZF());
        }
        DataObjectUtils.coverEmptyNullToString(resp);
    }

    /**
     * 病案首页诊断上传数据
     * @param resp
     * @param yljgd
     * @param bah
     */
    private void dealBasyZd(BasyUpLoadResp resp,Integer yljgd,String bah){
        List<BasyZdUpload> basyZdUploads = basyUploadDao.basyZdUpload(yljgd, bah);
        if (CollUtil.isNotEmpty(basyZdUploads)){
            List<BasyZdXx> collect = basyZdUploads.stream().map(o -> {
                BasyZdXx zdXx = new BasyZdXx();
                BeanUtils.copyProperties(o, zdXx);

                DataObjectUtils.coverEmptyNullToString(zdXx);
                return zdXx;
            }).collect(Collectors.toList());
            resp.setZDXX(collect);
        }else {
            List<BasyZdXx> basyZdXxes = new ArrayList<>();
            resp.setZDXX(basyZdXxes);
        }
    }

    /**
     * 病案首页手术信息
     * @param resp
     * @param doctorList
     * @param yljgd
     * @param bah
     */
    private void dealBasySs(BasyUpLoadResp resp,List<HrPersonnelModel> doctorList,Integer yljgd,String bah){
        List<BasySsUpload> basySsUploads = basyUploadDao.basySsUpload(yljgd, bah);
        if (CollUtil.isNotEmpty(basySsUploads)){
            List<BasySsXx> collect = basySsUploads.stream().map(item -> {
                BasySsXx ssXx = new BasySsXx();
                BeanUtils.copyProperties(item, ssXx);

                //转换医生
                doctorList.stream().filter(o ->String.valueOf(o.getPersonid()).equals(ssXx.getSZ_DM())).forEach(o -> {ssXx.setSZ_DM(o.getCertificatenum());ssXx.setSZ(o.getPersonname());});//术者信息
                doctorList.stream().filter(o ->String.valueOf(o.getPersonid()).equals(ssXx.getYZ())).forEach(o -> {ssXx.setYZ(o.getCertificatenum());});//一助信息
                doctorList.stream().filter(o ->String.valueOf(o.getPersonid()).equals(ssXx.getEZ())).forEach(o -> {ssXx.setEZ(o.getCertificatenum());});//二助信息
                doctorList.stream().filter(o ->String.valueOf(o.getPersonid()).equals(ssXx.getMZYSDM())).forEach(o -> {ssXx.setMZYSDM(o.getCertificatenum());ssXx.setMZYS(o.getPersonname());});//麻醉师信息

                DataObjectUtils.coverEmptyNullToString(ssXx);
                return ssXx;
            }).collect(Collectors.toList());
            resp.setSSXX(collect);
        }else {
            List<BasySsXx> ssXxes = new ArrayList<>();
            resp.setSSXX(ssXxes);
        }
    }

    /**
     * 病案首页费用上传数据
     * @param resp
     * @param yljgd
     * @param bah
     */
    private void dealBasyFy(BasyUpLoadResp resp,Integer yljgd,String bah){
        List<BasyFyUpload> basyFyUploads = basyUploadDao.basyFyUpload(yljgd, bah);
        if (CollUtil.isNotEmpty(basyFyUploads)){
            List<BasyFyXx> collect = basyFyUploads.stream().map(o -> {
                BasyFyXx fyXx = new BasyFyXx();
                BeanUtils.copyProperties(o, fyXx);
                DataObjectUtils.coverEmptyNullToString(fyXx);
                return fyXx;
            }).collect(Collectors.toList());
            resp.setFYXX(collect);
        }else {
            List<BasyFyXx> basyFyXxes = new ArrayList<>();
            resp.setFYXX(basyFyXxes);
        }
    }

    /**
     * 病案首页中心流水号
     * @param resp
     */
    private void dealbasyZxjylsh(BasyUpLoadResp resp){
        //BasyZxlshXx zxlshXx = new BasyZxlshXx();
        // TODO: 2020/11/2 等待视图

        List<BasyZxlshXx> basyZxlshXxes = new ArrayList<>();
        resp.setJYLSHXX(basyZxlshXxes);
    }

    /**
     * 病案首页发票和结算信息
     * @param resp
     */
    private void dealbasyFphjsxx(BasyUpLoadResp resp){
        //BasyFpXx fpXx = new BasyFpXx();

        // TODO: 2020/11/2 等待视图

        List<BasyFpXx> fpXxes = new ArrayList<>();
        resp.setFPXX(fpXxes);
    }


    public static void main(String[] args) {
//        BasyUpLoadResp resp = new BasyUpLoadResp();
////        BasyFyXx fyXx = new BasyFyXx();
////        fyXx.setBAFYLB("是的");
////        fyXx.setJE_0(new BigDecimal("20.25"));
////        ArrayList<BasyFyXx> basyFyXxes = new ArrayList<>();
////        basyFyXxes.add(fyXx);
////        resp.setFYXX(basyFyXxes);
////        resp.setBAH("12222");
////
////        //将空值转换成空字符串
////        JSONObject jsonObject = JSONUtil.parseObj(resp, false,true);
////        System.out.println(jsonObject.toString());

//        SymmetricCrypto sm4 = SmUtil.sm4();
//        String encrypt = sm4.encryptHex("11111",CharsetUtil.CHARSET_UTF_8);
//        System.out.println(encrypt);

//        String data = "{\\\"list\\\":[{\\\"SBNF\\\":null,\\\"SBYF\\\":null,\\\"SBR\\\":null,\\\"FLBM\\\":null,\\\"JYH\\\":\\\"310112041\\\",\\\"JGDM\\\":\\\"1001\\\",\\\"SBRQ\\\":\\\"20201102\\\",\\\"JYLSHXX\\\":null,\\\"YBSSJSBZ\\\":\\\"\\\",\\\"CYBZ\\\":\\\"\\\",\\\"SBLB\\\":\\\"\\\",\\\"ZXYBZ\\\":\\\"01\\\",\\\"YLFKFS\\\":\\\"1\\\",\\\"JKKH\\\":\\\"20201019029\\\",\\\"ZYCS\\\":\\\"2\\\",\\\"BAH\\\":\\\"0000205\\\",\\\"XM\\\":\\\"762a1fbd1ab96b495544e1ff8557d9ad\\\",\\\"XB\\\":\\\"1\\\",\\\"CSRQ\\\":\\\"1991-09-09\\\",\\\"NL\\\":\\\"30\\\",\\\"GJ\\\":\\\"156\\\",\\\"BZYZSNL\\\":\\\"124\\\",\\\"XSECSTZ\\\":null,\\\"XSERYTZ\\\":null,\\\"CSD\\\":\\\"110000110100110101\\\",\\\"GG\\\":\\\"110000110100\\\",\\\"MZ\\\":\\\"1\\\",\\\"ZJLX\\\":\\\"1\\\",\\\"SFZH\\\":\\\"22512f21c51b29c3beb66c24b23379d4f0a988284140977fc1b7e69f1f2459f9\\\",\\\"ZY\\\":\\\"11\\\",\\\"HY\\\":\\\"1\\\",\\\"XZZ_S0\\\":\\\"110000\\\",\\\"XZZ_S1\\\":\\\"110100\\\",\\\"XZZ_X\\\":\\\"110101\\\",\\\"XZZ_DZ\\\":\\\"萨达撒阿达撒斯达舒啊实打实的是ad\\\",\\\"DH\\\":\\\"18712981213\\\",\\\"YB1\\\":\\\"231029\\\",\\\"HKDZ\\\":null,\\\"YB2\\\":\\\"231092\\\",\\\"GZDWJMC\\\":\\\"上海同济大学李子园天佑医院\\\",\\\"DWDH\\\":\\\"021-81928129\\\",\\\"YB3\\\":\\\"231600\\\",\\\"LXRXM\\\":\\\"张三\\\",\\\"GX\\\":\\\"1\\\",\\\"LXR_S0\\\":\\\"\\\",\\\"LXR_S1\\\":\\\"\\\",\\\"LXR_X\\\":\\\"\\\",\\\"LXR_DZ\\\":\\\"北京市东城区\\\",\\\"DH1\\\":\\\"18329182981\\\",\\\"RYTJ\\\":\\\"1\\\",\\\"RYSJ\\\":\\\"2020-09-01 15:31:44\\\",\\\"RYKB\\\":\\\"989\\\",\\\"RYBF\\\":\\\"西1病房\\\",\\\"ZKKB\\\":\\\"990\\\",\\\"WSY_YLJG\\\":null,\\\"YZZY_YLJG\\\":null,\\\"CYSJ\\\":\\\"2020-09-29 15:32:33\\\",\\\"CYKB\\\":\\\"991\\\",\\\"SJZYTS\\\":\\\"29\\\",\\\"MZZD\\\":\\\"西医诊断\\\",\\\"JBBM_S\\\":\\\"XY.1212\\\",\\\"MZZD_XYZD\\\":\\\"中医诊断\\\",\\\"JBBM_S2\\\":\\\"ZY.231\\\",\\\"WBYY\\\":\\\"大火烧伤\\\",\\\"JBBM1_S\\\":\\\"SSZD.123432\\\",\\\"BLZD\\\":\\\"高血压\\\",\\\"JBBM2_S\\\":\\\"202\\\",\\\"BLH\\\":\\\"1\\\",\\\"YWGM\\\":\\\"1\\\",\\\"GMYW\\\":\\\"盐酸二甲双胍片\\\",\\\"SJ\\\":\\\"1\\\",\\\"XX\\\":\\\"1\\\",\\\"RH\\\":\\\"1\\\",\\\"QJCS\\\":null,\\\"CGCS\\\":null,\\\"SXFY\\\":\\\"\\\",\\\"RCMDSC\\\":null,\\\"XSRJBSC\\\":null,\\\"CHCX\\\":null,\\\"XSRXB\\\":null,\\\"XSRTZ\\\":null,\\\"XB_SBT\\\":null,\\\"TZ_SBT\\\":null,\\\"XB_SABT\\\":null,\\\"TZ_SABT\\\":null,\\\"KZR_DM\\\":\\\"52\\\",\\\"KZR\\\":null,\\\"ZRYS_DM\\\":\\\"53\\\",\\\"ZRYS\\\":null,\\\"ZZYS_DM\\\":\\\"54\\\",\\\"ZZYS\\\":null,\\\"ZYYS_DM\\\":\\\"59\\\",\\\"ZYYS\\\":null,\\\"JXYS_DM\\\":\\\"51\\\",\\\"JXYS\\\":null,\\\"ZRHS\\\":\\\"60\\\",\\\"SXYS\\\":\\\"50\\\",\\\"BMY\\\":\\\"49\\\",\\\"BAZL\\\":\\\"1\\\",\\\"ZKYS\\\":\\\"40\\\",\\\"ZKHS\\\":\\\"44\\\",\\\"ZKRQ\\\":\\\"2020-09-27\\\",\\\"LYFS\\\":\\\"1\\\",\\\"ZZYJH\\\":\\\"1\\\",\\\"MD\\\":\\\"1\\\",\\\"RYQ_T\\\":\\\"1\\\",\\\"RYQ_XS\\\":\\\"1\\\",\\\"RYQ_F\\\":\\\"1\\\",\\\"RYH_T\\\":\\\"1\\\",\\\"RYH_XS\\\":\\\"1\\\",\\\"RYH_F\\\":\\\"1\\\",\\\"ZFY\\\":null,\\\"ZFJE\\\":null,\\\"ZFIJE\\\":null,\\\"QTZF\\\":null,\\\"ZLLB\\\":\\\"1\\\",\\\"SSLCLJ2\\\":\\\"1\\\",\\\"ZYYJ\\\":\\\"1\\\",\\\"ZYZLSB\\\":\\\"1\\\",\\\"ZYZLJS\\\":\\\"1\\\",\\\"BZSH\\\":\\\"1\\\",\\\"ZDXX\\\":[{\\\"ZDXH\\\":\\\"1\\\",\\\"ZDBM1\\\":\\\"EM.999\\\",\\\"ZDMC1\\\":\\\"高血压\\\",\\\"ZYZDBM\\\":\\\"\\\",\\\"ZYZDMC\\\":\\\"\\\",\\\"RYBQ\\\":\\\"1\\\",\\\"CYQK\\\":\\\"\\\"},{\\\"ZDXH\\\":\\\"2\\\",\\\"ZDBM1\\\":\\\"MC.980\\\",\\\"ZDMC1\\\":\\\"糖尿病\\\",\\\"ZYZDBM\\\":\\\"\\\",\\\"ZYZDMC\\\":\\\"\\\",\\\"RYBQ\\\":\\\"2\\\",\\\"CYQK\\\":\\\"\\\"}],\\\"SSXX\\\":[{\\\"SSXH\\\":\\\"00\\\",\\\"SSBM1\\\":\\\"0012\\\",\\\"SSJCZMC1\\\":\\\"切割手术\\\",\\\"SSJCZRQ\\\":\\\"20200910\\\",\\\"SSJB\\\":\\\"1\\\",\\\"SZ_DM\\\":\\\"59\\\",\\\"SZ\\\":null,\\\"YZ\\\":\\\"60\\\",\\\"EZ\\\":\\\"1039\\\",\\\"QKYLB\\\":\\\"1\\\",\\\"MZFS\\\":\\\"1\\\",\\\"MZYSDM\\\":\\\"54\\\",\\\"MZYS\\\":null}],\\\"FYXX\\\":[{\\\"BAFYLB\\\":null,\\\"JE_0\\\":null}],\\\"FPXX\\\":null},{\\\"SBNF\\\":null,\\\"SBYF\\\":null,\\\"SBR\\\":null,\\\"FLBM\\\":null,\\\"JYH\\\":\\\"310112042\\\",\\\"JGDM\\\":\\\"1001\\\",\\\"SBRQ\\\":\\\"20201102\\\",\\\"JYLSHXX\\\":null,\\\"YBSSJSBZ\\\":\\\"\\\",\\\"CYBZ\\\":\\\"\\\",\\\"SBLB\\\":\\\"\\\",\\\"ZXYBZ\\\":\\\"01\\\",\\\"YLFKFS\\\":\\\"1\\\",\\\"JKKH\\\":\\\"20201019028\\\",\\\"ZYCS\\\":\\\"2\\\",\\\"BAH\\\":\\\"0000206\\\",\\\"XM\\\":\\\"cfa123e5918606cf500d1d5f311236ba\\\",\\\"XB\\\":\\\"1\\\",\\\"CSRQ\\\":\\\"2020-10-20\\\",\\\"NL\\\":\\\"20\\\",\\\"GJ\\\":\\\"\\\",\\\"BZYZSNL\\\":\\\"0\\\",\\\"XSECSTZ\\\":\\\"0\\\",\\\"XSERYTZ\\\":null,\\\"CSD\\\":\\\"000\\\",\\\"GG\\\":\\\"00\\\",\\\"MZ\\\":\\\"\\\",\\\"ZJLX\\\":\\\"1\\\",\\\"SFZH\\\":\\\"826c6faac8ce382adf22eb23c135fed3\\\",\\\"ZY\\\":\\\"\\\",\\\"HY\\\":\\\"\\\",\\\"XZZ_S0\\\":\\\"0\\\",\\\"XZZ_S1\\\":\\\"0\\\",\\\"XZZ_X\\\":\\\"0\\\",\\\"XZZ_DZ\\\":\\\"\\\",\\\"DH\\\":\\\"\\\",\\\"YB1\\\":\\\"\\\",\\\"HKDZ\\\":null,\\\"YB2\\\":\\\"\\\",\\\"GZDWJMC\\\":\\\"\\\",\\\"DWDH\\\":\\\"\\\",\\\"YB3\\\":\\\"\\\",\\\"LXRXM\\\":\\\"\\\",\\\"GX\\\":\\\"\\\",\\\"LXR_S0\\\":\\\"\\\",\\\"LXR_S1\\\":\\\"\\\",\\\"LXR_X\\\":\\\"\\\",\\\"LXR_DZ\\\":\\\"\\\",\\\"DH1\\\":\\\"\\\",\\\"RYTJ\\\":\\\"\\\",\\\"RYSJ\\\":\\\"2020-10-01 14:49:30\\\",\\\"RYKB\\\":\\\"1092121\\\",\\\"RYBF\\\":\\\"\\\",\\\"ZKKB\\\":\\\"\\\",\\\"WSY_YLJG\\\":null,\\\"YZZY_YLJG\\\":null,\\\"CYSJ\\\":\\\"2020-10-08 14:49:57\\\",\\\"CYKB\\\":\\\"1092121\\\",\\\"SJZYTS\\\":\\\"0\\\",\\\"MZZD\\\":\\\"\\\",\\\"JBBM_S\\\":\\\"\\\",\\\"MZZD_XYZD\\\":\\\"\\\",\\\"JBBM_S2\\\":\\\"\\\",\\\"WBYY\\\":\\\"\\\",\\\"JBBM1_S\\\":\\\"\\\",\\\"BLZD\\\":\\\"\\\",\\\"JBBM2_S\\\":\\\"\\\",\\\"BLH\\\":\\\"\\\",\\\"YWGM\\\":\\\"\\\",\\\"GMYW\\\":\\\"\\\",\\\"SJ\\\":\\\"\\\",\\\"XX\\\":\\\"\\\",\\\"RH\\\":\\\"\\\",\\\"QJCS\\\":null,\\\"CGCS\\\":null,\\\"SXFY\\\":\\\"\\\",\\\"RCMDSC\\\":null,\\\"XSRJBSC\\\":null,\\\"CHCX\\\":null,\\\"XSRXB\\\":null,\\\"XSRTZ\\\":null,\\\"XB_SBT\\\":null,\\\"TZ_SBT\\\":null,\\\"XB_SABT\\\":null,\\\"TZ_SABT\\\":null,\\\"KZR_DM\\\":\\\"\\\",\\\"KZR\\\":null,\\\"ZRYS_DM\\\":\\\"\\\",\\\"ZRYS\\\":null,\\\"ZZYS_DM\\\":\\\"\\\",\\\"ZZYS\\\":null,\\\"ZYYS_DM\\\":\\\"\\\",\\\"ZYYS\\\":null,\\\"JXYS_DM\\\":\\\"\\\",\\\"JXYS\\\":null,\\\"ZRHS\\\":\\\"\\\",\\\"SXYS\\\":\\\"\\\",\\\"BMY\\\":\\\"\\\",\\\"BAZL\\\":\\\"\\\",\\\"ZKYS\\\":\\\"\\\",\\\"ZKHS\\\":\\\"\\\",\\\"ZKRQ\\\":null,\\\"LYFS\\\":\\\"\\\",\\\"ZZYJH\\\":\\\"\\\",\\\"MD\\\":\\\"\\\",\\\"RYQ_T\\\":\\\"0\\\",\\\"RYQ_XS\\\":\\\"0\\\",\\\"RYQ_F\\\":\\\"0\\\",\\\"RYH_T\\\":\\\"0\\\",\\\"RYH_XS\\\":\\\"0\\\",\\\"RYH_F\\\":\\\"0\\\",\\\"ZFY\\\":null,\\\"ZFJE\\\":null,\\\"ZFIJE\\\":null,\\\"QTZF\\\":null,\\\"ZLLB\\\":\\\"\\\",\\\"SSLCLJ2\\\":\\\"\\\",\\\"ZYYJ\\\":\\\"\\\",\\\"ZYZLSB\\\":\\\"\\\",\\\"ZYZLJS\\\":\\\"\\\",\\\"BZSH\\\":\\\"\\\",\\\"ZDXX\\\":null,\\\"SSXX\\\":null,\\\"FYXX\\\":null,\\\"FPXX\\\":null}]}";
//        System.out.println(StringEscapeUtils.unescapeJava(data));

        BasyUploadRoot basyUploadRoot = new BasyUploadRoot();
        DataObjectUtils.coverEmptyNullToString(basyUploadRoot);

    }
}
