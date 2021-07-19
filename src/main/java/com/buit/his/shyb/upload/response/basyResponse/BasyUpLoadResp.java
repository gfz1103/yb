package com.buit.his.shyb.upload.response.basyResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author weijing
 * @Date 2020-10-27 13:40
 * @Description
 **/
@ApiModel("病案首页医保上传返回参数")
public class BasyUpLoadResp {
    @ApiModelProperty("上报年份")
    /**上报年份 年份中最后二位 **/
    private String SBNF;

    @ApiModelProperty("上报月份")
    /**上报月份**/
    private String SBYF;

    @ApiModelProperty("上报日")
    /**上报日 **/
    private String SBR;

    @ApiModelProperty("文件分类编码")
    /**文件分类编码 A：病案首页
     B：住院自费及医保非实时病人交易明细数据
     C：出院小结
     D：出院医嘱信息
     E：门诊自费及医保非实时病人交易明细数据
     F：门诊处方信息
     G：实验室检验报告
     H：医学影像检查报告**/
    private String FLBM;

    @ApiModelProperty("就诊流水号")
    /**就诊流水号 医院HIS系统的唯一编号**/
    private String JYH;

    @ApiModelProperty("医保医疗机构代码")
    /**医保医疗机构代码**/
    private String JGDM;

    @ApiModelProperty("申报日期")
    /**申报日期 格式：YYYYMMDD**/
    private String SBRQ;

    @ApiModelProperty("中心交易流水号(循环体)")
    /**中心交易流水号(循环体) 医保实时患者填写“中心流水号”，自费及医保非实时患者填写16个0**/
    private List<BasyZxlshXx> JYLSHXX;

    @ApiModelProperty("医保实时结算标志")
    /**医保实时结算标志 1:医保实时结算
     2:非医保实时结算（包含全自费结算）**/
    private String YBSSJSBZ;

    @ApiModelProperty("出院结算标志")
    /**出院结算标志 1:出院结算、2:在院结算**/
    private String CYBZ;

    @ApiModelProperty("医保支付方式")
    /**医保支付方式 1:按项目 2:单病种3:按病种分值4:疾病诊断相关分组（DRG）5:按床日6:按人头9:其它**/
    private String SBLB;

    @ApiModelProperty("中西医标志")
    /**中西医标志 西医:01、中医:02**/
    private String ZXYBZ;

    @ApiModelProperty("医疗付费方式")
    /**医疗付费方式**/
    private String YLFKFS;

    @ApiModelProperty("就诊卡号")
    /**就诊卡号**/
    private String JKKH;

    @ApiModelProperty("住院次数")
    /**住院次数**/
    private String ZYCS;

    @ApiModelProperty("病案号")
    /**病案号**/
    private String BAH;

    @ApiModelProperty("姓名")
    /**姓名  加密：个人隐私信息传输 todo**/
    private String XM;

    @ApiModelProperty("性别")
    /**性别**/
    private String XB;

    @ApiModelProperty("出生日期")
    /**出生日期 格式：YYYYMMDD**/
    private String CSRQ;

    @ApiModelProperty("年龄")
    /**年龄**/
    private String NL;

    @ApiModelProperty("国籍")
    /**国籍**/
    private String GJ;

    @ApiModelProperty("年龄不足一周岁的年龄(月龄)")
    /**年龄不足一周岁的年龄(月龄) 单位：天**/
    private String BZYZSNL;

    @ApiModelProperty("新生儿出生体重")
    /**新生儿出生体重**/
    private String XSECSTZ;

    @ApiModelProperty("新生儿入院体重")
    /**新生儿入院体重**/
    private String XSERYTZ;

    @ApiModelProperty("出生地")
    /**出生地**/
    private String CSD;

    @ApiModelProperty("籍贯")
    /**籍贯**/
    private String GG;

    @ApiModelProperty("民族")
    /**民族**/
    private String MZ;

    @ApiModelProperty("患者证件类别")
    /**患者证件类别**/
    private String ZJLX;

    @ApiModelProperty("患者证件号码")
    /**患者证件号码  加密 todo**/
    private String SFZH;

    @ApiModelProperty("职业")
    /**职业**/
    private String ZY;

    @ApiModelProperty("婚姻")
    /**婚姻**/
    private String HY;

    @ApiModelProperty("现住址（省）")
    /**现住址（省）**/
    private String XZZ_S0;

