package com.clothes.dao.yn;

import com.clothes.pojo.Customer;

/*
 *作者：杨聂；    
 *邮箱：1024938222@qq.com；      
 *时间：2016年12月12日下午10:14:03；      
 */
public interface CustomerDaoy {
	/**
	 * 获取指定客户
	 * @param id 客户id
	 * @return 返回客户
	 */
	public Customer getCustomerById(String id);
}
