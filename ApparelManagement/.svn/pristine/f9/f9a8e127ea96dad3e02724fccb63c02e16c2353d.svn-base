package com.clothes.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/*
 *作者：杨聂；    
 *邮箱：1024938222@qq.com；      
 *时间：2016年12月7日下午3:43:36；      
 */
public class EmpUtil {
	public static String getEmpAcount(String type) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date date1 = new Date();
		int t = Integer.parseInt(type) * 10000;
		Random random = new Random();
		int num = random.nextInt(10000) + t;
		return format.format(date1) + num;
	}
}
