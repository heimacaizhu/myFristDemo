package com.clothes.dao.yn;

import java.util.List;
import java.util.Map;

import com.clothes.pojo.Apply;

/*
 *作者：杨聂；    
 *邮箱：1024938222@qq.com；      
 *时间：2016年12月12日下午9:05:03；      
 *申请数据层
 */
public interface ApplyDaoy {
	/**
	 * 获取所有未处理的申请
	 * @return apply集合
	 */
	public List<Apply> getAllApply();
	
	/**
	 * 获取所有未处理的申请
	 * @return apply集合
	 */
	public List<Apply> getAllDealApply();
	
	/**
	 * 处理报单申请
	 * @param pMap 参数
	 * @return 处理条数
	 */
	public int updateDealApply(Map<String, String> pMap);
	
	/**
	 * 删除已处理报单
	 * @param pMap 参数
	 * @return 处理条数
	 */
	public int delDealApply(Map<String, String> pMap);
	
	/**
	 * 模糊查询报单
	 * @param pMap 条件
	 * @return apply集合
	 */
	public List<Apply> getApplyByLike(Map<String, String> pMap);
}
