package com.clothes.controller.wxq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clothes.pojo.Apply;
import com.clothes.pojo.Applyitem;
import com.clothes.pojo.Goodstitem;
import com.clothes.service.wxq.ApplyItemService;
import com.clothes.utils.UUIDUtils;

/**
 * 计划条目控制层
 * @author 吴晓强
 *
 */
@Controller
public class ApplyItemController {
	
	@Autowired
	private ApplyItemService applyItempServiceImp;
	
	/**
	 * 添加一条订单条目
	 * @param ai
	 * @param aid
	 * @param gid
	 * @return
	 */
	@RequestMapping("/addapplyitem")
	@ResponseBody
	public Map<String,Object> addApplyItem(Applyitem ai,String aid,int gid){
		Apply apply = new Apply();
		apply.setApId(aid);
		Goodstitem gi = new Goodstitem();
		gi.setGiId(gid);
		ai.setApply(apply);
		ai.setGoodsItem(gi);
		ai.setAiId(UUIDUtils.getUUID());
		int i = 2;
		try {
			i = applyItempServiceImp.addApplyItem(ai);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("msg", i);
		return map;
	}
	
	/**
	 * 获取一条计划所有的条目
	 * @param id
	 * @return
	 */
	@RequestMapping("/showapplyitem")
	@ResponseBody
	public List<Map<String,Object>>  findApplyItems(String id){
		Applyitem ai = new Applyitem();
		Apply apply = new Apply();
		apply.setApId(id);
		ai.setApply(apply);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = applyItempServiceImp.findAppliyItems(ai);
		return list;
	}
}
