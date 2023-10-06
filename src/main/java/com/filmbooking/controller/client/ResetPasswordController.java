package com.filmbooking.controller.client;

import com.filmbooking.DAOservices.IUserDAOServices;
import com.filmbooking.DAOservices.UserDAOServicesImpl;
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
    private IUserDAOServices userDAOServices;
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

        userDAOServices = new UserDAOServicesImpl();

        String usernameForgot = (String) req.getSession().getAttribute("forgot-username");

        if (newPassword.equals(confirmNewPassword)) {
            userDAOServices.changePassword(usernameForgot, newPassword);
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
