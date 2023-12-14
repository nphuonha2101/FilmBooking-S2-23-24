package com.filmbooking.filters;


import com.filmbooking.model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/auth/*")
public class AuthLoginFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        User loginUser = (User) req.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            res.sendRedirect("/login");
            return;
        }
        chain.doFilter(req, res);

    }
}
