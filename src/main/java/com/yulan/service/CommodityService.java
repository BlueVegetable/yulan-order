package com.yulan.service;

import com.yulan.pojo.Commodity;

public interface CommodityService {

	boolean addCommodity(Commodity commodity);

	boolean deleteCommodityByID(String commodityID);

	Commodity getCommodityByID(String commodityID);

	boolean updateCommodity(Commodity commodity);

}