package com.filmbooking.controller;

import java.io.*;

import com.filmbooking.DAOservices.IUserDAOServices;
import com.filmbooking.model.User;
import com.filmbooking.DAOservices.UserDAOServicesImpl;
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
                userSession.setAttribute("username", loginUser.getUsername());
                userSession.setAttribute("userFullName", loginUser.getUserFullName());
                userSession.setAttribute("userEmail", loginUser.getUserEmail());
                userSession.setAttribute("accountRole", loginUser.getAccountRole());

//                request.setAttribute("welcomeUser", loginUser.getUserFullName());
                System.out.println(loginUser.getAccountRole());

//                if (loginUser.getAccountRole().equalsIgnoreCase("admin")) {
//                    request.setAttribute("moreContents", "<a href='admin.jsp'>Admin page</a>");
//                    request.getRequestDispatcher("admin.jsp").forward(request, response);
//
//                }


                response.sendRedirect("home.jsp");
//                RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
//                requestDispatcher.forward(request, response);

            } else {
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