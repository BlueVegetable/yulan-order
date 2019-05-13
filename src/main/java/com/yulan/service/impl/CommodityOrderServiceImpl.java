package com.yulan.service.impl;

import com.yulan.dao.CommodityOrderDao;
import com.yulan.dao.CurtainCommodityDao;
import com.yulan.pojo.Commodity;
import com.yulan.pojo.CommodityOrder;
import com.yulan.pojo.CurtainCommodity;
import com.yulan.service.CommodityOrderService;
import com.yulan.utils.StringUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CommodityOrderServiceImpl implements CommodityOrderService {
    @Autowired
    private CommodityOrderDao commodityOrderDao;
    @Autowired
    private CurtainCommodityDao curtainCommodityDao;
    @Override
    public List<String> submitCommodityOrder(List<String> cartItemIDs, Map<String,Integer> lineNos) {
        List<String> orderItemIDs = new ArrayList<>();
        for (String cartItemID:cartItemIDs) {
            int orderNumber = 1;
            List<CommodityOrder> commodityOrders = new ArrayList<>();
            List<Commodity> curtainCommodities = curtainCommodityDao.getCommoditiesByCartItemID(cartItemID);
            for (Commodity commodity:curtainCommodities) {
                JSONObject jsonObject = JSONObject.fromObject(commodity);
                CommodityOrder commodityOrder = (CommodityOrder) JSONObject.toBean(jsonObject,CommodityOrder.class);
                commodityOrder.setOrderItemId(commodity.getCartItemId());
                commodityOrder.setSaveTime(new Timestamp(System.currentTimeMillis()));
                commodityOrder.setOrderItemNumber(orderNumber);
                CurtainCommodity curtainCommodity = (CurtainCommodity) commodity;
                String curtainPartName = curtainCommodity.getCurtainPartName();
                curtainPartName = StringUtil.GBKToUTF8(curtainPartName);
                switch (curtainPartName) {
                    case "帘头":commodityOrder.setCurtainPartName("lt");break;
                    case "帘身":commodityOrder.setCurtainPartName("ls");break;
                    case "纱":commodityOrder.setCurtainPartName("sha");break;
                    case "配件":commodityOrder.setCurtainPartName("pjb");break;
                    case "帘身配布":commodityOrder.setCurtainPartName("lspb");break;
                    default:commodityOrder.setCurtainPartName("other");break;
                }
                commodityOrder.setLineNo(lineNos.get(cartItemID));
                commodityOrder.setId(System.currentTimeMillis()+StringUtil.createStringID());
                orderNumber++;
                commodityOrders.add(commodityOrder);
            }
            if(curtainCommodities.size()!=0 && commodityOrderDao.addCommodityOrders(commodityOrders) > 0) {
                orderItemIDs.add(cartItemID);
            }
        }
        return orderItemIDs;
    }

    @Override
    public boolean addOrderNoByOrderItemIDs(String orderItemID, String orderNo) {
        return commodityOrderDao.addOrderNoByOrderItemIDs(orderItemID, orderNo) > 0;
    }

    @Override
    public List<CommodityOrder> getCommodityOrders(String orderNo, Integer lineNo) {
        return commodityOrderDao.getCommodityOrders(orderNo, lineNo);
    }
}
