package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.domain.LineItem;
import org.csu.mypetstore.persistence.AccountDAO;
import org.csu.mypetstore.persistence.CartItemDAO;
import org.csu.mypetstore.persistence.LineItemDAO;
import org.csu.mypetstore.persistence.impl.AccountDAOImpl;
import org.csu.mypetstore.persistence.impl.CartItemDAOImpl;
import org.csu.mypetstore.persistence.impl.LineItemDAOImpl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CartService {
    private CartItemDAO cartItemDAO;

    public CartService() {
        cartItemDAO = new CartItemDAOImpl();
    }

    public Cart getCartByUserId(String userId){
        List<CartItem> cartItems= cartItemDAO.getCartItemsByUserId(userId);
        Cart cart = new Cart();
        for (CartItem c: cartItems) {
            cart.addItem(c.getItem(),c.isInStock());
        }
        return cart;
    }

    public void insertCartItem(String username,CartItem cartItem){
        cartItemDAO.insertCartItem(username,cartItem);
    }

    public void deleteCartItemByUsername(String username){
        cartItemDAO.deleteCartItemByUsername(username);
    }


}
