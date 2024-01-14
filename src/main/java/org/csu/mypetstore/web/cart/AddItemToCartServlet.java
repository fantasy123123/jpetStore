package org.csu.mypetstore.web.cart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.service.CartService;
import org.csu.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "addItemToCart",urlPatterns = "/addItemToCart")
public class AddItemToCartServlet extends HttpServlet {
    //1. 处理完功能后的跳转页面
    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";
    private static final Logger logger = LogManager.getLogger(AddItemToCartServlet.class);

    //2. 定义处理该请求所需要的数据
    private String workingItemId;
    private Cart cart;
    private Account account;

    //3. 是否需要调用业务逻辑层
    private CatalogService catalogService = new CatalogService();
    private CartService cartService = new CartService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        workingItemId = req.getParameter("workingItemId");

        HttpSession session = req.getSession();
        cart = (Cart)session.getAttribute("cart");
        account = (Account)session.getAttribute("account");

        if(cart == null){
            cart = new Cart();
        }

        if(cart.containsItemId(workingItemId)){
            cart.incrementQuantityByItemId(workingItemId);
        }
        else{
            // isInStock is a "real-time" property that must be updated
            // every time an item is added to the cart, even if other
            // item details are cached.
            boolean isInStock = catalogService.isItemInStock(workingItemId);
            Item item = catalogService.getItem(workingItemId);
            cart.addItem(item, isInStock);
        }
        session.setAttribute("cart",cart);

        logger.info( (account==null? "A user" : account.getUsername()) + " adds " + workingItemId + " to cart.");
        req.getRequestDispatcher(VIEW_CART).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
