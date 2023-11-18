package com.filmbooking.controller.client;

import com.filmbooking.services.IUserServices;
import com.filmbooking.services.impls.UserServicesImpl;
import com.filmbooking.services.serviceResult.ServiceResult;
import com.filmbooking.statusEnums.StatusEnum;
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
        String username = req.getParameter("username");
        String email = req.getParameter("email");

        userServices = new UserServicesImpl();

        ServiceResult forgotPassResult = userServices.userForgotPassword(username, email);

        if (forgotPassResult.getStatus() == StatusEnum.USER_NOT_FOUND)
            req.setAttribute("errorMessage", forgotPassResult.getStatus().getMessage());

        if (forgotPassResult.getStatus() == StatusEnum.EMAIL_NOT_MATCH)
            req.setAttribute("errorMessage", forgotPassResult.getStatus().getMessage());
        if (forgotPassResult.getStatus() == StatusEnum.SUCCESSFUL)
            req.setAttribute("successfulMessage", "Email chứa mật khẩu mới đã được gửi đến bạn.");

//        RenderViewUtils.updateView(req, resp, ContextPathUtils.getClientPagesPath("forgot.jsp"));
        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getClientPagesPath("forgot.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));

    }

    @Override
    public void destroy() {
        userServices = null;
    }
}
