package com.yulan.encode;

import com.yulan.dao.CartItemDao;
import com.yulan.pojo.CartItem;
import com.yulan.pojo.Commodity;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemEncode {

    @Autowired
    private CartItemDao cartItemDao;

    public boolean addCartItem(CartItem cartItem) {
        return cartItemDao.addCartItem(cartItem)>0;
    }

    public boolean deleteCartItemByID(String cartItemID) {
        return cartItemDao.deleteCartItemByID(cartItemID)>0;
    }

    public CartItem getCartItemByID(String cartItemID) {
        return cartItemDao.getCartItemByID(cartItemID);
    }

    public List<CartItem> getCartItems(String cartID, String commodityType) {
        List<CartItem> cartItems = cartItemDao.getCartItems(cartID,commodityType);
        for (CartItem cartItem:cartItems) {
            List<Commodity> commodities = cartItem.getCommodities();
            for (Commodity commodity:commodities) {
                commodity.setNote(StringUtil.GBKToUTF8(commodity.getNote()));
                commodity.setUnit(StringUtil.GBKToUTF8(commodity.getUnit()));
            }
        }
        return cartItems;
    }

    public CartItem getCartItemOrder(String cartID, String commodityType, String activityGroupType, String productGroupType) {
        return cartItemDao.getCartItemOrder(cartID,commodityType,activityGroupType,productGroupType);
    }

    public boolean updateCartItem(CartItem cartItem) {
        return cartItemDao.updateCartItem(cartItem)>0;
    }
}
