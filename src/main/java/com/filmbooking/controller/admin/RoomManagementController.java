package com.filmbooking.controller.admin;

import com.filmbooking.model.Room;
import com.filmbooking.services.IRoomServices;
import com.filmbooking.services.IRoomViewServices;
import com.filmbooking.services.impls.RoomServicesImpl;
import com.filmbooking.services.impls.RoomViewServicesImpl;
import com.filmbooking.utils.ContextPathUtils;
import com.filmbooking.utils.RenderViewUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/room-management")
public class RoomManagementController extends HttpServlet {
    private IRoomViewServices roomViewServices;
    private IRoomServices roomServices;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        roomViewServices = new RoomViewServicesImpl();
        roomServices = new RoomServicesImpl();

        req.setAttribute("pageTitle", "Film Booking - Quản lý phòng");
        req.setAttribute("sectionTitle", "Quản lý phòng");
        req.setAttribute("roomData", roomViewServices.getAll());

        req.setAttribute("roomAvailableSeatsMap", roomServices.countAvailableSeats());

        RenderViewUtils.updateView(req, resp,
                ContextPathUtils.getAdminPagesPath("room-management.jsp"));
        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getAdminPagesPath("room-management.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));
    }
}
