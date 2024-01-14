package org.csu.mypetstore.web.cart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csu.mypetstore.domain.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "checkOut",urlPatterns = "/checkOut")
public class CheckOutServlet extends HttpServlet {
    private static final String CHECK_OUT = "/WEB-INF/jsp/cart/Checkout.jsp";
    private static final Logger logger = LogManager.getLogger(CheckOutServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account)session.getAttribute("account");
        if(account != null){
            logger.info(account.getUsername() +" checked out.");
        }

        req.getRequestDispatcher(CHECK_OUT).forward(req,resp);
    }
}
