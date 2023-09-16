package com.filmbooking.controller;

import java.io.*;

import com.filmbooking.model.IUserDAOServices;
import com.filmbooking.model.User;
import com.filmbooking.model.UserDAOServicesImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "handlesLogin", value = "/handles-login")
public class HandlesLoginServlet extends HttpServlet {

    private IUserDAOServices userDAOServices;
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User loginUser = null;

        userDAOServices = new UserDAOServicesImpl();

        if (userDAOServices.getUserByUsername(username) == null) {
            request.setAttribute("usernameError", "Username is not existed!");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            requestDispatcher.include(request, response);
        } else {
            loginUser = userDAOServices.getUserByUsername(username);
            if (loginUser.getUserPassword().equals(password)) {
                HttpSession userSession = request.getSession();
                userSession.setAttribute("user", loginUser);

                request.setAttribute("welcomeUser", loginUser.getUserFullName());
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
                requestDispatcher.forward(request, response);

            }
            else {
                request.setAttribute("passwordError", "Your password is wrong!");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
                requestDispatcher.include(request, response);
            }
        }



    }


    @Override
    public void destroy() {
    }
}