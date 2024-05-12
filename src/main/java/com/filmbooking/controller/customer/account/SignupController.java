package com.filmbooking.controller.customer.account;

import com.filmbooking.enumsAndConstants.enums.AccountRoleEnum;
import com.filmbooking.enumsAndConstants.enums.AccountTypeEnum;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.User;
import com.filmbooking.services.impls.UserServicesImpl;
import com.filmbooking.enumsAndConstants.enums.StatusCodeEnum;
import com.filmbooking.services.logProxy.CRUDServicesLogProxy;
import com.filmbooking.utils.WebAppPathUtils;
import com.filmbooking.utils.RenderViewUtils;
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
        req.setAttribute("pageTitle", "signupTitle");
        RenderViewUtils.renderViewToLayout(req, resp,
                WebAppPathUtils.getClientPagesPath("signup.jsp"),
                WebAppPathUtils.getLayoutPath("master.jsp"));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        userServices = new UserServicesImpl(hibernateSessionProvider);
        userServicesLog = new CRUDServicesLogProxy<>(new UserServicesImpl(), req, hibernateSessionProvider);

        String username = StringUtils.handlesInputString(req.getParameter("username"));
        String userFullName = StringUtils.handlesInputString(req.getParameter("user-full-name"));
        String userEmail = StringUtils.handlesInputString(req.getParameter("email"));
        String userPassword = StringUtils.handlesInputString(req.getParameter("password"));
        String confirmPassword = StringUtils.handlesInputString(req.getParameter("confirm-password"));

        // validate input
       validateInput(req, resp, username, userFullName, userEmail, userPassword, confirmPassword);


        // username existed!
        if (userServicesLog.getByID(username) != null) {
            req.setAttribute("statusCodeErr", StatusCodeEnum.USERNAME_EXISTED.getStatusCode());
            // username not existed but email existed!
        } else if (userServices.getByEmail(userEmail) != null) {
            req.setAttribute("statusCodeErr", StatusCodeEnum.EMAIL_EXISTED.getStatusCode());
            // username not existed and email not existed!
        } else if (userPassword.equals(confirmPassword)) {
            userPassword = userServices.hashPassword(userPassword);
            User newUser = new User(username, userFullName, userEmail, userPassword, AccountRoleEnum.CUSTOMER, AccountTypeEnum.NORMAL.getAccountType());
            userServicesLog.save(newUser);
            req.setAttribute("statusCodeSuccess", StatusCodeEnum.CREATE_NEW_USER_SUCCESSFUL.getStatusCode());
            // confirm password not match!
        } else {
            req.setAttribute("statusCodeErr", StatusCodeEnum.PASSWORD_CONFIRM_NOT_MATCH.getStatusCode());
        }

        req.setAttribute("pageTitle", "signupTitle");
        RenderViewUtils.renderViewToLayout(req, resp,
                WebAppPathUtils.getClientPagesPath("signup.jsp"),
                WebAppPathUtils.getLayoutPath("master.jsp"));

        hibernateSessionProvider.closeSession();
    }

    @Override
    public void destroy() {
        userServicesLog = null;
        userServices = null;
        hibernateSessionProvider = null;
    }

    private void validateInput(HttpServletRequest req, HttpServletResponse resp,String username, String userFullName, String userEmail, String userPassword, String confirmPassword) {
        if (!Regex.validate(UserRegexEnum.USER_EMAIL, userEmail) ) {
            handleInput(req, resp,  StatusCodeEnum.USER_EMAIL_ERROR.getStatusCode());

            return;
        }
        if (!Regex.validate(UserRegexEnum.USER_FULL_NAME, userFullName)) {
            handleInput(req, resp,  StatusCodeEnum.USER_FULL_NAME_ERROR.getStatusCode());
            return;
        }
        if (!Regex.validate(UserRegexEnum.USERNAME, username)) {
            handleInput(req, resp,  StatusCodeEnum.USERNAME_ERROR.getStatusCode());
            return;
        }
        if (!Regex.validate(UserRegexEnum.USER_PASS_WORD, userPassword)) {
            handleInput(req, resp,  StatusCodeEnum.USER_PASSWORD_ERROR.getStatusCode());
            return;
        }
    }

    private void handleInput(HttpServletRequest req, HttpServletResponse resp,int status) {
        req.setAttribute("statusCodeErr", status);

        req.setAttribute("pageTitle", "signupTitle");
        RenderViewUtils.renderViewToLayout(req, resp,
                WebAppPathUtils.getClientPagesPath("signup.jsp"),
                WebAppPathUtils.getLayoutPath("master.jsp"));
    }
}
