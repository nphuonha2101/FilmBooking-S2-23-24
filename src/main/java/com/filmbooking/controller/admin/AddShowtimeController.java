package com.filmbooking.controller.admin;

import com.filmbooking.services.IFilmServices;
import com.filmbooking.services.IRoomServices;
import com.filmbooking.services.IShowtimeServices;
import com.filmbooking.services.impls.FilmServicesImpl;
import com.filmbooking.services.impls.RoomServicesImpl;
import com.filmbooking.services.impls.ShowtimeServicesImpl;
import com.filmbooking.utils.ContextPathUtils;
import com.filmbooking.utils.RenderViewUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/add-showtime")
public class AddShowtimeController extends HttpServlet {
    private IFilmServices filmServices;
    private IShowtimeServices showtimeServices;
    private IRoomServices roomServices;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filmServices = new FilmServicesImpl();
        showtimeServices = new ShowtimeServicesImpl();
        roomServices = new RoomServicesImpl();

        req.setAttribute("sectionTitle", "Thêm suất chiếu");
        req.setAttribute("pageTitle", "Trang Admin - Thêm suất chiếu");

        req.setAttribute("filmData", filmServices.getAll());
        req.setAttribute("roomData", roomServices.getAll());

        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getAdminPagesPath("add-showtime.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));

        RenderViewUtils.updateView(req, resp,
                ContextPathUtils.getLayoutPath("master.jsp"));
    }
}
