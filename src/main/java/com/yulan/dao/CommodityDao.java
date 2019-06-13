package com.yulan.dao;

import com.yulan.pojo.Commodity;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface CommodityDao {

	int addCommodity(Commodity commodity);

	int deleteCommodityByID(String commodityID);

	int deleteCommoditiesByCartItemID(String cartItemID);

	Commodity getCommodityByID(String commodityID);

	Commodity getCommodityAppoint(@Param("activityID") String activityID,@Param("itemID")String itemID,
								  @Param("cartItemID") String cartItemID);

	long countByCartItemID(String cartItemID);

	List<Commodity> getCommoditiesByCartItemID(String cartItemID);

	int updateCommodity(Commodity commodity);

	int alterCommodityStatus(@Param("commodityID") String commodityID,@Param("status") int status);

	int alterCommoditiesStatus(@Param("commodityIDs") List<String> commodityIDs,@Param("status") int status);

	int alterCommoditiesStatusByCartItemId(@Param("cartItemIDs") List<String> cartItemIDs, @Param("status") int status);

	int alterCommodityPrice(@Param("commodityID") String commodityID, @Param("price") BigDecimal price);

}