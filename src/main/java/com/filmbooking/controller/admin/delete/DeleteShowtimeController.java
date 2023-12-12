package com.filmbooking.controller.admin.delete;

import com.filmbooking.hibernate.HibernateSessionProvider;
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
    private HibernateSessionProvider hibernateSessionProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        showtimeServices = new ShowtimeServicesImpl(hibernateSessionProvider);

        String showtimeID = req.getParameter("showtime-id_hidden");

        Showtime deleteShowtime = showtimeServices.getByID(showtimeID);

        if (showtimeServices.delete(deleteShowtime))
            resp.sendRedirect("showtime-management");

        hibernateSessionProvider.closeSession();

    }

    @Override
    public void destroy() {
        showtimeServices = null;
        hibernateSessionProvider = null;
    }
}
