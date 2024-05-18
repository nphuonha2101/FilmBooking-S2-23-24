package com.filmbooking.controller.admin.delete;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Showtime;
import com.filmbooking.services.impls.ShowtimeServicesImpl;
import com.filmbooking.enumsAndConstants.enums.StatusCodeEnum;
import com.filmbooking.services.logProxy.CRUDServicesLogProxy;
import com.filmbooking.utils.WebAppPathUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/delete/showtime")
public class DeleteShowtimeController extends HttpServlet {
    private CRUDServicesLogProxy<Showtime> showtimeServices;
    private HibernateSessionProvider hibernateSessionProvider;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        showtimeServices = new CRUDServicesLogProxy<>(new ShowtimeServicesImpl(), req, hibernateSessionProvider);

        String showtimeSlug = req.getParameter("showtime");

        Showtime deleteShowtime = showtimeServices.getBySlug(showtimeSlug);

        if (deleteShowtime != null && showtimeServices.delete(deleteShowtime)) {
            req.setAttribute("statusCodeSuccess", StatusCodeEnum.DELETE_SHOWTIME_SUCCESSFUL.getStatusCode());
//            req.getRequestDispatcher(WebAppPathUtils.getURLWithContextPath(req, resp, "/admin/management/showtime")).forward(req, resp);
        } else {
            req.setAttribute("statusCodeErr", StatusCodeEnum.DELETE_SHOWTIME_FAILED.getStatusCode());
//            req.getRequestDispatcher(WebAppPathUtils.getURLWithContextPath(req, resp, "/admin/management/showtime")).forward(req, resp);

        }

        hibernateSessionProvider.closeSession();

    }

    @Override
    public void destroy() {
        showtimeServices = null;
        hibernateSessionProvider = null;
    }
}
