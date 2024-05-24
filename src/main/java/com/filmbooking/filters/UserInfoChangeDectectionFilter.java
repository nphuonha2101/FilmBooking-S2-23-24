package com.filmbooking.filters;

import com.filmbooking.controller.customer.account.LogoutController;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.User;
import com.filmbooking.services.impls.UserServicesImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebFilter("/*")
public class UserInfoChangeDectectionFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        // Check if user info has changed
        HibernateSessionProvider hibernateSessionProvider = new HibernateSessionProvider();
        User currentUser = (User) req.getSession().getAttribute("loginUser");

        if (currentUser == null) {
            chain.doFilter(req, res);
            return;
        }
        UserServicesImpl userServices = new UserServicesImpl();
        userServices.setSessionProvider(hibernateSessionProvider);

        User currentUserInDB = userServices.getByUsername(currentUser.getUsername());

        if (!Objects.equals(currentUser.getAccountRole(), currentUserInDB.getAccountRole())) {
            LogoutController.handleLogOut(req, res);
            return;
        }

        hibernateSessionProvider.closeSession();
        chain.doFilter(req, res);
    }
}
