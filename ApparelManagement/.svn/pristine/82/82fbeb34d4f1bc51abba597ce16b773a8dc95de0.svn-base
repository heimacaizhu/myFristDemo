package com.clothes.service.wxq;

import java.util.List;
import java.util.Map;

import com.clothes.pojo.Region;
import com.clothes.utils.DataGride;

/**
 * 区域管理服务层接口
 * @author 吴晓强
 *
 */
public interface RegionService {
	
	/**
	 * 添加一个区域
	 * @param region 要添加的区域的信息实体
	 * @return  添加成功返回1   区域已经存在返回2  添加失败返回3
	 */
	public int addRegion(Region region);
	
	/**
	 * 根据条件查询区域,不分页
	 * @param region 查询区域的条件
	 * @return
	 */
	public List<Map<String,Object>> findRegion(Region region);
	
	/**
	 * 按照条件分页查询数据
	 * @param region
	 * @return
	 */
	public DataGride findRegionPage(Region region);
	
	/**
	 * 更新区域内容
	 * @param region
	 * @return 1  更新成功  2  该区域已经存在   3 更新失败
	 */
	public int updateRegion(Region region);
	
	/**
	 * 删除区域
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public int deleteRegions(String ids) throws Exception;
}
