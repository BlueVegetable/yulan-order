package com.yulan.service;

import java.util.List;

public interface CommodityOrderService {

    List<String> submitCommodityOrder(List<String> cartItemIDs);

    boolean addOrderNoByOrderItemIDs(String orderItemID,String orderNo);

}