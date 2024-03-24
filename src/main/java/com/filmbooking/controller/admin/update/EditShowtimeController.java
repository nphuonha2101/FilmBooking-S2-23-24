package com.filmbooking.controller.admin.update;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Film;
import com.filmbooking.model.Room;
import com.filmbooking.model.Showtime;
import com.filmbooking.services.impls.FilmServicesImpl;
import com.filmbooking.services.impls.RoomServicesImpl;
import com.filmbooking.services.impls.ShowtimeServicesImpl;
import com.filmbooking.enumsAndConstants.enums.StatusCodeEnum;
import com.filmbooking.utils.WebAppPathUtils;
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

@WebServlet(name = "editShowtime", value = "/admin/edit/showtime")
public class EditShowtimeController extends HttpServlet {
    private FilmServicesImpl filmServices;
    private ShowtimeServicesImpl showtimeServices;
    private RoomServicesImpl roomServices;
    private Showtime editShowtime;
    private HibernateSessionProvider hibernateSessionProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        filmServices = new FilmServicesImpl(hibernateSessionProvider);
        showtimeServices = new ShowtimeServicesImpl(hibernateSessionProvider);
        roomServices = new RoomServicesImpl(hibernateSessionProvider);

        String showtimeSlug = req.getParameter("showtime");
        editShowtime = showtimeServices.getBySlug(showtimeSlug);

        req.setAttribute("pageTitle", "editShowtimeTitle");

        req.setAttribute("editShowtime", editShowtime);
        req.setAttribute("filmData", filmServices.getAll());
        req.setAttribute("roomData", roomServices.getAll());

        RenderViewUtils.renderViewToLayout(req, resp,
                WebAppPathUtils.getAdminPagesPath("edit-showtime.jsp"),
                WebAppPathUtils.getLayoutPath("master.jsp"));

        hibernateSessionProvider.closeSession();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        filmServices = new FilmServicesImpl(hibernateSessionProvider);
        showtimeServices = new ShowtimeServicesImpl(hibernateSessionProvider);
        roomServices = new RoomServicesImpl(hibernateSessionProvider);

        String filmID = StringUtils.handlesInputString(req.getParameter("film-id"));
        String roomID = StringUtils.handlesInputString(req.getParameter("room-id"));
        LocalDateTime showtimeDate = LocalDateTime.parse(req.getParameter("showtime-datetime"), DateTimeFormatter.ISO_DATE_TIME);

        Film film = filmServices.getByID(filmID);
        Room room = roomServices.getByID(roomID);


        editShowtime.setFilm(film);
        editShowtime.setRoom(room);
        editShowtime.setShowtimeDate(showtimeDate);

        showtimeServices.update(editShowtime);

        req.setAttribute("statusCodeSuccess", StatusCodeEnum.UPDATE_SHOWTIME_SUCCESSFUL.getStatusCode());
        doGet(req, resp);

        hibernateSessionProvider.closeSession();
    }

    @Override
    public void destroy() {
        filmServices = null;
        showtimeServices = null;
        editShowtime = null;
        hibernateSessionProvider = null;
    }
}
