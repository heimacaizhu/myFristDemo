package com.clothes.service.wxq.imp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clothes.dao.wxq.OrderDao;
import com.clothes.pojo.Orders;
import com.clothes.service.wxq.OrderService;
import com.clothes.utils.DataGride;
import com.clothes.utils.PageUtil;

/**
 * 订单管理服务层实现类
 * @author 吴晓强
 *
 */
@Service
public class OrdersServiceImp implements OrderService {

	@Autowired
	private OrderDao orderDao;
	
	@Override
	public DataGride findOrders(Orders od) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = orderDao.findOrders(od);
		DataGride dg = PageUtil.convertToResult(list);
		return dg;
	}

	@Override
	public int updateOrder(Orders od) throws Exception{
		// TODO Auto-generated method stub
		int i = orderDao.updateOrders(od);
		if(i == 1){
			return 1;
		}else{
			throw new SQLException();
		}
	}
}
