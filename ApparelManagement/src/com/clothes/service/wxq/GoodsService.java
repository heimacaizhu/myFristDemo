package com.clothes.service.wxq;

import java.util.List;
import java.util.Map;

/**
 * 产品服务层接口
 * @author 吴晓强
 *
 */
public interface GoodsService {
	
	/**
	 * 查询所有的产品
	 * @return
	 */
	public List<Map<String,Object>>  findGoods();
}
