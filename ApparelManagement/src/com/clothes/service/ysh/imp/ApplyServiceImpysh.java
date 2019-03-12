package com.clothes.service.ysh.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clothes.dao.ysh.ApplyDaoysh;
import com.clothes.pojo.Apply;
import com.clothes.service.ysh.ApplyServiceysh;
import com.clothes.utils.DataGride;
import com.clothes.utils.PageUtil;
import com.github.pagehelper.PageHelper;

/**
 * 申请表操作-Service实现层
 * 作者:杨舒航;
 * 时间:2016年12月7日下午3:50:02;
 * 邮箱：513658133@qq.com;
 */
@Service
public class ApplyServiceImpysh implements ApplyServiceysh {
	@Autowired
	private ApplyDaoysh applyDao;

	/** 查询所有申请表-Service实现层 */
	@Override
	public DataGride showAllApply(Map<String, Object> map,int page,int rows) {
		PageHelper.startPage(page, rows);
		List<Map<String, Object>> list = applyDao.showAllApply(map);
		return PageUtil.convertToResult(list);
	}

	@Override
	public List<Map<String, Object>> showAllApplys() {
		// TODO Auto-generated method stub
		return applyDao.showAllApplys();
	}

	/** 查询单个详细申请表-Service实现层 */
	@Override
	public List<Map<String, Object>> showOneApply(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return applyDao.showOneApply(map);
	}
	/** 处理申请表-Service实现层 */
	@Override
	public int updateOneApply(Map<String, Object> map) {
		
		return applyDao.updateOneApply(map);
	}

	/** 显示商品信息——Service实现层 */
	@Override
	public List<Map<String, Object>> showGoods() {
		// TODO Auto-generated method stub
		return applyDao.showGoods();
	}

	@Override
	public int addApply(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return applyDao.addApply(map);
	}

	@Override
	public int addApplyitem(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return applyDao.addApplyitem(map);
	}

	@Override
	public List<Map<String, Object>> countCUtype() {
		// TODO Auto-generated method stub
		return applyDao.countCUtype();
	}

	@Override
	public List<Map<String, Object>> countCUname() {
		// TODO Auto-generated method stub
		return applyDao.countCUname();
	}

	@Override
	public List<Map<String, Object>> countApplicant() {
		// TODO Auto-generated method stub
		return applyDao.countApplicant();
	}

	@Override
	public List<Map<String, Object>> countDealPerson() {
		// TODO Auto-generated method stub
		return applyDao.countDealPerson();
	}

}
