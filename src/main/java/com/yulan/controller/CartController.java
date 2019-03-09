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
	@Autowired
	private ActivityGroupTypeService activityGroupTypeService;
	@Autowired
	private ProductGroupTypeService productGroupTypeService;
	@Autowired
	private UnitService unitService;

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
	public Map addCartItem(@RequestBody Map<String,Object> parameters) throws Exception {
		String customer_type = (String) parameters.get("customer_type");
		String CID = (String) parameters.get("CID");
		String itemNO = (String) parameters.get("itemNO");
		String commodityType = (String) parameters.get("commodityType");
		String activityID = (String) parameters.get("activityID");
		if(activityID.equals(""))
		    activityID = null;
		String quantity = (String) parameters.get("quantity");
		String price = (String) parameters.get("price");
		String width = (String) parameters.get("width");
		String height = (String) parameters.get("height");
		String note = (String) parameters.get("note");

		Item item = itemService.getItemByItemNO(itemNO);
		Cart cart = getSimpleCartByCID(CID);
		SalPromotion salPromotion = null;
		if(activityID != null) {
			salPromotion = salPromotionService.getSalPromotionByID(activityID);
		}
		CartItem cartItem = cartItemService.getCartItemOrder(cart.getCartId(), commodityType,
				salPromotion == null?null:salPromotion.getGroupType(),
				item.getGroupType());
		if(cartItem == null) {
			cartItem = new CartItem();
			cartItem.setCommodityType(commodityType);
			cartItem.setProductGroupType(item.getGroupType());
			cartItem.setActivityGroupType(salPromotion==null?null:salPromotion.getGroupType());
			cartItem.setCartId(cart.getCartId());
			if(!cartItemService.addCartItem(cartItem))
				return Response.getResponseMap(1,"添加失败",null);
		}
		Commodity commodity = commodityService.getCommodityAppoint(activityID,itemNO,cartItem.getCartItemId());
		if(commodity == null) {
            commodity = new Commodity();
            commodity.setItem(item);
            commodity.setCartItemId(cartItem.getCartItemId());
            commodity.setActivityId(activityID);
            Unit unit = unitService.getUnitByID(item.getUnit());
            commodity.setUnit(unit!=null?unit.getNote():null);
            switch (customer_type) {
                case "02":commodity.setPrice(item.getPriceSale());break;
                case "06":commodity.setPrice(item.getPriceFx());break;
                case "09":commodity.setPrice(item.getPriceHome());break;
                case "05":commodity.setPrice(item.getSalePrice());break;
                case "10":commodity.setPrice(new BigDecimal(price));break;
                default:return Response.getResponseMap(1,"添加失败",null);
            }
            if(quantity==null||quantity.equals("")) {
            	commodity.setWidth(new BigDecimal(width));
            	commodity.setHeight(new BigDecimal(height));
			} else {
            	commodity.setQuantity(new BigInteger(quantity));
			}
            if(!commodityService.addCommodity(commodity))
                return Response.getResponseMap(1,"添加失败",null);
            else
                return Response.getResponseMap(0,"添加成功",null);
        } else {
		    commodity.setItem(item);
			if(quantity==null||quantity.equals("")) {
				return Response.getResponseMap(2,"该产品已存在于购物车",null);
			} else {
				BigInteger count = new BigInteger(quantity);
				count = count.add(commodity.getQuantity());
				commodity.setQuantity(count);
			}
			if(note != null&&!note.equals("")) {
				if(commodity.getNote()!=null) {
					commodity.setNote(commodity.getNote()+"\r\n"+note);
				} else {
					commodity.setNote(note);
				}
			}
		    if(!commodityService.updateCommodity(commodity))
                return Response.getResponseMap(1,"添加失败",null);
		    else
                return Response.getResponseMap(0,"添加成功",null);
        }
	}

	@ResponseBody@RequestMapping("deleteCartItems")
	public Map deleteCartItems(@RequestBody List<String> cartItemIDs) {
		int number = 0;
		for (String cartItemID:cartItemIDs) {
			commodityService.deleteCommoditiesByCartItemID(cartItemID);
			cartItemService.deleteCartItemByID(cartItemID);
		}
		return Response.getResponseMap(0,"",number);
	}

	@ResponseBody@RequestMapping("deleteCommodities")
	public Map deleteCommodities(@RequestBody List<String> commodityIDs) {
		if (commodityIDs == null||commodityIDs.size() == 0) {
			return Response.getResponseMap(0,"",null);
		} else {
			Commodity commodity = commodityService.getCommodityByID(commodityIDs.get(0));
			String cartItemID = commodity.getCartItemId();
			for (String commodityID:commodityIDs) {
				commodityService.deleteCommodityByID(commodityID);
			}
			long number = commodityService.countByCartItemID(cartItemID);
			if(number == 0) {
				cartItemService.deleteCartItemByID(cartItemID);
			}
			return Response.getResponseMap(0,"",null);
		}
	}

	@ResponseBody@RequestMapping("updateCartItem")
	public Map updateCartItem(@RequestBody Map<String,String> parameters) throws Exception {
		String commodityID = parameters.get("commodityID");
		String activityID = parameters.get("activityID");
		String quantityString = parameters.get("quantity");
		String widthString = parameters.get("width");
		String heightString = parameters.get("height");
		String note = parameters.get("note");
		Commodity commodity = commodityService.getCommodityByID(commodityID);
		CartItem cartItem = cartItemService.getCartItemByID(commodity.getCartItemId());
        SalPromotion salPromotion;
        if(activityID!=null&&!activityID.equals("")) {
            salPromotion = salPromotionService.getSalPromotionByID(activityID);
        } else {
            salPromotion = null;
        }
		CartItem cartItemNew = cartItemService.getCartItemOrder(cartItem.getCartId(),
				cartItem.getCommodityType(),salPromotion==null?null:salPromotion.getGroupType(),
				commodity.getItem().getGroupType());
		if(cartItemNew == null) {
			cartItemNew = new CartItem();
			cartItemNew.setCartId(cartItem.getCartId());
			if(salPromotion!=null) {
                cartItemNew.setActivityGroupType(salPromotion.getGroupType());
            } else {
			    cartItemNew.setActivityGroupType(null);
            }
			cartItemNew.setProductGroupType(commodity.getItem().getGroupType());
			cartItemNew.setCommodityType(cartItem.getCommodityType());
			cartItemService.addCartItem(cartItemNew);
		}
		if(salPromotion!=null) {
		    commodity.setActivityId(salPromotion.getOrderType());
        } else {
		    commodity.setActivityId(null);
        }
		if(quantityString!=null&&!quantityString.equals("")) {
			commodity.setQuantity(new BigInteger(quantityString));
		} else {
			commodity.setHeight(new BigDecimal(heightString));
			commodity.setWidth(new BigDecimal(widthString));
		}
		commodity.setCartItemId(cartItemNew.getCartItemId());
		if(note==null||note.equals("")) {
			commodity.setNote(null);
		} else {
			commodity.setNote(note);
		}
		Commodity commodityMayBe = commodityService.getCommodityAppoint(commodity.getActivityId(),
                commodity.getItem().getItemNo(),commodity.getCartItemId());
		if(commodityMayBe == null) {
            commodityService.updateCommodity(commodity);
        } else {
		    if(commodityMayBe.getQuantity()!=null) {
		        commodityMayBe.setQuantity(commodity.getQuantity().add(commodityMayBe.getQuantity()));
		        commodityService.updateCommodity(commodityMayBe);
		        commodityService.deleteCommodityByID(commodity.getId());
            } else {
		        return Response.getResponseMap(2,"该产品在此活动下已存在",null);
            }
        }
		long count = commodityService.countByCartItemID(cartItem.getCartItemId());
		if(count == 0) {
		    cartItemService.deleteCartItemByID(cartItem.getCartItemId());
        }
		return Response.getResponseMap(0,"",null);
	}

}