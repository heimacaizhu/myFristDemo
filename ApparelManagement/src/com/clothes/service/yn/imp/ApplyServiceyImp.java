package com.clothes.service.yn.imp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clothes.dao.yn.ApplyDaoy;
import com.clothes.dao.yn.CustomerDaoy;
import com.clothes.dao.yn.EmployDaoy;
import com.clothes.dao.yn.GoodsDaoy;
import com.clothes.pojo.Apply;
import com.clothes.pojo.Applyitem;
import com.clothes.pojo.Customer;
import com.clothes.pojo.Employee;
import com.clothes.pojo.Goodstitem;
import com.clothes.service.yn.ApplyServicey;
import com.clothes.utils.DataGride;
import com.clothes.utils.PageUtil;

/*
 *作者：杨聂；    
 *邮箱：1024938222@qq.com；      
 *时间：2016年12月12日下午10:38:25；      
 */
@Service
public class ApplyServiceyImp implements ApplyServicey {
	@Autowired
	private EmployDaoy ed;
	@Autowired
	private GoodsDaoy gd;
	@Autowired
	private ApplyDaoy ad;
	@Autowired
	private CustomerDaoy cd;

	@Override
	public DataGride getAllApply() {
		List<Apply> allApply = ad.getAllApply();
		for(Apply apply:allApply){
			Employee applicant = apply.getApplicant();
			Employee deal_person = apply.getDeal_person();
			Customer customer = apply.getCustomer();
			apply.setApplicant(ed.getEmpById((applicant!=null?applicant.getEmId():0)+""));
			apply.setDeal_person(ed.getEmpById((deal_person!=null?deal_person.getEmId():0)+""));
			apply.setCustomer(cd.getCustomerById((customer!=null?customer.getCuId():0)+""));
			List<Applyitem> list = apply.getList();
			for(Applyitem li:list){
				Goodstitem goodsItem = li.getGoodsItem();
				li.setGoodsItem(gd.getGoodsByGid((goodsItem!=null?goodsItem.getGiId():0)+""));
			}
		}
		return PageUtil.convertToResult(allApply);
	}

	@Override
	public int updateDealApply(String state, String id,String eid) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Map<String, String> pMap=new HashMap<String, String>();
		pMap.put("state", state);
		pMap.put("id", id);
		pMap.put("dp", eid);
		pMap.put("ddate", format.format(date));
		return ad.updateDealApply(pMap);
	}

	@Override
	public DataGride getAllDealApply() {
		List<Apply> allApply = ad.getAllDealApply();
		for(Apply apply:allApply){
			Employee applicant = apply.getApplicant();
			Employee deal_person = apply.getDeal_person();
			Customer customer = apply.getCustomer();
			apply.setApplicant(ed.getEmpById((applicant!=null?applicant.getEmId():0)+""));
			apply.setDeal_person(ed.getEmpById((deal_person!=null?deal_person.getEmId():0)+""));
			apply.setCustomer(cd.getCustomerById((customer!=null?customer.getCuId():0)+""));
			List<Applyitem> list = apply.getList();
			for(Applyitem li:list){
				Goodstitem goodsItem = li.getGoodsItem();
				li.setGoodsItem(gd.getGoodsByGid((goodsItem!=null?goodsItem.getGiId():0)+""));
			}
		}
		return PageUtil.convertToResult(allApply);
	}

	@Override
	public int delDealApply(String id) {
		Map<String, String> pMap=new HashMap<String, String>();
		pMap.put("id", id);
		return ad.delDealApply(pMap);
	}

}
