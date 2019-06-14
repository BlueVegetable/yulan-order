package com.yulan.controller;

import com.yulan.pojo.*;
import com.yulan.service.*;
import com.yulan.utils.Response;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller @RequestMapping("cart")
public class CartController{

	@Autowired
	private Web_userService webUserService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private CartService cartService;
	@Autowired@Qualifier("cartItemService")
	private CartItemService cartItemService;
	@Autowired@Qualifier("curtainCartItemService")
	private CartItemService curtainCartItemService;
	@Autowired@Qualifier("commodityService")
	private CommodityService commodityService;
	@Autowired@Qualifier("curtainCommodityService")
    private CommodityService curtainCommodityService;
	@Autowired
	private SalPromotionService salPromotionService;
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

	@ResponseBody@RequestMapping("addCurtain")
	public Map<String,Object> addCurtain(@RequestBody CurtainCartItem curtainCartItem) {
	    curtainCartItemService.addCartItem(curtainCartItem);
	    for (CurtainList curtainList:curtainCartItem.getCurtainLists()) {
	        for (Commodity commodity:curtainList.getCurtainCommodities()) {
	            curtainCommodityService.addCommodity(commodity);
            }
        }
	    return Response.getResponseMap(0,"",null);
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
		String width = (String) parameters.get("width");
		String height = (String) parameters.get("height");
		String note = (String) parameters.get("note");
		Integer splitShipment = parameters.get("splitShipment")==null||parameters.get("splitShipment").equals("")?
				null:Integer.parseInt((String) parameters.get("splitShipment"));

		Item item = itemService.getItemByItemNO(itemNO);
		Cart cart = getSimpleCartByCID(CID);
		SalPromotion salPromotion = null;
		if(activityID != null) {
			salPromotion = salPromotionService.getSalPromotionByID(activityID);
		}
		CartItem cartItem = cartItemService.getCartItemOrder(cart.getCartId(), commodityType,
				salPromotion == null?"Z":salPromotion.getGroupType(),
				item.getGroupType());
		if(cartItem == null) {
			cartItem = new CartItem();
			cartItem.setCommodityType(commodityType);
			cartItem.setProductGroupType(item.getGroupType());
			cartItem.setActivityGroupType(salPromotion==null?"Z":salPromotion.getGroupType());
			cartItem.setCartId(cart.getCartId());
			if(!cartItemService.addCartItem(cartItem))
				return Response.getResponseMap(1,"添加失败",null);
		}


		Commodity commodity = new Commodity();
		commodity.setItem(item);
		commodity.setCartItemId(cartItem.getCartItemId());
		commodity.setActivityId(activityID);
		Unit unit = unitService.getUnitByID(item.getUnit());
		commodity.setUnit(unit!=null?unit.getNote():null);
		commodity.setNote(note);
		commodity.setSplitShipment(splitShipment);
		commodity.setStatus(Commodity.COMMODITY_EXIST_STATUS);
		switch (customer_type) {
			case "02":commodity.setPrice(item.getPriceSale());break;
			case "06":commodity.setPrice(item.getPriceFx());break;
			case "09":commodity.setPrice(item.getPriceHome());break;
			case "05":commodity.setPrice(item.getSalePrice());break;
			case "08":commodity.setPrice(item.getPriceSale());break;
			case "10":commodity.setPrice(item.getPriceSale());break;
			default:return Response.getResponseMap(1,"添加失败",null);
		}
		if(quantity==null||quantity.equals("")) {
			commodity.setWidth(new BigDecimal(width));
			commodity.setHeight(new BigDecimal(height));
		} else {
			commodity.setQuantity(new BigDecimal(quantity));
		}
		if(commodity.getPrice() == null) {
			return Response.getResponseMap(2,"该产品正在上架，暂时不能加入购物车",null);
		}
		if(!commodityService.addCommodity(commodity))
			return Response.getResponseMap(1,"添加失败",null);
		else
			return Response.getResponseMap(0,"添加成功",null);
	}

