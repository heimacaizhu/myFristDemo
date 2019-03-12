package com.clothes.utils;

import java.util.List;

import net.sf.json.JSONObject;

import com.github.pagehelper.Page;


/*
 *作者：杨聂；    
 *邮箱：1024938222@qq.com；      
 *时间：2016年11月25日上午10:09:40；      
 *分页包
 */
public class PageUtil {
	/**
	 * 放回分页数据
	 */
	public static DataGride convertToResult(List<?> gridList) {
		DataGride dGride = null;
		if (gridList != null) {
			if(gridList instanceof Page){
				Page<?> page=(Page<?>) gridList;
				dGride=new DataGride(page.getTotal(),page.getPages(),page.getResult());
			}else {
				dGride=new DataGride(new Long(gridList.size()),(int)Math.ceil(gridList.size()/10.0),gridList);
			}
		} else {
			dGride = new DataGride(new Long(0),0, null);
		}
		return dGride;
	}
	
	/**
	 * 将datagrid转化为json
	 */
	
	public static String converList(List<?> gridList){
		JSONObject jo=new JSONObject();
		if(gridList!=null){
			if(gridList instanceof Page){
				Page<?> page=(Page<?>) gridList;
				jo.put("total",page.getTotal());
				jo.put("rows",page.getResult());
			}else{
				jo.put("total",gridList.size());
				jo.put("rows",gridList);
			}
		}else{
			jo.put("total",0);
			jo.put("rows",null);
		}
		return "";
	}
}
