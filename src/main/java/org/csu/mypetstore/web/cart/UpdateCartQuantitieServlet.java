package org.csu.mypetstore.web.cart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.CartItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;

@WebServlet(name = "updateCartQuantities",urlPatterns = "/updateCartQuantities")
public class UpdateCartQuantitieServlet extends HttpServlet {
    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";
    private static final Logger logger = LogManager.getLogger(UpdateCartQuantitieServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart)session.getAttribute("cart");
        Account account = (Account)session.getAttribute("account");
        Iterator<CartItem> cartItems = cart.getAllCartItems();
        while(cartItems.hasNext()){
            CartItem cartItem = (CartItem)cartItems.next();
            String itemId = cartItem.getItem().getItemId();
            try {
                int quantity = Integer.parseInt((String)req.getParameter(itemId));
                cart.setQuantityByItemId(itemId,quantity);
                if(quantity < 1)
                    cartItems.remove();
                }catch (Exception e){
                //ignore parse exceptions on purpose
            }
        }
        logger.info(account.getUsername() + " updates his/her cart.");
        req.getRequestDispatcher(VIEW_CART).forward(req,resp);
    }
}
