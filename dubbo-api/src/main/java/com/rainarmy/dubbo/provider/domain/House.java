package com.rainarmy.dubbo.provider.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @author: wangchengjun
 * @date: 2019/2/22 16:16
 * @modified by:
 */
@Data
@ToString
@AllArgsConstructor
public class House implements Serializable{
	private String id;
	private Date creatTime;
}
