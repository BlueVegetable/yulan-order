package com.yulan.service;

import java.util.List;
import java.util.Map;

public interface CommodityOrderService {

    List<String> submitCommodityOrder(List<String> cartItemIDs, Map<String,Integer> lineNos);

    boolean addOrderNoByOrderItemIDs(String orderItemID,String orderNo);

}