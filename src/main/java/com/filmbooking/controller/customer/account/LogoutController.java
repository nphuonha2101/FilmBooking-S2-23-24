package com.filmbooking.controller.customer.account;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.Showtime;
import com.filmbooking.services.impls.ShowtimeServicesImpl;
import com.filmbooking.services.logProxy.CRUDServicesLogProxy;
import com.filmbooking.utils.WebAppPathUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "logout", value = "/logout")
public class LogoutController extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleLogOut(req, resp);
    }

    public static void handleLogOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            FilmBooking filmBooking = (FilmBooking) session.getAttribute("filmBooking");
            Showtime showtime = filmBooking.getShowtime();

            if (filmBooking.getBookedSeats() != null && showtime != null) {
                System.out.println(Arrays.toString(filmBooking.getBookedSeats()));
                HibernateSessionProvider hibernateSessionProvider = new HibernateSessionProvider();
                CRUDServicesLogProxy<Showtime> showtimeServices = new CRUDServicesLogProxy<>(new ShowtimeServicesImpl(), req, hibernateSessionProvider);
                showtime.releaseSeats(filmBooking.getBookedSeats());
                showtimeServices.update(showtime);
                hibernateSessionProvider.closeSession();
            }
            session.invalidate();
            resp.sendRedirect(WebAppPathUtils.getURLWithContextPath(req, resp, "/login"));
        } else resp.sendRedirect(WebAppPathUtils.getURLWithContextPath(req, resp, "/home"));
    }
}
