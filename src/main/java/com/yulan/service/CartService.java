package com.yulan.service;

import com.yulan.pojo.Cart;

public interface CartService {

	boolean addCart(Cart cart);

	boolean deleteCartByID(int cartID);

	Cart getCartByID(int cartID);

	Cart getCartByCustomerID(String customerId);

	boolean updateCart(Cart cart);

}