	@ResponseBody@RequestMapping("addCurtainCartItem")
	public Map addCurtainCartItem(@RequestBody Map<String,Object> parameters) {
		JSONObject jsonObject = JSONObject.fromObject(parameters);
		CurtainCartItem curtainCartItem = (CurtainCartItem) JSONObject.toBean(jsonObject,CurtainCartItem.class);
		{
			//进行参数注入
			List<CurtainList> curtainLists = new ArrayList<>();
			List<Map<String,Object>> curtainListParameter = (List<Map<String,Object>>) parameters.get("curtainLists");
			for (Map<String,Object> o:curtainListParameter) {
				CurtainList curtainList = new CurtainList();
				curtainList.setPartName((String) o.get("partName"));
				List<CurtainCommodity> curtainCommodities = new ArrayList<>();
				List<Map<String,Object>> commodityParameters = (List<Map<String, Object>>) o.get("curtainCommodities");
				for (Map<String,Object> commodityParameter:commodityParameters) {
					CurtainCommodity curtainCommodity;
					JSONObject inline = JSONObject.fromObject(commodityParameter);
					curtainCommodity = (CurtainCommodity) JSONObject.toBean(inline,CurtainCommodity.class);
					curtainCommodities.add(curtainCommodity);
				}
				curtainList.setCurtainCommodities(curtainCommodities);
				curtainLists.add(curtainList);
			}
			curtainCartItem.setCurtainLists(curtainLists);
			if(curtainCartItem.getOutsourcingBoxWidth()!=null) {
				curtainCartItem.setOutsourcingBoxExist(1);
			}
		}

		String CID = (String) parameters.get("CID");
		String cartId = cartService.getSimpleCartByCID(CID).getCartId();
		curtainCartItem.setCartId(cartId);
		curtainCartItem.setSaveTime(new Timestamp(System.currentTimeMillis()));
		curtainCartItemService.newCartItem(curtainCartItem);
		List<CurtainList> curtainLists = curtainCartItem.getCurtainLists();
		String customerType = (String) parameters.get("customerType");
		String price = (String) parameters.get("price");
		for (CurtainList curtainList:curtainLists) {
			List<CurtainCommodity> curtainCommodities = curtainList.getCurtainCommodities();
			for (CurtainCommodity curtainCommodity:curtainCommodities) {
				String itemNo = curtainCommodity.getItem().getItemNo();
				Item item = itemService.getItemByItemNO(itemNo);
				switch (customerType) {
					case "02":curtainCommodity.setPrice(item.getPriceSale());break;
					case "06":curtainCommodity.setPrice(item.getPriceFx());break;
					case "09":curtainCommodity.setPrice(item.getPriceHome());break;
					case "05":curtainCommodity.setPrice(item.getSalePrice());break;
					case "08":curtainCommodity.setPrice(item.getPriceSale());break;
					case "10":curtainCommodity.setPrice(item.getPriceSale());break;
					default:return Response.getResponseMap(1,"添加失败",null);
				}
				curtainCommodity.setCartItemId(curtainCartItem.getCartItemId());
			}
		}
        {
            boolean hasPrice = true;
            //进行价格的判断
            for (CurtainList curtainList:curtainCartItem.getCurtainLists()) {
                List<CurtainCommodity> curtainCommodities = curtainList.getCurtainCommodities();
                for (CurtainCommodity curtainCommodity:curtainCommodities) {
                    hasPrice = hasPrice && curtainCommodity.getPrice()!=null;
                    if(!hasPrice) {
                        return Response.getResponseMap(2,"该商品信息正在完善，请等待",null);
                    }
                }
            }
        }
		curtainCartItemService.addCartItem(curtainCartItem);
		return Response.getResponseMap(0,"",null);
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
		Web_user webUser = webUserService.getWebUserByCID(CID);
		List<String> webUserIDs = new ArrayList<>();
		if(webUser.getCompanyId() == null) {
			webUserIDs.add(CID);
		} else {
			List<Web_user> webUsers = webUserService.getWebUsersByCompanyId(webUser.getCompanyId());
			for (Web_user inline:webUsers) {
				webUserIDs.add(inline.getLoginName());
			}
		}

		List<CartItem> curtainCartItems = new ArrayList<>();
		List<CartItem> wallPaperCartItems = new ArrayList<>();
		List<CartItem> softCartItems = new ArrayList<>();

		Cart cart = new Cart();
		Map<String, Object> cartItems = new HashMap<>();

		for (String webUserID:webUserIDs) {
			Cart inline = getSimpleCartByCID(webUserID);
			List<CartItem> curtainCartItemsPart = curtainCartItemService.getCartItems(inline.getCartId(),CURTAIN);
			List<CartItem> wallPaperCartItemsPart = cartItemService.getCartItems(inline.getCartId(),WALLPAPER);
			List<CartItem> softCartItemsPart = cartItemService.getCartItems(inline.getCartId(),SOFT);

			setCartItemsCID(curtainCartItemsPart,webUserID);
			setCartItemsCID(wallPaperCartItemsPart,webUserID);
			setCartItemsCID(softCartItemsPart,webUserID);

			dealCommodityEffective(curtainCartItemsPart);
			dealCommodityEffective(wallPaperCartItemsPart);
			dealCommodityEffective(softCartItemsPart);

			curtainCartItems.addAll(curtainCartItemsPart);
			wallPaperCartItems.addAll(wallPaperCartItemsPart);
			softCartItems.addAll(softCartItemsPart);
		}

		cartItems.put(CURTAIN,dealCurtainCartItems(curtainCartItems));
		cartItems.put(WALLPAPER,wallPaperCartItems);
		cartItems.put(SOFT,softCartItems);

		cart.setCartItems(cartItems);

        return cart;
    }

