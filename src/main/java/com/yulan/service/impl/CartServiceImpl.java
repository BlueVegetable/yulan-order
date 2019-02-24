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
        cart.setCartId(System.currentTimeMillis()+ StringUtil.createStringID());
		return cartDao.addCart(cart)>0;
	}

	@Override
	public boolean existCart(String CID) {
		return cartDao.countCartByCID(CID) > 0;
	}

	@Override
	public Cart getSimpleCartByID(String cartID) {
		return cartDao.getCartByID(cartID);
	}

	@Override
	public Cart getSimpleCartByCID(String CID) {
		return cartDao.getCartByCID(CID);
	}

	@Override
	public Cart getCartByCID(String CID) {
		Cart cart = getSimpleCartByCID(CID);
		return cart;
	}
}