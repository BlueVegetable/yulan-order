package com.yulan.service.impl;

import com.yulan.dao.CommodityDao;
import com.yulan.encode.CommodityEncode;
import com.yulan.pojo.Commodity;
import com.yulan.service.CommodityService;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service("commodityService")
public class CommodityServiceImpl implements CommodityService {

	@Autowired
	private CommodityEncode commodityEncode;
	@Autowired
	private CommodityDao commodityDao;

	@Override
	public boolean addCommodity(Commodity commodity) {
		commodity.setId(System.currentTimeMillis() + StringUtil.createStringID());
		return commodityEncode.addCommodity(commodity);
	}

	@Override
	public boolean addCommodityWithID(Commodity commodity) {
		return commodityEncode.addCommodity(commodity);
	}

	@Override
	public Commodity getCommodityAppoint(String activityID, String itemID, String cartItemID) {
		Commodity commodity = commodityEncode.getCommodityAppoint(activityID, itemID, cartItemID);
		return commodity;
	}

	@Override
	public boolean deleteCommodityByID(String commodityID) {
		return commodityEncode.deleteCommodityByID(commodityID);
	}

	@Override
	public boolean deleteCommoditiesByCartItemID(String cartItemID) {
		return commodityEncode.deleteCommoditiesByCartItemID(cartItemID);
	}

	@Override
	public Commodity getCommodityByID(String commodityID) {
		return commodityEncode.getCommodityByID(commodityID);
	}

	@Override
	public long countByCartItemID(String cartItemID) {
		return commodityEncode.countByCartItemID(cartItemID);
	}

	@Override
	public List<Commodity> getCommoditiesByCIDWithoutGroupCE(String CID) {
		return commodityDao.getCommoditiesByCIDWithoutGroupCE(CID);
	}

	@Override
	public boolean updateCommodity(Commodity commodity) {
		return commodityEncode.updateCommodity(commodity);
	}

	@Override
	public boolean alterCommodityStatus(String commodityID, int status) {
		return commodityEncode.alterCommodityStatus(commodityID, status);
	}

	@Override
	public int alterCommoditiesStatus(List<String> commodityIDs, int status) {
		return commodityEncode.alterCommoditiesStatus(commodityIDs, status);
	}

	@Override
	public int alterCommoditiesStatusByCartItemId(List<String> cartItemIDs, int status) {
		return commodityEncode.alterCommoditiesStatusByCartItemId(cartItemIDs, status);
	}

	@Override
	public boolean alterCommodityPrice(String commodityID, BigDecimal price) {
		return commodityEncode.alterCommodityPrice(commodityID, price);
	}
}