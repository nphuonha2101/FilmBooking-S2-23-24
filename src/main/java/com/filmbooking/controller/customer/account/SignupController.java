package com.filmbooking.controller.customer.account;

import com.filmbooking.enumsAndConstants.enums.AccountRoleEnum;
import com.filmbooking.enumsAndConstants.enums.AccountTypeEnum;
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

import java.io.IOException;

@WebServlet(name = "signup", value = "/signup")
public class SignupController extends HttpServlet {
    private CRUDServicesLogProxy<User> userServicesLog;
    private UserServicesImpl userServices;
    private HibernateSessionProvider hibernateSessionProvider;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Page signupPage = new ClientPage(
                "signupTitle",
                "signup",
                "master"
        );
        signupPage.render(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        userServices = new UserServicesImpl(hibernateSessionProvider);
        userServicesLog = new CRUDServicesLogProxy<>(new UserServicesImpl(), req, hibernateSessionProvider);

        Page signupPage = new ClientPage(
                "signupTitle",
                "signup",
                "master"
        );

        String username = StringUtils.handlesInputString(req.getParameter("username"));
        String userFullName = StringUtils.handlesInputString(req.getParameter("user-full-name"));
        String userEmail = StringUtils.handlesInputString(req.getParameter("email"));
        String userPassword = StringUtils.handlesInputString(req.getParameter("password"));
        String confirmPassword = StringUtils.handlesInputString(req.getParameter("confirm-password"));


//         validate input
        boolean isAllValid = validateInput(req, resp, signupPage, username, userFullName, userEmail, userPassword, confirmPassword);
        if (!isAllValid) {
            return;
        }


        // username existed!
        if (userServicesLog.getByID(username) != null) {
            renderError(signupPage, StatusCodeEnum.USERNAME_EXISTED.getStatusCode(), req, resp);
            return;
        }

        // username not existed but email existed!
        if (userServices.getByEmail(userEmail) != null) {
            renderError(signupPage, StatusCodeEnum.EMAIL_EXISTED.getStatusCode(), req, resp);
            return;
        }

        // username not existed and email not existed!
        if (userPassword.equals(confirmPassword)) {
            userPassword = userServices.hashPassword(userPassword);
            User newUser = new User(username, userFullName, userEmail, userPassword, AccountRoleEnum.CUSTOMER, AccountTypeEnum.NORMAL.getAccountType(), 1);
            userServicesLog.save(newUser);
            hibernateSessionProvider.closeSession();

            renderSuccess(signupPage, StatusCodeEnum.CREATE_NEW_USER_SUCCESSFUL.getStatusCode(), req, resp);
        }

    }

    private void renderError(Page page, int statusError, HttpServletRequest req, HttpServletResponse resp) {
        page.putError(statusError);
        page.render(req, resp);
    }

    private void renderSuccess(Page page, int statusSuccess, HttpServletRequest req, HttpServletResponse resp) {
        page.putSuccess(statusSuccess);
        page.render(req, resp);
    }

    @Override
    public void destroy() {
        userServicesLog = null;
        userServices = null;
        hibernateSessionProvider = null;
    }


    private boolean validateInput(HttpServletRequest req, HttpServletResponse resp, Page page, String username, String userFullName, String userEmail, String userPassword, String confirmPassword) {
        if (!Regex.validate(UserRegexEnum.USER_EMAIL, userEmail)) {

            handleInput(req, resp, page, StatusCodeEnum.USER_EMAIL_ERROR.getStatusCode());
            return false;
        }
        if (!Regex.validate(UserRegexEnum.USER_FULL_NAME, userFullName)) {
            handleInput(req, resp, page, StatusCodeEnum.USER_FULL_NAME_ERROR.getStatusCode());
            return false;
        }
        if (!Regex.validate(UserRegexEnum.USERNAME, username)) {
            handleInput(req, resp, page, StatusCodeEnum.USERNAME_ERROR.getStatusCode());
            return false;
        }
        if (!Regex.validate(UserRegexEnum.USER_PASSWORD, userPassword)) {
            handleInput(req, resp, page, StatusCodeEnum.USER_PASSWORD_ERROR.getStatusCode());
            return false;
        }
        if (!userPassword.equals(confirmPassword)) {
            handleInput(req, resp, page, StatusCodeEnum.PASSWORD_CONFIRM_NOT_MATCH.getStatusCode());
            return false;
        }
        return true;
    }

    private void handleInput(HttpServletRequest req, HttpServletResponse resp, Page page, int statusError) {
        page.putError(statusError);
        page.render(req, resp);
    }
}
