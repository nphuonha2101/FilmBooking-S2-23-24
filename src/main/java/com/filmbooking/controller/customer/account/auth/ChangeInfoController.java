package com.filmbooking.controller.customer.account.auth;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.User;
import com.filmbooking.page.ClientPage;
import com.filmbooking.page.Page;
import com.filmbooking.services.impls.UserServicesImpl;
import com.filmbooking.enumsAndConstants.enums.StatusCodeEnum;
import com.filmbooking.services.logProxy.CRUDServicesLogProxy;
import com.filmbooking.utils.WebAppPathUtils;
import com.filmbooking.utils.StringUtils;
import com.filmbooking.utils.validateUtils.Regex;
import com.filmbooking.utils.validateUtils.UserRegexEnum;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(value = "/auth/change-info")

public class ChangeInfoController extends HttpServlet {
    private UserServicesImpl userServices;
    private CRUDServicesLogProxy<User> userServicesLog;
    private HibernateSessionProvider hibernateSessionProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageTitle", "changeInfoTitle");

        Page changeInfoPage = new ClientPage(
                "changeInfoTitle",
                "change-info",
                "master"
        );
        changeInfoPage.render(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        userServices = new UserServicesImpl(hibernateSessionProvider);
        userServicesLog = new CRUDServicesLogProxy<>(new UserServicesImpl(), req, hibernateSessionProvider);

        String userFullName = StringUtils.handlesInputString(req.getParameter("user-full-name"));
        String email = StringUtils.handlesInputString(req.getParameter("email"));
        String password = userServices.hashPassword(StringUtils.handlesInputString(req.getParameter("password")));

        Page changeInfoPage = new ClientPage(
                "changeInfoTitle",
                "change-info",
                "master"
        );

        // validate input
        if (!Regex.validate(UserRegexEnum.USER_EMAIL, email) || !Regex.validate(UserRegexEnum.USER_FULL_NAME, userFullName)) {
            changeInfoPage.putError(StatusCodeEnum.INVALID_INPUT.getStatusCode());
            changeInfoPage.render(req, resp);
            return;
        }


        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser != null && password.equals(loginUser.getUserPassword())) {
            // when user change email then verify email
            if (!email.equalsIgnoreCase(loginUser.getUserEmail()) && userServices.getByEmail(email) != null) {
                changeInfoPage.putError(StatusCodeEnum.EMAIL_EXISTED.getStatusCode());
                changeInfoPage.render(req, resp);
            } else {
                loginUser.setUserFullName(userFullName);
                loginUser.setUserEmail(email);
                userServicesLog.update(loginUser);

                resp.sendRedirect(WebAppPathUtils.getURLWithContextPath(req, resp, "/auth/account-info"));
            }
        } else {
            changeInfoPage.putError(StatusCodeEnum.PASSWORD_NOT_MATCH.getStatusCode());
            changeInfoPage.render(req, resp);
        }

        hibernateSessionProvider.closeSession();
    }

    @Override
    public void destroy() {
        userServices = null;
        hibernateSessionProvider = null;
    }
}
