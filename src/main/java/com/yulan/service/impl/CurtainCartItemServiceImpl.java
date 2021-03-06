package com.yulan.service.impl;

import com.yulan.dao.SalPromotionDao;
import com.yulan.encode.CurtainCartItemEncode;
import com.yulan.encode.CurtainCommodityEncode;
import com.yulan.pojo.*;
import com.yulan.service.CartItemService;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service("curtainCartItemService")
public class CurtainCartItemServiceImpl implements CartItemService {
    @Autowired
    private CurtainCartItemEncode curtainCartItemEncode;
    @Autowired
    private CurtainCommodityEncode curtainCommodityEncode;
    @Autowired
    private SalPromotionDao salPromotionDao;

    @Override
    public boolean addCartItem(CartItem cartItem) {
        boolean flag = true;
        CurtainCartItem curtainCartItem = (CurtainCartItem) cartItem;
        curtainCartItem.setCommodityType("curtain");
        Timestamp saveTime = new Timestamp(System.currentTimeMillis());
        for (CurtainList curtainList:curtainCartItem.getCurtainLists()) {
            List<CurtainCommodity> curtainCommodities = curtainList.getCurtainCommodities();
            String partName = curtainList.getPartName();
            for (CurtainCommodity curtainCommodity:curtainCommodities) {
                curtainCommodity.setId(System.currentTimeMillis()+StringUtil.createStringID());
                curtainCommodity.setCurtainPartName(partName);
                curtainCommodity.setStatus(1);
                curtainCommodity.setSaveTime(saveTime);
            }
        }
        for (CurtainList curtainList:curtainCartItem.getCurtainLists()) {
            List<CurtainCommodity> curtainCommodities = curtainList.getCurtainCommodities();
            for (CurtainCommodity curtainCommodity:curtainCommodities) {
                flag = flag && curtainCommodityEncode.addCommodity(curtainCommodity);
            }
        }
        return flag;
    }

    @Override
    public boolean newCartItem(CartItem cartItem) {
        CurtainCartItem curtainCartItem = (CurtainCartItem) cartItem;
        curtainCartItem.setCommodityType("curtain");
        curtainCartItem.setProductGroupType("E");
        String cartItemId = System.currentTimeMillis()+StringUtil.createStringID();
        curtainCartItem.setCartItemId(cartItemId);
        return curtainCartItemEncode.addCartItem(cartItem);
    }

    @Override
    public boolean deleteCartItemByID(String cartItemID) {
        return curtainCartItemEncode.deleteCartItemByID(cartItemID);
    }

    @Override
    public CartItem getCartItemByID(String cartItemID) {
        return curtainCartItemEncode.getCartItemByID(cartItemID);
    }

    @Override
    public List<CartItem> getCartItems(String cartID, String commodityType) {
        String activityID = null;

        List<CartItem> cartItems = curtainCartItemEncode.getCartItems(cartID, commodityType);
        List<CartItem> result = new ArrayList<>();
        for (CartItem cartItem:cartItems) {
            List<Commodity> commodities = curtainCommodityEncode.getCommoditiesByCartItemID(cartItem.getCartItemId());
            List<CurtainCommodity> lt = new ArrayList<>();
            List<CurtainCommodity> ls = new ArrayList<>();
            List<CurtainCommodity> sha = new ArrayList<>();
            List<CurtainCommodity> peijian = new ArrayList<>();
            List<CurtainCommodity> lspb = new ArrayList<>();
            List<CurtainList> curtainLists = new ArrayList<>();
            for (Commodity commodity:commodities) {
                CurtainCommodity curtainCommodity = (CurtainCommodity) commodity;
                switch (curtainCommodity.getCurtainPartName()) {
                    case "帘头":lt.add(curtainCommodity);break;
                    case "帘身":ls.add(curtainCommodity);break;
                    case "纱":sha.add(curtainCommodity);break;
                    case "配件":peijian.add(curtainCommodity);break;
                    case "帘身配布":lspb.add(curtainCommodity);break;
                    default:continue;
                }
                if(commodity.getActivityId()!=null&&activityID==null) {
                    activityID = commodity.getActivityId();
                }
            }
            curtainLists.add(new CurtainList("帘头",lt));
            curtainLists.add(new CurtainList("帘身",ls));
            curtainLists.add(new CurtainList("纱",sha));
            curtainLists.add(new CurtainList("配件",peijian));
            curtainLists.add(new CurtainList("帘身配布",lspb));
            CurtainCartItem curtainCartItem = (CurtainCartItem) cartItem;

            SalPromotion salPromotion = null;
            if(activityID!=null)
                salPromotion = salPromotionDao.getSalPromotionByID(activityID);
            curtainCartItem.setSalPromotion(salPromotion);
            curtainCartItem.setCurtainLists(curtainLists);
            if(lt.size()!=0||ls.size()!=0||sha.size()!=0||peijian.size()!=0) {
                result.add(curtainCartItem);
            }
        }
        return result;
    }

    @Override
    public CartItem getCartItemOrder(String cartID, String commodityType, String activityGroupType, String productGroupType) {
        return curtainCartItemEncode.getCartItemOrder(cartID, commodityType, activityGroupType, productGroupType);
    }

    @Override
    public boolean alterCartItemCount(String cartItemID, Integer count) {
        return curtainCartItemEncode.alterCurtainCartItem(cartItemID, count);
    }

    @Override
    public boolean updateCartItem(CartItem cartItem) {
        return curtainCartItemEncode.updateCartItem(cartItem);
    }

    public boolean alterCurtainCartItem(String cartItemID,Integer count) {
        return curtainCartItemEncode.alterCurtainCartItem(cartItemID, count);
    }

    private List<List<Commodity>> dealCommodities(List<Commodity> commodities) {
        List<List<Commodity>> result = new ArrayList<>();
        Long time = null;
        if(commodities!=null&&commodities.size()>0) {
            CurtainCommodity curtainCommodity = (CurtainCommodity) commodities.get(0);
            time = curtainCommodity.getSaveTime().getTime();
        }
        List<Commodity> inline = new ArrayList<>();
        for (Commodity commodity:commodities) {
            CurtainCommodity curtainCommodity = (CurtainCommodity) commodity;
            Long timeInline = curtainCommodity.getSaveTime().getTime();
            if(timeInline == time) {
                inline.add(commodity);
            } else {
                result.add(inline);
                inline = new ArrayList<>();
            }
        }
        return result;
    }
}
