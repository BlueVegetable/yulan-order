package com.yulan.service;

import com.yulan.pojo.CartItem;

import java.util.List;

public interface CartItemService {

	boolean addCartItem(CartItem cartItem);

	boolean newCartItem(CartItem cartItem);

	boolean deleteCartItemByID(String cartItemID);

	CartItem getCartItemByID(String cartItemID);

	List<CartItem> getCartItems(String cartID, String commodityType);

	CartItem getCartItemOrder(String cartID, String commodityType, String activityGroupType, String productGroupType);

	boolean alterCartItemCount(String cartItemID,Integer count);

	boolean updateCartItem(CartItem cartItem);

}