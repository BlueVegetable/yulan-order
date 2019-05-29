package com.yulan.encode;

import com.yulan.dao.CurtainCartItemDao;
import com.yulan.pojo.CartItem;
import com.yulan.pojo.CurtainCartItem;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("curtainCartItemEncode")
public class CurtainCartItemEncode {

    @Autowired
    private CurtainCartItemDao curtainCartItemDao;

    public boolean addCartItem(CartItem cartItem) {
        CurtainCartItem curtainCartItem = (CurtainCartItem) cartItem;
        curtainCartItem.setLocation(StringUtil.UTF8ToGBK(curtainCartItem.getLocation()));
        return curtainCartItemDao.addCartItem(cartItem) > 0;
    }

    public boolean deleteCartItemByID(String cartItemID) {
        return curtainCartItemDao.deleteCartItemByID(cartItemID) > 0;
    }

    public CartItem getCartItemByID(String cartItemID) {
        return curtainCartItemDao.getCartItemByID(cartItemID);
    }

    public List<CartItem> getCartItems(String cartID, String commodityType) {
        List<CartItem> cartItems = curtainCartItemDao.getCartItems(cartID,commodityType);
        for (CartItem cartItem:cartItems) {
            CurtainCartItem curtainCartItem = (CurtainCartItem) cartItem;
            curtainCartItem.setLocation(StringUtil.GBKToUTF8(curtainCartItem.getLocation()));
        }
        return cartItems;
    }

    public CartItem getCartItemOrder(String cartID, String commodityType, String activityGroupType, String productGroupType) {
        return curtainCartItemDao.getCartItemOrder(cartID, commodityType, activityGroupType, productGroupType);
    }

    public boolean updateCartItem(CartItem cartItem) {
        return curtainCartItemDao.updateCartItem(cartItem) > 0;
    }

    public boolean alterCurtainCartItem(String cartItemID,Integer count) {
        return curtainCartItemDao.alterCurtainCartItem(cartItemID, count) > 0;
    }
}
