package com.filmbooking.controller.apis;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.LogModel;
import com.filmbooking.services.impls.LogModelServicesImpl;
import com.filmbooking.utils.APIUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(urlPatterns = { "/api/v1/logs/*", "/api/v1/logs" })
public class LogAPI extends HttpServlet {
    LogModelServicesImpl logModelServices;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateSessionProvider sessionProvider = new HibernateSessionProvider();
        logModelServices = new LogModelServicesImpl(sessionProvider);

        APIUtils<LogModel> apiUtils = new APIUtils<>(logModelServices, req, resp);
        String command = req.getParameter("command");

        apiUtils.processRequest(command);
        apiUtils.writeResponse(null, 0);
    }
}
