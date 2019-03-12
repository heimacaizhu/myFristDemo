package com.clothes.service.wxq;


import java.util.List;
import java.util.Map;

import com.clothes.pojo.Customer;
import com.clothes.utils.DataGride;

/**
 * 客户管理服务层接口
 * @author 吴晓强
 *
 */
public interface CustomerService {
	
	/**
	 * 添加客户
	 * @param customer 要添加的客户
	 * @return 添加成功返回 1  客户已经存在返回2   添加失败返回3
	 */
	public int addCustomer(Customer customer);
	
	/**
	 * 根据条件查询客户，不分页
	 * @param customer 查询条件
	 * @return
	 */
	public DataGride findCustomer(Customer customer);
	
	/**
	 * 更新区域内容
	 * @param customer
	 * @return 1 修改成功    2 客户已经存在    3   修改失败
	 */
	public int updateCustomer(Customer customer) throws Exception;
	
	/**
	 * 删除客户
	 * @param ids
	 * @return 删除成功返回1
	 * @throws Exception
	 */
	public int deleteCustomers(String ids) throws Exception;
	
	/**
	 * 根据区域统计客户数量
	 * @return
	 */
	public Map<String,Object> countCustomerByRegion();
	
	/**
	 * 通过客户类型来统计
	 * @return
	 */
	public Map<String,Object> countCustomerByType();
	
	/**
	 * 获取所有的客户
	 * @return
	 */
	public List<Map<String,Object>> findAllCustomer();
}
