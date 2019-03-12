package com.clothes.dao.wxq;

import java.util.List;
import java.util.Map;

import com.clothes.pojo.Orders;

/**
 * 客户订单数据库层接口
 * @author 吴晓强
 *
 */
public interface OrderDao {
	
	/**
	 * 根据条件查询订单
	 * @param od
	 * @return
	 */
	public List<Map<String,Object>> findOrders(Orders od);
	
	/**
	 * 更新订单的付款信息
	 * @param od
	 * @return
	 */
	public int updateOrders(Orders od);
	
	/**
	 * 删除订单
	 * @param ids
	 * @return
	 */
	public int deleteOrders(String []ids);
}