	@ResponseBody@RequestMapping("updateCartItem")
	public Map updateCartItem(@RequestBody Map<String,String> parameters) throws Exception {
		String commodityID = parameters.get("commodityID");
		String activityID = parameters.get("activityID");
		String quantityString = parameters.get("quantity");
		String widthString = parameters.get("width");
		String heightString = parameters.get("height");
		String note = parameters.get("note");
		Integer splitShipment = parameters.get("splitShipment")==null||parameters.get("splitShipment").equals("")?
				null:Integer.parseInt(parameters.get("splitShipment"));
		Commodity commodity = commodityService.getCommodityByID(commodityID);
		commodity.setSplitShipment(splitShipment);
		CartItem cartItem = cartItemService.getCartItemByID(commodity.getCartItemId());
        SalPromotion salPromotion;
        if(activityID!=null&&!activityID.equals("")) {
            salPromotion = salPromotionService.getSalPromotionByID(activityID);
        } else {
            salPromotion = null;
        }
		CartItem cartItemNew = cartItemService.getCartItemOrder(cartItem.getCartId(),
				cartItem.getCommodityType(),salPromotion==null?"Z":salPromotion.getGroupType(),
				commodity.getItem().getGroupType());
		if(cartItemNew == null) {
			cartItemNew = new CartItem();
			cartItemNew.setCartId(cartItem.getCartId());
			if(salPromotion!=null) {
                cartItemNew.setActivityGroupType(salPromotion.getGroupType());
            } else {
			    cartItemNew.setActivityGroupType("Z");
            }
			cartItemNew.setProductGroupType(commodity.getItem().getGroupType());
			cartItemNew.setCommodityType(cartItem.getCommodityType());
			cartItemService.addCartItem(cartItemNew);
		}
		if(salPromotion!=null) {
		    commodity.setActivityId(salPromotion.getpId());
        } else {
		    commodity.setActivityId(null);
        }
		if(quantityString!=null&&!quantityString.equals("")) {
			commodity.setQuantity(new BigDecimal(quantityString));
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
		commodityService.updateCommodity(commodity);
//		Commodity commodityMayBe = commodityService.getCommodityAppoint(commodity.getActivityId(),
//                commodity.getItem().getItemNo(),commodity.getCartItemId());
//		if(commodityMayBe == null||commodityMayBe.getId().equals(commodity.getId())) {
//            commodityService.updateCommodity(commodity);
//        } else {
//		    if(commodityMayBe.getQuantity()!=null) {
//		        commodityMayBe.setQuantity(commodity.getQuantity().add(commodityMayBe.getQuantity()));
//		        commodityService.updateCommodity(commodityMayBe);
//		        commodityService.deleteCommodityByID(commodity.getId());
//            } else {
//		        return Response.getResponseMap(2,"该产品在此活动下已存在",null);
//            }
//        }
		long count = commodityService.countByCartItemID(cartItem.getCartItemId());
		if(count == 0) {
		    cartItemService.deleteCartItemByID(cartItem.getCartItemId());
        }
		return Response.getResponseMap(0,"",null);
	}

	@ResponseBody@RequestMapping("updateCurtainCartItem")
	public Map updateCurtainCartItem(@RequestBody Map<String,Object> parameters) {
		String customerType = (String) parameters.get("customerType");
		List<Map<String,Object>> curtainLists = (List<Map<String, Object>>) parameters.get("curtainLists");
		String cartItemId = (String) ((List<Map<String,Object>>)curtainLists.get(0).get("curtainCommodities")).get(0).get("cartItemId");
		curtainCommodityService.deleteCommoditiesByCartItemID(cartItemId);
		List<CurtainList> curtainListsNew = new ArrayList<>();
		{
			//进行参数注入
			List<Map<String,Object>> curtainListParameter = curtainLists;
			for (Map<String,Object> o:curtainListParameter) {
				CurtainList curtainList = new CurtainList();
				curtainList.setPartName((String) o.get("partName"));
				List<CurtainCommodity> curtainCommodities = new ArrayList<>();
				List<Map<String,Object>> commodityParameters = (List<Map<String, Object>>) o.get("curtainCommodities");
				for (Map<String,Object> commodityParameter:commodityParameters) {
					CurtainCommodity curtainCommodity;
					JSONObject inline = JSONObject.fromObject(commodityParameter);
					curtainCommodity = (CurtainCommodity) JSONObject.toBean(inline,CurtainCommodity.class);
					curtainCommodity.setStatus(CurtainCommodity.COMMODITY_EXIST_STATUS);
					curtainCommodities.add(curtainCommodity);
				}
				curtainList.setCurtainCommodities(curtainCommodities);
				curtainListsNew.add(curtainList);
			}
		}
		String price = (String) parameters.get("price");
		for (CurtainList curtainList:curtainListsNew) {
			List<CurtainCommodity> curtainCommodities = curtainList.getCurtainCommodities();
			for (CurtainCommodity curtainCommodity:curtainCommodities) {
				String itemNo = curtainCommodity.getItem().getItemNo();
				Item item = itemService.getItemByItemNO(itemNo);
				switch (customerType) {
					case "02":curtainCommodity.setPrice(item.getPriceSale());break;
					case "06":curtainCommodity.setPrice(item.getPriceFx());break;
					case "09":curtainCommodity.setPrice(item.getPriceHome());break;
					case "05":curtainCommodity.setPrice(item.getSalePrice());break;
					case "08":curtainCommodity.setPrice(item.getPriceSale());break;
					case "10":curtainCommodity.setPrice(item.getPriceSale());break;
					default:return Response.getResponseMap(1,"添加失败",null);
				}
			}
		}
		for (CurtainList curtainList:curtainListsNew) {
			List<CurtainCommodity> commodities = curtainList.getCurtainCommodities();
			for (CurtainCommodity curtainCommodity:commodities) {
				curtainCommodityService.addCommodity(curtainCommodity);
			}
		}
		return Response.getResponseMap(0,"",null);
	}

	@ResponseBody@RequestMapping("alterCurtainCartItemCount")
    public Map alterCurtainCartItem(@RequestParam("cartItemID")String cartItemID, @RequestParam("count")Integer count) {
        return Response.getResponseMap(0,"",curtainCartItemService.alterCartItemCount(cartItemID, count));
    }

    @ResponseBody@RequestMapping("alterCommodityPrice")
    public Map alterCommodityPrice(@RequestParam("commodityID")String commodityID,@RequestParam("price")BigDecimal price,
                                   @RequestParam("customerType")String customerType) {
	    if(!customerType.equals("10")) {
	        return Response.getResponseMap(2,"该账号无权限修改价格",null);
        }
	    return Response.getResponseMap(0, "", commodityService.alterCommodityPrice(commodityID, price));
    }

	private List<Map<String,Object>> dealCurtainCartItems(List<CartItem> cartItems) {
		List<Map<String,Object>> result = new ArrayList<>();
		if(cartItems == null||cartItems.size()==0) {
			return result;
		}
		Map<String,List<CartItem>> inline = new HashMap<>();
		for (CartItem cartItem:cartItems) {
			String productGroupType = cartItem.getProductGroupType();
			String activityGroupType = cartItem.getActivityGroupType();
			productGroupType = productGroupType ==null?"":productGroupType;
			activityGroupType = activityGroupType ==null?"":activityGroupType;
			String key = productGroupType+activityGroupType;
			List<CartItem> cartItemList = new ArrayList<>();
			inline.put(key,cartItemList);
		}
		for (CartItem cartItem:cartItems) {
			String productGroupType = cartItem.getProductGroupType();
			String activityGroupType = cartItem.getActivityGroupType();
			productGroupType = productGroupType ==null?"":productGroupType;
			activityGroupType = activityGroupType ==null?"":activityGroupType;
			String key = productGroupType+activityGroupType;
			List<CartItem> inlineProperty = inline.get(key);
			inlineProperty.add(cartItem);
		}
		Set<Map.Entry<String,List<CartItem>>> inlineProperty = inline.entrySet();
		for (Map.Entry<String,List<CartItem>> one : inlineProperty) {
			Map<String,Object> oneResult = new HashMap<>();
			List<CartItem> cartItemList = one.getValue();
			oneResult.put("productGroupType",cartItemList.get(0).getProductGroupType());
			oneResult.put("activityGroupType",cartItemList.get(0).getActivityGroupType());
			oneResult.put("curtainCartItems",cartItemList);
			result.add(oneResult);
		}
		return result;
	}

	private void dealCommodityEffective(List<CartItem> cartItems) {
		for (CartItem cartItem:cartItems) {
			if (cartItem.getCommodities()!=null) {
				List<Commodity> commodities = cartItem.getCommodities();
				for (Commodity commodity:commodities) {
					String activityID = commodity.getActivityId();
					SalPromotion salPromotion = salPromotionService.getSalPromotionByID(activityID);
					if(salPromotion!=null) {
						Date dateEnd = salPromotion.getDateEnd();
						if(dateEnd == null) {
							commodity.setActivityEffective(null);
						} else {
							SimpleDateFormat all = new SimpleDateFormat("yyyy-MM-dd");
							String dateEndString = all.format(dateEnd);
							dateEndString = dateEndString + " 23:59:59";
							Timestamp timestamp = Timestamp.valueOf(dateEndString);
							long time = timestamp.getTime();
							if(time<System.currentTimeMillis()) {
								commodity.setActivityEffective(false);
							} else {
								commodity.setActivityEffective(true);
							}
						}
					} else {
						commodity.setActivityEffective(null);
					}
				}
			} else {
				CurtainCartItem curtainCartItem = (CurtainCartItem) cartItem;
				List<CurtainList> curtainLists = curtainCartItem.getCurtainLists();
				for (CurtainList curtainList:curtainLists) {
					List<CurtainCommodity> curtainCommodities = curtainList.getCurtainCommodities();
					for (CurtainCommodity curtainCommodity:curtainCommodities) {
						String activityID = curtainCommodity.getActivityId();
						SalPromotion salPromotion = salPromotionService.getSalPromotionByID(activityID);
						if(salPromotion!=null) {
							Date dateEnd = salPromotion.getDateEnd();
							if(dateEnd==null) {
								curtainCommodity.setActivityEffective(null);
							} else {
								SimpleDateFormat all = new SimpleDateFormat("yyyy-MM-dd");
								String dateEndString = all.format(dateEnd);
								dateEndString = dateEndString + " 23:59:59";
								Timestamp timestamp = Timestamp.valueOf(dateEndString);
								long time = timestamp.getTime();
								if(time<System.currentTimeMillis()) {
									curtainCommodity.setActivityEffective(false);
								} else {
									curtainCommodity.setActivityEffective(true);
								}
							}
						} else {
							curtainCommodity.setActivityEffective(null);
						}
					}
				}
			}
		}
	}

	private void setCartItemsCID(List<CartItem> cartItems,String CID) {
		for (CartItem cartItem:cartItems) {
			cartItem.setCID(CID);
		}
	}

}