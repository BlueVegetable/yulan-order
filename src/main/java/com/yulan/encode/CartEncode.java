package com.yulan.encode;

import com.yulan.dao.CartDao;
import com.yulan.pojo.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartEncode {

    @Autowired
    private CartDao cartDao;

    public boolean addCart(Cart cart) {
        return cartDao.addCart(cart)>0;
    }

    public boolean existCart(String CID) {
        return cartDao.countCartByCID(CID) > 0;
    }

    public Cart getSimpleCartByID(String cartID) {
        return cartDao.getCartByID(cartID);
    }

    public Cart getSimpleCartByCID(String CID) {
        return cartDao.getCartByCID(CID);
    }

    public Cart getCartByCID(String CID) {
        Cart cart = getSimpleCartByCID(CID);
        return cart;
    }
}
