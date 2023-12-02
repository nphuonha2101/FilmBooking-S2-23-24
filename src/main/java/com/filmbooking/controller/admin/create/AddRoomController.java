package com.filmbooking.controller.admin.create;

import com.filmbooking.model.Room;
import com.filmbooking.model.Theater;
import com.filmbooking.services.IRoomServices;
import com.filmbooking.services.ITheaterServices;
import com.filmbooking.services.impls.RoomServicesImpl;
import com.filmbooking.services.impls.TheaterServicesImpl;
import com.filmbooking.utils.ContextPathUtils;
import com.filmbooking.utils.RenderViewUtils;
import com.filmbooking.utils.StringUtils;
import com.filmbooking.hibernate.HibernateSessionProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "addRoom", value = "/add-room")
public class AddRoomController extends HttpServlet {
    private IRoomServices roomServices;
    private ITheaterServices theaterServices;
    private HibernateSessionProvider hibernateSessionProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();

        theaterServices = new TheaterServicesImpl(hibernateSessionProvider);

        req.setAttribute("pageTitle", "addRoomTitle");

        req.setAttribute("theaters", theaterServices.getAll());

        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getAdminPagesPath("add-room.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));

//        RenderViewUtils.updateView(req, resp,
//                ContextPathUtils.getLayoutPath("master.jsp"));

        hibernateSessionProvider.closeSession();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();

        roomServices = new RoomServicesImpl(hibernateSessionProvider);
        theaterServices = new TheaterServicesImpl(hibernateSessionProvider);

        String roomName = StringUtils.handlesInputString(req.getParameter("room-name"));
        String theaterID = StringUtils.handlesInputString(req.getParameter("theater-id"));
        int roomRows = Integer.parseInt(req.getParameter("room-rows"));
        int roomCols = Integer.parseInt(req.getParameter("room-cols"));

        Theater theater = theaterServices.getByID(theaterID);

        Room newRoom = new Room(roomName, roomRows, roomCols, theater);

        roomServices.save(newRoom);

        resp.sendRedirect("room-management");

        hibernateSessionProvider.closeSession();
    }

    @Override
    public void destroy() {
        roomServices = null;
        theaterServices = null;
        hibernateSessionProvider = null;
    }
}
