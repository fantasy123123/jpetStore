package org.csu.mypetstore.web.account;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "signOut",urlPatterns = "/signOut")
public class SignOutServlet extends HttpServlet {
    private CartService cartService = new CartService();
    private static final String VIEW_MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
    private static final Logger logger = LogManager.getLogger(SignOutServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Account account = (Account) session.getAttribute("account");
        Cart cart = (Cart)session.getAttribute("cart");
        if(account != null){
            logger.info(account.getUsername() + " signed out.");
            cartService.deleteCartItemByUsername(account.getUsername());
        }
        if(cart != null){
            for (CartItem c:cart.getCartItemList()) {
                cartService.insertCartItem(account.getUsername(),c);
            }
        }

        session.setAttribute("account",null);
        session.setAttribute("cart",null);
        session.setAttribute("isSignIn",false);

        req.getRequestDispatcher(VIEW_MAIN).forward(req,resp);

        //message: sign out now
    }
}
