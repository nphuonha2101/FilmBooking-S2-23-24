package com.filmbooking.controller.admin.create;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Film;
import com.filmbooking.model.Room;
import com.filmbooking.model.Showtime;
import com.filmbooking.services.IFilmServices;
import com.filmbooking.services.IRoomServices;
import com.filmbooking.services.IShowtimeServices;
import com.filmbooking.services.impls.FilmServicesImpl;
import com.filmbooking.services.impls.RoomServicesImpl;
import com.filmbooking.services.impls.ShowtimeServicesImpl;
import com.filmbooking.statusEnums.StatusCodeEnum;
import com.filmbooking.utils.PathUtils;
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

@WebServlet("/admin/add/showtime")
public class AddShowtimeController extends HttpServlet {
    private IFilmServices filmServices;
    private IShowtimeServices showtimeServices;
    private IRoomServices roomServices;
    private HibernateSessionProvider hibernateSessionProvider;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();

        filmServices = new FilmServicesImpl(hibernateSessionProvider);
        roomServices = new RoomServicesImpl(hibernateSessionProvider);

        req.setAttribute("pageTitle", "addShowtimeTitle");

        req.setAttribute("filmData", filmServices.getAll());
        req.setAttribute("roomData", roomServices.getAll());

        RenderViewUtils.renderViewToLayout(req, resp,
                PathUtils.getAdminPagesPath("add-showtime.jsp"),
                PathUtils.getLayoutPath("master.jsp"));


        hibernateSessionProvider.closeSession();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();

        showtimeServices = new ShowtimeServicesImpl(hibernateSessionProvider);
        roomServices = new RoomServicesImpl(hibernateSessionProvider);
        filmServices = new FilmServicesImpl(hibernateSessionProvider);

        String filmID = StringUtils.handlesInputString(req.getParameter("film-id"));
        String roomID = StringUtils.handlesInputString(req.getParameter("room-id"));
        Room showtimeRoom = roomServices.getByRoomID(roomID);
        String showtimeDate = req.getParameter("showtime-datetime");
        LocalDateTime showtimeLDT = LocalDateTime.parse(showtimeDate, DateTimeFormatter.ISO_DATE_TIME);

        Film film = filmServices.getByFilmID(filmID);

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
