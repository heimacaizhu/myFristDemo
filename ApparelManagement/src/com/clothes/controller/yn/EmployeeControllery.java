package com.clothes.controller.yn;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clothes.pojo.Employee;
import com.clothes.service.yn.EmployeeServicey;
import com.clothes.utils.DataGride;
import com.github.pagehelper.PageHelper;

/*
 *作者：杨聂；    
 *邮箱：1024938222@qq.com；      
 *时间：2016年12月7日上午10:17:53；      
 */
@Controller
public class EmployeeControllery {
	@Autowired
	private EmployeeServicey es;
	
	@RequestMapping("/getAllEmp.do")
	@ResponseBody
	public DataGride getAllEmp(int page,int rows) {
        PageHelper.startPage(page, rows);
        DataGride emps = es.getAllEmp();
		return emps;
	}
	
	@RequestMapping("/getEmpById.do")
	@ResponseBody
	public Employee getEmpById(String id) {
		return es.getEmpById(id);
	}
	
	@RequestMapping("/getEmpByLike.do")
	@ResponseBody
	public DataGride getEmpByLike(HttpServletRequest request) {
        PageHelper.startPage(Integer.parseInt(request.getParameter("page")), Integer.parseInt(request.getParameter("rows")));
        String type=request.getParameter("p");
        String state=request.getParameter("p");
        Map<String, Object> emp=new HashMap<String, Object>();
        if ("管理员".equals(type)) {
        	emp.put("type", "%1%");
    	} else if ("客服人员".equals(type)) {
    		emp.put("type", "%2%");
    	} else if ("办公室人员".equals(type)) {
    		emp.put("type", "%3%");
    	} else if ("车间主任".equals(type)) {
    		emp.put("type", "%4%");
    	} else if ("仓库管理员".equals(type)) {
    		emp.put("type", "%5%");
    	} else {
    		emp.put("type", "%-1%");
    	}
        
        if ("不可用".equals(state)) {
        	emp.put("state", "%0%");
    	} else if ("可用".equals(state)) {
    		emp.put("state", "%1%");
    	} else {
    		emp.put("state", "%-1%");
    	}
       
		emp.put("acount", "%"+request.getParameter("p")+"%");
		emp.put("name", "%"+request.getParameter("p")+"%");
		emp.put("phone", "%"+request.getParameter("p")+"%");
        DataGride emps = es.getEmpByLike(emp);
		return emps;
	}
	
	@RequestMapping("/addEmp.do")
	@ResponseBody
	public Map<String, Integer> addEmp(HttpServletRequest request) {
		Map<String, Object> emp=new HashMap<String, Object>();
		emp.put("pwd", request.getParameter("pwd"));
		emp.put("type", request.getParameter("type"));
		emp.put("state", 1);
		emp.put("name", request.getParameter("name"));
		emp.put("phone", request.getParameter("phone"));
		Map<String, Integer> rMap=new HashMap<String, Integer>();
		try {
			rMap.put("result", es.addEmp(emp));	
		} catch (Exception e) {
			rMap.put("result", 0);	
		}
		return rMap;
	}
	
	@RequestMapping("/updateEmp.do")
	@ResponseBody
	public Map<String, Integer> updateEmp(HttpServletRequest request) {
		Map<String, Object> emp=new HashMap<String, Object>();
		emp.put("type", request.getParameter("type"));
		emp.put("id", request.getParameter("id"));
		emp.put("state", request.getParameter("state"));
		emp.put("phone", request.getParameter("phone"));
		Map<String, Integer> rMap=new HashMap<String, Integer>();
		try {
			rMap.put("result", es.updateEmp(emp));	
		} catch (Exception e) {
			rMap.put("result", 0);	
		}
		return rMap;
	}
	
	@RequestMapping("/delEmp.do")
	@ResponseBody
	public Map<String, Integer> delEmp(String id) { 
		Map<String, Integer> rMap=new HashMap<String, Integer>();
		try {
			rMap.put("result", es.delEmp(id));	
		} catch (Exception e) {
			rMap.put("result", 0);	
		}
		return rMap;
	}
	
}
