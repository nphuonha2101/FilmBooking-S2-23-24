package com.filmbooking.controller.admin.read;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Room;
import com.filmbooking.services.IRoomServices;
import com.filmbooking.services.impls.RoomServicesImpl;
import com.filmbooking.utils.ContextPathUtils;
import com.filmbooking.utils.RenderViewUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/room-management")
public class RoomManagementController extends HttpServlet {
    private IRoomServices roomServices;
    private HibernateSessionProvider hibernateSessionProvider;
    private static final int LIMIT = 10;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        roomServices = new RoomServicesImpl(hibernateSessionProvider);

        int offset = 0;
        int currentPage = 1;

        if (req.getParameter("page") != null) {
            currentPage = Integer.parseInt(req.getParameter("page"));
            offset = (currentPage - 1) * LIMIT;
        }

        int totalPages = (int) Math.ceil((double) roomServices.getTotalRecords() / LIMIT);

        if (currentPage < 0 || currentPage > totalPages) resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        else {
            List<Room> rooms = roomServices.getByOffset(offset, LIMIT);

            req.setAttribute("currentPage", currentPage);
            req.setAttribute("totalPages", totalPages);
            req.setAttribute("roomData", rooms);
            req.setAttribute("pageUrl", "room-management");

            req.setAttribute("pageTitle", "roomManagementTitle");

            RenderViewUtils.renderViewToLayout(req, resp, ContextPathUtils.getAdminPagesPath("room-management.jsp"), ContextPathUtils.getLayoutPath("master.jsp"));
        }
        hibernateSessionProvider.closeSession();
    }

    @Override
    public void destroy() {
        roomServices = null;
        hibernateSessionProvider = null;
    }
}
