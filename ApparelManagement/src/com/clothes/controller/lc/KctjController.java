package com.clothes.controller.lc;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clothes.service.lc.KctjService;

@Controller
public class KctjController {

	@Autowired
	private KctjService kctjService;
	//查询所有
	@RequestMapping("/getAllKctj.do")
	@ResponseBody
	public List<Map<String,Object>> getAllKctj(HttpServletRequest req){
		List<Map<String,Object>> list = kctjService.getAllKctj();
		System.out.println(list);
		return list;
	}
	
	
}
