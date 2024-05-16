package com.filmbooking.controller.admin.read;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Room;
import com.filmbooking.page.AdminPage;
import com.filmbooking.page.Page;
import com.filmbooking.services.impls.RoomServicesImpl;
import com.filmbooking.utils.PaginationUtils;
import com.filmbooking.utils.WebAppPathUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "roomManagement", value = "/admin/management/room")
public class RoomManagementController extends HttpServlet {
    private RoomServicesImpl roomServices;
    private HibernateSessionProvider hibernateSessionProvider;
    private static final int LIMIT = 10;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        roomServices = new RoomServicesImpl(hibernateSessionProvider);

        int currentPage = 1;
        int totalPages = (int) Math.ceil((double) roomServices.getTotalRecordRows() / LIMIT);
        int offset = PaginationUtils.handlesPagination(LIMIT, currentPage, totalPages, req, resp);

        Page roomManagementPage = new AdminPage(
                "roomManagementTitle",
                "room-management",
                "master"
        );


        // if page valid (offset != -2)
        if (offset != -2) {
            // if page has data (offset != -1)
            if (offset != -1) {
                List<Room> rooms = roomServices.getByOffset(offset, LIMIT).getMultipleResults();
                roomManagementPage.putAttribute("roomData", rooms);
                // set page url for pagination
                roomManagementPage.putAttribute("pageUrl", "admin/management/room");

            }
           roomManagementPage.render(req, resp);
        }

        hibernateSessionProvider.closeSession();
    }

    @Override
    public void destroy() {
        roomServices = null;
        hibernateSessionProvider = null;
    }
}
