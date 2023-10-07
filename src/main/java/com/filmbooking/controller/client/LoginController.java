package com.filmbooking.controller.client;

import java.io.*;

import com.filmbooking.model.User;
import com.filmbooking.services.UserServicesImpl;
import com.filmbooking.ultils.ContextPathUtils;
import com.filmbooking.ultils.RenderViewUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "login", value = "/login")
public class LoginController extends HttpServlet {
    private UserServicesImpl userServices;
    private String viewPath = ContextPathUtils.getClientPagesPath("login.jsp");
    private String layoutPath = ContextPathUtils.getLayoutPath("master.jsp");

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageTitle", "Film Booking - Đăng nhập");
        RenderViewUtils.renderViewToLayout(req, resp, viewPath, layoutPath);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();

        User loginUser = null;

        userServices = new UserServicesImpl();

        if (userServices.getByUsername(username) == null) {
            req.setAttribute("usernameError", "Tên người dùng không tồn tại!");
            RenderViewUtils.updateView(req, resp, viewPath);
            RenderViewUtils.renderViewToLayout(req, resp, viewPath, layoutPath);
        } else {
            loginUser = userServices.getByUsername(username);
            if (loginUser.getUserPassword().equals(password)) {
                HttpSession userSession = req.getSession();
                userSession.setAttribute("username", loginUser.getUsername());
                userSession.setAttribute("userFullName", loginUser.getUserFullName());
                userSession.setAttribute("userEmail", loginUser.getUserEmail());
                userSession.setAttribute("accountRole", loginUser.getAccountRole());

                System.out.println("Login Controller Test: " + loginUser.getAccountRole());

                // return to previous page that was visited before login
                String previousPage = (String) userSession.getAttribute("previousPage");
                if (previousPage != null && !previousPage.equals("/login")) {
                    resp.sendRedirect(previousPage);
                    return;
                }
                resp.sendRedirect("home");


            } else {
                req.setAttribute("passwordError", "Mật khẩu của bạn không đúng!");
                RenderViewUtils.updateView(req, resp, viewPath);
                RenderViewUtils.renderViewToLayout(req, resp, viewPath, layoutPath);
            }
        }
    }


    @Override
    public void destroy() {
    }
}