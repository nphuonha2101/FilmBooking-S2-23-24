package com.filmbooking.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/admin", "/add-film", "/edit-film", "/delete-film", "/film-management"})
public class AuthAdminLoginFilter extends HttpFilter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException,
            ServletException {

        HttpSession userSession = req.getSession();
        if (userSession.getAttribute("username") == null) {
            resp.sendRedirect("login");
            return;
        } else {
            String accountRole = (String) userSession.getAttribute("accountRole");
            if (!accountRole.equals("admin")) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
        }

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
