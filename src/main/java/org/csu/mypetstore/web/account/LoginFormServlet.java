package org.csu.mypetstore.web.account;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

@WebServlet(name = "loginForm",urlPatterns = "/loginForm")
public class LoginFormServlet extends HttpServlet {
    private static final String LOGIN_FORM = "/WEB-INF/jsp/account/SignonForm.jsp";
//    private CartService cartService = new CartService();
    private static final Logger logger = LogManager.getLogger(LoginFormServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String path = req.getServletPath();

//        System.out.println(path);
//        HttpSession session = req.getSession();
//        String path = (String)session.getAttribute("currentPath");
        logger.info("A user trys to sign in.");
        req.getRequestDispatcher(LOGIN_FORM).forward(req,resp);
    }
}
