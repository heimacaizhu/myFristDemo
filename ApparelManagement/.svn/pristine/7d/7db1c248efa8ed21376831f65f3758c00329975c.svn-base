package com.clothes.controller.wxq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clothes.pojo.Customer;
import com.clothes.pojo.Region;
import com.clothes.service.wxq.CustomerService;
import com.clothes.utils.DataGride;
import com.github.pagehelper.PageHelper;

/**
 * 客户管理控制层
 * @author 吴晓强
 *
 */
@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerServiceImp;
	
	/**
	 * 添加客户
	 * @param customer
	 * @param rId
	 * @return
	 */
	@RequestMapping("/addCustomer")
	@ResponseBody
	public Map<String,Object> addCustomer(Customer customer,int rId){
		Region region = new Region();
		region.setrId(rId);
		customer.setRegion(region);
		Map<String,Object> map = new HashMap<String, Object>();
		int i = customerServiceImp.addCustomer(customer);
		map.put("msg", i);
		return map;
	}
	
	/**
	 * 显示客户,展示
	 * @param customer
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/showCustomer")
	@ResponseBody
	public DataGride showCustomer(Customer customer,int page,int rows){
		String name = customer.getCuName();
		customer.setCuState(1);
		name = ("%" + name + "%");
		customer.setCuName(name);
		PageHelper.startPage(page,rows);
		DataGride dg = new DataGride();
		dg = customerServiceImp.findCustomer(customer);
		return dg;
	}
	
	/**
	 * 更新客户信息
	 * @param customer
	 * @return
	 */
	@RequestMapping("/genxinCustomer")
	@ResponseBody
	public Map<String,Object> updateCustomer(Customer customer,int rId){
		Region region = new Region();
		region.setrId(rId);
		customer.setRegion(region);
		Map<String,Object> map  = new HashMap<String,Object>();
		int i = 2;
		try {
			i = customerServiceImp.updateCustomer(customer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("msg", i);
		return map;
	}
	
	/**
	 * 删除客户
	 * @param ids
	 * @return 
	 */
	@RequestMapping("/deleteCustomer")
	@ResponseBody
	public Map<String,Object> deleteCustomer(String ids){
		Map<String,Object> map = new HashMap<String,Object>();
		int i = 2;
		try {
			i = customerServiceImp.deleteCustomers(ids);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("msg", i);
		return map;
	}
	
	/**
	 * 根据区域统计客户
	 * @return
	 */
	@RequestMapping("/countByRegion")
	@ResponseBody
	public Map<String,Object> countCustomerByRegion(){
		Map<String,Object> map = new HashMap<String,Object>();
		map = customerServiceImp.countCustomerByRegion();
		return map;
	}
	
	/**
	 * 根据用户类型统计用户
	 * @return
	 */
	@RequestMapping("/countByType")
	@ResponseBody
	public Map<String,Object> countCustomerByType(){
		Map<String,Object> map = new HashMap<String,Object>();
		map = customerServiceImp.countCustomerByType();
		return map;
	}
	
	/**
	 * 获取所有的客户
	 * @return
	 */
	@RequestMapping("/allcu")
	@ResponseBody
	public List<Map<String,Object>> getAllCustomer(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = customerServiceImp.findAllCustomer();
		return list;
	}
}
