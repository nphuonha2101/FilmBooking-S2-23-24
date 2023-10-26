package com.filmbooking.controller.admin.create;

import com.filmbooking.model.Showtime;
import com.filmbooking.services.IFilmServices;
import com.filmbooking.services.IRoomServices;
import com.filmbooking.services.IRoomViewServices;
import com.filmbooking.services.IShowtimeServices;
import com.filmbooking.services.impls.FilmServicesImpl;
import com.filmbooking.services.impls.RoomServicesImpl;
import com.filmbooking.services.impls.RoomViewServicesImpl;
import com.filmbooking.services.impls.ShowtimeServicesImpl;
import com.filmbooking.utils.ContextPathUtils;
import com.filmbooking.utils.RenderViewUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/add-showtime")
public class AddShowtimeController extends HttpServlet {
    private IFilmServices filmServices;
    private IShowtimeServices showtimeServices;
    private IRoomServices roomServices;
    private IRoomViewServices roomViewServices;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filmServices = new FilmServicesImpl();
        showtimeServices = new ShowtimeServicesImpl();
        roomServices = new RoomServicesImpl();
        roomViewServices = new RoomViewServicesImpl();

        req.setAttribute("sectionTitle", "Thêm suất chiếu");
        req.setAttribute("pageTitle", "Trang Admin - Thêm suất chiếu");

        req.setAttribute("filmData", filmServices.getAll());
        req.setAttribute("roomData", roomViewServices.getAll());

        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getAdminPagesPath("add-showtime.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));

        RenderViewUtils.updateView(req, resp,
                ContextPathUtils.getLayoutPath("master.jsp"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showtimeServices = new ShowtimeServicesImpl();

        String filmID = req.getParameter("film-id");
        String roomID = req.getParameter("room-id");
        String showtimeDate = req.getParameter("showtime-datetime");
        LocalDateTime showtimeLDT = LocalDateTime.parse(showtimeDate, DateTimeFormatter.ISO_DATE_TIME);

        Showtime newShowtime = new Showtime(filmID, roomID, showtimeLDT);
        showtimeServices.save(newShowtime);

        resp.sendRedirect("showtime-management");

    }

    @Override
    public void destroy() {
        filmServices = null;
        roomViewServices = null;
        roomServices = null;
        showtimeServices = null;
    }
}
