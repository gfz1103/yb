package com.buit.his.shyb.upload.model;

/**
 * @Author weijing
 * @Date 2020-10-30 14:47
 * @Description
 **/
public class BasyUpload {
    /**医院机构id**/
    private Integer YLJGD;

    /**就诊流水号 医院HIS系统的唯一编号**/
    private String JYH;

    /**医保医疗机构代码**/
    private String JGDM;

    /**申报日期 格式：YYYYMMDD**/
    private String SBRQ;

    /**医保实时结算标志 1:医保实时结算
     2:非医保实时结算（包含全自费结算）**/
    private String YBSSJSBZ;

    /**出院结算标志 1:出院结算、2:在院结算**/
    private String CYBZ;

    /**医保支付方式 1:按项目 2:单病种3:按病种分值4:疾病诊断相关分组（DRG）5:按床日6:按人头9:其它**/
    private String SBLB;

    /**中西医标志 西医:01、中医:02**/
    private String ZXYBZ;

    /**医疗付费方式**/
    private String YLFKFS;

    /**就诊卡号**/
    private String JKKH;

    /**住院次数**/
    private String ZYCS;

    /**病案号**/
    private String BAH;

    /**姓名  加密：个人隐私信息传输 todo**/
    private String XM;

    /**性别**/
    private String XB;

    /**出生日期 格式：YYYYMMDD**/
    private String CSRQ;

    /**年龄**/
    private String NL;

    /**国籍**/
    private String GJ;

    /**年龄整月分份**/
    private String NLDW;

    /**不足一月天数**/
    private String NLFZ;

    /**月固定天数  为30**/
    private String NLFM;

    /**新生儿出生体重**/
    private String XSECSTZ;

    /**新生儿入院体重**/
    private String XSERYTZ;

    /**出生地省**/
    private String CSDSHENG;

    /**出生地市**/
    private String CSDSHI;

    /**出生地**/
    private String CSDXIAN;

    /**籍贯省**/
    private String JGSHENG;

    /**籍贯市**/
    private String JGSHI;

    /**民族**/
    private String MZ;

    /**患者证件类别**/
    private String ZJLX;

    private String SFZH;

    /**职业**/
    private String ZY;

    /**婚姻**/
    private String HY;

    /**现住址（省）**/
    private String XZZ_S0;

    /**现住址（市）**/
    private String XZZ_S1;

    /**现住址（县）**/
    private String XZZ_X;

    /**现住址（地址）**/
    private String XZZ_DZ;

    /**电话号码**/
    private String DH;

    /**邮编(现住址)**/
    private String YB1;

    /**户口地址省**/
    private String HKDZSHENG;

    /**户口地址市**/
    private String HKDZSHI;

    /**户口地址县**/
    private String HKDZXIAN;

    /**户口地址详细**/
    private String HKDZXXDZ;

    /**邮编(户口地址)**/
    private String YB2;

    /**工作单位名称**/
    private String GZDWJMC;

    /**工作单位电话**/
    private String DWDH;

    /**工作单位邮编**/
    private String YB3;

    /**联系人姓名**/
    private String LXRXM;

    /**关系(联系人与患者关系)**/
    private String GX;

    /**联系人地址（省）**/
    private String LXR_S0;

    /**联系人地址（市）**/
    private String LXR_S1;

    /**联系人地址（县）**/
    private String LXR_X;

    /**联系人地址（地址）**/
    private String LXR_DZ;

    /**联系人电话号码**/
    private String DH1;

    /**入院途径**/
    private String RYTJ;

    /**入院时间  格式：yyyy-MM-dd HH:mm:ss**/
    private String RYSJ;

    /**入院科别**/
    private String RYKB;

    /**入院病房**/
    private String RYBF;

    /**转科科别**/
    private String ZKKB;

    /**拟接受医疗机构**/
    private String NJSYLJGMC;

    /**出院时间 格式：yyyy-MM-dd HH:mm:ss**/
    private String CYSJ;

    /**出院科别**/
    private String CYKB;

    /**实际住院天数**/
    private String SJZYTS;

    /**门(急)诊诊断名称（西医诊断）**/
    private String MZZD;

    /**门(急)诊诊断名称（西医诊断）疾病代码**/
    private String JBBM_S;

    /**门(急)诊诊断名称（中医诊断）**/
    private String MZZD_XYZD;

    /**门(急)诊诊断名称（中医诊断）疾病代码**/
    private String JBBM_S2;

    /**损伤、中毒的外部原因名称**/
    private String WBYY;

    /**损伤、中毒的外部原因**/
    private String JBBM1_S;

    /**病理诊断名称**/
    private String BLZD;

    /**病理诊断**/
    private String JBBM2_S;

    /**病理号**/
    private String BLH;

    /**药物过敏**/
    private String YWGM;

    /**过敏药物**/
    private String GMYW;

    /**死亡患者尸检**/
    private String SJ;

    /**血型**/
    private String XX;

    /**RH**/
    private String RH;

    /**抢救次数**/
    private String QJCS;

    /**成功次数**/
    private String CGCS;

    /**输血反应**/
    private String SXFY;

    /**妊娠梅毒筛查**/
    private String RCMDSC;

    /**新生儿疾病筛查**/
    private String XSRJBSC;

    /**产后出血**/
    private String CHCX;

    /**新生儿性别**/
    private String XSRXB;

    /**新生儿体重（g）**/
    private String XSRTZ;

    /**新生儿性别(双胞胎)**/
    private String XB_SBT;

