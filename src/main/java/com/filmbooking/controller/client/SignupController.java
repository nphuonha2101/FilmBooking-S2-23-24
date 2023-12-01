package com.filmbooking.controller.client;

import com.filmbooking.model.User;
import com.filmbooking.services.IUserServices;
import com.filmbooking.services.impls.UserServicesImpl;
import com.filmbooking.statusEnums.StatusCodeEnum;
import com.filmbooking.utils.ContextPathUtils;
import com.filmbooking.utils.RenderViewUtils;
import com.filmbooking.utils.StringUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "signup", value = "/signup")
public class SignupController extends HttpServlet {

    private IUserServices userServices;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageTitle", "signupTitle");
        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getClientPagesPath("signup.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userServices = new UserServicesImpl();
        userServices.openSession();

        String username = StringUtils.handlesInputString(req.getParameter("username"));
        String userFullName = StringUtils.handlesInputString(req.getParameter("user-full-name"));
        String userEmail = StringUtils.handlesInputString(req.getParameter("email"));
        String userPassword = StringUtils.handlesInputString(req.getParameter("password"));
        String confirmPassword = StringUtils.handlesInputString(req.getParameter("confirm-password"));

        // username existed!
        if (userServices.getByUsername(username) != null) {
            req.setAttribute("statusCodeErr", StatusCodeEnum.USERNAME_EXISTED.getStatusCode());
            // username not existed but email existed!
        } else if (userServices.getByEmail(userEmail) != null) {
            req.setAttribute("statusCodeErr", StatusCodeEnum.EMAIL_EXISTED.getStatusCode());
            // username not existed and email not existed!
        } else if (userPassword.equals(confirmPassword)) {
            userPassword = StringUtils.generateSHA256String(userPassword);
            User newUser = new User(username, userFullName, userEmail, userPassword, "customer");
            userServices.save(newUser);
            req.setAttribute("statusCodeSuccess", StatusCodeEnum.CREATE_NEW_USER_SUCCESSFUL.getStatusCode());
            // confirm password not match!
        } else {
            req.setAttribute("statusCodeErr", StatusCodeEnum.PASSWORD_CONFIRM_NOT_MATCH.getStatusCode());
        }

        req.setAttribute("pageTitle", "signupTitle");
        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getClientPagesPath("signup.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));

        userServices.closeSession();
    }

    @Override
    public void destroy() {
        userServices = null;
    }
}
