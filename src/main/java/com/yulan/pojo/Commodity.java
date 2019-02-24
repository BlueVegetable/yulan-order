package com.yulan.pojo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

public class Commodity {

	private String id;
	private BigDecimal price;
	private String activityId;
	private BigDecimal activityPrice;
	private Item item;
	private String cartItemId;
	private BigInteger quantity;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id=id;
	}

	public String getActivityId() {
		return this.activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId=activityId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getActivityPrice() {
		return activityPrice;
	}

	public void setActivityPrice(BigDecimal activityPrice) {
		this.activityPrice = activityPrice;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getCartItemId() {
		return this.cartItemId;
	}

	public void setCartItemId(String cartItemId) {
		this.cartItemId=cartItemId;
	}

	public BigInteger getQuantity() {
		return quantity;
	}

	public void setQuantity(BigInteger quantity) {
		this.quantity = quantity;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Commodity)) return false;
		Commodity commodity = (Commodity) o;
		return Objects.equals(getId(), commodity.getId()) &&
				Objects.equals(getPrice(), commodity.getPrice()) &&
				Objects.equals(getActivityId(), commodity.getActivityId()) &&
				Objects.equals(getActivityPrice(), commodity.getActivityPrice()) &&
				Objects.equals(getItem(), commodity.getItem()) &&
				Objects.equals(getCartItemId(), commodity.getCartItemId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getPrice(), getActivityId(), getActivityPrice(), getItem(), getCartItemId());
	}

	@Override
	public String toString() {
		return "Commodity{" +
				"id='" + id + '\'' +
				", price='" + price + '\'' +
				", activityId='" + activityId + '\'' +
				", activityPrice='" + activityPrice + '\'' +
				", item=" + item +
				", cartItemId='" + cartItemId + '\'' +
				'}';
	}
}