package com.filmbooking.controller.admin.update;

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
import com.filmbooking.utils.StringUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "editShowtime", value = "/edit-showtime")
public class EditShowtimeController extends HttpServlet {
    private IFilmServices filmServices;
    private IShowtimeServices showtimeServices;
    private IRoomViewServices roomViewServices;
    private Showtime editShowtime;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filmServices = new FilmServicesImpl();
        showtimeServices = new ShowtimeServicesImpl();
        roomViewServices = new RoomViewServicesImpl();

        String showtimeID = req.getParameter("showtime-id_hidden");
        editShowtime = showtimeServices.getByID(showtimeID);


        req.setAttribute("sectionTitle", "Sửa suất chiếu");
        req.setAttribute("pageTitle", "Trang Admin - Sửa suất chiếu");

        req.setAttribute("editShowtime", editShowtime);
        req.setAttribute("filmData", filmServices.getAll());
        req.setAttribute("roomData", roomViewServices.getAll());

        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getAdminPagesPath("edit-showtime.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));

        RenderViewUtils.updateView(req, resp,
                ContextPathUtils.getLayoutPath("master.jsp"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filmID = StringUtils.handlesInputString(req.getParameter("film-id"));
        String roomID = StringUtils.handlesInputString(req.getParameter("room-id"));
        LocalDateTime showtimeDate = LocalDateTime.parse(req.getParameter("showtime-datetime"), DateTimeFormatter.ISO_DATE_TIME);

        editShowtime.setFilmID(filmID);
        editShowtime.setRoomID(roomID);
        editShowtime.setShowtimeDate(showtimeDate);

        showtimeServices.update(editShowtime);

        resp.sendRedirect("showtime-management");
    }

    @Override
    public void destroy() {
        filmServices = null;
        showtimeServices = null;
        editShowtime = null;
        roomViewServices = null;
    }
}
