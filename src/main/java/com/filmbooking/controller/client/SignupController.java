package com.filmbooking.controller.client;

import com.filmbooking.model.User;
import com.filmbooking.services.IUserServices;
import com.filmbooking.services.impls.UserServicesImpl;
import com.filmbooking.utils.ContextPathUtils;
import com.filmbooking.utils.RenderViewUtils;
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
        req.setAttribute("pageTitle", "Film Booking - Đăng ký");
        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getClientPagesPath("signup.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String userFullName = request.getParameter("user-full-name");
        String userEmail = request.getParameter("email");
        String userPassword = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm-password");

        userServices = new UserServicesImpl();

        if (userServices.getByUsername(username) != null) {
            request.setAttribute("usernameError", "Tên người dùng đã tồn tại!" +
                    " Vui lòng chọn một tên người dùng khác.");

        } else if (userPassword.equals(confirmPassword)) {
            User newUser = new User(username, userFullName, userEmail, userPassword, "customer");
            userServices.save(newUser);
            request.setAttribute("successfulMessage", "<span class=\"material-symbols-outlined\">\n" +
                    "task_alt </span>" +
                    " Chúc mừng! Tài khoản của bạn đã được khởi tạo.");
        } else {
            request.setAttribute("confirmPasswordError", "Mật khẩu xác nhận không đúng!");
        }
        RenderViewUtils.updateView(request, response, ContextPathUtils.getClientPagesPath("signup.jsp"));

        RenderViewUtils.renderViewToLayout(request, response,
                ContextPathUtils.getClientPagesPath("signup.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));


    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