    @ApiModelProperty("现住址（市）")
    /**现住址（市）**/
    private String XZZ_S1;

    @ApiModelProperty("现住址（县）")
    /**现住址（县）**/
    private String XZZ_X;

    @ApiModelProperty("现住址（地址）")
    /**现住址（地址）**/
    private String XZZ_DZ;

    @ApiModelProperty("电话号码")
    /**电话号码**/
    private String DH;

    @ApiModelProperty("邮编(现住址)")
    /**邮编(现住址)**/
    private String YB1;

    @ApiModelProperty("户口地址")
    /**户口地址**/
    private String HKDZ;

    @ApiModelProperty("邮编(户口地址)")
    /**邮编(户口地址)**/
    private String YB2;

    @ApiModelProperty("工作单位名称")
    /**工作单位名称**/
    private String GZDWJMC;

    @ApiModelProperty("工作单位电话")
    /**工作单位电话**/
    private String DWDH;

    @ApiModelProperty("工作单位邮编")
    /**工作单位邮编**/
    private String YB3;

    @ApiModelProperty("联系人姓名")
    /**联系人姓名**/
    private String LXRXM;

    @ApiModelProperty("关系(联系人与患者关系)")
    /**关系(联系人与患者关系)**/
    private String GX;

    @ApiModelProperty("联系人地址（省）")
    /**联系人地址（省）**/
    private String LXR_S0;

    @ApiModelProperty("联系人地址（市）")
    /**联系人地址（市）**/
    private String LXR_S1;

    @ApiModelProperty("联系人地址（县）")
    /**联系人地址（县）**/
    private String LXR_X;

    @ApiModelProperty("联系人地址（地址）")
    /**联系人地址（地址）**/
    private String LXR_DZ;

    @ApiModelProperty("联系人电话号码")
    /**联系人电话号码**/
    private String DH1;

    @ApiModelProperty("入院途径")
    /**入院途径**/
    private String RYTJ;

    @ApiModelProperty("入院时间")
    /**入院时间  格式：yyyy-MM-dd HH:mm:ss**/
    private String RYSJ;

    @ApiModelProperty("入院科别")
    /**入院科别**/
    private String RYKB;

    @ApiModelProperty("入院病房")
    /**入院病房**/
    private String RYBF;

    @ApiModelProperty("转科科别")
    /**转科科别**/
    private String ZKKB;

    @ApiModelProperty("医嘱转社区")
    /**医嘱转社区**/
    private String WSY_YLJG;

    @ApiModelProperty("医嘱转院")
    /**医嘱转院**/
    private String YZZY_YLJG;

    @ApiModelProperty("出院时间")
    /**出院时间 格式：yyyy-MM-dd HH:mm:ss**/
    private String CYSJ;

    @ApiModelProperty("出院科别")
    /**出院科别**/
    private String CYKB;

    @ApiModelProperty("实际住院天数")
    /**实际住院天数**/
    private String SJZYTS;

    @ApiModelProperty("门(急)诊诊断名称（西医诊断）")
    /**门(急)诊诊断名称（西医诊断）**/
    private String MZZD;

    @ApiModelProperty("门(急)诊诊断名称（西医诊断）疾病代码")
    /**门(急)诊诊断名称（西医诊断）疾病代码**/
    private String JBBM_S;

    @ApiModelProperty("门(急)诊诊断名称（中医诊断）")
    /**门(急)诊诊断名称（中医诊断）**/
    private String MZZD_XYZD;

    @ApiModelProperty("门(急)诊诊断名称（中医诊断）疾病代码")
    /**门(急)诊诊断名称（中医诊断）疾病代码**/
    private String JBBM_S2;

    @ApiModelProperty("损伤、中毒的外部原因名称")
    /**损伤、中毒的外部原因名称**/
    private String WBYY;

    @ApiModelProperty("损伤、中毒的外部原因")
    /**损伤、中毒的外部原因**/
    private String JBBM1_S;

    @ApiModelProperty("病理诊断名称")
    /**病理诊断名称**/
    private String BLZD;

    @ApiModelProperty("病理诊断")
    /**病理诊断**/
    private String JBBM2_S;

    @ApiModelProperty("病理号")
    /**病理号**/
    private String BLH;

    @ApiModelProperty("药物过敏")
    /**药物过敏**/
    private String YWGM;

