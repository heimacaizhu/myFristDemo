package com.clothes.service.wxq.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clothes.dao.wxq.EmployeeDao;
import com.clothes.pojo.Employee;
import com.clothes.service.wxq.EmployeeService;

@Service
public class EmployeeServiceImp implements EmployeeService{

	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	public Employee findEmployee(Employee em) {
		// TODO Auto-generated method stub
		return employeeDao.findEmployee(em);
	}
	
}
