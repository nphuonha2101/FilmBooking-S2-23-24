package com.filmbooking.filters;

import com.filmbooking.controller.customer.account.LogoutController;
import com.filmbooking.model.User;
import com.filmbooking.services.impls.UserServicesImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebFilter("/*")
public class UserInfoChangeDetectionFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        // Check if user info has changed
        User currentUser = (User) req.getSession().getAttribute("loginUser");

        if (currentUser == null) {
            chain.doFilter(req, res);
            return;
        }
        UserServicesImpl userServices = new UserServicesImpl();

        User currentUserInDB = userServices.getByUsername(currentUser.getUsername());

        if (currentUserInDB != null) {
            if (!Objects.equals(currentUser.getAccountRole(), currentUserInDB.getAccountRole())) {
                LogoutController.handleLogOut(req, res);
                return;
            }
        }



        chain.doFilter(req, res);
    }
}