    @ApiModelProperty("过敏药物")
    /**过敏药物**/
    private String GMYW;

    @ApiModelProperty("死亡患者尸检")
    /**死亡患者尸检**/
    private String SJ;

    @ApiModelProperty("血型")
    /**血型**/
    private String XX;

    @ApiModelProperty("RH")
    /**RH**/
    private String RH;

    @ApiModelProperty("抢救次数")
    /**抢救次数**/
    private String QJCS;

    @ApiModelProperty("成功次数")
    /**成功次数**/
    private String CGCS;

    @ApiModelProperty("输血反应")
    /**输血反应**/
    private String SXFY;

    @ApiModelProperty("妊娠梅毒筛查")
    /**妊娠梅毒筛查**/
    private String RCMDSC;

    @ApiModelProperty("新生儿疾病筛查")
    /**新生儿疾病筛查**/
    private String XSRJBSC;

    @ApiModelProperty("产后出血")
    /**产后出血**/
    private String CHCX;

    @ApiModelProperty("新生儿性别")
    /**新生儿性别**/
    private String XSRXB;

    @ApiModelProperty("新生儿体重（g）")
    /**新生儿体重（g）**/
    private String XSRTZ;

    @ApiModelProperty("新生儿性别(双胞胎)")
    /**新生儿性别(双胞胎)**/
    private String XB_SBT;

    @ApiModelProperty("新生儿体重(双胞胎)")
    /**新生儿体重(双胞胎)**/
    private String TZ_SBT;

    @ApiModelProperty("新生儿性别(三胞胎)")
    /**新生儿性别(三胞胎)**/
    private String XB_SABT;

    @ApiModelProperty("新生儿体重(三胞胎)")
    /**新生儿体重(三胞胎)**/
    private String TZ_SABT;

    @ApiModelProperty("科主任医保医师代码")
    /**科主任医保医师代码**/
    private String KZR_DM;

    @ApiModelProperty("科主任姓名")
    /**科主任姓名**/
    private String KZR;

    @ApiModelProperty("主任（副主任）医保医师代码")
    /**主任（副主任）医保医师代码**/
    private String ZRYS_DM;

    @ApiModelProperty("主任（副主任）医师姓名")
    /**主任（副主任）医师姓名**/
    private String ZRYS;

    @ApiModelProperty("主治医师医保医师代码")
    /**主治医师医保医师代码**/
    private String ZZYS_DM;

    @ApiModelProperty("主治医师姓名")
    /**主治医师姓名**/
    private String ZZYS;

    @ApiModelProperty("住院医师医保医师代码")
    /**住院医师医保医师代码**/
    private String ZYYS_DM;

    @ApiModelProperty("住院医师姓名")
    /**住院医师姓名**/
    private String ZYYS;

    @ApiModelProperty("进修医生医保医师代码")
    /**进修医生医保医师代码**/
    private String JXYS_DM;

    @ApiModelProperty("进修医师姓名")
    /**进修医师姓名**/
    private String JXYS;

    @ApiModelProperty("责任护士")
    /**责任护士**/
    private String ZRHS;

    @ApiModelProperty("实习医师")
    /**实习医师**/
    private String SXYS;

    @ApiModelProperty("编码员")
    /**编码员**/
    private String BMY;

    @ApiModelProperty("病案质量")
    /**病案质量**/
    private String BAZL;

    @ApiModelProperty("质控医师")
    /**质控医师**/
    private String ZKYS;

    @ApiModelProperty("质控护士")
    /**质控护士**/
    private String ZKHS;

    @ApiModelProperty("质控日期")
    /**质控日期 格式：YYYYMMDD**/
    private String ZKRQ;

    @ApiModelProperty("离院方式")
    /**离院方式**/
    private String LYFS;

    @ApiModelProperty("是否有再住院计划")
    /**是否有再住院计划**/
    private String ZZYJH;

    @ApiModelProperty("目的")
    /**目的**/
    private String MD;

    @ApiModelProperty("入院前天")
    /**入院前天**/
    private String RYQ_T;

    @ApiModelProperty("入院前小时")
    /**入院前小时**/
    private String RYQ_XS;

    @ApiModelProperty("入院前分钟")
    /**入院前分钟**/
    private String RYQ_F;

    @ApiModelProperty("入院后天")
    /**入院后天**/
    private String RYH_T;

