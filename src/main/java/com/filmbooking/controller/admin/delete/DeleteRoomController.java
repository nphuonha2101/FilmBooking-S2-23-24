package com.filmbooking.controller.admin.delete;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Room;
import com.filmbooking.services.IRoomServices;
import com.filmbooking.services.impls.RoomServicesImpl;
import com.filmbooking.statusEnums.StatusCodeEnum;
import com.filmbooking.utils.PathUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "deleteRoom", value = "/admin/delete/room")
public class DeleteRoomController extends HttpServlet {
    private IRoomServices roomServices;
    private HibernateSessionProvider hibernateSessionProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        roomServices = new RoomServicesImpl(hibernateSessionProvider);

        String roomSlug = req.getParameter("room");
        System.out.println("DeleteFilmController Test: " + roomSlug);

        Room deletedRoom = roomServices.getBySlug(roomSlug);
        if (roomServices.delete(deletedRoom)) {
            req.setAttribute("statusCodeSuccess", StatusCodeEnum.DELETE_ROOM_SUCCESSFUL.getStatusCode());
            req.getRequestDispatcher(PathUtils.getURLWithContextPath(req, "/admin/management/room")).forward(req, resp);
        } else {
            req.setAttribute("statusCodeErr", StatusCodeEnum.DELETE_ROOM_FAILED.getStatusCode());
            req.getRequestDispatcher(PathUtils.getURLWithContextPath(req, "/admin/management/room")).forward(req, resp);
        }

        hibernateSessionProvider.closeSession();
    }

    @Override
    public void destroy() {
        roomServices = null;
        hibernateSessionProvider = null;
    }
}
