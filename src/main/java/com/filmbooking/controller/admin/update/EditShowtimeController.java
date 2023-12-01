package com.filmbooking.controller.admin.update;

import com.filmbooking.model.Film;
import com.filmbooking.model.Room;
import com.filmbooking.model.Showtime;
import com.filmbooking.services.IFilmServices;
import com.filmbooking.services.IRoomServices;
import com.filmbooking.services.IShowtimeServices;
import com.filmbooking.services.impls.FilmServicesImpl;
import com.filmbooking.services.impls.RoomServicesImpl;
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
    private IRoomServices roomServices;
    private Showtime editShowtime;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filmServices = new FilmServicesImpl();
        showtimeServices = new ShowtimeServicesImpl();
        roomServices = new RoomServicesImpl();

        filmServices.openSession();
        showtimeServices.openSession();
        roomServices.openSession();

        String showtimeID = req.getParameter("showtime-id_hidden");
        editShowtime = showtimeServices.getByID(showtimeID);

        req.setAttribute("pageTitle", "editShowtimeTitle");

        req.setAttribute("editShowtime", editShowtime);
        req.setAttribute("filmData", filmServices.getAll());
        req.setAttribute("roomData", roomServices.getAll());

        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getAdminPagesPath("edit-showtime.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));

//        RenderViewUtils.updateView(req, resp,
//                ContextPathUtils.getLayoutPath("master.jsp"));

        filmServices.closeSession();
        showtimeServices.closeSession();
        roomServices.closeSession();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filmServices.openSession();
        roomServices.openSession();
        showtimeServices.openSession();

        String filmID = StringUtils.handlesInputString(req.getParameter("film-id"));
        String roomID = StringUtils.handlesInputString(req.getParameter("room-id"));
        LocalDateTime showtimeDate = LocalDateTime.parse(req.getParameter("showtime-datetime"), DateTimeFormatter.ISO_DATE_TIME);

        Film film = filmServices.getByFilmID(filmID);
        Room room = roomServices.getByRoomID(roomID);


        editShowtime.setFilm(film);
        editShowtime.setRoom(room);
        editShowtime.setShowtimeDate(showtimeDate);

        showtimeServices.update(editShowtime);

        resp.sendRedirect("showtime-management");

        filmServices.closeSession();
        roomServices.closeSession();
        showtimeServices.closeSession();
    }

    @Override
    public void destroy() {
        filmServices = null;
        showtimeServices = null;
        editShowtime = null;
    }
}
