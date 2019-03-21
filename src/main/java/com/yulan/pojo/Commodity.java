package com.yulan.pojo;

import java.math.BigDecimal;
import java.util.Objects;

public class Commodity {

	private String id;
	private BigDecimal price;
	private String activityId;
	private BigDecimal activityPrice;
	private Item item;
	private String cartItemId;
	private BigDecimal quantity;
	private BigDecimal width;
	private BigDecimal height;
	private String note;
	private String unit;
	private Integer splitShipment;

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

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getWidth() {
		return width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

	public BigDecimal getHeight() {
		return height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getSplitShipment() {
		return splitShipment;
	}

	public void setSplitShipment(Integer splitShipment) {
		this.splitShipment = splitShipment;
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
				Objects.equals(getCartItemId(), commodity.getCartItemId()) &&
				Objects.equals(getQuantity(), commodity.getQuantity()) &&
				Objects.equals(getWidth(), commodity.getWidth()) &&
				Objects.equals(getHeight(), commodity.getHeight()) &&
				Objects.equals(getNote(), commodity.getNote()) &&
				Objects.equals(getUnit(), commodity.getUnit());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getPrice(), getActivityId(), getActivityPrice(), getItem(), getCartItemId(), getQuantity(), getWidth(), getHeight(), getNote(), getUnit());
	}

	@Override
	public String toString() {
		return "Commodity{" +
				"id='" + id + '\'' +
				", price=" + price +
				", activityId='" + activityId + '\'' +
				", activityPrice=" + activityPrice +
				", item=" + item +
				", cartItemId='" + cartItemId + '\'' +
				", quantity=" + quantity +
				", width=" + width +
				", height=" + height +
				'}';
	}
}