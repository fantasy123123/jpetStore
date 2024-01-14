package org.csu.mypetstore.web.account;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "logIn",urlPatterns = "/login")
public class LogInServlet extends HttpServlet {
    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
    private static final String ERROR = "WEB-INF/jsp/common/Error.jsp";
    AccountService accountService = new AccountService();
    CartService cartService = new CartService();
    Account account;
//    String message;
    private static final Logger logger = LogManager.getLogger(LogInServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String info;
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if(username==null || password==null || username.equals("") || password.equals("")){
            session.setAttribute("isSignIn",false);
            logger.info("Username or password is empty.  Signon failed");
        }
        else{
            account = accountService.getAccount(username,password);
            if(account == null){
                info = "false";     //登录失败
                session.setAttribute("isSignIn",false);
                logger.info("Invalid username or password.  Sign in failed.");
            }
            else{
                session.setAttribute("isSignIn",true);
                String currentPath = session.getAttribute("currentPath") == null ? "main":(String)session.getAttribute("currentPath");
                session.setAttribute("currentPath",null);


                logger.info("User "+account.getUsername()+" signs in successfully!");
                session.setAttribute("account",account);

                info = "true," + currentPath;
                //加载以前的购物车
                Cart cart_pre = cartService.getCartByUserId(username);
                Cart cart = (Cart)session.getAttribute("cart");
                session.setAttribute("cart",cart_pre);
                session.setAttribute("cart_new",cart);

                if(cart.getAllCartItems().hasNext()){
                    info = "UpdateCartOrNot," + currentPath;
                }
            }

            boolean right = session.getAttribute("isSignIn")==null ? true:false;
            System.out.println("login"+right);
            out.print(info);
            out.flush();
            out.close();
        }

    }
}
