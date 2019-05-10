package com.yulan.service.impl;

import com.yulan.encode.CurtainCommodityEncode;
import com.yulan.pojo.Commodity;
import com.yulan.service.CommodityService;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("curtainCommodityService")
public class CurtainCommodityServiceImpl implements CommodityService {

	@Autowired
	private CurtainCommodityEncode curtainCommodityEncode;

	@Override
	public boolean addCommodity(Commodity commodity) {
		commodity.setId(System.currentTimeMillis()+ StringUtil.createStringID());
		return curtainCommodityEncode.addCommodity(commodity);
	}

	@Override
	public Commodity getCommodityAppoint(String activityID, String itemID, String cartItemID) {
		Commodity commodity = curtainCommodityEncode.getCommodityAppoint(activityID, itemID, cartItemID);
		return commodity;
	}

	@Override
	public boolean deleteCommodityByID(String commodityID) {
		return curtainCommodityEncode.deleteCommodityByID(commodityID);
	}

	@Override
	public boolean deleteCommoditiesByCartItemID(String cartItemID) {
		return curtainCommodityEncode.deleteCommoditiesByCartItemID(cartItemID);
	}

	@Override
	public Commodity getCommodityByID(String commodityID) {
		return curtainCommodityEncode.getCommodityByID(commodityID);
	}

	@Override
	public long countByCartItemID(String cartItemID) {
		return curtainCommodityEncode.countByCartItemID(cartItemID);
	}

	@Override
	public boolean updateCommodity(Commodity commodity) {
		return curtainCommodityEncode.updateCommodity(commodity);
	}

	@Override
	public boolean alterCommodityStatus(String commodityID, int status) {
		return curtainCommodityEncode.alterCommodityStatus(commodityID,status);
	}

	@Override
	public int alterCommoditiesStatus(List<String> commodityIDs, int status) {
		return 0;
	}

	@Override
	public int alterCommoditiesStatusByCartItemId(List<String> cartItemIDs, int status) {
		return 0;
	}
}