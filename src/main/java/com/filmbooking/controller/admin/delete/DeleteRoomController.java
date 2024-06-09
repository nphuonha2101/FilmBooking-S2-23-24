package com.filmbooking.controller.admin.delete;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Room;
import com.filmbooking.services.impls.RoomServicesImpl;
import com.filmbooking.enumsAndConstants.enums.StatusCodeEnum;
import com.filmbooking.services.logProxy.CRUDServicesLogProxy;
import com.filmbooking.utils.WebAppPathUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "deleteRoom", value = "/admin/delete/room")
public class DeleteRoomController extends HttpServlet {
    private CRUDServicesLogProxy<Room> roomServices;
    private HibernateSessionProvider hibernateSessionProvider;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        roomServices = new CRUDServicesLogProxy<>(new RoomServicesImpl(), req, Room.class);

        String roomSlug = req.getParameter("room");
        System.out.println("DeleteFilmController Test: " + roomSlug);

        Room deletedRoom = roomServices.getBySlug(roomSlug);
        if (roomServices.delete(deletedRoom)) {
            req.setAttribute("statusCodeSuccess", StatusCodeEnum.DELETE_ROOM_SUCCESSFUL.getStatusCode());
//            req.getRequestDispatcher(WebAppPathUtils.getURLWithContextPath(req, resp, "/admin/management/room")).forward(req, resp);
        } else {
            req.setAttribute("statusCodeErr", StatusCodeEnum.DELETE_ROOM_FAILED.getStatusCode());
//            req.getRequestDispatcher(WebAppPathUtils.getURLWithContextPath(req, resp, "/admin/management/room")).forward(req, resp);
        }

        hibernateSessionProvider.closeSession();
    }

    @Override
    public void destroy() {
        roomServices = null;
        hibernateSessionProvider = null;
    }
}
