package com.yulan.service;

import com.yulan.pojo.CommodityOrder;

import java.util.List;
import java.util.Map;

public interface CommodityOrderService {

    /**
     * 将购物车的信息转移到订单
     * @param cartItemIDs
     * @param lineNos
     * @return
     */
    List<String> submitCommodityOrder(List<String> cartItemIDs, Map<String,Integer> lineNos);

    /**
     * 通过订单号和行号获取一套窗帘
     * @param orderNo
     * @param lineNo
     * @return
     */
    List<CommodityOrder> getCommodityOrders(String orderNo,Integer lineNo);

    /**
     * 修改orderItemID的orderNo
     * @param orderItemID
     * @param orderNo
     * @return
     */
    boolean addOrderNoByOrderItemIDs(String orderItemID,String orderNo);

}