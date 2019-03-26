package com.yulan.encode;

import com.yulan.dao.CommodityDao;
import com.yulan.pojo.Commodity;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommodityEncode {

    @Autowired
    private CommodityDao commodityDao;

    public boolean addCommodity(Commodity commodity) {
        String note = commodity.getNote();
        String unit = commodity.getUnit();
        commodity.setNote(StringUtil.UTF8ToGBK(note));
        commodity.setUnit(unit!=null?StringUtil.UTF8ToGBK(unit):null);
        return commodityDao.addCommodity(commodity)>0;
    }

    public Commodity getCommodityAppoint(String activityID, String itemID, String cartItemID) {
        Commodity commodity = commodityDao.getCommodityAppoint(activityID, itemID, cartItemID);
        if(commodity!=null) {
            commodity.setNote(StringUtil.GBKToUTF8(commodity.getNote()));
            commodity.setUnit(StringUtil.GBKToUTF8(commodity.getUnit()));
        }
        return commodity;
    }

    public boolean deleteCommodityByID(String commodityID) {
        return commodityDao.deleteCommodityByID(commodityID)>0;
    }

    public boolean deleteCommoditiesByCartItemID(String cartItemID) {
        return commodityDao.deleteCommoditiesByCartItemID(cartItemID) > 0;
    }

    public Commodity getCommodityByID(String commodityID) {
        Commodity commodity = commodityDao.getCommodityByID(commodityID);
        commodity.setUnit(StringUtil.GBKToUTF8(commodity.getUnit()));
        commodity.setNote(StringUtil.GBKToUTF8(commodity.getNote()));
        return commodity;
    }

    public long countByCartItemID(String cartItemID) {
        return commodityDao.countByCartItemID(cartItemID);
    }

    public boolean updateCommodity(Commodity commodity) {
        String note = commodity.getNote();
        String unit = commodity.getUnit();
        if(note != null) {
            commodity.setNote(StringUtil.UTF8ToGBK(commodity.getNote()));
        }
        commodity.setUnit(StringUtil.UTF8ToGBK(unit));
        return commodityDao.updateCommodity(commodity)>0;
    }

    public boolean alterCommodityStatus(String commodityID,int status) {
        return commodityDao.alterCommodityStatus(commodityID, status) > 0;
    }

}
