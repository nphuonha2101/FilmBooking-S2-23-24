package com.filmbooking.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/")
public class AuthLoginFilter extends HttpFilter {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession userSession = req.getSession();
        if (userSession.getAttribute("username") == null) {
            res.sendRedirect("login");
            return;
        } else {
            String referer = req.getHeader("Referer");
            res.sendRedirect(referer);

        }

        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
