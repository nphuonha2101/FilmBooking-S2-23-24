package com.filmbooking.controller.client;

import com.filmbooking.model.User;
import com.filmbooking.services.impls.UserServicesImpl;
import com.filmbooking.utils.ContextPathUtils;
import com.filmbooking.utils.HashTextGeneratorUtils;
import com.filmbooking.utils.RedirectPageUtils;
import com.filmbooking.utils.RenderViewUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "login", value = "/login")
public class LoginController extends HttpServlet {
    private UserServicesImpl userServices;
    private final String VIEW_PATH = ContextPathUtils.getClientPagesPath("login.jsp");
    private final String LAYOUT_PATH = ContextPathUtils.getLayoutPath("master.jsp");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("username") != null)
            resp.sendRedirect("home");
        else {
            req.setAttribute("pageTitle", "Film Booking - Đăng nhập");
            RenderViewUtils.renderViewToLayout(req, resp, VIEW_PATH, LAYOUT_PATH);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();
        password = HashTextGeneratorUtils.generateSHA256String(password);

        System.out.println(password);

        User loginUser = null;

        userServices = new UserServicesImpl();

        if (userServices.getByUsername(username) == null) {
            req.setAttribute("usernameError", "Tên người dùng không tồn tại!");
            RenderViewUtils.updateView(req, resp, VIEW_PATH);
            RenderViewUtils.renderViewToLayout(req, resp, VIEW_PATH, LAYOUT_PATH);
        } else {
            loginUser = userServices.getByUsername(username);
            if (loginUser.getUserPassword().equals(password)) {
                HttpSession userSession = req.getSession();
                userSession.setAttribute("username", loginUser.getUsername());
                userSession.setAttribute("userFullName", loginUser.getUserFullName());
                userSession.setAttribute("userEmail", loginUser.getUserEmail());
                userSession.setAttribute("accountRole", loginUser.getAccountRole());

                System.out.println("Login Controller Test: " + loginUser.getAccountRole());

                /* return to previous page that was visited before login
                 * if it has no previous page, return to home page
                 */
                RedirectPageUtils.redirectPreviousPageIfExist(req, resp);


            } else {
                req.setAttribute("passwordError", "Mật khẩu của bạn không đúng!");
                RenderViewUtils.updateView(req, resp, VIEW_PATH);
                RenderViewUtils.renderViewToLayout(req, resp, VIEW_PATH, LAYOUT_PATH);
            }
        }
    }

    @Override
    public void destroy() {
        userServices = null;
    }
}