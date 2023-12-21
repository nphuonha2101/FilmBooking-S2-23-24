package com.filmbooking.controller.admin.read;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Room;
import com.filmbooking.services.IRoomServices;
import com.filmbooking.services.impls.RoomServicesImpl;
import com.filmbooking.utils.PaginationUtils;
import com.filmbooking.utils.PathUtils;
import com.filmbooking.utils.RenderViewUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "roomManagement", value = "/admin/management/room")
public class RoomManagementController extends HttpServlet {
    private IRoomServices roomServices;
    private HibernateSessionProvider hibernateSessionProvider;
    private static final int LIMIT = 10;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        roomServices = new RoomServicesImpl(hibernateSessionProvider);

        int currentPage = 1;
        int totalPages = (int) Math.ceil((double) roomServices.getTotalRecords() / LIMIT);
        int offset = PaginationUtils.handlesPagination(LIMIT, currentPage, totalPages, req, resp);

        // if page valid (offset != -2)
        if (offset != -2) {
            // if page has data (offset != -1)
            if (offset != -1) {
                List<Room> rooms = roomServices.getByOffset(offset, LIMIT);

                req.setAttribute("roomData", rooms);
                // set page url for pagination
                req.setAttribute("pageUrl", "admin/management/room");

            }
            req.setAttribute("pageTitle", "roomManagementTitle");
            RenderViewUtils.renderViewToLayout(req, resp, PathUtils.getAdminPagesPath("room-management.jsp"), PathUtils.getLayoutPath("master.jsp"));
        }

        hibernateSessionProvider.closeSession();
    }

    @Override
    public void destroy() {
        roomServices = null;
        hibernateSessionProvider = null;
    }
}
