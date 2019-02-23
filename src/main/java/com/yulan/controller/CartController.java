package com.yulan.controller;

import com.yulan.pojo.Cart;
import com.yulan.pojo.CartItem;
import com.yulan.service.CartItemService;
import com.yulan.service.CartService;
import com.yulan.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("cart")
public class CartController{

	@Autowired
	private CartService cartService;
	@Autowired
	private CartItemService cartItemService;

	private final static String WALLPAPER = "wallpaper";
	private final static String SOFT = "soft";
	private final static String CURTAIN = "curtain";

	@ResponseBody@RequestMapping("addCart")
	public Map<String,Object> addCart(Cart cart) {
		if(cartService.addCart(cart))
			return Response.getResponseMap(0,"添加成功",null);
		else
			return Response.getResponseMap(1,"添加失败",null);
	}

	@ResponseBody@RequestMapping("getCartByID")
	public Cart getCartByID(String cartID) {
		return cartService.getSimpleCartByID(cartID);
	}

	@ResponseBody@RequestMapping("getSimpleCartByCID")
	public Cart getSimpleCartByCID(String CID) throws Exception {
		Cart cart = cartService.getSimpleCartByCID(CID);
		if(cart == null) {
			cart = new Cart();
			cart.setCustomerId(CID);
			if(!cartService.addCart(cart)) {
				throw new Exception("服务器出错");
			}
		}
		return cart;
	}

	@ResponseBody@RequestMapping("getAllCartByCID")
	public Cart getAllCartByCID(String CID) throws Exception {
		Cart cart = getSimpleCartByCID(CID);
		Map<String, List<CartItem>> cartItems = new HashMap<>();
		cartItems.put(CURTAIN,cartItemService.getCartItems(cart.getCartId(),CURTAIN));
		cartItems.put(WALLPAPER,cartItemService.getCartItems(cart.getCartId(),WALLPAPER));
		cartItems.put(SOFT,cartItemService.getCartItems(cart.getCartId(),SOFT));
		cart.setCartItems(cartItems);
		return cart;
	}

}