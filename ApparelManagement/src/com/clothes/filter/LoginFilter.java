package com.clothes.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.clothes.pojo.Employee;


public class LoginFilter implements Filter{

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response  = (HttpServletResponse)arg1;
		HttpSession session =  request.getSession(false);
		if(session==null){
			response.sendRedirect("/ApparelManagement/login.html");
		}else{
			Employee em = (Employee)session.getAttribute("employee");
			if(em==null||em.getEmAcount() == null||em.getEmAcount().equals("")){
				response.sendRedirect("/ApparelManagement/login.html");
			}else{
				arg2.doFilter(arg0, arg1);
			}
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
