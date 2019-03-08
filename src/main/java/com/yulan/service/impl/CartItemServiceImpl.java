package com.yulan.service.impl;

import com.yulan.dao.CartItemDao;
import com.yulan.pojo.CartItem;
import com.yulan.pojo.Commodity;
import com.yulan.service.CartItemService;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	private CartItemDao cartItemDao;

	@Override
	public boolean addCartItem(CartItem cartItem) {
		cartItem.setCartItemId(System.currentTimeMillis()+ StringUtil.createStringID());
		return cartItemDao.addCartItem(cartItem)>0;
	}

	@Override
	public boolean deleteCartItemByID(String cartItemID) {
		return cartItemDao.deleteCartItemByID(cartItemID)>0;
	}

	@Override
	public CartItem getCartItemByID(String cartItemID) {
		return cartItemDao.getCartItemByID(cartItemID);
	}

	@Override
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

	@Override
	public CartItem getCartItemOrder(String cartID, String commodityType, String activityGroupType, String productGroupType) {
		return cartItemDao.getCartItemOrder(cartID,commodityType,activityGroupType,productGroupType);
	}

	@Override
	public boolean updateCartItem(CartItem cartItem) {
		return cartItemDao.updateCartItem(cartItem)>0;
	}

}