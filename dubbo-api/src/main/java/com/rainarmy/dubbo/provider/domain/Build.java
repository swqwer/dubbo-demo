package com.rainarmy.dubbo.provider.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * build
 * @author wangchengjun
 * @version V1.0
 * @date 2019/7/15 17:39
 */
@Data
@AllArgsConstructor
@ToString
public class Build implements Serializable {
    private String id;
    private String name;
}
