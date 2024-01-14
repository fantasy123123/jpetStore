package org.csu.mypetstore.web.catalog;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ViewCategoryServlet extends HttpServlet {
    private static final String VIEW_CATEGORY = "/WEB-INF/jsp/catalog/Category.jsp";
    private String categoryId;
    private static final Logger logger = LogManager.getLogger(ViewCategoryServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        categoryId = req.getParameter("categoryId");

        CatalogService catalogService = new CatalogService();
        Category category = catalogService.getCategory(categoryId);
        List<Product> productList = catalogService.getProductListByCategory(categoryId);

        HttpSession session = req.getSession();
        session.setAttribute("category",category);
        session.setAttribute("productList",productList);

        logger.info("A user views category: " + categoryId);
        req.getRequestDispatcher(VIEW_CATEGORY).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req, resp);
    }
}
