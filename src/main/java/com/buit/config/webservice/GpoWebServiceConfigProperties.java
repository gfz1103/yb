package com.buit.config.webservice;

import com.buit.his.gpo.ws.GpoConsts;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author sunjx
 * @Date 2020-10-30 16:07
 * @Description GPO webservice 配置属性
 **/
@Component
@ConfigurationProperties(prefix = "gpo.ws")
public class GpoWebServiceConfigProperties {

    //2020-11-13 webservice 不区分药库类型
    //西药库
    private GpoWebServiceConfigPropertiesBean xiyao;

    //中药库
//    private GpoWebServiceConfigPropertiesBean zhongyao;

    public GpoWebServiceConfigPropertiesBean getXiyao() {
        return xiyao;
    }

    public void setXiyao(GpoWebServiceConfigPropertiesBean xiyao) {
        this.xiyao = xiyao;
    }

//    public GpoWebServiceConfigPropertiesBean getZhongyao() {
//        return zhongyao;
//    }
//
//    public void setZhongyao(GpoWebServiceConfigPropertiesBean zhongyao) {
//        this.zhongyao = zhongyao;
//    }
}