    @ApiModelProperty("入院后小时")
    /**入院后小时**/
    private String RYH_XS;

    @ApiModelProperty("入院后分钟")
    /**入院后分钟**/
    private String RYH_F;

    @ApiModelProperty("总费用")
    /**总费用**/
    private BigDecimal ZFY;

    @ApiModelProperty("自付金额")
    /**自付金额  医保支付范围内由个人自付金额**/
    private BigDecimal ZFJE;

    @ApiModelProperty("自费金额")
    /**自费金额 医保支付范围外由个人自费金额**/
    private BigDecimal ZFIJE;

    @ApiModelProperty("其他支付")
    /**其他支付**/
    private BigDecimal QTZF;

    @ApiModelProperty("治疗类别")
    /**治疗类别  中医病案首页**/
    private String ZLLB;

    @ApiModelProperty("实施临床路径")
    /**实施临床路径 中医病案首页、编码**/
    private String SSLCLJ2;

    @ApiModelProperty("使用医疗机构中药制剂")
    /**使用医疗机构中药制剂 中医病案首页**/
    private String ZYYJ;

    @ApiModelProperty("使用中医诊疗设备")
    /**使用中医诊疗设备 中医病案首页**/
    private String ZYZLSB;

    @ApiModelProperty("使用中医诊疗技术")
    /**使用中医诊疗技术 中医病案首页**/
    private String ZYZLJS;

    @ApiModelProperty("辩证施护")
    /**辩证施护 中医病案首页**/
    private String BZSH;

    @ApiModelProperty("诊断信息")
    /**诊断信息**/
    private List<BasyZdXx> ZDXX;

    @ApiModelProperty("手术信息")
    /**手术信息**/
    private List<BasySsXx> SSXX;

    @ApiModelProperty("费用信息")
    /**费用信息**/
    private List<BasyFyXx> FYXX;

    @ApiModelProperty("发票和结算信息")
    /**发票和结算信息**/
    private List<BasyFpXx> FPXX;

    public String getSBNF() {
        return SBNF;
    }

    public void setSBNF(String SBNF) {
        this.SBNF = SBNF;
    }

    public String getSBYF() {
        return SBYF;
    }

    public void setSBYF(String SBYF) {
        this.SBYF = SBYF;
    }

    public String getSBR() {
        return SBR;
    }

    public void setSBR(String SBR) {
        this.SBR = SBR;
    }

    public String getFLBM() {
        return FLBM;
    }

    public void setFLBM(String FLBM) {
        this.FLBM = FLBM;
    }

    public String getJYH() {
        return JYH;
    }

    public void setJYH(String JYH) {
        this.JYH = JYH;
    }

    public String getJGDM() {
        return JGDM;
    }

    public void setJGDM(String JGDM) {
        this.JGDM = JGDM;
    }

    public String getSBRQ() {
        return SBRQ;
    }

    public void setSBRQ(String SBRQ) {
        this.SBRQ = SBRQ;
    }

    public List<BasyZxlshXx> getJYLSHXX() {
        return JYLSHXX;
    }

    public void setJYLSHXX(List<BasyZxlshXx> JYLSHXX) {
        this.JYLSHXX = JYLSHXX;
    }

    public String getYBSSJSBZ() {
        return YBSSJSBZ;
    }

    public void setYBSSJSBZ(String YBSSJSBZ) {
        this.YBSSJSBZ = YBSSJSBZ;
    }

    public String getCYBZ() {
        return CYBZ;
    }

    public void setCYBZ(String CYBZ) {
        this.CYBZ = CYBZ;
    }

    public String getSBLB() {
        return SBLB;
    }

    public void setSBLB(String SBLB) {
        this.SBLB = SBLB;
    }

    public String getZXYBZ() {
        return ZXYBZ;
    }

    public void setZXYBZ(String ZXYBZ) {
        this.ZXYBZ = ZXYBZ;
    }

    public String getYLFKFS() {
        return YLFKFS;
    }

    public void setYLFKFS(String YLFKFS) {
        this.YLFKFS = YLFKFS;
    }

    public String getJKKH() {
        return JKKH;
    }

    public void setJKKH(String JKKH) {
        this.JKKH = JKKH;
    }

    public String getZYCS() {
        return ZYCS;
    }

    public void setZYCS(String ZYCS) {
        this.ZYCS = ZYCS;
    }

