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
import java.io.IOException;

@WebServlet(name = "editAccount",urlPatterns = "/editAccount")
public class EditAccountServlet extends HttpServlet {
    AccountService accountService = new AccountService();
    Account account;
    private static final Logger logger = LogManager.getLogger(EditAccountServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        account = new Account();
        account.setUsername(req.getParameter("username"));
        account.setPassword(req.getParameter("password"));
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

        accountService.updateAccount(account);

        logger.info(account.getUsername()+" updated his/her information.");
        account = accountService.getAccount(account.getUsername());
        //myList = catalogService.getProductListByCategory(account.getFavouriteCategoryId());

        //message: save the new info
    }
}
