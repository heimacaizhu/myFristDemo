package com.clothes.service.yn.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clothes.dao.yn.EmployDaoy;
import com.clothes.pojo.Employee;
import com.clothes.service.yn.EmployeeServicey;
import com.clothes.utils.DataGride;
import com.clothes.utils.EmpUtil;
import com.clothes.utils.PageUtil;

/*
 *作者：杨聂；    
 *邮箱：1024938222@qq.com；      
 *时间：2016年12月7日上午10:11:06；      
 */
@Service
public class EmployeeServiceyImp implements EmployeeServicey {
	@Autowired
	private EmployDaoy ed;

	@Override
	public DataGride getAllEmp() {
		List<Employee> emps = ed.getAllEmp();
		return PageUtil.convertToResult(emps);
	}

	@Override
	public int addEmp(Map<String, Object> emp) throws Exception {
		emp.put("acount", EmpUtil.getEmpAcount((String) emp.get("type")));
		System.out.println(emp.get("acount"));
		if(ed.addEmp(emp)>0){
			emp.put("id", ed.getEmpId((String) emp.get("acount")).getEmId());
			System.out.println(emp.get("id"));
			if(ed.addEmpInfo(emp)>0){
				return 1;
			}else {
				throw new Exception();
			}
		}else{
			throw new Exception();
		}
	}

	@Override
	public int updateEmp(Map<String, Object> emp) throws Exception {
		if(ed.updateEmp(emp)>0&&ed.updateEmpInfo(emp)>0){
			return 1;
		}else{
			throw new Exception();
		}
	}

	@Override
	public Employee getEmpById(String id) {
		return ed.getEmpById(id);
	}

	@Override
	public int delEmp(String id) throws Exception {
		if(ed.delEmp(id)>0&&ed.delEmpInfo(id)>0){
			return 1;
		}else {
			throw new Exception();
		}
	}

	@Override
	public DataGride getEmpByLike(Map<String, Object> emp) {
		List<Employee> emps = ed.getEmpByLike(emp);
		return PageUtil.convertToResult(emps);
	}

}
