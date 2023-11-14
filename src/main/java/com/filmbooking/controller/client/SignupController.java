package com.filmbooking.controller.client;

import com.filmbooking.model.User;
import com.filmbooking.services.IUserServices;
import com.filmbooking.services.impls.UserServicesImpl;
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
        req.setAttribute("pageTitle", "Film Booking - Đăng ký");
        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getClientPagesPath("signup.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = StringUtils.handlesInputString(req.getParameter("username"));
        String userFullName = StringUtils.handlesInputString(req.getParameter("user-full-name"));
        String userEmail = StringUtils.handlesInputString(req.getParameter("email"));
        String userPassword = StringUtils.handlesInputString(req.getParameter("password"));
        String confirmPassword = StringUtils.handlesInputString(req.getParameter("confirm-password"));
        userServices = new UserServicesImpl();

        if (userServices.getByUsername(username) != null) {
            req.setAttribute("errorMessage", "Tên người dùng đã tồn tại!" +
                    " Vui lòng chọn một tên người dùng khác.");
        }
        if (userServices.getByEmail(userEmail) != null) {
            req.setAttribute("errorMessage", "Email đã tồn tại!" +
                    " Vui lòng chọn một email khác.");
        } else if (userPassword.equals(confirmPassword)) {
            userPassword = StringUtils.generateSHA256String(userPassword);
            User newUser = new User(username, userFullName, userEmail, userPassword, "customer");
            userServices.save(newUser);
            req.setAttribute("successfulMessage", "<span class=\"material-symbols-outlined\">\n" +
                    "task_alt </span>" +
                    " Chúc mừng! Tài khoản của bạn đã được khởi tạo.");
        } else {
            req.setAttribute("errorMessage", "Mật khẩu xác nhận không đúng!");
        }

        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getClientPagesPath("signup.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));
    }

    @Override
    public void destroy() {
        userServices = null;
    }
}
