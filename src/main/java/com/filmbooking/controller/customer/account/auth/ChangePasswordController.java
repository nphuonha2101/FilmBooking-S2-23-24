package com.filmbooking.controller.customer.account.auth;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.User;
import com.filmbooking.services.IUserServices;
import com.filmbooking.services.impls.UserServicesImpl;
import com.filmbooking.statusEnums.StatusCodeEnum;
import com.filmbooking.utils.PathUtils;
import com.filmbooking.utils.RenderViewUtils;
import com.filmbooking.utils.StringUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(value = "/auth/change-password")

public class ChangePasswordController extends HttpServlet {
    private IUserServices userServices;
    private HibernateSessionProvider hibernateSessionProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageTitle", "changePasswordTitle");
        RenderViewUtils.renderViewToLayout(req, resp,
                PathUtils.getClientPagesPath("change-password.jsp"),
                PathUtils.getLayoutPath("master.jsp"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        userServices = new UserServicesImpl(hibernateSessionProvider);

        String currentPassword = req.getParameter("current-password");
        String newPassword = StringUtils.handlesInputString(req.getParameter("new-password"));
        String confirmNewPassword = StringUtils.handlesInputString(req.getParameter("confirm-new-password"));

        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("loginUser");


        if (loginUser.getUserPassword().equals(StringUtils.generateSHA256String(currentPassword))) {
            if (newPassword.equals(confirmNewPassword)) {
                newPassword = StringUtils.generateSHA256String(newPassword);
                loginUser.setUserPassword(newPassword);
                session.setAttribute("loginUser", loginUser);

                userServices.update(loginUser);
                req.setAttribute("statusCodeSuccess", StatusCodeEnum.PASSWORD_CHANGE_SUCCESSFUL.getStatusCode());
                // confirm password not match
            } else {
                req.setAttribute("statusCodeErr", StatusCodeEnum.PASSWORD_CONFIRM_NOT_MATCH.getStatusCode());
            }
            req.setAttribute("pageTitle", "changePasswordTitle");
            render(req, resp);

            // current password not match
        } else {
            req.setAttribute("statusCodeErr", StatusCodeEnum.PASSWORD_NOT_MATCH.getStatusCode());
            req.setAttribute("pageTitle", "changePasswordTitle");
            render(req, resp);
        }
        hibernateSessionProvider.closeSession();
    }

    @Override
    public void destroy() {
        userServices = null;
        hibernateSessionProvider = null;
    }

    private void render(HttpServletRequest req, HttpServletResponse resp) {
//        RenderViewUtils.updateView(req, resp,  PathUtils.getClientPagesPath("change-password.jsp"));
        RenderViewUtils.renderViewToLayout(req, resp, PathUtils.getClientPagesPath("change-password.jsp"), PathUtils.getLayoutPath("master.jsp"));
    }
}
