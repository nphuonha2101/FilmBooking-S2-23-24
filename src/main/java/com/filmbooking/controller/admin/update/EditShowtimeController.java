package com.filmbooking.controller.admin.update;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Film;
import com.filmbooking.model.Room;
import com.filmbooking.model.Showtime;
import com.filmbooking.page.AdminPage;
import com.filmbooking.page.Page;
import com.filmbooking.services.impls.FilmServicesImpl;
import com.filmbooking.services.impls.RoomServicesImpl;
import com.filmbooking.services.impls.ShowtimeServicesImpl;
import com.filmbooking.enumsAndConstants.enums.StatusCodeEnum;
import com.filmbooking.services.logProxy.CRUDServicesLogProxy;
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
    private CRUDServicesLogProxy<Showtime> showtimeServicesLog;
    private Showtime editShowtime;
    private HibernateSessionProvider hibernateSessionProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        filmServices = new FilmServicesImpl();
        showtimeServices = new ShowtimeServicesImpl();
        roomServices = new RoomServicesImpl();

        String showtimeSlug = req.getParameter("showtime");
        editShowtime = showtimeServices.getBySlug(showtimeSlug);

        Page editShowtimePage = new AdminPage(
                "editShowtimeTitle",
                "edit-showtime",
                "master");
        editShowtimePage.putAttribute("editShowtime", editShowtime);
        editShowtimePage.putAttribute("filmData", filmServices.selectAll());
        editShowtimePage.putAttribute("roomData", roomServices.selectAll());

        editShowtimePage.render(req, resp);

        hibernateSessionProvider.closeSession();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        filmServices = new FilmServicesImpl();
        showtimeServices = new ShowtimeServicesImpl();
        roomServices = new RoomServicesImpl();
        showtimeServicesLog = new CRUDServicesLogProxy<>(new ShowtimeServicesImpl(), req, Showtime.class);

        String filmID = StringUtils.handlesInputString(req.getParameter("film-id"));
        String roomID = StringUtils.handlesInputString(req.getParameter("room-id"));
        LocalDateTime showtimeDate = LocalDateTime.parse(req.getParameter("showtime-datetime"), DateTimeFormatter.ISO_DATE_TIME);

        Film film = filmServices.select(filmID);
        Room room = roomServices.select(roomID);


        editShowtime.setFilm(film);
        editShowtime.setRoom(room);
        editShowtime.setShowtimeDate(showtimeDate);

        showtimeServicesLog.update(editShowtime);

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
