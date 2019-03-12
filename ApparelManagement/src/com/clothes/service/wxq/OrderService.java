package com.clothes.service.wxq;


import com.clothes.pojo.Orders;
import com.clothes.utils.DataGride;

/**
 * 订单管理Service层接口
 * @author 吴晓强
 *
 */
public interface OrderService {
	
	/**
	 * 查询用户订单
	 * @param od
	 * @return
	 */
	public DataGride findOrders(Orders od);
	
	/**
	 * 更新订单支付款项
	 * @param od
	 * @return
	 */
	public int updateOrder(Orders od) throws Exception;
}
