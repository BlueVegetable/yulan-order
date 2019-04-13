package com.yulan.service.impl;

import com.yulan.encode.CartItemEncode;
import com.yulan.pojo.CartItem;
import com.yulan.pojo.Commodity;
import com.yulan.service.CartItemService;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
	public boolean newCartItem(CartItem cartItem) {
		return false;
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
		List<CartItem> cartItemsDeal = new ArrayList<>();
		for (CartItem cartItem:cartItems) {
			List<Commodity> commodities = cartItem.getCommodities();
			if(commodities.size()!=0) {
				cartItemsDeal.add(cartItem);
			}
		}
		return cartItemsDeal;
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