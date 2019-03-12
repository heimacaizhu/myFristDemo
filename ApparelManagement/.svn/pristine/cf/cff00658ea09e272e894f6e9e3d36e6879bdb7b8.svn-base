package com.clothes.controller.wxq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clothes.pojo.Region;
import com.clothes.service.wxq.RegionService;
import com.clothes.utils.DataGride;
import com.github.pagehelper.PageHelper;

/**
 * 区域管理控制层
 * @author 吴晓强
 *
 */
@Controller
public class RegionController {
	@Autowired
	private RegionService regionServiceImp;
	
	/**
	 * 新增区域
	 * @param region
	 * @return
	 */
	@RequestMapping("/addregion")
	@ResponseBody
	public Map<String,Object> addRegion(Region region){
		region.setrState(1);
		int i = regionServiceImp.addRegion(region);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("msg", i);
		return map;
	}
	/**
	 * 查询所有的区域,不分页
	 * @return
	 */
	@RequestMapping("/nopageRegion")
	@ResponseBody
	public List<Map<String,Object>> findRegions(){
		List<Map<String,Object>> list  = new ArrayList<Map<String,Object>>();
		Region region  = new Region();
		list = regionServiceImp.findRegion(region);
		return list;
	}
	
	/**
	 * 按照条件查询区域
	 * @param region 查找条件
	 * @param page 当前页
	 * @param rows 每页要显示的行数
	 * @return
	 */
	@RequestMapping("/onRegionPage")
	@ResponseBody
	public DataGride findRegionsPage(Region region,int page,int rows){
		String name = region.getrName();
		name = ("%" + name + "%");
		region.setrName(name);
		PageHelper.startPage(page,5);
		DataGride dg = new DataGride();
		dg = regionServiceImp.findRegionPage(region);
		return dg;
	}
	
	/**
	 * 更新区域内容
	 * @param region
	 * @return
	 */
	@RequestMapping("/editRegion")
	@ResponseBody
	public Map<String,Object> editRegion(Region region){
		Map<String,Object> map = new HashMap<String,Object>();
		int i = regionServiceImp.updateRegion(region);
		map.put("msg", i);
		return map;
	}
	
	/**
	 * 删除区域
	 * @param ids 要删除的所有id
	 * @return 删除成功返回1,删除失败返回2
	 */
	@RequestMapping("/delteRegion")
	@ResponseBody
	public Map<String,Object> deleteRegion(String ids){
		Map<String,Object> map = new HashMap<String,Object>();
		int i = 2;
		try {
			i = regionServiceImp.deleteRegions(ids);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("msg", i);
		return map;
	}
}
