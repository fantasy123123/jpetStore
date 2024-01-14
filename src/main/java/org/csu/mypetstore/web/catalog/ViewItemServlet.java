package org.csu.mypetstore.web.catalog;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

@WebServlet(name = "viewItem",urlPatterns = "/viewItem")
public class ViewItemServlet extends HttpServlet {
    private  static final String VIEW_ITEM = "/WEB-INF/jsp/catalog/Item.jsp";
    private String itemId;
    private static final Logger logger = LogManager.getLogger(ViewItemServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        itemId = req.getParameter("itemId");
        CatalogService catalogService = new CatalogService();
        Item item = catalogService.getItem(itemId);
//        System.out.println(item.getItemId());
        HttpSession session = req.getSession();
        session.setAttribute("item",item);
//        System.out.println(itemList.size());

        logger.info("A user views item: " + itemId);
        req.getRequestDispatcher(VIEW_ITEM).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
