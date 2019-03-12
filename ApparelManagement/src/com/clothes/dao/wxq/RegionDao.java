package com.clothes.dao.wxq;

import java.util.List;
import java.util.Map;

import com.clothes.pojo.Region;

/**
 * 区域管理数据库接口
 * @author 吴晓强
 *
 */
public interface RegionDao {
	/**
	 * 添加区域
	 * @param region 要添加的区域对象
	 * @return 添加成功返回1
	 */
	public int addRegion(Region region);
	
	/**
	 * 删除区域
	 * @param ids 要删除的区域的所有id，id之间用,分割
	 * @return 删除成功返回ids的长度
	 */
	public int deleteRegion(String[] ids);
	
	/**
	 * 更新区域信息
	 * @param region 要更新的区域的对象
	 * @return 更新成功返回1
	 */
	public int updateRegion(Region region);
	
	/**
	 * 根据条件查找区域内容
	 * @param region 要查找的区域条件
	 * @return 符合条件的区域
	 */
	public List<Map<String,Object>> findRegions(Region region);
	
	/**
	 * 按条件查找区域  模糊查询
	 * @param region
	 * @return
	 */
	public List<Map<String,Object>> findRegionLike(Region region);
}
