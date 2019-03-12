package com.clothes.controller.yn;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.clothes.pojo.Goods;
import com.clothes.pojo.Goodsimgs;
import com.clothes.service.yn.GoodsServicey;
import com.clothes.utils.DataGride;
import com.clothes.utils.UploadUtil;
import com.github.pagehelper.PageHelper;

/*
 *作者：杨聂；    
 *邮箱：1024938222@qq.com；      
 *时间：2016年12月8日下午12:33:02；      
 */
@Controller
public class GoodsControllery {
	@Autowired
	private GoodsServicey gs;

	@RequestMapping("/getAllgoods.do")
	@ResponseBody
	public DataGride getAllgoods(String type, int page, int rows) {
		PageHelper.startPage(page, rows);
		DataGride g = gs.getAllgoods(type);
		return g;
	}
	
	@RequestMapping("/getGoodsItems.do")
	@ResponseBody
	public DataGride getGoodsItems(String type, int page, int rows) {
		PageHelper.startPage(page, rows);
		DataGride g = gs.getGoodsItems(type);
		return g;
	}

	@RequestMapping("/getGoodsById.do")
	@ResponseBody
	public Goods getGoodsById(String id) {
		return gs.getGoodsById(id);
	}
	
	@RequestMapping("/getGoodsImgById.do")
	@ResponseBody
	public List<Goodsimgs> getGoodsImgById(String id) {
		return gs.getGoodsImgById(id);
	}

	@RequestMapping("/getGoodsItemById.do")
	@ResponseBody
	public Goods getGoodsItemById(String id) {
		return gs.getGoodsItemById(id);
	}

	@RequestMapping("/addGoods.do")
	@ResponseBody
	public Map<String, Integer> addGoods(HttpServletRequest request) {
		Map<String, Object> goods = new HashMap<String, Object>();
		goods.put("name", request.getParameter("name"));
		goods.put("brand", request.getParameter("brand"));
		goods.put("state", 1);
		goods.put("price", request.getParameter("price"));
		goods.put("fabrics", request.getParameter("fabrics"));
		goods.put("type", request.getParameter("type"));
		Map<String, Integer> rMap = new HashMap<String, Integer>();
		rMap.put("result", gs.addGoods(goods));
		return rMap;
	}

	@RequestMapping("/addGoodsItem.do")
	@ResponseBody
	public Map<String, Integer> addGoodsItem(HttpServletRequest request) {
		Map<String, Object> goods = new HashMap<String, Object>();
		goods.put("id", request.getParameter("id"));
		goods.put("color", request.getParameter("color"));
		goods.put("size", request.getParameter("size"));
		goods.put("state", request.getParameter("state"));
		Map<String, Integer> rMap = new HashMap<String, Integer>();
		rMap.put("result", gs.addGoodsItem(goods));
		return rMap;
	}

	@RequestMapping("/updateGoods.do")
	@ResponseBody
	public Map<String, Integer> updateGoods(HttpServletRequest request) {
		Map<String, Object> goods = new HashMap<String, Object>();
		goods.put("id", request.getParameter("id"));
		goods.put("name", request.getParameter("name"));
		goods.put("brand", request.getParameter("brand"));
		goods.put("state", request.getParameter("state"));
		goods.put("price", request.getParameter("price"));
		goods.put("fabrics", request.getParameter("fabrics"));
		Map<String, Integer> rMap = new HashMap<String, Integer>();
		rMap.put("result", gs.updateGoods(goods));
		return rMap;
	}

	@RequestMapping("/updateGoodsItem.do")
	@ResponseBody
	public Map<String, Integer> updateGoodsItem(HttpServletRequest request) {
		Map<String, Object> goods = new HashMap<String, Object>();
		goods.put("id", request.getParameter("id"));
		goods.put("color", request.getParameter("color"));
		goods.put("size", request.getParameter("size"));
		goods.put("state", request.getParameter("state"));
		Map<String, Integer> rMap = new HashMap<String, Integer>();
		rMap.put("result", gs.updateGoodsItem(goods));
		return rMap;
	}

	@RequestMapping("/delGoodsItem.do")
	@ResponseBody
	public Map<String, Integer> delGoodsItem(String id) {
		Map<String, Integer> rMap = new HashMap<String, Integer>();
		rMap.put("result", gs.delGoodsItem(id));
		return rMap;
	}
	
	@RequestMapping("/delGoodsImgById.do")
	@ResponseBody
	public Map<String, Integer> delGoodsImgById(String id) {
		Map<String, Integer> rMap = new HashMap<String, Integer>();
		rMap.put("result", gs.delGoodsImgById(id));
		return rMap;
	}

	@RequestMapping("/delGoods.do")
	@ResponseBody
	public Map<String, Integer> delGoods(String id) {
		Map<String, Integer> rMap = new HashMap<String, Integer>();
		try {
			rMap.put("result", gs.delGoods(id));
		} catch (Exception e) {
			rMap.put("result", 0);
		}
		return rMap;
	}

	@RequestMapping("/getGoodsByLike.do")
	@ResponseBody
	public DataGride getGoodsByLike(HttpServletRequest request) {
		PageHelper.startPage(Integer.parseInt(request.getParameter("page")),
				Integer.parseInt(request.getParameter("rows")));
		String state = request.getParameter("p");
		String type = request.getParameter("p");
		String isimg =request.getParameter("isimg");
		Map<String, Object> goods = new HashMap<String, Object>();
		if ("不可用".equals(state)) {
			goods.put("state", "%0%");
		} else if ("新品".equals(state)) {
			goods.put("state", "%1%");
		} else if ("老品牌".equals(state)) {
			goods.put("state", "%2%");
		} else {
			goods.put("state", "%-1%");
		}
		if ("产品".equals(type)) {
			goods.put("type", "%1%");
		} else if ("原材料".equals(type)) {
			goods.put("type", "%2%");
		} else {
			goods.put("type", "%-1%");
		}
		if("n".equals(isimg)){
			goods.put("isimg", null);
		}else{
			goods.put("isimg", "y");
		}
		goods.put("name", "%" + request.getParameter("p") + "%");
		goods.put("brand", "%" + request.getParameter("p") + "%");
		goods.put("fabrics", "%" + request.getParameter("p") + "%");
		goods.put("color", "%" + request.getParameter("p") + "%");
		goods.put("gsize", "%" + request.getParameter("p") + "%");
		goods.put("price", "%" + request.getParameter("p") + "%");
		DataGride g = gs.getGoodsByLike(goods);
		return g;
	}

	@RequestMapping("/upLoadGoodsItemImg.do")
	@ResponseBody
	public Map<String, String> upLoadGoodsItemImg(HttpServletRequest request,MultipartFile file) {
		// 得到上传文件的保存目录
		String path = request.getServletContext().getRealPath("/source/upload");
		Map<String, String> rMap = UploadUtil.upLoadImg(file, path);
		return rMap;
	}
	
	@RequestMapping("/addGoodsImg.do")
	@ResponseBody
	public Map<String, Integer> addGoodsImg(String id,String url) {
		Map<String, Object> img=new HashMap<>();
		img.put("state", "1");
		img.put("url", url);
		img.put("id", id);
		Map<String, Integer> rMap = new HashMap<String, Integer>();
		rMap.put("result", gs.addGoodsImg(img));
		return rMap;
	}

}
