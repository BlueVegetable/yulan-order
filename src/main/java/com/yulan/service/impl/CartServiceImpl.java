package com.yulan.service.impl;

import com.yulan.encode.CartEncode;
import com.yulan.pojo.Cart;
import com.yulan.service.CartService;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartEncode cartEncode;

	@Override
	public boolean addCart(Cart cart) {
        cart.setCartId(System.currentTimeMillis()+ StringUtil.createStringID());
		return cartEncode.addCart(cart);
	}

	@Override
	public boolean existCart(String CID) {
		CID = CID.toUpperCase();
		return cartEncode.existCart(CID);
	}

	@Override
	public Cart getSimpleCartByID(String cartID) {
		return cartEncode.getSimpleCartByID(cartID);
	}

	@Override
	public Cart getSimpleCartByCID(String CID) {
		return cartEncode.getCartByCID(CID);
	}

	@Override
	public Cart getCartByCID(String CID) {
		CID = CID.toUpperCase();
		Cart cart = getSimpleCartByCID(CID);
		return cart;
	}
}