    public String getBAH() {
        return BAH;
    }

    public void setBAH(String BAH) {
        this.BAH = BAH;
    }

    public String getXM() {
        return XM;
    }

    public void setXM(String XM) {
        this.XM = XM;
    }

    public String getXB() {
        return XB;
    }

    public void setXB(String XB) {
        this.XB = XB;
    }

    public String getCSRQ() {
        return CSRQ;
    }

    public void setCSRQ(String CSRQ) {
        this.CSRQ = CSRQ;
    }

    public String getNL() {
        return NL;
    }

    public void setNL(String NL) {
        this.NL = NL;
    }

    public String getGJ() {
        return GJ;
    }

    public void setGJ(String GJ) {
        this.GJ = GJ;
    }

    public String getBZYZSNL() {
        return BZYZSNL;
    }

    public void setBZYZSNL(String BZYZSNL) {
        this.BZYZSNL = BZYZSNL;
    }

    public String getXSECSTZ() {
        return XSECSTZ;
    }

    public void setXSECSTZ(String XSECSTZ) {
        this.XSECSTZ = XSECSTZ;
    }

    public String getXSERYTZ() {
        return XSERYTZ;
    }

    public void setXSERYTZ(String XSERYTZ) {
        this.XSERYTZ = XSERYTZ;
    }

    public String getCSD() {
        return CSD;
    }

    public void setCSD(String CSD) {
        this.CSD = CSD;
    }

    public String getGG() {
        return GG;
    }

    public void setGG(String GG) {
        this.GG = GG;
    }

    public String getMZ() {
        return MZ;
    }

    public void setMZ(String MZ) {
        this.MZ = MZ;
    }

    public String getZJLX() {
        return ZJLX;
    }

    public void setZJLX(String ZJLX) {
        this.ZJLX = ZJLX;
    }

    public String getSFZH() {
        return SFZH;
    }

    public void setSFZH(String SFZH) {
        this.SFZH = SFZH;
    }

    public String getZY() {
        return ZY;
    }

    public void setZY(String ZY) {
        this.ZY = ZY;
    }

    public String getHY() {
        return HY;
    }

    public void setHY(String HY) {
        this.HY = HY;
    }

    public String getXZZ_S0() {
        return XZZ_S0;
    }

    public void setXZZ_S0(String XZZ_S0) {
        this.XZZ_S0 = XZZ_S0;
    }

    public String getXZZ_S1() {
        return XZZ_S1;
    }

    public void setXZZ_S1(String XZZ_S1) {
        this.XZZ_S1 = XZZ_S1;
    }

    public String getXZZ_X() {
        return XZZ_X;
    }

    public void setXZZ_X(String XZZ_X) {
        this.XZZ_X = XZZ_X;
    }

    public String getXZZ_DZ() {
        return XZZ_DZ;
    }

    public void setXZZ_DZ(String XZZ_DZ) {
        this.XZZ_DZ = XZZ_DZ;
    }

    public String getDH() {
        return DH;
    }

    public void setDH(String DH) {
        this.DH = DH;
    }

    public String getYB1() {
        return YB1;
    }

    public void setYB1(String YB1) {
        this.YB1 = YB1;
    }

    public String getHKDZ() {
        return HKDZ;
    }

    public void setHKDZ(String HKDZ) {
        this.HKDZ = HKDZ;
    }

    public String getYB2() {
        return YB2;
    }

    public void setYB2(String YB2) {
        this.YB2 = YB2;
    }

    public String getGZDWJMC() {
        return GZDWJMC;
    }

    public void setGZDWJMC(String GZDWJMC) {
        this.GZDWJMC = GZDWJMC;
    }

    public String getDWDH() {
        return DWDH;
    }

    public void setDWDH(String DWDH) {
        this.DWDH = DWDH;
    }

    public String getYB3() {
        return YB3;
    }

    public void setYB3(String YB3) {
        this.YB3 = YB3;
    }

    public String getLXRXM() {
        return LXRXM;
    }

    public void setLXRXM(String LXRXM) {
        this.LXRXM = LXRXM;
    }

    public String getGX() {
        return GX;
    }

    public void setGX(String GX) {
        this.GX = GX;
    }

    public String getLXR_S0() {
        return LXR_S0;
    }

    public void setLXR_S0(String LXR_S0) {
        this.LXR_S0 = LXR_S0;
    }

