package com.clothes.dao.wxq;

import java.util.List;
import java.util.Map;

import com.clothes.pojo.Customer;

/**
 * 客户管理数据库层接口
 * @author 吴晓强
 *
 */
public interface CustomerDao {
	
	/**
	 * 添加员工
	 * @param customer
	 * @return 添加成功返回1
	 */
	public int addCustomer(Customer customer);
	
	/**
	 * 根据条件查找客户,不分页
	 * @param customer
	 * @return
	 */
	public List<Map<String,Object>> findCustomers(Customer customer);
	
	/**
	 * 根据条件模糊查询
	 * @param customer
	 * @return
	 */
	public List<Map<String,Object>> findCustomersLike(Customer customer);
	
	/**
	 * 更新客户内容
	 * @param customer
	 * @return
	 */
	public int updateCustomer(Customer customer);
	
	/**
	 * 删除客户
	 * @param ids
	 * @return
	 */
	public int deleteCustomer(String []ids);
	
	/**
	 * 统计客户数量
	 * @return
	 */
	public int countAllCustomer();
	
	/**
	 * 统计每个区域的客户数量
	 * @return
	 */
	public List<Map<String,Object>>  countCustomerByRegion();
	
	/**
	 * 分类统计客户数量
	 * @return
	 */
	public List<Map<String,Object>> countCustomerByType();
	
	/**
	 * 获取所有的客户
	 * @return
	 */
	public List<Map<String,Object>>  findAllCustomer();
}
