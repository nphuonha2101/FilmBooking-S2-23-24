package com.filmbooking.controller.client;

import com.filmbooking.model.User;
import com.filmbooking.services.IUserServices;
import com.filmbooking.services.UserServicesImpl;
import com.filmbooking.ultils.ContextPathUtils;
import com.filmbooking.ultils.RenderViewUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="resetPassword", value = "/reset-password")
public class ResetPasswordController extends HttpServlet {
    private IUserServices userServices;
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageTitle", "Film Booking - Đặt lại mật khẩu");
        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getClientPagesPath("reset-password.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newPassword = req.getParameter("new-password");
        String confirmNewPassword = req.getParameter("confirm-new-password");

        userServices = new UserServicesImpl();

        String usernameForgot = (String) req.getSession().getAttribute("forgot-username");

        User foundUser = userServices.getByUsername(usernameForgot);

        if (newPassword.equals(confirmNewPassword)) {
            foundUser.setUserPassword(newPassword);

            userServices.update(foundUser);

            req.setAttribute("successfulMessage", "<span class=\"material-symbols-outlined\">\n" +
                    "task_alt\n" +
                    "</span> Mật khẩu của bạn đã được đặt lại thành công");
            req.setAttribute("additionElement", "<a class=\"links\" href='login'>Đăng nhập ngay</a>");
        } else {
            req.setAttribute("confirmPasswordError", "Mật khẩu xác nhận không khớp!");
        }

        RenderViewUtils.updateView(req, resp, ContextPathUtils.getClientPagesPath("reset-password.jsp"));

        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getClientPagesPath("reset-password.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));

    }
}
