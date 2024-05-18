package com.filmbooking.controller.customer.account;

import com.filmbooking.enumsAndConstants.enums.StatusCodeEnum;
import com.filmbooking.enumsAndConstants.enums.TokenStateEnum;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.TokenModel;
import com.filmbooking.model.User;
import com.filmbooking.page.ClientPage;
import com.filmbooking.page.Page;
import com.filmbooking.services.impls.TokenServicesImpl;
import com.filmbooking.services.impls.UserServicesImpl;
import com.filmbooking.services.logProxy.CRUDServicesLogProxy;
import com.filmbooking.services.serviceResult.ServiceResult;
import com.filmbooking.utils.WebAppPathUtils;
import com.filmbooking.utils.validateUtils.Regex;
import com.filmbooking.utils.validateUtils.UserRegexEnum;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "resetPassword", value = "/reset-password")
public class ResetPasswordController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Page resetPasswordPage = new ClientPage(
                "resetPasswordTitle",
                "reset-password",
                "master"
        );
        resetPasswordPage.render(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username") != null ? req.getParameter("username") : (String) req.getAttribute("username");

        String newPassword = req.getParameter("new-password");
        String confirmPassword = req.getParameter("confirm-new-password");
        HibernateSessionProvider hibernateSessionProvider = new HibernateSessionProvider();
        UserServicesImpl userServices = new UserServicesImpl();
        CRUDServicesLogProxy<User> userServicesLog = new CRUDServicesLogProxy<>(userServices, req, hibernateSessionProvider);
        TokenServicesImpl tokenServices = new TokenServicesImpl(hibernateSessionProvider);
        if(!Regex.validate(UserRegexEnum.USER_PASSWORD, newPassword)){
            req.setAttribute("statusCodeErr", StatusCodeEnum.USER_PASSWORD_ERROR.getStatusCode());
            req.setAttribute("username", username);
            doGet(req, resp);
            hibernateSessionProvider.closeSession();
            return;
        }

        // get token from session
        TokenModel tokenModel = (TokenModel) req.getSession(false).getAttribute("token");
        ServiceResult serviceResult = tokenServices.verifyToken(tokenModel);

        // case token is valid and not expired
        if (serviceResult.getStatus().equals(StatusCodeEnum.TOKEN_VERIFIED)) {
            // in case confirm password match new password
            if (newPassword.equals(confirmPassword)) {
                User user = userServicesLog.getByID(username);
                System.out.println("reset password user: " + user);
                String encryptedPassword = userServices.hashPassword(newPassword);
                user.setUserPassword(encryptedPassword);
                userServicesLog.update(user);

                // set success status code
                req.setAttribute("statusCodeSuccess", StatusCodeEnum.PASSWORD_CHANGE_SUCCESSFUL.getStatusCode());

                // set token state to used
                tokenModel.setTokenState(TokenStateEnum.USED.getTokenState());
                tokenServices.update(tokenModel);

                // remove token from session
                req.getSession(false).removeAttribute("token");
                doGet(req, resp);
            }
            else {
                // set confirm password not match status code
                req.setAttribute("statusCodeErr", StatusCodeEnum.PASSWORD_NOT_MATCH.getStatusCode());
                doGet(req, resp);
            }
        } else {
            // set password reset failed status code
            req.setAttribute("statusCodeErr", StatusCodeEnum.PASSWORD_RESET_FAILED.getStatusCode());
            doGet(req, resp);
        }

        hibernateSessionProvider.closeSession();
    }
}
