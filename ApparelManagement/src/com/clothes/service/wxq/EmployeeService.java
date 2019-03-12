package com.clothes.service.wxq;

import com.clothes.pojo.Employee;

/**
 * 员工服务层接口
 * @author 吴晓强
 *
 */
public interface EmployeeService {
	
	/**
	 * 根据账号和密码查询一个员工
	 * @param em
	 * @return
	 */
	public Employee findEmployee(Employee em);
}
