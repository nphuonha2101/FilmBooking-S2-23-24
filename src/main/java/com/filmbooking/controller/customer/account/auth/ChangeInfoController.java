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

@WebServlet(value = "/auth/change-info")

public class ChangeInfoController extends HttpServlet {
    private IUserServices userServices;
    private HibernateSessionProvider hibernateSessionProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageTitle", "changeInfoTitle");

        RenderViewUtils.renderViewToLayout(req, resp, PathUtils.getClientPagesPath("change-info.jsp"),
                PathUtils.getLayoutPath("master.jsp"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        userServices = new UserServicesImpl(hibernateSessionProvider);

        String userFullName = StringUtils.handlesInputString(req.getParameter("user-full-name"));
        String email = StringUtils.handlesInputString(req.getParameter("email"));
        String password = StringUtils.generateSHA256String(StringUtils.handlesInputString(req.getParameter("password")));

        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser != null && password.equals(loginUser.getUserPassword())) {
            // when user change email then verify email
            if (!email.equalsIgnoreCase(loginUser.getUserEmail()) && userServices.getByEmail(email) != null) {
                req.setAttribute("statusCodeErr", StatusCodeEnum.EMAIL_EXISTED.getStatusCode());
                req.setAttribute("pageTitle", "changeInfoTitle");
                RenderViewUtils.renderViewToLayout(req, resp, PathUtils.getClientPagesPath("change-info.jsp"), PathUtils.getLayoutPath("master.jsp"));
            } else {
                loginUser.setUserFullName(userFullName);
                loginUser.setUserEmail(email);
                userServices.update(loginUser);
                resp.sendRedirect(PathUtils.getURLWithContextPath(req, "/auth/account-info"));
            }
        } else {
            req.setAttribute("statusCodeErr", StatusCodeEnum.PASSWORD_NOT_MATCH.getStatusCode());
            req.setAttribute("pageTitle", "changeInfoTitle");
//            RenderViewUtils.updateView(req, resp, PathUtils.getClientPagesPath("change-info.jsp"));
            RenderViewUtils.renderViewToLayout(req, resp, PathUtils.getClientPagesPath("change-info.jsp"), PathUtils.getLayoutPath("master.jsp"));
        }

        hibernateSessionProvider.closeSession();
    }

    @Override
    public void destroy() {
        userServices = null;
        hibernateSessionProvider = null;
    }
}
