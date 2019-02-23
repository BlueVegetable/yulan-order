package com.yulan.service;

import com.yulan.pojo.CartItem;

import java.util.List;

public interface CartItemService {

	boolean addCartItem(CartItem cartItem);

	boolean deleteCartItemByID(String cartItemID);

	CartItem getCartItemByID(String cartItemID);

	List<CartItem> getCartItems(String cartID, String commodityType);

	boolean updateCartItem(CartItem cartItem);

}