package com.buit;

import com.buit.config.EnableLocked;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.buit.utill.SpringContextUtil;
import org.springframework.scheduling.annotation.EnableAsync;

/**
* @ClassName: HisRun
* @Description: 启动方法
* @author 神算子
* @date 2020年4月26日 下午3:31:56
 */
@EnableDubbo
@SpringBootApplication
@EnableAsync
@EnableLocked
public class ShybRun {
	public static void main(String[] args) {
		ConfigurableApplicationContext con=SpringApplication.run(ShybRun.class, args);
		SpringContextUtil.setApplicationContext(con);
	}
}