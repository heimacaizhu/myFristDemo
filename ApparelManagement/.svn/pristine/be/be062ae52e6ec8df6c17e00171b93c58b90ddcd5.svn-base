package com.clothes.service.wxq.imp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clothes.dao.wxq.ApplyDao;
import com.clothes.pojo.Apply;
import com.clothes.service.wxq.ApplyService;
import com.clothes.utils.DataGride;
import com.clothes.utils.PageUtil;

/**
 * 申请计划服务层实现类
 * @author 吴晓强
 *
 */
@Service
public class ApplyServiceImp implements ApplyService {
	
	@Autowired
	private ApplyDao applyDao;
	
	@Override
	public int addApply(Apply apply) throws Exception {
		// TODO Auto-generated method stub
		int i = applyDao.addApply(apply);
		if(i == 1){
			return 1;
		}else{
			throw new SQLException();
		}
	}

	@Override
	public DataGride findApply(Apply apply) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
		list = applyDao.findApplys(apply);
		DataGride dg  = new DataGride();
		dg = PageUtil.convertToResult(list);
		return dg;
	}

	@Override
	public int updateFile(Apply apply) throws Exception {
		// TODO Auto-generated method stub
		int i = applyDao.saveFile(apply);
		if(i == 1){
			return 1;
		}else{
			throw new SQLException();
		}
	}

	@Override
	public String findFile(String id) {
		// TODO Auto-generated method stub
		String filename = "";
		filename = applyDao.getFilename(id);
		return filename;
	}

}
