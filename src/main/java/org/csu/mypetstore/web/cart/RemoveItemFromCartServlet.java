package org.csu.mypetstore.web.cart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "removeItemFromCart",urlPatterns = "/removeItemFromCart")
public class RemoveItemFromCartServlet extends HttpServlet {
    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";
    private static final String ERROR = "/WEB-INF/jsp/common/Error.jsp";
    private String workingItemId;
    private Cart cart;
    private static final Logger logger = LogManager.getLogger(RemoveItemFromCartServlet.class);
    private Account account;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        workingItemId = req.getParameter("workingItemId");

        HttpSession session = req.getSession();
        cart = (Cart) session.getAttribute("cart");
        account = (Account)session.getAttribute("account");
        Item item= cart.removeItemById(workingItemId);

        session.setAttribute("cart",cart);

        if(item == null){
            logger.warn("Attempted to remove null CartItem from Cart.");
            session.setAttribute("message","Attempted to remove null CartItem from Cart.");
            req.getRequestDispatcher(ERROR).forward(req,resp);
        }
        else {
            if(account != null)
                logger.info(account.getUsername() + " removed item " + workingItemId + " from cart.");
            req.getRequestDispatcher(VIEW_CART).forward(req,resp);
        }
    }
}
