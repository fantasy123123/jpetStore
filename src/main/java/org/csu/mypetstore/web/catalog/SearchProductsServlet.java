package org.csu.mypetstore.web.catalog;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet (name = "searchProducts", urlPatterns = "/searchProducts")
public class SearchProductsServlet extends HttpServlet {
    private static final String  SEARCH_PRODUCTS= "/WEB-INF/jsp/catalog/SearchProducts.jsp";
    private static final Logger logger = LogManager.getLogger(SearchProductsServlet.class);
    String keyword;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        keyword = req.getParameter("keyword");
        CatalogService catalogService = new CatalogService();
        List<Product> productsBySearch = new ArrayList<Product>();
        productsBySearch = catalogService.searchProductList(keyword);
        HttpSession session = req.getSession();
        session.setAttribute("productsBySearch",productsBySearch);

        logger.info("A user searchs for keyword: " + keyword);
        req.getRequestDispatcher(SEARCH_PRODUCTS).forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
