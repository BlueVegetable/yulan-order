package com.yulan.service.impl;

import com.yulan.encode.CartItemEncode;
import com.yulan.pojo.CartItem;
import com.yulan.service.CartItemService;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cartItemService")
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	private CartItemEncode cartItemEncode;

	@Override
	public boolean addCartItem(CartItem cartItem) {
		cartItem.setCartItemId(System.currentTimeMillis()+ StringUtil.createStringID());
		return cartItemEncode.addCartItem(cartItem);
	}

	@Override
	public boolean deleteCartItemByID(String cartItemID) {
		return cartItemEncode.deleteCartItemByID(cartItemID);
	}

	@Override
	public CartItem getCartItemByID(String cartItemID) {
		return cartItemEncode.getCartItemByID(cartItemID);
	}

	@Override
	public List<CartItem> getCartItems(String cartID, String commodityType) {
		List<CartItem> cartItems = cartItemEncode.getCartItems(cartID,commodityType);
		return cartItems;
	}

	@Override
	public CartItem getCartItemOrder(String cartID, String commodityType, String activityGroupType, String productGroupType) {
		return cartItemEncode.getCartItemOrder(cartID,commodityType,activityGroupType,productGroupType);
	}

	@Override
	public boolean updateCartItem(CartItem cartItem) {
		return cartItemEncode.updateCartItem(cartItem);
	}

}