package com.yulan.pojo;

import java.util.List;
import java.util.Objects;

public class Cart {

	private String cartId;
	private List<CartItem> cartItems;
	private String customerId;

	public Cart() {

	}

	public Cart(String customerId) {
		this.customerId = customerId;
	}

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

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Cart)) return false;
		Cart cart = (Cart) o;
		return getCartId().equals(cart.getCartId()) &&
				Objects.equals(getCartItems(), cart.getCartItems()) &&
				getCustomerId().equals(cart.getCustomerId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCartId(), getCartItems(), getCustomerId());
	}
}