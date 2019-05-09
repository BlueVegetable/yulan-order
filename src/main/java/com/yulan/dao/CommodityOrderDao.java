package com.yulan.dao;

import com.yulan.pojo.CommodityOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityOrderDao {

	int addCommodityOrder(CommodityOrder commodityOrder);

	int addCommodityOrders(@Param("commodityOrders") List<CommodityOrder> commodityOrders);

	int deleteCommodityOrdersByOrderItemID(String orderItemID);

	List<CommodityOrder> getCommodityOrders(@Param("orderNo") String orderNo,@Param("lineNo") Integer lineNo);

	List<CommodityOrder> getCommodityOrderByOrderItemID(String orderItemID);

	int addOrderNoByOrderItemIDs(@Param("orderItemID") String orderItemID,@Param("orderNo") String orderNo);

	/**
	 * 修改全部
	 * @param commodityOrder
	 * @return
	 */
	Boolean updateCommodityOrder(CommodityOrder commodityOrder);


	/**
	 * 修改兰居审核意见
	 * @param commodityOrder
	 * @return
	 */
	Boolean updateCommodityOrderSug(CommodityOrder commodityOrder);


}