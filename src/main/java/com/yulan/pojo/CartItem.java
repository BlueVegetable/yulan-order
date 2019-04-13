package com.yulan.pojo;

import java.util.List;
import java.util.Objects;

public class CartItem {

	private String cartItemId;
	private String commodityType;
	private String activityGroupType;
	private String productGroupType;
	private List<Commodity> commodities;
	/**
	 * @deprecated 购物车生成订单后会改变的一个状态
	 */
	private Boolean checked;
 	private String cartId;

	public String getCartItemId() {
		return this.cartItemId;
	}

	public void setCartItemId(String cartItemId) {
		this.cartItemId=cartItemId;
	}

	public String getCommodityType() {
		return this.commodityType;
	}

	public void setCommodityType(String commodityType) {
		this.commodityType=commodityType;
	}

	public String getActivityGroupType() {
		return this.activityGroupType;
	}

	public void setActivityGroupType(String activityGroupType) {
		this.activityGroupType=activityGroupType;
	}

	public String getProductGroupType() {
		return this.productGroupType;
	}

	public void setProductGroupType(String productGroupType) {
		this.productGroupType=productGroupType;
	}

	public List<Commodity> getCommodities() {
		return commodities;
	}

	public void setCommodities(List<Commodity> commodities) {
		this.commodities = commodities;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getCartId() {
		return this.cartId;
	}

	public void setCartId(String cartId) {
		this.cartId=cartId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CartItem)) return false;
		CartItem cartItem = (CartItem) o;
		return Objects.equals(getCartItemId(), cartItem.getCartItemId()) &&
				Objects.equals(getCommodityType(), cartItem.getCommodityType()) &&
				Objects.equals(getActivityGroupType(), cartItem.getActivityGroupType()) &&
				Objects.equals(getProductGroupType(), cartItem.getProductGroupType()) &&
				Objects.equals(getCommodities(), cartItem.getCommodities()) &&
				Objects.equals(getCartId(), cartItem.getCartId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCartItemId(), getCommodityType(), getActivityGroupType(), getProductGroupType(), getCommodities(), getCartId());
	}

	@Override
	public String toString() {
		return "CartItem{" +
				"cartItemId='" + cartItemId + '\'' +
				", commodityType='" + commodityType + '\'' +
				", activityGroupType='" + activityGroupType + '\'' +
				", productGroupType='" + productGroupType + '\'' +
				", commodities=" + commodities +
				", cartId='" + cartId + '\'' +
				'}';
	}
}