package com.rainarmy.dubbo.consumer;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description:
 * @author: wangchengjun
 * @date: 2019/2/22 17:48
 * @modified by:
 */
@SpringBootApplication
@EnableDubbo
public class DubboConsumerApplication {
	public static void main(String [] args){
		SpringApplication.run(DubboConsumerApplication.class);
	}
}
