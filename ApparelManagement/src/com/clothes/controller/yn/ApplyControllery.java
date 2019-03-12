package com.clothes.controller.yn;

import java.io.BufferedInputStream;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clothes.pojo.Employee;
import com.clothes.service.yn.ApplyServicey;
import com.clothes.utils.DataGride;
import com.github.pagehelper.PageHelper;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;
import com.zhuozhengsoft.pageoffice.OpenModeType;


/*
 *作者：杨聂；    
 *邮箱：1024938222@qq.com；      
 *时间：2016年12月12日下午11:39:48；      
 */
@Controller
public class ApplyControllery {
	@Autowired
	private ApplyServicey as;

	@RequestMapping("/getAllApply.do")
	@ResponseBody
	public DataGride getAllApply(int page, int rows) {
		PageHelper.startPage(page, rows);
		DataGride apply = as.getAllApply();
		return apply;
	}
	
	@RequestMapping("/getAllDealApply.do")
	@ResponseBody
	public DataGride getAllDealApply(int page, int rows) {
		PageHelper.startPage(page, rows);
		DataGride apply = as.getAllDealApply();
		return apply;
	}

	@RequestMapping("/updateDealApply.do")
	@ResponseBody
	public Map<String, Integer> updateDealApply(HttpServletRequest request,String state, String id) {
		Map<String, Integer> rMap = new HashMap<String, Integer>();
		HttpSession session = request.getSession();
		Employee emp=(Employee) session.getAttribute("employee");
		rMap.put("result", as.updateDealApply(state, id,emp.getEmId()+""));
		return rMap;
	}
	
	@RequestMapping("/delDealApply.do")
	@ResponseBody
	public Map<String, Integer> delDealApply(String id) {
		Map<String, Integer> rMap = new HashMap<String, Integer>();		
		rMap.put("result", as.delDealApply(id));
		return rMap;
	}

	@RequestMapping("/lookWord.do")
	public String lookWord(HttpServletRequest request){
		PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
		poCtrl1.setServerPage("poserver.zz"); // 此行必须
		// 设置标题
		poCtrl1.setCaption("打开文档操作");
		// 创建工具条
		poCtrl1.addCustomToolButton("-", "", 0);
		poCtrl1.addCustomToolButton("打印", "ShowPrintDlg()", 6);
		poCtrl1.addCustomToolButton("-", "", 0);
		poCtrl1.addCustomToolButton("全屏切换", "SetFullScreen()", 4);
		poCtrl1.addCustomToolButton("-", "", 0);
		poCtrl1.addCustomToolButton("加盖印章", "AddSeal()", 5);
		poCtrl1.addCustomToolButton("手写签批", "AddHandSign()", 5);
		poCtrl1.addCustomToolButton("验证印章", "VerifySeal()", 5);
		String file = "source/"+request.getParameter("url");
		System.out.println(file);
		poCtrl1.webOpen(file, OpenModeType.docNormalEdit, "apply");
		poCtrl1.setTagId("PageOfficeCtrl1"); // 此行必须
		return "editword";
	}

}
