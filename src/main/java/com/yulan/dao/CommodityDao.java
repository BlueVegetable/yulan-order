package com.yulan.dao;

import com.yulan.pojo.Commodity;

import java.util.List;

public interface CommodityDao {

	int addCommodity(Commodity commodity);

	int deleteCommodityByID(String commodityID);

	Commodity getCommodityByID(String commodityID);

	List<Commodity> getCommoditiesByCartItemID(String cartItemID);

	int updateCommodity(Commodity commodity);

}