    public String getLXR_S1() {
        return LXR_S1;
    }

    public void setLXR_S1(String LXR_S1) {
        this.LXR_S1 = LXR_S1;
    }

    public String getLXR_X() {
        return LXR_X;
    }

    public void setLXR_X(String LXR_X) {
        this.LXR_X = LXR_X;
    }

    public String getLXR_DZ() {
        return LXR_DZ;
    }

    public void setLXR_DZ(String LXR_DZ) {
        this.LXR_DZ = LXR_DZ;
    }

    public String getDH1() {
        return DH1;
    }

    public void setDH1(String DH1) {
        this.DH1 = DH1;
    }

    public String getRYTJ() {
        return RYTJ;
    }

    public void setRYTJ(String RYTJ) {
        this.RYTJ = RYTJ;
    }

    public String getRYSJ() {
        return RYSJ;
    }

    public void setRYSJ(String RYSJ) {
        this.RYSJ = RYSJ;
    }

    public String getRYKB() {
        return RYKB;
    }

    public void setRYKB(String RYKB) {
        this.RYKB = RYKB;
    }

    public String getRYBF() {
        return RYBF;
    }

    public void setRYBF(String RYBF) {
        this.RYBF = RYBF;
    }

    public String getZKKB() {
        return ZKKB;
    }

    public void setZKKB(String ZKKB) {
        this.ZKKB = ZKKB;
    }

    public String getWSY_YLJG() {
        return WSY_YLJG;
    }

    public void setWSY_YLJG(String WSY_YLJG) {
        this.WSY_YLJG = WSY_YLJG;
    }

    public String getYZZY_YLJG() {
        return YZZY_YLJG;
    }

    public void setYZZY_YLJG(String YZZY_YLJG) {
        this.YZZY_YLJG = YZZY_YLJG;
    }

    public String getCYSJ() {
        return CYSJ;
    }

    public void setCYSJ(String CYSJ) {
        this.CYSJ = CYSJ;
    }

    public String getCYKB() {
        return CYKB;
    }

    public void setCYKB(String CYKB) {
        this.CYKB = CYKB;
    }

    public String getSJZYTS() {
        return SJZYTS;
    }

    public void setSJZYTS(String SJZYTS) {
        this.SJZYTS = SJZYTS;
    }

    public String getMZZD() {
        return MZZD;
    }

    public void setMZZD(String MZZD) {
        this.MZZD = MZZD;
    }

    public String getJBBM_S() {
        return JBBM_S;
    }

    public void setJBBM_S(String JBBM_S) {
        this.JBBM_S = JBBM_S;
    }

    public String getMZZD_XYZD() {
        return MZZD_XYZD;
    }

    public void setMZZD_XYZD(String MZZD_XYZD) {
        this.MZZD_XYZD = MZZD_XYZD;
    }

    public String getJBBM_S2() {
        return JBBM_S2;
    }

    public void setJBBM_S2(String JBBM_S2) {
        this.JBBM_S2 = JBBM_S2;
    }

    public String getWBYY() {
        return WBYY;
    }

    public void setWBYY(String WBYY) {
        this.WBYY = WBYY;
    }

    public String getJBBM1_S() {
        return JBBM1_S;
    }

    public void setJBBM1_S(String JBBM1_S) {
        this.JBBM1_S = JBBM1_S;
    }

    public String getBLZD() {
        return BLZD;
    }

    public void setBLZD(String BLZD) {
        this.BLZD = BLZD;
    }

    public String getJBBM2_S() {
        return JBBM2_S;
    }

    public void setJBBM2_S(String JBBM2_S) {
        this.JBBM2_S = JBBM2_S;
    }

    public String getBLH() {
        return BLH;
    }

    public void setBLH(String BLH) {
        this.BLH = BLH;
    }

    public String getYWGM() {
        return YWGM;
    }

    public void setYWGM(String YWGM) {
        this.YWGM = YWGM;
    }

    public String getGMYW() {
        return GMYW;
    }

    public void setGMYW(String GMYW) {
        this.GMYW = GMYW;
    }

    public String getSJ() {
        return SJ;
    }

    public void setSJ(String SJ) {
        this.SJ = SJ;
    }

    public String getXX() {
        return XX;
    }

    public void setXX(String XX) {
        this.XX = XX;
    }

