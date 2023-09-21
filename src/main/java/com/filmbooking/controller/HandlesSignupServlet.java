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

import java.io.IOException;

@WebServlet(name = "handlesSignup", value = "/handles-signup")
public class HandlesSignupServlet extends HttpServlet {

    private IUserDAOServices userDAOServices;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("dynamicContents", "/views/pages/client/signup.jsp");

        RequestDispatcher homeDispatcher = req.getRequestDispatcher("/views/layout/master.jsp");
        homeDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String userFullName = request.getParameter("user-full-name");
        String userEmail = request.getParameter("email");
        String userPassword = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm-password");


        userDAOServices = new UserDAOServicesImpl();

        if (userDAOServices.getUserByUsername(username) != null) {
            request.setAttribute("usernameError", "Username existed. Please choose other username!");

        } else if (userPassword.equals(confirmPassword)) {
            User newUser = new User(username, userFullName, userEmail, userPassword, "customer");
            userDAOServices.saveUser(newUser);
            request.setAttribute("successfulMessage", "<span class=\"material-symbols-outlined\">\n" +
                    "task_alt </span>" +
                    " Congratulation! Your account was created.");
        } else {
            request.setAttribute("confirmPasswordError", "Your confirm password doesn't match!");
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/pages/client/signup.jsp");
        requestDispatcher.include(request, response);
        request.setAttribute("dynamicContents", "/views/pages/client/signup.jsp");
        RequestDispatcher masterDispatcher = request.getRequestDispatcher("/views/layout/master.jsp");
        masterDispatcher.forward(request, response);


    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
