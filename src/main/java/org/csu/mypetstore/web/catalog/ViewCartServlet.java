package org.csu.mypetstore.web.catalog;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "viewCart",urlPatterns = "/viewCart")
public class ViewCartServlet extends HttpServlet {
    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";
    private Cart cart;
    private static final Logger logger = LogManager.getLogger(ViewCartServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        cart = (Cart)session.getAttribute("cart");
        Account account = (Account)session.getAttribute("account");

        if(cart == null){
            cart = new Cart();
            session.setAttribute("cart",cart);
        }

        logger.info( (account==null? "A user" : account.getUsername()) + " views his/her cart.");
        req.getRequestDispatcher(VIEW_CART).forward(req,resp);
    }
}
