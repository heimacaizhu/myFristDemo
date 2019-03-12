package com.clothes.service.wxq.imp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clothes.dao.wxq.ApplyItemDao;
import com.clothes.pojo.Applyitem;
import com.clothes.service.wxq.ApplyItemService;

/**
 * 计划条目服务层实现
 * @author 吴晓强
 *
 */

@Service
public class ApplyItemServiceImp implements ApplyItemService {

	@Autowired
	private ApplyItemDao applyItemDao;
	
	@Override
	public int addApplyItem(Applyitem ai) throws Exception {
		// TODO Auto-generated method stub
		int i = applyItemDao.addApplyItem(ai);
		if(i == 1){
			return 1;
		}else{
			throw new SQLException();
		}
	}

	@Override
	public List<Map<String, Object>> findAppliyItems(Applyitem ai) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list = applyItemDao.findApplyItem(ai);
		return list;
	}

}
