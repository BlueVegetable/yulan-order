package com.yulan.controller;

import com.yulan.pojo.*;
import com.yulan.service.*;
import com.yulan.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("cart")
public class CartController{

	@Autowired
	private ItemService itemService;
	@Autowired
	private CartService cartService;
	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private CommodityService commodityService;
	@Autowired
	private SalPromotionService salPromotionService;

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

	@ResponseBody@RequestMapping("addCartItem")
	public Map addCartItem(@RequestBody Map<String,Object> parameters) {
		String customer_type = (String) parameters.get("customer_type");
		String CID = (String) parameters.get("CID");
		String itemNO = (String) parameters.get("itemNO");
		String commodityType = (String) parameters.get("commodityType");
		String activityID = (String) parameters.get("activityID");
		if(activityID.equals(""))
		    activityID = null;
		String quantity = (String) parameters.get("quantity");
		String price = (String) parameters.get("price");

		Item item = itemService.getItemByItemNO(itemNO);
		Cart cart = cartService.getSimpleCartByCID(CID);
		SalPromotion salPromotion = salPromotionService.getSalPromotionByID(activityID);
		CartItem cartItem = cartItemService.getCartItemOrder(cart.getCartId(), commodityType,
				salPromotion == null?null:salPromotion.getGroupType(),
				item.getGroupType());
		if(cartItem == null) {
			cartItem = new CartItem();
			cartItem.setCommodityType(commodityType);
			cartItem.setProductGroupType(item.getGroupType());
			cartItem.setActivityGroupType(salPromotion.getGroupType());
			cartItem.setCartId(cart.getCartId());
			if(!cartItemService.addCartItem(cartItem))
				return Response.getResponseMap(1,"添加失败",null);
		}
		Commodity commodity = commodityService.getCommodityAppoint(activityID,itemNO,cartItem.getCartItemId());
		if(commodity == null) {
            commodity = new Commodity();
            commodity.setItem(item);
            commodity.setQuantity(new BigInteger(quantity));
            commodity.setCartItemId(cartItem.getCartItemId());
            commodity.setActivityId(activityID);
            switch (customer_type) {
                case "02":commodity.setPrice(item.getPriceSale());break;
                case "06":commodity.setPrice(item.getPriceFx());break;
                case "09":commodity.setPrice(item.getPriceHome());break;
                case "05":commodity.setPrice(item.getSalePrice());break;
                case "10":commodity.setPrice(new BigDecimal(price));break;
                default:return Response.getResponseMap(1,"添加失败",null);
            }
            if(!commodityService.addCommodity(commodity))
                return Response.getResponseMap(1,"添加失败",null);
            else
                return Response.getResponseMap(0,"添加成功",null);
        } else {
		    commodity.setItem(item);
		    BigInteger count = new BigInteger(quantity);
		    count = count.add(commodity.getQuantity());
		    commodity.setQuantity(count);
		    if(!commodityService.updateCommodity(commodity))
                return Response.getResponseMap(1,"添加失败",null);
		    else
                return Response.getResponseMap(0,"添加成功",null);
        }
	}

}