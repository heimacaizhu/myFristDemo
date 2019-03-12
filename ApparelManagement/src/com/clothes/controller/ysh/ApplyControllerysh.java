package com.clothes.controller.ysh;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clothes.pojo.Employee;
import com.clothes.service.ysh.ApplyServiceysh;
import com.clothes.service.ysh.imp.ApplyServiceImpysh;
import com.clothes.utils.DataGride;
import com.clothes.utils.PageUtil;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;

/**
 * 
 * 作者:杨舒航;
 * 时间:2016年12月7日下午10:32:42;
 * 邮箱：513658133@qq.com;
 */
@Controller
public class ApplyControllerysh {
	@Autowired
	private ApplyServiceysh applyService;
	
	@RequestMapping("/apply/showAllApplys.do")
	@ResponseBody
	public List<Map<String, Object>> showAllApplys() {
		System.out.println(11);
		List<Map<String, Object>> list =new ArrayList<Map<String,Object>>();
		list = applyService.showAllApplys();
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
		return null;
	}
	
	/** 显示所有申请 */
	@RequestMapping("/apply/showAllApply.do")
	@ResponseBody
	public DataGride showAllApply(HttpServletRequest request) throws ServletException, IOException {
		System.out.println("showAllApply");
		request.setCharacterEncoding("UTF-8");
		String ap_state = request.getParameter("ap_state");
		String ap_type = request.getParameter("ap_type");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ap_state", ap_state);
		map.put("ap_type", ap_type);
		System.out.println("map is :"+map);
		List<Map<String, Object>> list =new ArrayList<Map<String,Object>>();
		int page = 1;
		int rows = 5;
		System.out.println(applyService.showAllApply(map, rows, rows));
//		list = (List<Map<String, Object>>) applyService.showAllApply(map, rows, rows);
//		for (Map<String, Object> maps : list) {
//			System.out.println(maps);
//		}
//		JSONArray jsonlist = JSONArray.fromObject(list);
//		PrintWriter out = response.getWriter();
//		out.println(jsonlist.toString());
//		//关闭流
//		out.flush();
//		out.close();
		return applyService.showAllApply(map, rows, rows);
	}
	