    public String getRH() {
        return RH;
    }

    public void setRH(String RH) {
        this.RH = RH;
    }

    public String getQJCS() {
        return QJCS;
    }

    public void setQJCS(String QJCS) {
        this.QJCS = QJCS;
    }

    public String getCGCS() {
        return CGCS;
    }

    public void setCGCS(String CGCS) {
        this.CGCS = CGCS;
    }

    public String getSXFY() {
        return SXFY;
    }

    public void setSXFY(String SXFY) {
        this.SXFY = SXFY;
    }

    public String getRCMDSC() {
        return RCMDSC;
    }

    public void setRCMDSC(String RCMDSC) {
        this.RCMDSC = RCMDSC;
    }

    public String getXSRJBSC() {
        return XSRJBSC;
    }

    public void setXSRJBSC(String XSRJBSC) {
        this.XSRJBSC = XSRJBSC;
    }

    public String getCHCX() {
        return CHCX;
    }

    public void setCHCX(String CHCX) {
        this.CHCX = CHCX;
    }

    public String getXSRXB() {
        return XSRXB;
    }

    public void setXSRXB(String XSRXB) {
        this.XSRXB = XSRXB;
    }

    public String getXSRTZ() {
        return XSRTZ;
    }

    public void setXSRTZ(String XSRTZ) {
        this.XSRTZ = XSRTZ;
    }

    public String getXB_SBT() {
        return XB_SBT;
    }

    public void setXB_SBT(String XB_SBT) {
        this.XB_SBT = XB_SBT;
    }

    public String getTZ_SBT() {
        return TZ_SBT;
    }

    public void setTZ_SBT(String TZ_SBT) {
        this.TZ_SBT = TZ_SBT;
    }

    public String getXB_SABT() {
        return XB_SABT;
    }

    public void setXB_SABT(String XB_SABT) {
        this.XB_SABT = XB_SABT;
    }

    public String getTZ_SABT() {
        return TZ_SABT;
    }

    public void setTZ_SABT(String TZ_SABT) {
        this.TZ_SABT = TZ_SABT;
    }

    public String getKZR_DM() {
        return KZR_DM;
    }

    public void setKZR_DM(String KZR_DM) {
        this.KZR_DM = KZR_DM;
    }

    public String getKZR() {
        return KZR;
    }

    public void setKZR(String KZR) {
        this.KZR = KZR;
    }

    public String getZRYS_DM() {
        return ZRYS_DM;
    }

    public void setZRYS_DM(String ZRYS_DM) {
        this.ZRYS_DM = ZRYS_DM;
    }

    public String getZRYS() {
        return ZRYS;
    }

    public void setZRYS(String ZRYS) {
        this.ZRYS = ZRYS;
    }

    public String getZZYS_DM() {
        return ZZYS_DM;
    }

    public void setZZYS_DM(String ZZYS_DM) {
        this.ZZYS_DM = ZZYS_DM;
    }

    public String getZZYS() {
        return ZZYS;
    }

    public void setZZYS(String ZZYS) {
        this.ZZYS = ZZYS;
    }

    public String getZYYS_DM() {
        return ZYYS_DM;
    }

    public void setZYYS_DM(String ZYYS_DM) {
        this.ZYYS_DM = ZYYS_DM;
    }

    public String getZYYS() {
        return ZYYS;
    }

    public void setZYYS(String ZYYS) {
        this.ZYYS = ZYYS;
    }

    public String getJXYS_DM() {
        return JXYS_DM;
    }

    public void setJXYS_DM(String JXYS_DM) {
        this.JXYS_DM = JXYS_DM;
    }

    public String getJXYS() {
        return JXYS;
    }

    public void setJXYS(String JXYS) {
        this.JXYS = JXYS;
    }

    public String getZRHS() {
        return ZRHS;
    }

    public void setZRHS(String ZRHS) {
        this.ZRHS = ZRHS;
    }

    public String getSXYS() {
        return SXYS;
    }

    public void setSXYS(String SXYS) {
        this.SXYS = SXYS;
    }

    public String getBMY() {
        return BMY;
    }

    public void setBMY(String BMY) {
        this.BMY = BMY;
    }

    public String getBAZL() {
        return BAZL;
    }

    public void setBAZL(String BAZL) {
        this.BAZL = BAZL;
    }

    public String getZKYS() {
        return ZKYS;
    }

