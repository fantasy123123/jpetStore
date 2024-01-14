package org.csu.mypetstore.web.catalog;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.domain.Item;
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

@WebServlet(name = "viewProduct",urlPatterns = "/viewProduct")
public class ViewProductServlet extends HttpServlet {
    private  static final String VIEW_PRODUCT = "/WEB-INF/jsp/catalog/Product.jsp";
    private String productId;
    private static final Logger logger = LogManager.getLogger(ViewProductServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        productId = req.getParameter("productId");
        CatalogService catalogService = new CatalogService();
        Product product = catalogService.getProduct(productId);
        List<Item> itemList = catalogService.getItemListByProduct(productId);

        HttpSession session = req.getSession();
        session.setAttribute("product",product);
        session.setAttribute("itemList",itemList);
//        System.out.println(itemList.size());

        logger.info("A user views product: " + productId);
        req.getRequestDispatcher(VIEW_PRODUCT).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
