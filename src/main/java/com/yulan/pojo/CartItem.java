package com.yulan.pojo;

import java.math.BigInteger;
import java.util.Objects;

public class CartItem {

	private String cartItemId;
	private Item item;
	private BigInteger quantity;
	private String cartID;

	public String getCartItemId() {
		return this.cartItemId;
	}

	public void setCartItemId(String cartItemId) {
		this.cartItemId=cartItemId;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public BigInteger getQuantity() {
		return this.quantity;
	}

	public void setQuantity(BigInteger quantity) {
		this.quantity=quantity;
	}

	public String getCartID() {
		return cartID;
	}

	public void setCartID(String cartID) {
		this.cartID = cartID;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CartItem)) return false;
		CartItem cartItem = (CartItem) o;
		return Objects.equals(getCartItemId(), cartItem.getCartItemId()) &&
				Objects.equals(getItem(), cartItem.getItem()) &&
				Objects.equals(getQuantity(), cartItem.getQuantity()) &&
				Objects.equals(getCartID(), cartItem.getCartID());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCartItemId(), getItem(), getQuantity(), getCartID());
	}
}