package com.rainarmy.dubbo.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.rainarmy.dubbo.provider.service.HouseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: wangchengjun
 * @date: 2019/2/22 17:51
 * @modified by:
 */
@RestController
public class HouseController {
	@Reference(version = "${demo.service.version}",loadbalance = "")
	private HouseService houseService;

	@RequestMapping("/getHouse")
	public String getHouse(String id){
		return houseService.findOneHouse(id).toString();
	}

	@RequestMapping("/getServerTime")
	public String getServerTime(){
		//获取服务器时间
		return String.valueOf(System.nanoTime());
	}

	private void print(){
		System.out.println("i am a print");
	}
}
