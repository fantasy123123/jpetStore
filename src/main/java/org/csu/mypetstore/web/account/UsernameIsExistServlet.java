package org.csu.mypetstore.web.account;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "usernameIsExist",urlPatterns = "/usernameIsExist")
public class UsernameIsExistServlet extends HttpServlet {
    AccountService service;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        System.out.println(username);

        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();

        if(username.length()!=0){
            service = new AccountService();
            Account account = service.getAccount(username);
            boolean result;
            if(account==null)
                result = false;
            else
                result = true;
            if(result){
                out.print("Exist");
            }
            else{
                out.print("Not Exist");
            }
            out.flush();
            out.close();
        }
        else{
            out.print("NULL");
            out.flush();
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
