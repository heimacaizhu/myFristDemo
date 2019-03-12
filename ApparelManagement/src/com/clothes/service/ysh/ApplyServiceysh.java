package com.clothes.service.ysh;

import java.util.List;
import java.util.Map;

import com.clothes.pojo.Apply;
import com.clothes.utils.DataGride;

/**
 * 申请表操作-Service层
 * 作者:杨舒航;
 * 时间:2016年12月7日下午3:50:27;
 * 邮箱：513658133@qq.com;
 */
public interface ApplyServiceysh {
	
	/**
	 * 查询所有申请表-Service层
	 * @param ap_state
	 * @param ap_type
	 * @return
	 */
	public DataGride showAllApply(Map<String, Object> map,int page,int rows);
	
	public List<Map<String, Object>> showAllApplys();
	
	/**
	 * 查询单个详细申请表-Service层
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> showOneApply(Map<String, Object> map);
	
	/**
	 * 修改订单——Service层
	 * @param map
	 */
	public int updateOneApply(Map<String, Object> map);
	
	/**
	 * 显示商品信息——Service
	 * @return
	 */
	public List<Map<String, Object>> showGoods();
	
	/**
	 * 新增生产计划——Service
	 * @param map
	 * @return
	 */
	public int addApply(Map<String, Object> map);
	
	/**
	 * 新增生产计划条目——Service
	 * @param map
	 * @return
	 */
	public int addApplyitem(Map<String, Object> map);
	

	public List<Map<String, Object>> countCUtype();
	
	public List<Map<String, Object>> countCUname();
	
	public List<Map<String, Object>> countApplicant();
	
	public List<Map<String, Object>> countDealPerson();
	
}
