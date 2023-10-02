package com.filmbooking.filters;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class HideFileExtensionFilter extends HttpFilter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException,
            ServletException {

        String uri = req.getRequestURI();
        System.out.println(" Test filter URI: " +uri);
        if (uri.endsWith(".jsp"))
            res.sendError(HttpServletResponse.SC_NOT_FOUND);
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {

    }
}
