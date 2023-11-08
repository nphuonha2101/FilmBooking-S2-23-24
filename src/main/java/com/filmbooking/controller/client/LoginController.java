package com.filmbooking.controller.client;

import com.filmbooking.model.Film;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.User;
import com.filmbooking.services.impls.UserServicesImpl;
import com.filmbooking.utils.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

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
        String username = StringUtils.handlesInputString(req.getParameter("username"));
        String password = StringUtils.handlesInputString(req.getParameter("password"));
        String rememberMe = req.getParameter("remember-me");
        password = StringUtils.generateSHA256String(password);

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
                // set login user and film booking object to session
                HttpSession userSession = req.getSession();
                userSession.setAttribute("loginUser", loginUser);

                FilmBooking filmBooking = new FilmBooking();
                filmBooking.setUsername(loginUser.getUsername());
                userSession.setAttribute("filmBooking", filmBooking);

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