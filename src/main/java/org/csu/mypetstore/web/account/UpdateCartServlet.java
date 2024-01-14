package org.csu.mypetstore.web.account;

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

@WebServlet(name = "loadCart",urlPatterns = "/loadCart")
public class UpdateCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        //合并两个购物车
        Cart cart_new = (Cart) session.getAttribute("cart_new");
        Cart cart = (Cart) session.getAttribute("cart");
        for (CartItem c : cart_new.getCartItemList()) {
            cart.addItem(c.getItem(), c.isInStock());
        }
        session.setAttribute("cart", cart);
    }
}
