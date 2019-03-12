package com.clothes.dao.ysh;

import java.util.List;
import java.util.Map;

import com.clothes.pojo.Apply;

/**
 * 申请表操作-Dao
 * 作者:杨舒航;
 * 时间:2016年12月7日上午9:17:00;
 * 邮箱：513658133@qq.com;
 */
public interface ApplyDaoysh {
	
	/**
	 * 查询所有申请表——Dao 
	 * @param ap_state
	 * @param ap_type
	 * @return
	 */
	public List<Map<String, Object>> showAllApply(Map<String, Object> map);
	
	public List<Map<String, Object>> showAllApplys();
	
	/**
	 * 查询单个详细申请——Dao
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> showOneApply(Map<String, Object> map);
	
	/**
	 * 修改订单——Dao
	 * @param map
	 */
	public int updateOneApply(Map<String, Object> map);
	
	/**
	 * 显示商品信息——Dao
	 * @return
	 */
	public List<Map<String, Object>> showGoods();
	
	/**
	 * 新增生产计划——Dao
	 * @param map
	 * @return
	 */
	public int addApply(Map<String, Object> map);
	
	/**
	 * 新增生产计划条目——Dao
	 * @param map
	 * @return
	 */
	public int addApplyitem(Map<String, Object> map);
	
	public List<Map<String, Object>> countCUtype();
	
	public List<Map<String, Object>> countCUname();
	
	public List<Map<String, Object>> countApplicant();
	
	public List<Map<String, Object>> countDealPerson();
	
	
}
