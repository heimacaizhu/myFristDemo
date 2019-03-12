package com.clothes.service.wxq;


import com.clothes.pojo.Apply;
import com.clothes.utils.DataGride;

/**
 * 申请计划服务层接口
 * @author 吴晓强
 *
 */
public interface ApplyService {
	
	/**
	 * 添加一个申请计划
	 * @param apply
	 * @return 添加成功返回1  否则抛出异常
	 * @throws Exception
	 */
	public int addApply(Apply apply) throws Exception;
	
	/**
	 * 根据条件查询计划
	 * @param apply
	 * @return
	 */
	public DataGride findApply(Apply apply);
	
	/**
	 * 保存文件附件
	 * @param apply
	 * @return 保存成功返回1
	 * @throws Exception
	 */
	public int updateFile(Apply apply) throws Exception;
	
	/**
	 * 获取计划的附件
	 * @param id 计划id
	 * @return
	 */
	public String findFile(String id);
}
