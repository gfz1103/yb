import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.buit.his.gpo.model.GpoDdmx;
import com.buit.his.gpo.ws.GpoZidian;
import com.buit.his.gpo.ws.response.BaseRespMain;
import com.buit.his.gpo.ws.response.DdYY009RespMain;
import com.buit.his.gpo.ws.response.DdYY009RespStruct;
import com.buit.his.gpo.ws.response.PsdYY003RespStruct;
import com.buit.his.gpo.ws.xml.CommonMapUtils;
import com.buit.his.gpo.ws.xml.GpoXmlUtil;
import com.buit.his.gpo.ws.xml.Struct;
import com.buit.his.gpo.ws.xml.XmlData;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author yueyu
 * @Date 2021/5/6 15:04
 */
public class TestXmlUtil {

    @Test
    public void testXml(){
        String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?><XMLDATA><HEAD><JSSJ>20210518/135643/</JSSJ><ZTCLJG>10000</ZTCLJG><CWXX>明细中有错误</CWXX><BZXX/></HEAD><MAIN><DDBH/></MAIN><DETAIL><STRUCT><DDMXBH>20210518000067062383</DDMXBH><SXH>1</SXH><SPLX>1</SPLX><ZXSPBM>XN0000030097389</ZXSPBM><YYSPBM/><CLJG>00000</CLJG><CLQKMS/></STRUCT><STRUCT><DDMXBH>20210518000067062384</DDMXBH><SXH>2</SXH><SPLX>1</SPLX><ZXSPBM>XN0000030095242</ZXSPBM><YYSPBM/><CLJG>13030</CLJG><CLQKMS>商品统编代码:XN0000030095242未议价药品，必须先议价才能采购!</CLQKMS></STRUCT><STRUCT><DDMXBH>20210518000067062385</DDMXBH><SXH>3</SXH><SPLX>1</SPLX><ZXSPBM>XN0000030093671</ZXSPBM><YYSPBM/><CLJG>13030</CLJG><CLQKMS>商品统编代码:XN0000030093671未议价药品，必须先议价才能采购!</CLQKMS></STRUCT><STRUCT><DDMXBH>20210518000067062386</DDMXBH><SXH>4</SXH><SPLX>1</SPLX><ZXSPBM>XN0000030076780</ZXSPBM><YYSPBM/><CLJG>13041</CLJG><CLQKMS>此药品采购类型为不允许采购，药品编码：XN0000030076780(盐酸度洛西汀肠溶胶囊)</CLQKMS></STRUCT><STRUCT><DDMXBH>20210518000067062387</DDMXBH><SXH>5</SXH><SPLX>1</SPLX><ZXSPBM>XN0000030086890</ZXSPBM><YYSPBM/><CLJG>00000</CLJG><CLQKMS/></STRUCT></DETAIL></XMLDATA>";
//        String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?><XMLDATA><HEAD><JSSJ>20210510/135238/</JSSJ><ZTCLJG>00000</ZTCLJG><CWXX/><BZXX/></HEAD><MAIN><DDBH>20210510000016559932</DDBH></MAIN><DETAIL><STRUCT><DDMXBH>20210510000066749905</DDMXBH><SXH>1</SXH><SPLX>1</SPLX><ZXSPBM>XN0000030060033</ZXSPBM><YYSPBM/><CLJG>00000</CLJG><CLQKMS>处理成功,药企库存情况:没有上传库存</CLQKMS></STRUCT></DETAIL></XMLDATA>";
        XmlData xmldata = GpoXmlUtil.toObj(xml,new TypeReference<XmlData>() {});
        if (CollUtil.isNotEmpty(xmldata.getDETAIL())) {
//            for (Struct<Object> struct : xmldata.getDETAIL()) {
//                if(struct.getSTRUCT() instanceof JSONArray){
//                    List<DdYY009RespStruct> list = JSONUtil.toList((JSONArray) struct.getSTRUCT(),DdYY009RespStruct.class);
//                }else{
//                    DdYY009RespStruct ddYY009RespStruct = JSONUtil.toBean((JSONObject) struct.getSTRUCT(), DdYY009RespStruct.class);
//                }
                System.out.println();
//            }
        }
    }

