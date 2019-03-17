package com.yulan.dao;

import com.yulan.pojo.Commodity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CurtainCommodityDao {

	int addCommodity(Commodity commodity);

	int deleteCommodityByID(String commodityID);

	int deleteCommoditiesByCartItemID(String cartItemID);

	Commodity getCommodityByID(String commodityID);

	Commodity getCommodityAppoint(@Param("activityID") String activityID, @Param("itemID") String itemID,
                                  @Param("cartItemID") String cartItemID);

	long countByCartItemID(String cartItemID);

	List<Commodity> getCommoditiesByCartItemID(String cartItemID);

	int updateCommodity(Commodity commodity);

}