package com.clothes.controller.wxq;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;

@Controller
public class PageOfficeContoller {
	
	@RequestMapping("/openword")
	public String openword(HttpServletRequest request,String file) throws Exception {
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
}
