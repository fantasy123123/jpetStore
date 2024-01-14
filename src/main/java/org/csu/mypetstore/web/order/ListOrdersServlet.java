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
import java.util.List;

@WebServlet(name = "listOrders",urlPatterns = "/listOrders")
public class ListOrdersServlet extends HttpServlet {
    private static final String LIST_ORDERS = "/WEB-INF/jsp/order/ListOrders.jsp";
    private OrderService orderService = new OrderService();
    private List<Order> orderList;
    private static final Logger logger = LogManager.getLogger(ListOrdersServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account)session.getAttribute("account");
        orderList = orderService.getOrdersByUsername(account.getUsername());
        session.setAttribute("orderList",orderList);

        logger.info(account.getUsername() + " view his/her previous orders.");
        req.getRequestDispatcher(LIST_ORDERS).forward(req,resp);
    }
}
