package com.clothes.service.lc.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clothes.dao.lc.KctjDao;
import com.clothes.service.lc.KctjService;

@Service
public class KctjServiceImp implements KctjService{
	@Autowired
	private KctjDao kctjDao;

	@Override
	public List<Map<String, Object>> getAllKctj() {
		return kctjDao.getAllKctj();
	}

}
