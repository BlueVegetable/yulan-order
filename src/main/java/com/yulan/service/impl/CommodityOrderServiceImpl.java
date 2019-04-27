package com.yulan.service.impl;

import com.yulan.dao.CommodityOrderDao;
import com.yulan.dao.CurtainCommodityDao;
import com.yulan.pojo.Commodity;
import com.yulan.pojo.CommodityOrder;
import com.yulan.service.CommodityOrderService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommodityOrderServiceImpl implements CommodityOrderService {
    @Autowired
    private CommodityOrderDao commodityOrderDao;
    @Autowired
    private CurtainCommodityDao curtainCommodityDao;
    @Override
    public List<String> submitCommodityOrder(List<String> cartItemIDs) {
        List<String> orderItemIDs = new ArrayList<>();
        for (String cartItemID:cartItemIDs) {
            int orderNumber = 1;
            List<CommodityOrder> commodityOrders = new ArrayList<>();
            List<Commodity> curtainCommodities = curtainCommodityDao.getCommoditiesByCartItemID(cartItemID);
            for (Commodity curtainCommodity:curtainCommodities) {
                JSONObject jsonObject = JSONObject.fromObject(curtainCommodity);
                CommodityOrder commodityOrder = (CommodityOrder) JSONObject.toBean(jsonObject,CommodityOrder.class);
                commodityOrder.setOrderItemId(curtainCommodity.getCartItemId());
                commodityOrder.setSaveTime(new Timestamp(System.currentTimeMillis()));
                commodityOrder.setOrderItemNumber(orderNumber);
                orderNumber++;
                commodityOrders.add(commodityOrder);
            }
            if(curtainCommodities.size()!=0 && commodityOrderDao.addCommodityOrders(commodityOrders) > 0) {
                orderItemIDs.add(cartItemID);
            }
        }
        return orderItemIDs;
    }
}
