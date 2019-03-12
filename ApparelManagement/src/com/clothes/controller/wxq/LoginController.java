package com.clothes.controller.wxq;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clothes.dao.tq.messageTMapper;
import com.clothes.pojo.Employee;
import com.clothes.pojo.EmployeeT;
import com.clothes.service.wxq.EmployeeService;

/**
 * 登录和退出登录
 * @author 吴晓强
 *
 */
@Controller
public class LoginController {
	
	@Autowired
	private EmployeeService employeeServiceImp;
	
	@Autowired
	private messageTMapper  messageTMapper;
	
	/**
	 * 登录
	 * @param em
	 * @param request
	 * @return
	 */
	@RequestMapping("/loginin")
	@ResponseBody
	public Map<String,Object> login(Employee em,String pageCode,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		HttpSession session = request.getSession(false);
		String code = "";
		//获取验证码
		if(session!=null){
			code = (String)session.getAttribute("validateCode");
		}else{
			map.put("msg", 2);
			return map;
		}
		if(!code.equals(pageCode)){
			//验证码不正确
			map.put("msg", 2);
			return map;
		}
		Employee emp = employeeServiceImp.findEmployee(em);
		if(emp == null){
			//账号和密码不匹配
			map.put("msg", 3);
			return map;
		}
		session.setAttribute("employee", emp);
		//放一个tq的employee
		EmployeeT employeeT = messageTMapper.employeetById(String.valueOf(emp.getEmId()));
		session.setAttribute("employeeT", employeeT);
		tyoe(employeeT);
		System.out.println("-------------->"+employeeT);
		
		
		map.put("msg", 1);
		return map;
	}
	
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session!=null){
			session.invalidate();
		}
		return "login";
	}
	
	public  void  tyoe(EmployeeT employeeT){
		if(employeeT.getEmType().equals("1")){
			employeeT.setEmType("管理员");
		}
		if(employeeT.getEmType().equals("2")){
			employeeT.setEmType("客服人员");
		}
		if(employeeT.getEmType().equals("3")){
			employeeT.setEmType("办公室人员");
		}
		if(employeeT.getEmType().equals("4")){
			employeeT.setEmType("车间主任");
		}
		if(employeeT.getEmType().equals("5")){
			employeeT.setEmType("仓库管理员");
		}
	}
	
	
}
