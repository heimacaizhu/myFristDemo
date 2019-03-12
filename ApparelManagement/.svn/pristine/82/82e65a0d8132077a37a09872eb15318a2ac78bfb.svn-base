package com.clothes.controller.wxq;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clothes.pojo.Customer;
import com.clothes.pojo.Employee;
import com.clothes.pojo.Orders;
import com.clothes.service.wxq.OrderService;
import com.clothes.utils.CloudInfDemo;
import com.clothes.utils.DataGride;
import com.github.pagehelper.PageHelper;

/**
 * 订单管理控制层
 * @author 吴晓强
 *
 */
@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderServiceImp;
	
	/**
	 * 显示订单
	 * @param od
	 * @return
	 */
	@RequestMapping("/showod")
	@ResponseBody
	public DataGride showOrders(Orders od,int page,int rows,String cuName){
		Customer cu = new Customer();
		cu.setCuName(cuName);
		Employee em = new Employee();
		em.setEmId(1);
		od.setCustomer(cu);
		od.setEmployee(em);
		PageHelper.startPage(page, rows);
		DataGride dg = new DataGride();
		dg = orderServiceImp.findOrders(od);
		return dg;
	}
	
	/**
	 * 更改订单的付款金额
	 * @param od
	 * @return
	 */
	@RequestMapping("/upod")
	@ResponseBody
	public Map<String,Object> updateOrder(Orders od){
		Map<String,Object> map = new HashMap<String, Object>();
		int i = 2;
		try {
			i = orderServiceImp.updateOrder(od);
			//发送短信消息
			String params[] = new String[]{"20161201120612340001"};
			CloudInfDemo.sendSmsTpl(od.getoId(), "18381306324", "859",params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("msg", i);
		return map;
	}
	
	/**
	 * 发送催款消息
	 * @param phone
	 * @param oid
	 * @param msgtype
	 * @return
	 */
	@RequestMapping("/sendMsg")
	@ResponseBody
	public Map<String,Object> sendMsg(String phone,String oid,String msgtype){
		Map<String,Object> map = new HashMap<String,Object>();
		String params[] = new String[]{"20161201120612340001"};
		int i = 2;
		try {
			CloudInfDemo.sendSmsTpl(oid, "18381306324", "859",params);
			i = 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("msg", i);
		return map;
	}
}
