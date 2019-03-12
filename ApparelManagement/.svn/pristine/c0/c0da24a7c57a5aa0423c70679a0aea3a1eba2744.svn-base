package com.clothes.service.wxq.imp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clothes.dao.wxq.CustomerDao;
import com.clothes.pojo.Customer;
import com.clothes.service.wxq.CustomerService;
import com.clothes.utils.DataGride;
import com.clothes.utils.PageUtil;

/**
 * 客户管理服务层实现类
 * @author 吴晓强
 *
 */
@Service
public class CustomerServiceImp implements CustomerService {

	@Autowired
	private CustomerDao customerDao;
	@Override
	public int addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = customerDao.findCustomers(customer);
		if(list.size()>0){
			//客户已经存在
			return 2;
		}
		int i = customerDao.addCustomer(customer);
		if(i == 1){
			//添加成功
			return 1;
		}else{
			//添加失败
			return 3;
		}
	}

	@Override
	public DataGride findCustomer(Customer customer) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list  = customerDao.findCustomersLike(customer);
		DataGride dg = PageUtil.convertToResult(list);
		return dg;
	}

	@Override
	public int updateCustomer(Customer customer) throws Exception {
		// TODO Auto-generated method stub
		int i = customerDao.updateCustomer(customer);
		if(i==1){
			return 1;
		}else{
			throw new SQLException();
		}
	}

	@Override
	public int deleteCustomers(String ids) throws Exception {
		// TODO Auto-generated method stub
		String [] idsTemp  = ids.split(",");
		int i = customerDao.deleteCustomer(idsTemp);
		if(i==idsTemp.length){
			return 1;
		}else{
			throw new SQLException();
		}
	}

	@Override
	public Map<String, Object> countCustomerByRegion() {
		// TODO Auto-generated method stub
		//获取用户数量
		int nums = customerDao.countAllCustomer();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = customerDao.countCustomerByRegion();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("nums", nums);
		map.put("cnums", list);
		return map;
	}

	@Override
	public Map<String, Object> countCustomerByType() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = customerDao.countCustomerByType();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cnums", list);
		return map;
	}

	@Override
	public List<Map<String, Object>> findAllCustomer() {
		// TODO Auto-generated method stub
		 List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		 list = customerDao.findAllCustomer();
		return list;
	}

}
