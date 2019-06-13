package com.yulan.encode;

import com.yulan.dao.CurtainCommodityDao;
import com.yulan.pojo.Commodity;
import com.yulan.pojo.CurtainCommodity;
import com.yulan.pojo.Item;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CurtainCommodityEncode {

    @Autowired
    private CurtainCommodityDao curtainCommodityDao;

    public boolean addCommodity(Commodity commodity) {
        CurtainCommodity curtainCommodity = (CurtainCommodity) commodity;
        curtainCommodity.setNote(StringUtil.UTF8ToGBK(curtainCommodity.getNote()));
        curtainCommodity.setUnit(StringUtil.UTF8ToGBK(curtainCommodity.getUnit()));
        curtainCommodity.setCurtainItemName(StringUtil.UTF8ToGBK(curtainCommodity.getCurtainItemName()));
        curtainCommodity.setCurtainPartName(StringUtil.UTF8ToGBK(curtainCommodity.getCurtainPartName()));
        curtainCommodity.setManufacturingInstructions(StringUtil.UTF8ToGBK(curtainCommodity.getManufacturingInstructions()));
        curtainCommodity.setIllustrate(StringUtil.UTF8ToGBK(curtainCommodity.getIllustrate()));
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
        List<Commodity> commodities = curtainCommodityDao.getCommoditiesByCartItemID(cartItemID);
        for (Commodity commodity:commodities) {
            CurtainCommodity curtainCommodity = (CurtainCommodity) commodity;
            curtainCommodity.setNote(StringUtil.GBKToUTF8(curtainCommodity.getNote()));
            curtainCommodity.setUnit(StringUtil.GBKToUTF8(curtainCommodity.getUnit()));
            curtainCommodity.setCurtainItemName(StringUtil.GBKToUTF8(curtainCommodity.getCurtainItemName()));
            curtainCommodity.setCurtainPartName(StringUtil.GBKToUTF8(curtainCommodity.getCurtainPartName()));
            curtainCommodity.setManufacturingInstructions(StringUtil.GBKToUTF8(curtainCommodity.getManufacturingInstructions()));
            curtainCommodity.setIllustrate(StringUtil.GBKToUTF8(curtainCommodity.getIllustrate()));
            Item item = commodity.getItem();
            if(item != null) {
                item.setRzGrade(StringUtil.GBKToUTF8(item.getRzGrade()));
            }
        }
        return commodities;
    }

    public long countByCartItemID(String cartItemID) {
        return curtainCommodityDao.countByCartItemID(cartItemID);
    }

    public boolean updateCommodity(Commodity commodity) {
        return curtainCommodityDao.updateCommodity(commodity)>0;
    }

    public boolean alterCommodityStatus(String commodityID,int status) {
        return curtainCommodityDao.alterCommodityStatus(commodityID, status) > 0;
    }

    public boolean alterCommodityPrice(String commodityID, BigDecimal price) {
        return curtainCommodityDao.alterCommodityPrice(commodityID, price) > 0;
    }

}