    /**新生儿体重(双胞胎)**/
    private String TZ_SBT;

    /**新生儿性别(三胞胎)**/
    private String XB_SABT;

    /**新生儿体重(三胞胎)**/
    private String TZ_SABT;

    /**科主任医保医师代码**/
    private String KZR_DM;

    /**科主任姓名**/
    private String KZR;

    /**主任（副主任）医保医师代码**/
    private String ZRYS_DM;

    /**主任（副主任）医师姓名**/
    private String ZRYS;

    /**主治医师医保医师代码**/
    private String ZZYS_DM;

    /**主治医师姓名**/
    private String ZZYS;

    /**住院医师医保医师代码**/
    private String ZYYS_DM;

    /**住院医师姓名**/
    private String ZYYS;

    /**进修医生医保医师代码**/
    private String JXYS_DM;

    /**进修医师姓名**/
    private String JXYS;

    /**责任护士**/
    private String ZRHS;

    /**实习医师**/
    private String SXYS;

    /**编码员**/
    private String BMY;

    /**病案质量**/
    private String BAZL;

    /**质控医师**/
    private String ZKYS;

    /**质控护士**/
    private String ZKHS;

    /**质控日期 格式：YYYYMMDD**/
    private String ZKRQ;

    /**离院方式**/
    private String LYFS;

    /**是否有再住院计划**/
    private String ZZYJH;

    /**目的**/
    private String MD;

    /**入院前天**/
    private String RYQ_T;

    /**入院前小时**/
    private String RYQ_XS;

    /**入院前分钟**/
    private String RYQ_F;

    /**入院后天**/
    private String RYH_T;

    /**入院后小时**/
    private String RYH_XS;

    /**入院后分钟**/
    private String RYH_F;

    /**总费用**/
    private String ZFY;

    /**自付金额  医保支付范围内由个人自付金额**/
    private String ZFJE;

    /**自费金额 医保支付范围外由个人自费金额**/
    private String ZFIJE;

    /**其他支付**/
    private String QTZF;

    /**治疗类别  中医病案首页**/
    private String ZLLB;

    /**实施临床路径 中医病案首页、编码**/
    private String SSLCLJ2;

    /**使用医疗机构中药制剂 中医病案首页**/
    private String ZYYJ;

    /**使用中医诊疗设备 中医病案首页**/
    private String ZYZLSB;

    /**使用中医诊疗技术 中医病案首页**/
    private String ZYZLJS;

    /**辩证施护 中医病案首页**/
    private String BZSH;

    public Integer getYLJGD() {
        return YLJGD;
    }

    public void setYLJGD(Integer YLJGD) {
        this.YLJGD = YLJGD;
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

    public String getNLDW() {
        return NLDW;
    }

    public void setNLDW(String NLDW) {
        this.NLDW = NLDW;
    }

    public String getNLFZ() {
        return NLFZ;
    }

    public void setNLFZ(String NLFZ) {
        this.NLFZ = NLFZ;
    }

    public String getNLFM() {
        return NLFM;
    }

    public void setNLFM(String NLFM) {
        this.NLFM = NLFM;
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

    public String getCSDSHENG() {
        return CSDSHENG;
    }

    public void setCSDSHENG(String CSDSHENG) {
        this.CSDSHENG = CSDSHENG;
    }

    public String getCSDSHI() {
        return CSDSHI;
    }

    public void setCSDSHI(String CSDSHI) {
        this.CSDSHI = CSDSHI;
    }

    public String getCSDXIAN() {
        return CSDXIAN;
    }

    public void setCSDXIAN(String CSDXIAN) {
        this.CSDXIAN = CSDXIAN;
    }

    public String getJGSHENG() {
        return JGSHENG;
    }

    public void setJGSHENG(String JGSHENG) {
        this.JGSHENG = JGSHENG;
    }

    public String getJGSHI() {
        return JGSHI;
    }

    public void setJGSHI(String JGSHI) {
        this.JGSHI = JGSHI;
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

    public String getHKDZSHENG() {
        return HKDZSHENG;
    }

    public void setHKDZSHENG(String HKDZSHENG) {
        this.HKDZSHENG = HKDZSHENG;
    }

    public String getHKDZSHI() {
        return HKDZSHI;
    }

    public void setHKDZSHI(String HKDZSHI) {
        this.HKDZSHI = HKDZSHI;
    }

    public String getHKDZXIAN() {
        return HKDZXIAN;
    }

    public void setHKDZXIAN(String HKDZXIAN) {
        this.HKDZXIAN = HKDZXIAN;
    }

    public String getHKDZXXDZ() {
        return HKDZXXDZ;
    }

    public void setHKDZXXDZ(String HKDZXXDZ) {
        this.HKDZXXDZ = HKDZXXDZ;
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

    public String getNJSYLJGMC() {
        return NJSYLJGMC;
    }

    public void setNJSYLJGMC(String NJSYLJGMC) {
        this.NJSYLJGMC = NJSYLJGMC;
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

    public String getZFY() {
        return ZFY;
    }

    public void setZFY(String ZFY) {
        this.ZFY = ZFY;
    }

    public String getZFJE() {
        return ZFJE;
    }

    public void setZFJE(String ZFJE) {
        this.ZFJE = ZFJE;
    }

    public String getZFIJE() {
        return ZFIJE;
    }

    public void setZFIJE(String ZFIJE) {
        this.ZFIJE = ZFIJE;
    }

    public String getQTZF() {
        return QTZF;
    }

    public void setQTZF(String QTZF) {
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
}
