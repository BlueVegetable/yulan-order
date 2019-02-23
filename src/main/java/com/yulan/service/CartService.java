package com.yulan.service;

import com.yulan.pojo.Cart;

public interface CartService {

	boolean addCart(Cart cart);

	boolean existCart(String CID);

	Cart getSimpleCartByID(String cartID);

	Cart getSimpleCartByCID(String CID);

	Cart getCartByCID(String CID);

}