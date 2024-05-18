package com.filmbooking.controller.admin.create;

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
import com.filmbooking.utils.WebAppPathUtils;
import com.filmbooking.utils.StringUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/admin/add/showtime")
public class AddShowtimeController extends HttpServlet {
    private CRUDServicesLogProxy<Film> filmServices;
    private CRUDServicesLogProxy<Showtime> showtimeServices;
    private CRUDServicesLogProxy<Room> roomServices;
    private HibernateSessionProvider hibernateSessionProvider;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();

        filmServices = new CRUDServicesLogProxy<>(new FilmServicesImpl(), req, hibernateSessionProvider);
        roomServices = new CRUDServicesLogProxy<>(new RoomServicesImpl(), req, hibernateSessionProvider);

        Page addShowtimePage = new AdminPage(
                "addShowtimeTitle",
                "add-showtime",
                "master");
        addShowtimePage.putAttribute("filmData", filmServices.getAll().getMultipleResults());
        addShowtimePage.putAttribute("roomData", roomServices.getAll().getMultipleResults());
        addShowtimePage.render(req, resp);

        hibernateSessionProvider.closeSession();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();

        showtimeServices = new CRUDServicesLogProxy<>(new ShowtimeServicesImpl(), req, hibernateSessionProvider);
        roomServices = new CRUDServicesLogProxy<>(new RoomServicesImpl(), req, hibernateSessionProvider);
        filmServices = new CRUDServicesLogProxy<>(new FilmServicesImpl(), req, hibernateSessionProvider);

        String filmID = StringUtils.handlesInputString(req.getParameter("film-id"));
        String roomID = StringUtils.handlesInputString(req.getParameter("room-id"));
        Room showtimeRoom = roomServices.getByID(roomID);
        String showtimeDate = req.getParameter("showtime-datetime");
        LocalDateTime showtimeLDT = LocalDateTime.parse(showtimeDate, DateTimeFormatter.ISO_DATE_TIME);

        Film film = filmServices.getByID(filmID);

        Showtime newShowtime = new Showtime(film, showtimeRoom, showtimeLDT);

        showtimeServices.save(newShowtime);

        req.setAttribute("statusCodeSuccess", StatusCodeEnum.ADD_SHOWTIME_SUCCESSFUL.getStatusCode());
        doGet(req, resp);

        hibernateSessionProvider.closeSession();
    }

    @Override
    public void destroy() {
        filmServices = null;
        roomServices = null;
        showtimeServices = null;
        hibernateSessionProvider = null;
    }
}
