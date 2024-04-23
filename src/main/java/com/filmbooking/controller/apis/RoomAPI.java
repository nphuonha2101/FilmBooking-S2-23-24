package com.filmbooking.controller.apis;

import java.io.IOException;
import java.util.List;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Film;
import com.filmbooking.model.Room;
import com.filmbooking.services.impls.RoomServicesImpl;
import com.filmbooking.utils.APIUtils;
import com.filmbooking.utils.gsonUtils.GSONUtils;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/api/v1/rooms/*", "/api/v1/rooms" })
public class RoomAPI extends HttpServlet {
    RoomServicesImpl roomServicesImpl;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateSessionProvider sessionProvider = new HibernateSessionProvider();
        roomServicesImpl = new RoomServicesImpl(sessionProvider);

        APIUtils<Room> apiUtils = new APIUtils<>(roomServicesImpl, req, resp);
        String command = req.getParameter("command");

        apiUtils.processRequest(command);
        apiUtils.writeResponse(null, 0);
    }
}
