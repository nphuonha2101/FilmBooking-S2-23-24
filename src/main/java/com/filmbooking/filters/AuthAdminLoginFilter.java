package com.filmbooking.filters;

import com.filmbooking.model.User;
import com.filmbooking.utils.RedirectPageUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/admin", "/add-film", "/edit-film", "/delete-film", "/film-management",
        "/room-management","/add-room", "/edit-room", "/delete-room"})
public class AuthAdminLoginFilter extends HttpFilter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException,
            ServletException {

        HttpSession userSession = req.getSession();
        User loginUser = (User) userSession.getAttribute("loginUser");
        if (loginUser == null) {
            RedirectPageUtils.redirectPage("login", null, req, resp);
            return;
        } else {
            String accountRole = loginUser.getAccountRole();
            if (!accountRole.equals("admin")) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
        }

        chain.doFilter(req, resp);
    }

}
