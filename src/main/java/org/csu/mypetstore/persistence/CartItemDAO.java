package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.CartItem;

import java.util.List;

public interface CartItemDAO {
    List<CartItem> getCartItemsByUserId(String userId);

    void insertCartItem(String username,CartItem cartItem);

    void deleteCartItemByUsername(String username);
}
