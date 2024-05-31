package com.filmbooking.controller.admin.read;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Room;
import com.filmbooking.page.AdminPage;
import com.filmbooking.page.Page;
import com.filmbooking.services.impls.RoomServicesImpl;
import com.filmbooking.utils.Pagination;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "roomManagement", value = "/admin/management/room")
public class RoomManagementController extends HttpServlet {
    private static final int LIMIT = 10;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoomServicesImpl roomServices = new RoomServicesImpl();
        Page roomManagementPage = new AdminPage(
                "roomManagementTitle",
                "room-management",
                "master"
        );

        Pagination<Room> pagination = new Pagination<>(roomServices, req, resp, LIMIT, "admin/management/room");

        roomManagementPage.putAttribute("roomData", pagination.getPaginatedRecords());
        roomManagementPage.render(req, resp);
    }
}

