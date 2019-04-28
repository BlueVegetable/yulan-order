package com.yulan.dao;

import com.yulan.pojo.CommodityOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityOrderDao {

	int addCommodityOrder(CommodityOrder commodityOrder);

	int addCommodityOrders(@Param("commodityOrders") List<CommodityOrder> commodityOrders);

	int deleteCommodityOrdersByOrderItemID(String orderItemID);

	List<CommodityOrder> getCommodityOrderByOrderItemID(String orderItemID);

	int addOrderNoByOrderItemIDs(@Param("orderItemID") String orderItemID,@Param("orderNo") String orderNo);

}