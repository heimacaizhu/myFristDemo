package com.clothes.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/*
 *作者：杨聂；    
 *邮箱：1024938222@qq.com；      
 *时间：2016年12月9日下午6:18:37；      
 */
public class UploadUtil {
	/**
	 * 图片上传
	 * @param file 文件
	 * @param path  保存路劲
	 * @return 结果
	 */
	public static Map<String, String> upLoadImg(MultipartFile file, String path) {
		Map<String, String> rMap = new HashMap<String, String>();
		String fileName =System.currentTimeMillis() +"-"+ file.getOriginalFilename();
		String url =fileName;
		File targetFile = new File(path, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			rMap.put("result", "0");
			return rMap;
		}
		rMap.put("result", "1");
		rMap.put("url", url);
		return rMap;
	}
}
