package com.clothes.service.yn;

import java.util.List;
import java.util.Map;

import com.clothes.pojo.Apply;
import com.clothes.utils.DataGride;

/*
 *作者：杨聂；    
 *邮箱：1024938222@qq.com；      
 *时间：2016年12月12日下午10:38:03；      
 */
public interface ApplyServicey {
	/**
	 * 获取所有未处理的申请
	 * @return apply集合
	 */
	public DataGride getAllApply();
	
	/**
	 * 获取所有未处理的申请
	 * @return apply集合
	 */
	public DataGride getAllDealApply();
	
	/**
	 * 处理报单申请
	 * @param state 状态
	 * @param id 报单id
	 * @return 处理条数
	 */
	public int updateDealApply(String state,String id,String eid);
	
	/**
	 * 删除已处理报单
	 * @param pMap 参数
	 * @return 处理条数
	 */
	public int delDealApply(String id);
}
