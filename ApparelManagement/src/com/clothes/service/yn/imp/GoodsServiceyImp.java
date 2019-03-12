package com.clothes.service.yn.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clothes.dao.yn.GoodsDaoy;
import com.clothes.pojo.Goods;
import com.clothes.pojo.Goodsimgs;
import com.clothes.pojo.Goodstitem;
import com.clothes.service.yn.GoodsServicey;
import com.clothes.utils.DataGride;
import com.clothes.utils.PageUtil;

/*
 *作者：杨聂；    
 *邮箱：1024938222@qq.com；      
 *时间：2016年12月8日下午12:29:33；      
 */
@Service
public class GoodsServiceyImp implements GoodsServicey {
	@Autowired
	private GoodsDaoy gd;

	@Override
	public DataGride getAllgoods(String type) {
		List<Goods> goods = gd.getAllgoods(type);
		return PageUtil.convertToResult(goods);
	}

	@Override
	public int addGoods(Map<String, Object> goods) {
		return gd.addGoods(goods);
	}

	@Override
	public int addGoodsItem(Map<String, Object> goods) {
		return gd.addGoodsItem(goods);
	}

	@Override
	public int addGoodsImg(Map<String, Object> img) {
		return gd.addGoodsImg(img);
	}

	@Override
	public int updateGoods(Map<String, Object> goods) {
		return gd.updateGoods(goods);
	}

	@Override
	public Goods getGoodsById(String id) {
		return gd.getGoodsById(id);
	}

	@Override
	public int updateGoodsItem(Map<String, Object> goods) {
		return gd.updateGoodsItem(goods);
	}

	@Override
	public Goods getGoodsItemById(String id) {
		return gd.getGoodsItemById(id);
	}

	@Override
	public int delGoodsItem(String id) {
		return gd.delGoodsItem(id);
	}

	@Override
	public int delGoods(String id) throws Exception{
		List<Goodstitem> item = gd.getGoodsById(id).getList();
		if(item.size()>0){
			List<Integer> ids=new ArrayList<Integer>();
			for(Goodstitem it:item){
				ids.add(it.getGiId());
			}
			if((gd.delGoodsImg(ids)+gd.delGoodsItems(id))>0){
				return 1;
			}else{
				throw new Exception();
			}
			
		}else{
			return gd.delGoods(id);
		}
	}

	@Override
	public DataGride getGoodsByLike(Map<String, Object> goods) {
		List<Goods> g = gd.getGoodsByLike(goods);
		return PageUtil.convertToResult(g);
	}

	@Override
	public DataGride getGoodsItems(String type) {
		List<Goods> goods = gd.getGoodsItems(type);
		return PageUtil.convertToResult(goods);
	}

	@Override
	public List<Goodsimgs> getGoodsImgById(String id) {
		return gd.getGoodsImgById(id);
	}

	@Override
	public int delGoodsImgById(String id) {
		return gd.delGoodsImgById(id);
	}

}
