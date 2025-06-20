package com.filmbooking.controller.customer.account.auth;

import com.filmbooking.model.User;
import com.filmbooking.page.ClientPage;
import com.filmbooking.page.Page;
import com.filmbooking.services.impls.UserServicesImpl;
import com.filmbooking.enumsAndConstants.enums.StatusCodeEnum;
import com.filmbooking.services.logProxy.CRUDServicesLogProxy;
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

@WebServlet(value = "/auth/change-password")

public class ChangePasswordController extends HttpServlet {
    private CRUDServicesLogProxy<User> userServicesLog;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Page changePasswordPage = new ClientPage(
                "changePasswordTitle",
                "change-password",
                "master"
        );

        changePasswordPage.render(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserServicesImpl userServices = new UserServicesImpl();
        userServicesLog = new CRUDServicesLogProxy<>(new UserServicesImpl(), req, User.class);

        String currentPassword = req.getParameter("current-password");
        String newPassword = StringUtils.handlesInputString(req.getParameter("new-password"));
        String confirmNewPassword = StringUtils.handlesInputString(req.getParameter("confirm-new-password"));

        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("loginUser");

        Page changePasswordPage = new ClientPage(
                "changePasswordTitle",
                "change-password",
                "master"
        );

        if (loginUser.getUserPassword().equals(userServices.hashPassword(currentPassword))){
            if(!Regex.validate(UserRegexEnum.USER_PASSWORD, newPassword)){
                changePasswordPage.putError(StatusCodeEnum.USER_PASSWORD_ERROR.getStatusCode());
                changePasswordPage.render(req, resp);
                return;
            }
            if (newPassword.equals(confirmNewPassword)) {
                newPassword = userServices.hashPassword(newPassword);
                loginUser.setUserPassword(newPassword);
                session.setAttribute("loginUser", loginUser);

                userServices.update(loginUser);
                changePasswordPage.putSuccess(StatusCodeEnum.PASSWORD_CHANGE_SUCCESSFUL.getStatusCode());
                // confirm password not match
            } else {
              changePasswordPage.putError(StatusCodeEnum.PASSWORD_CONFIRM_NOT_MATCH.getStatusCode());
            }
            changePasswordPage.render(req, resp);

            // current password not match
        } else {
            changePasswordPage.putError(StatusCodeEnum.PASSWORD_NOT_MATCH.getStatusCode());
            changePasswordPage.render(req, resp);
        }
    }

    @Override
    public void destroy() {
        userServicesLog = null;
    }
}
