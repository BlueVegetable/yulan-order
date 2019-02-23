package com.yulan.dao;

import com.yulan.pojo.CartItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartItemDao {

	int addCartItem(CartItem cartItem);

	int deleteCartItemByID(String cartItemID);

	CartItem getCartItemByID(String cartItemID);

	List<CartItem> getCartItems(@Param("cartID") String cartID, @Param("commodityType") String commodityType);

	int updateCartItem(CartItem cartItem);

}