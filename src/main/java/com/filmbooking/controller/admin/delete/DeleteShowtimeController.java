package com.filmbooking.controller.admin.delete;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Showtime;
import com.filmbooking.services.IShowtimeServices;
import com.filmbooking.services.impls.ShowtimeServicesImpl;
import com.filmbooking.statusEnums.StatusCodeEnum;
import com.filmbooking.utils.PathUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/delete/showtime")
public class DeleteShowtimeController extends HttpServlet {
    private IShowtimeServices showtimeServices;
    private HibernateSessionProvider hibernateSessionProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        showtimeServices = new ShowtimeServicesImpl(hibernateSessionProvider);

        String showtimeSlug = req.getParameter("showtime");

        Showtime deleteShowtime = showtimeServices.getBySlug(showtimeSlug);

        if (showtimeServices.delete(deleteShowtime)) {
            req.setAttribute("statusCodeSuccess", StatusCodeEnum.DELETE_SHOWTIME_SUCCESSFUL.getStatusCode());
            req.getRequestDispatcher(PathUtils.getURLWithContextPath(req, "/admin/management/showtime")).forward(req, resp);
        } else {
            req.setAttribute("statusCodeErr", StatusCodeEnum.DELETE_SHOWTIME_FAILED.getStatusCode());
            req.getRequestDispatcher(PathUtils.getURLWithContextPath(req, "/admin/management/showtime")).forward(req, resp);
        }

        hibernateSessionProvider.closeSession();

    }

    @Override
    public void destroy() {
        showtimeServices = null;
        hibernateSessionProvider = null;
    }
}
