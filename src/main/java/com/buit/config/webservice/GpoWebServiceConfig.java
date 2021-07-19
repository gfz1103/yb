package com.buit.config.webservice;

import cn.hutool.core.util.ObjectUtil;
import com.buit.his.gpo.dao.GpoWsjlDao;
import com.buit.his.gpo.ws.GpoWebServiceClient;
import com.buit.utill.RedisFactory;
//import org.apache.cxf.endpoint.Client;
//import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author sunjx
 * @Date 2020-10-30 16:18
 * @Description
 **/
@Configuration
public class GpoWebServiceConfig {

    private static final Logger log = LoggerFactory.getLogger(GpoWebServiceConfig.class);

    @Bean("xiyao")
    public GpoWebServiceClient gpoXiyaoClient(GpoWebServiceConfigProperties properties, GpoWsjlDao gpoWsjlDao, RedisFactory redisFactory){
        return gpoClient(properties.getXiyao(), gpoWsjlDao, redisFactory);
    }

//    @Bean("zhongyao")
//    public GpoWebServiceClient gpoZhongyaoClient(GpoWebServiceConfigProperties properties, GpoWsjlDao gpoWsjlDao, RedisFactory redisFactory){
//        return gpoClient(properties.getZhongyao(), gpoWsjlDao, redisFactory);
//    }

    private GpoWebServiceClient gpoClient(GpoWebServiceConfigPropertiesBean propertiesBean, GpoWsjlDao gpoWsjlDao, RedisFactory redisFactory){
        if(null == propertiesBean || ObjectUtil.hasEmpty(propertiesBean.getWsdl(), propertiesBean.getJgbm(), propertiesBean.getUser(), propertiesBean.getPwd())){
            log.error("GPO webservice client [{}] 未配置, GPO相关功能无法使用...", null != propertiesBean ? propertiesBean.getName() : "");
              return new GpoWebServiceClient(redisFactory, null, null, null, null, null,null);
        }

//        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        // 创建动态客户端
//        Client client = dcf.createClient(propertiesBean.getWsdl());
        return new GpoWebServiceClient( redisFactory, gpoWsjlDao, propertiesBean.getJgbm(), propertiesBean.getUser(), propertiesBean.getPwd(), propertiesBean.getVersion(),propertiesBean.getWsdl());
    }
}
