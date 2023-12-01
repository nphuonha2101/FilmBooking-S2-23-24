package com.filmbooking.controller.client;

import com.filmbooking.services.IUserServices;
import com.filmbooking.services.impls.UserServicesImpl;
import com.filmbooking.services.serviceResult.ServiceResult;
import com.filmbooking.statusEnums.StatusCodeEnum;
import com.filmbooking.utils.ContextPathUtils;
import com.filmbooking.utils.RenderViewUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "forgotPassword", value = "/forgot-password")
public class ForgotPasswordController extends HttpServlet {
    private IUserServices userServices;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageTitle", "forgotPassTitle");
        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getClientPagesPath("forgot.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userServices = new UserServicesImpl();
        userServices.openSession();

        String username = req.getParameter("username");
        String email = req.getParameter("email");


        ServiceResult forgotPassResult = userServices.userForgotPassword(username, email);

        if (forgotPassResult.getStatus().equals(StatusCodeEnum.SUCCESSFUL))
            req.setAttribute("statusCodeSuccess", StatusCodeEnum.SENT_RESET_PASSWD_EMAIL.getStatusCode());
        else
            req.setAttribute("statusCodeErr", forgotPassResult.getStatus().getStatusCode());


        req.setAttribute("pageTitle", "forgotPassTitle");
//        RenderViewUtils.updateView(req, resp, ContextPathUtils.getClientPagesPath("forgot.jsp"));
        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getClientPagesPath("forgot.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));

        userServices.closeSession();
    }

    @Override
    public void destroy() {
        userServices = null;
    }
}
