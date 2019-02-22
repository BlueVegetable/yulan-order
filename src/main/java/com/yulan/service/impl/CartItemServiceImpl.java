package com.yulan.service.impl;

import com.yulan.dao.CartItemDao;
import com.yulan.pojo.CartItem;
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
		cartItem.setCartItemId(StringUtil.createStringID());
		return cartItemDao.addCartItem(cartItem)>0;
	}

	@Override
	public boolean deleteCartItemByID(int cartItemID) {
		return cartItemDao.deleteCartItemByID(cartItemID)>0;
	}

	@Override
	public CartItem getCartItemByID(int cartItemID) {
		return cartItemDao.getCartItemByID(cartItemID);
	}

	@Override
	public List<CartItem> getCartItems(String itemId,String cartID) {
		return cartItemDao.getCartItems(itemId,cartID);
	}

	@Override
	public boolean updateCartItem(CartItem cartItem) {
		return cartItemDao.updateCartItem(cartItem)>0;
	}

}