package com.clothes.service.wxq.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clothes.dao.wxq.GoodsDao;
import com.clothes.service.wxq.GoodsService;

/**
 * 产品服务层实现类
 * @author 吴晓强
 *
 */
@Service
public class GoodsServiceImp implements GoodsService{

	@Autowired
	private GoodsDao goodsDao;
	
	@Override
	public List<Map<String, Object>> findGoods() {
		// TODO Auto-generated method stub
		List<Map<String,Object>>  list = new ArrayList<Map<String,Object>>();
		list = goodsDao.findGoods();
		return list;
	}
	
}
