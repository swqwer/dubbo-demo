package com.rainarmy.dubbo.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.rainarmy.dubbo.provider.domain.House;
import com.rainarmy.dubbo.provider.service.HouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

/**
 * @Description:
 * @author: wangchengjun
 * @date: 2019/2/22 17:39
 * @modified by:
 */
@Service(version = "${house.service.version}")
@Slf4j
public class HouseServiceImpl implements HouseService {

	@Value("${server.port}")
	private String port;

	@Override
	public House findOneHouse(String id) {
		log.info("called server from port {}",port);
		return new House(id,new Date());
	}
}
