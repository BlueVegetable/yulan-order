package com.yulan.dao;

import com.yulan.pojo.Cart;

public interface CartDao {

	int addCart(Cart cart);

	long countCartByCID(String CID);

	Cart getCartByID(String cartID);

	Cart getCartByCID(String CID);

}