package com.yulan.pojo;

import java.util.Map;
import java.util.Objects;

public class Cart {

	private String cartId;
	private String customerId;
	private Map<String,Object> cartItems;

	public String getCartId() {
		return this.cartId;
	}

	public void setCartId(String cartId) {
		this.cartId=cartId;
	}

	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId=customerId;
	}

	public Map<String, Object> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Map<String, Object> cartItems) {
		this.cartItems = cartItems;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Cart)) return false;
		Cart cart = (Cart) o;
		return Objects.equals(getCartId(), cart.getCartId()) &&
				Objects.equals(getCustomerId(), cart.getCustomerId()) &&
				Objects.equals(getCartItems(), cart.getCartItems());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCartId(), getCustomerId(), getCartItems());
	}

	@Override
	public String toString() {
		return "Cart{" +
				"cartId='" + cartId + '\'' +
				", customerId='" + customerId + '\'' +
				", cartItems=" + cartItems +
				'}';
	}
}