    public void setZKYS(String ZKYS) {
        this.ZKYS = ZKYS;
    }

    public String getZKHS() {
        return ZKHS;
    }

    public void setZKHS(String ZKHS) {
        this.ZKHS = ZKHS;
    }

    public String getZKRQ() {
        return ZKRQ;
    }

    public void setZKRQ(String ZKRQ) {
        this.ZKRQ = ZKRQ;
    }

    public String getLYFS() {
        return LYFS;
    }

    public void setLYFS(String LYFS) {
        this.LYFS = LYFS;
    }

    public String getZZYJH() {
        return ZZYJH;
    }

    public void setZZYJH(String ZZYJH) {
        this.ZZYJH = ZZYJH;
    }

    public String getMD() {
        return MD;
    }

    public void setMD(String MD) {
        this.MD = MD;
    }

    public String getRYQ_T() {
        return RYQ_T;
    }

    public void setRYQ_T(String RYQ_T) {
        this.RYQ_T = RYQ_T;
    }

    public String getRYQ_XS() {
        return RYQ_XS;
    }

    public void setRYQ_XS(String RYQ_XS) {
        this.RYQ_XS = RYQ_XS;
    }

    public String getRYQ_F() {
        return RYQ_F;
    }

    public void setRYQ_F(String RYQ_F) {
        this.RYQ_F = RYQ_F;
    }

    public String getRYH_T() {
        return RYH_T;
    }

    public void setRYH_T(String RYH_T) {
        this.RYH_T = RYH_T;
    }

    public String getRYH_XS() {
        return RYH_XS;
    }

    public void setRYH_XS(String RYH_XS) {
        this.RYH_XS = RYH_XS;
    }

    public String getRYH_F() {
        return RYH_F;
    }

    public void setRYH_F(String RYH_F) {
        this.RYH_F = RYH_F;
    }

    public BigDecimal getZFY() {
        return ZFY;
    }

    public void setZFY(BigDecimal ZFY) {
        this.ZFY = ZFY;
    }

    public BigDecimal getZFJE() {
        return ZFJE;
    }

    public void setZFJE(BigDecimal ZFJE) {
        this.ZFJE = ZFJE;
    }

    public BigDecimal getZFIJE() {
        return ZFIJE;
    }

    public void setZFIJE(BigDecimal ZFIJE) {
        this.ZFIJE = ZFIJE;
    }

    public BigDecimal getQTZF() {
        return QTZF;
    }

    public void setQTZF(BigDecimal QTZF) {
        this.QTZF = QTZF;
    }

    public String getZLLB() {
        return ZLLB;
    }

    public void setZLLB(String ZLLB) {
        this.ZLLB = ZLLB;
    }

    public String getSSLCLJ2() {
        return SSLCLJ2;
    }

    public void setSSLCLJ2(String SSLCLJ2) {
        this.SSLCLJ2 = SSLCLJ2;
    }

    public String getZYYJ() {
        return ZYYJ;
    }

    public void setZYYJ(String ZYYJ) {
        this.ZYYJ = ZYYJ;
    }

    public String getZYZLSB() {
        return ZYZLSB;
    }

    public void setZYZLSB(String ZYZLSB) {
        this.ZYZLSB = ZYZLSB;
    }

    public String getZYZLJS() {
        return ZYZLJS;
    }

    public void setZYZLJS(String ZYZLJS) {
        this.ZYZLJS = ZYZLJS;
    }

    public String getBZSH() {
        return BZSH;
    }

    public void setBZSH(String BZSH) {
        this.BZSH = BZSH;
    }

    public List<BasyZdXx> getZDXX() {
        return ZDXX;
    }

    public void setZDXX(List<BasyZdXx> ZDXX) {
        this.ZDXX = ZDXX;
    }

    public List<BasySsXx> getSSXX() {
        return SSXX;
    }

    public void setSSXX(List<BasySsXx> SSXX) {
        this.SSXX = SSXX;
    }

    public List<BasyFyXx> getFYXX() {
        return FYXX;
    }

    public void setFYXX(List<BasyFyXx> FYXX) {
        this.FYXX = FYXX;
    }

    public List<BasyFpXx> getFPXX() {
        return FPXX;
    }

    public void setFPXX(List<BasyFpXx> FPXX) {
        this.FPXX = FPXX;
    }
}
