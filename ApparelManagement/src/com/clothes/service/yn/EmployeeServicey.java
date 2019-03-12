package com.clothes.service.yn;

import java.util.List;
import java.util.Map;

import com.clothes.pojo.Employee;
import com.clothes.utils.DataGride;

/*
 *作者：杨聂；    
 *邮箱：1024938222@qq.com；      
 *时间：2016年12月7日上午10:07:29；      
 */
public interface EmployeeServicey {
	/**
	 * 获取所有员工的信息
	 * @return  放回员工信息对象
	 */
	public DataGride getAllEmp();
	
	/**
	 * 模糊查询
	 * @param emp  条件结合
	 * @return  反回员工信息对象
	 */
	public DataGride getEmpByLike(Map<String, Object> emp );
	
	/**
	 * 获取指定员工的信息
	 * @return  放回员工信息对象
	 */
	public Employee getEmpById(String id);
	
	/**
	 * 增加员工基本信息
	 * @param emp  信息结合
	 * @return  返回插入的行
	 * @throws Exception 插入不成功手动抛出异常，进行事物回滚
	 */
	public int addEmp(Map<String, Object> emp) throws Exception;
	
	/**
	 * 修改员工基本信息
	 * @param emp 信息结合
	 * @return  返回修改的行
	 */
	public int updateEmp(Map<String, Object> emp) throws Exception;
	
	/**
	 * 删除员工
	 * @param id  员工id
	 * @return  返回删除的行
	 * @throws Exception 
	 */
	public int delEmp(String id) throws Exception;
	
}
