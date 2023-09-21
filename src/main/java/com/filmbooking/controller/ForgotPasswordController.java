package com.filmbooking.controller;

import com.filmbooking.DAOservices.IUserDAOServices;
import com.filmbooking.model.User;
import com.filmbooking.DAOservices.UserDAOServicesImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "forgotPassword", value = "/forgot-password")
public class ForgotPasswordController extends HttpServlet {
    private IUserDAOServices userDAOServices;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");

        userDAOServices = new UserDAOServicesImpl();

        if (userDAOServices.getUserByUsername(username) == null) {
            request.setAttribute("usernameError", "This username doesn't exist!");
        } else {
            User foundUser = userDAOServices.getUserByUsername(username);
            if (foundUser.getUserEmail().equals(email)) {
                HttpSession session = request.getSession();
                session.setAttribute("forgot-username", foundUser.getUsername());

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("reset-password.jsp");
                requestDispatcher.forward(request, response);
            } else {
                request.setAttribute("emailError", "This email is not match with username!");
            }
        }

        request.getRequestDispatcher("forgot.jsp").include(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
