package org.csu.mypetstore.web.cart;

import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.CartItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

@WebServlet(name = "updateCartServlet",urlPatterns = "/updateCartServlet")
public class UpdateCartServlet extends HttpServlet {
    private Cart cart;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从对话中获取购物车
        HttpSession session = req.getSession();
        cart = (Cart)session.getAttribute("cart");


        String s = req.getParameter("quantity");
        System.out.print(s + " ");
        String quantity[] = s.split(",");
        int i=0;  //"quantity" ---length

        String quantityAll = "";

        Iterator<CartItem> cartItemIterator = cart.getAllCartItems();

        while(cartItemIterator.hasNext()){
            CartItem cartItem = cartItemIterator.next();
            String itemId = cartItem.getItem().getItemId();

             //try{
                //for(int i=0;i<quantity.length;) {
            System.out.println(quantity[i] + " ");

            cart.setQuantityByItemId(itemId, Integer.parseInt(quantity[i]));
            System.out.println(cartItem.getQuantity() + " ");

            if (Integer.parseInt(quantity[i]) < 1) {
                cartItemIterator.remove();
            }

            quantityAll += quantity[i] +","+cartItem.getTotal()+","+cart.getSubTotal()+",";

            i++;
        }

             //}
             //catch (Exception e) {
              //   e.printStackTrace();
             //}
            //}
        //session.setAttribute("cart",cart);


        /*
        //Cart cart1 = (Cart)session.getAttribute("cart");
        Iterator<CartItem> cartItemIterator1 = cart.getAllCartItems();

        while(cartItemIterator1.hasNext()){
            CartItem cartItem1 = cartItemIterator1.next();
            System.out.println(cartItem1.getItem().getItemId());
            int quantity1 = cartItem1.getQuantity();

            System.out.println(quantity1 + " ");


        }*/
        System.out.println(quantityAll);

        resp.setContentType("text/xml");
        PrintWriter out = resp.getWriter();
        out.write(quantityAll);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
