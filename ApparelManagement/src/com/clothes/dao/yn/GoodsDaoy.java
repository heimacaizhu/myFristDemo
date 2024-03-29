package com.clothes.dao.yn;

import java.util.List;
import java.util.Map;

import com.clothes.pojo.Goods;
import com.clothes.pojo.Goodsimgs;
import com.clothes.pojo.Goodstitem;

/*
 *作者：杨聂；    
 *邮箱：1024938222@qq.com；      
 *时间：2016年12月8日上午11:25:43；      
 *商品数据层
 */
public interface GoodsDaoy {
	/**
	 * 获取所有的物品
	 * @param type 物品类型
	 * @return  返回物品结合
	 */
	public List<Goods> getAllgoods(String type);
	
	/**
	 * 获取指定条目
	 * @param id 条目id
	 * @return  返回物品
	 */
	public Goodstitem getGoodsByGid(String id);
	
	/**
	 * 获取所有的物品条目
	 * @param type 物品类型
	 * @return  返回物品结合
	 */
	public List<Goods> getGoodsItems(String type);
	
	/**
	 * 根据id获取物品
	 * @param id 物品id
	 * @return  返回物品
	 */
	public Goods getGoodsById(String id);
	
	/**
	 * 根据id获取物品条目
	 * @param id 物品条目id
	 * @return  返回物品条目
	 */
	public Goods getGoodsItemById(String id);
	
	/**
	 * 增加物品
	 * @param goods 物品信息
	 * @return 返回物品条数
	 */
	public int addGoods(Map<String, Object> goods);
	
	/**
	 * 增加物品条目
	 * @param goods 物品条目信息
	 * @return 返回物品条数
	 */
	public int addGoodsItem(Map<String, Object> goods);
	
	/**
	 * 为物品增加图片
	 * @param img 图片信息
	 * @return 图片插入条数
	 */
	public int addGoodsImg(Map<String, Object> img);
	
	/**
	 * 修改物品
	 * @param goods 物品信息
	 * @return 返回修改物品条数
	 */
	public int updateGoods(Map<String, Object> goods);
	
	/**
	 * 修改物品条目
	 * @param goods 物品条目信息
	 * @return 返回修改物品条目条数
	 */
	public int updateGoodsItem(Map<String, Object> goods);
	
	/**
	 * 删除指定条目
	 * @param id 条目id
	 * @return 删除条数
	 */
	public int delGoodsItem(String id);
	
	/**
	 * 删除指定物品的所有条目
	 * @param id 物品id
	 * @return 删除条数
	 */
	public int delGoodsItems(String id);
	
	/**
	 * 删除指定物品
	 * @param id 物品id
	 * @return 删除条数
	 */
	public int delGoods(String id);
	
	/**
	 * 删除指定物品的图片
	 * @param id 条目id集合
	 * @return 删除条数
	 */
	public int delGoodsImg(List<Integer> ids);
	
	/**
	 * 删除指定的图片
	 * @param id 图片id集合
	 * @return 删除条数
	 */
	public int delGoodsImgById(String id);
	
	/**
	 * 模糊查询产品
	 * @param goods 物品信息
	 * @return 物品集合
	 */
	public List<Goods> getGoodsByLike(Map<String, Object> goods);
	
	/**
	 * 通过物品id查找该物品图片
	 * @param id 物品id
	 * @return 物品图片集合
	 */
	public List<Goodsimgs> getGoodsImgById(String id);
	
}
