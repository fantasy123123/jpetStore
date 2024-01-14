package org.csu.mypetstore.web.order;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "viewOrder",urlPatterns = "/viewOrder")
public class ViewOrderServlet extends HttpServlet {
    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/ViewOrder.jsp";
    private static final String ERROR = "WEB-INF/jsp/common/Error.jsp";
    private static final Logger logger = LogManager.getLogger(ViewOrderServlet.class);

    private Order order;
    OrderService orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Account account = (Account)session.getAttribute("account");

        int  orderId = Integer.parseInt(req.getParameter("orderId"));
//        int orderId = (int)session.getAttribute("orderId");
        if(orderId != 0){
            order = orderService.getOrder(orderId);
            session.setAttribute("order",order);
        }


        if(account.getUsername().equals(order.getUsername())){
            logger.info(account.getUsername() + " views his/her new order.");
            req.getRequestDispatcher(VIEW_ORDER).forward(req,resp);
        }else{
            order = null;
            logger.info("Only view your own orders.");
            String msg = "You may only view your own orders.";
            session.setAttribute("message",msg);
            req.getRequestDispatcher(ERROR).forward(req,resp);
        }
    }
}
