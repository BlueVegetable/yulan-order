package com.yulan.dao;

import com.yulan.pojo.Cart;
import org.apache.ibatis.annotations.Param;

public interface CartDao {

	int addCart(Cart cart);

	int deleteCartByID(int cartID);

	Cart getCartByID(int cartID);

	Cart getCartByCustomerID(@Param("customerId") String customerId);

	int updateCart(Cart cart);

}