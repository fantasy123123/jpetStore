package org.csu.mypetstore.web.catalog;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MainServlet extends javax.servlet.http.HttpServlet{
    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
    private static final Logger logger = LogManager.getLogger(MainServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Enter the pet store!");
        HttpSession session = req.getSession();
        String right = session.getAttribute("isSignIn")==null ? "true":"false";
        logger.info(right);
//        System.out.println(right);
       req.getRequestDispatcher(MAIN).forward(req,resp);
    }
}
