package com.filmbooking.filters;

import com.filmbooking.model.User;
import com.filmbooking.utils.WebAppPathUtils;
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

            RedirectPageUtils.redirectPage(WebAppPathUtils.getURLWithContextPath(req, resp, "/login"), null, req, resp);
            return;
        } else {
            String accountRole = loginUser.getAccountRole();
            String requestURI = req.getRequestURI();
            if (requestURI.contains("/admin/management/log") || requestURI.contains("/admin/management/user")) {
                if (!accountRole.equalsIgnoreCase("superAdmin")) {
                    resp.sendError(HttpServletResponse.SC_FORBIDDEN);
                    return;
                }
            } else if (!accountRole.equalsIgnoreCase("admin") && !accountRole.equalsIgnoreCase("superAdmin")) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }

        chain.doFilter(req, resp);
    }

}
