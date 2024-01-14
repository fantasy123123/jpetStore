package org.csu.mypetstore.web.account;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "newAccount",urlPatterns = "/newAccount")
public class NewAccountServlet extends HttpServlet {
    AccountService service = new AccountService();
    Account account = null;
    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
    private static final String NEW_ACCOUNT = "/WEB-INF/jsp/account/NewAccountForm.jsp";
    private static final String ERROR = "WEB-INF/jsp/common/Error.jsp";
    private static final Logger logger = LogManager.getLogger(NewAccountServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("isSignIn",true);
        account = new Account();
        account.setUsername(req.getParameter("username"));

        String password = req.getParameter("password");
        String repeatPwd = req.getParameter("repeatedPassword");
        if(!password.equals(repeatPwd)){
            //message
            session.setAttribute("message","The two passwords you typed do not match.");
            req.getRequestDispatcher(ERROR).forward(req,resp);
        }

        account.setPassword(password);
        account.setFirstName(req.getParameter("account.firstName"));
        account.setLastName(req.getParameter("account.lastName"));
        account.setEmail(req.getParameter("account.email"));
        account.setPhone(req.getParameter("account.phone"));
        account.setAddress1(req.getParameter("account.address1"));
        account.setAddress2(req.getParameter("account.address2"));
        account.setCity(req.getParameter("account.city"));
        account.setState(req.getParameter("account.state"));
        account.setZip(req.getParameter("account.zip"));
        account.setCountry(req.getParameter("account.country"));
        account.setLanguagePreference(req.getParameter("account.languagePreference"));
        account.setFavouriteCategoryId(req.getParameter("account.favouriteCategoryId"));

        account.setListOption(req.getParameter("account.listOption")==null ? false:true);
        account.setBannerOption(req.getParameter("account.bannerOption")==null? false:true);
//        account.setBannerName(req.getParameter(""));
        session.setAttribute("account",account);
        service.insertAccount(account);
        logger.info(account.getUsername() + " signs up successfully then enters the store.");
        req.getRequestDispatcher(MAIN).forward(req,resp);
    }
}
