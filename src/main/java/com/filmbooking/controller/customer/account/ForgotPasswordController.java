package com.filmbooking.controller.customer.account;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.services.IUserServices;
import com.filmbooking.services.impls.UserServicesImpl;
import com.filmbooking.services.serviceResult.ServiceResult;
import com.filmbooking.statusEnums.StatusCodeEnum;
import com.filmbooking.utils.PathUtils;
import com.filmbooking.utils.RenderViewUtils;
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
    private IUserServices userServices;
    private HibernateSessionProvider hibernateSessionProvider;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageTitle", "forgotPassTitle");
        RenderViewUtils.renderViewToLayout(req, resp,
                PathUtils.getClientPagesPath("forgot.jsp"),
                PathUtils.getLayoutPath("master.jsp"));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        userServices = new UserServicesImpl(hibernateSessionProvider);

        String username = req.getParameter("username");
        String userEmail = req.getParameter("email");

        // validate input
        if (!Regex.validate(UserRegexEnum.USER_EMAIL, userEmail) || !Regex.validate(UserRegexEnum.USERNAME, username)) {
            req.setAttribute("statusCodeErr", StatusCodeEnum.INVALID_INPUT.getStatusCode());
            req.setAttribute("pageTitle", "forgotPassTitle");
            RenderViewUtils.renderViewToLayout(req, resp,
                    PathUtils.getClientPagesPath("forgot.jsp"),
                    PathUtils.getLayoutPath("master.jsp"));
            return;
        }

        // get result from userServices
        String currentLanguage = (String) req.getSession().getAttribute("lang");
        ServiceResult forgotPassResult = userServices.userForgotPassword(username, userEmail, currentLanguage);

        if (forgotPassResult.getStatus().equals(StatusCodeEnum.SUCCESSFUL))
            req.setAttribute("statusCodeSuccess", StatusCodeEnum.SENT_RESET_PASSWD_EMAIL.getStatusCode());
        else
            req.setAttribute("statusCodeErr", forgotPassResult.getStatus().getStatusCode());


        req.setAttribute("pageTitle", "forgotPassTitle");
        RenderViewUtils.renderViewToLayout(req, resp,
                PathUtils.getClientPagesPath("forgot.jsp"),
                PathUtils.getLayoutPath("master.jsp"));

        hibernateSessionProvider.closeSession();
    }

    @Override
    public void destroy() {
        userServices = null;
        hibernateSessionProvider = null;
    }
}
