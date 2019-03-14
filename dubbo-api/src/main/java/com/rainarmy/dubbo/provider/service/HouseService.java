package com.rainarmy.dubbo.provider.service;

import com.rainarmy.dubbo.provider.domain.House;

/**
 * @Description:
 * @author: wangchengjun
 * @date: 2019/2/22 16:17
 * @modified by:
 */
public interface HouseService {
	House findOneHouse(String id);
}
