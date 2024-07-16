package com.filmbooking.controller.apis;

import java.io.IOException;

import com.filmbooking.model.Room;
import com.filmbooking.services.impls.RoomServicesImpl;
import com.filmbooking.utils.APIUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/api/v1/rooms/*", "/api/v1/rooms" })
public class RoomAPI extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoomServicesImpl roomServicesImpl = new RoomServicesImpl();

        APIUtils<Room> apiUtils = new APIUtils<>(roomServicesImpl, req, resp);
        String command = req.getParameter("command");

        apiUtils.processRequest(command);
        apiUtils.writeResponse(null, 0);
    }
}
