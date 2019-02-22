package com.yulan.dao;

import com.yulan.pojo.CartItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartItemDao {

	int addCartItem(CartItem cartItem);

	int deleteCartItemByID(int cartItemID);

	CartItem getCartItemByID(int cartItemID);

	List<CartItem> getCartItems(@Param("itemId") String itemId,@Param("cartID") String cartID);

	int updateCartItem(CartItem cartItem);

}