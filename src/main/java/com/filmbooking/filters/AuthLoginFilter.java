package com.filmbooking.filters;


import com.filmbooking.model.User;
import com.filmbooking.utils.WebAppPathUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//@WebFilter(urlPatterns = {"/auth/*", "/logout"})
public class AuthLoginFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
//        User loginUser = (User) req.getSession().getAttribute("loginUser");
//        if (loginUser == null) {
//            res.sendRedirect(WebAppPathUtils.getURLWithContextPath(req, res, "/login"));
//            return;
//        }
        chain.doFilter(req, res);

    }
}
