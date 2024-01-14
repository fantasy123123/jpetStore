package org.csu.mypetstore.web.order;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.web.account.NewAccountFormServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "newOrderForm",urlPatterns = "/newOrderForm")
public class NewOrderFormServlet extends HttpServlet {
    private static final String NEW_ORDER = "/WEB-INF/jsp/order/NewOrderForm.jsp";
    private static final String LOGIN_FORM = "/WEB-INF/jsp/account/SignonForm.jsp";
    private static final String ERROR = "WEB-INF/jsp/common/Error.jsp";
    private static final Logger logger = LogManager.getLogger(NewAccountFormServlet.class);

    private Order order = new Order();
    private boolean shippingAddressRequired;
    private boolean confirmed;

    static {
        List<String> cardList = new ArrayList<String>();
        cardList.add("Visa");
        cardList.add("MasterCard");
        cardList.add("American Express");
//        CARD_TYPE_LIST = Collections.unmodifiableList(cardList);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account)session.getAttribute("account");
        Cart cart = (Cart)session.getAttribute("cart");
        //req.getRequestDispatcher(NEW_ORDER).forward(req,resp);
        clear();

        if(account == null){
            //set message
            logger.info("A user trys to buy a item, but doesn't sign in.");
            session.setAttribute("currentPath","viewCart");
            req.getRequestDispatcher(LOGIN_FORM).forward(req,resp);
        }else if(cart != null){
            order.initOrder(account,cart);
            session.setAttribute("order",order);
            logger.info(account.getUsername() + " then fills the information to pay the bill.");
            req.getRequestDispatcher(NEW_ORDER).forward(req,resp);
        }else{
            // set message
            logger.warn("An order could not be created because a cart could not be found.");
            String msg = "An order could not be created because a cart could not be found.";
            session.setAttribute("message",msg);
            req.getRequestDispatcher(ERROR).forward(req,resp);
        }
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public boolean isShippingAddressRequired() {
        return shippingAddressRequired;
    }

    public void setShippingAddressRequired(boolean shippingAddressRequired) {
        this.shippingAddressRequired = shippingAddressRequired;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public void clear() {
        order = new Order();
        shippingAddressRequired = false;
        confirmed = false;
    }
}
