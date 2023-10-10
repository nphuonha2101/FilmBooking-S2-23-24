package com.filmbooking.controller.admin;

import com.filmbooking.dao.IDAO;
import com.filmbooking.model.Showtime;
import com.filmbooking.services.IShowtimeServices;
import com.filmbooking.services.impls.ShowtimeServicesImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/add-showtime")
public class AddShowtimeController extends HttpServlet {
    private IShowtimeServices showtimeServices;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showtimeServices = new ShowtimeServicesImpl();

        String showtimeID = req.getParameter("showtime-id");

        Showtime deleteShowtime = showtimeServices.getByID(showtimeID);

        showtimeServices.delete(deleteShowtime);

        resp.sendRedirect("showtime-management");

    }
}
