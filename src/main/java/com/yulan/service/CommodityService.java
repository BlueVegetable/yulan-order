package com.yulan.service;

import com.yulan.pojo.Commodity;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;

public interface CommodityService {

	boolean addCommodity(Commodity commodity);

	boolean addCommodityWithID(Commodity commodity);

	boolean deleteCommodityByID(String commodityID);

	boolean deleteCommoditiesByCartItemID(String cartItemID);

	Commodity getCommodityByID(String commodityID);

	Commodity getCommodityAppoint(String activityID,String itemID,String cartItemID);

	List<Commodity> getCommoditiesByCIDWithoutGroupCE(String CID);

	long countByCartItemID(String cartItemID);

	boolean updateCommodity(Commodity commodity) throws UnsupportedEncodingException;

	boolean alterCommodityStatus(String commodityID,int status);

	int alterCommoditiesStatus(List<String> commodityIDs,int status);

	int alterCommoditiesStatusByCartItemId(List<String> cartItemIDs,int status);

	boolean alterCommodityPrice(String commodityID, BigDecimal price);

}