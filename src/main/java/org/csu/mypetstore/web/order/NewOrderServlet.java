package org.csu.mypetstore.web.order;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.service.OrderService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "newOrder",urlPatterns = "/newOrder")
public class NewOrderServlet extends HttpServlet {
    private static final String SHIPPING = "/WEB-INF/jsp/order/ShippingForm.jsp";
    private static final String CONFIRM_ORDER = "/WEB-INF/jsp/order/ConfirmOrder.jsp";
    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/ViewOrder.jsp";
    private static final String ERROR = "WEB-INF/jsp/common/Error.jsp";
    private static final Logger logger = LogManager.getLogger(NewOrderServlet.class);

    OrderService orderService = new OrderService();
    private Account account;
    private Cart cart;
    private Order order;
    private boolean shippingAddressRequired;
    private boolean newOrderFormSubmited;
    private boolean shippAddressSubmited;
    private boolean confirmed;
//    private List<Order> orderList;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        account = (Account)session.getAttribute("account");
        cart = (Cart)session.getAttribute("cart");
        order = (Order)session.getAttribute("order");
        newOrderFormSubmited = req.getParameter("newOrderFormSubmited")==null ? false:true;
        shippAddressSubmited = req.getParameter("shippAddressSubmited")==null ? false:true;
        shippingAddressRequired = req.getParameter("shippingAddressRequired")==null ? false:true;
        confirmed = req.getParameter("confirmed")==null ? false:true;

        if(newOrderFormSubmited){
            logger.info(account.getUsername() + "submited the new order form.");
            newOrderFormSubmited = false;
            order.setCardType(req.getParameter("order.cardType"));
            order.setCreditCard(req.getParameter("order.creditCard"));
            order.setExpiryDate(req.getParameter("order.expiryDate"));
            order.setStatus(req.getParameter("Deal"));
            //            order.setLocale(req.getParameter(21));

            order.setBillAddress1(req.getParameter("order.billAddress1"));
            order.setBillAddress2(req.getParameter("order.billAddress1"));
            order.setBillCity(req.getParameter("order.billCity"));
            order.setBillCountry(req.getParameter("order.billCountry"));
            order.setBillState(req.getParameter("order.billState"));
            order.setBillToFirstName(req.getParameter("order.billToFirstName"));
            order.setBillToLastName(req.getParameter("order.billToLastName"));
            order.setBillZip(req.getParameter("order.billZip"));
            session.setAttribute("order",order);
        }
        if (shippAddressSubmited){
            shippAddressSubmited = false;
            logger.info(account.getUsername() + " submited the shipping-info form.");
            order.setShipAddress1(req.getParameter("order.shipToFirstName"));
            order.setShipAddress2(req.getParameter("order.shipToFirstName"));
            order.setShipCity(req.getParameter("order.shipCity"));
            order.setShipCountry(req.getParameter("order.shipCountry"));
            order.setShipState(req.getParameter("order.shipState"));
            order.setShipToFirstName(req.getParameter("order.shipAddress1"));
            order.setShipToLastName(req.getParameter("order.shipAddress2"));
            order.setShipZip(req.getParameter("order.shipZip"));
            session.setAttribute("order",order);
        }

        if(shippingAddressRequired){
            logger.info(account.getUsername() + " wanted his/her pet(s) to ship to another place.");
            session.setAttribute("shippingAddressRequired",shippingAddressRequired);
            shippingAddressRequired = false;
            req.getRequestDispatcher(SHIPPING).forward(req,resp);
        }else if(!confirmed){
            req.getRequestDispatcher(CONFIRM_ORDER).forward(req,resp);
        }else if(order != null){
            //bill
            order.setBillAddress1(req.getParameter("order.billAddress1"));
            order.setBillAddress2(req.getParameter("order.billAddress1"));
            order.setBillCity(req.getParameter("order.billCity"));
            order.setBillCountry(req.getParameter("order.billCountry"));
            order.setBillState(req.getParameter("order.billState"));
            order.setBillToFirstName(req.getParameter("order.billToFirstName"));
            order.setBillToLastName(req.getParameter("order.billToLastName"));
            order.setBillZip(req.getParameter("order.billZip"));

            shippingAddressRequired = session.getAttribute("shippingAddressRequired")==null ? false:true;

            //shipping
            if(shippingAddressRequired){
                order.setShipAddress1(req.getParameter("order.shipToFirstName"));
                order.setShipAddress2(req.getParameter("order.shipToFirstName"));
                order.setShipCity(req.getParameter("order.shipCity"));
                order.setShipCountry(req.getParameter("order.shipCountry"));
                order.setShipState(req.getParameter("order.shipState"));
                order.setShipToFirstName(req.getParameter("order.shipAddress1"));
                order.setShipToLastName(req.getParameter("order.shipAddress2"));
                order.setShipZip(req.getParameter("order.shipZip"));
            }else{
                logger.info(account.getUsername() + " confirmed the order.");
                order.setShipAddress1(order.getBillAddress1());
                order.setShipAddress2(order.getBillAddress2());
                order.setShipCity(order.getBillCity());
                order.setShipCountry(order.getBillCountry());
                order.setShipState(order.getBillState());
                order.setShipToFirstName(order.getBillToFirstName());
                order.setShipToLastName(order.getBillToLastName());
                order.setShipZip(order.getBillZip());
            }

            session.setAttribute("order",order);
            orderService.insertOrder(order);
            session.setAttribute("cart",null);

            //setMessage("Thank you, your order has been submitted.");
            req.getRequestDispatcher(VIEW_ORDER).forward(req,resp);
        }
        else{
            logger.warn("An error occurred processing your order (order was null).");
            String msg = "An error occurred processing your order (order was null).";
            session.setAttribute("message",msg);
            req.getRequestDispatcher(ERROR).forward(req,resp);
        }
    }
}
