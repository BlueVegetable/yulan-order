package com.yulan.dao;

public interface CurtainCartItemDao extends CartItemDao {
    int alterCurtainCartItem(String cartItemID,Integer count);
}