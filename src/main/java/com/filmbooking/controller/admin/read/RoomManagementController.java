package com.filmbooking.controller.admin.read;

import com.filmbooking.hibernate.HibernateSessionProvider;
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

@WebServlet("/room-management")
public class RoomManagementController extends HttpServlet {
    private IRoomServices roomServices;
    private HibernateSessionProvider hibernateSessionProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        roomServices = new RoomServicesImpl(hibernateSessionProvider);

        req.setAttribute("pageTitle", "roomManagementTitle");
        req.setAttribute("roomData", roomServices.getAll());


        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getAdminPagesPath("room-management.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));

        hibernateSessionProvider.closeSession();
    }

    @Override
    public void destroy() {
        roomServices = null;
        hibernateSessionProvider = null;
    }
}
