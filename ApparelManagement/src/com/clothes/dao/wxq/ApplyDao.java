package com.clothes.dao.wxq;

import java.util.List;
import java.util.Map;

import com.clothes.pojo.Apply;

/**
 * 计划申请数据库操作层接口
 * @author 吴晓强
 *
 */
public interface ApplyDao {
	
	/**
	 * 添加计划
	 * @param ap
	 * @return
	 */
	public int addApply(Apply ap);
	
	/**
	 * 根据条件查询计划
	 * @param apply
	 * @return
	 */
	public List<Map<String,Object>> findApplys(Apply apply);
	
	/**
	 * 保存文件附件
	 * @param apply
	 * @return
	 */
	public int saveFile(Apply apply);
	
	/**
	 * 获取计划的附件名称
	 * @param id
	 * @return
	 */
	public String getFilename(String id);
}
