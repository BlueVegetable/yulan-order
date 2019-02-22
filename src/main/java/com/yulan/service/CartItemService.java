package com.yulan.service;

import com.yulan.pojo.CartItem;

import java.util.List;

public interface CartItemService {

	boolean addCartItem(CartItem cartItem);

	boolean deleteCartItemByID(int cartItemID);

	CartItem getCartItemByID(int cartItemID);

	List<CartItem> getCartItems(String itemId,String cartID);

	boolean updateCartItem(CartItem cartItem);

}