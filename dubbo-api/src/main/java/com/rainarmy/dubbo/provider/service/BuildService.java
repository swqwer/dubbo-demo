package com.rainarmy.dubbo.provider.service;

import com.rainarmy.dubbo.provider.domain.Build;

import java.util.List;

/**
 * 楼栋接口
 * @author wangchengjun
 * @version V1.0
 * @date 2019/7/15 17:38
 */
public interface BuildService {
    List<Build> getBuilds(String projectId);
}
