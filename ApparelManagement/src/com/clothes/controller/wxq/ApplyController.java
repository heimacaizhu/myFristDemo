package com.clothes.controller.wxq;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.clothes.pojo.Apply;
import com.clothes.pojo.Customer;
import com.clothes.pojo.Employee;
import com.clothes.service.wxq.ApplyService;
import com.clothes.utils.CloudInfDemo;
import com.clothes.utils.DataGride;
import com.clothes.utils.DateUtil;
import com.clothes.utils.UUIDUtils;
import com.github.pagehelper.PageHelper;

/**
 * 申请计划控制层处理类
 * @author 吴晓强
 *
 */
@Controller
public class ApplyController {
	
	@Autowired
	private ApplyService applyServiceImp;
	
	/**
	 * 添加一个申请计划
	 * @param cuId 客户id
	 * @return 添加成功返回计划申请号
	 */
	@RequestMapping("/addaply")
	@ResponseBody
	public Map<String,Object> addApply(int cuId){
		//计划
		Apply apply = new Apply();
		//客户
		Customer customer = new Customer();
		customer.setCuId(cuId);
		//申请员工
		Employee em = new Employee();
		em.setEmId(1);
		String apid = UUIDUtils.getUUID();
		String date = DateUtil.getDateYMD();
		//设置申请计划id
		apply.setApId(apid);
		//设置申请人
		apply.setApplicant(em);
		//设置客户
		apply.setCustomer(customer);
		//设置申请的处理级别
		apply.setApLever(1);
		//设置计划类型
		apply.setApType(1);
		//设置申请时间
		apply.setApDate(date);
		int i = 2;
		try {
			i = applyServiceImp.addApply(apply);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String,Object> map = new HashMap<String,Object>();
		if(i == 1){
			map.put("msg",apid);
		}else{
			map.put("msg", i);
		}
		return map;
	}
	
	/**
	 * 查找计划
	 * @param apply  查找条件
	 * @param page  当前页
	 * @param rows  每页显示的行数
	 * @return
	 */
	@RequestMapping("/showapply")
	@ResponseBody
	public DataGride findApply(Apply apply,int page,int rows){
		PageHelper.startPage(page, rows);
		DataGride dg = new DataGride();
		String d = apply.getApDate();
		d = ("%"+d+"%");
		apply.setApDate(d);
		dg = applyServiceImp.findApply(apply);
		return dg;
	}
	
	/**
	 * 上传附件
	 * @return
	 */
	@RequestMapping("/fileUpload")
	@ResponseBody
	public Map<String,Object> fileUplode(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request){
		long  startTime=System.currentTimeMillis();
		System.out.println("fileName："+file.getOriginalFilename());
		String fname = UUIDUtils.getUUID();
		
		String path=request.getContextPath()+"/source/"+ fname;
		File newFile=new File(path);
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			file.transferTo(newFile);
			map.put("msg", fname);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("msg", 2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("msg", 2);
		}
        long  endTime=System.currentTimeMillis();
        System.out.println("时间:"+(endTime-startTime));
		return map;
	}
	
	/**
	 * 保存文件
	 * @param apply
	 * @return
	 */
	@RequestMapping("/saveFile")
	@ResponseBody
	public Map<String,Object> saveFile(Apply apply){
		Map<String,Object> map = new HashMap<String,Object>();
		int i = 2;
		try {
			i = applyServiceImp.updateFile(apply);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("msg", i);
		return map;
	}
	
	/**
	 * 获取报单的附件名
	 * @param id
	 * @return
	 */
	@RequestMapping("/showfilename")
	@ResponseBody
	public Map<String,Object> getFilename(String id){
		Map<String,Object> map = new HashMap<String,Object>();
		String file = applyServiceImp.findFile(id);
		map.put("file", file);
		return map;
	}
	
	/**
	 * 发送报单回馈消息
	 * @return
	 */
	@RequestMapping("/bdmsg")
	@ResponseBody
	public Map<String,Object> sendMsg(String id,String result,String phone,String msgid){
		Map<String,Object> map = new HashMap<String,Object>();
		int i = 2;
		String [] ps;
		//判断是发送的那种消息
		id = id.substring(0, 19);
		if(msgid!=null&&!"".equals(msgid)&&msgid.equals("880")){
			ps = new String[]{id,result};
			try {
				CloudInfDemo.sendSmsTpl(id, "18381306324", msgid, ps);
				i = 1;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(msgid!=null&&!"".equals(msgid)&&msgid.equals("881")){
			ps = new String[]{id,result};
			try {
				CloudInfDemo.sendSmsTpl(id, "18381306324", msgid, ps);
				i = 1;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		map.put("msg", i);
		return map;
	}
}
