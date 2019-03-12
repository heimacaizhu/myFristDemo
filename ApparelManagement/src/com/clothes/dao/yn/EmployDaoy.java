package com.clothes.dao.yn;

import java.util.List;
import java.util.Map;

import com.clothes.pojo.Employee;

/*
 *作者：杨聂；    
 *邮箱：1024938222@qq.com；      
 *时间：2016年12月7日上午9:06:47；      
 *员工数据层
 */
public interface EmployDaoy {
	/**
	 * 获取所有员工的信息
	 * @return  反回员工信息对象结合
	 */
	public List<Employee> getAllEmp();
	
	/**
	 * 获取指定员工的信息
	 * @return  反回员工信息对象
	 */
	public Employee getEmpById(String id);
	
	/**
	 * 模糊查询
	 * @param emp  条件结合
	 * @return  反回员工信息对象结合
	 */
	public List<Employee> getEmpByLike(Map<String, Object> emp );
	
	/**
	 * 增加员工基本信息
	 * @param emp  信息结合
	 * @return  返回插入的行
	 */
	public int addEmp(Map<String, Object> emp);
	
	/**
	 * 增加员工的详细信息
	 * @param emp 信息结合
	 * @return 返回插入的行
	 */
	public int addEmpInfo(Map<String, Object> emp);
	
	/**
	 * 根据员工账号获取员工id
	 * @param acount  员工账号
	 * @return  员工对象
	 */
	public Employee getEmpId(String acount);
	
	/**
	 * 修改员工基本信息
	 * @param emp 信息结合
	 * @return  返回修改的行
	 */
	public int updateEmp(Map<String, Object> emp);
	
	/**
	 * 修改员工详细信息
	 * @param emp 信息结合
	 * @return 返回修改的行
	 */
	public int updateEmpInfo(Map<String, Object> emp);
	
	/**
	 * 删除员工
	 * @param id  员工id
	 * @return  返回删除的行
	 */
	public int delEmp(String id);
	
	/**
	 * 删除员工信息
	 * @param id 员工id
	 * @return 返回删除的行
	 */
	public int delEmpInfo(String id);
}
