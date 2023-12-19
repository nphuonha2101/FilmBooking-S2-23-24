package com.filmbooking.filters;

import com.filmbooking.model.User;
import com.filmbooking.utils.PathUtils;
import com.filmbooking.utils.RedirectPageUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/admin/*")
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
            RedirectPageUtils.redirectPage(PathUtils.getURLWithContextPath(req, "/login"), null, req, resp);
            return;
        } else {
            String accountRole = loginUser.getAccountRole();
            if (!accountRole.equals("admin")) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }

        chain.doFilter(req, resp);
    }

}
