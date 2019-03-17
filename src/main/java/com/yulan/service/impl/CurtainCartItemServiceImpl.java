package com.yulan.service.impl;

import com.yulan.encode.CurtainCartItemEncode;
import com.yulan.encode.CurtainCommodityEncode;
import com.yulan.pojo.*;
import com.yulan.service.CartItemService;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("curtainCartItemService")
public class CurtainCartItemServiceImpl implements CartItemService {
    @Autowired
    private CurtainCartItemEncode curtainCartItemEncode;
    @Autowired
    private CurtainCommodityEncode curtainCommodityEncode;

    @Override
    public boolean addCartItem(CartItem cartItem) {
        cartItem.setCartItemId(System.currentTimeMillis()+StringUtil.createStringID());
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
        List<CartItem> cartItems = curtainCartItemEncode.getCartItems(cartID, commodityType);
        for (CartItem cartItem:cartItems) {
            List<Commodity> commodities = curtainCommodityEncode.getCommoditiesByCartItemID(cartItem.getCartItemId());
            List<CurtainCommodity> lt = new ArrayList<>();
            List<CurtainCommodity> ls = new ArrayList<>();
            List<CurtainCommodity> sha = new ArrayList<>();
            List<CurtainCommodity> peijian = new ArrayList<>();
            List<CurtainList> curtainLists = new ArrayList<>();
            for (Commodity commodity:commodities) {
                CurtainCommodity curtainCommodity = (CurtainCommodity) commodity;
                switch (curtainCommodity.getCurtainPartName()) {
                    case "帘头":lt.add(curtainCommodity);break;
                    case "帘身":ls.add(curtainCommodity);break;
                    case "纱":sha.add(curtainCommodity);break;
                    case "配件":peijian.add(curtainCommodity);break;
                    default:continue;
                }
            }
            curtainLists.add(new CurtainList("帘头",lt));
            curtainLists.add(new CurtainList("帘身",ls));
            curtainLists.add(new CurtainList("纱",sha));
            curtainLists.add(new CurtainList("配件",peijian));
            CurtainCartItem curtainCartItem = (CurtainCartItem) cartItem;
            curtainCartItem.setCurtainLists(curtainLists);
        }
        return cartItems;
    }

    @Override
    public CartItem getCartItemOrder(String cartID, String commodityType, String activityGroupType, String productGroupType) {
        return curtainCartItemEncode.getCartItemOrder(cartID, commodityType, activityGroupType, productGroupType);
    }

    @Override
    public boolean updateCartItem(CartItem cartItem) {
        return curtainCartItemEncode.updateCartItem(cartItem);
    }
}
