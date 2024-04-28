package com.filmbooking.controller.apis;

import java.io.IOException;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.User;
import com.filmbooking.services.impls.UserServicesImpl;
import com.filmbooking.utils.APIUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/api/v1/users/*", "/api/v1/users" })
public class UserAPI extends HttpServlet {
    UserServicesImpl userServicesImpl;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateSessionProvider sessionProvider = new HibernateSessionProvider();
        userServicesImpl = new UserServicesImpl(sessionProvider);

        APIUtils<User> apiUtils = new APIUtils<>(userServicesImpl, req, resp);
        String command = req.getParameter("command");

        apiUtils.processRequest(command);
        apiUtils.writeResponse(null, 0);
    }
}