    @Test
    public void Test(){
        Integer yklb = 1;
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><XMLDATA><HEAD><JSSJ>20210429/154636/</JSSJ><ZTCLJG>00000</ZTCLJG><CWXX></CWXX><BZXX></BZXX></HEAD><MAIN><JLS>5</JLS><SFWJ>1</SFWJ></MAIN><DETAIL><STRUCT><PSDH>ps210000000000347</PSDH><YQBM>913416005663861331</YQBM><PSDBM>4</PSDBM><CJRQ>20210420/000000/</CJRQ><PSMXBH>20210420000833474616</PSMXBH><PSDTM>ahjsxb0000368001</PSDTM><ZXLX>2</ZXLX><CGLX>1</CGLX><SPLX>1</SPLX><YPLX>3</YPLX><ZXSPBM>YP1003202001035</ZXSPBM><CPM>醋龟甲</CPM><YPJX/><GG>g*1000g</GG><BZDWXZ>1</BZDWXZ><BZDWMC>克</BZDWMC><YYDWMC>克</YYDWMC><BZNHSL>1</BZNHSL><SCQYMC/><SCPH>210301</SCPH><SCRQ>29991231</SCRQ><YXRQ>29991231</YXRQ><JHDH>210420134015-944</JHDH><XSDDH>xb210000003470001</XSDDH><DDMXBH>20210420000066086194</DDMXBH><SXH>16596</SXH><PSL>3000</PSL></STRUCT><STRUCT><PSDH>ps210000000000347</PSDH><YQBM>913416005663861331</YQBM><PSDBM>4</PSDBM><CJRQ>20210420/000000/</CJRQ><PSMXBH>20210420000833474617</PSMXBH><PSDTM>ahjsxb0000368002</PSDTM><ZXLX>2</ZXLX><CGLX>1</CGLX><SPLX>1</SPLX><YPLX>3</YPLX><ZXSPBM>YP0203302000373</ZXSPBM><CPM>炒蔓荆子</CPM><YPJX/><GG/><BZDWXZ>1</BZDWXZ><BZDWMC>克</BZDWMC><YYDWMC>克</YYDWMC><BZNHSL>1</BZNHSL><SCQYMC/><SCPH>201201</SCPH><SCRQ>29991231</SCRQ><YXRQ>29991231</YXRQ><JHDH>210420134015-944</JHDH><XSDDH>xb210000003470002</XSDDH><DDMXBH>20210420000066086195</DDMXBH><SXH>16597</SXH><PSL>10000</PSL></STRUCT><STRUCT><PSDH>ps210000000000347</PSDH><YQBM>913416005663861331</YQBM><PSDBM>4</PSDBM><CJRQ>20210420/000000/</CJRQ><PSMXBH>20210420000833474618</PSMXBH><PSDTM>ahjsxb0000368003</PSDTM><ZXLX>2</ZXLX><CGLX>1</CGLX><SPLX>1</SPLX><YPLX>3</YPLX><ZXSPBM>YP0102201000053</ZXSPBM><CPM>麦冬</CPM><YPJX/><GG>1g*1000g</GG><BZDWXZ>1</BZDWXZ><BZDWMC>克</BZDWMC><YYDWMC>克</YYDWMC><BZNHSL>1</BZNHSL><SCQYMC/><SCPH>200201</SCPH><SCRQ>29991231</SCRQ><YXRQ>29991231</YXRQ><JHDH>210420134015-944</JHDH><XSDDH>xb210000003470003</XSDDH><DDMXBH>20210420000066086196</DDMXBH><SXH>16598</SXH><PSL>10000</PSL></STRUCT><STRUCT><PSDH>ps210000000000347</PSDH><YQBM>913416005663861331</YQBM><PSDBM>4</PSDBM><CJRQ>20210420/000000/</CJRQ><PSMXBH>20210420000833474619</PSMXBH><PSDTM>ahjsxb0000368004</PSDTM><ZXLX>2</ZXLX><CGLX>1</CGLX><SPLX>1</SPLX><YPLX>3</YPLX><ZXSPBM>YP0100701000016</ZXSPBM><CPM>太子参</CPM><YPJX/><GG>1g*1000g</GG><BZDWXZ>1</BZDWXZ><BZDWMC>克</BZDWMC><YYDWMC>克</YYDWMC><BZNHSL>1</BZNHSL><SCQYMC/><SCPH>201201</SCPH><SCRQ>29991231</SCRQ><YXRQ>29991231</YXRQ><JHDH>210420134015-944</JHDH><XSDDH>xb210000003470004</XSDDH><DDMXBH>20210420000066086197</DDMXBH><SXH>16599</SXH><PSL>10000</PSL></STRUCT><STRUCT><PSDH>CK210429007005</PSDH><YQBM>91330205MA2H6L8D35</YQBM><PSDBM>3</PSDBM><CJRQ>20210429/000000/</CJRQ><PSMXBH>20210429000845016529</PSMXBH><PSDTM>CK210429007005</PSDTM><ZXLX>2</ZXLX><CGLX>1</CGLX><SPLX>1</SPLX><YPLX>2</YPLX><ZXSPBM>ZN0000030072075</ZXSPBM><CPM>冠心宁片</CPM><YPJX>薄膜衣片</YPJX><GG>0.38g</GG><BZDWXZ>1</BZDWXZ><BZDWMC>盒</BZDWMC><YYDWMC>片(粒,支,瓶,袋,听)</YYDWMC><BZNHSL>18</BZNHSL><SCQYMC>正大青春宝药业有限公司</SCQYMC><SCPH>2012002</SCPH><SCRQ>20201214</SCRQ><YXRQ>20221213</YXRQ><JHDH>210429113422-548</JHDH><XSDDH>7978</XSDDH><DDMXBH>20210429000066451866</DDMXBH><SXH>16766</SXH><PSL>600</PSL></STRUCT></DETAIL></XMLDATA>";
        XmlData<BaseRespMain, Object> resp = GpoXmlUtil.toObj(xml,new TypeReference<XmlData<BaseRespMain, Object>>() {});
        List<PsdYY003RespStruct> list = resp.getDETAIL().stream().flatMap(t->{
            if ( t.getSTRUCT() instanceof JSONObject) {
                PsdYY003RespStruct psdobj = JSONUtil.toBean((JSONObject)t.getSTRUCT(),PsdYY003RespStruct.class);
                return Arrays.asList(psdobj).stream();
            }
            if ( t.getSTRUCT() instanceof JSONArray) {
                List<PsdYY003RespStruct> psdList = JSONUtil.toList((JSONArray) t.getSTRUCT(), PsdYY003RespStruct.class);
                return psdList.stream();
            }
            return null;
         }).filter(t ->
            (t.getYPLX().equals(GpoZidian.YPLX.ZYYP.getVal()) && yklb == 2)
                    || (!t.getYPLX().equals(GpoZidian.YPLX.ZYYP.getVal()) && yklb == 1)
        ).collect(Collectors.toList());
        System.out.println();

    }


}
