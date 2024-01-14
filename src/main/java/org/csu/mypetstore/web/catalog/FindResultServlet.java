package org.csu.mypetstore.web.catalog;

import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "findResultServlet",urlPatterns = "/findResultServlet")
public class FindResultServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取搜索框的输入值
        String keyword = req.getParameter("keyword");
        //调用相应业务
        CatalogService service = new CatalogService();
        List<Product> productList = service.searchProductList(keyword);

        resp.setContentType("text/xml");
        PrintWriter out = resp.getWriter();

        //返回结果
        String res = "";
        for(int i=0; i<productList.size(); i++){
            if(i>0){
                res += "," + productList.get(i);
            }
            else{
                res += productList.get(i);
            }
        }

        //System.out.println(productList);
        out.write(res);

        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
