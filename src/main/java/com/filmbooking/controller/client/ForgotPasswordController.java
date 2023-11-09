package com.filmbooking.controller.client;

import com.filmbooking.model.User;
import com.filmbooking.services.IUserServices;
import com.filmbooking.services.impls.UserServicesImpl;
import com.filmbooking.utils.ContextPathUtils;
import com.filmbooking.utils.RenderViewUtils;
import com.filmbooking.utils.StringUtils;
import com.filmbooking.utils.mailUtils.SendEmail;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
        req.setAttribute("pageTitle", "Film Booking - Quên mật khẩu");
        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getClientPagesPath("forgot.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");

        userServices = new UserServicesImpl();

        if (userServices.getByUsername(username) == null) {
            req.setAttribute("usernameError", "Tên người dùng không tồn tại!");
        } else {
            User foundUser = userServices.getByUsername(username);
            if (foundUser.getUserEmail().equals(email)) {
//                HttpSession session = req.getSession();
//                session.setAttribute("forgot-username", foundUser.getUsername());
                String randomPassWD = StringUtils.createRandomStringUtil(9);
                // set new password to user
                foundUser.setUserPassword(StringUtils.generateSHA256String(randomPassWD));
                userServices.update(foundUser);

                SendEmail.getInstance().sendEmailToUser(foundUser.getUserEmail(), "Mật khẩu mới của bạn", "Hãy nhập mật khẩu: " + "<b>" + randomPassWD + "</b>");


                req.setAttribute("successfulMessage", "<span class=\"material-symbols-outlined\">\n" +
                        "task_alt </span>" +
                        " Mật khẩu mới đã được gửi đến Email của bạn.");


//                resp.sendRedirect("reset-password");
            } else {
                req.setAttribute("emailError", "Email này không khớp với tên người dùng trong hệ thống");
            }
            RenderViewUtils.updateView(req, resp, ContextPathUtils.getClientPagesPath("forgot.jsp"));
            RenderViewUtils.renderViewToLayout(req, resp,
                    ContextPathUtils.getClientPagesPath("forgot.jsp"),
                    ContextPathUtils.getLayoutPath("master.jsp"));
        }

    }

    @Override
    public void destroy() {
        userServices = null;
    }
}
