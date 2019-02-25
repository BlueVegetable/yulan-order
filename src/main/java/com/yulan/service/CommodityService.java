package com.yulan.service;

import com.yulan.pojo.Commodity;

public interface CommodityService {

	boolean addCommodity(Commodity commodity);

	boolean deleteCommodityByID(String commodityID);

	boolean deleteCommoditiesByCartItemID(String cartItemID);

	Commodity getCommodityByID(String commodityID);

	Commodity getCommodityAppoint(String activityID,String itemID,String cartItemID);

	long countByCartItemID(String cartItemID);

	boolean updateCommodity(Commodity commodity);

}