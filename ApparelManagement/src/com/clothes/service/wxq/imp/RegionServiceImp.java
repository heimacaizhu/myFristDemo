package com.clothes.service.wxq.imp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clothes.dao.wxq.RegionDao;
import com.clothes.pojo.Region;
import com.clothes.service.wxq.RegionService;
import com.clothes.utils.DataGride;
import com.clothes.utils.PageUtil;

/**
 * 区域管理服务层实现类
 * @author 吴晓强
 *
 */
@Service
public class RegionServiceImp implements RegionService{

	@Autowired
	private RegionDao regionDao;
	@Override
	public int addRegion(Region region) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = regionDao.findRegions(region);
		if(list.size()>0){
			//该区域已经存在
			return 2;
		}
		int i = regionDao.addRegion(region);
		if(i==1){
			//添加成功
			return 1;
		}else{
			//添加失败
			return 3;
		}
	}

	@Override
	public List<Map<String, Object>> findRegion(Region region) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = regionDao.findRegionLike(region);
		return list;
	}

	@Override
	public DataGride findRegionPage(Region region) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list = regionDao.findRegionLike(region);
		DataGride dg = PageUtil.convertToResult(list);
		return dg;
	}

	@Override
	public int updateRegion(Region region) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = regionDao.findRegions(region);
		if(list.size()>0){
			//该区域已经存在
			return 2;
		}
		int i = regionDao.updateRegion(region);
		if(i==1){
			return 1;
		}else{
			return 3;
		}
	}

	@Override
	public int deleteRegions(String ids) throws Exception {
		// TODO Auto-generated method stub
		String []idsTemp = ids.split(",");
		int size = idsTemp.length;
		int i = regionDao.deleteRegion(idsTemp);
		if(size == i){
			return 1;
		}else{
			throw new SQLException();
		}
	}
	
}
