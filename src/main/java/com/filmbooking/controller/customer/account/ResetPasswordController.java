package com.filmbooking.controller.customer.account;

import com.filmbooking.enumsAndConstants.enums.StatusCodeEnum;
import com.filmbooking.enumsAndConstants.enums.TokenStateEnum;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.TokenModel;
import com.filmbooking.model.User;
import com.filmbooking.services.impls.TokenServicesImpl;
import com.filmbooking.services.impls.UserServicesImpl;
import com.filmbooking.services.serviceResult.ServiceResult;
import com.filmbooking.utils.StringUtils;
import com.filmbooking.utils.WebAppPathUtils;
import com.filmbooking.utils.RenderViewUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "resetPassword", value = "/reset-password")
public class ResetPasswordController extends HttpServlet {
    private String status;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        status = (String) req.getAttribute("verifyStatus");
        System.out.println(status);
        status = (String) req.getAttribute("username");

        // if status is null, check if token is in session
        if (status == null) {
            TokenModel tokenModel = (TokenModel) req.getSession(false).getAttribute("token");
            if (tokenModel != null) {
                String token = tokenModel.getToken();
                String tokenType = tokenModel.getTokenType();
                String tokenUsername = tokenModel.getUsername();

                // redirect to verify tokens
                resp.sendRedirect(WebAppPathUtils.getURLWithContextPath(req, resp,
                        "/token/verify?token=" + token + "&username=" + tokenUsername + "&token-type=" + tokenType));
                return;
            }

            // if token is null, response with 404
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

        req.setAttribute("pageTitle", "Reset Password");
        RenderViewUtils.renderViewToLayout(req, resp,
                WebAppPathUtils.getClientPagesPath("reset-password.jsp"),
                WebAppPathUtils.getLayoutPath("master.jsp"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        String newPassword = req.getParameter("new-password");
        String confirmPassword = req.getParameter("confirm-new-password");
        HibernateSessionProvider hibernateSessionProvider = new HibernateSessionProvider();
        UserServicesImpl userServices = new UserServicesImpl(hibernateSessionProvider);
        TokenServicesImpl tokenServices = new TokenServicesImpl(hibernateSessionProvider);

        // get token from session
        TokenModel tokenModel = (TokenModel) req.getSession(false).getAttribute("token");
        ServiceResult serviceResult = tokenServices.verifyToken(tokenModel);

        if (status.equals("token-verified")) {
            if (newPassword.equals(confirmPassword)) {
                User user = userServices.getByID(username);
                String encryptedPassword = StringUtils.generateSHA256String(newPassword);
                user.setUserPassword(encryptedPassword);
                userServices.update(user);

                // set success status code
                req.setAttribute("statusCodeSuccess", StatusCodeEnum.PASSWORD_CHANGE_SUCCESSFUL.getStatusCode());

                // set token state to used
                tokenModel.setTokenState(TokenStateEnum.USED.getTokenState());
                tokenServices.update(tokenModel);

                // remove token from session
                req.getSession(false).removeAttribute("token");

                doGet(req, resp);

                System.out.println("Test");
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
