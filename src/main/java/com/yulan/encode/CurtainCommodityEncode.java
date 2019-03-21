package com.yulan.encode;

import com.yulan.dao.CurtainCommodityDao;
import com.yulan.pojo.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurtainCommodityEncode {

    @Autowired
    private CurtainCommodityDao curtainCommodityDao;

    public boolean addCommodity(Commodity commodity) {
        return curtainCommodityDao.addCommodity(commodity)>0;
    }

    public Commodity getCommodityAppoint(String activityID, String itemID, String cartItemID) {
        Commodity commodity = curtainCommodityDao.getCommodityAppoint(activityID, itemID, cartItemID);
        return commodity;
    }

    public boolean deleteCommodityByID(String commodityID) {
        return curtainCommodityDao.deleteCommodityByID(commodityID)>0;
    }

    public boolean deleteCommoditiesByCartItemID(String cartItemID) {
        return curtainCommodityDao.deleteCommoditiesByCartItemID(cartItemID) > 0;
    }

    public Commodity getCommodityByID(String commodityID) {
        return curtainCommodityDao.getCommodityByID(commodityID);
    }

    public List<Commodity> getCommoditiesByCartItemID(String cartItemID) {
        return curtainCommodityDao.getCommoditiesByCartItemID(cartItemID);
    }

    public long countByCartItemID(String cartItemID) {
        return curtainCommodityDao.countByCartItemID(cartItemID);
    }

    public boolean updateCommodity(Commodity commodity) {
        return curtainCommodityDao.updateCommodity(commodity)>0;
    }

}
