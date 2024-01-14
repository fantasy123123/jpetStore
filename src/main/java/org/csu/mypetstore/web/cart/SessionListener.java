package org.csu.mypetstore.web.cart;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.service.CartService;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener{
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();

        CartService cartService = new CartService();
        Cart cart_pre = cartService.getCartByUserId("A user");
        session.setAttribute("cart",cart_pre);
        session.setAttribute("isSignIn",false);
        System.out.println("created");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        CartService cartService = new CartService();
        Account account = (Account) session.getAttribute("account");
        Cart cart = (Cart)session.getAttribute("cart");
        if(account != null){
            cartService.deleteCartItemByUsername(account.getUsername());

            if(cart != null){
                for (CartItem c:cart.getCartItemList()) {
                    cartService.insertCartItem(account.getUsername(),c);
                }
            }
        }
        System.out.println("destroy");
    }
}
