package com.yulan.service.impl;

import com.yulan.dao.CartDao;
import com.yulan.pojo.Cart;
import com.yulan.service.CartService;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDao cartDao;

	@Override
	public boolean addCart(Cart cart) {
		cart.setCartId(StringUtil.createStringID());
		return cartDao.addCart(cart)>0;
	}

	@Override
	public boolean deleteCartByID(int cartID) {
		return cartDao.deleteCartByID(cartID)>0;
	}

	@Override
	public Cart getCartByID(int cartID) {
		return cartDao.getCartByID(cartID);
	}

	@Override
	public Cart getCartByCustomerID(String customerId) {
		return cartDao.getCartByCustomerID(customerId);
	}

	@Override
	public boolean updateCart(Cart cart) {
		return cartDao.updateCart(cart)>0;
	}

}