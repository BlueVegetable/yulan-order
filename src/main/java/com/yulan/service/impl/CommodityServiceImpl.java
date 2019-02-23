package com.yulan.service.impl;

import com.yulan.dao.CommodityDao;
import com.yulan.pojo.Commodity;
import com.yulan.service.CommodityService;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommodityServiceImpl implements CommodityService {

	@Autowired
	private CommodityDao commodityDao;

	@Override
	public boolean addCommodity(Commodity commodity) {
		commodity.setId(System.currentTimeMillis()+ StringUtil.createStringID());
		return commodityDao.addCommodity(commodity)>0;
	}

	@Override
	public boolean deleteCommodityByID(String commodityID) {
		return commodityDao.deleteCommodityByID(commodityID)>0;
	}

	@Override
	public Commodity getCommodityByID(String commodityID) {
		return commodityDao.getCommodityByID(commodityID);
	}

	@Override
	public boolean updateCommodity(Commodity commodity) {
		return commodityDao.updateCommodity(commodity)>0;
	}

}