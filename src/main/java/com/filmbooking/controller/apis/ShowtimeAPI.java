package com.filmbooking.controller.apis;

import java.io.IOException;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Showtime;
import com.filmbooking.services.impls.ShowtimeServicesImpl;
import com.filmbooking.utils.APIUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/api/v1/showtimes/*", "/api/v1/showtimes" })
public class ShowtimeAPI extends HttpServlet {
    ShowtimeServicesImpl showtimeServicesImpl;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateSessionProvider sessionProvider = new HibernateSessionProvider();
        showtimeServicesImpl = new ShowtimeServicesImpl(sessionProvider);

        APIUtils<Showtime> apiUtils = new APIUtils<>(showtimeServicesImpl, req, resp);
        String command = req.getParameter("command");

        apiUtils.processRequest(command);
        apiUtils.writeResponse(null, 0);
    }
}
