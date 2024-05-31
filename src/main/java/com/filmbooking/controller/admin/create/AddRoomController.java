package com.filmbooking.controller.admin.create;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Room;
import com.filmbooking.model.Theater;
import com.filmbooking.page.AdminPage;
import com.filmbooking.page.Page;
import com.filmbooking.services.impls.RoomServicesImpl;
import com.filmbooking.services.impls.TheaterServicesImpl;
import com.filmbooking.enumsAndConstants.enums.StatusCodeEnum;
import com.filmbooking.services.logProxy.CRUDServicesLogProxy;
import com.filmbooking.utils.StringUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "addRoom", value = "/admin/add/room")
public class AddRoomController extends HttpServlet {
    private CRUDServicesLogProxy<Room> roomServices;
    private CRUDServicesLogProxy<Theater> theaterServices;
    private HibernateSessionProvider hibernateSessionProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        theaterServices = new CRUDServicesLogProxy<>(new TheaterServicesImpl(), req, Theater.class);

        Page addRoomPage = new AdminPage(
                "addRoomTitle",
                "add-room",
                "master"
        );
        addRoomPage.putAttribute("theaters", theaterServices.selectAll());
        addRoomPage.render(req, resp);

        hibernateSessionProvider.closeSession();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();


        roomServices = new CRUDServicesLogProxy<>(new RoomServicesImpl(), req, Room.class);
        theaterServices = new CRUDServicesLogProxy<>(new TheaterServicesImpl(), req, Theater.class);

        String roomName = StringUtils.handlesInputString(req.getParameter("room-name"));
        String theaterID = StringUtils.handlesInputString(req.getParameter("theater-id"));
        int roomRows = Integer.parseInt(req.getParameter("room-rows"));
        int roomCols = Integer.parseInt(req.getParameter("room-cols"));

        Theater theater = theaterServices.select(theaterID);

        Room newRoom = new Room(roomName, roomRows, roomCols, theater);

        roomServices.insert(newRoom);

        req.setAttribute("statusCodeSuccess", StatusCodeEnum.ADD_ROOM_SUCCESSFUL.getStatusCode());
        doGet(req, resp);

        hibernateSessionProvider.closeSession();
    }

    @Override
    public void destroy() {
        roomServices = null;
        theaterServices = null;
        hibernateSessionProvider = null;
    }
}
