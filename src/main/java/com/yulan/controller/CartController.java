package com.yulan.controller;

import com.yulan.pojo.Cart;
import com.yulan.pojo.CartItem;
import com.yulan.service.CartItemService;
import com.yulan.service.CartService;
import com.yulan.utils.Response;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@Controller
@RequestMapping("cart")
public class CartController{

	@Autowired
	private CartService cartService;
	@Autowired
	private CartItemService cartItemService;

	@ResponseBody@RequestMapping("getCartByID")
	public Cart getCartByID(int cartID) {
		return cartService.getCartByID(cartID);
	}

	@ResponseBody@RequestMapping("addCartItem")
	public Map addCartItem(@RequestBody Map<String,Object> datas) throws InvocationTargetException, IllegalAccessException {
	    String customerID = (String) datas.get("customerID");
        CartItem cartItem = new CartItem();
        BeanUtils.populate(cartItem, (Map<String,Object>) datas.get("cartItem"));
	    Cart cart = cartService.getCartByCustomerID(customerID);
	    if(cart!=null) {
	        cartItem.setCartID(cart.getCartId());
        } else {
	        cart = new Cart(customerID);
	        cartService.addCart(cart);
	        cartItem.setCartID(cart.getCartId());
        }
	    if(cartItemService.addCartItem(cartItem)) {
	        return Response.getResponseMap(0,"",null);
        } else {
	        return Response.getResponseMap(1,"添加失败",null);
        }
    }

}