package com.filmbooking.controller.customer.account;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.User;
import com.filmbooking.page.ClientPage;
import com.filmbooking.page.Page;
import com.filmbooking.services.impls.UserServicesImpl;
import com.filmbooking.services.logProxy.UserServicesLogProxy;
import com.filmbooking.services.serviceResult.ServiceResult;
import com.filmbooking.enumsAndConstants.enums.StatusCodeEnum;
import com.filmbooking.utils.WebAppPathUtils;
import com.filmbooking.utils.validateUtils.Regex;
import com.filmbooking.utils.validateUtils.UserRegexEnum;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "forgotPassword", value = "/forgot-password")
public class ForgotPasswordController extends HttpServlet {
    private UserServicesLogProxy<User> userServicesLog;
    private HibernateSessionProvider hibernateSessionProvider;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Page forgotPassPage = new ClientPage(
                "forgotPassTitle",
                "forgot",
                "master"
        );

        forgotPassPage.render(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        userServicesLog = new UserServicesLogProxy<>(new UserServicesImpl(), req, hibernateSessionProvider);

        String username = req.getParameter("username");
        String userEmail = req.getParameter("email");

        Page forgotPassPage = new ClientPage(
                "forgotPassTitle",
                "forgot",
                "master"
        );

        // validate input
        if (!Regex.validate(UserRegexEnum.USER_EMAIL, userEmail) || !Regex.validate(UserRegexEnum.USERNAME, username)) {
            forgotPassPage.putError(StatusCodeEnum.INVALID_INPUT.getStatusCode());
            forgotPassPage.render(req, resp);
            return;
        }

        // get result from userServices
        String currentLanguage = (String) req.getSession().getAttribute("lang");

        ServiceResult forgotPassResult = userServicesLog.userForgotPassword(username, userEmail, currentLanguage);

        if (forgotPassResult.getStatus().equals(StatusCodeEnum.SUCCESSFUL))
            forgotPassPage.putSuccess(StatusCodeEnum.SENT_RESET_PASSWD_EMAIL.getStatusCode());
        else
            forgotPassPage.putError(forgotPassResult.getStatus().getStatusCode());

        forgotPassPage.render(req, resp);

        hibernateSessionProvider.closeSession();
    }

    @Override
    public void destroy() {
        userServicesLog = null;
        hibernateSessionProvider = null;
    }
}
