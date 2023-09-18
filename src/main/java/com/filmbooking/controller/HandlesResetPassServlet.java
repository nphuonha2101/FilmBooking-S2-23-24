package com.filmbooking.controller;

import com.filmbooking.DAOservices.IUserDAOServices;
import com.filmbooking.DAOservices.UserDAOServicesImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="handlesResetPass", value = "/handles-reset-password")
public class HandlesResetPassServlet extends HttpServlet {
    private IUserDAOServices userDAOServices;
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newPassword = request.getParameter("new-password");
        String confirmNewPassword = request.getParameter("confirm-new-password");

        userDAOServices = new UserDAOServicesImpl();

        String usernameForgot = (String) request.getSession().getAttribute("forgot-username");

        if (newPassword.equals(confirmNewPassword)) {
            userDAOServices.changePassword(usernameForgot, newPassword);
            request.setAttribute("successfulMessage", "<span class=\"material-symbols-outlined\">\n" +
                    "task_alt\n" +
                    "</span> Your password was reset successfully!");
            request.setAttribute("additionElement", "<a href='login.jsp'>Login now");
        } else {
            request.setAttribute("confirmPasswordError", "Your confirm password is not match!");
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("reset-password.jsp");
        requestDispatcher.include(request, response);

    }
}