	/** 显示多个申请详情 */
	@RequestMapping("/apply/showOneApply.do")
	@ResponseBody
	public List<Map<String, Object>> showOneApply(HttpServletRequest request) throws UnsupportedEncodingException {
		System.out.println("showOneApply");
		request.setCharacterEncoding("UTF-8");
		String ap_id = request.getParameter("ap_id");
		System.out.println("ap_id is: "+ap_id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ap_id", ap_id);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list = applyService.showOneApply(map);
		for (Map<String, Object> map2 : list) {
			System.out.println(map2);
		}
		return list;
	}
	
	/** 处理申请 
	 * @throws IOException */
	@RequestMapping("/apply/updateOneApply.do")
	@ResponseBody
	public int updateOneApply(HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println("updateOneApply");
		request.setCharacterEncoding("UTF-8");
		//模拟session
		HttpSession session = request.getSession(false);
		
//		Employee emp = new Employee();
//		emp.setEmId(2);
//		session.setAttribute("emp", emp);
		//获取session
		Employee employee =  (Employee) session.getAttribute("employee");
		if( employee == null ){
			response.sendRedirect("login.html");
		}
		int deal_person = employee.getEmId();//处理人id
		
		String result = request.getParameter("result");//处理结语
		String ap_state = request.getParameter("ap_state");//处理状态
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String ap_dealdate = df.format(new Date());// new Date()为获取当前系统时间
		
		String ap_id = request.getParameter("ap_id");//处理申请id
		System.out.println("============");
		System.out.println(result);
		System.out.println(ap_state);
		System.out.println(ap_dealdate);
		System.out.println(deal_person);
		System.out.println(ap_id);
		System.out.println("============");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		map.put("ap_state", ap_state);
		map.put("ap_dealdate", ap_dealdate);
		map.put("deal_person", deal_person);
		map.put("ap_id", ap_id);
		System.out.println("map is ： "+map);
		int flag =  applyService.updateOneApply(map);
		return flag;
	}
	
	/** 信息商品信息 */
	@RequestMapping("/apply/showGoods.do")
	@ResponseBody
	public List<Map<String, Object>> showGoods() {
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list = applyService.showGoods();
		System.out.println(list);
		return list;
	}
	
	@RequestMapping("/openword.do")
	public String openword(HttpServletRequest request,String file) throws Exception {
		System.out.println("openword");
		System.out.println(file);
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
		file = "source/"+file;
		poCtrl1.webOpen(file, OpenModeType.docNormalEdit, "张三");
		poCtrl1.setTagId("PageOfficeCtrl1"); // 此行必须
		return "editword";
	}
	
	@RequestMapping("/apply/addApply.do")
	public String addApply(HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println("addApply");
		request.setCharacterEncoding("UTF-8");
		//模拟session
		HttpSession session = request.getSession(false);
		
//		Employee emp = new Employee();
//		emp.setEmId(2);
//		session.setAttribute("emp", emp);
		//获取session
		Employee employee =  (Employee) session.getAttribute("employee");
		if( employee == null ){
			response.sendRedirect("login.html");
		}
		int applicant = employee.getEmId();//处理人id
		
		
		String remark = request.getParameter("remark");//备注
		String gi_id = request.getParameter("goods");//商品id
		String num = request.getParameter("num");//数量
		System.out.println(remark+"|"+gi_id+"|"+num);
		UUID uuid = UUID.randomUUID(); //申请id
		String ap_id = uuid.toString().replaceAll("-", "");//申请id
		int ap_state = 1; //申请状态
		int ap_type = 3;	//申请类别
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String ap_date = df.format(new Date());// new Date()为获取当前系统时间
		int ap_lever = 2;//申请级别
		int ai_state = 1;//申请条目状态
		UUID uuid1 = UUID.randomUUID(); //申请条目id
		String ai_id = uuid1.toString().replaceAll("-", "");//申请id
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ap_id", ap_id);
		map.put("remark", remark);
		map.put("ap_state", ap_state);
		map.put("ap_type", ap_type);
		map.put("ap_date", ap_date);
		map.put("ap_lever", ap_lever);
		map.put("applicant", applicant);
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("ai_id", ai_id);
		map1.put("ap_id", ap_id);
		map1.put("num", num);
		map1.put("gi_id", gi_id);
		map1.put("ai_state", ai_state);
		System.out.println(map);
		System.out.println(map1);
		int flag = applyService.addApply(map);
		System.out.println(flag);
		if (flag == 1) {
			int flage1 = applyService.addApplyitem(map1);
			System.out.println(flage1);
		}
		return "GenerateRequisition";
	}
	
	@RequestMapping("/apply/countCUtype.do")
	@ResponseBody
	public List<Map<String, Object>> countCUtype() {
		System.out.println("countCUtype");
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list =applyService.countCUtype(); 
		System.out.println(list);
		return list;
	}
	
	@RequestMapping("/apply/countCUname.do")
	@ResponseBody
	public List<Map<String, Object>> countCUname() {
		System.out.println("countCUname");
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list =applyService.countCUname(); 
		System.out.println(list);
		return list;
	}
	
	@RequestMapping("/apply/countApplicant.do")
	@ResponseBody
	public List<Map<String, Object>> countApplicant() {
		System.out.println("countApplicant");
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list =applyService.countApplicant(); 
		System.out.println(list);
		return applyService.countApplicant();
	}
	
	@RequestMapping("/apply/countDealPerson.do")
	@ResponseBody
	public List<Map<String, Object>> countDealPerson() {
		System.out.println("countDealPerson");
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list =applyService.countDealPerson(); 
		System.out.println(list);
		return list;
	}
	
}
