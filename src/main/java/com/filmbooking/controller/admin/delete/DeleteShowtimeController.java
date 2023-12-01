package com.filmbooking.controller.admin.delete;

import com.filmbooking.model.Showtime;
import com.filmbooking.services.IShowtimeServices;
import com.filmbooking.services.impls.ShowtimeServicesImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delete-showtime")
public class DeleteShowtimeController extends HttpServlet {
    private IShowtimeServices showtimeServices;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showtimeServices = new ShowtimeServicesImpl();

        showtimeServices.openSession();

        String showtimeID = req.getParameter("showtime-id_hidden");

        Showtime deleteShowtime = showtimeServices.getByID(showtimeID);

        showtimeServices.delete(deleteShowtime);

        resp.sendRedirect("showtime-management");

        showtimeServices.closeSession();

    }

    @Override
    public void destroy() {
        showtimeServices = null;
    }
}
