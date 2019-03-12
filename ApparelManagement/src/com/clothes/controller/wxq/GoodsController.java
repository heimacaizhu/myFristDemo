package com.clothes.controller.wxq;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clothes.service.wxq.GoodsService;

@Controller
public class GoodsController {
	
	@Autowired
	private GoodsService goodsServiceImp;
	
	/**
	 * 获取所有的产品
	 * @return
	 */
	@RequestMapping("/showgoods")
	@ResponseBody
	public List<Map<String,Object>>  findGoods(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = goodsServiceImp.findGoods();
		return list;
	}
}
